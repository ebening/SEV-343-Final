/**
 * 
 */
package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author OMAR
 *
 */
public class ReporteVentaFiltroDTO  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String filtro;
	
    private int ventaUnidades;
	
	private BigDecimal ventaPesos;

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
	 * @return the ventaUnidades
	 */
	public int getVentaUnidades() {
		return ventaUnidades;
	}

	/**
	 * @param ventaUnidades the ventaUnidades to set
	 */
	public void setVentaUnidades(int ventaUnidades) {
		this.ventaUnidades = ventaUnidades;
	}

	/**
	 * @return the ventaPesos
	 */
	public BigDecimal getVentaPesos() {
		return ventaPesos;
	}

	/**
	 * @param ventaPesos the ventaPesos to set
	 */
	public void setVentaPesos(BigDecimal ventaPesos) {
		this.ventaPesos = ventaPesos;
	}
	
	public void addValorVentaUnidades(int ventaUnidades){
		this.ventaUnidades = this.ventaUnidades + ventaUnidades;
		
	}
	
	public void addValorVentaPesos(BigDecimal ventaPesos){
		this.ventaPesos = this.ventaPesos.add(ventaPesos);
	}
	
	public String getVentaPesosFormato(){
		return Constants.formatNumber.format( ventaPesos) ;
	}
	
	public String getVentaUnidadesFormato(){
		return Constants.formatNumber.format( ventaUnidades) ;
	}
	

}
