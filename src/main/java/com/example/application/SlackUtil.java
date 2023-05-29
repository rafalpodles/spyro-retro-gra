package com.example.application;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
public class SlackUtil {
    static final String slackUrl = "changeit";

    public static void sendToChannel(String message) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{\"text\":\"" + message + "\"}";
        System.out.println(body);
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(slackUrl, HttpMethod.POST, requestEntity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("All Good!");

        } else{
            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
            throw new Exception("Something was wrong!");
        }
    }
}
