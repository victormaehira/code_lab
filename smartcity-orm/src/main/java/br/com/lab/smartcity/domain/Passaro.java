package br.com.lab.smartcity.domain;

import javax.persistence.Entity;

@Entity
public class Passaro extends Animal {
	private int num_asas;

	public int getNum_asas() {
		return num_asas;
	}

	public void setNum_asas(int num_asas) {
		this.num_asas = num_asas;
	}

}
