package br.com.lab.eglicemia.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_usuario")
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {
	@Id
	@SequenceGenerator(name="usuario", sequenceName= "sq_tb_usuario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario")
	@Column(name = "id_usuario")
	private int id;
	
	@Column(name="nome_usuario", nullable=false)
	private String nome;
	
	private String email;
	
	private String password;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_tipodiabetes")
	private TipoDiabetes tipoDiabetes;

	@OneToMany(mappedBy="usuario", cascade=CascadeType.PERSIST)
	private List<Glicemia> glicemias;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.PERSIST)
	private List<Alarme> alarmes;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoDiabetes getTipoDiabetes() {
		return tipoDiabetes;
	}

	public void setTipoDiabetes(TipoDiabetes tipoDiabetes) {
		this.tipoDiabetes = tipoDiabetes;
	}

	public List<Glicemia> getGlicemias() {
		return glicemias;
	}

	public void setGlicemias(List<Glicemia> glicemias) {
		this.glicemias = glicemias;
	}

	public List<Alarme> getAlarmes() {
		return alarmes;
	}

	public void setAlarmes(List<Alarme> alarmes) {
		this.alarmes = alarmes;
	}
	
	public Usuario() {
		super();
	}

	public Usuario(String nome, String email, String password, TipoDiabetes tipoDiabetes, List<Glicemia> glicemias,
			List<Alarme> alarmes) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.tipoDiabetes = tipoDiabetes;
		this.glicemias = glicemias;
		this.alarmes = alarmes;
	}

}
