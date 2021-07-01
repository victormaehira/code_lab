package br.com.victor.lab.eglicemia.resource;

import br.com.victor.lab.eglicemia.model.Alarme;
import br.com.victor.lab.eglicemia.repository.AlarmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alarmes")
public class AlarmeResource {
    @Autowired
    private AlarmeRepository alarmeRepository;

    @PostMapping
    public ResponseEntity<Alarme> criar(@RequestBody Alarme alarme, HttpServletResponse response) {
        Alarme alarmeSalvo = alarmeRepository.save(alarme);
        return ResponseEntity.status(HttpStatus.CREATED).body(alarmeSalvo);
    }

    @GetMapping
    public List<Alarme> listar() {
        return alarmeRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alarme> remover(@PathVariable Long id) {
        Optional<Alarme> alarme = alarmeRepository.findById(id);
        if (alarme.isPresent()) {
            alarmeRepository.delete(alarme.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alarme> atualizar(@PathVariable Long id, @RequestBody Alarme alarme) {
        try {
            Optional<Alarme> alarmeEncontrado = alarmeRepository.findById(id);
            if (alarmeEncontrado.isPresent()) {
                Alarme alarmeSalvo = alarmeEncontrado.get();
                BeanUtils.copyProperties(alarme, alarmeSalvo, "id");
                return ResponseEntity.ok(alarmeRepository.save(alarmeSalvo));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
