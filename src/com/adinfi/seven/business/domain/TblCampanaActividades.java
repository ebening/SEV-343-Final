package com.adinfi.seven.business.domain;

import java.util.Date;

public class TblCampanaActividades implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private TblCampana tblCampana;
	private Integer idActividad;
	private Integer idUsuarioResp;
	private String nombreActividad;
	private int esFlujo;
	private CatEstatus catEstatus;
	private Date fechaCierre;
	private Date fechaInicio;
	private Date fechaFin;
	private int idRol;
	private int usuarioCreacion;
	private Date fechaCreacion;
	private Integer usuarioModificacion;
	private Date fechaModificacion;
	private int porcentajeCompletado;
	private Date fechaInicioReal;
	private Date fechaFinReal;
	private int activo= 1;

	public TblCampanaActividades() {
	}

	public TblCampanaActividades(long id, TblCampana tblCampana,
			String nombreActividad, int esFlujo, CatEstatus catEstatus, Date fechaInicio,
			Date fechaFin, int idRol, int usuarioCreacion, Date fechaCreacion) {
		this.id = id;
		this.tblCampana = tblCampana;
		this.nombreActividad = nombreActividad;
		this.esFlujo = esFlujo;
		this.catEstatus = catEstatus;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idRol = idRol;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}
	
	public TblCampanaActividades(TblCampana tblCampana,
			Integer idActividad, Integer idUsuarioResp, String nombreActividad,
			int esFlujo, CatEstatus catEstatus, Date fechaCierre, Date fechaInicio,
			Date fechaFin, int idRol, int usuarioCreacion, Date fechaCreacion,
			Integer usuarioModificacion, Date fechaModificacion,
			int porcentajeCompletado, Date fechaInicioReal, Date fechaFinReal) {
		this.tblCampana = tblCampana;
		this.idActividad = idActividad;
		this.idUsuarioResp = idUsuarioResp;
		this.nombreActividad = nombreActividad;
		this.esFlujo = esFlujo;
		this.catEstatus = catEstatus;
		this.fechaCierre = fechaCierre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idRol = idRol;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.porcentajeCompletado = porcentajeCompletado;
		this.fechaInicioReal = fechaInicioReal;
		this.fechaFinReal = fechaFinReal;
	}

	public TblCampanaActividades(long id, TblCampana tblCampana,
			Integer idActividad, Integer idUsuarioResp, String nombreActividad,
			int esFlujo, CatEstatus catEstatus, Date fechaCierre, Date fechaInicio,
			Date fechaFin, int idRol, int usuarioCreacion, Date fechaCreacion,
			Integer usuarioModificacion, Date fechaModificacion,
			int porcentajeCompletado, Date fechaInicioReal, Date fechaFinReal) {
		this.id = id;
		this.tblCampana = tblCampana;
		this.idActividad = idActividad;
		this.idUsuarioResp = idUsuarioResp;
		this.nombreActividad = nombreActividad;
		this.esFlujo = esFlujo;
		this.catEstatus = catEstatus;
		this.fechaCierre = fechaCierre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idRol = idRol;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.porcentajeCompletado = porcentajeCompletado;
		this.fechaInicioReal = fechaInicioReal;
		this.fechaFinReal = fechaFinReal;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TblCampana getTblCampana() {
		return this.tblCampana;
	}

	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

	public Integer getIdActividad() {
		return this.idActividad;
	}

	public void setIdActividad(Integer idActividad) {
		this.idActividad = idActividad;
	}

	public Integer getIdUsuarioResp() {
		return this.idUsuarioResp;
	}

	public void setIdUsuarioResp(Integer idUsuarioResp) {
		this.idUsuarioResp = idUsuarioResp;
	}

	public String getNombreActividad() {
		return this.nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public int getEsFlujo() {
		return this.esFlujo;
	}

	public void setEsFlujo(int esFlujo) {
		this.esFlujo = esFlujo;
	}

	public CatEstatus getCatEstatus() {
		return catEstatus;
	}

	public void setCatEstatus(CatEstatus catEstatus) {
		this.catEstatus = catEstatus;
	}

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
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

	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
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

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getPorcentajeCompletado() {
		return this.porcentajeCompletado;
	}

	public void setPorcentajeCompletado(int porcentajeCompletado) {
		this.porcentajeCompletado = porcentajeCompletado;
	}

	public Date getFechaInicioReal() {
		return this.fechaInicioReal;
	}

	public void setFechaInicioReal(Date fechaInicioReal) {
		this.fechaInicioReal = fechaInicioReal;
	}

	public Date getFechaFinReal() {
		return this.fechaFinReal;
	}

	public void setFechaFinReal(Date fechaFinReal) {
		this.fechaFinReal = fechaFinReal;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

}
