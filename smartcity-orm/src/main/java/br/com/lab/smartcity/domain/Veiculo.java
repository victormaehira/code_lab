package br.com.lab.smartcity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Veiculo {
	@Id
	@SequenceGenerator(name="veiculo", sequenceName= "sq_veiculo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="veiculo")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
