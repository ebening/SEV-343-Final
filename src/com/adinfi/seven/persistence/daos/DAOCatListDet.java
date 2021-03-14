package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatListDet;

public interface DAOCatListDet extends AbstractDao<CatListDet> {

	CatListDet getCatListDet(String string);

	List<CatListDet> getCatListDetList(CatListDet catListDet) throws GeneralException;
        
        List<CatListDet> getCatListDetByItemID(String itemID);
}
