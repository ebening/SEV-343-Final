package com.adinfi.seven.business.domain;
// Generated 10-ago-2015 17:57:09 by Hibernate Tools 4.3.1



/**
 * RelZoneStore generated by hbm2java
 */
public class RelZoneStore  implements java.io.Serializable {


     private RelZoneStoreId id;
     private CatStore catStore;
     private CatZone catZone;

    public RelZoneStore() {
    }

	
    public RelZoneStore(RelZoneStoreId id) {
        this.id = id;
    }
    public RelZoneStore(RelZoneStoreId id, CatStore catStore, CatZone catZone) {
       this.id = id;
       this.catStore = catStore;
       this.catZone = catZone;
    }
   
    public RelZoneStoreId getId() {
        return this.id;
    }
    
    public void setId(RelZoneStoreId id) {
        this.id = id;
    }
    public CatStore getCatStore() {
        return this.catStore;
    }
    
    public void setCatStore(CatStore catStore) {
        this.catStore = catStore;
    }
    public CatZone getCatZone() {
        return this.catZone;
    }
    
    public void setCatZone(CatZone catZone) {
        this.catZone = catZone;
    }




}


