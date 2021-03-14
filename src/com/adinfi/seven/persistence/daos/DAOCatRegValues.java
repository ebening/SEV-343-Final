package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatRegValues;

public interface DAOCatRegValues extends AbstractDao<CatRegValues> {
	void deleteByCatAttrId(int catAttrId) throws Exception;

	List<CatRegValues> getByCatAttrId(int catAttrId) throws Exception;

	List<CatRegValues> getByCatAttrIdRefAndValue(int catAttrId, String value)
			throws Exception;

	List<CatRegValues> getByCatAttrIdAndValue(int catAttrId, String value)
			throws Exception;

	public List<CatRegValues> getByCatRegId(List<Integer> catRegIds) throws GeneralException;
}