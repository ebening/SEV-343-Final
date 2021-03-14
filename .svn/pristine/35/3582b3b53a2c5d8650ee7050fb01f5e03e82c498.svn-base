package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.business.domain.TblCadenaAutorizacionDet;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOCadenaAutorizacionDetImpl extends
		AbstractDaoImpl<TblCadenaAutorizacionDet> implements
		DAOCadenaAutorizacionDet {

	@SuppressWarnings("unchecked")
	@Override
	public TblCadenaAutorizacionDet getIdOrdenCadenaAutorizacionDet(
			int idCadenaAutorizacion) {
		Iterator<TblCadenaAutorizacionDet> tblCadenaAutorizacionDetIterator = getHibernateTemplate()
				.iterate(
						" from TblCadenaAutorizacionDet cadenaAutDet WHERE cadenaAutDet.id.idCadenaAutorizacion = ? "
								+ "AND cadenaAutDet.id.idOrden IN (SELECT MAX(cadenaAutDet.id.idOrden) FROM cadenaAutDet "
								+ "WHERE cadenaAutDet.id.idCadenaAutorizacion = ? "
								+ "GROUP BY cadenaAutDet.id.idCadenaAutorizacion)",
						new Object[] { idCadenaAutorizacion,
								idCadenaAutorizacion });
		return toInitializedInstance(tblCadenaAutorizacionDetIterator);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCadenaAutorizacionDet> getCadenaAutorizacionDetList(
			TblCadenaAutorizacion tblCadenaAutorizacion)
			throws GeneralException {
		Iterator<TblCadenaAutorizacionDet> itAutDet = getHibernateTemplate()
				.iterate(
						" from TblCadenaAutorizacionDet tblCadenaAutorizacionDet where"
								+ " tblCadenaAutorizacionDet.id.idCadenaAutorizacion= ? order by tblCadenaAutorizacionDet.id.idOrden",
						tblCadenaAutorizacion.getIdCadenaAutorizacion());
		return toInitializedList(itAutDet);
	}

}
