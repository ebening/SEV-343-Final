/**
 * 
 */
package com.adinfi.seven.business.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author OMAR
 *
 */
public class TblExistenciaItemTienda implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TblExistenciaItemTiendaId id;
    private BigDecimal existencia;
    private Date fechaUpromo;
    private String tipoUpromo;
    private BigDecimal precioUpromo;
    private String tipoPromoAanterior;
    private BigDecimal precioPromoAanterior;
    
    private SimpleDateFormat formatDate = new SimpleDateFormat(
			"dd-MMMM-yyyy", new Locale("es", "ES"));
    

   public TblExistenciaItemTienda() {
   }

	
   public TblExistenciaItemTienda(TblExistenciaItemTiendaId id, BigDecimal existencia) {
       this.id = id;
       this.existencia = existencia;
   }
   public TblExistenciaItemTienda(TblExistenciaItemTiendaId id, BigDecimal existencia, Date fechaUpromo, String tipoUpromo, BigDecimal precioUpromo, String tipoPromoAanterior, BigDecimal precioPromoAanterior) {
      this.id = id;
      this.existencia = existencia;
      this.fechaUpromo = fechaUpromo;
      this.tipoUpromo = tipoUpromo;
      this.precioUpromo = precioUpromo;
      this.tipoPromoAanterior = tipoPromoAanterior;
      this.precioPromoAanterior = precioPromoAanterior;
   }
  
   public TblExistenciaItemTiendaId getId() {
       return this.id;
   }
   
   public void setId(TblExistenciaItemTiendaId id) {
       this.id = id;
   }
   public BigDecimal getExistencia() {
       return this.existencia;
   }
   
   public void setExistencia(BigDecimal existencia) {
       this.existencia = existencia;
   }
   public String getTipoUpromo() {
       return this.tipoUpromo;
   }
   
   public void setTipoUpromo(String tipoUpromo) {
       this.tipoUpromo = tipoUpromo;
   }
   public BigDecimal getPrecioUpromo() {
       return this.precioUpromo;
   }
   
   public void setPrecioUpromo(BigDecimal precioUpromo) {
       this.precioUpromo = precioUpromo;
   }
   public String getTipoPromoAanterior() {
       return this.tipoPromoAanterior;
   }
   
   public void setTipoPromoAanterior(String tipoPromoAanterior) {
       this.tipoPromoAanterior = tipoPromoAanterior;
   }
   public BigDecimal getPrecioPromoAanterior() {
       return this.precioPromoAanterior;
   }
   
   public void setPrecioPromoAanterior(BigDecimal precioPromoAanterior) {
       this.precioPromoAanterior = precioPromoAanterior;
   }


	/**
	 * @return the fechaUpromo
	 */
	public Date getFechaUpromo() {
		return fechaUpromo;
	}
	
	
	/**
	 * @param fechaUpromo the fechaUpromo to set
	 */
	public void setFechaUpromo(Date fechaUpromo) {
		this.fechaUpromo = fechaUpromo;
	}

	public String getFechaUpromoStr(){
		if(this.fechaUpromo != null){
			return formatDate.format(this.fechaUpromo);
		}else return Constants.EMPTY;
				
		
	}

   


}
