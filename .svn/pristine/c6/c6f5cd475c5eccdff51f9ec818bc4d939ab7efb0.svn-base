package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;
import com.adinfi.seven.business.domain.TblFolleto;

public class DAOFolletoImpl extends AbstractDaoImpl<TblFolleto> implements
		DAOFolleto {

	@Override
	public boolean validarFolleto(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblFolleto> tblfolletoSistemaVenta = getHibernateTemplate()
				.iterate(
						"from TblFolleto obj where obj.idFolleto = ? ",
						new Object[] {idFolleto});
		List<TblFolleto> list = toInitializedList(tblfolletoSistemaVenta);	
		return (!list.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblFolleto> getFolletoLstByIdCampana(long idCampana) throws Exception {
		Iterator<TblFolleto> folletoLst = null ;
		folletoLst =	getHibernateTemplate().iterate(" FROM TblFolleto a WHERE a.tblCampana.idCampana = ?", new Object[] {idCampana});
		return toInitializedList(folletoLst);
	}
}
