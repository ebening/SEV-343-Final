package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.RelItemLista;

import java.util.List;

/**
 * Created by jdominguez on 5/12/16.
 */
public interface ServiceRelItemLista {

    public List<RelItemLista> getRelItemListaByListaID(int idlista);
}
