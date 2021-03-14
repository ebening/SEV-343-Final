package com.adinfi.seven.persistence.daos;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.presentation.views.util.Constants;

import java.util.Iterator;
import java.util.List;

public class DAOCatProveedorImpl extends AbstractDaoImpl<CatProveedor> implements
		DAOCatProveedor {
    
    
    @SuppressWarnings("unchecked")
    @Override
    public CatProveedor getCatProveedor(Integer proveedorId) {
        Iterator<CatProveedor> itActivity = getHibernateTemplate()
                .iterate(
                        "from CatProveedor catProveedor where catProveedor.proveedorId = ? ",
                        new Object[] { proveedorId });
        return toInitializedInstance(itActivity);
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public List<CatProveedor> getCatProveedorList(Integer idCategoria) throws GeneralException {
		return toInitializedList(getHibernateTemplate()
				.iterate("from CatProveedor catProveedor where catProveedor.catCategory.categoryId = ?",
						new Object[] { idCategoria }));
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
    
}
