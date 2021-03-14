package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class ReporteArticulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8118149892699890823L;
	private String sku;
	private String categoria;
	private String descripcion;
	private String precio;
	private Integer hoja;
	private Integer espacio;
	private String sistemaVenta;

	public ReporteArticulo() {
		// TODO Auto-generated constructor stub
	}

	public ReporteArticulo(String sku, String categoria, String descripcion,
			String d, Integer hoja, Integer espacio,
			String sistemaVenta) {
		this.sku = sku;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio = d;
		this.hoja = hoja;
		this.espacio = espacio;
		this.sistemaVenta = sistemaVenta;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio =  "$"+precio.toString();
	}

	/**
	 * @return the hoja
	 */
	public Integer getHoja() {
		return hoja;
	}

	/**
	 * @param hoja
	 *            the hoja to set
	 */
	public void setHoja(Integer hoja) {
		this.hoja = hoja;
	}

	/**
	 * @return the espacio
	 */
	public Integer getEspacio() {
		return espacio;
	}

	/**
	 * @param espacio
	 *            the espacio to set
	 */
	public void setEspacio(Integer espacio) {
		this.espacio = espacio;
	}

	/**
	 * @return the sistemaVenta
	 */
	public String getSistemaVenta() {
		return sistemaVenta;
	}

	/**
	 * @param sistemaVenta
	 *            the sistemaVenta to set
	 */
	public void setSistemaVenta(String sistemaVenta) {
		this.sistemaVenta = sistemaVenta;
	}
}
