/**
 * 
 */
package com.adinfi.seven.presentation.views;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;

import com.adinfi.seven.business.domain.TblExistenciaItemTienda;
import com.adinfi.seven.business.domain.TblListaSeleccion;
import com.adinfi.seven.business.domain.TblListaSeleccionSku;
import com.adinfi.seven.business.domain.TblReporteVentas;
import com.adinfi.seven.business.services.ServiceAnalisis;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceReporte;
import com.adinfi.seven.persistence.dto.CatItemDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.DepartamentoDTO;
import com.adinfi.seven.persistence.dto.ItemDTO;
import com.adinfi.seven.persistence.dto.SubcategoriaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.datamodel.ItemDTODataModel;
import com.adinfi.seven.presentation.views.datamodel.ReporteVentasModel;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.DTOUtil;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.TreeUtil;
import com.adinfi.seven.presentation.views.util.Util;

/**
 * @author OMAR
 *
 */
public class MBAnalisis implements Serializable {
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServiceAnalisis  serviceAnalisis;
	
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	
	private UsuarioDTO userLogin;
	
	private ItemDTODataModel  itemDTODataModel;
	
	private TblListaSeleccion nuevaListaSeleccion;
	
	private List <ItemDTO> itemDTOList ;
	
	private List<ItemDTO> selectedItemList;

	private List<TblListaSeleccion> listaSeleccion;
	
	private List<CategoriaDTO> listCategorias;
	
	private Map<String,String> tiendasMap = new HashMap<String, String>(); 
	
	private Map<String,String> departamentosMap = new HashMap<String, String>();
	
	private Map<String,String> categoriasMap = new HashMap<String, String>();
	
	private Map<String,String> subcategoriasMap = new HashMap<String, String>();
	
	private Map<String,String> mecanicasMap = new HashMap<String, String>();
	
	private Map<String,ItemDTO> itemsLoad = new HashMap<String, ItemDTO>(); 
	
	private String tiendaSeleccionada;
	
	private String departamentoSeleccionado;
	
	private String categoriaSeleccionada;
	
	private String subcategoriaSeleccionada;
	
	private String nombreListaCreada;
	
	private String skuAgregar;
	
	private String skuBuscar;
	
	private boolean edicion ;
	
	private Integer idListaSeleccionada;
	
	private TreeNode root;
	
	private TreeNode[] selectedTableNodes;
	
	/**Demo*/
	private ServiceReporte serviceReporte;
	private List<ItemDTO> promocionHistorial;
	private List<ReporteVentasModel> repVentasModel;
	private Date fechaIPU;
	private Date fechaFPU;
	private Date fechaIPD;
	private Date fechaFPD;
	private String skuVentas;
	/***/
	
	/**
	 * ATRIBUTOS PARA CALCULOS INTERNOS, NO CUENTAN CON SET Y GET
	 */
	private List<String> listIdCategoria;
	
	
	@PostConstruct
	public void init() {
		try {
			inicializarVariables();
			cargarCategoriasId();
			cargarListasSeleccion();
			cargarComboBox();
		} catch (Exception e) {
			Util.logger(getClass()).error(e);
		}
		
	}
	
	/**Demo*/
	public void seleccionSku(String sku){
		skuVentas = sku;
		repVentasModel = new ArrayList<ReporteVentasModel>();
		fechaIPU = new Date();
		fechaFPU = new Date();
		fechaIPD = new Date();
		fechaFPD = new Date();
	}
	
	public void buscarVentasPeriodo(){
		try{
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMMMM-yyyy");
			List<TblReporteVentas> repVentas = new ArrayList<TblReporteVentas>();
			repVentasModel = new ArrayList<ReporteVentasModel>();
			
			DecimalFormat formato = new DecimalFormat("#,###.00");
			DecimalFormat formato2 = new DecimalFormat("#,###");
			
			repVentas = serviceReporte.getReporteVentasByDateSku(fechaIPU, fechaFPU, skuVentas);
			if(repVentas != null && repVentas.size() > 0){
				ReporteVentasModel repVentMod= new ReporteVentasModel();
				BigDecimal monto = BigDecimal.ZERO;
				Integer cant = Integer.parseInt("0");
				for (TblReporteVentas rep : repVentas) {
					repVentMod.setSku(skuVentas);
					repVentMod.setFechaIni(sdf.format(fechaIPU));
					repVentMod.setFechaFin(sdf.format(fechaFPU));
					cant = cant.intValue() + rep.getVentasUnidades().intValue();
					repVentMod.setVentaUni(formato2.format(cant.doubleValue()));
					monto = monto.add(rep.getVentasPesos());
					repVentMod.setVentaPes(formato.format(monto.doubleValue()));
				}
				repVentasModel.add(repVentMod);
			}
			
			repVentas = serviceReporte.getReporteVentasByDateSku(fechaIPD, fechaFPD, skuVentas);
			if(repVentas != null && repVentas.size() > 0){
				ReporteVentasModel repVentMod= new ReporteVentasModel();
				BigDecimal monto = BigDecimal.ZERO;
				Integer cant = Integer.parseInt("0");
				for (TblReporteVentas rep : repVentas) {
					repVentMod.setSku(skuVentas);
					repVentMod.setFechaIni(sdf.format(fechaIPD));
					repVentMod.setFechaFin(sdf.format(fechaFPD));
					cant = cant.intValue() + rep.getVentasUnidades().intValue();
					repVentMod.setVentaUni(formato2.format(cant.doubleValue()));
					monto = monto.add(rep.getVentasPesos());
					repVentMod.setVentaPes(formato.format(monto.doubleValue()));
				}
				repVentasModel.add(repVentMod);
			}
			
		}catch (Exception e) {
			Util.logger(getClass()).error(e);
			repVentasModel = new ArrayList<ReporteVentasModel>();
		}
	}
	
	public void cargarHistorialPromocion(String idSku, String fecha, String tipo, String precio){
		try {
			promocionHistorial = new ArrayList<ItemDTO>();
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMMMM-yyyy");
			DecimalFormat formato = new DecimalFormat("#,###.00");
			Date iFecha=sdf.parse(fecha);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(iFecha);
			
			calendar.add(Calendar.DAY_OF_YEAR, -2);
			Date iFecha1=calendar.getTime();
			String fecha1 = sdf.format(iFecha1);
			calendar.add(Calendar.DAY_OF_YEAR, -4);
			Date iFecha2=calendar.getTime();
			String fecha2 = sdf.format(iFecha2);
			calendar.add(Calendar.DAY_OF_YEAR, -6);
			Date iFecha3=calendar.getTime();
			String fecha3 = sdf.format(iFecha3);
			calendar.add(Calendar.DAY_OF_YEAR, -10);
			Date iFecha4=calendar.getTime();
			String fecha4 = sdf.format(iFecha4);
			calendar.add(Calendar.DAY_OF_YEAR, -15);
			Date iFecha5=calendar.getTime();
			String fecha5 = sdf.format(iFecha5);
			
			String dTipo1="DESCUENTO 10P";
			String dTipo2="DESCUENTO 20P";
			String dTipo3="NORMAL";
			
			Double dPrecio = Double.valueOf(precio);
			Double dPrecio1 = dPrecio.doubleValue() - 2;
			Double dPrecio2 = dPrecio.doubleValue() - 1;
			Double dPrecio3 = dPrecio.doubleValue() - 3;
			Double dPrecio4 = dPrecio.doubleValue() - 4;
			Double dPrecio5 = dPrecio.doubleValue() - 5;
			
			ItemDTO item = new ItemDTO();
			item.setSku(idSku);
			item.setFechaUltimaPromocion(fecha);
			item.setTipoUltimaPromocion(tipo);
			item.setPrecioUltimaPromocion(formato.format(dPrecio.doubleValue()));
			promocionHistorial.add(item);
			
			if(dPrecio1.doubleValue() > 0){
				ItemDTO item1 = new ItemDTO();
				item1.setSku(idSku);
				item1.setFechaUltimaPromocion(fecha1);
				item1.setTipoUltimaPromocion(dTipo1);
				item1.setPrecioUltimaPromocion(formato.format(dPrecio1.doubleValue()));
				promocionHistorial.add(item1);
			}
			
			if(dPrecio2.doubleValue() > 0){
				ItemDTO item2 = new ItemDTO();
				item2.setSku(idSku);
				item2.setFechaUltimaPromocion(fecha2);
				item2.setTipoUltimaPromocion(dTipo2);
				item2.setPrecioUltimaPromocion(formato.format(dPrecio2.doubleValue()));
				promocionHistorial.add(item2);
			}
			
			if(dPrecio3.doubleValue() > 0){
				ItemDTO item3 = new ItemDTO();
				item3.setSku(idSku);
				item3.setFechaUltimaPromocion(fecha3);
				item3.setTipoUltimaPromocion(dTipo3);
				item3.setPrecioUltimaPromocion(formato.format(dPrecio3.doubleValue()));
				promocionHistorial.add(item3);
			}
			
			if(dPrecio4.doubleValue() > 0){
				ItemDTO item4 = new ItemDTO();
				item4.setSku(idSku);
				item4.setFechaUltimaPromocion(fecha4);
				item4.setTipoUltimaPromocion(tipo);
				item4.setPrecioUltimaPromocion(formato.format(dPrecio4.doubleValue()));
				promocionHistorial.add(item4);
			}
			
			if(dPrecio5.doubleValue() > 0){
				ItemDTO item5 = new ItemDTO();
				item5.setSku(idSku);
				item5.setFechaUltimaPromocion(fecha5);
				item5.setTipoUltimaPromocion(dTipo2);
				item5.setPrecioUltimaPromocion(formato.format(dPrecio5.doubleValue()));
				promocionHistorial.add(item5);
			}
		} catch (Exception e) {
			Util.logger(getClass()).error(e);
			promocionHistorial = new ArrayList<ItemDTO>();
		}
	}
	
	public void cargarHistorialSubCategoria(int idDep, int idCat, int idSubCat){		
		try {
			List<AttrSearch> listAttrSearch								= new ArrayList<AttrSearch>();
			List<CatItemDTO> listCatItem								= new ArrayList<CatItemDTO>();
			Boolean isTodasLasTiendas 									= this.isTodasLasTiendas();
			int idTienda 												= 0;
			Map<String,TblExistenciaItemTienda>  mapExistenciaItem		= new HashMap<String,TblExistenciaItemTienda>();
			
			this.tiendaSeleccionada = (this.nuevaListaSeleccion.getIdTienda()!=null )?this.nuevaListaSeleccion.getIdTienda().toString() :Constants.EMPTY ;
			
			if(isTodasLasTiendas){
				idTienda = GlobalDefines.ANALISIS_TIENDA_BASE_CONFIG;
			}else{
				idTienda = Integer.parseInt(getTiendaSeleccionada());
			}
			
			
			if(idDep > 0){
				listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_DEPTO, String.valueOf(idDep), AttrSearch.SEARCH_TYPE_EQUAL) );
			}
			
			if(idCat > 0){
				listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_CATEGORY, String.valueOf(idCat), AttrSearch.SEARCH_TYPE_EQUAL) );
			}
			
			if(idSubCat > 0){
				listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_SCATEGORY, String.valueOf(idSubCat), AttrSearch.SEARCH_TYPE_EQUAL) );
			}
			
			
			listCatItem =  MBUtil.getCatItemByAttr(serviceDynamicCatalogs, listAttrSearch);
			mapExistenciaItem = serviceAnalisis.getListaExistenciaItemByIdTienda(idTienda);
			
			TblExistenciaItemTienda existenciaItemInfo 			= null;
			promocionHistorial = new ArrayList<ItemDTO>();
			int i=0;
			for (CatItemDTO catItemDTO : listCatItem) {
				existenciaItemInfo = mapExistenciaItem.get(catItemDTO.getId());
				ItemDTO item = new ItemDTO();
				item.setSku(catItemDTO.getId());
				item.setFechaUltimaPromocion(existenciaItemInfo.getFechaUpromoStr());
				item.setTipoUltimaPromocion(existenciaItemInfo.getTipoUpromo());
				item.setPrecioUltimaPromocion(existenciaItemInfo.getPrecioUpromo().toString());
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMMMM-yyyy");
				Date iFecha=sdf.parse(existenciaItemInfo.getFechaUpromoStr());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(iFecha);
				
				calendar.add(Calendar.DAY_OF_YEAR, -2);
				Date iFecha1=calendar.getTime();
				String fecha1 = sdf.format(iFecha1);
				calendar.add(Calendar.DAY_OF_YEAR, -4);
				Date iFecha2=calendar.getTime();
				String fecha2 = sdf.format(iFecha2);
				
				String dTipo1="DESCUENTO 10P";
				String dTipo2="DESCUENTO 20P";
				String dTipo3="NORMAL";
				
				Double dPrecio = Double.valueOf(existenciaItemInfo.getPrecioUpromo().toString());
				Double dPrecio1 = dPrecio.doubleValue() - 2;
				Double dPrecio2 = dPrecio.doubleValue() - 1;
				
				item.setCampoFechaUno(fecha1);
				item.setCampoTipoUno(dTipo1);
				item.setCampoPrecioUno(dPrecio1.toString());
				
				item.setCampoFechaDos(fecha2);
				item.setCampoTipoDos(dTipo2);
				item.setCampoPrecioDos(dPrecio2.toString());
				
				promocionHistorial.add(item);
				i++;
			}
			
			
		} catch (Exception e) {
			Util.logger(getClass()).error(e);
		}
	}
	
	private void inicializarVariables(){
		
		
		this.idListaSeleccionada 		= null;
		this.itemDTODataModel 			= null;
		this.departamentoSeleccionado 	= Constants.EMPTY;
		this.categoriaSeleccionada 		= Constants.EMPTY;
		this.subcategoriaSeleccionada	= Constants.EMPTY;
		this.skuBuscar 					= Constants.EMPTY;
		this.skuAgregar					= Constants.EMPTY;
		this.nombreListaCreada			= Constants.EMPTY;
		this.userLogin					= (UsuarioDTO)Util.getSessionAttribute("userLoged");
		this.tiendaSeleccionada 		= null;
		this.edicion 					= Boolean.FALSE;
		this.nuevaListaSeleccion 		= new TblListaSeleccion();
		this.selectedItemList 			= new ArrayList<ItemDTO>();
		this.itemDTOList 				= new ArrayList<ItemDTO>();
		this.itemsLoad 					= new HashMap<String,ItemDTO>();
		this.root 						= null;
		
	}
	
	
	
	private void cargarListasSeleccion() throws Exception{
		UsuarioDTO user = this.userLogin;
		listaSeleccion = serviceAnalisis.getListaSeleccionbyUser(user);
		
	}
	
	
	private void cargarComboBox() throws Exception{

		setTiendasMap( MBUtil.cargarcombos(Constants.CAT_STORE,Constants.CAT_DESC_STORE,this.serviceDynamicCatalogs) );
		
		setDepartamentosMap( cargarComboDepartamentos() );
		
		setMecanicasMap( MBUtil.cargarcombosDescripcion( Constants.CAT_MECANICA , this.serviceDynamicCatalogs));

	}
	
	
	private void cargarComboBoxEdicion() throws Exception{
		
		Map<String, String> responseArray;
		
		responseArray= MBUtil.cargarcombos(Constants.CAT_STORE,Constants.CAT_DESC_STORE,this.serviceDynamicCatalogs);
		setTiendasMap(responseArray);

	}
	
	
	public void tiendaChangeEvent() throws Exception {  
		
		if(!isTodasLasTiendas()){
			nuevaListaSeleccion.setIdTienda(Integer.parseInt( getTiendaSeleccionada() ) );
		}
		loadItems();
		
	}
	
	
	/**
	 * LOAD  CARGA ITEMS PRODUCTOS
	 * @throws Exception
	 */
	private void  loadItems() throws Exception{
		
		List<AttrSearch> listAttrSearch								= new ArrayList<AttrSearch>();
		List<CatItemDTO> listCatItem								= new ArrayList<CatItemDTO>();
		Boolean isTodasLasTiendas 									= this.isTodasLasTiendas();
		int idTienda 												= 0;
		Map<String,TblExistenciaItemTienda>  mapExistenciaItem		= new HashMap<String,TblExistenciaItemTienda>();
		
		
		String idDepto 			= (!this.departamentoSeleccionado.equals(Constants.EMPTY) )?this.departamentoSeleccionado :Constants.EMPTY ;
		String idCategoria 		= (!this.categoriaSeleccionada.equals(Constants.EMPTY) )?this.categoriaSeleccionada :Constants.EMPTY ;
		String idSubcategoria 	= (!this.subcategoriaSeleccionada.equals(Constants.EMPTY) )?this.subcategoriaSeleccionada :Constants.EMPTY ;
		
		this.tiendaSeleccionada = (this.nuevaListaSeleccion.getIdTienda()!=null )?this.nuevaListaSeleccion.getIdTienda().toString() :Constants.EMPTY ;
		
		if(isTodasLasTiendas){
			idTienda = GlobalDefines.ANALISIS_TIENDA_BASE_CONFIG;
		}else{
			idTienda = Integer.parseInt(getTiendaSeleccionada());
		}
		
		
		if(!idDepto.equals(Constants.EMPTY)){
			listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_DEPTO, idDepto, AttrSearch.SEARCH_TYPE_EQUAL) );
		}
		
		if(!idCategoria.equals(Constants.EMPTY)){
			listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_CATEGORY, idCategoria, AttrSearch.SEARCH_TYPE_EQUAL) );
		}
		
		if(!idSubcategoria.equals(Constants.EMPTY)){
			listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_SCATEGORY, idSubcategoria, AttrSearch.SEARCH_TYPE_EQUAL) );
		}
		
		
		listCatItem =  MBUtil.getCatItemByAttr(serviceDynamicCatalogs, listAttrSearch);
		mapExistenciaItem = serviceAnalisis.getListaExistenciaItemByIdTienda(idTienda);
		
		this.itemDTOList = DTOUtil.generateItemDTOList(listCatItem,idTienda,isTodasLasTiendas,serviceAnalisis,mapExistenciaItem);
		for(ItemDTO item:this.itemDTOList ){
			this.itemsLoad.put(item.getSku(), item);
		}
		itemDTODataModel = new ItemDTODataModel(this.itemDTOList);
		
		DTOUtil.loadInfoFaltante(this.itemDTOList, serviceDynamicCatalogs);
		
		root = TreeUtil.createTree(this.itemDTOList);

		
	}
		 
	
	public void departamentoChangeEvent() throws Exception {  
		List<AttrSearch> listAttrSearch		= new ArrayList<AttrSearch>();
		this.categoriaSeleccionada 			= Constants.EMPTY;
		this.subcategoriaSeleccionada		= Constants.EMPTY;
		
		loadItems();
		cargarCategoriasId();
		
		listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_DEPTO, this.getDepartamentoSeleccionado(), AttrSearch.SEARCH_TYPE_EQUAL));
		
		this.listIdCategoria.retainAll( MBUtil.getCategoriesDeptoByAttr(serviceDynamicCatalogs, listAttrSearch));
		
		
		setCategoriasMap(cargarComboCategorias());
    }  
	
	
	 public void categoriaChangeEvent() throws Exception {  	        
			List<AttrSearch> listAttrSearch		= new ArrayList<AttrSearch>();
			this.subcategoriaSeleccionada 		= Constants.EMPTY;

			listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_CATEGORY, this.getCategoriaSeleccionada(), AttrSearch.SEARCH_TYPE_EQUAL));			
			
			setSubcategoriasMap ( DTOUtil.cargarComboSubcategorias( MBUtil.getSubcategoriasByAttr(serviceDynamicCatalogs, listAttrSearch) ) );
			
			loadItems();
			
	    }  
	 
	 
	 public void subcategoriahangeEvent() throws Exception{
		 loadItems();
	 }
	 
	 
	 private void cargarCategoriasId() throws Exception{
		 int idUser							= this.userLogin.getUserId();
		 this.listIdCategoria 				= MBUtil.getUserCategories(serviceDynamicCatalogs, idUser);
	 }
	 
	 
	 /**
	  * LOAD  CARGA 
	  * Carga el Combo de Departamentos, de acuerdo a los departamentos que puede ver el actual usuario conectado 
	  * @return
	  * @throws Exception
	  */
	private Map<String,String> cargarComboDepartamentos() throws Exception{
		 
		 List<AttrSearch> listAttrSearch	= new ArrayList<AttrSearch>();
		 List<String> listIdDepartamentos	= new ArrayList<String>();
		 List<DepartamentoDTO> listDeptos	= new ArrayList<DepartamentoDTO>();
		 List<String> idTemp 				= null;
		 
		 
		 /** Crea la lista de Atributos, con los ID de las categorias cargadas */
		 listAttrSearch = crearListaAttr(this.listIdCategoria, Constants.ATT_ID_CATEGORY, AttrSearch.SEARCH_TYPE_EQUAL_OR);
		 
		 /** obtiene la lista de Id de Departamentos de acuerdo a las Categorias del Usuario*/
		 listIdDepartamentos = MBUtil.getDeptoIdListByCatUserCategory(serviceDynamicCatalogs, listAttrSearch);
		 
		 /** Obtiene la lista de Departamentos uno por uno*/
		 for(String idDeptos:listIdDepartamentos){
			 idTemp = new ArrayList<String>();
			 idTemp.add(idDeptos);
			 
			 listAttrSearch = crearListaAttr(idTemp, Constants.ATT_ID, AttrSearch.SEARCH_TYPE_EQUAL);
			 listDeptos.addAll( MBUtil.getDepartamentoByAttr(serviceDynamicCatalogs, listAttrSearch) );
		 }
		
		 
		 return DTOUtil.cargarComboDepartamentos(listDeptos);
		 
	 }
	
	
	private Map<String,String> cargarComboCategorias() throws Exception{
		
		 List<AttrSearch> listAttrSearch		= new ArrayList<AttrSearch>();
		 List<CategoriaDTO> listaCategorias 	= new ArrayList<CategoriaDTO>();
		 List<String> idTemp 					= null;
		  
		 /** Crea la lista de Atributos, con los ID de las categorias cargadas */
		 
		 for(String idCategoria:this.listIdCategoria){
			 idTemp = new ArrayList<String>();
			 idTemp.add(idCategoria);
			 
			 listAttrSearch = crearListaAttr(idTemp, Constants.ATT_ID, AttrSearch.SEARCH_TYPE_EQUAL);
			 listaCategorias.addAll(  MBUtil.getCategoriasByAttr(serviceDynamicCatalogs, listAttrSearch) );
			 
		 }
		 
		 return DTOUtil.cargarComboCategorias(listaCategorias);
	} 
	
	
	
	private Map<String,String> cargarSubcategorias() throws Exception{
		
		 List<AttrSearch> listAttrSearch			= new ArrayList<AttrSearch>();
		 List<SubcategoriaDTO> listSubcategorias 	= new ArrayList<SubcategoriaDTO>();
		 List<String> idTemp 				= null;
		 
		 /** Crea la lista de Atributos, con los ID de las categorias cargadas */
		 for(String idCategoria:this.listIdCategoria){
			 idTemp = new ArrayList<String>();
			 idTemp.add(idCategoria);
			 
			 listAttrSearch = crearListaAttr(idTemp, Constants.ATT_ID_CATEGORY, AttrSearch.SEARCH_TYPE_EQUAL);
			 listSubcategorias.addAll( MBUtil.getSubcategoriasByAttr(serviceDynamicCatalogs, listAttrSearch) );
			
		 }
		 
		 return DTOUtil.cargarComboSubcategorias(listSubcategorias);
		
	}
	
	
	public void crearNuevaLista()throws Exception {
		this.inicializarVariables();
	}
	
	
	/**
	 * GUARDAR - SAVE-  Guarda la Informacion de Una Lista Nueva 
	 * @throws Exception
	 */
	public void guardarListaDeSeleccion() throws Exception { 
		
		List<TblListaSeleccionSku> listaItems   = null;
		int idUser								= this.userLogin.getUserId();
		int idLista 							= 0;		
		boolean existLista						= serviceAnalisis.validateExistListaSeleccion(idUser, this.nombreListaCreada);
		 
		if( !existLista ){
			if( this.selectedTableNodes != null){
				this.nuevaListaSeleccion.setIdUsr(idUser);
				this.nuevaListaSeleccion.setNombre(this.nombreListaCreada);
				if(this.isTodasLasTiendas()){
					this.nuevaListaSeleccion.setIdTienda(0);
				}else{
					this.nuevaListaSeleccion.setIdTienda(Integer.parseInt( getTiendaSeleccionada() ) ) ;
				}
	
				this.selectedItemList = TreeUtil.procesarInfoNodos(this.selectedTableNodes);
				
				idLista = serviceAnalisis.saveListaSeleccion(this.nuevaListaSeleccion);
				listaItems = DTOUtil.itemDTOToTblListaSeleccionSKU(this.selectedItemList);
				serviceAnalisis.saveListaSeleccionSKU(idLista, listaItems);
				
				init();
			}else{
				Messages.mensajeAlerta("No se ha seleccionado ningun elemento");
			}
		}else{
			Messages.mensajeAlerta("Ya existe una lista con ese nombre");
		}
    }  
	
	
	/**
	 * EDICION - EDITAR - EDIT - Carga la informacion de la Lista Selecionada
	 * @throws Exception
	 */
	public void viewInfoLista() throws Exception {
		 
		Map<String, String> params 						= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Integer idLista 								= Integer.parseInt( params.get("idLista") );
	    TblListaSeleccion lista 						= new TblListaSeleccion();
	    
	    lista = serviceAnalisis.getListaById(idLista);
	    this.tiendaSeleccionada = lista.getIdTienda().toString();	    
		this.setNombreListaCreada( lista.getNombre() ) ;
		this.idListaSeleccionada = lista.getIdLista();
		
		cargarComboBoxEdicion();
		cargarItems(idLista);
		setEdicion(Boolean.TRUE);   
		}
	
	

	
	
	public void eliminarLista() throws Exception {
		
		if(idListaSeleccionada != null ){
			serviceAnalisis.deleteListaItemsByIdLista(idListaSeleccionada);
			serviceAnalisis.deleteListaSeleccion(idListaSeleccionada);
			
			init();
		}else{
			Messages.mensajeAlerta("No ha Seleccionado una Lista");
		}
		
	}
	
	/**
	 * AGREGAR NUEVO PRODUCTO a lista existente
	 * @throws Exception
	 */
	public void agregarProducto() throws Exception{
		
		List<CatItemDTO> listCatItem								= new ArrayList<CatItemDTO>();
		List<AttrSearch> listAttrSearch								= new ArrayList<AttrSearch>();
		List<TblListaSeleccionSku> listaItems   					= new ArrayList<TblListaSeleccionSku>();
		int idLista 												= this.idListaSeleccionada;
		boolean isTodasLasTiendas 									= this.isTodasLasTiendas();
		Map<String,TblExistenciaItemTienda>  mapExistenciaItem		= new HashMap<String,TblExistenciaItemTienda>();
		int idTienda 												= Integer.parseInt( getTiendaSeleccionada() );
		List<ItemDTO> listItemTemp 									= null;
		
		
		if(TreeUtil.validationisNewLeaf(getSkuAgregar(), this.root)){
			
			listAttrSearch.addAll( DTOUtil.generateAttrList(Constants.ATT_ID_ITEM, getSkuAgregar(), AttrSearch.SEARCH_TYPE_EQUAL) );		
			
			listCatItem =  MBUtil.getCatItemByAttr(serviceDynamicCatalogs, listAttrSearch);
			
			if(listCatItem.size()>0){
				
			
				if(!isTodasLasTiendas){
					mapExistenciaItem = serviceAnalisis.getListaExistenciaItemByIdTienda(idTienda );
				}else{
					mapExistenciaItem = serviceAnalisis.getListaExistenciaItemByIdTienda( Integer.getInteger(Constants.TIENDA_BASE) );
				}
				
				listItemTemp = DTOUtil.generateItemDTOList(listCatItem,idTienda,isTodasLasTiendas,serviceAnalisis,mapExistenciaItem) ;
				
				
				
				try {
					listaItems = DTOUtil.itemDTOToTblListaSeleccionSKU(listItemTemp);
					serviceAnalisis.saveListaSeleccionSKU(idLista, listaItems);
					serviceAnalisis.updateFechaListaSeleccion(this.idListaSeleccionada);
					
					DTOUtil.loadInfoFaltante(listItemTemp, serviceDynamicCatalogs);
					this.itemDTOList.addAll(listItemTemp);
					TreeUtil.addNode(listItemTemp.get(Constants.ZERO), root);
				} catch (Exception e) {
					Util.logger(getClass()).error(e);
				}
				
				/**cargarItems(idLista);**/
			}else{
				Messages.mensajeAlerta("No Existe un Producto con ese SKU");
			}
		}else{
			Messages.mensajeAlerta("El producto ya existe en la lista de seleccion");
		}
		
	}
	
	
	/**
	 * ELIMINAR DELETE BORRAR
	 * 
	 */
	public void eliminarProducto(){
		List<String> listSKU 				= new ArrayList<String>();
		List<ItemDTO> removeItems 			= new ArrayList<ItemDTO>();
		int idLista 						= this.idListaSeleccionada;
		
		try {
			if(this.selectedTableNodes.length > Constants.ZERO ){
				removeItems = TreeUtil.procesarInfoNodos(selectedTableNodes);
				for(ItemDTO item :removeItems){
					listSKU.add(item.getSku());
				}
				
				serviceAnalisis.deleteSeleccionListByIdAndSKU(idLista, listSKU);
				serviceAnalisis.updateFechaListaSeleccion(idLista);
				
				for(TreeNode node : selectedTableNodes){
					TreeUtil.removeNode(node);
				}
				itemDTOList.removeAll(removeItems);
				Messages.mensajeSatisfactorio("Se Han Eliminado los Productos Seleccionados");
				
			}else{
				Messages.mensajeAlerta("No han seleccionado productos para eliminar");
			   }
		} catch (Exception e) {
			Messages.mensajeAlerta("No se pueden Eliminar lo productos Seleccionados");
		}
	}
		
	
	
	/**
	 * AUTOCOMPLETE
	 * @param query
	 * @return
	 */
	 public List<String> busquedaRapida(String query) {  
	        List<String> results = new ArrayList<String>(); 
	        
	        for(ItemDTO item :itemDTOList){
	        	if( item.getSku().contains(query) ){
	        		results.add(item.getSku() );
	        	}
	        }
	        
	        if(results.size()==0){
	        	results.add("No hay coincidencias");
	        }
	          
	        return results;  
	    }  
	 
	 
	 
	 /**
	  * BUSQUEDA FIND 
	  */
	 public void agregarProductoBusqueda(){
		 TreeNode selectNode 	= null;

		 selectNode = TreeUtil.findLeaf(this.skuBuscar, this.root);
		 selectNode.setSelected(Boolean.TRUE);
	 }
	
	
	/**
	 * Metodo que crea una Lista de AttrSearch de acuerdo a los valore que se le proporcione en la list de Strings.
	 * @param valoresAttr
	 * @param nombreAttr
	 * @param tipoDeBusqueda
	 * @return
	 */
	private List<AttrSearch> crearListaAttr(List<String> valoresAttr,String nombreAttr,int tipoDeBusqueda){
		
		AttrSearch attr 					= null;
		List<AttrSearch> listAttrSearch		= new ArrayList<AttrSearch>();
		
		for(String valorAttr:valoresAttr){
			attr = new AttrSearch();
			attr.setSearchType(tipoDeBusqueda);
			attr.setAttrName(nombreAttr);
			attr.setValue(valorAttr);
			listAttrSearch.add(attr);
		 }
		
		return listAttrSearch;
		
	}
	
	private Boolean isTodasLasTiendas(){
		Boolean result = Boolean.FALSE;
		
		if(getTiendaSeleccionada().equals(Constants.TODAS_LAS_TIENDAS) ||
				getTiendaSeleccionada().equals(Constants.EMPTY)){
			result = Boolean.TRUE;
		}
		
		
		
		return result;
	}
	
	
	private void cargarItems(Integer idLista) throws Exception{
		CatItemDTO itemTemp 							= null;
		
		itemDTOList = DTOUtil.tblListaSeleccionSKUToItemDTO( serviceAnalisis.getListaITemsByIdLista(idLista) );
			
			for(ItemDTO item: itemDTOList){
				itemTemp = DTOUtil.loadItemBySKU(item.getSku(),serviceDynamicCatalogs);
				item.setDescripcionSku( itemTemp.getCode());
				this.itemsLoad.put(item.getSku(), item);
			}
			
			DTOUtil.loadInfoFaltante(this.itemDTOList, serviceDynamicCatalogs);
			root = TreeUtil.createTree(this.itemDTOList);
			itemDTODataModel = new ItemDTODataModel(itemDTOList);
		
	}
	
	
	/**
	 * Agrega la informacion de Precio Promocion a todas las hojas del nodo
	 */
	public void changeInfoPrecioPromocion(){
		
		Map<String, String> params			= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String titulo						= params.get("titulo");
		String value 						= Constants.EMPTY;
		
		
		if(!titulo.equals("null")){
			TreeNode nodo = TreeUtil.findNode(titulo, root);
			if(nodo != null){
				value =  ( (ItemDTO)nodo.getData() ).getPrecioPromocion();
				( (ItemDTO)nodo.getData() ).setPrecioPromocion(Constants.EMPTY);
				TreeUtil.setInfoPrecioPromocion(nodo, value);
			}
		}
	}
	

	/**
	 * Agrega la informacion de Precio Promocion a todas las hojas del nodo
	 */
	public void changeInfoTipoPromocion(){
		
		Map<String, String> params			= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String titulo						= params.get("titulo");
		String value 						= Constants.EMPTY;
		
		if(!titulo.equals("null")){
			TreeNode nodo = TreeUtil.findNode(titulo, root);
			if(nodo != null){
				value =  ( (ItemDTO)nodo.getData() ).getTipoPromocion();
				( (ItemDTO)nodo.getData() ).setTipoPromocion(Constants.EMPTY);
				TreeUtil.setInfoTipoPromocion(nodo, value);
			}
		}
		
		
	}
	
	
	/**
	 * Agrega la informacion de Precio Promocion a todas las hojas del nodo
	 */
	public void changeInfoTipoPublicidad(){
		
		Map<String, String> params			= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String titulo						= params.get("titulo");
		List<String> values 				= new ArrayList<String>();
		
		if(!titulo.equals("null")){
			TreeNode nodo = TreeUtil.findNode(titulo, root);
			if(nodo != null){
				values =  ( (ItemDTO)nodo.getData() ).getTipoPublicidad();
				TreeUtil.setInfoTipoPublicidad(nodo, values);
			}
		
		}
		
	}
	
	
	
	/**
	 * GUARDAR - SAVE- EDICION Guarda la Informacion de  Lista Editada 
	 * @throws Exception
	 */
	public void guardarListaDeSeleccionEditada() throws Exception { 
		List<TblListaSeleccionSku> listaItems   	= null;
		
		itemDTOList = TreeUtil.procesarInfoTree(root);
		listaItems = DTOUtil.itemDTOToTblListaSeleccionSKU(itemDTOList);
		
		if( serviceAnalisis.deleteListaItemsByIdLista(this.idListaSeleccionada) ){
			serviceAnalisis.saveListaSeleccionSKU(this.idListaSeleccionada, listaItems);
		}
		
		
		
    }  
	
		
	/***************************************************************************************/
	/** *********************************** GET & SET *********************************** **/
	/***************************************************************************************/
	
	

	/**
	 * @return the itemDTODataModel
	 */
	public ItemDTODataModel getItemDTODataModel() {
		return itemDTODataModel;
	}

	/**
	 * @param itemDTODataModel the itemDTODataModel to set
	 */
	public void setItemDTODataModel(ItemDTODataModel itemDTODataModel) {
		this.itemDTODataModel = itemDTODataModel;
	}

	/**
	 * @return the selectedItemList
	 */
	public List<ItemDTO> getSelectedItemList() {
		return selectedItemList;
	}

	/**
	 * @param selectedItemList the selectedItemList to set
	 */
	public void setSelectedItemList(List<ItemDTO> selectedItemList) {
		this.selectedItemList = selectedItemList;
	}

	/**
	 * @return the listaSeleccion
	 */
	public List<TblListaSeleccion> getListaSeleccion() {
		return listaSeleccion;
	}

	/**
	 * @param listaSeleccion the listaSeleccion to set
	 */
	public void setListaSeleccion(List<TblListaSeleccion> listaSeleccion) {
		this.listaSeleccion = listaSeleccion;
	}

	/**
	 * @return the serviceAnalisis
	 */
	public ServiceAnalisis getServiceAnalisis() {
		return serviceAnalisis;
	}

	/**
	 * @param serviceAnalisis the serviceAnalisis to set
	 */
	public void setServiceAnalisis(ServiceAnalisis serviceAnalisis) {
		this.serviceAnalisis = serviceAnalisis;
	}

	/**
	 * @return the itemDTOList
	 */
	public List<ItemDTO> getItemDTOList() {
		return itemDTOList;
	}

	/**
	 * @param itemDTOList the itemDTOList to set
	 */
	public void setItemDTOList(List<ItemDTO> itemDTOList) {
		this.itemDTOList = itemDTOList;
	}


	/**
	 * @return the departamentoSeleccionado
	 */
	public String getDepartamentoSeleccionado() {
		return departamentoSeleccionado;
	}

	/**
	 * @param departamentoSeleccionado the departamentoSeleccionado to set
	 */
	public void setDepartamentoSeleccionado(String departamentoSeleccionado) {
		this.departamentoSeleccionado = departamentoSeleccionado;
	}

	/**
	 * @return the categoriaSeleccionada
	 */
	public String getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	/**
	 * @param categoriaSeleccionada the categoriaSeleccionada to set
	 */
	public void setCategoriaSeleccionada(String categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	/**
	 * @return the subcategoriaSeleccionada
	 */
	public String getSubcategoriaSeleccionada() {
		return subcategoriaSeleccionada;
	}

	/**
	 * @param subcategoriaSeleccionada the subcategoriaSeleccionada to set
	 */
	public void setSubcategoriaSeleccionada(String subcategoriaSeleccionada) {
		this.subcategoriaSeleccionada = subcategoriaSeleccionada;
	}

	/**
	 * @return the tiendasMap
	 */
	public Map<String, String> getTiendasMap() {
		return tiendasMap;
	}

	/**
	 * @param tiendasMap the tiendasMap to set
	 */
	public void setTiendasMap(Map<String, String> tiendasMap) {
		this.tiendasMap = tiendasMap;
	}

	/**
	 * @return the departamentosMap
	 */
	public Map<String, String> getDepartamentosMap() {
		return departamentosMap;
	}

	/**
	 * @param departamentosMap the departamentosMap to set
	 */
	public void setDepartamentosMap(Map<String, String> departamentosMap) {
		this.departamentosMap = departamentosMap;
	}

	/**
	 * @return the categoriasMap
	 */
	public Map<String, String> getCategoriasMap() {
		return categoriasMap;
	}

	/**
	 * @param categoriasMap the categoriasMap to set
	 */
	public void setCategoriasMap(Map<String, String> categoriasMap) {
		this.categoriasMap = categoriasMap;
	}

	/**
	 * @return the subcategoriasMap
	 */
	public Map<String, String> getSubcategoriasMap() {
		return subcategoriasMap;
	}

	/**
	 * @param subcategoriasMap the subcategoriasMap to set
	 */
	public void setSubcategoriasMap(Map<String, String> subcategoriasMap) {
		this.subcategoriasMap = subcategoriasMap;
	}


	/**
	 * @return the serviceDynamicCatalogs
	 */
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}


	/**
	 * @param serviceDynamicCatalogs the serviceDynamicCatalogs to set
	 */
	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}


	/**
	 * @return the listCategorias
	 */
	public List<CategoriaDTO> getListCategorias() {
		return listCategorias;
	}


	/**
	 * @param listCategorias the listCategorias to set
	 */
	public void setListCategorias(List<CategoriaDTO> listCategorias) {
		this.listCategorias = listCategorias;
	}


	/**
	 * @return the nombreListaCreada
	 */
	public String getNombreListaCreada() {
		return nombreListaCreada;
	}


	/**
	 * @param nombreListaCreada the nombreListaCreada to set
	 */
	public void setNombreListaCreada(String nombreListaCreada) {
		this.nombreListaCreada = nombreListaCreada;
	}


	/**
	 * @return the tiendaSeleccionada
	 */
	public String getTiendaSeleccionada() {
		if(tiendaSeleccionada == null){
			tiendaSeleccionada = Constants.EMPTY;
		}
		if(tiendaSeleccionada == Constants.TODAS_LAS_TIENDAS){
			tiendaSeleccionada = String.valueOf( GlobalDefines.ANALISIS_TIENDA_BASE_CONFIG);
		}
		return tiendaSeleccionada;
	}


	/**
	 * @param tiendaSeleccionada the tiendaSeleccionada to set
	 */
	public void setTiendaSeleccionada(String tiendaSeleccionada) {
		this.tiendaSeleccionada = tiendaSeleccionada;
	}


	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}


	/**
	 * @param edicion the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	/**
	 * @return the skuAgregar
	 */
	public String getSkuAgregar() {
		return skuAgregar;
	}

	/**
	 * @param skuAgregar the skuAgregar to set
	 */
	public void setSkuAgregar(String skuAgregar) {
		this.skuAgregar = skuAgregar;
	}

	/**
	 * @return the skuBuscar
	 */
	public String getSkuBuscar() {
		return skuBuscar;
	}

	/**
	 * @param skuBuscar the skuBuscar to set
	 */
	public void setSkuBuscar(String skuBuscar) {
		this.skuBuscar = skuBuscar;
	}
	
	
	/**
	 * @return the Venta Historica Config
	 */
	public boolean isConfigVentaHistorica() {
		boolean result = Boolean.FALSE;
		
		if(GlobalDefines.ANALISIS_VHISTORICAS_CONFIG.equals(Constants.TRUE_FILE_CONFIG)){
			result = Boolean.TRUE;
		}
		
		return result;
	}

	
	/**
	 * @return the Contribucion Config
	 */
	public boolean isConfigContribucion() {
		boolean result = Boolean.FALSE;
		
		if(GlobalDefines.ANALISIS_MCONTRIBUCION_CONFIG.equals(Constants.TRUE_FILE_CONFIG)){
			result = Boolean.TRUE;
		}
		
		return result;
	}

	
	/**
	 * @return the Existencia Config
	 */
	public boolean isConfigExistencia() {
		boolean result = Boolean.FALSE;
		
		if(GlobalDefines.ANALISIS_EXISTENCIAS_CONFIG.equals(Constants.TRUE_FILE_CONFIG)){
			result = Boolean.TRUE;
		}
		
		return result;
	}

	
	/**
	 * @return the Nuevo Config
	 */
	public boolean isConfigNuevo() {
		boolean result = Boolean.FALSE;
		
		if(GlobalDefines.ANALISIS_NUEVO_CONFIG.equals(Constants.TRUE_FILE_CONFIG)){
			result = Boolean.TRUE;
		}
		
		return result;
	}

	/**
	 * @return the Ultima Promo Config
	 */
	public boolean isConfigUPromo() {
		boolean result = Boolean.FALSE;
		
		if(GlobalDefines.ANALISIS_UPROMO_CONFIG.equals(Constants.TRUE_FILE_CONFIG)){
			result = Boolean.TRUE;
		}
		
		return result;
	}

	

	/**
	 * @return the Promo Anterior Config
	 */
	public boolean isConfigPromoAnterior() {
		boolean result = Boolean.FALSE;
		
		if(GlobalDefines.ANALISIS_PROMO_AANTERIOR_CONFIG.equals(Constants.TRUE_FILE_CONFIG)){
			result = Boolean.TRUE;
		}
		
		return result;
	}


	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @return the selectedTableNodes
	 */
	public TreeNode[] getSelectedTableNodes() {
		return selectedTableNodes;
	}

	/**
	 * @param selectedTableNodes the selectedTableNodes to set
	 */
	public void setSelectedTableNodes(TreeNode[] selectedTableNodes) {
		this.selectedTableNodes = selectedTableNodes;
	}

	/**
	 * @return the mecanicasMap
	 */
	public Map<String, String> getMecanicasMap() {
		return mecanicasMap;
	}

	/**
	 * @param mecanicasMap the mecanicasMap to set
	 */
	public void setMecanicasMap(Map<String, String> mecanicasMap) {
		this.mecanicasMap = mecanicasMap;
	}

	public List <ItemDTO> getPromocionHistorial() {
		return promocionHistorial;
	}

	public void setPromocionHistorial(List <ItemDTO> promocionHistorial) {
		this.promocionHistorial = promocionHistorial;
	}

	public Date getFechaIPU() {
		return fechaIPU;
	}

	public void setFechaIPU(Date fechaIPU) {
		this.fechaIPU = fechaIPU;
	}

	public Date getFechaFPU() {
		return fechaFPU;
	}

	public void setFechaFPU(Date fechaFPU) {
		this.fechaFPU = fechaFPU;
	}

	public Date getFechaIPD() {
		return fechaIPD;
	}

	public void setFechaIPD(Date fechaIPD) {
		this.fechaIPD = fechaIPD;
	}

	public Date getFechaFPD() {
		return fechaFPD;
	}

	public void setFechaFPD(Date fechaFPD) {
		this.fechaFPD = fechaFPD;
	}

	public ServiceReporte getServiceReporte() {
		return serviceReporte;
	}

	public void setServiceReporte(ServiceReporte serviceReporte) {
		this.serviceReporte = serviceReporte;
	}

	public List<ReporteVentasModel> getRepVentasModel() {
		return repVentasModel;
	}

	public void setRepVentasModel(List<ReporteVentasModel> repVentasModel) {
		this.repVentasModel = repVentasModel;
	}
}
