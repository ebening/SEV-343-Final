package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOCadenaAutorizacionImpl extends
		AbstractDaoImpl<TblCadenaAutorizacion> implements DAOCadenaAutorizacion {

	@SuppressWarnings("unchecked")
	@Override
	public TblCadenaAutorizacion getCadenaAutorizacion(int idCadenaAutorizacion) {
		Iterator<TblCadenaAutorizacion> tblCadenaAutorizacionIterator = getHibernateTemplate()
				.iterate(
						"from TblCadenaAutorizacion cadenaAut where cadenaAut.idCadenaAutorizacion = ? ",
						new Object[] { idCadenaAutorizacion });
		return toInitializedInstance(tblCadenaAutorizacionIterator);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCadenaAutorizacion> getCadenaAutorizacionList(
			TblCadenaAutorizacion tblCadenaAutorizacion)
			throws GeneralException {
		Iterator<TblCadenaAutorizacion> itActivity = getHibernateTemplate()
				.iterate(
						" from TblCadenaAutorizacion tblCadenaAutorizacion where"
								+ " tblCadenaAutorizacion.idCadenaAutorizacion= ?",
						tblCadenaAutorizacion.getIdCadenaAutorizacion());
		return toInitializedList(itActivity);
	}

}
