/**
 * 
 */
package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author OMAR
 *
 */
public class TblVentasItemId implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String idItem;
     private int idTienda;
     private int mes;
     private int anio;

    public TblVentasItemId() {
    }

    public TblVentasItemId(String idItem, int idTienda, int mes, int anio) {
       this.idItem = idItem;
       this.idTienda = idTienda;
       this.mes = mes;
       this.anio = anio;
    }
   
    public String getIdItem() {
        return this.idItem;
    }
    
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    public int getIdTienda() {
        return this.idTienda;
    }
    
    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }
    public int getMes() {
        return this.mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAnio() {
        return this.anio;
    }
    
    public void setAnio(int anio) {
        this.anio = anio;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TblVentasItemId) ) return false;
		 TblVentasItemId castOther = ( TblVentasItemId ) other; 
         
		 return ( (this.getIdItem()==castOther.getIdItem()) || ( this.getIdItem()!=null && castOther.getIdItem()!=null && this.getIdItem().equals(castOther.getIdItem()) ) )
 && (this.getIdTienda()==castOther.getIdTienda())
 && (this.getMes()==castOther.getMes())
 && (this.getAnio()==castOther.getAnio());
   }
   
   public int hashCode() {
         int result = Constants.DIECISIETE;
         
         result = Constants.TREINTAYSIETE * result + ( getIdItem() == null ? 0 : this.getIdItem().hashCode() );
         result = Constants.TREINTAYSIETE * result + this.getIdTienda();
         result = Constants.TREINTAYSIETE * result + this.getMes();
         result = Constants.TREINTAYSIETE * result + this.getAnio();
         return result;
   }   


	 
	
}
