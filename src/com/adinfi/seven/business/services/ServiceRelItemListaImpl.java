package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.RelItemLista;
import com.adinfi.seven.persistence.daos.DAORelItemLista;

import java.util.List;

/**
 * Created by jdominguez on 5/12/16.
 */
public class ServiceRelItemListaImpl implements ServiceRelItemLista {

    private DAORelItemLista daoRelItemLista;

    @Override
    public List<RelItemLista> getRelItemListaByListaID(int idlista) {
        return daoRelItemLista.getRelItemListaByListaID(idlista);
    }

    public DAORelItemLista getDaoRelItemLista() {
        return daoRelItemLista;
    }

    public void setDaoRelItemLista(DAORelItemLista daoRelItemLista) {
        this.daoRelItemLista = daoRelItemLista;
    }
}
