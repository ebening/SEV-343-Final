package com.adinfi.seven.business.domain;

import java.util.Date;

public class TblDelegacionActividades implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idDelegacion;
	private String idUsuarioDelega;
	private String idUsuarioDelegado;
	private Date fechaInicio;
	private Date fechaFin;

	public TblDelegacionActividades() {
	}

	public TblDelegacionActividades(int idDelegacion, String idUsuarioDelega,
			String idUsuarioDelegado, Date fechaInicio, Date fechaFin) {
		this.idDelegacion = idDelegacion;
		this.idUsuarioDelega = idUsuarioDelega;
		this.idUsuarioDelegado = idUsuarioDelegado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public int getIdDelegacion() {
		return this.idDelegacion;
	}

	public void setIdDelegacion(int idDelegacion) {
		this.idDelegacion = idDelegacion;
	}

	public String getIdUsuarioDelega() {
		return this.idUsuarioDelega;
	}

	public void setIdUsuarioDelega(String idUsuarioDelega) {
		this.idUsuarioDelega = idUsuarioDelega;
	}

	public String getIdUsuarioDelegado() {
		return this.idUsuarioDelegado;
	}

	public void setIdUsuarioDelegado(String idUsuarioDelegado) {
		this.idUsuarioDelegado = idUsuarioDelegado;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
