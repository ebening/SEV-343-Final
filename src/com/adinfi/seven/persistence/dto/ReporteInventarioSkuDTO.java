/**
 * 
 */
package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author OMAR
 *
 */
public class ReporteInventarioSkuDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String distrito;
	
	private String zonaNombre;
	
	private String sucursal;
	
	private String sku;
	
	private String descripcion;
	
	private String categoria;
	
	private String subcategoria;
	
	private int inventarioTienda;
	
	private int inventarioCedis;
	
	private int inventarioTransito;
	
	

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
	 * @return the inventarioTienda
	 */
	public int getInventarioTienda() {
		return inventarioTienda;
	}

	/**
	 * @param inventarioTienda the inventarioTienda to set
	 */
	public void setInventarioTienda(int inventarioTienda) {
		this.inventarioTienda = inventarioTienda;
	}

	/**
	 * @return the inventarioCedis
	 */
	public int getInventarioCedis() {
		return inventarioCedis;
	}

	/**
	 * @param inventarioCedis the inventarioCedis to set
	 */
	public void setInventarioCedis(int inventarioCedis) {
		this.inventarioCedis = inventarioCedis;
	}

	/**
	 * @return the inventarioTransito
	 */
	public int getInventarioTransito() {
		return inventarioTransito;
	}

	/**
	 * @param inventarioTransito the inventarioTransito to set
	 */
	public void setInventarioTransito(int inventarioTransito) {
		this.inventarioTransito = inventarioTransito;
	}
	
	
	public String getInventarioTiendaFormato(){
		return Constants.formatNumber.format( inventarioTienda) ;
	}
	
	public String getInventarioCedisFormato(){
		return Constants.formatNumber.format( inventarioCedis) ;
	}
	
	public String getInventarioTransitoFormato(){
		return Constants.formatNumber.format( inventarioTransito) ;
	}
	
	
}
