package com.adinfi.seven.persistence.daos;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblDelegacionActividades;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAODelegacionActividadesImpl extends
		AbstractDaoImpl<TblDelegacionActividades> implements
		DAODelegacionActividades {

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblDelegacionActividades> getDelegateActivity(String idUsuario) {
		Iterator<TblDelegacionActividades> itDelegateActivity = getHibernateTemplate()
				.iterate(
						" from TblDelegacionActividades a where a.idUsuarioDelega= ?",
						idUsuario);
		return toInitializedList(itDelegateActivity);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblDelegacionActividades> getDelegateActivity(
			String idUsuarioDelega, String idUsuarioDelegado, Date dateInit,
			Date dateEnd) throws GeneralException {
		Iterator<TblDelegacionActividades> itDelegateActivity = getHibernateTemplate()
				.iterate(
						" from TblDelegacionActividades a where a.idUsuarioDelega= ? and "
								+ "a.idUsuarioDelegado= ? and "
								+ "? between a.fechaInicio and a.fechaFin and "
								+ "? between a.fechaInicio and a.fechaFin",
						new Object[] { idUsuarioDelega, idUsuarioDelegado,
								dateInit, dateEnd });
		return toInitializedList(itDelegateActivity);
	}
}
