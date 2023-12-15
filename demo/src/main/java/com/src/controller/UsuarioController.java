package com.src.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.src.repository.UsuarioRepository;
import com.src.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository){
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuarios> obteUsuarios(){
        List<Usuarios> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    
    // Com esse controller, podemos criar um usuário/adicionar um usuário no banco 
    @PostMapping("/criar")
    public ResponseEntity<Usuarios> criarUsuario(@RequestBody Usuarios novoUsuario){
        Usuarios usuarioCriado = usuarioService.criarUsuario(novoUsuario);

        if (usuarioCriado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // endpoint: http://localhost:8080/usuarios/buscarEmail?email=
    @GetMapping("/buscarEmail")
    public ResponseEntity<Usuarios> buscarUsuarioPorEmail(@RequestParam String email) {

        Usuarios usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscarId/{idUsuario}")
    public Usuarios buscarId(@PathVariable Long idUsuario){
        return usuarioService.buscarId(idUsuario);
    }



    // Endpoint para editar um usuário
    // caminho endpoint: http://localhost:8080/usuarios/editar/id
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
    public void deletar(@PathVariable Long idUsuario) {
        usuarioService.excluirUsuario(idUsuario);
    }
}

