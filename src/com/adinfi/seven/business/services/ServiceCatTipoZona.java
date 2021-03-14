package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatTipoZona;

public interface ServiceCatTipoZona {

	boolean crearCatTipoZona(CatTipoZona catTipoZona);

	boolean eliminarCatTipoZona(CatTipoZona catTipoZona);
	
	List<CatTipoZona> getCatTipoZonaList() throws Exception;

	CatTipoZona getCatTipoZonaById(CatTipoZona catTipoZona);
}
