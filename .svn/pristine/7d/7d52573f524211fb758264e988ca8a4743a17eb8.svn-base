package com.adinfi.seven.persistence.daos;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblPreciosPrensaDet;

public class DAOPrecioPrensaDetImpl extends
		AbstractDaoImpl<TblPreciosPrensaDet> implements DAOPrecioPrensaDet {

	@Override
	public void delete(int idPrecPrensa) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblPreciosPrensaDet a where a.tblPreciosPrensa.idPrecPrensa = "
								+ idPrecPrensa);
		query.executeUpdate();
	}
}
