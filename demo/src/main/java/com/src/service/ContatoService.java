package com.src.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.model.Contatos;
import com.src.repository.ContatoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }
    

    public void criarContato(Contatos contatos) {
       contatoRepository.save(contatos);
    }

    public void editarContatos(Long idcontato, Contatos contatos) {
        
        if (contatoRepository.existsById(idcontato)){
            contatos.setIdcontato(idcontato);
            contatoRepository.save(contatos);
        } else {
            throw new EntityNotFoundException("Contato não encontrado");
        }
    }

    public void deletarContato (Long idcontato) {

        if (contatoRepository.existsById(idcontato)){
            contatoRepository.deleteById(idcontato);
        } else {
            throw new EntityNotFoundException("Contato não encontrado");
        }
    }

    public Contatos buscarContatoPorId(Long idcontato) {

        return contatoRepository.findByIdcontato(idcontato)
        .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
    }


    public List<Contatos> buscarContatoPorNome(String nome) {
        if(nome != null) {
            return contatoRepository.findByNomeIgnoreCaseContaining(nome);
        } else {
            return contatoRepository.findAll();
        }
    }






}
