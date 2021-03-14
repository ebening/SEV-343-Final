package com.adinfi.seven.persistence.daos;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.presentation.views.util.Constants;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;

public class DAOCatSubCategoryImpl extends AbstractDaoImpl<CatSubCategory> implements
		DAOCatSubCategory {
    
    
//    @SuppressWarnings("unchecked")
//    @Override
//    public CatSubCategory getCatSubCategory(Integer subCategoryId){
//        Iterator<CatSubCategory> itActivity = getHibernateTemplate()
//                .iterate(
//                        "from CatSubCategory catSubCategory where catSubCategory.idSubcategory = ? ",
//                        new Object[] { subCategoryId });
//        return toInitializedInstance(itActivity);
//    }
    
    @SuppressWarnings("unchecked")
    @Override
    public CatSubCategory getCatSubCategory(Integer subCategoryId, Integer categoryID){
    	CatSubCategory cat = new CatSubCategory();
    	 
        System.out.println("buscando: subCat: " + subCategoryId + ", category: " + categoryID );
        
        
    	//List<CatSubCategory> subs = getHibernateTemplate().find("FROM CatSubCategory subcat WHERE subcat.idSubcategory = ? and subcat.catCategory.idCategory = ?", new Object[] { subCategoryId,categoryID });
        List<CatSubCategory> subs = getHibernateTemplate()
                .find("FROM CatSubCategory subcat WHERE subcat.idSubcategory = ?", new Object[] { subCategoryId });
    	
        System.out.println("RESULTADO LISTA: " + subs.size());
        
        if(subs != null){
    		for(CatSubCategory c : subs){
                
                System.out.println("subCategoria en iteracion: " + c.getCode() + 
                        " subCatId: " + c.getIdSubcategory() + 
                        ", catId: " + c.getCatCategory().getIdCategory());
                
                if(c.getCatCategory().getIdCategory() == categoryID){
                    System.out.println("encontrado para categoria");
                    cat = c;
                    break;
                }else{
                    System.out.println("no pertenece a categoria");
                }
    		}
    	}
    	
    	return cat;
    }
    
    @SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
    @Override
    public List<CatSubCategory> getCatSubCategoryList()
            throws GeneralException {
        Iterator<CatSubCategory> itActivity = getHibernateTemplate()
                .iterate(
                        " from CatSubCategory catSubCategory order by idSubcategory asc ");
        return toInitializedList(itActivity);
    }
    
    @SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
    @Override
    public List<CatProveedor> getCatProveedorList()
            throws GeneralException {
        Iterator<CatProveedor> itActivity = getHibernateTemplate()
                .iterate(
                        " from CatProveedor catProveedor order by proveedorId asc ");
        return toInitializedList(itActivity);
    }
    
    @SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
    @Override
    public List<CatCategory> getCatCategoryList()
            throws GeneralException {
        Iterator<CatCategory> itActivity = getHibernateTemplate()
                .iterate(
                        " from CatCategory catCategory order by idCategory asc ");
        return toInitializedList(itActivity);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<CatSubCategory> getCatSubCategoryByProveedor(Integer id) {
		// FIXME: cambiar la liga de proveedor a subcategoria pasando por el item
		return toInitializedList(getHibernateTemplate()
				.iterate(" from CatSubCategory subcat where catProveedor.proveedorId = ? ",
						new Object[] { id }));
	}

    @Override
    public List<CatSubCategory> getCatSubCategoryByCategoryID(int id) {
        List subs = getHibernateTemplate().find("FROM CatSubCategory subcat WHERE subcat.catCategory.idCategory = ?", id);
        return subs;
    }

    @Override
    public CatSubCategory getCatSubCategoryByCatId(int subCatId, CatCategory catId){ 
        
        this.getSession().clear();
        
        List<CatSubCategory> list = getHibernateTemplate().find("FROM CatSubCategory subcat WHERE subcat.catCategory = ? and subcat.idSubcategory = ?", new Object[] { catId, subCatId });
        if(list != null && list.size() >0 ){
            return list.get(0);
        }else{
            return null;
        }
        
    }
}
