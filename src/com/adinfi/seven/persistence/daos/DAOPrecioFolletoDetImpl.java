package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblPreciosFolletoDet;

public class DAOPrecioFolletoDetImpl extends
		AbstractDaoImpl<TblPreciosFolletoDet> implements DAOPreciosFolletoDet {
	
	@Override
	public List<TblPreciosFolletoDet> getPreciosArticuloHoja(int idSistemaVenta, int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblPreciosFolletoDet> preciosDet = getHibernateTemplate()
				.iterate(
						"from TblPreciosFolletoDet obj where obj.idSistemaVenta = ? and obj.tblPreciosFolleto.idPrecFolleto in "
					    + " ( select pf.idPrecFolleto from TblPreciosFolleto pf where pf.tblFolleto.idFolleto = ? ) ",
						new Object[] { idSistemaVenta, idFolleto });
		return toInitializedList(preciosDet);
	}
	
	@Override
	public void delete(int idPrecFolleto) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblPreciosFolletoDet a where a.tblPreciosFolleto.idPrecFolleto = "
								+ idPrecFolleto);
		query.executeUpdate();
	}
}
