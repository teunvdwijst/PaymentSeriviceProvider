package com.example;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teun
 */
public class InvoiceTranslator {

    private static final String CANCELURL = "https://example.com/cancel";
    private static final String RETURNURL = "https://example.com/return";

    public static Payment toPayPalInvoice(InvoiceRequest invoice) {
        Amount amount = new Amount();
        amount.setCurrency(invoice.getCurrency().toUpperCase());
        amount.setTotal(String.valueOf(invoice.getPrice()));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(CANCELURL);
        redirectUrls.setReturnUrl(RETURNURL);
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    public static InvoiceReply toInvoiceReply(Payment createdPayment) {
        return new InvoiceReply(createdPayment.getState(), createdPayment.getIntent(), createdPayment.getCreateTime());
    }

//    public static Payment toIDealInvoice(InvoiceRequest invoice) {
//        return null;
//    }
//
//    public static InvoiceReply toInvoiceReply(Object idealPayment) {
//        return null;
//    }
//
//    public static Payment toGooglePayInvoice(InvoiceRequest invoice) {
//        return null;
//    }
//
//    public static InvoiceReply toInvoiceReply(Object googlePayment) {
//        return null;
//    }
}
