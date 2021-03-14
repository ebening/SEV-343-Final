package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatLista;

public interface DAOCatLista extends AbstractDao<CatLista> {

	CatLista getCatLista(Integer idCatLista);

	List<CatLista> getCatListaList(CatLista catLista) throws GeneralException;
}
