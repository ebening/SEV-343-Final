package com.adinfi.seven.persistence.daos;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatDepto;
import java.util.List;

public interface DAOCatDepto extends AbstractDao<CatDepto> {
    
    CatDepto getCatDepto(Integer idDepto);

    List<CatDepto> getCatDeptoList(CatDepto catDepto) throws GeneralException;

}
