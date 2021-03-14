package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;

public class DAOFolletoSistemaVentaImpl extends
		AbstractDaoImpl<TblFolletoSistemaVenta> implements
		DAOFolletoSistemaVenta {

	@Override
	public TblFolletoSistemaVenta getFolletoSistemaVentaByFolio(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblFolletoSistemaVenta> tblfolletoSistemaVenta = getHibernateTemplate()
				.iterate(
						"from TblFolletoSistemaVenta obj where obj.id.idFolleto = ? ",
						new Object[] {idFolleto});
		return toInitializedInstance(tblfolletoSistemaVenta);
	}
	
	@Override
	public List<TblFolletoSistemaVenta> getSistemaVentaByFolleto(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblFolletoSistemaVenta> tblfolletoSistemaVenta = getHibernateTemplate()
				.iterate(
						"from TblFolletoSistemaVenta obj where obj.id.idFolleto = ? ",
						new Object[] {idFolleto});
		return toInitializedList(tblfolletoSistemaVenta);
	}
	
	@Override
	public void delete(int idFolleto) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblFolletoSistemaVenta a where a.id.idFolleto = "
								+ idFolleto);
		query.executeUpdate();
	}
}
