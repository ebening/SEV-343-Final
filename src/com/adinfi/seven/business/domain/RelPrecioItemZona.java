package com.adinfi.seven.business.domain;
// Generated Nov 19, 2015 8:08:33 PM by Hibernate Tools 4.3.1



/**
 * RelPrecioItemZona generated by hbm2java
 */
public class RelPrecioItemZona  implements java.io.Serializable {


     private int idRpiz;
     private CatItem catItem;
     private CatZone catZone;
     private double precio;

    public RelPrecioItemZona() {
    }

    public RelPrecioItemZona(int idRpiz, CatItem catItem, CatZone catZone, double precio) {
       this.idRpiz = idRpiz;
       this.catItem = catItem;
       this.catZone = catZone;
       this.precio = precio;
    }
   
    public int getIdRpiz() {
        return this.idRpiz;
    }
    
    public void setIdRpiz(int idRpiz) {
        this.idRpiz = idRpiz;
    }
    public CatItem getCatItem() {
        return this.catItem;
    }
    
    public void setCatItem(CatItem catItem) {
        this.catItem = catItem;
    }
    public CatZone getCatZone() {
        return this.catZone;
    }
    
    public void setCatZone(CatZone catZone) {
        this.catZone = catZone;
    }
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }




}

