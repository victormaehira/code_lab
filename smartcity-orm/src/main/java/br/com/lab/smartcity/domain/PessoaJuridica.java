package br.com.lab.smartcity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaJuridica extends Pessoa {

	@Column(name="nr_cnpj")
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
