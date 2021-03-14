package com.adinfi.seven.business.domain;

import java.util.Date;

public class CatItem_bkp_jorge implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7605277960128491048L;
	
	private String idItem;
	private String code;
	private CatSubCategory catSubCategory;
	private String marca;
	private Date fechaCreacion;
	private int existencia;
	
	public CatItem_bkp_jorge() {
	}
	
	public CatItem_bkp_jorge(String idItem, String code, CatSubCategory catSubCategory,
			String marca, Date fechaCreacion, int existencia) {
		super();
		this.idItem = idItem;
		this.code = code;
		this.catSubCategory = catSubCategory;
		this.marca = marca;
		this.fechaCreacion = fechaCreacion;
		this.existencia = existencia;
	}

	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public CatSubCategory getCatSubCategory() {
		return catSubCategory;
	}
	public void setCatSubCategory(CatSubCategory catSubCategory) {
		this.catSubCategory = catSubCategory;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getExistencia() {
		return existencia;
	}
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	@Override
	public String toString() {
		return "CatItem [idItem=" + idItem + ", code=" + code
				+ ", catSubCategory=" + catSubCategory + ", marca=" + marca
				+ ", fechaCreacion=" + fechaCreacion + ", existencia="
				+ existencia + "]";
	}
	

}
