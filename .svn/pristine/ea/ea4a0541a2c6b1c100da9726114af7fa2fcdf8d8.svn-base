package com.adinfi.seven.business.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CatItem implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private String idItem;
	private CatCategory catCategory;
	private String code;
	private Integer idSubcategory;
	private String marca;
	private Date fechaCreacion;
	private Integer existencia;
//	private Set<RelItemLista> relItemListas = new HashSet<RelItemLista>(0);
	private Set<CatListDet> catListDets = new HashSet<CatListDet>(0);
	private Set<RelItemStore> relItemStores = new HashSet<RelItemStore>(0);
	private Set<RelItemStoreProveedor> relItemStoreProveedors = new HashSet<RelItemStoreProveedor>(0);

	public CatItem() {
	}

	public CatItem(String idItem) {
		this.idItem = idItem;
	}

	public CatItem(String idItem, CatCategory catCategory, String code,
			Integer idSubcategory, String marca, Date fechaCreacion,
			Integer existencia, 
			Set<CatListDet> catListDets, Set<RelItemStore> relItemStores,
			Set<RelItemStoreProveedor> relItemStoreProveedors) {
		this.idItem = idItem;
		this.catCategory = catCategory;
		this.code = code;
		this.idSubcategory = idSubcategory;
		this.marca = marca;
		this.fechaCreacion = fechaCreacion;
		this.existencia = existencia;
//		this.relItemListas = relItemListas;
		this.catListDets = catListDets;
		this.relItemStores = relItemStores;
		this.relItemStoreProveedors = relItemStoreProveedors;
	}

	public String getIdItem() {
		return this.idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public CatCategory getCatCategory() {
		return this.catCategory;
	}

	public void setCatCategory(CatCategory catCategory) {
		this.catCategory = catCategory;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIdSubcategory() {
		return this.idSubcategory;
	}

	public void setIdSubcategory(Integer idSubcategory) {
		this.idSubcategory = idSubcategory;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getExistencia() {
		return this.existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

//	public Set<RelItemLista> getRelItemListas() {
//		return this.relItemListas;
//	}
//
//	public void setRelItemListas(Set<RelItemLista> relItemListas) {
//		this.relItemListas = relItemListas;
//	}

	public Set<CatListDet> getCatListDets() {
		return this.catListDets;
	}

	public void setCatListDets(Set<CatListDet> catListDets) {
		this.catListDets = catListDets;
	}

	public Set<RelItemStore> getRelItemStores() {
		return this.relItemStores;
	}

	public void setRelItemStores(Set<RelItemStore> relItemStores) {
		this.relItemStores = relItemStores;
	}

	public Set<RelItemStoreProveedor> getRelItemStoreProveedors() {
		return this.relItemStoreProveedors;
	}

	public void setRelItemStoreProveedors(
			Set<RelItemStoreProveedor> relItemStoreProveedors) {
		this.relItemStoreProveedors = relItemStoreProveedors;
	}
	
	@Override
	public String toString() {
		return "CatItem [idItem=" + idItem + ", code=" + code
				+ ", catCategory=" + catCategory + ", marca=" + marca
				+ ", fechaCreacion=" + fechaCreacion + ", existencia="
				+ existencia + "]";
	}
}
