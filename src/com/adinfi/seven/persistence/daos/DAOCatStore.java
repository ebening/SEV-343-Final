package com.adinfi.seven.persistence.daos;

import java.util.List;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatStore;

public interface DAOCatStore extends AbstractDao<CatStore> {

	CatStore getCatStore(Integer idStore);

	List<CatStore> getCatStoreList(CatStore catStore) throws GeneralException;
	
	List<CatStore> getCatStoreListbyZone(Integer idZone) throws GeneralException;
	
	public List<CatStore> getCatStoreListbyZone(List<Integer> ids);
	public List<CatStore> getCatStore(List<Integer> idsStore);
}
