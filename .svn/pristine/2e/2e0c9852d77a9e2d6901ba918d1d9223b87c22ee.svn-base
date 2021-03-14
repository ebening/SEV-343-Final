package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;


import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOCatDeptoImpl extends AbstractDaoImpl<CatDepto> implements
		DAOCatDepto {
    
    
    @SuppressWarnings("unchecked")
    @Override
    public CatDepto getCatDepto(Integer deptoId) {
            Iterator<CatDepto> tblCatDeptoIterator = getHibernateTemplate()
                            .iterate(
                                            "from CatDepto catDepto where catDepto.deptoId = ? ",
                                            new Object[] { deptoId });
            return toInitializedInstance(tblCatDeptoIterator);
    }

    @SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
    @Override
    public List<CatDepto> getCatDeptoList(CatDepto catDepto)
            throws GeneralException {
        Iterator<CatDepto> itActivity = getHibernateTemplate()
                .iterate(
                        " from CatDepto catDepto order by deptoId asc ");
        return toInitializedList(itActivity);
    }
    
}
