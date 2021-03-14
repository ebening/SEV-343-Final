package com.adinfi.seven.persistence.dto;

public class SubCategoriasDTO {
	public String code;
	public Integer id;
	public CategoriaDTO categoria;
	public Character mercancia;

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
	 * @return the categoria
	 */
	public CategoriaDTO getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the mercancia
	 */
	public Character getMercancia() {
		return mercancia;
	}

	/**
	 * @param mercancia
	 *            the mercancia to set
	 */
	public void setMercancia(Character mercancia) {
		this.mercancia = mercancia;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof SubCategoriasDTO)) {
			return false;
		}
		SubCategoriasDTO other = (SubCategoriasDTO) object;
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
