/**
 * 
 */
package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

/**
 * @author OMAR
 *
 */
public class SubcategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String codigo;
	
	private Integer idCategoria;
	
	private boolean mercancia;
	
	

	/**
	 * @return the mercancia
	 */
	public boolean isMercancia() {
		return mercancia;
	}

	/**
	 * @param mercancia the mercancia to set
	 */
	public void setMercancia(boolean mercancia) {
		this.mercancia = mercancia;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

}
