package br.com.lab.smartcity.domain;

import javax.persistence.Entity;

@Entity
public class Cachorro extends Animal {
	private int num_patas;

	public int getNum_patas() {
		return num_patas;
	}

	public void setNum_patas(int num_patas) {
		this.num_patas = num_patas;
	}
	
}
