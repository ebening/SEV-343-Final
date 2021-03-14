package com.adinfi.seven.business.domain;

import java.util.Date;

public class TblDisenador implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idEmpleado;
	private String nombreEmpleado;
	private short activo;
	private Date fechaCreacion;
	private int idUsuarioCreacion;
	private Date fechaModificacion;
	private Integer idUsuarioModificacion;

	public TblDisenador() {
	}

	public TblDisenador(int idEmpleado, String nombreEmpleado, short activo,
			Date fechaCreacion, int idUsuarioCreacion) {
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public TblDisenador(int idEmpleado, String nombreEmpleado, short activo,
			Date fechaCreacion, int idUsuarioCreacion, Date fechaModificacion,
			Integer idUsuarioModificacion) {
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.idUsuarioCreacion = idUsuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public int getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return this.nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public short getActivo() {
		return this.activo;
	}

	public void setActivo(short activo) {
		this.activo = activo;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(int idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getIdUsuarioModificacion() {
		return this.idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

}
