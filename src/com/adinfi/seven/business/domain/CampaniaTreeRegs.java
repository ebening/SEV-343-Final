package com.adinfi.seven.business.domain;

import java.io.Serializable;

/**
 * 
 * @author christian
 */
public class CampaniaTreeRegs implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int anio;
	private String tipoCampania;
	private Integer id;
	private Integer idTipoCampania;

	public CampaniaTreeRegs() {
	}

	public CampaniaTreeRegs(String tipoCampania, Integer anio, String nombre, int id) {
		this.nombre = nombre;
		this.anio = anio;
		this.tipoCampania = tipoCampania;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getTipoCampania() {
		return tipoCampania;
	}

	public void setTipoCampania(String tipoCampania) {
		this.tipoCampania = tipoCampania;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdTipoCampania() {
		return idTipoCampania;
	}

	public void setIdTipoCampania(Integer idCampania) {
		this.idTipoCampania = idCampania;
	}

	@Override
	public String toString() {
		return getNombre();
	}

}
