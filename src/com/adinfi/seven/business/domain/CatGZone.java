package com.adinfi.seven.business.domain;
// Generated 10-ago-2015 17:57:09 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * CatGZone generated by hbm2java
 */
public class CatGZone  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGrupoZona;
     private String code;
     private Set<CatZone> catZones = new HashSet<>(0);

    public CatGZone() {
    }

	
    public CatGZone(int idGrupoZona, String code) {
        this.idGrupoZona = idGrupoZona;
        this.code = code;
    }
    public CatGZone(int idGrupoZona, String code, Set<CatZone> catZones) {
       this.idGrupoZona = idGrupoZona;
       this.code = code;
       this.catZones = catZones;
    }
   
    public int getIdGrupoZona() {
        return this.idGrupoZona;
    }
    
    public void setIdGrupoZona(int idGrupoZona) {
        this.idGrupoZona = idGrupoZona;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public Set<CatZone> getCatZones() {
        return this.catZones;
    }
    
    public void setCatZones(Set<CatZone> catZones) {
        this.catZones = catZones;
    }




}


