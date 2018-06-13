package appgateway;

import domain.InvoiceReply;
import domain.InvoiceRequest;
import domain.TableRow;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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

    public String newRequest(InvoiceRequest request) {
        String gson = serializer.requestToString(request);
        return sendMessage(gson);
    }

    public void newReply(InvoiceReply reply) {
        String gson = serializer.replyToString(reply);
        sendMessage(gson);
    }

    private String sendMessage(String message) {
        Message msg = sender.createTextMessage(message);
        try {
            sender.send(msg);
            return msg.getJMSMessageID();
        } catch (JMSException ex) {
            Logger.getLogger(AppGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void onReplyArrived(ObservableList<TableRow> list, Map<String, TableRow> map) {
        receiver.setListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                try {
                    ActiveMQTextMessage message = (ActiveMQTextMessage) msg;
                    InvoiceReply ir = serializer.replyFromString(message.getText());
                    String correlationId = message.getCorrelationId();
                    list.remove(map.get(correlationId));

                    InvoiceRequest request = ir.getRequest();
                    TableRow newRow = new TableRow(request.toString(), ir.toString());
                    map.put(correlationId, newRow);
                    list.add(newRow);
                } catch (JMSException ex) {
                    Logger.getLogger(AppGateway.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
