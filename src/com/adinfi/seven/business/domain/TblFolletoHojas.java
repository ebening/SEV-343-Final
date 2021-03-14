package com.adinfi.seven.business.domain;

import java.sql.Blob;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

 

public class TblFolletoHojas  implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5831786431632867565L;
	Integer  idFolleto;
	Integer  idHoja;
	Integer  idCategory;
	Integer  idScategory;
	Integer idDesigner;
	Integer  idUserInvitado;
	Integer  hojaParentSegId;
	Short    numHoja;
	Integer  idSistemaVenta;
	Integer  idTemplate;
	TblTemplate template;
	Character estatus;
	Integer  idHojaPadre;
	Set<TblFolletoHojas> childHojas;
	Set<TblArticulosHoja> articulos;

	private Blob diseno;
	
	
	
	public Integer getIdHojaPadre() {
		return idHojaPadre;
	}
	public void setIdHojaPadre(Integer idHojaPadre) {
		this.idHojaPadre = idHojaPadre;
	}
	public Character getEstatus() {
		return estatus;
	}
	public void setEstatus(Character estatus) {
		this.estatus = estatus;
	}	
	public Integer getIdScategory() {
		return idScategory;
	}
	public void setIdScategory(Integer idScategory) {
		this.idScategory = idScategory;
	}
	public Set<TblArticulosHoja> getArticulos() {
		return articulos;
	}
	public void setArticulos(Set<TblArticulosHoja> articulos) {
		this.articulos = articulos;
	}
	public Integer getIdFolleto() {
		return idFolleto;
	}
	public void setIdFolleto(Integer idFolleto) {
		this.idFolleto = idFolleto;
	}
	public Integer getIdHoja() {
		return idHoja;
	}
	public void setIdHoja(Integer idHoja) {
		this.idHoja = idHoja;
	}
	public Integer getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
 
	public Integer getIdUserInvitado() {
		return idUserInvitado;
	}
	public void setIdUserInvitado(Integer idUserInvitado) {
		this.idUserInvitado = idUserInvitado;
	}
	public Short getNumHoja() {
		return numHoja;
	}
	public void setNumHoja(Short numHoja) {
		this.numHoja = numHoja;
	}
	public Integer getIdSistemaVenta() {
		return idSistemaVenta;
	}
	public void setIdSistemaVenta(Integer idSistemaVenta) {
		this.idSistemaVenta = idSistemaVenta;
	}
	public Integer getIdTemplate() {
		return idTemplate;
	}
	public void setIdTemplate(Integer idTemplate) {
		this.idTemplate = idTemplate;
	}
	
	public Map<String,TblArticulosHoja> getArticulosByArtIdAndSegment(){
		 if( this.articulos==null ) return null;
		 Map<String,TblArticulosHoja> mapArts;
		 mapArts=new TreeMap<String,TblArticulosHoja>();
		 for( TblArticulosHoja articulo:articulos ){
			 mapArts.put(articulo.getIdArticulo()+"_"+ articulo.getSegmentId() , articulo);
		 }
		 		 		 
		 return mapArts;
		
	}
	
	
	public Map<Integer,TblArticulosHoja> getArticulosBySegment(){
		 if( this.articulos==null ) return null;
		 Map<Integer ,TblArticulosHoja> mapArts;
		 mapArts=new TreeMap<Integer,TblArticulosHoja>();
		 for( TblArticulosHoja articulo:articulos ){
			 mapArts.put( articulo.getSegmentId() , articulo);
		 }
		 		 		 
		 return mapArts;
		
	}	
	
	
	
	public Set<TblFolletoHojas> getChildHojas() {
		return childHojas;
	}
	public void setChildHojas(Set<TblFolletoHojas> childHojas) {
		this.childHojas = childHojas;
	}
	public Integer getIdDesigner() {
		return idDesigner;
	}
	public void setIdDesigner(Integer idDesigner) {
		this.idDesigner = idDesigner;
	}
	public TblTemplate getTemplate() {
		return template;
	}
	public void setTemplate(TblTemplate template) {
		this.template = template;
	}
	public Integer getHojaParentSegId() {
		return hojaParentSegId;
	}
	public void setHojaParentSegId(Integer hojaParentSegId) {
		this.hojaParentSegId = hojaParentSegId;
	}
	public Blob getDiseno() {
		return diseno;
	}
	public void setDiseno(Blob diseno) {
		this.diseno = diseno;
	}
	
	

}
