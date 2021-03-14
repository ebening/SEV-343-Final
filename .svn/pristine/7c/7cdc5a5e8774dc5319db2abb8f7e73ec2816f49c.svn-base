package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import com.adinfi.seven.business.domain.CatTipoZona;

public class DAOCatTipoZonaImpl extends AbstractDaoImpl<CatTipoZona> implements DAOCatTipoZona {

	@SuppressWarnings("unchecked")
	@Override
	public CatTipoZona getCatTipoZona(Integer idTipoZona) {
		Iterator<CatTipoZona> tblCatTipoZonaIterator = getHibernateTemplate()
				.iterate(
						"from CatTipoZona catTipoZona where catTipoZona.idTipoZona = ? ",
						new Object[] { idTipoZona });
		return toInitializedInstance(tblCatTipoZonaIterator);
	}

}
