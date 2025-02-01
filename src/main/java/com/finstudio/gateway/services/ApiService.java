package com.finstudio.gateway.services;

import com.finstudio.gateway.config.ApiConfig;
import com.finstudio.gateway.config.AppConfig;
import com.finstudio.gateway.model.ApiResponse;
import com.finstudio.gateway.model.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    private final ApiConfig apiConfig;


    @Value("${base.url}")
    private String baseURL;






 public ApiService(ApiConfig apiConfig){
     this.apiConfig = apiConfig;
     this.restTemplate = new AppConfig(apiConfig).restTemplate();
 }


 public List<UserProfile> getUserList(){
     var endpoint = "/api/user/";
     System.out.println("Making API call with RestTemplate..."+baseURL);

     //  restTemplate.exchange gives full control of http metadata getFromObject only returns body
     ResponseEntity<ApiResponse<List<UserProfile>>> response = restTemplate.exchange(
             baseURL+endpoint,
             HttpMethod.GET,
             null,
             new ParameterizedTypeReference<ApiResponse<List<UserProfile>>>() {}
     );

     return response.getBody() != null ? response.getBody().getData() : null;

 }
}
