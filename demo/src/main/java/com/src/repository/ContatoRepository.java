package com.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.src.model.Contatos;

import java.util.List;
import java.util.Optional;



public interface ContatoRepository extends JpaRepository<Contatos, Long> {
    Optional<Contatos> findByIdcontato(Long id);
    List<Contatos> findByNomeIgnoreCaseContaining(String nome);
}
