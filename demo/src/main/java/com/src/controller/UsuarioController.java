package com.src.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.src.model.Usuarios;
import com.src.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    
    // Com esse controller, podemos criar um usuário/adicionar um usuário no banco 
    @PostMapping("/criar")
    public ResponseEntity<Usuarios> criarUsuario(@RequestBody Usuarios usuarios){

        Usuarios novoUsuarios = usuarioService.criarUsuario(usuarios.getNome(), 
                                                            usuarios.getSobrenome(),
                                                            usuarios.getEmail(), 
                                                            usuarios.getSenhaHash()
                                                            );


        if (novoUsuarios != null ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarios);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/buscarPorEmail")
    public ResponseEntity<Usuarios> buscarUsuarioPorEmail(@RequestParam String email) {

        Usuarios usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para editar um usuário
    @PutMapping("/editar/{idUsuario}")
    public ResponseEntity<Usuarios> editarUsuario(@PathVariable Long idUsuario, @RequestBody Usuarios usuario) {
        Usuarios usuarioEditar = usuarioService.editarUsuario(idUsuario, 
                                                              usuario.getNome(), 
                                                              usuario.getSobrenome(), 
                                                              usuario.getEmail()
                                                             );
        if (usuarioEditar != null) {
            return ResponseEntity.ok(usuarioEditar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
        
    // Endpoint para excluir um usuário
    @DeleteMapping("/excluir/{idUsuario}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long idUsuario, @RequestParam String confirmacao) {
        boolean deleted = usuarioService.excluirUsuario(idUsuario, confirmacao);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao excluir o usuário.");
        }
    }
    
}

