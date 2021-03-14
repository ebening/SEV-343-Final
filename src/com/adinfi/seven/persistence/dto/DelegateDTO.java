package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.Date;

public class DelegateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private UsuarioDTO delega;
	private UsuarioDTO delegado;
	private Date fechaInicio;
	private Date fechaFin;

	/**
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the delega
	 */
	public UsuarioDTO getDelega() {
		return delega;
	}

	/**
	 * @param delega
	 *            the delega to set
	 */
	public void setDelega(UsuarioDTO delega) {
		this.delega = delega;
	}

	/**
	 * @return the delegado
	 */
	public UsuarioDTO getDelegado() {
		return delegado;
	}

	/**
	 * @param delegado
	 *            the delegado to set
	 */
	public void setDelegado(UsuarioDTO delegado) {
		this.delegado = delegado;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}
