package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.persistence.daos.DAOCatStore;

public class ServiceCatStoreImpl implements ServiceCatStore {
	
	private final Logger LOG = Logger.getLogger(ServiceCatStoreImpl.class);
	private DAOCatStore daoCatStore;

	@Override
	public boolean crearCatStore(CatStore catStore) {
		try {
			daoCatStore.saveOrUpdate(catStore);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatStore> getCatStoreList() throws Exception {
		return daoCatStore.getAll();
	}

	@Override
	public boolean eliminarCatStore(CatStore catStore) {
		try {
			daoCatStore.delete(catStore);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public CatStore getCatStoreById(CatStore catStore) {
		try {
			return daoCatStore.getCatStore(catStore.getIdStore());
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	@Override
	public List<CatStore> getCatStoreByIds(List<Integer> idsStore) {
		try {
			return daoCatStore.getCatStore(idsStore);
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	public DAOCatStore getDaoCatStore() {
		return daoCatStore;
	}

	public void setDaoCatStore(DAOCatStore daoCatStore) {
		this.daoCatStore = daoCatStore;
	}

	@Override
	public List<CatStore> getCatStoreListByZone(Integer idZone) {
		try {
			return this.daoCatStore.getCatStoreListbyZone(idZone);
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public List<CatStore> getCatStoreListByZone(List<Integer> ids) {
		return daoCatStore.getCatStoreListbyZone(ids);
	}

}