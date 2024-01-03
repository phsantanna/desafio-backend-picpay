package com.picpay.backend.desafio.infra;

import com.picpay.backend.desafio.model.GroupType;
import com.picpay.backend.desafio.model.Usuario;
import com.picpay.backend.desafio.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",Map.class);
        if (authorizeResponse.getStatusCode().equals(HttpStatus.OK)) {
            String message = (String) authorizeResponse.getBody().get("message");
            System.out.println(authorizeResponse.getStatusCode());
            System.out.println(message);
        }
    }
}
