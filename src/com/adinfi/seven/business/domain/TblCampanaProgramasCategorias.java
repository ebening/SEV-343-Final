package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class TblCampanaProgramasCategorias implements java.io.Serializable, Cloneable {
	private static final long serialVersionUID = -5246354892685828329L;
	private TblCampanaProgramas tblCampanaProgramas;
	private int idCategoria;
	private long id;
	
	public TblCampanaProgramasCategorias clone() throws CloneNotSupportedException {
		TblCampanaProgramasCategorias campanaProgrCateg= (TblCampanaProgramasCategorias) super.clone();
		campanaProgrCateg.setTblCampanaProgramas(getTblCampanaProgramas().clone());
		return campanaProgrCateg;
	}
	
	public TblCampanaProgramas getTblCampanaProgramas() {
		return tblCampanaProgramas;
	}
	public void setTblCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) {
		this.tblCampanaProgramas = tblCampanaProgramas;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}