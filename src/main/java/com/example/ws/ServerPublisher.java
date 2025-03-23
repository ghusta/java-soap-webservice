package com.example.ws;

import jakarta.xml.ws.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerPublisher {

    private static final Logger log = LoggerFactory.getLogger(ServerPublisher.class);

    public static void main(String[] args) {
        String url = "http://localhost:8080/ws/hello";
        Endpoint.publish(url, new HelloServiceImpl());
        log.info("Service SOAP publié à : {}?wsdl ", url);
    }

}
