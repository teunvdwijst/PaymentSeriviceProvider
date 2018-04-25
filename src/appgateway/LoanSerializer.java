package appgateway;

import com.owlike.genson.Genson;
import domain.*;

/**
 *
 * @author Teun
 */
public class LoanSerializer {

    public LoanSerializer() {
    }

    public String requestToString(InvoiceRequest request) {
        return new Genson().serialize(request);

    }

    public InvoiceRequest requestFromString(String str) {
        return new Genson().deserialize(str, InvoiceRequest.class);

    }

    public String replyToString(InvoiceReply reply) {
        return new Genson().serialize(reply);
    }

    public InvoiceReply replyFromString(String str) {
        return new Genson().deserialize(str, InvoiceReply.class);
    }
}
