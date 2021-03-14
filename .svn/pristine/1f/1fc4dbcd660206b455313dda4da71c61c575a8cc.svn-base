package com.adinfi.seven.business.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.CatalogException;
import com.adinfi.catalogs.bos.exception.catalogs.CatalogExceptionInfo;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.Attribs;
import com.adinfi.seven.business.domain.CatAttrs;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.CatViewControls;
import com.adinfi.seven.business.domain.CatalogosOpc;
import com.adinfi.seven.business.domain.Catalogs;
import com.adinfi.seven.persistence.daos.DAOAttribs;
import com.adinfi.seven.persistence.daos.DAOCatAttrs;
import com.adinfi.seven.persistence.daos.DAOCatRegValues;
import com.adinfi.seven.persistence.daos.DAOCatRegs;
import com.adinfi.seven.persistence.daos.DAOCatViewControls;
import com.adinfi.seven.persistence.daos.DAOCatalogos;
import com.adinfi.seven.persistence.daos.DAOCatalogs;

public class ServiceDynamicCatalogsImpl implements ServiceDynamicCatalogs, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Logger LOG = Logger.getLogger(ServiceDynamicCatalogsImpl.class);
	public static int OP_TYPE_INS = 1;
	public static int OP_TYPE_UPDATE = 2;
	public static int OP_TYPE_DELETE = 3;

	private DAOCatalogos daoCatalogos;
	private DAOCatalogs daoCatalogs;
	private DAOCatViewControls daoCatViewControls;
	private DAOAttribs daoAttribs;
	private DAOCatAttrs daoCatAttrs;
	private DAOCatRegs daoCatRegs;

	public DAOCatRegValues getDaoCatRegValues() {
		return daoCatRegValues;
	}

	public void setDaoCatRegValues(DAOCatRegValues daoCatRegValues) {
		this.daoCatRegValues = daoCatRegValues;
	}

	DAOCatRegValues daoCatRegValues;

	public DAOCatRegs getDaoCatRegs() {
		return daoCatRegs;
	}

	public void setDaoCatRegs(DAOCatRegs daoCatRegs) {
		this.daoCatRegs = daoCatRegs;
	}

	@Override
	public List<CatAttrs> getCatalogAttributes(Integer id) throws Exception {
		return daoCatAttrs.getCatalogAttributes(id);
	}

	@Override
	public Catalogs getCatalog(Integer id) throws Exception {
		return daoCatalogs.getById(id);
	}

	@Override
	public List<CatViewControls> getCatalogViewControl(Integer id)
			throws Exception {
		return daoCatViewControls.getCatalogViewControl(id);
	}

	@Override
	public Attribs getAttribId(Integer id) throws Exception {
		return daoAttribs.getById(id);
	}

	public DAOCatalogs getDaoCatalogs() {
		return daoCatalogs;
	}

	public void setDaoCatalogs(DAOCatalogs daoCatalogs) {
		this.daoCatalogs = daoCatalogs;
	}

	public DAOCatViewControls getDaoCatViewControls() {
		return daoCatViewControls;
	}

	public void setDaoCatViewControls(DAOCatViewControls daoCatViewControls) {
		this.daoCatViewControls = daoCatViewControls;
	}

	public DAOAttribs getDaoAttribs() {
		return daoAttribs;
	}

	public void setDaoAttribs(DAOAttribs daoAttribs) {
		this.daoAttribs = daoAttribs;
	}

	public DAOCatAttrs getDaoCatAttrs() {
		return daoCatAttrs;
	}

	public void setDaoCatAttrs(DAOCatAttrs daoCatAttrs) {
		this.daoCatAttrs = daoCatAttrs;
	}

	public Catalogs getCatalogByName(String catName) throws Exception {
		return daoCatalogs.getByCatName(catName);
	}

	public DAOCatalogos getDaoCatalogos() {
		return daoCatalogos;
	}

	public void setDaoCatalogos(DAOCatalogos daoCatalogos) {
		this.daoCatalogos = daoCatalogos;
	}

    @Override
	public List<CatRegs> getRegs(String catName, List<AttrSearch> lstSearchAttrs) throws Exception {
		return getRegsJobs(catName, lstSearchAttrs);
	}

    @Override
	public List<CatRegs> getRegsJobs(String catName, List<AttrSearch> lstSearchAttrs) throws Exception {
    	List<List<AttrSearch>> list = new ArrayList<List<AttrSearch>>();
    	list.add(lstSearchAttrs);
		return getRegsJobsByList(catName, list);
	}
    
    public List<CatRegs> getRegsJobsByList(String catName, List<List<AttrSearch>> lstSearchAttrs) throws Exception {
    	List<CatRegs> lstRegs = this.daoCatRegs.getRegs(catName, lstSearchAttrs);
		if (lstRegs != null) {
			List<Integer> ids = new ArrayList<Integer>();
			for (CatRegs reg : lstRegs) {
				ids.add(reg.getCatRegId());
			}
			Map<Integer, Set<CatRegValues>> map = new HashMap<>();
			List<CatRegValues> lstValues = daoCatRegValues.getByCatRegId(ids);
			for(CatRegValues o: lstValues){
				Integer id = o.getCatRegId().getCatRegId();
				Set<CatRegValues> set = map.get(id);
				if(set==null){
					set = new HashSet<>();
					map.put(id, set);
				}
				set.add(o);
			}
			for (CatRegs reg : lstRegs) {
				Set<CatRegValues> set = map.get(reg.getCatRegId());
				if(set==null) reg.setRegValues(new HashSet<CatRegValues>());
				else reg.setRegValues(set);
			}
			
		}
		return lstRegs;
    }

        @Override
	public List<CatRegs> getRegsNotification(String catName, List<AttrSearch> lstSearchAttrs) throws GeneralException {
    	try {
			return getRegsJobs(catName, lstSearchAttrs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeneralException(e);
		}
	}

	@SuppressWarnings("unchecked")
        @Override
	public int updateRegs(String catalogName, Map<String, Object> updateVals,
			List<AttrSearch> lstSearchAttrs) throws Exception {
		int n = 0;
		Set<CatRegValues> setReg;
		List<CatRegs> lstRegs = getRegs(catalogName, lstSearchAttrs);
		/* Object objNewVal=null; */
		String attrName;
		// List<CatRegValues> lstDel=null;
		// Map<String ,CatAttrs> mapAttrs=null;
		// List<String> lstAttrs=null;
		// CatRegValues catRegValueNvo=null;
		Map<String, CatRegValues> mapCurrValues = new TreeMap<>();
		List<CatAttrs> lstCatAttrs;
		if (lstRegs != null && lstRegs.size() > 0) {

			for (CatRegs reg : lstRegs) {
				// ****inici
				setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						mapCurrValues.put(regVals.getCatAttrs().getAttribs()
								.getAttrName(), regVals);
					}
				}

				lstCatAttrs = this.daoCatAttrs.getCatalogAttributes(reg
						.getCatalogs().getCatId());
				CatRegValues catRegValue;

				Object objValue;
				List<String> lstValues;
				if (lstCatAttrs != null) {
					for (CatAttrs catAttrs : lstCatAttrs) {

						LOG.info(catAttrs.getAttribs().getAttrName());
						attrName = catAttrs.getAttribs().getAttrName();
						objValue = updateVals.get(attrName);
						if (objValue == null)
							continue;

						catRegValue = mapCurrValues.get(attrName);
						if (catRegValue != null) {
							if (objValue instanceof String) {
								catRegValue.setValue((String) objValue);
								this.daoCatRegValues.saveOrUpdate(catRegValue);
							} else if (objValue instanceof List) {
								this.daoCatRegValues
										.deleteByCatAttrId(catRegValue
												.getCatAttrs().getCatAttribId());
								lstValues = (List<String>) objValue;
								for (String val : lstValues) {
									catRegValue = new CatRegValues();
									// catRegValue.setCatRegId(reg.getCatRegId());
									catRegValue.setCatRegId(reg);
									catRegValue.setValue(val);
									catRegValue.setCatAttrs(catAttrs);
									this.daoCatRegValues.save(catRegValue);
								}

							}

						} else {
							if (objValue instanceof String) {
								catRegValue = new CatRegValues();
								// catRegValue.setCatRegId(reg.getCatRegId());
								catRegValue.setCatRegId(reg);
								catRegValue.setValue((String) objValue);
								catRegValue.setCatAttrs(catAttrs);
								this.daoCatRegValues.save(catRegValue);
							} else if (objValue instanceof List) {
								lstValues = (List<String>) objValue;
								for (String val : lstValues) {
									catRegValue = new CatRegValues();
									// catRegValue.setCatRegId(reg.getCatRegId());
									catRegValue.setCatRegId(reg);
									catRegValue.setValue(val);
									catRegValue.setCatAttrs(catAttrs);
									this.daoCatRegValues.save(catRegValue);
								}

							}

						}

					}

				}

				// ****fin

			}

		}
		return n;
	}

	@SuppressWarnings("unchecked")
        @Override
	public void insertReg(String catName, Map<String, Object> mapAttrValues)
			throws Exception {

		Catalogs catalogs = daoCatalogs.getByCatName(catName);
		if (catalogs == null) {
			return;
		}
		// validateIntegrity( catName , mapAttrValues ,0 , OP_TYPE_INS );
		CatRegs catRegs = new CatRegs();
		catRegs.setCatalogs(catalogs);
		catRegs.setInsertDate(new java.util.Date());
		daoCatRegs.save(catRegs);
		LOG.info("Id :" + catRegs.getCatRegId());

		List<CatAttrs> lstCatAttrs = this.daoCatAttrs
				.getCatalogAttributes(catalogs.getCatId());
		CatRegValues catRegValue = null;
		Set<CatRegValues> catRegVals = new HashSet<CatRegValues>();

		Object objValue = null;
		List<String> lstValues;
		String attrName = null;
		if (lstCatAttrs != null) {
			for (CatAttrs catAttrs : lstCatAttrs) {

				LOG.info(catAttrs.getAttribs().getAttrName());
				attrName = catAttrs.getAttribs().getAttrName();
				objValue = mapAttrValues.get(attrName);
				if (objValue == null)
					continue;

				if (objValue instanceof String) {
					catRegValue = new CatRegValues();
					// catRegValue.setCatRegId(catRegs.getCatRegId());
					catRegValue.setCatRegId(catRegs);
					catRegValue.setValue((String) objValue);
					catRegValue.setCatAttrs(catAttrs);
					catRegVals.add(catRegValue);
					this.daoCatRegValues.save(catRegValue);
				} else if (objValue instanceof List) {
					lstValues = (List<String>) objValue;
					for (String val : lstValues) {
						catRegValue = new CatRegValues();
						// catRegValue.setCatRegId(catRegs.getCatRegId());
						catRegValue.setCatRegId(catRegs);
						catRegValue.setValue(val);
						catRegValue.setCatAttrs(catAttrs);
						catRegVals.add(catRegValue);
						this.daoCatRegValues.save(catRegValue);
					}

				}

			}

		}
		catRegs.setRegValues(catRegVals);
	}

        @Override
	public int deleteRegs(String catName, List<AttrSearch> lstSearchAttrs) throws  Exception {
		int n = 0;
		List<CatalogException> lstCatException = null;
		Set<CatRegValues> setReg = null;
		List<CatRegs> lstRegs = getRegs(catName, lstSearchAttrs);

		if (lstRegs != null && lstRegs.size() > 0) {
			for (CatRegs reg : lstRegs) {
				LOG.info("Clave:" + reg.getCatRegId());
				try {
					validateIntegrity(catName, null, reg.getCatRegId(),OP_TYPE_DELETE);
				} catch (CatalogException catEx) {
					if (lstCatException == null) {
						lstCatException = new ArrayList<>();
					}
					lstCatException.add(catEx);
					continue;
				}
				setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						LOG.info("atributo : "
								+ regVals.getCatAttrs().getAttribs()
										.getAttrName());
						LOG.info("Valor :" + regVals.getValue());
						daoCatRegValues.delete(regVals);
					}
				}

				daoCatRegs.delete(reg);
				n++;
			}

			if (lstCatException != null && lstCatException.size() > 0) {
				throw new CatalogException(CatalogException.CAT_ERR_BATCH,
						lstCatException);
			}

		}
		return n;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void validateIntegrity(String catName,
			Map<String, Object> mapAttrValues, int regId, int opType)
			throws CatalogException {
		CatRegs catReg = null;
		CatalogExceptionInfo catExInfo = null;
		Set<CatRegValues> setCatRegValues = null;
		try {

			Catalogs catalogs = daoCatalogs.getByCatName(catName);
			if (catalogs == null) {
				return;
			}
			List<CatRegValues> lstCurrValues = null;
			List lstExtValues = null;
			String extTableName = null;
			String extFldName = null;

			if (opType == OP_TYPE_DELETE) {
				catReg = this.daoCatRegs.getById(regId);
				setCatRegValues = catReg.getRegValues();
				if (setCatRegValues != null) {
					for (CatRegValues catRegVal : setCatRegValues) {
						extTableName = null;
						extFldName = null;

						// if( catRegVal.getCatAttrs().getCatAttribIdRef()>0 ){
						lstCurrValues = this.daoCatRegValues
								.getByCatAttrIdRefAndValue(catRegVal
										.getCatAttrs().getCatAttribId(),
										catRegVal.getValue());
						if (lstCurrValues != null && lstCurrValues.size() > 0) {
							catExInfo = new CatalogExceptionInfo();
							catExInfo.setAttrName(catRegVal.getCatAttrs()
									.getAttribs().getAttrName());
							catExInfo.setRegId(regId);
							catExInfo.setValue(catRegVal.getValue());
							throw new CatalogException(
									CatalogException.CAT_ERR_VAL_REF_FOUND,
									catExInfo, null);
						}

						extTableName = catRegVal.getCatAttrs().getExtfkTName();
						extFldName = catRegVal.getCatAttrs().getExtfkFName();

						if (extTableName != null
								&& extTableName.isEmpty() == false
								&& extFldName != null
								&& extFldName.isEmpty() == false) {

							lstExtValues = this.daoCatAttrs.getRegExternalFK(
									extTableName, extFldName,
									catRegVal.getValue());
							if (lstExtValues != null && lstExtValues.size() > 0) {
								catExInfo = new CatalogExceptionInfo();
								catExInfo.setAttrName(catRegVal.getCatAttrs()
										.getAttribs().getAttrName());
								catExInfo.setRegId(regId);
								catExInfo.setValue(catRegVal.getValue());
								catExInfo.setExtFkTable(extTableName);
								catExInfo.setExtFkField(extFldName);
								throw new CatalogException(
										CatalogException.CAT_ERR_VAL_EXT_REF_FOUND,
										catExInfo, null);
							}

						}

						// }
					}
				}

			}

			if (opType == OP_TYPE_DELETE)
				return;

			List<CatAttrs> lstCatAttrs = this.daoCatAttrs
					.getCatalogAttributes(catalogs.getCatId());
			Object objValue = null;
			boolean found = false;
			List<String> lstValues;
			String attrName = null;
			int attrIdRef = 0;
			if (opType <= 0)
				return;
			lstCurrValues = null;
			if (lstCatAttrs != null) {
				for (CatAttrs catAttrs : lstCatAttrs) {

					LOG.info(catAttrs.getAttribs().getAttrName());
					attrName = catAttrs.getAttribs().getAttrName();

					objValue = mapAttrValues.get(attrName);
					if (objValue == null) {

						if (opType == OP_TYPE_INS) {
							if ("Y".equals(String.valueOf(catAttrs
									.getRequired()))) {
								catExInfo = new CatalogExceptionInfo();
								catExInfo.setAttrName(attrName);
								throw new CatalogException(
										CatalogException.CAT_ERR_REQUIRED_VAL,
										catExInfo, null);
							}

							extTableName = catAttrs.getExtfkTName();
							extFldName = catAttrs.getExtfkFName();

							if (extTableName != null
									&& extTableName.isEmpty() == false
									&& extFldName != null
									&& extFldName.isEmpty() == false) {

								lstExtValues = this.daoCatAttrs
										.getRegExternalFK(extTableName,
												extFldName, null);
								if (lstExtValues == null
										|| lstExtValues.size() <= 0) {
									catExInfo = new CatalogExceptionInfo();
									catExInfo.setAttrName(catAttrs.getAttribs()
											.getAttrName());
									catExInfo.setRegId(regId);
									catExInfo.setValue(null);
									catExInfo.setExtFkTable(extTableName);
									catExInfo.setExtFkField(extFldName);
									throw new CatalogException(
											CatalogException.CAT_ERR_VAL_EXT_REF_FOUND,
											catExInfo, null);
								}

							}

						}
					} else {

						// ****iniico

						attrIdRef = catAttrs.getCatAttribIdRef();
						if (attrIdRef > 0) {

							if (objValue instanceof String) {
								lstCurrValues = this.daoCatRegValues
										.getByCatAttrIdAndValue(attrIdRef,
												(String) objValue);
								if (lstCurrValues == null
										|| lstCurrValues.size() <= 0) {
									catExInfo = new CatalogExceptionInfo();
									catExInfo.setAttrName(attrName);
									throw new CatalogException(
											CatalogException.CAT_ERR_VAL_REF_NOT_FOUND,
											catExInfo, null);
								}

							} else if (objValue instanceof List) {
								lstValues = (List<String>) objValue;

								found = false;
								for (String val : lstValues) {
									lstCurrValues = this.daoCatRegValues
											.getByCatAttrIdAndValue(attrIdRef,
													val);
									if (lstCurrValues != null
											&& lstCurrValues.size() > 0) {
										found = true;
										break;
									}
									if (found == false) {
										catExInfo = new CatalogExceptionInfo();
										catExInfo.setAttrName(attrName);
										throw new CatalogException(
												CatalogException.CAT_ERR_VAL_REF_NOT_FOUND,
												catExInfo, null);

									}
								}

							}

						}// hasta aqui

						// ***
					}

				}

			}

		} catch (CatalogException ex) {
			throw ex;
		} catch (Exception e) {
			LOG.info("Error al validar ");
			throw new CatalogException(CatalogException.CAT_ERR_UNKNOWN, null,
					e);
		}

	}

	
        @Override
	public Catalogs getCatalogByNameAndDescription(String catName,String description) throws Exception{
		return daoCatalogs.getByCatNameAndDescription(catName, description);
	}


	@Override
	public List<CatalogosOpc> getMenuElements() throws Exception {
		return daoCatalogos.getMenuElemets();
	}
}
