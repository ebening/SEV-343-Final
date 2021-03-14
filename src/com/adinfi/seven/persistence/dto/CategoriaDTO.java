package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String codigo;
	private Character esMercancia;
	private UsuarioDTO usuDto;
	private CampanaMedioDTO campanaMedioDTO;
	private Long idCampana;
	private String programa;
	private double ingreso;
	private String esSencillo;
	private String plazaSelect;

	public Integer getId() {
		return id;
	}

	public CampanaMedioDTO getCampanaMedioDTO() {
		return campanaMedioDTO;
	}

	public void setCampanaMedioDTO(CampanaMedioDTO campanaMedioDTO) {
		this.campanaMedioDTO = campanaMedioDTO;
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
	/**
	 * @return the esMercancia
	 */
	public Character getEsMercancia() {
		return esMercancia;
	}
	/**
	 * @param esMercancia the esMercancia to set
	 */
	public void setEsMercancia(Character esMercancia) {
		this.esMercancia = esMercancia;
	}
	


	@Override
	public String toString() {
		return getCodigo();
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof CategoriaDTO)) {
			return false;
		}
		CategoriaDTO other = (CategoriaDTO) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public UsuarioDTO getUsuDto() {
		return usuDto;
	}

	public void setUsuDto(UsuarioDTO usuDto) {
		this.usuDto = usuDto;
	}

	public Long getIdCampana() {
		return idCampana;
	}

	public void setIdCampana(Long idCampana) {
		this.idCampana = idCampana;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public double getIngreso() {
		return ingreso;
	}

	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}

	public String getEsSencillo() {
		return esSencillo;
	}

	public void setEsSencillo(String esSencillo) {
		this.esSencillo = esSencillo;
	}

	public String getPlazaSelect() {
		return plazaSelect;
	}

	public void setPlazaSelect(String plazaSelect) {
		this.plazaSelect = plazaSelect;
	}
	
}
