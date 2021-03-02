package br.com.lab.eglicemia.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_alarme")
public class Alarme {
	@Id
	@SequenceGenerator(name="alarme", sequenceName= "sq_tb_alarme", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="alarme")
	@Column(name = "id_alarme")
	private int id;
	
	private Boolean ativo;
	
	private LocalTime hora;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Alarme() {
		super();
	}
	
	public Alarme(Boolean ativo, LocalTime hora) {
		super();
		this.ativo = ativo;
		this.hora = hora;
	}

}
