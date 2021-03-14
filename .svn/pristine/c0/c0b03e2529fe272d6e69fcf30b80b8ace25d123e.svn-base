package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatEstatus;
import com.adinfi.seven.persistence.daos.DAOCatEstatus;

import java.util.List;

/**
 * Created by jdominguez on 2/24/16.
 */
public class ServiceCatEstatusImpl implements ServiceCatEstatus {

    private DAOCatEstatus daoCatEstatus;

    @Override
    public List<CatEstatus> getEstatusAll() {
        return daoCatEstatus.getEstatusAll();
    }

    @Override
    public CatEstatus getEstatusById(int id) {
        return daoCatEstatus.getEstatusById(id);
    }

    @Override
    public CatEstatus getEstatusByNameContains(String nombre) {
        return daoCatEstatus.getEstatusByNameContains(nombre);
    }

    public DAOCatEstatus getDaoCatEstatus() {
        return daoCatEstatus;
    }

    public void setDaoCatEstatus(DAOCatEstatus daoCatEstatus) {
        this.daoCatEstatus = daoCatEstatus;
    }
}
