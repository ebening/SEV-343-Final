package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatEstatus;

import java.util.List;

/**
 * Created by jdominguez on 2/24/16.
 */
public interface ServiceCatEstatus {

    public List<CatEstatus> getEstatusAll();
    public CatEstatus getEstatusById(int id);
    public CatEstatus getEstatusByNameContains(String nombre);
}
