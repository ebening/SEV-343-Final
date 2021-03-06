package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.ArqSevenTreeNode;
import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.CatActPred;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatEstatus;
import com.adinfi.seven.business.domain.CatEtiquetas;
import com.adinfi.seven.business.domain.CatFlujoAct;
import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatListDet;
import com.adinfi.seven.business.domain.CatLista;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.CatPromo;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.business.domain.CatTipoPromo;
import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.Componente;
import com.adinfi.seven.business.domain.DetalleComponenteZona;
import com.adinfi.seven.business.domain.RelDisenoSenal;
import com.adinfi.seven.business.domain.RelFlujoAct;
import com.adinfi.seven.business.domain.RelGrupoZonaCampana;
import com.adinfi.seven.business.domain.RelItemLista;
import com.adinfi.seven.business.domain.RelItemStore;
import com.adinfi.seven.business.domain.RelStoreCampana;
import com.adinfi.seven.business.domain.RelZonaCampana;
import com.adinfi.seven.business.domain.RelZoneStore;
import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;
import com.adinfi.seven.business.domain.TblMecanica;
import com.adinfi.seven.business.domain.sort.CatStoreSorter;
import com.adinfi.seven.business.domain.sort.CatZoneSorter;
import com.adinfi.seven.business.domain.sort.SortUtils;
import com.adinfi.seven.business.services.ServiceArquitecturaSeven;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceCatActPred;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatEstatus;
import com.adinfi.seven.business.services.ServiceCatGZone;
import com.adinfi.seven.business.services.ServiceCatItem;
import com.adinfi.seven.business.services.ServiceCatListDet;
import com.adinfi.seven.business.services.ServiceCatLista;
import com.adinfi.seven.business.services.ServiceCatPrograma;
import com.adinfi.seven.business.services.ServiceCatPromo;
import com.adinfi.seven.business.services.ServiceCatProveedor;
import com.adinfi.seven.business.services.ServiceCatSenal;
import com.adinfi.seven.business.services.ServiceCatStore;
import com.adinfi.seven.business.services.ServiceCatSubCategory;
import com.adinfi.seven.business.services.ServiceCatTipoPromo;
import com.adinfi.seven.business.services.ServiceCatZone;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceRelItemLista;
import com.adinfi.seven.business.services.ServiceRelItemStore;
import com.adinfi.seven.business.services.ServiceRelUsuariosCategorias;
import com.adinfi.seven.business.services.ServiceTblActividades;
import com.adinfi.seven.business.services.ServiceUsuarios;
import com.adinfi.seven.business.services.admin.ServiceMenuAndRoles;
import com.adinfi.seven.persistence.dto.ColumnModel;
import com.adinfi.seven.persistence.dto.ComponenteDTO;
import com.adinfi.seven.persistence.dto.ConfMecanicaDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO.RelObj;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.GenericItemString;
import com.adinfi.seven.persistence.dto.MecanicaDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.PreciosPromocionDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.EtapaDashboard;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;

public class MBArquitectura implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger LOG = Logger.getLogger(MBArquitectura.class);
    private String msgInfoFaltante = "Falta informaci?n por capturar, revise e int?ntelo de nuevo. Campo: @CAMPO@.";
    private CatUsuarios usuarioLogeado;
    private int rolUsuario;
    private TreeNode raiz;
    private TreeNode selectedNode;
    private TreeNode raizPrecio;
    private TreeNode raizPrecionSeleccionado;
    private TreeNode raizPrecioPop;
    private TreeNode raizDisenio;
    private TreeNode tProgramaDisenio;
    private ServiceDynamicCatalogs serviceDynamicCatalogs;
    private ServiceArquitecturaSeven serviceArquitecturaSeven;    
    private ServiceMenuAndRoles serviceMenuAndRoles;
    private ServiceRelItemStore serviceRelItemStore; 
    private String pageWork;
    private List<DetalleComponenteZona> lDetCompZona;
    private DetalleComponenteZona detCompZonaSel;
    private ArqPeriodo arqPeriodo;
    private ArqMecanica arqMecanica;
    private ArqComponente arqComponente;
    private String nomNvaMecanica;
    private String nomNvoComponenete;
    private boolean mostrarOtroSen;
    private int impPeriodoId;
    private int impProgranaId;
    private int impGrupoZonaId;
    private int impZonaId;
    private int impMecanicaId;
    private boolean habilitarComp;
    private boolean disableEspacioComp = false;
    private boolean disableForList = false;
    private boolean disableList = false;
    private boolean puedeAgregarComp;
    private ArqComponente arqComponenteSel;
    private MecanicaDTO mecanicaDTO;

    private double precPromo;
    private double porcPromo;
    private double ahorroFijo;
    private double recuProveCant;
    private double recuProvePorc;
    //campos para distribucion de rebaja
    private double cantidadDistribucionRebaja;
    private double porcentajeDistribucionRebaja;
    
    private double objetivo;
    private String comenDisenio;
    
    /*nuevos valores de precio promocion*/
    private double precioPromocionIVA;
    private double precioPromocionImpuesto;
    private double precioPromocionRegularNuevo;

    private String nomPromoPrec;
    private int numPromoPrec;
    private String listaPrec;
    private double precPromoPrec;
    private int idStatusRevPrec;
    private int idStatusCapPrec;
    private int idStatusRevision;
    private int idStatusCaptura;
    private List<SelectItem> lStatusRevision;
    private List<SelectItem> lStatusCaptura;

    private List<SelectItem> lProgramas;
    private Map<String, String> mCategorias;
    private List<SelectItem> lPromociones;
    private List<SelectItem> lTipoPromociones;
    private Map<String, String> mSenialamientos;
    private Map<String, String> mGruposZona;
    private List<SelectItem> lGruposZona;
    
    //actualizado de estatus todos los grids
    private int estatusCapturaAll;
    private int estatusRevisionAll;

    private ServiceCampana serviceCampana;

    /**
     * Lista para grupos de zona para dialogos de adjuntar y ver
     * dise?o, y secci?n de componente y mec?nica.
     */
    private List<CatGZone> mGruposZone;
    private List<CatZone> mZones;
    /**
     * Lista de tiendas para combo en seccion de mecanica.
     */
    private List<CatStore> mStores;
    /**
     * Lista de categorias para combo en seccion de promocional.
     */
    private List<CatCategory> mCategories;
    private List<CatSenal> mCatSignals;

    private List<CatGZone> lGroupZoneImp;
    private List<CatZone> lZoneImp;
    private List<CatPrograma> lProgramaImp;
    private List<CatPrograma> lPrograms;
    private List<CatProveedor> lCatProveedor;
    private List<CatSubCategory> lCatSubCategories;
    private List<CatLista> lCatListas;
    /**
     * Lista de categorias para combos en arquitectura en componente y dialgo de
     * primicia.
     */
    private List<CatCategory> lCatCategories;

    private List<CatPrograma> lCatProgramasPrec;
    private List<CatCategory> lCatCategoriasPrec;
    private List<CatSubCategory> lCatSubCategoriasPrec;
    private List<CatGZone> mCatGruposZonaPrec;
    private List<CatZone> mCatZonasPrec;
    private List<CatStore> mCatTiendasPrec;
    private List<CatLista> lCatListasPrec;
    private List<CatCategory> lCatCategEstra;
    private List<CatCategory> lCatCategEstra2;
    private List<CatGZone> mCatGposZonaEstra;
    private List<CatZone> mCatZonasEstra;

    private List<CatPromo> lCatPromociones;
    private List<CatTipoPromo> lCatTipoPromociones;

    private ServiceCatPromo serviceCatPromo;
    private ServiceCatTipoPromo serviceCatTipoPromo;

    private Map<String, String> mZonas;
    private List<SelectItem> lZonas;
    private Map<String, String> mTiendas;
    private List<SelectItem> lProveedores;
    private List<SelectItem> lSubCategorias;
    private List<SelectItem> lDescripciones;
    private Map<String, String> mSku;
    private Map<String, String> mUpc;
    private List<String> lUpcComp;
    private List<SelectItem> lListas;
    private List<SelectItem> lEspaciosPromocionales;
    private List<SelectItem> lCategorias;
    private List<SelectItem> lComponentes;
    
    private List<SelectItem> lComponentesPrecioPromocion;
    private int componentePrecioPromocionSeleccionado;
    
    private List<SelectItem> lMecanica;
    private List<SelectItem> lPeriodos;
    private List<SelectItem> lUnidades;
    private List<ArqComponente> lArqComponente;
    private List<ArqComponente> lArqComponenteZona;
    private List<ArqComponente> lArqCompPrecProm;
    private List<ArqComponente> lArqPreciosPrint;

    private List<SelectItem> lProgramasImp;
    private List<SelectItem> lGruposZonaImp;
    private List<SelectItem> lZonasImp;
    private List<SelectItem> lMecanicaImp;

    private List<SelectItem> lProgramasPrec;
    private String senialamientosPrec;
    private Map<String, String> mGruposZonaPrec;
    private Map<String, String> mZonasPrec;
    private Map<String, String> mTiendasPrec;
    private List<SelectItem> lCategoriasPrec;
    private List<SelectItem> lSubCategoriasPrec;
    private List<SelectItem> lDescripcionesPrec;
    private Map<String, String> mSkuPrec;
    private Map<String, String> mUpcPrec;
    private List<SelectItem> lListasPrec;
    private List<SelectItem> lComponentesPrec;
    private List<SelectItem> lMecanicaPrec;

    private int idProgPrec;
    private List<String> lGrupoZonasPrec;
    private List<String> lZonasPrec;
    private List<String> lTiendasPrec;
    private int idCatPrec;
    private int idSubCatPrec;
    private int idDescPrec;
    private List<String> lSkuPrec;
    private List<String> lUpcPrec;
    private int idListaPrec;
    private int idCompPrec;
    private int idMecanicaPrec;

    private int idStatusPrecio;
    private int idStatusDisenio;
    private List<SelectItem> lStatusPrecio;
    private List<SelectItem> lStatusDisenio;

    private boolean agregarPromocion;
    private boolean precioPromocion;
    private boolean precio;
    private boolean disenio;
    private boolean estrategia;

    private int numCompPrimicia;
    private String descPrimicia;
    private int categPrimicia;
    private String upcPrimicia;
    private double precioPrimicia;

    // Estrategia
    private int idListaEstra;
    private List<SelectItem> lDescEstra;
    private int idDescEstra;
    private List<SelectItem> lSKUEstra;
    private List<CatListDet> lCatSKUEstra;
    private int idSkuEstra;
    private List<SelectItem> lUpcEstra;
    private int idUpcEstra;
    private List<SelectItem> lCategEstra;
    private int idCategEstra;

    private List<SelectItem> lDescEstra2;
    private int idDescEstra2;
    private List<SelectItem> lSKUEstra2;
    private List<CatListDet> lCatSKUEstra2;
    private int idSkuEstra2;
    private List<SelectItem> lUpcEstra2;
    private int idUpcEstra2;
    private List<SelectItem> lCategEstra2;
    private int idCategEstra2;
    private Map<String, String> mGposZonaEstra;
    private List<String> lGposZonaEstra;
    private Map<String, String> mZonasEstra;
    private List<String> lZonasEstra;

    private List<Object> lEstraZona;
    private List<Object> lEstrategiaProd;

    private ConfigMecanica configMecanica;
    private List<String> lZonaXGrupo;

    private List<Map<String, String>> responseArray;
    List<ColumnModel> columns;
    private List<Map<String, String>> responseArray2;
    List<ColumnModel> columns2;

    // VER DISE?O
    private int verDisenoProgramaId;
    private List<SelectItem> lMecanicaVerDiseno;
    private int verDisenoIdMecanica;
    private List<String> verDisenoLSenalamiento;
    private int verDisenoIdSenalamiento;
    private List<String> verDisenoGrupoZonas;
    private List<String> verDisenoZonas;
    private Map<String, String> mZonasVerDiseno;
    /**
     * Lista de zonas para combo en dialogo adjuntar y ver dise?o. Filtrado en
     * base a grupos de zonas seleccionados.
     */
    private List<CatZone> mCatZonasVerDiseno;
    private List<String> verDisenoTiendas;
    private Map<String, String> mTiendasVerDiseno;
    private List<CatStore> mCatTiendasVerDiseno;
    private Integer verDisenoAutorizado;
    private String verDisenoComentarios;
    private List<DisenosDTO> disenosLst;
    private DisenosDTO disenoDTO = new DisenosDTO();
    private List<CatItem> listCatItem;
    private List<CatItem> listCatItemDesc;
    private List<CatSenal> senalListSelected;
    private List<String> senalListSelectedStr;

    
    private int numCategorias;
    private double ahorroMaximo;

    private ServiceCatCategory serviceCatCategory;
    private ServiceCatProveedor serviceCatProveedor;
    private ServiceCatSubCategory serviceCatSubCategory;
    private ServiceCatLista serviceCatLista;
    private ServiceCatSenal serviceCatSenal;
    private ServiceCatItem serviceCatItem;
    private ServiceRelItemLista serviceRelItemLista;

    private List<CatListDet> comboSKU;
    private List<CatListDet> comboUPC;
    private List<CatListDet> mCatSku;
    private List<CatListDet> mCatSkuPrec;
    private ServiceCatListDet serviceCatListDet;
    private List<CatListDet> comboSKUByProv;
    private List<CatListDet> comboSKUByFamilia;
    private List<CatListDet> comboUPCByProv;
    private List<CatListDet> comboUPCByFamilia;
    
    
    
    private String descZona;
    private String itemIDSelected  = "";
    private List<String> itemIdList = new ArrayList<String>();
    
    private DefaultTreeNode parentSelected;
    private ArqSevenTreeNode nodoSeleccionado;
    private ServiceRelUsuariosCategorias serviceRelUsuariosCategorias;
    private String labelDisenoGlobal;
    private Integer idStatusDisenoGlobal;
    private String titleDise?oGlobal;
    
    private String valueDesignDescription;
    private List<String> lDesginDescriptions;
    
    
    private Double porcentajeDescuento;
    private Double porcentajeRetencion;
    private Double elasticidad;
    
        
    private String porcentajeDescuentoStr;
    private String porcentajeRetencionStr;
    private String elasticidadStr;
    
    private Boolean esCombo = new Boolean(false);
    
    private Boolean esCategory = new Boolean(false);
    
    private String espacioPromocionalErrorClass = "";
    
    private List<CatZone> ppCatZones = new ArrayList<CatZone>();
    private String ppCatZoneSelected;
    
    private List<String> ppCatZonesSelected = new ArrayList<String>();

    private boolean validarPrecioPromocion = true;
    private boolean overridePrecioPromocion = false;

    public MBArquitectura() {
        LOG.info("*** CONSTRUCTO MBArquitectura ***");
    }

    @PostConstruct
    public void init() {
    	long start = System.currentTimeMillis();
        usuarioLogeado = Util.getSessionAttribute("userLoged");
        if(usuarioLogeado != null){
            rolUsuario = usuarioLogeado.getCatRole() != null  ? usuarioLogeado.getCatRole().getIdrole() : 0;
            generarMenu(usuarioLogeado);
            initCombos();
            arqComponente = new ArqComponente();
            arqMecanica = new ArqMecanica();
            arqPeriodo = new ArqPeriodo();
            raizPrecio = new DefaultTreeNode("root",null);
            raizDisenio = new DefaultTreeNode("root", null);
            lArqCompPrecProm = new ArrayList<ArqComponente>();
        }
        LOG.info("Total time: " + (System.currentTimeMillis() - start) + " ms.");
    }

    private void initCombos() {
            try {
                lPrograms = getProgramas();


                lCatPromociones = MBUtil.cargarComboCatPromo(serviceCatPromo);
                lCatTipoPromociones = MBUtil .cargarComboCatTipoPromo(serviceCatTipoPromo);
                mCatSignals = MBUtil.cargarcomboSenal(serviceCatSenal);
                mGruposZone = MBUtil.cargarcomboGrupoZonas(serviceCatGZone);

                mZones = new ArrayList<>();
                mStores = new ArrayList<>();

                    //lCatProveedor = new ArrayList<>();
                lCatProveedor = serviceCatProveedor.getCatProveedorList();
                lSubCategorias = new ArrayList<>();
                lCatSubCategories = new ArrayList<>();

                listCatItem = this.serviceCatItem.getCatItemList();

                lCatListas = MBUtil.cargarcomboCatListas(serviceCatLista);

                lEspaciosPromocionales = MBUtil.getSelectItemsByCatalog(serviceDynamicCatalogs, Constants.CAT_ESP_PROMO);

                lCatCategories = MBUtil.cargarcomboCategorias(serviceCatCategory);

                lComponentes = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    lComponentes.add(new SelectItem(i + 1, String.valueOf(i + 1)));
                }
                lUnidades = MBUtil.getSelectItemsByCatalog(serviceDynamicCatalogs, Constants.CAT_UNIDADES);

                lProgramaImp = getProgramas();

                lGroupZoneImp = MBUtil.cargarcomboGrupoZonas(serviceCatGZone);

            } catch (Exception e) {
                    LOG.error(e);
            }
    }

    private ServiceCatPrograma serviceCatPrograma;

    public List<CatPrograma> getProgramas() {
            List<CatPrograma> catProgramasList = new ArrayList<>();
            try {
                    catProgramasList = serviceCatPrograma.getCatProgramas();

                    if (catProgramasList != null) {
                            return catProgramasList;
                    }
            } catch (Exception e) {
                    LOG.error(e);
            }
            return catProgramasList;
    }

    public ServiceCatPrograma getServiceCatPrograma() {
		return serviceCatPrograma;
	}

    public List<CatListDet> getComboSKU() {
		return comboSKU;
	}

	public void setComboSKU(List<CatListDet> comboSKU) {
		this.comboSKU = comboSKU;
	}

	public List<CatListDet> getComboUPC() {
        return comboUPC;
    }

    public void setComboUPC(List<CatListDet> comboUPC) {
        this.comboUPC = comboUPC;
    }

    public String getItemIDSelected() {
        return itemIDSelected;
    }

    public void setItemIDSelected(String itemIDSelected) {
        this.itemIDSelected = itemIDSelected;
    }

    public void setServiceCatPrograma(ServiceCatPrograma serviceCatPrograma) {
            this.serviceCatPrograma = serviceCatPrograma;
    }

    
    public List<CatListDet> getComboSKUByProv() {
		return comboSKUByProv;
	}

	public void setComboSKUByProv(List<CatListDet> comboSKUByProv) {
		this.comboSKUByProv = comboSKUByProv;
	}
	
	public List<CatListDet> getComboSKUByFamilia() {
		return comboSKUByFamilia;
	}

	public void setComboSKUByFamilia(List<CatListDet> comboSKUByFamilia) {
		this.comboSKUByFamilia = comboSKUByFamilia;
	}

	public List<CatListDet> getComboUPCByProv() {
		return comboUPCByProv;
	}

	public void setComboUPCByProv(List<CatListDet> comboUPCByProv) {
		this.comboUPCByProv = comboUPCByProv;
	}

	public List<CatListDet> getComboUPCByFamilia() {
		return comboUPCByFamilia;
	}

	public void setComboUPCByFamilia(List<CatListDet> comboUPCByFamilia) {
		this.comboUPCByFamilia = comboUPCByFamilia;
	}

	private void initCombosPrec() {
            try {
                    lCatProgramasPrec = new ArrayList<>();
                    lCatProgramasPrec.add(serviceCatPrograma.getCatPrograma(arqMecanica.getIdPrograma()));
            //        lCatCategoriasPrec = MBUtil.cargarcomboCategorias(serviceCatCategory);
           //         mCatGruposZonaPrec = MBUtil.cargarcomboGrupoZonas(serviceCatGZone);
                    cargaComboCategoryByMecanica(arqMecanica.getIdMecanica());
                    cargaComboGZPrecProm();
                    cargaCombosPrecProm();
            //        mCatZonasPrec = new ArrayList<>();
            //        lGrupoZonasPrec = new ArrayList<>();
                    
                    lZonasPrec = new ArrayList<>();
            } catch (Exception e) {
                    LOG.error(e);
            }
    }

    private void initCombosDisenio() {
        try {
            lCatProgramasPrec = new ArrayList<>();
            if (arqMecanica.getIdPrograma() != 0){
                lCatProgramasPrec.add(serviceCatPrograma.getCatPrograma(arqMecanica.getIdPrograma()));
            }
                    
            cargaComboCategoryByMecanica(arqMecanica.getIdMecanica());
            cargaComboGZPrecProm();
            cargaCombosPrecProm();
            
            /* asigno valor base */
            if(lCatCategoriasPrec != null && lCatCategoriasPrec.size() > 0){
                idCatPrec = lCatCategoriasPrec.get(0).getIdCategory();
            }
            
            

            idMecanicaPrec = 0;
            idCatPrec = 0;
            idStatusPrecio = 0;
            idStatusDisenio = 0;
            RequestContext.getCurrentInstance().update("formAdjDiseno:cmbSenalDiseno");
        } catch (Exception e) {
            LOG.error(e);
        }
    }
    
    private void cargaComboGZPrecProm(){
    		List<CatGZone> temp = MBUtil.cargarcomboGrupoZonas(serviceCatGZone);
    		List<String> lgz = arqMecanica.getlGrupoZonas();
    		mCatGruposZonaPrec = new ArrayList<>();
    		for(CatGZone cgz : temp){
    			if(lgz.contains(String.valueOf(cgz.getIdGrupoZona()))){
    				mCatGruposZonaPrec.add(cgz);
    			}
    		}
    }
    
    private void cargaCombosPrecProm(){
		mCatZonasPrec = new ArrayList<>();
		List<CatZone> catZoneList;
		List<String>lgz = arqMecanica.getlGrupoZonas();
		List<String>lz = arqMecanica.getlZonas();
		try {
			catZoneList = serviceCatZone.getCatZoneList();
			if (lgz.size() > 0) {
				for (String idGZone : lgz) {
					for (CatZone z : catZoneList) {
						if (z.getCatGZone().getIdGrupoZona() == Integer.valueOf(idGZone) && lz.contains(String.valueOf(z.getIdZone()))) {
							mCatZonasPrec.add(z);
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
    }
    
    private void cargaComboCategoryByMecanica(int idMecanica){
    		List<CatCategory> lstCategorias = MBUtil.cargarcomboCategorias(serviceCatCategory);
    		MecanicaDTO mDTO = serviceArquitecturaSeven.getMecanica(idMecanica);
        
        List<ComponenteDTO> lstG = mDTO.getComponenteList();

        Set<Integer> catDeComponentes = new HashSet<>();

        if(lstG != null){
        		List<CatCategory> tempCategories = new ArrayList<>();
            	for(ComponenteDTO dto : lstG){
            		catDeComponentes.add(dto.getIdCateg());
            	}
            	if (lstG.size() > 0) {
                    for (CatCategory cat : lstCategorias) {
                            if (catDeComponentes.contains(cat.getIdCategory())) {
                                    tempCategories.add(cat);
                            }
                    }
            }
            lCatCategoriasPrec = tempCategories;
        }
    }

    private void initInfoPrecProm() {
        LOG.info("ejectuando: initInfoPrecProm");
        try {
            LOG.info("cargando programas");
            lCatProgramasPrec = getProgramas();
            senialamientosPrec = "";
            if (arqMecanica.getlSenalamiento() != null) {
                LOG.info("iterando sobre los se?alamientos de la mecanica");
                for (String sen : arqMecanica.getlSenalamiento()) {
                    if (sen.equals("0")) { 
                        senialamientosPrec = senialamientosPrec + arqMecanica.getOtroSenalamiento() + ", ";
                    } else {
                        senialamientosPrec = senialamientosPrec + this.serviceCatSenal.getCatSenal(Integer.valueOf(sen)).getNombre() + ", ";
                    }
                }
            }
            LOG.info("procesando se?alamientos resultantes");
            if (!senialamientosPrec.isEmpty()) {
                    senialamientosPrec = senialamientosPrec.substring(0, senialamientosPrec.lastIndexOf(","));
            }
           
            LOG.info("cargando combos");
            cargaComboGZPrecProm();
            cargaCombosPrecProm();
            
            mCatTiendasPrec = new ArrayList<>();
            lCatCategoriasPrec = new ArrayList<>();

            int idMecanica = arqMecanica.getIdMecanica();
            LOG.info("Cargando categorias por mecanica");
            cargaComboCategoryByMecanica(idMecanica);
            lSkuPrec = new ArrayList<>();
            lUpcPrec = new ArrayList<>();
            mCatSkuPrec =  new ArrayList<>();
            lCatSubCategoriasPrec = new ArrayList<>();

            List<GenericItem> lGi = MBUtil.genericSearch(Constants.ATT_CODE,Constants.ATT_MARCA, Constants.CAT_ITEM, serviceDynamicCatalogs);
            mUpcPrec = new HashMap<>();
            if (lGi != null) {
                for (GenericItem gi : lGi) {
                    mUpcPrec.put(String.valueOf(gi.getId()), String.valueOf(gi.getId()));
                }
            }

            idProgPrec = 0;
            
            LOG.info("cargando listados a partir de arqMecanica");
            //lGrupoZonasPrec = new ArrayList<>();
            lGrupoZonasPrec = arqMecanica.lGrupoZonas;
            //lZonasPrec = new ArrayList<>();
            lZonasPrec = arqMecanica.lZonas;
            //lTiendasPrec = new ArrayList<>();
            lTiendasPrec = arqMecanica.lTiendas;
            
            idCatPrec = 0;
            idSubCatPrec = 0;
            idDescPrec = 0;

            idListaPrec = 0;
            idCompPrec = 0;
            
            if (arqMecanica != null) {
                    idMecanicaPrec = arqMecanica.getIdMecanica();
            } else {
                    idMecanicaPrec = 0;
            }
            comenDisenio = arqMecanica.getComentario();

        } catch (NumberFormatException | GeneralException e) {
            LOG.error(e);
        }
    }

    private void generarMenu(CatUsuarios user) {
        mCategories = MBUtil.cargarcomboCategorias(serviceCatCategory);
    	lPeriodos = new ArrayList<>();
        raiz = new DefaultTreeNode("raiz", null);
        Map<String,TreeNode> treeMap = new HashMap<String,TreeNode>();
        boolean validaCategorias = user.getCatRole().getIdrole() != 1;
    	List<Object[]> items = serviceMenuAndRoles.getMenuByUsuario(user.getIdusuarios());
    	for (Object[] data : items) {
    		CampaniaTreeRegs campaniaTreeRegs = (CampaniaTreeRegs) data[0];
    		TblCampana tblCampana = (TblCampana) data[1];
    		CatEtiquetas catEtiquetas = (CatEtiquetas) data[2];
    		TblCampanaProgramas tblCampProg =  (TblCampanaProgramas) data[3];
    		CatPrograma catPrograma = (CatPrograma) data[4]; 
    		lPeriodos.add(new SelectItem(Long.valueOf(tblCampana.getIdCampana()).intValue(), tblCampana.getNombre()));
    		Integer idMecanica= (Integer) data[5];
    		String mecanica = (String) data[6];
    		boolean authPrograma = data[7].equals("Y");
    		boolean authMecanica = data[8].equals("Y");
    		String key = campaniaTreeRegs.getTipoCampania();
            TreeNode typeNode = treeMap.get(key);
            if(typeNode == null) {
                typeNode = new DefaultTreeNode(new ArqSevenTreeNode(campaniaTreeRegs.getTipoCampania(), "#000000", null, null, null), raiz);
                treeMap.put(key, typeNode);
            }
            key += campaniaTreeRegs.getAnio();
            TreeNode yearNode = treeMap.get(key);
            if (yearNode == null) {
            	yearNode = new DefaultTreeNode(new ArqSevenTreeNode(String.valueOf(campaniaTreeRegs.getAnio()), "#000000", null, null, null), typeNode);
            	treeMap.put(key, yearNode);
            }
            key += tblCampana.getNombre();
            TreeNode campanaNode = treeMap.get(key);
            if (campanaNode == null) {
            	campanaNode = new DefaultTreeNode(new ArqSevenTreeNode(tblCampana.getNombre(), catEtiquetas.getCodigo(), tblCampana, null, null), yearNode);
            	treeMap.put(key, campanaNode);
            }
            if (catPrograma != null && (!validaCategorias || authPrograma)) {
            	key += catPrograma.getNombre();
            	TreeNode progNode = treeMap.get(key);
                if (progNode == null) {
                	progNode = new DefaultTreeNode(new ArqSevenTreeNode(catPrograma.getNombre(), catEtiquetas.getCodigo(), tblCampana, tblCampProg, null), campanaNode);
                	treeMap.put(key, progNode);
                }
            	if (mecanica != null && (!validaCategorias || authMecanica)) {
            		TblMecanica tblMecanica = new TblMecanica();
            		tblMecanica.setCampana(tblCampana);
            		tblMecanica.setProgramaId(catPrograma.getIdPrograma());
            		tblMecanica.setMecanicaId(idMecanica);
            		tblMecanica.setNombreMecanica(mecanica);
            		new DefaultTreeNode(new ArqSevenTreeNode(mecanica, catEtiquetas.getCodigo(), tblCampana, tblCampProg, tblMecanica), progNode);
            	}
            }
    	}
    }

    private void completeCampaignStructure(TreeNode element){
        //si no es una hoja, itera recursivamente
        if(!element.isLeaf()){
            for(TreeNode child : element.getChildren()){
                this.completeCampaignStructure(child);
            }
        }else{
            CampaniaTreeRegs promotionalPeriod = (CampaniaTreeRegs)element.getData();
            int campaignId = promotionalPeriod.getId();
            LOG.info("Id de campania: " + campaignId);
        }
    }

    
    public void onNodeSelect(NodeSelectEvent event) {
    	long start = System.currentTimeMillis();
        esCategory = false;
        TreeNode nodeSelected = event.getTreeNode();
        if (!nodeSelected.isLeaf()){
            Messages.mensajeAlerta("Seleccione una mecanica para continuar","Seleccione una mecanica para continuar");
            return;
        }
        nodoSeleccionado = (ArqSevenTreeNode) nodeSelected.getData();
        DefaultTreeNode temp = (DefaultTreeNode) nodeSelected.getParent();
        parentSelected =(DefaultTreeNode) (temp.getParent().getData().equals("raiz") ?  nodeSelected : nodeSelected.getParent());
        mostrarOtroSen = false;
        lArqComponente = new ArrayList<>();

        switch (rolUsuario) {
            case Constants.ADMINISTRADOR:
                if (nodoSeleccionado != null && nodoSeleccionado.getTblCampana() != null && nodoSeleccionado.getTblCampanaProgramas() != null) {
                    prepareCategoryView();
                } else {
                    changeVariablesBoolean(false);
                }
                break;
            case Constants.CATEGORY:
                esCategory = true;
                if (nodoSeleccionado != null && nodoSeleccionado.getTblCampana() != null  && nodoSeleccionado.getTblCampanaProgramas() != null) {
                    prepareCategoryView();
                } else {
                    changeVariablesBoolean(false);
                }
                break;

            case Constants.DISE?O:
                if (nodoSeleccionado != null && nodoSeleccionado.getTblCampana() != null && nodoSeleccionado.getTblCampanaProgramas() != null) {
                    prepareDise?oView();
                    pintaArbol(raizDisenio);
                } else {
                    changeVariablesBoolean(false);
                }
                break;
            case Constants.PRECIOS:
                raizPrecio = null;
                LOG.info("preparando vista de precios");
                preparePreciosView();
                raizPrecioPop = raizPrecio;
                pintaArbol(raizPrecio);
                break;
                            
        }

        enableGridComponente(false);
        if (lArqCompPrecProm != null && !lArqCompPrecProm.isEmpty()){
            getEstatusDisenoGlobal();
        }
        LOG.info("Total time: " + (System.currentTimeMillis() - start) + " ms.");
    }

    private void pintaArbol(TreeNode node){
        node.setExpanded(true);
        if(node.getChildCount() > 0){
            for(TreeNode child : node.getChildren()){
                pintaArbol(child);
            }
        }
    }

    private boolean hasDesigns = false;

    public boolean isHasDesigns() {
        return hasDesigns;
    }

    public void setHasDesigns(boolean hasDesigns) {
        this.hasDesigns = hasDesigns;
    }

    private void prepareDise?oView(){
        agregarPromocion = false;
        precio = false;
        precioPromocion = false;
        disenio = true;
        estrategia = false;
        disenoDTO = new DisenosDTO();

        if (etapaPrograma == null || etapaPrograma.isEmpty()){
            etapaPrograma = nodoSeleccionado.getTblCampanaProgramas().getEtapa();
        }else{
            nodoSeleccionado.getTblCampanaProgramas().setEtapa(etapaPrograma);
        }


        if (Util.convertProgramaEtapa(etapaPrograma) == EtapaDashboard.DISENO){
            programaActivo = true;
        }else {
            programaActivo = false;
        }

        if (nodoSeleccionado.getTblMecanica() != null) {
            
            idMecanicaPrec = nodoSeleccionado.getTblMecanica().getMecanicaId();
            disenoDTO.setPkMec(idMecanicaPrec);
            obtenerMecanica(idMecanicaPrec);
            arqMecanica.setIdPrograma(nodoSeleccionado.getTblMecanica().getProgramaId());
            disenoDTO.setProgramaId(nodoSeleccionado.getTblMecanica().getProgramaId());
            
            List<Integer> liZonas = new ArrayList<>();
            for (String zn : arqMecanica.getlZonas()) {
                liZonas.add(Integer.valueOf(zn));
            }
            
            obtenerDisenio(idMecanicaPrec, 0, liZonas, 0, 0, null);

            raizDisenio = new DefaultTreeNode("root", null);
            generateTreeTableDisenio(arqMecanica.getlGrupoZonas(),arqMecanica.getlZonas());
            lMecanica = new ArrayList<>();
            lMecanica.add(new SelectItem(arqMecanica.getIdMecanica(), arqMecanica.getNombre()));
            
             /*
                logica para reorganizar componentes y cambiar la vista
            */
            boolean allPrices = allPricesValidation();
            LOG.info("--------------------------------------------------------------------");
            LOG.info("tienen todos los componentes precio: " + allPrices);
            if(allPrices){
                LOG.info("reorganizando componentes");
                sortPromotionPriceList();
            }else{
                LOG.info("no es combo, continuando");
                this.esCombo = false;
            }
        
        } else {
            List<TblMecanica> lTblMecanicas = serviceArquitecturaSeven.getAllMecanica(
                Long.valueOf(nodoSeleccionado.getTblCampanaProgramas().getId().getIdCampana()).intValue(),
                nodoSeleccionado.getTblCampanaProgramas().getId().getIdPrograma());
            
            if (lTblMecanicas != null) {
                raizDisenio = new DefaultTreeNode("root", null);
                lMecanica = new ArrayList<>();
                for (TblMecanica tblMecanica : lTblMecanicas) {
                    lMecanica.add(new SelectItem(tblMecanica.getMecanicaId(), tblMecanica.getNombreMecanica()));
                    obtenerMecanica(tblMecanica.getMecanicaId());

                    List<Integer> liZonas = new ArrayList<>();
                    for (String zn : arqMecanica.getlZonas()) {
                        liZonas.add(Integer.valueOf(zn));
                    }
                    obtenerDisenio(tblMecanica.getMecanicaId(), 0,liZonas, 0, 0, null);
                    generateTreeTableDisenio(arqMecanica.getlGrupoZonas(),arqMecanica.getlZonas());
                    
                     /*
                        logica para reorganizar componentes y cambiar la vista
                    */
                    boolean allPrices = allPricesValidation();
                    LOG.info("--------------------------------------------------------------------");
                    LOG.info("tienen todos los componentes precio: " + allPrices);
                    if(allPrices){
                        LOG.info("reorganizando componentes");
                        sortPromotionPriceList();
                    }else{
                        LOG.info("no es combo, continuando");
                        this.esCombo = false;
                    }
                }
            }
        }

        // Determina si ya existe algun dise?o en la mecanica
        try {
            if (serviceArquitecturaSeven.getDisenosByIdMecanica(idMecanicaPrec).size() > 0){
                hasDesigns = true;
                LOG.info("Mecanica tiene Dise?os");
            }else {
                LOG.info("Mecanica no tiene Dise?os");
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
        }


        initCombosDisenio(); 
        /*iteracion para seleccionar por defecto el listado de grupos de zona*/
        lGrupoZonasPrec = new ArrayList<String>();
        for(CatGZone e : mCatGruposZonaPrec){
            lGrupoZonasPrec.add(String.valueOf(e.getIdGrupoZona()));
        }

        /*iteracion para seleccionar por defecto el listado de zonas*/
        lZonasPrec = new ArrayList<String>();
        for(CatZone e : mCatZonasPrec){
            lZonasPrec.add(String.valueOf(e.getIdZone()));
        }
    }

    private boolean allPricesExists = true;

    public boolean isAllPricesExists() {
        return allPricesExists;
    }

    public void setAllPricesExists(boolean allPricesExists) {
        this.allPricesExists = allPricesExists;
    }

    private void prepareCategoryView(){
        LOG.info("cargando listado de senalamientos");
        mCatSignals = MBUtil.cargarcomboSenal(serviceCatSenal);
        
        arqPeriodo = new ArqPeriodo();

        TblCampana campana = nodoSeleccionado.getTblCampana();
        TblCampanaProgramas programa = nodoSeleccionado.getTblCampanaProgramas();
        arqPeriodo.setIdPeriodo(campana.getIdCampana());
        arqPeriodo.setDescripcion(campana.getNombre());
        arqPeriodo.setIngreso(programa.getIngreso());

        etapaPrograma = programa.getEtapa();

        if(etapaPrograma!=null){
	        EtapaDashboard etapa = Util.convertProgramaEtapa(etapaPrograma);
	        if (etapa == EtapaDashboard.CAPTURA || etapa == EtapaDashboard.PENDIENTE){
	            programaActivo = true;
	        }else {
	            programaActivo = false;
	        }
	
	        if (etapa == EtapaDashboard.REV_DISENO){
	            programaRevisionDiseno = true;
	        }else{
	            programaRevisionDiseno = false;
	        }
        }else{
        	programaActivo = false;
        	programaRevisionDiseno = false;
        }

        configurarFechaInicioFin(campana);

        if (nodoSeleccionado.getTblMecanica() != null) {
            arqMecanica.setIdMecanica(nodoSeleccionado.getTblMecanica().getMecanicaId());
            arqMecanica.setIdPrograma(nodoSeleccionado.getTblMecanica().getProgramaId());
            obtenerMecanica(nodoSeleccionado.getTblMecanica().getMecanicaId());
        } else {
            arqMecanica = new ArqMecanica();
            arqComponente = new ArqComponente();

            arqPeriodo.setIdPrograma(programa.getId().getIdPrograma());
            CatPromo p = new CatPromo();
            if(!this.lCatPromociones.isEmpty()){
            	 p = this.lCatPromociones.get(0);
                 arqMecanica.setIdPromocion(p.getIdPromo());
            }
            arqMecanica.setIdTipoPromocion(Constants.TIPO_PROMOCION_COMPLEJA);
            List<Integer> categoriasIds = serviceCampana.getCategoriasIdsByCampanaIdAndPrograma(programa.getId().getIdCampana(), programa.getId().getIdPrograma());
            if (categoriasIds != null) {
            	List<String> catsIds = new ArrayList<String>();
            	for(Integer cid: categoriasIds){
            		catsIds.add(cid.toString());
                }
            	arqPeriodo.setlCategorias(catsIds);
                setCategoriasSelected();
            }
			
        }
        Set<TblCampanaProgramasCategorias> tblCampPrgCat = programa.getTblCampanaProgramasCategorias();

        if (tblCampPrgCat != null) {
        	/*
            Iterator<TblCampanaProgramasCategorias> iCampPrgCat = tblCampPrgCat.iterator();
            if (iCampPrgCat != null) {
                if (arqPeriodo.getlCategorias() == null) {
                    arqPeriodo.setlCategorias(new ArrayList<String>());
                }
                while (iCampPrgCat.hasNext()) {
                    TblCampanaProgramasCategorias campPrgCat = iCampPrgCat.next();
                    arqPeriodo.getlCategorias().add( String.valueOf(campPrgCat .getIdCategoria()));
                }
            }
            */

        }

        List<TblMecanica> lTblMecanicas = serviceArquitecturaSeven.getAllMecanica((int) programa.getId().getIdCampana(), programa.getId().getIdPrograma());
        if (lTblMecanicas != null) {
            lMecanica = new ArrayList<>();
            Boolean tieneMecanicaSeleccionado = false;
            for (TblMecanica tblMecanica : lTblMecanicas) {
            	if(tblMecanica.getNombreMecanica() != null){
                lMecanica.add(new SelectItem(tblMecanica.getMecanicaId(), tblMecanica.getNombreMecanica()));
                if (nodoSeleccionado.getTblMecanica() != null && nodoSeleccionado.getTblMecanica().getMecanicaId() == tblMecanica.getMecanicaId()){
                	arqMecanica.setIdMecanica(tblMecanica.getMecanicaId());
                	tieneMecanicaSeleccionado = true;
                }
            }
            SelectItem mecanica = new SelectItem();
            if(!tieneMecanicaSeleccionado){
            	if(!lMecanica.isEmpty()){
	            	mecanica = lMecanica.get(0);
	            	arqMecanica.setIdMecanica((int) mecanica.getValue());
            	}
            }
            }
        }

        agregarPromocion = true;
        precio = false;
        precioPromocion = false;
        disenio = false;
        estrategia = false;
        
        LOG.info("filtrando senalamientos por programa: " + arqPeriodo.getIdPrograma());
        this.filterSignalList(arqPeriodo.getIdPrograma());

        List<TblMecanica> mecanicaList = serviceArquitecturaSeven.getMecanicaByPrograma(arqPeriodo.getIdPeriodo(), arqMecanica.getIdPrograma());
        for (TblMecanica mecanica : mecanicaList){
            if (!serviceArquitecturaSeven.checkPrecioExistsByMecanica(mecanica.getMecanicaId())){
                allPricesExists = false;
                break;
            }
        }
    }
    
    private void filterSignalList(int programId){
        List<CatSenal> tmp = new ArrayList<CatSenal>();
        for(CatSenal senalamiento :  mCatSignals){
            LOG.info("senalamiento en iteracion: " + senalamiento.getNombre());
            if(senalamiento.getCatPrograma().getIdPrograma() == arqPeriodo.getIdPrograma()){
                LOG.info("senalamiento coincide con programa, agregando");
                tmp.add(senalamiento);
            }else{
                LOG.info("senalamiento no coincide con programa, descartando");
            }
        }
        this.mCatSignals = tmp;
    }

    private void preparePreciosView(){
        LOG.info("inicio metodo: preparePreciosView");
        //si el nodo seleccionado tiene campania y periodo
        if (nodoSeleccionado != null && nodoSeleccionado.getTblCampana() != null  && nodoSeleccionado.getTblCampanaProgramas() != null) {
            LOG.info("Nodo tiene campania y programa");

            arqPeriodo = new ArqPeriodo();
            agregarPromocion = false;
            precio = true;
            precioPromocion = false;
            disenio = false;

            TblCampana campana = nodoSeleccionado.getTblCampana();

            TblCampanaProgramas tcp = serviceCampana.getProgramaById(campana.getIdCampana(), nodoSeleccionado.getTblCampanaProgramas().getPrograma().getIdPrograma());
            nodoSeleccionado.getTblCampanaProgramas().setEtapa(tcp.getEtapa());
            etapaPrograma = nodoSeleccionado.getTblCampanaProgramas().getEtapa();

            if (etapaPrograma == null || etapaPrograma.isEmpty()){
                etapaPrograma = nodoSeleccionado.getTblCampanaProgramas().getEtapa();
            }else{
                nodoSeleccionado.getTblCampanaProgramas().setEtapa(etapaPrograma);
            }

            if (etapaPrograma != null){
            	
            	EtapaDashboard etapa = Util.convertProgramaEtapa(etapaPrograma);
            	
                if (etapa == EtapaDashboard.REV_PRECIOS){
                    programaActivo = true;
                }else {
                    programaActivo = false;
                }

                if (etapa == EtapaDashboard.REV_DISENO){
                    programaRevisionDiseno = true;
                }else{
                    programaRevisionDiseno = false;
                }
            }

            //si el nodo seleccionado tiene mecanica, ejecuta esta logica
            if (nodoSeleccionado.getTblMecanica() != null) {
                LOG.info("Nodo tiene mecanica");
                raizPrecio = new DefaultTreeNode("padreConMecanica","root", null);

                idMecanicaPrec = nodoSeleccionado.getTblMecanica().getMecanicaId();
                
                arqMecanica.setIdPrograma(nodoSeleccionado.getTblMecanica().getProgramaId());
                
                //se obtiene la mecanica
                LOG.info("inicio de metodo obtenerMecanica");
                obtenerMecanica(idMecanicaPrec);
                LOG.info("fin de metodo obtenerMecanica");


                List<Integer> liZonas = new ArrayList<>();
                for (String zn : arqMecanica.getlZonas()) {
                    liZonas.add(Integer.valueOf(zn));
                }

                /*
                    este es el que me interesa
                */
                obtenerPrecios(idMecanicaPrec, 0, liZonas, 0, 0);
                LOG.info("seteando raizPrecio como nodo base");
                raizPrecio = new DefaultTreeNode("root", null);

                LOG.info("inicio de ejecucion del metodo generateTreeTablePrecio");
                generateTreeTablePrecio(arqMecanica.getlGrupoZonas(), arqMecanica.getlZonas());
                LOG.info("fin de ejecucion del metodo generateTreeTablePrecio");
                
                /*
                    logica para reorganizar componentes y cambiar la vista
                */
                boolean allPrices = allPricesValidation();
                LOG.info("--------------------------------------------------------------------");
                LOG.info("tienen todos los componentes precio: " + allPrices);
                if(allPrices){
                    LOG.info("reorganizando componentes");
                    sortPromotionPriceList();
                }else{
                    LOG.info("no es combo, continuando");
                    this.esCombo = false;
                }

                lMecanica = new ArrayList<>();
                lMecanica.add(new SelectItem(arqMecanica.getIdMecanica(), arqMecanica.getNombre()));

            } else {
                //trae todas las mecanicas si ninguna esta seleccionada, espero que nunca pase por aqui,
                //porque el onNodeSelect siempre trae mecanica
                List<TblMecanica> lTblMecanicas = serviceArquitecturaSeven.getAllMecanica(Long.valueOf(
                        nodoSeleccionado.getTblCampanaProgramas().getId().getIdCampana()).intValue(),
                        nodoSeleccionado.getTblCampanaProgramas().getId().getIdPrograma());
                if (lTblMecanicas != null) {
                    lMecanica = new ArrayList<>();
                    raizPrecio = new DefaultTreeNode("padreSinMecanica","root", null);
                    for (TblMecanica tblMecanica : lTblMecanicas) {
                        obtenerMecanica(tblMecanica.getMecanicaId());

                        List<Integer> liZonas = new ArrayList<>();
                        for (String zn : arqMecanica.getlZonas()) {
                            liZonas.add(Integer.valueOf(zn));
                        }
                        obtenerPrecios(tblMecanica.getMecanicaId(), 0,liZonas, 0, 0);
                        generateTreeTablePrecio(arqMecanica.getlGrupoZonas(),arqMecanica.getlZonas());
                        
                        /*
                            logica para reorganizar componentes y cambiar la vista
                        */
                        boolean allPrices = allPricesValidation();
                        LOG.info("--------------------------------------------------------------------");
                        LOG.info("tienen todos los componentes precio: " + allPrices);
                        if(allPrices){
                            LOG.info("reorganizando componentes");
                            sortPromotionPriceList();
                        }else{
                            LOG.info("no es combo, continuando");
                            this.esCombo = false;
                        }
                        
                    }
                    lMecanica.add(new SelectItem(arqMecanica.getIdMecanica(), arqMecanica.getNombre()));
                }
            }
            
            initCombosPrec();
            
            /*iteracion para seleccionar por defecto el listado de grupos de zona*/
            lGrupoZonasPrec = new ArrayList<String>();
            for(CatGZone e : mCatGruposZonaPrec){
                lGrupoZonasPrec.add(String.valueOf(e.getIdGrupoZona()));
            }
            
            /*iteracion para seleccionar por defecto el listado de zonas*/
            lZonasPrec = new ArrayList<String>();
            for(CatZone e : mCatZonasPrec){
                lZonasPrec.add(String.valueOf(e.getIdZone()));
            }
            
        } else {
           changeVariablesBoolean(false);
        }
    }

    private void changeVariablesBoolean(boolean estate){
        agregarPromocion = estate;
        precio = estate;
        precioPromocion = estate;
        disenio = estate;
        estrategia = estate;
    }

    private void getEstatusDisenoGlobal() {
		String label = getLabelEstatusDisenioByid(5);
		labelDisenoGlobal = label;
	}

	private void enableGridComponente(boolean b) {
    	habilitarComp = b;
		disableForList = b;
	    disableList = b;
	    disableEspacioComp = b;
	}

	private void configurarFechaInicioFin(TblCampana campana) {
		String idTipoCampanaString = String.valueOf(campana.getIdTipoCampana());
		if (!Constants.TIPO_CAMPANA_ESPECIAL.equals(idTipoCampanaString)) {
			// La fecha de inicio y fin debe venir del periodo excepto en campanas de tipo especial
			Integer idPeriodo = campana.getIdPeriodo();
			try {
				List<PeriodoDTO> periodos = MBUtil.getPeriodos(serviceDynamicCatalogs, Collections.singletonList(new AttrSearch(Constants.ATT_ID, idPeriodo.toString())));
				for (PeriodoDTO periodo : periodos) {
					if (Objects.equals(idPeriodo, periodo.getId())) {
						arqPeriodo.setFechaInicio(periodo.getFechaInicial());
						arqPeriodo.setFechaFin(periodo.getFechaFinal());
						arqPeriodo.setHoraInicio(arqPeriodo.getFechaInicio());
						arqPeriodo.setHoraFin(arqPeriodo.getFechaFin());
						break;
					}
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		} else {
			arqPeriodo.setFechaInicio(campana.getFechaInicio());
			arqPeriodo.setFechaFin(campana.getFechaFin());
			arqPeriodo.setHoraInicio(campana.getFechaInicio());
			arqPeriodo.setHoraFin(campana.getFechaFin());
		}
    }

    public void enableEspacioComp(){
    	if(arqComponente.getIdEspPromocional() != 0)
    		disableEspacioComp = true;
        	else
        		disableEspacioComp = false;
    }
    
    public void disableForList(){
    	if(arqComponente.getIdLista() != 0)
            disableForList = true;
    	else
    		disableForList = false;
    }
    
    public void disableList(){
    	if(arqComponente.getIdCategoria() != 0)
        disableList = true;
    	else
    		disableList = false;
    }
    
    public void resetSelectedNode(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse(); //Optional
    }

    private void generateTreeTablePrecio(List<String> lGpoZona, List<String> lZona) {

        LOG.info("generando nodo de mecanica");
        ArqComponente arqComp = new ArqComponente();
        arqComp.setDescMecanica(arqMecanica.getNombre());
        LOG.info("asignando nodo de mecanica a root");
        arqComp.setElementType("(M)");
        TreeNode tMecanica = new DefaultTreeNode("mecanica", arqComp, raizPrecio);

        lArqPreciosPrint = new ArrayList<>();
        if (lGpoZona != null) {
            for (String gz : lGpoZona) {
                boolean encontrado = false;
                if (lArqCompPrecProm != null) {
                    for (ArqComponente aComp : lArqCompPrecProm) {
                        if (String.valueOf(aComp.getIdGpoZona()).equals(gz)) {
                            encontrado = true;
                        }
                    }
                }

                if (!encontrado) {
                    LOG.info("no se ha encontrado grupo de zona");
                    continue;
                }

                String gpoZona = "";
                for (CatGZone catGzone : mGruposZone) {
                    if (catGzone.getIdGrupoZona() == Integer.valueOf(gz)) {
                        LOG.info("grupo de zona encontrado: " + gpoZona);
                        gpoZona = catGzone.getCode();
                        break;
                    }
                }

                arqComp = new ArqComponente();
                arqComp.setGpoZona(gpoZona);
                LOG.info("seteando grupo zona como hijo de mecanica");
                arqComp.setElementType("(GZ)");
                TreeNode tGpoZona = new DefaultTreeNode("grupoZona", arqComp, tMecanica);
                tGpoZona.setExpanded(true);

                for (String z : lZona) {
                    String descZona = "";
                    encontrado = false;

                    LOG.info("obteniendo zona por id: " + z);
                    CatZone zona = serviceCatZone.getCatZoneById(Integer.valueOf(z));

                    arqComp = new ArqComponente();
                    arqComp.setZona(zona != null ? zona.getCode() : "");
                    arqComp.setIdZona(zona != null ? zona.getIdZone() : 0);
                    LOG.info("agregando zona como nodo hijo de grupo de zona");
                    arqComp.setElementType("(Z)");
                    TreeNode tZona = new DefaultTreeNode("zona", arqComp, tGpoZona);
                    tZona.setExpanded(true);

                    if (lArqCompPrecProm != null) {
                        LOG.info("lArqCompPrecProm no vacia al armar arbol");
                        for (ArqComponente aComp : lArqCompPrecProm) {
                            if (aComp.getPreciosPromocionDTO() != null && gz.equals(String.valueOf(aComp.getIdGpoZona())) && z.equals(String.valueOf(aComp.getIdZona()))) {
                                LOG.info("agragando grupo de precios a zona");
                                aComp.setIsLastTreeElement(true);
                                
                                if(aComp.getPreciosPromocionDTO().getPrecioR() > 0){
                                    aComp.setHasPrecioPromocion(true);
                                }
                                
                                aComp.setElementType("(P)");
                                TreeNode precioNode = new DefaultTreeNode("precio", aComp, tZona);
                                precioNode.setExpanded(true);
                                ArqComponente comp = new ArqComponente(aComp);
                                comp.setStrMecanica(arqMecanica.getNombre());
                                comp.setGpoZona(gpoZona);
//                               comp.setZona("tucol");
                                comp.setZona(aComp.getZona());
                                
                                
                                LOG.info("agregando ArqComponente a treeNode: " +  comp.getDescripcion());
                                LOG.info("valor de labelEstatusRevision: " + comp.getLabelEstatusRevision());
                                LOG.info("valor de strEstatusRevision: " + comp.getStrEstatusPrecio());
                                LOG.info("valor de estatus en precioPromociondto: " + aComp.getPreciosPromocionDTO().getEstatusRevision());
                                
                                lArqPreciosPrint.add(comp);
                            }
                        }
                    }else{
                        LOG.info("lArqCompPrecProm vacia al armar arbol");
                    }
                }
            }
        }
        
        lArqPreciosPrint = lArqCompPrecProm;
    }
    
    private List<CatSenal> senalsDise?o;

    public List<CatSenal> getSenalsDise?o() {
        return senalsDise?o;
    }

    public void setSenalsDise?o(List<CatSenal> senalsDise?o) {
        this.senalsDise?o = senalsDise?o;
    }

    private void generateTreeTableDisenio(List<String> lGpoZona, List<String> lZona) {
        String nomProg = "";
        senalsDise?o = new ArrayList<>();
        
        //lDesginDescriptions = new ArrayList<String>();
        
        ArqComponente arqComp = new ArqComponente();
        try {
            CatPrograma catP = new CatPrograma();
            catP.setIdPrograma(arqPeriodo.getIdPrograma());
            catP = this.serviceCatPrograma.getCatPrograma(catP);
            arqComp.setPrograma(catP.getNombre());
            nomProg = catP.getNombre();
        } catch (Exception e) {
            LOG.error(e);
        }

        for (String s : arqMecanica.getlSenalamiento()){
            CatSenal senal = serviceCatSenal.getCatSenal(Integer.valueOf(s));
            if (senal != null){
                senalsDise?o.add(senal);
            }
        }
        //Set Nivel 0 root
        arqComp.setTreeNivel(Constants.LVL_ROOT);
        TreeNode tProgramaDisenio = new DefaultTreeNode(arqComp, raizDisenio);

        arqComp = new ArqComponente();
        arqComp.setDescMecanica(arqMecanica.getNombre());
        setEstatusDisenoGlobal(arqMecanica.getIdMecanica());
        arqMecanica.getNombre();

        //Set Nivel 1 Mecanica
        arqComp.setTreeNivel(Constants.LVL_MECANICA);
        TreeNode tMecanica = new DefaultTreeNode(arqComp, tProgramaDisenio);
        lArqPreciosPrint = new ArrayList<>();
        if (lGpoZona != null) {
            for (String gz : lGpoZona) {
                boolean encontrado = false;
                if (lArqCompPrecProm != null) {
                    for (ArqComponente aComp : lArqCompPrecProm) {
                        if (String.valueOf(aComp.getIdGpoZona()).equals(gz)) {
                            encontrado = true;
                            break;
                        }
                    }
                }

                if (!encontrado) {
                    continue;
                }

                String gpoZona = "";
                for (CatGZone catGzone : mGruposZone) {
                    if (catGzone.getIdGrupoZona() == Integer.valueOf(gz)) {
                        gpoZona = catGzone.getCode();
                        break;
                    }
                }

                arqComp = new ArqComponente();
                arqComp.setGpoZona(gpoZona);
                //Set Nivel 2 Grupo Zona
                arqComp.setTreeNivel(Constants.LVL_GPO_ZONA);
                TreeNode tGpoZona = new DefaultTreeNode(arqComp, tMecanica);
                for (String z : lZona) {
                    String descZona = "";
                    encontrado = false;
                    if (lArqCompPrecProm != null) {
                        for (ArqComponente aComp : lArqCompPrecProm) {
                            if (String.valueOf(aComp.getIdZona()).equals(z)) {
                                encontrado = true;
                                break;
                            }
                        }
                    }

                    if (!encontrado) {
                        continue;
                    }

                    for (Iterator<CatZone> iterator = mZones.iterator(); iterator.hasNext();) {
                        CatZone catZone = (CatZone) iterator.next();
                        if (catZone.getIdZone() == Integer.valueOf(z)) {
                            descZona = catZone.getCode();
                            break;
                        }
                    }

                    arqComp = new ArqComponente();
                    arqComp.setZona(descZona);
                    //Set Nivel 3 Zona
                    arqComp.setTreeNivel(Constants.LVL_ZONA);
                    TreeNode tZona = new DefaultTreeNode(arqComp, tGpoZona);

                    if (lArqCompPrecProm != null) {
                        LOG.info("******************************************************************* generando nivel 4 de arbol de dise?o");
                        for (ArqComponente aComp : lArqCompPrecProm) {
                            if (aComp.getPreciosPromocionDTO() != null && 
                                    gz.equals(String.valueOf(aComp.getIdGpoZona())) && z.equals(String.valueOf(aComp.getIdZona()))) {
                                
                                int estatusRevision = aComp.getPreciosPromocionDTO().getEstatusRevision();
                                aComp.setIdEstatusPrecio(estatusRevision);
                                //Set Nivel 4 Componente
                                aComp.setTreeNivel(Constants.LVL_COMPONENTE);
                                
                                //lDesginDescriptions.add(aComp.getDescripcion());
                                
                                new DefaultTreeNode(aComp, tZona);
                                
                                ArqComponente comp = new ArqComponente(aComp);
                                comp.setStrMecanica(arqMecanica.getNombre());
                                comp.setGpoZona(gpoZona);
                                comp.setZona(descZona);
                                comp.setPrograma(nomProg);
                                comp.setIdEstatusPrecio(aComp.getPreciosPromocionDTO().getEstatusRevision());
                                lArqPreciosPrint.add(comp);
                            }
                        }
                        LOG.info("Fin --------------------------------------------------------------------------");
                    }
                }
            }
        }
        
        /*iteracion sobre los componentes para cargar las descripciones*/
        /*
        LOG.info("total de elementos en precio promocion: " + lArqCompPrecProm.size());
        Map<String, String> keys = new HashMap<String, String>();
        if (lArqCompPrecProm != null) {
            for (ArqComponente aComp : lArqCompPrecProm) {
                if(!keys.containsKey(aComp.getDescripcion())){
                    lDesginDescriptions.add(aComp.getDescripcion());
                    keys.put(aComp.getDescripcion(), aComp.getDescripcion());
                }
                
            }
        }
        */
    }
    
    public boolean validarComponentes(int mecanicaId, Integer categoriaId, Integer subCategoriaId, Integer descripcionId,
        Integer componenteId, Integer listaId, List<String> skuId, List<String> lUPC, List<String> lZonas){
        LOG.info("validando y reorganizando componentes de mecanica");
        
        List<ComponenteDTO> lCompDTO = serviceArquitecturaSeven.getPreciosPromocionComponentes(mecanicaId, categoriaId, subCategoriaId, descripcionId, componenteId,listaId, skuId, lUPC);
        List<ArqComponente> lArqComp = prepararComponentes(lCompDTO, mecanicaDTO.getSenalamientoList());
        
        Map<Integer, List<ArqComponente>> agrupaciones = new HashMap<Integer, List<ArqComponente>>();
        
        for(ArqComponente componente: lArqComp){
            Integer no = Integer.parseInt(String.valueOf(componente.getNumeroComponente()));
            if(agrupaciones.containsKey(no)){
                agrupaciones.get(no).add(componente);
            }else{
                List<ArqComponente> listadoDeComponentes = new ArrayList<ArqComponente>();
                listadoDeComponentes.add(componente);
                agrupaciones.put(no, listadoDeComponentes);
            }
        }
        LOG.info("Resultados de agrupaciones: ");
        
        for (Map.Entry<Integer, List<ArqComponente>> entry : agrupaciones.entrySet()) {
            Integer no = entry.getKey();
            List<ArqComponente> elements = entry.getValue();
            LOG.info("No de componente: " + no + ", elementos: " + elements.size());
        }
        
        LOG.info("Ordenando agrupaciones");
        agrupaciones = sortMapByValue(agrupaciones);
        
        LOG.info("agrupaciones ordenadas:");
        int numeroDeComponenteFinal = 1;
        for (Map.Entry<Integer, List<ArqComponente>> entry : agrupaciones.entrySet()) {
            Integer no = entry.getKey();
            List<ArqComponente> elements = entry.getValue();
            LOG.info("No Articulo final: " + numeroDeComponenteFinal + ", No de componente: " + no + ", elementos: " + elements.size());
            
            if(numeroDeComponenteFinal == no.intValue()){
                LOG.info("posicion igual al numero de componente, no se modifican");
            }else{
                LOG.info("posicion distinta al numero de componente, ajustando valores");
                for(ArqComponente e : elements){
                    try{
                        this.serviceArquitecturaSeven.updateComponentNumber(e.getIdComponente(), numeroDeComponenteFinal);
                        e.setNumeroComponente(numeroDeComponenteFinal);
                    }catch(Exception ex){
                        ex.printStackTrace();
                        LOG.info("error al actualizar el componente: " +  ex.getMessage());
                    }
                }
            }
            numeroDeComponenteFinal += 1;
        }
        
        
        List<String> errores = new ArrayList<String>();
        
        for (Map.Entry<Integer, List<ArqComponente>> entry : agrupaciones.entrySet()) {
            LOG.info("validando subcategorias y familias de componente: " + entry.getKey());
            
            //Map<String, String> subCategoriaSku = new HashMap<String, String>();
            //Map<Integer, String> familiaSku = new HashMap<Integer, String>();
            
            Map<String, String> categoriaSku = new HashMap<String, String>();
            
            
                        
            for(ArqComponente e : entry.getValue()){
                LOG.info("componente: " + e.getDescripcion() + 
                        ", categoria: " + e.getCategoria() + 
                        ", subcategoria: " + e.getSubCategoria() + 
                        ", familia: " + e.getIdLista());
                /*
                if(subCategoriaSku.size() == 0 && familiaSku.size() == 0){
                    subCategoriaSku.put(e.getSubCategoria(), e.getSku());
                    familiaSku.put(e.getIdLista(), e.getSku());
                    continue;
                }
                */
                if(categoriaSku.size() == 0){
                    categoriaSku.put(e.getCategoria(), e.getSku());
                    continue;
                }
                
                if(!categoriaSku.containsKey(e.getCategoria())){
                    String skuBase = new ArrayList<String>(categoriaSku.values()).get(0);
                    String catBase = new ArrayList<String>(categoriaSku.keySet()).get(0);
                    errores.add("Existen articulos con distinta Categoria en componente " + entry.getKey() + ". " +
                       "SKU " + skuBase + ", Categoria: " + catBase + 
                     ". SKU " + e.getSku() +", Categoria: " + e.getCategoria()
                    );
                }
                
                /*
                if(!subCategoriaSku.containsKey(e.getSubCategoria())){
                   String skuBase = new ArrayList<String>(subCategoriaSku.values()).get(0);
                   String subCatBase = new ArrayList<String>(subCategoriaSku.keySet()).get(0);
                   errores.add("Existen articulos con distinta subcategoria en componente " + entry.getKey() + ". " +
                       "SKU " + skuBase + ", Categoria: " + subCatBase + 
                     ". SKU " + e.getSku() +", Categoria: " + e.getSubCategoria()
                    );
                }
                */
                
                /*
                if(!familiaSku.containsKey(e.getIdLista())){
                   
                   String skuBase = new ArrayList<String>(familiaSku.values()).get(0);
                   Integer familiaBase = new ArrayList<Integer>(familiaSku.keySet()).get(0);
                   
                   String familiaBaseDesc;
                   if(familiaBase.intValue() == 0){
                       familiaBaseDesc = "No tiene";
                   }else{
                       CatLista cl = this.serviceCatLista.getCatListaById(familiaBase.intValue());
                       familiaBaseDesc = cl.getCode();
                   }
                   
                   String currentFamiliaBase;
                   if(e.getIdLista() == 0){
                       currentFamiliaBase = "No tiene";
                   }else{
                       CatLista cl = this.serviceCatLista.getCatListaById(e.getIdLista());
                       currentFamiliaBase = cl.getCode();
                   }
                   
                   
                   errores.add("Existen articulos con distinta familia en componente " + e.getNumeroComponente() + ". " +
                       "SKU " + skuBase + ", Familia: " + familiaBaseDesc + 
                     ". SKU " + e.getSku() +", Familia: " + currentFamiliaBase
                    );
                }
                */
            }  
        }
        
        //Logueando errores
        if(errores.size() > 0){
            for(String e : errores){
                 Messages.mensajeErroneo(e, e);
            }
            return false;
        }
        
        return true;
    }
    
    private static Map<Integer, List<ArqComponente>> sortMapByValue(Map<Integer, List<ArqComponente>> unsortMap) {

        List<Map.Entry<Integer, List<ArqComponente>>> list = new LinkedList<Map.Entry<Integer, List<ArqComponente>>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, List<ArqComponente>>>() {
            @Override
            public int compare(Map.Entry<Integer, List<ArqComponente>> o1, Map.Entry<Integer, List<ArqComponente>> o2) {
                return (o1.getValue().size() > o2.getValue().size()) ? 1 : 
                        ((o1.getValue().size() == o2.getValue().size()) ? 0 : -1);
            }
        });

        Map<Integer, List<ArqComponente>> sortedMap = new LinkedHashMap<Integer, List<ArqComponente>>();
        for (Map.Entry<Integer, List<ArqComponente>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    

    public void selectPrecioPromocion() {
    	long start = System.currentTimeMillis();
        agregarPromocion = false;
        precio = false;
        precioPromocion = true;
        
        boolean isOk = validarComponentes(arqMecanica.getIdMecanica(), 0, 0, 0, 0, 0, null, null, arqMecanica.getlZonas());
        if(!isOk){
            prepareCategoryView();
            return;
        }
        
        LOG.info("cargando informacion de precios promocion");
        initInfoPrecProm();
        LOG.info("fin informacion de precios promocion");
        
        try {
            if (idMecanicaPrec > 0) {
                LOG.info("cargado precios promocion de mecanica: " + idMecanicaPrec);
                obtenerPreciosPromocion(idMecanicaPrec, 0, 0, 0, 0, 0, null, null, arqMecanica.getlZonas());
                                
                comboCompPrecProm();
            }
        
        } catch (Exception e) {
                LOG.error(e);
        }
        
        boolean allPrices = allPricesValidation();
        LOG.info("--------------------------------------------------------------------");
        LOG.info("tienen todos los componentes precio: " + allPrices);
        if(allPrices){
            LOG.info("reorganizando componentes");
            sortPromotionPriceList();
        }else{
            this.esCombo = false;
        }
        
        //se limpian los datos del grid
        this.precPromo = 0D;
        this.porcPromo = 0D;
        this.ahorroFijo = 0D;
        this.recuProveCant = 0D;
        this.recuProvePorc = 0D;
        this.cantidadDistribucionRebaja = 0D;
        if(lComponentesPrecioPromocion.isEmpty() || lComponentesPrecioPromocion.size() > 1){
        	this.porcentajeDistribucionRebaja = 0D;
        	this.porcentajeRetencionStr = "0.0";
        }
        
        
        this.objetivo = 0D;
        this.comenDisenio = "";
        
        //construye el combo de precios promocion de zonas
        this.buildZonesCatList();
        LOG.info("Total time: " + (System.currentTimeMillis() - start) + " ms.");
    }
    
    private void buildZonesCatList(){
        List<Integer> catZones = new ArrayList<Integer>();
        for(String zoneId : arqMecanica.getlZonas()){
            catZones.add(Integer.parseInt(zoneId));
            //Selecciona todo por default
            this.ppCatZonesSelected.add(zoneId);
        }
        this.ppCatZones = this.serviceCatZone.getCatZoneByIds(catZones);
    }
    
    private void comboCompPrecProm(){
    		lSkuPrec = new ArrayList<>();
        lUpcPrec = new ArrayList<>();
        mCatSkuPrec =  new ArrayList<>();
        listCatItemDesc = new ArrayList<>();
        lComponentesPrec = new ArrayList<>();
        int index = 1;
    		for(ArqComponente dto : lArqCompPrecProm){
    			if(!lSkuPrec.contains(dto.getSku())){
    				lSkuPrec.add(dto.getSku());
    				mCatSkuPrec.add(MBUtil.parseArqComp(dto));
    			}
    			if(!lUpcPrec.contains(dto.getUpc())){
    				lUpcPrec.add(dto.getUpc());
    			}
    			listCatItemDesc.add(MBUtil.parseItemArqComp(dto));
    			lComponentesPrec.add(new SelectItem(index, "Componente " + index));
    			index++;
    		}
    }
    

    private void obtenerPreciosPromocion(int mecanicaId, Integer categoriaId, Integer subCategoriaId, Integer descripcionId,
        Integer componenteId, Integer listaId, List<String> skuId, List<String> lUPC, List<String> lZonas) {
        
        lComponentesPrecioPromocion = new ArrayList<SelectItem>();
        
        int idPromo = arqMecanica.getIdPromocion();
        LOG.info("Id de promocion: " + idPromo);
        
        String promo = "";
        for(CatPromo p : this.lCatPromociones){
            if(p.getIdPromo() == idPromo){
                promo = p.getNombre();
                break;
            }
        }
        LOG.info("Promocion Seleccionada: " + promo);
        nomPromoPrec = promo;
        
        try {
            LOG.info("obteniendo componentes");
            List<ComponenteDTO> lCompDTO = serviceArquitecturaSeven.getPreciosPromocionComponentes(mecanicaId, categoriaId,
                                            subCategoriaId, descripcionId, componenteId,listaId, skuId, lUPC);
            if (lCompDTO != null) {
            	LOG.info("preparando componentes");
                List<ArqComponente> lArqComp = prepararComponentes(lCompDTO, mecanicaDTO.getSenalamientoList());

                LOG.info("obteniendo precios por mecanica");
                List<PreciosPromocionDTO> lPrecPromoDTO = serviceArquitecturaSeven.getPreciosByMecanica(mecanicaId);

                LOG.info("obtener configuracion de mecanica");
                obtenerConfigMecanica();
                
                lArqCompPrecProm = new ArrayList<>();
                LOG.info("iterando por zonas");
                StringBuffer zonas = new StringBuffer();
                for (String sZona : lZonas) {
                	if (arqMecanica.getlZonas().contains(sZona)) {
                		zonas.append(sZona).append(",");
                	}
                }
                StringBuffer componentes = new StringBuffer();
                Map<Integer, List<ArqComponente>> mapComponente = new HashMap<>();
                for (ArqComponente aComp : lArqComp) {
                	if (aComp.getUpc() == null || aComp.getUpc().isEmpty()) {
                        continue;
                    }
                	List<ArqComponente> list = mapComponente.get(aComp.getIdComponente());
                	if (list == null) {
                		list = new ArrayList<>();
                		mapComponente.put(aComp.getIdComponente(), list);
                	}
                	list.add(aComp);
                	componentes.append(aComp.getIdComponente()).append(",");
                	
                	//para calcular el combo de componentes
                    SelectItem e = new SelectItem();
                    e.setValue(aComp.getNumeroComponente());
                    e.setLabel("Articulo No. " + aComp.getNumeroComponente());
                    boolean exists = false;
                    for(SelectItem i : lComponentesPrecioPromocion){
                        if(i.getValue() == e.getValue()){
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        lComponentesPrecioPromocion.add(e);
                    }
                }
                if (zonas.length() > 0 && componentes.length() > 0) {
                	String zoneList = zonas.substring(0, zonas.length() - 1);
                	String componentList = componentes.substring(0, componentes.length() - 1);
                	Map<Integer, CatZone> zoneMap = new HashMap<>();
                	List<Object[]> zones = this.serviceCatZone.getCatZonesWithCatGZoneByZonas(zoneList);
                	for (Object[] data : zones) {
                		CatZone zone = (CatZone) data[0];
                		zone.setCatGZone((CatGZone) data[1]);
                		zoneMap.put(zone.getIdZone(), zone);
                	}
                	List<TblComponenteZonaPrecio> listaPreciosBase = this.serviceArquitecturaSeven.getByComponentIdAndZoneId(componentList, zoneList);
                	for (TblComponenteZonaPrecio precioBase : listaPreciosBase) {
                		// Se valida si hay un precio regular nuevo
                        boolean cambioEnPrecioRegular = false;
                        
                        if(precioBase.getPrecioProducto()!=null 
                        		&& precioBase.getPrecioProducto() != 0.0){
                            LOG.info("cambio en el precio regular del producto");
                            LOG.info("valor original: " +  precioBase.getPrecioProducto());
                            cambioEnPrecioRegular = true;
                        }else{
                            LOG.info("Precio regular no ha cambiado");
                        }
                        List<ArqComponente> list = mapComponente.get(precioBase.getComponenteId());
                        for (ArqComponente aComp : list) {
                        	ArqComponente arqComp = new ArqComponente(aComp);
                            
                            LOG.info("-------------------------------------------------------------------------------------------");
                            LOG.info("calculando montos para componente: " + aComp.descripcion);
                            
                            
                            boolean firstLoad = true;
                            
                            if (lPrecPromoDTO != null && lPrecPromoDTO.size() > 0) {
                                for (PreciosPromocionDTO precPromoDTO : lPrecPromoDTO) {
                                    if (arqComp.getIdComponente() == precPromoDTO.getPkComp() && precioBase.getZonaId() == precPromoDTO.getZonaId()) {
                                        
                                        LOG.info("seteando valores de precios a componente en iteracion");
                                        
                                        if(precPromoDTO.getPorcentajeDescuento() == null){
                                            LOG.info("porcentaje de descuento nulo, encendiendo bandera");
                                            arqComp.setTienePorcentajeDescuento(false);
                                        }else{
                                            LOG.info("porcentaje de descuento no nulo: " + precPromoDTO.getPorcentajeDescuento());
                                            arqComp.setTienePorcentajeDescuento(true);
                                        }
                                        
                                        arqComp.setPreciosPromocionDTO(precPromoDTO);
                                        
                                        arqComp.setDistribucionRebaja(precPromoDTO.getDistribucionRebajaCantidad());
                                        arqComp.setDistribucionRebajaPorc(precPromoDTO.getDistribucionRebajaPorcentaje());
                                        
                                        arqComp.setPrecioEdit(precPromoDTO.getPrecio());
                                        arqComp.setPorcentajeEdit(precPromoDTO.getPorcentaje());
                                        arqComp.setAhorroFijoEdit(precPromoDTO.getAhorroFijo());
                                        arqComp.setRecuperacionEdit(precPromoDTO.getRecuperacion());
                                        arqComp.setRecuperacionPorcentajeEdit(precPromoDTO.getRecuperacionPorcentaje());
                                        
                                               
                                        Double elasticidad = arqComp.getPreciosPromocionDTO().getElasticidad();
                                        LOG.info("elasticidad: " + elasticidad);
                                        Double porcentaje = arqComp.getPreciosPromocionDTO().getPorcentaje();
                                        LOG.info("porcentaje: " + porcentaje);
                                        
                                        Double nuevoObjetivo = (elasticidad * porcentaje);
                                        LOG.info("Nuevo objetivo: " + nuevoObjetivo);
                                        
                                        //arqComp.setVentaUPTD(new Double(precPromoDTO.getObjetivo()));
                                        arqComp.setVentaUPTD(nuevoObjetivo);
                                        
                                                                                
                                        //cantidad ya vienen arqComponente
                                        //precioVenta en precioPromocionDTO
                                        
                                        arqComp.setImpuesto(precioBase.getImpuesto());
                                        
                                        arqComp.setPrecioRegularNuevo(0D);
                                        
                                        //double margenRegular = margenFinal * arqComp.getCantidadProducto();
                                        //arqComp.setMgnRegular(margenRegular);
                                        arqComp.setMgnRegular(precioBase.getMargenRegular());
                                        
                                        //porcentaje de margen regular
                                        //arqComp.setMgnRegularPorc(porcMargenFinal);
                                        arqComp.setMgnRegularPorc(precioBase.getPorcentajeMargenRegularProducto());
                                        
                                        arqComp.setTienePrecioPromocion(true);
                                        
                                        firstLoad = false;
                                        break;                                        
                                    }
                                }
                            }
                            
                            //cuando no se tenga precio promocion
                            if(firstLoad){
                                LOG.info("Aun no se asocia precio promocion. Cargando informacion base");
                                PreciosPromocionDTO precioPromocion = new PreciosPromocionDTO();
                                
                                //para que no truene al calcular el margen promocion
                                precioPromocion.setAhorroFijo(0D);
                                precioPromocion.setRecuperacion(0D);
                                
                                //cantidad de producto ya viene pre-cargado
                                //precio de venta
                                //double precioVenta = arqComp.getPrecioVentaOriginal() * arqComp.getCantProductos();
                                //precioPromocion.setPrecioVenta(arqComp.getPrecioVentaOriginal() * arqComp.getCantProductos());
                                precioPromocion.setPrecioVenta(precioBase.getPrecioVenta());

                                //precio regular nuevo
                                if(cambioEnPrecioRegular){
                                    this.notificarDeCambioEnPrecioRegular(arqMecanica.getNombre(), String.valueOf(0D), String.valueOf(precioBase.getPrecioProducto()), arqComp.sku);
                                }
                                arqComp.setPrecioRegularNuevo(0D);
                                //impuesto
                                //arqComp.setImpuesto(impuestoFinal);
                                arqComp.setImpuesto(precioBase.getImpuesto());
                               
                                //margen regular
                                //double margenRegular = margenFinal * arqComp.getCantidadProducto();
                                //LOG.info("margen regular: " + margenRegular);
                                //arqComp.setMgnRegular(margenRegular);
                                arqComp.setMgnRegular(precioBase.getMargenRegular());

                                //porcentaje de margen regular
                                //arqComp.setMgnRegularPorc(porcMargenFinal);
                                arqComp.setMgnRegularPorc(precioBase.getPorcentajeMargenRegularProducto());
                                
                                
                                arqComp.setTienePrecioPromocion(false);
                                arqComp.setTienePorcentajeDescuento(false);
                                //asignando precio promocion a objeto
                                arqComp.setPreciosPromocionDTO(precioPromocion);
                            }
                            
                            for (SelectItem si : lMecanica) {
                                if (((Integer) si.getValue()) == arqComp.getIdMecanica()) {
                                    arqComp.setDescMecanica(si.getLabel());
                                    break;
                                }
                            }

                            arqComp.setIdZona(precioBase.getZonaId());

                            for (CatZone catZone : mZones) {
                                if (catZone.getIdZone() == arqComp.getIdZona()) {
                                    arqComp.setZona(catZone.getCode());
                                    break;
                                }
                            }
                            
                            CatZone cz = zoneMap.get(precioBase.getZonaId());
                            arqComp.setIdGpoZona(cz.getCatGZone().getIdGrupoZona());
                            arqComp.setGpoZona(cz.getCatGZone().getCode());
                            lArqCompPrecProm.add(arqComp);                        	
                        }
                	}
                }
                porcentajeRetencionStrActivo = true;
//                lComponentesPrecioPromocion.remove(0);
                if(!lComponentesPrecioPromocion.isEmpty() && lComponentesPrecioPromocion.size() == 1){
                	porcentajeRetencionStr = "100.0";
                	porcentajeDistribucionRebaja = 100D;
                	
                	porcentajeRetencionStrActivo = false;
                	SelectItem i = lComponentesPrecioPromocion.get(0);
                	componentePrecioPromocionSeleccionado = (int) i.getValue();
                }
                
                //se sacan todos los numero de componente existentes
                Map<Integer, Double> ahorros = new HashMap<Integer,Double>();
                for(ArqComponente e : lArqCompPrecProm){
                    
                    LOG.info("elemento en iteracion: " + e.getDescripcion() + ", ahorro: " + e.getPreciosPromocionDTO().getAhorroFijo());
                    
                    Integer noComp = Integer.parseInt(String.valueOf(e.getNumeroComponente()));
                    if(!ahorros.containsKey(noComp)){
                        LOG.info("componente no existe, agregando");
                        ahorros.put(noComp, e.getPreciosPromocionDTO().getAhorroFijo());
                    }else{
                        LOG.info("elemento existente, comparando");
                        LOG.info("maximo al momento: " + ahorros.get(noComp).doubleValue());
                        LOG.info("actual: " + e.getPreciosPromocionDTO().getAhorroFijo().doubleValue());
                        if(ahorros.get(noComp).doubleValue() < e.getPreciosPromocionDTO().getAhorroFijo().doubleValue()){
                            LOG.info("planchando valor");
                            ahorros.remove(noComp);
                            ahorros.put(noComp, e.getPreciosPromocionDTO().getAhorroFijo());
                        }
                    }
                }
                
                LOG.info("Mapa por componente: " + ahorros);
                LOG.info("......................................................................." );
                
                //nueva iteracion para setar valor
                for(ArqComponente e : lArqCompPrecProm){
                    Integer noComp = Integer.parseInt(String.valueOf(e.getNumeroComponente()));
                    e.setAhorroMaximo(ahorros.get(noComp));
                }
                
                //calculo de valores finales
                for(String s: arqMecanica.getlZonas()){
                    
                    Double ahorroMaximo = 0D;
                    Double sumatoriaAhorrosFijos = 0D;
                    Double sumatoriaRecuperacionProveedor = 0D;
                    
                    //primera iteracion para calcular ahorro maximo y sumatorias
                    for(ArqComponente a : lArqCompPrecProm){
                        //si el componente pertence a la zona en cuestion
                        if(a.getIdZona() == Integer.parseInt(s)){
                            if(a.getPreciosPromocionDTO().getAhorroFijo() > ahorroMaximo){
                                ahorroMaximo = a.getPreciosPromocionDTO().getAhorroFijo();
                            }
                            sumatoriaAhorrosFijos = sumatoriaAhorrosFijos + a.getPreciosPromocionDTO().getAhorroFijo();
                            sumatoriaRecuperacionProveedor = sumatoriaRecuperacionProveedor + a.getPreciosPromocionDTO().getRecuperacion();
                        }
                    }
                    
                    //segunda iteracion para calcular margenes de promocion y asignar a elementos
                    for(ArqComponente a : lArqCompPrecProm){
                        //si el componente pertence a la zona en cuestion
                        if(a.getIdZona() == Integer.parseInt(s)){
                            //a.setAhorroMaximo(ahorroMaximo);
                            
                            //Double margenPromocion = a.getMgnRegular() + ((sumatoriaAhorrosFijos + sumatoriaRecuperacionProveedor) / (1 + (a.getImpuesto()/100)));
                            
                            LOG.info("porcentaje margen regular: " + a.getMgnRegularPorc());
                            LOG.info("precio promocion: " + a.getPreciosPromocionDTO().getPrecioR());
                            
                            Double porc = a.getMgnRegularPorc() / 100F;
                            Double margenRegularXPrecio = porc * a.getPreciosPromocionDTO().getPrecioR();
                            
                            
                            LOG.info("Margen regular por precioPromocion: " + margenRegularXPrecio);
                            
                            Double recuperacionMenosAhorroFijo = a.getPreciosPromocionDTO().getRecuperacion() - a.getPreciosPromocionDTO().getAhorroFijo();
                            LOG.info("Recuperacion proveedor: " + a.getPreciosPromocionDTO().getRecuperacion());
                            LOG.info("ahorro fijo: " + a.getPreciosPromocionDTO().getAhorroFijo());
                            
                            LOG.info("Porcentaje de retencion: " + a.getPreciosPromocionDTO().getPorcentajeRetencion());
                            
                            Double recuperacionAhorroEntreRedencion = 0D;
                            if(a.getPreciosPromocionDTO().getPorcentajeRetencion() != null){
                                recuperacionAhorroEntreRedencion = recuperacionMenosAhorroFijo / (a.getPreciosPromocionDTO().getPorcentajeRetencion() / 100F);
                            }
                            LOG.info("Porcentaje entre retencion: " + recuperacionAhorroEntreRedencion);
                            
                            Double margenPromocion = margenRegularXPrecio + recuperacionAhorroEntreRedencion;
                            
                            LOG.info("Margen promocion: " + margenPromocion);
                            a.setMgnPromocion(margenPromocion);
                            
                            //Double porcMargenPromocion = margenPromocion / (a.getPreciosPromocionDTO().getPrecioVenta() / ( 1 + (a.getImpuesto() / 100 )));
                            Double porcMargenPromocion = margenPromocion / a.getPreciosPromocionDTO().getPrecioR();
                            LOG.info("porcentaje margen promocion: " + porcMargenPromocion);
                            a.setMgnPromocionPorc(porcMargenPromocion * 100);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);
        }
    }
    
    public void notificarDeCambioEnPrecioRegular(String mecanica, String precioActual, String precioOriginal, String sku){
        try{
            String msj = "Se agrego precio regular nuevo en la mec?nica " + mecanica + " " +
                "<br /><br />Precio Regular Nuevo: " + precioActual + " " +
                "<br />Precio Al agregar componente: " + precioOriginal + " " +
                "<br />SKU del producto: " + sku + " ";

            //category y precios
            CatUsuarios category = serviceUsuarios.getUsuarioById(26);
            CatUsuarios userPrecios = serviceUsuarios.getUsuarioById(61);
            //String [] mailsTo = {category.getEmail(), userPrecios.getEmail() };
            String [] mailsTo = {category.getEmail(), userPrecios.getEmail()};

            SendMail.sendGenericEmail(mailsTo, "Cambio en Precio Regular nuevo", msj);
            LOG.info("Correo enviado");
        }catch(Exception e){
            LOG.error("Error al tratar de notificar por correo el cambio de precio regular");
            LOG.error(e.getMessage());
        }
    }

    public void buscarPrecio() {
        try {
            if (idMecanicaPrec > 0) {
                //diferencia es que una es integer y la otra string
                List<Integer> lZonasAux = new ArrayList<>();
                List<String> lZonasAux2 = new ArrayList<>();
                
                //si hay grupos de zonas y zonas seleccionadas
                if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() && (lZonasPrec == null || lZonasPrec.isEmpty())) {
                    //se itera sobre el listado de zonas por grupo
                    for (String z : lZonaXGrupo) {
                        lZonasAux.add(Integer.valueOf(z));
                    }
                    //se copia el listado de zonas por grupo
                    lZonasAux2.addAll(lZonaXGrupo);
                } 
                //si algun combo esta vacio
                else {
                    //si se selecciono alguna zona
                    if (lZonasPrec != null) {
                        //agrega al listado de zonas
                        for (String z : lZonasPrec) {
                            lZonasAux.add(Integer.valueOf(z));
                        }
                    }
                    //agrega el listado de zonas al auxiliar
                    lZonasAux2.addAll(lZonasPrec);
                }
                
                //si el lstado de zonas auxiliaresta vacio
                if (lZonasAux.isEmpty()) {
                    //itera pos las zonas de la mecanica
                    for (String z : arqMecanica.getlZonas()) {
                        lZonasAux.add(Integer.valueOf(z));
                    }
                    //agrega todas las zonas de la mecanica
                    lZonasAux2.addAll(arqMecanica.getlZonas());
                }

                obtenerPrecios(idMecanicaPrec, idCatPrec, lZonasAux, idStatusRevPrec, idStatusCapPrec);

                raizPrecio = new DefaultTreeNode("root", null);
                generateTreeTablePrecio(
                lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() ? lGrupoZonasPrec : arqMecanica.getlGrupoZonas(), 
                !lZonasAux2.isEmpty() ? lZonasAux2 : arqMecanica.getlZonas());
            
                
                /*
                    filtrar la lista actual de los que no tienen precio promocion
                */
                List<ArqComponente> tmp = new ArrayList<ArqComponente>();
                for(ArqComponente e: lArqCompPrecProm){
                    if(e.getTienePrecioPromocion()){
                        tmp.add(e);
                    }
                }
                
                if(!tmp.isEmpty())
                	lArqCompPrecProm = tmp;
                
                /*
                    logica para reorganizar componentes y cambiar la vista
                */
                boolean allPrices = allPricesValidation();
                LOG.info("--------------------------------------------------------------------");
                LOG.info("tienen todos los componentes precio: " + allPrices);
                if(allPrices){
                    LOG.info("reorganizando componentes");
                    sortPromotionPriceList();
                }else{
                    LOG.info("no es combo, continuando");
                    this.esCombo = false;
                }
                

            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void buscarDisenio() {
        
        LOG.info("Buscando componentes con estatus precio: " + idStatusPrecio);
        LOG.info("Buscando componentes con descripcion: " + valueDesignDescription);
        List<Integer> lZonasAux = new ArrayList<>();
        List<String> lZonasAux2 = new ArrayList<>();
        if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() && (lZonasPrec == null || lZonasPrec.isEmpty())) {
            for (String z : lZonaXGrupo) {
                lZonasAux.add(Integer.valueOf(z));
            }
            lZonasAux2.addAll(lZonaXGrupo);
        } else {
            if (lZonasPrec != null) {
                for (String z : lZonasPrec) {
                    lZonasAux.add(Integer.valueOf(z));
                }
            }
            lZonasAux2.addAll(lZonasPrec);
        }

        if (lZonasAux.isEmpty()) {
            for (String z : arqMecanica.getlZonas()) {
                lZonasAux.add(Integer.valueOf(z));
            }
        }
            
        obtenerDisenio(idMecanicaPrec, idCatPrec, lZonasAux, idStatusPrecio, idStatusDisenio, valueDesignDescription);
            
        raizDisenio = new DefaultTreeNode("root", null);

        generateTreeTableDisenio(lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() ? lGrupoZonasPrec : arqMecanica.getlGrupoZonas(),
                    !lZonasAux2.isEmpty() ? lZonasAux2 : arqMecanica.getlZonas());
        
        pintaArbol(raizDisenio);
        
        /*
            filtrar la lista actual de los que no tienen precio promocion
        */
        List<ArqComponente> tmp = new ArrayList<ArqComponente>();
        for(ArqComponente e: lArqCompPrecProm){
            if(e.getTienePrecioPromocion()){
                tmp.add(e);
            }
        }

        lArqCompPrecProm = tmp;

        /*
            logica para reorganizar componentes y cambiar la vista
        */
        boolean allPrices = allPricesValidation();
        LOG.info("--------------------------------------------------------------------");
        LOG.info("tienen todos los componentes precio: " + allPrices);
        if(allPrices){
            LOG.info("reorganizando componentes");
            sortPromotionPriceList();
        }else{
            LOG.info("no es combo, continuando");
            this.esCombo = false;
        }

    }
    
    
    public void limpiarTreeTableDisenio(){
        buscarDisenio();
        removeElemetOfTreeNode(raizDisenio,tProgramaDisenio);
    }
    
    public boolean removeElemetOfTreeNode(TreeNode rootNode,
            TreeNode nodeToDelete) {
        if (rootNode.getChildren().remove(nodeToDelete)) {
            return true;
        } else {
            for (TreeNode childNode : rootNode.getChildren()) {
                if (childNode.getChildCount() > 0) {
                    return removeElemetOfTreeNode(childNode, nodeToDelete);
                }

            }
            return false;
        }
    }
    
    public int getSelectedMecanica(){
    	return 0;
    }

    private void obtenerDisenio(int mecanicaId, Integer categoriaId, List<Integer> lZonas, 
            int estatusRevisionId, int estatusCapturaId, String description) {
        try {
            
            List<ComponenteDTO> lCompDTO = serviceArquitecturaSeven.getPreciosPromocionComponetes(categoriaId, 0, mecanicaId, null, lZonas);
            List<ArqComponente> lArqComp = prepararComponentes(lCompDTO,mecanicaDTO.getSenalamientoList());
            List<PreciosPromocionDTO> lPrecPromoDTO = serviceArquitecturaSeven.getPreciosByMecanica(mecanicaId, estatusRevisionId, estatusCapturaId);
            
            obtenerConfigMecanica();
            
            //double distRebaja = 0;
            
            /*
            if (configMecanica != null) {
                distRebaja = configMecanica.getTotalCant();
            }
            */

            //ahorroMaximo = serviceArquitecturaSeven.getAhorroMaximoByMecanicaId(mecanicaId);

            lDesginDescriptions = new ArrayList<String>();
            Map<String, String> keys = new HashMap<String, String>();
            
            if (lCompDTO != null) {
                lArqCompPrecProm = new ArrayList<>();
                
                for (Integer iZona : lZonas) {
                    if (arqMecanica.getlZonas().contains(iZona.toString())) {
                        
                        for (ArqComponente aComp : lArqComp) {
                            
                            if(!keys.containsKey(aComp.getDescripcion())){
                                lDesginDescriptions.add(aComp.getDescripcion());
                                keys.put(aComp.getDescripcion(), aComp.getDescripcion());
                            }
                            
                            if(description != null && description.length() > 0 && !description.equals("Seleccione")){
                                if(!aComp.getDescripcion().equals(description)){
                                    LOG.info("Componente distinto al de la busqueda");
                                    continue;
                                }else{
                                    LOG.info("Componente igual a la busqueda");
                                }
                            }
                            
                            ArqComponente arqComp = new ArqComponente(aComp);
                            if (arqComp.getUpc() == null || arqComp.getUpc().isEmpty()) {
                                continue;
                            }
                            
                            List<TblComponenteZonaPrecio> listaPreciosBase = 
                                    this.serviceArquitecturaSeven.getByComponentIdAndZoneId(aComp.getIdComponente(),iZona);
                            
                            TblComponenteZonaPrecio precioBase = null;
                            if(listaPreciosBase != null && listaPreciosBase.size() > 0){
                                precioBase = listaPreciosBase.get(0);
                            }
                            
                            //para calcular el combo de componentes
                            /*
                            SelectItem e = new SelectItem();
                            e.setValue(aComp.getNumeroComponente());
                            e.setLabel("Articulo No. " + aComp.getNumeroComponente());
                            
                            if(lComponentesPrecioPromocion != null){
                                boolean exists = false;
                                for(SelectItem i : lComponentesPrecioPromocion){
                                    if(i.getValue() == e.getValue()){
                                        exists = true;
                                        break;
                                    }
                                }
                                if(!exists){
                                    lComponentesPrecioPromocion.add(e);
                                }
                            }
                            */
                            
                            LOG.info("-------------------------------------------------------------------------------------------");
                            LOG.info("calculando montos para componente: " + aComp.descripcion);
                            
                            
                            Double precioActual = this.obtenerPrecioActualPorZona(aComp.getSku(), String.valueOf(iZona));
                            
                            // Se valida si hay un precio regular nuevo
                            boolean cambioEnPrecioRegular = false;
                            
                            LOG.info("validando si precio actual calculado es distinto a precio original");
                            //if(precioActual != arqComp.getPrecioVentaOriginal()){
                            if(precioActual.doubleValue() != precioBase.getPrecioProducto()){
                                LOG.info("cambio en el precio regular del producto");
                                LOG.info("valor original: " +  precioBase.getPrecioProducto());
                                LOG.info("valor calculado: " + precioActual);
                                cambioEnPrecioRegular = true;
                            }else{
                                LOG.info("Precio regular no ha cambiado");
                            }
                            
                            boolean firstLoad = true;

                            /*
                            double dVentaBase = 0;
                            double dMgnReg = 0;
                            double dMgnRegPorc = 0;
                            double dCosto = 0;

                            List<RelItemStore> itemsStore = this.serviceRelItemStore.getItemStoreByItemId(aComp.sku);
                            List<Double> precios = new ArrayList<Double>();
                            String margenRel = null;
                            Double porcMargenRel = 0D;

                            for(RelItemStore item : itemsStore){
                                precios.add(item.getPrecioAtual().doubleValue());
                                margenRel  = item.getMargen();
                                porcMargenRel = item.getPorcMargen().doubleValue();
                            }

                            String margen = margenRel;
                            Double precioActual = MBUtil.getMode(precios);
                            Double porcMargen = porcMargenRel;

                            // Obtener venta base de log?stica
                            List<AttrSearch> insertSearch = new ArrayList<>();
                            AttrSearch attrSearch = new AttrSearch(Constants.ATT_CODE, aComp.sku);
                            attrSearch.setAttrName(Constants.ATT_CODE);
                            attrSearch.setValue(aComp.sku);
                            insertSearch.add(attrSearch);
                            String logisticaVentaBase = MBUtil.getCatalogAttribByKey(serviceDynamicCatalogs, Constants.CAT_LOGISTICA, insertSearch, Constants.ATT_VENTA_BASE);
                            if (logisticaVentaBase.isEmpty() == false) {
                                    dVentaBase += Double.parseDouble(logisticaVentaBase);
                            }
                            if(margen != null){
                                dMgnReg += Double.parseDouble(margen);
                            }else{
                                Double sinMargen = 0D;
                                dMgnReg += sinMargen ;
                            }
                            dCosto += precioActual;
                            dMgnRegPorc += porcMargen;
                            */

                            
                            if (lPrecPromoDTO != null && lPrecPromoDTO.size() > 0) {
                                LOG.info("iterando listado precio promocion en buscar dise?o");
                                for (PreciosPromocionDTO precPromoDTO : lPrecPromoDTO) {
                                    if (arqComp.getIdComponente() == precPromoDTO.getPkComp() && iZona == precPromoDTO.getZonaId()) {
                                        LOG.info("seteando valores de precios a componente en iteracion");
                                        
                                        arqComp.setPreciosPromocionDTO(precPromoDTO);
                                        
                                        arqComp.setDistribucionRebaja(precPromoDTO.getDistribucionRebajaCantidad());
                                        arqComp.setDistribucionRebajaPorc(precPromoDTO.getDistribucionRebajaPorcentaje());
                                        
                                        arqComp.setPrecioEdit(precPromoDTO.getPrecio());
                                        arqComp.setPorcentajeEdit(precPromoDTO.getPorcentaje());
                                        arqComp.setAhorroFijoEdit(precPromoDTO.getAhorroFijo());
                                        arqComp.setRecuperacionEdit(precPromoDTO.getRecuperacion());
                                        arqComp.setRecuperacionPorcentajeEdit(precPromoDTO.getRecuperacionPorcentaje());
                                        
                                        
                                         Double elasticidad = arqComp.getPreciosPromocionDTO().getElasticidad();
                                        LOG.info("elasticidad: " + elasticidad);
                                        Double porcentaje = arqComp.getPreciosPromocionDTO().getPorcentaje();
                                        LOG.info("porcentaje: " + porcentaje);
                                        
                                        Double nuevoObjetivo = (elasticidad * porcentaje);
                                        LOG.info("Nuevo objetivo: " + nuevoObjetivo);
                                        
                                        //arqComp.setVentaUPTD(new Double(precPromoDTO.getObjetivo()));
                                        arqComp.setVentaUPTD(nuevoObjetivo);
                                                                                
                                        //cantidad ya vienen arqComponente
                                        //precioVenta en precioPromocionDTO
                                        
                                        arqComp.setImpuesto(precioBase.getImpuesto());
                                        
                                        if(cambioEnPrecioRegular){
                                            arqComp.setPrecioRegularNuevo(precioActual);           
                                        }else{
                                            arqComp.setPrecioRegularNuevo(0D);
                                        }
                                        
                                        //double margenRegular = margenFinal * arqComp.getCantidadProducto();
                                        //arqComp.setMgnRegular(margenRegular);
                                        arqComp.setMgnRegular(precioBase.getMargenRegular());
                                        
                                        //porcentaje de margen regular
                                        //arqComp.setMgnRegularPorc(porcMargenFinal);
                                        arqComp.setMgnRegularPorc(precioBase.getPorcentajeMargenRegularProducto());
                                        
                                        arqComp.setTienePrecioPromocion(true);
                                        
                                        firstLoad = false;
                                        break; 
                                    }
                                }
                            }
                            
                            //cuando no se tenga precio promocion
                            if(firstLoad){
                                LOG.info("Aun no se asocia precio promocion. Cargando informacion base");
                                PreciosPromocionDTO precioPromocion = new PreciosPromocionDTO();
                                
                                //para que no truene al calcular el margen promocion
                                precioPromocion.setAhorroFijo(0D);
                                precioPromocion.setRecuperacion(0D);
                                
                                //cantidad de producto ya viene pre-cargado
                                //precio de venta
                                //double precioVenta = arqComp.getPrecioVentaOriginal() * arqComp.getCantProductos();
                                //precioPromocion.setPrecioVenta(arqComp.getPrecioVentaOriginal() * arqComp.getCantProductos());
                                precioPromocion.setPrecioVenta(precioBase.getPrecioVenta());

                                //precio regular nuevo
                                if(cambioEnPrecioRegular){
                                    //this.notificarDeCambioEnPrecioRegular(arqMecanica.getNombre(), String.valueOf(precioActual), String.valueOf(arqComp.getPrecioVentaOriginal()), arqComp.sku);
                                    this.notificarDeCambioEnPrecioRegular(arqMecanica.getNombre(), String.valueOf(precioActual), String.valueOf(precioBase.getPrecioProducto()), arqComp.sku);
                                    arqComp.setPrecioRegularNuevo(precioActual);           
                                }else{
                                    arqComp.setPrecioRegularNuevo(0D);
                                }
                                //impuesto
                                //arqComp.setImpuesto(impuestoFinal);
                                arqComp.setImpuesto(precioBase.getImpuesto());
                               
                                //margen regular
                                //double margenRegular = margenFinal * arqComp.getCantidadProducto();
                                //LOG.info("margen regular: " + margenRegular);
                                //arqComp.setMgnRegular(margenRegular);
                                arqComp.setMgnRegular(precioBase.getMargenRegular());

                                //porcentaje de margen regular
                                //arqComp.setMgnRegularPorc(porcMargenFinal);
                                arqComp.setMgnRegularPorc(precioBase.getPorcentajeMargenRegularProducto());
                                
                                
                                arqComp.setTienePrecioPromocion(false);
                                //asignando precio promocion a objeto
                                arqComp.setPreciosPromocionDTO(precioPromocion);
                            }

                            arqComp.setDescMecanica(arqMecanica.getNombre());
                            arqComp.setIdZona(iZona);
                            
                            for (CatZone catZone : mZones) {
                                if (catZone.getIdZone() == arqComp.getIdZona()) {
                                    arqComp.setZona(catZone.getCode());
                                    break;
                                }
                            }

                            CatZone zona = serviceCatZone.getCatZoneById(iZona);
                            arqComp.setIdGpoZona(zona.getCatGZone().getIdGrupoZona());
                            arqComp.setGpoZona(zona.getCatGZone().getCode());

                            if(arqComp.getPreciosPromocionDTO().getPrecioVenta() != null){
                                arqComp.setPrecioRegularXUnidad(arqComp.getPreciosPromocionDTO().getPrecioVenta());
                            }else{
                                arqComp.setPrecioRegularXUnidad(0D);
                            }

                            int cantProductos = arqComp.getCantProductos();
                            arqComp.setPrecioRegularPromo(cantProductos * arqComp.getPrecioRegularXUnidad());

                            LOG.info("agregando componente a listado final");
                            lArqCompPrecProm.add(arqComp);
                        }
                    }
                }// fin de la iteracion de zonas
                
                //se sacan todos los numero de componente existentes
                Map<Integer, Double> ahorros = new HashMap<Integer,Double>();
                for(ArqComponente e : lArqCompPrecProm){
                    
                    LOG.info("elemento en iteracion: " + e.getDescripcion() + ", ahorro: " + e.getPreciosPromocionDTO().getAhorroFijo());
                    
                    Integer noComp = Integer.parseInt(String.valueOf(e.getNumeroComponente()));
                    if(!ahorros.containsKey(noComp)){
                        LOG.info("componente no existe, agregando");
                        ahorros.put(noComp, e.getPreciosPromocionDTO().getAhorroFijo());
                    }else{
                        LOG.info("elemento existente, comparando");
                        LOG.info("maximo al momento: " + ahorros.get(noComp).doubleValue());
                        LOG.info("actual: " + e.getPreciosPromocionDTO().getAhorroFijo().doubleValue());
                        if(ahorros.get(noComp).doubleValue() < e.getPreciosPromocionDTO().getAhorroFijo().doubleValue()){
                            LOG.info("planchando valor");
                            ahorros.remove(noComp);
                            ahorros.put(noComp, e.getPreciosPromocionDTO().getAhorroFijo());
                        }
                    }
                }
                
                LOG.info("Mapa por componente: " + ahorros);
                LOG.info("......................................................................." );
                
                //nueva iteracion para setar valor
                for(ArqComponente e : lArqCompPrecProm){
                    Integer noComp = Integer.parseInt(String.valueOf(e.getNumeroComponente()));
                    e.setAhorroMaximo(ahorros.get(noComp));
                }
                
                //calculo de valores finales
                for(String s: arqMecanica.getlZonas()){
                    
                    Double ahorroMaximo = 0D;
                    Double sumatoriaAhorrosFijos = 0D;
                    Double sumatoriaRecuperacionProveedor = 0D;
                    
                    //primera iteracion para calcular ahorro maximo y sumatorias
                    for(ArqComponente a : lArqCompPrecProm){
                        //si el componente pertence a la zona en cuestion
                        if(a.getIdZona() == Integer.parseInt(s)){
                            if(a.getPreciosPromocionDTO().getAhorroFijo() > ahorroMaximo){
                                ahorroMaximo = a.getPreciosPromocionDTO().getAhorroFijo();
                            }
                            sumatoriaAhorrosFijos = sumatoriaAhorrosFijos + a.getPreciosPromocionDTO().getAhorroFijo();
                            sumatoriaRecuperacionProveedor = sumatoriaRecuperacionProveedor + a.getPreciosPromocionDTO().getRecuperacion();
                        }
                    }
                    
                    //segunda iteracion para calcular margenes de promocion y asignar a elementos
                    for(ArqComponente a : lArqCompPrecProm){
                        //si el componente pertence a la zona en cuestion
                        if(a.getIdZona() == Integer.parseInt(s)){
                            //a.setAhorroMaximo(ahorroMaximo);
                            
                            //Double margenPromocion = a.getMgnRegular() + ((sumatoriaAhorrosFijos + sumatoriaRecuperacionProveedor) / (1 + (a.getImpuesto()/100)));
                            
                            LOG.info("porcentaje margen regular: " + a.getMgnRegularPorc());
                            LOG.info("precio promocion: " + a.getPreciosPromocionDTO().getPrecioR());
                            
                            Double porc = a.getMgnRegularPorc() / 100F;
                            Double margenRegularXPrecio = porc * a.getPreciosPromocionDTO().getPrecioR();
                            
                            
                            LOG.info("Margen regular por precioPromocion: " + margenRegularXPrecio);
                            
                            Double recuperacionMenosAhorroFijo = a.getPreciosPromocionDTO().getRecuperacion() - a.getPreciosPromocionDTO().getAhorroFijo();
                            LOG.info("Recuperacion proveedor: " + a.getPreciosPromocionDTO().getRecuperacion());
                            LOG.info("ahorro fijo: " + a.getPreciosPromocionDTO().getAhorroFijo());
                            
                            LOG.info("Porcentaje de retencion: " + a.getPreciosPromocionDTO().getPorcentajeRetencion());
                            
                            Double recuperacionAhorroEntreRedencion = 0D;
                            if(a.getPreciosPromocionDTO().getPorcentajeRetencion() != null){
                                recuperacionAhorroEntreRedencion = recuperacionMenosAhorroFijo / (a.getPreciosPromocionDTO().getPorcentajeRetencion() / 100F);
                            }
                            LOG.info("Porcentaje entre retencion: " + recuperacionAhorroEntreRedencion);
                            
                            Double margenPromocion = margenRegularXPrecio + recuperacionAhorroEntreRedencion;
                            
                            LOG.info("Margen promocion: " + margenPromocion);
                            a.setMgnPromocion(margenPromocion);
                            
                            //Double porcMargenPromocion = margenPromocion / (a.getPreciosPromocionDTO().getPrecioVenta() / ( 1 + (a.getImpuesto() / 100 )));
                            Double porcMargenPromocion = margenPromocion / a.getPreciosPromocionDTO().getPrecioR();
                            LOG.info("porcentaje margen promocion: " + porcMargenPromocion);
                            a.setMgnPromocionPorc(porcMargenPromocion * 100);
                        }
                    }
                }
                
            }
            LOG.info("total de elementos: " + lArqCompPrecProm.size());
        } catch (Exception e) {
                LOG.info(e.getMessage());
        }
    }

    private void obtenerPrecios(int mecanicaId, Integer categoriaId, List<Integer> lZonas, int estatusRevisionId, int estatusCapturaId) {
        try {
            List<ComponenteDTO> lCompDTO = serviceArquitecturaSeven.getPreciosPromocionComponetes(categoriaId, 0, mecanicaId, null, lZonas);
            List<ArqComponente> lArqComp = prepararComponentes(lCompDTO, mecanicaDTO.getSenalamientoList());
            List<PreciosPromocionDTO> lPrecPromoDTO = serviceArquitecturaSeven.getPreciosByMecanica(mecanicaId, estatusRevisionId, estatusCapturaId);
            
            
            obtenerConfigMecanica();
            
            if (lCompDTO != null) {
                lArqCompPrecProm = new ArrayList<>();
                LOG.info("iterando por zonas");
                double maximoAhorroFijo = 0;
                   
                //por cada zona del listado (lZonas son las sonas de arqMecanica)
                //for (String sZona : lZonas) {          
                for(Integer sZona : lZonas){
                    
                    //if (arqMecanica.getlZonas().contains(sZona)) {
                    if (arqMecanica.getlZonas().contains(sZona.toString())) {
                    
                        //si la zona en iteracion la tiene la mecanica (En teoria esta de mas)
                        LOG.info("***************************************************************************************");
                        LOG.info("Zona en iteracion: " + sZona);
                        
                        //se iteran sobre los componentes
                        for (ArqComponente aComp : lArqComp) {
                            
                            List<TblComponenteZonaPrecio> listaPreciosBase = 
                                    this.serviceArquitecturaSeven.getByComponentIdAndZoneId(aComp.getIdComponente(),sZona);
                            
                            TblComponenteZonaPrecio precioBase = null;
                            if(listaPreciosBase != null && listaPreciosBase.size() > 0){
                                precioBase = listaPreciosBase.get(0);
                            }
                            
                            //para calcular el combo de componentes
                            SelectItem e = new SelectItem();
                            e.setValue(aComp.getNumeroComponente());
                            e.setLabel("Articulo No. " + aComp.getNumeroComponente());
                            
                            if(lComponentesPrecioPromocion != null){
                                boolean exists = false;
                                for(SelectItem i : lComponentesPrecioPromocion){
                                    if(i.getValue() == e.getValue()){
                                        exists = true;
                                        break;
                                    }
                                }
                                if(!exists){
                                    lComponentesPrecioPromocion.add(e);
                                }
                            }
                            
                            LOG.info("-------------------------------------------------------------------------------------------");
                            LOG.info("calculando montos para componente: " + aComp.descripcion);
                            
                            ArqComponente arqComp = new ArqComponente(aComp);
                            if (arqComp.getUpc() == null || arqComp.getUpc().isEmpty()) {
                                continue;
                            }
                            
                            Double precioActual = this.obtenerPrecioActualPorZona(aComp.getSku(), String.valueOf(sZona));
                            
                            // Se valida si hay un precio regular nuevo
                            boolean cambioEnPrecioRegular = false;
                            
                            LOG.info("validando si precio actual calculado es distinto a precio original");
                            //if(precioActual != arqComp.getPrecioVentaOriginal()){
                            if(precioBase != null && precioActual.doubleValue() != precioBase.getPrecioProducto()){
                                LOG.info("cambio en el precio regular del producto");
                                LOG.info("valor original: " +  precioBase.getPrecioProducto());
                                LOG.info("valor calculado: " + precioActual);
                                cambioEnPrecioRegular = true;
                            }else{
                                LOG.info("Precio regular no ha cambiado");
                            }
                            
                            boolean firstLoad = true;
                            
                            if (lPrecPromoDTO != null && lPrecPromoDTO.size() > 0) {
                                for (PreciosPromocionDTO precPromoDTO : lPrecPromoDTO) {
                                    if (arqComp.getIdComponente() == precPromoDTO.getPkComp() && sZona == precPromoDTO.getZonaId()) {
                                        
                                        LOG.info("seteando valores de precios a componente en iteracion");
                                        
                                        arqComp.setPreciosPromocionDTO(precPromoDTO);
                                        
                                        arqComp.setDistribucionRebaja(precPromoDTO.getDistribucionRebajaCantidad());
                                        arqComp.setDistribucionRebajaPorc(precPromoDTO.getDistribucionRebajaPorcentaje());
                                        
                                        arqComp.setPrecioEdit(precPromoDTO.getPrecio());
                                        arqComp.setPorcentajeEdit(precPromoDTO.getPorcentaje());
                                        arqComp.setAhorroFijoEdit(precPromoDTO.getAhorroFijo());
                                        arqComp.setRecuperacionEdit(precPromoDTO.getRecuperacion());
                                        arqComp.setRecuperacionPorcentajeEdit(precPromoDTO.getRecuperacionPorcentaje());
                                        
                                        
                                         Double elasticidad = arqComp.getPreciosPromocionDTO().getElasticidad();
                                        LOG.info("elasticidad: " + elasticidad);
                                        Double porcentaje = arqComp.getPreciosPromocionDTO().getPorcentaje();
                                        LOG.info("porcentaje: " + porcentaje);
                                        
                                        Double nuevoObjetivo = (elasticidad * porcentaje);
                                        LOG.info("Nuevo objetivo: " + nuevoObjetivo);
                                        
                                        //arqComp.setVentaUPTD(new Double(precPromoDTO.getObjetivo()));
                                        arqComp.setVentaUPTD(nuevoObjetivo);
                                                                                
                                        //cantidad ya vienen arqComponente
                                        //precioVenta en precioPromocionDTO
                                        
                                        arqComp.setImpuesto(precioBase.getImpuesto());
                                        
                                        if(cambioEnPrecioRegular){
                                            arqComp.setPrecioRegularNuevo(precioActual);           
                                        }else{
                                            arqComp.setPrecioRegularNuevo(0D);
                                        }
                                        
                                        //double margenRegular = margenFinal * arqComp.getCantidadProducto();
                                        //arqComp.setMgnRegular(margenRegular);
                                        arqComp.setMgnRegular(precioBase.getMargenRegular());
                                        
                                        //porcentaje de margen regular
                                        //arqComp.setMgnRegularPorc(porcMargenFinal);
                                        arqComp.setMgnRegularPorc(precioBase.getPorcentajeMargenRegularProducto());
                                        
                                        arqComp.setTienePrecioPromocion(true);
                                        
                                        firstLoad = false;
                                        break;                                        
                                    }
                                }
                            }
                            
                            //cuando no se tenga precio promocion
                            if(firstLoad){
                                LOG.info("Aun no se asocia precio promocion. Cargando informacion base");
                                PreciosPromocionDTO precioPromocion = new PreciosPromocionDTO();
                                
                                //para que no truene al calcular el margen promocion
                                precioPromocion.setAhorroFijo(0D);
                                precioPromocion.setRecuperacion(0D);
                                
                                //cantidad de producto ya viene pre-cargado
                                //precio de venta
                                //double precioVenta = arqComp.getPrecioVentaOriginal() * arqComp.getCantProductos();
                                //precioPromocion.setPrecioVenta(arqComp.getPrecioVentaOriginal() * arqComp.getCantProductos());
                                if(precioBase != null)
                                	precioPromocion.setPrecioVenta(precioBase.getPrecioVenta());

                                //precio regular nuevo
                                if(cambioEnPrecioRegular){
                                    //this.notificarDeCambioEnPrecioRegular(arqMecanica.getNombre(), String.valueOf(precioActual), String.valueOf(arqComp.getPrecioVentaOriginal()), arqComp.sku);
                                	 if(precioBase != null){
	                                    this.notificarDeCambioEnPrecioRegular(arqMecanica.getNombre(), String.valueOf(precioActual), String.valueOf(precioBase.getPrecioProducto()), arqComp.sku);
	                                    arqComp.setPrecioRegularNuevo(precioActual);      
                                	 }
                                }else{
                                    arqComp.setPrecioRegularNuevo(0D);
                                }
                                //impuesto
                                //arqComp.setImpuesto(impuestoFinal);
                                if(precioBase != null)
                                	arqComp.setImpuesto(precioBase.getImpuesto());
                               
                                //margen regular
                                //double margenRegular = margenFinal * arqComp.getCantidadProducto();
                                //LOG.info("margen regular: " + margenRegular);
                                //arqComp.setMgnRegular(margenRegular);
                                if(precioBase != null)
                                	arqComp.setMgnRegular(precioBase.getMargenRegular());

                                //porcentaje de margen regular
                                //arqComp.setMgnRegularPorc(porcMargenFinal);
                                if(precioBase != null)
                                	arqComp.setMgnRegularPorc(precioBase.getPorcentajeMargenRegularProducto());
                                
                                
                                arqComp.setTienePrecioPromocion(false);
                                //asignando precio promocion a objeto
                                arqComp.setPreciosPromocionDTO(precioPromocion);
                            }
                            
                            arqComp.setDescMecanica(arqMecanica.getNombre());


                            arqComp.setIdZona(Integer.valueOf(sZona));

                            for (CatZone catZone : mZones) {
                                if (catZone.getIdZone() == arqComp.getIdZona()) {
                                    arqComp.setZona(catZone.getCode());
                                    break;
                                }
                            }
                            
                            String sIdGrupoZona = null;
                            CatZone cz = new CatZone();
                            cz.setIdZone(Integer.valueOf(sZona));
                            cz = this.serviceCatZone.getCatZoneById(cz);
                            sIdGrupoZona = String.valueOf(cz.getCatGZone().getIdGrupoZona());
                            arqComp.setIdGpoZona(Integer.valueOf(sIdGrupoZona));
                            
                            String sGrupoZona = null;
                            sGrupoZona = cz.getCatGZone().getCode();
                            arqComp.setGpoZona(sGrupoZona);
                            
                            lArqCompPrecProm.add(arqComp);
                        }
                    }
                } //fin de iteracion de zonas
                
                //se sacan todos los numero de componente existentes
                Map<Integer, Double> ahorros = new HashMap<Integer,Double>();
                for(ArqComponente e : lArqCompPrecProm){
                    
                    LOG.info("elemento en iteracion: " + e.getDescripcion() + ", ahorro: " + e.getPreciosPromocionDTO().getAhorroFijo());
                    
                    Integer noComp = Integer.parseInt(String.valueOf(e.getNumeroComponente()));
                    if(!ahorros.containsKey(noComp)){
                        LOG.info("componente no existe, agregando");
                        ahorros.put(noComp, e.getPreciosPromocionDTO().getAhorroFijo());
                    }else{
                        LOG.info("elemento existente, comparando");
                        LOG.info("maximo al momento: " + ahorros.get(noComp).doubleValue());
                        LOG.info("actual: " + e.getPreciosPromocionDTO().getAhorroFijo().doubleValue());
                        if(ahorros.get(noComp).doubleValue() < e.getPreciosPromocionDTO().getAhorroFijo().doubleValue()){
                            LOG.info("planchando valor");
                            ahorros.remove(noComp);
                            ahorros.put(noComp, e.getPreciosPromocionDTO().getAhorroFijo());
                        }
                    }
                }
                
                LOG.info("Mapa por componente: " + ahorros);
                LOG.info("......................................................................." );
                
                //nueva iteracion para setar valor
                for(ArqComponente e : lArqCompPrecProm){
                    Integer noComp = Integer.parseInt(String.valueOf(e.getNumeroComponente()));
                    e.setAhorroMaximo(ahorros.get(noComp));
                }
                
                //calculo de valores finales
                for(String s: arqMecanica.getlZonas()){
                    
                    Double ahorroMaximo = 0D;
                    Double sumatoriaAhorrosFijos = 0D;
                    Double sumatoriaRecuperacionProveedor = 0D;
                    
                    //primera iteracion para calcular ahorro maximo y sumatorias
                    for(ArqComponente a : lArqCompPrecProm){
                        //si el componente pertence a la zona en cuestion
                        if(a.getIdZona() == Integer.parseInt(s)){
                            if(a.getPreciosPromocionDTO().getAhorroFijo() > ahorroMaximo){
                                ahorroMaximo = a.getPreciosPromocionDTO().getAhorroFijo();
                            }
                            sumatoriaAhorrosFijos = sumatoriaAhorrosFijos + a.getPreciosPromocionDTO().getAhorroFijo();
                            sumatoriaRecuperacionProveedor = sumatoriaRecuperacionProveedor + a.getPreciosPromocionDTO().getRecuperacion();
                        }
                    }
                    
                    //segunda iteracion para calcular margenes de promocion y asignar a elementos
                    for(ArqComponente a : lArqCompPrecProm){
                        //si el componente pertence a la zona en cuestion
                        if(a.getIdZona() == Integer.parseInt(s)){
                            //a.setAhorroMaximo(ahorroMaximo);
                            
                            //Double margenPromocion = a.getMgnRegular() + ((sumatoriaAhorrosFijos + sumatoriaRecuperacionProveedor) / (1 + (a.getImpuesto()/100)));
                            
                            LOG.info("porcentaje margen regular: " + a.getMgnRegularPorc());
                            LOG.info("precio promocion: " + a.getPreciosPromocionDTO().getPrecioR());
                            
                            Double porc = a.getMgnRegularPorc() / 100F;
                            Double margenRegularXPrecio = porc * a.getPreciosPromocionDTO().getPrecioR();
                            
                            
                            LOG.info("Margen regular por precioPromocion: " + margenRegularXPrecio);
                            
                            Double recuperacionMenosAhorroFijo = a.getPreciosPromocionDTO().getRecuperacion() - a.getPreciosPromocionDTO().getAhorroFijo();
                            LOG.info("Recuperacion proveedor: " + a.getPreciosPromocionDTO().getRecuperacion());
                            LOG.info("ahorro fijo: " + a.getPreciosPromocionDTO().getAhorroFijo());
                            
                            LOG.info("Porcentaje de retencion: " + a.getPreciosPromocionDTO().getPorcentajeRetencion());
                            
                            Double recuperacionAhorroEntreRedencion = 0D;
                            if(a.getPreciosPromocionDTO().getPorcentajeRetencion() != null){
                                recuperacionAhorroEntreRedencion = recuperacionMenosAhorroFijo / (a.getPreciosPromocionDTO().getPorcentajeRetencion() / 100F);
                            }
                            LOG.info("Porcentaje entre retencion: " + recuperacionAhorroEntreRedencion);
                            
                            Double margenPromocion = margenRegularXPrecio + recuperacionAhorroEntreRedencion;
                            
                            LOG.info("Margen promocion: " + margenPromocion);
                            a.setMgnPromocion(margenPromocion);
                            
                            //Double porcMargenPromocion = margenPromocion / (a.getPreciosPromocionDTO().getPrecioVenta() / ( 1 + (a.getImpuesto() / 100 )));
                            Double porcMargenPromocion = margenPromocion / a.getPreciosPromocionDTO().getPrecioR();
                            LOG.info("porcentaje margen promocion: " + porcMargenPromocion);
                            a.setMgnPromocionPorc(porcMargenPromocion * 100);
                        }
                    }
                }
                
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Error Obtener Precios: "+ e.getMessage());
        }
    }

    public void preAgregarMecanica() {
            arqMecanica = new ArqMecanica();
    }

    public void agregarMecanica() {
        try {
            if (nomNvaMecanica.equals("")) {
                return;
            }

            List<TblMecanica> mecs = serviceArquitecturaSeven.getMecanicaByPrograma(arqPeriodo.getIdPeriodo(), arqPeriodo.getIdPrograma());
            for (TblMecanica m : mecs){
                if (m.getNombreMecanica() != null && m.getNombreMecanica().equalsIgnoreCase(nomNvaMecanica)){
                    Messages.mensajeAlerta("No se pueden crear 2 mecanicas con el mismo nombre", "");
                    return;
                }
            }

            MecanicaDTO mecanicaDTO_local = new MecanicaDTO();
            mecanicaDTO_local.setNombreMecanica(nomNvaMecanica);
            mecanicaDTO_local.setIdCampana(arqPeriodo.getIdPeriodo());
            mecanicaDTO_local.setProgramaId(arqPeriodo.getIdPrograma());
            mecanicaDTO_local.setHoraFin(arqPeriodo.getHoraFin());
            mecanicaDTO_local.setHoraIni(arqPeriodo.getHoraInicio());
            mecanicaDTO_local.setLunes(0);
            mecanicaDTO_local.setMartes(0);
            mecanicaDTO_local.setMiercoles(0);
            mecanicaDTO_local.setJueves(0);
            mecanicaDTO_local.setViernes(0);
            mecanicaDTO_local.setSabado(0);
            mecanicaDTO_local.setDomingo(0);
            mecanicaDTO_local.setIdPromo(0);
            mecanicaDTO_local.setIngresoPopReal(0);

            int idmecanica = serviceArquitecturaSeven.saveMecanica(mecanicaDTO_local);
                                
            lMecanica.add(new SelectItem(idmecanica, mecanicaDTO_local.getNombreMecanica()));

            arqMecanica = new ArqMecanica();
            arqMecanica.setIdMecanica(mecanicaDTO_local.getPkMec());
                    
            enableGridComponente(false);
            TblMecanica tblMecanica = crearActividadesMecanica(idmecanica);
            
            arqMecanica.setlGrupoZonas(lGrupoZonasPrec);
            
            mecanicaDTO = serviceArquitecturaSeven.getMecanica(idmecanica);
            arqMecanica.setDto(mecanicaDTO);

            LOG.info("id de campa?a: " + mecanicaDTO.getIdCampana());
            LOG.info("id de programa: " + arqPeriodo.getIdPrograma());
            LOG.info("obteniendo campania de mecanica");
            TblCampanaProgramas campanaProgramas = serviceCatPrograma.getTblCampanaProgramasById(mecanicaDTO.getIdCampana() ,arqPeriodo.getIdPrograma());

            if (campanaProgramas != null){
            LOG.info("programa no nulo");
            List<String> gzonas = new ArrayList<>();
            List<String> zonasCamp = new ArrayList<>();
            List<String> tiendasCamp = new ArrayList<>();

            LOG.info("cargando grupos de zonas, zonas y tiendas");
            for (RelGrupoZonaCampana r : campanaProgramas.getGrupoZonas()){
                LOG.info("agregando grupo: " + r.getGrupoId());
                gzonas.add(String.valueOf(r.getGrupoId()));
            }

            for (RelZonaCampana r : campanaProgramas.getZonas()){
                LOG.info("agregando zona: " + r.getZonaId());
                zonasCamp.add(String.valueOf(r.getZonaId()));
            }

            List<RelStoreCampana> list = serviceCampana.getRelStoreCampanaByCampanaPrograma(mecanicaDTO.getIdCampana() ,arqPeriodo.getIdPrograma());
            campanaProgramas.setTiendas(new HashSet<>(list));
            if(campanaProgramas.getTiendas()!=null){
            	for (RelStoreCampana r : campanaProgramas.getTiendas()){
                    LOG.info("agregando tienda: " + r.getStoreId());
                    tiendasCamp.add(String.valueOf(r.getStoreId()));
                }
            }
            arqMecanica.setlGrupoZonas(gzonas);
            arqMecanica.setlZonas(zonasCamp);
            arqMecanica.setlTiendas(tiendasCamp);
            
            LOG.info("Cargando grupo de zonas");
            if (arqMecanica.getlGrupoZonas() != null){
                mZones = new ArrayList<>();
                List<Integer> ids = new ArrayList<>();
                for (String z : arqMecanica.getlGrupoZonas()){
                    ids.add(Integer.valueOf(z));
                }
                mZones.addAll(serviceCatZone.getCatZonesByGrupoZonas(ids));
            }

            LOG.info("cargando tiendas");
            if (arqMecanica.getlZonas() != null) {
                mStores = new ArrayList<>();
                List<Integer> ids = new ArrayList<>();
                for (String gz : arqMecanica.getlZonas()) {
                    ids.add(Integer.valueOf(gz));
                }
                mStores.addAll(MBUtil.cargacomboStores(serviceCatStore, ids));
            }
            }else{
                LOG.info("programa nulo");
            }
            
            if (Util.convertProgramaEtapa(etapaPrograma) == EtapaDashboard.PENDIENTE){
                etapaPrograma = Util.convertProgramaEtapa(EtapaDashboard.CAPTURA);
                changeEstatusPrograma(arqPeriodo.getIdPeriodo(), arqPeriodo.getIdPrograma(), EtapaDashboard.CAPTURA);
            }
            
            ArqSevenTreeNode campanaNode = (ArqSevenTreeNode) parentSelected.getData();
            TblCampana tblCampana = campanaNode.getTblCampana();
            ArqSevenTreeNode mecProgTreeNode = new ArqSevenTreeNode(mecanicaDTO_local.getNombreMecanica(),tblCampana.getCatEtiquetas().getCodigo(),tblCampana, campanaProgramas, tblMecanica);
            new DefaultTreeNode(mecProgTreeNode, parentSelected);
            
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);
        }
    }
 // ************************************ Dashboard ************************************* //
    private ServiceCatActPred serviceCatActPred;
    private ServiceTblActividades serviceTblActividades;
    private ServiceCatEstatus serviceCatEstatus;
    private ServiceUsuarios serviceUsuarios;

    private boolean programaActivo;
    private boolean programaRevisionDiseno;
    private String etapaPrograma;
    private boolean porcentajeRetencionStrActivo;
    
    private TblMecanica crearActividadesMecanica(int idmecanica){
        List<CatActPred> catalogo = serviceCatActPred.getCatologoActividades();
        CatEstatus estatusAb = serviceCatEstatus.getEstatusByNameContains("Abiert");
        CatEstatus estatusPen = serviceCatEstatus.getEstatusByNameContains("Pendiente");
        Collections.sort(catalogo, new Comparator<CatActPred>() {
            @Override
            public int compare(CatActPred o1, CatActPred o2) {
                return o1.getOrden().compareTo(o2.getOrden());
            }
        });
        TblMecanica mecanica = serviceArquitecturaSeven.getMecanicaById(idmecanica);
        CatFlujoAct flujoAct = mecanica.getCampana().getCatFlujoAct();
        TblActividad beforeAct = null;
        Date fechaInicio = calcularFechaInicio(flujoAct.getDiasantes(), catalogo, arqPeriodo.getFechaInicio());
        CatCategory cat = new ArrayList<>(usuarioLogeado.getRelUsuariosCategoriases()).get(0).getCatCategory();
        for (CatActPred actPred : catalogo){
            TblActividad tblActividad = new TblActividad();
            tblActividad.setOrden(actPred.getOrden());
            tblActividad.setEstatusEscalable(0);
            RelFlujoAct relFlujoActs = new ArrayList<>(actPred.getRelFlujoActs()).get(0);
            tblActividad.setVigenciaInicio(fechaInicio);
            tblActividad.setAlerta(Util.sumarRestarDiasFecha(fechaInicio, relFlujoActs.getAlerta()));
            fechaInicio = Util.sumarRestarDiasFecha(fechaInicio, relFlujoActs.getDias());
            tblActividad.setVigenciaFinal(fechaInicio);
            tblActividad.setDescripcion(actPred.getDescripcion());
            tblActividad.setFechaCreacion(new Date());
            tblActividad.setTblMecanica(mecanica);
            tblActividad.setNivelEscalable(actPred.getNivelEscalable());
            tblActividad.setAvance(0);
            tblActividad.setCatEstatus(actPred.getOrden() == 1 ? estatusAb : estatusPen);
            tblActividad.setCatUsuariosByIdcreador(usuarioLogeado);
            tblActividad.setIdactbefore(beforeAct == null ? 0 : beforeAct.getIdactividad());
            tblActividad.setCatRole(actPred.getRole());
            tblActividad.setCatUsuariosByIdresponsable(serviceUsuarios.getUsuarioByRoleAndCategory(actPred.getRole().getIdrole(), cat.getIdCategory()));
            if(!serviceTblActividades.saveActividad(tblActividad)){
                LOG.info("Crear Actividades Mecanica: Error al guardar Actividad: " + tblActividad.getDescripcion());
            }else{
                if (beforeAct != null){
                    beforeAct.setIdactafter(tblActividad.getIdactividad());
                    if(!serviceTblActividades.saveActividad(beforeAct)){
                        LOG.info("Error al actualizar Actividad: " + beforeAct.getDescripcion());
                    }
                }
                beforeAct = tblActividad;
            }
        }
        return mecanica;
    }

    public void checkDisenoEstatus (){
        TblMecanica mecanica = serviceArquitecturaSeven.getMecanicaById(verDisenoIdMecanica);
        List<TblMecanica> mecanicaList = serviceArquitecturaSeven.getAllMecanica((int) mecanica.getCampana().getIdCampana(), verDisenoProgramaId);
        boolean allDisenosAuth = true;
        for (TblMecanica m : mecanicaList){
            try {
                List<DisenosDTO> disenosList = serviceArquitecturaSeven.getDisenosByIdMecanica(m.getMecanicaId());
                if (disenosList != null){
                    for (DisenosDTO d : disenosList){
                        if (!d.getAuth()){
                            LOG.info("Dise?o sin autorizar: Id = " + d.getDisenoId() + ", Mecanica: " + d.getMecanicaStr());
                            allDisenosAuth = false;
                            break;
                        }
                    }
                }else{
                    allDisenosAuth = false;
                    LOG.info("Mecanica no tiene dise?os: " + m.getNombreMecanica());
                }
                if (!allDisenosAuth){
                    break;
                }
            } catch (Exception e) {
                LOG.info("Mecanica no tiene dise?os: " + m.getNombreMecanica());
            }
        }
        if (allDisenosAuth){
            closeRevisionDiseno(mecanica);
        }
    }

    private void closeRevisionDiseno(TblMecanica mecanica){
        List<TblMecanica> mecanicaList = serviceArquitecturaSeven.getAllMecanica((int) mecanica.getCampana().getIdCampana(), verDisenoProgramaId);
        CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Cerrado");
        CatEstatus abierto = serviceCatEstatus.getEstatusByNameContains("Abierto");
        if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicaList, cerrado, EtapaDashboard.REV_DISENO)){
            if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicaList, abierto, EtapaDashboard.ENVIO_DISENO)){
                Messages.mensajeSatisfactorio("Actividades Actualizadas", "");
                changeEstatusPrograma(mecanica.getCampana().getIdCampana(), verDisenoProgramaId, EtapaDashboard.ENVIO_DISENO);
                programaActivo = false;
            }else{
                Messages.mensajeErroneo("Error al abrir actividades", "");
            }
        }else{
            Messages.mensajeErroneo("Error al cerrar actividades", "");
        }
    }

    public void disenoRechazado(){
        TblMecanica mecanica = serviceArquitecturaSeven.getMecanicaById(verDisenoIdMecanica);
        CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Pendiente");
        CatEstatus reabierto = serviceCatEstatus.getEstatusByNameContains("Re-");
        
        TblActividad disenoAct = serviceTblActividades.getTblActividadByMecanicaAndOrden(verDisenoIdMecanica, 3);
        TblActividad revisionDiseno = serviceTblActividades.getTblActividadByMecanicaAndOrden(verDisenoIdMecanica, 4);
        
        disenoAct.setCatEstatus(reabierto);
        disenoAct.setFechaFin(null);
        revisionDiseno.setCatEstatus(cerrado);
        if (serviceTblActividades.updateActividad(disenoAct) && serviceTblActividades.updateActividad(revisionDiseno)){
            Messages.mensajeSatisfactorio("Actividades Actualizadas", "");
            changeEstatusPrograma(mecanica.getCampana().getIdCampana(), verDisenoProgramaId, EtapaDashboard.DISENO);
            String[] mailsTo = {disenoAct.getCatUsuariosByIdresponsable().getEmail(), revisionDiseno.getCatUsuariosByIdresponsable().getEmail()};
            try {
                SendMail.sendRechazoDisenoMail(mailsTo, revisionDiseno, mecanica.getNombreMecanica(), rolUsuario);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Messages.mensajeErroneo("Error al actualizar actividades", "");
        }
    }

    public void precioRechazado(){
        TblMecanica mecanica = serviceArquitecturaSeven.getMecanicaById(idMecanicaPrec);
        CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Pendiente");
        CatEstatus reabierto = serviceCatEstatus.getEstatusByNameContains("Re-");
        TblActividad capturaAct = serviceTblActividades.getTblActividadByMecanicaAndOrden(mecanica.getMecanicaId(), 1);
        TblActividad revisionPreciosAct = serviceTblActividades.getTblActividadByMecanicaAndOrden(mecanica.getMecanicaId(), 2);
        capturaAct.setCatEstatus(reabierto);
        capturaAct.setFechaFin(null);
        revisionPreciosAct.setCatEstatus(cerrado);
        if (serviceTblActividades.updateActividad(revisionPreciosAct) && serviceTblActividades.updateActividad(capturaAct)){

            if (changeEstatusPrograma(mecanica.getCampana().getIdCampana(), mecanica.getProgramaId() ,EtapaDashboard.CAPTURA)){
                Messages.mensajeSatisfactorio("Actividades Actualizadas ", "");
                if(capturaAct.getCatUsuariosByIdresponsable() != null && revisionPreciosAct.getCatUsuariosByIdresponsable() != null){
                String [] mailsTo = {capturaAct.getCatUsuariosByIdresponsable().getEmail(), 
                        revisionPreciosAct.getCatUsuariosByIdresponsable().getEmail()};
                    try {
                        String msj = "Precios rechazados de la mecanica " + mecanica.getNombreMecanica() + " " +
                                "<br /><br />Usuario que rechazo: " + revisionPreciosAct.getCatUsuariosByIdresponsable().getNombre() + " " +
                                "" + revisionPreciosAct.getCatUsuariosByIdresponsable().getPlastName();
                        SendMail.sendGenericEmail(mailsTo, "Precios Rechazados", msj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                Messages.mensajeErroneo("Error al actualizar estatus programa", "");
            }

        }else{
            Messages.mensajeErroneo("Error al actualizar actividades", "");
        }
    }

    private boolean changeEstatusPrograma(long idCampana, int idPrograma, EtapaDashboard etapa){
        return serviceCampana.changeEtapaPrograma((int) idCampana, idPrograma, Util.convertProgramaEtapa(etapa));
      /*  List<TblCampanaProgramas> p = serviceCampana.getCampanaProgramas(idCampana);

        for (TblCampanaProgramas t : p){
            if (t.getId().getIdPrograma() == idPrograma){
                t.setEtapa(Util.convertProgramaEtapa(etapa));
              //  try {
                    //serviceCampana.updateCampanaProgramas(t);

              //  } catch (GeneralException e) {
              //      e.printStackTrace();
              //  }
            }
        } */
    }

    public void closeActividadesDiseno (){
        TblMecanica mecanica = serviceArquitecturaSeven.getMecanicaById(idMecanicaPrec);
        List<TblMecanica> mecanicas = serviceArquitecturaSeven.getMecanicaByPrograma(mecanica.getCampana().getIdCampana(), mecanica.getProgramaId());
        CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Cerrado");
        CatEstatus abierto = serviceCatEstatus.getEstatusByNameContains("Abierto");
        if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, cerrado, EtapaDashboard.DISENO)){
            if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, abierto, EtapaDashboard.REV_DISENO)){
                Messages.mensajeSatisfactorio("Actividades Cerradas", "");
                changeEstatusPrograma(mecanica.getCampana().getIdCampana(), mecanica.getProgramaId() ,EtapaDashboard.REV_DISENO);
                programaActivo = false;
                etapaPrograma = Util.convertProgramaEtapa(EtapaDashboard.REV_DISENO);
                enviarEmailCierre(mecanicas, EtapaDashboard.DISENO, EtapaDashboard.REV_DISENO);
            }else{
                Messages.mensajeErroneo("Error al abrir actividades Dise?o", "");
            }
        }else{
            Messages.mensajeErroneo("Error al cerrar actividades");
        }
    }

    public void closeActividadesPrecio (){
        TblMecanica mecanica = serviceArquitecturaSeven.getMecanicaById(idMecanicaPrec);
        List<TblMecanica> mecanicas = serviceArquitecturaSeven.getMecanicaByPrograma(mecanica.getCampana().getIdCampana(), mecanica.getProgramaId());
        CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Cerrado");
        CatEstatus abierto = serviceCatEstatus.getEstatusByNameContains("Abierto");
        if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, cerrado, EtapaDashboard.REV_PRECIOS)){
            if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, abierto, EtapaDashboard.DISENO)){
                if (changeEstatusPrograma(mecanica.getCampana().getIdCampana(), mecanica.getProgramaId() ,EtapaDashboard.DISENO)){
                    Messages.mensajeSatisfactorio("Actividades Cerradas", "");
                    programaActivo = false;
                    etapaPrograma = Util.convertProgramaEtapa(EtapaDashboard.DISENO);
                    enviarEmailCierre(mecanicas, EtapaDashboard.REV_PRECIOS, EtapaDashboard.DISENO);
                }else{
                    Messages.mensajeErroneo("Error al actualizar estatus programa", "");
                }
            }else{
                Messages.mensajeErroneo("Error al abrir actividades Dise?o", "");
            }
        }else{
            Messages.mensajeErroneo("Error al cerrar actividades");
        }
    }
    public void showMessage() {
//        Messages.mensajeSatisfactorio("El porcentaje de Distribuci?n Rebaja debe ser mayor a 0", "");
//        return;
    }
    public void closeActividadArqitectura (){
        
        LOG.info("Cerrado actividades");
        
        allPricesExists = true;
        
        List<TblMecanica> mecanicaList = serviceArquitecturaSeven.getMecanicaByPrograma(arqPeriodo.getIdPeriodo(), arqMecanica.getIdPrograma());
        for (TblMecanica mecanica : mecanicaList){
            if (!serviceArquitecturaSeven.checkPrecioExistsByMecanica(mecanica.getMecanicaId())){
                allPricesExists = false;
                break;
            }
        }
        
        LOG.info("Existen todoso los precios: " + allPricesExists);
        if(!allPricesExists){
            Messages.mensajeErroneo("Todos los componentes deben tener un precio asignado. Verifique.", "Todos los componentes deben tener un precio asignado. Verifique.");
            return;
        }
        
        List<TblMecanica> mecanicas = serviceArquitecturaSeven.getMecanicaByPrograma(arqPeriodo.getIdPeriodo(), arqPeriodo.getIdPrograma());
        CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Cerrado");
        CatEstatus abierto = serviceCatEstatus.getEstatusByNameContains("Abierto");
        if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, cerrado, EtapaDashboard.CAPTURA)){
            if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, abierto, EtapaDashboard.REV_PRECIOS)){
                Messages.mensajeSatisfactorio("Actividades Cerradas", "");
                changeEstatusPrograma(arqPeriodo.getIdPeriodo(), arqPeriodo.getIdPrograma(), EtapaDashboard.REV_PRECIOS);
                try {
                    updatePricesToPending();
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(MBArquitectura.class.getName()).log(Level.SEVERE, null, ex);
                }
                puedeAgregarComp = false;
                programaActivo = false;
                etapaPrograma = Util.convertProgramaEtapa(EtapaDashboard.REV_PRECIOS);
                enviarEmailCierre(mecanicas, EtapaDashboard.CAPTURA, EtapaDashboard.REV_PRECIOS);
            }else{
                Messages.mensajeErroneo("Error al abrir actividades de precios", "");
            }
        }else{
            Messages.mensajeErroneo("Error al cerrar actividades", "");
        }
    }
    
    private void updatePricesToPending() throws Exception{
        List<Integer> liZonas = new ArrayList<>();
        for (String zn : arqMecanica.getlZonas()) {
            liZonas.add(Integer.valueOf(zn));
        }
        obtenerPrecios(arqMecanica.getIdMecanica(), 0, liZonas, 0, 0);
        List<PreciosPromocionDTO> p = new ArrayList<PreciosPromocionDTO>();
        for(ArqComponente e : lArqCompPrecProm){
            if(e.getPreciosPromocionDTO() != null){
                PreciosPromocionDTO x = e.getPreciosPromocionDTO();
                x.setEstatusRevision(1);
                p.add(x);
            }
        }
        serviceArquitecturaSeven.savePrecios(p);
    }

    private void enviarEmailCierre (List<TblMecanica> mecanicas, EtapaDashboard etapaCierre, EtapaDashboard etapaApertura){
        int ordenCierre = Util.etapaDashboardToInt(etapaCierre);
        int ordenApertura = Util.etapaDashboardToInt(etapaApertura);
        List<String> mailList = new ArrayList<>();
        mailList.add(Util.getCoordinadorEmail(serviceUsuarios));
        String programa = "";
        String evento = "";
        for (TblMecanica m : mecanicas){
            TblActividad actCierre = serviceTblActividades.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), ordenCierre);
            TblActividad actApertura = serviceTblActividades.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), ordenApertura);
            String mailCierre = actCierre.getCatUsuariosByIdresponsable().getEmail();
            String mailApert = actApertura.getCatUsuariosByIdcreador().getEmail();
            if (!mailList.contains(mailCierre)){
                mailList.add(mailCierre);
            }
            if (!mailList.contains(mailApert)){
                mailList.add(mailApert);
            }
            if (programa.isEmpty()){
                TblCampanaProgramas prog = serviceCampana.getProgramaById(m.getCampana().getIdCampana(), m.getProgramaId());
                programa = prog.getPrograma().getNombre();
                evento = prog.getTblCampana().getNombre();
            }
            if (ordenApertura == 4){
                TblActividad actRevDiseno = serviceTblActividades.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), 5);
                String mailRevDiseno = actRevDiseno.getCatUsuariosByIdresponsable().getEmail();
                if (!mailList.contains(mailRevDiseno)){
                    mailList.add(mailRevDiseno);
                }
            }
        }


        String[] toMails = mailList.toArray(new String[mailList.size()]);
        String subject = "AVISO: "+ evento +": Cierre de Actividades - " + Util.convertProgramaEtapa(etapaCierre);
        String msj = "Este correo es solo un aviso de cambio de actividades<br /><br />" +
                "Periodo Promocional: " + evento + "<br />" +
                "Programa: " + programa + "<br />" +
                "Etapa Cierre: " + Util.convertProgramaEtapa(etapaCierre) + "<br />" +
                "Etapa Apertura: " + Util.convertProgramaEtapa(etapaApertura) + "<br /><br /><br />" +
                "Correo generado automaticamente, NO RESPONDER";
        SendMail.sendGenericEmail(toMails, subject, msj);
    }

    private Date calcularFechaInicio(int diasAntes, List<CatActPred> catalogo, Date inicioCampana){
        for (CatActPred c : catalogo){
            List<RelFlujoAct> temp = new ArrayList<>(c.getRelFlujoActs());
            for (RelFlujoAct t : temp){
                diasAntes += t.getDias();
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicioCampana);
        calendar.add(Calendar.DAY_OF_YEAR, 0 - diasAntes);
        return calendar.getTime();
    }

    public boolean isProgramaRevisionDiseno() {
        return programaRevisionDiseno;
    }

    public void setProgramaRevisionDiseno(boolean programaRevisionDiseno) {
        this.programaRevisionDiseno = programaRevisionDiseno;
    }

    public String getEtapaPrograma() {
        return etapaPrograma;
    }

    public void setEtapaPrograma(String etapaPrograma) {
        this.etapaPrograma = etapaPrograma;
    }

    public ServiceCatActPred getServiceCatActPred() {
        return serviceCatActPred;
    }

    public void setServiceCatActPred(ServiceCatActPred serviceCatActPred) {
        this.serviceCatActPred = serviceCatActPred;
    }

    public ServiceTblActividades getServiceTblActividades() {
        return serviceTblActividades;
    }

    public void setServiceTblActividades(ServiceTblActividades serviceTblActividades) {
        this.serviceTblActividades = serviceTblActividades;
    }

    public ServiceCatEstatus getServiceCatEstatus() {
        return serviceCatEstatus;
    }

    public void setServiceCatEstatus(ServiceCatEstatus serviceCatEstatus) {
        this.serviceCatEstatus = serviceCatEstatus;
    }

    public ServiceUsuarios getServiceUsuarios() {
        return serviceUsuarios;
    }

    public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
        this.serviceUsuarios = serviceUsuarios;
    }

    public boolean isProgramaActivo() {
        return programaActivo;
    }

    public void setProgramaActivo(boolean programaActivo) {
        this.programaActivo = programaActivo;
    }

    public ServiceCampana getServiceCampana() {
        return serviceCampana;
    }

    public void setServiceCampana(ServiceCampana serviceCampana) {
        this.serviceCampana = serviceCampana;
    }

    public boolean isPorcentajeRetencionStrActivo() {
		return porcentajeRetencionStrActivo;
	}

	public void setPorcentajeRetencionStrActivo(boolean porcentajeRetencionStrActivo) {
		this.porcentajeRetencionStrActivo = porcentajeRetencionStrActivo;
	}

	//**************************************************************************** //
    public void guardarMecanica() {
    	Long time = System.currentTimeMillis();
        try {
            MecanicaDTO mecanicaDTO_local = arqMecanica.getDto();
            mecanicaDTO_local.setIdCampana(arqPeriodo.getIdPeriodo());
            mecanicaDTO_local.setProgramaId(arqPeriodo.getIdPrograma());

            if (validateMecanica()) {
                mecanicaDTO_local.setIngresoPopReal(arqPeriodo.getIngresoReal());
                List<GenericItem> lGi = new ArrayList<>();
                for (String id : arqPeriodo.lCategorias) {
                    GenericItem gi = new GenericItem();
                    gi.setId(Integer.valueOf(id));
                    if (!lGi.contains(gi)) {
                        lGi.add(gi);
                    }
                }
                mecanicaDTO_local.setCategoriaList(lGi);
                mecanicaDTO_local.setProgramaId(arqPeriodo.getIdPrograma());
                LOG.info("before saveMecanica:  "+(System.currentTimeMillis()-time));

                long time2 = System.currentTimeMillis();
                serviceArquitecturaSeven.saveMecanica(mecanicaDTO_local);
                LOG.info("only saveMecanica:  "+(System.currentTimeMillis()-time2));
                /* se reconstruyen los componentes */
                LOG.info("obteniendo mecanica");
                
                time2 = System.currentTimeMillis();
                mecanicaDTO = serviceArquitecturaSeven.getMecanicaForComponenteSenalamiento(arqMecanica.getIdMecanica());
                LOG.info("getMecanica:  "+(System.currentTimeMillis()-time2));
                
                List<ArqComponente> lArqComp = prepararComponentes(mecanicaDTO.getComponenteList(),mecanicaDTO.getSenalamientoList());
                LOG.info("total de componentes: " +  lArqComp.size());
                
                time2 = System.currentTimeMillis();
                //se borran anteriores
                LOG.info("borrando componentes anteriores");
                borrarComponentes(lArqComp);
                LOG.info("componentes anteriores eliminados:  "+(System.currentTimeMillis()-time2));
                
                time2 = System.currentTimeMillis();
                calculaPrecioVentaZona(lArqComp);
                LOG.info("for:  "+(System.currentTimeMillis()-time2));
                
                Messages.mensajeSatisfactorio("La mec?nica se guard? exitosamente","La mec?nica se guard? exitosamente");
                int idMecanica = arqMecanica.getIdMecanica();
                
                
                arqMecanica = new ArqMecanica();
                arqComponente = new ArqComponente();
                lArqComponente = new ArrayList<>();
                lArqComponenteZona = new ArrayList<>();
                
                time2 = System.currentTimeMillis();
                obtenerMecanica(idMecanica);
                LOG.info("obtenerMecanica:  "+(System.currentTimeMillis()-time2));
                
                time2 = System.currentTimeMillis();
                enableGridComponente(false);
                LOG.info("enableGridComponente:  "+(System.currentTimeMillis()-time2));
            }
        } catch (Exception e) {
            LOG.error(e);
            e.printStackTrace();
            Messages.mensajeAlerta("Ocurrio un error al guardar la mec?nica","Ocurrio un error al guardar la mec?nica");
        }
        LOG.info("guardarMecanica(): "+(System.currentTimeMillis()-time));
    }
    
    private void borrarComponentes(final List<ArqComponente> lArqComp) throws Exception{
    	if(!lArqComp.isEmpty()){
	    	ExecutorService executor = Executors.newFixedThreadPool(lArqComp.size());
	    	for(final ArqComponente e : lArqComp){
	    		Runnable r = new Runnable() { @Override public void run() {
	    			try {
						serviceArquitecturaSeven.deleteComponenteZonaByComponenteId(e.getIdComponente());
					} catch (Exception e) {
						e.printStackTrace();
					}
	    		}};
	    		executor.execute(r);
	        }
	    	executor.shutdown();
	        while (!executor.isTerminated()){}
    	} 
    }
    
    private void calculaPrecioVentaZona(final List<ArqComponente> lArqComp) throws Exception{
    	Set<Integer> ids = new HashSet<>();
    	Set<String> skus = new HashSet<>();
    	List<Object[]> compZonas = new ArrayList<>();
    	for(final String zona : arqMecanica.getlZonas()){
    		Integer z = Integer.valueOf(zona);
    		ids.add(z);
    		for(final ArqComponente e : lArqComp){
        		skus.add(e.getSku());
        		compZonas.add(new Object[]{e.getIdComponente(),z});
        	}
    	}
    	
    	List<CatStore> stores = serviceCatStore.getCatStoreListByZone(new ArrayList<>(ids));
    	List<RelItemStore> itemsStore = this.serviceRelItemStore.getItemStoreByItemId(new ArrayList<>(skus));
    	List<TblComponenteZonaPrecio> czps = serviceArquitecturaSeven.getByComponentIdAndZoneId(compZonas);
    	
    	Map<Integer, List<CatStore>> mapTiendas = new HashMap<Integer, List<CatStore>>();
    	for(CatStore s: stores){
    		Integer idZona = null;
    		for(RelZoneStore zs: s.getRelZoneStores()){
    			idZona = zs.getId().getIdZone();
    			break;
    		}
    		if(idZona!=null){
    			List<CatStore> list = mapTiendas.get(idZona);
    			if(list==null){
    				list = new ArrayList<>();
    				mapTiendas.put(idZona, list);
    			}
    			list.add(s);
    		}else{
    			LOG.info("idZona nulo, VERIFICAR.");
    		}
    	}
    	
    	Map<String, List<RelItemStore>> mapItempStore = new HashMap<String, List<RelItemStore>>();
    	for(RelItemStore s: itemsStore){
    		String sku = s.getCatItem().getIdItem();
    		if(sku!=null){
    			List<RelItemStore> list = mapItempStore.get(sku);
    			if(list==null){
    				list = new ArrayList<>();
    				mapItempStore.put(sku, list);
    			}
    			list.add(s);
    		}else{
    			LOG.info("sku nulo, VERIFICAR.");
    		}
    	}
    	
    	Map<String, List<TblComponenteZonaPrecio>> mapCzp = new HashMap<String, List<TblComponenteZonaPrecio>>();
    	for(TblComponenteZonaPrecio czp: czps){
    		String key = czp.getComponenteId()+"_"+czp.getZonaId();
			List<TblComponenteZonaPrecio> list = mapCzp.get(key);
			if(list==null){
				list = new ArrayList<>();
				mapCzp.put(key, list);
			}
			list.add(czp);
    	}
    	
    	List<TblComponenteZonaPrecio> czpToSave = new ArrayList<>();
    	
    	for(final String zona : arqMecanica.getlZonas()){
    		LOG.info("Calculando precio de venta para zona: " + zona);
            final List<CatStore> tiendas = mapTiendas.get(Integer.valueOf(zona));
            LOG.info("total de tiendas en la zona: " + tiendas);
            if(tiendas != null){
                for(final ArqComponente e : lArqComp){
            		try {
            			LOG.info("Iterando componente");
            	        TblComponenteZonaPrecio czp = null;

            	        LOG.info("Buscando componente");
            	        String key = e.getIdComponente()+"_"+zona;
            	        List<TblComponenteZonaPrecio> l = mapCzp.get(key);
            	        if(l != null && l.size() > 0){
            	            czp = l.get(0);
            	        }else{
            	            czp = new TblComponenteZonaPrecio();
            	        }
            	        
            	        LOG.info("buscando precio de venta por zona");
            	        czp = this.getPrecioVentaFromStores(mapItempStore.get(e.getSku()), tiendas, czp);
            	        LOG.info("precio de zona obtenido");
            	        czp.setCantidad(new Double(e.getCantidadProducto()));
            	        czp.calculaPrecioVentaYMargen();
            	        czp.setComponenteId(e.getIdComponente());
            	        czp.setZonaId(Integer.parseInt(zona));
            	        czpToSave.add(czp);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
                }
            }else{
               LOG.info("tiendas nulo para zona");
            }
        }
    	LOG.info("Saving TblComponenteZonaPrecio...");
    	Long time = System.currentTimeMillis();
    	serviceArquitecturaSeven.insertOrUpdateComponenteZonaPrecio(czpToSave);
    	LOG.info("Total Time Saving TblComponenteZonaPrecio: "+(System.currentTimeMillis()-time));
    }
    
    private void guardarComponentesLista() {
		try {
			List<Componente> lstComponente = serviceArquitecturaSeven.getComponentesFromLista(arqComponente.getIdLista());
			if(lstComponente.size() > 0)
			{
				arqComponente.setIdMecanica(arqMecanica.getIdMecanica());
				if (validateComponentesLista()) {
					for(Componente c : lstComponente){
                        if (!arqComponente.getlSku().contains(c.getSKU())){
                           continue;
                        }
						ComponenteDTO compDTO = arqComponente.getDto();
						compDTO.setIdLista(c.getIdLista());
						//compDTO.setIdDescription(Integer.parseInt(c.getSKU()));
                        compDTO.setIdDescription(c.getSKU());
						compDTO.setIdCateg(c.getIdCategory());
						compDTO.setIdSubCategoria(c.getIdSubCategory());
						arqComponente.setDescripcion(c.getCode());
						arqComponente.setIdCategoria(c.getIdCategory());
						//arqComponente.setIdDescripcion(Integer.parseInt(c.getSKU()));
                        arqComponente.setIdDescripcion(c.getSKU());
						arqComponente.setIdSubCategoria(c.getIdSubCategory());
						arqComponente.setSku(c.getSKU());
						arqComponente.setIdUpc(c.getUPC());
						arqComponente.setUpc(c.getUPC());
                        
                        String sku = arqComponente.getSku();
                        LOG.info("obteniendo precio de venta actual de objeto con sku: " + sku);
                        Double precioVenta = getRelItemStorePrice(sku);
                        LOG.info("precio de venta calculado: " + precioVenta);
                        arqComponente.setPrecioVentaOriginal(precioVenta);
                        
                        
						//TODO Agregar listasku para que muestre el boton editar
						serviceArquitecturaSeven.saveComponentes(arqComponente.getDto(), arqPeriodo.getIdPrograma(), arqMecanica.getIdMecanica());
                        
                        //despues de agregar el componente, se ocupa el id para guardar los precio al crear por zona   
					}
					arqComponente = new ArqComponente();
					Messages.mensajeSatisfactorio("Componentes de la lista agregados exitosamente",
							"Componentes de la lista agregados exitosamente");
					obtenerMecanica(arqMecanica.getIdMecanica());
					enableGridComponente(false);
				}
			}
			else{
				Messages.mensajeAlerta("No se han agregado componentes, la lista seleccionada est? vac?a.", "No se han agregado componentes.");
			}
		} catch (Exception e) {
			LOG.error(e);
			Messages.mensajeAlerta("Ocurrio un error al guardar el componente",
					"Ocurrio un error al guardar el componente");
		}
	}
    
    private Double getRelItemStorePrice(String sku){
        
        //se obtienen los precios de todas las tiendas
        List<RelItemStore> itemsStore = this.serviceRelItemStore.getItemStoreByItemId(sku);
        
        List<Double> precios = new ArrayList<Double>();
        
        if(itemsStore != null){
            for(RelItemStore r : itemsStore){
                precios.add(r.getPrecioAtual().doubleValue());
            }
            if(precios.size() > 0){
                return MBUtil.getMode(precios); 
            }else{
                return 0D;
            }
        }else{
            return 0D;
        }
           
    }

    public void guardarComponente() {
    	try {
    		if(arqComponente.getIdLista() != 0){
    			LOG.info("guardando listado de componentes");
    			guardarComponentesLista();
    		}else{
    			LOG.info("guardando 1 componente");
    			if (validateComponente()) {
    				arqComponente.setIdMecanica(arqMecanica.getIdMecanica());
    				for(String currentSku : arqComponente.getlSku()){
    					arqComponente.setSku(currentSku);
    					CatItem currentItem = this.serviceCatItem.getCatItemById(currentSku);
    					arqComponente.setDescripcion(currentItem.getCode());
    					arqComponente.setIdDescripcion(currentSku);

    					LOG.info("obteniendo precio de venta actual de objeto con sku: " + currentSku);
    					Double precioVenta = getRelItemStorePrice(currentSku);
    					LOG.info("precio de venta calculado: " + precioVenta);
    					arqComponente.setPrecioVentaOriginal(precioVenta);

    					if (arqComponente.getIdSubCategoria() == 0){
    						CatItem item = serviceCatItem.getCatItemById(currentSku);
    						arqComponente.setIdSubCategoria(item.getIdSubcategory());
    						arqComponente.setIdCategoria(item.getCatCategory().getIdCategory());
    					}
    					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    					LOG.info("sacando upcs de sku: " + currentSku);
    					List<CatListDet> upcs = MBUtil.cargarComboUPCByItemID(serviceCatListDet, currentSku);

    					for (String upc : lUpcComp) {
    						LOG.info("iterando upc seleccionado: " + upc);
    						LOG.info("total de upcs de sku: " +  upcs.size());
    						for(CatListDet catUps : upcs){
    							LOG.info("iterando upc de catalogo: " + catUps.getId());
    							if(upc.equals(catUps.getId())){
    								LOG.info("coinciden, guardando");
    								arqComponente.setIdUpc(upc);
    								arqComponente.setUpc(upc);
    								serviceArquitecturaSeven.saveComponentes(arqComponente.getDto(), arqPeriodo.getIdPrograma(), arqMecanica.getIdMecanica());
    							}
    						}
    					}
    				}

    				arqComponente = new ArqComponente();
    				idDescripcionCombo = null;
    				idDescFiltroProv = null;
    				Messages.mensajeSatisfactorio("El componente se guard? exitosamente","El componente se guard? exitosamente");
    				lCategoriasSelected = new ArrayList<>();
    				obtenerMecanica(arqMecanica.getIdMecanica());
    				enableGridComponente(false);
    			}
    		}

    		LOG.info("--------------------------------------------------------------------------------");
    		LOG.info("guardando precio por zona para componente");
    		for(String zona : arqMecanica.getlZonas()){
    			LOG.info("Calculando precio de venta para zona: " + zona);

    			//List<CatStore> tiendas = MBUtil.cargacomboStores(serviceCatStore, zona);

    			//if(tiendas != null){
    			//LOG.info("total de tiendas: " + tiendas.size());

    			for(ArqComponente e : lArqComponente){

    				TblComponenteZonaPrecio czp = null;

    				List<TblComponenteZonaPrecio> l = this.serviceArquitecturaSeven.getByComponentIdAndZoneId(e.getIdComponente(), Integer.parseInt(zona));
    				if(l != null && l.size() > 0){
    					czp = l.get(0);
    				}else{
    					czp = new TblComponenteZonaPrecio();
    				}

    				czp.setZonaId(Integer.parseInt(zona));

    				czp = this.serviceRelItemStore.getPromotionPriceByZone(czp, e.getSku());

    				//czp = this.getPrecioVentaFromStores(e.getSku(), tiendas, czp);

    				czp.setCantidad(new Double(e.getCantidadProducto()));
    				czp.calculaPrecioVentaYMargen();
    				czp.setComponenteId(e.getIdComponente());

    				//LOG.info(czp);

    				czp = this.serviceArquitecturaSeven.insertOrUpdateComponenteZonaPrecio(czp);
    			}
    		}
    	} catch (Exception e) {
    		LOG.error(e);
    		Messages.mensajeAlerta("Ocurrio un error al guardar el componente","Ocurrio un error al guardar el componente");
    	}
    }
    
    public Double obtenerPrecioActualPorZona(String sku, String zona){
        Double precio = 0D;
        List<CatStore> tiendas = MBUtil.cargacomboStores(serviceCatStore, zona);
        List<RelItemStore> itemsStore = this.serviceRelItemStore.getItemStoreByItemId(sku);
        List<RelItemStore> zoneStores = new ArrayList<RelItemStore>();
        for(RelItemStore store : itemsStore){
            if(store.getCatStore() != null){
                for(CatStore s: tiendas ){
                    if(store.getCatStore().getIdStore() == s.getIdStore()){
                        zoneStores.add(store);
                        break;
                    }
                } 
            }
        }
        List<Double> precios = new ArrayList<Double>();
        if(zoneStores != null){
            for(RelItemStore r : zoneStores){
                precios.add(r.getPrecioAtual().doubleValue());
            }
            if(precios.size() > 0){   
               return MBUtil.getMode(precios);
            }
        }
        return precio;
    }
    
    public TblComponenteZonaPrecio getPrecioVentaFromStores(List<RelItemStore> itemsStore, List<CatStore> stores, TblComponenteZonaPrecio e){
        
        List<RelItemStore> zoneStores = new ArrayList<RelItemStore>();
        
        //por cada tienda que tenga el elemento registrado
        for(RelItemStore store : itemsStore){
            if(store.getCatStore() != null){
                //por cada tienda de la zona
                for(CatStore s: stores ){
                    //si son iguales, la agrega
                    if(store.getCatStore().getIdStore() == s.getIdStore()){
                        zoneStores.add(store);
                        break;
                    }
                } 
            }
        }
        
        List<Double> precios = new ArrayList<Double>();
        List<Double> margenes = new ArrayList<Double>();
        List<Double> impuestos = new ArrayList<Double>();
        List<Double> porcentajes = new ArrayList<Double>();
        
        if(zoneStores != null){
            for(RelItemStore r : zoneStores){
                precios.add(r.getPrecioAtual().doubleValue());
                margenes.add(r.getMargen() == null ? 0D : Double.parseDouble(r.getMargen()));
                impuestos.add(r.getImpuesto().doubleValue());
                porcentajes.add(r.getPorcMargen().doubleValue());
            }
            if(precios.size() > 0){   
                e.setPrecioProducto(MBUtil.getMode(precios));
                e.setMargenRegularProducto(MBUtil.getMode(margenes));
                e.setImpuesto(MBUtil.getMode(impuestos));
                e.setPorcentajeMargenRegularProducto(MBUtil.getMode(porcentajes));
                return e;
            }
        }
        e.setPrecioProducto(0D);
        e.setMargenRegularProducto(0D);
        e.setImpuesto(0D);
        e.setPorcentajeMargenRegularProducto(0D);
        return e;
    }

    public void guardarPrimicia() {
        try {
            if (validatePrimicia()) {
                arqComponente.setIdMecanica(arqMecanica.getIdMecanica());
                ComponenteDTO dto = new ComponenteDTO();
                dto.setNumeroComponente(numCompPrimicia);
                dto.setPrimDescripcion(descPrimicia);
                dto.setPrimCat(categPrimicia);
                dto.setPrimPrecio(precioPrimicia);
                dto.setPrimUpc(upcPrimicia);
                dto.setIdDescription("0");
                dto.setCantidadProd(1);
                dto.setAbastoInicial(1);
                dto.setIdProveedor(0);
                dto.setIdCateg(categPrimicia);
                dto.setPkMec(arqMecanica.getIdMecanica());
                dto.setHoja(0);

                serviceArquitecturaSeven.saveComponentes(dto, arqPeriodo.getIdPrograma(),arqMecanica.getIdMecanica());
                arqComponente = new ArqComponente();
                Messages.mensajeSatisfactorio("El componente se guard? exitosamente", "El componente se guard? exitosamente");

                obtenerMecanica(arqMecanica.getIdMecanica());
                numCompPrimicia = 0;
                descPrimicia = "";
                categPrimicia = 0;
                precioPrimicia = 0;
                upcPrimicia = "";
            }
        } catch (Exception e) {
            LOG.error(e);
            Messages.mensajeAlerta("Ocurrio un error al guardar el primicia",
                                    "Ocurrio un error al guardar el primicia");
        }
    }

    public void borrarMecanica() {
            try {
                    serviceArquitecturaSeven.deleteMecanica(arqMecanica.getIdMecanica());
                    Messages.mensajeSatisfactorio("La mec?nica se borr? exitosamente", "La mec?nica se borr? exitosamente");
                    generarMenu((CatUsuarios) Util.getSessionAttribute("userLoged"));
                    List<TblMecanica> lTblMecanicas = serviceArquitecturaSeven.getAllMecanica((int) arqPeriodo.getIdPeriodo(), arqPeriodo.getIdPrograma());
                    if (lTblMecanicas != null) {
                            lMecanica = new ArrayList<>();
                            for (TblMecanica tblMecanica : lTblMecanicas) {
                                    lMecanica.add(new SelectItem(tblMecanica.getMecanicaId(),
                                                    tblMecanica.getNombreMecanica()));
                            }
                    }
                    arqMecanica = new ArqMecanica();
                    arqComponente = new ArqComponente();
            } catch (Exception e) {
                    LOG.error(e);
                    Messages.mensajeAlerta("Ocurrio un error al borrar la mec?nica: " + e.getMessage(),
                                    "Ocurrio un error al borrar la mec?nica");
            }
    }
    
    public void preBorrarComponente(){
    	RequestContext requestContext = RequestContext.getCurrentInstance();  
        requestContext.execute("confEliComp.show();");
    }

    public void borrarComponente() {
            try {
                    if (arqComponenteSel != null) {
                            serviceArquitecturaSeven.deleteComponente(arqComponenteSel
                                            .getIdComponente());
                            arqComponenteSel = new ArqComponente();
                            Messages.mensajeSatisfactorio(
                                            "El componente se elimin? exitosamente",
                                            "El componente se elimin? exitosamente");

                            obtenerMecanica(arqMecanica.getIdMecanica());
                    }
            } catch (Exception e) {
                    LOG.error(e);
                    Messages.mensajeAlerta(
                                    "Ocurrio un error al eliminar el componente",
                                    "Ocurrio un error al eliminar el componente");
            }
    }

    public void guardarDiasPromocion() {
            MecanicaDTO mecanicaDTO2 = arqMecanica.getDto();
            try {
                    mecanicaDTO2.setIdCampana(arqPeriodo.getIdPeriodo());
                    mecanicaDTO2.setProgramaId(arqPeriodo.getIdPrograma());
                    serviceArquitecturaSeven.saveMecanica(mecanicaDTO2);               
            } catch (Exception e) {
                    LOG.error(e);
            }
    } 
    
    
    public void guardarComponenteGrid() {   
        try {
            
            
            arqComponenteSel.updateChangeValues();
            
            if (arqComponenteSel != null) {
                ComponenteDTO componente = arqComponenteSel.getDto();
                int idPrograma = arqPeriodo.getIdPrograma();
                int idMecanica = arqMecanica.getIdMecanica();
                
                serviceArquitecturaSeven.saveComponentes(arqComponenteSel.getDto(), arqPeriodo.getIdPrograma(), arqMecanica.getIdMecanica());
                
                LOG.info("--------------------------------------------------------------------------------");
                LOG.info("guardando precio por zona para componente");
                for(String zona : arqMecanica.getlZonas()){
                    LOG.info("Calculando precio de venta para zona: " + zona);                    
                    TblComponenteZonaPrecio czp = null;
                    List<TblComponenteZonaPrecio> l = 
                            this.serviceArquitecturaSeven.getByComponentIdAndZoneId(arqComponenteSel.getIdComponente(), 
                                    Integer.parseInt(zona));
                    if(l != null && l.size() > 0){
                        czp = l.get(0);
                    }else{
                        czp = new TblComponenteZonaPrecio();
                    }
                    czp.setZonaId(Integer.parseInt(zona));
                    czp = this.serviceRelItemStore.getPromotionPriceByZone(czp, arqComponenteSel.getSku());
                    czp.setCantidad(new Double(arqComponenteSel.getCantProductos()));
                    czp.calculaPrecioVentaYMargen();
                    czp.setComponenteId(arqComponenteSel.getIdComponente());
                    czp = this.serviceArquitecturaSeven.insertOrUpdateComponenteZonaPrecio(czp);  
                }
                
                arqComponenteSel = new ArqComponente();
                Messages.mensajeSatisfactorio("El componente se edit? exitosamente","El componente se edit? exitosamente");

                
                
                
                obtenerMecanica(arqMecanica.getIdMecanica());
                LOG.info("Fin del proceso");
    
                
            }
        } catch (Exception e) {
            LOG.error(e);
            Messages.mensajeAlerta("Ocurrio un error al editar el componente","Ocurrio un error al editar el componente");
        }
    }

	public void valueChangeMecanica(ValueChangeEvent e) {

		if (e.getNewValue() != null) {
			Integer idMec = (Integer) e.getNewValue();
			obtenerMecanica(idMec);
		}
		enableGridComponente(false);
	}
	
	public void valueChangePromocion(ValueChangeEvent e) {

		if (e.getNewValue() != null) {
			Integer idP = (Integer) e.getNewValue();
			 arqMecanica.setIdPromocion(idP);
		}
	}
	
	public void valueChangeTipoPromocion(ValueChangeEvent e) {

		if (e.getNewValue() != null) {
			Integer idTp = (Integer) e.getNewValue();
			 arqMecanica.setIdTipoPromocion(idTp);
		}
	}
	

	public void valueChangeMecanicaPrec(ValueChangeEvent e) {

		if (e.getNewValue() != null) {
			Integer idMec = (Integer) e.getNewValue();

			if (idMec > 0) {

				idMecanicaPrec = idMec;
				obtenerMecanica(idMec);

				List<String> lSkuPre = null;
				if (lSkuPrec != null) {
					lSkuPre = new ArrayList<>();
					for (String sku : lSkuPrec) {
						lSkuPre.add(sku);
					}
				}

				List<String> lUpcPre = null;
				if (lUpcPrec != null) {
					lUpcPre = new ArrayList<>();
					for (String upc : lUpcPrec) {
						lUpcPre.add(upc);
					}
				}

				List<String> lZonasAux = new ArrayList<>();
				if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
						&& (lZonasPrec == null || lZonasPrec.isEmpty())) {
					lZonasAux.addAll(lZonaXGrupo);
				} else {
					if (lZonasPrec != null) {
						lZonasAux.addAll(lZonasPrec);
					}
				}

				if (lZonasAux.isEmpty()) {
					lZonasAux.addAll(arqMecanica.getlZonas());
				}
                LOG.info("Llamando a obtenerPreciosPromocion desde: valueChangeMecanicaPrec");
				obtenerPreciosPromocion(idMec, idCatPrec, idSubCatPrec,
						idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
						lZonasAux);
			}
		}
	}

	private ServiceCatGZone serviceCatGZone;

	private ServiceCatZone serviceCatZone;

	private ServiceCatStore serviceCatStore;

	private void obtenerMecanica(int idMecanica) {
        try {
            if (idMecanica > -1) {
                LOG.info("obteniendo mecanica");
                mecanicaDTO = serviceArquitecturaSeven.getMecanica(idMecanica);
                arqMecanica.setDto(mecanicaDTO);
                
                //para mantener mecanica seleccionado
                if(nodoSeleccionado.getTblMecanica() !=null){
	                nodoSeleccionado.getTblMecanica().setMecanicaId(idMecanica);
	                nodoSeleccionado.getTblMecanica().setNombreMecanica(mecanicaDTO.getNombreMecanica());
	                nodoSeleccionado.getTblMecanica().setProgramaId(mecanicaDTO.getProgramaId());
                }
                if (arqMecanica.getlGrupoZonas() != null && !arqMecanica.getlGrupoZonas().isEmpty()) {
                    LOG.info("cargando lista de zonas");
                    List<Integer> ids = new ArrayList<>();
                    for (String gz : arqMecanica.getlGrupoZonas()) {
        				ids.add(Integer.valueOf(gz));
        			}
                    mZones = serviceCatZone.getCatZonesByGrupoZonas(ids);
                    LOG.info("fin de carga de lista de zonas");
                }else{
                    LOG.info("Mecanica no tiene zonas");
                    List<String> gzonas = new ArrayList<>();
                    List<String> zonasCamp = new ArrayList<>();
                    List<String> tiendasCamp = new ArrayList<>();

                    LOG.info("cargando grupos de zonas, zonas y tiendas");
                    for (RelGrupoZonaCampana r : serviceCampana.getRelGrupoZonaCampanaByCampanaPrograma(mecanicaDTO.getIdCampana(), mecanicaDTO.getProgramaId())){
                        gzonas.add(String.valueOf(r.getGrupoId()));
                    }

                    for (RelZonaCampana r : serviceCampana.getRelZonaCampanaByCampanaPrograma(mecanicaDTO.getIdCampana(), mecanicaDTO.getProgramaId())){
                        zonasCamp.add(String.valueOf(r.getZonaId()));
                    }

                    for (RelStoreCampana r : serviceCampana.getRelStoreCampanaByCampanaPrograma(mecanicaDTO.getIdCampana(), mecanicaDTO.getProgramaId())){
                        tiendasCamp.add(String.valueOf(r.getStoreId()));
                    }
                    arqMecanica.setlGrupoZonas(gzonas);
                    arqMecanica.setlZonas(zonasCamp);
                    arqMecanica.setlTiendas(tiendasCamp);
                }

                LOG.info("Cargando grupo de zonas");
                if (arqMecanica.getlGrupoZonas() != null){
                	List<Integer> ids = new ArrayList<>();
                    for (String gz : arqMecanica.getlGrupoZonas()) {
        				ids.add(Integer.valueOf(gz));
        			}
                    mZones = serviceCatZone.getCatZonesByGrupoZonas(ids);
                }

                LOG.info("cargando tiendas");
                if (arqMecanica.getlZonas() != null) {
                    mStores = new ArrayList<>();
                    List<Integer> ids = new ArrayList<Integer>();
                    for (String gz : arqMecanica.getlZonas()) {
                    	ids.add(Integer.valueOf(gz));
                    }
                    mStores.addAll(serviceCatStore.getCatStoreListByZone(ids));
                }

                arqPeriodo.setIngresoReal(mecanicaDTO.getIngresoPopReal());
                puedeAgregarComp = puedeAgregarComponente();

                List<Integer> categoriasIds = serviceCampana.getCategoriasIdsByCampanaIdAndPrograma(mecanicaDTO.getIdCampana(), mecanicaDTO.getProgramaId());
                if (categoriasIds != null) {
                	List<String> catsIds = new ArrayList<String>();
                	for(Integer cid: categoriasIds){
                		catsIds.add(cid.toString());
                    }
                	arqPeriodo.setlCategorias(catsIds);
                    setCategoriasSelected();
                }

                if (mecanicaDTO.getCategoriaList() != null) {
                    arqPeriodo.setIdPrograma(mecanicaDTO.getProgramaId());
                }

                if (mecanicaDTO.getConfMecanicaDTO() != null) {
                    List<ConfigMecanicaDet> lConfigMecDet = new ArrayList<>();
                    for (ConfMecanicaDTO cnofMecDTO : mecanicaDTO.getConfMecanicaDTO()) {
                        ConfigMecanicaDet configMecDet = new ConfigMecanicaDet();
                        configMecDet.setIdCategoria(cnofMecDTO.getCategoriaId());
                        configMecDet.setDistCantidad(cnofMecDTO.getAhorro());
                        configMecDet.setDistPorcentaje(cnofMecDTO.getAhorroPorcentaje());
                        for (CatCategory item : lCatCategories) {
                            if (item.getIdCategory() == cnofMecDTO.getCategoriaId()) {
                                configMecDet.setDescCategoria(item.getCode());
                                break;
                            }
                        }

                        lConfigMecDet.add(configMecDet);
                    }
                    ConfigMecanica confMec = new ConfigMecanica();
                    confMec.setIdMecanica(idMecanica);
                    confMec.setlConfigMecanicaDet(lConfigMecDet);
                    arqMecanica.setConfigMecanica(confMec);
                }

                lArqComponente = new ArrayList<>();
                if (mecanicaDTO.getComponenteList() != null) {
                    List<ArqComponente> lArqComp = prepararComponentes(mecanicaDTO.getComponenteList(),mecanicaDTO.getSenalamientoList());//tardado
                    lArqComponente.addAll(lArqComp);

                    lArqComponenteZona = new ArrayList<>();
                    Long temp2 = System.currentTimeMillis();
                    if (lArqComponente != null && arqMecanica.getlZonas() != null) {
                    	procesaComponentes();
                    }
                    LOG.info("last for:  "+(System.currentTimeMillis()-temp2));
                }
            } else {
                arqMecanica = new ArqMecanica();
                lArqComponente = new ArrayList<>();
                lArqComponenteZona = new ArrayList<>();
            }
        } catch (Exception e) {
        	e.printStackTrace();
            LOG.info("Error en ObtenerMecanica: " + e.getMessage());
        }
	}
	
	private void procesaComponentes(){
		List<Integer> ids = new ArrayList<>();
		for (final String sZona : arqMecanica.getlZonas()) {
			ids.add(Integer.valueOf(sZona));
		}
		List<CatZone> list = serviceCatZone.getCatZoneByIds(ids);
		Map<Integer, CatZone> mapZone = new HashMap<>();
		for(CatZone c: list){
			mapZone.put(c.getIdZone(), c);
		}
		
		for (final String sZona : arqMecanica.getlZonas()) {
            for (final ArqComponente arqComp : lArqComponente) {
            	Integer idZona = Integer.valueOf(sZona);
                CatZone cat = mapZone.get(idZona);
                arqComp.setIdZona(cat.getIdZone());
                arqComp.setZona(cat.getCode());
                arqComp.setIdGpoZona(cat.getCatGZone().getIdGrupoZona());
                arqComp.setGpoZona(cat.getCatGZone().getCode());
                ArqComponente arqCompAux = new ArqComponente(arqComp);
                lArqComponenteZona.add(arqCompAux);
            }
        }
	}

	private List<ArqComponente> prepararComponentes(List<ComponenteDTO> lComponenteDTO, final List<GenericItem> lSenalamientos) {
        LOG.info("Generando listado ArqComponente a partir de ComponenteDTO");
        Long time = System.currentTimeMillis();
		List<ArqComponente> lArqComp = new ArrayList<>();
		if(lComponenteDTO.isEmpty()) return lArqComp;
        for (ComponenteDTO componenteDTO : lComponenteDTO) {
			ArqComponente arqComp = new ArqComponente();
			arqComp.setDto(componenteDTO);
            
            LOG.info("seteando descripcion, sku y upc");
            if (!componenteDTO.getIdDescription().equals("0")){
                CatItem catItem = serviceCatItem.getCatItemById(String.valueOf(componenteDTO.getIdDescription()));
                arqComp.setDescripcion(catItem.getCode());
                arqComp.setSku(catItem.getIdItem());
                arqComp.setUpc(String.valueOf(componenteDTO.getUpcList().getId()));
            }else{
                arqComp.setDescripcion(componenteDTO.getPrimDescripcion());
                arqComp.setUpc(componenteDTO.getPrimUpc());
            }

            LOG.info("iterando sobre listado de upc");
			String strUpc = "";
			if (componenteDTO.getUpcList() != null) {
				GenericItemString gi = componenteDTO.getUpcList();
				for (CatItem catListItem : listCatItem) {
					if ((catListItem.getIdItem()).equals(gi.getId())) {
						strUpc = strUpc + catListItem.getIdItem();
						break;
					}
				}
			}

            LOG.info("iterando sobre listado de se?alamientos");
			String strSenalamiento = "";
			if (lSenalamientos != null) {
				for (GenericItem gi : lSenalamientos) {
					for (CatSenal ent : mCatSignals) {
						if (String.valueOf(gi.getId()).equals("0")) {
							strSenalamiento = strSenalamiento + gi.getValue() + ", ";
							break;
						}

						if (Objects.equals(ent.getIdSenal(), gi.getId())) {
							strSenalamiento = strSenalamiento + ent.getNombre() + ", ";
							break;
						}
					}
				}
			}

			if (strSenalamiento.isEmpty()) {
				arqComp.setSenalamiento(strSenalamiento);
			} else {
				arqComp.setSenalamiento(strSenalamiento.substring(0, strSenalamiento.lastIndexOf(",")));
			}

            LOG.info("cargando categorias");
			for (CatCategory item : lCatCategories) {
				if (item.getIdCategory() == componenteDTO.getIdCateg()) {
					arqComp.setCategoria(item.getCode());
					break;
				}
			}
			arqComp.setIdCategoria(componenteDTO.getIdCateg());

			String categoria = arqComp.getCategoria();
			if ((categoria == null || categoria.isEmpty()) && componenteDTO.getPrimCat() != null) {
				for (CatCategory item : lCatCategories) {
					if (Objects.equals(item.getIdCategory(), componenteDTO.getPrimCat())) {
						arqComp.setCategoria(item.getCode());
						break;
					}
				}
				arqComp.setIdCategoria(componenteDTO.getPrimCat());
			}

			if (componenteDTO.getIdSubCategoria() > 0) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID);
				attrSearch.setValue(Integer.toString(componenteDTO.getIdSubCategoria()));
				List<AttrSearch> insertSearch = new ArrayList<>();
				insertSearch.add(attrSearch);
				try {
                    LOG.info("ELEMENTO: " + arqComp.getDescripcion());
                    LOG.info("BUSCANDO SUBCATEGORIA: " + componenteDTO.getIdSubCategoria() + ", categoria: " + componenteDTO.getIdCateg());
					//CatSubCategory catsub = this.serviceCatSubCategory.getCatSubCategoryById(Integer.valueOf(componenteDTO.getIdSubCategoria()),Integer.valueOf(componenteDTO.getIdCateg()));
					CatSubCategory catsub = getSubCategoriesByCatId(componenteDTO.getIdCateg(), componenteDTO.getIdSubCategoria());
                    
                    LOG.info("SUBCATEGORIA ENCONTRADA: " + catsub.getCode());
                    arqComp.setSubCategoria(catsub.getCode());
				} catch (Exception e) {
					LOG.error(e);
				}
			}

            LOG.info("cargando precio y acuerdos");
			arqComp.setPrecio(componenteDTO.getPrimPrecio() == null ? 0 : componenteDTO.getPrimPrecio());

			arqComp.setAcuerdo1(arqMecanica.getAcuerdos() != null && arqMecanica.getAcuerdos()[0] != null ? arqMecanica.getAcuerdos()[0] : "");
			arqComp.setAcuerdo2(arqMecanica.getAcuerdos() != null && arqMecanica.getAcuerdos()[1] != null ? arqMecanica.getAcuerdos()[1] : "");
			arqComp.setAcuerdo3(arqMecanica.getAcuerdos() != null && arqMecanica.getAcuerdos()[2] != null ? arqMecanica.getAcuerdos()[2] : "");

            //seteando cantidad de producto
            arqComp.setCantProductos(componenteDTO.getCantidadProd());
            arqComp.setCantidadProducto(componenteDTO.getCantidadProd());
            
            //seteando el precio original
            LOG.info("seteando precio original a arqComp: " + componenteDTO.getPrecioVentaOriginal() );
            arqComp.setPrecioVentaOriginal(componenteDTO.getPrecioVentaOriginal());
            
			lArqComp.add(arqComp);
		}
        LOG.info("prepararComponentes:  "+(System.currentTimeMillis()-time));
		return lArqComp;
	}
    
    
    private CatSubCategory getSubCategoriesByCatId(Integer categoryId, Integer subCategoryId){
        
        CatCategory catCategory = this.serviceCatCategory.getCatCategoryById(categoryId);
        CatSubCategory subCateg = this.serviceCatSubCategory.getCatSubCategoryByCatId(subCategoryId, catCategory);
        
        LOG.info("subCateg, code: " + subCateg.getCode() + ", id: " + subCateg.getIdSubcategory());
        LOG.info("--------------------------------------------------------------------------------");
        
        CatSubCategory subCategory = null;
        LOG.info("obteniendo subcategorias de categoria: " + categoryId);
        LOG.info("subcategoria a buscar: " + subCategoryId);
        List<CatSubCategory> subCategories = serviceCatSubCategory.getCatSubCategoryByCategoryID(categoryId);
        LOG.info("total de subCategorias: " + subCategories.size());
        for(CatSubCategory subCat: subCategories){
            LOG.info("elemento en iteracion: " + subCat.getIdSubcategory() + subCat.getCode());
            if(subCat.getIdSubcategory().intValue() == subCategoryId.intValue()){
                LOG.info("elemento encontrado, id category:  " + subCat.getCatCategory().getIdCategory() );
                subCategory = subCat;
            }
        }
        return subCategory;
    }

	@SuppressWarnings("unchecked")
	public void valueChangeSenalamientos(ValueChangeEvent e) {
             mostrarOtroSen = e.getNewValue() != null && ((List<String>) e.getNewValue()).contains("0");
             
             arqMecanica.setIdMecanica(arqMecanica.getIdMecanica());
             arqMecanica.setIdPromocion(arqMecanica.getIdPromocion());
             arqMecanica.setIdTipoPromocion(arqMecanica.getIdTipoPromocion());

	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeGrupoZona1(ValueChangeEvent e) {
        LOG.info("ejecutando valueChangeGrupoZona1");
		if (e.getNewValue() != null) {
			List<String> lgz = (List<String>) e.getNewValue();
			List<AttrSearch> insertSearch = new ArrayList<>();
			for (String gz : lgz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_GRUPO);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			if (!lgz.isEmpty()) {
				mZonas = MBUtil.cargarcombos(Constants.CAT_ZONA, insertSearch,
						serviceDynamicCatalogs, Constants.ATT_DESCRIPTION,
						Constants.ATT_ID);
                                mGruposZonasLabel = lgz.size() + " Seleccionado";
                                mZonesLabel = mZonas.size() > 0 ? mZonas.size() + " Seleccionado" : "Zonas";
			} else {
				mZonas = new HashMap<>();
			}
			mTiendas = new HashMap<>();
		}
	}

        // ******************* Labels Combo **************************** //
        private String mGruposZonasLabel = "Grupo Zonas";
        private String mZonesLabel = "Zonas";
        private String mSKUsLabel = "SKUs";
        private String mUPCsLabel = "UPCs";
        private String mSKUsLabelByProv = "SKUs";
        private String mUPCsLabelByProv = "UPCs";
        private String mSKUsLabelByFamilia = "SKUs";
        private String mUPCsLabelByFamilia = "UPCs";

    public String getmGruposZonasLabel() {
        return mGruposZonasLabel;
    }

    public void setmGruposZonasLabel(String mGruposZonasLabel) {
        this.mGruposZonasLabel = mGruposZonasLabel;
    }

    public String getmZonesLabel() {
        return mZonesLabel;
    }

    public void setmZonesLabel(String mZonesLabel) {
        this.mZonesLabel = mZonesLabel;
    }
        
        
        
	public String getmSKUsLabel() {
		return mSKUsLabel;
	}

	public void setmSKUsLabel(String mSKUsLabel) {
		this.mSKUsLabel = mSKUsLabel;
	}

	public String getmUPCsLabel() {
		return mUPCsLabel;
	}

	public void setmUPCsLabel(String mUPCsLabel) {
		this.mUPCsLabel = mUPCsLabel;
	}
	
	public String getmSKUsLabelByProv() {
		return mSKUsLabelByProv;
	}

	public void setmSKUsLabelByProv(String mSKUsLabelByProv) {
		this.mSKUsLabelByProv = mSKUsLabelByProv;
	}

	public String getmUPCsLabelByProv() {
		return mUPCsLabelByProv;
	}

	public void setmUPCsLabelByProv(String mUPCsLabelByProv) {
		this.mUPCsLabelByProv = mUPCsLabelByProv;
	}

	public String getmSKUsLabelByFamilia() {
		return mSKUsLabelByFamilia;
	}

	public void setmSKUsLabelByFamilia(String mSKUsLabelByFamilia) {
		this.mSKUsLabelByFamilia = mSKUsLabelByFamilia;
	}

	public String getmUPCsLabelByFamilia() {
		return mUPCsLabelByFamilia;
	}

	public void setmUPCsLabelByFamilia(String mUPCsLabelByFamilia) {
		this.mUPCsLabelByFamilia = mUPCsLabelByFamilia;
	}

	@SuppressWarnings("unchecked")
	public void valueChangeGrupoZona(ValueChangeEvent e) {
        LOG.info("EJECUTANDO: valueChangeGrupoZona");
			changeGrupoZonas((List<String>) e.getNewValue());
			RequestContext requestContext = RequestContext.getCurrentInstance();  
    		requestContext.execute("loader.hide()");
	}

	private void changeGrupoZonas(List<String> lz) {
        List<CatZone> catZoneList;
		mStores = new ArrayList<>();
		List<CatZone> zonas = new ArrayList<>();
		if (lz != null) {
			List<Integer> ids = new ArrayList<>();
			for (String gz : lz) {
				ids.add(Integer.valueOf(gz));
			}
			catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
			if (catZoneList != null) {
				zonas.addAll(catZoneList);
			}
		}
        
        Map<Integer, CatZone> zones = new HashMap<Integer, CatZone>();
        for(CatZone s : zonas){
            if(zones.containsKey(Integer.parseInt(String.valueOf(s.getIdZone())))){
                continue;
            }else{
                zones.put(Integer.parseInt(String.valueOf(s.getIdZone())), s);
            }
        }
        List<CatZone> l = new ArrayList<CatZone>();
        for (Map.Entry<Integer, CatZone> entry : zones.entrySet()){
            l.add(entry.getValue());
        }
        zonas = l;
        
		Collections.sort(zonas, new CatZoneSorter());
		mZones = zonas;
                
                mGruposZonasLabel = lz.size() + " Seleccionado";
//		getCurrentProgramaDTO().setGrupoZonas(lz);
	}

	public void valueChangeGrupoZonaImpl(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			changeGrupoZonasImpl(String.valueOf(e.getNewValue()));
		}
	}

	public void valueChangeZonaImpl(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			changeZonasImpl((Integer) e.getNewValue());
		}
	}

	private void changeZonasImpl(Integer impZonaId) {

		if (impZonaId != null) {

			List<TblMecanica> lTblMecanica = serviceArquitecturaSeven
					.getAllMecanica(impPeriodoId, impProgranaId,
							impGrupoZonaId, impZonaId);
			if (lTblMecanica != null) {
				lMecanicaImp = new ArrayList<>();
				for (TblMecanica tblMecanica : lTblMecanica) {
					lMecanicaImp.add(new SelectItem(
							tblMecanica.getMecanicaId(), tblMecanica
									.getNombreMecanica()));
				}
			}
		}
	}

	private void changeGrupoZonasImpl(String impGrupoZonaId) {
		 lZoneImp = new ArrayList<>();

		if (impGrupoZonaId != null) {
			List<Integer> ids = new ArrayList<>();
			ids.add(Integer.valueOf(impGrupoZonaId));
            List<CatZone> catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
			if (catZoneList != null) {
				lZoneImp.addAll(catZoneList);
				impZonaId = 0;
			} else {
				lZoneImp = new ArrayList<>();
			}
			List<TblMecanica> lTblMecanica = serviceArquitecturaSeven.getAllMecanica(impPeriodoId, impProgranaId, Integer.valueOf(impGrupoZonaId), impZonaId);
			if (lTblMecanica != null) {
				lMecanicaImp = new ArrayList<>();
				for (TblMecanica tblMecanica : lTblMecanica) {
					lMecanicaImp.add(new SelectItem(tblMecanica.getMecanicaId(), tblMecanica.getNombreMecanica()));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void valueChangeZona(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			changeZonas((List<String>) e.getNewValue());
			RequestContext requestContext = RequestContext.getCurrentInstance();  
    		requestContext.execute("loader.hide()");
		}
//		else {
//			changeZonas(Collections.<String>emptyList());
//		}
	}

	private void changeZonas(List<String> listaDeZonas) {
            mStores = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (listaDeZonas != null) {
                for (String zona : listaDeZonas) {
                	ids.add(Integer.valueOf(zona));
                }
            }
            List<CatStore> catStoreList = serviceCatStore.getCatStoreListByZone(ids);
            if (catStoreList!= null && !catStoreList.isEmpty()) {
            	mStores.addAll(catStoreList);
            }
            
            Map<Integer, CatStore> stores = new HashMap<>();
            for(CatStore e : mStores){
                if(stores.containsKey(e.getIdStore())){
                    continue;
                }else{
                    stores.put(Integer.parseInt(String.valueOf(e.getIdStore())), e);
                }
            }
            
            List<CatStore> l = new ArrayList<>();
            for (Map.Entry<Integer, CatStore> entry : stores.entrySet()){
                l.add(entry.getValue());
            }
            
            mStores = l;
            mZonesLabel = listaDeZonas.size() + " Seleccionado";
            Collections.sort(mStores, new CatStoreSorter());
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeZona1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lz = (List<String>) e.getNewValue();
			List<AttrSearch> insertSearch = new ArrayList<>();
			for (String gz : lz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_ZONA);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			if (!lz.isEmpty()) {
				mTiendas = MBUtil.cargarcombos(Constants.CAT_STORE,
						insertSearch, serviceDynamicCatalogs,
						Constants.ATT_CODE, Constants.ATT_ID);
			} else {
				mTiendas = new HashMap<>();
			}
		}
	}

	@Deprecated
	public void valueChangeCategoria1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			Integer cat = (Integer) e.getNewValue();
			lSubCategorias = new ArrayList<SelectItem>();
			AttrSearch attrSearch = new AttrSearch();
			attrSearch.setAttrName(Constants.ATT_ID_CATEGORY);
			attrSearch.setValue(cat.toString());
			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			insertSearch.add(attrSearch);
			try {
				lSubCategorias = MBUtil.getSelectItemsByCatalog(
						serviceDynamicCatalogs, Constants.CAT_SCATEGORY,
						insertSearch);
			} catch (Exception exc) {
				LOG.error(exc);
			}
		}
	}

	public void valueChangeCategoria(ValueChangeEvent e) {
        listCatItem = new ArrayList<>();
        arqComponente.setIdProveedor(0);
        arqComponente.setIdLista(0);
        
        comboSKU = new ArrayList<>();
        comboUPC = new ArrayList<>();
        comboSKUByProv = new ArrayList<>();
        comboSKUByFamilia = new ArrayList<>();
        
        Integer cat = 0;
        if (e.getNewValue() != null) {
            cat = (Integer) e.getNewValue();
            filtrarPorCategoria(cat);
        }else {
            lCatSubCategories = new ArrayList<>();
        }
        
         
	}
    
    private void filtrarPorCategoria(int idCategoria){
        try {
            // lCatProveedor = MBUtil.cargarcomboCatProveedor(serviceCatProveedor, cat);
            lCatSubCategories = MBUtil.cargarcomboSubCategoriasByIdCategory(serviceCatSubCategory, idCategoria);
            
            listCatItem = new ArrayList<>();
            comboSKU = new ArrayList<>();
            comboUPC = new ArrayList<>();
            comboSKUByFamilia = new ArrayList<>();
            //cargar todas las descripciones de todas las subcategorias
            List<CatItem> allItems = new ArrayList<>();
            if(lCatSubCategories.size() > 0){
                for(CatSubCategory subCat : lCatSubCategories){
                    allItems.addAll(serviceCatItem.getCatItemListBySubCatID(subCat.getIdSubcategory(),idCategoria));
                }
            }
            //cargar todos los skus
            Map<String, CatItem> im = new HashMap<>();
            for(CatItem s : allItems){
                if(!im.containsKey(s.getIdItem())){
                    im.put(s.getIdItem(), s);
                    //Cambio para poder agregar descripcion a al combo de sku
                    CatListDet catItem = new CatListDet();
                    catItem.setId(s.getIdItem());
                    catItem.setCode(s.getCode());
                    comboSKU.add(catItem);
                }
            }
            
            allItems = new ArrayList<>();
            for (Map.Entry<String, CatItem> mEntry : im.entrySet()) {
                allItems.add(mEntry.getValue());
            }

            listCatItem = allItems;

            itemIdList = new ArrayList<>();
            comboUPC = new ArrayList<>();
            for(CatItem i : listCatItem){
                itemIdList.add(i.getIdItem());
            }
            getUpcBySkuList(itemIdList, true);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("loader.hide()");
         } catch (Exception e1) {
             e1.printStackTrace();
         }
    }
    
    public void skuChange(ValueChangeEvent e){
        LOG.info("Ejecutando cambio de skus");
        List<String> lskus = (List<String>) e.getNewValue();
        getUpcBySkuList(lskus, false);
        mSKUsLabel = lskus.size() + " Seleccionado";
    }
    
    private HashMap<String, List<CatListDet>> skuRegistry;
    
    public void cmbUpcChange(ValueChangeEvent e){
        LOG.info("Ejecutando cambio UPC");
        List<String> lupc = (List<String>) e.getNewValue();
        arqComponente.lSku = new ArrayList<>();
        for (Map.Entry<String, List<CatListDet>> pair : skuRegistry.entrySet()){
            for (CatListDet x : pair.getValue()){
                if (lupc.contains(x.getId())){
                    arqComponente.lSku.add(pair.getKey());
                }
            }
        }
        mUPCsLabel = lupc.size() + " Seleccionado";
    }
    
    private void getUpcBySkuList(List<String> lskus, boolean clearUpcSelected){
        List<CatListDet> upcs = new ArrayList<>();
        skuRegistry = new HashMap<>();
         for(String sku : lskus){
            List<CatListDet> upcsList = MBUtil.cargarComboUPCByItemID(serviceCatListDet, sku);
            skuRegistry.put(sku, upcsList);
            if(upcsList != null){
                upcs.addAll(upcsList);
            
                if(upcsList.size() == 1){
                
                    if(lUpcComp == null){
                        lUpcComp = new ArrayList<>();
                    }
                    lUpcComp.add(upcsList.get(0).getId());
                }
            }
        }
        depurateList(lUpcComp);
        if (clearUpcSelected){
            lUpcComp = new ArrayList<>();
            mUPCsLabel = "UPCs";
        }
        comboUPC = upcs;
        if(!lUpcComp.isEmpty())
        	mUPCsLabel = comboUPC.size() + " Seleccionado";
    }

    private void depurateList(List<String> l){
        Set<String> hs = new HashSet<>();
        hs.addAll(l);
        l.clear();
        l.addAll(hs);
    }
    
	public void valueChangeProveedor(ValueChangeEvent e) {
		arqComponente.setIdCategoria(0);
        arqComponente.setIdSubCategoria(0);
        arqComponente.setIdLista(0);
        lCatSubCategories = Collections.emptyList();
        mSKUsLabel = "SKUs";
        mUPCsLabel = "UPCs";
        comboSKU = new ArrayList<>();
        comboUPC = new ArrayList<>();
        comboSKUByFamilia = new ArrayList<>();
        
        if (e.getNewValue() != null) {
			Integer idProveedor = (Integer) e.getNewValue();
			//lCatSubCategories = MBUtil.cargarComboSubCategoriasByIdProveedor(serviceCatSubCategory, idProveedor);
            List<Integer> stores = new ArrayList<>();
            for (String s : arqMecanica.lTiendas){
                stores.add(Integer.valueOf(s));
            }
            listCatItem = serviceCatItem.getCatItemByProveedor(idProveedor, stores);
            comboSKUByProv = new ArrayList<>();
          	 //cargar todos los skus
            Map<String, CatItem> im = new HashMap<>();
            for(CatItem s : listCatItem){
                if(!im.containsKey(s.getIdItem())){
                    im.put(s.getIdItem(), s);
                    //Cambio para poder agregar descripcion a al combo de sku
                    CatListDet catItem = new CatListDet();
                    catItem.setId(s.getIdItem());
                    catItem.setCode(s.getCode());
                    comboSKUByProv.add(catItem);
                }
            }
		}
		else {
            listCatItem = new ArrayList<>();
		}
	}





	@Deprecated
	public void valueChangeGrupoZonaImpl1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			impGrupoZonaId = (Integer) e.getNewValue();
			AttrSearch attrSearch = new AttrSearch();
			attrSearch.setAttrName(Constants.ATT_ID_GRUPO);
			attrSearch.setValue(Integer.valueOf(impGrupoZonaId).toString());
			List<AttrSearch> insertSearch = new ArrayList<>();
			insertSearch.add(attrSearch);
			try {
				lZonasImp = MBUtil.getSelectItemsByCatalog(serviceDynamicCatalogs, Constants.CAT_ZONA,insertSearch);
				impZonaId = 0;
			} catch (Exception exc) {
				LOG.error(exc);
			}

			List<TblMecanica> lTblMecanica = serviceArquitecturaSeven
					.getAllMecanica(impPeriodoId, impProgranaId,
							impGrupoZonaId, impZonaId);
			if (lTblMecanica != null) {
				lMecanicaImp = new ArrayList<SelectItem>();
				for (TblMecanica tblMecanica : lTblMecanica) {
					lMecanicaImp.add(new SelectItem(
							tblMecanica.getMecanicaId(), tblMecanica
									.getNombreMecanica()));
				}
			}
		}
	}

	@Deprecated
	public void valueChangeZonaImpl1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			impZonaId = (Integer) e.getNewValue();
			List<TblMecanica> lTblMecanica = serviceArquitecturaSeven
					.getAllMecanica(impPeriodoId, impProgranaId,
							impGrupoZonaId, impZonaId);
			if (lTblMecanica != null) {
				lMecanicaImp = new ArrayList<SelectItem>();
				for (TblMecanica tblMecanica : lTblMecanica) {
					lMecanicaImp.add(new SelectItem(
							tblMecanica.getMecanicaId(), tblMecanica
									.getNombreMecanica()));
				}
			}
		}
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeGrupoZonaEstr1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lgz = (List<String>) e.getNewValue();
			mZonasEstra = new HashMap<String, String>();
			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			for (String gz : lgz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_GRUPO);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			if (!lgz.isEmpty()) {
				mZonasEstra = MBUtil.cargarcombos(Constants.CAT_ZONA,
						insertSearch, serviceDynamicCatalogs,
						Constants.ATT_DESCRIPTION, Constants.ATT_ID);
			}
		}
		if (lZonasEstra != null) {
			lZonasEstra.clear();
		}
	}

	@SuppressWarnings("unchecked")
	public void valueChangeGrupoZonaEstr(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			changeGrupoZonasEstr((List<String>) e.getNewValue());
		}
	}

	private void changeGrupoZonasEstr(List<String> lz) {
		List<CatZone> catZoneList;
		mCatZonasEstra = new ArrayList<>();
		if (lz != null) {
			List<Integer> ids = new ArrayList<>();
			for (String gz : lz) {
				ids.add(Integer.valueOf(gz));
			}
			catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
			if (catZoneList != null) {
				mCatZonasEstra.addAll(catZoneList);
			} else {
				mCatZonasEstra = new ArrayList<>();
			}
		}
		if (lZonasEstra != null) {
			lZonasEstra.clear();
		}
	}

	@SuppressWarnings("unchecked")
	public void valueChangeGrupoZonaPrec(ValueChangeEvent e) {
        LOG.info("ejecutando valueChangeGrupoZonaPrec");
        
		List<CatZone> catZoneList = new ArrayList<>();
		if (e.getNewValue() != null) {
			List<String> lgz = (List<String>) e.getNewValue();
			mCatZonasPrec = new ArrayList<>();

			try {
				catZoneList = this.getServiceCatZone().getCatZoneList();
				if (lgz.size() > 0) {
					for (String idGZone : lgz) {
						for (CatZone z : catZoneList) {
							if (z.getCatGZone().getIdGrupoZona() == Integer.valueOf(idGZone)) {
								mCatZonasPrec.add(z);
							}
						}
					}
				}

			} catch (Exception e1) {
				LOG.error(e);
			}

			List<String> zonaXGrupo = new ArrayList<>();
			for (CatZone catZone : mCatZonasPrec) {
				zonaXGrupo.add(String.valueOf(catZone.getIdZone()));
			}
			lZonaXGrupo = zonaXGrupo;

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}

			mCatTiendasPrec = new ArrayList<>();
            /*
            LOG.info("Llamando a obtenerPreciosPromocion desde: valueChangeGrupoZonaPrec");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
					lZonaXGrupo);
            */
		}
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeGrupoZonaPrec1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lgz = (List<String>) e.getNewValue();
			mZonasPrec = new HashMap<String, String>();
			for (String gz : lgz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_GRUPO);
				attrSearch.setValue(gz);
				List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
				insertSearch.add(attrSearch);
				Map<String, String> mZonasAux = MBUtil.cargarcombos(
						Constants.CAT_ZONA, insertSearch,
						serviceDynamicCatalogs, Constants.ATT_DESCRIPTION,
						Constants.ATT_ID);
				mZonasPrec.putAll(mZonasAux);
			}
			lZonaXGrupo = new ArrayList<String>();
			for (Map.Entry<String, String> mEntry : mZonasPrec.entrySet()) {
				lZonaXGrupo.add(mEntry.getValue());
			}

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}

			mTiendasPrec = new HashMap<String, String>();
            LOG.info("Llamando a obtenerPreciosPromocion desde: valueChangeGrupoZonaPrec1");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
					lZonaXGrupo);
		}
	}

	@SuppressWarnings("unchecked")
	public void valueChangeZonaPrec(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lz = (List<String>) e.getNewValue();
			mCatTiendasPrec = new ArrayList<CatStore>();

			List<CatStore> catStoreList = new ArrayList<CatStore>();
			if (lz != null) {
				for (String z : lz) {
					catStoreList = MBUtil.cargarcomboStores(serviceCatZone, z);
					if (catStoreList != null) {
						mCatTiendasPrec.addAll(catStoreList);
					} else {
						mCatTiendasPrec = new ArrayList<CatStore>();
					}
				}
			}

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}

			if (lz.isEmpty()) {
				lz.addAll(lZonaXGrupo);
			}
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeZonaPrec");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre, lz);
		}
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeZonaPrec1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lz = (List<String>) e.getNewValue();
			mTiendasPrec = new HashMap<String, String>();
			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			for (String gz : lz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_ZONA);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			
			//TODO
			Map<String, String> mZonasAux = MBUtil.cargarcombos(
					Constants.CAT_STORE, insertSearch, serviceDynamicCatalogs,
					Constants.ATT_CODE, Constants.ATT_ID);
			mTiendasPrec.putAll(mZonasAux);

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}

			if (lz.isEmpty()) {
				lz.addAll(lZonaXGrupo);
			}
            
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeZonaPrec1");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre, lz);
		}
	}

	@SuppressWarnings("unchecked")
	public void valueChangeSKUPrec(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lSKU = (List<String>) e.getNewValue();

			List<String> lSkuPre = null;
			if (lSKU != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSKU) {
					lSkuPre.add(sku);
				}

			}

			List<String> lZonasAux = new ArrayList<String>();
			if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
					&& (lZonasPrec == null || lZonasPrec.isEmpty())) {
				lZonasAux.addAll(lZonaXGrupo);
			} else {
				if (lZonasPrec != null) {
					lZonasAux.addAll(lZonasPrec);
				}
			}

			if (lZonasAux == null || lZonasAux.isEmpty()) {
				if (lZonasAux == null) {
					lZonasAux = new ArrayList<String>();
				}

				lZonasAux.addAll(arqMecanica.getlZonas());
			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeSKUPrec");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
					lZonasAux);
		}
	}

	@SuppressWarnings("unchecked")
	public void valueChangeUPCPrec(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lUPC = (List<String>) e.getNewValue();

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUPC != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUPC) {
					lUpcPre.add(upc);
				}

			}

			List<String> lZonasAux = new ArrayList<String>();
			if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
					&& (lZonasPrec == null || lZonasPrec.isEmpty())) {
				lZonasAux.addAll(lZonaXGrupo);
			} else {
				if (lZonasPrec != null) {
					lZonasAux.addAll(lZonasPrec);
				}
			}

			if (lZonasAux == null || lZonasAux.isEmpty()) {
				if (lZonasAux == null) {
					lZonasAux = new ArrayList<String>();
				}

				lZonasAux.addAll(arqMecanica.getlZonas());
			}
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeUPCPrec");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
					lZonasAux);
		}
	}

	public void valueChangeComponente(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			Integer comp = (Integer) e.getNewValue();
			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}

			List<String> lZonasAux = new ArrayList<>();
			if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
					&& (lZonasPrec == null || lZonasPrec.isEmpty())) {
				lZonasAux.addAll(lZonaXGrupo);
			} else {
				if (lZonasPrec != null) {
					lZonasAux.addAll(lZonasPrec);
				}
			}

			if (lZonasAux.isEmpty()) {
                            lZonasAux.addAll(arqMecanica.getlZonas());
			}
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeComponente");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, comp, idListaPrec, lSkuPre, lUpcPre, lZonasAux);
		}
	}
	public void valueChangeComponenteArt(ValueChangeEvent e) {
		//por cada componente
//		double distribucionRebaja = 0D;
//        for(ArqComponente c: lArqCompPrecProm){
//        	System.out.println(c.getNumeroComponente());
//        	System.out.println(c.getDistribucionRebajaPorc());
//        	if(c.getDistribucionRebajaPorc() == 0) continue;
//        	else if (c.getDistribucionRebajaPorc() > 0){
//        		distribucionRebaja = c.getDistribucionRebajaPorc();
//        	}
//        }
//        
//    	int i = 1;
//    	for (SelectItem componente : lComponentesPrecioPromocion){
//    		System.out.println(componente.getValue());
//    		if(i == lComponentesPrecioPromocion.size()){
//    			this.porcentajeDistribucionRebaja = 99D;
//    		} else if(i == lComponentesPrecioPromocion.size()){
//    			this.porcentajeDistribucionRebaja = distribucionRebaja - 100D;
//    		}
//    		i++;
//    	}
	}
	

    public void valueChangeDescripcion(ValueChangeEvent e) {
        if (e.getNewValue() != null) {
            Integer desc = (Integer) e.getNewValue();
            itemIDSelected = String.valueOf(desc);
            comboUPC = MBUtil.cargarComboUPCByItemID(serviceCatListDet, itemIDSelected);
            List<String> lSkuPre = null;
            if (lSkuPrec != null) {
                    lSkuPre = new ArrayList<>();
                    for (String sku : lSkuPrec) {
                            lSkuPre.add(sku);
                    }

            }

            List<String> lZonasAux = new ArrayList<>();
            if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
                            && (lZonasPrec == null || lZonasPrec.isEmpty())) {
                    lZonasAux.addAll(lZonaXGrupo);
            } else {
                    if (lZonasPrec != null) {
                            lZonasAux.addAll(lZonasPrec);
                    }
            }

            if (lZonasAux.isEmpty()) {
                lZonasAux.addAll(arqMecanica.getlZonas());
            }
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeDescripcion");
            obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
                            desc, idCompPrec, idListaPrec, lSkuPre, null, lZonasAux);

            AttrSearch attrSearch = new AttrSearch();
            List<AttrSearch> insertSearch = new ArrayList<>();
            if (idListaPrec > 0) {
                    attrSearch.setAttrName(Constants.ATT_ID_LISTA);
                    attrSearch.setValue(String.valueOf(idListaPrec));
                    insertSearch.add(attrSearch);
            }

            if (desc.intValue() > 0) {
                    attrSearch = new AttrSearch();
                    attrSearch.setAttrName(Constants.ATT_ID_DESCRIPCION);
                    attrSearch.setValue(desc.toString());
                    insertSearch.add(attrSearch);
            }
            try {
                    List<GenericItem> lGi = new ArrayList<>();
                    if (insertSearch.size() > 0) {

                            //TODO
                            lGi = MBUtil.genericSearch(Constants.ATT_CODE,
                                            Constants.ATT_MARCA, Constants.CAT_ITEM,
                                            serviceDynamicCatalogs, insertSearch);
                    } else {
                            //TODO
                            lGi = MBUtil.genericSearch(Constants.ATT_CODE,
                                            Constants.ATT_MARCA, Constants.CAT_ITEM,
                                            serviceDynamicCatalogs);
                    }
                    mUpcPrec = new HashMap<>();
                    if (lGi != null) {
                            for (GenericItem gi : lGi) {
                                    mUpcPrec.put(String.valueOf(gi.getId()),
                                                    String.valueOf(gi.getId()));
                            }
                    }
                    lUpcPrec = new ArrayList<>();
            } catch (Exception ex) {
                    LOG.error(ex);
            }
        }
    }

	public void valueChangeListaPrec(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			Integer lista = (Integer) e.getNewValue();

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lZonasAux = new ArrayList<>();
			if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
					&& (lZonasPrec == null || lZonasPrec.isEmpty())) {
				lZonasAux.addAll(lZonaXGrupo);
			} else {
				if (lZonasPrec != null) {
					lZonasAux.addAll(lZonasPrec);
				}
			}

			if (lZonasAux == null || lZonasAux.isEmpty()) {
				if (lZonasAux == null) {
					lZonasAux = new ArrayList<>();
				}

				lZonasAux.addAll(arqMecanica.getlZonas());
			}
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeListaPrec");
			obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec,
					idDescPrec, idCompPrec, lista, lSkuPre, null, lZonasAux);

			AttrSearch attrSearch = new AttrSearch();
			List<AttrSearch> insertSearch = new ArrayList<>();
			if (lista.intValue() > 0) {
				attrSearch.setAttrName(Constants.ATT_ID_LISTA);
				attrSearch.setValue(lista.toString());
				insertSearch.add(attrSearch);
			}

			if (idDescPrec > 0) {
				attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_DESCRIPCION);
				attrSearch.setValue(String.valueOf(idDescPrec));
				insertSearch.add(attrSearch);
			}
			try {
				List<GenericItem> lGi = new ArrayList<>();
				if (insertSearch.size() > 0) {
					//TODO
					lGi = MBUtil.genericSearch(Constants.ATT_CODE,
							Constants.ATT_MARCA, Constants.CAT_ITEM,
							serviceDynamicCatalogs, insertSearch);
				} else {
					lGi = MBUtil.genericSearch(Constants.ATT_CODE,
							Constants.ATT_MARCA, Constants.CAT_ITEM,
							serviceDynamicCatalogs);
				}
				mUpcPrec = new HashMap<String, String>();
				if (lGi != null) {
					for (GenericItem gi : lGi) {
						mUpcPrec.put(String.valueOf(gi.getId()),
								String.valueOf(gi.getId()));
					}
				}
				lUpcPrec = new ArrayList<String>();
			} catch (Exception ex) {
				LOG.error(ex);
			}
		}
	}

	public void valueChangeCategoriaPrec(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			Integer cat = (Integer) e.getNewValue();

			/*
			 * lSubCategoriasPrec = new ArrayList<SelectItem>(); AttrSearch
			 * attrSearch = new AttrSearch();
			 * attrSearch.setAttrName(Constants.ATT_ID_CATEGORY);
			 * attrSearch.setValue(cat.toString()); List<AttrSearch>
			 * insertSearch = new ArrayList<AttrSearch>();
			 * insertSearch.add(attrSearch); try { lSubCategoriasPrec =
			 * MBUtil.getSelectItemsByCatalog( serviceDynamicCatalogs,
			 * Constants.CAT_SCATEGORY, insertSearch); } catch (Exception exc) {
			 * LOG.error(exc); }
			 */

			try {
				lCatSubCategoriasPrec = MBUtil.cargarcomboSubCategoriasByIdCategory(serviceCatSubCategory, cat);
			} catch (Exception exc) {
				LOG.error(exc);
			}

			List<String> lSkuPre = null;
			if (lSkuPrec != null) {
				lSkuPre = new ArrayList<String>();
				for (String sku : lSkuPrec) {
					lSkuPre.add(sku);
				}

			}

			List<String> lUpcPre = null;
			if (lUpcPrec != null) {
				lUpcPre = new ArrayList<String>();
				for (String upc : lUpcPrec) {
					lUpcPre.add(upc);
				}

			}

			List<String> lZonasAux = new ArrayList<String>();
			if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() && (lZonasPrec == null || lZonasPrec.isEmpty())) {
				lZonasAux.addAll(lZonaXGrupo);
			} else {
				if (lZonasPrec != null) {
					lZonasAux.addAll(lZonasPrec);
				}
			}

			if (lZonasAux == null || lZonasAux.isEmpty()) {
				if (lZonasAux == null) {
					lZonasAux = new ArrayList<String>();
				}

				lZonasAux.addAll(arqMecanica.getlZonas());
			}

			idSubCatPrec = 0;
            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeCategoriaPrec");
			obtenerPreciosPromocion(idMecanicaPrec, cat, idSubCatPrec,
					idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
					lZonasAux);
		}
	}

    public void valueChangeSubCategoriaArq(ValueChangeEvent e){
        int idCat = arqComponente.getIdCategoria();
        int idSub = (int) e.getNewValue();
        
        //si selecciono una subcategoria
        if(idSub != 0){
            filtarPorSubCategoria(idCat, idSub);
        }else{
            this.idDescripcionCombo = "0";
            if (this.arqComponente.getlSku()!=null)
            	this.arqComponente.getlSku().clear();
            this.lUpcComp.clear();
            filtrarPorCategoria(idCat);
            
        }
        
        RequestContext requestContext = RequestContext.getCurrentInstance();  
		requestContext.execute("loader.hide()");
    }
    
    private void filtarPorSubCategoria(int idCategoria, int idSubCategoria){
        listCatItem = serviceCatItem.getCatItemListBySubCatID(idSubCategoria,idCategoria);
        if(itemIdList == null){
            itemIdList = new ArrayList<String>();
        }
        comboSKU = new ArrayList<>();
        itemIdList.clear();
        for(CatItem ci : listCatItem){
            itemIdList.add(ci.getIdItem());
        }
        
        //cargar todos los skus
      Map<String, CatItem> im = new HashMap<>();
      for(CatItem s : listCatItem){
          if(!im.containsKey(s.getIdItem())){
              im.put(s.getIdItem(), s);
              //Cambio para poder agregar descripcion a al combo de sku
              CatListDet catItem = new CatListDet();
              catItem.setId(s.getIdItem());
              catItem.setCode(s.getCode());
              comboSKU.add(catItem);
          }
      }
      
//      allItems = new ArrayList<>();
//      for (Map.Entry<String, CatItem> mEntry : im.entrySet()) {
//          allItems.add(mEntry.getValue());
//      }
//
//      listCatItem = allItems;

      itemIdList = new ArrayList<>();
      comboUPC = new ArrayList<>();
      for(CatItem i : listCatItem){
          itemIdList.add(i.getIdItem());
      } 
      getUpcBySkuList(itemIdList, true);
        
    }

	public void valueChangeSubCategoria(ValueChangeEvent e) {
            Object newValue = e.getNewValue();
            if (newValue != null) {
                    Integer subCat = (Integer) newValue;

                    List<String> lSkuPre = null;
                    if (lSkuPrec != null) {
                            lSkuPre = new ArrayList<>();
                            for (String sku : lSkuPrec) {
                                    lSkuPre.add(sku);
                            }
                    }

                    List<String> lUpcPre = null;
                    if (lUpcPrec != null) {
                            lUpcPre = new ArrayList<>();
                            for (String upc : lUpcPrec) {
                                    lUpcPre.add(upc);
                            }
                    }

                    List<String> lZonasAux = new ArrayList<>();
                    if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
                                    && (lZonasPrec == null || lZonasPrec.isEmpty())) {
                            lZonasAux.addAll(lZonaXGrupo);
                    } else {
                            if (lZonasPrec != null) {
                                    lZonasAux.addAll(lZonasPrec);
                            }
                    }

                    if (lZonasAux == null || lZonasAux.isEmpty()) {
                            if (lZonasAux == null) {
                                    lZonasAux = new ArrayList<String>();
                            }

                            lZonasAux.addAll(arqMecanica.getlZonas());
                    }
                    
                    
                    /* Se obtiene la lista de descripciones dependiendo la subcategoria, esta informacion
                       se toma de la tabla CAT_ITEM
                    */
                 /*   int idCategory = arqComponente.getIdCategoria();
                    listCatItemDesc = serviceCatItem.getCatItemListBySubCatID(subCat,idCategory); */
                    List<CatItem> temp = serviceCatItem.getCatItemListBySubCatID(subCat, idCatPrec);
                    listCatItemDesc = new ArrayList<>();
                    for (CatItem ci : temp){
                        for (ArqComponente qc : lArqCompPrecProm){
                            if (ci.getCode().equals(qc.getDescripcion())){
                                listCatItemDesc.add(ci);
                                break;
                            }
                        }
                    }

            LOG.info("llamando a obtenerPreciosPromocion desde: valueChangeSubCategoria");
                    obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, subCat,
                                    idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre,
                                    lZonasAux);
            }
	}

    private List<CatCategory> lCategoriasSelected;

	public void setCategoriasSelected (){
        lCategoriasSelected = new ArrayList<>();
        if (arqPeriodo != null && arqPeriodo.getlCategorias() != null){
            for (String s : arqPeriodo.getlCategorias()){
                for (CatCategory cc : mCategories){
                    if (cc.getIdCategory() == Integer.valueOf(s) && !lCategoriasSelected.contains(cc)){
                        lCategoriasSelected.add(cc);
                    }
                }
            }
        }
    }

    public List<CatCategory> getlCategoriasSelected() {
        if(lCategoriasSelected != null && lCategoriasSelected.size() > 0){
            Collections.sort(lCategoriasSelected, new Comparator<CatCategory>() {
                @Override
                public int compare(CatCategory o1, CatCategory o2) {
                    //return (o1.getCode() > o2.getId()) ? 1 : (o1.getId() == o2.getId()) ? 0 : -1;
                     return o1.getCode().toUpperCase().compareTo(o2.getCode().toUpperCase());
                }
            });
        }
        return lCategoriasSelected;
    }

    public void setlCategoriasSelected(List<CatCategory> lCategoriasSelected) {
        this.lCategoriasSelected = lCategoriasSelected;
    }

    public List<String> skuFiltroLista;

    public List<String> getSkuFiltroLista() {
        return skuFiltroLista;
    }

    public void setSkuFiltroLista(List<String> skuFiltroLista) {
        this.skuFiltroLista = skuFiltroLista;
    }

    public void valueChangeLista(ValueChangeEvent e){
        Integer idLista = (Integer) e.getNewValue();
        List<RelItemLista> listaList = serviceRelItemLista.getRelItemListaByListaID(idLista);
        listCatItem = new ArrayList<>();
        skuFiltroLista = new ArrayList<>();
        arqComponente.setlSku(new ArrayList<String>());
        arqComponente.setIdProveedor(0);
        arqComponente.setIdCategoria(0);
        arqComponente.setIdSubCategoria(0);
        comboUPC = new ArrayList<>();
        lUpcComp = new ArrayList<>();
        lCatSubCategories = new ArrayList<>();
        arqComponente.setIdSubCategoria(0);
        comboSKU = new ArrayList<>();
        comboSKUByProv = new ArrayList<>();
        comboSKUByFamilia = new ArrayList<>();
        mSKUsLabel = "SKUs";
        mUPCsLabel = "UPCs";
        for (RelItemLista r : listaList){
            listCatItem.add(r.getCatItem());
            skuFiltroLista.add(r.getCatItem().getIdItem());
            //Cambio para poder agregar descripcion a al combo de sku
			CatListDet catItem = new CatListDet();
			catItem.setId(r.getCatItem().getIdItem());
			catItem.setCode(r.getCatItem().getCode());
			comboSKUByFamilia.add(catItem);
            //arqComponente.getlSku().add(r.getCatItem().getIdItem());
           // arqComponente.getlSkuFamilia().add(r.getCatItem().getIdItem());
//            List<CatListDet> temp = MBUtil.cargarComboUPCByItemID(serviceCatListDet, r.getCatItem().getIdItem());
//            for (CatListDet c : temp){
//                lUpcComp.add(c.getCode());
//                comboUPC.add(c);
//            }
        }
        arqComponente.setIdLista(idLista);

      /*  if(idLista != 0){
            disableForList = true;
        }else{
            disableForList = false;
        } */
       // guardarComponente();
    }

    /*public void valueChangeLista(ValueChangeEvent e) {
		Integer iLis = (Integer) e.getNewValue();
		if (iLis != null) {
			try {
				AttrSearch attrSearch = new AttrSearch();
				List<AttrSearch> insertSearch = new ArrayList<>();
				if (iLis.intValue() > 0) {
					attrSearch.setAttrName(Constants.ATT_ID_LISTA);
					attrSearch.setValue(iLis.toString());
					insertSearch.add(attrSearch);
				}

				//if (arqComponente != null&& arqComponente.getIdDescripcion() > 0) {
                if (arqComponente != null && arqComponente.getIdDescripcion() != null) {
                    attrSearch = new AttrSearch();
					attrSearch.setAttrName(Constants.ATT_ID_DESCRIPCION);
					attrSearch.setValue(String.valueOf(arqComponente
							.getIdDescripcion()));
					insertSearch.add(attrSearch);
				}

				List<GenericItem> lGi = new ArrayList<GenericItem>();
				if (insertSearch.size() > 0) {
					
					//TODO
					lGi = MBUtil.genericSearch(Constants.ATT_CODE,
							Constants.ATT_MARCA, Constants.CAT_ITEM,
							serviceDynamicCatalogs, insertSearch);
				} else {
					lGi = MBUtil.genericSearch(Constants.ATT_CODE,
							Constants.ATT_MARCA, Constants.CAT_ITEM,
							serviceDynamicCatalogs);
				}
				mUpc = new HashMap<String, String>();
				if (lGi != null) {
					for (GenericItem gi : lGi) {
						mUpc.put(String.valueOf(gi.getId()),
								String.valueOf(gi.getId()));
					}
				}
				RequestContext requestContext = RequestContext.getCurrentInstance();  
        		requestContext.execute("loader.hide()");
			} catch (GeneralException e1) {
				LOG.error(e1);
			}
		}
	} */

    private String idDescripcionCombo;
    private String idDescFiltroProv;

    public String getIdDescripcionCombo() {
        return idDescripcionCombo;
    }

    public void setIdDescripcionCombo(String idDescripcionCombo) {
        this.idDescripcionCombo = idDescripcionCombo;
    }

    public String getIdDescFiltroProv() {
        return idDescFiltroProv;
    }

    public void setIdDescFiltroProv(String idDescFiltroProv) {
        this.idDescFiltroProv = idDescFiltroProv;
    }

    public void valueChangeDesc(ValueChangeEvent e){
        itemIDSelected = String.valueOf(e.getNewValue());
        skuFiltroLista = new ArrayList<>();
        skuFiltroLista.add(itemIDSelected);
        comboSKU = new ArrayList<>();

        if (!itemIDSelected.equals("0")){
            comboUPC = MBUtil.cargarComboUPCByItemID(serviceCatListDet, itemIDSelected);
            CatItem item = serviceCatItem.getCatItemById(itemIDSelected);
            arqComponente.setSku(itemIDSelected);
            arqComponente.setDescripcion(item.getCode());
            arqComponente.setIdDescripcion(itemIDSelected);
            
            CatListDet catItem = new CatListDet();
            catItem.setId(itemIDSelected);
            catItem.setCode(item.getCode());
            comboSKU.add(catItem);
            
            if(this.itemIdList == null){
                this.itemIdList = new ArrayList<String>();
            }
            this.itemIdList.clear();
            this.itemIdList.add(itemIDSelected);
            if(arqComponente.getlSku() == null){
                arqComponente.setlSku(new ArrayList<String>());
            }
            arqComponente.getlSku().clear();
            for(String s : itemIdList){
                arqComponente.getlSku().add(s);
                
                List<CatListDet> upcsList = MBUtil.cargarComboUPCByItemID(serviceCatListDet, s);

                comboUPC.clear();
                
                if(upcsList != null){
                    for(CatListDet upc : upcsList){
                        comboUPC.add(upc);
                        if(upcsList.size() == 1){

                            if(lUpcComp == null){
                                lUpcComp = new ArrayList<String>();
                            }
                            lUpcComp.add(upcsList.get(0).getId());
                        }   
                    }
                }
                
                depurateList(lUpcComp);
            }
        }
        else{
            this.arqComponente.getlSku().clear();
            this.lUpcComp.clear();
            this.comboUPC.clear();
            this.filtarPorSubCategoria(arqComponente.getIdCategoria(), arqComponente.getIdSubCategoria());
        }
    }

    /****************************************
     * Importar Mecanicas                          *
     ****************************************/

    private List<TblCampana> lPeriodosImport;
    private List<TblMecanica> lMecImpor;
    private TblCampana periodoSelectedImport;
    private CatPrograma pSelectedImport;
    private TblMecanica mSelectedImport;
    private boolean bttnImportMec;

    public void initCombosImp() {
        lPeriodosImport = serviceCampana.getCampanas(null, null, null).getCampanas();
        impPeriodoId = 0;
        impProgranaId = 0;
        impGrupoZonaId = 0;
        lZonasImp = new ArrayList<>();
        impZonaId = 0;
        lProgramaImp = new ArrayList<>();
        lMecanicaImp = new ArrayList<>();
        impMecanicaId = 0;
        impProgranaId = 0;
        bttnImportMec = true;
    }

    public void importarMecanica() {
      /*  MecanicaDTO mecDTO = serviceArquitecturaSeven.getMecanica(mSelectedImport.getMecanicaId());
        try {
            mecDTO.setPkMec(0);
            mecDTO.setIdCampana(arqPeriodo.getIdPeriodo());
            mecDTO.setProgramaId(arqPeriodo.getIdPrograma());

            serviceArquitecturaSeven.saveMecanica(mecDTO);



            lArqComponente = new ArrayList<>();

            generarMenu();

            Messages.mensajeSatisfactorio("La mec?nica se import? exitosamente","La mec?nica se import? exitosamente");
            arqMecanica = new ArqMecanica();
            arqComponente = new ArqComponente();

        } catch (Exception e) {
            LOG.error(e);
        } */
        try {
            if (mSelectedImport == null){
                throw new Exception("Debe seleccionar Mecanica a Importar");
            }
            TblMecanica temp = serviceArquitecturaSeven.getMecanicaById(mSelectedImport.getMecanicaId());
            mSelectedImport.setMecanicaId(0);
            TblCampana c = serviceCampana.getCampana(arqPeriodo.idPeriodo);
            mSelectedImport.setCampana(c);
            mSelectedImport.setProgramaId(arqPeriodo.getIdPrograma());
            mSelectedImport.setRelGrupoZonas(temp.getRelGrupoZonas());
            if (serviceArquitecturaSeven.saveMecanicaDirect(mSelectedImport)){
                crearActividadesMecanica(mSelectedImport.getMecanicaId());
                Messages.mensajeSatisfactorio("Mecanica se importo correctamente", "");
                lArqComponente = new ArrayList<>();
                generarMenu((CatUsuarios) Util.getSessionAttribute("userLoged"));
                arqMecanica = new ArqMecanica();
                arqComponente = new ArqComponente();
            }
        }catch (Exception e){
            LOG.info(e);
            Messages.mensajeErroneo("Error ", e.getMessage());
        }

    }

    public void valueChangePeriodoImpl() {
        if (periodoSelectedImport != null){
            lProgramaImp = new ArrayList<>();
            lMecImpor = serviceArquitecturaSeven.getMecanicasByCampana(periodoSelectedImport.getIdCampana());
            List<TblCampanaProgramas> progs = new ArrayList<>(periodoSelectedImport.getTblCampanaProgramas());
            for (TblCampanaProgramas cp : progs){
                lProgramaImp.add(cp.getPrograma());
            }
            bttnImportMec = false;
        }else{
            lMecImpor = new ArrayList<>();
            lProgramaImp = new ArrayList<>();
            bttnImportMec = true;
        }

    }

    public void valueChangeProgramaImpl() {
        if (pSelectedImport != null){
            lMecImpor = serviceArquitecturaSeven.getMecanicaByPrograma(periodoSelectedImport.getIdCampana(), pSelectedImport.getIdPrograma());
        }else{
            valueChangePeriodoImpl();
        }
    }

    public List<TblCampana> getlPeriodosImport() {
        return lPeriodosImport;
    }

    public void setlPeriodosImport(List<TblCampana> lPeriodosImport) {
        this.lPeriodosImport = lPeriodosImport;
    }

    public TblCampana getPeriodoSelectedImport() {
        return periodoSelectedImport;
    }

    public void setPeriodoSelectedImport(TblCampana periodoSelectedImport) {
        this.periodoSelectedImport = periodoSelectedImport;
    }

    public List<TblMecanica> getlMecImpor() {
        return lMecImpor;
    }

    public void setlMecImpor(List<TblMecanica> lMecImpor) {
        this.lMecImpor = lMecImpor;
    }

    public CatPrograma getpSelectedImport() {
        return pSelectedImport;
    }

    public void setpSelectedImport(CatPrograma pSelectedImport) {
        this.pSelectedImport = pSelectedImport;
    }

    public TblMecanica getmSelectedImport() {
        return mSelectedImport;
    }

    public void setmSelectedImport(TblMecanica mSelectedImport) {
        this.mSelectedImport = mSelectedImport;
    }

    public boolean isBttnImportMec() {
        return bttnImportMec;
    }

    public void setBttnImportMec(boolean bttnImportMec) {
        this.bttnImportMec = bttnImportMec;
    }

    /**
     * *****************************************
     */

    public void nuevoComponentes() {
        if (arqMecanica != null && arqMecanica.getIdMecanica() > 0) {
            lUpcComp = new ArrayList<>();
            habilitarComp = true;
        }
    }

    public boolean isPuedeAgregarComp() {
            return puedeAgregarComponente();
    }

    public void setPuedeAgregarComp(boolean puedeAgregarComp) {
            this.puedeAgregarComp = puedeAgregarComp;
    }


    

    public void showEstrategiaSeven(){
        estrategia = true;
        agregarPromocion = false;
        lCategEstra2 = MBUtil.getSelectItemsCategory(serviceCatCategory);
    }
    
    public void fillComboDescEstrategia(){
        LOG.info(idCategEstra2);
        List<CatItem> items = serviceCatItem.getCatItemByCategory(idCategEstra2);
        lDescEstra2 = new ArrayList<>();
        for (CatItem item : items){
            lDescEstra2.add(new SelectItem(item.getIdItem(), item.getCode()));
        }
    }
    
    public void fillComboSKUEstrategia(){
        lSKUEstra2 = new ArrayList<>();
        lSKUEstra2.add(new SelectItem(idDescEstra2, String.valueOf(idDescEstra2)));
    }

    @SuppressWarnings("unchecked")
    public void filterGrid2() {
            try {
                    Object[] response = serviceArquitecturaSeven.getZonasPrecio(
                                    idDescEstra2, idSkuEstra2, idUpcEstra2, idCategEstra2);
                    if (response != null && response.length > 1) {
                            setColumns2((List<ColumnModel>) response[0]);
                            setResponseArray2((List<Map<String, String>>) response[1]);
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
    }

    @SuppressWarnings("unchecked")
    public void filterGrid() {
            try {
                    Object[] response = serviceArquitecturaSeven.getZonaProductoPrecio(
                                    idDescEstra, idSkuEstra, idUpcEstra, idCategEstra,
                                    lGposZonaEstra, lZonasEstra, idListaEstra);
                    if (response != null && response.length > 1) {
                            setColumns((List<ColumnModel>) response[0]);
                            setResponseArray((List<Map<String, String>>) response[1]);
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
            }
    }

    public void volverPromocion() {
            estrategia = false;
            agregarPromocion = true;
    }

    public void configMecanica() {
            obtenerMecanica(idMecanicaPrec);
            obtenerConfigMecanica();
    }

    public void updateHojaComponente(ArqComponente componente){
        if (usuarioLogeado.getCatRole().getIdrole() == 1){
            if(serviceArquitecturaSeven.updateHojaComponente(componente.getIdComponente(), componente.hoja)){
                Messages.mensajeSatisfactorio("Informacion actualizada", "");
            }else{
                Messages.mensajeErroneo("Error al guardar informacion");
            }
        }else{
            Messages.mensajeErroneo("Permiso Denegado", "Solo Administrador puede modificar");
        }
    }

    private void obtenerConfigMecanica() {
        if (lArqComponente != null) {
            configMecanica = new ConfigMecanica();
            configMecanica.setIdMecanica(arqMecanica.idMecanica);
            configMecanica.setIdPrograma(arqPeriodo.getIdPrograma());
            configMecanica.setlConfigMecanicaDet(new ArrayList<ConfigMecanicaDet>());
            List<Integer> lAux = new ArrayList<Integer>();
            if (arqMecanica.getConfigMecanica() != null && arqMecanica.getConfigMecanica().getlConfigMecanicaDet() != null
                                    && arqMecanica.getConfigMecanica().getlConfigMecanicaDet().size() > 0) {
                configMecanica.getlConfigMecanicaDet().addAll(arqMecanica.getConfigMecanica().getlConfigMecanicaDet());
                for (ConfigMecanicaDet det : arqMecanica.getConfigMecanica().getlConfigMecanicaDet()) {
                    lAux.add(det.getIdCategoria());
                }
            }

            for (ArqComponente arqComp : lArqComponente) {
                if (arqComp.getIdCategoria() > 0 && !lAux.contains(arqComp.getIdCategoria())) {
                    ConfigMecanicaDet confMecDet = new ConfigMecanicaDet();
                    confMecDet.setIdCategoria(arqComp.getIdCategoria());
                    confMecDet.setDescCategoria(arqComp.getCategoria());
                    configMecanica.getlConfigMecanicaDet().add(confMecDet);
                    lAux.add(arqComp.getIdCategoria());
                }
            }

        }
    }

    private boolean validateConfigMecanica() {
        if (configMecanica.getTotalCant() != ahorroMaximo) {
            Messages.mensajeAlerta("Distribuci?n Ahorro es diferente a Ahorro Maximo",
                    "Distribuci?n Ahorro es diferente a Ahorro Maximo");
            return false;
        }

        if (configMecanica.getTotalPorc() != 100) {
            Messages.mensajeAlerta(
                    "Total Distribuci?n Ahorro debe ser 100 %, favor de revisar",
                    "Total Distribuci?n Ahorro debe ser 100 %, favor de revisar");
            return false;
        }

        return true;
    }

    public void configMecanicaCal(boolean cantidad){
        double totalCant = 0;
        double totalPorc = 0;
        for (ConfigMecanicaDet configMecanicaDet : configMecanica.getlConfigMecanicaDet()){
            if (cantidad){
                double p = (configMecanicaDet.getDistCantidad() * 100) / ahorroMaximo;
                configMecanicaDet.setDistPorcentaje(p);
            }else{
                configMecanicaDet.setDistCantidad(ahorroMaximo * (configMecanicaDet.getDistPorcentaje()/100));
            }
            configMecanicaDet.setDistPorcentaje(configMecanicaDet.getDistPorcentaje());
            totalCant += configMecanicaDet.getDistCantidad();
        }
        configMecanica.setTotalCant(totalCant);
        configMecanica.setTotalPorc(totalPorc);
        if (totalCant > ahorroMaximo || totalPorc > 100){
            Messages.mensajeAlerta("Distribucion Ahorro mayor a permitido", "Distribucion Ahorro mayor a permitido");
        }
    }

    public void guardarConfigMecanica() {
        if (validateConfigMecanica()) {
            List<ConfMecanicaDTO> lConfMecDTO = new ArrayList<>();
            for (ConfigMecanicaDet confMecDet : configMecanica.getlConfigMecanicaDet()) {
                ConfMecanicaDTO confMecDTO = new ConfMecanicaDTO();
                confMecDTO.setAhorro(confMecDet.getDistCantidad());
                confMecDTO.setAhorroPorcentaje(confMecDet.getDistPorcentaje());
                confMecDTO.setCategoriaId(confMecDet.getIdCategoria());
                confMecDTO.setPkMec(idMecanicaPrec);
                lConfMecDTO.add(confMecDTO);
            }

            try {
                String msj = "La Configuraci?n Mec?nica se guard? exitosamente";
                serviceArquitecturaSeven.saveConfMecanica(lConfMecDTO);
                for (ArqComponente c : lArqCompPrecProm){
                    c.setDistRebaja(configMecanica.getTotalCant());
                }
                Messages.mensajeSatisfactorio(msj,msj);
            } catch (Exception e) {
                    LOG.error(e);
            }
        }
    }

    public void setearEditando(){
        if(arqComponenteSel != null){
            LOG.info("Elemento seleccionado no nulo");
            for(ArqComponente e : this.lArqCompPrecProm){
                if(e.getIdComponente() == arqComponenteSel.getIdComponente()){
                    LOG.info("componente encontrado, poniendo edit");
                    e.setEditando(true);
                }
            }
        }else{
            LOG.info("elemento seleccionado nulo");
        }
    }
    
	public void guardarPrecio() {
		try {
			List<PreciosPromocionDTO> lPrecPromo = new ArrayList<>();
        
			if (lArqCompPrecProm != null && lArqCompPrecProm.size() > 0) {
				for (ArqComponente arqComPreProm : lArqCompPrecProm) {
					PreciosPromocionDTO precioPromocionDTO = arqComPreProm.getPreciosPromocionDTO();
                    precioPromocionDTO.setZonaId(arqComPreProm.getIdZona());
                    precioPromocionDTO.setPkComp(arqComPreProm.getIdComponente());
					
					precioPromocionDTO.setEstatusRevision(idStatusRevision);
					lPrecPromo.add(precioPromocionDTO);
				}
				
                serviceArquitecturaSeven.savePrecios(lPrecPromo);
				Messages.mensajeSatisfactorio("El precio se guard? exitosamente", "El precio se guard? exitosamente");
				
                if(idStatusRevision == 4){
                    Messages.mensajeSatisfactorio("Estatus Rechazado. Reasignando actividades.", "Estatus Rechazado. Reasignando actividades.");
                    precioRechazado();
                }
                
                
                precPromoPrec = 0;
				numPromoPrec = 0;
				idStatusCaptura = 0;
				idStatusRevision = 0;
				nomPromoPrec = "";
                
                this.preparePreciosView();
			}
		} catch (Exception e) {
			LOG.error(e);
			Messages.mensajeAlerta("Ocurrio un error al guardar el precio", "Ocurrio un error al guardar el precio");
		}
	}
    
    double mRound(double value, double factor) {
        return Math.round(value / factor) * factor;
    }
    public void preGuardarPrecioPromocion(boolean validar) {
       LOG.warn("VALIDANDO...");
       Integer zonaSeleccionadas = this.ppCatZonesSelected.size();
       
       Map<Integer, String> selected = new HashMap<Integer, String>();
       for(String s: this.ppCatZonesSelected){
           selected.put(Integer.parseInt(s), s);
       }
       
       LOG.info("Total de Zonas seleccionadas: " + zonaSeleccionadas);
       
		try {
			
           List<ArqComponente> elementosAGuardar = new ArrayList<ArqComponente>();
           
           if(lArqCompPrecProm != null && lArqCompPrecProm.size() > 0 && validatePrecioPromo()){
               boolean confirmar = false;
               //por cada zona de la mecanica
               for (String sZona : arqMecanica.getlZonas()){
                   //por cada componente
                   for(ArqComponente e: lArqCompPrecProm){
                   
                       //si el numero de componente es el mismo y el id de la zona es el mismo al de la zona en cuestion
                       if(e.getNumeroComponente() == this.componentePrecioPromocionSeleccionado && e.getIdZona() == Integer.parseInt(sZona)){
                           
                           //si iteran las zonas seleccionadas
                           if(selected.containsKey(e.getIdZona())){
                               elementosAGuardar.add(e);
                               PreciosPromocionDTO ppDTO = e.getPreciosPromocionDTO();
                               if (validar && (
                            		   (ppDTO.getAhorroFijo() != null && ppDTO.getAhorroFijo() > 0.0) 
                            		   || (ppDTO.getPorcentaje() != null && ppDTO.getPorcentaje() > 0.0) 
                            		   || (ppDTO.getPrecio() != null && ppDTO.getPrecio() > 0.0)
                            		   )) {
                            	   confirmar = true;
                               }
                           }
                       }
                   }
               }
               if (confirmar) {
            	   RequestContext.getCurrentInstance().execute("confGuarComp.show();"); 
               } else {
            	   guardarPrecioPromocion(elementosAGuardar);
               }
           }
           
           boolean allPrices = allPricesValidation();
           LOG.info("--------------------------------------------------------------------");
           LOG.info("tienen todos los componentes precio: " + allPrices);
           if(allPrices){
               LOG.info("reorganizando componentes");
               sortPromotionPriceList();
           }
           
		} catch (Exception e) {
           e.printStackTrace();
			LOG.error(e);
			Messages.mensajeAlerta("Ocurrio un error al guardar el precio promoci?n","Ocurrio un error al guardar el precio promoci?n");
		}
    }
	public void guardarPrecioPromocion(List<ArqComponente> elementosAGuardar) throws Exception {
		LOG.warn("GUARDAR PROMOCI?N");
		List<PreciosPromocionDTO> lPrecPromo = new ArrayList<>();
        boolean capturoPrecio = false;
        boolean capturoPorc = false;
        boolean capturoAhorro = false;
        
        if(precPromo > 0 || (precPromo == 0 && porcPromo == 0 && ahorroFijo == 0)){
            capturoPrecio = true;
        }if(porcPromo > 0){
            capturoPorc = true;
        }if(ahorroFijo > 0){
            capturoAhorro = true;
        }
        
        boolean capturoRecuperacionCantidad = false;
        boolean capturoRecuperacionPorcentaje = false;
        
        if(recuProveCant > 0){
            capturoRecuperacionCantidad = true;
        }if(recuProvePorc > 0){
            capturoRecuperacionPorcentaje = true;
        }
        
        boolean capturoDistribucionCantidad = false;
        
        if(cantidadDistribucionRebaja > 0){
            capturoDistribucionCantidad = true;
        }
    
        LOG.info("Banderas, capturoPrecio:  " + capturoPrecio );
        LOG.info("Banderas, capturo Porcentaje:  " + capturoPorc );
        LOG.info("Banderas, capturo ahorro:  " + capturoAhorro ); 
        
        for(ArqComponente elementoAGuardar :  elementosAGuardar){
            
            LOG.info("Elemento a guardar: " + elementoAGuardar.getDescripcion());
            LOG.info("Valores, Monto: " + precPromo + ", %Promocion: " + porcPromo + ", ahorroFijo: " + ahorroFijo);

        
            Double dPrecioVenta = elementoAGuardar.getPreciosPromocionDTO().getPrecioVenta();

            //si capturo precio
            if(capturoPrecio){
                LOG.info("calculando por precio");
                if(dPrecioVenta > 0){
                    //porcPromo = ((dPrecioVenta - precPromo) / dPrecioVenta) * 100;
                    porcPromo = ( precPromo / dPrecioVenta ) - 1;
                }else{
                    porcPromo = 0;
                }
                ahorroFijo = dPrecioVenta - precPromo;
            }
            double porcPromoRecalculado = porcPromo;
            //si capturo porcentaje
            if(capturoPorc){
                LOG.info("calculando por porcentaje");
                if(dPrecioVenta > 0){
                   double pp = dPrecioVenta * (1 - (porcPromo / 100F));
                   LOG.info("valor pp calculado en porcPromo: " + pp);
                   precPromo = this.mRound(pp, 0.5D);
                   LOG.info("Valor redondeado: " + precPromo);
                   
                   LOG.info("recalculando porcentaje en base a precio");
                   porcPromoRecalculado = ( precPromo / dPrecioVenta ) - 1;
                }else{
                   precPromo = 0;	
                }
                ahorroFijo = dPrecioVenta - precPromo;
            }
            
            //si capturo ahorro fijo
            if(capturoAhorro){
                LOG.info("calculando por ahorro");
                precPromo = dPrecioVenta - ahorroFijo;
                if(dPrecioVenta > 0){
                    porcPromo = ( precPromo / dPrecioVenta ) - 1;
                    porcPromoRecalculado = porcPromo;
                } 
            }

            if(capturoRecuperacionCantidad && ahorroFijo > 0){
                recuProvePorc = ((recuProveCant / ahorroFijo) * 100);
            }else if(capturoRecuperacionPorcentaje){
                recuProveCant = ahorroFijo * (recuProvePorc / 100);
            }
            
            LOG.info("porcenantaje de recuperacion de vendedor: cantidad:" + recuProveCant + ", por:" + recuProvePorc);

            if(capturoDistribucionCantidad){
                porcentajeDistribucionRebaja = (cantidadDistribucionRebaja * 100F) / ahorroFijo;
            }else{
            	cantidadDistribucionRebaja = (porcentajeDistribucionRebaja * ahorroFijo) / 100;
            }
            LOG.info("Distribucion de rebaja. Cantidad:" + cantidadDistribucionRebaja + ", por:" + porcentajeDistribucionRebaja);

            PreciosPromocionDTO ppDTO = elementoAGuardar.getPreciosPromocionDTO();
            
            ppDTO.setPrecio(precPromo);
            ppDTO.setPorcentaje(porcPromoRecalculado * 100);
            ppDTO.setAhorroFijo(ahorroFijo);
            ppDTO.setRecuperacion(recuProveCant);
            ppDTO.setRecuperacionPorcentaje(recuProvePorc);
            ppDTO.setDistribucionRebajaCantidad(cantidadDistribucionRebaja);
            ppDTO.setDistribucionRebajaPorcentaje(porcentajeDistribucionRebaja);

            ppDTO.setObjetivo(String.valueOf(objetivo));
            ppDTO.setComentario(comenDisenio);
            ppDTO.setPkMec(elementoAGuardar.getIdMecanica());
            ppDTO.setPkComp(elementoAGuardar.getIdComponente());
            ppDTO.setZonaId(elementoAGuardar.getIdZona());
            ppDTO.setPrecioVenta(dPrecioVenta);
            
            ppDTO.setPorcentajeDescuento(this.porcentajeDescuento);
            
            if(this.porcentajeRetencion == null){
                ppDTO.setPorcentajeRetencion(100D);
            }else{
                ppDTO.setPorcentajeRetencion(this.porcentajeRetencion);
            }
            
            if(this.elasticidad == null){
                ppDTO.setElasticidad(-1D);
            }else{
                ppDTO.setElasticidad(this.elasticidad);
            }
            
            lPrecPromo.add(ppDTO);

        }
        
        serviceArquitecturaSeven.savePrecios(lPrecPromo);
        Messages.mensajeSatisfactorio("El precio promoci?n se guard? exitosamente","El precio promoci?n se guard? exitosamente");
    
    
        precPromo = 0;
        porcPromo = 0;
        ahorroFijo = 0;
        recuProveCant = 0;
        recuProvePorc = 0;
        cantidadDistribucionRebaja = 0;
        porcentajeDistribucionRebaja = 0;
        objetivo = 0;
        comenDisenio = "";
        this.porcentajeDescuento = null;
        this.porcentajeRetencion = null;
        this.elasticidad = null;
        this.porcentajeDescuentoStr = null;
        this.porcentajeRetencionStr = null;
        this.elasticidadStr = null;

        List<String> lSkuPre = null;
        if (lSkuPrec != null) {
            lSkuPre = new ArrayList<>();
            for (String sku : lSkuPrec) {
                lSkuPre.add(sku);
            }
        }

        List<String> lUpcPre = null;
        if (lUpcPrec != null) {
            lUpcPre = new ArrayList<>();
            for (String upc : lUpcPrec) {
                lUpcPre.add(upc);
            }
        }

        List<String> lZonasAux = new ArrayList<>();
        if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() && (lZonasPrec == null || lZonasPrec.isEmpty())) {
            lZonasAux.addAll(lZonaXGrupo);
        } else {
            if (lZonasPrec != null) {
                lZonasAux.addAll(lZonasPrec);
            }
        }

        if (lZonasAux.isEmpty()) {
            lZonasAux.addAll(arqMecanica.getlZonas());
        }

        LOG.info("ejecutando 'obtenerPreciosPromocion' desde: guardarPrecioPromocion");
        obtenerPreciosPromocion(idMecanicaPrec, idCatPrec,idSubCatPrec, idDescPrec, idCompPrec, idListaPrec, lSkuPre, lUpcPre, lZonasAux);
        LOG.info("fin del proceso");
	}
    
    public Map<Integer, Integer> getZonesFromList(List<ArqComponente> l){
        Map<Integer,Integer> zones = new HashMap<Integer, Integer>();
        for(ArqComponente e: l){
            Integer zone = Integer.parseInt(String.valueOf(e.getIdZona()));
            if(!zones.containsKey(zone)){
                zones.put(zone, zone);
            }
        }
        return zones;
    }
    
    public void sortPromotionPriceList(){
        Map<Integer,List<ArqComponente>> grps = getMapForCompoentList(lArqCompPrecProm);
        //si solo es un elemento, no aplica
        if(grps.size() == 1){
            LOG.info("Solo existe un componente, no aplica");
            this.esCombo = false;
            return;
        }
        //si tiene componentes complejos, como no es uno, ya aplica
        if(tieneComponenteComplejo(grps)){
            try{
            LOG.info("Componentes complejos, reorganizando grid");
            this.esCombo = true;
            updateGrid(grps);
            }catch(Exception ex){
                 LOG.info("ERROR AL REORGANIZAR**********************+");
                ex.printStackTrace();
            }
            
        }else{
            LOG.info("Componentes no complejos, continuando");
            this.esCombo = false;
        }
    }
    
    
    public List<ArqComponente> getListByZone(List<ArqComponente> l, Integer zone){
        LOG.info("filtrando lista de : " + l.size() + " elementos, por zona: " + zone);
        List<ArqComponente> tmp = new ArrayList<ArqComponente>();
        for(ArqComponente e : l){
            if(e.getIdZona() == zone){
                tmp.add(e);
            }
        }
        LOG.info("Tam de lista filtrada: " + tmp.size());
        return tmp;
    }
    
    public void updateGrid(Map<Integer, List<ArqComponente>> e){
        
        LOG.info("++++++++++++++++++++ INICIO DE UPDATE GRID +++++++++++++++++++++++++++++++++");
        
        //sacar la lista con mas elementos (siempre ser? el ultimo elemento de la lista
        List<ArqComponente> ultimoComponente = e.get(e.size());
        LOG.info("Total de elemento de ultimo componente considerando todas las zonas: " + ultimoComponente.size());
        
        //sacar el total de zonas
        Map<Integer, Integer> zones = this.getZonesFromList(lArqCompPrecProm);
        LOG.info("Total de zonas: " + zones.size());
        
        List<List<ArqComponente>> componentes = new ArrayList<List<ArqComponente>>();
        
        //genero una lista con las listas de componentes, descartando el ultimo
        
        for(int i = 1; i <=(e.size() - 1); i++){
            componentes.add(e.get(i));
        }
        
        LOG.info("Total de listas de componentes: " + componentes.size());
        
        //Lista final
        List<ArqComponente> finalList = new ArrayList<ArqComponente>();
        
        int counter = 1;
        for(Integer zone : zones.keySet()){
            
            Double ahorroMaximoDeZona = 0D;
            
            LOG.info("*** Armando combinaciones para zona: " + zones);
            //defino mi lista final vacia
            List<List<ArqComponente>> listaFinal = new ArrayList<List<ArqComponente>>();
        
            //procesamiento por cada componente
            for(int i = 0; i < componentes.size(); i++){
                
                List<ArqComponente> componenteEnIteracion = this.getListByZone(componentes.get(i), zone);
                
                
                LOG.info("iteracion del componente: " + i);
                //si el tama?o de la lista final es cero hago directa la primera union
                if(listaFinal.isEmpty()){
                    
                    if(componentes.size() >= ((i+1)+1)){
                        LOG.info("primera iteracion, haciendo primer union manual");
                        List<ArqComponente> siguienteComponente = this.getListByZone(componentes.get(i+1), zone);
                    
                        for(ArqComponente el1 : componenteEnIteracion){
                            for(ArqComponente el2 : siguienteComponente){
                                List<ArqComponente> union = new ArrayList<ArqComponente>(Arrays.asList(el1, el2));
                                listaFinal.add(union);   
                            }   
                        }
                    }else{
                        for(ArqComponente el1 : componenteEnIteracion){
                            List<ArqComponente> union = new ArrayList<ArqComponente>(Arrays.asList(el1));
                            listaFinal.add(union); 
                        }
                    }
                    
                    
                }
                //si el indice es 1, se continua al siguiente porque el 1 se consume en la iteracion inicial
                else if(i == 1){
                    LOG.info("indice uno integrado en primer union, continuando");
                }
                //si no es los casos anteriores, se itera la lista temporal por el elemento en cuestion
                else{
                    LOG.info("procesando lista parcial vs elemeneto");
                    List<List<ArqComponente>> tmp = new ArrayList<List<ArqComponente>>();

                    for(List<ArqComponente> listaParcial : listaFinal){

                        for(ArqComponente ce : componenteEnIteracion){
                            
                            List<ArqComponente> union = new ArrayList<ArqComponente>();
                            union.addAll(listaParcial);
                            union.add(ce);
                            tmp.add(union);
                        }
                    }
                    listaFinal = tmp;
                }
            }
            
            LOG.info("--------------- lista al momento de iteracion en zona: " + zone + "-------------------");
            for(List<ArqComponente> lista : listaFinal){
                LOG.info(lista);
            }


            //a cada lista le asocia los elementos finales
            LOG.info("agregando elementos finales a componente ???????????????????????????????????????");
            for(List<ArqComponente> c : listaFinal){
                LOG.info("Total de elementos en la lista en question:");
                
                Double totalPrecioVenta = 0D;
                Double totalMgnRegular = 0D;
                Double totalDistribucionRebaja = 0D;
                Double totalDistribucionRebajaPorc = 0D;
                Double totalPrecioR = 0D;
                Double totalRecuperacion = 0D;
                
                for(ArqComponente cl : c){
                    totalPrecioVenta = totalPrecioVenta + cl.getPreciosPromocionDTO().getPrecioVenta();
                    totalMgnRegular = totalMgnRegular + cl.getMgnRegular();
                    totalDistribucionRebaja = totalDistribucionRebaja + cl.getDistribucionRebaja();
                    totalDistribucionRebajaPorc = totalDistribucionRebajaPorc + cl.getDistribucionRebajaPorc();
                    totalPrecioR = totalPrecioR + cl.getPreciosPromocionDTO().getPrecioR();
                    totalRecuperacion = totalRecuperacion + cl.getPreciosPromocionDTO().getRecuperacion();
                }
                
                LOG.info("Total previo de venta al momento: " + totalPrecioVenta);
                
                List<ArqComponente> ultimoComponentePorZona = this.getListByZone(ultimoComponente, zone);
                for(ArqComponente cul : ultimoComponentePorZona){
                    
                    ArqComponente cul2 = (ArqComponente)SerializationUtils.clone(cul);
                    
                    cul2.setEsUltimoElementoDeCombo(true);
                    
                    Double precioVentaCombo = cul2.getPreciosPromocionDTO().getPrecioVenta() + totalPrecioVenta;
                    Double mgnRegularCombo = cul2.getMgnRegular() + totalMgnRegular;
                    Double mgnRegularPorcCombo = mgnRegularCombo / precioVentaCombo;
                    Double distribucionRebajaCombo = totalDistribucionRebaja + cul2.getDistribucionRebaja();
                    Double distribucionRebajaPorcCombo = totalDistribucionRebajaPorc + cul2.getDistribucionRebajaPorc();
                    Double precioRcombo = totalPrecioR + cul2.getPreciosPromocionDTO().getPrecioR();
                    Double descuentoPromocionCombo = precioRcombo / precioVentaCombo - 1;
                    Double ahorroFijoCombo = precioVentaCombo - precioRcombo;
                    
                    if(ahorroFijoCombo > ahorroMaximoDeZona){
                        ahorroMaximoDeZona = ahorroFijoCombo;
                    }
                    
                    Double recuperacionCombo = totalRecuperacion + cul2.getPreciosPromocionDTO().getRecuperacion();
                    Double recuperacionPorcentajeCombo = recuperacionCombo / ahorroFijoCombo;
                    
                    Double mgnPromocionCombo = (mgnRegularPorcCombo * precioRcombo) + ((recuperacionCombo - ahorroFijoCombo) * 1);
                    Double mgnPromocionPorcCombo = mgnPromocionCombo / precioRcombo;
                    
                    Double ventaUPTDCombo = descuentoPromocionCombo * cul2.getPreciosPromocionDTO().getElasticidad();
                    
                    //asignacion de nuevos valores
                    cul2.getPreciosPromocionDTO().setPrecioVentaCombo(precioVentaCombo);
                    cul2.setMgnRegularCombo(mgnRegularCombo);
                    cul2.setMgnRegularPorcCombo(mgnRegularPorcCombo * 100);
                    cul2.setPorcentajeRetencionCombo(100D);
                    cul2.setDistribucionRebajaCombo(distribucionRebajaCombo);
                    cul2.setDistribucionRebajaPorcCombo(distribucionRebajaPorcCombo);
                    cul2.getPreciosPromocionDTO().setPrecioRCombo(precioRcombo);
                    cul2.getPreciosPromocionDTO().setPorcentajeCombo(descuentoPromocionCombo * 100);
                    cul2.getPreciosPromocionDTO().setAhorroFijoCombo(ahorroFijoCombo);
                    cul2.getPreciosPromocionDTO().setRecuperacionCombo(recuperacionCombo);
                    cul2.getPreciosPromocionDTO().setRecuperacionPorcentajeCombo(recuperacionPorcentajeCombo * 100);
                    
                    cul2.setMgnPromocionCombo(mgnPromocionCombo);
                    cul2.setMgnPromocionPorcCombo(mgnPromocionPorcCombo * 100);
                    
                    cul2.setVentaUPTDCombo(ventaUPTDCombo * 100);
                    
                    c.add(cul2);
                } 
            }
            
            LOG.info("--------------- lista al momento de iteracion en zona: " + zone + "-------------------");
            for(List<ArqComponente> lista : listaFinal){
                for(ArqComponente el : lista){
                    el.setAhorroMaximoCombo(ahorroMaximoDeZona);
                }
            }
        
            //a cada combinacion de la lista final, se concatenan los elementos de ultimo componente
            LOG.info("generando lista ultima final");
            for(List<ArqComponente> f : listaFinal){
                for(ArqComponente combinacionElement : f){
                    if(counter % 2 > 0){
                        combinacionElement.setRowStyle("rowStyleZigZag");
                    }else{combinacionElement.setRowStyle("");}
                    finalList.add(combinacionElement);
                }
            }
            counter += 1;
            LOG.info("************************ fin de procamiento por zona ******************************");
            
        
        }
        
        
        LOG.info("--------------------------------------------------------------------------");
        LOG.info("FIN DE ITERACION DE ZONAS, LISTADO FINAL");
        
        
        for(ArqComponente element : finalList){
            LOG.info("# " + element.getNumeroComponente() + ", " + element.getDescripcion() );
        }
        
        LOG.info("FINAL ***");
        lArqCompPrecProm = finalList;
    }
    
    public boolean tieneComponenteComplejo(Map<Integer,List<ArqComponente>> grps ){
        boolean tieneComponenteComplejo = false;
        for (Map.Entry<Integer, List<ArqComponente>> entry : grps.entrySet()) {
            if(entry.getValue().size() > 1){
                tieneComponenteComplejo = true;
                break;
            }
        }
        return tieneComponenteComplejo;
    }
    
    public Map<Integer, List<ArqComponente>> getMapForCompoentList(List<ArqComponente> l){
        Map<Integer,List<ArqComponente>> agrupaciones = new HashMap<Integer,List<ArqComponente>>();
        for(ArqComponente componente: l){
            Integer no = Integer.parseInt(String.valueOf(componente.getNumeroComponente()));
            if(agrupaciones.containsKey(no)){
                agrupaciones.get(no).add(componente);
            }else{
                List<ArqComponente> listadoDeComponentes = new ArrayList<ArqComponente>();
                listadoDeComponentes.add(componente);
                agrupaciones.put(no, listadoDeComponentes);
            }
        }
        agrupaciones = sortMapByValue(agrupaciones);
        return agrupaciones;
    }
    
    public boolean allPricesValidation(){
        boolean allPrices = true;
        for(ArqComponente e: lArqCompPrecProm){
            if(!e.tienePrecioPromocion){
                allPrices = false;
                break;
            }
        }
        return allPrices;
    }

	public void guardarEdicionPrecPromo() {
		try {
			if (arqComponenteSel != null && validatePrecioPromoEdicion()) {
				double dPrecioVenta = (double) 0;
				PreciosPromocionDTO precioPromocionDto = arqComponenteSel.getPreciosPromocionDTO();
				Double ahorroFijoLocal = precioPromocionDto.getAhorroFijo();
				if (precioPromocionDto.getPrecioVenta() != null) {
					dPrecioVenta = precioPromocionDto.getPrecioVenta();
					double precioEdit = arqComponenteSel.getPrecioEdit();
					double porcentajeEdit = arqComponenteSel.getPorcentajeEdit();
					Double precioDto = precioPromocionDto.getPrecio();
					double ahorroFijoEdit = arqComponenteSel.getAhorroFijoEdit();

					if (precioEdit != precioDto) {
						if (dPrecioVenta == 0) {
							precioPromocionDto.setPorcentaje(0D);
						} else {
							precioPromocionDto.setPorcentaje(((dPrecioVenta - precioEdit) / dPrecioVenta) * 100);
						}
						precioPromocionDto.setAhorroFijo(dPrecioVenta - precioEdit);
						precioPromocionDto.setPrecio(precioEdit);
					} else if (porcentajeEdit != precioPromocionDto.getPorcentaje()) {
						precioPromocionDto.setPrecio(dPrecioVenta * (porcentajeEdit / 100));
						precioPromocionDto.setAhorroFijo(dPrecioVenta - precioDto);
						precioPromocionDto.setPorcentaje(porcentajeEdit);
					} else if (ahorroFijoEdit != ahorroFijoLocal) {
						precioPromocionDto.setPrecio(dPrecioVenta - ahorroFijoEdit);
						if (dPrecioVenta == 0) {
							precioPromocionDto.setPorcentaje(0D);
						} else {
							precioPromocionDto.setPorcentaje((ahorroFijoEdit / dPrecioVenta) * 100);
						}
						precioPromocionDto.setAhorroFijo(ahorroFijoEdit);
					}
				}
				
				double recuperacion = (double) 0;
				if (precioPromocionDto.getRecuperacion() != null) {
					recuperacion = precioPromocionDto.getRecuperacion();
				}
				if (arqComponenteSel.getRecuperacionEdit() != recuperacion) {
					if (ahorroFijoLocal == 0) {
						precioPromocionDto.setRecuperacionPorcentaje(0D);
					} else {
						precioPromocionDto.setRecuperacionPorcentaje((arqComponenteSel.getRecuperacionEdit() / ahorroFijoLocal) * 100);
					}
					precioPromocionDto.setRecuperacion(arqComponenteSel.getRecuperacionEdit());
				} else if (arqComponenteSel.getRecuperacionPorcentajeEdit() != precioPromocionDto.getRecuperacionPorcentaje()) {
					precioPromocionDto.setRecuperacion(ahorroFijoLocal * (arqComponenteSel.getRecuperacionPorcentajeEdit() / 100));
					precioPromocionDto.setRecuperacionPorcentaje(arqComponenteSel.getRecuperacionPorcentajeEdit());
				}
				
				List<PreciosPromocionDTO> lPrecPromo = new ArrayList<>();
				lPrecPromo.add(precioPromocionDto);
				serviceArquitecturaSeven.savePrecios(lPrecPromo);
				Messages.mensajeSatisfactorio(
						"El precio promoci?n se edit? exitosamente", 
						"El precio promoci?n se edit? exitosamente");
				
				List<String> lSkuPre = null;
				if (lSkuPrec != null) {
					lSkuPre = new ArrayList<>();
					for (String sku : lSkuPrec) {
						lSkuPre.add(sku);
					}
				}
				
				List<String> lUpcPre = null;
				if (lUpcPrec != null) {
					lUpcPre = new ArrayList<>();
					for (String upc : lUpcPrec) {
						lUpcPre.add(upc);
					}
				}
				
				List<String> lZonasAux = new ArrayList<>();
				if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty() && (lZonasPrec == null || lZonasPrec.isEmpty())) {
					lZonasAux.addAll(lZonaXGrupo);
				} else if (lZonasPrec != null) {
					lZonasAux.addAll(lZonasPrec);

				}
				
				if (lZonasAux.isEmpty()) {
					lZonasAux.addAll(arqMecanica.getlZonas());
				}
				LOG.info("llamando a obtenerPreciosPromocion desde:  guardarEdicionPrecPromo");
				obtenerPreciosPromocion(idMecanicaPrec, idCatPrec, idSubCatPrec, idDescPrec,
						idCompPrec, idListaPrec, lSkuPre, lUpcPre, lZonasAux);
			}
		} catch (Exception e) {
			LOG.error(e);
			Messages.mensajeAlerta(
					"Ocurrio un error al editar el precio promoci?n",
					"Ocurrio un error al editar el precio promoci?n");
		}
	}

	public void guardarEdicionPrec() {
        try {
            if (arqComponenteSel != null) {
                
                LOG.info("guardando promocion en precios");
                int estatusCaptura = arqComponenteSel.getPreciosPromocionDTO().getEstatusCapturaEdicion();
                int estatusRevision = arqComponenteSel.getPreciosPromocionDTO().getEstatusRevisionEdicion();
                LOG.info("estatus captura seleccionado: " + estatusCaptura);
                LOG.info("estatus revision seleccionado: " + estatusRevision);
                
                arqComponenteSel.getPreciosPromocionDTO().setEstatusCaptura(estatusCaptura);
                arqComponenteSel.getPreciosPromocionDTO().setEstatusRevision(estatusRevision);
                
                /*
                double dPrecioVenta = arqComponenteSel.getPreciosPromocionDTO().getPrecioVenta();

                if (arqComponenteSel.getPrecioEdit() != arqComponenteSel.getPreciosPromocionDTO().getPrecio()) {
                    arqComponenteSel.getPreciosPromocionDTO().setPorcentaje(((dPrecioVenta - arqComponenteSel.getPrecioEdit()) / dPrecioVenta) * 100);
                    arqComponenteSel.getPreciosPromocionDTO().setAhorroFijo(dPrecioVenta - arqComponenteSel.getPrecioEdit());
                    arqComponenteSel.getPreciosPromocionDTO().setPrecio(arqComponenteSel.getPrecioEdit());
                    arqComponenteSel.getPreciosPromocionDTO().setRecuperacionPorcentaje((arqComponenteSel.getRecuperacionEdit() / arqComponenteSel.getPreciosPromocionDTO().getAhorroFijo()) * 100);
                }
                */

                List<PreciosPromocionDTO> lPrecPromo = new ArrayList<>();
                lPrecPromo.add(arqComponenteSel.getPreciosPromocionDTO());
                serviceArquitecturaSeven.savePrecios(lPrecPromo);
                Messages.mensajeSatisfactorio(
                                "El precio se edit? exitosamente",
                                "El precio se edit? exitosamente");

                if(estatusRevision == 4){
                    precioRechazado();
                }
                
                this.preparePreciosView();
                
                
                
                /*
                List<Integer> lZonasAux = new ArrayList<>();
                if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
                                && (lZonasPrec == null || lZonasPrec.isEmpty())) {
                        for (String z : lZonaXGrupo) {
                                lZonasAux.add(Integer.valueOf(z));
                        }
                } else {
                        if (lZonasPrec != null) {
                                for (String z : lZonasPrec) {
                                        lZonasAux.add(Integer.valueOf(z));
                                }
                        }
                }

                if (lZonasAux.isEmpty()) {
                    for (String z : arqMecanica.getlZonas()) {
                            lZonasAux.add(Integer.valueOf(z));
                    }
                }

                obtenerPrecios(idMecanicaPrec, idCatPrec, lZonasAux,
                                    idStatusRevPrec, idStatusCapPrec);
                */
            }
        } catch (Exception e) {
                LOG.error(e);
                Messages.mensajeAlerta(
                                "Ocurrio un error al editar el precio promoci?n",
                                "Ocurrio un error al editar el precio promoci?n");
        }
	}
	
	public List<PreciosPromocionDTO> getTreeComponentes(int treeLvl){
		List<PreciosPromocionDTO> lComp = new ArrayList<>();
		boolean cond = false;
		for(TreeNode treeRoot : raizDisenio.getChildren())
		{
    		for(TreeNode treeMecanica : treeRoot.getChildren())
    		{
    			for(TreeNode treeGpoZona : treeMecanica.getChildren())
        		{
    				if(treeLvl == Constants.LVL_GPO_ZONA){
    					ArqComponente gpoZona = (ArqComponente) treeGpoZona.getData();
						cond = arqComponenteSel.getGpoZona().equals(gpoZona.getGpoZona());
					}
					if(cond || treeLvl == Constants.LVL_ZONA){
	    				for(TreeNode treeZona : treeGpoZona.getChildren())
	            		{
	    					if(treeLvl == Constants.LVL_ZONA){
	    						ArqComponente zona = (ArqComponente) treeZona.getData();
	    						cond = arqComponenteSel.getZona().equals(zona.getZona());
	    					}
	    					if(cond || treeLvl == Constants.LVL_GPO_ZONA){
	    						for(TreeNode treeComp : treeZona.getChildren())
	                    		{
	            					ArqComponente comp = (ArqComponente) treeComp.getData();
	            					comp.getPreciosPromocionDTO().setEstatusDiseno(arqComponenteSel.getPreciosPromocionDTO().getEstatusDiseno());
	            					lComp.add(comp.getPreciosPromocionDTO());
	                    		}
	    					}
	            		}
					}
        		}
    		}
		}
		return lComp;
	}

    public void updateEstatusDiseno(){
        String[] emailTo = {"jorged@adinfi.com","sandram@adinfi.com"};
        String msj = "<br />Mecanica: " + arqMecanica.nombre
                + "<br />Programa Actualizado: "+arqComponenteSel.getPrograma() + "<br />Estatus Precio: " + arqComponenteSel.getStrEstatusPrecio()
                + "<br />Estatus Dise?o: " + arqComponenteSel.getStrEstatusDisenio()
                + "<br /><br /><br />"
                + "Modificado por: " + usuarioLogeado.getNombre() + " " + usuarioLogeado.getPlastName() + " - " + usuarioLogeado.getEmail();
        try {
        	List<PreciosPromocionDTO> lCompDiseno;
        	switch(arqComponenteSel.getTreeNivel())
        	{
        	case Constants.LVL_ROOT:
                //serviceArquitecturaSeven.saveComponentes(arqComponenteSel.getDto(), 0,arqMecanica.getIdMecanica());
                //SendMail.sendGenericEmail(emailTo, "Prueba Guardar Estatus en Arquitectura", msj);
                Messages.mensajeSatisfactorio("Confirmacion", "Informacion Actualizada");
            	serviceArquitecturaSeven.updateStatusDisenoByMecanica(arqMecanica.getIdMecanica(), arqComponenteSel.getPreciosPromocionDTO().getEstatusDiseno());
            	setEstatusDisenoGlobal(arqMecanica.getIdMecanica());
    			break;
        	case Constants.LVL_MECANICA:
        		lCompDiseno = new ArrayList<>();
        		for(ArqComponente comp : lArqPreciosPrint)
        		{
        			comp.getPreciosPromocionDTO().setEstatusDiseno(arqComponenteSel.getPreciosPromocionDTO().getEstatusDiseno());
        			lCompDiseno.add(comp.getPreciosPromocionDTO());
        		}
        		serviceArquitecturaSeven.savePrecios(lCompDiseno);
        		break;
        	case Constants.LVL_GPO_ZONA:
        		serviceArquitecturaSeven.savePrecios(getTreeComponentes(Constants.LVL_GPO_ZONA));
    			break;
        	case Constants.LVL_ZONA:
        		serviceArquitecturaSeven.savePrecios(getTreeComponentes(Constants.LVL_ZONA));
    			break;
        	case Constants.LVL_COMPONENTE:
        		List<PreciosPromocionDTO> lPrecPromo = new ArrayList<>();
                lPrecPromo.add(arqComponenteSel.getPreciosPromocionDTO());
                serviceArquitecturaSeven.savePrecios(lPrecPromo);
    			break;
        	}
            getEstatusDisenoGlobal();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MBArquitectura.class.getName()).log(Level.SEVERE, null, ex);
            Messages.mensajeErroneo("Exception", ex.getMessage());
        }
    }    
    
    
    public void testStatus(){
		LOG.info(this.idStatusDisenio);
		LOG.info(this.idStatusDisenio);
	}
    
    public void testDescripcion(){
        LOG.info("----------------------------------------------------------");
        LOG.info(this.valueDesignDescription);
    }
        
    @Deprecated
    public void guardarDisenio() {
            arqComponenteSel.getIdEstatusPrecio();
            arqComponenteSel.getPreciosPromocionDTO().getEstatusDiseno();
            Messages.mensajeSatisfactorio("El Dise?o se edit? exitosamente",
                            "El Dise?o se edit? exitosamente");
            List<Integer> lZonasAux = new ArrayList<>();
            if (lGrupoZonasPrec != null && !lGrupoZonasPrec.isEmpty()
                            && (lZonasPrec == null || lZonasPrec.isEmpty())) {
                    for (String z : lZonaXGrupo) {
                            lZonasAux.add(Integer.valueOf(z));
                    }
            } else {
                    if (lZonasPrec != null) {
                            for (String z : lZonasPrec) {
                                    lZonasAux.add(Integer.valueOf(z));
                            }
                    }
            }

            if (lZonasAux.isEmpty()) {
                lZonasAux = new ArrayList<>();
                for (String z : arqMecanica.getlZonas()) {
                        lZonasAux.add(Integer.valueOf(z));
                }
            }
            obtenerDisenio(idMecanicaPrec, idCatPrec, lZonasAux, idStatusPrecio,
                            idStatusDisenio, null);
    }

	public void regresarCapturaPromo() {
        this.prepareCategoryView();
		agregarPromocion = true;
		precio = false;
		precioPromocion = false;
		disenio = false;
		estrategia = false;
	}

	/************************************* Validadores *************************************/

	private boolean puedeAgregarComponente() {
		if (arqMecanica.getIdMecanica() < 1) {
			return false;
		}

		if (arqMecanica.getIdPromocion() < 1) {
			return false;
		}

		if (arqMecanica.getIdTipoPromocion() < 1) {
			return false;
		}

		if (arqMecanica.getlSenalamiento() == null
				|| arqMecanica.getlSenalamiento().size() == 0) {
			return false;
		}

		if (arqMecanica.getlSenalamiento().contains("0")
				&& (arqMecanica.otroSenalamiento == null || arqMecanica.otroSenalamiento
						.trim().isEmpty())) {
			return false;
		}

		if (arqMecanica.getlGrupoZonas() == null
				|| arqMecanica.getlGrupoZonas().size() == 0) {
			return false;
		}

		if (arqMecanica.getlZonas() == null
				|| arqMecanica.getlZonas().size() == 0) {
			return false;
		}

		if (arqMecanica.getlTiendas() == null
				|| arqMecanica.getlTiendas().size() == 0) {
			return false;
		}

		return true;
	}

	private boolean validatePrecioPromo() {
        List<String> messages = new ArrayList<String>(); 
        
        if(componentePrecioPromocionSeleccionado <= 0){
            messages.add("Por Favor seleccione un articulo o variedad para continuar");
        }
        
        Integer zonaSeleccionadas = this.ppCatZonesSelected.size();
        if(zonaSeleccionadas <= 0){
            messages.add("Por Favor seleccione una Zona para continuar");
        }
        
        /*
		if (precPromo == 0 && porcPromo == 0 && ahorroFijo == 0) {
            messages.add("Por Favor ingese al menos uno de los siguientes campos: Precio Promoci?n, % Promoci?n o Ahorro fijo.");
		}
        */
        if ( (precPromo > 0 && porcPromo > 0) || (ahorroFijo > 0 && precPromo > 0) || (ahorroFijo > 0 && porcPromo > 0) || (ahorroFijo > 0 && porcPromo > 0 && precPromo > 0)) {
            messages.add("Por Favor ingrese solamente uno de los 3 campos: (Precio Promoci?n, % Promoci?n o Ahorro Fijo).");
		}
        
        if(this.porcentajeDescuentoStr != null && this.porcentajeDescuentoStr.length() > 0){
            try{
                Double number = Double.parseDouble(porcentajeDescuentoStr);
                if(number < 0 || number > 100){
                   messages.add("Por Favor ingrese un porcentaje de descuento correcto: (Valores entre 0 y 100)."); 
                }else{
                    this.porcentajeDescuento = number;
                }
            }catch(Exception ex){
                messages.add("El valor de porcentaje de descuento no es un numero. Verifique.");
            }
        }
        
        if(this.porcentajeRetencionStr != null && this.porcentajeRetencionStr.length() > 0){
            try{
                Double number = Double.parseDouble(porcentajeRetencionStr);
                if(number <= 0 || number > 100){
                   messages.add("Por Favor ingrese un porcentaje de Redencion correcto: (Valores entre 0 y 100)."); 
                }else{
                    this.porcentajeRetencion = number;
                }
            }catch(Exception ex){
                messages.add("El valor de porcentaje de Redencion no es un numero. Verifique.");
            }
        }
        
        if(this.elasticidadStr != null && this.elasticidadStr.length() > 0){
            try{
                Double number = Double.parseDouble(elasticidadStr);
                if(number < -100 || number > 0){
                   messages.add("Por Favor ingrese un valor de elasticidad correcto: (Valores entre -0 y -100)."); 
                }else{
                    this.elasticidad = number;
                }
            }catch(Exception ex){
                messages.add("El valor de elasticidad no es un numero. Verifique.");
            }
        }
        
        /*
		if (recuProveCant == 0 && recuProvePorc == 0) {
            messages.add("Por Favor ingese la recuperaci?n del proveedor, en cantidad o en porcentaje.");
		}
        */
        
        if(recuProveCant > 0 && recuProvePorc > 0 ){
            messages.add("Capture solamente uno de los dos valores. ($ Recuperaci?n proveedor ? % Recuperaci?n Proveedor)");
        }
        
        
        if (cantidadDistribucionRebaja == 0 && porcentajeDistribucionRebaja == 0) {
            messages.add("Por Favor ingese la districuci?n de Rebaja, en cantidad o en porcentaje.");
		}
        
        
        if(cantidadDistribucionRebaja > 0 && porcentajeDistribucionRebaja > 0 ){
            messages.add("Capture solamente uno de los dos valores. ($ Distribuci?n de Rebaja ? % Distribuci?n de Rebaja)");
        }
        
        /*
        if(objetivo ==  0 ){
            messages.add("Por favor ingrese el objetivo.");
        }
        */
        
        for(String m : messages){
            Messages.mensajeErroneo(m,m);
        }
        
		return messages.size() == 0;
	}

	private boolean validatePrecioPromoEdicion() {
		if (arqComponenteSel.getPrecioEdit() == 0
				&& arqComponenteSel.getPorcentajeEdit() == 0
				&& arqComponenteSel.getAhorroFijoEdit() == 0) {
			Messages.mensajeAlerta(
					"Falta informaci?n por capturar, revise e int?ntelo de nuevo.",
					"Falta informaci?n por capturar, revise e int?ntelo de nuevo.");
			return false;
		}

		if (arqComponenteSel.getRecuperacionEdit() == 0
				&& arqComponenteSel.getRecuperacionPorcentajeEdit() == 0) {
			Messages.mensajeAlerta(
					"Falta informaci?n por capturar, revise e int?ntelo de nuevo.",
					"Falta informaci?n por capturar, revise e int?ntelo de nuevo.");
			return false;
		}
		return true;
	}

	private boolean validateMecanica() {
		if (arqMecanica == null) {
			Messages.mensajeAlerta(
					"Falta Informaci?n por capturar en la Mec?nica",
					"Falta Informaci?n por capturar en la Mec?nica");
			return false;
		}

		if (arqPeriodo.getIdPrograma() < 1) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Programa"),
					msgInfoFaltante.replace("@CAMPO@", "Programa"));
			return false;
		}

		if (arqPeriodo.getlCategorias() == null
				|| arqPeriodo.getlCategorias().size() == 0) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Categor?a"),
					msgInfoFaltante.replace("@CAMPO@", "Categor?a"));
			return false;
		}

		if (arqPeriodo.getIngresoReal() == 0.0) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Ingreso R11 POP Real"),
					msgInfoFaltante.replace("@CAMPO@", "Ingreso R11 POP Real"));
            arqPeriodo.setPopRealErrorClass("border:solid red 1px!Important;");
			return false;
		}else{
            arqPeriodo.setPopRealErrorClass("");
        }

		if (arqMecanica.getIdMecanica() < 1) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Mec?nica"),
					msgInfoFaltante.replace("@CAMPO@", "Mec?nica"));
			return false;
		}

		if (arqMecanica.getIdPromocion() < 1) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Promoci?n"),
					msgInfoFaltante.replace("@CAMPO@", "Promoci?n"));
			return false;
		}

		if (arqMecanica.getIdTipoPromocion() < 1) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Tipo de Promoci?n"),
					msgInfoFaltante.replace("@CAMPO@", "Tipo de Promoci?n"));
			return false;
		}

		if (arqMecanica.getlSenalamiento() == null
				|| arqMecanica.getlSenalamiento().size() == 0) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Se?alamiento"),
					msgInfoFaltante.replace("@CAMPO@", "Se?alamiento"));
			return false;
		}

		if (arqMecanica.getlSenalamiento().contains("0")
				&& (arqMecanica.otroSenalamiento == null || arqMecanica.otroSenalamiento
						.trim().isEmpty())) {
			Messages.mensajeAlerta(
					"Si selecciona la opci?n Otros, debe capturar un nombre",
					"Si selecciona la opci?n Otros, debe capturar un nombre");
			return false;
		}

		if (arqMecanica.getlGrupoZonas() == null
				|| arqMecanica.getlGrupoZonas().size() == 0) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Grupo Zonas"),
					msgInfoFaltante.replace("@CAMPO@", "Grupo Zonas"));
			return false;
		}

		if (arqMecanica.getlZonas() == null
				|| arqMecanica.getlZonas().size() == 0) {
			Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "Zona"),
					msgInfoFaltante.replace("@CAMPO@", "Zona"));
			return false;
		}

		if (arqMecanica.getlTiendas() == null
				|| arqMecanica.getlTiendas().size() == 0) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Tienda"),
					msgInfoFaltante.replace("@CAMPO@", "Tienda"));
			return false;
		}

		return true;
	}


	
	private boolean validateComponentesLista() {
        List<String> m = new ArrayList<String>();
		if (arqComponente == null) {
			Messages.mensajeAlerta("Falta Informaci?n por capturar en el Componente","Falta Informaci?n por capturar en la Componente");
			return false;
		}

		if (arqComponente.getNumeroComponente() < 1) {
			//Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "Componente"),msgInfoFaltante.replace("@CAMPO@", "Componente"));
			//return false;
            m.add(msgInfoFaltante.replace("@CAMPO@", "Componente"));
		}
        if(arqComponente.getIdLista() > 0){
            LOG.info("validando por familia");
        }else if(arqComponente.getIdCategoria() > 0){
            LOG.info("validando por categoria");
        }else if(arqComponente.getIdProveedor() > 0){
            LOG.info("validando por proveedor");
        }else{
            LOG.info("no se que rollo");
        }
          
        if(arqComponente.getIdLista() > 0){
            if (arqComponente.getlSkuFamilia() == null || arqComponente.getlSkuFamilia().size() == 0) {
                LOG.info("familia seleccionada nula o cero");
                //Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "SKU"), msgInfoFaltante.replace("@CAMPO@", "SKU"));
                //return false;
                m.add(msgInfoFaltante.replace("@CAMPO@", "SKU"));
            }else{
                LOG.info("agregando listado de familia a listado original");
                arqComponente.setlSku(arqComponente.getlSkuFamilia());
            }
        }else{
            if (arqComponente.getlSku() == null || arqComponente.getlSku().size() == 0) {
                //Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "SKU"), msgInfoFaltante.replace("@CAMPO@", "SKU"));
                //return false;
                m.add(msgInfoFaltante.replace("@CAMPO@", "SKU"));
            }
        }

		if (lUpcComp == null || lUpcComp.size() == 0) {
			//Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "UPC"),msgInfoFaltante.replace("@CAMPO@", "UPC"));
			//return false;
            m.add(msgInfoFaltante.replace("@CAMPO@", "UPC"));
		}

		if (arqComponente.getCantProductos() == 0) {
			//Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "Cantidad de productos"),msgInfoFaltante.replace("@CAMPO@", "Cantidad de productos"));
			//return false;
            m.add(msgInfoFaltante.replace("@CAMPO@", "Cantidad de productos"));
		}
        
        if(m.size() > 0){
            for(String s : m){
                Messages.mensajeAlerta(s,s);
            }
            return false;
        }else{
            return true;
        }        
	}

	private boolean validateComponente() {
        LOG.info("Validando componente");
		List<String> m = new ArrayList<String>();
		
        if (arqComponente == null) {
			Messages.mensajeAlerta("Falta Informaci?n por capturar en el Componente","Falta Informaci?n por capturar en la Componente");
			return false;
		}

		if (arqComponente.getNumeroComponente() < 1) {
			//Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "Componente"),msgInfoFaltante.replace("@CAMPO@", "Componente"));
			//return false;
            m.add(msgInfoFaltante.replace("@CAMPO@", "Componente"));
		}
        
        if (arqComponente.getIdEspPromocional() < 1) {
			//Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "Componente"),msgInfoFaltante.replace("@CAMPO@", "Componente"));
			//return false;
            m.add(msgInfoFaltante.replace("@CAMPO@", "Espacio Promocional"));
            this.espacioPromocionalErrorClass = "border:solid red 1px!Important;";
		}else{
             this.espacioPromocionalErrorClass = "";
        }
        
        if(arqComponente.getIdCategoria() > 0 || arqComponente.getIdProveedor() > 0){
            
            LOG.info("filtro por categoria o proveedor, validando");
            if (arqComponente.getlSku() == null || arqComponente.getlSku().size() == 0) {
                //Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "SKU"), msgInfoFaltante.replace("@CAMPO@", "SKU"));
                //return false;
                m.add(msgInfoFaltante.replace("@CAMPO@", "SKU"));
            } 
        }
        
        if(arqComponente.getIdLista() > 0){
            
            LOG.info("validando por familia: (validateComponente)");
            
            if (arqComponente.getlSkuFamilia() == null || arqComponente.getlSkuFamilia().size() == 0) {
                //Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "SKU"), msgInfoFaltante.replace("@CAMPO@", "SKU"));
                //return false;
                m.add(msgInfoFaltante.replace("@CAMPO@", "SKU"));
            } 
            else{
                arqComponente.setlSku(arqComponente.getlSkuFamilia());
            }
        }
        
        if (lUpcComp == null || lUpcComp.size() == 0) {
                    //Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "UPC"),msgInfoFaltante.replace("@CAMPO@", "UPC"));
                    //return false;
                m.add(msgInfoFaltante.replace("@CAMPO@", "UPC"));
            }

		

		if (arqComponente.getCantProductos() == 0) {
			//Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@", "Cantidad de productos"),msgInfoFaltante.replace("@CAMPO@", "Cantidad de productos"));
			//return false;
            m.add(msgInfoFaltante.replace("@CAMPO@", "Cantidad de productos"));
		}
        
        if(m.size() > 0){
            for(String s : m){
                Messages.mensajeAlerta(s,s);
            }
            return false;
        }else{
            return true;
        } 
	}

	private boolean validatePrimicia() {
		if (numCompPrimicia < 1) {
			Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@",
					"N?mero de componente en primicia"), msgInfoFaltante
					.replace("@CAMPO@", "N?mero de componente en primicia"));
			return false;
		}

		if (descPrimicia == null || descPrimicia.isEmpty()) {
			Messages.mensajeAlerta(msgInfoFaltante.replace("@CAMPO@",
					"Descripcion de primicia"), msgInfoFaltante.replace(
					"@CAMPO@", "Descripcion de primicia"));
			return false;
		}

		if (categPrimicia < 1) {
			Messages.mensajeAlerta(
					msgInfoFaltante.replace("@CAMPO@", "Categor?a en primicia"),
					msgInfoFaltante.replace("@CAMPO@", "Categor?a en primicia"));
			return false;
		}
		return true;
	}

    public void valueChangeVerDisenoPrograma(){
        int programaId = verDisenoProgramaId;

        List<TblMecanica> lTblMecanica = serviceArquitecturaSeven.getAllMecanica(null, programaId, null, null);
        if (lTblMecanica != null) {
            lMecanicaVerDiseno = new ArrayList<>();
            for (TblMecanica tblMecanica : lTblMecanica) {
                lMecanicaVerDiseno.add(new SelectItem(tblMecanica.getMecanicaId(), tblMecanica.getNombreMecanica()));
            }
        }
        verDisenoIdMecanica = 0;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("loader.hide()");
    }
        
	// TODO: Ver Dise?o
	/**
	 * @author Carlos F?lix 03/12/2014
         * @param e
	 */
	public void valueChangeVerDisenoPrograma(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			int programaId = (Integer) e.getNewValue();

			List<TblMecanica> lTblMecanica = serviceArquitecturaSeven
					.getAllMecanica(null, programaId, null, null);
			if (lTblMecanica != null) {
				lMecanicaVerDiseno = new ArrayList<>();
				for (TblMecanica tblMecanica : lTblMecanica) {
					lMecanicaVerDiseno.add(new SelectItem(tblMecanica
							.getMecanicaId(), tblMecanica.getNombreMecanica()));
				}
			}
			verDisenoIdMecanica = 0;
		}
		RequestContext requestContext = RequestContext.getCurrentInstance();  
		requestContext.execute("loader.hide()");
	}

	@SuppressWarnings("unchecked")
	public void valueChangeVerDisenoGrupoZona(ValueChangeEvent e) {
		List<String> lgz;
		if (e.getNewValue() != null) {
			lgz = (List<String>) e.getNewValue();
			mCatZonasVerDiseno = new ArrayList<>();
			if (lgz != null) {
				List<Integer> ids = new ArrayList<>();
                for (String gz : lgz) {
    				ids.add(Integer.valueOf(gz));
    			}
                mCatZonasVerDiseno = serviceCatZone.getCatZonesByGrupoZonas(ids);
			}
			verDisenoZonas = new ArrayList<>();
			verDisenoTiendas = new ArrayList<>();
			mCatTiendasVerDiseno = new ArrayList<>();
			RequestContext requestContext = RequestContext.getCurrentInstance();  
                      requestContext.execute("loader.hide()");
		}
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeVerDisenoGrupoZona1(ValueChangeEvent e) {
		if (e.getNewValue() != null) {
			List<String> lgz = (List<String>) e.getNewValue();
			mZonasVerDiseno = new HashMap<String, String>();
			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			for (String gz : lgz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_GRUPO);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			mZonasVerDiseno = MBUtil.cargarcombos(Constants.CAT_ZONA,
					insertSearch, serviceDynamicCatalogs,
					Constants.ATT_DESCRIPTION, Constants.ATT_ID);
			verDisenoZonas = new ArrayList<String>();
			verDisenoTiendas = new ArrayList<String>();
			mTiendasVerDiseno = new HashMap<String, String>();
		}
	}

	private void senalDialogVer (){
        mCatSignals = new ArrayList<>();
        for (String s : arqMecanica.getlSenalamiento()){
            CatSenal senal;
            if (s.equals("0")){
                senal = new CatSenal();
                senal.setIdSenal(0);
                senal.setNombre("Otro");
            }else{
                senal = serviceCatSenal.getCatSenal(Integer.valueOf(s));
            }
            mCatSignals.add(senal);
        }
    }

	public void verDiseno() {
        if (nodoSeleccionado != null){
            LOG.info("nodo seleccionado no es nulo");
            int programaId = nodoSeleccionado.getTblMecanica().getProgramaId();
            int mecanicaId = nodoSeleccionado.getTblMecanica().getMecanicaId();
            LOG.info("programa: " + programaId);
            LOG.info("mecanica: " + mecanicaId);
            verDisenoProgramaId = programaId;
            verDisenoIdMecanica = mecanicaId;
            
            /* se genera el objeto de mecanica con los catalogos */
            arqMecanica.setIdMecanica(nodoSeleccionado.getTblMecanica().getMecanicaId());
            arqMecanica.setIdPrograma(nodoSeleccionado.getTblMecanica().getProgramaId());
            obtenerMecanica(nodoSeleccionado.getTblMecanica().getMecanicaId());
            
            /* se carga el listado de mecanicas asociadas al folio*/
            List<TblMecanica> lTblMecanicas = serviceArquitecturaSeven.getAllMecanica(
                Long.valueOf(nodoSeleccionado.getTblCampanaProgramas().getId().getIdCampana()).intValue(),
                nodoSeleccionado.getTblCampanaProgramas().getId().getIdPrograma());
            if (lTblMecanicas != null) {
                lMecanica = new ArrayList<>();
                for (TblMecanica tblMecanica : lTblMecanicas) {
                    LOG.info("agregando mecanica id: " + tblMecanica.getMecanicaId() + ", con nombre: " + tblMecanica.getNombreMecanica());
                    lMecanica.add(new SelectItem(tblMecanica.getMecanicaId(), tblMecanica.getNombreMecanica()));
                }
            }
           
            /*cargando senalamientos*/
            //mCatSignals = MBUtil.cargarcomboSenal(serviceCatSenal);

            senalDialogVer();
            
            verDisenoLSenalamiento = arqMecanica.getlSenalamiento();
            verDisenoGrupoZonas = arqMecanica.getlGrupoZonas();
            verDisenoZonas = arqMecanica.getlZonas();
            verDisenoTiendas = arqMecanica.getlTiendas();
            
        }else{
            LOG.info("nodo seleccionado nulo");
            verDisenoProgramaId = -1;
            verDisenoIdMecanica = -1;
            verDisenoGrupoZonas = new ArrayList<String>();
            verDisenoZonas = new ArrayList<String>();
            verDisenoTiendas = new ArrayList<String>();
        }

		lMecanicaVerDiseno = new ArrayList<SelectItem>();
		mCatZonasVerDiseno = new ArrayList<CatZone>();
		mCatTiendasVerDiseno = new ArrayList<CatStore>();
		disenosLst = new ArrayList<DisenosDTO>();
		verDisenoAutorizado = -1;
		verDisenoComentarios = "";

		//verDisenoSearch();
	}

	public void valueChangeAdjDisenoPrograma(ValueChangeEvent e) {
		verDisenoIdMecanica = 0;
		lMecanicaVerDiseno = new ArrayList<SelectItem>();
		Integer programaId = new Integer(0);
		if (e.getNewValue() != null) {
			programaId = (Integer) e.getNewValue();
			List<TblMecanica> lTblMecanica = serviceArquitecturaSeven
					.getAllMecanica(null, programaId, null, null);
			if (lTblMecanica != null) {
				for (TblMecanica tblMecanica : lTblMecanica) {
					lMecanicaVerDiseno.add(new SelectItem(tblMecanica
							.getMecanicaId(), tblMecanica.getNombreMecanica()));
				}
			}
		}
		getDisenoDTO().setProgramaId(programaId);
	}
	
	
	public void setEstatusDisenoGlobal(Integer idMecanica){
		  try {
				List<DisenosDTO> listaDisenos = serviceArquitecturaSeven.getDisenosByIdMecanica(idMecanica);
				if(listaDisenos.isEmpty() == false){
					
					DisenosDTO diseno  = listaDisenos.get(0);
					this.labelDisenoGlobal = getLabelEstatusDisenioByid(diseno.getPreciosAuth());
					this.idStatusDisenoGlobal = diseno.getPreciosAuth();
		            this.titleDise?oGlobal = getTitleEstatusDisenioByid(diseno.getPreciosAuth());

				}else{
					
			        this.labelDisenoGlobal = getLabelEstatusDisenioByid(1);
		            this.idStatusDisenoGlobal = 1;
		            this.titleDise?oGlobal = getTitleEstatusDisenioByid(1);
					
				}
			} catch (Exception e) {
				LOG.info(e);
			}
	}
	
	public String getLabelEstatusDisenioByid(Integer id) {
		String labelEstatusDisenio = "";
			switch (id) {
			case 1:
				labelEstatusDisenio = "#ffff00";
				break;
			case 2:
				labelEstatusDisenio = "#604a7b";
				break;
			case 3:
				labelEstatusDisenio = "#984807";
				break;
			case 4:
				labelEstatusDisenio = "#f57913";
				break;
			case 5:
				labelEstatusDisenio = "#5fd543";
				break;
			case 6:
				labelEstatusDisenio = "#2a7119";
				break;
			case 7:
				labelEstatusDisenio = "#ff0000";
				break;
			}
		return labelEstatusDisenio;
	}
	
	
	public String getTitleEstatusDisenioByid(Integer id) {
		String title = "";
			switch (id) {
			case 1:
				title = "Pendiente";
				break;
			case 2:
				title = "En dise?o";
				break;
			case 3:
				title = "Revisi?n";
				break;
			case 4:
				title = "Rechazado";
				break;
			case 5:
				title = "Aprobado";
				break;
			case 6:
				title = "Enviado a Impresi?n";
				break;
			case 7:
				title = "Cancelado";
				break;
			}
		return title;
	}
	
	
	

	public void valueChangeAdjDisenoSenalamiento(ValueChangeEvent e) {
		Integer senalamiento = null;
		if (e.getNewValue() != null) {
			senalamiento = (Integer) e.getNewValue();
		}
	//	getDisenoDTO().setSenalamientoId(senalamiento);
	}

	public void valueChangeAdjDisenoMecanica(ValueChangeEvent e) {
		Integer mecanicaId = 0;
		if (e.getNewValue() != null) {
			mecanicaId = (Integer) e.getNewValue();
		}
		getDisenoDTO().setPkMec(mecanicaId);
	}

	@SuppressWarnings("unchecked")
	public void valueChangeAdjDisenoTiendas(ValueChangeEvent e) {
		List<String> lz = null;
		if (e.getNewValue() != null) {
			lz = (List<String>) e.getNewValue();
		}
		getDisenoDTO().setTiendas(lz);
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeAdjDisenoGrupoZona1(ValueChangeEvent e) {
		mZonasVerDiseno = new HashMap<>();
		verDisenoZonas = new ArrayList<>();
		mTiendasVerDiseno = new HashMap<>();
		verDisenoTiendas = new ArrayList<>();
		List<String> lgz = null;
		if (e.getNewValue() != null) {
			lgz = (List<String>) e.getNewValue();
			List<AttrSearch> insertSearch = new ArrayList<>();
			for (String gz : lgz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_GRUPO);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			mZonasVerDiseno = MBUtil.cargarcombos(Constants.CAT_ZONA,
					insertSearch, serviceDynamicCatalogs,
					Constants.ATT_DESCRIPTION, Constants.ATT_ID);
		}
		getDisenoDTO().setGrupoZonas(lgz);
	}

    @SuppressWarnings("unchecked")
    public void valueChangeAdjDisenoGrupoZona(ValueChangeEvent e) {
            verDisenoZonas = new ArrayList<>();
            mCatTiendasVerDiseno = new ArrayList<>();
            verDisenoTiendas = new ArrayList<>();

            getDisenoDTO().setZonas(new ArrayList<String>());
            getDisenoDTO().setTiendas(new ArrayList<String>());

            List<CatZone> listaZonas = new ArrayList<>();
            List<String> listaGruposZona = null;
            if (e.getNewValue() != null) {
                listaGruposZona = (List<String>) e.getNewValue();
                List<CatZone> catZoneList;
                if (listaGruposZona != null) {
                	List<Integer> ids = new ArrayList<>();
                    for (String gz : listaGruposZona) {
        				ids.add(Integer.valueOf(gz));
        			}
                    catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
                    if (catZoneList != null) {
                        listaZonas.addAll(catZoneList);
                    }
                }
            }
            mCatZonasVerDiseno = listaZonas;
            getDisenoDTO().setGrupoZonas(listaGruposZona);
    }

    @SuppressWarnings("unchecked")
    public void valueChangeVerDisenoZona(ValueChangeEvent e) {
        List<String> zonasSeleccionadas;
        if (e.getNewValue() != null) {
            zonasSeleccionadas = (List<String>) e.getNewValue();
            List<CatStore> catStoreList;
            List<CatStore> listaTiendas = new ArrayList<>();
            if (zonasSeleccionadas != null) {
                for (String zonaId : zonasSeleccionadas) {
//					catStoreList = MBUtil.cargarcomboStores(serviceCatZone, zonaId);
                    catStoreList = MBUtil.cargacomboStores(serviceCatStore, zonaId);
                    if (catStoreList != null) {
                            listaTiendas.addAll(catStoreList);
                    }
                }
            }
            verDisenoTiendas = new ArrayList<>();
            mCatTiendasVerDiseno = SortUtils.sort(listaTiendas, new CatStoreSorter());
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();  
        requestContext.execute("loader.hide()");
    }

    @SuppressWarnings("unchecked")
    public void valueChangeAdjDisenoZona(ValueChangeEvent e) {
        List<String> listaZonas = new ArrayList<>();
        List<CatStore> tiendasList = new ArrayList<>();

        if (e.getNewValue() != null) {
            listaZonas = (List<String>) e.getNewValue();
            List<CatStore> catStoreList;
            if (listaZonas != null) {
                for (String zona : listaZonas) {
                    //catStoreList = MBUtil.cargarcomboStores(serviceCatZone, zona);
                    catStoreList = MBUtil.cargacomboStores(serviceCatStore, zona);
                    if (catStoreList != null) {
                            tiendasList.addAll(catStoreList);
                    }
                }
            }
        }

        mCatTiendasVerDiseno = tiendasList;
        verDisenoTiendas = new ArrayList<>();

        getDisenoDTO().setZonas(listaZonas);
        getDisenoDTO().setTiendas(new ArrayList<String>());
    }
    
    
    // TODO: Revisar este codigo en duro.
    @SuppressWarnings("unchecked")
    public void estatusDisenoChanged(){
        String[] emailsTo = {"jorged@adinfi.com","sandram@adinfi.com"};
        SendMail.sendGenericEmail(emailsTo, "Cambio de Estatus AdMaster", "Cambio de Estatus a: " + idStatusDisenio);
    }

	@Deprecated
	@SuppressWarnings("unchecked")
	public void valueChangeAdjDisenoZona1(ValueChangeEvent e) {
		mTiendasVerDiseno = new HashMap<>();
		verDisenoTiendas = new ArrayList<>();
		List<String> lz = null;
		if (e.getNewValue() != null) {
			lz = (List<String>) e.getNewValue();
			List<AttrSearch> insertSearch = new ArrayList<>();
			for (String gz : lz) {
				AttrSearch attrSearch = new AttrSearch();
				attrSearch.setAttrName(Constants.ATT_ID_ZONA);
				attrSearch.setValue(gz);
				attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL_OR);
				insertSearch.add(attrSearch);
			}
			Map<String, String> mZonasAux = MBUtil.cargarcombos(
					Constants.CAT_STORE, insertSearch, serviceDynamicCatalogs,
					Constants.ATT_CODE, Constants.ATT_ID);
			mTiendasVerDiseno.putAll(mZonasAux);
		}
		getDisenoDTO().setZonas(lz);
	}

	public void adjDisenoInit() {
        
        disenoDTO = new DisenosDTO();
        
        if (nodoSeleccionado != null){
            LOG.info("nodo seleccionado no es nulo");
            int programaId = nodoSeleccionado.getTblMecanica().getProgramaId();
            int mecanicaId = nodoSeleccionado.getTblMecanica().getMecanicaId();
            LOG.info("programa: " + programaId);
            LOG.info("mecanica: " + mecanicaId);
            
            if(arqMecanica == null){
                LOG.info("objeto mecanica nulo, cargando");
                /* se genera el objeto de mecanica con los catalogos */
                arqMecanica.setIdMecanica(nodoSeleccionado.getTblMecanica().getMecanicaId());
                arqMecanica.setIdPrograma(nodoSeleccionado.getTblMecanica().getProgramaId());
                obtenerMecanica(nodoSeleccionado.getTblMecanica().getMecanicaId());
            }else{
                LOG.info("objeto mecanica no nulo");
            }
            
            /* se carga el listado de mecanicas asociadas al folio*/
            List<TblMecanica> lTblMecanicas = serviceArquitecturaSeven.getAllMecanica(
                Long.valueOf(nodoSeleccionado.getTblCampanaProgramas().getId().getIdCampana()).intValue(),
                nodoSeleccionado.getTblCampanaProgramas().getId().getIdPrograma());
            if (lTblMecanicas != null) {
                lMecanica = new ArrayList<>();
                for (TblMecanica tblMecanica : lTblMecanicas) {
                    LOG.info("agregando mecanica id: " + tblMecanica.getMecanicaId() + ", con nombre: " + tblMecanica.getNombreMecanica());
                    lMecanica.add(new SelectItem(tblMecanica.getMecanicaId(), tblMecanica.getNombreMecanica()));
                }
            }
           
            /*cargando senalamientos*/
            //mCatSignals = MBUtil.cargarcomboSenal(serviceCatSenal);

            senalDialogVer();
            
            disenoDTO.setProgramaId(programaId);
            disenoDTO.setPkMec(mecanicaId);
            disenoDTO.setGrupoZonas(arqMecanica.getlGrupoZonas());
            disenoDTO.setZonas(arqMecanica.getlZonas());
            disenoDTO.setTiendas(arqMecanica.getlTiendas());
            
            senalListSelectedStr = arqMecanica.getlSenalamiento();

        }else{
            LOG.info("nodo seleccionado nulo");
        }
        
        // cargando zonas en base a grupos de zonas
        List<CatZone> listaZonas = new ArrayList<>();            
        List<CatZone> catZoneList; 
        List<Integer> ids = new ArrayList<>();
        for (String gz : arqMecanica.getlGrupoZonas()) {
        	ids.add(Integer.valueOf(gz));
        }
        catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
        if (catZoneList != null) {
        	listaZonas.addAll(catZoneList);
        }
        mCatZonasVerDiseno = listaZonas;
        
        //cargando tiendas en base a zonas
        List<CatStore> catStoreList;
         List<CatStore> listaTiendas = new ArrayList<>();
        for (String zonaId : arqMecanica.getlZonas()) {
            catStoreList = MBUtil.cargacomboStores(serviceCatStore, zonaId);
            if (catStoreList != null) {
                    listaTiendas.addAll(catStoreList);
            }
        }
            
        mCatTiendasVerDiseno = SortUtils.sort(listaTiendas, new CatStoreSorter());
	}

    /**
     * Metodo para eliminar Dise?o desde Adjuntar Dise?o
     */
    public void eliminarDiseno(DisenosDTO disenoDTO){
        //LOG.info(disenoDTO);
        boolean success = false;
        try {
            serviceArquitecturaSeven.deleteDiseno(disenoDTO.getDisenoId());
            disenosLst.remove(disenoDTO);
            success = true;
        } catch (Exception e) {
            LOG.info(e);
        }
        if(success){
            Messages.mensajeSatisfactorio("Dise?o Eliminado","Dise?o Eliminado");
        }else{
            Messages.mensajeErroneo("Error al eliminar dise?o", "Error al eliminar dise?o");
        }
    }

    /**
	 * Viene del boton Grabar en el popup de Adjuntar Dise?o
	 */
	public void adjDiseno() {
		try {
			if (disenoDTO.getImage() == null) {
				Messages.mensajeAlerta(
						"Falta informaci?n por capturar, revise e intente de nuevo. Campo: Archivo",
						"Falta informaci?n por capturar, revise e intente de nuevo. Campo: Archivo"
						);
				return;
			}
			
			List<String> grupoZonas = disenoDTO.getGrupoZonas();
			if (grupoZonas != null) {
				disenoDTO.setGrupoZonaLst(new ArrayList<DisenosDTO.RelObj>());
				for (String valor : grupoZonas) {
					RelObj e = new RelObj();
					e.setId(Integer.parseInt(valor));
					disenoDTO.getGrupoZonaLst().add(e);
				}
			}

			List<String> zonas = disenoDTO.getZonas();
			if (zonas != null) {
				disenoDTO.setZonaLst(new ArrayList<DisenosDTO.RelObj>());
				for (String valor : zonas) {
					RelObj e = new RelObj();
					e.setId(Integer.parseInt(valor));
					disenoDTO.getZonaLst().add(e);
				}
			}

			List<String> tiendas = disenoDTO.getTiendas();
			if (tiendas != null) {
				disenoDTO.setStoreLst(new ArrayList<DisenosDTO.RelObj>());
				for (String valor : tiendas) {
					RelObj e = new RelObj();
					e.setId(Integer.parseInt(valor));
					disenoDTO.getStoreLst().add(e);
				}
			}


            fillSelectedSenals();

            disenoDTO.setSenalList(new ArrayList<RelDisenoSenal>());
            for (CatSenal cs : senalListSelected){
                RelDisenoSenal rel = new RelDisenoSenal();
                rel.setCatSenal(cs);
                disenoDTO.getSenalList().add(rel);
            }



			serviceArquitecturaSeven.saveDiseno(Arrays.asList(disenoDTO),
					rolUsuario, verDisenoAutorizado, verDisenoComentarios);
			
			disenoDTO = new DisenosDTO();
            hasDesigns = true;
            Messages.mensajeSatisfactorio("Dise?o Guardado","Dise?o Guardado");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Realiza la busqueda de dise?os en el di?logo de adjuntar dise?os y
	 * despliega los resultados en su dataGrid.
	 */
	public void adjDisenoSearch() {
		try {
            fillSelectedSenals();
			disenosLst = serviceArquitecturaSeven.searchDisenos(
					disenoDTO.getProgramaId(), disenoDTO.getPkMec(),
					senalListSelected, disenoDTO.getGrupoZonas(),
					disenoDTO.getZonas(), disenoDTO.getTiendas(), null, null);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

    private void fillSelectedSenals (){
        senalListSelected = new ArrayList<>();
        List<String> senals;
        if (senalListSelectedStr != null){
            senals = senalListSelectedStr;
        }else if (verDisenoLSenalamiento != null){
            senals = verDisenoLSenalamiento;
        }else{
            senals = new ArrayList<>();
        }
        for (String s : senals){
            for (CatSenal cs : mCatSignals){
                if (cs.getIdSenal() == Integer.valueOf(s)){
                    senalListSelected.add(cs);
                    break;
                }
            }
        }
    }

	public void verDisenoSearch() {
		try {
            fillSelectedSenals();
            if (senalListSelected == null || senalListSelected.isEmpty()){
                senalListSelected = new ArrayList<>();
                if (verDisenoIdSenalamiento > 0){
                    senalListSelected.add(new CatSenal(verDisenoIdSenalamiento));
                }
            }
            Long time = System.currentTimeMillis();
            disenosLst = serviceArquitecturaSeven.searchDisenos(
					verDisenoProgramaId, verDisenoIdMecanica,
					senalListSelected, verDisenoGrupoZonas,
					verDisenoZonas, verDisenoTiendas, null, null);
            System.out.println("verDisenoSearch: "+(System.currentTimeMillis()-time));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void verDisenoGuardar() {
		try {
            LOG.info("ejecutando metodo guardar disenio");
            List<DisenosDTO> selected = new ArrayList<>();
            for(DisenosDTO diseno : disenosLst){
                if(diseno.isSelected()){
                    selected.add(diseno);
                }
            }
            
            LOG.info("actualizando disenios, total seleccionados: " + selected.size());
            LOG.info("valor de ver disenio autorizado : " + verDisenoAutorizado);
            LOG.info("comentarios de guardar disenio: " + verDisenoComentarios);
			serviceArquitecturaSeven.updateDiseno(selected, rolUsuario, verDisenoAutorizado, verDisenoComentarios);
            LOG.info("diseno actualizado");
            
            
            if (verDisenoAutorizado != null && verDisenoAutorizado.intValue() == 0){
                LOG.info("diseno rechazado");
                disenoRechazado();
                LOG.info("fin de proceso de diseno rechazado");
            }else{
                LOG.info("inicio de validacion de estatus");
                checkDisenoEstatus();
                LOG.info("fin de validacion de estatus");
            }
            
            LOG.info("fin de actualizacion");
            

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void fileUpload(FileUploadEvent event) {
		try {
			UploadedFile file = event.getFile();
			byte[] byteFile = file.getContents();
			disenoDTO.setImage(byteFile);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/* ************************************ Accesores ************************************ */

	public TreeNode getRaiz() {
		return raiz;
	}

	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public ServiceArquitecturaSeven getServiceArquitecturaSeven() {
		return serviceArquitecturaSeven;
	}

	public void setServiceArquitecturaSeven(
			ServiceArquitecturaSeven serviceArquitecturaSeven) {
		this.serviceArquitecturaSeven = serviceArquitecturaSeven;
	}

	public String getPageWork() {
		return pageWork;
	}

	public void setPageWork(String pageWork) {
		this.pageWork = pageWork;
	}

	public List<DetalleComponenteZona> getlDetCompZona() {
		return lDetCompZona;
	}

	public void setlDetCompZona(List<DetalleComponenteZona> lDetCompZona) {
		this.lDetCompZona = lDetCompZona;
	}

	public DetalleComponenteZona getDetCompZonaSel() {
		return detCompZonaSel;
	}

	public void setDetCompZonaSel(DetalleComponenteZona detCompZonaSel) {
		this.detCompZonaSel = detCompZonaSel;
	}

	public List<SelectItem> getlProgramas() {
		return lProgramas;
	}

	public void setlProgramas(List<SelectItem> lProgramas) {
		this.lProgramas = lProgramas;
	}

	public Map<String, String> getmCategorias() {
		return mCategorias;
	}

	public void setmCategorias(Map<String, String> mCategorias) {
		this.mCategorias = mCategorias;
	}

	public List<SelectItem> getlPromociones() {
		return lPromociones;
	}

	public void setlPromociones(List<SelectItem> lPromociones) {
		this.lPromociones = lPromociones;
	}

	public List<SelectItem> getlTipoPromociones() {
		return lTipoPromociones;
	}

	public void setlTipoPromociones(List<SelectItem> lTipoPromociones) {
		this.lTipoPromociones = lTipoPromociones;
	}

	public Map<String, String> getmSenialamientos() {
		return mSenialamientos;
	}

	public void setmSenialamientos(Map<String, String> mSenialamientos) {
		this.mSenialamientos = mSenialamientos;
	}

	public Map<String, String> getmGruposZona() {
		return mGruposZona;
	}

	public void setmGruposZona(Map<String, String> mGruposZona) {
		this.mGruposZona = mGruposZona;
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

	public List<SelectItem> getlProveedores() {
		return lProveedores;
	}

	public void setlProveedores(List<SelectItem> lProveedores) {
		this.lProveedores = lProveedores;
	}

	public List<SelectItem> getlSubCategorias() {
		return lSubCategorias;
	}

	public void setlSubCategorias(List<SelectItem> lSubCategorias) {
		this.lSubCategorias = lSubCategorias;
	}

	public List<SelectItem> getlDescripciones() {
		return lDescripciones;
	}

	public void setlDescripciones(List<SelectItem> lDescripciones) {
		this.lDescripciones = lDescripciones;
	}

	public Map<String, String> getmSku() {
		return mSku;
	}

	public void setmSku(Map<String, String> mSku) {
		this.mSku = mSku;
	}

	public Map<String, String> getmUpc() {
		return mUpc;
	}

	public void setmUpc(Map<String, String> mUpc) {
		this.mUpc = mUpc;
	}

	public List<SelectItem> getlListas() {
		return lListas;
	}

	public void setlListas(List<SelectItem> lListas) {
		this.lListas = lListas;
	}

	public List<SelectItem> getlEspaciosPromocionales() {
		return lEspaciosPromocionales;
	}

	public void setlEspaciosPromocionales(
			List<SelectItem> lEspaciosPromocionales) {
		this.lEspaciosPromocionales = lEspaciosPromocionales;
	}

	public ArqPeriodo getArqPeriodo() {
		return arqPeriodo;
	}

	public void setArqPeriodo(ArqPeriodo arqPeriodo) {
		this.arqPeriodo = arqPeriodo;
	}

	public ArqMecanica getArqMecanica() {
		return arqMecanica;
	}

	public void setArqMecanica(ArqMecanica arqMecanica) {
		this.arqMecanica = arqMecanica;
	}

	public ArqComponente getArqComponente() {
		return arqComponente;
	}

	public void setArqComponente(ArqComponente arqComponente) {
		this.arqComponente = arqComponente;
	}

	public List<SelectItem> getlCategorias() {
		return lCategorias;
	}

	public void setlCategorias(List<SelectItem> lCategorias) {
		this.lCategorias = lCategorias;
	}

	public String getNomNvaMecanica() {
		return nomNvaMecanica;
	}

	public void setNomNvaMecanica(String nomNvaMecanica) {
		this.nomNvaMecanica = nomNvaMecanica;
	}

	public String getNomNvoComponenete() {
		return nomNvoComponenete;
	}

	public void setNomNvoComponenete(String nomNvoComponenete) {
		this.nomNvoComponenete = nomNvoComponenete;
	}

	public List<SelectItem> getlComponentes() {
		return lComponentes;
	}

	public void setlComponentes(List<SelectItem> lComponentes) {
		this.lComponentes = lComponentes;
	}

	public List<SelectItem> getlMecanica() {
		return lMecanica;
	}

	public void setlMecanica(List<SelectItem> lMecanica) {
		this.lMecanica = lMecanica;
	}
    
    public List<ArqMecanica> obtenerMecanicas(){
        List<ArqMecanica> l = new ArrayList<ArqMecanica>();
        for(SelectItem s : lMecanica){
            ArqMecanica a = new ArqMecanica();
            a.setIdMecanica((Integer)s.getValue());
            a.setNombre(s.getLabel());
            l.add(a);
        }
        return l;
    }

	public boolean isMostrarOtroSen() {
		return mostrarOtroSen;
	}

	public void setMostrarOtroSen(boolean mostrarOtroSen) {
		this.mostrarOtroSen = mostrarOtroSen;
	}

	public List<SelectItem> getPeriodos() {
		return lPeriodos;
	}

	public void setlPeriodos(List<SelectItem> lPeriodos) {
		this.lPeriodos = lPeriodos;
	}

	public int getImpPeriodoId() {
		return impPeriodoId;
	}

	public void setImpPeriodoId(int impPeriodoId) {
		this.impPeriodoId = impPeriodoId;
	}

	public int getImpProgranaId() {
		return impProgranaId;
	}

	public void setImpProgranaId(int impProgranaId) {
		this.impProgranaId = impProgranaId;
	}

	public int getImpGrupoZonaId() {
		return impGrupoZonaId;
	}

	public void setImpGrupoZonaId(int impGrupoZonaId) {
		this.impGrupoZonaId = impGrupoZonaId;
	}

	public int getImpZonaId() {
		return impZonaId;
	}

	public void setImpZonaId(int impZonaId) {
		this.impZonaId = impZonaId;
	}

	public int getImpMecanicaId() {
		return impMecanicaId;
	}

	public void setImpMecanicaId(int impMecanicaId) {
		this.impMecanicaId = impMecanicaId;
	}

	public List<SelectItem> getlGruposZona() {
		return lGruposZona;
	}

	public void setlGruposZona(List<SelectItem> lGruposZona) {
		this.lGruposZona = lGruposZona;
	}

	public List<SelectItem> getlZonas() {
		return lZonas;
	}

	public void setlZonas(List<SelectItem> lZonas) {
		this.lZonas = lZonas;
	}
	
	   public boolean isDisableEspacioComp() {
           return disableEspacioComp;
       }

       public void setDisableEspacioComp(boolean disableEspacioComp) {
           this.disableEspacioComp = disableEspacioComp;
       }


	public boolean isHabilitarComp() {
		return habilitarComp;
	}

	public void setHabilitarComp(boolean habilitarComp) {
		this.habilitarComp = habilitarComp;
	}

	public List<SelectItem> getlUnidades() {
		return lUnidades;
	}

	public void setlUnidades(List<SelectItem> lUnidades) {
		this.lUnidades = lUnidades;
	}

	public List<ArqComponente> getlArqComponente() {
		return lArqComponente;
	}

	public void setlArqComponente(List<ArqComponente> lArqComponente) {
		this.lArqComponente = lArqComponente;
	}

	public List<ArqComponente> getlArqComponenteZona() {
		return lArqComponenteZona;
	}

	public void setlArqComponenteZona(List<ArqComponente> lArqComponenteZona) {
		this.lArqComponenteZona = lArqComponenteZona;
	}

	public ArqComponente getArqComponenteSel() {
		return arqComponenteSel;
	}

	public void setArqComponenteSel(ArqComponente arqComponenteSel) {
		this.arqComponenteSel = arqComponenteSel;
	}

	public List<SelectItem> getlProgramasImp() {
		return lProgramasImp;
	}

	public void setlProgramasImp(List<SelectItem> lProgramasImp) {
		this.lProgramasImp = lProgramasImp;
	}

	public List<SelectItem> getlGruposZonaImp() {
		return lGruposZonaImp;
	}

	public void setlGruposZonaImp(List<SelectItem> lGruposZonaImp) {
		this.lGruposZonaImp = lGruposZonaImp;
	}

	public List<SelectItem> getlZonasImp() {
		return lZonasImp;
	}

	public void setlZonasImp(List<SelectItem> lZonasImp) {
		this.lZonasImp = lZonasImp;
	}

	public List<SelectItem> getlMecanicaImp() {
		return lMecanicaImp;
	}

	public void setlMecanicaImp(List<SelectItem> lMecanicaImp) {
		this.lMecanicaImp = lMecanicaImp;
	}

	public int getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(int rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public boolean isAgregarPromocion() {
		return agregarPromocion;
	}

	public void setAgregarPromocion(boolean agregarPromocion) {
		this.agregarPromocion = agregarPromocion;
	}

	public boolean isPrecioPromocion() {
		return precioPromocion;
	}

	public void setPrecioPromocion(boolean precioPromocion) {
		this.precioPromocion = precioPromocion;
	}

	public boolean isPrecio() {
		return precio;
	}

	public void setPrecio(boolean precio) {
		this.precio = precio;
	}

	public List<SelectItem> getlProgramasPrec() {
		return lProgramasPrec;
	}

	public void setlProgramasPrec(List<SelectItem> lProgramasPrec) {
		this.lProgramasPrec = lProgramasPrec;
	}

	public String getSenialamientosPrec() {
		return senialamientosPrec;
	}

	public void setSenialamientosPrec(String senialamientosPrec) {
		this.senialamientosPrec = senialamientosPrec;
	}

	public Map<String, String> getmGruposZonaPrec() {
		return mGruposZonaPrec;
	}

	public void setmGruposZonaPrec(Map<String, String> mGruposZonaPrec) {
		this.mGruposZonaPrec = mGruposZonaPrec;
	}

	public Map<String, String> getmZonasPrec() {
		return mZonasPrec;
	}

	public void setmZonasPrec(Map<String, String> mZonasPrec) {
		this.mZonasPrec = mZonasPrec;
	}

	public Map<String, String> getmTiendasPrec() {
		return mTiendasPrec;
	}

	public void setmTiendasPrec(Map<String, String> mTiendasPrec) {
		this.mTiendasPrec = mTiendasPrec;
	}

	public List<SelectItem> getlCategoriasPrec() {
		return lCategoriasPrec;
	}

	public void setlCategoriasPrec(List<SelectItem> lCategoriasPrec) {
		this.lCategoriasPrec = lCategoriasPrec;
	}

	public List<SelectItem> getlSubCategoriasPrec() {
		return lSubCategoriasPrec;
	}

	public void setlSubCategoriasPrec(List<SelectItem> lSubCategoriasPrec) {
		this.lSubCategoriasPrec = lSubCategoriasPrec;
	}

	public List<SelectItem> getlDescripcionesPrec() {
		return lDescripcionesPrec;
	}

	public void setlDescripcionesPrec(List<SelectItem> lDescripcionesPrec) {
		this.lDescripcionesPrec = lDescripcionesPrec;
	}

	public Map<String, String> getmSkuPrec() {
		return mSkuPrec;
	}

	public void setmSkuPrec(Map<String, String> mSkuPrec) {
		this.mSkuPrec = mSkuPrec;
	}

	public List<SelectItem> getlListasPrec() {
		return lListasPrec;
	}

	public void setlListasPrec(List<SelectItem> lListasPrec) {
		this.lListasPrec = lListasPrec;
	}

	public List<SelectItem> getlComponentesPrec() {
		return lComponentesPrec;
	}

	public void setlComponentesPrec(List<SelectItem> lComponentesPrec) {
		this.lComponentesPrec = lComponentesPrec;
	}

	public List<SelectItem> getlMecanicaPrec() {
		return lMecanicaPrec;
	}

	public void setlMecanicaPrec(List<SelectItem> lMecanicaPrec) {
		this.lMecanicaPrec = lMecanicaPrec;
	}

	public int getIdProgPrec() {
		return idProgPrec;
	}

	public void setIdProgPrec(int idProgPrec) {
		this.idProgPrec = idProgPrec;
	}

	public List<String> getlGrupoZonasPrec() {
		return lGrupoZonasPrec;
	}

	public void setlGrupoZonasPrec(List<String> lGrupoZonasPrec) {
		this.lGrupoZonasPrec = lGrupoZonasPrec;
	}

	public List<String> getlZonasPrec() {
		return lZonasPrec;
	}

	public void setlZonasPrec(List<String> lZonasPrec) {
		this.lZonasPrec = lZonasPrec;
	}

	public List<String> getlTiendasPrec() {
		return lTiendasPrec;
	}

	public void setlTiendasPrec(List<String> lTiendasPrec) {
		this.lTiendasPrec = lTiendasPrec;
	}

	public int getIdCatPrec() {
		return idCatPrec;
	}

	public void setIdCatPrec(int idCatPrec) {
		this.idCatPrec = idCatPrec;
	}

	public int getIdSubCatPrec() {
		return idSubCatPrec;
	}

	public void setIdSubCatPrec(int idSubCatPrec) {
		this.idSubCatPrec = idSubCatPrec;
	}

	public int getIdDescPrec() {
		return idDescPrec;
	}

	public void setIdDescPrec(int idDescPrec) {
		this.idDescPrec = idDescPrec;
	}

	public List<String> getlSkuPrec() {
		return lSkuPrec;
	}

	public void setlSkuPrec(List<String> lSkuPrec) {
		this.lSkuPrec = lSkuPrec;
	}

	public Map<String, String> getmUpcPrec() {
		return mUpcPrec;
	}

	public void setmUpcPrec(Map<String, String> mUpcPrec) {
		this.mUpcPrec = mUpcPrec;
	}

	public List<String> getlUpcPrec() {
		return lUpcPrec;
	}

	public void setlUpcPrec(List<String> lUpcPrec) {
		this.lUpcPrec = lUpcPrec;
	}

	public int getIdListaPrec() {
		return idListaPrec;
	}

	public void setIdListaPrec(int idListaPrec) {
		this.idListaPrec = idListaPrec;
	}

	public int getIdCompPrec() {
		return idCompPrec;
	}

	public void setIdCompPrec(int idCompPrec) {
		this.idCompPrec = idCompPrec;
	}

	public int getIdMecanicaPrec() {
		return idMecanicaPrec;
	}

	public void setIdMecanicaPrec(int idMecanicaPrec) {
		this.idMecanicaPrec = idMecanicaPrec;
	}

	public double getPrecPromo() {
		return precPromo;
	}

	public void setPrecPromo(double precPromo) {
		this.precPromo = precPromo;
	}

	public double getPorcPromo() {
		return porcPromo;
	}

	public void setPorcPromo(double porcPromo) {
		this.porcPromo = porcPromo;
	}

	public double getAhorroFijo() {
		return ahorroFijo;
	}

	public void setAhorroFijo(double ahorroFijo) {
		this.ahorroFijo = ahorroFijo;
	}

	public double getRecuProveCant() {
		return recuProveCant;
	}

	public void setRecuProveCant(double recuProveCant) {
		this.recuProveCant = recuProveCant;
	}

	public double getRecuProvePorc() {
		return recuProvePorc;
	}

	public void setRecuProvePorc(double recuProvePorc) {
		this.recuProvePorc = recuProvePorc;
	}

	public double getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(double objetivo) {
		this.objetivo = objetivo;
	}

	public String getComenDisenio() {
		return comenDisenio;
	}

	public void setComenDisenio(String comenDisenio) {
		this.comenDisenio = comenDisenio;
	}

	public String getNomPromoPrec() {
		return nomPromoPrec;
	}

	public void setNomPromoPrec(String nomPromoPrec) {
		this.nomPromoPrec = nomPromoPrec;
	}

	public int getNumPromoPrec() {
		return numPromoPrec;
	}

	public void setNumPromoPrec(int numPromoPrec) {
		this.numPromoPrec = numPromoPrec;
	}

	public double getPrecPromoPrec() {
		return precPromoPrec;
	}

	public void setPrecPromoPrec(double precPromoPrec) {
		this.precPromoPrec = precPromoPrec;
	}

	public int getIdStatusRevision() {
		return idStatusRevision;
	}

	public void setIdStatusRevision(int idStatusRevision) {
		this.idStatusRevision = idStatusRevision;
	}

	public int getIdStatusCaptura() {
		return idStatusCaptura;
	}

	public void setIdStatusCaptura(int idStatusCaptura) {
		this.idStatusCaptura = idStatusCaptura;
	}

	public List<SelectItem> getlStatusRevision() {
		return lStatusRevision;
	}

	public void setlStatusRevision(List<SelectItem> lStatusRevision) {
		this.lStatusRevision = lStatusRevision;
	}

	public List<SelectItem> getlStatusCaptura() {
		return lStatusCaptura;
	}

	public void setlStatusCaptura(List<SelectItem> lStatusCaptura) {
		this.lStatusCaptura = lStatusCaptura;
	}

	public String getListaPrec() {
		return listaPrec;
	}

	public void setListaPrec(String listaPrec) {
		this.listaPrec = listaPrec;
	}

	public boolean isDisenio() {
		return disenio;
	}

	public void setDisenio(boolean disenio) {
		this.disenio = disenio;
	}

	public int getIdStatusPrecio() {
		return idStatusPrecio;
	}

	public void setIdStatusPrecio(int idStatusPrecio) {
		this.idStatusPrecio = idStatusPrecio;
	}

	public int getIdStatusDisenio() {
		return idStatusDisenio;
	}

	public void setIdStatusDisenio(int idStatusDisenio) {
		this.idStatusDisenio = idStatusDisenio;
	}

	public List<SelectItem> getlStatusPrecio() {
		return lStatusPrecio;
	}

	public void setlStatusPrecio(List<SelectItem> lStatusPrecio) {
		this.lStatusPrecio = lStatusPrecio;
	}

	public List<SelectItem> getlStatusDisenio() {
		return lStatusDisenio;
	}

	public void setlStatusDisenio(List<SelectItem> lStatusDisenio) {
		this.lStatusDisenio = lStatusDisenio;
	}

	public String getDescPrimicia() {
		return descPrimicia;
	}

	public void setDescPrimicia(String descPrimicia) {
		this.descPrimicia = descPrimicia;
	}

	public int getCategPrimicia() {
		return categPrimicia;
	}

	public void setCategPrimicia(int categPrimicia) {
		this.categPrimicia = categPrimicia;
	}

	public String getUpcPrimicia() {
		return upcPrimicia;
	}

	public void setUpcPrimicia(String upcPrimicia) {
		this.upcPrimicia = upcPrimicia;
	}

	public double getPrecioPrimicia() {
		return precioPrimicia;
	}

	public void setPrecioPrimicia(double precioPrimicia) {
		this.precioPrimicia = precioPrimicia;
	}

	public List<SelectItem> getlDescEstra() {
		return lDescEstra;
	}

	public void setlDescEstra(List<SelectItem> lDescEstra) {
		this.lDescEstra = lDescEstra;
	}

	public int getIdDescEstra() {
		return idDescEstra;
	}

	public void setIdDescEstra(int idDescEstra) {
		this.idDescEstra = idDescEstra;
	}

	public List<SelectItem> getlSKUEstra() {
		return lSKUEstra;
	}

	public void setlSKUEstra(List<SelectItem> lSKUEstra) {
		this.lSKUEstra = lSKUEstra;
	}

	public int getIdSkuEstra() {
		return idSkuEstra;
	}

	public void setIdSkuEstra(int idSkuEstra) {
		this.idSkuEstra = idSkuEstra;
	}

	public List<SelectItem> getlUpcEstra() {
		return lUpcEstra;
	}

	public void setlUpcEstra(List<SelectItem> lUpcEstra) {
		this.lUpcEstra = lUpcEstra;
	}

	public int getIdUpcEstra() {
		return idUpcEstra;
	}

	public void setIdUpcEstra(int idUpcEstra) {
		this.idUpcEstra = idUpcEstra;
	}

	public List<SelectItem> getlCategEstra() {
		return lCategEstra;
	}

	public void setlCategEstra(List<SelectItem> lCategEstra) {
		this.lCategEstra = lCategEstra;
	}

	public int getIdCategEstra() {
		return idCategEstra;
	}

	public void setIdCategEstra(int idCategEstra) {
		this.idCategEstra = idCategEstra;
	}

	public List<SelectItem> getlDescEstra2() {
		return lDescEstra2;
	}

	public void setlDescEstra2(List<SelectItem> lDescEstra2) {
		this.lDescEstra2 = lDescEstra2;
	}

	public int getIdDescEstra2() {
		return idDescEstra2;
	}

	public void setIdDescEstra2(int idDescEstra2) {
		this.idDescEstra2 = idDescEstra2;
	}

	public List<SelectItem> getlSKUEstra2() {
		return lSKUEstra2;
	}

	public void setlSKUEstra2(List<SelectItem> lSKUEstra2) {
		this.lSKUEstra2 = lSKUEstra2;
	}

	public int getIdSkuEstra2() {
		return idSkuEstra2;
	}

	public void setIdSkuEstra2(int idSkuEstra2) {
		this.idSkuEstra2 = idSkuEstra2;
	}

	public List<SelectItem> getlUpcEstra2() {
		return lUpcEstra2;
	}

	public void setlUpcEstra2(List<SelectItem> lUpcEstra2) {
		this.lUpcEstra2 = lUpcEstra2;
	}

	public int getIdUpcEstra2() {
		return idUpcEstra2;
	}

	public void setIdUpcEstra2(int idUpcEstra2) {
		this.idUpcEstra2 = idUpcEstra2;
	}

	public List<SelectItem> getlCategEstra2() {
		return lCategEstra2;
	}

	public void setlCategEstra2(List<SelectItem> lCategEstra2) {
		this.lCategEstra2 = lCategEstra2;
	}

	public int getIdCategEstra2() {
		return idCategEstra2;
	}

	public void setIdCategEstra2(int idCategEstra2) {
		this.idCategEstra2 = idCategEstra2;
	}

	public Map<String, String> getmGposZonaEstra() {
		return mGposZonaEstra;
	}

	public void setmGposZonaEstra(Map<String, String> mGposZonaEstra) {
		this.mGposZonaEstra = mGposZonaEstra;
	}

	public List<String> getlGposZonaEstra() {
		return lGposZonaEstra;
	}

	public void setlGposZonaEstra(List<String> lGposZonaEstra) {
		this.lGposZonaEstra = lGposZonaEstra;
	}

	public Map<String, String> getmZonasEstra() {
		return mZonasEstra;
	}

	public void setmZonasEstra(Map<String, String> mZonasEstra) {
		this.mZonasEstra = mZonasEstra;
	}

	public List<String> getlZonasEstra() {
		return lZonasEstra;
	}

	public void setlZonasEstra(List<String> lZonasEstra) {
		this.lZonasEstra = lZonasEstra;
	}

	public boolean isEstrategia() {
		return estrategia;
	}

	public void setEstrategia(boolean estrategia) {
		this.estrategia = estrategia;
	}

	public List<Object> getlEstraZona() {
		return lEstraZona;
	}

	public void setlEstraZona(List<Object> lEstraZona) {
		this.lEstraZona = lEstraZona;
	}

	public List<Object> getlEstrategiaProd() {
		return lEstrategiaProd;
	}

	public void setlEstrategiaProd(List<Object> lEstrategiaProd) {
		this.lEstrategiaProd = lEstrategiaProd;
	}

	public ConfigMecanica getConfigMecanica() {
		return configMecanica;
	}

	public void setConfigMecanica(ConfigMecanica configMecanica) {
		this.configMecanica = configMecanica;
	}

	public int getNumCompPrimicia() {
		return numCompPrimicia;
	}

	public void setNumCompPrimicia(int numCompPrimicia) {
		this.numCompPrimicia = numCompPrimicia;
	}

	public List<ArqComponente> getlArqCompPrecProm() {
		return lArqCompPrecProm;
	}

	public void setlArqCompPrecProm(List<ArqComponente> lArqCompPrecProm) {
		this.lArqCompPrecProm = lArqCompPrecProm;
	}

	public TreeNode getRaizDisenio() {
		return raizDisenio;
	}

	public void setRaizDisenio(TreeNode raizDisenio) {
		this.raizDisenio = raizDisenio;
	}

	public int getNumCategorias() {
		numCategorias = 0;
		if (configMecanica != null
				&& configMecanica.getlConfigMecanicaDet() != null) {
			numCategorias = configMecanica.getlConfigMecanicaDet().size();
		}
		return numCategorias;
	}

	public void setNumCategorias(int numCategorias) {
		this.numCategorias = numCategorias;
	}

	public int getIdStatusRevPrec() {
		return idStatusRevPrec;
	}

	public void setIdStatusRevPrec(int idStatusRevPrec) {
		this.idStatusRevPrec = idStatusRevPrec;
	}

	public int getIdStatusCapPrec() {
		return idStatusCapPrec;
	}

	public void setIdStatusCapPrec(int idStatusCapPrec) {
		this.idStatusCapPrec = idStatusCapPrec;
	}

	public TreeNode getRaizPrecio() {
		return raizPrecio;
	}

	public void setRaizPrecio(TreeNode raizPrecio) {
		this.raizPrecio = raizPrecio;
	}

	public List<ArqComponente> getlArqPreciosPrint() {
		return lArqPreciosPrint;
	}

	public void setlArqPreciosPrint(List<ArqComponente> lArqPreciosPrint) {
		this.lArqPreciosPrint = lArqPreciosPrint;
	}

	public List<String> getlUpcComp() {
		return lUpcComp;
	}

	public void setlUpcComp(List<String> lUpcComp) {
		this.lUpcComp = lUpcComp;
	}

    /**
     * @return the raizPrecionSeleccionado
     */
    public TreeNode getRaizPrecionSeleccionado() {
        return raizPrecionSeleccionado;
    }

    /**
     * @param raizPrecionSeleccionado the raizPrecionSeleccionado to set
     */
    public void setRaizPrecionSeleccionado(TreeNode raizPrecionSeleccionado) {
        this.raizPrecionSeleccionado = raizPrecionSeleccionado;
    }

    /**
     * @return the precioPromocionIVA
     */
    public double getPrecioPromocionIVA() {
        return precioPromocionIVA;
    }

    /**
     * @param precioPromocionIVA the precioPromocionIVA to set
     */
    public void setPrecioPromocionIVA(double precioPromocionIVA) {
        this.precioPromocionIVA = precioPromocionIVA;
    }

    /**
     * @return the precioPromocionImpuesto
     */
    public double getPrecioPromocionImpuesto() {
        return precioPromocionImpuesto;
    }

    /**
     * @param precioPromocionImpuesto the precioPromocionImpuesto to set
     */
    public void setPrecioPromocionImpuesto(double precioPromocionImpuesto) {
        this.precioPromocionImpuesto = precioPromocionImpuesto;
    }

    /**
     * @return the precioPromocionRegularNuevo
     */
    public double getPrecioPromocionRegularNuevo() {
        return precioPromocionRegularNuevo;
    }

    /**
     * @param precioPromocionRegularNuevo the precioPromocionRegularNuevo to set
     */
    public void setPrecioPromocionRegularNuevo(double precioPromocionRegularNuevo) {
        this.precioPromocionRegularNuevo = precioPromocionRegularNuevo;
    }

    /**
     * @return the senalListSelectedStr
     */
    public List<String> getSenalListSelectedStr() {
        return senalListSelectedStr;
    }

    /**
     * @param senalListSelectedStr the senalListSelectedStr to set
     */
    public void setSenalListSelectedStr(List<String> senalListSelectedStr) {
        this.senalListSelectedStr = senalListSelectedStr;
    }

    /**
     * @return the espacioPromocionalErrorClass
     */
    public String getEspacioPromocionalErrorClass() {
        return espacioPromocionalErrorClass;
    }

    /**
     * @param espacioPromocionalErrorClass the espacioPromocionalErrorClass to set
     */
    public void setEspacioPromocionalErrorClass(String espacioPromocionalErrorClass) {
        this.espacioPromocionalErrorClass = espacioPromocionalErrorClass;
    }

    /**
     * @return the ppCatZones
     */
    public List<CatZone> getPpCatZones() {
        return ppCatZones;
    }

    /**
     * @param ppCatZones the ppCatZones to set
     */
    public void setPpCatZones(List<CatZone> ppCatZones) {
        this.ppCatZones = ppCatZones;
    }

    /**
     * @return the ppCatZoneSelected
     */
    public String getPpCatZoneSelected() {
        return ppCatZoneSelected;
    }

    /**
     * @param ppCatZoneSelected the ppCatZoneSelected to set
     */
    public void setPpCatZoneSelected(String ppCatZoneSelected) {
        this.ppCatZoneSelected = ppCatZoneSelected;
    }

	/**************************************************** Dominios locales *******************************************************/

	public static class ArqPeriodo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private long idPeriodo;
		private String descripcion;
		private Date fechaInicio;
		private Date fechaFin;
		private Date horaInicio;
		private Date horaFin;
		private int idPrograma;
		private List<String> lCategorias;
		private double ingreso;
		private double ingresoReal;
        
        private String popRealErrorClass;



		private static final DecimalFormat DF = new DecimalFormat(
				"###,###,##0.00");

		public long getIdPeriodo() {
			return idPeriodo;
		}

		public void setIdPeriodo(long idPeriodo) {
			this.idPeriodo = idPeriodo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Date getFechaInicio() {
			return fechaInicio;
		}

		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}

		public Date getFechaFin() {
			return fechaFin;
		}

		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}

		public Date getHoraInicio() {
			return horaInicio;
		}

		public void setHoraInicio(Date horaInicio) {
			this.horaInicio = horaInicio;
		}

		public Date getHoraFin() {
			return horaFin;
		}

		public void setHoraFin(Date horaFin) {
			this.horaFin = horaFin;
		}

		public int getIdPrograma() {
			return idPrograma;
		}

		public void setIdPrograma(int idPrograma) {
			this.idPrograma = idPrograma;
		}

		public List<String> getlCategorias() {
			return lCategorias;
		}

		public void setlCategorias(List<String> lCategorias) {
			this.lCategorias = lCategorias;
		}

		public double getIngreso() {
			return ingreso;
		}

		public void setIngreso(double ingreso) {
			this.ingreso = ingreso;
		}

		public double getIngresoReal() {
			return ingresoReal;
		}

		public void setIngresoReal(double ingresoReal) {
			this.ingresoReal = ingresoReal;
		}

		public String getStrIngreso() {
			return DF.format(ingreso);
		}

		public void setStrIngreso(String strIngreso) {
			double dIngreso = 0;
			try {
				dIngreso = DF.parse(strIngreso).doubleValue();
			} catch (ParseException e) {
				LOG.error(e);
			}
			this.ingreso = dIngreso;
		}

		public String getStrIngresoReal() {
			return DF.format(ingresoReal);
		}

		public void setStrIngresoReal(String strIngresoReal) {
			double dIngreso = 0;
			try {
				dIngreso = DF.parse(strIngresoReal).doubleValue();
			} catch (ParseException e) {
				LOG.error(e);
			}
			this.ingresoReal = dIngreso;
		}

		public String getFechaIniFormat() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			return sdf.format(fechaInicio);
		}

		public String getFechaFinFormat() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			return sdf.format(fechaFin);
		}

		public String getHoraIniFormat() {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			return sdf.format(horaInicio);
		}

		public String getHoraFinFormat() {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			return sdf.format(horaFin);
		}

        /**
         * @return the popRealErrorClass
         */
        public String getPopRealErrorClass() {
            return popRealErrorClass;
        }

        /**
         * @param popRealErrorClass the popRealErrorClass to set
         */
        public void setPopRealErrorClass(String popRealErrorClass) {
            this.popRealErrorClass = popRealErrorClass;
        }
	}

	public static class ArqMecanica implements Serializable {
		private int idMecanica;
		private String nombre;
		private List<String> lMecanicas;
		private int idPromocion;
		private int idTipoPromocion;
		private int idPrograma;
		private List<String> lSenalamiento;
		private int compartido = -1;
		private List<String> lGrupoZonas;
		private List<String> lZonas;
		private List<String> lTiendas;
		private String[] acuerdos = new String[3];
		private String comentario;
		private boolean lunes = true;
		private boolean martes = true;
		private boolean miercoles = true;
		private boolean jueves = true;
		private boolean viernes = true;
		private boolean sabado = true;
		private boolean domingo = true;
		private Date horaInicio = new Date();
		private Date horaFin = new Date();
		private String otroSenalamiento;
		private MecanicaDTO dto;
		private ConfigMecanica configMecanica;

		public void setDto(MecanicaDTO mecanicaDTO) {
			idMecanica = mecanicaDTO.getPkMec();
			nombre = mecanicaDTO.getNombreMecanica();
			idPromocion = mecanicaDTO.getIdPromo();
			idTipoPromocion = mecanicaDTO.getIdTipoPromocion();
			if(idTipoPromocion == 0 )
				idTipoPromocion = Constants.TIPO_PROMOCION_COMPLEJA; //Defualt
			
			lSenalamiento = new ArrayList<>();
			if (mecanicaDTO.getSenalamientoList() != null) {
				for (GenericItem gi : mecanicaDTO.getSenalamientoList()) {
					if (gi.getId() == 0) {
						otroSenalamiento = gi.getValue();
					}
					lSenalamiento.add(String.valueOf(gi.getId()));
				}
			}

			compartido = mecanicaDTO.getEsCompartido();
			lGrupoZonas = new ArrayList<String>();
			if (mecanicaDTO.getGroupList() != null) {
				for (GenericItem gz : mecanicaDTO.getGroupList()) {
					lGrupoZonas.add(String.valueOf(gz.getId()));
				}
			}

			lZonas = new ArrayList<String>();
			if (mecanicaDTO.getZoneList() != null) {
				for (GenericItem z : mecanicaDTO.getZoneList()) {
					lZonas.add(String.valueOf(z.getId()));
				}
			}

			lTiendas = new ArrayList<String>();
			if (mecanicaDTO.getStoreList() != null) {
				for (GenericItem t : mecanicaDTO.getStoreList()) {
					lTiendas.add(String.valueOf(t.getId()));
				}
			}

			acuerdos[0] = mecanicaDTO.getAcuerdo1();
			acuerdos[1] = mecanicaDTO.getAcuerdo2();
			acuerdos[2] = mecanicaDTO.getAcuerdo3();
			comentario = mecanicaDTO.getComentarios();
			
			//TODO: Revisar el comportamiento de esta parte al guardar:
			
			lunes = mecanicaDTO.getLunes() == 1;
			martes = mecanicaDTO.getMartes() == 1;
			miercoles = mecanicaDTO.getMiercoles() == 1;
			jueves = mecanicaDTO.getJueves() == 1;
			viernes = mecanicaDTO.getViernes() == 1;
			sabado = mecanicaDTO.getSabado() == 1;
			domingo = mecanicaDTO.getDomingo() == 1;
			horaFin = mecanicaDTO.getHoraFin();
			horaInicio = mecanicaDTO.getHoraIni();
		}

		public MecanicaDTO getDto() {
			dto = new MecanicaDTO();
			dto.setPkMec(idMecanica);
			dto.setNombreMecanica(nombre);
			dto.setIdPromo(idPromocion);
			dto.setIdTipoPromocion(idTipoPromocion);
			List<GenericItem> lGenItem = new ArrayList<GenericItem>();
			if (lSenalamiento != null) {
                for (String sen : lSenalamiento) {
                    GenericItem gi = new GenericItem();
                    gi.setId(Integer.valueOf(sen));
                    if (sen.equals("0")) {
                        gi.setValue(otroSenalamiento);
                    }
                    lGenItem.add(gi);
                }
            }
			dto.setSenalamientoList(lGenItem);
			dto.setEsCompartido(compartido);
			dto.setGroupList(new ArrayList<GenericItem>());
			if (lGrupoZonas != null) {
				for (String gz : lGrupoZonas) {
					GenericItem gi = new GenericItem();
					gi.setId(Integer.valueOf(gz));
					dto.getGroupList().add(gi);
				}
			}

			dto.setZoneList(new ArrayList<GenericItem>());
			if (lZonas != null) {
				for (String z : lZonas) {
					GenericItem gi = new GenericItem();
					gi.setId(Integer.valueOf(z));
					dto.getZoneList().add(gi);
				}
			}

			dto.setStoreList(new ArrayList<GenericItem>());
			if (lTiendas != null) {
				for (String t : lTiendas) {
					GenericItem gi = new GenericItem();
					gi.setId(Integer.valueOf(t));
					dto.getStoreList().add(gi);
				}
			}

			dto.setAcuerdo1(acuerdos[0]);
			dto.setAcuerdo2(acuerdos[1]);
			dto.setAcuerdo3(acuerdos[2]);
			dto.setComentarios(comentario);
			dto.setLunes(lunes ? 1 : 0);
			dto.setMartes(martes ? 1 : 0);
			dto.setMiercoles(miercoles ? 1 : 0);
			dto.setJueves(jueves ? 1 : 0);
			dto.setViernes(viernes ? 1 : 0);
			dto.setSabado(sabado ? 1 : 0);
			dto.setDomingo(domingo ? 1 : 0);
			dto.setHoraFin(horaFin);
			dto.setHoraIni(horaInicio);
			return dto;
		}

		public int getIdMecanica() {
			return idMecanica;
		}

		public void setIdMecanica(int idMecanica) {
			this.idMecanica = idMecanica;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public List<String> getlMecanicas() {
			return lMecanicas;
		}

		public void setlMecanicas(List<String> lMecanicas) {
			this.lMecanicas = lMecanicas;
		}

		public int getIdPromocion() {
			return idPromocion;
		}

		public void setIdPromocion(int idPromocion) {
			this.idPromocion = idPromocion;
		}

		public int getIdTipoPromocion() {
			return idTipoPromocion;
		}

		public void setIdTipoPromocion(int idTipoPromocion) {
			this.idTipoPromocion = idTipoPromocion;
		}

		public List<String> getlSenalamiento() {
			return lSenalamiento;
		}

		public void setlSenalamiento(List<String> lSenalamiento) {
			this.lSenalamiento = lSenalamiento;
		}

		public int getCompartido() {
			return compartido;
		}

		public void setCompartido(int compartido) {
			this.compartido = compartido;
		}

		public List<String> getlGrupoZonas() {
			return lGrupoZonas;
		}

		public void setlGrupoZonas(List<String> lGrupoZonas) {
			this.lGrupoZonas = lGrupoZonas;
		}

		public List<String> getlZonas() {
			return lZonas;
		}

		public void setlZonas(List<String> lZonas) {
			this.lZonas = lZonas;
		}

		public List<String> getlTiendas() {
			return lTiendas;
		}

		public void setlTiendas(List<String> lTiendas) {
			this.lTiendas = lTiendas;
		}

		public String[] getAcuerdos() {
			return acuerdos;
		}

		public void setAcuerdos(String[] acuerdos) {
			this.acuerdos = acuerdos;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public boolean isLunes() {
			return lunes;
		}

		public void setLunes(boolean lunes) {
			this.lunes = lunes;
		}

		public boolean isMartes() {
			return martes;
		}

		public void setMartes(boolean martes) {
			this.martes = martes;
		}

		public boolean isMiercoles() {
			return miercoles;
		}

		public void setMiercoles(boolean miercoles) {
			this.miercoles = miercoles;
		}

		public boolean isJueves() {
			return jueves;
		}

		public void setJueves(boolean jueves) {
			this.jueves = jueves;
		}

		public boolean isViernes() {
			return viernes;
		}

		public void setViernes(boolean viernes) {
			this.viernes = viernes;
		}

		public boolean isSabado() {
			return sabado;
		}

		public void setSabado(boolean sabado) {
			this.sabado = sabado;
		}

		public boolean isDomingo() {
			return domingo;
		}

		public void setDomingo(boolean domingo) {
			this.domingo = domingo;
		}

		public Date getHoraInicio() {
			return horaInicio;
		}

		public void setHoraInicio(Date horaInicio) {
			this.horaInicio = horaInicio;
		}

		public Date getHoraFin() {
			return horaFin;
		}

		public void setHoraFin(Date horaFin) {
			this.horaFin = horaFin;
		}

		public String getOtroSenalamiento() {
			return otroSenalamiento;
		}

		public void setOtroSenalamiento(String otroSenalamiento) {
			this.otroSenalamiento = otroSenalamiento;
		}

		public ConfigMecanica getConfigMecanica() {
			return configMecanica;
		}

		public void setConfigMecanica(ConfigMecanica configMecanica) {
			this.configMecanica = configMecanica;
		}

		public int getIdPrograma() {
			return idPrograma;
		}

		public void setIdPrograma(int idPrograma) {
			this.idPrograma = idPrograma;
		}
		
		
	}

	public static class ArqComponente implements Serializable{
		
        public String toString(){
            return "Zona: " + this.idZona + ", # " + this.numeroComponente + ", Desc: " +  this.descripcion;
        }
        
        private Boolean editandoDos;
        
        private String rowStyle;
		private int idMecanica;
		private String strMecanica;
		private int idComponente;
		private int idCategoria;
		private int idProveedor;
		private int idSubCategoria;
		private String idDescripcion;
		private int idLista;
		private List<String> lSku;
        private List<String> lSkuFamilia;
		private String idUpc;
		private int cantProductos;
		private int idEspPromocional;
		private int numero;
		private int abastoInicial;
		private int numero2;
		private int abastoInicial2;
        private int idUnidad;
		private int numeroComponente;
		private ComponenteDTO dto;
		private String descripcion;
		private String sku;
		private String upc;
		private String senalamiento;
		private String categoria;
		private String subCategoria;
		private int idGpoZona;
		private String gpoZona;
		private int idZona;
		private String zona;
        private List<String>lZonas;
		private Boolean editando = new Boolean(false);
		private double precio;
		private String descMecanica;
		private String acuerdo1;
		private String acuerdo2;
		private String acuerdo3;
		private double mgnRegular;
		private double mgnRegularPorc;
		private double mgnPromocion;
		private double mgnPromocionPorc;
		private double ventaUPTD;
		private double puntoEquilibrio;
		private double distRebaja;
		private double precioEdit;
		private double porcentajeEdit;
		private double ahorroFijoEdit;
		private double recuperacionEdit;
		private double recuperacionPorcentajeEdit;
		private double ahorroMaximo;
		private PreciosPromocionDTO preciosPromocionDTO = new PreciosPromocionDTO();
		private int idEstatusPrecio;
		private String labelEstatusCaptura;
		private String labelEstatusRevision;
		private String labelEstatusPrecio;
		private String labelEstatusDisenio;
		private String strEstatusCaptura;
		private String strEstatusRevision;
		private String strEstatusPrecio;
		private String strEstatusDisenio;
		private double precioRegularXUnidad;
		private double precioRegularPromo;
		private String programa;
		private int treeNivel;
                
        private int cantProductosEdit;
		private int numeroEdit;
		private int abastoInicialEdit;
                
        private int hoja;
        private Boolean isLastTreeElement = false;
        private String elementType;
        private int testUpdateSVN;
                
        private Boolean hasPrecioPromocion = false;

        //nuevos valores
        private Double iva;
        private Double impuesto;
        private Double precioRegularNuevo;
        
        private int cantidadProducto;
        
        private double distribucionRebaja;
		private double distribucionRebajaPorc;
        
        private double precioVentaOriginal;
        
        private Boolean tienePrecioPromocion;
        private Boolean tienePorcentajeDescuento;
        
        private Boolean esUltimoElementoDeCombo = false;
        
        private Double mgnRegularCombo = 0D;
        private Double mgnRegularPorcCombo = 0D;
        private Double porcentajeRetencionCombo = 0D;
        private Double distribucionRebajaCombo = 0D;
        private Double distribucionRebajaPorcCombo = 0D;
        private Double mgnPromocionCombo = 0D;
        private Double mgnPromocionPorcCombo = 0D;
        private Double ventaUPTDCombo = 0D;
        private Double ahorroMaximoCombo = 0D;
        
        private int pruebaUno;
        
		public void updateChangeValues(){
			this.cantProductos = this.cantProductosEdit;
            this.numero = this.numeroEdit;
            this.abastoInicial = this.abastoInicialEdit;
        }
        
        public String getPriceDescription(){
            if(this.elementType.contains("G")){
                return this.elementType + " - " + this.getGpoZona();
            }else if(this.elementType.contains("Z")){
                return this.elementType + " - " + this.getZona();
            }else{
                return this.elementType + " - " + this.getDescMecanica();
            }
        }

		public ArqComponente() {

		}

		public ArqComponente(ArqComponente arqComp) {
			this.idMecanica = arqComp.getIdMecanica();
			this.idComponente = arqComp.getIdComponente();
			this.idCategoria = arqComp.getIdCategoria();
			this.idProveedor = arqComp.getIdProveedor();
			this.idSubCategoria = arqComp.idSubCategoria;
			this.idDescripcion = arqComp.idDescripcion;
			this.idLista = arqComp.idLista;
			this.lSku = new ArrayList<>();
			if (arqComp.getlSku() != null) {
				this.lSku.addAll(arqComp.getlSku());
			}
			this.idUpc = arqComp.getIdUpc();
			this.cantProductos = arqComp.getCantProductos();
			this.idEspPromocional = arqComp.getIdEspPromocional();
			this.numero = arqComp.getNumero();
			this.abastoInicial = arqComp.getAbastoInicial();
			this.idUnidad = arqComp.getIdUnidad();
			this.numeroComponente = arqComp.getNumeroComponente();
			this.descripcion = arqComp.getDescripcion();
			this.sku = arqComp.getSku();
			this.upc = arqComp.getUpc();
			this.senalamiento = arqComp.getSenalamiento();
			this.categoria = arqComp.getCategoria();
			this.subCategoria = arqComp.getSubCategoria();
			this.idZona = arqComp.getIdZona();
			this.zona = arqComp.getZona();
			this.idGpoZona = arqComp.getIdGpoZona();
			this.gpoZona = arqComp.getGpoZona();
			this.acuerdo1 = arqComp.getAcuerdo1();
			this.acuerdo2 = arqComp.getAcuerdo2();
			this.acuerdo3 = arqComp.getAcuerdo3();
			this.preciosPromocionDTO = arqComp.getPreciosPromocionDTO();
            this.hoja = arqComp.hoja;
            
            this.cantProductos = arqComp.getCantProductos();
            this.cantidadProducto = arqComp.getCantidadProducto();
            this.precioVentaOriginal = arqComp.getPrecioVentaOriginal();
            this.ahorroMaximo = arqComp.getAhorroMaximo();
            
            this.pruebaUno = arqComp.getPruebaUno();
		}

		public ComponenteDTO getDto() {
			dto = new ComponenteDTO();
			dto.setAbastoInicial(abastoInicial);
			dto.setPkComp(idComponente);
			dto.setIdCateg(idCategoria);
			dto.setIdProveedor(idProveedor);
			dto.setIdSubCategoria(idSubCategoria);
			dto.setIdDescription(idDescripcion);
			dto.setIdLista(idLista);

			if (lSku != null) {
				dto.setSkuList(new ArrayList<GenericItem>());

				for (int i = 0; i < this.lSku.size(); i++) {
                    GenericItem item = new GenericItem();
                    Integer c = 2;
                    //	Integer sku_ = Integer.parseInt(lSku.get(i));
                    item.setId(c);
                    dto.getSkuList().add(item);
				}
			}

			GenericItemString item = new GenericItemString();
			item.setId(idUpc);
			dto.setUpcList(item);

			dto.setCantidadProd(cantProductos);
			dto.setIdEspacioPromocional(idEspPromocional);
			dto.setNumero(numero);
			dto.setUnidades(idUnidad);
			dto.setNumeroComponente(numeroComponente);
			dto.setPkMec(idMecanica);
            dto.setHoja(hoja);
            
            dto.setPrecioVentaOriginal(precioVentaOriginal);
            
            
			return dto;
		}

		public void setDto(ComponenteDTO dto) {
			abastoInicial = dto.getAbastoInicial() != null ? dto.getAbastoInicial() : 0;
			idComponente = dto.getPkComp();
			idCategoria = dto.getIdCateg();
			idProveedor = dto.getIdProveedor();
			idSubCategoria = dto.getIdSubCategoria();
			idDescripcion = dto.getIdDescription();
			idLista = dto.getIdLista();
			lSku = new ArrayList<>();
			if (dto.getSkuList() != null) {
				for (GenericItem sku_local : dto.getSkuList()) {
					lSku.add(sku_local.getId().toString());
				}
			}
			idUpc = dto.getUpcList().getId() == null ? String.valueOf(0) : String.valueOf(dto.getUpcList().getId());

			cantProductos = dto.getCantidadProd();
			idEspPromocional = dto.getIdEspacioPromocional();
			numero = dto.getNumero() != null ? dto.getNumero() : 0;
			idUnidad = dto.getUnidades() != null ? dto.getUnidades()
					: 0;
			numeroComponente = dto.getNumeroComponente();
			idMecanica = dto.getPkMec();
            hoja = dto.getHoja() == null ? 0 : dto.getHoja();
		}

        public int getHoja() {
            return hoja;
        }

        public void setHoja(int hoja) {
            this.hoja = hoja;
        }

        public String getPrograma() {
			return programa;
		}

		public void setPrograma(String programa) {
			this.programa = programa;
		}

		public int getIdMecanica() {
			return idMecanica;
		}

		public void setIdMecanica(int idMecanica) {
			this.idMecanica = idMecanica;
		}

		public String getStrMecanica() {
			return strMecanica;
		}

		public void setStrMecanica(String strMecanica) {
			this.strMecanica = strMecanica;
		}

		public int getIdComponente() {
			return idComponente;
		}

		public void setIdComponente(int idComponente) {
			this.idComponente = idComponente;
		}

		public int getIdCategoria() {
			return idCategoria;
		}

		public void setIdCategoria(int idCategoria) {
			this.idCategoria = idCategoria;
		}

		public int getIdProveedor() {
			return idProveedor;
		}

		public void setIdProveedor(int idProveedor) {
			this.idProveedor = idProveedor;
		}

		public int getIdSubCategoria() {
			return idSubCategoria;
		}

		public void setIdSubCategoria(int idSubCategoria) {
			this.idSubCategoria = idSubCategoria;
		}

		public String getIdDescripcion() {
			return idDescripcion;
		}

		public void setIdDescripcion(String idDescripcion) {
			this.idDescripcion = idDescripcion;
		}

		public int getIdLista() {
			return idLista;
		}

		public void setIdLista(int idLista) {
			this.idLista = idLista;
		}


		public List<String> getlSku() {
			return lSku;
		}

		public void setlSku(List<String> lSku) {
			List<String> nueva = new ArrayList<>();
			for (Object o : lSku) {
				try {
					nueva.add(o.toString());
				} catch (NumberFormatException e) {
					LOG.error(e);
				}
			}
			this.lSku = nueva;
		}

		public String getIdUpc() {
			return idUpc;
		}

		public void setIdUpc(String idUpc) {
			this.idUpc = idUpc;
		}

		public int getCantProductos() {
			return cantProductos;
		}

		public void setCantProductos(int cantProductos) {
			this.cantProductos = cantProductos;
		}

		public int getIdEspPromocional() {
			return idEspPromocional;
		}

		public void setIdEspPromocional(int idEspPromocional) {
			this.idEspPromocional = idEspPromocional;
		}

		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		public int getAbastoInicial() {
			return abastoInicial;
		}

		public void setAbastoInicial(int abastoInicial) {
			this.abastoInicial = abastoInicial;
		}

		public int getIdUnidad() {
			return idUnidad;
		}

		public void setIdUnidad(int idUnidad) {
			this.idUnidad = idUnidad;
		}

		public int getNumeroComponente() {
			return numeroComponente;
		}

		public void setNumeroComponente(int numeroComponente) {
			this.numeroComponente = numeroComponente;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public String getUpc() {
			return upc;
		}

		public void setUpc(String upc) {
			this.upc = upc;
		}

		public String getSenalamiento() {
			return senalamiento;
		}

		public void setSenalamiento(String senalamiento) {
			this.senalamiento = senalamiento;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public String getSubCategoria() {
			return subCategoria;
		}

		public void setSubCategoria(String subCategoria) {
			this.subCategoria = subCategoria;
		}

		public String getZona() {
			return zona;
		}

		public void setZona(String zona) {
			this.zona = zona;
		}

		public Boolean getEditando() {
			return editando;
		}

		public void setEditando(Boolean editando) {
			this.editando = editando;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public PreciosPromocionDTO getPreciosPromocionDTO() {
			return preciosPromocionDTO;
		}

		public void setPreciosPromocionDTO(
				PreciosPromocionDTO preciosPromocionDTO) {
			this.preciosPromocionDTO = preciosPromocionDTO;
		}

		public String getDescMecanica() {
			return descMecanica;
		}

		public void setDescMecanica(String descMecanica) {
			this.descMecanica = descMecanica;
		}

		public int getIdGpoZona() {
			return idGpoZona;
		}

		public void setIdGpoZona(int idGpoZona) {
			this.idGpoZona = idGpoZona;
		}

		public String getGpoZona() {
			return gpoZona;
		}

		public void setGpoZona(String gpoZona) {
			this.gpoZona = gpoZona;
		}

		public int getIdZona() {
			return idZona;
		}

		public void setIdZona(int idZona) {
			this.idZona = idZona;
		}

		public String getAcuerdo1() {
			return acuerdo1;
		}

		public void setAcuerdo1(String acuerdo1) {
			this.acuerdo1 = acuerdo1;
		}

		public String getAcuerdo2() {
			return acuerdo2;
		}

		public void setAcuerdo2(String acuerdo2) {
			this.acuerdo2 = acuerdo2;
		}

		public String getAcuerdo3() {
			return acuerdo3;
		}

		public void setAcuerdo3(String acuerdo3) {
			this.acuerdo3 = acuerdo3;
		}

		public double getMgnRegular() {
			return mgnRegular;
		}

		public void setMgnRegular(double mgnRegular) {
			this.mgnRegular = mgnRegular;
		}

		public double getMgnRegularPorc() {
			return mgnRegularPorc;
		}

		public void setMgnRegularPorc(double mgnRegularPorc) {
			this.mgnRegularPorc = mgnRegularPorc;
		}

		public double getMgnPromocion() {
			return mgnPromocion;
		}

		public void setMgnPromocion(double mgnPromocion) {
			this.mgnPromocion = mgnPromocion;
		}

		public double getMgnPromocionPorc() {
			return mgnPromocionPorc;
		}

		public void setMgnPromocionPorc(double mgnPromocionPorc) {
			this.mgnPromocionPorc = mgnPromocionPorc;
		}

		public double getVentaUPTD() {
			return ventaUPTD;
		}

		public void setVentaUPTD(double ventaUPTD) {
			this.ventaUPTD = ventaUPTD;
		}

		public double getPuntoEquilibrio() {
			return puntoEquilibrio;
		}

		public void setPuntoEquilibrio(double puntoEquilibrio) {
			this.puntoEquilibrio = puntoEquilibrio;
		}

		public double getDistRebaja() {
			return distRebaja;
		}

		public void setDistRebaja(double distRebaja) {
			this.distRebaja = distRebaja;
		}

		public double getPrecioEdit() {
			return precioEdit;
		}

		public void setPrecioEdit(double precioEdit) {
			this.precioEdit = precioEdit;
		}

		public double getPorcentajeEdit() {
			return porcentajeEdit;
		}

		public void setPorcentajeEdit(double porcentajeEdit) {
			this.porcentajeEdit = porcentajeEdit;
		}

		public double getAhorroFijoEdit() {
			return ahorroFijoEdit;
		}

		public void setAhorroFijoEdit(double ahorroFijoEdit) {
			this.ahorroFijoEdit = ahorroFijoEdit;
		}

		public double getRecuperacionEdit() {
			return recuperacionEdit;
		}

		public void setRecuperacionEdit(double recuperacionEdit) {
			this.recuperacionEdit = recuperacionEdit;
		}

		public double getRecuperacionPorcentajeEdit() {
			return recuperacionPorcentajeEdit;
		}

		public void setRecuperacionPorcentajeEdit(
				double recuperacionPorcentajeEdit) {
			this.recuperacionPorcentajeEdit = recuperacionPorcentajeEdit;
		}

		public double getAhorroMaximo() {
			return ahorroMaximo;
		}

		public void setAhorroMaximo(double ahorroMaximo) {
			this.ahorroMaximo = ahorroMaximo;
		}

		public String getLabelEstatusCaptura() {
			labelEstatusCaptura = "#FFFFFF";
			if (preciosPromocionDTO != null) {
				switch (preciosPromocionDTO.getEstatusCaptura()) {
				case 1:
					labelEstatusCaptura = "#ffff00";
					break;
				case 2:
					labelEstatusCaptura = "#00b050";
					break;
				}
			}
			return labelEstatusCaptura;
		}

		public void setLabelEstatusCaptura(String labelEstatusCaptura) {
			this.labelEstatusCaptura = labelEstatusCaptura;
		}

		public String getLabelEstatusRevision() {
			labelEstatusRevision = "#ffff00";
			if (preciosPromocionDTO != null) {
				switch (preciosPromocionDTO.getEstatusRevision()) {
				case 1:
					labelEstatusRevision = "#ffff00";
					break;
				case 2:
					labelEstatusRevision = "#00b050";
					break;
				case 3:
					labelEstatusRevision = "#1f497d";
					break;
                case 4:
                    labelEstatusRevision = "#FF0000";
					break;
				}
			}
			return labelEstatusRevision;
		}

		public void setLabelEstatusRevision(String labelEstatusRevision) {
			this.labelEstatusRevision = labelEstatusRevision;
		}

		public String getStrEstatusCaptura() {
			strEstatusCaptura = "";
			if (preciosPromocionDTO != null) {
				switch (preciosPromocionDTO.getEstatusCaptura()) {
				case 1:
					strEstatusCaptura = "Pendiente";
					break;
				case 2:
					strEstatusCaptura = "Capturado";
					break;
				}
			}
			return strEstatusCaptura;
		}

		public void setStrEstatusCaptura(String strEstatusCaptura) {
			this.strEstatusCaptura = strEstatusCaptura;
		}

		public String getStrEstatusRevision() {
			strEstatusRevision = "";
			if (preciosPromocionDTO != null) {
				switch (preciosPromocionDTO.getEstatusRevision()) {
				case 1:
					strEstatusRevision = "Pendiente";
					break;
				case 2:
					strEstatusRevision = "Autorizado";
					break;
				case 3:
					strEstatusRevision = "Autorizado con Ajuste";
					break;
				}
			}
			return strEstatusRevision;
		}

		public String getLabelEstatusPrecio() {
			labelEstatusPrecio = "#FFFFFF";
			switch (idEstatusPrecio) {
			case 1:
				labelEstatusPrecio = "#ffff00";
				break;
			case 2:
				labelEstatusPrecio = "#00b050";
				break;
			case 3:
				labelEstatusPrecio = "#1f497d";
				break;
			}

			return labelEstatusPrecio;
		}

		public void setLabelEstatusPrecio(String labelEstatusPrecio) {
			this.labelEstatusPrecio = labelEstatusPrecio;
		}

		public String getLabelEstatusDisenio() {
			labelEstatusDisenio = "";
			if (preciosPromocionDTO != null && preciosPromocionDTO.getEstatusDiseno() != null) {
				switch (preciosPromocionDTO.getEstatusDiseno()) {
				case 1:
					labelEstatusDisenio = "#ffff00";
					break;
				case 2:
					labelEstatusDisenio = "#604a7b";
					break;
				case 3:
					labelEstatusDisenio = "#984807";
					break;
				case 4:
					labelEstatusDisenio = "#f57913";
					break;
				case 5:
					labelEstatusDisenio = "#5fd543";
					break;
				case 6:
					labelEstatusDisenio = "#2a7119";
					break;
				case 7:
					labelEstatusDisenio = "#ff0000";
					break;
				}
			}
			return labelEstatusDisenio;
		}
		
		
	

		public void setLabelEstatusDisenio(String labelEstatusDisenio) {
			this.labelEstatusDisenio = labelEstatusDisenio;
		}

		public String getStrEstatusPrecio() {
			strEstatusPrecio = "Pendiente";
			switch (idEstatusPrecio) {
			case 1:
				strEstatusPrecio = "Pendiente";
				break;
			case 2:
				strEstatusPrecio = "Autorizado";
				break;
			case 3:
				strEstatusPrecio = "Autorizado con Ajuste";
				break;
            case 4:
				strEstatusPrecio = "Rechazado";
				break;
			}

			return strEstatusPrecio;
		}

		public void setStrEstatusPrecio(String strEstatusPrecio) {
			this.strEstatusPrecio = strEstatusPrecio;
		}
		
		

		public String getStrEstatusDisenio() {
			strEstatusDisenio = "";
			if (preciosPromocionDTO != null
					&& preciosPromocionDTO.getEstatusDiseno() != null) {
				switch (preciosPromocionDTO.getEstatusDiseno().intValue()) {
				case 1:
					strEstatusDisenio = "Pendiente";
					break;
				case 2:
					strEstatusDisenio = "En dise?o";
					break;
				case 3:
					strEstatusDisenio = "Revisi?n";
					break;
				case 4:
					strEstatusDisenio = "Rechazado";
					break;
				case 5:
					strEstatusDisenio = "Aprobado";
					break;
				case 6:
					strEstatusDisenio = "Enviado a Impresi?n";
					break;
				case 7:
					strEstatusDisenio = "Cancelado";
					break;
				}
			}
			return strEstatusDisenio;
		}

		public void setStrEstatusDisenio(String strEstatusDisenio) {
			this.strEstatusDisenio = strEstatusDisenio;
		}

		public void setStrEstatusRevision(String strEstatusRevision) {
			this.strEstatusRevision = strEstatusRevision;
		}

		public int getIdEstatusPrecio() {
			return idEstatusPrecio;
		}

		public void setIdEstatusPrecio(int idEstatusPrecio) {
			this.idEstatusPrecio = idEstatusPrecio;
		}

		public double getPrecioRegularXUnidad() {
            return precioRegularXUnidad;
		}

		public void setPrecioRegularXUnidad(double precioRegularXUnidad) {
			this.precioRegularXUnidad = precioRegularXUnidad;
		}

		public double getPrecioRegularPromo() {
			return precioRegularPromo;
		}

		public void setPrecioRegularPromo(double precioRegularPromo) {
			this.precioRegularPromo = precioRegularPromo;
		}

		/**
		 * @return the treeNivel
		 */
		public int getTreeNivel() {
			return treeNivel;
		}

		/**
		 * @param treeNivel the treeNivel to set
		 */
		public void setTreeNivel(int treeNivel) {
			this.treeNivel = treeNivel;
		}

        public List<String> getlZonas() {
            List<String> zonas = new ArrayList<>();
            if (zona.length() > 0){
                String [] aux = zona.split(",");
                for (int i = 0; i < aux.length; i++){
                    zonas.add(aux[i]);
                }
            }
            return zonas;
        }

        public void setlZonas(List<String> lZonas) {
            this.lZonas = lZonas;
        }

        //	    //Eclipse Generated hashCode and equals
//	    @Override
//	    public int hashCode() {
//	        final int prime = 31;
//	        int result = 1;
//	        result = prime * result + ((gpoZona == null) ? 0 : gpoZona.hashCode());
//	        return result;
//	    }
//	 
//	    @Override
//	    public boolean equals(Object obj) {
//	        if (this == obj)
//	            return true;
//	        if (obj == null)
//	            return false;
//	        if (getClass() != obj.getClass())
//	            return false;
//	        ArqComponente other = (ArqComponente) obj;
//	        if (gpoZona == null) {
//	            if (other.gpoZona != null)
//	                return false;
//	        } else if (!gpoZona.equals(other.gpoZona))
//	            return false;
//	       
//	        return true;
//	    }
//	 
//	    @Override
//	    public String toString() {
//	        return gpoZona;
//	    }
//	 
//	    public int compareTo(ArqComponente document) {
//	        return this.getGpoZona().compareTo(document.getGpoZona());
//	    }

        /**
         * @return the cantProductosEdit
         */
        public int getCantProductosEdit() {
            return cantProductos;
        }

        /**
         * @param cantProductosEdit the cantProductosEdit to set
         */
        public void setCantProductosEdit(int cantProductosEdit) {
            this.cantProductosEdit = cantProductosEdit;
        }

        /**
         * @return the numeroEdit
         */
        public int getNumeroEdit() {
            return numero;
        }

        /**
         * @param numeroEdit the numeroEdit to set
         */
        public void setNumeroEdit(int numeroEdit) {
            this.numeroEdit = numeroEdit;
        }

        /**
         * @return the abastoInicialEdit
         */
        public int getAbastoInicialEdit() {
            return abastoInicial;
        }

        /**
         * @param abastoInicialEdit the abastoInicialEdit to set
         */
        public void setAbastoInicialEdit(int abastoInicialEdit) {
            this.abastoInicialEdit = abastoInicialEdit;
        }

        /**
         * @return the isLastTreeElement
         */
        public Boolean getIsLastTreeElement() {
            return isLastTreeElement;
        }

        /**
         * @param isLastTreeElement the isLastTreeElement to set
         */
        public void setIsLastTreeElement(Boolean isLastTreeElement) {
            this.isLastTreeElement = isLastTreeElement;
        }

        /**
         * @return the elementType
         */
        public String getElementType() {
            return elementType;
        }

        /**
         * @param elementType the elementType to set
         */
        public void setElementType(String elementType) {
            this.elementType = elementType;
        }

        /**
         * @return the iva
         */
        public Double getIva() {
            return iva;
        }

        /**
         * @param iva the iva to set
         */
        public void setIva(Double iva) {
            this.iva = iva;
        }

        /**
         * @return the impuesto
         */
        public Double getImpuesto() {
            return impuesto;
        }

        /**
         * @param impuesto the impuesto to set
         */
        public void setImpuesto(Double impuesto) {
            this.impuesto = impuesto;
        }

        /**
         * @return the precioRegularNuevo
         */
        public Double getPrecioRegularNuevo() {
            return precioRegularNuevo;
        }

        /**
         * @param precioRegularNuevo the precioRegularNuevo to set
         */
        public void setPrecioRegularNuevo(Double precioRegularNuevo) {
            this.precioRegularNuevo = precioRegularNuevo;
        }

        /**
         * @return the cantidadProducto
         */
        public int getCantidadProducto() {
            return cantidadProducto;
        }

        /**
         * @param cantidadProducto the cantidadProducto to set
         */
        public void setCantidadProducto(int cantidadProducto) {
            this.cantidadProducto = cantidadProducto;
        }

        /**
         * @return the distribucionRebaja
         */
        public double getDistribucionRebaja() {
            return distribucionRebaja;
        }

        /**
         * @param distribucionRebaja the distribucionRebaja to set
         */
        public void setDistribucionRebaja(double distribucionRebaja) {
            this.distribucionRebaja = distribucionRebaja;
        }

        /**
         * @return the distribucionRebajaPorc
         */
        public double getDistribucionRebajaPorc() {
            return distribucionRebajaPorc;
        }

        /**
         * @param distribucionRebajaPorc the distribucionRebajaPorc to set
         */
        public void setDistribucionRebajaPorc(double distribucionRebajaPorc) {
            this.distribucionRebajaPorc = distribucionRebajaPorc;
        }

        /**
         * @return the precioVentaOriginal
         */
        public double getPrecioVentaOriginal() {
            return precioVentaOriginal;
        }

        /**
         * @param precioVentaOriginal the precioVentaOriginal to set
         */
        public void setPrecioVentaOriginal(double precioVentaOriginal) {
            this.precioVentaOriginal = precioVentaOriginal;
        }

        /**
         * @return the hasPrecioPromocion
         */
        public Boolean getHasPrecioPromocion() {
            return hasPrecioPromocion;
        }

        /**
         * @param hasPrecioPromocion the hasPrecioPromocion to set
         */
        public void setHasPrecioPromocion(Boolean hasPrecioPromocion) {
            this.hasPrecioPromocion = hasPrecioPromocion;
        }

        /**
         * @return the tienePrecioPromocion
         */
        public Boolean getTienePrecioPromocion() {
            return tienePrecioPromocion;
        }

        /**
         * @param tienePrecioPromocion the tienePrecioPromocion to set
         */
        public void setTienePrecioPromocion(Boolean tienePrecioPromocion) {
            this.tienePrecioPromocion = tienePrecioPromocion;
        }

        /**
         * @return the lSkuFamilia
         */
        public List<String> getlSkuFamilia() {
            return lSkuFamilia;
        }

        /**
         * @param lSkuFamilia the lSkuFamilia to set
         */
        public void setlSkuFamilia(List<String> lSkuFamilia) {
            this.lSkuFamilia = lSkuFamilia;
        }

        /**
         * @return the tienePorcentajeDescuento
         */
        public Boolean getTienePorcentajeDescuento() {
            return tienePorcentajeDescuento;
        }

        /**
         * @param tienePorcentajeDescuento the tienePorcentajeDescuento to set
         */
        public void setTienePorcentajeDescuento(Boolean tienePorcentajeDescuento) {
            this.tienePorcentajeDescuento = tienePorcentajeDescuento;
        }

        /**
         * @return the rowStyle
         */
        public String getRowStyle() {
            return rowStyle;
        }

        /**
         * @param rowStyle the rowStyle to set
         */
        public void setRowStyle(String rowStyle) {
            this.rowStyle = rowStyle;
        }

        /**
         * @return the esUltimoElementoDeCombo
         */
        public Boolean getEsUltimoElementoDeCombo() {
            return esUltimoElementoDeCombo;
        }

        /**
         * @param esUltimoElementoDeCombo the esUltimoElementoDeCombo to set
         */
        public void setEsUltimoElementoDeCombo(Boolean esUltimoElementoDeCombo) {
            this.esUltimoElementoDeCombo = esUltimoElementoDeCombo;
        }

        /**
         * @return the mgnRegularCombo
         */
        public Double getMgnRegularCombo() {
            return mgnRegularCombo;
        }

        /**
         * @param mgnRegularCombo the mgnRegularCombo to set
         */
        public void setMgnRegularCombo(Double mgnRegularCombo) {
            this.mgnRegularCombo = mgnRegularCombo;
        }

        /**
         * @return the mgnRegularPorcCombo
         */
        public Double getMgnRegularPorcCombo() {
            return mgnRegularPorcCombo;
        }

        /**
         * @param mgnRegularPorcCombo the mgnRegularPorcCombo to set
         */
        public void setMgnRegularPorcCombo(Double mgnRegularPorcCombo) {
            this.mgnRegularPorcCombo = mgnRegularPorcCombo;
        }

        /**
         * @return the porcentajeRetencionCombo
         */
        public Double getPorcentajeRetencionCombo() {
            return porcentajeRetencionCombo;
        }

        /**
         * @param porcentajeRetencionCombo the porcentajeRetencionCombo to set
         */
        public void setPorcentajeRetencionCombo(Double porcentajeRetencionCombo) {
            this.porcentajeRetencionCombo = porcentajeRetencionCombo;
        }

        /**
         * @return the distribucionRebajaCombo
         */
        public Double getDistribucionRebajaCombo() {
            return distribucionRebajaCombo;
        }

        /**
         * @param distribucionRebajaCombo the distribucionRebajaCombo to set
         */
        public void setDistribucionRebajaCombo(Double distribucionRebajaCombo) {
            this.distribucionRebajaCombo = distribucionRebajaCombo;
        }

        /**
         * @return the distribucionRebajaPorcCombo
         */
        public Double getDistribucionRebajaPorcCombo() {
            return distribucionRebajaPorcCombo;
        }

        /**
         * @param distribucionRebajaPorcCombo the distribucionRebajaPorcCombo to set
         */
        public void setDistribucionRebajaPorcCombo(Double distribucionRebajaPorcCombo) {
            this.distribucionRebajaPorcCombo = distribucionRebajaPorcCombo;
        }

        /**
         * @return the mgnPromocionCombo
         */
        public Double getMgnPromocionCombo() {
            return mgnPromocionCombo;
        }

        /**
         * @param mgnPromocionCombo the mgnPromocionCombo to set
         */
        public void setMgnPromocionCombo(Double mgnPromocionCombo) {
            this.mgnPromocionCombo = mgnPromocionCombo;
        }

        /**
         * @return the mgnPromocionPorcCombo
         */
        public Double getMgnPromocionPorcCombo() {
            return mgnPromocionPorcCombo;
        }

        /**
         * @param mgnPromocionPorcCombo the mgnPromocionPorcCombo to set
         */
        public void setMgnPromocionPorcCombo(Double mgnPromocionPorcCombo) {
            this.mgnPromocionPorcCombo = mgnPromocionPorcCombo;
        }

        /**
         * @return the ventaUPTDCombo
         */
        public Double getVentaUPTDCombo() {
            return ventaUPTDCombo;
        }

        /**
         * @param ventaUPTDCombo the ventaUPTDCombo to set
         */
        public void setVentaUPTDCombo(Double ventaUPTDCombo) {
            this.ventaUPTDCombo = ventaUPTDCombo;
        }

        /**
         * @return the ahorroMaximoCombo
         */
        public Double getAhorroMaximoCombo() {
            return ahorroMaximoCombo;
        }

        /**
         * @param ahorroMaximoCombo the ahorroMaximoCombo to set
         */
        public void setAhorroMaximoCombo(Double ahorroMaximoCombo) {
            this.ahorroMaximoCombo = ahorroMaximoCombo;
        }

        /**
         * @return the editandoDos
         */
        public Boolean getEditandoDos() {
            return editandoDos;
        }

        /**
         * @param editandoDos the editandoDos to set
         */
        public void setEditandoDos(Boolean editandoDos) {
            this.editandoDos = editandoDos;
        }

        /**
         * @return the numero2
         */
        public int getNumero2() {
            return numero2;
        }

        /**
         * @param numero2 the numero2 to set
         */
        public void setNumero2(int numero2) {
            this.numero2 = numero2;
        }

        /**
         * @return the abastoInicial2
         */
        public int getAbastoInicial2() {
            return abastoInicial2;
        }

        /**
         * @param abastoInicial2 the abastoInicial2 to set
         */
        public void setAbastoInicial2(int abastoInicial2) {
            this.abastoInicial2 = abastoInicial2;
        }

        /**
         * @return the pruebaUno
         */
        public int getPruebaUno() {
            return pruebaUno;
        }

        /**
         * @param pruebaUno the pruebaUno to set
         */
        public void setPruebaUno(int pruebaUno) {
            this.pruebaUno = pruebaUno;
        }
	}

	public class ConfigMecanica {
		private int idPrograma;
		private int idMecanica;
		private int idComponente;
		private List<ConfigMecanicaDet> lConfigMecanicaDet;
		private double totalCant;
		private double totalPorc;

		public int getIdPrograma() {
			return idPrograma;
		}

		public void setIdPrograma(int idPrograma) {
			this.idPrograma = idPrograma;
		}

		public int getIdMecanica() {
			return idMecanica;
		}

		public void setIdMecanica(int idMecanica) {
			this.idMecanica = idMecanica;
		}

		public int getIdComponente() {
			return idComponente;
		}

		public void setIdComponente(int idComponente) {
			this.idComponente = idComponente;
		}

		public List<ConfigMecanicaDet> getlConfigMecanicaDet() {
			return lConfigMecanicaDet;
		}

		public void setlConfigMecanicaDet(
				List<ConfigMecanicaDet> lConfigMecanicaDet) {
			this.lConfigMecanicaDet = lConfigMecanicaDet;
		}

		public double getTotalCant() {
			totalCant = 0;
			if (lConfigMecanicaDet != null) {
				for (ConfigMecanicaDet confMecDet : lConfigMecanicaDet) {
					totalCant = totalCant + confMecDet.getDistCantidad();
				}
			}
			return totalCant;
		}

		public void setTotalCant(double totalCant) {
			this.totalCant = totalCant;
		}

		public double getTotalPorc() {
			totalPorc = 0;
			if (lConfigMecanicaDet != null) {
				for (ConfigMecanicaDet confMecDet : lConfigMecanicaDet) {
					totalPorc = totalPorc + confMecDet.getDistPorcentaje();
				}
			}
			return totalPorc;
		}

		public void setTotalPorc(double totalPorc) {
			this.totalPorc = totalPorc;
		}
	}

	public class ConfigMecanicaDet {
		private int idCategoria;
		private String descCategoria;
		private double distCantidad;
		private double distPorcentaje;

		public int getIdCategoria() {
			return idCategoria;
		}

		public void setIdCategoria(int idCategoria) {
			this.idCategoria = idCategoria;
		}

		public String getDescCategoria() {
			return descCategoria;
		}

		public void setDescCategoria(String descCategoria) {
			this.descCategoria = descCategoria;
		}

		public double getDistCantidad() {
			return distCantidad;
		}

		public void setDistCantidad(double distCantidad) {
			this.distCantidad = distCantidad;
		}

		public double getDistPorcentaje() {
			return distPorcentaje;
		}

		public void setDistPorcentaje(double distPorcentaje) {
			this.distPorcentaje = distPorcentaje;
		}
	}

	public List<Map<String, String>> getResponseArray() {
		return responseArray;
	}

	public void setResponseArray(List<Map<String, String>> responseArray) {
		this.responseArray = responseArray;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public List<Map<String, String>> getResponseArray2() {
		return responseArray2;
	}

	public void setResponseArray2(List<Map<String, String>> responseArray2) {
		this.responseArray2 = responseArray2;
	}

	public List<ColumnModel> getColumns2() {
		return columns2;
	}

	public void setColumns2(List<ColumnModel> columns2) {
		this.columns2 = columns2;
	}

	public int getVerDisenoProgramaId() {
		return verDisenoProgramaId;
	}

	public void setVerDisenoProgramaId(int verDisenoProgramaId) {
		this.verDisenoProgramaId = verDisenoProgramaId;
	}

	public int getVerDisenoIdMecanica() {
		return verDisenoIdMecanica;
	}

	public void setVerDisenoIdMecanica(int verDisenoIdMecanica) {
		this.verDisenoIdMecanica = verDisenoIdMecanica;
	}

	public int getVerDisenoIdSenalamiento() {
		return verDisenoIdSenalamiento;
	}

	public void setVerDisenoIdSenalamiento(int verDisenoIdSenalamiento) {
		this.verDisenoIdSenalamiento = verDisenoIdSenalamiento;
	}

	public List<String> getVerDisenoGrupoZonas() {
		return verDisenoGrupoZonas;
	}

	public void setVerDisenoGrupoZonas(List<String> verDisenoGrupoZonas) {
		this.verDisenoGrupoZonas = verDisenoGrupoZonas;
	}

	public List<String> getVerDisenoZonas() {
		return verDisenoZonas;
	}

	public void setVerDisenoZonas(List<String> verDisenoZonas) {
		this.verDisenoZonas = verDisenoZonas;
	}

	public List<String> getVerDisenoTiendas() {
		return verDisenoTiendas;
	}

	public void setVerDisenoTiendas(List<String> verDisenoTiendas) {
		this.verDisenoTiendas = verDisenoTiendas;
	}

	public Integer getVerDisenoAutorizado() {
		return verDisenoAutorizado;
	}

	public void setVerDisenoAutorizado(Integer verDisenoAutorizado) {
		this.verDisenoAutorizado = verDisenoAutorizado;
	}

	public String getVerDisenoComentarios() {
		return verDisenoComentarios;
	}

	public void setVerDisenoComentarios(String verDisenoComentarios) {
		this.verDisenoComentarios = verDisenoComentarios;
	}

	public List<DisenosDTO> getDisenosLst() {
		return disenosLst;
	}

	public void setDisenosLst(List<DisenosDTO> disenosLst) {
		this.disenosLst = disenosLst;
	}

	public List<SelectItem> getlMecanicaVerDiseno() {
		return lMecanicaVerDiseno;
	}

	public void setlMecanicaVerDiseno(List<SelectItem> lMecanicaVerDiseno) {
		this.lMecanicaVerDiseno = lMecanicaVerDiseno;
	}

	public Map<String, String> getmZonasVerDiseno() {
		return mZonasVerDiseno;
	}

	public void setmZonasVerDiseno(Map<String, String> mZonasVerDiseno) {
		this.mZonasVerDiseno = mZonasVerDiseno;
	}

	public Map<String, String> getmTiendasVerDiseno() {
		return mTiendasVerDiseno;
	}

	public void setmTiendasVerDiseno(Map<String, String> mTiendasVerDiseno) {
		this.mTiendasVerDiseno = mTiendasVerDiseno;
	}

	public DisenosDTO getDisenoDTO() {
		return disenoDTO;
	}

	public void setDisenoDTO(DisenosDTO disenoDTO) {
		this.disenoDTO = disenoDTO;
	}

	public int getIdListaEstra() {
		return idListaEstra;
	}

	public void setIdListaEstra(int idListaEstra) {
		this.idListaEstra = idListaEstra;
	}

	public List<CatGZone> getmGruposZone() {
		return mGruposZone;
	}

	public void setmGruposZone(List<CatGZone> mGruposZone) {
		this.mGruposZone = mGruposZone;
	}

	public ServiceCatGZone getServiceCatGZone() {
		return serviceCatGZone;
	}

	public void setServiceCatGZone(ServiceCatGZone serviceCatGZone) {
		this.serviceCatGZone = serviceCatGZone;
	}

	public List<CatZone> getmZones() {
        Collections.sort(mZones, new CatZoneSorter());
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

	public ServiceCatZone getServiceCatZone() {
		return serviceCatZone;
	}

	public void setServiceCatZone(ServiceCatZone serviceCatZone) {
		this.serviceCatZone = serviceCatZone;
	}

	public ServiceCatStore getServiceCatStore() {
		return serviceCatStore;
	}

	public void setServiceCatStore(ServiceCatStore serviceCatStore) {
		this.serviceCatStore = serviceCatStore;
	}

	public List<CatGZone> getlGroupZoneImp() {
		return lGroupZoneImp;
	}

	public void setlGroupZoneImp(List<CatGZone> lGroupZoneImp) {
		this.lGroupZoneImp = lGroupZoneImp;
	}

	public List<CatZone> getlZoneImp() {
		return lZoneImp;
	}

	public void setlZoneImp(List<CatZone> lZoneImp) {
		this.lZoneImp = lZoneImp;
	}

	public List<CatPrograma> getlProgramaImp() {
		return lProgramaImp;
	}

	public void setlProgramaImp(List<CatPrograma> lProgramaImp) {
		this.lProgramaImp = lProgramaImp;
	}

	public List<CatPrograma> getlPrograms() {
        Collections.sort(lPrograms, new Comparator<CatPrograma>() {
            @Override
            public int compare(CatPrograma o1, CatPrograma o2) {
                //return (o1.getCode() > o2.getId()) ? 1 : (o1.getId() == o2.getId()) ? 0 : -1;
                 return o1.getNombre().toUpperCase().compareTo(o2.getNombre().toUpperCase());
            }
        });
		return lPrograms;
	}

	public void setlPrograms(List<CatPrograma> lPrograms) {
		this.lPrograms = lPrograms;
	}

	public List<CatCategory> getmCategories() {
		return mCategories;
	}

	public void setmCategories(List<CatCategory> mCategories) {
		this.mCategories = mCategories;
	}

	public ServiceCatCategory getServiceCatCategory() {
		return serviceCatCategory;
	}

	public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
		this.serviceCatCategory = serviceCatCategory;
	}

	public ServiceCatProveedor getServiceCatProveedor() {
		return serviceCatProveedor;
	}

	public void setServiceCatProveedor(ServiceCatProveedor serviceCatProveedor) {
		this.serviceCatProveedor = serviceCatProveedor;
	}

	public List<CatProveedor> getlCatProveedor() {
		return lCatProveedor;
	}

	public void setlCatProveedor(List<CatProveedor> lCatProveedor) {
		this.lCatProveedor = lCatProveedor;
	}

	public List<CatSubCategory> getlCatSubCategories() {
		return lCatSubCategories;
	}

	public void setlCatSubCategories(List<CatSubCategory> lCatSubCategories) {
		this.lCatSubCategories = lCatSubCategories;
	}

	public ServiceCatSubCategory getServiceCatSubCategory() {
		return serviceCatSubCategory;
	}

	public void setServiceCatSubCategory(
			ServiceCatSubCategory serviceCatSubCategory) {
		this.serviceCatSubCategory = serviceCatSubCategory;
	}

	public List<CatLista> getlCatListas() {
		return lCatListas;
	}

	public void setlCatListas(List<CatLista> lCatListas) {
		this.lCatListas = lCatListas;
	}

	public ServiceCatLista getServiceCatLista() {
		return serviceCatLista;
	}

	public void setServiceCatLista(ServiceCatLista serviceCatLista) {
		this.serviceCatLista = serviceCatLista;
	}

	public List<CatCategory> getlCatCategories() {
		return lCatCategories;
	}

	public void setlCatCategories(List<CatCategory> lCatCategories) {
		this.lCatCategories = lCatCategories;
	}

	public List<CatSenal> getmCatSignals() {
		return mCatSignals;
	}

	public void setmCatSignals(List<CatSenal> mCatSignals) {
		this.mCatSignals = mCatSignals;
	}

	public List<CatPromo> getlCatPromociones() {
		return lCatPromociones;
	}

	public void setlCatPromociones(List<CatPromo> lCatPromociones) {
		this.lCatPromociones = lCatPromociones;
	}

	public List<CatTipoPromo> getlCatTipoPromociones() {
		return lCatTipoPromociones;
	}

	public void setlCatTipoPromociones(List<CatTipoPromo> lCatTipoPromociones) {
		this.lCatTipoPromociones = lCatTipoPromociones;
	}

	public ServiceCatPromo getServiceCatPromo() {
		return serviceCatPromo;
	}

	public void setServiceCatPromo(ServiceCatPromo serviceCatPromo) {
		this.serviceCatPromo = serviceCatPromo;
	}

	public ServiceCatTipoPromo getServiceCatTipoPromo() {
		return serviceCatTipoPromo;
	}

	public void setServiceCatTipoPromo(ServiceCatTipoPromo serviceCatTipoPromo) {
		this.serviceCatTipoPromo = serviceCatTipoPromo;
	}

	public ServiceCatSenal getServiceCatSenal() {
		return serviceCatSenal;
	}

	public void setServiceCatSenal(ServiceCatSenal serviceCatSenal) {
		this.serviceCatSenal = serviceCatSenal;
	}

	public List<CatPrograma> getlCatProgramasPrec() {
		return lCatProgramasPrec;
	}

	public void setlCatProgramasPrec(List<CatPrograma> lCatProgramasPrec) {
		this.lCatProgramasPrec = lCatProgramasPrec;
	}

	public List<CatCategory> getlCatCategoriasPrec() {
		return lCatCategoriasPrec;
	}

	public void setlCatCategoriasPrec(List<CatCategory> lCatCategoriasPrec) {
		this.lCatCategoriasPrec = lCatCategoriasPrec;
	}

	public List<CatSubCategory> getlCatSubCategoriasPrec() {
		return lCatSubCategoriasPrec;
	}

	public void setlCatSubCategoriasPrec(
			List<CatSubCategory> lCatSubCategoriasPrec) {
		this.lCatSubCategoriasPrec = lCatSubCategoriasPrec;
	}

	public List<CatGZone> getmCatGruposZonaPrec() {
		return mCatGruposZonaPrec;
	}

	public void setmCatGruposZonaPrec(List<CatGZone> mCatGruposZonaPrec) {
		this.mCatGruposZonaPrec = mCatGruposZonaPrec;
	}

	public List<CatZone> getmCatZonasPrec() {
		return mCatZonasPrec;
	}

	public void setmCatZonasPrec(List<CatZone> mCatZonasPrec) {
		this.mCatZonasPrec = mCatZonasPrec;
	}

	public List<CatStore> getmCatTiendasPrec() {
		return mCatTiendasPrec;
	}

	public void setmCatTiendasPrec(List<CatStore> mCatTiendasPrec) {
		this.mCatTiendasPrec = mCatTiendasPrec;
	}

	public List<CatLista> getlCatListasPrec() {
		return lCatListasPrec;
	}

	public void setlCatListasPrec(List<CatLista> lCatListasPrec) {
		this.lCatListasPrec = lCatListasPrec;
	}

	public List<CatCategory> getlCatCategEstra() {
		return lCatCategEstra;
	}

	public void setlCatCategEstra(List<CatCategory> lCatCategEstra) {
		this.lCatCategEstra = lCatCategEstra;
	}

	public List<CatCategory> getlCatCategEstra2() {
		return lCatCategEstra2;
	}

	public void setlCatCategEstra2(List<CatCategory> lCatCategEstra2) {
		this.lCatCategEstra2 = lCatCategEstra2;
	}

	public List<CatGZone> getmCatGposZonaEstra() {
		return mCatGposZonaEstra;
	}

	public void setmCatGposZonaEstra(List<CatGZone> mCatGposZonaEstra) {
		this.mCatGposZonaEstra = mCatGposZonaEstra;
	}

	public List<CatZone> getmCatZonasEstra() {
		return mCatZonasEstra;
	}

	public void setmCatZonasEstra(List<CatZone> mCatZonasEstra) {
		this.mCatZonasEstra = mCatZonasEstra;
	}

	public List<CatZone> getmCatZonasVerDiseno() {
		return mCatZonasVerDiseno;
	}

	public void setmCatZonasVerDiseno(List<CatZone> mCatZonasVerDiseno) {
		this.mCatZonasVerDiseno = mCatZonasVerDiseno;
	}

	public List<CatStore> getmCatTiendasVerDiseno() {
		return mCatTiendasVerDiseno;
	}

	public void setmCatTiendasVerDiseno(List<CatStore> mCatTiendasVerDiseno) {
		this.mCatTiendasVerDiseno = mCatTiendasVerDiseno;
	}

	public List<CatItem> getListCatItem() {
		return listCatItem;
	}

	public void setListCatItem(List<CatItem> listCatItem) {
		this.listCatItem = listCatItem;
	}

	public ServiceCatItem getServiceCatItem() {
		return serviceCatItem;
	}

	public void setServiceCatItem(ServiceCatItem serviceCatItem) {
		this.serviceCatItem = serviceCatItem;
	}

	public List<CatListDet> getlCatSKUEstra() {
		return lCatSKUEstra;
	}

	public void setlCatSKUEstra(List<CatListDet> lCatSKUEstra) {
		this.lCatSKUEstra = lCatSKUEstra;
	}

	public List<CatListDet> getlCatSKUEstra2() {
		return lCatSKUEstra2;
	}

	public void setlCatSKUEstra2(List<CatListDet> lCatSKUEstra2) {
		this.lCatSKUEstra2 = lCatSKUEstra2;
	}

	public ServiceCatListDet getServiceCatListDet() {
		return serviceCatListDet;
	}

	public void setServiceCatListDet(ServiceCatListDet serviceCatListDet) {
		this.serviceCatListDet = serviceCatListDet;
	}

	public List<CatListDet> getmCatSku() {
		if (mCatSku == null){
			mCatSku = MBUtil.cargarcomboCatListDets(serviceCatListDet);
		}
		return mCatSku;
	}

	public void setmCatSku(List<CatListDet> mCatSku) {
		this.mCatSku = mCatSku;
	}

	public List<CatListDet> getmCatSkuPrec() {
		return mCatSkuPrec;
	}

	public void setmCatSkuPrec(List<CatListDet> mCatSkuPrec) {
		this.mCatSkuPrec = mCatSkuPrec;
	}

	public String getDescZona() {
		return descZona;
	}

	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

	public List<CatItem> getListCatItemDesc() {
		return listCatItemDesc;
	}

	public void setListCatItemDesc(List<CatItem> listCatItemDesc) {
		this.listCatItemDesc = listCatItemDesc;
	}

	public ServiceRelItemStore getServiceRelItemStore() {
		return serviceRelItemStore;
	}

	public void setServiceRelItemStore(ServiceRelItemStore serviceRelItemStore) {
		this.serviceRelItemStore = serviceRelItemStore;
	}

	public TreeNode gettProgramaDisenio() {
		return tProgramaDisenio;
	}

	public void settProgramaDisenio(TreeNode tProgramaDisenio) {
		this.tProgramaDisenio = tProgramaDisenio;
	}

	public ServiceRelUsuariosCategorias getServiceRelUsuariosCategorias() {
		return serviceRelUsuariosCategorias;
	}

	public void setServiceRelUsuariosCategorias(
			ServiceRelUsuariosCategorias serviceRelUsuariosCategorias) {
		this.serviceRelUsuariosCategorias = serviceRelUsuariosCategorias;
	}

	/**
	 * @return the disableForList
	 */
	public boolean isDisableForList() {
		return disableForList;
	}

	/**
	 * @param disableForList the disableForList to set
	 */
	public void setDisableForList(boolean disableForList) {
		this.disableForList = disableForList;
	}

	/**
	 * @return the disableList
	 */
	public boolean isDisableList() {
		return disableList;
	}

	/**
	 * @param disableList the disableList to set
	 */
	public void setDisableList(boolean disableList) {
		this.disableList = disableList;
	}

	public String getLabelDisenoGlobal() {
		return labelDisenoGlobal;
	}

	public void setLabelDisenoGlobal(String labelDisenoGlobal) {
		this.labelDisenoGlobal = labelDisenoGlobal;
	}

	public Integer getIdStatusDisenoGlobal() {
		return idStatusDisenoGlobal;
	}

	public void setIdStatusDisenoGlobal(Integer idStatusDisenoGlobal) {
		this.idStatusDisenoGlobal = idStatusDisenoGlobal;
	}

	public String getTitleDise?oGlobal() {
		return titleDise?oGlobal;
	}

	public void setTitleDise?oGlobal(String titleDise?oGlobal) {
		this.titleDise?oGlobal = titleDise?oGlobal;
	}

    public ServiceRelItemLista getServiceRelItemLista() {
        return serviceRelItemLista;
    }

    public void setServiceRelItemLista(ServiceRelItemLista serviceRelItemLista) {
        this.serviceRelItemLista = serviceRelItemLista;
    }

    public List<CatSenal> getSenalListSelected() {
        return senalListSelected;
    }

    public void setSenalListSelected(List<CatSenal> senalListSelected) {
        this.senalListSelected = senalListSelected;
    }

    /**
     * @return the estatusCapturaAll
     */
    public int getEstatusCapturaAll() {
        return estatusCapturaAll;
    }

    /**
     * @param estatusCapturaAll the estatusCapturaAll to set
     */
    public void setEstatusCapturaAll(int estatusCapturaAll) {
        this.estatusCapturaAll = estatusCapturaAll;
    }

    /**
     * @return the estatusRevisionAll
     */
    public int getEstatusRevisionAll() {
        return estatusRevisionAll;
    }

    /**
     * @param estatusRevisionAll the estatusRevisionAll to set
     */
    public void setEstatusRevisionAll(int estatusRevisionAll) {
        this.estatusRevisionAll = estatusRevisionAll;
    }
    
    /**
     * Metodo para actualizar el estatus de revision de todos los elementos del grid de precios
     */
    
    private void updateListDTO(List<PreciosPromocionDTO> list, TreeNode node, int type) throws Exception{
        if(node.getChildCount() > 0){
            for(TreeNode child : node.getChildren()){
                updateListDTO(list, child, type);
            }
        }else{
            ArqComponente e = (ArqComponente)node.getData();
            if(e.getPreciosPromocionDTO() != null && e.getElementType().contains("P")){
                PreciosPromocionDTO dto = e.getPreciosPromocionDTO();
                dto.setPkComp(e.getIdComponente());
                dto.setZonaId(e.getIdZona());
                dto.setPkMec(idMecanicaPrec);
                
                if(serviceArquitecturaSeven.existsPrecioPromocion(dto)){
                    if(type == 1){
                    dto.setEstatusRevision(this.estatusRevisionAll);
                    }else{
                        dto.setEstatusCaptura(this.estatusCapturaAll);
                    }
                    list.add(dto);
                }else{
                    LOG.warn("Registro no tiene precio promocion: Mecanica: " + dto.getPkMec() + ", zona: " + dto.getZonaId() + ", componente: " + dto.getPkComp());
                }
            }
        }
    }
    
    public void updateAllRevisionStatus(){
        try{
            List<PreciosPromocionDTO> toUpdateList = new ArrayList<PreciosPromocionDTO>();
            //this.updateListDTO(toUpdateList, raizPrecio, 1);
            
            for(ArqComponente e : this.lArqCompPrecProm){
               PreciosPromocionDTO dto = e.getPreciosPromocionDTO();
               
                if(serviceArquitecturaSeven.existsPrecioPromocion(dto)){
                    dto.setEstatusRevision(this.estatusRevisionAll);

                    toUpdateList.add(dto);
                }else{
                    LOG.warn("Registro no tiene precio promocion: Mecanica: " + dto.getPkMec() + ", zona: " + dto.getZonaId() + ", componente: " + dto.getPkComp());
                }
            }
            
            //serviceArquitecturaSeven.updatePrecios(toUpdateList);
            this.updatePrecioPromocion(toUpdateList);
            
            Messages.mensajeSatisfactorio("Estatus de Revision Actualizado Exitosamente", "Estatus de Revision Actualizado exitosamente");
        
            if(this.estatusRevisionAll == 4){
                precioRechazado();
            }
            
            this.preparePreciosView();
            
        }catch(Exception ex){
            LOG.error(ex.getMessage());
            Messages.mensajeAlerta("Ocurrio un error al actualizar el estatus de revision", "Ocurrio un error al actualizar el estatus de revision");
        } 
    }
    
    public void updateAllCaptureStatus(){
        try{
            List<PreciosPromocionDTO> toUpdateList = new ArrayList<PreciosPromocionDTO>();
            this.updateListDTO(toUpdateList, raizPrecio, 2);
            
            //serviceArquitecturaSeven.updatePrecios(toUpdateList);
            this.updatePrecioPromocion(toUpdateList);
            
            Messages.mensajeSatisfactorio("Estatus de Captura Actualizado Exitosamente", "Estatus de Captura Actualizado exitosamente");	
        
            if(estatusRevisionAll == 4){
                precioRechazado();
            }
        
        }catch(Exception ex){
            LOG.error(ex.getMessage());
            Messages.mensajeAlerta("Ocurrio un error al actualizar el estatus de revision", "Ocurrio un error al actualizar el estatus de revision");
        }
    }
    
    private void updatePrecioPromocion(List<PreciosPromocionDTO> precios){
        for(PreciosPromocionDTO p : precios){
            List<PreciosPromocionDTO> lt = new ArrayList<PreciosPromocionDTO>();
            LOG.info("guardando: pkMecanica: " + p.getPkMec() + ", zona: " + p.getZonaId() + ", componente: " + p.getPkComp());
            lt.add(p);
            try{
                serviceArquitecturaSeven.updatePrecios(lt);
                LOG.info("Precio Promocion actualizado");
            }catch(Exception e){
                LOG.error("Error al actualizar precio promocion: " + e.getMessage());
            }
        }
    }

    public void onNodeUnselect(NodeExpandEvent event){
        LOG.info("deseleccionando nodo raiz");
        event.getTreeNode().setSelected(false);
    }

    public void nodeExpand(NodeExpandEvent event) {
        LOG.info("expandiendo nodo raiz");
        event.getTreeNode().setExpanded(true);
        return;
    }

    public void nodeCollapse(NodeCollapseEvent event) {
        LOG.info("colapsando nodo raiz");
        event.getTreeNode().setExpanded(false);
        return;
    }

    /*eventos ajax de pantalla de precios*/
    public void priceExpand(NodeExpandEvent event) {
        LOG.info("Expandiendo nodo precio");
        event.getTreeNode().setExpanded(true);
    }

    public void priceCollapse(NodeCollapseEvent event) {
        LOG.info("colapsando nodo precio");
        event.getTreeNode().setExpanded(false);
    }
    public void priceSelect(NodeSelectEvent event) {
        LOG.info("Seleccionando precio");
    }
    public void priceUnselect(NodeUnselectEvent event) {
        LOG.info("DesSeleccionando precio");
    }

    /**
     * @return the raizPrecioPop
     */
    public TreeNode getRaizPrecioPop() {
        return raizPrecioPop;
    }

    /**
     * @param raizPrecioPop the raizPrecioPop to set
     */
    public void setRaizPrecioPop(TreeNode raizPrecioPop) {
        this.raizPrecioPop = raizPrecioPop;
    }

    /**
     * @return the verDisenoLSenalamiento
     */
    public List<String> getVerDisenoLSenalamiento() {
        return verDisenoLSenalamiento;
    }

    /**
     * @param verDisenoLSenalamiento the verDisenoLSenalamiento to set
     */
    public void setVerDisenoLSenalamiento(List<String> verDisenoLSenalamiento) {
        this.verDisenoLSenalamiento = verDisenoLSenalamiento;
    }

    /**
     * @return the cantidadDistribucionRebaja
     */
    public double getCantidadDistribucionRebaja() {
        return cantidadDistribucionRebaja;
    }

    /**
     * @param cantidadDistribucionRebaja the cantidadDistribucionRebaja to set
     */
    public void setCantidadDistribucionRebaja(double cantidadDistribucionRebaja) {
        this.cantidadDistribucionRebaja = cantidadDistribucionRebaja;
    }

    /**
     * @return the porcentajeDistribucionRebaja
     */
    public double getPorcentajeDistribucionRebaja() {
        return porcentajeDistribucionRebaja;
    }

    /**
     * @param porcentajeDistribucionRebaja the porcentajeDistribucionRebaja to set
     */
    public void setPorcentajeDistribucionRebaja(double porcentajeDistribucionRebaja) {
        this.porcentajeDistribucionRebaja = porcentajeDistribucionRebaja;
    }

    /**
     * @return the valueDesignDescription
     */
    public String getValueDesignDescription() {
        return valueDesignDescription;
    }

    /**
     * @param valueDesignDescription the valueDesignDescription to set
     */
    public void setValueDesignDescription(String valueDesignDescription) {
        this.valueDesignDescription = valueDesignDescription;
    }

    /**
     * @return the lDesginDescriptions
     */
    public List<String> getlDesginDescriptions() {
        return lDesginDescriptions;
    }

    /**
     * @param lDesginDescriptions the lDesginDescriptions to set
     */
    public void setlDesginDescriptions(List<String> lDesginDescriptions) {
        this.lDesginDescriptions = lDesginDescriptions;
    }
    
    public void postProcessCategoryXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        
        cellStyle.setFont(font);
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

    /**
     * @return the lComponentesPrecioPromocion
     */
    public List<SelectItem> getlComponentesPrecioPromocion() {
        return lComponentesPrecioPromocion;
    }

    /**
     * @param lComponentesPrecioPromocion the lComponentesPrecioPromocion to set
     */
    public void setlComponentesPrecioPromocion(List<SelectItem> lComponentesPrecioPromocion) {
        this.lComponentesPrecioPromocion = lComponentesPrecioPromocion;
    }

    /**
     * @return the componentePrecioPromocionSeleccionado
     */
    public int getComponentePrecioPromocionSeleccionado() {
        return componentePrecioPromocionSeleccionado;
    }

    /**
     * @param componentePrecioPromocionSeleccionado the componentePrecioPromocionSeleccionado to set
     */
    public void setComponentePrecioPromocionSeleccionado(int componentePrecioPromocionSeleccionado) {
        this.componentePrecioPromocionSeleccionado = componentePrecioPromocionSeleccionado;
    }

    /**
     * @return the porcentajeDescuento
     */
    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * @param porcentajeDescuento the porcentajeDescuento to set
     */
    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * @return the porcentajeRetencion
     */
    public Double getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    /**
     * @param porcentajeRetencion the porcentajeRetencion to set
     */
    public void setPorcentajeRetencion(Double porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
    }

    /**
     * @return the elasticidad
     */
    public Double getElasticidad() {
        return elasticidad;
    }

    /**
     * @param elasticidad the elasticidad to set
     */
    public void setElasticidad(Double elasticidad) {
        this.elasticidad = elasticidad;
    }

    /**
     * @return the porcentajeDescuentoStr
     */
    public String getPorcentajeDescuentoStr() {
        return porcentajeDescuentoStr;
    }

    /**
     * @param porcentajeDescuentoStr the porcentajeDescuentoStr to set
     */
    public void setPorcentajeDescuentoStr(String porcentajeDescuentoStr) {
        this.porcentajeDescuentoStr = porcentajeDescuentoStr;
    }

    /**
     * @return the porcentajeRetencionStr
     */
    public String getPorcentajeRetencionStr() {
        return porcentajeRetencionStr;
    }

    /**
     * @param porcentajeRetencionStr the porcentajeRetencionStr to set
     */
    public void setPorcentajeRetencionStr(String porcentajeRetencionStr) {
        this.porcentajeRetencionStr = porcentajeRetencionStr;
    }

    /**
     * @return the elasticidadStr
     */
    public String getElasticidadStr() {
        return elasticidadStr;
    }

    /**
     * @param elasticidadStr the elasticidadStr to set
     */
    public void setElasticidadStr(String elasticidadStr) {
        this.elasticidadStr = elasticidadStr;
    }

    /**
     * @return the esCombo
     */
    public Boolean getEsCombo() {
        return esCombo;
    }

    /**
     * @param esCombo the esCombo to set
     */
    public void setEsCombo(Boolean esCombo) {
        this.esCombo = esCombo;
    }
    
    public void testx(){
        LOG.info("blabla");
    }

    /**
     * @return the esCategory
     */
    public Boolean getEsCategory() {
        return esCategory;
    }

    /**
     * @param esCategory the esCategory to set
     */
    public void setEsCategory(Boolean esCategory) {
        this.esCategory = esCategory;
    }

    /**
     * @return the itemIdList
     */
    public List<String> getItemIdList() {
        return itemIdList;
    }

    /**
     * @param itemIdList the itemIdList to set
     */
    public void setItemIdList(List<String> itemIdList) {
        this.itemIdList = itemIdList;
    }

    /**
     * @return the ppCatZonesSelected
     */
    public List<String> getPpCatZonesSelected() {
        return ppCatZonesSelected;
    }

    /**
     * @param ppCatZonesSelected the ppCatZonesSelected to set
     */
    public void setPpCatZonesSelected(List<String> ppCatZonesSelected) {
        this.ppCatZonesSelected = ppCatZonesSelected;
    }

	public ServiceMenuAndRoles getServiceMenuAndRoles() {
		return serviceMenuAndRoles;
	}

	public void setServiceMenuAndRoles(ServiceMenuAndRoles serviceMenuAndRoles) {
		this.serviceMenuAndRoles = serviceMenuAndRoles;
	}

	public boolean isValidarPrecioPromocion() {
		return validarPrecioPromocion;
	}

	public void setValidarPrecioPromocion(boolean validarPrecioPromocion) {
		this.validarPrecioPromocion = validarPrecioPromocion;
	}

	public boolean isOverridePrecioPromocion() {
		return overridePrecioPromocion;
	}

	public void setOverridePrecioPromocion(boolean overridePrecioPromocion) {
		this.overridePrecioPromocion = overridePrecioPromocion;
	}
    
}
