package com.adinfi.seven.business.domain;
// Generated 19-ago-2015 16:11:16 by Hibernate Tools 4.3.1



/**
 * CatListDet generated by hbm2java
 */
public class CatListDet  implements java.io.Serializable {


     private String id;
     private CatItem catItem;
     private String code;

    public CatListDet() {
    }

	
    public CatListDet(String id, String code) {
        this.id = id;
        this.code = code;
    }
    public CatListDet(String id, CatItem catItem, String code) {
       this.id = id;
       this.catItem = catItem;
       this.code = code;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public CatItem getCatItem() {
        return this.catItem;
    }
    
    public void setCatItem(CatItem catItem) {
        this.catItem = catItem;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }




}

