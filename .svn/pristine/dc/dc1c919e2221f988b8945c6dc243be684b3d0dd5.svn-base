package com.adinfi.seven.persistence.daos;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblPrensaEspacios;

public class DAOPrensaEspaciosImpl extends AbstractDaoImpl<TblPrensaEspacios>
		implements DAOPrensaEspacios {
	@Override
	public void delete(int idPrensa) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblPrensaEspacios a where a.tblPrensa.idPrensa = "
								+ idPrensa);
		query.executeUpdate();
	}
}
