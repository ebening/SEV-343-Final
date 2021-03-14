package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class TblCadenaAutorizacion implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idCadenaAutorizacion;
	private String nombreCadena;
	private Set<TblSolicitudAutorizacion> tblSolicitudAutorizacions = new HashSet<TblSolicitudAutorizacion>(
			0);
	private Set<TblCampanaAutorizacion> tblCampanaAutorizacions = new HashSet<TblCampanaAutorizacion>(
			0);
	private Set<TblCadenaAutorizacionDet> tblCadenaAutorizacionDets = new HashSet<TblCadenaAutorizacionDet>(
			0);

	public TblCadenaAutorizacion() {
	}

	public TblCadenaAutorizacion(String nombreCadena) {
		this.nombreCadena = nombreCadena;
	}

	public TblCadenaAutorizacion(int idCadenaAutorizacion, String nombreCadena,
			Set<TblSolicitudAutorizacion> tblSolicitudAutorizacions,
			Set<TblCampanaAutorizacion> tblCampanaAutorizacions,
			Set<TblCadenaAutorizacionDet> tblCadenaAutorizacionDets) {
		this.idCadenaAutorizacion = idCadenaAutorizacion;
		this.nombreCadena = nombreCadena;
		this.tblSolicitudAutorizacions = tblSolicitudAutorizacions;
		this.tblCampanaAutorizacions = tblCampanaAutorizacions;
		this.tblCadenaAutorizacionDets = tblCadenaAutorizacionDets;
	}

	public int getIdCadenaAutorizacion() {
		return this.idCadenaAutorizacion;
	}

	public void setIdCadenaAutorizacion(int idCadenaAutorizacion) {
		this.idCadenaAutorizacion = idCadenaAutorizacion;
	}

	public String getNombreCadena() {
		return this.nombreCadena;
	}

	public void setNombreCadena(String nombreCadena) {
		this.nombreCadena = nombreCadena;
	}

	public Set<TblSolicitudAutorizacion> getTblSolicitudAutorizacions() {
		return this.tblSolicitudAutorizacions;
	}

	public void setTblSolicitudAutorizacions(
			Set<TblSolicitudAutorizacion> tblSolicitudAutorizacions) {
		this.tblSolicitudAutorizacions = tblSolicitudAutorizacions;
	}

	public Set<TblCampanaAutorizacion> getTblCampanaAutorizacions() {
		return this.tblCampanaAutorizacions;
	}

	public void setTblCampanaAutorizacions(
			Set<TblCampanaAutorizacion> tblCampanaAutorizacions) {
		this.tblCampanaAutorizacions = tblCampanaAutorizacions;
	}

	public Set<TblCadenaAutorizacionDet> getTblCadenaAutorizacionDets() {
		return this.tblCadenaAutorizacionDets;
	}

	public void setTblCadenaAutorizacionDets(
			Set<TblCadenaAutorizacionDet> tblCadenaAutorizacionDets) {
		this.tblCadenaAutorizacionDets = tblCadenaAutorizacionDets;
	}
}