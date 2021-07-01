package br.com.victor.lab.eglicemia.resource;

import br.com.victor.lab.eglicemia.model.Glicemia;
import br.com.victor.lab.eglicemia.model.Glicemia;
import br.com.victor.lab.eglicemia.repository.GlicemiaRepository;
import br.com.victor.lab.eglicemia.repository.GlicemiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/glicemias")
public class GlicemiaResource {
    @Autowired
    private GlicemiaRepository glicemiaRepository;

    @PostMapping
    public ResponseEntity<Glicemia> criar(@RequestBody Glicemia glicemia, HttpServletResponse response) {
        Glicemia glicemiaSalvo = glicemiaRepository.save(glicemia);
        return ResponseEntity.status(HttpStatus.CREATED).body(glicemiaSalvo);
    }

    @GetMapping
    public List<Glicemia> listar() {
        return glicemiaRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Glicemia> remover(@PathVariable Long id) {
        Optional<Glicemia> glicemia = glicemiaRepository.findById(id);
        if (glicemia.isPresent()) {
            glicemiaRepository.delete(glicemia.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Glicemia> atualizar(@PathVariable Long id, @RequestBody Glicemia glicemia) {
        try {
            Optional<Glicemia> glicemiaEncontrado = glicemiaRepository.findById(id);
            if (glicemiaEncontrado.isPresent()) {
                Glicemia glicemiaSalvo = glicemiaEncontrado.get();
                BeanUtils.copyProperties(glicemia, glicemiaSalvo, "id");
                return ResponseEntity.ok(glicemiaRepository.save(glicemiaSalvo));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
