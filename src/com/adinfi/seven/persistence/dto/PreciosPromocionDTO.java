package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class PreciosPromocionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pkMec;
	private int pkComp;
	private int zonaId;
	private Double precio=0d;
	private Double porcentaje;
	private Double ahorroFijo;
	private Double recuperacion;
	private Double recuperacionPorcentaje;
	private String objetivo;
	private String comentario;
	private String nombre;
	private int promoId;
	private int estatusRevision;
    private int estatusRevisionEdicion;
	private int estatusCaptura;
    private int estatusCapturaEdicion;
	private Double precioVenta;
    private Double precioVentaCombo;
	private Integer estatusDiseno;
    
    private double distribucionRebajaCantidad;
    private double distribucionRebajaPorcentaje;
    
    //nuevos valores
    private Double iva;
    private Double impuesto;
    private Double precioRegularNuevo;
    
    //valores finales
    private Double porcentajeDescuento;
    private Double porcentajeRetencion;
    private Double elasticidad;
 
    private Double precioRCombo;
    private Double porcentajeCombo;
    private Double ahorroFijoCombo;
    private Double recuperacionCombo;
    private Double recuperacionPorcentajeCombo;
    private Double elasticidadCombo;

	public int getPkMec() {
		return pkMec;
	}
	public void setPkMec(int pkMec) {
		this.pkMec = pkMec;
	}
	public int getPkComp() {
		return pkComp;
	}
	public void setPkComp(int pkComp) {
		this.pkComp = pkComp;
	}
	public Double getPrecio() {
		return precio;
	}
	public Double getPrecioR(){
		return (Math.ceil(precio*100))/100;
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
	public int getZonaId() {
		return zonaId;
	}
	public void setZonaId(int zonaId) {
		this.zonaId = zonaId;
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
     * @return the distribucionRebajaCantidad
     */
    public double getDistribucionRebajaCantidad() {
        return distribucionRebajaCantidad;
    }

    /**
     * @param distribucionRebajaCantidad the distribucionRebajaCantidad to set
     */
    public void setDistribucionRebajaCantidad(double distribucionRebajaCantidad) {
        this.distribucionRebajaCantidad = distribucionRebajaCantidad;
    }

    /**
     * @return the distribucionRebajaPorcentaje
     */
    public double getDistribucionRebajaPorcentaje() {
        return distribucionRebajaPorcentaje;
    }

    /**
     * @param distribucionRebajaPorcentaje the distribucionRebajaPorcentaje to set
     */
    public void setDistribucionRebajaPorcentaje(double distribucionRebajaPorcentaje) {
        this.distribucionRebajaPorcentaje = distribucionRebajaPorcentaje;
    }

    /**
     * @return the estatusRevisionEdicion
     */
    public int getEstatusRevisionEdicion() {
        return estatusRevisionEdicion;
    }

    /**
     * @param estatusRevisionEdicion the estatusRevisionEdicion to set
     */
    public void setEstatusRevisionEdicion(int estatusRevisionEdicion) {
        this.estatusRevisionEdicion = estatusRevisionEdicion;
    }

    /**
     * @return the estatusCapturaEdicion
     */
    public int getEstatusCapturaEdicion() {
        return estatusCapturaEdicion;
    }

    /**
     * @param estatusCapturaEdicion the estatusCapturaEdicion to set
     */
    public void setEstatusCapturaEdicion(int estatusCapturaEdicion) {
        this.estatusCapturaEdicion = estatusCapturaEdicion;
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

    /**
     * @return the precioVentaCombo
     */
    public Double getPrecioVentaCombo() {
        return precioVentaCombo;
    }

    /**
     * @param precioVentaCombo the precioVentaCombo to set
     */
    public void setPrecioVentaCombo(Double precioVentaCombo) {
        this.precioVentaCombo = precioVentaCombo;
    }

    /**
     * @return the precioRCombo
     */
    public Double getPrecioRCombo() {
        return precioRCombo;
    }

    /**
     * @param precioRCombo the precioRCombo to set
     */
    public void setPrecioRCombo(Double precioRCombo) {
        this.precioRCombo = precioRCombo;
    }

    /**
     * @return the porcentajeCombo
     */
    public Double getPorcentajeCombo() {
        return porcentajeCombo;
    }

    /**
     * @param porcentajeCombo the porcentajeCombo to set
     */
    public void setPorcentajeCombo(Double porcentajeCombo) {
        this.porcentajeCombo = porcentajeCombo;
    }

    /**
     * @return the ahorroFijoCombo
     */
    public Double getAhorroFijoCombo() {
        return ahorroFijoCombo;
    }

    /**
     * @param ahorroFijoCombo the ahorroFijoCombo to set
     */
    public void setAhorroFijoCombo(Double ahorroFijoCombo) {
        this.ahorroFijoCombo = ahorroFijoCombo;
    }

    /**
     * @return the recuperacionCombo
     */
    public Double getRecuperacionCombo() {
        return recuperacionCombo;
    }

    /**
     * @param recuperacionCombo the recuperacionCombo to set
     */
    public void setRecuperacionCombo(Double recuperacionCombo) {
        this.recuperacionCombo = recuperacionCombo;
    }

    /**
     * @return the recuperacionPorcentajeCombo
     */
    public Double getRecuperacionPorcentajeCombo() {
        return recuperacionPorcentajeCombo;
    }

    /**
     * @param recuperacionPorcentajeCombo the recuperacionPorcentajeCombo to set
     */
    public void setRecuperacionPorcentajeCombo(Double recuperacionPorcentajeCombo) {
        this.recuperacionPorcentajeCombo = recuperacionPorcentajeCombo;
    }

    /**
     * @return the elasticidadCombo
     */
    public Double getElasticidadCombo() {
        return elasticidadCombo;
    }

    /**
     * @param elasticidadCombo the elasticidadCombo to set
     */
    public void setElasticidadCombo(Double elasticidadCombo) {
        this.elasticidadCombo = elasticidadCombo;
    }
}