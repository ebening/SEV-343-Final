package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatFlujoAct;
import com.adinfi.seven.business.domain.RelFlujoAct;

import java.util.List;

/**
 * Created by jdominguez on 8/19/16.
 */
public interface DAOFlujoAct extends AbstractDao<CatFlujoAct> {

    public List<CatFlujoAct> getAllFull();
    public List<RelFlujoAct> getRelFlujoByFlujo(CatFlujoAct flujoAct);
    public boolean updateRelFlujoAct(RelFlujoAct relFlujoAct);
    public CatFlujoAct getFlujoById(int id);
    public int getDiasTotalesFlujo(CatFlujoAct catFlujoAct);
}
