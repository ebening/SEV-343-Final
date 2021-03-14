package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatalogosOpc;
import com.adinfi.seven.business.domain.Opcion;
import com.adinfi.seven.persistence.dto.MenuElement;

import java.util.List;

/**
 * Created by christian on 26/01/2015.
 */
public interface DAOCatalogos extends AbstractDao<CatalogosOpc>{

    public List<CatalogosOpc> getMenuElemets() throws  Exception;
}
