package com.adinfi.seven.business.domain;
// Generated 10-ago-2015 17:57:09 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * CatTipoZona generated by hbm2java
 */
public class CatTipoZona  implements java.io.Serializable {


     private int idTipoZona;
     private String code;
     private Set<CatZone> catZones = new HashSet<CatZone>(0);

    public CatTipoZona() {
    }

	
    public CatTipoZona(int idTipoZona, String code) {
        this.idTipoZona = idTipoZona;
        this.code = code;
    }
    public CatTipoZona(int idTipoZona, String code, Set<CatZone> catZones) {
       this.idTipoZona = idTipoZona;
       this.code = code;
       this.catZones = catZones;
    }
   
    public int getIdTipoZona() {
        return this.idTipoZona;
    }
    
    public void setIdTipoZona(int idTipoZona) {
        this.idTipoZona = idTipoZona;
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

