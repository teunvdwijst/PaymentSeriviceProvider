package com.example;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Teun
 */
public class InvoiceRequestRouter {

    public static InvoiceReply handleInvoiceRequest(InvoiceRequest request) {
        switch (request.getPaymentMethod().toLowerCase()) {
            case "paypal":

                try {
                    Payment payment = InvoiceTranslator.toPayPalInvoice(request);

                    APIContext apiContext = new APIContext(PayPalApiKeys.CLIENT_ID, PayPalApiKeys.CLIENT_SECRET, PayPalApiKeys.PAYPAL_ENVIRONMENT);
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

                throw new NotImplementedException();

            case "google pay":

                throw new NotImplementedException();

            default:
                return null;
        }
    }
}
