package com.adinfi.seven.business.domain;

public class TblCampanaProgramasPlazas implements java.io.Serializable{
	private static final long serialVersionUID = 111112424635193249L;
	private TblCampanaProgramas tblCampanaProgramas;
	private int idPlaza;
	private long id;
	
	public TblCampanaProgramas getTblCampanaProgramas() {
		return tblCampanaProgramas;
	}
	public void setTblCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) {
		this.tblCampanaProgramas = tblCampanaProgramas;
	}
	public int getIdPlaza() {
		return idPlaza;
	}
	public void setIdPlaza(int idPlaza) {
		this.idPlaza = idPlaza;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
