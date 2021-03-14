package com.adinfi.seven.presentation.views;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CatMedioDTO;
import com.adinfi.seven.persistence.dto.CatTipoMedioDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.NegocioDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.SubCategoriasDTO;
import com.adinfi.seven.persistence.dto.SucursalDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.persistence.dto.ZonaDTO;
import com.adinfi.seven.presentation.views.util.MBUtil;

public class MBAuxConverter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -484226894861187927L;
	private Logger LOG = Logger.getLogger(MBAuxConverter.class);;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;

	public CatTipoMedioDTO getTipoMedio(java.lang.Integer id) {
		CatTipoMedioDTO tipoMedio = null;
		try {
			tipoMedio = MBUtil.getTipo(serviceDynamicCatalogs, id);
		} catch (Exception e) {

			LOG.info(e);
		}

		return tipoMedio;
	}

	public CatMedioDTO getMedio(java.lang.Integer id) {
		CatMedioDTO medio = null;
		try {
			medio = MBUtil.getMedio(serviceDynamicCatalogs, id);
		} catch (Exception e) {

			LOG.info(e);
		}
		return medio;
	}

	public EtiquetaDTO getEtiqueta(java.lang.Integer id) {
		EtiquetaDTO etiqueta = null;
		try {
			etiqueta = MBUtil.getEtiqueta(serviceDynamicCatalogs, id);
		} catch (Exception e) {

			LOG.info(e);
		}
		return etiqueta;
	}

	public SubCategoriasDTO getSubCategoria(java.lang.Integer id) {
		SubCategoriasDTO etiqueta = null;
		try {
			etiqueta = MBUtil.getSubCategoria(serviceDynamicCatalogs, id);
		} catch (Exception e) {

			LOG.info(e);
		}
		return etiqueta;
	}

	public TipoCampanaDTO getTipoCampana(java.lang.Integer id) {
		TipoCampanaDTO tipo = null;
		try {
			tipo = MBUtil.getTipoCampana(serviceDynamicCatalogs, id);
		} catch (Exception e) {
			LOG.info(e);
		}
		return tipo;
	}
	
	public PeriodoDTO getPeriodo(java.lang.Integer id) {
		PeriodoDTO periodo = null;
		try {
			periodo = MBUtil.getPeriodoAlterno(serviceDynamicCatalogs, id);
		} catch (Exception e) {
			LOG.info(e);
		}
		return periodo;
	}

	public UsuarioDTO getUsuario(java.lang.Integer id) {
		UsuarioDTO user = null;
		try {
			user = MBUtil.getUsuario(serviceDynamicCatalogs, id);
		} catch (Exception e) {
			LOG.info(e);
		}
		return user;
	}

	/**
	 * @return the serviceDynamicCatalogs
	 */
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	/**
	 * @param serviceDynamicCatalogs
	 *            the serviceDynamicCatalogs to set
	 */
	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public NegocioDTO getNegocio(Integer id) {
		NegocioDTO negocio = null;
		try {
			negocio = MBUtil.getNegocio(serviceDynamicCatalogs, id);
		} catch (Exception e) {
			LOG.info(e);
		}
		return negocio;
	}

	public ZonaDTO getZona(Integer key) {
		ZonaDTO zona = null;
		try {
			zona = MBUtil.getZona(serviceDynamicCatalogs, key);
		} catch (Exception e) {
			LOG.info(e);
		}
		return zona;
	}

	public SucursalDTO getSucursal(Integer key) {
		SucursalDTO sucursal = null;
		try {
			sucursal = MBUtil.getSucursal(serviceDynamicCatalogs, key);
		} catch (Exception e) {
			LOG.info(e);
		}
		return sucursal;
	}

	public CategoriaDTO getCategoria(Integer key) {
		CategoriaDTO categoria = null;
		try {
			categoria = MBUtil.getCategoria(serviceDynamicCatalogs, key);
		} catch (Exception e) {
			LOG.info(e);
		}
		return categoria;
	}

}
