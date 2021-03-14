package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import com.adinfi.seven.business.domain.TblFolletoZona;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOFolletoZonaImpl extends AbstractDaoImpl<TblFolletoZona>
		implements DAOFolletoZona {

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblFolletoZona> getZonasById(int idFolleto) throws Exception {
		Iterator<TblFolletoZona> itTiendasId = null ;
		itTiendasId =	getHibernateTemplate().iterate(" FROM TblFolletoZona tft WHERE tft.id.idFolleto = ?", new Object[] {idFolleto});
		
		return toInitializedList(itTiendasId);
	}
	
	@Override
	public void delete(int idFolleto) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblFolletoZona a where a.id.idFolleto = "
								+ idFolleto);
		query.executeUpdate();
	}

}
