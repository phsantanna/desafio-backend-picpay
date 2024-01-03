package com.picpay.backend.desafio.model.dto;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionDto(@NotNull BigDecimal amount, @NotNull Long senderId, @NotNull Long receiverId) {
}
