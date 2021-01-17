package br.com.lab.eglicemia.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_glicemia")
public class Glicemia {
	@Id
	@SequenceGenerator(name="glicemia", sequenceName= "sq_tb_glicemia", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="glicemia")
	@Column(name = "id_glicemia")
	private int id;
	
	private Float valor;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_registro")
	private Calendar data;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Glicemia() {
		super();
	}
	
	public Glicemia(Float valor, Calendar data) {
		super();
		this.valor = valor;
		this.data = data;
	}
}
