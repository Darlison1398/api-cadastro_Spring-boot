package com.src.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.src.model.Contatos;
import com.src.repository.ContatoRepository;
import com.src.service.ContatoService;

@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = "http://localhost:5173")
public class ContatoController {

    private final ContatoService contatoService;
    private final ContatoRepository contatoRepository;

    @Autowired
    public ContatoController(ContatoService contatoService, ContatoRepository contatoRepository) {
        this.contatoService = contatoService;
        this.contatoRepository = contatoRepository;
    }

    @GetMapping
    public List<Contatos> todosContatos(){
        List<Contatos> listaContatos = contatoRepository.findAll();
        return listaContatos;
    }

    @PostMapping("/criar")
    public void criar(@RequestBody Contatos contatos) {
        contatoService.criarContato(contatos);
    } 

    @PutMapping("/editar/{idcontato}")
    public void editar(@PathVariable Long idcontato, @RequestBody Contatos contatos) {
        contatoService.editarContatos(idcontato, contatos);
    }

    // endpoint: http://localhost:8080/contatos/deletar/id do contato a ser deletado
    @DeleteMapping("/deletar/{idContato}")
    public void deletar(@PathVariable Long idContato) {
        contatoService.deletarContato(idContato);
    }

    // endpoint: http://localhost:8080/contatos/buscarId/id do contato
    @GetMapping("/buscarId/{idcontato}")
    public Contatos buscaContatoId(@PathVariable Long idcontato) {
        return contatoService.buscarContatoPorId(idcontato);
    }

    // endpoint: http://localhost:8080/contatos/buscarNome/nome do contato
    @GetMapping("/buscarNome/{nome}")
    public List<Contatos> buscaContatoNome(@PathVariable String nome) {
        return contatoService.buscarContatoPorNome(nome);
    }

    

    
}
