package br.com.lab.eglicemia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_tipodiabetes")
public class TipoDiabetes {
	@Id
	@SequenceGenerator(name="tipodiabetes", sequenceName= "sq_tb_tipodiabetes", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipodiabetes")
	@Column(name = "id_tipodiabetes")
	private int id;
	
	@Column(name="tipo_diabetes", nullable=false)
	private String tipo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public TipoDiabetes() {
		super();
	}
	
	public TipoDiabetes(String tipo) {
		super();
		this.tipo = tipo;
	}

}
