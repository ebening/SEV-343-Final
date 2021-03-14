package com.adinfi.seven.persistence.dto;

import java.util.List;

import com.adinfi.seven.persistence.dto.DisenosDTO.RelObj;

public class CampanaProgramaDTO implements java.io.Serializable{
	private static final long serialVersionUID = -5824323201491362968L;
	private int idPrograma;
	private String descripcion;
	private List<String> plazaSelect;
	private List<String> categoriaSelect;
	private double ingreso;
	private String strEsSencillo;
	private String strPlazas;
	private String strCatgerias;
	private String descCampana;
	private String etapa;

	private List<RelObj> grupoZonaLst;
	private List<RelObj> zonaLst;
	private List<RelObj> storeLst;
	private List<String> grupoZonas;
	private List<String> zonas;
	private List<String> tiendas;
        
        private int sencillo;
	
	public String getGrupoZonaStr(){
		return getStr(grupoZonaLst);
	}
	public String getZonaStr(){
		return getStr(zonaLst);
	}
	public String getStoreStr(){
		return getStr(storeLst);
	}
	
	public String getStr(List<RelObj> lst){
		StringBuilder value=new StringBuilder("");
		if(lst!=null){
			for(RelObj obj: lst){
				value.append(obj.getStr());
				value.append(", ");
			}
			if(value.length()>0){
				value.deleteCharAt(value.length()-1);
			}
		}
		return value.toString();
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<String> getPlazaSelect() {
		return plazaSelect;
	}
	public void setPlazaSelect(List<String> plazaSelect) {
		this.plazaSelect = plazaSelect;
	}
	public List<String> getCategoriaSelect() {
		return categoriaSelect;
	}
	public void setCategoriaSelect(List<String> categoriaSelect) {
		this.categoriaSelect = categoriaSelect;
	}
	public double getIngreso() {
		return ingreso;
	}
	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}
	public String getStrEsSencillo() {
            return strEsSencillo;
	}
	public void setStrEsSencillo(String strEsSencillo) {
		this.strEsSencillo = strEsSencillo;
	}
	public String getStrPlazas() {
		return strPlazas;
	}
	public void setStrPlazas(String strPlazas) {
		this.strPlazas = strPlazas;
	}
	public String getStrCatgerias() {
		return strCatgerias;
	}
	public void setStrCatgerias(String strCatgerias) {
		this.strCatgerias = strCatgerias;
	}
	public String getDescCampana() {
		return descCampana;
	}
	public void setDescCampana(String descCampana) {
		this.descCampana = descCampana;
	}
	public List<RelObj> getGrupoZonaLst() {
		return grupoZonaLst;
	}
	public void setGrupoZonaLst(List<RelObj> grupoZonaLst) {
		this.grupoZonaLst = grupoZonaLst;
	}
	public List<RelObj> getZonaLst() {
		return zonaLst;
	}
	public void setZonaLst(List<RelObj> zonaLst) {
		this.zonaLst = zonaLst;
	}
	public List<RelObj> getStoreLst() {
		return storeLst;
	}
	public void setStoreLst(List<RelObj> storeLst) {
		this.storeLst = storeLst;
	}
	public List<String> getGrupoZonas() {
		return grupoZonas;
	}
	public void setGrupoZonas(List<String> grupoZonas) {
		this.grupoZonas = grupoZonas;
	}
	public List<String> getZonas() {
		return zonas;
	}
	public void setZonas(List<String> zonas) {
		this.zonas = zonas;
	}
	public List<String> getTiendas() {
		return tiendas;
	}
	public void setTiendas(List<String> tiendas) {
		this.tiendas = tiendas;
	}  

    public int getSencillo() {
        return sencillo;
    }

    public void setSencillo(int sencillo) {
        this.sencillo = sencillo;
        strEsSencillo = sencillo == 0 ? "Sencillo" : "Compartido";
    }
        
        
}
