package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.List;

public class SucursalDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2178019039884966155L;
	private Integer id;
	private String code;
	private ZonaDTO zona;
	private List<NegocioDTO> negocios;

	/**
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the zona
	 */
	public ZonaDTO getZona() {
		return zona;
	}

	/**
	 * @param zona
	 *            the zona to set
	 */
	public void setZona(ZonaDTO zona) {
		this.zona = zona;
	}

	/**
	 * @return the negocios
	 */
	public List<NegocioDTO> getNegocios() {
		return negocios;
	}

	/**
	 * @param negocios
	 *            the negocios to set
	 */
	public void setNegocios(List<NegocioDTO> negocios) {
		this.negocios = negocios;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof SucursalDTO)) {
			return false;
		}
		SucursalDTO other = (SucursalDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return code;
	}

}
