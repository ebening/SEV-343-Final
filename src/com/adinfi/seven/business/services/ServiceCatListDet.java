package com.adinfi.seven.business.services;

import java.util.List;
import com.adinfi.seven.business.domain.CatListDet;

public interface ServiceCatListDet {

	boolean crearCatListDet(CatListDet catListDet);

	boolean eliminarCatListDet(CatListDet catListDet);

	List<CatListDet> getCatListDetList() throws Exception;
        
        List<CatListDet> getCatListDetByItemID(String itemID);

	CatListDet getCatListDetById(CatListDet catListDet);
	
	CatListDet getCatListDetById(String id);
}
