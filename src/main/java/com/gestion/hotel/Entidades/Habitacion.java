package com.gestion.hotel.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "habitacion",schema="public")
public class Habitacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numeroHabitacion", nullable = false)
	private String numeroHabitacion;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "precio", nullable = false, unique = true)
	private Float precio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estadohabitacionId", nullable = false)
	private Estadohabitacion estadohabitacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nivelpisoId", nullable = false)
	private Nivelhabitacion nivelpiso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipohabitacionId", nullable = false)
	private Tipohabitacion tipohabitacion;
	
	public Habitacion() {

	}
	
	public Habitacion(Long id) {
		this.id = id;
	}
	
	public Habitacion(Long id, String numeroHabitacion, String descripcion, Float precio,
			Estadohabitacion estadohabitacion, Nivelhabitacion nivelpiso, Tipohabitacion tipohabitacion) {
		super();
		this.id = id;
		this.numeroHabitacion = numeroHabitacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estadohabitacion = estadohabitacion;
		this.nivelpiso = nivelpiso;
		this.tipohabitacion = tipohabitacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Estadohabitacion getEstadohabitacion() {
		return estadohabitacion;
	}

	public void setEstadohabitacion(Estadohabitacion estadohabitacion) {
		this.estadohabitacion = estadohabitacion;
	}

	public Nivelhabitacion getNivelpiso() {
		return nivelpiso;
	}

	public void setNivelpiso(Nivelhabitacion nivelpiso) {
		this.nivelpiso = nivelpiso;
	}

	public Tipohabitacion getTipohabitacion() {
		return tipohabitacion;
	}

	public void setTipohabitacion(Tipohabitacion tipohabitacion) {
		this.tipohabitacion = tipohabitacion;
	}
		
	
}
