package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.seven.business.domain.CatRegs;

public interface DAOCatRegs extends AbstractDao<CatRegs> {
	public List<CatRegs> getRegs(String catName, List<List<AttrSearch>> lstSearchAttrs) throws Exception;
}
