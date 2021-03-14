package com.adinfi.seven.presentation.views;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblArticulosEspacios;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblPrensaSistemaVenta;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceReporte;
import com.adinfi.seven.persistence.dto.CabeceraReporteDTO;
import com.adinfi.seven.persistence.dto.CatItemDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.ColumnModel;
import com.adinfi.seven.persistence.dto.MenuReportes;
import com.adinfi.seven.persistence.dto.ReporteArticulo;
import com.adinfi.seven.persistence.dto.ReporteArticuloSinFotoDescripcion;
import com.adinfi.seven.persistence.dto.ReporteInventarioFiltroDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioSkuDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaFiltroDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaSkuDTO;
import com.adinfi.seven.persistence.dto.SistemaVentaDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.DTOUtil;
import com.adinfi.seven.presentation.views.util.ExportPDFUtil;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.ReporteInventarioUtil;
import com.adinfi.seven.presentation.views.util.ReporteVentasUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;

public class ReporteController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2146710067599408215L;
	private Logger LOG = Logger.getLogger(ReporteController.class);
	private TreeNode raiz;
	private TreeNode selectedNode;
	private boolean reporteArticulos = false;
	private boolean reporteArticulosSinFotoDescripcion = false;
	private boolean reporteVenta = false;
	private boolean reporteInventario = false;

	private ServiceDynamicCatalogs serviceDynamicCatalogs;

	private ServiceReporte serviceReporte;

	/**
	 * Atributos Comunes
	 */

	private CabeceraReporteDTO cabecera;

	private String tipo;

	private String folio;

	private String opcionTotalizar;
	
	private Boolean reporteHide;
	
	private Map<String,String> mecanicasMap = new HashMap<String, String>();

	/**
	 * Atributos Reporte Venta
	 */

	private List<ReporteVentaSkuDTO> infoReporteVentaSku;

	private List<ReporteVentaFiltroDTO> infoReporteVentaFiltro;

	private Boolean reporteVentaSKU;

	private Boolean reporteVentaFiltro;

	/**
	 * Atributos Reporte Inventario
	 */

	private List<ReporteInventarioSkuDTO> infoReporteInventarioSku;

	private List<ReporteInventarioFiltroDTO> infoReporteInventarioFiltro;

	private Boolean reporteInventarioSKU;

	private Boolean reporteInventarioFiltro;

	/**
	 * Atributos Reporte Articulos
	 */
	private final static List<String> VALID_COLUMN_KEYS_FOLLETO = Arrays
			.asList(Constants.COLUMNA_SKU, Constants.COLUMNA_CATEGORIA,
					Constants.COLUMNA_DESCRIPCION, Constants.COLUMNA_HOJA,
					Constants.COLUMNA_PRECIO,
					Constants.COLUMNA_SISTEMA_DE_VENTA);

	private final static List<String> VALID_COLUMN_KEYS_PRENSA = Arrays.asList(
			Constants.COLUMNA_SKU, Constants.COLUMNA_CATEGORIA,
			Constants.COLUMNA_DESCRIPCION, Constants.COLUMNA_ESPACIO,
			Constants.COLUMNA_PRECIO, Constants.COLUMNA_SISTEMA_DE_VENTA);

	private final static List<String> VALID_COLUMN_KEYS_FOLLETO2 = Arrays
			.asList(Constants.COLUMNA_SKU, Constants.COLUMNA_CATEGORIA,
					Constants.COLUMNA_HOJA, Constants.COLUMNA_SISTEMA_DE_VENTA,
					Constants.COLUMNA_FALTANTE_DE);

	private final static List<String> VALID_COLUMN_KEYS_PRENSA2 = Arrays
			.asList(Constants.COLUMNA_SKU, Constants.COLUMNA_CATEGORIA,
					Constants.COLUMNA_ESPACIO,
					Constants.COLUMNA_SISTEMA_DE_VENTA,
					Constants.COLUMNA_FALTANTE_DE);

	private String columnTemplatePrensa = "sku categoria descripcion espacio precio sistemaVenta";
	private String columnTemplateFolleto = "sku categoria descripcion hoja precio sistemaVenta";
	private String columnTemplatePrensa2 = "sku categoria espacio sistemaVenta faltanteDe";
	private String columnTemplateFolleto2 = "sku categoria hoja sistemaVenta faltanteDe";
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private List<?> listaArticulos;
	private boolean table;

	
	
	/**
	 * @return the reporteHide
	 */
	public Boolean getReporteHide() {
		return reporteHide;
	}

	/**
	 * @param reporteHide the reporteHide to set
	 */
	public void setReporteHide(Boolean reporteHide) {
		this.reporteHide = reporteHide;
	}

	/**
	 * @return the table
	 */
	public boolean isTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(boolean table) {
		this.table = table;
	}

	/**
	 * @return the columns
	 */
	public List<ColumnModel> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	/**
	 * @return the listaArticulos
	 */
	public List<?> getListaArticulos() {
		return listaArticulos;
	}

	/**
	 * @param listaArticulos
	 *            the listaArticulos to set
	 */
	public void setListaArticulos(List<?> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	/**
	 * @return the reporteArticulos
	 */
	public boolean isReporteArticulos() {
		return reporteArticulos;
	}

	/**
	 * @return the reporteArticulosSinFotoDescripcion
	 */
	public boolean isReporteArticulosSinFotoDescripcion() {
		return reporteArticulosSinFotoDescripcion;
	}

	/**
	 * @return the reporteVenta
	 */
	public boolean isReporteVenta() {
		return reporteVenta;
	}

	@PostConstruct
	private void init() {
		MenuReportes tree = new MenuReportes();
		raiz = tree.getRaiz();
		setMecanicasMap( MBUtil.cargarcombosDescripcion( Constants.CAT_MECANICA , this.serviceDynamicCatalogs));
	}

	public void onNodeExpand(NodeExpandEvent event) {

	}

	public void onNodeCollapse(NodeCollapseEvent event) {

	}

	public void onNodeSelect(NodeSelectEvent event) {
		if (event.getTreeNode().toString()
				.equals(Constants.REPORTE_DE_ARTICULOS)) {
			reporteArticulos = true;
			reporteArticulosSinFotoDescripcion = false;
			reporteVenta = false;
			reporteInventario = false;
			inicializarVariablesArticulos();

		}
		if (event.getTreeNode().toString()
				.equals(Constants.REPORTE_DE_INVENTARIO)) {
			reporteArticulos = false;
			reporteArticulosSinFotoDescripcion = false;
			reporteVenta = false;
			reporteInventario = true;
			inicializarVariablesInventario();
		}
		if (event.getTreeNode().toString().equals(Constants.REPORTE_DE_VENTAS)) {
			reporteArticulos = false;
			reporteArticulosSinFotoDescripcion = false;
			reporteVenta = true;
			reporteInventario = false;
			inicializarVariablesVenta();
		}
		if (event.getTreeNode().toString()
				.equals(Constants.REPORTES_SIN_FOTO_DESCRIPCION)) {
			reporteArticulos = false;
			reporteArticulosSinFotoDescripcion = true;
			reporteVenta = false;
			reporteInventario = false;
			inicializarVariablesArticulos();

		}
		if (event.getTreeNode().toString().equals(Constants.REPORTES)) {
			reporteArticulos = false;
			reporteArticulosSinFotoDescripcion = false;
			reporteVenta = false;
			reporteInventario = false;
		}
	}

	public void onNodeUnselect(NodeUnselectEvent event) {

	}

	/**
	 * @return the raiz
	 */
	public TreeNode getRaiz() {
		return raiz;
	}

	/**
	 * @param raiz
	 *            the raiz to set
	 */
	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
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
	 * @return the reporteInventario
	 */
	public boolean isReporteInventario() {
		return reporteInventario;
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
	 * @return the serviceReporte
	 */
	public ServiceReporte getServiceReporte() {
		return serviceReporte;
	}

	/**
	 * @param serviceReporte
	 *            the serviceReporte to set
	 */
	public void setServiceReporte(ServiceReporte serviceReporte) {
		this.serviceReporte = serviceReporte;
	}

	/**
	 * @return the cabecera
	 */
	public CabeceraReporteDTO getCabecera() {
		return cabecera;
	}

	/**
	 * @param cabecera
	 *            the cabecera to set
	 */
	public void setCabecera(CabeceraReporteDTO cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio
	 *            the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return the opcionTotalizar
	 */
	public String getOpcionTotalizar() {
		return opcionTotalizar;
	}

	/**
	 * @param opcionTotalizar
	 *            the opcionTotalizar to set
	 */
	public void setOpcionTotalizar(String opcionTotalizar) {
		this.opcionTotalizar = opcionTotalizar;
	}


	/**
	 * @return the infoReporteVentaSku
	 */
	public List<ReporteVentaSkuDTO> getInfoReporteVentaSku() {
		return infoReporteVentaSku;
	}

	/**
	 * @param infoReporteVentaSku
	 *            the infoReporteVentaSku to set
	 */
	public void setInfoReporteVentaSku(
			List<ReporteVentaSkuDTO> infoReporteVentaSku) {
		this.infoReporteVentaSku = infoReporteVentaSku;
	}

	/**
	 * @return the infoReporteVentaFiltro
	 */
	public List<ReporteVentaFiltroDTO> getInfoReporteVentaFiltro() {
		return infoReporteVentaFiltro;
	}

	/**
	 * @param infoReporteVentaFiltro
	 *            the infoReporteVentaFiltro to set
	 */
	public void setInfoReporteVentaFiltro(
			List<ReporteVentaFiltroDTO> infoReporteVentaFiltro) {
		this.infoReporteVentaFiltro = infoReporteVentaFiltro;
	}

	/**
	 * @return the reporteVentaSKU
	 */
	public Boolean getReporteVentaSKU() {
		return reporteVentaSKU;
	}

	/**
	 * @param reporteVentaSKU
	 *            the reporteVentaSKU to set
	 */
	public void setReporteVentaSKU(Boolean reporteVentaSKU) {
		this.reporteVentaSKU = reporteVentaSKU;
	}

	/**
	 * @return the reporteVentaFiltro
	 */
	public Boolean getReporteVentaFiltro() {
		return reporteVentaFiltro;
	}

	/**
	 * @param reporteVentaFiltro
	 *            the reporteVentaFiltro to set
	 */
	public void setReporteVentaFiltro(Boolean reporteVentaFiltro) {
		this.reporteVentaFiltro = reporteVentaFiltro;
	}

	/**
	 * @return the infoReporteInventarioSku
	 */
	public List<ReporteInventarioSkuDTO> getInfoReporteInventarioSku() {
		return infoReporteInventarioSku;
	}

	/**
	 * @param infoReporteInventarioSku
	 *            the infoReporteInventarioSku to set
	 */
	public void setInfoReporteInventarioSku(
			List<ReporteInventarioSkuDTO> infoReporteInventarioSku) {
		this.infoReporteInventarioSku = infoReporteInventarioSku;
	}

	/**
	 * @return the infoReporteInventarioFiltro
	 */
	public List<ReporteInventarioFiltroDTO> getInfoReporteInventarioFiltro() {
		return infoReporteInventarioFiltro;
	}

	/**
	 * @param infoReporteInventarioFiltro
	 *            the infoReporteInventarioFiltro to set
	 */
	public void setInfoReporteInventarioFiltro(
			List<ReporteInventarioFiltroDTO> infoReporteInventarioFiltro) {
		this.infoReporteInventarioFiltro = infoReporteInventarioFiltro;
	}

	/**
	 * @return the reporteInventarioSKU
	 */
	public Boolean getReporteInventarioSKU() {
		return reporteInventarioSKU;
	}

	/**
	 * @param reporteInventarioSKU
	 *            the reporteInventarioSKU to set
	 */
	public void setReporteInventarioSKU(Boolean reporteInventarioSKU) {
		this.reporteInventarioSKU = reporteInventarioSKU;
	}

	/**
	 * @return the reporteInventarioFiltro
	 */
	public Boolean getReporteInventarioFiltro() {
		return reporteInventarioFiltro;
	}

	/**
	 * @param reporteInventarioFiltro
	 *            the reporteInventarioFiltro to set
	 */
	public void setReporteInventarioFiltro(Boolean reporteInventarioFiltro) {
		this.reporteInventarioFiltro = reporteInventarioFiltro;
	}

	/**
	 * @param reporteArticulos
	 *            the reporteArticulos to set
	 */
	public void setReporteArticulos(boolean reporteArticulos) {
		this.reporteArticulos = reporteArticulos;
	}

	/**
	 * @param reporteArticulosSinFotoDescripcion
	 *            the reporteArticulosSinFotoDescripcion to set
	 */
	public void setReporteArticulosSinFotoDescripcion(
			boolean reporteArticulosSinFotoDescripcion) {
		this.reporteArticulosSinFotoDescripcion = reporteArticulosSinFotoDescripcion;
	}

	/**
	 * @param reporteVenta
	 *            the reporteVenta to set
	 */
	public void setReporteVenta(boolean reporteVenta) {
		this.reporteVenta = reporteVenta;
	}

	/**
	 * @param reporteInventario
	 *            the reporteInventario to set
	 */
	public void setReporteInventario(boolean reporteInventario) {
		this.reporteInventario = reporteInventario;
	}

	public SelectItem[] getTipos() {
		SelectItem[] items = null;
		try {
			items = MBUtil.getSelectItems(
					MBUtil.getTiposMedios(serviceDynamicCatalogs), true);
		} catch (Exception e) {
			LOG.info(e);
		}

		return items;
	}

	/**
	 * FUNCIONALIDAD
	 */

	private void inicializarVariablesVenta() {

		tipo 					= Constants.EMPTY;
		folio 					= Constants.EMPTY;
		opcionTotalizar 		= Constants.OPCION_SKU;
		infoReporteVentaSku 	= new ArrayList<ReporteVentaSkuDTO>();
		infoReporteVentaFiltro 	= new ArrayList<ReporteVentaFiltroDTO>();
		reporteVentaSKU 		= Boolean.TRUE;
		reporteVentaFiltro 		= Boolean.FALSE;
		reporteHide 			= Boolean.TRUE;
	}

	private void inicializarVariablesInventario() {

		tipo 						= Constants.EMPTY;
		folio 						= Constants.EMPTY;
		opcionTotalizar 			= Constants.OPCION_SKU;
		infoReporteInventarioSku 	= new ArrayList<ReporteInventarioSkuDTO>();		
		infoReporteInventarioFiltro = new ArrayList<ReporteInventarioFiltroDTO>();
		reporteInventarioSKU 		= Boolean.TRUE;
		reporteInventarioFiltro 	= Boolean.FALSE;
		reporteHide 				= Boolean.TRUE;
		
	}

	public void generarReporteVenta() {

		try {

			if (Constants.FOLLETO.equals(tipo)) {
				infoReporteVentaSku = serviceReporte
						.calcularReporteVentaSKUFolleto(getFolio());
				cabecera = serviceReporte.getCabeceraFolleto(getFolio());

			} else if (Constants.PRENSA.equals(tipo)) {
				infoReporteVentaSku = serviceReporte
						.calcularReporteVentaSKUPrensa(getFolio());
				cabecera = serviceReporte.getCabeceraPrensa(getFolio());
			} else {
				Messages.mensajeAlerta(Constants.MENSAJE_REPORTE_TIPO_MEDIO);
			}

			DTOUtil.loadDescripcionAllItemsVenta(infoReporteVentaSku,
					serviceDynamicCatalogs);
			reporteVentaSKU = ocultarPanelesVenta();
			opcionTotalizar = Constants.OPCION_SKU;
			reporteHide = Boolean.FALSE;

		} catch (GeneralException e) {
			Messages.mensajeAlerta(e.getMessage());
		} catch (Exception e) {
			Messages.mensajeAlerta(e.getMessage());
		}

	}

	public void generarReporteInventario() throws Exception {

		try {

			if (Constants.FOLLETO.equals(tipo)) {
				infoReporteInventarioSku = serviceReporte
						.calcularReporteInventarioSKUFolleto(getFolio());
				cabecera = serviceReporte.getCabeceraFolleto(getFolio());

			} else if (Constants.PRENSA.equals(tipo)) {
				infoReporteInventarioSku = serviceReporte
						.calcularReporteInventarioSKUPrensa(getFolio());
				cabecera = serviceReporte.getCabeceraPrensa(getFolio());
			} else {
				Messages.mensajeAlerta(Constants.MENSAJE_REPORTE_TIPO_MEDIO);
			}

			DTOUtil.loadDescripcionAllItemsInventario(infoReporteInventarioSku,
					serviceDynamicCatalogs);
			reporteInventarioSKU = ocultarPanelesInventario();
			opcionTotalizar = Constants.OPCION_SKU;
			reporteHide = Boolean.FALSE;

		} catch (GeneralException e) {
			Messages.mensajeAlerta(e.getMessage());
		} catch (Exception e) {
			Messages.mensajeAlerta(e.getMessage());
		}

	}

	public void radioButtonChangeVenta(AjaxBehaviorEvent e) {
		infoReporteVentaFiltro = null;
		if (Constants.OPCION_SKU.equals(opcionTotalizar)) {
			reporteVentaSKU = ocultarPanelesVenta();
		} else if (Constants.OPCION_DISTRITO.equals(opcionTotalizar)) {
			infoReporteVentaFiltro = ReporteVentasUtil
					.loadInfoDistrito(infoReporteVentaSku);
			reporteVentaFiltro = ocultarPanelesVenta();
		} else if (Constants.OPCION_ZONA.equals(opcionTotalizar)) {
			infoReporteVentaFiltro = ReporteVentasUtil
					.loadInfoZona(infoReporteVentaSku);
			reporteVentaFiltro = ocultarPanelesVenta();
		} else if (Constants.OPCION_SUCURSAL.equals(opcionTotalizar)) {
			infoReporteVentaFiltro = ReporteVentasUtil
					.loadInfoSucursal(infoReporteVentaSku);
			reporteVentaFiltro = ocultarPanelesVenta();
		}
	}

	public void radioButtonChangeInventario(AjaxBehaviorEvent e) {
		infoReporteInventarioFiltro = null;
		if (Constants.OPCION_SKU.equals(opcionTotalizar)) {
			reporteInventarioSKU = ocultarPanelesInventario();
		} else if (Constants.OPCION_DISTRITO.equals(opcionTotalizar)) {
			infoReporteInventarioFiltro = ReporteInventarioUtil
					.loadInfoDistrito(infoReporteInventarioSku);
			reporteInventarioFiltro = ocultarPanelesInventario();
		} else if (Constants.OPCION_ZONA.equals(opcionTotalizar)) {
			infoReporteInventarioFiltro = ReporteInventarioUtil
					.loadInfoZona(infoReporteInventarioSku);
			reporteInventarioFiltro = ocultarPanelesInventario();
		} else if (Constants.OPCION_SUCURSAL.equals(opcionTotalizar)) {
			infoReporteInventarioFiltro = ReporteInventarioUtil
					.loadInfoSucursal(infoReporteInventarioSku);
			reporteInventarioFiltro = ocultarPanelesInventario();
		}
	}

	/**
	 * Devuelve el total Venta Unidad de todo el Reporte por SKU
	 * 
	 * @return
	 */
	public String getTotalVUSku() {
		int total = 0;
		if (infoReporteVentaSku.size() > 0) {
			for (ReporteVentaSkuDTO detalle : infoReporteVentaSku) {
				total = total + detalle.getVentaUnidades();
			}
		}

		return Constants.formatNumber.format(total);
	}

	/**
	 * Devuelve el total Venta Pesos de todo el Reporte por SKU
	 * 
	 * @return
	 */
	public String getTotalVPSku() {
		BigDecimal total = BigDecimal.ZERO;

		if (infoReporteVentaSku.size() > 0) {
			for (ReporteVentaSkuDTO detalle : infoReporteVentaSku) {
				total = total.add(detalle.getVentaPesos());
			}
		}

		return Constants.formatNumber.format(total);
	}

	/**
	 * Devuelve el total Venta Unidad de todo el Reporte por Filto
	 * 
	 * @return
	 */
	public String getTotalVUFiltro() {
		int total = 0;
		if (infoReporteVentaFiltro.size() > 0) {
			for (ReporteVentaFiltroDTO detalle : infoReporteVentaFiltro) {
				total = total + detalle.getVentaUnidades();
			}
		}

		return Constants.formatNumber.format(total);
	}

	/**
	 * Devuelve el total Venta Pesos de todo el Reporte por Filtro
	 * 
	 * @return
	 */
	public String getTotalVPFiltro() {
		BigDecimal total = BigDecimal.ZERO;

		if (infoReporteVentaFiltro.size() > 0) {
			for (ReporteVentaFiltroDTO detalle : infoReporteVentaFiltro) {
				total = total.add(detalle.getVentaPesos());
			}
		}

		return Constants.formatNumber.format(total);
	}

	private Boolean ocultarPanelesVenta() {
		reporteVentaSKU = Boolean.FALSE;
		reporteVentaFiltro = Boolean.FALSE;

		return Boolean.TRUE;
	}

	private Boolean ocultarPanelesInventario() {
		reporteInventarioSKU = Boolean.FALSE;
		reporteInventarioFiltro = Boolean.FALSE;

		return Boolean.TRUE;
	}

	/**
	 * Devuelve el total Inventario Tienda de todo el Reporte por SKU
	 * 
	 * @return
	 */
	public String getTotalITISku() {
		int total = 0;
		if (infoReporteInventarioSku.size() > 0) {
			for (ReporteInventarioSkuDTO detalle : infoReporteInventarioSku) {
				total = total + detalle.getInventarioTienda();
			}
		}
		
		return Constants.formatNumber.format(total);
	}

	/**
	 * Devuelve el total Inventario CEDIS de todo el Reporte por SKU
	 * 
	 * @return
	 */
	public String getTotalICSku() {
		int total = 0;
		if (infoReporteInventarioSku.size() > 0) {
			for (ReporteInventarioSkuDTO detalle : infoReporteInventarioSku) {
				total = total + detalle.getInventarioCedis();
			}
		}

		return Constants.formatNumber.format(total);
	}

	/**
	 * Devuelve el total Inventario Transito de todo el Reporte por SKU
	 * 
	 * @return
	 */
	public String getTotalITRSku() {
		int total = 0;
		if (infoReporteInventarioSku.size() > 0) {
			for (ReporteInventarioSkuDTO detalle : infoReporteInventarioSku) {
				total = total + detalle.getInventarioTransito();
			}
		}
		return Constants.formatNumber.format(total);
	}

	/**
	 * Reporte Articulos
	 */

	public void createDynamicColumns(String columnTemplate,
			List<String> validColumnKeys, String tipoReporte) throws Exception {
		
		String[] columnKeys = columnTemplate.split(" ");
		columns.clear();
		if (tipoReporte.equals(Constants.REPORTE_DE_ARTICULOS)) {
			for (String columnKey : columnKeys) {
				String key = columnKey.trim();
				if (validColumnKeys.contains(key)) {
					if (key.equals("sistemaVenta")) {
						columns.add(new ColumnModel("Sistema de Venta",
								columnKey));
					} else {
						columns.add(new ColumnModel(maysPrimera(columnKey
								.toLowerCase()), columnKey));
					}
				}
			}
		} else if (tipoReporte
				.equals(Constants.REPORTE_DE_ARTICULOS_SIN_FOTO_DESCRIPCION)) {
			for (String columnKey : columnKeys) {
				String key = columnKey.trim();
				if (validColumnKeys.contains(key)) {
					if (validColumnKeys.contains(key)) {
						if (key.equals("sistemaVenta")) {
							columns.add(new ColumnModel("Sistema de Venta",
									columnKey));
						} else if (key.equals("faltanteDe")) {
							columns.add(new ColumnModel("Faltante de ",
									columnKey));
						} else {
							columns.add(new ColumnModel(maysPrimera(columnKey
									.toLowerCase()), columnKey));
						}
					}
				}
			}
		}
		table = true;
	}

	private void recreateModel(String tipo, String tipoReporte) {
			try {
				switch (tipoReporte) {
				case Constants.REPORTE_DE_ARTICULOS:
					switch (tipo) {
					case Constants.FOLLETO:

						cabecera = serviceReporte.getCabeceraFolleto(folio);
						createDynamicColumns(columnTemplateFolleto,
								VALID_COLUMN_KEYS_FOLLETO, tipoReporte);
						break;

					case Constants.PRENSA:
						cabecera = serviceReporte.getCabeceraPrensa(folio);
						createDynamicColumns(columnTemplatePrensa,
								VALID_COLUMN_KEYS_PRENSA, tipoReporte);
						break;
					}
					break;
				case Constants.REPORTE_DE_ARTICULOS_SIN_FOTO_DESCRIPCION:
					cabecera = serviceReporte.getCabeceraPrensa(folio);
					createDynamicColumns(columnTemplatePrensa2,
							VALID_COLUMN_KEYS_PRENSA2, tipoReporte);
					switch (tipo) {
					case Constants.FOLLETO:
						cabecera = serviceReporte.getCabeceraFolleto(folio);
						createDynamicColumns(columnTemplateFolleto2,
								VALID_COLUMN_KEYS_FOLLETO2, tipoReporte);
						break;
					case Constants.PRENSA:
						cabecera = serviceReporte.getCabeceraPrensa(folio);
						createDynamicColumns(columnTemplatePrensa2,
								VALID_COLUMN_KEYS_PRENSA2, tipoReporte);
						break;
					}
					break;
				}

			} catch (Exception e) {
				LOG.error(e);
			}
	}

	public void generarReporte(String tipoReporte) throws Exception {
			switch (tipoReporte) {
			case Constants.REPORTE_DE_ARTICULOS:
				listaArticulos = getDataTable(tipo);
				recreateModel(tipo, tipoReporte);
				break;
			case Constants.REPORTE_DE_ARTICULOS_SIN_FOTO_DESCRIPCION:
				listaArticulos = getDataTable2(tipo);
				recreateModel(tipo, tipoReporte);
				break;
			}
	}

	public List<ReporteArticuloSinFotoDescripcion> getDataTable2(String tipo)
			throws Exception {
		List<ReporteArticuloSinFotoDescripcion> list = new ArrayList<>();
		switch (tipo) {
		case Constants.FOLLETO:
			List<TblArticulosHoja> espacio = serviceReporte
					.getArticulosByFolioFolleto(folio);
			list = getArticulosByFolioFolleto2(espacio);
			break;

		case Constants.PRENSA:
			List<TblArticulosEspacios> hoja = serviceReporte
					.getArticulosByFolioPrensa(folio);
			list = getArticulosByFolioPrensa2(hoja);
			break;
		}
		return list;
	}

	private List<ReporteArticuloSinFotoDescripcion> getArticulosByFolioFolleto2(
			List<TblArticulosHoja> list) throws Exception {
		CategoriaDTO categoria;
		ReporteArticuloSinFotoDescripcion reporte;
		TblFolletoSistemaVenta folletoSistemaPrensa;
		SistemaVentaDTO sistemaVenta;
		String tipoDeFaltante = "No le falta nada";
		List<ReporteArticuloSinFotoDescripcion> articulosList = new ArrayList<>();
		CatItemDTO articulo;
		try {
			for (TblArticulosHoja element : list) {
				reporte = new ReporteArticuloSinFotoDescripcion();
				articulo = DTOUtil.loadItemBySKU(
						String.valueOf(element.getIdArticulo()),
						serviceDynamicCatalogs);
				folletoSistemaPrensa = serviceReporte
						.getFolletoSistemaVentaByFolio(folio);
				sistemaVenta = MBUtil.getSistemaVentaDTO(
						serviceDynamicCatalogs, folletoSistemaPrensa.getId()
								.getIdSistemaVenta());
				categoria = MBUtil.getCategoria(serviceDynamicCatalogs,
						articulo.getIdCategoria());
				tipoDeFaltante = serviceReporte.getTipoDeFaltante(element
						.getTblImageArticulo().getIdImageArt());
				reporte.setSku(articulo.getId());
				reporte.setCategoria(categoria.getCodigo());
				reporte.setHoja(element.getHoja().getIdHoja());
				reporte.setSistemaVenta(sistemaVenta.getDescripcion());
				reporte.setFaltanteDe(tipoDeFaltante);
				articulosList.add(reporte);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return articulosList;

	}

	private List<ReporteArticuloSinFotoDescripcion> getArticulosByFolioPrensa2(
			List<TblArticulosEspacios> list) {
		ReporteArticuloSinFotoDescripcion reporte;
		TblFolletoSistemaVenta folletoSistemaPrensa = null;
		SistemaVentaDTO sistemaVenta = null;
		CategoriaDTO categoria = null;
		List<ReporteArticuloSinFotoDescripcion> articulosList = new ArrayList<>();
		CatItemDTO articulo = null;
		String tipoDeFaltante = "No le falta nada";
		try {
			for (TblArticulosEspacios element : list) {
				reporte = new ReporteArticuloSinFotoDescripcion();
				try {
					articulo = DTOUtil.loadItemBySKU(
							String.valueOf(element.getId().getIdArticulo()),
							serviceDynamicCatalogs);
					reporte.setSku(articulo.getId());
				} catch (Exception e) {
					LOG.error(e);
				}
				try {
					categoria = MBUtil.getCategoria(serviceDynamicCatalogs,
							articulo.getIdCategoria());
					reporte.setCategoria(categoria.getCodigo());
				} catch (Exception e) {
					LOG.error(e);
				}
				try {
					folletoSistemaPrensa = serviceReporte
							.getFolletoSistemaVentaByFolio(folio);
				} catch (GeneralException e) {
					LOG.error(e);
				}
				try {
					sistemaVenta = MBUtil.getSistemaVentaDTO(
							serviceDynamicCatalogs, folletoSistemaPrensa
									.getId().getIdSistemaVenta());
					reporte.setSistemaVenta(sistemaVenta.getDescripcion());
				} catch (Exception e) {
					LOG.error(e);
				}
				try {
					tipoDeFaltante = serviceReporte.getTipoDeFaltante(element
							.getTblImageArticulo().getIdImageArt());
					reporte.setFaltanteDe(tipoDeFaltante);
				} catch (GeneralException e) {
					LOG.error(e);
				}

				reporte.setEspacio(element.getId().getIdEspacio());

				articulosList.add(reporte);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return articulosList;
	}

	public List<ReporteArticulo> getDataTable(String tipo) throws Exception {
		List<ReporteArticulo> list = new ArrayList<>();
		switch (tipo) {
		case Constants.FOLLETO:
			List<TblArticulosHoja> espacio = serviceReporte.getArticulosByFolioFolleto(folio);
			list = getArticulosByFolioFolleto(espacio);
			break;

		case Constants.PRENSA:
			List<TblArticulosEspacios> hoja = serviceReporte.getArticulosByFolioPrensa(folio);
			list = getArticulosByFolioPrensa(hoja);
			break;
		}
		return list;
	}

	private List<ReporteArticulo> getArticulosByFolioFolleto(
			List<TblArticulosHoja> list) throws Exception {

		ReporteArticulo reporteArticulo;
		CategoriaDTO categoria;
		TblFolletoSistemaVenta folletoSistemaPrensa;
		SistemaVentaDTO sistemaVenta;
		List<ReporteArticulo> articulosList = new ArrayList<>();
		CatItemDTO articulo;
		try {
			for (TblArticulosHoja element : list) {
				reporteArticulo = new ReporteArticulo();
				articulo = DTOUtil.loadItemBySKU(
						String.valueOf(element.getIdArticulo()),
						serviceDynamicCatalogs);
				categoria = MBUtil.getCategoria(serviceDynamicCatalogs,
						articulo.getIdCategoria());
				folletoSistemaPrensa = serviceReporte
						.getFolletoSistemaVentaByFolio(folio);
				sistemaVenta = MBUtil.getSistemaVentaDTO(
						serviceDynamicCatalogs, folletoSistemaPrensa.getId()
								.getIdSistemaVenta());
				reporteArticulo.setSku(articulo.getId());
				reporteArticulo.setDescripcion(articulo.getCode());
				reporteArticulo.setCategoria(categoria.getCodigo());
				reporteArticulo.setHoja(element.getHoja().getIdHoja());
				reporteArticulo.setPrecio(element.getPrecioPromocion()
						.doubleValue());
				reporteArticulo.setSistemaVenta(sistemaVenta.getDescripcion());
				articulosList.add(reporteArticulo);
			}
		} catch (Exception e) {
			LOG.error(e);

		}
		return articulosList;
	}

	private List<ReporteArticulo> getArticulosByFolioPrensa(
			List<TblArticulosEspacios> list) throws Exception {
		CatItemDTO articulo;
		CategoriaDTO categoria;
		SistemaVentaDTO sistemaVenta;
		TblPrensaSistemaVenta prensaSistemaVenta;
		ReporteArticulo reporteArticulo;
		List<ReporteArticulo> articulosList = new ArrayList<>();
		try {
			for (TblArticulosEspacios element : list) {
				reporteArticulo = new ReporteArticulo();
				articulo = DTOUtil.loadItemBySKU(
						String.valueOf(element.getId().getIdArticulo()),
						serviceDynamicCatalogs);
				categoria = MBUtil.getCategoria(serviceDynamicCatalogs,
						articulo.getIdCategoria());
				prensaSistemaVenta = serviceReporte
						.getPrensaSistemaVentaByFolio(folio);
				sistemaVenta = MBUtil.getSistemaVentaDTO(
						serviceDynamicCatalogs, prensaSistemaVenta.getId()
								.getIdSistemaVenta());
				reporteArticulo.setSku(articulo.getId());
				reporteArticulo.setDescripcion(articulo.getCode());
				reporteArticulo.setCategoria(categoria.getCodigo());
				reporteArticulo.setEspacio(element.getId().getIdEspacio());
				reporteArticulo.setPrecio(element.getPrecioPromocion()
						.doubleValue());
				reporteArticulo.setSistemaVenta(sistemaVenta.getDescripcion());
				articulosList.add(reporteArticulo);
			}
		} catch (Exception e) {
			LOG.error(e);

		}
		return articulosList;
	}

	private void inicializarVariablesArticulos() {
		tipo = Constants.EMPTY;
		folio = Constants.EMPTY;
		listaArticulos = new ArrayList<>();
		table = false;
		cabecera = new CabeceraReporteDTO();
	}
	
	
	
	public void preProcessPDF(Object document) {
		com.lowagie.text.Document doc = (com.lowagie.text.Document) document;
        doc.setPageSize(PageSize.LETTER.rotate());
    }

	private String maysPrimera(String cadena) {
		Character d = cadena.charAt(0);
		return Character.toUpperCase(d) + cadena.substring(1);
	}
	
	
	public void createPDFInventario() {
		
		if(infoReporteInventarioSku !=null){
		    try { 
		        FacesContext context 			= FacesContext.getCurrentInstance();
		        Document document 				= new Document();
		        ByteArrayOutputStream baos 		= new ByteArrayOutputStream();
		        
		        PdfWriter.getInstance(document, baos);
	
		        if (!document.isOpen()) {
		            document.open();
		        }
	
		        ExportPDFUtil.addHeaderPage(document,cabecera);
		        document.add(ExportPDFUtil.exportInventario(this.infoReporteInventarioSku));
	
		        //Keep modifying your pdf file (add pages and more)
	
		        document.close();
		        String fileName = Constants.NOMBRE_REPORTE_INVENTARIO + "-"+ cabecera.getNombreCampania();
	
		        ExportPDFUtil.writePDFToResponse(context.getExternalContext(), baos,fileName );
		        context.responseComplete();
	
		    } catch (Exception e) {
		        //e.printStackTrace();          
		    }
		}
	}
	
	
	public void createPDFInventarioFiltro() {
		
		if(infoReporteInventarioFiltro !=null){
		    try {
		        FacesContext context 		= FacesContext.getCurrentInstance();
		        Document document 			= new Document();
		        ByteArrayOutputStream baos 	= new ByteArrayOutputStream();
		        PdfWriter.getInstance(document, baos);
	
		        if (!document.isOpen()) {
		            document.open();
		        }
	
		        ExportPDFUtil.addHeaderPage(document,cabecera);
		        document.add(ExportPDFUtil.exportInventarioFiltro(this.infoReporteInventarioFiltro,opcionTotalizar));
	
		        //Keep modifying your pdf file (add pages and more)
	
		        document.close();
		        String fileName = Constants.NOMBRE_REPORTE_INVENTARIO + "-"+ cabecera.getNombreCampania();
	
		        ExportPDFUtil.writePDFToResponse(context.getExternalContext(), baos, fileName);
	
		        context.responseComplete();
	
		    } catch (Exception e) {
		        //e.printStackTrace();          
		    }
		}
	}
	
	
	public void createPDFVenta() {
			
			if(infoReporteVentaSku !=null){
			    try { 
			        FacesContext context 			= FacesContext.getCurrentInstance();
			        Document document 				= new Document();
			        ByteArrayOutputStream baos 		= new ByteArrayOutputStream();
			        
			        PdfWriter.getInstance(document, baos);
		
			        if (!document.isOpen()) {
			            document.open();
			        }
		
			        ExportPDFUtil.addHeaderPage(document,cabecera);
			        document.add(ExportPDFUtil.exportVenta(infoReporteVentaSku));
		
			        //Keep modifying your pdf file (add pages and more)
		
			        document.close();
			        String fileName = Constants.NOMBRE_REPORTE_VENTA + "-"+ cabecera.getNombreCampania();
		
			        ExportPDFUtil.writePDFToResponse(context.getExternalContext(), baos,fileName );
			        context.responseComplete();
		
			    } catch (Exception e) {
			        //e.printStackTrace();          
			    }
			}
		}
	
	
	public void createPDFVentaFiltro() {
		
		if(infoReporteVentaFiltro !=null){
		    try { 
		        FacesContext context 			= FacesContext.getCurrentInstance();
		        Document document 				= new Document();
		        ByteArrayOutputStream baos 		= new ByteArrayOutputStream();
		        
		        PdfWriter.getInstance(document, baos);
	
		        if (!document.isOpen()) {
		            document.open();
		        }
	
		        ExportPDFUtil.addHeaderPage(document,cabecera);
		        document.add(ExportPDFUtil.exportVentaFiltro(infoReporteVentaFiltro,opcionTotalizar));
	
		        //Keep modifying your pdf file (add pages and more)
	
		        document.close();
		        String fileName = Constants.NOMBRE_REPORTE_VENTA + "-"+ cabecera.getNombreCampania();
	
		        ExportPDFUtil.writePDFToResponse(context.getExternalContext(), baos,fileName );
		        context.responseComplete();
	
		    } catch (Exception e) {
		        //e.printStackTrace();          
		    }
		}
	}

	public Map<String,String> getMecanicasMap() {
		return mecanicasMap;
	}

	public void setMecanicasMap(Map<String,String> mecanicasMap) {
		this.mecanicasMap = mecanicasMap;
	}
	
	
	
}
