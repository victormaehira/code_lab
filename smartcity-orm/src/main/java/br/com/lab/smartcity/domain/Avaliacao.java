package br.com.lab.smartcity.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_avaliacao")
//@IdClass(AvaliacaoId.class)
public class Avaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576972718312637932L;

	@EmbeddedId
	private AvaliacaoId id;

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	//@Id
	@JoinColumn(name = "id_usuario", insertable = false, updatable = false)
	//@JoinColumn(name = "id_usuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	

	//@Id
	@JoinColumn(name = "id_estabelecimento", insertable = false, updatable = false)
	//@JoinColumn(name = "id_estabelecimento")
	@ManyToOne(optional = false)
	private Estabelecimento estabelecimento;
	
	@Column(name = "vl_nota")
	private int nota;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Avaliacao(AvaliacaoId id, Usuario usuario, Estabelecimento estabelecimento, int nota) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.estabelecimento = estabelecimento;
		this.nota = nota;
	}

	public Avaliacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AvaliacaoId getId() {
		return id;
	}

	public void setId(AvaliacaoId id) {
		this.id = id;
	}

	
}