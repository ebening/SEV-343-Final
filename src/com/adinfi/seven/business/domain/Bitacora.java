package com.adinfi.seven.business.domain;

// Generated 26/10/2013 09:27:46 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bitacora generated by hbm2java
 */
public class Bitacora implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int bitacoraId;
	private Integer usuarioId;
	private String ip;
	private String dao;
	private String metodo;
	private Date fechaMov = new Date();
	private Set<BitacoraParam> bitacoraParams = new HashSet<BitacoraParam>(0);

	public Bitacora() {
	}

	public Bitacora(int bitacoraId) {
		this.bitacoraId = bitacoraId;
	}

	public Bitacora(int bitacoraId, Integer usuarioId, String ip, String dao,
			String metodo, Set<BitacoraParam> bitacoraParams) {
		this.bitacoraId = bitacoraId;
		this.usuarioId = usuarioId;
		this.ip = ip;
		this.dao = dao;
		this.metodo = metodo;
		this.bitacoraParams = bitacoraParams;
	}

	public int getBitacoraId() {
		return this.bitacoraId;
	}

	public void setBitacoraId(int bitacoraId) {
		this.bitacoraId = bitacoraId;
	}

	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDao() {
		return this.dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public Set<BitacoraParam> getBitacoraParams() {
		return this.bitacoraParams;
	}

	public void setBitacoraParams(Set<BitacoraParam> bitacoraParams) {
		this.bitacoraParams = bitacoraParams;
	}

	/**
	 * @return the fechaMov
	 */
	public Date getFechaMov() {
		return fechaMov;
	}

	/**
	 * @param fechaMov
	 *            the fechaMov to set
	 */
	public void setFechaMov(Date fechaMov) {
		this.fechaMov = fechaMov;
	}

}
