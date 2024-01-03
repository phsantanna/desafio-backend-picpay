package com.picpay.backend.desafio.service;


import com.picpay.backend.desafio.model.Usuario;
import com.picpay.backend.desafio.model.dto.NotificationDto;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void enviarMensagem(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificationDto notificationRequest = new NotificationDto(email, mensagem);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Erro ao enviar notificação");
            throw new Exception("Serviço de notificação não está funcionando");
        }
    }
}
