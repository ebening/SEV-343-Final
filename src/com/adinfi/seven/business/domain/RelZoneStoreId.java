package com.adinfi.seven.business.domain;
// Generated 10-ago-2015 17:57:09 by Hibernate Tools 4.3.1



/**
 * RelZoneStoreId generated by hbm2java
 */
public class RelZoneStoreId  implements java.io.Serializable {


     private Integer idZone;
     private Integer idStore;

    public RelZoneStoreId() {
    }

    public RelZoneStoreId(Integer idZone, Integer idStore) {
       this.idZone = idZone;
       this.idStore = idStore;
    }
   
    public Integer getIdZone() {
        return this.idZone;
    }
    
    public void setIdZone(Integer idZone) {
        this.idZone = idZone;
    }
    public Integer getIdStore() {
        return this.idStore;
    }
    
    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RelZoneStoreId) ) return false;
		 RelZoneStoreId castOther = ( RelZoneStoreId ) other; 
         
		 return ( (this.getIdZone()==castOther.getIdZone()) || ( this.getIdZone()!=null && castOther.getIdZone()!=null && this.getIdZone().equals(castOther.getIdZone()) ) )
 && ( (this.getIdStore()==castOther.getIdStore()) || ( this.getIdStore()!=null && castOther.getIdStore()!=null && this.getIdStore().equals(castOther.getIdStore()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdZone() == null ? 0 : this.getIdZone().hashCode() );
         result = 37 * result + ( getIdStore() == null ? 0 : this.getIdStore().hashCode() );
         return result;
   }   


}


