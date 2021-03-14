/**
 * 
 */
package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author OMAR
 *
 */
public class CabeceraReporteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombreCampania;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private String folio;
	
	private String tipo;
	
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MMMM/yyyy", new Locale("es", "ES"));

	/**
	 * @return the nombreCampania
	 */
	public String getNombreCampania() {
		return nombreCampania;
	}

	/**
	 * @param nombreCampania the nombreCampania to set
	 */
	public void setNombreCampania(String nombreCampania) {
		this.nombreCampania = nombreCampania;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public String getVigencia(){
		return formatDate.format(fechaInicio)+"  -  "+formatDate.format(fechaFin);
		
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
	
	

}
