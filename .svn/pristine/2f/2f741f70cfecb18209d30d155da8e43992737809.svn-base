package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.business.services.ServiceCatEtiquetas;
import org.apache.log4j.Logger;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceFolleto;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaMedioDTO;
import com.adinfi.seven.persistence.dto.CatMedioDTO;
import com.adinfi.seven.persistence.dto.CatTipoMedioDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.ParametrosFolletoAndPrensaDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.SubCategoriasDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.persistence.dto.TreeCampanias;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.datamodel.CampanaDTODataModel;
import com.adinfi.seven.presentation.views.datamodel.CampanaMedioDataModel;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;

public class CampanaController implements Serializable {

	public enum CampanaConstants {
		ZERO(0), GET_ALL(1), FIND_BY_YEAR(2), FIND_BY_YEAR_AND_TYPE_CAMPANA(3), FIND_BY_TYPE_CAMPANA(
				4), ETIQUETA_DEFAUL(1);
		private CampanaConstants(int value) {
			this.value = value;
		}

		private final int value;

		public int getValue() {
			return value;
		}

	}

	private static final long serialVersionUID = 6577370041533258665L;
	private Logger LOG = Logger.getLogger(CampanaController.class);
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceCampana serviceCampana;
	private ServiceCatEtiquetas serviceCatEtiquetas;
	private ServiceFolleto serviceFolleto;
	private CampanaDTO current;
	private TblCampana campana = new TblCampana();
	private List<ArrayList<HojaFolleto>> createFolleto = new ArrayList<ArrayList<HojaFolleto>>();
	private List<HojaFolleto> espacioLst= new ArrayList<HojaFolleto>();
	private List<HojaFolleto> folletoLstVar= new ArrayList<HojaFolleto>();
	private HojaFolleto hojaFolleto= new HojaFolleto();
	
	private Integer indexHoja;
	private Integer indexEspacio;
	
	private Object[] createPrensa;
	private Date date1;
	private List<TipoCampanaDTO> lista;
	private CampanaDTODataModel campanaDataModel;
	private List<CampanaDTO> campanaDataModelFiltered;
	private CampanaMedioDataModel campanaMedioDataModel;
	private CampanaMedioDTO[] selectedCampanaMedioDTO;
	private CampanaDTO[] selectedCampanas;
	private boolean consulta = true;
	private boolean alta = false;
	private boolean edicion = false;
	private boolean detalle = false;
	private TreeNode raiz;
	private TreeNode selectedNode;
	private UsuarioDTO usuarioLogeado;
	private List<GenericItem> tipos;
	private ParametrosFolletoAndPrensaDTO parametros;
	private TblFolleto folletoObj;
	private CampanaMedioDTO medioDTOeditObj;

	/** Proximamente en el CampanaDTO **/
	private CatTipoMedioDTO tipo = new CatTipoMedioDTO();
	private String titulo;
	private CatMedioDTO medio;
	private UsuarioDTO responsable;
	private List<String> sucursal;
	private List<String> zona;
	private Integer espacioHoja;
	/**************************/

	/***** Combos de Folletos y prensas ********/
	private UsuarioDTO disenador;
	private SelectItem[] designerLst;
	private Map<String,String> responsableLst;

	private SelectItem[] subcategorias;
	private List<GenericItem> generalSubCategorias;
	private List<GenericItem> sucursalesLst;
	private List<GenericItem> sistemaVentas;
	private List<GenericItem> zonasLst;
	private List<String> sistemaVentasLst;
	/*****************************************/
	private boolean editMode= false;
	private boolean folleto = false;
	private boolean prensa = false;

	// ***************** Methods *************** //

	@PostConstruct
	private void init() {
		espacioHoja = CampanaConstants.ZERO.value;
		usuarioLogeado = Util.getSessionAttribute("userLoged");
		recreateCampanaMenu();
		recreateModelCampana(CampanaConstants.GET_ALL,
				CampanaConstants.ZERO.getValue(),
				CampanaConstants.ZERO.getValue());
		generateSheetsBooklet(espacioHoja);
		setCreatePrensa(new Object[10]);
		try {
			setSistemaVentas(MBUtil.genericSearch(Constants.ATT_ID,
					Constants.ATT_CODE, Constants.CAT_SISTEMA_VENTA,
					serviceDynamicCatalogs));
			List<AttrSearch> attrSearchLst = new ArrayList<AttrSearch>();
			AttrSearch attrSearch = new AttrSearch();
			attrSearch.setAttrName(Constants.ATT_ROLE);
			attrSearch.setValue(String.valueOf(Constants.DISEÑO));
			attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
			attrSearch.setExact(true);
			attrSearchLst.add(attrSearch);
			List<UsuarioDTO> designerLstAux = MBUtil.getUsuario(
					serviceDynamicCatalogs, attrSearchLst);
			designerLst= MBUtil.getSelectItems(designerLstAux, true);

			attrSearchLst = new ArrayList<AttrSearch>();
			sucursalesLst = MBUtil.genericSearch(Constants.ATT_ID,
					Constants.ATT_CODE, Constants.CAT_STORE,
					serviceDynamicCatalogs, attrSearchLst);
			
			zonasLst= MBUtil.genericSearch(Constants.ATT_ID,
					Constants.ATT_CODE, Constants.CAT_ZONA,
					serviceDynamicCatalogs, attrSearchLst);
		} catch (GeneralException e) {
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void recreateCampanaMenu() {
		List<VerTodas> listaVerTodas;
		List<CampaniaTreeRegs> listaCampaniaRegs;
		TreeCampanias tree;
		try {
			lista = MBUtil.getTipoCampanas(serviceDynamicCatalogs);
		} catch (Exception e) {
		}
		raiz = null;
		tree = null;
		listaCampaniaRegs = serviceCampana.todas();
		listaVerTodas = serviceCampana.showAllvT();
		tree = new TreeCampanias(listaCampaniaRegs, listaVerTodas);
		raiz = tree.getRaiz();
	}

	private void recreateModelCampana(CampanaConstants opc, int year,
			int typeCampana) {
		try {
			switch (opc) {
			case ZERO:
				break;
			case FIND_BY_YEAR:
				campanaDataModel = new CampanaDTODataModel(
						getListCampanaDTOByYear(year));
				break;
			case FIND_BY_YEAR_AND_TYPE_CAMPANA:
				campanaDataModel = new CampanaDTODataModel(
						getListCampanaDTOByYearAndTypeCampana(year, typeCampana));
				break;
			case FIND_BY_TYPE_CAMPANA:
				campanaDataModel = new CampanaDTODataModel(
						getListCampanaDTOByTypeCampana(typeCampana));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			selectedCampanas = null;
		}
	}

	private void recreateModelCampanaMedio(long IdCampana)
			throws GeneralException {
		campanaMedioDataModel = new CampanaMedioDataModel(
				getListCampanaMedioDTO(IdCampana));

	}

	public void deleteCampanas(ActionEvent e) {
		if(Integer.parseInt(usuarioLogeado.getRole())!=Constants.ADMINISTRADOR){
			Messages.mensajeErroneo("Solo un administrador puede eliminar campañas"
					+ selectedCampanas.length);
			return;
		}
		switch (selectedCampanas.length) {
		case 0:
			Messages.mensajeErroneo("Seleccione las Campañas a eliminar"
					+ selectedCampanas.length);
			break;
		default:
			try{
				for (CampanaDTO element : selectedCampanas) {
					LOG.error("Campaña a eliminar: "+element.getTblCampana().getIdCampana());
					removeCampanas(element);
				}
			}catch(Exception ex){
				LOG.error(ex.getMessage(),ex);
				Messages.mensajeErroneo("No se pudieron eliminar todas las campañas");
				break;
			}
			Messages.mensajeSatisfactorio("Se eliminaron las campañas seleccionadas");
			break;
		}
		recreateCampanaMenu();
		recreateModelCampana(CampanaConstants.GET_ALL,
				CampanaConstants.ZERO.getValue(),
				CampanaConstants.ZERO.getValue());
	}

	public void createViewCampanas(ActionEvent e) {
		current = new CampanaDTO();
		consulta = false;
		alta = true;
		detalle = false;
		espacioHoja = CampanaConstants.ZERO.value;
		setParametros(new ParametrosFolletoAndPrensaDTO(espacioHoja));
		generateSheetsBooklet(espacioHoja);
	}

	public void updateViewCampanas() {
		updateViewCampanas(null);
	}
	public void updateViewCampanas(ActionEvent e) {
		switch (selectedCampanas.length) {
		case 0:
			Messages.mensajeAlerta("Escoja  una opcion");
			LOG.info("Escoja  una opcion");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		case 1:
			setCurrent(selectedCampanas[0]);
			consulta = false;
			edicion = true;
			detalle = false;
			LOG.info(current);
			break;

		default:
			Messages.mensajeAlerta("Escoja solo una opcion");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		}
	}

	public void createMedio() {
		if(validateMedio()){
			hideAll();
			initCreateMedio();
			editMode= false;
			if (tipo.getId().intValue() == Constants.INT_FOLLETO) {
				folleto = true;
				espacioHoja= 1;
				generateSheetsBooklet(espacioHoja);
			} else if (tipo.getId().intValue() == Constants.INT_PRENSA) {
				prensa = true;
				espacioHoja= 1;
				generateSheetsBooklet(espacioHoja);
			}
		}else{
			Messages.mensajeAlerta("Favor de llenar todos los campos obligatorios");
		}
	}
	
	private boolean validateMedio() {
		boolean bRet= true;
		if(null==titulo||titulo.trim().length()==0){
			bRet= false;
		}
		if(null==medio.getId()||
				medio.getId().intValue()<=0){
			bRet= false;
		}
		if(null==tipo.getId()||
				tipo.getId().intValue()<=0){
			bRet= false;
		}
		if(null==responsable||
				null==responsable.getUserId()){
			bRet= false;
		}
		return bRet;
	}

	public void deleteMedio() throws GeneralException {
		if(selectedCampanaMedioDTO!=null){
			switch(selectedCampanaMedioDTO.length){
			case 0: Messages.mensajeAlerta("Escoja una opcion");return;
			case 1:
				deleteMedio(selectedCampanaMedioDTO[0]);
				recreateModelCampanaMedio(campana.getIdCampana());
				break;
			default:Messages.mensajeAlerta("Escoja solo una opcion");return;
			}
		}
	}
	
	private void deleteMedio(CampanaMedioDTO campanaMedioDTO) {
		try{
			TblCampanaMedio tblCampanaMedio = serviceCampana.getCampanaMedio(Integer.valueOf(campanaMedioDTO.getIdCampanaMedio()));
			serviceCampana.deleteFolleto(tblCampanaMedio.getIdFolleto().intValue());
			serviceCampana.deleteCampanaMedio(tblCampanaMedio);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
	}

	public void editMedio() throws GeneralException {
		hideAll();
		if(selectedCampanaMedioDTO!=null){
			switch(selectedCampanaMedioDTO.length){
			case 0: Messages.mensajeAlerta("Escoja una opcion");return;
			case 1:
				initEditMode(selectedCampanaMedioDTO[0]);
				break;
			default:Messages.mensajeAlerta("Escoja solo una opcion");return;
			}
		}
	}

	private void initEditMode(CampanaMedioDTO medioDTO) throws GeneralException {
		if (medioDTO.getTipoMedio().getId().intValue() == Constants.INT_FOLLETO) {
			folleto = true;
		} else if (medioDTO.getTipoMedio().getId().intValue() == Constants.INT_PRENSA) {
			prensa = true;
		}
		folletoObj= serviceCampana.getFolleto(medioDTO.getFolletoId());
		espacioHoja= folletoObj.getHojas();
		medioDTOeditObj= medioDTO;
		setTipo(medioDTO.getTipoMedio());
		setMedio(medioDTO.getMedio());
		initEditZonas(medioDTO);
		initEditSucursales(medioDTO);
		initEditSistemas(medioDTO);
		initEditHojas(medioDTO);
		titulo= medioDTO.getTitulo();
		editMode= true;
	}
	
	private void initEditZonas(CampanaMedioDTO medioDTO) throws GeneralException {
		zona= new ArrayList<String>();
		List<TblFolletoZona> zonas = serviceCampana.getZonasIdByFolletoId(medioDTO.getFolletoId());
		if(zonas!=null){
			for(TblFolletoZona z: zonas){
				zona.add(String.valueOf(z.getId().getIdZona()));
			}
		}
	}
	
	private void initEditSucursales(CampanaMedioDTO medioDTO) throws GeneralException {
		sucursal= new ArrayList<String>();
		List<TblFolletoTienda> tiendas = serviceCampana.getTiendasIdByFolletoId(medioDTO.getFolletoId());
		if(tiendas!=null){
			for(TblFolletoTienda tienda: tiendas){
				sucursal.add(String.valueOf(tienda.getId().getIdTienda()));
			}
		}
	}

	private void initEditSistemas(CampanaMedioDTO medioDTO) throws GeneralException {
		sistemaVentasLst= new ArrayList<String>();
		List<TblFolletoSistemaVenta> sistemasLst = serviceCampana.getSistemaVentaByFolleto(medioDTO.getFolletoId());
		if(sistemasLst!=null){
			for(TblFolletoSistemaVenta sistema: sistemasLst){
				sistemaVentasLst.add(String.valueOf(sistema.getId().getIdSistemaVenta()));
			}
		}
	}

	private void initEditHojas(CampanaMedioDTO medioDTO) throws GeneralException{
		try{
			createFolleto= new ArrayList<ArrayList<HojaFolleto>>();
			List<TblFolletoHojas> hojaLst = serviceCampana.getFolletoHojas(medioDTO.getFolletoId());
			if(hojaLst!=null){
				ArrayList<HojaFolleto> createFolletoHojas= null;
				short hojaIni=-1;
				int size=0;
				for(TblFolletoHojas hoja: hojaLst){
					size++;
					if(null!=hoja.getNumHoja() &&
							hoja.getNumHoja().shortValue()!=hojaIni){
						hojaIni= hoja.getNumHoja().shortValue();
						if(createFolletoHojas!=null){
							createFolleto.add(createFolletoHojas);
						}
						createFolletoHojas= new ArrayList<HojaFolleto>();
					}
					HojaFolleto hojaFolleto= new HojaFolleto();
					hojaFolleto.setCategory(MBUtil.getCategoria(serviceDynamicCatalogs, hoja.getIdCategory()));
					hojaFolleto.setDesigner(MBUtil.getUsuario(serviceDynamicCatalogs, hoja.getIdDesigner()));
					hojaFolleto.setHostedBuyer(MBUtil.getUsuario(serviceDynamicCatalogs, hoja.getIdUserInvitado()));
					List<SubCategoriasDTO> subCategoriaLst = getSubCategoryList(hojaFolleto
							.getCategory());
					hojaFolleto.setSubcategoryLst(MBUtil.getSelectItems(
							subCategoriaLst, true));
					hojaFolleto.setSubcategory(MBUtil.getSubCategoria(serviceDynamicCatalogs, hoja.getIdScategory()));

					getHojaFolleto().setCompradorLst(MBUtil.getSelectItems(getCompradorListMeth(hojaFolleto.getCategory(), hojaFolleto.getSubcategory()), true));

					hojaFolleto.setHojaId(hoja.getIdHoja());
					createFolletoHojas.add(hojaFolleto);
					if(hojaLst.size()==size){
						createFolleto.add(createFolletoHojas);
					}
				}
			}
		}catch(Exception e){
			throw new GeneralException(e);
		}
	}
	
	public void saveCampana() throws Exception {
		if(validateCampana()){
			createCampanas();
		}else{
			Messages.mensajeAlerta("Favor de llenar todos los campos obligatorios");
		}
	}

	private boolean validateCampana() {
		boolean bRet= true;
		if(null==current.getTblCampana().getNombre() ||
				current.getTblCampana().getNombre().isEmpty()){
			bRet= false;
		}
		if(null==current.getTipo().getId() ||
				current.getTipo().getId().intValue()<=0){
			bRet= false;
		}
		if(null==current.getTblCampana().getFechaFin()){
			bRet= false;
		}
		if(null==current.getTblCampana().getFechaInicio()){
			bRet= false;
		}
		return bRet;
	}

	public void createCampanas() {
		try {
			campana= new TblCampana();
			campana.setFechaCreacion(new Date());
			campana.setIdUsuarioCreacion(usuarioLogeado.getUserId());
			saveUpdateCampanas(campana, true);
		} catch (Exception e) {
			Messages.mensajeAlerta("Error al crear la campaña: " + e);
			LOG.info("Error al crear la campaña: " + e);
		}
	}
	
	private void saveUpdateCampanas(TblCampana camp, boolean create) throws GeneralException {
		try {
			campana.setNombre(current.getTblCampana().getNombre());
			campana.setIdTipoCampana(current.getTipo().getId());
			campana.setFechaFin(current.getTblCampana().getFechaFin());
			campana.setFechaInicio(current.getTblCampana().getFechaInicio());
			campana.setComentarios(current.getTblCampana().getComentarios());
			campana.setIdResponsable(current.getResponsable().getUserId());
			campana.setCatEtiquetas(serviceCatEtiquetas.getEtiquetaById(current.getEtiqueta().getId()));
		//	campana.setIdEtiqueta(((current.getEtiqueta() == null)
		//			?CampanaConstants.ETIQUETA_DEFAUL.getValue()
		//			:current.getEtiqueta().getId()));
			if (create) {
				serviceCampana.saveCampana(camp);
				Messages.mensajeSatisfactorio(Messages.getString("msg4"));
			} else {
				serviceCampana.updateCampana(camp);
			}
			showDetalle();
			recreateModelCampanaMedio(campana.getIdCampana());
		} catch (Exception e) {
			throw new GeneralException(e);
		} finally {
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
		}
	}
	
	public void editCampanas() {
		try {
			campana = serviceCampana.getCampana(current.getTblCampana()
					.getIdCampana());
			campana.setFechaModificacion(new Date());
			campana.setIdUsuarioModificacion(null != usuarioLogeado ? usuarioLogeado
					.getUserId() : null);
			saveUpdateCampanas(campana, false);
		} catch (Exception e) {
			Messages.mensajeErroneo("Error al guardar la informacion");
		}
	}

	private int getIdSistemaVentaDefaultGenerado(int folletoId){
		int retVal=0;
		try {
			List<TblFolletoSistemaVenta> lstSistVentFolleto = serviceFolleto.getFolletoSistemaVentaByFolleto(folletoId);
			for (TblFolletoSistemaVenta sisVenta : lstSistVentFolleto) {
				if(sisVenta.getSistemaDefault() == 'Y'){
					retVal = sisVenta.getId().getIdSistemaVenta();
					break;
				}
			}
		} catch (Exception e) {
			LOG.error(e);
			retVal=0;
		}
		if(retVal == 0){
			retVal = Integer.parseInt(getIdSistemaDefault());
		}
		return retVal;
	}
	
	private String getIdSistemaDefault(){
		List<GenericItem> svaDefId = null;
		String sistemaVentaDefault = "0";
		try {
			svaDefId = MBUtil.genericSearch(Constants.ATT_ID,	Constants.Tipo, 
					Constants.CAT_SISTEMA_VENTA, serviceDynamicCatalogs);

			for (GenericItem sisVen : svaDefId) {
				if( "Y".equals( sisVen.getValue() ) ){
					sistemaVentaDefault = sisVen.getId().toString();
					break;
				}
			}
			
			boolean buscarDefault=false;
			for (String idSelSisVen : sistemaVentasLst) {
				if( sistemaVentaDefault.equals(idSelSisVen) ){
					buscarDefault=true;
					break;
				}
			}
			
			if(!buscarDefault && sistemaVentasLst!=null && sistemaVentasLst.size() > 0){
				sistemaVentaDefault = sistemaVentasLst.get(0);
			}
		} catch (GeneralException e) {
			LOG.error(e);
		}
		
		return sistemaVentaDefault;
	}

	public void submitFolleto() {
		try {
			if(!validateMedioImpreso()){
				Messages.mensajeAlerta("Favor de llenar todos los campos obligatorios");
				return;
			}
			if(editMode){
				TblFolleto entityFolleto = saveFolleto();
				int idSistVentDef = getIdSistemaVentaDefaultGenerado(entityFolleto.getIdFolleto());
				updateTiendas(entityFolleto);
				updateZonas(entityFolleto);
				updateSistemas(entityFolleto, idSistVentDef);
				serviceCampana.saveFolletoHojas(createFolleto,
						entityFolleto.getIdFolleto(), idSistVentDef, getTipo().getId());
				serviceCampana.deleteHojas(entityFolleto.getIdFolleto(), espacioHoja.intValue());
				editMode= false;
				recreateModelCampanaMedio(campana.getIdCampana());
			}else{
				TblFolleto entityFolleto = saveFolleto();
				String idSistVentDef = getIdSistemaDefault();
				serviceCampana.saveFolletoTienda(sucursal,
						entityFolleto.getIdFolleto());
				serviceCampana.saveFolletoZona(zona, entityFolleto.getIdFolleto());
				serviceCampana.saveFolletoSistemaVenta(sistemaVentasLst,
						entityFolleto.getIdFolleto(), idSistVentDef);
				serviceCampana.saveFolletoHojas(createFolleto,
						entityFolleto.getIdFolleto(), Integer.valueOf(idSistVentDef), getTipo().getId());
				editMode= false;
				saveCampanaMedio(entityFolleto);
				recreateModelCampanaMedio(campana.getIdCampana());
			}
			showDetalle();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	private boolean validateMedioImpreso() {
		boolean publicidad= false;
		boolean bRet= true;
		if(null==espacioHoja||
				espacioHoja.intValue()<=0){
			bRet= false;
		}
		if(null==sucursal||
				sucursal.size()==0){
			bRet= false;
		}
		if(null==sistemaVentas||
				sistemaVentas.size()==0){
			bRet= false;
		}
		if(null==createFolleto){
			bRet= false;
		}else{
			for(ArrayList<HojaFolleto> hojaLst: createFolleto){
				boolean firstPage= true;
				for(HojaFolleto espacio: hojaLst){
					if(!firstPage && (null==espacio.getCategory()
										|| null==espacio.getCategory().getId()
										|| espacio.getCategory().getId().intValue()<=0)){
						return false;
					}
					if(!firstPage && (null==espacio.getDesigner())){
						publicidad= false;
					}
					firstPage= false;
				}
			}
		}
		if(bRet&&!publicidad){
			try {
				//UsuarioDTO publicidadDTO= MBUtil.getUsuarioByRole(serviceDynamicCatalogs, Constants.PUBLICIDAD);
				//SendMail.sendGenericEmail(publicidadDTO.getEmail(),"EMAIL_PUBLICISTA_TITLE","EMAIL_PUBLICISTA_DETAIL",titulo,campana.getNombre());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bRet;
	}

	private void updateSistemas(TblFolleto entityFolleto, int idSistVentDef) throws Exception {
		List<TblFolletoSistemaVenta> sistemasLst = serviceCampana.getSistemaVentaByFolleto(entityFolleto.getIdFolleto());
		if(sistemasLst!=null){
			for(TblFolletoSistemaVenta sistema: sistemasLst){
				serviceCampana.deleteSistemaVenta(sistema);
			}
		}
		serviceCampana.saveFolletoSistemaVenta(sistemaVentasLst,
				entityFolleto.getIdFolleto(), String.valueOf(idSistVentDef));
	}

	private void updateTiendas(TblFolleto entityFolleto) throws Exception {
		List<TblFolletoTienda> tiendasLst = serviceCampana.getTiendasIdByFolletoId(entityFolleto.getIdFolleto());
		if(tiendasLst!=null){
			for(TblFolletoTienda entity: tiendasLst){
				serviceCampana.deleteTienda(entity);
			}
		}
		serviceCampana.saveFolletoTienda(sucursal,entityFolleto.getIdFolleto());
	}
	
	private void updateZonas(TblFolleto entityFolleto) throws Exception {
		List<TblFolletoZona> zonaLst = serviceCampana.getZonasIdByFolletoId(entityFolleto.getIdFolleto());
		if(zonaLst!=null){
			for(TblFolletoZona entity: zonaLst){
				serviceCampana.deleteZona(entity);
			}
		}
		serviceCampana.saveFolletoZona(zona, entityFolleto.getIdFolleto());
	}

	private TblFolleto saveFolleto() throws GeneralException {
		TblFolleto entityFolleto = new TblFolleto();
		entityFolleto.setFechaFin(campana.getFechaFin());
		entityFolleto.setFechaInicio(campana.getFechaFin());
		entityFolleto.setHojas(espacioHoja);
		entityFolleto.setTblCampana(campana);
		if(editMode){
			entityFolleto.setIdFolleto(folletoObj.getIdFolleto());
		}
		serviceCampana.saveFolleto(entityFolleto);
		return entityFolleto;
	}

	private TblCampanaMedio getCampanaMedio() throws GeneralException {
		TblCampanaMedio entity = new TblCampanaMedio();
		entity.setTblCampana(campana);
		entity.setIdMedio(getMedio() != null ? getMedio().getId() : null);
		entity.setIdTipoMedio(getTipo() != null ? getTipo().getId() : null);
		entity.setResponsableId(getResponsable() != null ? getResponsable().getUserId() : null);
		entity.setTitulo(getTitulo());
		if(editMode){
			entity.setIdCampanaMedio(medioDTOeditObj.getIdCampanaMedio());
		}
		return entity;
	}

	private void saveCampanaMedio(TblFolleto entityFolleto)
			throws GeneralException {
		TblCampanaMedio entity = getCampanaMedio();
		entity.setIdFolleto(entityFolleto.getIdFolleto());
		serviceCampana.saveCampanaMedio(entity);
	}

	private void hideAll() {
		alta = false;
		prensa = false;
		folleto = false;
		consulta = false;
		detalle = false;
		edicion = false;
	}
	
	private void initCreateMedio(){
		sucursal= new ArrayList<String>();
		sistemaVentasLst= new ArrayList<String>();
		zona= new ArrayList<String>();
		createFolleto= new ArrayList<ArrayList<HojaFolleto>>();
	}

	public void showConsulta() {
		alta = false;
		prensa = false;
		folleto = false;
		consulta = true;
		detalle = false;
		edicion = false;
		folleto = false;
		prensa = false;
		medio= new CatMedioDTO();
		tipo= new CatTipoMedioDTO();
	}
	
	public void showDetalleAux(){
		try {
			campana = serviceCampana.getCampana(current.getTblCampana()
					.getIdCampana());
			recreateModelCampanaMedio(campana.getIdCampana());
			showDetalle();
		} catch (GeneralException e) {
			LOG.error(e.getMessage(),e);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}

	public void showDetalle() {
		alta = false;
		prensa = false;
		folleto = false;
		consulta = false;
		detalle = true;
		edicion = false;
		folleto = false;
		prensa = false;
		medio= new CatMedioDTO();
		tipo= new CatTipoMedioDTO();
	}

	private void removeCampanas(CampanaDTO campanaDTO) throws Exception {
		TblCampana campana = serviceCampana.getCampana(campanaDTO
				.getTblCampana().getIdCampana());
		//serviceCampana.deletePrensa(campana);
		serviceCampana.deleteFolleto(campana);
		
		List<TblCampanaMedio> medioLst= serviceCampana.getCampanaMedio(campana.getIdCampana());
		if(medioLst!=null){
			for(TblCampanaMedio medio: medioLst){
				serviceCampana.deleteCampanaMedio(medio);
			}
		}
		
		List<TblCampanaDetalle> detalleLst= serviceCampana.getCampanaDetalleByIdCampana(campana.getIdCampana());
		if(detalleLst!=null){
			for(TblCampanaDetalle detalle: detalleLst){
				serviceCampana.deleteCampanaDetalle(detalle);
			}
		}
		
		List<TblCampanaCategorias> categoriaLst= serviceCampana.getCampanaCategorias(campana.getIdCampana());
		if(categoriaLst!=null){
			for(TblCampanaCategorias categoria: categoriaLst){
				serviceCampana.deleteCampanaCategoria(categoria);
			}
		}
		
		List<TblCampanaAutorizacion> authLst= serviceCampana.getAutorizacionLst(campana.getIdCampana());
		if(authLst!=null){
			for(TblCampanaAutorizacion auth: authLst){
				serviceCampana.deleteCampanaAuth(auth);
			}
		}

		serviceCampana.deleteCampanaActividadByIdCampanaFull(campana.getIdCampana());
		serviceCampana.deleteCampana(campana);
	}

	public List<CampanaDTO> getListCampanaDTOByYear(int year) throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		listCampanaDTO = new ArrayList<>();
		CampanaDTO campanaDTO;
		List<PeriodoDTO> periodos = MBUtil.getPeriodos(serviceDynamicCatalogs);
		listCampanas = serviceCampana.getAllCampanasByYearAsc(year, periodos);
		for (TblCampana campana : listCampanas) {
			campanaDTO = llenadoCampanaDTO(campana);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	public List<CampanaMedioDTO> getListCampanaMedioDTO(long idCampana) {
		List<CampanaMedioDTO> list = new ArrayList<>();
		List<TblCampanaMedio> campanaMedios;
		list = new ArrayList<>();
		CampanaMedioDTO campanaMedioDTO;
		campanaMedios = serviceCampana.getCampanaMediosByIdCampana(idCampana);
		for (TblCampanaMedio element : campanaMedios) {
			campanaMedioDTO = llenadoCampanaMedioDTO(element);
			list.add(campanaMedioDTO);
		}
		return list;

	}

	public List<CampanaDTO> getListCampanaDTOByYearAndTypeCampana(int year,
			int typeCampana) throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		listCampanaDTO = new ArrayList<>();
		CampanaDTO campanaDTO;
		List<PeriodoDTO> periodos = MBUtil.getPeriodos(serviceDynamicCatalogs);
		listCampanas = serviceCampana.getListCampanaDTOByYearAndTypeCampana(
				year, typeCampana, periodos);
		for (TblCampana campana : listCampanas) {
			campanaDTO = llenadoCampanaDTO(campana);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	public List<CampanaDTO> getListCampanaDTOByTypeCampana(int typeCampana)
			throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		listCampanaDTO = new ArrayList<>();
		CampanaDTO campanaDTO;
		listCampanas = serviceCampana.getAllCampanaByTypeCampana(typeCampana);
		for (TblCampana campana : listCampanas) {
			campanaDTO = llenadoCampanaDTO(campana);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	private CampanaMedioDTO llenadoCampanaMedioDTO(TblCampanaMedio element) {
		CampanaMedioDTO medioDTO = new CampanaMedioDTO();
		try {
			medioDTO.setIdCampanaMedio(element.getIdCampanaMedio());
			CatMedioDTO medio = MBUtil.getMedio(serviceDynamicCatalogs, element.getIdMedio());
			
			List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
			AttrSearch attrSearch = new AttrSearch();
			attrSearch.setAttrName( Constants.ATT_ID_MEDIO );
			attrSearch.setValue( medio.getCode() );
			attrSearch.setSearchType( AttrSearch.SEARCH_TYPE_EQUAL );
			lstSearchAttrs.add( attrSearch );
			
			List<GenericItem> giTipoMedio = MBUtil.genericSearch(Constants.ATT_ID, Constants.ATT_CODE, Constants.CAT_TIPO_MEDIO, 
																 serviceDynamicCatalogs, lstSearchAttrs);
			CatTipoMedioDTO tipoMedio = new CatTipoMedioDTO();
			for (GenericItem item : giTipoMedio) {
				if( item.getId().intValue() == element.getIdTipoMedio().intValue() ){
					tipoMedio.setId(item.getId());
					tipoMedio.setCode(item.getValue());
					tipoMedio.setCatMedio(medio);
				}
			}
			medioDTO.setCampana(element.getTblCampana());
			
		//	medioDTO.setEtiqueta(MBUtil.getEtiqueta(serviceDynamicCatalogs,element.getTblCampana().getIdEtiqueta()));
			medioDTO.setTitulo(element.getTitulo());
			medioDTO.setTipoMedio(tipoMedio);
			medioDTO.setMedio(medio);
			medioDTO.setFolletoId(element.getIdFolleto());
			medioDTO.setPrensaId(element.getIdPrensa());
		} catch (Exception e) {
			LOG.error(e);
		}
		return medioDTO;
	}

	private CampanaDTO llenadoCampanaDTO(TblCampana campana) throws Exception {
		UsuarioDTO usuarioDTO;
		TipoCampanaDTO tipoDTO;
		EtiquetaDTO etiquetaDTO;
		CampanaDTO campanaDTO;
		PeriodoDTO periodoDTO = new PeriodoDTO();
		Calendar cal = Calendar.getInstance();
		campanaDTO = new CampanaDTO();
		usuarioDTO = MBUtil.getUsuario(serviceDynamicCatalogs,
				campana.getIdResponsable());
		tipoDTO = MBUtil.getTipoCampana(serviceDynamicCatalogs,
				campana.getIdTipoCampana());
//		etiquetaDTO = MBUtil.getEtiqueta(serviceDynamicCatalogs,
///				campana.getIdEtiqueta());
		periodoDTO.setFechaFin(campana.getFechaFin());
		periodoDTO.setFechaInicio(campana.getFechaInicio());
//		campanaDTO.setEtiqueta(etiquetaDTO);
		campanaDTO.setPeriodo(periodoDTO);
		campanaDTO.setTipo(tipoDTO);
		campanaDTO.setResponsable(usuarioDTO);
		campanaDTO.setTblCampana(campana);
		cal.setTime(campana.getFechaInicio());
		campanaDTO.setYear(String.valueOf(cal.get(Calendar.YEAR)));
		return campanaDTO;
	}

	public SelectItem[] getEtiquetas() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getEtiquetas(serviceDynamicCatalogs), false);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}

	public SelectItem[] getTipoCampanas() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getTipoCampanas(serviceDynamicCatalogs), false);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}

	public SelectItem[] getCategorias() {
		SelectItem[] items = null;

		try {
			items = MBUtil.getSelectItems(
					MBUtil.getCategorias(serviceDynamicCatalogs), true);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}

	public SelectItem[] getResponsables() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getUSers(serviceDynamicCatalogs), false);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}

	public SelectItem[] getMedios() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getMedios(serviceDynamicCatalogs), true);
			/*
			 * if (items != null && items.length > 0) { CatMedioDTO item =
			 * (CatMedioDTO) items[0].getValue();
			 * 
			 * List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
			 * AttrSearch attrSearch = new AttrSearch();
			 * attrSearch.setAttrName(Constants.ATT_ID_MEDIO);
			 * attrSearch.setValue(item.getCode());
			 * attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
			 * lstSearchAttrs.add(attrSearch); tipos= MBUtil.genericSearch(
			 * Constants.ATT_ID, Constants.ATT_CODE, Constants.CAT_TIPO_MEDIO,
			 * serviceDynamicCatalogs, lstSearchAttrs); }
			 */
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}

	public List<GenericItem> getTipos() {
		return tipos;
	}

	public List<Integer> getEspaciosHojas() {
		List<Integer> items = null;
		try {
			items = MBUtil.getHojasEspacios(serviceDynamicCatalogs);
		} catch (Exception e) {
			LOG.info(e);
		}

		return items;
	}

	public void changeTipoMedios(AjaxBehaviorEvent b) {
		try {
			List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
			AttrSearch attrSearch = new AttrSearch();
			attrSearch.setAttrName(Constants.ATT_ID_MEDIO);
			attrSearch.setValue(getMedio().getCode());
			attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
			lstSearchAttrs.add(attrSearch);
			tipos = MBUtil.genericSearch(Constants.ATT_ID, Constants.ATT_CODE,
					Constants.CAT_TIPO_MEDIO, serviceDynamicCatalogs,
					lstSearchAttrs);
		} catch (Exception e) {
			LOG.info("Error: " + e);
		}

	}

	public void EspacioHojaEvent(AjaxBehaviorEvent b) {
		generateSheetsBooklet(espacioHoja);
		setParametros(new ParametrosFolletoAndPrensaDTO(espacioHoja));

	}

	private void generateSheetsBooklet(Integer espacioHoja) {
		if (getCreateFolleto() != null && espacioHoja != null) {
			if (getCreateFolleto().size() > espacioHoja.intValue()) {
				for (int i = getCreateFolleto().size(); i > espacioHoja
						.intValue();) {
					getCreateFolleto().remove(--i);
				}
			} else if (getCreateFolleto().size() < espacioHoja.intValue()) {
				for (int i = getCreateFolleto().size(); i < espacioHoja
						.intValue(); i++) {
					ArrayList<HojaFolleto> hojaNueva= new ArrayList<HojaFolleto>();
					hojaNueva.add(new HojaFolleto());
					hojaNueva.add(new HojaFolleto());
					getCreateFolleto().add(hojaNueva);
				}
			}
		}
	}
	
	public void eliminarEspacioHoja() {
		try {
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String indexDelStr = params.get("indexDel");
			String indexEspacioDelStr = params.get("indexEspacioDel");
			Integer indexDel= new Integer(indexDelStr);
			Integer indexEspacioDel= new Integer(indexEspacioDelStr);
			
			ArrayList<HojaFolleto> espaciosHojaLst = createFolleto.get(indexDel.intValue());
			espaciosHojaLst.remove(indexEspacioDel.intValue());
			createFolleto.set(indexDel.intValue(), espaciosHojaLst);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
	}
	
	public void agregarEspacios() {
		try {
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String index = params.get("index");
			ArrayList<HojaFolleto> espaciosHojaLst = createFolleto.get(Integer.valueOf(index));
			if(espaciosHojaLst==null){
				espaciosHojaLst= new ArrayList<HojaFolleto>();
			}
			espaciosHojaLst.add(new HojaFolleto());
			createFolleto.set(Integer.valueOf(index), espaciosHojaLst);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
	}
	
	public void asignarEspacioHoja(){
		try {
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String indexHojaStr = params.get("indexHoja");
			String indexEspacioStr = params.get("indexEspacio");
			setIndexHoja(Integer.valueOf(indexHojaStr));
			setIndexEspacio(Integer.valueOf(indexEspacioStr));
			
			@SuppressWarnings("unchecked")
			ArrayList<HojaFolleto> hojasLst = (ArrayList<HojaFolleto>)createFolleto.get(getIndexHoja()).clone();
			setHojaFolleto(hojasLst.get(getIndexEspacio()));
			getHojaFolleto().setCompradorLst(
					MBUtil.getSelectItems(
							getCompradorListMeth(getHojaFolleto().getCategory(),getHojaFolleto().getSubcategory())
							, true
						)
					);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void guardarEspacioHoja(){
		try {
			ArrayList<HojaFolleto> hojasLst = (ArrayList<HojaFolleto>)createFolleto.get(getIndexHoja()).clone();
			hojasLst.set(getIndexEspacio(),getHojaFolleto());
			createFolleto.set(getIndexHoja(), hojasLst);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
	}
	
	public void subCategoriaEvent (ValueChangeEvent event) {
		try {
			SubCategoriasDTO subCategoryDTO= (SubCategoriasDTO)event.getNewValue();
			getHojaFolleto().setCompradorLst(null);
			getHojaFolleto().setHostedBuyer(new UsuarioDTO());
			getHojaFolleto().setCompradorLst(MBUtil.getSelectItems(getCompradorListMeth(getHojaFolleto().getCategory(),subCategoryDTO), true));
		} catch (GeneralException e) {
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void categoriaEvent(ValueChangeEvent event) {
		try {
			CategoriaDTO category = (CategoriaDTO) event.getNewValue();
			getHojaFolleto().setSubcategoryLst(null);
			getHojaFolleto().setCompradorLst(null);
			getHojaFolleto().setHostedBuyer(new UsuarioDTO());

			if(category!=null){
				getHojaFolleto().setCategory(category);
			}
			if(getHojaFolleto().getCategory()==null){
				return;
			}
			List<SubCategoriasDTO> subCategoriaLst= getSubCategoryList(getHojaFolleto().getCategory());
			getHojaFolleto().setSubcategoryLst(MBUtil.getSelectItems(
					subCategoriaLst, true));
			getHojaFolleto().setSubcategory(new SubCategoriasDTO());
			getHojaFolleto().setIncharge(new UsuarioDTO());
			
			getHojaFolleto().setCompradorLst(MBUtil.getSelectItems(getCompradorListMeth(getHojaFolleto().getCategory(), getHojaFolleto().getSubcategory()), true));
		} catch (GeneralException e) {
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	private List<UsuarioDTO> getCompradorListMeth(CategoriaDTO categoriaDTO, SubCategoriasDTO subCategoryDTO)
			throws Exception {
		List<UsuarioDTO> usuarioLst= new ArrayList<UsuarioDTO>();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		if(categoriaDTO!=null&&categoriaDTO.getId()!=null&&categoriaDTO.getId().intValue()>0){
			attrSearch.setAttrName(Constants.ATT_ID_CATEGORIA);
			attrSearch.setValue(String.valueOf(categoriaDTO.getId().intValue()));
			insertSearch.add(attrSearch);
			if(subCategoryDTO!=null&&subCategoryDTO.getId()!=null&&subCategoryDTO.getId().intValue()>0){
				AttrSearch attrAdd = new AttrSearch();
				attrAdd.setAttrName(Constants.ATT_ID_SCATEGORY);
				attrAdd.setValue(String.valueOf(subCategoryDTO.getId().intValue()));
				insertSearch.add(attrAdd);
				usuarioLst.addAll(MBUtil.getUsuarioByIdCategoria(serviceDynamicCatalogs, insertSearch));
			}
			
			insertSearch = new ArrayList<AttrSearch>();
			insertSearch.add(attrSearch);
			AttrSearch attrAdd2 = new AttrSearch();
			attrAdd2.setAttrName(Constants.ATT_ID_SCATEGORY);
			attrAdd2.setValue("");
			insertSearch.add(attrAdd2);
			usuarioLst.addAll(MBUtil.getUsuarioByIdCategoria(serviceDynamicCatalogs, insertSearch));
		}
		return usuarioLst;
	}

	private List<SubCategoriasDTO> getSubCategoryList(CategoriaDTO categoria)
			throws GeneralException {
		List<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_ID_CATEGORY);
		attrSearch.setValue(String.valueOf(categoria.getId()));
		attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
		lstSearchAttrs.add(attrSearch);
		List<GenericItem> subcategoriasSelect = MBUtil.genericSearch(
				Constants.ATT_ID, Constants.ATT_CODE, Constants.CAT_SCATEGORY,
				serviceDynamicCatalogs, lstSearchAttrs);
		List<SubCategoriasDTO> subCategoriaLst = null;
		if (subcategoriasSelect != null) {
			subCategoriaLst = new ArrayList<SubCategoriasDTO>();
			for (GenericItem item : subcategoriasSelect) {
				SubCategoriasDTO dto = new SubCategoriasDTO();
				dto.setId(item.getId());
				dto.setCode(item.getValue());
				subCategoriaLst.add(dto);
			}
		}
		return subCategoriaLst;
	}
	
	public void generateActivities(){
		try{
			if(selectedCampanaMedioDTO!=null){
				switch(selectedCampanaMedioDTO.length){
				case 0:Messages.mensajeAlerta("Escoja una opcion");return;
				case 1:
					generateActivities(selectedCampanaMedioDTO[0]);
					Messages.mensajeSatisfactorio("Actividades generadas exitosamente","Actividades generadas exitosamente");
					break;
				default:Messages.mensajeAlerta("Escoja solo una opcion");break;
				}
			}
		}catch(Exception e){
			Messages.mensajeErroneo("Ocurrio un error al generar las actividades","Ocurrio un error al generar las actividades");
			LOG.error(e.getMessage(), e);
		}
	}
	
	private void generateActivities(CampanaMedioDTO medioDTO) throws Exception{
		serviceCampana.generateActivities(current.getTblCampana().getIdCampana(),
				usuarioLogeado,
				serviceDynamicCatalogs,medioDTO);
	}

	public void onNodeSelect(NodeSelectEvent event) {
		String anio = null;
		String tipoCampania = null;
		TipoCampanaDTO tipoCampana = null;
		TblCampana tblCampania = null;
		if (event.getTreeNode().getData() instanceof CampaniaTreeRegs) {
			CampaniaTreeRegs campania = (CampaniaTreeRegs) event.getTreeNode()
					.getData();
			if (!isDetalle() || isAlta() || isEdicion()) {
				try {
					tblCampania = serviceCampana.getCampana(campania.getId());
					recreateModelCampanaMedio(campania.getId());
					current = llenadoCampanaDTO(tblCampania);
					alta = false;
					edicion = false;
					consulta = false;
					detalle = true;
					folleto = false;
					prensa = false;
					tipo = new CatTipoMedioDTO();
					medio = null;
				} catch (Exception e) {
					Messages.mensajeErroneo("Error", e);
					LOG.error("Error:" + e);
				}
			} else {
				try {
					tblCampania = serviceCampana.getCampana(campania.getId());
					recreateModelCampanaMedio(campania.getId());
					current = llenadoCampanaDTO(tblCampania);
					consulta = false;
					alta = false;
					edicion = false;
					detalle = true;
					folleto = false;
					prensa = false;
					tipo = new CatTipoMedioDTO();
					medio = null;
				} catch (Exception e) {
					Messages.mensajeErroneo("Error", e);
					LOG.error("Error:" + e);
				}
			}

		} else if (event.getTreeNode().getData() instanceof VerTodas) {
			VerTodas showAll = (VerTodas) event.getTreeNode().getData();
			LOG.info("*******************Año: " + showAll.getAnio()
					+ "*****************");
			if (isDetalle()) {
				alta = false;
				edicion = false;
				consulta = true;
				detalle = false;
				folleto = false;
				prensa = false;
				tipo = new CatTipoMedioDTO();
				medio = null;
				recreateModelCampana(CampanaConstants.FIND_BY_YEAR,
						showAll.getAnio(), CampanaConstants.ZERO.getValue());
			} else {
				alta = false;
				edicion = false;
				folleto = false;
				prensa = false;
				tipo = new CatTipoMedioDTO();
				medio = null;
				consulta = true;
				recreateModelCampana(CampanaConstants.FIND_BY_YEAR,
						showAll.getAnio(), CampanaConstants.ZERO.getValue());
			}
		} else if (event.getTreeNode().getData() instanceof String) {

			for (TipoCampanaDTO obj : lista) {
				if (obj != event.getTreeNode().getData()) {
					anio = event.getTreeNode().getData().toString();
					tipoCampania = event.getTreeNode().getParent().toString();
					tipoCampana = obj;
					break;
				}
			}
			/********* Lo que vallas a hacer *****************/
			if (tipoCampania.equals("raiz")) {
				if (anio.equals("Ver Todos")) {
					/*** Lo que vallas hacer con "Ver todas" ***/
					if (isDetalle()) {
						alta = false;
						edicion = false;
						consulta = true;
						detalle = false;
						folleto = false;
						tipo = new CatTipoMedioDTO();
						medio = null;
						prensa = false;
						recreateModelCampana(CampanaConstants.GET_ALL,
								CampanaConstants.ZERO.getValue(),
								CampanaConstants.ZERO.getValue());
					} else {
						alta = false;
						edicion = false;
						consulta = true;
						tipo = new CatTipoMedioDTO();
						medio = null;
						folleto = false;
						prensa = false;
						recreateModelCampana(CampanaConstants.GET_ALL,
								CampanaConstants.ZERO.getValue(),
								CampanaConstants.ZERO.getValue());
					}

				} else {
					/*** Lo que vallas hacer con los tipos de Campañas ***/
					if (isDetalle() || isAlta() || isEdicion() || isFolleto()
							|| isPrensa()) {
						alta = false;
						edicion = false;
						consulta = true;
						detalle = false;
						tipo = new CatTipoMedioDTO();
						medio = null;
						folleto = false;
						prensa = false;
						recreateModelCampana(
								CampanaConstants.FIND_BY_TYPE_CAMPANA,
								CampanaConstants.ZERO.getValue(),
								tipoCampana.getId());
					} else {
						alta = false;
						edicion = false;
						tipo = new CatTipoMedioDTO();
						medio = null;
						consulta = true;
						recreateModelCampana(
								CampanaConstants.FIND_BY_TYPE_CAMPANA,
								CampanaConstants.ZERO.getValue(),
								tipoCampana.getId());
					}
				}
			} else {
				if (isDetalle() || isAlta() || isEdicion()) {
					alta = false;
					edicion = false;
					consulta = true;
					tipo = new CatTipoMedioDTO();
					medio = null;
					detalle = false;
					recreateModelCampana(
							CampanaConstants.FIND_BY_YEAR_AND_TYPE_CAMPANA,
							Integer.valueOf(anio), tipoCampana.getId());
				} else {
					alta = false;
					edicion = false;
					consulta = true;
					folleto = false;
					tipo = new CatTipoMedioDTO();
					medio = null;
					prensa = false;
					recreateModelCampana(
							CampanaConstants.FIND_BY_YEAR_AND_TYPE_CAMPANA,
							Integer.valueOf(anio), tipoCampana.getId());
					/*** Lo que vallas ha hacer con el año y el tipo de Campaña ***/
					Messages.mensajeSatisfactorio(anio + " " + tipoCampania);
					LOG.info(3 + ":" + anio + " " + tipoCampana);
				}
			}
		}
	}

	public void onNodeExpand(NodeExpandEvent event) {
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
	}

	public void onNodeUnselect(NodeUnselectEvent event) {
	}

	public TreeNode getRaiz() {
		return raiz;
	}

	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

	/**
	 * @return the campanaDataModel
	 */
	public CampanaDTODataModel getCampanaDataModel() {
		return campanaDataModel;
	}

	/**
	 * @return the serviceCampana
	 */
	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}

	/**
	 * @param serviceCampana
	 *            the serviceCampana to set
	 */
	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	/**
	 * @return the serviceDynamicCatalogs
	 */
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	/**
	 * @param serviceDynamicCatalogs
	 *            the serviceDynamicCatalogs to set
	 */
	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	/**
	 * @return the date1
	 */
	public Date getDate1() {
		return date1;
	}

	/**
	 * @param date1
	 *            the date1 to set
	 */
	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	/**
	 * @return the createPrensa
	 */
	public Object[] getCreatePrensa() {
		return createPrensa;
	}

	/**
	 * @param createPrensa
	 *            the createPrensa to set
	 */
	public void setCreatePrensa(Object[] createPrensa) {
		this.createPrensa = new Object[createPrensa.length];
		System.arraycopy(createPrensa, 0, this.createPrensa, 0,
				createPrensa.length);
	}

	/**
	 * @return the selectedCampanas
	 */
	public CampanaDTO[] getSelectedCampanas() {
		return selectedCampanas;
	}

	/**
	 * @param selectedCampanas
	 *            the selectedCampanas to set
	 */
	public void setSelectedCampanas(CampanaDTO[] selectedCampanas) {
		this.selectedCampanas = new CampanaDTO[selectedCampanas.length];
		System.arraycopy(selectedCampanas, 0, this.selectedCampanas, 0,
				selectedCampanas.length);
	}

	public ServiceCatEtiquetas getServiceCatEtiquetas() {
		return serviceCatEtiquetas;
	}

	public void setServiceCatEtiquetas(ServiceCatEtiquetas serviceCatEtiquetas) {
		this.serviceCatEtiquetas = serviceCatEtiquetas;
	}

	public CampanaDTO getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(CampanaDTO current) {
		this.current = current;
	}

	/**
	 * @return the consulta
	 */
	public boolean isConsulta() {
		return consulta;
	}

	/**
	 * @param consulta
	 *            the consulta to set
	 */
	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}

	/**
	 * @return the alta
	 */
	public boolean isAlta() {
		return alta;
	}

	/**
	 * @param alta
	 *            the alta to set
	 */
	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	/**
	 * @return the baja
	 */
	public boolean isEdicion() {
		return edicion;
	}

	/**
	 * @param baja
	 *            the baja to set
	 */
	public void setEdicion(boolean baja) {
		this.edicion = baja;
	}

	/**
	 * @return the tipo
	 */
	public CatTipoMedioDTO getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(CatTipoMedioDTO tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the medio
	 */
	public CatMedioDTO getMedio() {
		return medio;
	}

	/**
	 * @param medio
	 *            the medio to set
	 */
	public void setMedio(CatMedioDTO medio) {
		this.medio = medio;
	}

	/**
	 * @return the campanaMedioDataModel
	 */
	public CampanaMedioDataModel getCampanaMedioDataModel() {
		return campanaMedioDataModel;
	}

	/**
	 * @return the selectedCampanaMedioDTO
	 */
	public CampanaMedioDTO[] getSelectedCampanaMedioDTO() {
		return selectedCampanaMedioDTO;
	}

	/**
	 * @param selectedCampanaMedioDTO
	 *            the selectedCampanaMedioDTO to set
	 */
	public void setSelectedCampanaMedioDTO(
			CampanaMedioDTO[] selectedCampanaMedioDTO) {
		this.selectedCampanaMedioDTO = new CampanaMedioDTO[selectedCampanaMedioDTO.length];
		System.arraycopy(selectedCampanaMedioDTO, 0,
				this.selectedCampanaMedioDTO, 0, selectedCampanaMedioDTO.length);
	}

	/**
	 * @return the detalle
	 */
	public boolean isDetalle() {
		return detalle;
	}

	/**
	 * @return the selectedNode
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode
	 *            the selectedNode to set
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	/**
	 * @param tipos
	 *            the tipos to set
	 */
	public void setTipos(List<GenericItem> tipos) {
		this.tipos = tipos;
	}

	public boolean isFolleto() {
		return folleto;
	}

	public boolean isPrensa() {
		return prensa;
	}

	/**
	 * @return the sucursal
	 */
	public List<String> getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(List<String> sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the espacioHoja
	 */
	public Integer getEspacioHoja() {
		return espacioHoja;
	}

	/**
	 * @param espacioHoja
	 *            the espacioHoja to set
	 */
	public void setEspacioHoja(Integer espacioHoja) {
		this.espacioHoja = espacioHoja;
	}

	/**
	 * @return the parametros
	 */
	public ParametrosFolletoAndPrensaDTO getParametros() {
		return parametros;
	}

	/**
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(ParametrosFolletoAndPrensaDTO parametros) {
		this.parametros = parametros;
	}

	public List<GenericItem> getSistemaVentas() {
		return sistemaVentas;
	}

	public void setSistemaVentas(List<GenericItem> sistemaVentas) {
		this.sistemaVentas = sistemaVentas;
	}

	public List<String> getSistemaVentasLst() {
		return sistemaVentasLst;
	}

	public void setSistemaVentasLst(List<String> sistemaVentasLst) {
		this.sistemaVentasLst = sistemaVentasLst;
	}

	public List<GenericItem> getGeneralSubCategorias() {
		return generalSubCategorias;
	}

	public void setGeneralSubCategorias(List<GenericItem> generalSubCategorias) {
		this.generalSubCategorias = generalSubCategorias;
	}

	public SelectItem[] getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(SelectItem[] subcategorias) {
		this.subcategorias = subcategorias;
	}

	public UsuarioDTO getDisenador() {
		return disenador;
	}

	public void setDisenador(UsuarioDTO disenador) {
		this.disenador = disenador;
	}

	public List<GenericItem> getSucursalesLst() {
		return sucursalesLst;
	}

	public void setSucursalesLst(List<GenericItem> sucursalesLst) {
		this.sucursalesLst = sucursalesLst;
	}

	public ServiceFolleto getServiceFolleto() {
		return serviceFolleto;
	}

	public void setServiceFolleto(ServiceFolleto serviceFolleto) {
		this.serviceFolleto = serviceFolleto;
	}

	public static class HojaFolleto {
		private CategoriaDTO category = new CategoriaDTO();
		private UsuarioDTO incharge = new UsuarioDTO();
		private SubCategoriasDTO subcategory = new SubCategoriasDTO();
		private UsuarioDTO designer = new UsuarioDTO();
		private UsuarioDTO hostedBuyer = new UsuarioDTO();
		private SelectItem[] subcategoryLst;
		private SelectItem[] compradorLst;
		private Integer hojaId= null;

		public CategoriaDTO getCategory() {
			return category;
		}

		public void setCategory(CategoriaDTO category) {
			this.category = category;
		}

		public UsuarioDTO getIncharge() {
			return incharge;
		}

		public void setIncharge(UsuarioDTO incharge) {
			this.incharge = incharge;
		}

		public SubCategoriasDTO getSubcategory() {
			return subcategory;
		}

		public void setSubcategory(SubCategoriasDTO subcategory) {
			this.subcategory = subcategory;
		}

		public UsuarioDTO getDesigner() {
			return designer;
		}

		public void setDesigner(UsuarioDTO designer) {
			this.designer = designer;
		}

		public UsuarioDTO getHostedBuyer() {
			return hostedBuyer;
		}

		public void setHostedBuyer(UsuarioDTO hostedBuyer) {
			this.hostedBuyer = hostedBuyer;
		}

		public SelectItem[] getSubcategoryLst() {
			return subcategoryLst;
		}

		public void setSubcategoryLst(SelectItem[] subcategoryLst) {
			this.subcategoryLst = subcategoryLst;
		}

		public Integer getHojaId() {
			return hojaId;
		}

		public void setHojaId(Integer hojaId) {
			this.hojaId = hojaId;
		}

		public SelectItem[] getCompradorLst() {
			return compradorLst;
		}

		public void setCompradorLst(SelectItem[] compradorLst) {
			this.compradorLst = compradorLst;
		}
	}

	public List<CampanaDTO> getCampanaDataModelFiltered() {
		return campanaDataModelFiltered;
	}

	public void setCampanaDataModelFiltered(
			List<CampanaDTO> campanaDataModelFiltered) {
		this.campanaDataModelFiltered = campanaDataModelFiltered;
	}

	public TblFolleto getFolletoObj() {
		return folletoObj;
	}

	public void setFolletoObj(TblFolleto folletoObj) {
		this.folletoObj = folletoObj;
	}

	public CampanaMedioDTO getMedioDTOeditObj() {
		return medioDTOeditObj;
	}

	public void setMedioDTOeditObj(CampanaMedioDTO medioDTOeditObj) {
		this.medioDTOeditObj = medioDTOeditObj;
	}

	public List<HojaFolleto> getEspacioLst() {
		return espacioLst;
	}

	public void setEspacioLst(List<HojaFolleto> espacioLst) {
		this.espacioLst = espacioLst;
	}

	public List<ArrayList<HojaFolleto>> getCreateFolleto() {
		return createFolleto;
	}

	public void setCreateFolleto(List<ArrayList<HojaFolleto>> createFolleto) {
		this.createFolleto = createFolleto;
	}

	public List<HojaFolleto> getFolletoLstVar() {
		return folletoLstVar;
	}

	public void setFolletoLstVar(List<HojaFolleto> folletoLstVar) {
		this.folletoLstVar = folletoLstVar;
	}

	public HojaFolleto getHojaFolleto() {
		return hojaFolleto;
	}

	public void setHojaFolleto(HojaFolleto hojaFolleto) {
		this.hojaFolleto = hojaFolleto;
	}

	public Integer getIndexHoja() {
		return indexHoja;
	}

	public void setIndexHoja(Integer indexHoja) {
		this.indexHoja = indexHoja;
	}

	public Integer getIndexEspacio() {
		return indexEspacio;
	}

	public void setIndexEspacio(Integer indexEspacio) {
		this.indexEspacio = indexEspacio;
	}

	public UsuarioDTO getResponsable() {
		return responsable;
	}

	public void setResponsable(UsuarioDTO responsable) {
		this.responsable = responsable;
	}

	public Map<String, String> getResponsableLst() {
		try {
			if(responsableLst==null){
				responsableLst= MBUtil.cargarcombos(Constants.CAT_RESP, serviceDynamicCatalogs);
			}
		} catch (Exception e) {
			LOG.info(e);
		}
		return responsableLst;
	}

	public void setResponsableLst(Map<String, String> responsableLst) {
		this.responsableLst = responsableLst;
	}

	public List<GenericItem> getZonasLst() {
		return zonasLst;
	}

	public void setZonasLst(List<GenericItem> zonasLst) {
		this.zonasLst = zonasLst;
	}

	public List<String> getZona() {
		return zona;
	}

	public void setZona(List<String> zona) {
		this.zona = zona;
	}

	public SelectItem[] getDesignerLst() {
		return designerLst;
	}

	public void setDesignerLst(SelectItem[] designerLst) {
		this.designerLst = designerLst;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}