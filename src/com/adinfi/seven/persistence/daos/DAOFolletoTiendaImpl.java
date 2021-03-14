package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblFolletoTienda;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOFolletoTiendaImpl extends AbstractDaoImpl<TblFolletoTienda>
		implements DAOFolletoTienda {

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblFolletoTienda> getTiendasIdByFolletoId(int idFolleto)
			throws Exception {
		Iterator<TblFolletoTienda> itTiendasId = null ;
		itTiendasId =	getHibernateTemplate().iterate(" FROM  TblFolletoTienda tft WHERE tft.id.idFolleto = ?", new Object[] {idFolleto});
		
		return toInitializedList(itTiendasId);
	}
	
	@Override
	public void delete(int idFolleto) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblFolletoTienda a where a.id.idFolleto = "
								+ idFolleto);
		query.executeUpdate();
	}

}
