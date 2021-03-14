package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.List;

import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaCategorias;

public class CampanaDTO implements Serializable, Comparable<CampanaDTO> {

	private static final long serialVersionUID = -3977679405294079621L;

	private TblCampana tblCampana;
	private UsuarioDTO responsable;
	private PeriodoDTO periodo;
	private TipoCampanaDTO tipo;
	private CatTipoMedioDTO tipoMedio;
	private EtiquetaDTO etiqueta;
	private List<TblCampanaCategorias> categorias;
	private List<CampanaProgramaDTO> lCampanaProgramaDTO;
	
	private String year;

	public CampanaDTO() {
		 tblCampana = new TblCampana();
	}

	public EtiquetaDTO getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(EtiquetaDTO etiqueta) {
		this.etiqueta = etiqueta;
	}

	public TipoCampanaDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoCampanaDTO tipo) {
		this.tipo = tipo;
	}

	public PeriodoDTO getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoDTO periodo) {
		this.periodo = periodo;
	}

	public TblCampana getTblCampana() {
		return tblCampana;
	}

	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

	public UsuarioDTO getResponsable() {
		return responsable;
	}

	public void setResponsable(UsuarioDTO responsable) {
		this.responsable = responsable;
	}

	public CatTipoMedioDTO getTipoMedio() {
		return tipoMedio;
	}
	
	public void setMedio(CatTipoMedioDTO medio) {
		this.tipoMedio = medio;
	}
	
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {

		return getTblCampana().getNombre();
	}

	public List<TblCampanaCategorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<TblCampanaCategorias> categorias) {
		this.categorias = categorias;
	}

	public List<CampanaProgramaDTO> getlCampanaProgramaDTO() {
		return lCampanaProgramaDTO;
	}

	public void setlCampanaProgramaDTO(List<CampanaProgramaDTO> lCampanaProgramaDTO) {
		this.lCampanaProgramaDTO = lCampanaProgramaDTO;
	}

    @Override
    public int compareTo(CampanaDTO o) {
		return getPeriodo().compareTo(o.getPeriodo());
    }
    
    public String getEtiquetaString(){
        if(this.etiqueta.equals("#0000FF")){
            return "azul";
        }
        if(this.etiqueta.equals("#A113B1")){
            return "morado";
        }
        if(this.etiqueta.equals("#FF0000")){
            return "rojo";
        }
        return "Seleccione";
    }
}
