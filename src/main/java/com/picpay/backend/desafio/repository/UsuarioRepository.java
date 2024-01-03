package com.picpay.backend.desafio.repository;

import com.picpay.backend.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
