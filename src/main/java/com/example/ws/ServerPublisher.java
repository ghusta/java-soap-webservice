package com.example.ws;

import jakarta.xml.ws.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerPublisher {

    private static final Logger log = LoggerFactory.getLogger(ServerPublisher.class);

    public static void main(String[] args) {
        int port = 8080; // Port par défaut

        // 1️⃣ Check system property (-Dserver.port=XXXX)
        String portProperty = System.getProperty("server.port");

        // 4️⃣ Check command-line argument
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                log.warn("Numéro de port invalide en argument CLI, utilisation des autres méthodes...");
            }
        } else {
            // Apply the first valid value (priority order: CLI > System Property > Env > Config File > Default)
            if (portProperty != null) {
                try {
                    port = Integer.parseInt(portProperty);
                } catch (NumberFormatException e) {
                    log.warn("Numéro de port invalide en propriété système, utilisation des autres méthodes...");
                }
            }
        }

        String url = "http://localhost:%d/ws/hello".formatted(port);
        Endpoint.publish(url, new HelloServiceImpl());
        log.info("Service SOAP publié à : {}?wsdl ", url);
    }

}
