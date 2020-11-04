package br.com.lab.smartcity.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_contato_aluguel")
public class ContratoAluguel {
	@Id
	@Column(name="id_contrato")
	private int id;
	
	@Column(name="vl_aluguel")
	private float valor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_vencimento_aluguel")
	private Calendar dataVencimeto;
	
	@OneToOne
	@JoinColumn(name="id_estabelecimento")
	private Estabelecimento estabelecimento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Calendar getDataVencimeto() {
		return dataVencimeto;
	}

	public void setDataVencimeto(Calendar dataVencimeto) {
		this.dataVencimeto = dataVencimeto;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
}
