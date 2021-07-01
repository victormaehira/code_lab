package br.com.victor.lab.eglicemia.repository;

import br.com.victor.lab.eglicemia.model.Alarme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmeRepository extends JpaRepository<Alarme, Long> {
}
