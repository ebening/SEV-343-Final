package com.adinfi.seven.business.domain;


public class CatLista implements java.io.Serializable {

	private int idLista;
	private String code;
/*	private CatCategory catCategory;
	private CatSubCategory catSubCategory;
	private Set<CatItem> catItems = new HashSet<CatItem>(0);
	private Set<CatListDet> catListDets = new HashSet<CatListDet>(0); */

	public CatLista() {
	}

	public CatLista(int idLista, String code) {
		this.idLista = idLista;
		this.code = code;
	}

	/*	public CatLista(Integer idLista, String code, int idCategory, int idSubcategory,
			CatCategory catCategory, CatSubCategory catSubcategory,
			Set<CatItem> catItems, Set<CatListDet> catListDets) { 
		this.idLista = idLista;
		this.code = code;
		this.catCategory = catCategory;
		this.catSubCategory = catSubcategory;
		this.catItems = catItems;
		this.catListDets = catListDets;
	} */

	public Integer getIdLista() {
		return this.idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

/*	public CatCategory getCatCategory() {
		return catCategory;
	}

	public void setCatCategory(CatCategory catCategory) {
		this.catCategory = catCategory;
	}

	public Set<CatItem> getCatItems() {
		return catItems;
	}

	public void setCatItems(Set<CatItem> catItems) {
		this.catItems = catItems;
	}

	public CatSubCategory getCatSubCategory() {
		return catSubCategory;
	}

	public void setCatSubCategory(CatSubCategory catSubCategory) {
		this.catSubCategory = catSubCategory;
	}
	
	public Set<CatListDet> getCatListDets() {
		return this.catListDets;
	}

	public void setCatListDets(Set<CatListDet> catListDets) {
		this.catListDets = catListDets;
	}
 */
}
