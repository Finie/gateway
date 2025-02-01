package com.finstudio.gateway.interceptors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class GatewayNetworkInterceptor  implements ClientHttpRequestInterceptor {



    private final String apiKey;

    public GatewayNetworkInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Intercepts the client application request
     * logs the request headers and add new items
     * to the request header such as the api-key
     * or bearer token before call
     * */

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        // add request headers to your outgoing network request
        request.getHeaders().set("api_key", apiKey);
        System.out.println("🚀 Sending Request: \n[" + request.getMethod() + "]: " + request.getURI());
        request.getHeaders().forEach((key, value)-> {
            System.out.println("🔹 " + key + " : " + value);
        });

        return execution.execute(request, body);
    }
}
