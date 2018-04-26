/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentserviceprovider;

import appgateway.AppGateway;
import domain.InvoiceReply;
import domain.InvoiceRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teun
 */
public class PaymentServiceProvider {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final List<String> gsonRequests = new ArrayList();
        AppGateway gateway = new AppGateway("ChannelName", "ChannelName");
        gateway.onReplyArrived(gsonRequests);

        InvoiceRequest req1 = new InvoiceRequest("0", 1.05);
        InvoiceRequest req2 = new InvoiceRequest("0", 1.10);
        InvoiceRequest req3 = new InvoiceRequest("0", 1.12);
        InvoiceRequest req4 = new InvoiceRequest("0", 1.13);
        InvoiceRequest req5 = new InvoiceRequest("0", 1.15);

        gateway.newRequest(req1);
        gateway.newRequest(req2);
        gateway.newRequest(req3);
        gateway.newRequest(req4);
        gateway.newRequest(req5);
    }
}
