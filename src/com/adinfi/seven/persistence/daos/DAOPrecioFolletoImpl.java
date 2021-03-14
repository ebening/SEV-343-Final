package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblPreciosFolleto;

public class DAOPrecioFolletoImpl extends AbstractDaoImpl<TblPreciosFolleto>
		implements DAOPrecioFolleto {

	@Override
	public List<TblPreciosFolleto> getPreciosFolletoByFolleto(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblPreciosFolleto> folletoHojas = getHibernateTemplate()
				.iterate(
						"from TblPreciosFolleto obj where obj.tblFolleto.idFolleto = ? ",
						new Object[] { idFolleto });
		return toInitializedList(folletoHojas);
	}
}
