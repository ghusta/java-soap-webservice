package com.example.ws;

import jakarta.xml.ws.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerPublisher {

    private static final Logger log = LoggerFactory.getLogger(ServerPublisher.class);

    public static void main(String[] args) throws UnknownHostException {
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

        String hostIp = InetAddress.getLocalHost().getHostAddress();
        String loopbackIp = InetAddress.getLoopbackAddress().getHostAddress();

        String url = "http://%s:%d/ws/hello".formatted(loopbackIp, port);
        Endpoint endpoint = Endpoint.publish(url, new HelloServiceImpl());
        log.info("SOAP endpoint for service '{}' published at : {}?wsdl ",
                HelloServiceImpl.class.getName(), url);
        if (endpoint.getExecutor() != null) {
            log.info("Endpoint using executor {}", endpoint.getExecutor().getClass().getName());
        }
    }

}
