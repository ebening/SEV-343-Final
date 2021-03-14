package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblArticulosEspacios;

public class DAOArticulosEspacioImpl extends
		AbstractDaoImpl<TblArticulosEspacios> implements DAOArticulosEspacio {

	@Override
	public List<TblArticulosEspacios> getArticulosByIdPrensa(int idPrensa)
			throws Exception {
		Iterator<TblArticulosEspacios> itListaArticulos = null ;
		itListaArticulos =	getHibernateTemplate().iterate(" FROM  TblArticulosEspacios tae WHERE tae.id.idPrensa = ?", new Object[] {idPrensa});
		return toInitializedList(itListaArticulos);
	}

}
