package com.adinfi.seven.business.domain;

public class TblJuntaParticipantes implements java.io.Serializable{
	private static final long serialVersionUID = -8017079674437977283L;
	private int id;
	private TblJuntaPlaneacion tblJuntaPlaneacion;
	private int idUsuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TblJuntaPlaneacion getTblJuntaPlaneacion() {
		return tblJuntaPlaneacion;
	}

	public void setTblJuntaPlaneacion(TblJuntaPlaneacion tblJuntaPlaneacion) {
		this.tblJuntaPlaneacion = tblJuntaPlaneacion;
	}
	
}
