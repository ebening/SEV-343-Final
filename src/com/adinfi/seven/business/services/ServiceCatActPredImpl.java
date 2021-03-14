package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatActPred;
import com.adinfi.seven.persistence.daos.DAOCatActPred;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jdominguez on 2/16/16.
 */
public class ServiceCatActPredImpl implements ServiceCatActPred {

    private DAOCatActPred daoCatActPred;

    @Override
    public List<CatActPred> getCatologoActividades() {
        List<CatActPred> catalogo = daoCatActPred.getCatalogoActividades();
        Collections.sort(catalogo, new Comparator<CatActPred>() {
            @Override
            public int compare(CatActPred o1, CatActPred o2) {
                return o1.getOrden() - o2.getOrden();
            }
        });
        return catalogo;
    }

    public DAOCatActPred getDaoCatActPred() {
        return daoCatActPred;
    }

    public void setDaoCatActPred(DAOCatActPred daoCatActPred) {
        this.daoCatActPred = daoCatActPred;
    }
}
