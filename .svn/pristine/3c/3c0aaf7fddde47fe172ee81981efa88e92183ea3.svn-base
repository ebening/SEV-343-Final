package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.catalogs.bos.exception.catalogs.LoginException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatListDet;
import com.adinfi.seven.business.domain.CatLista;
import com.adinfi.seven.business.domain.CatPromo;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.business.domain.CatTipoPromo;
import com.adinfi.seven.business.domain.CatViewControls;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.Catalogs;
import com.adinfi.seven.business.domain.ProgramaDTO;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatGZone;
import com.adinfi.seven.business.services.ServiceCatListDet;
import com.adinfi.seven.business.services.ServiceCatLista;
import com.adinfi.seven.business.services.ServiceCatPromo;
import com.adinfi.seven.business.services.ServiceCatProveedor;
import com.adinfi.seven.business.services.ServiceCatSenal;
import com.adinfi.seven.business.services.ServiceCatStore;
import com.adinfi.seven.business.services.ServiceCatSubCategory;
import com.adinfi.seven.business.services.ServiceCatTipoPromo;
import com.adinfi.seven.business.services.ServiceCatZone;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CatItemDTO;
import com.adinfi.seven.persistence.dto.CatMedioDTO;
import com.adinfi.seven.persistence.dto.CatTipoMedioDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.DepartamentoDTO;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.NegocioDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.SistemaVentaDTO;
import com.adinfi.seven.persistence.dto.SubCategoriasDTO;
import com.adinfi.seven.persistence.dto.SubcategoriaDTO;
import com.adinfi.seven.persistence.dto.SucursalDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.persistence.dto.ZonaDTO;
import com.adinfi.seven.presentation.views.ArticuloDTO;
import com.adinfi.seven.presentation.views.MBArquitectura.ArqComponente;

/**
 * Utilerias para el manejo de Manage Bean
 * 
 * @author Julio Pérez
 * @date 13/Sep/2013
 * 
 */
public final class MBUtil {

	private static Logger LOG = Logger.getLogger(MBUtil.class);

	private MBUtil() {
	}
	
	/**
	 * Regresa un Objeto CatItem para la pantalla Precio Promocion Combo Descripcion
	 * 
	 * @param dto
	 * @return
	 */
	public static CatItem parseItemArqComp(ArqComponente dto){
		CatItem ci = new CatItem();
		ci.setCode(dto.getDescripcion());
		ci.setIdItem(dto.getSku());
		return ci;
	}
	
	/**
	 * Regresa un objeto CatListDet para la pantalla de Precio Promocion Combo Sku
	 * 
	 * @param dto
	 * @return
	 */
	public static CatListDet parseArqComp(ArqComponente dto){
		CatListDet cld = new CatListDet();
		cld.setId(dto.getSku());
		cld.setCode(dto.getSku());
		return cld;
	}

	/**
	 * Regresa una lista de mapas en la que cada mapa es un registro del
	 * catalogo especificado.
	 * 
	 * @param catName la constante con el nombre de catalogo dinamico
	 * @param serviceDynamicCatalogs servicio de DB para los catalogos
	 * @return la lista de registros
	 * @throws GeneralException
	 */
	public static List<Map<String, String>> getCatalogValues(String catName,
			ServiceDynamicCatalogs serviceDynamicCatalogs)
			throws GeneralException {
		List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
		List<Map<String, String>> responseArray = new ArrayList<Map<String, String>>();
		List<CatRegs> regs = serviceDynamicCatalogs.getRegsNotification(
				catName, lstSearchAttrs);
		if (regs != null) {
			int i = 0;
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				Map<String, String> row = new HashMap<String, String>();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (row.containsKey(attName)) {
							String value = row.get(attName);
							row.remove(attName);
							row.put(attName, value + "," + regVals.getValue());
						} else {
							row.put(attName, regVals.getValue());
						}
					}
					responseArray.add(i, sortByValue(row));
					i++;
				}
			}
		}
		return responseArray;
	}
	
	/**
	 * Obtiene el valor del attributo {@code CODE} del registro del catálogo
	 * especificado identificado por la búsqueda dada.
	 * 
	 * @param serviceDynamicCatalogs servicio de DB para los catálogos
	 * @param catalogo constante con el nombre del catálogo
	 * @param insertSearch parámetros de búsqueda
	 * @return el valor como cadena del campo CODE
	 * @throws Exception
	 */
	public static String getCatalogCodeByKey(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String catalogo, List<AttrSearch> insertSearch)
			throws Exception {

		String sCode = "";
		Catalogs catalogUser = serviceDynamicCatalogs.getCatalogByName(catalogo);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(catalogo, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_CODE)) {
							sCode = regVals.getValue();
						}
						
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + catalogo);
		}
		return sCode;
	}
	
	/**
	 * Regresa el valor raw de un campo de un registro de catálogo
	 * dinámico de acuerdo a una búsqueda.
	 * 
	 * @param serviceDynamicCatalogs
	 * @param catalogo
	 * @param insertSearch
	 * @param attribute
	 * @return
	 * @throws Exception
	 */
	public static String getCatalogAttribByKey(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String catalogo, 
			List<AttrSearch> insertSearch, String attribute)
			throws Exception {

		String sCode = "";
		Catalogs catalogUser = serviceDynamicCatalogs.getCatalogByName(catalogo);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(catalogo, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(attribute)) {
							sCode = regVals.getValue();
						}
						
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + catalogo);
		}
		return sCode;
	}
	
	public static List<PeriodoDTO> getPeriodos(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		return getElementosDeCatalogo(serviceDynamicCatalogs, Constants.CAT_PERIODO, PeriodoDTO.class);
	}
	
	public static List<PeriodoDTO> getPeriodos(ServiceDynamicCatalogs serviceDynamicCatalogs, List<AttrSearch> parametrosBusqueda) throws Exception {
		return getElementosDeCatalogo(serviceDynamicCatalogs, Constants.CAT_PERIODO, PeriodoDTO.class, parametrosBusqueda);
	}
	
	/**
	 * Metodo para obtener los valores de un Combo.
	 * 
	 * @param catName
	 * @param serviceDynamicCatalogs
	 * @return
	 */
	public static Map<String, String> cargarcombos(String catName,
			ServiceDynamicCatalogs serviceDynamicCatalogs) {

		Map<String, String> responseArray = new HashMap<String, String>();
		List<CatViewControls> viewList;
		boolean bandera;
		try {
			Catalogs catalog = serviceDynamicCatalogs.getCatalogByName(catName);
			if (catalog != null) {
				viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
				Collections.sort(viewList);
				List<AttrSearch> insertSearch 	= new ArrayList<AttrSearch>();
				List<CatRegs> regs 				= serviceDynamicCatalogs.getRegsJobs(catName,insertSearch);
				if (regs != null) {
					for (CatRegs reg : regs) {
						Set<CatRegValues> setReg = reg.getRegValues();
						if (setReg != null) {
							for (CatRegValues regVals : setReg) {
								String attName = regVals.getCatAttrs().getAttribs().getAttrName();
								if (attName.equals(Constants.ATT_ID) || attName.equals(Constants.ATT_CODE)) {
									String value = (String) regVals.getValue();
									if (responseArray.containsKey(String.valueOf(reg.getCatRegId()))) {
										String aux = (String) responseArray.get(String.valueOf(reg.getCatRegId()));
										responseArray.remove(String.valueOf(reg.getCatRegId()));
										bandera = false;
										try {
											Integer.parseInt(aux);
										} catch (Exception e) {
											bandera = true;
										}
										if (bandera) {
											responseArray.put(aux, value);
										} else {
											responseArray.put(value, aux);
										}
									} else {
										responseArray.put(String.valueOf(reg.getCatRegId()), String.valueOf(regVals.getValue()));
									}

								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return sortByValue(responseArray);
	}
	
	public static List<CatCategory> cargarcomboCategorias(ServiceCatCategory serviceCatCategory) {
		try {
			return serviceCatCategory.getCatCategoryList();
		} catch (Exception e) {
                    System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
        
        public static List<SelectItem> getSelectItemsCategory(ServiceCatCategory service){
            try{
                List<CatCategory> categs = service.getCatCategoryList();
                List<SelectItem> items = new ArrayList<>();
                for (CatCategory c : categs){
                    items.add(new SelectItem(c.getIdCategory(), c.getCode()));
                }
                return items;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            return new ArrayList<>();
        }
	
	public static List<CatLista> cargarcomboCatListas(ServiceCatLista serviceCatLista) {
		try {
			return serviceCatLista.getCatListaList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
        
        public static List<CatListDet> cargarComboUPCByItemID(ServiceCatListDet service, String itemID){
            return service.getCatListDetByItemID(itemID);
        }
	
	public static List<CatListDet> cargarcomboCatListDets(ServiceCatListDet serviceCatListDet) {
		try {
			return serviceCatListDet.getCatListDetList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public static List<CatSubCategory> cargarcomboSubCategorias(ServiceCatSubCategory serviceCatSubCategory) {
		try {
			return serviceCatSubCategory.getCatSubCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public static List<CatSenal> cargarcomboSenal(ServiceCatSenal serviceCatSenal) {
		try {
			return serviceCatSenal.getCatSenalesForCombo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public static List<CatPromo> cargarComboCatPromo(ServiceCatPromo serviceCatPromo) {
		try {
			return serviceCatPromo.getCatPromos();
		} catch (Exception e) {
			LOG.error(e);
		}
		return new ArrayList<>();
	}
	
	
	public static List<CatTipoPromo> cargarComboCatTipoPromo(ServiceCatTipoPromo serviceCatTipoPromo) {
		try {
			return serviceCatTipoPromo.getCatTipoPromos();
		} catch (Exception e) {
			LOG.error(e);
		}
		return new ArrayList<>();
	}

	
	public static List<CatGZone> cargarcomboGrupoZonas(ServiceCatGZone serviceCatGZone) {
		try {
			return serviceCatGZone.getCatGZoneList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	public static List<CatProveedor> cargarcomboCatProveedor(ServiceCatProveedor serviceCatProveedor, Integer idCategoria){
		if (idCategoria == null) {
			return cargarcomboCatProveedor(serviceCatProveedor);
		}
		try {
			return serviceCatProveedor.getCatProveedorList(idCategoria);
		} catch (Exception e) {
			LOG.error(e);
		}
		return new ArrayList<>();
	}
	
    public static List<CatProveedor> cargarcomboCatProveedor(ServiceCatProveedor serviceCatProveedor){
    	try {
            return serviceCatProveedor.getCatProveedorList();
        } catch (Exception e) {
            LOG.error(e);
        }
    	return new ArrayList<>();
    }
    
	public static List<CatSubCategory> cargarcomboSubCategoriasByIdCategory(ServiceCatSubCategory serviceCatSubCategory, Integer id) {
        /*    if (id == null) {
                    return Collections.emptyList();
            }
            CatCategory catCategory = new CatCategory();
            catCategory.setCategoryId(id);
            List<CatSubCategory> catSubCategoryList = new ArrayList<>();
            try {
                     catCategory = serviceCatCategory.getCatCategoryById(catCategory);
                     //TODO ADINFI
//			 Set<CatSubCategory> catSubCategorySet = catCategory.getCatSubcategories();
                     Set<CatSubCategory> catSubCategorySet = new HashSet<>();
                     catSubCategoryList = new ArrayList<>(catSubCategorySet);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return catSubCategoryList;
        */
            return serviceCatSubCategory.getCatSubCategoryByCategoryID(id);
 
	}
	
	public static List<CatSubCategory> cargarComboSubCategoriasByIdProveedor(ServiceCatSubCategory service, Integer id) {
		if (id == null) {
			return Collections.emptyList();
		}
		else {
			try {
				return service.getCatSubCategoryByIdProveedor(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Collections.emptyList();
	}
	
	public static List<CatZone> cargarcomboZona(ServiceCatZone serviceCatZone) {
		try {
			return serviceCatZone.getCatZoneList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	/**
	 * Regresa una lista de tiendas para un id de zona.
	 * 
         * Remplazada en restructuracion Agosto 2015 - @jorged
         * 
	 * @param serviceCatZone
	 * @param id
	 * @return
	 */
        @Deprecated
	public static List<CatStore> cargarcomboStores(ServiceCatZone serviceCatZone, String id) {
		List<CatStore> catStoreList = null;
		try {
			CatZone catZone = new CatZone();
			catZone.setIdZone(Integer.valueOf(id));
			catStoreList = new ArrayList<>();
			catZone = serviceCatZone.getCatZoneById(catZone);
//			Set<CatStore> catStoreSet = catZone.getCatStores();

//			catStoreList = new ArrayList<CatStore>(catStoreSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catStoreList;
	}
        
    public static List<CatStore> cargacomboStores(ServiceCatStore serviceStores, String id) {
        return serviceStores.getCatStoreListByZone(Integer.valueOf(id));
    }
    
    public static List<CatStore> cargacomboStores(ServiceCatStore serviceStores, List<Integer> ids) {
        return serviceStores.getCatStoreListByZone(ids);
    }
	public static Map<String, String> cargarcombos(String catName, List<AttrSearch> insertSearch,
			ServiceDynamicCatalogs serviceDynamicCatalogs, String id, String value) {
		Map<String, String> responseArray = new HashMap<String, String>();
		List<CatViewControls> viewList;
		try {
			Catalogs catalog = serviceDynamicCatalogs.getCatalogByName(catName);
			if (catalog != null) {
				viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
				Collections.sort(viewList);
				List<CatRegs> regs = serviceDynamicCatalogs.getRegs(catName, insertSearch);
				if (regs != null) {
					for (CatRegs reg : regs) {
						Set<CatRegValues> setReg = reg.getRegValues();
						if (setReg != null) {
							String valueStr="", idStr="";
							for (CatRegValues regVals : setReg) {
								String attName = regVals.getCatAttrs().getAttribs().getAttrName();
								if (attName.equals(id)){
									idStr= (String) regVals.getValue();
								}
								if (attName.equals(value)){
									valueStr= (String) regVals.getValue();
								}
							}
							if (!responseArray.containsKey(idStr)) {
								responseArray.put(idStr, valueStr);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}

		return sortByValue(responseArray);
	}

	public static Map<String, String> sortByValue(Map<String, String> map) {
		List<Map.Entry<String, String>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return o1.getKey().trim().compareToIgnoreCase(o2.getKey().trim());
			}
		});

		Map<String, String> result = new LinkedHashMap<>();
		for (Entry<String, String> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static Map<String, String> cargarcombos(String catName, List<AttrSearch> insertSearch,
			ServiceDynamicCatalogs serviceDynamicCatalogs) {

		Map<String, String> responseArray = new HashMap<String, String>();
		List<CatViewControls> viewList;
		boolean bandera;
		try {
			Catalogs catalog = serviceDynamicCatalogs.getCatalogByName(catName);
			if (catalog != null) {
				viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
				Collections.sort(viewList);
				List<CatRegs> regs 				= serviceDynamicCatalogs.getRegs(catName,insertSearch);
				if (regs != null) {
					for (CatRegs reg : regs) {
						Set<CatRegValues> setReg = reg.getRegValues();
						if (setReg != null) {
							for (CatRegValues regVals : setReg) {
								String attName = regVals.getCatAttrs().getAttribs().getAttrName();
								if (attName.equals(Constants.ATT_ID) || attName.equals(Constants.ATT_CODE)) {
									String value = (String) regVals.getValue();
									if (responseArray.containsKey(String.valueOf(reg.getCatRegId()))) {
										String aux = (String) responseArray.get(String.valueOf(reg.getCatRegId()));
										responseArray.remove(String.valueOf(reg.getCatRegId()));
										bandera = false;
										try {
											Integer.parseInt(aux);
										} catch (Exception e) {
											bandera = true;
										}
										if (bandera) {
											responseArray.put(aux, value);
										} else {
											responseArray.put(value, aux);
										}
									} else {
										responseArray.put(String.valueOf(reg.getCatRegId()), String.valueOf(regVals.getValue()));
									}

								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return sortByValue(responseArray);
	}
	
	/**
	 * Metodo para obtener los valores y llenas un Combo , donde valor y textValue tengan la Descripcion
	 * 
	 * @param catName
	 * @param serviceDynamicCatalogs
	 * @return
	 */
	public static Map<String, String> cargarcombosDescripcion(String catName,
			ServiceDynamicCatalogs serviceDynamicCatalogs) {

		Map<String, String> responseArray = new HashMap<String, String>();
		List<CatViewControls> viewList;
		try {
			Catalogs catalog = serviceDynamicCatalogs.getCatalogByName(catName);
			if (catalog != null) {
				viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
				Collections.sort(viewList);
				List<AttrSearch> insertSearch 	= new ArrayList<AttrSearch>();
				List<CatRegs> regs 				= serviceDynamicCatalogs.getRegs(catName,insertSearch);
				if (regs != null) {
					for (CatRegs reg : regs) {
						Set<CatRegValues> setReg = reg.getRegValues();
						if (setReg != null) {
							for (CatRegValues regVals : setReg) {
								String attName = regVals.getCatAttrs().getAttribs().getAttrName();
								if (attName.equals(Constants.ATT_CODE)) {
									String value = (String) regVals.getValue();
									
									if (!responseArray.containsKey(value)) {
										responseArray.put(value, value);
									} 

								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return sortByValue(responseArray);
	}

	public static Map<String, String> cargarComboUsuario(String catName,
			ServiceDynamicCatalogs serviceDynamicCatalogs) {

		Map<String, String> responseArray = new HashMap<String, String>();
		List<CatViewControls> viewList;
		boolean bandera;
		try {
			Catalogs catalog = serviceDynamicCatalogs.getCatalogByName(catName);
			if (catalog != null) {
				viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog
						.getCatId());
				Collections.sort(viewList);
				List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
				List<CatRegs> regs = serviceDynamicCatalogs.getRegs(catName,
						insertSearch);
				if (regs != null) {
					for (CatRegs reg : regs) {
						Set<CatRegValues> setReg = reg.getRegValues();
						if (setReg != null) {
							for (CatRegValues regVals : setReg) {
								String attName = regVals.getCatAttrs()
										.getAttribs().getAttrName();
								if (attName.equals(Constants.ATT_ID)
										|| attName.equals(Constants.ATT_NAME)) {
									String value = (String) regVals.getValue();
									if (responseArray.containsKey(String
											.valueOf(reg.getCatRegId()))) {
										String aux = (String) responseArray
												.get(String.valueOf(reg
														.getCatRegId()));
										responseArray.remove(String.valueOf(reg
												.getCatRegId()));
										bandera = false;
										try {
											Integer.parseInt(aux);
										} catch (Exception e) {
											bandera = true;
										}
										if (bandera) {
											responseArray.put(aux, value);
										} else {
											responseArray.put(value, aux);
										}
									} else {
										responseArray.put(String.valueOf(reg
												.getCatRegId()), String
												.valueOf(regVals.getValue()));
									}

								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return sortByValue(responseArray);
	}

	public static String genericSearchJob(String attNameSearch, String id,
			String attNameValue, String catalog,
			ServiceDynamicCatalogs serviceDynamicCatalogs)
			throws GeneralException {
		String code = null;
		List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
		try {
			AttrSearch att = new AttrSearch();
			att.setAttrName(attNameSearch);
			att.setExact(true);
			att.setValue(id);
			lstSearchAttrs.add(att);
			List<CatRegs> lstResp = serviceDynamicCatalogs.getRegsJobs(catalog,
					lstSearchAttrs);
			if (lstResp != null) {
				CatRegs reg = lstResp.get(0);
				Set<CatRegValues> setValues = reg.getRegValues();
				if (setValues != null) {
					Iterator<CatRegValues> it = setValues.iterator();
					while (it.hasNext()) {
						CatRegValues valueReg = it.next();
						if (valueReg.getCatAttrs().getAttribs().getAttrName()
								.compareTo(attNameValue) == 0) {
							return valueReg.getValue();
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return code;
	}

	public static String genericSearch(String attNameSearch, String id, String attNameValue, String catalog, ServiceDynamicCatalogs serviceDynamicCatalogs) throws GeneralException {
		List<String> ids = new ArrayList<String>();
		ids.add(id);
		Map<String, String> map = genericSearch(attNameSearch, ids, attNameValue, catalog, serviceDynamicCatalogs);
		if(map!=null && !map.isEmpty())
			return map.values().iterator().next();
		return null;
	}

	public static Map<String, String> genericSearch(String attNameSearch, List<String> ids, String attNameValue, String catalog, ServiceDynamicCatalogs serviceDynamicCatalogs) throws GeneralException {
		Map<String, String> map = new HashMap<>();
		List<List<AttrSearch>> list = new ArrayList<List<AttrSearch>>();
		try {
			for(String id: ids){
				List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
				AttrSearch att = new AttrSearch();
				att.setAttrName(attNameSearch);
				att.setExact(true);
				att.setValue(id);
				lstSearchAttrs.add(att);
				list.add(lstSearchAttrs);
			}
			List<CatRegs> lstResp = serviceDynamicCatalogs.getRegsJobsByList(catalog, list);
			if (lstResp != null) {
				for(CatRegs reg: lstResp){
					Set<CatRegValues> setValues = reg.getRegValues();
					if (setValues != null) {
						Iterator<CatRegValues> it = setValues.iterator();
						while (it.hasNext()) {
							CatRegValues valueReg = it.next();
							if (valueReg.getCatAttrs().getAttribs().getAttrName().compareTo(attNameValue) == 0) {
								map.put(valueReg.getCatRegValId()+"", valueReg.getValue());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return map;
	}

	public static List<GenericItem> genericSearch(String attNameId, String attNameValue, String catalog, ServiceDynamicCatalogs serviceDynamicCatalogs) throws GeneralException {
		return genericSearch(attNameId, attNameValue, catalog, serviceDynamicCatalogs, new ArrayList<AttrSearch>());
	}
		
	public static List<GenericItem> genericSearch(String attNameId,
			String attNameValue, String catalog,
			ServiceDynamicCatalogs serviceDynamicCatalogs, List<AttrSearch> lstSearchAttrs)
			throws GeneralException {
		List<GenericItem> listGenericItem = new ArrayList<GenericItem>();
		try {
			List<CatRegs> lstResp = serviceDynamicCatalogs.getRegs(catalog, lstSearchAttrs);
			if (lstResp != null) {
				for (CatRegs reg : lstResp) {
					Set<CatRegValues> setValues = reg.getRegValues();
					if (setValues != null) {
						GenericItem item = new GenericItem();
						Iterator<CatRegValues> it = setValues.iterator();
						while (it.hasNext()) {
							CatRegValues valueReg = it.next();
							if (valueReg.getCatAttrs().getAttribs().getAttrName().compareTo(attNameId) == 0) {
								item.setId(Integer.valueOf(valueReg.getValue()));
							} else if (valueReg.getCatAttrs().getAttribs().getAttrName().compareTo(attNameValue) == 0) {
								item.setValue(valueReg.getValue());
							}
						}
						listGenericItem.add(item);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return listGenericItem;
	}

	public static UsuarioDTO makeUser(String login, String password,
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		UsuarioDTO responseDTO = null;
		List<AttrSearch> lstSearchAttrs = null;
		AttrSearch attSearch = null;
		Set<CatRegValues> setReg = null;
		CatRegs reg = null;
		String attrName = null;
		lstSearchAttrs = new ArrayList<AttrSearch>();
		if (login != null && login.trim().length() > 0) {
			attSearch = new AttrSearch();
			attSearch.setAttrName(Constants.ATT_LOGIN);
			attSearch.setValue(login);
			lstSearchAttrs.add(attSearch);
		}
		if (password != null && password.trim().length() > 0) {
			attSearch = new AttrSearch();
			attSearch.setAttrName(Constants.ATT_PASSWORD);
			attSearch.setValue(password);
			lstSearchAttrs.add(attSearch);
		}

		List<CatRegs> regs = serviceDynamicCatalogs.getRegs(Constants.CAT_USER,
				lstSearchAttrs);

		if (regs != null && regs.size() > 0) {
			reg = regs.get(0);
			setReg = reg.getRegValues();
			if (setReg != null) {
				responseDTO = new UsuarioDTO();
				responseDTO.setLogin(login);
				for (CatRegValues regVals : setReg) {
					attrName = regVals.getCatAttrs().getAttribs().getAttrName();
					if (attrName == null || attrName.isEmpty())
						continue;
					if (Constants.ATT_NAME.equals(attrName)) {
						responseDTO.setName(regVals.getValue());
					} else if (Constants.ATT_EMAIL.equals(attrName)) {
						responseDTO.setEmail(regVals.getValue());
					} else if (Constants.ATT_ID.equals(attrName)) {
						responseDTO.setUserId(Integer.valueOf(regVals
								.getValue()));
					} else if (Constants.ATT_PLAST_NAME.equals(attrName)) {
						responseDTO.setPlastName(regVals.getValue());
					} else if (Constants.ATT_MLAST_NAME.equals(attrName)) {
						responseDTO.setMlastName(regVals.getValue());
					} else if (Constants.ATT_ROLE.equals(attrName)) {
						responseDTO.setRole(regVals.getValue());
					} else if (Constants.ATT_PASSWORD.equals(attrName)) {
						responseDTO.setPassword(regVals.getValue());
					} else if (Constants.ATT_CATEGORY.equals(attrName)) {
						if(responseDTO.getCategorias()==null){
							responseDTO.setCategorias(new ArrayList<CategoriaDTO>());
						}
						CategoriaDTO categ= new CategoriaDTO();
						categ.setId(new Integer(regVals.getValue()));
						responseDTO.getCategorias().add(categ);
					} else if (Constants.ATT_N_CHANGE.equals(attrName)) {
						responseDTO.setChangePassword(regVals.getValue()
								.charAt(0));
					}
				}
			} else {
				throw new LoginException(
						"El usuario no fue encontrado en la base de datos");
			}
		} else {
			throw new LoginException(
					"El usuario no fue encontrado en la base de datos");
		}
		return responseDTO;
	}

	public static UsuarioDTO makeUser(String id,
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		UsuarioDTO responseDTO = null;
		List<AttrSearch> lstSearchAttrs = null;
		AttrSearch attSearch = null;
		Set<CatRegValues> setReg = null;
		CatRegs reg = null;
		String attrName = null;
		lstSearchAttrs = new ArrayList<AttrSearch>();
		attSearch = new AttrSearch();
		attSearch.setAttrName(Constants.ATT_ID);
		attSearch.setValue(id);
		lstSearchAttrs.add(attSearch);

		List<CatRegs> regs = null;
		try {
			regs = serviceDynamicCatalogs.getRegs(Constants.CAT_USER,
					lstSearchAttrs);
		} catch (Exception e) {
			LOG.error(e);
			return responseDTO;
		}

		if (regs != null && regs.size() > 0) {
			reg = regs.get(0);
			setReg = reg.getRegValues();
			if (setReg != null) {
				responseDTO = new UsuarioDTO();
				for (CatRegValues regVals : setReg) {
					attrName = regVals.getCatAttrs().getAttribs().getAttrName();
					if (attrName == null || attrName.isEmpty())
						continue;
					if (Constants.ATT_NAME.equals(attrName)) {
						responseDTO.setName(regVals.getValue());
					} else if (Constants.ATT_EMAIL.equals(attrName)) {
						responseDTO.setEmail(regVals.getValue());
					} else if (Constants.ATT_PLAST_NAME.equals(attrName)) {
						responseDTO.setPlastName(regVals.getValue());
					} else if (Constants.ATT_MLAST_NAME.equals(attrName)) {
						responseDTO.setMlastName(regVals.getValue());
					} else if (Constants.ATT_ROLE.equals(attrName)) {
						responseDTO.setRole(regVals.getValue());
					} else if (Constants.ATT_PASSWORD.equals(attrName)) {
						responseDTO.setPassword(regVals.getValue());
					} else if (Constants.ATT_N_CHANGE.equals(attrName)) {
						responseDTO.setChangePassword(regVals.getValue()
								.charAt(0));
					} else if (Constants.ATT_LOGIN.equals(attrName)) {
						responseDTO.setEmail(regVals.getValue());
					} else if (Constants.ATT_ID.equals(attrName)) {
						responseDTO.setUserId(Integer.valueOf(regVals
								.getValue()));
					}
				}
			}
		}
		return responseDTO;
	}

	public static Long dateDiff(Date dateIni, Date dateFin, Long divisor) {
		if (dateIni == null || dateFin == null || divisor == null) {
			return null;
		}
		Long hrs = Long.valueOf(0);
		try {
			hrs = (dateFin.getTime() - dateIni.getTime()) / divisor;
			if (hrs < 0) {
				hrs = Long.valueOf(0);
			}
		} catch (Exception e) {
			LOG.error(e);
		}

		return hrs;
	}

	public static List<UsuarioDTO> getUSers(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		List<UsuarioDTO> listUsuariosDTO = new ArrayList<UsuarioDTO>();
		AttrSearch attr = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attr);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_USER);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_USER, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					UsuarioDTO usuario = new UsuarioDTO();
					for (CatRegValues regVals : setReg) {

						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_NAME)) {
							usuario.setName(regVals.getValue());
						} else if (attName.equals(Constants.ATT_PLAST_NAME)) {
							usuario.setPlastName(regVals.getValue());
						} else if (attName.equals(Constants.ATT_MLAST_NAME)) {
							usuario.setMlastName(regVals.getValue());
						} else if (attName.equals(Constants.ATT_EMAIL)) {
							usuario.setEmail(regVals.getValue());
						} else if (attName.equals(Constants.ATT_ROLE)) {
							usuario.setRole(regVals.getValue());
						} else if (attName.equals(Constants.ATT_ID)) {
							usuario.setUserId(Integer.valueOf(regVals
									.getValue()));
						}
					}
					listUsuariosDTO.add(usuario);
				}
			}
		} else {
			LOG.info("El catalogo CAT_USER está vacio");
		}
		return listUsuariosDTO;
	}

	public static List<CategoriaDTO> getCategorias(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {

		List<CategoriaDTO> listCategoriaDTO = new ArrayList<CategoriaDTO>();
		AttrSearch attr = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();

		insertSearch.add(attr);

		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_CATEGORY);
		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_CATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {

					listCategoriaDTO.add(DTOUtil.crearCategoriaDTO(setReg));

				} else {
					LOG.info("El catalogo CAT_CATEGORY está vacio");
				}
			}
		}

		return listCategoriaDTO;
	}
	
	public static List<SistemaVentaDTO> getSistemaVenta(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {

		List<SistemaVentaDTO> listSistVenDTO = new ArrayList<SistemaVentaDTO>();
		AttrSearch attr = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();

		insertSearch.add(attr);

		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SISTEMA_VENTA);
		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_SISTEMA_VENTA, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {

					listSistVenDTO.add(DTOUtil.crearSistemaVentaDTO(setReg));

				} else {
					LOG.info("El catalogo CAT_SISTM_VENTA está vacio");
				}
			}
		}

		return listSistVenDTO;
	}
	
	/**
	 * Crea una instancia del DTO especificado. La clase debe tener un
	 * constructor default.
	 * 
	 * @param tipo
	 * @return
	 */
	private static <T> T nuevoContenedor(Class<T> tipo) {
		try {
			return tipo.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LOG.error("Contendor no def const." + tipo.getName());
			return null;
		}
	}
	
	/**
	 * Regresa <code>true</code> si el catalogo dinamico existe.
	 * 
	 * @param servicio
	 * @param nombreCatalogo
	 * @return
	 */
	private static boolean existeCatalogo(ServiceDynamicCatalogs servicio, String nombreCatalogo) {
		try {
			Catalogs catalogo = servicio.getCatalogByName(nombreCatalogo);
			return catalogo != null;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}
	
	/**
	 * Regresa el primer elemento del catalogo que conforma el parametro de
	 * busqueda. Los elementos solo tienen el campo de Id, Code y Descripcion.
	 * 
	 * @param servicio
	 * @param catalogo
	 * @param tipoElemento
	 * @param campo
	 * @param valor
	 * @return
	 * @throws Exception
	 */
	private static <T> T getElementoDeCatalogo(ServiceDynamicCatalogs servicio, String catalogo, Class<T> tipoElemento,
			String campo, String valor) throws Exception {
		List<T> registros = getElementosDeCatalogo(servicio, catalogo, tipoElemento, Arrays.asList(new AttrSearch(campo, valor)));
		if (registros.size() > 0) {
			return registros.get(0);
		}
		return nuevoContenedor(tipoElemento);
	}

	/**
	 * Regresa todos los elementos de un catalogo que conforman a la
	 * busqueda. Los elementos solo tienen el campo de Id, Code y Descripcion.
	 * 
	 * @param servicio
	 * @param catalogo
	 * @param tipoElemento
	 * @param parametrosBusqueda
	 * @return
	 */
	private static <T> List<T> getElementosDeCatalogo(ServiceDynamicCatalogs servicio,
			String catalogo, Class<T> tipoElemento, List<AttrSearch> parametrosBusqueda) {
		List<T> resultado = new ArrayList<>();
		if (!existeCatalogo(servicio, catalogo)) {
			LOG.info("No existe el Catalogo " + catalogo);
			return resultado;
		}

		try {
			List<CatRegs> registros = servicio.getRegsJobs(catalogo, parametrosBusqueda);
			for (CatRegs registro : registros) {
				// Si el tipo tiene metodo para crear registro en DTOUtil, usarlo
				if (tipoElemento == SistemaVentaDTO.class) {
					resultado.add(tipoElemento.cast(DTOUtil.crearSistemaVentaDTO(registro.getRegValues())));
				} else if (tipoElemento == SubcategoriaDTO.class) {
					resultado.add(tipoElemento.cast(DTOUtil.crearSubcategoriaDTO(registro.getRegValues())));
				} else if (tipoElemento == CategoriaDTO.class) {
					resultado.add(tipoElemento.cast(DTOUtil.crearCategoriaDTO(registro.getRegValues())));
				} else if (tipoElemento == PeriodoDTO.class) {
					resultado.add(tipoElemento.cast(DTOUtil.crearPeriodoDTO(registro.getRegValues())));
				} else if (tipoElemento == DepartamentoDTO.class) {
					resultado.add(tipoElemento.cast(DTOUtil.crearDepartamentoDTO(registro.getRegValues())));
				} else {
					T contenedor = nuevoContenedor(tipoElemento);
					for (CatRegValues valor : registro.getRegValues()) {
						if (Constants.ATT_ID.equals(valor.getCatAttrs().getAttribs().getAttrName())) {
							BeanUtils.setProperty(contenedor, Constants.PROP_ID, Integer.valueOf(valor.getValue()));
						}
						if (Constants.ATT_CODE.equals(valor.getCatAttrs().getAttribs().getAttrName())) {
							BeanUtils.setProperty(contenedor, Constants.PROP_CODIGO, valor.getValue());
						}
						if (Constants.ATT_DESCRIPTION.equals(valor.getCatAttrs().getAttribs().getAttrName())) {
							BeanUtils.setProperty(contenedor, Constants.PROP_DESCRIPCION, valor.getValue());
						}
					}
					resultado.add(contenedor);
				}
			}
		} catch (Exception e) {
			LOG.warn("No se puede obtener contenido de catalogo " + catalogo);
		}

		return resultado;
	}
	
	/**
	 * Regresa una lista de los elementos de un catalogo. Los elementos solo
	 * tienen el campo de Id, Code y Descripcion.
	 * 
	 * @param servicio
	 * @param catalogo
	 * @param tipoElemento
	 * @return
	 */
	private static <T> List<T> getElementosDeCatalogo(ServiceDynamicCatalogs servicio,
			String catalogo, Class<T> tipoElemento) {
		return getElementosDeCatalogo(servicio, catalogo, tipoElemento, null);
	}

	public static List<TipoCampanaDTO> getTipoCampanas(ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		return getElementosDeCatalogo(serviceDynamicCatalogs, Constants.CAT_TIPO_CAM, TipoCampanaDTO.class);
	}

	public static List<EtiquetaDTO> getEtiquetas(ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		return getElementosDeCatalogo(serviceDynamicCatalogs, Constants.CAT_ETIQ, EtiquetaDTO.class);
	}

	public static EtiquetaDTO getEtiqueta(ServiceDynamicCatalogs serviceDynamicCatalogs, Integer idEtiqueta) 
			throws GeneralException {
		
		try {
			return getElementoDeCatalogo(serviceDynamicCatalogs, Constants.CAT_ETIQ, EtiquetaDTO.class,
					Constants.ATT_ID, idEtiqueta.toString());
		} catch (Exception e1) {
			LOG.error(e1);
			return new EtiquetaDTO();
		}
	}

	public static SistemaVentaDTO getSistemaVentaDTO(ServiceDynamicCatalogs serviceDynamicCatalogs, Integer id)
			throws Exception {
		return getElementoDeCatalogo(serviceDynamicCatalogs, Constants.CAT_SISTEMA_VENTA, SistemaVentaDTO.class,
				Constants.ATT_ID, id.toString());
	}
	
	public static PeriodoDTO getPeriodo(
			ServiceDynamicCatalogs serviceDynamicCatalogs, int idPeriodo)
			throws Exception {
		return getElementoDeCatalogo(serviceDynamicCatalogs, Constants.CAT_PERIODO, PeriodoDTO.class,
				Constants.ATT_ID, Integer.toString(idPeriodo));
	}

	public static PeriodoDTO getPeriodoAlterno(
			ServiceDynamicCatalogs serviceDynamicCatalogs, int idPeriodo)
			throws Exception {

		PeriodoDTO periodoDTO = new PeriodoDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(idPeriodo));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_PERIODO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_PERIODO, insertSearch);
			
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					periodoDTO=	DTOUtil.crearPeriodoDTO(setReg);
				} else {
					LOG.info("El catalogo CAT_PERIODO está vacio");
				}
			}
			
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_PERIODO);
		}
		return periodoDTO;
	}

	public static TipoCampanaDTO getTipoCampana(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer idTipoCampana)
			throws Exception {
		return getElementoDeCatalogo(serviceDynamicCatalogs, Constants.CAT_TIPO_CAM,
				TipoCampanaDTO.class, Constants.ATT_ID, idTipoCampana.toString());
	}

	public static UsuarioDTO getUsuario(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer idUsuario)
			throws Exception {
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(idUsuario));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		List<UsuarioDTO> usuarios = getUsuario(serviceDynamicCatalogs, insertSearch);
		if (usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		}
		return null;
	}
	
	public static List<UsuarioDTO> getUsuario( ServiceDynamicCatalogs serviceDynamicCatalogs, List<AttrSearch> insertSearch)
			throws GeneralException {
		List<UsuarioDTO> usuariosLst= new ArrayList<UsuarioDTO>();
		List<CategoriaDTO> listCategory = new ArrayList<>();
		CategoriaDTO category = null;
		try{
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_USER, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					UsuarioDTO userDTO = new UsuarioDTO();
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setUserId(Integer.valueOf(regVals
									.getValue()));
						}
						if (attName.equals(Constants.ATT_NAME)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setName(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_PLAST_NAME)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setPlastName(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_MLAST_NAME)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setMlastName(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_EMAIL)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setEmail(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_LOGIN)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setLogin(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_PASSWORD)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setPassword(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_ROLE)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setRole(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_IS_LEVEL1)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setIsNivel1(regVals.getValue().charAt(0));
						}
						if (attName.equals(Constants.ATT_INACTIVE)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							userDTO.setIsActivo(regVals.getValue().charAt(0));
						}
						if (attName.equals(Constants.ATT_CATEGORY)) {
							LOG.info("Atributo" + attName + "Valor"
									+ regVals.getValue());
							category = MBUtil.getCategoriaByName(
									serviceDynamicCatalogs, regVals.getValue());
							listCategory.add(category);
						}
					}
					userDTO.setCategorias(listCategory);
					usuariosLst.add(userDTO);
				}
			}
		}catch(Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
		return usuariosLst;
	}

	/**
	 * Metodo para obtener los valores de un Combo por nombre y descripcion
	 * 
	 * @param catName
	 * @param serviceDynamicCatalogs
	 * @param catDescription
	 * @return
	 */
	public static Map<String, String> cargarcombos(String catName,
			String catDescription, ServiceDynamicCatalogs serviceDynamicCatalogs) {

		Map<String, String> responseArray = new HashMap<String, String>();
		List<CatViewControls> viewList;
		boolean bandera;
		try {
			Catalogs catalog = serviceDynamicCatalogs.getCatalogByNameAndDescription(catName, catDescription);
			if (catalog != null) {
				viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
				Collections.sort(viewList);
				List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
				List<CatRegs> regs = serviceDynamicCatalogs.getRegs(catName, insertSearch);
				if (regs != null) {
					for (CatRegs reg : regs) {
						Set<CatRegValues> setReg = reg.getRegValues();
						if (setReg != null) {
							for (CatRegValues regVals : setReg) {
								String attName = regVals.getCatAttrs().getAttribs().getAttrName();
								if (attName.equals(Constants.ATT_ID) 
										|| attName.equals(Constants.ATT_CODE)) {
									String value = (String) regVals.getValue();
									if (responseArray.containsKey(String.valueOf(reg.getCatRegId()))) {
										String aux = responseArray.get(String.valueOf(reg.getCatRegId()));
										responseArray.remove(String.valueOf(reg.getCatRegId()));
										bandera = false;
										try {
											Integer.parseInt(aux);
										} catch (Exception e) {
											bandera = true;
										}
										if (bandera) {
											responseArray.put(aux, value);
										} else {
											responseArray.put(value, aux);
										}
									} else {
										responseArray.put(String.valueOf(reg.getCatRegId()), regVals.getValue());
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}

		return sortByValue(responseArray);
	}

	/**
	 * Metodo para Obtener una lista de DTO de Subcategorias
	 * 
	 * @param serviceDynamicCatalogs
	 * @return
	 * @throws Exception
	 */
	public static List<SubcategoriaDTO> getSubcategoriasByAttr(
			ServiceDynamicCatalogs serviceDynamicCatalogs,
			List<AttrSearch> insertSearch) throws Exception {

		List<SubcategoriaDTO> listSubcategoriaDTO = new ArrayList<SubcategoriaDTO>();

		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SCATEGORY);

		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_SCATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					listSubcategoriaDTO.add(DTOUtil
							.crearSubcategoriaDTO(setReg));
				}
			}
		} else {
			LOG.info("El catalogo CAT_SCATEGORY está vacio");
		}
		return listSubcategoriaDTO;
	}

	/**
	 * devuelve la lista de catItems de acuerdo a la lista de atributos que se
	 * solicite.se tiene que pasar el atributo inicializado para que funcione
	 * 
	 * @param serviceDynamicCatalogs
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static List<CatItemDTO> getCatItemByAttr(
			ServiceDynamicCatalogs serviceDynamicCatalogs,
			List<AttrSearch> insertSearch) throws Exception {

		List<CatItemDTO> listCatItemDTO = new ArrayList<CatItemDTO>();
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_ITEM);

		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_ITEM, insertSearch);

			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();

				if (setReg != null) {
					listCatItemDTO.add(DTOUtil.crearCatItemDTO(setReg));
				}
			}
		} else {
			LOG.info("El catalogo CAT_ITEM está vacio");
		}
		return listCatItemDTO;
	}

	/**
	 * devuelve la lista de CategoriaDTO de acuerdo a la lista de atributos que
	 * se solicite.se tiene que pasar el atributo inicializado para que funcione
	 * 
	 * @param serviceDynamicCatalogs
	 * @param insertSearch
	 * @return
	 * @throws Exception
	 */
	public static List<CategoriaDTO> getCategoriasByAttr(
			ServiceDynamicCatalogs serviceDynamicCatalogs,
			List<AttrSearch> insertSearch) throws Exception {

		List<CategoriaDTO> listCategoriaDTO = new ArrayList<CategoriaDTO>();
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_CATEGORY);

		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_CATEGORY, insertSearch);
			for (CatRegs reg : regs) {

				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					try {
						listCategoriaDTO.add(DTOUtil.crearCategoriaDTO(setReg));
					} catch (Exception e) {
						LOG.error(e);
					}
				}
			}

		} else {
			LOG.info("El catalogo CAT_CATEGORY está vacio");
		}
		return listCategoriaDTO;
	}

	/**
	 * Metodo que obtiene la Lista de Id de las categorias a las que un usuario
	 * tiene acceso.
	 * 
	 * @param serviceDynamicCatalogs
	 * @param idUser
	 * @return
	 * @throws Exception
	 */
	public static List<String> getUserCategories(
			ServiceDynamicCatalogs serviceDynamicCatalogs, int idUser)
			throws Exception {

		AttrSearch attr = new AttrSearch();
		List<String> listaIdCategorias = new ArrayList<String>();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();

		attr.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
		attr.setAttrName(Constants.ATT_ID_USUARIO);
		attr.setValue(String.valueOf(idUser));
		insertSearch.add(attr);

		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_US_CATEGORY);

		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_US_CATEGORY, insertSearch);
			for (CatRegs reg : regs) {

				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					try {
						String idCategoria = "";
						for (CatRegValues regVals : setReg) {
							String attName = regVals.getCatAttrs().getAttribs()
									.getAttrName();
							if (attName.equals(Constants.ATT_ID_CATEGORIA)) {
								idCategoria = regVals.getValue();
							}
						}
						listaIdCategorias.add(idCategoria);
					} catch (Exception e) {
						LOG.error(e);
					}
				}
			}

		} else {
			LOG.info("El catalogo CAT_US_CATEGORY está vacio");
		}
		return listaIdCategorias;
	}

	/**
	 * devuelve la lista de DepartamentoDTO de acuerdo a la lista de atributos
	 * que se solicite.se tiene que pasar el atributo inicializado para que
	 * funcione
	 * 
	 * @param serviceDynamicCatalogs
	 * @param listAttrSearch
	 * @return
	 * @throws Exception
	 */
	public static List<DepartamentoDTO> getDepartamentoByAttr(
			ServiceDynamicCatalogs serviceDynamicCatalogs,
			List<AttrSearch> listAttrSearch) throws Exception {

		List<DepartamentoDTO> listDeptos = new ArrayList<DepartamentoDTO>();
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_DEPTO);

		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_DEPTO, listAttrSearch);
			for (CatRegs reg : regs) {

				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					try {
						listDeptos.add(DTOUtil.crearDepartamentoDTO(setReg));
					} catch (Exception e) {
						LOG.error(e);
					}
				}
			}

		} else {
			LOG.info("El catalogo CAT_DEPTO está vacio");
		}
		return listDeptos;
	}

	/**
	 * Obtiene la lista de ID de los departamento del catalogo CAT_ITEM , de
	 * acuerdo a la lista de category Id que se le proporcione en el parametro
	 * listAttrSearch
	 * 
	 * @param serviceDynamicCatalogs
	 * @param listAttrSearch
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDeptoIdListByCatUserCategory(
			ServiceDynamicCatalogs serviceDynamicCatalogs,
			List<AttrSearch> listAttrSearch) throws Exception {

		List<String> listIdDepto = new ArrayList<String>();
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_ITEM);
		Set<String> setIdDeptos = new HashSet<String>();

		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_ITEM, listAttrSearch);
			for (CatRegs reg : regs) {

				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					try {
						for (CatRegValues regVals : setReg) {
							String attName = regVals.getCatAttrs().getAttribs()
									.getAttrName();

							if (attName.equals(Constants.ATT_ID_DEPTO)) {

								if (!setIdDeptos.contains(regVals.getValue())) {

									setIdDeptos.add(regVals.getValue());
								}

							}
						}
					} catch (Exception e) {
						LOG.error(e);
					}
				}
			}

		} else {
			LOG.info("El catalogo CAT_ITEM está vacio");
		}
		listIdDepto.addAll(setIdDeptos);
		return listIdDepto;

	}

	public static SelectItem[] getSelectItems(List<?> entities,
			boolean initValue) {
		int size = initValue ? entities.size() + 1 : entities.size();
		SelectItem[] items = new SelectItem[size];
		int i = 0;

		if(initValue){
			items[i++] = new SelectItem(null, "Seleccione");
		}
		for (Object x : entities) {
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}

	public static CatMedioDTO getMedio(
			ServiceDynamicCatalogs serviceDynamicCatalogs, int idMedio)
			throws Exception {
		CatMedioDTO medio = new CatMedioDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(idMedio));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							medio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							medio.setCode(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_MEDIO);
		}
		return medio;
	}

	public static List<CatMedioDTO> getMedios(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		List<CatMedioDTO> listaMedios = new ArrayList<>();
		AttrSearch attrSearch = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					CatMedioDTO medio = new CatMedioDTO();
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();

						if (attName.equals(Constants.ATT_ID)) {
							medio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							medio.setCode(regVals.getValue());
						}
					}
					listaMedios.add(medio);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_MEDIO);
		}
		return listaMedios;
	}
	
	private static  CatMedioDTO getMedioByName1(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String code)
			throws Exception {

		CatMedioDTO medio =null;
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CODE);
		attrSearch.setValue(String.valueOf(code));	
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						 medio = new CatMedioDTO();
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							medio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							medio.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							medio.setDescripcion(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_MEDIO);
		}
		return medio;
	}

	public static CatMedioDTO getMedioByName(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String code)
			throws Exception {

		CatMedioDTO medio = new CatMedioDTO();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CODE);
		attrSearch.setValue(String.valueOf(code));	
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							medio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							medio.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							medio.setDescripcion(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_MEDIO);
		}
		return medio;
	}

	public static CatTipoMedioDTO getTipo(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer idTipo)
			throws Exception {

		CatTipoMedioDTO tipo = new CatTipoMedioDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(idTipo));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_TIPO_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_TIPO_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							tipo.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							tipo.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_ID_MEDIO)) {
							tipo.setCode(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_TIPO_CAM);
		}
		return tipo;
	}
	
	public static CatTipoMedioDTO getTipoMediosByIdTipo(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer idTipo)
			throws Exception {
		CatTipoMedioDTO tipoMedio = null;
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(idTipo));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_TIPO_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_TIPO_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				 tipoMedio = new CatTipoMedioDTO();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							tipoMedio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							tipoMedio.setCode(regVals.getValue());
						}						
						if (attName.equals(Constants.ATT_ID_MEDIO)) {
							CatMedioDTO medio = MBUtil.getMedioByName(
									serviceDynamicCatalogs, regVals.getValue());
							tipoMedio.setCatMedio(medio);
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_TIPO_MEDIO);
		}
		return tipoMedio;
	}

	public static List<CatTipoMedioDTO> getTiposMedios(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {

		List<CatTipoMedioDTO> listaTipoMedios = new ArrayList<>();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_TIPO_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_TIPO_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					CatTipoMedioDTO tipoMedio = new CatTipoMedioDTO();
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							tipoMedio
									.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							tipoMedio.setCode(regVals.getValue());

						}
						if (attName.equals(Constants.ATT_ID_MEDIO)) {
							CatMedioDTO medio = getMedioByName1(
									serviceDynamicCatalogs, regVals.getValue());
							tipoMedio.setCatMedio(medio);
						}

					}

					listaTipoMedios.add(tipoMedio);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_TIPO_MEDIO);
		}
		return listaTipoMedios;
	}

	public static List<CatTipoMedioDTO> getTiposMediosByIdMedio(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String idmedio)
			throws Exception {

		List<CatTipoMedioDTO> listaTipoMedios = new ArrayList<>();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID_MEDIO);
		attrSearch.setValue(String.valueOf(idmedio));
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_TIPO_MEDIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_TIPO_MEDIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					CatTipoMedioDTO tipoMedio = new CatTipoMedioDTO();
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							tipoMedio
									.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							tipoMedio.setCode(regVals.getValue());

						}
						if (attName.equals(Constants.ATT_ID_MEDIO)) {
							CatMedioDTO medio = MBUtil.getMedioByName(
									serviceDynamicCatalogs, regVals.getValue());
							tipoMedio.setCatMedio(medio);
						}
					}
					listaTipoMedios.add(tipoMedio);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_TIPO_MEDIO);
		}
		return listaTipoMedios;
	}

	public static List<SucursalDTO> getSucursales(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {

		List<SucursalDTO> listSucursales = new ArrayList<>();
		SucursalDTO sucursal = null;
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SUCURSAL);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_SUCURSAL, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							sucursal = MBUtil.getSucursal(
									serviceDynamicCatalogs,
									(Integer.valueOf(regVals.getValue())));
						}

					}
					listSucursales.add(sucursal);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_TIPO_MEDIO);
		}

		return listSucursales;
	}

	public static NegocioDTO getNegocio(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer id)
			throws Exception {

		NegocioDTO negocio = new NegocioDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_NEGOCIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_NEGOCIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							negocio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							negocio.setCode(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_NEGOCIO);
		}
		return negocio;
	}

	public static ZonaDTO getZona(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer id)
			throws Exception {

		ZonaDTO zona = new ZonaDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_ZONA);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_ZONA, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							zona.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							zona.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							zona.setDescripcion(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return zona;
	}

	public static SucursalDTO getSucursal(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer id)
			throws Exception {

		SucursalDTO sucursal = new SucursalDTO();
		List<NegocioDTO> listNegocios = new ArrayList<>();
		NegocioDTO negocio;
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SUCURSAL);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_SUCURSAL, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							sucursal.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							sucursal.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.CODE_ZONA)) {
							ZonaDTO zona = getZonaByName(
									serviceDynamicCatalogs, regVals.getValue());
							sucursal.setZona(zona);
						}

						if (attName.equals(Constants.CODE_NEGOCIO)) {
							negocio = getNegocioByName(serviceDynamicCatalogs,
									regVals.getValue());
							listNegocios.add(negocio);
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		sucursal.setNegocios(listNegocios);
		return sucursal;
	}

	private static NegocioDTO getNegocioByName(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String code)
			throws Exception {
		NegocioDTO negocio = new NegocioDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CODE);
		attrSearch.setValue(String.valueOf(code));
		attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_NEGOCIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_NEGOCIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							negocio.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							negocio.setCode(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_NEGOCIO);
		}
		return negocio;
	}

	private static ZonaDTO getZonaByName(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String code)
			throws Exception {

		ZonaDTO zona = new ZonaDTO();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CODE);
		attrSearch.setValue(String.valueOf(code));
		attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_ZONA);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_ZONA, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							zona.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							zona.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							zona.setDescripcion(regVals.getValue());
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return zona;
	}

	public static List<Integer> getHojasEspacios(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		List<Integer> hojasEspacios = new ArrayList<>();
		AttrSearch attrSearch = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.HOJA_ESPACIO);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.HOJA_ESPACIO, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							hojasEspacios.add((Integer.valueOf(regVals
									.getValue())));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.HOJA_ESPACIO);
		}
		return hojasEspacios;
	}

	public static CategoriaDTO getCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer id)
			throws Exception {

		CategoriaDTO category = new CategoriaDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_CATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_CATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							category.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							category.setCodigo(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							category.setEsMercancia(regVals.getValue()
									.charAt(0));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return category;
	}
	
	
	
	public static CatItemDTO getArticulo(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer id)
			throws Exception {

		CatItemDTO category = new CatItemDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_CATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_ITEM, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							category.setId(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_CODE)) {
							category.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							category.setCode(regVals.getValue());
						}
						
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return category;
	}
	
	
	
	
	
	

	public static CategoriaDTO getCategoriaByName(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String code)
			throws Exception {

		CategoriaDTO category = new CategoriaDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CODE);
		attrSearch.setValue(String.valueOf(code));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_CATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_CATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							category.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							category.setCodigo(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							category.setEsMercancia(regVals.getValue()
									.charAt(0));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return category;
	}
	
	public static ProgramaDTO getPrograma(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String id)
			throws Exception {
		ProgramaDTO programaDTO = new ProgramaDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_PROGRAMA);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_PROGRAMA, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_CODE)) {
							programaDTO.setCode(regVals.getValue());
						} else if (attName.equals(Constants.ATT_ID)) {
							programaDTO.setId(Integer.valueOf(regVals
									.getValue()));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_PROGRAMA);
		}
		return programaDTO;
	}
	
	public static CategoriaDTO getCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String id)
			throws Exception {

		CategoriaDTO category = new CategoriaDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(id));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_CATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_CATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							category.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							category.setCodigo(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							category.setEsMercancia(regVals.getValue()
									.charAt(0));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_CATEGORY);
		}
		return category;
	}

	public static SubCategoriasDTO getSubCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer key)
			throws Exception {

		SubCategoriasDTO subcategoria = new SubCategoriasDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID);
		attrSearch.setValue(String.valueOf(key));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SCATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_SCATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							subcategoria.setId((Integer.valueOf(regVals
									.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							subcategoria.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							subcategoria.setMercancia(regVals.getValue()
									.charAt(0));
						}
						if (attName.equals(Constants.ATT_CATEGORY)) {
							CategoriaDTO categoria = MBUtil.getCategoria(
									serviceDynamicCatalogs,
									Integer.valueOf(regVals.getValue()));
							subcategoria.setCategoria(categoria);
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return subcategoria;
	}

	public static SubCategoriasDTO getSubCategoriaByName(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String key)
			throws Exception {

		SubCategoriasDTO subcategoria = new SubCategoriasDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CODE);
		attrSearch.setValue(String.valueOf(key));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SCATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_SCATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							subcategoria.setId((Integer.valueOf(regVals
									.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							subcategoria.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_DESCRIPTION)) {
							subcategoria.setMercancia(regVals.getValue()
									.charAt(0));
						}
						if (attName.equals(Constants.ATT_CATEGORY)) {
							CategoriaDTO categoria = MBUtil.getCategoria(
									serviceDynamicCatalogs,
									Integer.valueOf(regVals.getValue()));
							subcategoria.setCategoria(categoria);
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return subcategoria;
	}

	public static List<SubCategoriasDTO> getSubCategorias(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {

		List<SubCategoriasDTO> list = new ArrayList<>();
		SubCategoriasDTO subCategoria = null;
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SCATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_SCATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							subCategoria = MBUtil.getSubCategoria(
									serviceDynamicCatalogs,
									Integer.valueOf(regVals.getValue()));
						}

					}
					list.add(subCategoria);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_TIPO_MEDIO);
		}

		return list;
	}
	
	public static List<SubCategoriasDTO> getSubCategoriasByCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String code)
			throws Exception {
		List<SubCategoriasDTO> list = new ArrayList<>();
		SubCategoriasDTO subcategoria = new SubCategoriasDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CATEGORY);
		attrSearch.setValue(String.valueOf(code));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_SCATEGORY);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_SCATEGORY, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							subcategoria.setId((Integer.valueOf(regVals.getValue())));
						}
						if (attName.equals(Constants.ATT_CODE)) {
							subcategoria.setCode(regVals.getValue());
						}
						if (attName.equals(Constants.ATT_CATEGORY)) {
							CategoriaDTO categoria = getCategoriaByName(
									serviceDynamicCatalogs, regVals.getValue());
							subcategoria.setCategoria(categoria);
						}

					}
					list.add(subcategoria);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_SCATEGORY);
		}
		return list;
	}
	
	public static UsuarioDTO getUsuarioByCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String categoria)
			throws Exception {

		UsuarioDTO user = new UsuarioDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CATEGORY);
		attrSearch.setValue(String.valueOf(categoria));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_USER);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_USER, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							user = MBUtil.getUsuario(serviceDynamicCatalogs, (Integer.valueOf(regVals.getValue())));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return user;
	}
	
	public static UsuarioDTO getUsuarioByIdCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, Integer idCategoria)
			throws Exception {
		UsuarioDTO user= new UsuarioDTO();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID_CATEGORIA);
		attrSearch.setValue(String.valueOf(idCategoria));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		List<UsuarioDTO> usuarioLst= getUsuarioByIdCategoria(serviceDynamicCatalogs, insertSearch);
		if(usuarioLst!=null&&usuarioLst.size()>0){
			user= usuarioLst.get(0);
		}
		return user;
	}
	
	public static List<UsuarioDTO> getUsuarioByIdCategoria(
			ServiceDynamicCatalogs serviceDynamicCatalogs, List<AttrSearch> insertSearch)
			throws Exception {
		List<UsuarioDTO> usuarioLst= new ArrayList<UsuarioDTO>();
		List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
				Constants.CAT_US_CATEGORY, insertSearch);
		for (CatRegs reg : regs) {
			Set<CatRegValues> setReg = reg.getRegValues();
			if (setReg != null) {
				for (CatRegValues regVals : setReg) {
					String attName = regVals.getCatAttrs().getAttribs()
							.getAttrName();
					if (attName.equals(Constants.ATT_ID_USUARIO)) {
						UsuarioDTO user = new UsuarioDTO();
						user = MBUtil.getUsuario(serviceDynamicCatalogs, (Integer.valueOf(regVals.getValue())));
						if(user!=null){
							usuarioLst.add(user);
						}
					}
				}
			}
		}
		return usuarioLst;
	}

	public static UsuarioDTO getUsuarioByCategoriaAndRole(
			ServiceDynamicCatalogs serviceDynamicCatalogs, String categoria,Integer role)
			throws Exception {

		UsuarioDTO user = new UsuarioDTO();
		AttrSearch attrSearch = new AttrSearch();
		AttrSearch attrSearch2 = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_CATEGORY);
		attrSearch.setValue(String.valueOf(categoria));
		attrSearch2.setAttrName(Constants.ATT_ROLE);
		attrSearch2.setValue(String.valueOf(role));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		insertSearch.add(attrSearch2);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_USER);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_USER, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							user = MBUtil.getUsuario(serviceDynamicCatalogs, (Integer.valueOf(regVals.getValue())));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return user;
	}
	
	
	public static UsuarioDTO getUsuarioByRole(
			ServiceDynamicCatalogs serviceDynamicCatalogs,Integer role)
			throws Exception {

		UsuarioDTO user = new UsuarioDTO();
		AttrSearch attrSearch2 = new AttrSearch();
		attrSearch2.setAttrName(Constants.ATT_ROLE);
		attrSearch2.setValue(String.valueOf(role));
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch2);
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_USER);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_USER, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ID)) {
							user = MBUtil.getUsuario(serviceDynamicCatalogs, (Integer.valueOf(regVals.getValue())));
						}
					}
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + Constants.CAT_ZONA);
		}
		return user;
	}
	
	
	
	/**
	 * Metodo que obtiene la Lista de Id de las categorias a las que un usuario
	 * tiene acceso.
	 * 
	 * @param serviceDynamicCatalogs
	 * @param idUser
	 * @return
	 * @throws Exception
	 */
	public static List<String> getCategoriesDeptoByAttr(ServiceDynamicCatalogs serviceDynamicCatalogs, List<AttrSearch> insertSearch)throws Exception {

		List<String> listaIdCategorias 	= new ArrayList<String>();

		Catalogs catalogUser = serviceDynamicCatalogs.getCatalogByName(Constants.CAT_DEPTO_CATE);

		if (catalogUser != null) {

			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(Constants.CAT_DEPTO_CATE, insertSearch);
			for (CatRegs reg : regs) {

				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					try {
						String idCategoria = "";
						for (CatRegValues regVals : setReg) {
							String attName = regVals.getCatAttrs().getAttribs().getAttrName();
							if (attName.equals(Constants.ATT_ID_CATEGORY)) {
								idCategoria = regVals.getValue();
							}
						}
						listaIdCategorias.add(idCategoria);
					} catch (Exception e) {
						LOG.error(e);
					}
				}
			}

		} else {
			LOG.info("El Catalogo CAT_DEPTO_CATE  esta vacio");
		}
		return listaIdCategorias;
	}
	

	/**
	 * devuelve la lista de catItems de acuerdo a la lista de atributos que se
	 * solicite.se tiene que pasar el atributo inicializado para que funcione
	 * 
	 * @param serviceDynamicCatalogs
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static List<ArticuloDTO> getArticuloDTOsByAttr(
			ServiceDynamicCatalogs serviceDynamicCatalogs,ServiceArquitectura serviceArquitectura,
			List<AttrSearch> insertSearch) throws Exception {

		List<ArticuloDTO> listArticulo = new ArrayList<ArticuloDTO>();
		Catalogs catalogUser = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_ARTICULO);

		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(
					Constants.CAT_ARTICULO, insertSearch);

			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();

				if (setReg != null) {
					listArticulo.add(DTOUtil.crearArticuloDTO(setReg,serviceArquitectura));
				}
			}
		} else {
			LOG.info("El catalogo CAT_ARTICULO está vacio");
		}
		return listArticulo;
	}
	
	public static List<SelectItem> getSelectItemsByCatalog(ServiceDynamicCatalogs serviceDynamicCatalogs, String catalogo) throws Exception {
		List<SelectItem> lSelectItems = new ArrayList<SelectItem>();
		AttrSearch attrSearch = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch.add(attrSearch);
		Catalogs catalogUser = serviceDynamicCatalogs.getCatalogByName(catalogo);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(catalogo, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					int value = 0;
					String label = "";
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs().getAttrName();
						if (attName.equals(Constants.ATT_CODE)) {
							label = regVals.getValue();
						} else if (attName.equals(Constants.ATT_ID)) {
							value = Integer.valueOf(regVals.getValue());
						}
					}
					SelectItem selItem = new SelectItem(value, label);
					lSelectItems.add(selItem);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + catalogo);
		}
		return lSelectItems;
	}
	
	public static List<SelectItem> getSelectItemsByCatalog(ServiceDynamicCatalogs serviceDynamicCatalogs, String catalogo, List<AttrSearch> attrSearch) throws Exception {
		List<SelectItem> lSelectItems = new ArrayList<SelectItem>();
		Catalogs catalogUser = serviceDynamicCatalogs.getCatalogByName(catalogo);
		if (catalogUser != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegsJobs(catalogo, attrSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					int value = 0;
					String label = "";
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs().getAttrName();
						if (attName.equals(Constants.ATT_CODE)) {
							label = regVals.getValue();
						} else if (attName.equals(Constants.ATT_ID)) {
							value = Integer.valueOf(regVals.getValue());
						}
					}
					SelectItem selItem = new SelectItem(value, label);
					lSelectItems.add(selItem);
				}
			}
		} else {
			LOG.info("No existe el Catalogo " + catalogo);
		}
		return lSelectItems;
	}
	
	
	  
	
	public static double getMode(List<Double> numberList) {
	    HashMap<Double,Double> freqs = new HashMap<Double,Double>();
	    for (double d: numberList) {
	        Double freq = freqs.get(d);
	        freqs.put(d, (freq == null ? 1 : freq + 1));   
	    }
	    double mode = 0;
	    double maxFreq = 0;    
	    for (Map.Entry<Double,Double> entry : freqs.entrySet()) {     
	        double freq = entry.getValue();
	        if (freq > maxFreq) {
	            maxFreq = freq;
	            mode = entry.getKey();
	        }
	    }    
	    return mode;
	}

}
