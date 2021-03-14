package com.adinfi.seven.presentation.views;

import java.util.List;

import com.adinfi.seven.business.domain.TblImageArticulo;

public class ArticuloDTO {
private String sku;
private String departamento;
private String marca;
private String precio;
private String tipo;
private String medida;
private List<TblImageArticulo> imagenesArticulos;
private String imagenPath;
private String idImage;
private String descripcion;
private String categoria;





public String getIdImage() {
	return idImage;
}
public void setIdImage(String idImage) {
	this.idImage = idImage;
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
 * @return the departamento
 */
public String getDepartamento() {
	return departamento;
}
/**
 * @param departamento the departamento to set
 */
public void setDepartamento(String departamento) {
	this.departamento = departamento;
}
/**
 * @return the marca
 */
public String getMarca() {
	return marca;
}
/**
 * @param marca the marca to set
 */
public void setMarca(String marca) {
	this.marca = marca;
}
/**
 * @return the precio
 */
public String getPrecio() {
	return precio;
}
/**
 * @param precio the precio to set
 */
public void setPrecio(String precio) {
	this.precio = precio;
}
/**
 * @return the tipo
 */
public String getTipo() {
	return tipo;
}
/**
 * @param tipo the tipo to set
 */
public void setTipo(String tipo) {
	this.tipo = tipo;
}
/**
 * @return the medida
 */
public String getMedida() {
	return medida;
}
/**
 * @param medida the medida to set
 */
public void setMedida(String medida) {
	this.medida = medida;
}
/**
 * @return the imagenesArticulos
 */
public List<TblImageArticulo> getImagenesArticulos() {
	return imagenesArticulos;
}
/**
 * @param imagenesArticulos the imagenesArticulos to set
 */
public void setImagenesArticulos(List<TblImageArticulo> imagenesArticulos) {
	this.imagenesArticulos = imagenesArticulos;
}
/**
 * @return the imagenPath
 */
public String getImagenPath() {
	return imagenPath;
}
/**
 * @param imagenPath the imagenPath to set
 */
public void setImagenPath(String imagenPath) {
	this.imagenPath = imagenPath;
}
}
