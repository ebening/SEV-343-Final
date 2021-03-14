package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatListDet;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOCatListDetImpl extends AbstractDaoImpl<CatListDet> implements DAOCatListDet {

	@SuppressWarnings("unchecked")
	@Override
	public CatListDet getCatListDet(String idCatListDet) {
		Iterator<CatListDet> tblCatListDetIterator = getHibernateTemplate()
				.iterate(
						"from CatListDet catListDet where catListDet.idItem = ? ",
						new Object[] { idCatListDet });
		return toInitializedInstance(tblCatListDetIterator);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatListDet> getCatListDetList(
			CatListDet catListDet)
			throws GeneralException {
		Iterator<CatListDet> itCatListDet = getHibernateTemplate()
				.iterate(
						" from CatListDet catListDet where catListDet.id= ?",
								catListDet.getId());
		return toInitializedList(itCatListDet);
	}

    @Override
    public List<CatListDet> getCatListDetByItemID(String itemID) {
        return getHibernateTemplate().find("FROM CatListDet cat WHERE cat.catItem.idItem = ?", itemID);
    }

}
