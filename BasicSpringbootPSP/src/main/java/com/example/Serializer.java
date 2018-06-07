package com.example;

import com.google.gson.Gson;

/**
 *
 * @author Teun
 */
public class Serializer {

    private final Gson gson = new Gson();

    public Serializer() {
    }

    public String requestToString(InvoiceRequest request) {
        return gson.toJson(request);
    }

    public InvoiceRequest requestFromString(String str) {
        return gson.fromJson(str, InvoiceRequest.class);
    }

    public String replyToString(InvoiceReply reply) {
        return gson.toJson(reply);
    }

    public InvoiceReply replyFromString(String str) {
        return gson.fromJson(str, InvoiceReply.class);
    }
}
