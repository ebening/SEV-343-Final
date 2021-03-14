package com.adinfi.seven.business.services;

import java.util.List;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatPromo;
import com.adinfi.seven.persistence.daos.DAOCatPromo;

public class ServiceCatPromoImpl implements ServiceCatPromo {
	
	private Logger LOG = Logger.getLogger(ServiceCatPromoImpl.class);
	private DAOCatPromo daoCatPromo;

	@Override
	public boolean crearCatPromo(CatPromo catPromo) {
		try {
			daoCatPromo.saveOrUpdate(catPromo);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public boolean eliminarCatPromo(CatPromo catPromo) {

		try {
			 daoCatPromo.delete(catPromo);
			 return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatPromo> getCatPromos() throws Exception {
		return daoCatPromo.getCatPromos();
	}

	@Override
	public CatPromo getCatPromo(CatPromo catPromo) {
		return daoCatPromo.getCatPromo(catPromo.getIdPromo());
	}

	public Logger getLOG() {
		return LOG;
	}

	public void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public DAOCatPromo getDaoCatPromo() {
		return daoCatPromo;
	}

	public void setDaoCatPromo(DAOCatPromo daoCatPromo) {
		this.daoCatPromo = daoCatPromo;
	}
	
}
