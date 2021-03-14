package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblPrensaTienda;

public class DAOPrensaTiendaImpl extends AbstractDaoImpl<TblPrensaTienda>
		implements DAOPrensaTienda {

	@SuppressWarnings("unchecked")
	@Override
	public List<TblPrensaTienda> getTiendasIdByPrensaId(int idPrensa) throws Exception {
		Iterator<TblPrensaTienda> itTiendasId = null ;
		itTiendasId =	getHibernateTemplate().iterate(" FROM  TblPrensaTienda tpt WHERE tpt.id.idPrensa = ?", new Object[] {idPrensa});
		
		return toInitializedList(itTiendasId);
	}
	
	@Override
	public void delete(int idPrensa) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblPrensaTienda a where a.id.idPrensa = "
								+ idPrensa);
		query.executeUpdate();
	}

}
