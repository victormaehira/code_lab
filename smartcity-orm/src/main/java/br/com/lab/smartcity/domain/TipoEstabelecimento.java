package br.com.lab.smartcity.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_estabelecimento")
public class TipoEstabelecimento {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="tipo", sequenceName="sq_tb_tipo", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tipo")
	@Column(name = "id_tipo_estabelecimento")
	private int id;

	@Column(name = "nm_tipo_estabelecimento", length=25, nullable=false)
	private String nome;
	
	@OneToMany(mappedBy = "tipo")
	private Collection<Estabelecimento> estabelecimentos;
	

	public Collection<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}

	public void setEstabelecimentos(Collection<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoEstabelecimento(int id, String nome, Collection<Estabelecimento> estabelecimentos) {
		super();
		this.id = id;
		this.nome = nome;
		this.estabelecimentos = estabelecimentos;
	}

	public TipoEstabelecimento() {
		super();
		// TODO Auto-generated constructor stub
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
		TipoEstabelecimento other = (TipoEstabelecimento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	

}
