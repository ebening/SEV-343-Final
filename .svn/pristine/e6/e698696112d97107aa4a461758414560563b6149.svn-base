/**
 * 
 */
package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author OMAR
 *
 */
public class ReporteVentaSkuDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String distrito;
	
	private String zonaNombre;
	
	private String sucursal;
	
	private String sku;
	
	private String descripcion;
	
	private String categoria;
	
	private String subcategoria;
	
	private int ventaUnidades;
	
	private BigDecimal ventaPesos;

	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	/**
	 * @return the zonaNombre
	 */
	public String getZonaNombre() {
		return zonaNombre;
	}

	/**
	 * @param zonaNombre the zonaNombre to set
	 */
	public void setZonaNombre(String zonaNombre) {
		this.zonaNombre = zonaNombre;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the subcategoria
	 */
	public String getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria the subcategoria to set
	 */
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
	 * @return the ventaUnidades
	 */
	public int getVentaUnidades() {
		return ventaUnidades;
	}

	/**
	 * @param ventaUnidades the ventaUnidades to set
	 */
	public void setVentaUnidades(int ventaUnidades) {
		this.ventaUnidades = ventaUnidades;
	}

	/**
	 * @return the ventaPesos
	 */
	public BigDecimal getVentaPesos() {
		return ventaPesos;
	}

	/**
	 * @param ventaPesos the ventaPesos to set
	 */
	public void setVentaPesos(BigDecimal ventaPesos) {
		this.ventaPesos = ventaPesos;
	}
	
	public String getVentaPesosFormato(){
		return Constants.formatNumber.format( ventaPesos) ;
	}
	
	public String getVentaUnidadesFormato(){
		return Constants.formatNumber.format( ventaUnidades) ;
	}
	
	
	


}
