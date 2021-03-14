package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.RelItemLista;

import java.util.List;

/**
 * Created by jdominguez on 5/12/16.
 */
public interface DAORelItemLista extends AbstractDao<RelItemLista> {
    public List<RelItemLista> getRelItemListaByListaID(int idlista);
}
