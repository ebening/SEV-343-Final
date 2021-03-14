package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblPreciosPrensa;

public class DAOPrecioPrensaImpl extends AbstractDaoImpl<TblPreciosPrensa>
		implements DAOPrecioPrensa {
	@SuppressWarnings("unchecked")
	@Override
	public List<TblPreciosPrensa> getByPrensaId(int idPrensa) throws Exception {
		Iterator<TblPreciosPrensa> precioPrensaLst = null ;
		precioPrensaLst =	getHibernateTemplate().iterate(" FROM TblPreciosPrensa a WHERE a.tblPrensa.idPrensa = ?", new Object[] {idPrensa});
		return toInitializedList(precioPrensaLst);
	}
}