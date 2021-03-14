/**
 * 
 */
package com.adinfi.seven.business.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author OMAR
 *
 */
public class TblReporteVentas implements java.io.Serializable  {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private int id;
     private String codeArt;
     private String distrito;
     private String zona;
     private Integer idSucursal;
     private String sucursal;
     private String categoria;
     private String subcategoria;
     private Integer ventasUnidades;
     private BigDecimal ventasPesos;
     private Date fechaVenta;

    public TblReporteVentas() {
    }

	
    public TblReporteVentas(int id, String codeArt) {
        this.id = id;
        this.codeArt = codeArt;
    }
    public TblReporteVentas(int id, String codeArt, String distrito, String zona, Integer idSucursal, String sucursal, String categoria, String subcategoria, Integer ventasUnidades, BigDecimal ventasPesos, Date fechaVenta) {
       this.id = id;
       this.codeArt = codeArt;
       this.distrito = distrito;
       this.zona = zona;
       this.idSucursal = idSucursal;
       this.sucursal = sucursal;
       this.categoria = categoria;
       this.subcategoria = subcategoria;
       this.ventasUnidades = ventasUnidades;
       this.ventasPesos = ventasPesos;
       this.fechaVenta = fechaVenta;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getCodeArt() {
        return this.codeArt;
    }
    
    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }
    public String getDistrito() {
        return this.distrito;
    }
    
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public String getZona() {
        return this.zona;
    }
    
    public void setZona(String zona) {
        this.zona = zona;
    }
    public Integer getIdSucursal() {
        return this.idSucursal;
    }
    
    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }
    public String getSucursal() {
        return this.sucursal;
    }
    
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getSubcategoria() {
        return this.subcategoria;
    }
    
    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }
    public Integer getVentasUnidades() {
        return this.ventasUnidades;
    }
    
    public void setVentasUnidades(Integer ventasUnidades) {
        this.ventasUnidades = ventasUnidades;
    }
    public BigDecimal getVentasPesos() {
        return this.ventasPesos;
    }
    
    public void setVentasPesos(BigDecimal ventasPesos) {
        this.ventasPesos = ventasPesos;
    }
    public Date getFechaVenta() {
        return this.fechaVenta;
    }
    
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }



}
