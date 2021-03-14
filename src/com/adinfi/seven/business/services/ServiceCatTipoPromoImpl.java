package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.CatPromo;
import com.adinfi.seven.persistence.daos.DAOCatPromo;
import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatTipoPromo;
import com.adinfi.seven.persistence.daos.DAOCatTipoPromo;

public class ServiceCatTipoPromoImpl implements ServiceCatTipoPromo  {
	
	private Logger LOG = Logger.getLogger(ServiceCatTipoPromoImpl.class);
	private DAOCatTipoPromo daoCatTipoPromo;
	private DAOCatPromo daoCatPromo;

	@Override
	public boolean tipoHavePromos(int idTipoPromo) {
		List<CatPromo> promos = daoCatPromo.getCatPromosByTipoPromo(idTipoPromo);
		if (promos != null && !promos.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean crearCatTipoPromo(CatTipoPromo catTipoPromo) {
		try {
			daoCatTipoPromo.saveOrUpdate(catTipoPromo);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;

	}

	@Override
	public boolean eliminarCatTipoPromo(CatTipoPromo catTipoPromo) {
		
		try {
			daoCatTipoPromo.delete(catTipoPromo);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatTipoPromo> getCatTipoPromos() throws Exception {
		return daoCatTipoPromo.getAll();
	}

	@Override
	public CatTipoPromo getCatTipoPromo(CatTipoPromo catTipoPromo) {
		try {
			return daoCatTipoPromo.getById(catTipoPromo.getIdTipoPromo());
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	public DAOCatTipoPromo getDaoCatTipoPromo() {
		return daoCatTipoPromo;
	}

	public void setDaoCatTipoPromo(DAOCatTipoPromo daoCatTipoPromo) {
		this.daoCatTipoPromo = daoCatTipoPromo;
	}

	public DAOCatPromo getDaoCatPromo() {
		return daoCatPromo;
	}

	public void setDaoCatPromo(DAOCatPromo daoCatPromo) {
		this.daoCatPromo = daoCatPromo;
	}
}
