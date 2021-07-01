package br.com.victor.lab.eglicemia.repository;

import br.com.victor.lab.eglicemia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
