package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaCategorias;
import com.adinfi.seven.business.domain.TblCampanaCategoriasId;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.business.domain.VerTodas;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaMedioDTO;
import com.adinfi.seven.persistence.dto.CatMedioDTO;
import com.adinfi.seven.persistence.dto.CatTipoMedioDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTODataModel;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.persistence.dto.TreeCampanias;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.datamodel.CampanaDTODataModel;
import com.adinfi.seven.presentation.views.datamodel.CampanaMedioDataModel;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.Util;

public class MBDetalleCampanaSeven implements Serializable {

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577370041533258665L;

	private Logger LOG = Logger.getLogger(MBDetalleCampanaSeven.class);
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceCampana serviceCampana;
	private CampanaDTO currentCat;

	private Date date1;
	private List<TipoCampanaDTO> lista;
	private CampanaDTODataModel campanaDataModel;
	
	private CampanaDTO[] selectedCampanas;
	private boolean consulta = true;
	private boolean alta = false;
	
	private boolean edicion = false;
	private boolean detalle = false;
	private boolean edicionCategorias = false;
	
	private TreeNode raiz;
	private TreeNode selectedNode;
	private UsuarioDTO usuarioLogeado;
	private List<String> selectedCategories;

	
	private Map<String, String> categorias;
	private List<CategoriaDTO> categoryList; 
	
	private CampanaMedioDataModel campanaMedioDataModel;
	private CampanaMedioDTO[] selectedCampanaMedioDTO;

	private CategoriaDTODataModel categoriaDTODataModel;
	
	List<CategoriaDTO> selectedCategoriaDTO;

	@PostConstruct
	private void init() {
		
		usuarioLogeado = Util.getSessionAttribute("userLoged");
		recreateCampanaMenu();
		recreateModelCampana(CampanaConstants.GET_ALL,
				CampanaConstants.ZERO.getValue(),
				CampanaConstants.ZERO.getValue());	
		
		this.cargarComboBox();
		this.selectCategoriasDefault(this.getCategorias());		
	}

	public void selectCategoriasDefault(Map<String,String> cats){
		
		selectedCategories = new ArrayList<String>();
		
		for (Map.Entry<String, String> entry : cats.entrySet())
		{
			selectedCategories.add(entry.getValue());
		}
	}
	
	private void cargarComboBox(){Map<String, String> responseArray;
		
		responseArray= MBUtil.cargarcombos(Constants.CAT_CATEGORY,this.serviceDynamicCatalogs);
		setCategorias(responseArray);

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
			case GET_ALL:
				campanaDataModel = new CampanaDTODataModel(getListCampanaDTO());
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

	public void deleteCategorias(ActionEvent e) {
		LOG.info("123456");
		switch (selectedCategoriaDTO.size()) {
		case 0:
			Messages.mensajeErroneo("Seleccione las Campanas a eliminar"
					+ selectedCampanas.length);
			LOG.info("Seleccione las Campanas a eliminar"
					+ selectedCampanas.length);
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		case 1:
			removeCategoria(selectedCategoriaDTO.get(0));
			Messages.mensajeSatisfactorio("Se elimino las categorias  seleccionada");
			LOG.info("Se eliminaron las categorias seleccionadas");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		default:		
			for (CategoriaDTO categoriaDTO : selectedCategoriaDTO) {
				removeCategoria(categoriaDTO);
			}
			
			Messages.mensajeSatisfactorio("Se eliminaron las campañas seleccionadas");
			LOG.info("Se eliminaron las campanas seleccionadas");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		}
	}

	public void createViewCampanas(ActionEvent e) {
		currentCat = new CampanaDTO();
		consulta = false;
		alta = true;
		detalle = false;

	}
	
	public void info(){
		LOG.info("Hola");
	}

	public void createCampanas() {
		try {
			LOG.info("NombreCampana="+currentCat.getTblCampana().getNombre());
			TblCampana camp = new TblCampana();
			camp.setIdResponsable(currentCat.getResponsable().getUserId());
		//	camp.setIdEtiqueta(((currentCat.getEtiqueta() == null) ? CampanaConstants.ETIQUETA_DEFAUL
		//			.getValue() : currentCat.getEtiqueta().getId()));
			camp.setIdTipoCampana(currentCat.getTipo().getId());
			camp.setFechaFin(currentCat.getTblCampana().getFechaFin());
			camp.setFechaInicio(currentCat.getTblCampana().getFechaInicio());
			camp.setComentarios(currentCat.getTblCampana().getComentarios());
			camp.setFechaCreacion(new Date());
			camp.setIdUsuarioCreacion(usuarioLogeado.getUserId());
			camp.setNombre(currentCat.getTblCampana().getNombre());
			camp.setIdTipoEvento(2);			
			TblCampana tblCampanaNueva = saveUpdateCampana(camp, true);						
			
			this.asociaCategorias(tblCampanaNueva);
			
			currentCat = new CampanaDTO();
			camp = null;
		} catch (Exception e) {
			Messages.mensajeAlerta("Error al crear la campaña: " + e);
			LOG.info("Error al crear la campaña: " + e);
		}
	}
	
	
	private void asociaCategorias(TblCampana camp){
		
		if (camp.getIdCampana() != 0) {
			camp.setTblCampanaCategoriases(this.getSelectedCategorias(camp));
			this.saveUpdateCampana(camp,false);
		} else {
			LOG.info("Campana NUll");
		}
		
	}
	
	public void getSelectedCategorias1(){
		
		for (CategoriaDTO category : categoryList) {
			LOG.info("categoryId="+ category.getId() );
			LOG.info("categoryCodigo="+ category.getCodigo() );
		}
	}
	
	public Set<TblCampanaCategorias> updateSelectedCategorias(TblCampana tblCampana){
								
		List<TblCampanaCategorias> setTblCampanaCategoriasesExistentes = 
				serviceCampana.getCampanaCategorias(tblCampana.getIdCampana());
								
		for (Iterator<TblCampanaCategorias> element = (Iterator<TblCampanaCategorias>) setTblCampanaCategoriasesExistentes
				.iterator(); element.hasNext();) {
			TblCampanaCategorias tblCampCat = (TblCampanaCategorias)element.next();
			serviceCampana.removeCampanaCategoriaById(tblCampCat.getId().getIdCategoria(), tblCampCat.getId().getIdCampana());
		}
					
		Set<TblCampanaCategorias> setTblCampanaCategoriases = new HashSet<TblCampanaCategorias>();
		
		for (Iterator<String> iterator = selectedCategories.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			LOG.info(id);

//			serviceCampana.updateCampanaCategorias(tblCampana.getIdCampana(), Integer.valueOf(id));			
		}
		
		this.selectedCategories.clear();
			
		
		return setTblCampanaCategoriases;
	}
	
	public Set<TblCampanaCategorias> getSelectedCategorias(TblCampana tblCampana){

	

		LOG.info("Entro"+selectedCategories.size());
		for (Iterator<String> iterator = selectedCategories.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			LOG.info(id);
		}		
		
		Set<TblCampanaCategorias> setTblCampanaCategoriases = new HashSet<TblCampanaCategorias>();
		
		for (Iterator<String> iterator = selectedCategories.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			LOG.info(id);

			TblCampanaCategorias tblCampanaCategorias = new TblCampanaCategorias(
					new TblCampanaCategoriasId(tblCampana.getIdCampana(), Integer.parseInt(id) ),
					tblCampana);
			setTblCampanaCategoriases.add(tblCampanaCategorias);
		}
				
		
		return setTblCampanaCategoriases;
	}
	
	private TblCampana saveUpdateCampana(TblCampana camp, boolean create) {
		TblCampana tblCampana = new TblCampana();
		try {
			if (campanaExist(camp)) {
				if (create) {					
					 tblCampana = this.serviceCampana.saveNewCampana(camp);
					Messages.mensajeSatisfactorio(Messages.getString("msg4"));
				} else {
					 tblCampana = this.serviceCampana.saveNewCampana(camp);
				}
			} else {
				Messages.mensajeErroneo("Error", Messages.getString("msg6"));
			}

		} catch (Exception e) {
			LOG.error(e);
		} 
		return tblCampana;
	}

	public void updateViewCategorias1(ActionEvent e) {
		switch (selectedCategoriaDTO.size()) {
		case 0:
			Messages.mensajeAlerta("Escoja  una opcion");
			LOG.info("Escoja  una opcion");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		case 1:								
			try {
				TblCampana campana = serviceCampana.getCampana(selectedCategoriaDTO.get(0).getIdCampana());
				currentCat = llenadoCampanaDTO(campana);	
			} catch (Exception e1) {
				LOG.error(e);
			}			
			setCurrentCat(currentCat);
			
			replaceCategoriasByCampana(currentCat);
			
			consulta = false;
			edicion = false;
			edicionCategorias = true;
			detalle = false;
			
			Messages.mensajeSatisfactorio(currentCat);
			LOG.info(currentCat);
			break;

		default:
			Messages.mensajeAlerta("Seleccione una opcion");
			LOG.info("Seleccione solo una opcion");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		}
	}
	
	public void updateViewCategorias(ActionEvent e) {					
			try {
				TblCampana campana = serviceCampana.getCampana(selectedCategoriaDTO.get(0).getIdCampana());
				currentCat = llenadoCampanaDTO(campana);	
			} catch (Exception ex) {
				LOG.error(ex);
			}			
			setCurrentCat(currentCat);
			
			replaceCategoriasByCampana(currentCat);
			
			consulta = false;
			edicion = false;
			edicionCategorias = true;
			detalle = false;
			
			Messages.mensajeSatisfactorio(currentCat);
			LOG.info(currentCat);		
	}
	
	
	
	private void replaceCategoriasByCampana(CampanaDTO camDto) {
		selectedCategories = new ArrayList<String>();			
//		Iterator<Integer> iter =  camDto.getCategorias().iterator();
		Iterator<Integer> iter =  null;
		while (iter.hasNext()) {
		  Integer idCat = iter.next(); 
		  selectedCategories.add(String.valueOf(idCat));
		}
}

	public void editCampanas() {
		try {
			TblCampana camp = serviceCampana.getCampana(currentCat.getTblCampana()
					.getIdCampana());
		
			LOG.info("CurrentCat="+currentCat.getTblCampana().getIdCampana());
			LOG.info("campId="+camp.getIdCampana());
			
			
			updateSelectedCategorias(camp);
			
			
			camp = null;
			currentCat = null;
			consulta = true;
			edicion = false;
			detalle = false;
		} catch (Exception e) {
			Messages.mensajeErroneo("Error al guardar la informacion");
			LOG.info("Error"+e);
		}
	}

	private void saveUpdateCampanas(TblCampana camp, boolean create) {
		try {
			if (campanaExist(camp)) {
				if (create) {
					serviceCampana.saveCampana(camp);
					Messages.mensajeSatisfactorio(Messages.getString("msg4"));
				} else {
					serviceCampana.updateCampana(camp);
				}
			} else {
				Messages.mensajeErroneo("Error", Messages.getString("msg6"));
			}

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
		}
	}

	public boolean campanaExist(TblCampana campana) {
		boolean validacion = false;
		try {
			validacion = serviceCampana.getCampanaByName(campana.getNombre());
		} catch (Exception e) {
			LOG.error(e);
		}
		return validacion;
	}
	
	private void removeCategoria(CategoriaDTO categoriaDTO) {
				
		try {
			LOG.info("IDCat="+categoriaDTO.getId());
			LOG.info("IDCamp="+categoriaDTO.getIdCampana());
			serviceCampana.removeCampanaCategoriaById(categoriaDTO.getId(), categoriaDTO.getIdCampana());
		} catch (Exception e) {
			LOG.error(e);
		}finally{			
			try {
				recreateModelCategorias(categoriaDTO.getIdCampana());
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}
	
	public List<CampanaDTO> getListCampanaDTO() throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<CampanaDTO>();
		List<TblCampana> listCampanas;
		CampanaDTO campanaDTO;
		listCampanas = serviceCampana.getCampanas(null, null, null).getCampanas();
		for (TblCampana campana : listCampanas) {
			campanaDTO = llenadoCampanaDTO(campana);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	public List<CampanaDTO> getListCampanaDTOByYear(int year) throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<CampanaDTO>();
		List<TblCampana> listCampanas;
		CampanaDTO campanaDTO;
		List<PeriodoDTO> periodos = MBUtil.getPeriodos(serviceDynamicCatalogs);
		listCampanas = serviceCampana.getAllCampanasByYearAsc(year, periodos);
		for (TblCampana campana : listCampanas) {
			campanaDTO = llenadoCampanaDTO(campana);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}


	public List<CampanaDTO> getListCampanaDTOByYearAndTypeCampana(int year,
			int typeCampana) throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<CampanaDTO>();
		List<TblCampana> listCampanas;
		
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
		List<CampanaDTO> listCampanaDTO = new ArrayList<CampanaDTO>();
		List<TblCampana> listCampanas;
	
		CampanaDTO campanaDTO;
		listCampanas = serviceCampana.getAllCampanaByTypeCampana(typeCampana);
		for (TblCampana campana : listCampanas) {
			campanaDTO = llenadoCampanaDTO(campana);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

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
		etiquetaDTO = MBUtil.getEtiqueta(serviceDynamicCatalogs,
				campana.getCatEtiquetas().getIdetiqueta());
		periodoDTO.setFechaFin(campana.getFechaFin());
		periodoDTO.setFechaInicio(campana.getFechaInicio());
		campanaDTO.setEtiqueta(etiquetaDTO);
		campanaDTO.setPeriodo(periodoDTO);
		campanaDTO.setTipo(tipoDTO);
		campanaDTO.setResponsable(usuarioDTO);
		campanaDTO.setTblCampana(campana);
		cal.setTime(campana.getFechaInicio());
		campanaDTO.setYear(String.valueOf(cal.get(Calendar.YEAR)));
//		campanaDTO.setCategorias(getCategoriasByCampana(campana));
		return campanaDTO;
	}
	
	private List<Integer> getCategoriasByCampana(TblCampana campana) {
		 
		List<Integer> categoryList = new ArrayList<Integer>();
				
		Iterator<TblCampanaCategorias> iter = (Iterator<TblCampanaCategorias>)campana.getTblCampanaCategoriases().iterator();
		while (iter.hasNext()) {
		  TblCampanaCategorias tblCampCat = iter.next(); 
		  categoryList.add(tblCampCat.getId().getIdCategoria());
		}
		return categoryList;
}

	public void onNodeSelect(NodeSelectEvent event) {
		String anio = null;
		String tipoCampania = null;
		TipoCampanaDTO tipoCampana = null;
		TblCampana tblCampania = null;
		if (event.getTreeNode().getData() instanceof CampaniaTreeRegs) {
			CampaniaTreeRegs campania = (CampaniaTreeRegs) event.getTreeNode()
					.getData();
			if (!isDetalle() || isAlta()) {
				LOG.info("=======1 Entra cuando toco ultimo Nivel");
				try {
					tblCampania = serviceCampana.getCampana(campania.getId());
					recreateModelCampanaMedio(campania.getId());
					recreateModelCategorias(tblCampania.getIdCampana());
					currentCat = llenadoCampanaDTO(tblCampania);					
					alta = false;
					edicion = false;
					consulta = false;
					detalle = true;
				} catch (Exception e) {
					Messages.mensajeErroneo("Error", e);
					LOG.error("Error:" + e);
				}
			} else {
				LOG.info("=======1 Entra cuando navego en ultimos niveles");
				try {
					tblCampania = serviceCampana.getCampana(campania.getId());
					recreateModelCampanaMedio(campania.getId());
					recreateModelCategorias(tblCampania.getIdCampana());
					currentCat = llenadoCampanaDTO(tblCampania);					
					consulta = false;
					alta = false;
					edicion = false;
					detalle = true;
				} catch (Exception e) {
					Messages.mensajeErroneo("Error", e);
					LOG.error("Error:" + e);
				}
			}

		} /**else if (event.getTreeNode().getData() instanceof VerTodas) {
			VerTodas showAll = (VerTodas) event.getTreeNode().getData();
			LOG.info("Ver Todas=====");

		} else if (event.getTreeNode().getData() instanceof String) {
			
			
			for (TipoCampanaDTO obj : lista) {
				if (obj != event.getTreeNode().getData()) {
					anio = event.getTreeNode().getData().toString();
					tipoCampania = event.getTreeNode().getParent().toString();
					tipoCampana = obj;
					break;
				}
			}
			
			if (tipoCampania.equals("raiz")) {
				if (anio.equals("Ver Todos")) {
					
					
					LOG.info("Click ver todos");
				}
			} else {
				
			}
		}**/
	}
	
	private void recreateModelCampanaMedio(long IdCampana) throws Exception {
		selectedCampanaMedioDTO = null;
		campanaMedioDataModel = new CampanaMedioDataModel(
				getListCampanaMedioDTO(IdCampana));
	}
	
	
	private void recreateModelCategorias(long idCampana) throws Exception {
		selectedCampanaMedioDTO = null;
		categoriaDTODataModel = new CategoriaDTODataModel(
				getCategoriaDTOList(idCampana));
	}
	
	private List<CategoriaDTO> getCategoriaDTOList(long idCampana) throws Exception {
		List<CategoriaDTO> list = new ArrayList<>();
		LOG.info("************************************");
		List<TblCampanaCategorias> setTblCampanaCategorias = serviceCampana.getCampanaCategorias(idCampana);
		
		for (TblCampanaCategorias tblCamCats : setTblCampanaCategorias) {
			LOG.info("============");
			LOG.info("TblCat============="+tblCamCats.getId().getIdCategoria());
			LOG.info("============");
			CategoriaDTO catDto = llenadoCatgoriaDTO(tblCamCats.getId().getIdCategoria());
			catDto.setCampanaMedioDTO(getListCampanaMedioDTO(idCampana).get(0));
			catDto.setIdCampana(idCampana);
			list.add(catDto);
		}						
		return list;
	}
	
	private CategoriaDTO llenadoCatgoriaDTO(Integer idCat) {
		CategoriaDTO catDto = new CategoriaDTO();
		
		try {
			catDto = MBUtil.getCategoria(serviceDynamicCatalogs, idCat);
			UsuarioDTO usDto = MBUtil.getUsuarioByIdCategoria(serviceDynamicCatalogs,idCat);
			catDto.setUsuDto(usDto);			
		} catch (Exception e) {
			LOG.error(e);
		} 
		return catDto;
	}
	
	public List<CampanaMedioDTO> getListCampanaMedioDTO(long idCampana) {
		List<CampanaMedioDTO> list = new ArrayList<CampanaMedioDTO>();
		
		CampanaMedioDTO campanaMedioDTO;
		List<TblCampanaMedio> tblCampanaMedioList = serviceCampana.getCampanaMedio(idCampana);
		for (TblCampanaMedio element : tblCampanaMedioList) {
			campanaMedioDTO = llenadoCampanaMedioDTO(element);
			list.add(campanaMedioDTO);
		}
		return list;
	}
	
	private CampanaMedioDTO llenadoCampanaMedioDTO(TblCampanaMedio element) {
		CampanaMedioDTO medioDTO = new CampanaMedioDTO();
		
		try {
			CatTipoMedioDTO tipoMedio = MBUtil.getTipoMediosByIdTipo(serviceDynamicCatalogs,element.getIdTipoMedio());
			CatMedioDTO medio = MBUtil.getMedio(serviceDynamicCatalogs, element.getIdMedio());

			medioDTO.setCampana(element.getTblCampana());
			medioDTO.setTipoMedio(tipoMedio);
			medioDTO.setMedio(medio);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
		}
		return medioDTO;
	}
	
	public SelectItem[] getEtiquetas() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getEtiquetas(serviceDynamicCatalogs), true);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}
	
	public SelectItem[] getResponsables() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getUSers(serviceDynamicCatalogs), true);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}
	
	public SelectItem[] getTipoCampanas() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getTipoCampanas(serviceDynamicCatalogs), true);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
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

	public List<String> getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(List<String> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}
	
	public Map<String, String> getCategorias() {
		return categorias;
	}

	public void setCategorias(Map<String, String> categorias) {
		this.categorias = categorias;
	}
	
	public CampanaMedioDataModel getCampanaMedioDataModel() {
		return campanaMedioDataModel;
	}

	public void setCampanaMedioDataModel(CampanaMedioDataModel campanaMedioDataModel) {
		this.campanaMedioDataModel = campanaMedioDataModel;
	}

	public CampanaMedioDTO[] getSelectedCampanaMedioDTO() {
		return selectedCampanaMedioDTO;
	}

	public void setSelectedCampanaMedioDTO(CampanaMedioDTO[] selectedCampanaMedioDTO) {
		this.selectedCampanaMedioDTO  = new CampanaMedioDTO[selectedCampanaMedioDTO.length];
		System.arraycopy(selectedCampanaMedioDTO, 0, this.selectedCampanaMedioDTO, 0,
				selectedCampanaMedioDTO.length);
	}

	public CategoriaDTODataModel getCategoriaDTODataModel() {
		return categoriaDTODataModel;
	}

	public void setCategoriaDTODataModel(CategoriaDTODataModel categoriaDTODataModel) {
		this.categoriaDTODataModel = categoriaDTODataModel;
	}
	
	public List<CategoriaDTO> getSelectedCategoriaDTO() {
		return selectedCategoriaDTO;
	}

	public void setSelectedCategoriaDTO(List<CategoriaDTO> selectedCategoriaDTO) {
		this.selectedCategoriaDTO = selectedCategoriaDTO;
	}
	public boolean isEdicionCategorias() {
		return edicionCategorias;
	}

	public void setEdicionCategorias(boolean edicionCategorias) {
		this.edicionCategorias = edicionCategorias;
	}
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public CampanaDTO getCurrentCat() {
		return currentCat;
	}

	public void setCurrentCat(CampanaDTO currentCat) {
		this.currentCat = currentCat;
	}

}