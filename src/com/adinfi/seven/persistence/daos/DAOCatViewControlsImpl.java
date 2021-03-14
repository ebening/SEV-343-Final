package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.CatViewControls;

public class DAOCatViewControlsImpl extends AbstractDaoImpl<CatViewControls>
		implements DAOCatViewControls {

	@SuppressWarnings("unchecked")
	@Override
	public List<CatViewControls> getCatalogViewControl(Integer id)
			throws Exception {
		Iterator<CatViewControls> CatViewControlsI = getHibernateTemplate()
				.iterate(
						"from CatViewControls c where c.catalogsByCatId.catId= ? Order by c.orderId ASC",
						id);
		return toInitializedList(CatViewControlsI);
	}

}
