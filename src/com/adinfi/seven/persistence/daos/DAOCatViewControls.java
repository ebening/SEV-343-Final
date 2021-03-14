package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.CatViewControls;

public interface DAOCatViewControls extends AbstractDao<CatViewControls> {
	List<CatViewControls> getCatalogViewControl(Integer id) throws Exception;
}
