package com.adinfi.seven.persistence.dto;

public class DiseñoDTO {
	
	private String programa;
	private String mecanica;
	private String señalamiento;
	private String grupoZona;
	private String zona;
	private String tienda;
	private String pid;
	
	public DiseñoDTO(){
		
	}
	
	public DiseñoDTO(String programa, String mecanica, String señalamiento, String grupoZona, String zona, String tienda){
		this.programa = programa;
		this.mecanica = mecanica;
		this.señalamiento = señalamiento;
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
	 * @return the señalamiento
	 */
	public String getSeñalamiento() {
		return señalamiento;
	}

	/**
	 * @param señalamiento the señalamiento to set
	 */
	public void setSeñalamiento(String señalamiento) {
		this.señalamiento = señalamiento;
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
