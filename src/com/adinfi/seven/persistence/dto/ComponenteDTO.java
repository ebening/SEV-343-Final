package com.adinfi.seven.persistence.dto;

import java.util.List;

public class ComponenteDTO{
	private int pkComp;
	private int pkMec;
	private int numeroComponente;
	private int idCateg;
	private int idSubCategoria;
	private int idProveedor;
	private String idDescription;
	private int idLista;
	private int cantidadProd;
	private int idEspacioPromocional;
	private Integer numero;
	private Integer abastoInicial;
	private Integer unidades;
	private Integer hoja;
	private List<GenericItem> skuList;
	private GenericItemString upcList;
	//PRIMICIA
	private String primDescripcion;
	private Integer primCat;
	private String primUpc;
	private Double primPrecio;
    
    private Double precioVentaOriginal;

	public int getPkComp() {
		return pkComp;
	}
	public void setPkComp(int pkComp) {
		this.pkComp = pkComp;
	}
	public int getPkMec() {
		return pkMec;
	}
	public void setPkMec(int pkMec) {
		this.pkMec = pkMec;
	}
	public int getIdCateg() {
		return idCateg;
	}
	public void setIdCateg(int idCateg) {
		this.idCateg = idCateg;
	}
	public int getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getIdDescription() {
		return idDescription;
	}
	public void setIdDescription(String idDescription) {
		this.idDescription = idDescription;
	}
	public int getIdLista() {
		return idLista;
	}
	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}
	public int getCantidadProd() {
		return cantidadProd;
	}
	public void setCantidadProd(int cantidadProd) {
		this.cantidadProd = cantidadProd;
	}
	public int getIdEspacioPromocional() {
		return idEspacioPromocional;
	}
	public void setIdEspacioPromocional(int idEspacioPromocional) {
		this.idEspacioPromocional = idEspacioPromocional;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getAbastoInicial() {
		return abastoInicial;
	}
	public void setAbastoInicial(Integer abastoInicial) {
		this.abastoInicial = abastoInicial;
	}
	public Integer getUnidades() {
		return unidades;
	}
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	public List<GenericItem> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<GenericItem> skuList) {
		this.skuList = skuList;
	}
	public GenericItemString getUpcList() {
		return upcList;
	}
	public void setUpcList(GenericItemString upcList) {
		this.upcList = upcList;
	}
	public int getNumeroComponente() {
		return numeroComponente;
	}
	public void setNumeroComponente(int numeroComponente) {
		this.numeroComponente = numeroComponente;
	}
	public String getPrimDescripcion() {
		return primDescripcion;
	}
	public void setPrimDescripcion(String primDescripcion) {
		this.primDescripcion = primDescripcion;
	}
	public Integer getPrimCat() {
		return primCat;
	}
	public void setPrimCat(Integer primCat) {
		this.primCat = primCat;
	}
	public String getPrimUpc() {
		return primUpc;
	}
	public void setPrimUpc(String primUpc) {
		this.primUpc = primUpc;
	}
	public Double getPrimPrecio() {
		return primPrecio;
	}
	public void setPrimPrecio(Double primPrecio) {
		this.primPrecio = primPrecio;
	}

	public Integer getHoja() {
		return hoja;
	}

	public void setHoja(Integer hoja) {
		this.hoja = hoja;
	}

    /**
     * @return the precioVentaOriginal
     */
    public Double getPrecioVentaOriginal() {
        return precioVentaOriginal;
    }

    /**
     * @param precioVentaOriginal the precioVentaOriginal to set
     */
    public void setPrecioVentaOriginal(Double precioVentaOriginal) {
        this.precioVentaOriginal = precioVentaOriginal;
    }
}