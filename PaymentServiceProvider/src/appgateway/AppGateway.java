package appgateway;

import domain.InvoiceReply;
import domain.InvoiceRequest;
import java.util.List;
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
        sendMessage(gson);
    }

    public void newReply(InvoiceReply reply) {
        String gson = serializer.replyToString(reply);
        sendMessage(gson);
    }

    private void sendMessage(String message) {
        Message msg = sender.createTextMessage(message);
        sender.send(msg);
    }

    public void onReplyArrived(List<String> list) {
        receiver.setListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                try {
                    ActiveMQTextMessage message = (ActiveMQTextMessage) msg;
                    InvoiceRequest lr = serializer.requestFromString(message.getText());
                    lr.setId(msg.getJMSMessageID());
//                    System.out.println(lr.toString());
                    list.add(lr.toString());
//                    newReply(new InvoiceReply("1", "payed"));
                    System.out.println(new InvoiceReply(msg.getJMSMessageID(), "payed").toString());
                } catch (JMSException ex) {
                    Logger.getLogger(AppGateway.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
