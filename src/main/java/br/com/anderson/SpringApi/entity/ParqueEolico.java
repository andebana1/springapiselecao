package br.com.anderson.SpringApi.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="parque_eolico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class ParqueEolico implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="latitude", nullable=false)
	private Integer latitude;
	
	@Column(name="longitude", nullable=false)
	private Integer longitude;
	
	@Column(name="potencia_instalada", nullable=false)
	private Float potencia_instalada;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="complexo_eolico_id", nullable=false)
	private ComplexoEolico complexo_eolico;
	
	@JsonIgnore
	@OneToMany(mappedBy="parque", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Aerogerador> aerogerador;
	
	public ParqueEolico () {}

	public ParqueEolico(String nome, Integer latitude, Integer longitude, Float potencia_instalada) {
		super();
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.potencia_instalada = potencia_instalada;
	}
	

	public ComplexoEolico getComplexo_eolico() {
		return complexo_eolico;
	}

	public void setComplexo_eolico(ComplexoEolico complexo_eolico) {
		this.complexo_eolico = complexo_eolico;
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

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Float getPotencia_instalada() {
		return potencia_instalada;
	}

	public void setPotencia_instalada(Float potencia_instalada) {
		this.potencia_instalada = potencia_instalada;
	}

	public Set<Aerogerador> getAerogerador() {
		return aerogerador;
	}

	public void setAerogerador(Set<Aerogerador> aerogerador) {
		this.aerogerador = aerogerador;
	}
}
