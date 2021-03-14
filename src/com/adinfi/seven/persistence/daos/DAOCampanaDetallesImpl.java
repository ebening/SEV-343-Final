package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaDetalle;

public class DAOCampanaDetallesImpl extends AbstractDaoImpl<TblCampanaDetalle> implements
		DAOCampanaDetalles {

	@SuppressWarnings("unchecked")
	@Override
	public TblCampanaDetalle getCampDetByIdCampana(long idCampana) {
		Iterator<TblCampanaDetalle> CamDetRegsList = getHibernateTemplate()
				.iterate(
						"from TblCampanaDetalle campDet where campDet.id.idCampana = ? ",
						new Object[] { idCampana });
		return toInitializedInstance(CamDetRegsList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaDetalle> getCampanaDetallesListByidCampana(
			long idCampana) throws GeneralException {
		Iterator<TblCampanaDetalle> itRegistroCampanaDetalle = getHibernateTemplate()
				.iterate(
						" from TblCampanaDetalle tblCampDet where tblCampDet.id.idCampana= ? "
								+ idCampana);
		return toInitializedList(itRegistroCampanaDetalle);
	}

	public int getIdCategoriaByIdCampana(long idCampana) {
		Query query = this
				.getSession()
				.createQuery(
						"select idCategoria from  TblCampanaDetalle campDet where campDet.id.idCampana= "
								+ idCampana);
		int idCategoria = query.executeUpdate();
		return idCategoria;
	}
}
