package br.com.lab.smartcity.domain;

import javax.persistence.Entity;

@Entity
public class Carro extends Veiculo{
	private int numPortas;

	public int getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(int numPortas) {
		this.numPortas = numPortas;
	}
}
