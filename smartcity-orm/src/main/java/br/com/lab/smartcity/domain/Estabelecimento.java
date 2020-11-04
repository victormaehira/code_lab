package br.com.lab.smartcity.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_estabelecimento")
public class Estabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estabelecimento")
	private Integer id;

	@Column(name = "nm_estabelecimento", length = 50)
	private String nome;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao")
	private Calendar dataCriacao;

	//@Formula("(select avg(a.nota)+1 from avaliacao a where a.id_estabelecimento = id_estabelecimento)")
	//private Double mediaAvaliacoes;
    @JoinColumn(name = "id_tipo_estabelecimento")
	@ManyToOne
	private TipoEstabelecimento tipo;
    
    //qdo eh bidirecional, precisa do mappedBy
    @OneToOne(mappedBy="estabelecimento")
    private ContratoAluguel contrato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public TipoEstabelecimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEstabelecimento tipo) {
		this.tipo = tipo;
	}

	public ContratoAluguel getContrato() {
		return contrato;
	}

	public void setContrato(ContratoAluguel contrato) {
		this.contrato = contrato;
	}

}
