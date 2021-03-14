package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class ConsultarEstrategiaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String zonaA;
	private String zonaB;
	private String precio;
	private String producto;
	public String getZonaA() {
		return zonaA;
	}
	public void setZonaA(String zonaA) {
		this.zonaA = zonaA;
	}
	public String getZonaB() {
		return zonaB;
	}
	public void setZonaB(String zonaB) {
		this.zonaB = zonaB;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
}