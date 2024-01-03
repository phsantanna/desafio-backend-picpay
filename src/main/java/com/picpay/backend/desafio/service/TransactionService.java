package com.picpay.backend.desafio.service;


import com.picpay.backend.desafio.model.GroupType;
import com.picpay.backend.desafio.model.Transaction;
import com.picpay.backend.desafio.model.Usuario;
import com.picpay.backend.desafio.model.dto.TransactionDto;
import com.picpay.backend.desafio.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;
    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        Usuario sender = usuarioService.findUserById(transactionDto.senderId());
        Usuario receiver = usuarioService.findUserById(transactionDto.receiverId());

        usuarioService.validateTransaction(sender, transactionDto.amount());

        boolean isAuthorized = this.authorizeTransaction(sender, transactionDto.amount());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDate.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.amount()));

        this.transactionRepository.save(newTransaction);
        this.usuarioService.salvarUsuario(sender);
        this.usuarioService.salvarUsuario(receiver);

        this.notificationService.enviarMensagem(sender, "Transação realizada com sucesso.");
        this.notificationService.enviarMensagem(receiver, "Transação recebida com sucesso.");

        return newTransaction;
    }

    public boolean authorizeTransaction(Usuario sender, BigDecimal amount) {
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",Map.class);
        if (authorizeResponse.getStatusCode().equals(HttpStatus.OK)) {
            String message = (String) authorizeResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }


    public List<Transaction> listarTransacoes() {
        return this.transactionRepository.findAll();
    }
}
