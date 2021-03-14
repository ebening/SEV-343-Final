package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class CatTipoMedioDTO implements Serializable {

	private static final long serialVersionUID = -2677873888541771015L;
	private Integer id;
	private String code;
	private CatMedioDTO catMedio;

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
	 * @return the catmedio
	 */
	public CatMedioDTO getCatMedio() {
		return catMedio;
	}

	/**
	 * @param catmedio
	 *            the catmedio to set
	 */
	public void setCatMedio(CatMedioDTO catMedio) {
		this.catMedio = catMedio;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof CatTipoMedioDTO)) {
			return false;
		}
		CatTipoMedioDTO other = (CatTipoMedioDTO) object;
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
