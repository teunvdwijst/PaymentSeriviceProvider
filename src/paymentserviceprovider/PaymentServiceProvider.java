/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentserviceprovider;

import appgateway.AppGateway;
import domain.InvoiceReply;
import domain.InvoiceRequest;

/**
 *
 * @author Teun
 */
public class PaymentServiceProvider {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppGateway gateway = new AppGateway("ChannelName", "ChannelName");
    }
}
