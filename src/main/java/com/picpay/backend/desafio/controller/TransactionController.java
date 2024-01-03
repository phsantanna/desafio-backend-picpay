package com.picpay.backend.desafio.controller;


import com.picpay.backend.desafio.model.Transaction;
import com.picpay.backend.desafio.model.dto.TransactionDto;
import com.picpay.backend.desafio.repository.TransactionRepository;
import com.picpay.backend.desafio.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<Transaction> criarTransacao(@RequestBody @Valid TransactionDto transactionDto) throws Exception {
        Transaction transaction = this.transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listarTransacoes(){
        List<Transaction> transacoes = this.transactionService.listarTransacoes();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

}
