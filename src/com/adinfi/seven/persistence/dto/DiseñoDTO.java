package com.adinfi.seven.persistence.dto;

public class Dise�oDTO {
	
	private String programa;
	private String mecanica;
	private String se�alamiento;
	private String grupoZona;
	private String zona;
	private String tienda;
	private String pid;
	
	public Dise�oDTO(){
		
	}
	
	public Dise�oDTO(String programa, String mecanica, String se�alamiento, String grupoZona, String zona, String tienda){
		this.programa = programa;
		this.mecanica = mecanica;
		this.se�alamiento = se�alamiento;
		this.grupoZona = grupoZona;
		this.zona = zona;
		this.tienda = tienda;
	}
	
	/**
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * @return the mecanica
	 */
	public String getMecanica() {
		return mecanica;
	}

	/**
	 * @param mecanica the mecanica to set
	 */
	public void setMecanica(String mecanica) {
		this.mecanica = mecanica;
	}

	/**
	 * @return the se�alamiento
	 */
	public String getSe�alamiento() {
		return se�alamiento;
	}

	/**
	 * @param se�alamiento the se�alamiento to set
	 */
	public void setSe�alamiento(String se�alamiento) {
		this.se�alamiento = se�alamiento;
	}

	/**
	 * @return the grupoZona
	 */
	public String getGrupoZona() {
		return grupoZona;
	}

	/**
	 * @param grupoZona the grupoZona to set
	 */
	public void setGrupoZona(String grupoZona) {
		this.grupoZona = grupoZona;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the tienda
	 */
	public String getTienda() {
		return tienda;
	}

	/**
	 * @param tienda the tienda to set
	 */
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
