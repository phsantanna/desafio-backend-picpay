package com.picpay.backend.desafio.model;
import com.picpay.backend.desafio.model.dto.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity(name = "Usuario")
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String senha;
    private GroupType groupType;
    private BigDecimal balance;

    public Usuario(UsuarioDto usuarioDto){
        this.nomeCompleto = usuarioDto.nomeCompleto();
        this.cpf = usuarioDto.cpf();
        this.email = usuarioDto.email();
        this.senha = usuarioDto.senha();
        this.groupType = usuarioDto.groupType();
        this.balance = usuarioDto.balance();
    }
}
