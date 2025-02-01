package com.finstudio.gateway.config;


import com.finstudio.gateway.interceptors.GatewayNetworkInterceptor;
import com.finstudio.gateway.interceptors.NetworkRequestErrorHandlers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class AppConfig {

    /**
     * This file creates an instance of
     * RestTemplate with custom interceptor
     * and custom error handlers before making
     * network request
     * */

    private final ApiConfig apiConfig;

    public AppConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;  // Inject ApiConfig into the AppConfig class
    }



    @Bean
    public RestTemplate restTemplate(){

        RestTemplate restTemplate = new RestTemplate(getHttpClientRequestFactory());

        // setting up header interceptors before a restTemplate instance is created for a network call
        restTemplate.setInterceptors(List.of(new GatewayNetworkInterceptor(apiConfig.getKey())));

        // setting up a custom error handler before a network call
        restTemplate.setErrorHandler(new NetworkRequestErrorHandlers());
        return restTemplate;
    }


    private ClientHttpRequestFactory getHttpClientRequestFactory(){
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(apiConfig.getConnectionTimeout());
        factory.setReadTimeout(apiConfig.getReadTimeout());
        return  factory;
    }


}
