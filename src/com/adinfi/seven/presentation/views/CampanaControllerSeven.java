package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.hibernate.Hibernate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatFlujoAct;
import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.ProgramaDTO;
import com.adinfi.seven.business.domain.RelGrupoZonaCampana;
import com.adinfi.seven.business.domain.RelStoreCampana;
import com.adinfi.seven.business.domain.RelZonaCampana;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.business.domain.TblCampanaCategorias;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.business.domain.TblCampanaProgramasId;
import com.adinfi.seven.business.domain.VerTodas;
import com.adinfi.seven.business.domain.sort.CatGZoneSorter;
import com.adinfi.seven.business.domain.sort.CatStoreSorter;
import com.adinfi.seven.business.domain.sort.CatZoneSorter;
import com.adinfi.seven.business.services.ServiceCadenaAutorizacion;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatEstatus;
import com.adinfi.seven.business.services.ServiceCatEtiquetas;
import com.adinfi.seven.business.services.ServiceCatGZone;
import com.adinfi.seven.business.services.ServiceCatPrograma;
import com.adinfi.seven.business.services.ServiceCatStore;
import com.adinfi.seven.business.services.ServiceCatZone;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceSolicitudAutorizacion;
import com.adinfi.seven.business.services.ServiceTblActividades;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaProgramaDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO.RelObj;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.persistence.dto.TreeCampanias;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.datamodel.LazyCampanaDataModel;
import com.adinfi.seven.presentation.views.util.CampanaUtil;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;

public class CampanaControllerSeven implements Serializable {

	public enum CampanaConstants {
        ZERO(0),
        GET_ALL(1),
        FIND_BY_YEAR(2),
        FIND_BY_YEAR_AND_TYPE_CAMPANA(3),
        FIND_BY_TYPE_CAMPANA(4),
        ETIQUETA_DEFAUL(1);

        private CampanaConstants(int value) {
        	this.value = value;
        }

        private final int value;

        public int getValue() {
        	return value;
        }
    }

    private static final long serialVersionUID = 6577370041533258665L;
    private static final transient Logger LOG = Logger.getLogger(CampanaControllerSeven.class);
    private ServiceDynamicCatalogs serviceDynamicCatalogs;
    private ServiceCampana serviceCampana;
    private ServiceCatEtiquetas serviceCatEtiquetas;
    private ServiceSolicitudAutorizacion serviceSolicitudAutorizacion;
    private ServiceCatEstatus serviceCatEstatus;
    /**
     * Campana que esta siendo capturada.
     */
    private CampanaDTO current;
    private Date date1;
    private List<TipoCampanaDTO> listaTiposCampana;
    private List<CampanaDTO> lCampDTO;
    private List<CampanaDTO> campanaDataModelFiltered;

    private CampanaDTO[] selectedCampanas;
    private boolean consulta = true;
    private boolean alta = false;
    private boolean edicion = false;
    private boolean detalle = false;
    private boolean catgGuardada = false;
    private boolean altaPrograma= false;
    private boolean tituloEditarProg = false;

    private TreeNode raiz;
    private TreeNode selectedNode;
    private CatUsuarios usuarioLogeado;
    private SelectItem[] tipos;

    private Map<String, String> categorias;
    private List<CategoriaDTO> categoryList;
    private ServiceCadenaAutorizacion serviceCadenaAutorizacion; 
    private List<ProgramaDTO> programas;
    private Map<String, String> plazas;

    private CategoriaDM categoriaDM;
    private TblCampanaCategorias[] categoriasSelecteds;
    private SelectItem[] responsables;
    private CategoriaDTODM categoriaDTODM;
    private CategoriaDTO[] categoriaDTO;
    private String section = "campanias";
    private boolean campTreeSelect;
    private List<TblCampanaCategorias> lTblCampCategorias;
    private CampanaProgramaDTO currentProgramaDTO = new CampanaProgramaDTO();
    private CampanaProgramaNode selProgramaDTO = new CampanaProgramaNode();
    private List<CampanaProgramaDTO> lCampanaProgramaDTO;
    private CampanaProgramaDTO[] selCampanaProgramaDTO;
    
    private String selectedCampainOnGrid;
    private String selectedCampainType;
    
    @Deprecated
    private TreeNode programasTree;
    
    
    private List<CampanaProgramaNode> programasTableList;
    final long hoursInMillis = 60L * 60L * 1000L;

    private Map<String, String> mGruposZona;
    private List<CatGZone> mGruposZone;
    private Map<String, String> mZonas;
    private List<CatZone> mZones;
    private Map<String, String> mTiendas;
    private List<CatStore> mStores;
    private List<CatCategory> mCategories;
    
    private Map<Integer, CatStore> tiendasPorId = new LinkedHashMap<>();
    private Map<Integer, CatZone>  zonasPorId = new LinkedHashMap<>();
    private Map<Integer, CatGZone>  gZonasPorId = new LinkedHashMap<>();
    private Map<Integer, TipoCampanaDTO> tiposCampanaPorId = new LinkedHashMap<>();
    private Map<String, EtiquetaDTO> etiquetasPorId = new LinkedHashMap<>();
    private Map<Integer, PeriodoDTO> periodosPorId = new LinkedHashMap<>();
    
    private LazyDataModel<CampanaDTO> campanaDataModel;
    
    List<TblCampana> listCampanasCache = new ArrayList<>();

    public void getProgramasTable(){
        programasTableList = new ArrayList<>();
            if (lCampanaProgramaDTO != null){
                for(CampanaProgramaDTO campProgDTO : lCampanaProgramaDTO){
                    String strPrograma = "";
                    for (ProgramaDTO progDTO : getProgramas()) {
                            if (progDTO.getId() == campProgDTO.getIdPrograma()) {
                                    strPrograma = progDTO.getCode();
                                    break;
                            }
                    }

                    // TODO: CARGAR NOMBRES DE GRUPOS, ZONAS, TIENDAS
                    campProgDTO.getGrupoZonaLst().clear();
                    campProgDTO.getZonaLst().clear();
                    campProgDTO.getStoreLst().clear();
                    programasTableList.add(parseProgramaDTO(campProgDTO, strPrograma));
                }
            }
    }
    
    private List<CatFlujoAct> flujoActList;
    private ServiceTblActividades serviceTblActividades;

    public List<CatFlujoAct> getFlujoActList() {
        return flujoActList;
    }

    public void setFlujoActList(List<CatFlujoAct> flujoActList) {
        this.flujoActList = flujoActList;
    }

    public ServiceTblActividades getServiceTblActividades() {
        return serviceTblActividades;
    }

    public void setServiceTblActividades(ServiceTblActividades serviceTblActividades) {
        this.serviceTblActividades = serviceTblActividades;
    }

    private void initCombos(){
    	Long time = System.currentTimeMillis();
    	System.out.println("starting initCombos");
        List<CatGZone> grupos = MBUtil.cargarcomboGrupoZonas(serviceCatGZone);
        Collections.sort(grupos, new CatGZoneSorter());
	    mGruposZone = grupos;
        mCategories = MBUtil.cargarcomboCategorias(serviceCatCategory);
        System.out.println("initCombos: "+(System.currentTimeMillis()-time));
    }

    private CampanaProgramaNode parseProgramaDTO(CampanaProgramaDTO campProgDTO, String strPrograma){
        if (campProgDTO.getGrupoZonas() != null) {
            for (String value : campProgDTO.getGrupoZonas()) {
                CampanaUtil.addGrupoZona(Integer.valueOf(value), campProgDTO, gZonasPorId);
            }
        }
        List<String> zonas = new ArrayList<>();
        if (campProgDTO.getZonas() != null) {
                for (String value : campProgDTO.getZonas()) {
                       zonas.add(addZona(Integer.valueOf(value), campProgDTO));
                }
        }

        List<String>tiendas = new ArrayList<>();
        if (campProgDTO.getTiendas() != null) {
                for (String value : campProgDTO.getTiendas()) {
                        tiendas.add(addTienda(Integer.valueOf(value), campProgDTO));
                }
        }

        List<String> categos = new ArrayList<>();
        StringBuilder categoriasLocal = new StringBuilder();
        for (String catStr : campProgDTO.getCategoriaSelect()) {
            for (int i = 0; i < mCategories.size(); i++) {
                CatCategory categoria = mCategories.get(i);
                if (String.valueOf(categoria.getIdCategory()).equals(catStr)) {
                    categoriasLocal.append(categoria.getCode());
                    categos.add(categoria.getCode());
                    if (i < mCategories.size() - 1) {
                            categoriasLocal.append(",");
                    }
                    break;
                }
            }
        }
        
        
        
        CampanaProgramaNode campanaNode = new CampanaProgramaNode(campProgDTO.getIdPrograma(),
                        strPrograma,
                        categoriasLocal.toString(),
                        "",
                        campProgDTO.getIngreso(),
                        campProgDTO.getSencillo()== 1 ? "Compartido" : "Sencillo",
                        campProgDTO.getGrupoZonaStr(),
                        campProgDTO.getZonaStr(),
                        campProgDTO.getStoreStr(),
                        tiendas,zonas,categos,campProgDTO.getEtapa());
        
        return campanaNode;
    }
    
    public void addProgram(){
            if (lCampanaProgramaDTO == null){
                    lCampanaProgramaDTO = new ArrayList<>();
            }
            if (validateProg()){
                   if(currentProgramaDTO.getGrupoZonas()!=null){
                            currentProgramaDTO.setGrupoZonaLst(new ArrayList<DisenosDTO.RelObj>());
                          /*   for(String value:currentProgramaDTO.getGrupoZonas()){
                                    addGrupoZona(Integer.valueOf(value), currentProgramaDTO);
                            } */
                    }
                    if(currentProgramaDTO.getZonas()!=null){
                            currentProgramaDTO.setZonaLst(new ArrayList<DisenosDTO.RelObj>());
                            for(String value:currentProgramaDTO.getZonas()){
                                    addZona(Integer.valueOf(value), currentProgramaDTO);
                            }
                    }
                    if(currentProgramaDTO.getTiendas()!=null){
                            currentProgramaDTO.setStoreLst(new ArrayList<DisenosDTO.RelObj>());
                            for(String value:currentProgramaDTO.getTiendas()){
                                    addTienda(Integer.valueOf(value), currentProgramaDTO);
                            }
                    }
                    String descPrograma = "";
                    for (ProgramaDTO p : programas){
                        if(p.getId() == currentProgramaDTO.getIdPrograma()){
                            descPrograma = p.getCode();
                            break;
                        }
                    }
                    programasTableList.add(parseProgramaDTO(currentProgramaDTO, descPrograma));
                    lCampanaProgramaDTO.add(currentProgramaDTO);
                    currentProgramaDTO = new CampanaProgramaDTO();
            } /*else {
                    Messages.mensajeAlerta("No se puede agregar el Programa, existe informacion sin capturar", "No se puede agregar el Programa, existe informacion sin capturar");
            } */
    }
    
    public void preRemoveProgram(){
    	RequestContext requestContext = RequestContext.getCurrentInstance();  
        requestContext.execute("confEliProg.show();");
    } 

    public void removeProgramRow(){
        CampanaProgramaNode toDelete = null;
        for (CampanaProgramaNode n : programasTableList){
            if (n.getIdPrograma() == selProgramaDTO.getIdPrograma()){
                toDelete = n;
                break;
            }
        }
        if (toDelete != null){
            programasTableList.remove(toDelete);
            removeFromMainLCampanaProgramasDTO(toDelete);
            removeProgram(current.getTblCampana().getIdCampana(), toDelete.getIdPrograma());
            Messages.mensajeSatisfactorio("Programa eliminado","Programa Eliminado");
        }
    }
    
    private void removeFromMainLCampanaProgramasDTO(CampanaProgramaNode n){
        CampanaProgramaDTO cp = null;
        for (CampanaProgramaDTO c : lCampanaProgramaDTO){
            if (c.getIdPrograma() == n.getIdPrograma()){
                cp = c;
                break;
            }
        }
        if (cp != null){
            lCampanaProgramaDTO.remove(cp);
        }
    }
    
    public void removeProgram(){
        TreeNode toDelete = null;
        for (TreeNode node : programasTree.getChildren()){
            CampanaProgramaNode pro = (CampanaProgramaNode)node.getData();
            if(pro.getIdPrograma() == selProgramaDTO.getIdPrograma()){
                toDelete = node;
                break;
            }
        }
        if(toDelete != null){
            programasTree.getChildren().remove(toDelete);
//            Messages.mensajeSatisfactorio("Programa eliminado","Programa Eliminado");
//            RequestContext.getCurrentInstance().update(":editarProgramas:treePrgs");
        }

    }

    public void editProgram(){
        
        for (CampanaProgramaDTO campProgDTO : lCampanaProgramaDTO){
            if (campProgDTO.getIdPrograma() == selProgramaDTO.getIdPrograma()){
                currentProgramaDTO = campProgDTO;
                changeGrupoZonas(currentProgramaDTO.getGrupoZonas());
                changeZonas(currentProgramaDTO.getZonas());
                break;
            }
        }
    	
    }
    
    public void testRadio(){
    	
    	LOG.info(currentProgramaDTO.getSencillo());
    	
    } 

    public void saveProgram(){
        if (validateProg()){
            for (CampanaProgramaDTO campProgDTO : lCampanaProgramaDTO){
                if (campProgDTO.getIdPrograma() == currentProgramaDTO.getIdPrograma()){
                    campProgDTO.setCategoriaSelect(currentProgramaDTO.getCategoriaSelect());
                    campProgDTO.setIngreso(currentProgramaDTO.getIngreso());
                    campProgDTO.setSencillo(currentProgramaDTO.getSencillo());
                    //campProgDTO.setPlazaSelect(currentProgramaDTO.getPlazaSelect());
                    campProgDTO.setGrupoZonas(currentProgramaDTO.getGrupoZonas());
                    campProgDTO.setZonas(currentProgramaDTO.getZonas());
                    campProgDTO.setTiendas(currentProgramaDTO.getTiendas());
                    campProgDTO.getGrupoZonaLst().clear();
                    campProgDTO.getZonaLst().clear();
                    campProgDTO.getStoreLst().clear();
                    if(currentProgramaDTO.getGrupoZonas()!=null){
                            for(String value:currentProgramaDTO.getGrupoZonas()){
                                    CampanaUtil.addGrupoZona(Integer.valueOf(value), campProgDTO, gZonasPorId);
                            }
                    }
                    if(currentProgramaDTO.getZonas()!=null){
                            for(String value:currentProgramaDTO.getZonas()){
                                    addZona(Integer.valueOf(value), campProgDTO);
                            }
                    }
                    if(currentProgramaDTO.getTiendas()!=null){
                            for(String value:currentProgramaDTO.getTiendas()){
                                    addTienda(Integer.valueOf(value), campProgDTO);
                            }
                    }
                    CampanaProgramaNode updatedNode = parseProgramaDTO(campProgDTO, campProgDTO.getDescripcion());
                    updateTableView(updatedNode);

                    break;
                }
                
            } 
            currentProgramaDTO = new CampanaProgramaDTO();
        } else {
                Messages.mensajeAlerta("No se puede agregar el Programa, existe informacion sin capturar", "No se puede agregar el Programa, existe informacion sin capturar");
        }
    }
    
    private void updateTableView(CampanaProgramaNode updatedNode){
        for(CampanaProgramaNode node : programasTableList){
            if (node.idPrograma == updatedNode.idPrograma){
                node.setCategos(updatedNode.categos);
                node.setGrupoZonasStr(updatedNode.grupoZonasStr);
                node.setIngreso(updatedNode.ingreso);
                node.setStrCategoria(updatedNode.strCategoria);
                node.setStrEsSencillo(updatedNode.strEsSencillo);
                node.setStrPlazas(updatedNode.strPlazas);
                node.setStrPrograma(updatedNode.strPrograma);
                node.setTiendas(updatedNode.tiendas);
                node.setZonas(updatedNode.zonas);
                node.setZonasStr(updatedNode.zonasStr);
            }
        }
    }

    public CampanaProgramaNode getSelProgramaDTO() {
            return selProgramaDTO;
    }

    public List<CampanaProgramaDTO> getlCampanaProgramaDTO() {
            return lCampanaProgramaDTO;
    }

    public void setlCampanaProgramaDTO(List<CampanaProgramaDTO> lCampanaProgramaDTO) {
            this.lCampanaProgramaDTO = lCampanaProgramaDTO;
    }

    public CampanaProgramaDTO[] getSelCampanaProgramaDTO() {
            return selCampanaProgramaDTO;
    }

    public void setSelCampanaProgramaDTO(CampanaProgramaDTO[] selCampanaProgramaDTO) {
            this.selCampanaProgramaDTO = selCampanaProgramaDTO.clone();
    }

    public void setSelProgramaDTO(CampanaProgramaNode selProgramaDTO) {
            this.selProgramaDTO = selProgramaDTO;
    }

    public List<CampanaProgramaNode> getProgramasTableList() {
        return programasTableList;
    }

    public void setProgramasTableList(List<CampanaProgramaNode> programasTableList) {
        this.programasTableList = programasTableList;
    }

    public void setProgramasTree(TreeNode programasTree) {
            this.programasTree = programasTree;
    }

    public CampanaProgramaDTO getCurrentProgramaDTO() {
            return currentProgramaDTO;
    }

    public void setCurrentProgramaDTO(CampanaProgramaDTO currentProgramaDTO) {
            this.currentProgramaDTO = currentProgramaDTO;
    }

    public void subMenuCampanias(){
            section = "campanias";
            init();
    }

    public void subMenuCategorias(){
            section = "categorias";
            campTreeSelect = false;
            init();
    }

    public List<CampanaDTO> getlCampDTO() {
            return lCampDTO;
    }

    public void setlCampDTO(List<CampanaDTO> lCampDTO) {
            this.lCampDTO = lCampDTO;
    }

    @PostConstruct
    public void init() {
    	Long time = System.currentTimeMillis();
    	System.out.println("Starting init...");
        tipos = new SelectItem[1];
        tipos[0] = new SelectItem("", "-------");
        usuarioLogeado = Util.getSessionAttribute("userLoged");
        ExecutorService executor = Executors.newFixedThreadPool(15);
        Runnable r = new Runnable() { @Override public void run() {
        	initCombos();
        }};
        executor.execute(r);
        r = new Runnable() { @Override public void run() {
        	recreateCampanaMenu();
        }};
        executor.execute(r);
        llenarCaches(executor);
        executor.shutdown();
        while (!executor.isTerminated()){}
        campanaDataModel = new LazyCampanaDataModel(serviceCampana, tiposCampanaPorId, periodosPorId, gZonasPorId);
        campanaDataModelFiltered = lCampDTO;
        cambiarEstado(Estado.CONSULTA);
        catgGuardada = false;
        
	    System.out.println("init: "+(System.currentTimeMillis()-time));
    }

    private void llenarCaches(ExecutorService executor) {
        try {
            Runnable r = new Runnable() { @Override public void run() {
				try {
					Long time = System.currentTimeMillis();
					List<CatStore> tiendas = serviceCatStore.getCatStoreList();
					System.out.println("getCatStoreList: "+(System.currentTimeMillis()-time));
					for (CatStore tienda : tiendas) {
    	                tiendasPorId.put(tienda.getIdStore(), tienda);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }};
            executor.execute(r);
            
            r = new Runnable() { @Override public void run() {
				try {
					Long time = System.currentTimeMillis();
					List<CatZone> zonas = serviceCatZone.getCatZoneList();
					System.out.println("getCatZoneList: "+(System.currentTimeMillis()-time));
			        for(CatZone z : zonas){
			        	    zonasPorId.put(z.getIdZone(),z);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
            }};
            executor.execute(r);
	        
            r = new Runnable() { @Override public void run() {
				try {
					Long time = System.currentTimeMillis();
					List<CatGZone> gzonas = serviceCatGZone.getCatGZoneList();
					System.out.println("getCatGZoneList: "+(System.currentTimeMillis()-time));
			        for(CatGZone gz : gzonas){
			        	    gZonasPorId.put(gz.getIdGrupoZona(),gz);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
            }};
            executor.execute(r);
	        
//            r = new Runnable() { @Override public void run() {
//				try {
//					Long time = System.currentTimeMillis();
//					listCampanasCache = serviceCampana.getAllCampanaOrderByYearAsc(1);
//					System.out.println("getAllCampanaOrderByYearAsc: "+(System.currentTimeMillis()-time));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//            }};
//            executor.execute(r);
            
            r = new Runnable() { @Override public void run() {
				try {
					Long time = System.currentTimeMillis();
					List<TipoCampanaDTO> tipoCampanas = MBUtil.getTipoCampanas(serviceDynamicCatalogs);
					System.out.println("getTipoCampanas: "+(System.currentTimeMillis()-time));
			        for (TipoCampanaDTO tipo : tipoCampanas) {
			                tiposCampanaPorId.put(tipo.getId(), tipo);				
			        }
			        listaTiposCampana = new ArrayList<>(tiposCampanaPorId.values());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }};
            executor.execute(r);
            
            r = new Runnable() { @Override public void run() {
				try {
					Long time = System.currentTimeMillis();
					List<PeriodoDTO> periodos = MBUtil.getPeriodos(serviceDynamicCatalogs);
					System.out.println("getPeriodos: "+(System.currentTimeMillis()-time));
			        for (PeriodoDTO periodo : periodos) {
			                periodosPorId.put(periodo.getId(), periodo);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
            }};
            executor.execute(r);
            
	        for (int i = 1; i <= 3; i++){
	            EtiquetaDTO et = Constants.getEtiquetaCode(i);
	            etiquetasPorId.put(et.getDescription(), et);
	        }
	        
        } catch (Exception e) {
                LOG.error(e);
        }
    }

    private void recreateCampanaMenu() {
    	Long time = System.currentTimeMillis();
    	System.out.println("starting recreateCampanaMenu");
    	final List<VerTodas> listaVerTodas = new ArrayList<VerTodas>();
        final List<CampaniaTreeRegs> listaCampaniaRegs = new ArrayList<CampaniaTreeRegs>();
        TreeCampanias tree;

        Long time2 = System.currentTimeMillis();
    	List<VerTodas> l2 = serviceCampana.showAllvT();
    	System.out.println("showAllvT: "+(System.currentTimeMillis()-time2));
    	listaVerTodas.addAll(l2);
    
    	Long time3 = System.currentTimeMillis();
    	List<CampaniaTreeRegs> l = serviceCampana.todas();
    	System.out.println("todas(): "+(System.currentTimeMillis()-time2));
    	listaCampaniaRegs.addAll(l);
        
        tree = new TreeCampanias(listaCampaniaRegs, listaVerTodas);
        raiz = tree.getRaiz();
        System.out.println("recreateCampanaMenu: "+(System.currentTimeMillis()-time));
    }

    private void recreateModelCampana(CampanaConstants opc, int year, int typeCampana) {
        try {
                switch (opc) {
                case ZERO:
                        break;
                case GET_ALL:
                        lCampDTO = getListCampanaDTO();
                        break;
                case FIND_BY_YEAR:
                        lCampDTO = getListCampanaDTOByYear(year);
                        break;
                case FIND_BY_YEAR_AND_TYPE_CAMPANA:
                        lCampDTO = getListCampanaDTOByYearAndTypeCampana(year, typeCampana);
                        System.out.println("total de campanas: " + lCampDTO.size());
                        if(lCampDTO.size()>0){
                        	this.selectedCampanas = new CampanaDTO[]{lCampDTO.get(0)};
                        }
                        break;
                case FIND_BY_TYPE_CAMPANA:
                        lCampDTO = getListCampanaDTOByTypeCampana(typeCampana);
                        break;
                default:
                        break;
                }
                if(!lCampDTO.isEmpty()){
                    Collections.sort(lCampDTO, Collections.reverseOrder());
                }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
                selectedCampanas = null;
        }
    }

    public void preDeleteCampanas(){
            if (null == selectedCampanas || selectedCampanas.length == 0){
                    Messages.mensajeAlerta("Debe seleccionar uno o mas registros", "Debe seleccionar uno o mas registros");
            } else {
                    RequestContext requestContext = RequestContext.getCurrentInstance();  
                    requestContext.execute("confEliCamp.show();");
            }
    }

    public void deleteCampanas() {
        String msj = "";
        for (CampanaDTO element : selectedCampanas) {
            Long id = element.getTblCampana().getIdCampana();
            if(!serviceCampana.campanaHasMecanica(id.intValue())){
                msj = msj + "\nEvento Promocional "+ element.getTblCampana().getNombre() + " no es posible eliminar porque tiene datos asociados";
            }else{
                if (removeCampanas(element)){
                    msj = msj + "Evento Promocional "+ element.getTblCampana().getNombre() +" eliminado correctamente";
                    recreateCampanaMenu();
                    recreateModelCampana(CampanaConstants.GET_ALL,CampanaConstants.ZERO.getValue(),CampanaConstants.ZERO.getValue());
                }else{
                    msj = msj + "\nEvento Promocional "+ element.getTblCampana().getNombre() +" no fue eliminado debido a un error interno";
                }
            }

        }
        Messages.mensajeAlerta(msj, "Resultado");
        current = null;
        init();
    }

    private List<PeriodoDTO> periodoForNew;

    public List<PeriodoDTO> getPeriodoForNew() {
        return periodoForNew;
    }

    public void setPeriodoForNew(List<PeriodoDTO> periodoForNew) {
        this.periodoForNew = periodoForNew;
    }

    /**
	 * Boton de "Nuevo" sobre tabla con campanas.
	 * 
	 * @param e
	 * @throws ParseException
	 */
	public void createViewCampanas(ActionEvent e) throws ParseException {
		current = new CampanaDTO();
		Date fechaHoy = DateUtils.addHours(DateUtils.truncate(new Date(), Calendar.DATE), 12);
		current.getTblCampana().setFechaInicio(fechaHoy);
		fechaHoy = DateUtils.addHours(fechaHoy, 12);
		fechaHoy = DateUtils.addMinutes(fechaHoy, -1);
		current.getTblCampana().setFechaFin(fechaHoy);

		cambiarEstado(Estado.ALTA);

		currentProgramaDTO = new CampanaProgramaDTO();
		lCampanaProgramaDTO = new ArrayList<>();
        try {
            List<PeriodoDTO> ptemp = MBUtil.getPeriodos(serviceDynamicCatalogs);
            periodoForNew = new ArrayList<>();
            for (PeriodoDTO p : ptemp){
                if (p.getFechaInicial().after(new Date())){
                    periodoForNew.add(p);
                }
            }
        }catch (Exception ex){
        	ex.printStackTrace();
        }

        flujoActList = serviceTblActividades.getAllFull();
	}

	public void createViewCampana() {
		current = new CampanaDTO();
		cambiarEstado(Estado.ALTA);
	}
	
	/**
	 * Accion para el boton del disco para la primera pantalla de creacion de
	 * campanias.
	 */
	public void preCreateCampanas() {
		if (validateCamp()) {
            tituloEditarProg = alta;
			cambiarEstado(Estado.ALTA_PROGRAMA);
            getProgramasTable();
		} /* else {
			Messages.mensajeAlerta(
					Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO), 
					Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO));
		} */
	}

	/**
	 * Accion de boton de disco en segunda pantalla de creacion de campa?a.
	 */
	public void createCampanas() {
		Long time = System.currentTimeMillis();
        try {
            if (validateCamp() && validateProgs()) {
                TblCampana campana = current.getTblCampana();
                if (campana.getIdCampana() == 0) {
                    // Construye objeto de campana con estado actual del bean
                    TblCampana camp = new TblCampana();
                    camp.setCatEtiquetas(serviceCatEtiquetas.getEtiquetaById((current.getEtiqueta() == null) ? CampanaConstants.ETIQUETA_DEFAUL.getValue(): current.getEtiqueta().getId()));
                    TipoCampanaDTO tipoCampana = current.getTipo();
                    camp.setIdTipoCampana(tipoCampana.getId());
                    camp.setFechaFin(campana.getFechaFin());
                    camp.setFechaCreacion(new Date());
                    camp.setIdUsuarioCreacion(usuarioLogeado.getIdusuarios());
                    camp.setNombre(campana.getNombre());
                    camp.setIdTipoEvento(getCurrentProgramaDTO().getSencillo());
                    camp.setCatFlujoAct(campana.getCatFlujoAct());
                    camp.setCatEstatus(serviceCatEstatus.getEstatusById(1));

                    if (tipoCampana.getId().toString().equals(Constants.TIPO_CAMPANA_ESPECIAL)) {
                        // Campana especial no usa periodo, usa las fechas directo
                        camp.setFechaInicio(campana.getFechaInicio());
                        camp.setFechaFin(campana.getFechaFin());
                    } else {
                        camp.setIdPeriodo(current.getPeriodo().getId());
                        camp.setFechaInicio(current.getPeriodo().getFechaInicial());
                        camp.setFechaFin(current.getPeriodo().getFechaFinal());
                    }

                    saveUpdateCampana(camp, true);
                    current.setTblCampana(camp);

                    camp = null;
                    
                    List<String> categoriasAGuardar = this.currentProgramaDTO.getCategoriaSelect();
                    for(String s: categoriasAGuardar){
                        System.out.println("Guardando categoria: " + s);
                    }
                    
                    List<TblCampanaCategorias> lAuxCamCat = lTblCampCategorias;
                    lTblCampCategorias = lAuxCamCat;
                }else{
                    editCampanas();
                }
                init();
            }

            /*else {
                Messages.mensajeAlerta(Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO),Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO));
            }*/
        } catch (Exception e) {
            LOG.error(e);
            Messages.mensajeAlerta("Error al crear Evento: " + e.getMessage(), "Error al crear Evento: " + e.getMessage());
            LOG.info("Error al crear Evento: " + e);
        }
        System.out.println("Save: "+(System.currentTimeMillis()-time));
	}
	
	private Boolean campanaExists(){
		if(current.getTblCampana()!=null && StringUtils.isNotEmpty(current.getTblCampana().getNombre())
        		&& !current.getTblCampana().getNombre().trim().isEmpty()
        		&& current.getPeriodo()!=null
        		&& current.getPeriodo().getFechaFinal()!=null && current.getPeriodo().getFechaFinal()!=null 
        		&& current.getTipo()!=null && current.getTipo().getId()!=null
        		){
        	Integer tipoCampana = current.getTipo().getId();
        	Calendar c = Calendar.getInstance();
        	c.setTime(current.getPeriodo().getFechaInicial());
        	String nombre = current.getTblCampana().getNombre();
        	return serviceCampana.campanaExists(c.get(Calendar.YEAR), tipoCampana, nombre);
        }
		return false;
	}
	
	private boolean validateCamp() {
		boolean datosCorrectos = true;
        String msj = "Verificar: ";
		if (current.getTipo() == null || current.getTipo().getId() == 0) {
			datosCorrectos = false;
		} else if (current.getTblCampana() == null ||
				current.getTblCampana().getNombre() == null ||
				current.getTblCampana().getNombre().trim().isEmpty()) {
			// Debe tener nombre
            msj += " Periodo Promocional";
			datosCorrectos = false;
		} else if (current.getEtiqueta() == null ||
				current.getEtiqueta().getId() == null ||
				current.getEtiqueta().getId() == 0) {
			// Debe tener etiqueta
            msj += " Etiqueta";
			datosCorrectos = false;
		} else if (current.getTipo().getId() != 3 && (current.getPeriodo() == null || current.getPeriodo().getId() == 0)) {
			// Requiere un periodo si no es tipo de campana 3
            msj += " Periodo";
			datosCorrectos = false;
		} else if (current.getTipo().getId() == 3 && current.getTblCampana().getFechaInicio() == null) {
			// Requiere una fecha de inicio si es tipo de campana 3
            msj += " Fecha Inicio";
			datosCorrectos = false;
		} else if (current.getTipo().getId() == 3 && current.getTblCampana().getFechaFin() == null) {
			// Requiere una fecha de fin si es tipo de campana 3
            msj += " Fecha Fin";
			datosCorrectos = false;
		} else if (current.getTipo().getId() == 3 &&
				current.getTblCampana().getFechaFin().getTime() < current.getTblCampana().getFechaInicio().getTime()) {
			// La fecha de inicio debe ser antes que la fecha de fin
            msj += " Fecha Inicio debe ser antes que la Fecha Fin";
			datosCorrectos = false;
		} else if(campanaExists()){
			msj += " Campa\u00F1a con nombre \""+current.getTblCampana().getNombre()+"\" ya existe";
			datosCorrectos = false;
		} else {
            Date inicioEvento = new Date();
            if(current.getTipo().getId() == 3 && datosCorrectos){
                current.setPeriodo(new PeriodoDTO());
                current.getPeriodo().setFechaInicio(current.getTblCampana().getFechaInicio());
                current.getPeriodo().setFechaFin(current.getTblCampana().getFechaFin());
                inicioEvento = current.getTblCampana().getFechaInicio();
            }else if (datosCorrectos){
                inicioEvento = current.getPeriodo().getFechaInicial();
            }
            CatFlujoAct flujo = current.getTblCampana().getCatFlujoAct();
            int totalDias = serviceTblActividades.getDiasActividadesByFlujo(flujo) + flujo.getDiasantes();
            Date minDate = Util.sumarRestarDiasFecha(new Date(), totalDias);
            if (minDate.after(inicioEvento) && current.getTblCampana().getIdCampana() == 0){
                /* Fecha de Inicio debe ser despues de sumar los dias que requieren
                   las actividades.
                   Definidas en el catalogo de Flujo de Actividades
                 */
                msj += "Fecha Inicio o Periodo debe ser despues de las Actividades";
                datosCorrectos = false;
            }
        }

        if (!datosCorrectos){
            Messages.mensajeErroneo(msj, "");
        }

		return datosCorrectos;
	}
	
	private boolean validateProg(){
		boolean datosCorrectos = true;
        String msj = "";
		if (currentProgramaDTO.getIdPrograma() == 0){
			datosCorrectos = false;
		} 
		
		if (currentProgramaDTO.getCategoriaSelect() == null || currentProgramaDTO.getCategoriaSelect().isEmpty()){
			datosCorrectos = false;
            msj += "Categoria";
		}
		
		if (currentProgramaDTO.getIngreso() == 0){
			datosCorrectos = false;
            msj += ", Ingreso R11 Pop Ppto";
		}
        if (currentProgramaDTO.getGrupoZonas().isEmpty()){
            datosCorrectos = false;
            msj += ", Grupo zonas";
        }
        if (currentProgramaDTO.getZonas().isEmpty()){
            datosCorrectos = false;
            msj += ", Zonas";
        }
        if (currentProgramaDTO.getTiendas().isEmpty()){
            datosCorrectos = false;
            msj += ", Tiendas";
        }
		if (!datosCorrectos){
            Messages.mensajeErroneo("Falta informacion por Capturar: " + msj, "");
        }
		return datosCorrectos;
	}
	
	/**
	 * Valida los programas capturados para la campana antes de guardarlos.
	 * 
	 * @return
	 */
	private boolean validateProgs(){
		boolean datosCorrectos = true;
        String msj = "";
		if (lCampanaProgramaDTO == null || lCampanaProgramaDTO.isEmpty()){
			datosCorrectos = false;
		} else {
			for (CampanaProgramaDTO tblCamProg : lCampanaProgramaDTO){
				if (tblCamProg.getIdPrograma() == 0){
					datosCorrectos = false;
                    msj += "Programa, ";
					break;
				} 
				
				if (tblCamProg.getCategoriaSelect() == null || tblCamProg.getCategoriaSelect().isEmpty()){
					datosCorrectos = false;
                    msj += "Categorias en Programa: " + tblCamProg.getDescripcion();
					break;
				}
				
				if (tblCamProg.getIngreso() == 0){
					datosCorrectos = false;
                    msj += ", Ingreso";
					break;
				}
			}
		}
		if (!datosCorrectos){
            Messages.mensajeAlerta("Falta Informacion: " + msj,"");
        }
		return datosCorrectos;
	}
	
	public Set<TblCampanaMedio> getMedios(TblCampana tblCampana) {
		return new HashSet<>();

		// Set<TblCampanaMedio> setTblCampanaMedios = new
		// HashSet<TblCampanaMedio>();

		// TblCampanaMedioId tblCampanaMedioId = new TblCampanaMedioId();
		// TblCampanaMedio tblCampanaMedio = new TblCampanaMedio();
		// tblCampanaMedio.setTblCampana(tblCampana);
		// tblCampanaMedio.setIdMedio(Constants.IMPRESO_MEDIO);

		// tblCampanaMedio.setId(tblCampanaMedioId);
		// tblCampanaMedio.setIdTipoMedio(Constants.ID_TIPO_MEDIO_SEVEN);

		// setTblCampanaMedios.add(tblCampanaMedio);

		// return setTblCampanaMedios;
	}
	
	private void asociaProgramas(TblCampana camp, boolean nuevo){
		
		if (camp.getIdCampana() != 0) {
			LOG.info("Campana Inicio: " + camp.getFechaInicio());
			//camp.setTblCampanaCategoriases(this.getSelectedCategorias(camp));
			camp.setTblCampanaProgramas(getSelectedProgramas(camp));
			try {
				serviceCampana.saveCampana(camp);
				//saveCampanaCategoriasYPlazas(camp, nuevo);
				savePrograms(new ArrayList<TblCampanaProgramas>(camp.getTblCampanaProgramas()));
				
				if (nuevo){
					sendMailsRespCateg(camp);
				}
				
			} catch (Exception e) {
				LOG.error(e);
			}
		} else {
			LOG.info("Campana NUll");			
		}
	}
	
	public Set<TblCampanaCategorias> getSelectedCategorias(TblCampana tblCampana){
		
		Set<TblCampanaCategorias> setTblCampanaCategoriases = new HashSet<>();
		
		for (TblCampanaCategorias tblCampCat : lTblCampCategorias) {
			if (tblCampCat.isSelected()){
				if (tblCampCat.getId().getIdCampana() == 0){
					tblCampCat.getId().setIdCampana(tblCampana.getIdCampana());
				}
				setTblCampanaCategoriases.add(tblCampCat);
			}
		}                  
		return setTblCampanaCategoriases;
	}
	
	public Set<TblCampanaProgramas> getSelectedProgramas(final TblCampana campana){
		final Set<TblCampanaProgramas> setTblCampProg = new HashSet<>();
		if(!lCampanaProgramaDTO.isEmpty()){
			for (final CampanaProgramaDTO campProgDTO : lCampanaProgramaDTO) {
		            TblCampanaProgramas l = serviceCampana.getProgramaById(campana.getIdCampana(), campProgDTO.getIdPrograma());
		            if (l == null){
		            	l = new TblCampanaProgramas();
		                TblCampanaProgramasId tblCampProgId = new TblCampanaProgramasId();
		                tblCampProgId.setIdCampana(campana.getIdCampana());
		                tblCampProgId.setIdPrograma(campProgDTO.getIdPrograma());
		                l.setId(tblCampProgId);
		            }
		            final TblCampanaProgramas tblCampProg = l;
		            tblCampProg.setEtapa(campProgDTO.getEtapa() == null ? Constants.ETAPA_PENDIENTE : campProgDTO.getEtapa());
					tblCampProg.setIngreso(campProgDTO.getIngreso());
		            tblCampProg.setEsSencillo(campProgDTO.getSencillo());
		
		            tblCampProg.setGrupoZonas(new HashSet<RelGrupoZonaCampana>());
		            tblCampProg.setZonas(new HashSet<RelZonaCampana>());
		            tblCampProg.setTiendas(new HashSet<RelStoreCampana>());
					for( String grupoZona: campProgDTO.getGrupoZonas()){
						RelGrupoZonaCampana e= new RelGrupoZonaCampana();
						e.setGrupoId(Integer.valueOf(grupoZona));
						e.setTblCampanaProgramas(tblCampProg);
						tblCampProg.getGrupoZonas().add(e);
					}
					for( String zona: campProgDTO.getZonas()){
						RelZonaCampana e= new RelZonaCampana();
						e.setZonaId(Integer.valueOf(zona));
						e.setTblCampanaProgramas(tblCampProg);
						tblCampProg.getZonas().add(e);
					}
					for( String tienda: campProgDTO.getTiendas()){
						RelStoreCampana e= new RelStoreCampana();
						e.setStoreId(Integer.valueOf(tienda));
						e.setTblCampanaProgramas(tblCampProg);
						tblCampProg.getTiendas().add(e);
					}
					
					final Set<TblCampanaProgramasCategorias> setTblCamProgCateg = new HashSet<>();
					CatPrograma cp = serviceCatPrograma.getCatPrograma(campProgDTO.getIdPrograma());
					if(!campProgDTO.getCategoriaSelect().isEmpty()){
						for (final String strIdCateg : campProgDTO.getCategoriaSelect()){
							TblCampanaProgramasCategorias tblCampProgCateg = new TblCampanaProgramasCategorias();
							tblCampProgCateg.setIdCategoria(Integer.valueOf(strIdCateg));
							tblCampProgCateg.setTblCampanaProgramas(tblCampProg);
			                tblCampProgCateg.getTblCampanaProgramas().setPrograma(cp);
							setTblCamProgCateg.add(tblCampProgCateg);
						}
					}
					tblCampProg.setTblCampanaProgramasCategorias(setTblCamProgCateg);
		            tblCampProg.setTblCampana(campana);
					setTblCampProg.add(tblCampProg);
			}
		}
		return setTblCampProg;
	}
	
	private TblCampana saveUpdateCampana(TblCampana camp, boolean create) {
		TblCampana tblCampana = new TblCampana();
        camp.setTblCampanaProgramas(getSelectedProgramas(camp));
		try {
			if (create) {
				tblCampana = this.serviceCampana.saveNewCampana(camp);
				camp.setTblCampanaMedios(getMedios(camp));
				asociaProgramas(tblCampana, true);
			} else {
				serviceCampana.updateCampana(camp);
				asociaProgramas(camp, false);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return tblCampana;
	}

	public void updateViewCampanas() throws ParseException{
        System.out.println("entrando a actualizar pantalla.");
        System.out.println("Elementos a actualizar: " + selectedCampanas.length);
        try{
		switch (selectedCampanas.length) {
		case 0:
			Messages.mensajeAlerta("Debe seleccionar un registro", "Debe seleccionar un registro");
			LOG.info("Escoja  una opcion");
			recreateCampanaMenu();		
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		case 1:
            System.out.println("editando: " + selectedCampanas[0]);
            selectedCampanas[0].setTblCampana(serviceCampana.getCampana(selectedCampanas[0].getTblCampana().getIdCampana()));
			if (current == null || (!selectedCampanas[0].toString().equals(current.toString()))
					|| (selectedCampanas[0].toString().equals(current.toString()) && current.getlCampanaProgramaDTO()==null)
				){
				setCurrent(selectedCampanas[0]);
				List<TblCampanaProgramas> cps = serviceCampana.getCampanaProgramas(current.getTblCampana().getIdCampana());
				current.setlCampanaProgramaDTO(CampanaUtil.getListCamapanaProgramasDTO(new HashSet<>(cps), gZonasPorId));
				buildProgramsDTOList(current);
			}
			if (current.getTipo().getId() != 3){
				Date fechaHoy = DateUtils.truncate(new Date(), Calendar.DATE);
				fechaHoy = DateUtils.addHours(fechaHoy, 12);
				current.getTblCampana().setFechaInicio(fechaHoy);
				fechaHoy = DateUtils.addHours(fechaHoy, 12);
				fechaHoy = DateUtils.addMinutes(fechaHoy, -1);
				current.getTblCampana().setFechaFin(fechaHoy);
			}
			cambiarEstado(Estado.EDICION);
            flujoActList = serviceTblActividades.getAllFull();
			break;

		default:
			Messages.mensajeAlerta("Debe seleccionar solo un registro", "Debe seleccionar solo un registro");
			recreateCampanaMenu();
			recreateModelCampana(CampanaConstants.GET_ALL,
					CampanaConstants.ZERO.getValue(),
					CampanaConstants.ZERO.getValue());
			break;
		}
        }catch(Exception e){
            System.out.println("ERROR al editar promocion");
            e.printStackTrace();
        }
        
        System.out.println("fin de edicion de componente");
        if(this.current.getTblCampana() != null){
            System.out.println("Objeto campana no nulo");
            if(this.current.getTblCampana().getCatFlujoAct() != null){
                System.out.println("cat no nulo");
            }else{
                System.out.println("cat nulo");
            }
        }else{
            System.out.println("objeto campana nulo");
        }
        if(flujoActList == null || flujoActList.size() == 0){
            System.out.println("Lista de flujos nnula o vacia");
        }else{
            System.out.println("Lista de flujos con contenido");
        }
        this.current.getTblCampana().setCatFlujoAct(this.current.getTblCampana().getCatFlujoAct());
        
        flujoActList = serviceTblActividades.getAllFull();
        
        
	}
	
	public void updateViewCat() {
		if (!catgGuardada) {
			setCurrent(selectedCampanas[0]);
		}
		cambiarEstado(Estado.EDICION);
	}
	
	public void succesSaveMessage(){
		Messages.mensajeSatisfactorio(Messages.getString("msg4"), Messages.getString("msg4"));
	}
	
	public void preEditCampanas(){
		RequestContext requestContext = RequestContext.getCurrentInstance();  
		if ("campanias".equals(section)){
			if (validateCamp()){
				requestContext.execute("w_medioEditar.show();");
			} else {
				Messages.mensajeAlerta(Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO), Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO));
			}
		} else {
			requestContext.execute("w_medioEditar.show();");
		}
	}

    public void editCampanas() {
        try {
            if (validateCamp()){
                TblCampana camp = serviceCampana.getCampana(current.getTblCampana().getIdCampana());
                camp.setCatEtiquetas(serviceCatEtiquetas.getEtiquetaById(current.getEtiqueta().getId()));
                camp.setIdTipoCampana(current.getTipo().getId());
                //camp.setFechaFin(current.getTblCampana().getFechaFin());
                //camp.setFechaInicio(current.getTblCampana().getFechaInicio());
                camp.setComentarios(current.getTblCampana().getComentarios());
                camp.setFechaModificacion(new Date());
                camp.setIdUsuarioModificacion(usuarioLogeado.getIdusuarios());
                camp.setNombre(current.getTblCampana().getNombre());
                camp.setIdTipoEvento(getCurrentProgramaDTO().getSencillo());
                if (current.getTipo().getId().toString().equals(Constants.TIPO_CAMPANA_ESPECIAL)){
                        camp.setFechaInicio(current.getTblCampana().getFechaInicio());
                        camp.setFechaFin(current.getTblCampana().getFechaFin());
                } else {
                    camp.setIdPeriodo(current.getPeriodo().getId());
                    camp.setFechaInicio(current.getPeriodo().getFechaInicial());
                    camp.setFechaFin(current.getPeriodo().getFechaFinal());
                }

                if ("campanias".equals(section)){
                    current = null;
                    saveUpdateCampanas(camp, false);
                    init();
                } else {
                    saveUpdateCampanas(camp, false);
//                    camp = serviceCampana.getCampana(camp.getIdCampana());
                    current = CampanaUtil.llenadoCampanaDTO(camp, tiposCampanaPorId, periodosPorId, gZonasPorId);
                    if (null == selectedCampanas){
                            selectedCampanas = new CampanaDTO[1];
                    }
                    recargarCamapanaDTO(camp.getIdCampana());
                    catgGuardada = true;
                }
                camp = null;
                cambiarEstado(Estado.CONSULTA);
                Messages.mensajeSatisfactorio(Messages.getString("msg4"), Messages.getString("msg4"));
            
            } else {
                   
            	Messages.mensajeAlerta(Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO), Messages.getString(Constants.MENSAJE_VALIDACION_CAPTURA_INFO));
            	
         
            }
        } catch (Exception e) {
                Messages.mensajeErroneo("Error al guardar la informacion", "Error al guardar la informacion");
                LOG.info("Error al guardar");
        }
    }

	private void saveUpdateCampanas(TblCampana camp, boolean create) {
		try {
			if (create) {
				serviceCampana.saveCampana(camp);
				camp.setTblCampanaMedios(getMedios(camp));					 
				asociaProgramas(camp, true);	
				Messages.mensajeSatisfactorio(Messages.getString("msg4"), Messages.getString("msg4"));
			} else {
				camp.setTblCampanaProgramas(getSelectedProgramas(camp));
				serviceCampana.updateCampana(camp);
				updatePrograms(camp.getIdCampana(),camp);	
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
	
	/*public Set<TblCampanaCategorias> updateSelectedCategorias(TblCampana tblCampana){
		
		List<TblCampanaCategorias> setTblCampanaCategoriasesExistentes = 
				serviceCampana.getCampanaCategorias(tblCampana.getIdCampana());
								
		for (Iterator<TblCampanaCategorias> element = (Iterator<TblCampanaCategorias>) setTblCampanaCategoriasesExistentes
				.iterator(); element.hasNext();) {
			TblCampanaCategorias tblCampCat = (TblCampanaCategorias)element.next();
			if (!Hibernate.isInitialized(tblCampCat)){
				Hibernate.initialize(tblCampCat);
			}
			serviceCampana.removePlazaByCategoryAndCampana(tblCampCat.getId().getIdCategoria(), tblCampCat.getId().getIdCampana());
			serviceCampana.removeCampanaCategoriaById(tblCampCat.getId().getIdCategoria(), tblCampCat.getId().getIdCampana());
		}
		
		saveCampanaCategoriasYPlazas(tblCampana, false);
		
		return new HashSet<TblCampanaCategorias>(setTblCampanaCategoriasesExistentes);
	}*/
	
	private void updateGrupoZonaTienda(List<TblCampanaProgramas> programs) {
		System.out.println("init updateGrupoZonaTienda...");

		List<Long> idsCampana = new ArrayList<Long>();
		List<Integer> idsPrograma = new ArrayList<>();
		for(TblCampanaProgramas o: programs){
			idsCampana.add(o.getId().getIdCampana());
			idsPrograma.add(o.getId().getIdPrograma());
		}
		System.out.println("init deletion...");

		serviceCampana.removeCampProgGrupoZonaByProgramaAndCampana(idsCampana, idsPrograma);
		serviceCampana.removeCampProgZonaByProgramaAndCampana(idsCampana, idsPrograma);
		serviceCampana.removeCampProgTiendaByProgramaAndCampana(idsCampana, idsPrograma);
		System.out.println("delete completed...");
		if(!lCampanaProgramaDTO.isEmpty()){
	    	serviceCampana.updateGrupoZonaTienda(programs, lCampanaProgramaDTO);
		}
	}

        
        
	private void updatePrograms(long idCampana, TblCampana camp){
		try{
            List<TblCampanaProgramas> lTblCampProgs = new ArrayList<>();
            for (CampanaProgramaDTO dto : lCampanaProgramaDTO){
                TblCampanaProgramas tbl = serviceCampana.getProgramaById(idCampana, dto.getIdPrograma());
                if (tbl == null){
                    TblCampanaProgramasId id = new TblCampanaProgramasId();
                    id.setIdCampana(idCampana);
                    id.setIdPrograma(dto.getIdPrograma());
                    tbl = new TblCampanaProgramas();
                    tbl.setId(id);
                }

                tbl.setDescripcion(dto.getDescripcion());
                tbl.setEsSencillo(dto.getSencillo());
                tbl.setGrupoZonas(tbl.getGrupoZonas());
                tbl.setIngreso(dto.getIngreso());
                tbl.setPlazaSelect(dto.getPlazaSelect());
                tbl.setEtapa(dto.getEtapa());
                        
                Set<TblCampanaProgramasCategorias> setTblCamProgCateg = new HashSet<>();
			for (String strIdCateg : dto.getCategoriaSelect()){
				TblCampanaProgramasCategorias tblCampProgCateg = new TblCampanaProgramasCategorias();
				tblCampProgCateg.setIdCategoria(Integer.valueOf(strIdCateg));
				tblCampProgCateg.setTblCampanaProgramas(tbl);
				setTblCamProgCateg.add(tblCampProgCateg);
			}
                        tbl.setTblCampanaProgramasCategorias(setTblCamProgCateg);
                        lTblCampProgs.add(tbl);
                        serviceCampana.updateCampanaProgramas(tbl);
                    }
//			List<TblCampanaProgramas> lTblCampProgs = serviceCampana.getCampanaProgramas(idCampana);
			for (TblCampanaProgramas tblCampProgs : lTblCampProgs){
				serviceCampana.removeCampProgCategByProgramaAndCampana(tblCampProgs.getId().getIdCampana(), tblCampProgs.getId().getIdPrograma());
				//serviceCampana.removeCampProgPlazasByProgramaAndCampana(tblCampProgs.getId().getIdCampana(), tblCampProgs.getId().getIdPrograma());
				//serviceCampana.removeCampProgGrupoZonaByProgramaAndCampana(tblCampProgs.getId().getIdCampana(), tblCampProgs.getId().getIdPrograma());
				//serviceCampana.removeCampProgZonaByProgramaAndCampana(tblCampProgs.getId().getIdCampana(), tblCampProgs.getId().getIdPrograma());
				//serviceCampana.removeCampProgTiendaByProgramaAndCampana(tblCampProgs.getId().getIdCampana(), tblCampProgs.getId().getIdPrograma());
				
				if (!Hibernate.isInitialized(tblCampProgs.getTblCampanaProgramasCategorias())){
					Hibernate.initialize(tblCampProgs.getTblCampanaProgramasCategorias());
				}
				/*if (!Hibernate.isInitialized(tblCampProgs.getTblCampanaProgramasPlazas())){
					Hibernate.initialize(tblCampProgs.getTblCampanaProgramasPlazas());
				}
				if (!Hibernate.isInitialized(tblCampProgs.getGrupoZonas())){
					Hibernate.initialize(tblCampProgs.getGrupoZonas());
				}
				if (!Hibernate.isInitialized(tblCampProgs.getZonas())){
					Hibernate.initialize(tblCampProgs.getZonas());
				}
				if (!Hibernate.isInitialized(tblCampProgs.getTiendas())){
					Hibernate.initialize(tblCampProgs.getTiendas());
				}*/
			}
			savePrograms(lTblCampProgs);
		} catch (GeneralException ge){
			LOG.error(ge.getMessage());
			Messages.mensajeErroneo("Error al guardar programas", "Error al guardar programas");
		}
	}
	
	// XXX: verificar que no se esten guardando doble los programas
	private void savePrograms(List<TblCampanaProgramas> iterCampProg){
		Long time = System.currentTimeMillis();
		System.out.println("init savePrograms...");

        try {
        	if(!iterCampProg.isEmpty()){
        		List<TblCampanaProgramasCategorias> list = new ArrayList<TblCampanaProgramasCategorias>();
        		for (TblCampanaProgramas tblCampProg:iterCampProg ){
        			list.addAll(tblCampProg.getTblCampanaProgramasCategorias());
                }
        		serviceCampana.saveCampanaProgramasCategorias(list);
        		updateGrupoZonaTienda(iterCampProg);
        	}
        } catch (Exception e){
                LOG.error(e.getMessage());
                Messages.mensajeErroneo("Error al guardar categorias y plazas del programa", "Error al guardar categorias y plazas del programa");
        }
        System.out.println("savePrograms: "+(System.currentTimeMillis()-time));
	}
	
	private void sendMailsRespCateg(final TblCampana tblCamp){
		final Map<UsuarioDTO, String> mUsuCat = new HashMap<>();
		try {
			if(!lCampanaProgramaDTO.isEmpty()){
		    	ExecutorService executor = Executors.newFixedThreadPool(15);
		    	for (final CampanaProgramaDTO campProgDTO : lCampanaProgramaDTO){
					for (final String sIdCategoria : campProgDTO.getCategoriaSelect()) {
						Runnable r = new Runnable() { @Override public void run() {
							try{
								UsuarioDTO usuDTO = MBUtil.getUsuarioByIdCategoria(serviceDynamicCatalogs, Integer.valueOf(sIdCategoria));
								String nomCategoria = getNameCategoriaById(Integer.valueOf(sIdCategoria));
								if (usuDTO.getEmail() != null){
									if (!mUsuCat.containsKey(usuDTO)){
										mUsuCat.put(usuDTO, nomCategoria);
									} else {
										String strCats = mUsuCat.get(usuDTO);
										if (!strCats.contains(nomCategoria)){
											mUsuCat.remove(usuDTO);
											mUsuCat.put(usuDTO, strCats + ", " + nomCategoria);
										}
									}
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}};
			    		executor.execute(r);
					}
				}
		    	executor.shutdown();
		        while (!executor.isTerminated()){}
			}
		
			if (mUsuCat.size() > 0){
				Long time = System.currentTimeMillis();
				for (final Map.Entry<UsuarioDTO, String> eUsuCat : mUsuCat.entrySet()){
					Object[] params = new Object[3];
					params[0] = tblCamp.getNombre();
					params[1] = Util.getSessionAttribute("userLoged").toString();
					params[2] = eUsuCat.getValue().contains(",") ? eUsuCat.getValue().substring(0, eUsuCat.getValue().lastIndexOf(',')) + 
								" y" + eUsuCat.getValue().substring(eUsuCat.getValue().lastIndexOf(',') + 1) : eUsuCat.getValue();
					
					SendMail.sendGenericEmail(eUsuCat.getKey().getEmail(),
							Constants.EMAIL_CAMPANA_TITLE,
							Constants.EMAIL_CAMPANA_DETAIL,
							params);
				}
				System.out.println("send emails: "+(System.currentTimeMillis()-time));
			}
		} catch (Exception e) {
			LOG.error(e);
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

	private boolean removeCampanas(CampanaDTO campanaDTO) {
		try {
			TblCampana campana = serviceCampana.getCampana(campanaDTO.getTblCampana().getIdCampana());
			//removeProgCatPlazas(campana.getIdCampana());
                        serviceCampana.deleteCampanaActividadByIdCampana(campana.getIdCampana());
			return serviceCampana.deleteCampana(campana) != 0;

		} catch (Exception e) {
			LOG.error(e);
                        return false;
		}
	}

	public List<CampanaDTO> getListCampanaDTO() throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		CampanaDTO campanaDTO;
		listCampanas = listCampanasCache;
		for (TblCampana campana : listCampanas) {
			campanaDTO = CampanaUtil.llenadoCampanaDTO(campana, tiposCampanaPorId, periodosPorId, gZonasPorId);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	public List<CampanaDTO> getListCampanaDTOByYear(int year) throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		CampanaDTO campanaDTO;
		listCampanas = serviceCampana.getAllCampanasByYearAsc(year, getPeriodosCatalogo());
		for (TblCampana campana : listCampanas) {
			campanaDTO = CampanaUtil.llenadoCampanaDTO(campana, tiposCampanaPorId, periodosPorId, gZonasPorId);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}
	
	private List<PeriodoDTO> getPeriodosCatalogo() {
		return new ArrayList<>(periodosPorId.values());
	}


	public List<CampanaDTO> getListCampanaDTOByYearAndTypeCampana(int year,
			int typeCampana) throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		CampanaDTO campanaDTO;
		listCampanas = serviceCampana.getListCampanaDTOByYearAndTypeCampana(year, typeCampana, getPeriodosCatalogo());
		for (TblCampana campana : listCampanas) {
			campanaDTO = CampanaUtil.llenadoCampanaDTO(campana, tiposCampanaPorId, periodosPorId, gZonasPorId);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	public List<CampanaDTO> getListCampanaDTOByTypeCampana(int typeCampana)
			throws Exception {
		List<CampanaDTO> listCampanaDTO = new ArrayList<>();
		List<TblCampana> listCampanas;
		CampanaDTO campanaDTO;
		listCampanas = serviceCampana.getAllCampanaByTypeCampana(typeCampana);
		for (TblCampana campana : listCampanas) {
			campanaDTO = CampanaUtil.llenadoCampanaDTO(campana, tiposCampanaPorId, periodosPorId, gZonasPorId);
			listCampanaDTO.add(campanaDTO);
		}
		return listCampanaDTO;

	}

	private String addTienda(int tienda, CampanaProgramaDTO campProgDTO) {
		CatStore catStore = tiendasPorId.get(tienda);
		if (catStore != null) {
			RelObj relObj = new RelObj();
			relObj.setId(catStore.getIdStore());
			relObj.setStr(catStore.getCode());
			campProgDTO.getStoreLst().add(relObj);
                        return catStore.getCode();
		}
                return null;
	}
	
	private String addZona(int zonaId, CampanaProgramaDTO campProgDTO) {
		CatZone catZone = new CatZone();
		//catZone.setIdZone(zonaId);
		try {
			//catZone = serviceCatZone.getCatZoneById(catZone);
			catZone = zonasPorId.get(zonaId);
					
		} catch (Exception e) {
			LOG.error(e);
		}
		RelObj relObj= new RelObj();
		relObj.setId(catZone.getIdZone());
		relObj.setStr(catZone.getCode());
		campProgDTO.getZonaLst().add(relObj);
                return catZone.getCode();
	}

	private void buildProgramsDTOList(CampanaDTO campanaDTO){

		lCampanaProgramaDTO = campanaDTO.getlCampanaProgramaDTO();
		if (lCampanaProgramaDTO != null){
			for (CampanaProgramaDTO campProgDTO : lCampanaProgramaDTO){
				campProgDTO.setDescCampana(campanaDTO.getTblCampana().getNombre());
				
				for (ProgramaDTO progDTO : getProgramas()){
					if (progDTO.getId() == campProgDTO.getIdPrograma()){
						campProgDTO.setDescripcion(progDTO.getCode());
						break;
					}
				}

                List<RelZonaCampana> zonaCampanas = serviceCampana.getRelZonaCampanaByCampanaPrograma(campanaDTO.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma()); 
                List<RelStoreCampana> relStores = serviceCampana.getRelStoreCampanaByCampanaPrograma(campanaDTO.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());
                List<String> stores = new ArrayList<>();
                for (RelStoreCampana s : relStores){
                    stores.add(String.valueOf(s.getStoreId()));
                }

                campProgDTO.setTiendas(stores);

                List<String> zones = new ArrayList<>();
                for(RelZonaCampana s : zonaCampanas){
                    zones.add(String.valueOf(s.getZonaId()));
                }

                campProgDTO.setZonas(zones);
				/*StringBuffer sbPlazas = new StringBuffer();
				for (String strPlaza : campProgDTO.getPlazaSelect()){
					sbPlazas.append(getNamePlazaById(Integer.valueOf(strPlaza).intValue()));
					sbPlazas.append(", ");
				}
				campProgDTO.setStrPlazas(!sbPlazas.toString().isEmpty() ? sbPlazas.toString().substring(0, sbPlazas.toString().lastIndexOf(',')) : "");
				*/
				StringBuffer sbCateg = new StringBuffer();
				List<Integer> ids = new ArrayList<>();
				for (String strCateg : campProgDTO.getCategoriaSelect()){
					ids.add(Integer.valueOf(strCateg));
				}
				for(CatCategory cc: serviceCatCategory.getCatCategoryByIds(ids)){
					sbCateg.append(cc.getCode());
					sbCateg.append(", ");
				}
				
				campProgDTO.setStrCatgerias(!sbCateg.toString().isEmpty() ? sbCateg.toString().substring(0, sbCateg.toString().lastIndexOf(',')) : "");
				
				campProgDTO.setStrEsSencillo(campProgDTO.getSencillo()== 0 ? "Compartido" : "Sencillo");
                                
			}
		}
		
	}
	
	public void preDeleteProgramas(){
		if (null == selCampanaProgramaDTO || selCampanaProgramaDTO.length == 0){
			Messages.mensajeAlerta("Debe seleccionar uno o mas registros", "Debe seleccionar uno o mas registros");
		} else{
			RequestContext requestContext = RequestContext.getCurrentInstance();  
			requestContext.execute("confEliProg.show();");
		}
	}
	
	public void deleteProgramas() throws Exception{	
		for (CampanaProgramaDTO campProgDTO : selCampanaProgramaDTO) {
			removeProgram(current.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());

			serviceCampana.removeCampProgCategByProgramaAndCampana(current.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());
			//serviceCampana.removeCampProgPlazasByProgramaAndCampana(current.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());
			serviceCampana.removeCampProgGrupoZonaByProgramaAndCampana(current.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());
			serviceCampana.removeCampProgZonaByProgramaAndCampana(current.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());
			serviceCampana.removeCampProgTiendaByProgramaAndCampana(current.getTblCampana().getIdCampana(), campProgDTO.getIdPrograma());
		}
		
		Messages.mensajeSatisfactorio("Los registros se eliminaron exitosamente", "Los registros se eliminaron exitosamente");
		
		try {
			TblCampana tblCampania = serviceCampana.getCampana(current.getTblCampana().getIdCampana());

			current = CampanaUtil.llenadoCampanaDTO(tblCampania, tiposCampanaPorId, periodosPorId, gZonasPorId);
			recargarCamapanaDTO(current.getTblCampana().getIdCampana());
		} catch(Exception exc){
			LOG.error(exc.getMessage());
		}
	}
	
	private void removeProgram(long idCampana, int idPrograma){
		try{
			serviceCampana.removeCampanaProgramasByIdCampanaIdPrograma(idCampana, idPrograma);
		} catch(Exception e){
			LOG.error(e.getMessage());
		}
	}
	
	public void generateActivities(){
		try {
//			serviceCampana.generateActivities(current.getTblCampana().getIdCampana(),(UsuarioDTO)Util.getSessionAttribute("userLoged"), serviceDynamicCatalogs);
                    UsuarioDTO user = Util.getSessionAttribute("userLoged");
                    TblCampanaActividades activity = new TblCampanaActividades();
                    activity.setNombreActividad("Evento " + current.getTblCampana().getNombre());
                    activity.setEsFlujo(Constants.NUMERO_ES_FLUJO_PERS);
                    activity.setFechaCreacion(new Date());
                    activity.setUsuarioCreacion(user.getUserId());
                    activity.setIdUsuarioResp(user.getUserId());
                    activity.setIdRol(1);
                    activity.setTblCampana(current.getTblCampana());
                    activity.setFechaInicio(current.getPeriodo().getFechaInicial());
                    activity.setFechaFin(current.getPeriodo().getFechaFinal());
                    serviceCampana.saveActivity(activity);
               //     SendMail.sendActivity(Constants.CORREOS_ACTIVIDAD_FROM_EVENTOS, activity, false);
                    Messages.mensajeSatisfactorio("Se generaron las actividades", "Se generaron las actividades");
		} catch (GeneralException e){
			Messages.mensajeErroneo("Error al generar actividades", "Error al generar actividades");
			LOG.error(e.getMessage(), e);
		} catch (Exception e) {
                        Messages.mensajeErroneo("Error al enviar correo de actividades", "Error al enviar correo de actividades");
			LOG.error(e.getMessage(), e);
            }
	}
	
	public SelectItem[] getEtiquetas() {
		return MBUtil.getSelectItems(new ArrayList<>(etiquetasPorId.values()), true);
	}

	public SelectItem[] getCategory() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getCategorias(serviceDynamicCatalogs), false);
		} catch (Exception e) {
			LOG.info(e);
		}
		return items;
	}

	
	public SelectItem[] getTipoCampanas() {
		SelectItem[] items = null;
		items = MBUtil.getSelectItems(
				new ArrayList<>(tiposCampanaPorId.values()), true);
		return items;
	}

	public SelectItem[] getResponsables() {
		try {
			if (responsables == null){
				responsables = MBUtil.getSelectItems(
							MBUtil.getUSers(serviceDynamicCatalogs), true);
			}
		} catch (Exception e) {
			LOG.info(e);
		}
		
		return responsables;
	}
	
	public SelectItem[] getPeriodos() {
		SelectItem[] items = null;
		items = MBUtil.getSelectItems(
				new ArrayList<>(periodosPorId.values()), false);
		return items;
	}

	public SelectItem[] getTipos() {
		return tipos;
	}

	public void onNodeSelect(NodeSelectEvent event) {
        LOG.info("evento on node select");
		int anio = 0;
		TipoCampanaDTO tipoCampana = null;
		
		TreeNode nodoSeleccionado = event.getTreeNode();
		Object treeNodeData = nodoSeleccionado.getData();
		String nombreSeleccionado = treeNodeData.toString().toUpperCase();
		
		if (treeNodeData instanceof CampaniaTreeRegs) {
            LOG.info("clase: CampaniaTreeRegs");
			CampaniaTreeRegs campania = (CampaniaTreeRegs) treeNodeData;
            
            int anioCampania = campania.getAnio();
            int idTipoCampania = campania.getIdTipoCampania();
            
            if(anioCampania > 0 && idTipoCampania > 0){
                filterByTypeAndYear(anioCampania,idTipoCampania, nombreSeleccionado);
            }
            this.selectedCampainOnGrid = nombreSeleccionado;
            this.selectedCampainType = "3";
            
		} else if (treeNodeData instanceof VerTodas) {
            LOG.info("clase: VerTodas");
			// Es nodo de ver todas para un anio
			
			VerTodas showAll = (VerTodas) treeNodeData;
			LOG.info("*******************Anio: " + showAll.getAnio() + "*****************");
			if (isDetalle()) {
				cambiarEstado(Estado.CONSULTA);
				recreateModelCampana(CampanaConstants.FIND_BY_YEAR,
						showAll.getAnio(), CampanaConstants.ZERO.getValue());
			} else {
				cambiarEstado(Estado.CONSULTA);
				recreateModelCampana(CampanaConstants.FIND_BY_YEAR,
						showAll.getAnio(), CampanaConstants.ZERO.getValue());
			}
		} else if ("VER TODOS".equals(nombreSeleccionado)) {
            LOG.info("nombre seleccionado: VER TODOS");
			// Lo que vayas a hacer con "Ver todas" 
			if (isDetalle()) {
				cambiarEstado(Estado.CONSULTA);
				recreateModelCampana(CampanaConstants.GET_ALL,
						CampanaConstants.ZERO.getValue(),
						CampanaConstants.ZERO.getValue());
			} else {
				cambiarEstado(Estado.CONSULTA);
				recreateModelCampana(CampanaConstants.GET_ALL,
						CampanaConstants.ZERO.getValue(),
						CampanaConstants.ZERO.getValue());
			}
			campTreeSelect = false;
		} else if (treeNodeData instanceof String) {
            this.filterGrid(nodoSeleccionado, tipoCampana, anio, nombreSeleccionado);
		}
        
        System.out.println("fin nodeSelect");
        if(this.current.getTblCampana() != null){
            System.out.println("Objeto campana no nulo");
            System.out.println("id campana: " + current.getTblCampana().getIdCampana());
            try {
                TblCampana tmp = serviceCampana.getCampana(current.getTblCampana().getIdCampana());
                System.out.println("flujo seleccionado: " + tmp.getCatFlujoAct().getNombre());
                this.current.setTblCampana(tmp);
            } catch (Exception ex) {
                ex.printStackTrace();
                java.util.logging.Logger.getLogger(CampanaControllerSeven.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(this.current.getTblCampana().getCatFlujoAct() != null){
                System.out.println("cat no nulo");
            }else{
                System.out.println("cat nulo");
            }
        }else{
            System.out.println("objeto campana nulo");
        }
        if(flujoActList == null || flujoActList.size() == 0){
            System.out.println("Lista de flujos nnula o vacia");
        }else{
            System.out.println("Lista de flujos con contenido");
        }
        
        flujoActList = serviceTblActividades.getAllFull();
        
	}
    
    private void filterGrid(TreeNode nodoSeleccionado, TipoCampanaDTO tipoCampana, int anio, String nombreSeleccionado){
        Object treeNodeData = nodoSeleccionado.getData();
        LOG.info("Clase String, valor: " + treeNodeData);
        LOG.info("Valor de nodo seleccionado: " + nodoSeleccionado);

        // Buscar un hijo de tipo CampaniaTreeRegs
        Integer idTipoCampana = getIdTipoCampana(nodoSeleccionado);

        //valida que tenga id de tipo de tipo de campa?a, si no retorna
        if (idTipoCampana == null) {
            LOG.info("No hay campanias para el nodo: " + treeNodeData.toString());
            return;
        }

        //itera para obtener objeto
        for (TipoCampanaDTO tipo : listaTiposCampana) {
            if (Objects.equals(idTipoCampana, tipo.getId())) {
                tipoCampana = tipo;
                break;
            }
        }
        
        //intenta converir entero para determinar si es anio
        try {
            anio = Integer.parseInt(treeNodeData.toString().trim());
        } catch (NumberFormatException e) {
            anio = 0;
        }

        //validacion tipo campania no nula
        if (tipoCampana == null) {
            LOG.warn("No se encontro el tipo de campana para: " + nombreSeleccionado);
            return;
        }

        cambiarEstado(Estado.CONSULTA);
        if (anio == 0) {
            recreateModelCampana(CampanaConstants.FIND_BY_TYPE_CAMPANA,CampanaConstants.ZERO.getValue(),tipoCampana.getId());
        } else {
                recreateModelCampana(CampanaConstants.FIND_BY_YEAR_AND_TYPE_CAMPANA, anio, tipoCampana.getId());
        }
        campTreeSelect = false;
    }
    
    private void filterByTypeAndYear(int year, int campaignTypeId, String selected){
        cambiarEstado(Estado.CONSULTA);
        recreateModelCampana(CampanaConstants.FIND_BY_YEAR_AND_TYPE_CAMPANA, year, campaignTypeId); 
        
        System.out.println("buscando programa: " + selected);
        for(CampanaDTO e : this.lCampDTO){
            System.out.println("elemento en cuestion: " + e.toString());
            if(e.toString().trim().equalsIgnoreCase(selected.trim())){
                System.out.println("campana encontrada, seteando");
                setCurrent(e);
				buildProgramsDTOList(current);
                this.selectedCampanas = new CampanaDTO[]{current};
                break;
            }
        }
        campanaDataModelFiltered = lCampDTO;
        campTreeSelect = false;
    }

	
	private enum Estado {
		CONSULTA,
		ALTA,
		DETALLE,
		EDICION,
		ALTA_PROGRAMA,
	}
	
	private void cambiarEstado(Estado estado) {
		consulta = estado == Estado.CONSULTA;
		alta = estado == Estado.ALTA;
		detalle = estado == Estado.DETALLE;
		edicion = estado == Estado.EDICION;
		altaPrograma = estado == Estado.ALTA_PROGRAMA;
	}
	
	private Integer getIdTipoCampana(TreeNode nodoSeleccionado) {
		if (nodoSeleccionado.getData() instanceof CampaniaTreeRegs) {
			CampaniaTreeRegs campania = (CampaniaTreeRegs) nodoSeleccionado.getData();
			return campania.getIdTipoCampania();
		}
		else {
			for (TreeNode hijo : nodoSeleccionado.getChildren()) {
				Integer idTipoCampana = getIdTipoCampana(hijo);
				if (idTipoCampana != null) {
					return idTipoCampana;
				}
			}
		}

		return null;
	}

	private void recargarCamapanaDTO(long idCampana) throws Exception{
		TblCampana tblCampania = serviceCampana.getCampana(idCampana);
		
		if (!catgGuardada){
			current = CampanaUtil.llenadoCampanaDTO(tblCampania, tiposCampanaPorId, periodosPorId, gZonasPorId);;
			selectedCampanas = new CampanaDTO[1];
			selectedCampanas[0] = current;
		}
		
		if ("campanias".equals(section)){
			cambiarEstado(Estado.DETALLE);
		} else {
			cambiarEstado(Estado.CONSULTA);
		}
		buildProgramsDTOList(current);
	}
	
	private String getNameCategoriaById(int idCategoria){		
		CatCategory cat = serviceCatCategory.getCatCategoryById(idCategoria);
		return cat.getCode();
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
	public LazyDataModel<CampanaDTO> getCampanaDataModel() {
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
		this.selectedCampanas = selectedCampanas;
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
	public void setTipos(SelectItem[] tipos) {
		this.tipos = new SelectItem[tipos.length];
		System.arraycopy(tipos, 0, this.tipos, 0, tipos.length);
	}

	private List<String> selectedCategories;

	public List<String> getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(List<String> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}
		
	public List<CatCategory> getmCategories() {
		if (null == mCategories){
			mCategories = MBUtil.cargarcomboCategorias(serviceCatCategory);
		}
		return mCategories;
	}

	public void setCategorias(Map<String, String> categorias) {
		this.categorias = categorias;
	}
	
	public List<CategoriaDTO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoriaDTO> categoryList) {
		this.categoryList = categoryList;
	}

	public ServiceSolicitudAutorizacion getServiceSolicitudAutorizacion() {
		return serviceSolicitudAutorizacion;
	}

	public void setServiceSolicitudAutorizacion(
			ServiceSolicitudAutorizacion serviceSolicitudAutorizacion) {
		this.serviceSolicitudAutorizacion = serviceSolicitudAutorizacion;
	}

	public ServiceCadenaAutorizacion getServiceCadenaAutorizacion() {
		return serviceCadenaAutorizacion;
	}

	public void setServiceCadenaAutorizacion(
			ServiceCadenaAutorizacion serviceCadenaAutorizacion) {
		this.serviceCadenaAutorizacion = serviceCadenaAutorizacion;
	}
	
	public Date getToday() throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(sdf.format(new Date()));
	}
	
	public void setProgramas(List<ProgramaDTO> programas) {
		this.programas = programas;
	}
	
	private ServiceCatPrograma serviceCatPrograma;
	
	public List<ProgramaDTO> getProgramas() {
		try {
			if (null == programas) {
				List<CatPrograma> catProgramasList = serviceCatPrograma.getCatProgramas();
				programas = new ArrayList<ProgramaDTO>();
				for (CatPrograma catPrograma : catProgramasList) {
					ProgramaDTO programaDTO = new ProgramaDTO();
					programaDTO.setCode(catPrograma.getNombre());
					programaDTO.setId(catPrograma.getIdPrograma());
					programas.add(programaDTO);
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
        
        Collections.sort(programas, new Comparator<ProgramaDTO>() {
            @Override
            public int compare(ProgramaDTO o1, ProgramaDTO o2) {
                //return (o1.getCode() > o2.getId()) ? 1 : (o1.getId() == o2.getId()) ? 0 : -1;
                 return o1.getCode().toUpperCase().compareTo(o2.getCode().toUpperCase());
            }
        });
        
		return programas;
	}
	
	public ServiceCatPrograma getServiceCatPrograma() {
		return serviceCatPrograma;
	}
	
	public void setServiceCatPrograma(ServiceCatPrograma serviceCatPrograma) {
		this.serviceCatPrograma = serviceCatPrograma;
	}

	private ServiceCatCategory serviceCatCategory;
    
    public ServiceCatCategory getServiceCatCategory() {
        return serviceCatCategory;
    }

    public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
        this.serviceCatCategory = serviceCatCategory;
    }
    
	private ServiceCatGZone serviceCatGZone;
    
	public ServiceCatGZone getServiceCatGZone() {
		return serviceCatGZone;
	}

	public void setServiceCatGZone(ServiceCatGZone serviceCatGZone) {
		this.serviceCatGZone = serviceCatGZone;
	}
    
	private ServiceCatZone serviceCatZone;
    
	public ServiceCatZone getServiceCatZone() {
		return serviceCatZone;
	}

	public void setServiceCatZone(ServiceCatZone serviceCatZone) {
		this.serviceCatZone = serviceCatZone;
	}
	
	private ServiceCatStore serviceCatStore;
	
	public ServiceCatStore getServiceCatStore() {
		return serviceCatStore;
	}

	public void setServiceCatStore(ServiceCatStore serviceCatStore) {
		this.serviceCatStore = serviceCatStore;
	}
    
	public Map<String, String> getPlazas() {
		if (null == plazas){
			plazas = MBUtil.cargarcombos(Constants.CAT_PLACE, this.serviceDynamicCatalogs);
		}
		return plazas;
	}

	public void setPlazas(Map<String, String> plazas) {
		this.plazas = plazas;
	}
	
	public CategoriaDM getCategoriaDM() {
		return categoriaDM;
	}

	public void setCategoriaDM(CategoriaDM categoriaDM) {
		this.categoriaDM = categoriaDM;
	}

	public TblCampanaCategorias[] getCategoriasSelecteds() {
		return categoriasSelecteds;
	}

	public void setCategoriasSelecteds(TblCampanaCategorias[] categoriasSelecteds) {
		this.categoriasSelecteds = categoriasSelecteds;
	}

	public void serviceChange() {
	    LOG.info("");
	}
	
	public CategoriaDTODM getCategoriaDTODM() {
		return categoriaDTODM;
	}

	public void setCategoriaDTODM(CategoriaDTODM categoriaDTODM) {
		this.categoriaDTODM = categoriaDTODM;
	}

	public CategoriaDTO[] getCategoriaDTO() {
		return categoriaDTO;
	}

	public void setCategoriaDTO(CategoriaDTO[] categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}
	
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	public boolean isCampTreeSelect() {
		return campTreeSelect;
	}

	public void setCampTreeSelect(boolean campTreeSelect) {
		this.campTreeSelect = campTreeSelect;
	}

	public List<CampanaDTO> getCampanaDataModelFiltered() {
		return campanaDataModelFiltered;
	}

	public void setCampanaDataModelFiltered(List<CampanaDTO> campanaDataModelFiltered) {
		this.campanaDataModelFiltered = campanaDataModelFiltered;
	}
	
	public List<TblCampanaCategorias> getlTblCampCategorias() {
		return lTblCampCategorias;
	}

	public void setlTblCampCategorias(List<TblCampanaCategorias> lTblCampCategorias) {
		this.lTblCampCategorias = lTblCampCategorias;
	}

	public static class CategoriaDM extends ListDataModel<TblCampanaCategorias> implements SelectableDataModel<TblCampanaCategorias>, Serializable {

		private static final long serialVersionUID = 4417040915619389481L;

		public CategoriaDM(List<TblCampanaCategorias> lCategorias){
			super(lCategorias);
		}
		
		@Override
		public TblCampanaCategorias getRowData(String idCategoria) {
			@SuppressWarnings("unchecked")
			List<TblCampanaCategorias> lCategorias = (List<TblCampanaCategorias>) getWrappedData();;
			for (TblCampanaCategorias cat : lCategorias) {
				if (cat.getId().getIdCategoria() == Integer.valueOf(idCategoria).intValue()) {
					return cat;
				}
			}
			return new TblCampanaCategorias();
		}

		@Override
		public Object getRowKey(TblCampanaCategorias cat) {
			return cat.getId().getIdCategoria();
		}

	}
	
	public class CategoriaDTODM extends ListDataModel<CategoriaDTO>
		implements SelectableDataModel<CategoriaDTO> {
		
		public CategoriaDTODM(List<CategoriaDTO> data) {
			super(data);
		}
		
		@Override
		public CategoriaDTO getRowData(String rowKey) {
		
			@SuppressWarnings("unchecked")
			List<CategoriaDTO> categorias = (List<CategoriaDTO>) getWrappedData();
		
			for (CategoriaDTO categoriaDTO : categorias) {
				Integer idCat = categoriaDTO.getId();
				if (idCat.toString().equals(rowKey))
					return categoriaDTO;
			}
		
			return null;
		}
		
		@Override
		public Object getRowKey(CategoriaDTO categoria) {
			return categoria.getId();
		}
	}
	
    public class CampanaProgramaNode implements java.io.Serializable {
        private static final long serialVersionUID = -7900018492843927599L;
        private int idPrograma;
        private String strPrograma;
        private String strCategoria;
        private String strPlazas;
        private double ingreso;
        private String strEsSencillo;
        private String grupoZonasStr;
        private String zonasStr;
        private String tiendasStr;
        private String etapa;
        private List<String> tiendas;
        private List<String> zonas;
        private List<String> categos;
        public CampanaProgramaNode(int idPrograma, 
                                 String strPrograma,
                                 String strCategoria,
                                 String strPlazas,
                                 double ingreso,
                                 String strEsSencillo,
                                 String grupoZonasStr,
                                 String zonasStr,
                                 String tiendasStr,
                                 List<String> tiendas,
                                 List<String> zonas,
                                 List<String> categos,
                                 String etapa  ){
                this.idPrograma = idPrograma;
                this.strPrograma = strPrograma;
                this.strCategoria = strCategoria;
                this.strPlazas = strPlazas;
                this.ingreso = ingreso;
                this.strEsSencillo = strEsSencillo;
                this.grupoZonasStr = grupoZonasStr;
                this.zonasStr = zonasStr;
                this.tiendasStr = tiendasStr;
                this.tiendas = tiendas;
                this.zonas = zonas;
                this.categos = categos;
                this.etapa = etapa;
        }
        public CampanaProgramaNode(){

        }

        public List<String> getTiendas() {
            return tiendas;
        }

        public void setTiendas(List<String> tiendas) {
            this.tiendas = tiendas;
        }

        public List<String> getZonas() {
            return zonas;
        }

        public void setZonas(List<String> zonas) {
            this.zonas = zonas;
        }

        public List<String> getCategos() {
            return categos;
        }

        public void setCategos(List<String> categos) {
            this.categos = categos;
        }

        public int getIdPrograma() {
                return idPrograma;
        }
        public void setIdPrograma(int idPrograma) {
                this.idPrograma = idPrograma;
        }
        public String getStrPrograma() {
                return strPrograma;
        }
        public void setStrPrograma(String strPrograma) {
                this.strPrograma = strPrograma;
        }
        public String getStrCategoria() {
                return strCategoria;
        }
        public void setStrCategoria(String strCategoria) {
                this.strCategoria = strCategoria;
        }
        public String getStrPlazas() {
                return strPlazas;
        }
        public void setStrPlazas(String strPlazas) {
                this.strPlazas = strPlazas;
        }
        public double getIngreso() {
                return ingreso;
        }
        public void setIngreso(double ingreso) {
                this.ingreso = ingreso;
        }
        public String getStrEsSencillo() {
                return strEsSencillo;
        }
        public void setStrEsSencillo(String strEsSencillo) {
                this.strEsSencillo = strEsSencillo;
        }
        public String getGrupoZonasStr() {
                return grupoZonasStr;
        }
        public void setGrupoZonasStr(String grupoZonasStr) {
                this.grupoZonasStr = grupoZonasStr;
        }
        public String getZonasStr() {
                return zonasStr;
        }
        public void setZonasStr(String zonasStr) {
                this.zonasStr = zonasStr;
        }
        public String getTiendasStr() {
                return tiendasStr;
        }
        public void setTiendasStr(String tiendasStr) {
                this.tiendasStr = tiendasStr;
        }

        public String getEtapa() {
            return etapa;
        }

        public void setEtapa(String etapa) {
            this.etapa = etapa;
        }
    }
	
	@SuppressWarnings("unchecked")
	public void valueChangeAdjDisenoGrupoZona(ValueChangeEvent e) {
        System.out.println("Ejectuando: valueChangeAdjDisenoGrupoZona" );
		changeGrupoZonas((List<String>) e.getNewValue());
		RequestContext requestContext = RequestContext.getCurrentInstance();  
		requestContext.execute("loader.hide()");
	}
	
	private void changeGrupoZonas(List<String> lz) {
        List<CatZone> catZoneList;
        List<CatZone> zonas = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        if (lz != null) {
            for (String gz : lz) {
            	ids.add(Integer.valueOf(gz));
            }
            catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
            if (catZoneList != null) {
            	zonas.addAll(catZoneList);
            }
        }
        Collections.sort(zonas, new CatZoneSorter());
        mZones = zonas;
        getCurrentProgramaDTO().setGrupoZonas(lz);
	}

	private void changeZonas(List<String> lz) {
        List<CatStore> stores = new ArrayList<>();
        currentProgramaDTO.setTiendas(new ArrayList<String>());
        List<Integer> ids = new ArrayList<>();
        if (lz != null) {
            for (String gz : lz) {
            	ids.add(Integer.valueOf(gz));
            }
            
        }
        List<CatStore> catStoreList = serviceCatStore.getCatStoreListByZone(ids);
        if (catStoreList!= null && !catStoreList.isEmpty()) {
        	stores.addAll(catStoreList);
        }
        for (CatStore cs : stores){
            currentProgramaDTO.getTiendas().add(String.valueOf(cs.getIdStore()));
        }
        
        Map<Integer, CatStore> storesMap = new HashMap<Integer, CatStore>();
            for(CatStore e : stores){
                if(storesMap.containsKey(e.getIdStore())){
                    continue;
                }else{
                    storesMap.put(Integer.parseInt(String.valueOf(e.getIdStore())), e);
                }
            }
            
            List<CatStore> l = new ArrayList<CatStore>();
            for (Map.Entry<Integer, CatStore> entry : storesMap.entrySet()){
                l.add(entry.getValue());
            }
        stores = l;
        Collections.sort(stores, new CatStoreSorter());
        mStores = stores;
        getCurrentProgramaDTO().setZonas(lz);
	}

	@SuppressWarnings("unchecked")
	public void valueChangeAdjDisenoZona(ValueChangeEvent e) {
		changeZonas((List<String>)e.getNewValue());
		RequestContext requestContext = RequestContext.getCurrentInstance();  
		requestContext.execute("loader.hide()");
	}
	
	public Map<String, String> getmZonas() {
		return mZonas;
	}

	public void setmZonas(Map<String, String> mZonas) {
		this.mZonas = mZonas;
	}

	public Map<String, String> getmTiendas() {
		return mTiendas;
	}

	public void setmTiendas(Map<String, String> mTiendas) {
		this.mTiendas = mTiendas;
	}

	public boolean isAltaPrograma() {
		return altaPrograma;
	}

	public void setAltaPrograma(boolean altaPrograma) {
		this.altaPrograma = altaPrograma;
	}


    public Map<String, String> getmGruposZona() {
        return mGruposZona;
    }

    public void setmGruposZona(Map<String, String> mGruposZona) {
        this.mGruposZona = mGruposZona;
    }

	public List<CatGZone> getmGruposZone() {
		return mGruposZone;
	}

	public void setmGruposZone(List<CatGZone> mGruposZone) {
		this.mGruposZone = mGruposZone;
	}

	public List<CatZone> getmZones() {
		return mZones;
	}

	public void setmZones(List<CatZone> mZones) {
		this.mZones = mZones;
	}

	public List<CatStore> getmStores() {
		return mStores;
	}

	public void setmStores(List<CatStore> mStores) {
		this.mStores = mStores;
	}

	public void setmCategories(List<CatCategory> mCategories) {
		this.mCategories = mCategories;
	}

	public List<TblCampana> getListCampanasCache() {
		return listCampanasCache;
	}

	public void setListCampanasCache(List<TblCampana> listCampanasCache) {
		this.listCampanasCache = listCampanasCache;
	}

    public boolean isTituloEditarProg() {
        return tituloEditarProg;
    }

    public void setTituloEditarProg(boolean tituloEditarProg) {
        this.tituloEditarProg = tituloEditarProg;
    }

    public ServiceCatEtiquetas getServiceCatEtiquetas() {
        return serviceCatEtiquetas;
    }

    public void setServiceCatEtiquetas(ServiceCatEtiquetas serviceCatEtiquetas) {
        this.serviceCatEtiquetas = serviceCatEtiquetas;
    }

    /**
     * @return the selectedCampainType
     */
    public String getSelectedCampainType() {
        return selectedCampainType;
    }

    /**
     * @param selectedCampainType the selectedCampainType to set
     */
    public void setSelectedCampainType(String selectedCampainType) {
        this.selectedCampainType = selectedCampainType;
    }
    
    public void postProcessCategoryXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setWrapText(true);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
        
        for(int colNum = 0; colNum<header.getLastCellNum();colNum++){
            wb.getSheetAt(0).autoSizeColumn(colNum);
        }
        
        int columns = header.getPhysicalNumberOfCells();
        LOG.info("total de columnas: " + columns);
        Iterator rowIter = sheet.iterator();
        while (rowIter.hasNext()) {
           HSSFRow row = (HSSFRow)rowIter.next();
           for(int i=0; i < header.getPhysicalNumberOfCells(); i++){
                HSSFCell cell = row.getCell(i);
                String content = cell.getStringCellValue();
                LOG.info("contenido : " + content);
                if(content.contains("<") || content.contains(">")){
                    LOG.info("eliminando");
                    row.removeCell(cell);
                }
           }
        }
    }
    
    public ServiceCatEstatus getServiceCatEstatus() {
        return serviceCatEstatus;
    }

    public void setServiceCatEstatus(ServiceCatEstatus serviceCatEstatus) {
        this.serviceCatEstatus = serviceCatEstatus;
    }
    
    /**
     * @return the selectedCampainOnGrid
     */
    public String getSelectedCampainOnGrid() {
        return selectedCampainOnGrid;
    }

    /**
     * @param selectedCampainOnGrid the selectedCampainOnGrid to set
     */
    public void setSelectedCampainOnGrid(String selectedCampainOnGrid) {
        this.selectedCampainOnGrid = selectedCampainOnGrid;
    }

}
