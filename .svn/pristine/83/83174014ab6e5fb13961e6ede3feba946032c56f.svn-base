package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.BitacoraParam;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOBitacoraParamImpl extends AbstractDaoImpl<BitacoraParam>
		implements DAOBitacoraParam {
	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<BitacoraParam> getParamsListByBitacoraId(int bitacora)
			throws GeneralException {
		Iterator<BitacoraParam> paramList = null;
		try {
			paramList = getHibernateTemplate().iterate(
					"from BitacoraParam b where b.bitacora.bitacoraId= ?",
					new Object[] { bitacora });
			return toInitializedList(paramList);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
}