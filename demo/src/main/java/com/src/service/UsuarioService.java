package com.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Usuarios criarUsuario(String nome, String sobrenome, String email, String senhaHash ){

        Usuarios novoUsuario = new Usuarios();
        novoUsuario.setNome(nome);
        novoUsuario.setSobrenome(sobrenome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenhaHash(senhaHash);

        // método que vai obter os dados e salvar no banco
        return usuarioRepository.save(novoUsuario);     
    }


    // Buscar usuário por email
    public Usuarios buscarPorEmail(String email) {
        return usuarioRepository.findByEmail( email);
    }


    // Editar
    public Usuarios editarUsuario(Long usuarioId, String novoNome, String novoSobrenome, String novoEmail ) {

        Usuarios usuarioExistente = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuarioExistente == null) {
            // Lidar com o caso em que o usuário não é encontrado
            // fazer a exeção aqui
            return null;
        }

        if (novoNome != null) {
            usuarioExistente.setNome(novoNome);
        }

        if (novoSobrenome != null) {
            usuarioExistente.setSobrenome(novoSobrenome);
        }

        if (novoEmail != null) {
            Usuarios usuarioComNovoEmail = usuarioRepository.findByEmail(novoEmail);
            if (usuarioComNovoEmail != null && !usuarioComNovoEmail.getIdUsuario().equals(usuarioId)) {
                // Lidar com o caso em que o novo email já está em uso
                return null;
            }
            usuarioExistente.setEmail(novoEmail);
        }
        return usuarioRepository.save(usuarioExistente);
    }

    public boolean excluirUsuario(Long idUsuario,  String confimacao ) {
        Usuarios usuarioExistente = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuarioExistente == null) {
            // Implementar lógica para quando o usuário não for encontrado.

            return false;
        }

        if ("confirmar".equals(confimacao)) {
            usuarioRepository.delete(usuarioExistente);
            return true;
        }

        return false;

    }

}
