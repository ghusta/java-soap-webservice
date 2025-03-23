package com.example.ws.client;

import com.example.ws.HelloService;
import jakarta.xml.ws.Service;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Locale;

public class HelloClient {

    private static final Logger log = LoggerFactory.getLogger(HelloClient.class);

    public static void main(String[] args) throws Exception {
        URL wsdlURL = new URL("http://localhost:8080/ws/hello?wsdl");
        QName qname = new QName("http://ws.example.com/", "HelloServiceImplService");

        Service service = Service.create(wsdlURL, qname);
        HelloService helloService = service.getPort(HelloService.class);

        try {
            Faker faker = new Faker(Locale.FRENCH);

            // Générer un prénom aléatoire
            String prenom = faker.name().firstName();

            String result = helloService.sayHello(prenom);
//            String result = helloService.sayHello(null);
            log.info("-> {}", result);
        } catch (Exception e) {
            // SOAPFaultException can be ServerSOAPFaultException for example
            log.error(e.toString());
        }
    }
}