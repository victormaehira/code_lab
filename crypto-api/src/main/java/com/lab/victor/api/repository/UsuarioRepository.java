package com.lab.victor.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.victor.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
	
}
