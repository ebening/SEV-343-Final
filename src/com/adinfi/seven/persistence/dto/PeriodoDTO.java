package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.Date;

import com.adinfi.seven.presentation.views.util.Util;


public class PeriodoDTO implements Serializable, Comparable<PeriodoDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String codigo;
	private String duracionDias;
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicial;
	private Date fechaFinal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDuracionDias() {
		return duracionDias;
	}

	public void setDuracionDias(String duracionDias) {
		this.duracionDias = duracionDias;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {

		this.fechaInicio = Util.fechaFormatComplete(fechaInicio);
	}


	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = Util.fechaFormatComplete(fechaFin);
	}
	
	public String getPeriodoStr(){
		return toString();
	}

	@Override
	public String toString() {
            String retVal;
            if (getCodigo() == null){
                    retVal = getFechaInicio() + " al " + getFechaFin();
            }else{
                    retVal = getCodigo() + " : " + getFechaInicio() + " a " + getFechaFin();
            }
            return retVal;
	}
    
    public String getShortDescription(){
        String s = this.toString();
        if(s.length() > 50){
            String sd = s.substring(0, 48);
            sd+="...";
            return sd;
        }else{
            return s;
        }
    }
	
	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public String getPeriodoDesc(){	
			return getFechaInicial() + " - " + getFechaFinal();
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof PeriodoDTO)) {
			return false;
		}
		PeriodoDTO other = (PeriodoDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

    @Override
    public int compareTo(PeriodoDTO o){
        if(getFechaInicial() != null && o.getFechaInicial() != null){
            return getFechaInicial().compareTo(o.getFechaInicial());
        }else{
            return 1;
        }
            
    }
}
