/**
 * 
 */
package com.adinfi.seven.persistence.dto;

import java.util.List;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author OMAR
 *
 */
public class ItemDTO {
	
	/* id_item */
	private String sku;
	
	/*  code */
	private String descripcionSku;
	
	private String ventasHistoricas;
	
	/*  margen_contribucion*/
	private String margenContribucion;
	
	private String inventario;
	
	private boolean articuloNuevo;
	
	private String fechaUltimaPromocion;
	
	private String tipoUltimaPromocion;
	
	private String precioUltimaPromocion;
	
	private String tipoPromocionAnioAnterior;
	
	private String precioPromocionAnioAnterior;
	
	private int idDepartamento;
	
	private int idCategoria;
	
	private int idSubcategoria;
	
	private String titulo;
	
	private String departamento;
	
	private String categoria;
	
	private String subcategoria;
	
	private Boolean isItem;
	
	private String precioPromocion;
	
	private String tipoPromocion;
	
	private List<String> tipoPublicidad;
	
	/**Demo*/
	private String campoFechaUno;
	private String campoTipoUno;
	private String campoPrecioUno;
	private String campoFechaDos;
	private String campoTipoDos;
	private String campoPrecioDos;

	public ItemDTO(String titulo, Boolean isItem) {
		super();
		this.titulo = titulo;
		this.isItem = isItem;
	}

	public ItemDTO() {
		super();
		setIsItem(Boolean.TRUE);
	}

	/**
	 * @return the isItem
	 */
	public Boolean getIsItem() {
		return isItem;
	}

	/**
	 * @param isItem the isItem to set
	 */
	public void setIsItem(Boolean isItem) {
		this.isItem = isItem;
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
	public String getDescripcionSku() {
		return descripcionSku;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcionSku(String descripcionSku) {
		this.descripcionSku = descripcionSku;
	}

	/**
	 * @return the ventasHistoricas
	 */
	public String getVentasHistoricas() {
		if(ventasHistoricas.equals(Constants.EMPTY)){
			ventasHistoricas = Constants.ZERO_STRING;
		}
		return ventasHistoricas;
	}

	/**
	 * @param ventasHistoricas the ventasHistoricas to set
	 */
	public void setVentasHistoricas(String ventasHistoricas) {
		this.ventasHistoricas = ventasHistoricas;
	}

	/**
	 * @return the margenContribucion
	 */
	public String getMargenContribucion() {
		if(margenContribucion.equals(Constants.EMPTY)){
			margenContribucion = Constants.ZERO_STRING;
		}
		return margenContribucion;
	}

	/**
	 * @param margenContribucion the margenContribucion to set
	 */
	public void setMargenContribucion(String margenContribucion) {
		this.margenContribucion = margenContribucion;
	}

	/**
	 * @return the inventario
	 */
	public String getInventario() {
		if(inventario.equals(Constants.EMPTY)){
			inventario = Constants.ZERO_STRING;
		}
		return inventario;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(String inventario) {
		this.inventario = inventario;
	}



	/**
	 * @return the articuloNuevo
	 */
	public boolean isArticuloNuevo() {
		return articuloNuevo;
	}

	/**
	 * @param articuloNuevo the articuloNuevo to set
	 */
	public void setArticuloNuevo(boolean articuloNuevo) {
		this.articuloNuevo = articuloNuevo;
	}

	/**
	 * @return the fechaUltimaPromocion
	 */
	public String getFechaUltimaPromocion() {
		return fechaUltimaPromocion;
	}

	/**
	 * @param fechaUltimaPromocion the fechaUltimaPromocion to set
	 */
	public void setFechaUltimaPromocion(String fechaUltimaPromocion) {
		this.fechaUltimaPromocion = fechaUltimaPromocion;
	}

	/**
	 * @return the tipoUltimaPromocion
	 */
	public String getTipoUltimaPromocion() {
		return tipoUltimaPromocion;
	}

	/**
	 * @param tipoUltimaPromocion the tipoUltimaPromocion to set
	 */
	public void setTipoUltimaPromocion(String tipoUltimaPromocion) {
		this.tipoUltimaPromocion = tipoUltimaPromocion;
	}

	/**
	 * @return the precioUltimaPromocion
	 */
	public String getPrecioUltimaPromocion() {
		if(precioUltimaPromocion.equals(Constants.EMPTY)){
			precioUltimaPromocion = Constants.ZERO_STRING;
		}
		return precioUltimaPromocion;
	}

	/**
	 * @param precioUltimaPromocion the precioUltimaPromocion to set
	 */
	public void setPrecioUltimaPromocion(String precioUltimaPromocion) {
		this.precioUltimaPromocion = precioUltimaPromocion;
	}

	/**
	 * @return the tipoPromocionAnioAnterior
	 */
	public String getTipoPromocionAnioAnterior() {
		return tipoPromocionAnioAnterior;
	}

	/**
	 * @param tipoPromocionAnioAnterior the tipoPromocionAnioAnterior to set
	 */
	public void setTipoPromocionAnioAnterior(String tipoPromocionAnioAnterior) {
		this.tipoPromocionAnioAnterior = tipoPromocionAnioAnterior;
	}

	/**
	 * @return the precioPromocionAnioAnterior
	 */
	public String getPrecioPromocionAnioAnterior() {
		if(precioPromocionAnioAnterior.equals(Constants.EMPTY)){
			precioPromocionAnioAnterior = Constants.ZERO_STRING;
		}
		return precioPromocionAnioAnterior;
	}

	/**
	 * @param precioPromocionAnioAnterior the precioPromocionAnioAnterior to set
	 */
	public void setPrecioPromocionAnioAnterior(String precioPromocionAnioAnterior) {
		this.precioPromocionAnioAnterior = precioPromocionAnioAnterior;
	}

	/**
	 * @return the idDepartamento
	 */
	public int getIdDepartamento() {
		return idDepartamento;
	}

	/**
	 * @param idDepartamento the idDepartamento to set
	 */
	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	/**
	 * @return the idCategoria
	 */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the idSubcategoria
	 */
	public int getIdSubcategoria() {
		return idSubcategoria;
	}

	/**
	 * @param idSubcategoria the idSubcategoria to set
	 */
	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * @return the precioPromocion
	 */
	public String getPrecioPromocion() {
		return precioPromocion;
	}

	/**
	 * @param precioPromocion the precioPromocion to set
	 */
	public void setPrecioPromocion(String precioPromocion) {
		this.precioPromocion = precioPromocion;
	}

	/**
	 * @return the tipoPromocion
	 */
	public String getTipoPromocion() {
		return tipoPromocion;
	}

	/**
	 * @param tipoPromocion the tipoPromocion to set
	 */
	public void setTipoPromocion(String tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	/**
	 * @return the tipoPublicidad
	 */
	public List<String> getTipoPublicidad() {
		return tipoPublicidad;
	}

	/**
	 * @param tipoPublicidad the tipoPublicidad to set
	 */
	public void setTipoPublicidad(List<String> tipoPublicidad) {
		this.tipoPublicidad = tipoPublicidad;
	}
	
	
	public String getTipoPublicidadExport(){
		String resultado = Constants.EMPTY;
		
		for(String value:tipoPublicidad){
			resultado +=  value + " -  ";
		}
		
		return resultado;
	}

	public String getCampoFechaUno() {
		return campoFechaUno;
	}

	public void setCampoFechaUno(String campoFechaUno) {
		this.campoFechaUno = campoFechaUno;
	}

	public String getCampoTipoUno() {
		return campoTipoUno;
	}

	public void setCampoTipoUno(String campoTipoUno) {
		this.campoTipoUno = campoTipoUno;
	}

	public String getCampoPrecioUno() {
		return campoPrecioUno;
	}

	public void setCampoPrecioUno(String campoPrecioUno) {
		this.campoPrecioUno = campoPrecioUno;
	}

	public String getCampoFechaDos() {
		return campoFechaDos;
	}

	public void setCampoFechaDos(String campoFechaDos) {
		this.campoFechaDos = campoFechaDos;
	}

	public String getCampoTipoDos() {
		return campoTipoDos;
	}

	public void setCampoTipoDos(String campoTipoDos) {
		this.campoTipoDos = campoTipoDos;
	}

	public String getCampoPrecioDos() {
		return campoPrecioDos;
	}

	public void setCampoPrecioDos(String campoPrecioDos) {
		this.campoPrecioDos = campoPrecioDos;
	}

	
	

}
