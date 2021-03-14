package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatActPred;

import java.util.List;

/**
 * Created by jdominguez on 2/15/16.
 */
public interface DAOCatActPred extends AbstractDao<CatActPred> {

    public List<CatActPred> getCatalogoActividades();
    public CatActPred getActById(int id);
}
