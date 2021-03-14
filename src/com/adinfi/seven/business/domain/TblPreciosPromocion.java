package com.adinfi.seven.business.domain;

import java.io.Serializable;

public class TblPreciosPromocion implements Serializable {
	private static final long serialVersionUID = 1L;
	private TblPreciosPromocionId id;
	private Double precio;
	private Double porcentaje;
	private Double ahorroFijo;
	private Double recuperacion;
	private Double recuperacionPorcentaje;
	private String objetivo;
	private String comentario;
	private String nombre;
	private int promoId;
	private int estatusRevision;
	private int estatusCaptura;
	private Double precioVenta;
	private Integer estatusDiseno;
    
    //nuevos valores
    private Double iva;
    private Double impuesto;
    private Double precioRegularNuevo;

    private Double distribucionRebaja;
	private Double distribucionRebajaPor;
    
    //valores finales
    private Double porcentajeDescuento;
    private Double porcentajeRetencion;
    private Double elasticidad;
    
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public Double getAhorroFijo() {
		return ahorroFijo;
	}
	public void setAhorroFijo(Double ahorroFijo) {
		this.ahorroFijo = ahorroFijo;
	}
	public Double getRecuperacion() {
		return recuperacion;
	}
	public void setRecuperacion(Double recuperacion) {
		this.recuperacion = recuperacion;
	}
	public Double getRecuperacionPorcentaje() {
		return recuperacionPorcentaje;
	}
	public void setRecuperacionPorcentaje(Double recuperacionPorcentaje) {
		this.recuperacionPorcentaje = recuperacionPorcentaje;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPromoId() {
		return promoId;
	}
	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}
	public int getEstatusRevision() {
		return estatusRevision;
	}
	public void setEstatusRevision(int estatusRevision) {
		this.estatusRevision = estatusRevision;
	}
	public int getEstatusCaptura() {
		return estatusCaptura;
	}
	public void setEstatusCaptura(int estatusCaptura) {
		this.estatusCaptura = estatusCaptura;
	}
	public TblPreciosPromocionId getId() {
		return id;
	}
	public void setId(TblPreciosPromocionId id) {
		this.id = id;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Integer getEstatusDiseno() {
		return estatusDiseno;
	}
	public void setEstatusDiseno(Integer estatusDiseno) {
		this.estatusDiseno = estatusDiseno;
	}

    /**
     * @return the iva
     */
    public Double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(Double iva) {
        this.iva = iva;
    }

    /**
     * @return the impuesto
     */
    public Double getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * @return the precioRegularNuevo
     */
    public Double getPrecioRegularNuevo() {
        return precioRegularNuevo;
    }

    /**
     * @param precioRegularNuevo the precioRegularNuevo to set
     */
    public void setPrecioRegularNuevo(Double precioRegularNuevo) {
        this.precioRegularNuevo = precioRegularNuevo;
    }

    /**
     * @return the distribucionRebaja
     */
    public Double getDistribucionRebaja() {
        return distribucionRebaja;
    }

    /**
     * @param distribucionRebaja the distribucionRebaja to set
     */
    public void setDistribucionRebaja(Double distribucionRebaja) {
        this.distribucionRebaja = distribucionRebaja;
    }

    /**
     * @return the distribucionRebajaPor
     */
    public Double getDistribucionRebajaPor() {
        return distribucionRebajaPor;
    }

    /**
     * @param distribucionRebajaPor the distribucionRebajaPor to set
     */
    public void setDistribucionRebajaPor(Double distribucionRebajaPor) {
        this.distribucionRebajaPor = distribucionRebajaPor;
    }

    /**
     * @return the porcentajeDescuento
     */
    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * @param porcentajeDescuento the porcentajeDescuento to set
     */
    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * @return the porcentajeRetencion
     */
    public Double getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    /**
     * @param porcentajeRetencion the porcentajeRetencion to set
     */
    public void setPorcentajeRetencion(Double porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
    }

    /**
     * @return the elasticidad
     */
    public Double getElasticidad() {
        return elasticidad;
    }

    /**
     * @param elasticidad the elasticidad to set
     */
    public void setElasticidad(Double elasticidad) {
        this.elasticidad = elasticidad;
    }
}