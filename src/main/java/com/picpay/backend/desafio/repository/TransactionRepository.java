package com.picpay.backend.desafio.repository;

import com.picpay.backend.desafio.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
