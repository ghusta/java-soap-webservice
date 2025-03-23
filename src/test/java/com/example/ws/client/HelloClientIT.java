package com.example.ws.client;

import com.example.ws.HelloService;
import com.example.ws.HelloServiceException;
import jakarta.xml.ws.Service;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class HelloClientIT {

    private static final Logger log = LoggerFactory.getLogger(HelloClientIT.class);

    private HelloService helloService;

    private Faker faker;

    @BeforeEach
    void setUp() throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:8080/ws/hello?wsdl");
        QName qname = new QName("http://ws.example.com/", "HelloServiceImplService");

        Service service = Service.create(wsdlURL, qname);
        helloService = service.getPort(HelloService.class);

        faker = new Faker(Locale.FRANCE);
    }

    @Test
    void sayHello_ok() throws HelloServiceException {
        // Générer un prénom aléatoire
        String prenom = faker.name().firstName();

        String result = helloService.sayHello(prenom);
        assertThat(result).isNotEmpty();
        log.info("-> {}", result);
    }

    @Test
    void sayHello_fault() {
        Throwable thrown = catchThrowable(() -> {
            helloService.sayHello(null);
        });
        // SOAPFaultException can be ServerSOAPFaultException for example
        assertThat(thrown).isInstanceOf(HelloServiceException.class);
    }

}
