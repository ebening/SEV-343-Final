package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatEtiquetas;
import com.adinfi.seven.persistence.daos.DAOCatEtiquetas;

import java.util.List;

/**
 * Created by jdominguez on 2/24/16.
 */
public class ServiceCatEtiquetasImpl implements ServiceCatEtiquetas {

    private DAOCatEtiquetas daoCatEtiquetas;

    @Override
    public List<CatEtiquetas> getEtiquetasAll() {
        return daoCatEtiquetas.getEtiquetasAll();
    }

    @Override
    public CatEtiquetas getEtiquetaById(int id) {
        return daoCatEtiquetas.getEtiquetaById(id);
    }

    public DAOCatEtiquetas getDaoCatEtiquetas() {
        return daoCatEtiquetas;
    }

    public void setDaoCatEtiquetas(DAOCatEtiquetas daoCatEtiquetas) {
        this.daoCatEtiquetas = daoCatEtiquetas;
    }
}
