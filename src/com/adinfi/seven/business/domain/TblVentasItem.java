/**
 * 
 */
package com.adinfi.seven.business.domain;

import java.math.BigDecimal;

/**
 * @author OMAR
 *
 */
public class TblVentasItem implements java.io.Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	 private TblVentasItemId id;
	 private BigDecimal vunidades;
     private BigDecimal vmonto;

    public TblVentasItem() {
    }

    public TblVentasItem(TblVentasItemId id, BigDecimal vunidades, BigDecimal vmonto) {
       this.id = id;
       this.vunidades = vunidades;
       this.vmonto = vmonto;
    }
   
    public TblVentasItemId getId() {
        return this.id;
    }
    
    public void setId(TblVentasItemId id) {
        this.id = id;
    }
    public BigDecimal getVunidades() {
        return this.vunidades;
    }
    
    public void setVunidades(BigDecimal vunidades) {
        this.vunidades = vunidades;
    }
    public BigDecimal getVmonto() {
        return this.vmonto;
    }
    
    public void setVmonto(BigDecimal vmonto) {
        this.vmonto = vmonto;
    }





}
