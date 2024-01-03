package com.picpay.backend.desafio.model.dto;


import com.picpay.backend.desafio.model.GroupType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record   UsuarioDto(@NotNull String nomeCompleto,
                        @NotNull String cpf, @NotNull String email,
                        @NotNull String senha, @NotNull GroupType groupType,
                        @NotNull BigDecimal balance) {


}
