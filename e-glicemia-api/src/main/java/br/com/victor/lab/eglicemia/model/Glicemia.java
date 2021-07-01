package br.com.victor.lab.eglicemia.model;

import javax.persistence.*;
import java.time.LocalDateTime;
//import java.time.LocalDateTime;

@Entity
@Table(name = "glicemia")
public class Glicemia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float valor;
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    //@JsonFormat(pattern="yyyy-MM-dd")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
