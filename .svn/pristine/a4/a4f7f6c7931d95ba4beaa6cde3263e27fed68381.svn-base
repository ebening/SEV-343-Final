package com.adinfi.seven.business.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author christian
 */
public class TblFolleto implements java.io.Serializable {

	private static final long serialVersionUID = -6904526311522780433L;
     private int idFolleto;
     private TblCampana tblCampana;
     private Date fechaInicio;
     private Date fechaFin;
     private Integer hojas;
     private Set tblPreciosFolletos = new HashSet(0);
	private Map<Integer , TblFolletoHojas> mapHojas;

	public Map<Integer, TblFolletoHojas> getMapHojas() {
		return mapHojas;
	}

	public void setMapHojas(Map<Integer, TblFolletoHojas> mapHojas) {
		this.mapHojas = mapHojas;
	}

	public TblFolleto() {
	}

	
    public TblFolleto(int idFolleto) {
        this.idFolleto = idFolleto;
    }
    
    public TblFolleto(int idFolleto, TblCampana tblCampana, Date fechaInicio, Date fechaFin, Integer hojas, Set tblPreciosFolletos) {
       this.idFolleto = idFolleto;
       this.tblCampana = tblCampana;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.hojas = hojas;
       this.tblPreciosFolletos = tblPreciosFolletos;
    }
   
    public int getIdFolleto() {
        return this.idFolleto;
    }
    
    public void setIdFolleto(int idFolleto) {
        this.idFolleto = idFolleto;
    }
    public TblCampana getTblCampana() {
        return this.tblCampana;
    }
    
    public void setTblCampana(TblCampana tblCampana) {
        this.tblCampana = tblCampana;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Integer getHojas() {
        return this.hojas;
    }
    
    public void setHojas(Integer hojas) {
        this.hojas = hojas;
    }
    public Set getTblPreciosFolletos() {
        return this.tblPreciosFolletos;
    }
    
    public void setTblPreciosFolletos(Set tblPreciosFolletos) {
        this.tblPreciosFolletos = tblPreciosFolletos;
    }




}


