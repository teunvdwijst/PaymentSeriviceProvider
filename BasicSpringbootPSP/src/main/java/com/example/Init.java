package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Teun
 */
@Component
public class Init implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Init.class);
    private final InvoiceReplyRepository replyRepository;
    private final InvoiceRequestRepository requestRepository;

    @Autowired
    public Init(InvoiceReplyRepository replyRepository, InvoiceRequestRepository requestRepository) {
        this.replyRepository = replyRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AppGateway gatewayClient1 = new AppGateway("PSPToClient1", "Client1ToPSP");
        AppGateway gatewayClient2 = new AppGateway("PSPToClient2", "Client2ToPSP");

        gatewayClient1.onRequestArrived(requestRepository, replyRepository);
        gatewayClient2.onRequestArrived(requestRepository, replyRepository);
    }
}
