package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class CatTipoPromo implements java.io.Serializable {

	private int idTipoPromo;
	private String nombre;
	private Set<CatPromo> catPromos = new HashSet<CatPromo>(0);

	public CatTipoPromo() {
	}

	public CatTipoPromo(int idTipoPromo) {
		this.idTipoPromo = idTipoPromo;
	}

	public CatTipoPromo(int idTipoPromo, String nombre, Set<CatPromo> catPromos) {
		this.idTipoPromo = idTipoPromo;
		this.nombre = nombre;
		this.catPromos = catPromos;
	}

	public int getIdTipoPromo() {
		return this.idTipoPromo;
	}

	public void setIdTipoPromo(int idTipoPromo) {
		this.idTipoPromo = idTipoPromo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<CatPromo> getCatPromos() {
		return this.catPromos;
	}

	public void setCatPromos(Set<CatPromo> catPromos) {
		this.catPromos = catPromos;
	}
	
	@Override
	public String toString(){
		return nombre;
	}

}
