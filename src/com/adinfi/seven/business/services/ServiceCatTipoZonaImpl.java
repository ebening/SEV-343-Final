package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatTipoZona;
import com.adinfi.seven.persistence.daos.DAOCatTipoZona;

public class ServiceCatTipoZonaImpl implements ServiceCatTipoZona {
	private Logger LOG = Logger.getLogger(ServiceCatTipoZonaImpl.class);
	private DAOCatTipoZona daoCatTipoZona;

	@Override
	public boolean crearCatTipoZona(CatTipoZona catTipoZona) {
		try {
			daoCatTipoZona.saveOrUpdate(catTipoZona);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public boolean eliminarCatTipoZona(CatTipoZona catTipoZona) {
		try {
			daoCatTipoZona.delete(catTipoZona);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}
	
	@Override
	public List<CatTipoZona> getCatTipoZonaList() throws Exception {
		return daoCatTipoZona.getAll();
	}

	@Override
	public CatTipoZona getCatTipoZonaById(CatTipoZona catTipoZona) {
		try {
			return daoCatTipoZona.getCatTipoZona(catTipoZona.getIdTipoZona());
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	public DAOCatTipoZona getDaoCatTipoZona() {
		return daoCatTipoZona;
	}

	public void setDaoCatTipoZona(DAOCatTipoZona daoCatTipoZona) {
		this.daoCatTipoZona = daoCatTipoZona;
	}

}