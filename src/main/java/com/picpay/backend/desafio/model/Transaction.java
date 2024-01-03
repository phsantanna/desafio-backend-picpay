package com.picpay.backend.desafio.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Transaction")
@Table(name = "tb_transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Usuario sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Usuario receiver;
    private LocalDate timestamp;
}
