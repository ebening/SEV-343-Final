package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatalogosOpc;
import java.util.List;

/**
 * Created by christian on 26/01/2015.
 */
public class DAOCatalogosImpl extends AbstractDaoImpl<CatalogosOpc> implements DAOCatalogos {

    @Override
    public List<CatalogosOpc> getMenuElemets()throws Exception{
        return getAll();
    }
}
