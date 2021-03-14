package com.adinfi.seven.business.domain;


import java.util.Objects;

public class CatSenal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idSenal;
	private CatPrograma catPrograma;
	private String nombre;

	public CatSenal() {
	}

	public CatSenal(int idSenal) {
		this.idSenal = idSenal;
	}

	public CatSenal(int idSenal, CatPrograma catPrograma) {
		this.idSenal = idSenal;
		this.catPrograma = catPrograma;
	}

	public CatSenal(int idSenal, CatPrograma catPrograma, String nombre) {
		this.idSenal = idSenal;
		this.catPrograma = catPrograma;
		this.nombre = nombre;
	}
	
	public CatSenal(int idSenal, int idPrograma, String nombre) {
		this.idSenal = idSenal;
		this.catPrograma = new CatPrograma(idPrograma);
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CatSenal catSenal = (CatSenal) o;
		return idSenal == catSenal.idSenal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSenal);
	}

	@Override
	public String toString() {
		return nombre;
	}

	public int getIdSenal() {
		return this.idSenal;
	}

	public void setIdSenal(int idSenal) {
		this.idSenal = idSenal;
	}

	public CatPrograma getCatPrograma() {
		return this.catPrograma;
	}

	public void setCatPrograma(CatPrograma catPrograma) {
		this.catPrograma = catPrograma;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}