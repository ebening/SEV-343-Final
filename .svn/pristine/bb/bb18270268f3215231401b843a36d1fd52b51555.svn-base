package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblCampanaAutorizacionId;

public class DAOCampanaAutorizacionImpl extends
		AbstractDaoImpl<TblCampanaAutorizacion> implements
		DAOCampanaAutorizacion {

	@SuppressWarnings("unchecked")
	@Override
	public TblCampanaAutorizacion getAutorizacion(
			TblCampanaAutorizacionId tblCampanaAutorizacionId) {
		Iterator<TblCampanaAutorizacion> tblCampanaAutorizacionIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaAutorizacion campanaAut where campanaAut.id.idAutorizacion = ?  and campanaAut.id.idOrden = ?",
						new Object[] {
								tblCampanaAutorizacionId.getIdAutorizacion(),
								tblCampanaAutorizacionId.getIdOrden() });
		return toInitializedInstance(tblCampanaAutorizacionIterator);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaAutorizacion> getAutorizacionLst(long idCampana) throws GeneralException {
		Iterator<TblCampanaAutorizacion> authLst = getHibernateTemplate()
				.iterate(
						" from TblCampanaAutorizacion a where a.idCampana= ? ",
								new Object[]{new Long(idCampana)});
		return toInitializedList(authLst);
	}

}
