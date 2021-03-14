package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

public class TblComponenteZonaPrecio implements java.io.Serializable {
	
    private int componenteZonaPrecioId;
    private int componenteId;
    private int zonaId;
    private Double precioProducto;
    private Double cantidad;
    private Double precioVenta;
    private Double porcentajeMargenRegularProducto;
    private Double margenRegularProducto;
    private Double margenRegular;
    private Double impuesto;
    

    /**
     * @return the componenteZonaPrecioId
     */
    public int getComponenteZonaPrecioId() {
        return componenteZonaPrecioId;
    }

    /**
     * @param componenteZonaPrecioId the componenteZonaPrecioId to set
     */
    public void setComponenteZonaPrecioId(int componenteZonaPrecioId) {
        this.componenteZonaPrecioId = componenteZonaPrecioId;
    }

    /**
     * @return the componenteId
     */
    public int getComponenteId() {
        return componenteId;
    }

    /**
     * @param componenteId the componenteId to set
     */
    public void setComponenteId(int componenteId) {
        this.componenteId = componenteId;
    }

    /**
     * @return the zonaId
     */
    public int getZonaId() {
        return zonaId;
    }

    /**
     * @param zonaId the zonaId to set
     */
    public void setZonaId(int zonaId) {
        this.zonaId = zonaId;
    }
    
    @Override
	public String toString(){
        return "ComponenteId: " + this.componenteId + ", zonaId: " + this.zonaId + ", precio Producto: " + this.precioProducto +
                ", cantidad: " + this.cantidad + ", precioVenta: " + this.precioVenta + ", margen: " + this.margenRegularProducto + ", margenTotal: " + this.margenRegular + 
                ", porcentaje Margen: " + this.porcentajeMargenRegularProducto + ", impuesto: " + this.impuesto;
    }

    /**
     * @return the precioProducto
     */
    public Double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * @param precioProducto the precioProducto to set
     */
    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioVenta
     */
    public Double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * @return the porcentajeMargenRegularProducto
     */
    public Double getPorcentajeMargenRegularProducto() {
        return porcentajeMargenRegularProducto;
    }

    /**
     * @param porcentajeMargenRegularProducto the porcentajeMargenRegularProducto to set
     */
    public void setPorcentajeMargenRegularProducto(Double porcentajeMargenRegularProducto) {
        this.porcentajeMargenRegularProducto = porcentajeMargenRegularProducto;
    }

    /**
     * @return the margenRegularProducto
     */
    public Double getMargenRegularProducto() {
        return margenRegularProducto;
    }

    /**
     * @param margenRegularProducto the margenRegularProducto to set
     */
    public void setMargenRegularProducto(Double margenRegularProducto) {
        this.margenRegularProducto = margenRegularProducto;
    }

    /**
     * @return the margenRegular
     */
    public Double getMargenRegular() {
        return margenRegular;
    }

    /**
     * @param margenRegular the margenRegular to set
     */
    public void setMargenRegular(Double margenRegular) {
        this.margenRegular = margenRegular;
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

    public void calculaPrecioVentaYMargen(){
        System.out.println("Calculando precio de venta y margen");
        System.out.println("Cantidad: " + this.cantidad);
        System.out.println("PrecioProducto: " + this.precioProducto);
        System.out.println("MargenProducto: " + this.margenRegularProducto);
        
        this.precioVenta = this.precioProducto * this.cantidad;
        this.margenRegular = this.margenRegularProducto * this.cantidad;
    }

}