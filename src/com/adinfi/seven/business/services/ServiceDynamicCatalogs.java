package com.adinfi.seven.business.services;

import java.util.List;
import java.util.Map;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.*;

public interface ServiceDynamicCatalogs {
	Catalogs getCatalog(Integer id) throws Exception;

	Catalogs getCatalogByName(String catName) throws Exception;
	Catalogs getCatalogByNameAndDescription(String catName,String description) throws Exception;
	List<CatViewControls> getCatalogViewControl(Integer id) throws Exception;

	Attribs getAttribId(Integer id) throws Exception;

	List<CatAttrs> getCatalogAttributes(Integer id) throws Exception;

	void insertReg(String catName, Map<String, Object> mapAttrValues)
			throws Exception;

	List<CatRegs> getRegs(String catName, List<AttrSearch> lstSearchAttrs)
			throws Exception;

	List<CatRegs> getRegsJobs(String catName, List<AttrSearch> lstSearchAttrs)
			throws Exception;

	int updateRegs(String catalogName, Map<String, Object> updateVals,
			List<AttrSearch> lstSearchAttrs) throws Exception;

	int deleteRegs(String catName, List<AttrSearch> lstSearchAttrs)
			throws Exception;

	List<CatRegs> getRegsNotification(String catName,
			List<AttrSearch> lstSearchAttrs) throws GeneralException;

	List<CatalogosOpc> getMenuElements() throws Exception;
	
	public List<CatRegs> getRegsJobsByList(String catName, List<List<AttrSearch>> lstSearchAttrs) throws Exception;

}