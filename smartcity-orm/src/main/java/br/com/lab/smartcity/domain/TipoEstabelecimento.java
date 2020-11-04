package br.com.lab.smartcity.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_estabelecimento")
public class TipoEstabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_estabelecimento")
	private Integer id;
	
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

}
