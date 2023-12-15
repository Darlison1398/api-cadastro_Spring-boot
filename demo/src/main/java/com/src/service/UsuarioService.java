package com.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.src.model.Usuarios;
import com.src.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    

    // Criar
    public Usuarios criarUsuario(Usuarios novoUsuario ){
        // método que vai obter os dados e salvar no banco
        return usuarioRepository.save(novoUsuario);     
    }


    // Buscar usuário por email
    public Usuarios buscarPorEmail(String email) {
        return usuarioRepository.findByEmail( email);
    }

    public Usuarios buscarId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!!!"));
    }


  

    // Editar
    public Usuarios editarUsuario(Long usuarioId, Usuarios editarUsuario ) {

        Usuarios usuarioExistente = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuarioExistente == null) {
            return null;
        }

        if (editarUsuario.getNome() != null) {
            usuarioExistente.setNome(editarUsuario.getNome());
        }

        if( editarUsuario.getSobrenome() != null) {
            usuarioExistente.setSobrenome(editarUsuario.getSobrenome());
        }

        if( editarUsuario.getEmail() != null) {
            usuarioExistente.setEmail(editarUsuario.getEmail());
        }

        if ( editarUsuario.getSenha() != null ) {
            usuarioExistente.setSenha(editarUsuario.getSenha());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    public boolean excluirUsuario(Long idUsuario ) {
        
        Usuarios usuarioExistente = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuarioExistente == null) {
            // Tratamento para quando o usuário não for encontrado
            return false;
        }
    
        usuarioRepository.delete(usuarioExistente);
        return true;

    }

}
