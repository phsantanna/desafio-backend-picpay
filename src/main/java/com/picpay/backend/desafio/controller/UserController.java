package com.picpay.backend.desafio.controller;


import com.picpay.backend.desafio.model.Usuario;
import com.picpay.backend.desafio.model.dto.UsuarioDto;
import com.picpay.backend.desafio.service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
   private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
    Usuario usuario = usuarioService.criarUsuario(usuarioDto);
    return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){
        List<Usuario> usuarios = this.usuarioService.listarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
