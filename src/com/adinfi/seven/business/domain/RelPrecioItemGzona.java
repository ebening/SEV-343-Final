package com.adinfi.seven.business.domain;
// Generated Nov 19, 2015 8:08:33 PM by Hibernate Tools 4.3.1



/**
 * RelPrecioItemGzona generated by hbm2java
 */
public class RelPrecioItemGzona  implements java.io.Serializable {


     private int idRpigz;
     private CatGZone catGZone;
     private CatItem catItem;
     private double precio;

    public RelPrecioItemGzona() {
    }

    public RelPrecioItemGzona(int idRpigz, CatGZone catGZone, CatItem catItem, double precio) {
       this.idRpigz = idRpigz;
       this.catGZone = catGZone;
       this.catItem = catItem;
       this.precio = precio;
    }
   
    public int getIdRpigz() {
        return this.idRpigz;
    }
    
    public void setIdRpigz(int idRpigz) {
        this.idRpigz = idRpigz;
    }
    public CatGZone getCatGZone() {
        return this.catGZone;
    }
    
    public void setCatGZone(CatGZone catGZone) {
        this.catGZone = catGZone;
    }
    public CatItem getCatItem() {
        return this.catItem;
    }
    
    public void setCatItem(CatItem catItem) {
        this.catItem = catItem;
    }
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }




}


