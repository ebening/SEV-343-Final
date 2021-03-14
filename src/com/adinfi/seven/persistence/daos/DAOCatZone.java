package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatZone;

public interface DAOCatZone extends AbstractDao<CatZone> {

	CatZone getCatZone(Integer idZone);
	List<CatZone> getCatZoneList(CatZone catZone) throws GeneralException;
	public List<CatZone> getCatZonesByGrupoZonas(List<Integer> idGrupoZona);
	public List<CatZone> getCatZoneByIds(List<Integer> ids);
	public List<Object[]> getCatZonesWithCatGZoneByZonas(String zoneList);
}
