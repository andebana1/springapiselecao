package br.com.anderson.SpringApi.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="aerogerador")
public class Aerogerador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="latitude", nullable=false)
	private Float latitude;
	
	@Column(name="longitude", nullable=false)
	private Float longitude;
	
	@Column(name="altura_torre", nullable=false)
	private Float altura_torre;
	
	@Column(name="diametro_varredura", nullable=false)
	private Float diametro_varredura;
	
	@Column(name="modelo", nullable=false)
	private String modelo;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="parque_eolico_id", nullable=false)
	private ParqueEolico parque;
	
	public Aerogerador() {}

	public Aerogerador(String nome, Float latitude, Float longitude, Float altura_torre, Float diametro_varredura, String modelo) {
		super();
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altura_torre = altura_torre;
		this.diametro_varredura = diametro_varredura;
		this.modelo = modelo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getAltura_torre() {
		return altura_torre;
	}

	public void setAltura_torre(Float altura_torre) {
		this.altura_torre = altura_torre;
	}

	public Float getDiametro_varredura() {
		return diametro_varredura;
	}

	public void setDiametro_varredura(Float diametro_varredura) {
		this.diametro_varredura = diametro_varredura;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public ParqueEolico getParque() {
		return parque;
	}

	public void setParque(ParqueEolico parque) {
		this.parque = parque;
	}
}
