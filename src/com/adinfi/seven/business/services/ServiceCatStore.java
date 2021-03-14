package com.adinfi.seven.business.services;

import java.util.List;
import com.adinfi.seven.business.domain.CatStore;

public interface ServiceCatStore {

	boolean crearCatStore(CatStore catStore);

	boolean eliminarCatStore(CatStore catStore);

	List<CatStore> getCatStoreList() throws Exception;

	CatStore getCatStoreById(CatStore catStore);
	
	List<CatStore> getCatStoreListByZone(Integer idZone);
	
	public List<CatStore> getCatStoreListByZone(List<Integer> ids);
	public List<CatStore> getCatStoreByIds(List<Integer> idsStore);
}
