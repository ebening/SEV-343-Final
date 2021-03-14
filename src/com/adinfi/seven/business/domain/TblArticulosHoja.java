package com.adinfi.seven.business.domain;
// Generated 15/01/2014 11:03:37 AM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * TblArticulosHoja generated by hbm2java
 */
public class TblArticulosHoja  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 3375414259116210313L;
	 private TblArticulosHojaId id;
	 private int idFolleto;
     //private int idHoja;
	 TblFolletoHojas hoja;
     private String idArticulo;
     private TblFolleto tblFolleto;
     private TblImageArticulo tblImageArticulo;
     private Integer segmentParentId;
     private Integer segmentId;
     private BigDecimal precioPromocion;
     private Integer idArticulosHoja;
     private String  segmentName;
     
     private String plazos;
     private BigDecimal abSem6mes;
     private BigDecimal abSem9mes;
     private BigDecimal abSem12mes;
     private BigDecimal abSem15mes;
     private BigDecimal abSem18mes;
     private BigDecimal abSem24mes;     
     private Integer    pronosticoVenta;
  //   private String     segName;
     private String     principal;
     private Integer imgWidth;
     private Integer imgHeight;

    public TblArticulosHojaId getId() {
		return id;
	}



	public void setId(TblArticulosHojaId id) {
		this.id = id;
	}



	public String getPlazos() {
		return plazos;
	}



	public void setPlazos(String plazos) {
		this.plazos = plazos;
	}



	public BigDecimal getAbSem6mes() {
		return abSem6mes;
	}



	public void setAbSem6mes(BigDecimal abSem6mes) {
		this.abSem6mes = abSem6mes;
	}



	public BigDecimal getAbSem9mes() {
		return abSem9mes;
	}



	public void setAbSem9mes(BigDecimal abSem9mes) {
		this.abSem9mes = abSem9mes;
	}



	public BigDecimal getAbSem12mes() {
		return abSem12mes;
	}



	public void setAbSem12mes(BigDecimal abSem12mes) {
		this.abSem12mes = abSem12mes;
	}



	public BigDecimal getAbSem15mes() {
		return abSem15mes;
	}



	public void setAbSem15mes(BigDecimal abSem15mes) {
		this.abSem15mes = abSem15mes;
	}



	public BigDecimal getAbSem18mes() {
		return abSem18mes;
	}



	public void setAbSem18mes(BigDecimal abSem18mes) {
		this.abSem18mes = abSem18mes;
	}



	public BigDecimal getAbSem24mes() {
		return abSem24mes;
	}



	public void setAbSem24mes(BigDecimal abSem24mes) {
		this.abSem24mes = abSem24mes;
	}



	public Integer getPronosticoVenta() {
		return pronosticoVenta;
	}



	public void setPronosticoVenta(Integer pronosticoVenta) {
		this.pronosticoVenta = pronosticoVenta;
	}


 


	public String getPrincipal() {
		return principal;
	}



	public void setPrincipal(String principal) {
		this.principal = principal;
	}



	public TblFolletoHojas getHoja() {
		return hoja;
	}



	public void setHoja(TblFolletoHojas hoja) {
		this.hoja = hoja;
	}



	public String getSegmentName() {
		return segmentName;
	}



	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}



	public int getIdFolleto() {
		return idFolleto;
	}



	public void setIdFolleto(int idFolleto) {
		this.idFolleto = idFolleto;
	}



	public String getIdArticulo() {
		return idArticulo;
	}



	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}



	public Integer getIdArticulosHoja() {
		return idArticulosHoja;
	}



	public void setIdArticulosHoja(Integer idArticulosHoja) {
		this.idArticulosHoja = idArticulosHoja;
	}



	public TblArticulosHoja() {
    }

	
   
    public TblFolleto getTblFolleto() {
        return this.tblFolleto;
    }
    
    public void setTblFolleto(TblFolleto tblFolleto) {
        this.tblFolleto = tblFolleto;
    }
    public TblImageArticulo getTblImageArticulo() {
        return this.tblImageArticulo;
    }
    
    public void setTblImageArticulo(TblImageArticulo tblImageArticulo) {
        this.tblImageArticulo = tblImageArticulo;
    }
    public Integer getSegmentParentId() {
        return this.segmentParentId;
    }
    
    public void setSegmentParentId(Integer segmentParentId) {
        this.segmentParentId = segmentParentId;
    }
    public Integer getSegmentId() {
        return this.segmentId;
    }
    
    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }
    public BigDecimal getPrecioPromocion() {
        return this.precioPromocion;
    }
    
    public void setPrecioPromocion(BigDecimal precioPromocion) {
        this.precioPromocion = precioPromocion;
    }
    
	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}
	
	public Integer getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}

}


