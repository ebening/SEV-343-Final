package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.CatTipoPromo;

@SuppressWarnings("serial")
public class DAOCatTipoPromoImpl extends AbstractDaoImpl<CatTipoPromo> implements DAOCatTipoPromo {

	@Override
	@SuppressWarnings("unchecked")
	public CatTipoPromo getCatTipoPromo(int id) {
		
		Iterator<CatTipoPromo> tblCatTipoPromoIterator 
		= getHibernateTemplate().
		iterate("from CatTipoPromo catTipoPromo where catTipoPromo.idTipoPromo = ? ", new Object[]{id});
		return toInitializedInstance(tblCatTipoPromoIterator);
		
	}

	



}
