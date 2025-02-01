package com.finstudio.gateway.interceptors;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class NetworkRequestErrorHandlers implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }


    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        System.out.println("Api Error ❌: method: "+method.name()+" Scheme path: "+ url.getScheme()+url.getRawSchemeSpecificPart()+" Error: "+new String(response.getBody().readAllBytes()));
       // throw custom interceptors from here
    }


}
