package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class CatGZone_bkp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idGrupoZona;
	private String code;

	public CatGZone_bkp() {
	}

	public CatGZone_bkp(Integer idGrupoZona, String code) {
		this.idGrupoZona = idGrupoZona;
		this.code = code;
	}

	public CatGZone_bkp(int idGrupoZona, String code) {
		this.idGrupoZona = idGrupoZona;
		this.code = code;
	}

	public Integer getIdGrupoZona() {
		return this.idGrupoZona;
	}

	public void setIdGrupoZona(Integer idGrupoZona) {
		this.idGrupoZona = idGrupoZona;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}