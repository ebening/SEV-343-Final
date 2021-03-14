package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;






import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.RelItemStoreProveedor;
import com.adinfi.seven.presentation.views.util.Constants;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DAOCatItemImpl extends AbstractDaoImpl<CatItem> implements DAOCatItem {

    @Override
    public List<CatItem> getCatItemByProveedor(int idProveedor, List<Integer> stores) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(RelItemStoreProveedor.class, "rel");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("rel.catItem", "item", Criteria.LEFT_JOIN);
            criteria.createAlias("rel.catProveedor", "prov", Criteria.LEFT_JOIN);
            criteria.createAlias("rel.catStore", "store", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("prov.idProveedor", idProveedor));
            criteria.add(Restrictions.in("store.idStore", stores));
            List<RelItemStoreProveedor> listrel = criteria.list();
            tx.commit();
            List<CatItem> catItemList = new ArrayList<>();
            for (RelItemStoreProveedor r : listrel){
                if (catItemList.contains(r.getCatItem())){
                    continue;
                }
                catItemList.add(r.getCatItem());
            }
            return catItemList;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
	@Override
	public CatItem getCatItem(String idCatItem) {
		/*
		Query query = getSession().createQuery("from CatItem catItem where catItem.idItem = :ID");
		query.setString("ID", idCatItem);
		
		Iterator<CatItem> tblCatItemIterator = getHibernateTemplate()
				.iterate("from CatItem catItem where catItem.idItem = ? ",
						new String[] { idCatItem });
		return toInitializedInstance(tblCatItemIterator); */
            
            List<CatItem> result = getHibernateTemplate().find("FROM CatItem item WHERE item.idItem = ?", idCatItem);
            return result.get(0);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatItem> getCatItemList(
			CatItem catItem)
			throws GeneralException {
		Iterator<CatItem> itActivity = getHibernateTemplate()
				.iterate(
						" from CatItem catItem where catItem.idItem= ?",
								catItem.getIdItem());
		return toInitializedList(itActivity);
	}

    @SuppressWarnings("unchecked")
	@Override
    public List<CatItem> getCatItemListBySubCatID(int idSubCat, int idCat) {
    	
        List<CatItem> lstItems = getHibernateTemplate().find("FROM CatItem c WHERE c.idSubcategory = ? and c.catCategory.idCategory = ?", new Object[]{idSubCat,idCat});
        if(lstItems != null && lstItems.isEmpty() == false){
        	return lstItems;
        }else{
        	return Collections.EMPTY_LIST;
        }
        
    }
    
    @Override
    public List<CatItem> getCatItemByCategory(int idCat) {
        String s = "FROM CatItem c WHERE c.catCategory.idCategory = ?";
        List<CatItem> items = getHibernateTemplate().find(s, idCat);
        if (items.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return items;
    }

}
