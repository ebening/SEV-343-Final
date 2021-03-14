package com.adinfi.seven.business.services;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.persistence.daos.DAOCatGZone;

public class ServiceCatGZoneImpl implements ServiceCatGZone {
	
	private final Logger LOG = Logger.getLogger(ServiceCatGZoneImpl.class);
	private DAOCatGZone daoCatGZone;

	@Override
	public boolean crearCatGZone(CatGZone catGZone) {
		try {
			daoCatGZone.saveOrUpdate(catGZone);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatGZone> getCatGZoneList() throws Exception {
		return daoCatGZone.getAll();
	}

	@Override
	public boolean eliminarCatGZone(CatGZone catGZone) {
		try {
			daoCatGZone.delete(catGZone);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public CatGZone getCatGZoneById(Integer idGrupoZona) {
		return daoCatGZone.getCatGZone(idGrupoZona);
	}

	@Override
	public List<CatGZone> getCatGZoneById(List<Integer> ids) {
		return daoCatGZone.getCatGZone(ids);
	}

	public DAOCatGZone getDaoCatGZone() {
		return daoCatGZone;
	}

	public void setDaoCatGZone(DAOCatGZone daoCatGZone) {
		this.daoCatGZone = daoCatGZone;
	}
    
    @Override
    public Map<Integer, Boolean> hasZoneStores(List<CatZone> catZoneList){
        return this.daoCatGZone.hasZoneStores(catZoneList);
    }

}