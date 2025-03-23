package com.example.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface HelloService {

    @WebMethod
    String sayHello(String name) throws HelloServiceException;

}
