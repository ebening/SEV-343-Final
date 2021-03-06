package com.adinfi.seven.business.services;

import java.util.List;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.persistence.daos.DAOCatZone;

public class ServiceCatZoneImpl implements ServiceCatZone {
	
	private final Logger LOG = Logger.getLogger(ServiceCatZoneImpl.class);
	private DAOCatZone daoCatZone;

	@Override
	public boolean crearCatZone(CatZone catZone) {
		try {
			daoCatZone.saveOrUpdate(catZone);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatZone> getCatZoneList() throws Exception {
		return daoCatZone.getAll();
	}

	@Override
	public boolean eliminarCatZone(CatZone catZone) {
		try {
			daoCatZone.delete(catZone);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public CatZone getCatZoneById(int catZone) {
		try {
			return daoCatZone.getCatZone(catZone);
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	@Override
	public CatZone getCatZoneById(CatZone catZone) {
		return getCatZoneById(catZone.getIdZone());
	}

	public DAOCatZone getDaoCatZone() {
		return daoCatZone;
	}

	public void setDaoCatZone(DAOCatZone daoCatZone) {
		this.daoCatZone = daoCatZone;
	}
	
	@Override
	public List<CatZone> getCatZonesByGrupoZonas(List<Integer> idGrupoZona) {
		return daoCatZone.getCatZonesByGrupoZonas(idGrupoZona);
	}
	
	@Override
	public List<Object[]> getCatZonesWithCatGZoneByZonas(String zoneList) {
		return daoCatZone.getCatZonesWithCatGZoneByZonas(zoneList);
	}
	
	@Override
	public List<CatZone> getCatZoneByIds(List<Integer> ids) {
		return daoCatZone.getCatZoneByIds(ids);
	}


}