package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class CatPrograma implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idPrograma;
	private String nombre;
	private Set<CatSenal> catSenals = new HashSet<CatSenal>(0);

	public CatPrograma() {
	}

	public CatPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public CatPrograma(int idPrograma, String nombre) {
		this.idPrograma = idPrograma;
		this.nombre = nombre;
	}
	
	public CatPrograma(int idPrograma, String nombre, Set<CatSenal> catSenals) {
		this.idPrograma = idPrograma;
		this.nombre = nombre;
		this.catSenals = catSenals;
	}

	public int getIdPrograma() {
		return this.idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<CatSenal> getCatSenals() {
		return this.catSenals;
	}

	public void setCatSenals(Set<CatSenal> catSenals) {
		this.catSenals = catSenals;
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}

}