package br.com.lab.smartcity.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AvaliacaoId implements Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	@Column(name = "id_usuario")
	private int usuario;
	
	@Column(name = "id_estabelecimento")
	private int estabelecimento;

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(int estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estabelecimento;
		result = prime * result + usuario;
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
		AvaliacaoId other = (AvaliacaoId) obj;
		if (estabelecimento != other.estabelecimento)
			return false;
		if (usuario != other.usuario)
			return false;
		return true;
	}

	public AvaliacaoId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AvaliacaoId(int usuario, int estabelecimento) {
		super();
		this.usuario = usuario;
		this.estabelecimento = estabelecimento;
	}

	
}
