package com.victor.vitalsigns.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.vitalsigns.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
