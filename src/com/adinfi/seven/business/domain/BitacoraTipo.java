package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class BitacoraTipo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int tipoId;
	private String codigo;
	private String descripcion;
	private int active;
	private Set<BitacoraMetodo> bitacoraMetodos= new HashSet<BitacoraMetodo>(0);

	/**
	 * @return the tipoId
	 */
	public int getTipoId() {
		return tipoId;
	}

	/**
	 * @param tipoId the tipoId to set
	 */
	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * @return the bitacoraMetodos
	 */
	public Set<BitacoraMetodo> getBitacoraMetodos() {
		return bitacoraMetodos;
	}

	/**
	 * @param bitacoraMetodos the bitacoraMetodos to set
	 */
	public void setBitacoraMetodos(Set<BitacoraMetodo> bitacoraMetodos) {
		this.bitacoraMetodos = bitacoraMetodos;
	}
}