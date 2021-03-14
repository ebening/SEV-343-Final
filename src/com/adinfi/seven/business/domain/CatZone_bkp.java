package com.adinfi.seven.business.domain;

import java.util.Set;

public class CatZone_bkp implements java.io.Serializable {

	private Integer idZone;
	private CatGZone catGZone;
	private CatTipoZona catTipoZona;
	private String code;
//	private String descrip;
//	private Set<CatStore> catStores = new HashSet<CatStore>(0);

	public CatZone_bkp() {
		
	}

	public CatZone_bkp(Integer idZone, CatGZone catGZone, CatTipoZona catTipoZona,
			String code) {
		this.idZone = idZone;
		this.catGZone = catGZone;
		this.catTipoZona = catTipoZona;
		this.code = code;
	}

	public CatZone_bkp(Integer idZone, CatGZone catGZone, CatTipoZona catTipoZona,
			String code, String descrip, Set<CatStore> catStores) {
		this.idZone = idZone;
		this.catGZone = catGZone;
		this.catTipoZona = catTipoZona;
		this.code = code;
//		this.descrip = descrip;
//		this.catStores = catStores;
	}

	public Integer getIdZone() {
		return this.idZone;
	}

	public void setIdZone(Integer idZone) {
		this.idZone = idZone;
	}

	public CatGZone getCatGZone() {
		return this.catGZone;
	}

	public void setCatGZone(CatGZone catGZone) {
		this.catGZone = catGZone;
	}

	public CatTipoZona getCatTipoZona() {
		return this.catTipoZona;
	}

	public void setCatTipoZona(CatTipoZona catTipoZona) {
		this.catTipoZona = catTipoZona;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
/*
	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Set<CatStore> getCatStores() {
		return this.catStores;
	}

	public void setCatStores(Set<CatStore> catStores) {
		this.catStores = catStores;
	}
*/
}
