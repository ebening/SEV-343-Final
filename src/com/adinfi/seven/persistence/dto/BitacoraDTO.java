package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitacoraDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bitacoraId;
	private Integer usuarioId;
	private String ip;
	private String dao;
	private String metodo;
	private Date fechaMov = new Date();

	/**
	 * @return the bitacoraId
	 */
	public int getBitacoraId() {
		return bitacoraId;
	}

	/**
	 * @param bitacoraId
	 *            the bitacoraId to set
	 */
	public void setBitacoraId(int bitacoraId) {
		this.bitacoraId = bitacoraId;
	}

	/**
	 * @return the usuarioId
	 */
	public Integer getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId
	 *            the usuarioId to set
	 */
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the dao
	 */
	public String getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(String dao) {
		this.dao = dao;
	}

	/**
	 * @return the metodo
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * @param metodo
	 *            the metodo to set
	 */
	public void setMetodo(String metodo) {
		this.metodo = metodo;
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

	public String getFechaMovStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(fechaMov);
	}
}
