package br.com.anderson.SpringApi.entity;

import br.com.anderson.SpringApi.entity.ParqueEolico;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="complexo_eolico")
public class ComplexoEolico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="uf", nullable=false)
	private String uf;
	
	@Column(name="identificador", nullable=false)
	private String identificador;
	
	@JsonIgnore
	@OneToMany(mappedBy="complexo_eolico", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<ParqueEolico> parques;
	
	public ComplexoEolico() {}
	
	public ComplexoEolico(String nome, String uf, String identificador) {
		this.nome = nome;
		this.uf = uf;
		this.identificador = identificador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	

	public Set<ParqueEolico> getParques() {
		return parques;
	}

	public void setParques(Set<ParqueEolico> parques) {
		this.parques = parques;
	}
}
