package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.BitacoraTipo;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOBitacoraTipoImpl extends AbstractDaoImpl<BitacoraTipo>
		implements DAOBitacoraTipo {

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<BitacoraTipo> getTipoActivo() throws GeneralException {
		Iterator<BitacoraTipo> bitacoraTipoIt = null;
		try {
			bitacoraTipoIt = getHibernateTemplate().iterate(
					"from BitacoraTipo b where b.active= 1");
			return toInitializedList(bitacoraTipoIt);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
}
