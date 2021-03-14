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
public class ReporteInventarioFiltroDTO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String filtro;
	
	private int inventarioTienda;
	
	private int inventarioCedis;
	
	private int inventarioTransito;

	/**
	 * @return the filtro
	 */
	public String getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(String filtro) {
		this.filtro = filtro;
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
	
	public void addValorInventarioTienda(int inventarioTienda){
		this.inventarioTienda = this.inventarioTienda + inventarioTienda;
		
	}
	
	public void addValorInventarioCedis(int inventarioCedis){
		this.inventarioCedis = this.inventarioCedis + inventarioCedis;
		
	}
	
	public void addValorInventarioTransito(int inventarioTransito){
		this.inventarioTransito = this.inventarioTransito + inventarioTransito;
		
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
