package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;
// Generated 15/01/2014 11:03:37 AM by Hibernate Tools 3.2.1.GA



/**
 * TblArticulosHojaId generated by hbm2java
 */
public class TblArticulosHojaId  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1759947662198570547L;
	private int idFolleto;
     private int idHoja;
     private String idArticulo;

    public TblArticulosHojaId() {
    }

    public TblArticulosHojaId(int idFolleto, int idHoja, String idArticulo) {
       this.idFolleto = idFolleto;
       this.idHoja = idHoja;
       this.idArticulo = idArticulo;
    }
   
    public int getIdFolleto() {
        return this.idFolleto;
    }
    
    public void setIdFolleto(int idFolleto) {
        this.idFolleto = idFolleto;
    }
    public int getIdHoja() {
        return this.idHoja;
    }
    
    public void setIdHoja(int idHoja) {
        this.idHoja = idHoja;
    }
    public String getIdArticulo() {
        return this.idArticulo;
    }
    
    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TblArticulosHojaId) ) return false;
		 TblArticulosHojaId castOther = ( TblArticulosHojaId ) other; 
         
		 return (this.getIdFolleto()==castOther.getIdFolleto())
 && (this.getIdHoja()==castOther.getIdHoja())
 && ( (this.getIdArticulo()==castOther.getIdArticulo()) || ( this.getIdArticulo()!=null && castOther.getIdArticulo()!=null && this.getIdArticulo().equals(castOther.getIdArticulo()) ) );
   }
   
   public int hashCode() {
         int result = Constants.DIECISIETE;
         
         result = Constants.TREINTAYSIETE * result + this.getIdFolleto();
         result = Constants.TREINTAYSIETE * result + this.getIdHoja();
         result = Constants.TREINTAYSIETE * result + ( getIdArticulo() == null ? Constants.ZERO : this.getIdArticulo().hashCode() );
         return result;
   }   


}