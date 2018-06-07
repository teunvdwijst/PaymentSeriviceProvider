package com.example;

import static com.example.PayPalApiKeys.*;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.activemq.command.ActiveMQTextMessage;

/**
 *
 * @author Teun
 */
public class AppGateway {

    private static final Logger LOGGER = Logger.getLogger(AppGateway.class.getName());
    private final MessageSenderGateway sender;
    private final MessageReceiverGateway receiver;
    private final Serializer serializer;

    public AppGateway(String senderChannelName, String receiverChannelName) {
        sender = new MessageSenderGateway(senderChannelName);
        receiver = new MessageReceiverGateway(receiverChannelName);
        serializer = new Serializer();
    }

    public void newRequest(InvoiceRequest request) {
        String gson = serializer.requestToString(request);
        sendMessage(gson, "");
    }

    public void newReply(InvoiceReply reply, String correlationId) {
        String gson = serializer.replyToString(reply);
        sendMessage(gson, correlationId);
    }

    private void sendMessage(String message, String correlationId) {
        try {
            Message msg = sender.createTextMessage(message);
            msg.setJMSCorrelationID(correlationId);
            sender.send(msg);
        } catch (JMSException ex) {
            Logger.getLogger(AppGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRequestArrived(InvoiceRequestRepository requestRepository, InvoiceReplyRepository replyRepository) {
        receiver.setListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                try {
                    ActiveMQTextMessage message = (ActiveMQTextMessage) msg;
                    InvoiceRequest request = serializer.requestFromString(message.getText());

                    // save request to database
                    requestRepository.save(request);
                    LOGGER.log(Level.INFO, "Received request: {0}", request.toString());

                    // handle request
                    InvoiceReply reply = handleInvoiceRequest(request);

                    // save reply to database
                    replyRepository.save(reply);
                    LOGGER.log(Level.INFO, "Received reply: {0}", reply.toString());

                    newReply(reply, message.getJMSMessageID());
                } catch (JMSException ex) {
                    Logger.getLogger(AppGateway.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static InvoiceReply handleInvoiceRequest(InvoiceRequest request) {
        switch (request.getPaymentMethod().toLowerCase()) {
            case "paypal":

                try {
                    Payment payment = InvoiceTranslator.toPayPalInvoice(request);

                    APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
                    Payment createdPayment = payment.create(apiContext);
                    // For debug purposes only: 
                    // System.out.println(createdPayment.toString());

                    InvoiceReply reply = InvoiceTranslator.toInvoiceReply(createdPayment);
                    reply.setRequest(request);
                    return reply;
                } catch (PayPalRESTException ex) {
                    Logger.getLogger(AppGateway.class.getName()).log(Level.SEVERE, null, ex);
                }

            case "ideal":

                return null;

            case "google pay":

                return null;

            default:
                return null;
        }
    }
}
