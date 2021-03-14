package com.adinfi.seven.business.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TblSolicitudAutorizacion implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long idAutorizacion;
	private TblCampana tblCampana;
	private TblCadenaAutorizacion tblCadenaAutorizacion;
	private Date fechaModificacion;
	private int idResponsable;
	private Date fechaCaptura;
	private int idPeriodo;
	private int usuarioCreacion;
	private Date fechaCreacion;
	private Integer usuarioModificacion;
	private Set<TblCampanaAutorizacion> tblCampanaAutorizacions = new HashSet<TblCampanaAutorizacion>(
			0);

	public TblSolicitudAutorizacion() {
	}

	public TblSolicitudAutorizacion(long idAutorizacion, TblCampana tblCampana,
			int idResponsable, Date fechaCaptura, int idPeriodo,
			int usuarioCreacion, Date fechaCreacion) {
		this.idAutorizacion = idAutorizacion;
		this.tblCampana = tblCampana;
		this.idResponsable = idResponsable;
		this.fechaCaptura = fechaCaptura;
		this.idPeriodo = idPeriodo;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public TblSolicitudAutorizacion(long idAutorizacion, TblCampana tblCampana,
			TblCadenaAutorizacion tblCadenaAutorizacion,
			Date fechaModificacion, int idResponsable, Date fechaCaptura,
			int idPeriodo, int usuarioCreacion, Date fechaCreacion,
			Integer usuarioModificacion,
			Set<TblCampanaAutorizacion> tblCampanaAutorizacions) {
		this.idAutorizacion = idAutorizacion;
		this.tblCampana = tblCampana;
		this.tblCadenaAutorizacion = tblCadenaAutorizacion;
		this.fechaModificacion = fechaModificacion;
		this.idResponsable = idResponsable;
		this.fechaCaptura = fechaCaptura;
		this.idPeriodo = idPeriodo;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.tblCampanaAutorizacions = tblCampanaAutorizacions;
	}
	
	public TblSolicitudAutorizacion(TblCampana tblCampana,
			TblCadenaAutorizacion tblCadenaAutorizacion, int idResponsable, Date fechaCaptura,
			int idPeriodo, int usuarioCreacion, Date fechaCreacion) {
		this.tblCampana = tblCampana;
		this.tblCadenaAutorizacion = tblCadenaAutorizacion;
		this.idResponsable = idResponsable;
		this.fechaCaptura = fechaCaptura;
		this.idPeriodo = idPeriodo;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public long getIdAutorizacion() {
		return this.idAutorizacion;
	}

	public void setIdAutorizacion(long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}

	public TblCampana getTblCampana() {
		return this.tblCampana;
	}

	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

	public TblCadenaAutorizacion getTblCadenaAutorizacion() {
		return this.tblCadenaAutorizacion;
	}

	public void setTblCadenaAutorizacion(
			TblCadenaAutorizacion tblCadenaAutorizacion) {
		this.tblCadenaAutorizacion = tblCadenaAutorizacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getIdResponsable() {
		return this.idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}

	public Date getFechaCaptura() {
		return this.fechaCaptura;
	}

	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public int getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public int getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(int usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(Integer usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Set<TblCampanaAutorizacion> getTblCampanaAutorizacions() {
		return this.tblCampanaAutorizacions;
	}

	public void setTblCampanaAutorizacions(
			Set<TblCampanaAutorizacion> tblCampanaAutorizacions) {
		this.tblCampanaAutorizacions = tblCampanaAutorizacions;
	}

}
