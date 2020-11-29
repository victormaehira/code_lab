package com.lab.victor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.victor.api.model.Chave;
import com.lab.victor.api.repository.chave.ChaveRepositoryQuery;

public interface ChaveRepository extends JpaRepository<Chave, Long> , ChaveRepositoryQuery {

}
