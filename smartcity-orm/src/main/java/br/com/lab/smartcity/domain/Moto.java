package br.com.lab.smartcity.domain;

import javax.persistence.Entity;

@Entity
public class Moto extends Veiculo {

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
