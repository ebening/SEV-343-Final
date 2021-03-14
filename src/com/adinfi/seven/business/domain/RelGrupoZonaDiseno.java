package com.adinfi.seven.business.domain;

public class RelGrupoZonaDiseno  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int grupoId;
	private TblDisenos disenos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(int grupoId) {
		this.grupoId = grupoId;
	}
	public TblDisenos getDisenos() {
		return disenos;
	}
	public void setDisenos(TblDisenos disenos) {
		this.disenos = disenos;
	}
}