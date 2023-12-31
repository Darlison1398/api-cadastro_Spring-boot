package com.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.src.model.Usuarios;

public interface UsuarioRepository extends JpaRepository <Usuarios, Long>{
    Usuarios findByEmail(String email);
}
