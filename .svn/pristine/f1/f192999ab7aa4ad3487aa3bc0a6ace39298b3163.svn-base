package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblCampanaMedio;

public class DAOCampanaMedioImpl extends AbstractDaoImpl<TblCampanaMedio>
		implements DAOCampanaMedio {

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaMedio> getCatByCampanaId(long idCampana) {
		Iterator<TblCampanaMedio> tblCampanaIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaMedio tblCampanaMedio  where tblCampanaMedio.tblCampana.idCampana = ? ",
						new Object[] { idCampana });
		return toInitializedList(tblCampanaIterator);
	}

}
