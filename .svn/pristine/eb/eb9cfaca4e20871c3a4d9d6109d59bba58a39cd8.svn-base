package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatLista;
import com.adinfi.seven.persistence.dto.CabeceraReporteDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DAOCatListaImpl extends AbstractDaoImpl<CatLista> implements DAOCatLista {

	@SuppressWarnings("unchecked")
	@Override
	public CatLista getCatLista(Integer idCatLista) {
	 	Iterator<CatLista> tblCatListaIterator = getHibernateTemplate()
				.iterate(
						"from CatLista catLista where catLista.idLista = ? ",
						new Object[] { idCatLista });
		return toInitializedInstance(tblCatListaIterator);
	/*	Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CatLista.class, "lista");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//	criteria.createAlias("lista.catCategory", "categ", Criteria.LEFT_JOIN);
		//	criteria.createAlias("lista.catSubCategory", "subcateg", Criteria.LEFT_JOIN);
			criteria.createAlias("lista.catItems", "items", Criteria.LEFT_JOIN);
			criteria.createAlias("lista.catListDets", "listdet", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("lista.idLista", idCatLista));
			CatLista catLista = (CatLista) criteria.uniqueResult();
			return  catLista;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		} */
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatLista> getCatListaList(
			CatLista catLista)
			throws GeneralException {
		Iterator<CatLista> itActivity = getHibernateTemplate()
				.iterate(
						" from CatLista catLista where catLista.idLista= ?",
								catLista.getIdLista());
		return toInitializedList(itActivity);
	}

}
