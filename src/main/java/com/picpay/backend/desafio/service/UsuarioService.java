package com.picpay.backend.desafio.service;


import com.picpay.backend.desafio.model.GroupType;
import com.picpay.backend.desafio.model.Usuario;
import com.picpay.backend.desafio.model.dto.UsuarioDto;
import com.picpay.backend.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public void salvarUsuario(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }
    public Usuario criarUsuario(UsuarioDto usuarioDto){
        Usuario newUsuario = new Usuario(usuarioDto);
        this.salvarUsuario(newUsuario);
        return newUsuario;
    }

    public void validateTransaction(Usuario sender, BigDecimal amount) throws Exception {
        if(sender.getGroupType() == GroupType.LOJISTA){
            throw new Exception("Usuário do tipo lojista não pode realizar transaçõoes.");
        }
        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente.");
        }

    }
    public Usuario findUserById(Long id) throws Exception {
        return this.usuarioRepository.findById(id).orElseThrow(()-> new Exception("Usuário não encontrado"));
    }

    public List<Usuario> listarTodosUsuarios(){
        return this.usuarioRepository.findAll();
    }
}
