package appgateway;

import com.owlike.genson.Genson;
import domain.*;

/**
 *
 * @author Teun
 */
public class Serializer {

    private final Genson genson = new Genson();

    public Serializer() {
    }

    public String requestToString(InvoiceRequest request) {
        return genson.serialize(request);
    }

    public InvoiceRequest requestFromString(String str) {
        return genson.deserialize(str, InvoiceRequest.class);
    }

    public String replyToString(InvoiceReply reply) {
        return genson.serialize(reply);
    }

    public InvoiceReply replyFromString(String str) {
        return genson.deserialize(str, InvoiceReply.class);
    }
}
