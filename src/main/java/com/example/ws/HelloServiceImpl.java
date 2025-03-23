package com.example.ws;

import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(endpointInterface = "com.example.ws.HelloService")
public class HelloServiceImpl implements HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String sayHello(String name) throws HelloServiceException {
        log.info("sayHello called !");
        if (name == null || name.trim().isEmpty()) {
            String errorMessage = "Le paramètre 'name' ne peut pas être vide.";
            // throwSoapException(errorMessage);
            throw new HelloServiceException(errorMessage);
        }
        return "Bonjour, " + name + "!";
    }

    private static void throwSoapException(String message) throws SOAPException {
        SOAPFault fault = SOAPFactory.newInstance().createFault(
                message,
                new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/envelope/", "Client")
        );
        throw new SOAPFaultException(fault);
    }
}
