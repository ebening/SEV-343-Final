/**
 * 
 */
package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;


/**
 * @author OMAR
 *
 */
public class TblExistenciaItemTiendaId implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idItem;
    private int idTienda;

   public TblExistenciaItemTiendaId() {
   }

   public TblExistenciaItemTiendaId(String idItem, int idTienda) {
      this.idItem = idItem;
      this.idTienda = idTienda;
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


  public boolean equals(Object other) {
        if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TblExistenciaItemTiendaId) ) return false;
		 TblExistenciaItemTiendaId castOther = ( TblExistenciaItemTiendaId ) other; 
        
		 return ( (this.getIdItem()==castOther.getIdItem()) || ( this.getIdItem()!=null && castOther.getIdItem()!=null && this.getIdItem().equals(castOther.getIdItem()) ) )
&& (this.getIdTienda()==castOther.getIdTienda());
  }
  
  public int hashCode() {
        int result = Constants.DIECISIETE;
        
        result = Constants.TREINTAYSIETE * result + ( getIdItem() == null ? Constants.ZERO : this.getIdItem().hashCode() );
        result = Constants.TREINTAYSIETE * result + this.getIdTienda();
        return result;
  }   


}
