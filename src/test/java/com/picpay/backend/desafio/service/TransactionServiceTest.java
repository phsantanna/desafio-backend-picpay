package com.picpay.backend.desafio.service;

import com.picpay.backend.desafio.model.GroupType;
import com.picpay.backend.desafio.model.Usuario;
import com.picpay.backend.desafio.model.dto.TransactionDto;
import com.picpay.backend.desafio.repository.TransactionRepository;
import com.picpay.backend.desafio.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Service
class TransactionServiceTest {

    @Mock
    private UsuarioService usuarioService;


    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private NotificationService notificationService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createTransaction() throws Exception {
        Usuario usuarioSender = new Usuario(1L,"Paulo","123","abc@gmail.com","123", GroupType.COMUM, new BigDecimal(1000));
        Usuario usuarioReceiver = new Usuario(2L,"Pedro","132","abd@gmail.com","124", GroupType.COMUM, new BigDecimal(1000));

        when(usuarioService.findUserById(1L)).thenReturn(usuarioSender);
        when(usuarioService.findUserById(2L)).thenReturn(usuarioReceiver);


        TransactionDto requisicao = new TransactionDto(new BigDecimal(100),1L, 2L);

        transactionService.createTransaction(requisicao);
        when(transactionService.authorizeTransaction(usuarioSender,new BigDecimal(100))).thenReturn(true);

        verify(transactionRepository,times(1)).save(any());

        usuarioSender.setBalance(new BigDecimal(900));
        verify(usuarioService,times(1)).salvarUsuario(usuarioSender);

        usuarioReceiver.setBalance(new BigDecimal(1100));
        verify(usuarioService,times(1)).salvarUsuario(usuarioReceiver);

        verify(notificationService,times(1)).enviarMensagem(usuarioSender,"Transação enviada.");
        verify(notificationService,times(1)).enviarMensagem(usuarioReceiver,"Transação recebida.");



    }

    @Test
    void createTransaction2(){

    }
}