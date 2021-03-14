package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblPrensa;

public class DAOPrensaImpl extends AbstractDaoImpl<TblPrensa> implements
		DAOPrensa {

	@Override
	public boolean validarPrensa(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblPrensa> tblfolletoSistemaVenta = getHibernateTemplate()
				.iterate(
						"from TblPrensa obj where obj.idPrensa = ? ",
						new Object[] {idFolleto});
		List<TblPrensa> list = toInitializedList(tblfolletoSistemaVenta);	
		return (!list.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblPrensa> getTblPrensaLstByIdCampana(long idCampana) throws Exception {
		Iterator<TblPrensa> prensaLst = null ;
		prensaLst =	getHibernateTemplate().iterate(" FROM TblPrensa a WHERE a.tblCampana.idCampana = ?", new Object[] {idCampana});
		return toInitializedList(prensaLst);
	}
}