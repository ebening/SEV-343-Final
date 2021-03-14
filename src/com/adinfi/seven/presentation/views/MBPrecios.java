package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblPreciosFolleto;
import com.adinfi.seven.business.domain.TblPreciosFolletoDet;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServicePrecioFolleto;
import com.adinfi.seven.business.services.ServicePrecioPrensa;
import com.adinfi.seven.persistence.dto.CampanaMedioDTO;
import com.adinfi.seven.persistence.dto.CatMedioDTO;
import com.adinfi.seven.persistence.dto.CatTipoMedioDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.PreciosDataModel;
import com.adinfi.seven.persistence.dto.PreciosExcelRow;
import com.adinfi.seven.persistence.dto.SistemaVentaDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.UploadFilesBean;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.primefaces.event.FileUploadEvent;

public class MBPrecios implements Serializable {

	private static final long serialVersionUID = -7119376568956945399L;
	private Logger LOG = Logger.getLogger(MBPrecios.class);
	public static final String OPCION_PRECIOS = "/pages/ArqPrecios.xhtml";
	public static final String OPCION_PRECIOS_CARGAR = "/pages/ArqPreciosCarga.xhtml";
	private ServicePrecioFolleto servicePrecioFolleto;
	private ServicePrecioPrensa servicePrecioPrensa;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceCampana serviceCampana;

	private PreciosDataModel preciosDataModel;
	private List<CampanaMedioDTO> precios;
	private CampanaMedioDTO precio;

	private List<String> codigoHojas;
	private Map<String, List<PreciosExcelRow>> libro = new HashMap<String, List<PreciosExcelRow>>();
	
	private List<SistemaVentaDTO> sistemasVentas;
	private String nombreExcel;
	private UploadFilesBean upload;
	
	private boolean mostrarResultados;
	private List<TblPreciosFolleto> respaldoPreciosFolleto;
	
	@PostConstruct
	public void init(){
		recreateModel();
		setUpload(new UploadFilesBean());
		mostrarResultados=false;
		precio=null;
		nombreExcel=null;
	}
	
	public String preCargarExcel(){
		if(precio == null){
			Messages.mensajeErroneo("!Se debe seleccionar un elemento!");
			return OPCION_PRECIOS;
		}else if(precio.getCampana() == null || precio.getCampana().getNombre() == null){
			Messages.mensajeErroneo("!No se pudo ingresar a la configuracion de la Campaña!");
			return OPCION_PRECIOS;
		}else if( (precio.getFolletoId() == null || precio.getFolletoId() == 0) ){
			Messages.mensajeErroneo("!El elemento seleccionado se encuentra mal configurado!");
			return OPCION_PRECIOS;
		}
		
		cargarSistemaVenta();
		libro = new HashMap<String, List<PreciosExcelRow>>();
		mostrarResultados = false;
		return OPCION_PRECIOS_CARGAR;
	}
	
	private void recreateModel() {
		try {
			setPreciosDataModel(new PreciosDataModel(getListPrecios()));
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void upload(FileUploadEvent event){
		String xls=upload.upload(event, GlobalDefines.RUTA_CARGA);
		setNombreExcel(xls);
	}

	private List<CampanaMedioDTO> getListPrecios(){
		List<CampanaMedioDTO> retVal= new ArrayList<CampanaMedioDTO>();
		try {
			List<TblCampanaMedio> campanaMedio = serviceCampana.getCampanaMedioAll();
			for (TblCampanaMedio cm : campanaMedio) {
				
				if( cm.getIdFolleto() != null && cm.getIdFolleto() > 0 )
				{
					CampanaMedioDTO medioDTO = new CampanaMedioDTO();
					try {
						CatMedioDTO medio = MBUtil.getMedio(serviceDynamicCatalogs, cm.getIdMedio());
						
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
							if( item.getId().intValue() == cm.getIdTipoMedio().intValue() ){
								tipoMedio.setId(item.getId());
								tipoMedio.setCode(item.getValue());
								tipoMedio.setCatMedio(medio);
							}
						}
						
						medioDTO.setCampana(cm.getTblCampana());
						medioDTO.setTipoMedio(tipoMedio);
						medioDTO.setMedio(medio);
						medioDTO.setFolletoId(cm.getIdFolleto());
						//medioDTO.setPrensaId(cm.getIdPrensa());
					} catch (Exception e) {
						LOG.error(e);
					}
					retVal.add(medioDTO);
				}
				
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return retVal;
	}
	
	private void cargarSistemaVenta(){
		try {
			sistemasVentas = new ArrayList<SistemaVentaDTO>();
			
			List<SistemaVentaDTO> lstSistVentaDTO = MBUtil.getSistemaVenta(serviceDynamicCatalogs);
			List<TblFolletoSistemaVenta> lstFolletoSistemaVenta = serviceCampana.getSistemaVentaByFolleto(precio.getFolletoId());
			
			for (TblFolletoSistemaVenta folletoSistemaVenta : lstFolletoSistemaVenta) {
				for (SistemaVentaDTO sistemaVentaDTO : lstSistVentaDTO) {
					if( sistemaVentaDTO.getId() == folletoSistemaVenta.getId().getIdSistemaVenta() ){
						sistemasVentas.add(sistemaVentaDTO);
						break;
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void cargaExcel(){
		if(leerArchivo()){
			preparePrecios();
			mostrarResultados = guardar();
			terminarPrecios();
			Messages.mensajeSatisfactorio("Se guardaron las listas de Precios correctamente.");
		}else{
			mostrarResultados = false;
			Messages.mensajeErroneo("Ocurrio un error al guardar las listas de Precios.");
		}
	}
	
	public void preparePrecios(){
		respaldoPreciosFolleto = new ArrayList<TblPreciosFolleto>();
		try{
			respaldoPreciosFolleto = servicePrecioFolleto.getPreciosFolletoByFolleto(precio.getFolletoId());
		}catch(Exception e){
			LOG.error(e);
			Messages.mensajeErroneo("Ocurrio un error al cargar los precios anteriores: "+e.getMessage());
		}
	}
	
	public void terminarPrecios(){
		try{
			for (TblPreciosFolleto resPreciosFolleto : respaldoPreciosFolleto) {
				servicePrecioFolleto.deletePreciosFolleto(resPreciosFolleto);
			}
		}catch(Exception e){
			LOG.error(e);
			Messages.mensajeErroneo("Ocurrio un error al eliminar los precios anteriores: "+e.getMessage());
		}
	}

	public List<PreciosExcelRow> getMuestraHoja(String sistema){
		List<PreciosExcelRow> hoja = new ArrayList<PreciosExcelRow>();
		
		if(mostrarResultados){
			hoja = libro.get(sistema);
			
			if(hoja==null){
				hoja = new ArrayList<PreciosExcelRow>();
			}
		}
		
		return hoja;
	}
	
	public boolean guardar(){
		boolean retVal = true;
		try{
			if(precio.getFolletoId() != null && precio.getFolletoId() > 0){
				
				for (SistemaVentaDTO sistema : sistemasVentas) {
					
					TblFolleto folleto = servicePrecioFolleto.getFolleto(precio.getFolletoId());
					TblPreciosFolleto pFolleto = new TblPreciosFolleto();
					
					pFolleto.setFechaCarga(new Date());
					pFolleto.setFileName(nombreExcel);
					pFolleto.setTblFolleto(folleto);
					
					Set<TblPreciosFolletoDet> lstFolletos = new HashSet<TblPreciosFolletoDet>();
					List<PreciosExcelRow> pers = libro.get(sistema.getCode());
					
					for (PreciosExcelRow per : pers) {
					
						TblPreciosFolletoDet pFolletoDet = new TblPreciosFolletoDet();
						pFolletoDet.setIdSistemaVenta(sistema.getId());
						pFolletoDet.setIdSku(per.getSku());
						pFolletoDet.setCdoPublicar( obtenPrecio(per.getContado()) );
						pFolletoDet.setAbsem6mes( obtenPrecio(per.getAbSem6Mes()) );
						pFolletoDet.setAbsem9mes( obtenPrecio(per.getAbSem9Mes()) );
						pFolletoDet.setAbsem12mes( obtenPrecio(per.getAbSem12Mes()) );
						pFolletoDet.setAbsem15mes( obtenPrecio(per.getAbSem15Mes()) );
						pFolletoDet.setAbsem18mes( obtenPrecio(per.getAbSem18Mes()) );
						pFolletoDet.setAbsem24mes( obtenPrecio(per.getAbSem24Mes()) );
						pFolletoDet.setTblPreciosFolleto(pFolleto);
						
						lstFolletos.add(pFolletoDet);
					}
					
					pFolleto.setTblPreciosFolletoDets(lstFolletos);
					
					retVal = servicePrecioFolleto.guardarPrecioFolleto(pFolleto);
				}
				
			}else{
				Messages.mensajeErroneo("No se encuentra configurada correctamente el medio.");
				retVal=false;
			}
				/**for (SistemaVentaDTO sistema : sistemasVentas) {
					
					TblPrensa prensa = servicePrecioPrensa.getPrensa(precio.getPrensaId());
					TblPreciosPrensa pPrensa = new TblPreciosPrensa();
					
					pPrensa.setFechaCarga(new Date());
					pPrensa.setFileName(nombreExcel);
					pPrensa.setTblPrensa(prensa);
					
					Set<TblPreciosPrensaDet> lstPrensas = new HashSet<TblPreciosPrensaDet>();
					List<PreciosExcelRow> pers = libro.get(sistema.getCode());
					
					for (PreciosExcelRow per : pers) {
					
						TblPreciosPrensaDet pPrensaDet = new TblPreciosPrensaDet();
						pPrensaDet.setIdSistemaVenta(sistema.getId());
						pPrensaDet.setIdSku(per.getSku());
						pPrensaDet.setCdoPublicar( obtenPrecio(per.getContado()) );
						pPrensaDet.setAbsem6mes( obtenPrecio(per.getAbSem6Mes()) );
						pPrensaDet.setAbsem9mes( obtenPrecio(per.getAbSem9Mes()) );
						pPrensaDet.setAbsem12mes( obtenPrecio(per.getAbSem12Mes()) );
						pPrensaDet.setAbsem15mes( obtenPrecio(per.getAbSem15Mes()) );
						pPrensaDet.setAbsem18mes( obtenPrecio(per.getAbSem18Mes()) );
						pPrensaDet.setAbsem24mes( obtenPrecio(per.getAbSem24Mes()) );
						pPrensaDet.setTblPreciosPrensa(pPrensa);
						
						lstPrensas.add(pPrensaDet);
					}
					
					pPrensa.setTblPreciosPrensaDets(lstPrensas);
					
					retVal = servicePrecioPrensa.guardarPrecioPrensa(pPrensa);
				}*/
			
		}catch(Exception e){
			LOG.error(e);
			retVal = false;
			Messages.mensajeErroneo("Ocurrio un error al guardar las listas de Precios. "+e.getMessage());
		}
		
		return retVal;
	}
	
	private BigDecimal obtenPrecio(String str){
		BigDecimal bd = BigDecimal.ZERO;
		
		try{
			bd = new BigDecimal( "".equals(str.trim()) ? "0" : str  ); 
		}catch(NumberFormatException ne){
			bd = BigDecimal.ZERO;
		}catch (Exception e) {
			bd = BigDecimal.ZERO;
		}
		
		return bd;
	}

	private boolean leerArchivo(){
		List<PreciosExcelRow> hoja=null;
		boolean retVal = true;
		try {

		   ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		   String realPath=(String) servletContext.getRealPath(GlobalDefines.RUTA_CARGA + nombreExcel);
		   
           InputStream input = new BufferedInputStream( new FileInputStream(realPath) );
           POIFSFileSystem fs = new POIFSFileSystem( input );
           HSSFWorkbook wb = new HSSFWorkbook( fs );
           
           libro = new HashMap<String, List<PreciosExcelRow>>();
           for (SistemaVentaDTO sistema : sistemasVentas) {
        	   
        	   hoja = new ArrayList<PreciosExcelRow>();
        	   
	           HSSFSheet sheet = wb.getSheet( sistema.getCode() );
	           Iterator<Row> rows = sheet.rowIterator();
	           
	           while( rows.hasNext() ) {  
	               HSSFRow row = (HSSFRow) rows.next();
	               if(row.getRowNum()==0)
	            	   continue;
	               
	               Iterator<Cell> cells = row.cellIterator();
	               
	               PreciosExcelRow per = new PreciosExcelRow();
	               
	               while( cells.hasNext() ) {
	                    
	                   HSSFCell cell = (HSSFCell) cells.next();
	
	                   Object ob=null;
	                   if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
	                	   ob=cell.getNumericCellValue();
	                   else if(HSSFCell.CELL_TYPE_STRING==cell.getCellType())
	                	   ob=cell.getStringCellValue();
	                   else if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
	                	   ob=cell.getBooleanCellValue();
	                   else if(HSSFCell.CELL_TYPE_BLANK==cell.getCellType())
	                	   ob=" ";
	                   else if(HSSFCell.CELL_TYPE_FORMULA==cell.getCellType())
	                	   ob=cell.getNumericCellValue();
	                   else
	                	   ob=" ";
	                   
	                   if(cell.getColumnIndex()==0)
	                	   per.setSku(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==17)
	                	   per.setContado(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==25)
	                	   per.setAbSem6Mes(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==26)
	                	   per.setAbSem9Mes(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==27)
	                	   per.setAbSem12Mes(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==28)
	                	   per.setAbSem15Mes(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==29)
	                	   per.setAbSem18Mes(String.valueOf(ob));
	                   else if(cell.getColumnIndex()==30)
	                	   per.setAbSem24Mes(String.valueOf(ob));
	
	               }
	               per.setCodigoHoja(sheet.getSheetName());
	               hoja.add(per);
	           }
	           
	           libro.put(sistema.getCode(), hoja);
           }
            
       } catch ( IOException ex ) {
    	   retVal=false;
           LOG.error(ex);
           Messages.mensajeErroneo("Ocurrio un error al cargar el archivo. "+ex.getMessage());
       }
		
		return retVal;
	}
	

	public PreciosDataModel getPreciosDataModel() {
		return preciosDataModel;
	}

	public void setPreciosDataModel(PreciosDataModel preciosDataModel) {
		this.preciosDataModel = preciosDataModel;
	}

	public List<CampanaMedioDTO> getPrecios() {
		return precios;
	}

	public void setPrecios(List<CampanaMedioDTO> precios) {
		this.precios = precios;
	}

	public CampanaMedioDTO getPrecio() {
		return precio;
	}

	public void setPrecio(CampanaMedioDTO precio) {
		this.precio = precio;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public List<String> getCodigoHojas() {
		return codigoHojas;
	}

	public void setCodigoHojas(List<String> codigoHojas) {
		this.codigoHojas = codigoHojas;
	}

	public List<SistemaVentaDTO> getSistemasVentas() {
		return sistemasVentas;
	}

	public void setSistemasVentas(List<SistemaVentaDTO> sistemasVentas) {
		this.sistemasVentas = sistemasVentas;
	}

	public Map<String, List<PreciosExcelRow>> getLibro() {
		return libro;
	}

	public void setLibro(Map<String, List<PreciosExcelRow>> libro) {
		this.libro = libro;
	}

	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}

	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	public String getNombreExcel() {
		return nombreExcel;
	}

	public void setNombreExcel(String nombreExcel) {
		this.nombreExcel = nombreExcel;
	}

	public UploadFilesBean getUpload() {
		return upload;
	}

	public void setUpload(UploadFilesBean upload) {
		this.upload = upload;
	}

	public ServicePrecioFolleto getServicePrecioFolleto() {
		return servicePrecioFolleto;
	}

	public void setServicePrecioFolleto(ServicePrecioFolleto servicePrecioFolleto) {
		this.servicePrecioFolleto = servicePrecioFolleto;
	}

	public ServicePrecioPrensa getServicePrecioPrensa() {
		return servicePrecioPrensa;
	}

	public void setServicePrecioPrensa(ServicePrecioPrensa servicePrecioPrensa) {
		this.servicePrecioPrensa = servicePrecioPrensa;
	}
	
}
