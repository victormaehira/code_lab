package br.com.lab.smartcity.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_estabelecimento")
public class Estabelecimento {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="estabelecimento", sequenceName="sq_tb_estabelecimento", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estabelecimento")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_estabelecimento")
	private int id;

	@Column(name = "nm_estabelecimento", length = 50)
	private String nome;

    //qdo eh bidirecional, precisa do mappedBy
    @OneToOne(mappedBy="estabelecimento", cascade=CascadeType.PERSIST)
    private ContratoAluguel contrato;
    
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(joinColumns=@JoinColumn(name="id_estabelecimento"),
    inverseJoinColumns=@JoinColumn(name="id_cliente"), 
    name="tb_cliente_estabelecimento")
    private List<Cliente> clientes;

    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao")
	private Calendar dataCriacao;

	//@Formula("(select avg(a.nota)+1 from avaliacao a where a.id_estabelecimento = id_estabelecimento)")
	//private Double mediaAvaliacoes;
    @JoinColumn(name = "id_tipo_estabelecimento")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private TipoEstabelecimento tipo;
    
	private Double latitude;
	private Double longitude;
	
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estabelecimento other = (Estabelecimento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Estabelecimento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estabelecimento(int id, String nome, ContratoAluguel contrato, List<Cliente> clientes,
			TipoEstabelecimento tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.contrato = contrato;
		this.clientes = clientes;
		this.tipo = tipo;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	

	
}
