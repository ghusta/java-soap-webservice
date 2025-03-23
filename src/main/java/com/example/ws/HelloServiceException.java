package com.example.ws;

import jakarta.xml.ws.WebFault;

import java.io.Serial;

@WebFault(name = "HelloServiceException")
public class HelloServiceException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String faultInfo;

    // Constructeur avec deux paramètres (obligatoire pour JAX-WS)
    public HelloServiceException(String message, String faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    // Constructeur avec un seul paramètre
    public HelloServiceException(String message) {
        super(message);
        this.faultInfo = message; // On peut assigner le même message par défaut
    }

    public String getFaultInfo() {
        return faultInfo;
    }
}
