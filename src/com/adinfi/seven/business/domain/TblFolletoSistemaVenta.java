package com.adinfi.seven.business.domain;
// Generated 20/02/2014 07:05:11 PM by Hibernate Tools 3.6.0



/**
 * TblFolletoSistemaVenta generated by hbm2java
 */
public class TblFolletoSistemaVenta  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 2772929967676545427L;
	private TblFolletoSistemaVentaId id;
     private Character sistemaDefault;

    public TblFolletoSistemaVenta() {
    }

	
    public TblFolletoSistemaVenta(TblFolletoSistemaVentaId id) {
        this.id = id;
    }
    public TblFolletoSistemaVenta(TblFolletoSistemaVentaId id, Character sistemaDefault) {
       this.id = id;
       this.sistemaDefault = sistemaDefault;
    }
   
    public TblFolletoSistemaVentaId getId() {
        return this.id;
    }
    
    public void setId(TblFolletoSistemaVentaId id) {
        this.id = id;
    }
    public Character getSistemaDefault() {
        return this.sistemaDefault;
    }
    
    public void setSistemaDefault(Character sistemaDefault) {
        this.sistemaDefault = sistemaDefault;
    }




}


