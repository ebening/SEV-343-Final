package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;



public class CatCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCategory;
    private CatDepto catDepto;
    private String code;
    private boolean esMercancia;
    private Set<RelUsuariosCategorias> relUsuariosCategoriases = new HashSet<RelUsuariosCategorias>(0);

   public CatCategory() {
   }

	
   public CatCategory(int idCategory, CatDepto catDepto, boolean esMercancia) {
       this.idCategory = idCategory;
       this.catDepto = catDepto;
       this.esMercancia = esMercancia;
   }
   public CatCategory(int idCategory, CatDepto catDepto, String code, boolean esMercancia, Set<RelUsuariosCategorias> relUsuariosCategoriases) {
      this.idCategory = idCategory;
      this.catDepto = catDepto;
      this.code = code;
      this.esMercancia = esMercancia;
      this.relUsuariosCategoriases = relUsuariosCategoriases;
   }
  
   @Override
	public String toString() {
		return code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCategory;
		return result;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatCategory other = (CatCategory) obj;
		if (idCategory != other.idCategory)
			return false;
		return true;
	}

public int getIdCategory() {
       return this.idCategory;
   }
   
   public void setIdCategory(int idCategory) {
       this.idCategory = idCategory;
   }
   public CatDepto getCatDepto() {
       return this.catDepto;
   }
   
   public void setCatDepto(CatDepto catDepto) {
       this.catDepto = catDepto;
   }
   public String getCode() {
       return this.code;
   }
   
   public void setCode(String code) {
       this.code = code;
   }
   public boolean isEsMercancia() {
       return this.esMercancia;
   }
   
   public void setEsMercancia(boolean esMercancia) {
       this.esMercancia = esMercancia;
   }
   public Set<RelUsuariosCategorias> getRelUsuariosCategoriases() {
       return this.relUsuariosCategoriases;
   }
   
   public void setRelUsuariosCategoriases(Set<RelUsuariosCategorias> relUsuariosCategoriases) {
       this.relUsuariosCategoriases = relUsuariosCategoriases;
   }

}

