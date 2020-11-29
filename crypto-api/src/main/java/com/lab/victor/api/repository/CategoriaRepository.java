package com.lab.victor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.victor.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
