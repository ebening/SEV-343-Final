package com.adinfi.seven.persistence.daos;

import java.util.Iterator;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblPrensaSistemaVenta;

public class DAOPrensaSistemaVentaImpl extends
		AbstractDaoImpl<TblPrensaSistemaVenta> implements DAOPrensaSistemaVenta {
	@Override
	public TblPrensaSistemaVenta getPrensaSistemaVentaByFolio(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblPrensaSistemaVenta> tblPrensa = getHibernateTemplate()
				.iterate(
						"from TblPrensaSistemaVenta obj where obj.id.idPrensa = ?",
						new Object[] {idFolleto});
		return toInitializedInstance(tblPrensa);
	}
	@Override
	public void delete(int idPrensa) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblPrensaSistemaVenta a where a.id.idPrensa = "
								+ idPrensa);
		query.executeUpdate();
	}
}
