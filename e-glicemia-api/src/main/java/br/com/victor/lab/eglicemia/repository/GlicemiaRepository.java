package br.com.victor.lab.eglicemia.repository;

import br.com.victor.lab.eglicemia.model.Glicemia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {
}
