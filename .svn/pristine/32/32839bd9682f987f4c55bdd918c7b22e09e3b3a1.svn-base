package com.adinfi.seven.business.services;

import java.util.List;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatListDet;
import com.adinfi.seven.persistence.daos.DAOCatListDet;

public class ServiceCatListDetImpl implements ServiceCatListDet {
	private Logger LOG = Logger.getLogger(ServiceCatListDetImpl.class);
	private DAOCatListDet daoCatListDet;

	@Override
	public boolean crearCatListDet(CatListDet catListDet) {
		try {
			daoCatListDet.saveOrUpdate(catListDet);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatListDet> getCatListDetList() throws Exception {
		return daoCatListDet.getAll();
	}

	@Override
	public boolean eliminarCatListDet(CatListDet catListDet) {
		try {
			daoCatListDet.delete(catListDet);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public CatListDet getCatListDetById(CatListDet catListDet) {
		try {
			return daoCatListDet.getCatListDet(catListDet.getId());
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	public DAOCatListDet getDaoCatListDet() {
		return daoCatListDet;
	}

	public void setDaoCatListDet(DAOCatListDet daoCatListDet) {
		this.daoCatListDet = daoCatListDet;
	}

	@Override
	public CatListDet getCatListDetById(String id) {
		try {
			return daoCatListDet.getCatListDet(id);
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

    @Override
    public List<CatListDet> getCatListDetByItemID(String itemID) {
        return daoCatListDet.getCatListDetByItemID(itemID);
    }

}