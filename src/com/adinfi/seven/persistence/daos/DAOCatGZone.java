package com.adinfi.seven.persistence.daos;

import java.util.List;
import java.util.Map;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatZone;

public interface DAOCatGZone extends AbstractDao<CatGZone> {

	public CatGZone getCatGZone(Integer idCadenaAutorizacion);
	public Map<Integer, Boolean> hasZoneStores(List<CatZone> catZoneList);
	public List<CatGZone> getCatGZone(List<Integer> idCatGZone);
}
