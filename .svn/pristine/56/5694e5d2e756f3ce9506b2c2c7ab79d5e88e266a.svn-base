package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatListDet;
import com.adinfi.seven.business.domain.Componente;
import com.adinfi.seven.business.domain.RelItemLista;
import com.adinfi.seven.business.domain.TblComponente;

public class DAOTblComponenteImpl extends AbstractDaoImpl<TblComponente> implements DAOTblComponente {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public boolean checkPrecioExistsByMecanica(int idMecanica) {
        Session session = getSession();
        Transaction tx = null;
        String q = "SELECT COUNT(p.PRECIO) AS preciosCompo, COUNT(c.NUMERO_COMPONENTE) AS totalComponentes FROM SEV343DEV.TBL_COMPONENTE AS c " +
                "LEFT JOIN SEV343DEV.TBL_PRECIOS_PROMOCION AS p ON c.COMPONENTE_ID = p.COMPONENTE_ID " +
                "WHERE c.MECANICA_ID = " + idMecanica;
        try {
            System.out.println("Query: " + q);
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(q);
            Object[] result = (Object[]) query.uniqueResult();
            int cantidadPrecios = (Integer) result[0];
            int totalComponentes = (Integer) result[1];
            System.out.println("cantidad de precios: " + cantidadPrecios);
            System.out.println("cantidad de componentes: " + totalComponentes);
            
            return cantidadPrecios == totalComponentes;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            System.out.println(ex);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<TblComponente> getByMecanicaId(int mecanicaId)
			throws GeneralException {
		Iterator<TblComponente> itComponente = getHibernateTemplate()
				.iterate(" from TblComponente a where a.tblMecanica.mecanicaId= ?",
						new Object[]{Long.valueOf(mecanicaId)});
		return toInitializedList(itComponente);
	}

	@SuppressWarnings("unchecked")
	public List<TblComponente> getPreciosPromocionComponentes(int mecanicaId, Integer categoriaId, Integer subCategoriaId, Integer descripcionId, Integer componenteId, Integer listaId,List<String> skuList, List<String> upcList){
		List<TblComponente> returnList;
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(TblComponente.class, "a");
			cr.createAlias("a.tblMecanica", "mec", Criteria.LEFT_JOIN);
			cr.createAlias("a.relCompSkus", "skus", Criteria.LEFT_JOIN);
			cr.createAlias("a.relCompUpcs", "upcs",Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("mec.mecanicaId", mecanicaId));
/*			if (skuList !=null && !skuList.isEmpty()){
				cr.add(Restrictions.in("skus.skuId", Util.castListStringToInteger(skuList)));
			} */
			if (upcList != null && !upcList.isEmpty()){
				cr.add(Restrictions.in("upcs.upcId", upcList));
			} 
			if (categoriaId != 0){
				cr.add(Restrictions.eq("a.categoriaId", categoriaId));
			}
			if (subCategoriaId != 0){
				cr.add(Restrictions.eq("a.subCategoriaId", subCategoriaId));
			}
			if (componenteId != 0){
				cr.add(Restrictions.eq("a.numeroComponente", componenteId));
			}
			if (descripcionId != 0){
				cr.add(Restrictions.eq("a.descripcionId", descripcionId));
			}
			if (listaId != 0){
				cr.add(Restrictions.eq("a.listaId", listaId));
			}
			returnList = cr.list();
			tx.commit();
			return returnList;
		}catch(HibernateException ex){
			System.out.println(ex);
			if(tx != null){
				tx.rollback();
			}
			return new ArrayList<>();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TblComponente> getPreciosPromocionComponentes(Integer categoriaId,
			Integer programaId, Integer mecanicaId, List<Integer> grupoZona,
			List<Integer> zona) {
		StringBuilder from= new StringBuilder(" from TblComponente a ");
		StringBuilder where= new StringBuilder();
		armarQuery(" a.categoriaId= ", categoriaId, where);
		armarQuery(" a.tblMecanica.programaId=", programaId, where);
		armarQuery(" a.tblMecanica.mecanicaId=", mecanicaId, where);
		from.append(where.toString());
		Iterator<TblComponente> itComponente = getHibernateTemplate().iterate(from.toString());
		return toInitializedList(itComponente);
	}

	@Override
	public boolean updateHoja(int pkcomp, int hoja) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE TblComponente SET hoja = :hoja WHERE id = :pkcomp");
			query.setParameter("hoja", hoja);
			query.setParameter("pkcomp", pkcomp);
			int x = query.executeUpdate();
			tx.commit();
			return x == 1;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return false;
		}

	}
    
    
    @Override
	public boolean updateComponentNumber(int componentId, int numeroComponente) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("UPDATE TblComponente SET numeroComponente = :numeroComponente WHERE componenteId = :componenteId");
			query.setParameter("numeroComponente", numeroComponente);
			query.setParameter("componenteId", componentId);
			int x = query.executeUpdate();
			tx.commit();
			return x == 1;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return false;
		}

	}

	private void armarQuery(String condicion, Integer valor, StringBuilder query){
		if(valor != null && valor>0){
			if(query.indexOf("WHERE")>0){
				query.append(" AND ");
			}else{
				query.append(" WHERE ");
			}
			query.append(condicion);
			query.append(valor);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Componente> getComponentesFromLista(int idLista) {
		/*SQLQuery query = getSession().createSQLQuery("SELECT L.ID_LISTA, I.ID_ITEM, I.CODE, I.ID_CATEGORY, I.ID_SUBCATEGORY, LD.ID FROM SEV343DEV.CAT_LISTA AS L INNER JOIN SEV343DEV.REL_ITEM_LISTA AS IL ON L.ID_LISTA = IL.ID_LISTA INNER JOIN SEV343DEV.CAT_ITEM AS I ON IL.ID_ITEM = I.ID_ITEM INNER JOIN SEV343DEV.CAT_LIST_DET AS LD ON I.ID_ITEM = LD.ID_ITEM WHERE L.ID_LISTA = :idLista");
		query.addScalar("ID_LISTA", Hibernate.INTEGER);
		query.addScalar("ID_ITEM", Hibernate.STRING);
		query.addScalar("CODE", Hibernate.STRING);
		query.addScalar("ID_CATEGORY", Hibernate.INTEGER);
		query.addScalar("ID_SUBCATEGORY", Hibernate.INTEGER);
		query.addScalar("ID", Hibernate.STRING);
		query.setParameter("idLista", idLista);
		List<Object[]> rows = query.list();
		List<Componente> componentes = new ArrayList();
		for(Object[] element : rows){
		    Componente comp = new Componente();
		    comp.setIdLista((Integer)element[0]);
		    comp.setSKU((String)element[1]);
		    comp.setCode((String)element[2]);
		    comp.setIdCategory((Integer)element[3]);
		    comp.setIdSubCategory((Integer)element[4]);
		    comp.setUPC((String)element[5]);
		    componentes.add(comp);
		} */

		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(RelItemLista.class, "rel");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.createAlias("rel.catItem", "catITem", Criteria.LEFT_JOIN);
			criteria.createAlias("rel.catLista", "lista", Criteria.LEFT_JOIN);
			criteria.createAlias("catItem.catListDets", "upc", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("lista.idLista", idLista));
			List<RelItemLista> listaList = criteria.list();
			List<Componente> componentes = new ArrayList();
			for (RelItemLista r : listaList){
				List<CatListDet> upcs = new ArrayList<>(r.getCatItem().getCatListDets());
				Componente comp = new Componente();
				comp.setIdLista(r.getCatLista().getIdLista());
				comp.setSKU(r.getCatItem().getIdItem());
				comp.setCode(r.getCatItem().getCode());
				comp.setIdCategory(r.getCatItem().getCatCategory().getIdCategory());
				comp.setIdSubCategory(r.getCatItem().getIdSubcategory());
				comp.setUPC( upcs.isEmpty() ? "" : upcs.get(0).getId());
				componentes.add(comp);
			}
			tx.commit();
			return componentes;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return new ArrayList<>();
		}
	}
	
	@Override
	public void save(List<TblComponente> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (TblComponente o : list) {
			session.save(o);
			i++;
		    if ( i % 50 == 0 ) {
		    	this.getSession().flush();
		    	this.getSession().clear();
		    }
		}
		tx.commit();
		session.close();
	}
}