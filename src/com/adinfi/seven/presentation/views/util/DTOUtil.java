/**
 * 
 */
package com.adinfi.seven.presentation.views.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;




import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.TblExistenciaItemTienda;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblListaSeleccionSku;
import com.adinfi.seven.business.domain.TblPrensa;
import com.adinfi.seven.business.domain.TblReporteInventario;
import com.adinfi.seven.business.domain.TblReporteVentas;
import com.adinfi.seven.business.services.ServiceAnalisis;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CabeceraReporteDTO;
import com.adinfi.seven.persistence.dto.CatItemDTO;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.DepartamentoDTO;
import com.adinfi.seven.persistence.dto.ItemDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioSkuDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaSkuDTO;
import com.adinfi.seven.persistence.dto.SistemaVentaDTO;
import com.adinfi.seven.persistence.dto.SubcategoriaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.ArticuloDTO;


/**
 * @author OMAR
 *
 */
public  final class DTOUtil {
	
	private static  SimpleDateFormat formatDate = new SimpleDateFormat(
			"dd-MMMM-yyyy", new Locale("es", "ES"));
	
	/**
	 * Metodo que forma un objeto CategoriaDTO con la informacion que se le proporciona
	 * @param setReg
	 * @return
	 */
	public static CategoriaDTO crearCategoriaDTO(Set<CatRegValues> setReg){
		
		CategoriaDTO catDto	= new CategoriaDTO();
		
		for(CatRegValues  regVals : setReg  ) {				
			String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
			if(attName.equals(Constants.ATT_CODE)){
				catDto.setCodigo(regVals.getValue());
			}else if(attName.equals(Constants.ATT_ID)){
				catDto.setId(Integer.valueOf(regVals.getValue())); 
			}else if ( attName.equals(Constants.ATT_ES_MERCANCIA) ){
				catDto.setEsMercancia(regVals.getValue().charAt(0));
			}
		}
		
		return catDto;
	}
	
	/**
	 * Metodo que forma un objeto SistemaVentaDTO con la informacion que se le proporciona
	 * @param setReg
	 * @return
	 */
	public static SistemaVentaDTO crearSistemaVentaDTO(Set<CatRegValues> setReg){
		
		SistemaVentaDTO sisDto	= new SistemaVentaDTO();
		
		for(CatRegValues  regVals : setReg  ) {				
			String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
			if(attName.equals(Constants.ATT_CODE)){
				sisDto.setCode(regVals.getValue());
			}else if(attName.equals(Constants.ATT_ID)){
				sisDto.setId(Integer.valueOf(regVals.getValue())); 
			}else if ( attName.equals(Constants.ATT_DESCRIPTION) ){
				sisDto.setDescripcion(regVals.getValue());
			}
		}
		
		return sisDto;
	}
	
	/**
	 * Metodo que forma un objeto SubcategoriaDTO con la informacion que se le proporciona
	 * @param setReg
	 * @return
	 */
	public static SubcategoriaDTO crearSubcategoriaDTO(Set<CatRegValues> setReg){
		
		SubcategoriaDTO subcategoriaDTO = new SubcategoriaDTO();
		
		for(CatRegValues  regVals : setReg  ) {				
			String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
			if( attName.equals(Constants.ATT_CODE) ){
				subcategoriaDTO.setCodigo(regVals.getValue());
			}else if( attName.equals(Constants.ATT_ID) ){
				subcategoriaDTO.setId(Integer.valueOf(regVals.getValue())); 
			}else if( attName.equals(Constants.ATT_ID_CATEGORY) ){
				subcategoriaDTO.setIdCategoria(Integer.valueOf(regVals.getValue()));
			}else if( attName.equals(Constants.ATT_ES_MERCANCIA) ){
				String result = regVals.getValue();
				if(result.equals(Constants.VALUE_TRUE)){
					subcategoriaDTO.setMercancia(Boolean.TRUE);
				}
				else {
					subcategoriaDTO.setMercancia(Boolean.FALSE);
				}
			}
		}
		
		return subcategoriaDTO;
		
	}
	
	/**
	 * Metodo que forma un objeto PeriodoDTO con la informacion que se le proporciona
	 * @param setReg
	 * @return
	 * @throws ParseException 
	 */
	public static PeriodoDTO crearPeriodoDTO(Set<CatRegValues> setReg) throws ParseException{
		
            PeriodoDTO perDto = new PeriodoDTO();
            Date fechaIniCamp;
            Date fechaFinCamp;

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 0);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
            for (CatRegValues regVals : setReg) {
                String attName = regVals.getCatAttrs().getAttribs().getAttrName();
                if (attName.equals(Constants.ATT_ID)) {
                        perDto.setId((Integer.valueOf(regVals.getValue())));
                }
                if (attName.equals(Constants.ATT_CODE)) {
                        perDto.setCodigo(regVals.getValue());
                }
                if (attName.equals(Constants.ATT_FECHA_INICIO)) {
                        fechaIniCamp = dateFormat.parse(regVals.getValue()
                                        .replaceAll("/", "-"));
                        perDto.setFechaInicial(fechaIniCamp);
                        perDto.setFechaInicio(dateFormat.parse(regVals.getValue()
                                        .replaceAll("/", "-")));
                }
                if (attName.equals(Constants.ATT_FECHA_FIN)) {

                    String[] ftemp = regVals.getValue().split("/");
                    cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(ftemp[0]));
                    cal.set(Calendar.MONTH, Integer.valueOf(ftemp[1])-1);
                    cal.set(Calendar.YEAR, Integer.valueOf(ftemp[2]));

                    fechaFinCamp = cal.getTime();
                    perDto.setFechaFinal(fechaFinCamp);
                    perDto.setFechaFin(fechaFinCamp);
//						fechaFinCamp = dateFormat.parse(regVals.getValue().replaceAll("/", "-"));
//						perDto.setFechaFin(dateFormat.parse(regVals.getValue().replaceAll("/", "-")));
                }
                if (attName.equals(Constants.ATT_DUR_DAYS)) {
                        perDto.setDuracionDias(regVals.getValue());
                }
            }
            return perDto;
	}
	
	
	/**
	 * Metodo que forma un objeto CatItemDTO con la informacion que se le proporciona
	 * @param setReg
	 * @return
	 * @throws ParseException
	 */
	public static CatItemDTO crearCatItemDTO(Set<CatRegValues> setReg) throws ParseException{
		
		CatItemDTO catItemDTO 				= new CatItemDTO();
		SimpleDateFormat dateFormat 		= new SimpleDateFormat("dd-MM-yyyy");
		Date fechaCreacion 					= null;
		
		for(CatRegValues  regVals : setReg  ) {				
			String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
			
			if( attName.equals(Constants.ATT_CODE) ){
				catItemDTO.setCode(regVals.getValue());
			}else 
				if( attName.equals(Constants.ATT_ID_ITEM) ){
					catItemDTO.setId(regVals.getValue()); 
			}else 
				if( attName.equals(Constants.ATT_SEGMENTO) ){
					catItemDTO.setSegmento(regVals.getValue());
			}else 
				if( attName.equals(Constants.ATT_ID_SCATEGORY) ){
					catItemDTO.setIdSubcatgoria( Integer.valueOf(regVals.getValue()) );
			}else 
				if( attName.equals(Constants.ATT_ID_CATEGORY) ){
					catItemDTO.setIdCategoria(Integer.valueOf(regVals.getValue()));
			}else 
				if( attName.equals(Constants.ATT_ID_DEPTO) ){
					catItemDTO.setIdDepto(Integer.valueOf(regVals.getValue()));
			}else 
				if( attName.equals(Constants.ATT_FABRICANTE) ){
					catItemDTO.setFabricante(regVals.getValue());
			}else 
				if( attName.equals(Constants.ATT_PROVEEDOR) ){
					catItemDTO.setProveedor(regVals.getValue());
			}else 
				if( attName.equals(Constants.ATT_MARCA) ){
					catItemDTO.setMarca(regVals.getValue());
			}else 
				if( attName.equals(Constants.ATT_ES_MERCANCIA) ){
					catItemDTO.setMercancia(Boolean.getBoolean(regVals.getValue()));
			}else 
				if ( attName.equals(Constants.ATT_FECHA_CREACION)){
					fechaCreacion = dateFormat.parse(regVals.getValue().replace('/', '-'));
					catItemDTO.setFechaCreacion(fechaCreacion);
			}else 
				if ( attName.equals(Constants.ATT_PRECIO_ACTUAL) ){
					catItemDTO.setPrecioActual( Double.parseDouble( regVals.getValue() ));
			}else 
				if ( attName.equals(Constants.ATT_MARGEN) ){
					catItemDTO.setMargen( Double.parseDouble( regVals.getValue() ));
			}else 
				if ( attName.equals(Constants.ATT_EXISTENCIA) ){
					catItemDTO.setExistencia( Double.parseDouble( regVals.getValue() ));
				}
		}
		
		return catItemDTO;
	}
	
	
	
	
		public static UsuarioDTO crearUsuarioDTO(Set<CatRegValues> setReg){
			UsuarioDTO usuario = new UsuarioDTO();
			
			for(CatRegValues  regVals : setReg  ) {
				String attName = regVals.getCatAttrs().getAttribs().getAttrName();
				if(attName.equals(Constants.ATT_NAME)){
					usuario.setName(regVals.getValue());
				}else if(attName.equals(Constants.ATT_PLAST_NAME)){
					usuario.setPlastName(regVals.getValue());
				}else if(attName.equals(Constants.ATT_MLAST_NAME)){
					usuario.setMlastName(regVals.getValue());
				}else if(attName.equals(Constants.ATT_EMAIL)){
					usuario.setEmail(regVals.getValue());
				}else if(attName.equals(Constants.ATT_ROLE)){
					usuario.setRole(regVals.getValue());
				}else if(attName.equals(Constants.ATT_ID)){
					usuario.setUserId(Integer.valueOf(regVals.getValue()));
				}
			 }
			return usuario;	
				
			}
		
		
		
		public static DepartamentoDTO crearDepartamentoDTO(Set<CatRegValues> setReg){
			
			DepartamentoDTO deptoDTO	= new DepartamentoDTO();
			
			for(CatRegValues  regVals : setReg  ) {				
				String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
				if(attName.equals(Constants.ATT_CODE)){
					deptoDTO.setCodigo(regVals.getValue());
				}else if(attName.equals(Constants.ATT_ID)){
					deptoDTO.setId(Integer.valueOf(regVals.getValue())); 
				}else if ( attName.equals(Constants.ATT_ES_MERCANCIA) ){
					deptoDTO.setEsMercancia( Boolean.getBoolean( regVals.getValue() ));
				}
			}
			return deptoDTO;
		}
		
		
		public static Map<String,String> cargarComboDepartamentos(List<DepartamentoDTO> listDepartamentos){
			
		Map<String,String> deptoMap = new HashMap<String, String>();
		
		for(DepartamentoDTO depto : listDepartamentos){
			deptoMap.put(depto.getCodigo(),String.valueOf(depto.getId()));
		}
		
		return deptoMap;
		
		}
		
		public static Map<String,String> cargarComboCategorias(List<CategoriaDTO> listCategorias){
			
			Map<String,String> categoriaMap = new HashMap<String, String>();
			
			for(CategoriaDTO categoria : listCategorias){
				categoriaMap.put(categoria.getCodigo(),String.valueOf(categoria.getId()));
			}
			
			return categoriaMap;
			
			}
		
		public static Map<String,String> cargarComboSubcategorias(List<SubcategoriaDTO> listSubcategorias){
			
			Map<String,String> categoriaMap = new HashMap<String, String>();
			
			for(SubcategoriaDTO subcategoria : listSubcategorias){
				categoriaMap.put(subcategoria.getCodigo(),String.valueOf(subcategoria.getId()));
			}
			
			return categoriaMap;
			
			}
		
		
		public static List <ItemDTO> generateItemDTOList(List<CatItemDTO> listCatItem,int idTienda, boolean isTodasLasTiendas,
				ServiceAnalisis service,Map<String,TblExistenciaItemTienda> mapExistenciaItem) throws Exception{
			
			List <ItemDTO> itemDTOList 							= new ArrayList<ItemDTO>();
			ItemDTO itemDTO 									= null;
			BigDecimal  resultadoDecimal 						= null;
			TblExistenciaItemTienda existenciaItemInfo 			= null;
			
			
			for(CatItemDTO catItem : listCatItem){
				
				String idItem		= catItem.getId();
				itemDTO 			= new ItemDTO();
				existenciaItemInfo 	= mapExistenciaItem.get(idItem);
				
				itemDTO.setSku( idItem  );
				itemDTO.setDescripcionSku(catItem.getCode());
				itemDTO.setMargenContribucion(  Constants.formatNumber.format( catItem.getMargen() ));
				itemDTO.setArticuloNuevo( calculateIsNuevo(catItem.getFechaCreacion() )  );
				itemDTO.setIdDepartamento( catItem.getIdDepto());
				itemDTO.setIdCategoria( catItem.getIdCategoria());
				itemDTO.setIdSubcategoria( catItem.getIdSubcatgoria());
				
				if(isTodasLasTiendas){
					resultadoDecimal = service.getVentasHistoricasTodasLasTiendas(idItem);
					itemDTO.setVentasHistoricas(Constants.formatNumber.format( resultadoDecimal) );
					itemDTO.setInventario( String.valueOf( catItem.getExistencia()) );
				}else{
					resultadoDecimal = service.getVentasHistoricasbyTienda(idItem, idTienda);
					itemDTO.setVentasHistoricas( (resultadoDecimal!=null )?Constants.formatNumber.format(resultadoDecimal):Constants.EMPTY );
					resultadoDecimal = service.getInventarioItemByIdTienda(idItem, idTienda);
					itemDTO.setInventario( (resultadoDecimal!=null )?Constants.formatNumber.format( resultadoDecimal):Constants.EMPTY );
				}
				
				itemDTO.setFechaUltimaPromocion((existenciaItemInfo !=null )? existenciaItemInfo.getFechaUpromoStr():Constants.EMPTY );
				itemDTO.setTipoUltimaPromocion((existenciaItemInfo !=null )? existenciaItemInfo.getTipoUpromo():Constants.EMPTY );
				itemDTO.setPrecioUltimaPromocion((existenciaItemInfo !=null )? Constants.formatNumber.format( existenciaItemInfo.getPrecioUpromo()) :Constants.EMPTY ) ;
				itemDTO.setTipoPromocionAnioAnterior((existenciaItemInfo !=null )? existenciaItemInfo.getTipoPromoAanterior():Constants.EMPTY );
				itemDTO.setPrecioPromocionAnioAnterior((existenciaItemInfo !=null )? Constants.formatNumber.format( existenciaItemInfo.getPrecioPromoAanterior()) :Constants.EMPTY );
				itemDTOList.add(itemDTO);
			}
			
			
			
			return itemDTOList;
		}
		
		
		private static boolean calculateIsNuevo( Date fechaCreacion ){
			Boolean result 				= Boolean.FALSE;
			int rango 					= 0;
			GregorianCalendar date1 	= new GregorianCalendar();
			GregorianCalendar date2 	= new GregorianCalendar();
			
			/** CREAMOS LOS OBJETOS GREGORIAN CALENDAR PARA EFECTUAR LA RESTA **/
			date1.setTime( fechaCreacion ); 	/**dateIni es el objeto Date**/
			date2.setTime(new Date());		 	/**dateFin es el objeto Date**/

			/** COMPROBAMOS SI ESTAMOS EN EL MISMO AÑO */
			if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
			rango = date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR);
			} else {
			/** SI ESTAMOS EN DISTINTO AÑO COMPROBAMOS QUE EL AÑO DEL DATEINI NO SEA BISIESTO
			* SI ES BISIESTO SON 366 DIAS EL AÑO
			* SINO SON 365
			*/
			int diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;

			/** CALCULAMOS EL RANGO DE AÑOS */
			int rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

			/** CALCULAMOS EL RANGO DE DIAS QUE HAY */
			rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
			}
			
			if(rango <= GlobalDefines.ANALISIS_DIAS_ANTIGUEDAD_CONFIG){
				result = Boolean.TRUE;
			}
			
			
			return result;
		}
		
		
		public static List<TblListaSeleccionSku> itemDTOToTblListaSeleccionSKU(List<ItemDTO> listaItems)  throws Exception{
			List<TblListaSeleccionSku> listaFinal 	= new ArrayList<TblListaSeleccionSku>();
			TblListaSeleccionSku item 				= null;
			String numberString 					= Constants.EMPTY;
			
			for(ItemDTO itemDTO :listaItems){
				numberString = itemDTO.getVentasHistoricas().replaceAll(",", "");
				BigDecimal ventasHistoricas= BigDecimal.valueOf(  Double.parseDouble( numberString ));
				
				numberString = itemDTO.getMargenContribucion().replaceAll(",", "");
				BigDecimal margenContribucion =BigDecimal.valueOf(  Double.parseDouble( numberString ));
				
				numberString = itemDTO.getInventario().replaceAll(",", "");
				BigDecimal existencia = BigDecimal.valueOf(  Double.parseDouble( numberString ));
				
				numberString = itemDTO.getPrecioUltimaPromocion().replaceAll(",", "");
				BigDecimal precioUPromo = BigDecimal.valueOf(  Double.parseDouble( numberString ));
				
				numberString = itemDTO.getPrecioPromocionAnioAnterior().replaceAll(",", "");
				BigDecimal precioPromoAAnterior = BigDecimal.valueOf(  Double.parseDouble( numberString ));
				
				numberString = itemDTO.getPrecioPromocion().replaceAll(",", "");
				BigDecimal precioPromocion =  (numberString != Constants.EMPTY)? BigDecimal.valueOf(  Double.parseDouble( numberString )) : BigDecimal.ZERO;
				/**BigDecimal precioPromocion = BigDecimal.valueOf(  Double.parseDouble( numberString ));**/
				
				item = new TblListaSeleccionSku();
				item.setIdItem(itemDTO.getSku());
				item.setVentasHistoricas( ventasHistoricas );
				item.setMargenContribucion( margenContribucion );
				item.setExistencia( existencia );
				item.setArticuloNuevo(  ( itemDTO.isArticuloNuevo() ) ?Constants.VALUE_TRUE : Constants.VALUE_FALSE );
				if(itemDTO.getFechaUltimaPromocion() != null){
					if(!itemDTO.getFechaUltimaPromocion().equals(Constants.EMPTY) ){
						item.setFechaUpromo( formatDate.parse( itemDTO.getFechaUltimaPromocion()) );
					}
					
				}
				item.setTipoUpromo( itemDTO.getTipoUltimaPromocion());
			    item.setPrecioUpromo( precioUPromo );
				item.setTipoPromoAanterior( itemDTO.getTipoPromocionAnioAnterior() );
				item.setPrecioPromoAanterior( precioPromoAAnterior );	
				item.setIdDepartamento( itemDTO.getIdDepartamento());
				item.setIdCategoria( itemDTO.getIdCategoria());
				item.setIdSubcategoria( itemDTO.getIdSubcategoria());
				item.setPrecioPromo( precioPromocion );
				item.setTipoPromo( itemDTO.getTipoPromocion());
				item.setTipoPublicidad(  concatInfo(itemDTO.getTipoPublicidad()) );
				
		
				listaFinal.add(item);
				
			}
			
			
			return listaFinal;
		}
		
		
		public static List<ItemDTO> tblListaSeleccionSKUToItemDTO(List<TblListaSeleccionSku> listaItems)  throws Exception{
			List<ItemDTO> listaFinal = new ArrayList<ItemDTO>();
			ItemDTO item = null;
			
			for(TblListaSeleccionSku itemSeleccion :listaItems){
				item = new ItemDTO();
				item.setSku( itemSeleccion.getIdItem());
				item.setVentasHistoricas(  (itemSeleccion.getVentasHistoricas() !=null)? Constants.formatNumber.format(itemSeleccion.getVentasHistoricas() ): Constants.EMPTY  );
				item.setMargenContribucion( (itemSeleccion.getMargenContribucion() !=null)? Constants.formatNumber.format( itemSeleccion.getMargenContribucion() ) : Constants.EMPTY  );
				item.setInventario( (itemSeleccion.getExistencia() !=null)? Constants.formatNumber.format( itemSeleccion.getExistencia()) : Constants.EMPTY );
				item.setArticuloNuevo(  ( itemSeleccion.getArticuloNuevo().equals(Constants.VALUE_TRUE) ) ?Boolean.TRUE : Boolean.FALSE     );
				if(itemSeleccion.getFechaUpromo() != null){
					item.setFechaUltimaPromocion( formatDate.format( itemSeleccion.getFechaUpromo()) );
				}
				item.setTipoUltimaPromocion( itemSeleccion.getTipoUpromo());
				item.setPrecioUltimaPromocion( (itemSeleccion.getPrecioUpromo() !=null)? Constants.formatNumber.format( itemSeleccion.getPrecioUpromo()) : Constants.EMPTY  );
				item.setTipoPromocionAnioAnterior( itemSeleccion.getTipoPromoAanterior());
				item.setPrecioPromocionAnioAnterior( (itemSeleccion.getPrecioPromoAanterior() !=null)?Constants.formatNumber.format( itemSeleccion.getPrecioPromoAanterior()) : Constants.EMPTY  );
				item.setIdDepartamento( itemSeleccion.getIdDepartamento() );
				item.setIdCategoria( itemSeleccion.getIdCategoria() );
				item.setIdSubcategoria( itemSeleccion.getIdSubcategoria() );
				item.setPrecioPromocion( (itemSeleccion.getPrecioPromo() !=null)? Constants.formatNumber.format(itemSeleccion.getPrecioPromo() ): Constants.EMPTY  );
				item.setTipoPromocion( itemSeleccion.getTipoPromo());
				item.setTipoPublicidad( unconcatInfo(itemSeleccion.getTipoPublicidad()));
				
				listaFinal.add(item);
				
			}
			
			return listaFinal;
		}
		
		
		
		public static CabeceraReporteDTO tblFolletoToCabeceraReporteDTO(TblFolleto folleto){
			CabeceraReporteDTO cabecera 	= new CabeceraReporteDTO();
			
			cabecera.setNombreCampania(folleto.getTblCampana().getNombre());
			cabecera.setFechaInicio(folleto.getFechaInicio());
			cabecera.setFechaFin(folleto.getFechaFin());
			cabecera.setFolio(String.valueOf( folleto.getIdFolleto()) );
			
			return cabecera;
			
		}
		
		public static CabeceraReporteDTO tblFolletoToCabeceraReporteDTO(TblPrensa prensa){
			CabeceraReporteDTO cabecera 	= new CabeceraReporteDTO();
			
			cabecera.setNombreCampania(prensa.getTblCampana().getNombre());
			cabecera.setFechaInicio(prensa.getFehcaInicio());
			cabecera.setFechaFin(prensa.getFechaFin());
			cabecera.setFolio(String.valueOf( prensa.getIdPrensa()) );
			
			return cabecera;
			
		}
		
		
		public static List<ReporteVentaSkuDTO> tblReporteVentasToReporteVentaSkuDTO(List<TblReporteVentas> reporteVentas){
			
			List<ReporteVentaSkuDTO> resultado 	= new ArrayList<ReporteVentaSkuDTO>();
			ReporteVentaSkuDTO detalleReporte 	= null;
			
			for(TblReporteVentas reporte : reporteVentas){
				detalleReporte = new ReporteVentaSkuDTO();
				
				detalleReporte.setDistrito( reporte.getDistrito());
				detalleReporte.setZonaNombre( reporte.getZona());
				detalleReporte.setSucursal( reporte.getSucursal());
				detalleReporte.setSku( reporte.getCodeArt());
				detalleReporte.setCategoria( reporte.getCategoria());
				detalleReporte.setSubcategoria( reporte.getSubcategoria());
				detalleReporte.setVentaUnidades( reporte.getVentasUnidades());
				detalleReporte.setVentaPesos( reporte.getVentasPesos());
				
				resultado.add(detalleReporte);
			}
			
		
			return resultado;
			
		}
		
		
		public static CatItemDTO loadItemBySKU(String sku,ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception{
			
			List<CatItemDTO> listCatItem								= new ArrayList<CatItemDTO>();
			List<AttrSearch> listAttrSearch								= new ArrayList<AttrSearch>();		
			
			listAttrSearch = generateAttrList(Constants.ATT_ID_ITEM, sku, AttrSearch.SEARCH_TYPE_EQUAL);
			
			listCatItem =  MBUtil.getCatItemByAttr(serviceDynamicCatalogs, listAttrSearch);
			return listCatItem.get(0);
			
		}
		
	public static DepartamentoDTO loadDepartamentoByID(int idDepto,ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception{
			
			List<DepartamentoDTO> listDepto								= new ArrayList<DepartamentoDTO>();
			List<AttrSearch> listAttrSearch								= new ArrayList<AttrSearch>();	
			
			listAttrSearch = generateAttrList(Constants.ATT_ID, String.valueOf( idDepto ), AttrSearch.SEARCH_TYPE_EQUAL);
			
			listDepto = MBUtil.getDepartamentoByAttr(serviceDynamicCatalogs, listAttrSearch);
			
			return listDepto.get(0);
			
		}
	
	public static List<AttrSearch> generateAttrList(String attrName, String attrValue , int searchType){
		
		List<AttrSearch> listAttrSearch				= new ArrayList<AttrSearch>();	
		AttrSearch attr 							= null;
		
		attr = new AttrSearch();
		attr.setSearchType(searchType);
		attr.setAttrName(attrName);
		attr.setValue(attrValue );
		listAttrSearch.add(attr);
		
		return listAttrSearch;
	}
		
		
		
		
		
		public static void loadDescripcionAllItemsVenta(List<ReporteVentaSkuDTO> infoReporteVentaSku,ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception{
			CatItemDTO catItemDTO = new CatItemDTO();
			
			for(ReporteVentaSkuDTO detalle :infoReporteVentaSku){
				catItemDTO = DTOUtil.loadItemBySKU(detalle.getSku(), serviceDynamicCatalogs);
				detalle.setDescripcion(catItemDTO.getCode());
			}
			
		}
		
		public static void loadDescripcionAllItemsInventario(List<ReporteInventarioSkuDTO> infoReporteInventarioSku,ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception{
			CatItemDTO catItemDTO = new CatItemDTO();
			
			for(ReporteInventarioSkuDTO detalle :infoReporteInventarioSku){
				catItemDTO = DTOUtil.loadItemBySKU(detalle.getSku(), serviceDynamicCatalogs);
				detalle.setDescripcion(catItemDTO.getCode());
			}
			
		}
		
		
		
		public static List<ReporteInventarioSkuDTO> tblReporteInventarioToReporteInventarioSkuDTO(List<TblReporteInventario> reporteInventario){
					
					List<ReporteInventarioSkuDTO> resultado 	= new ArrayList<ReporteInventarioSkuDTO>();
					ReporteInventarioSkuDTO detalleReporte 		= null;
					
					for(TblReporteInventario reporte : reporteInventario){
						detalleReporte = new ReporteInventarioSkuDTO();
						
						detalleReporte.setDistrito( reporte.getDistrito());
						detalleReporte.setZonaNombre( reporte.getZona());
						detalleReporte.setSucursal( reporte.getSucursal());
						detalleReporte.setSku( reporte.getCodeArt());
						detalleReporte.setCategoria( reporte.getCategoria());
						detalleReporte.setSubcategoria( reporte.getSubcategoria());
						detalleReporte.setInventarioTienda(reporte.getInvTienda());
						detalleReporte.setInventarioCedis( reporte.getInvCedis());
						detalleReporte.setInventarioTransito( reporte.getInvTransito());
						
						resultado.add(detalleReporte);
					}
					
				
					return resultado;
					
				}
		
		
		/**
		 * setea la informacion faltante de un itemDTO , el nombre del Departamento, Categoria y Subcategoria
		 * @param itemDTOList
		 * @param serviceDynamicCatalogs
		 * @throws Exception 
		 */
		public static void loadInfoFaltante(List <ItemDTO> itemDTOList ,ServiceDynamicCatalogs serviceDynamicCatalogs ) throws Exception{
			
			Map<String,String> mapDepartamentos 		= new HashMap<String,String>();
			Map<String,String> mapCategorias 			= new HashMap<String,String>();
			Map<String,String> mapSubcategorias 		= new HashMap<String,String>();
			List<DepartamentoDTO> listDeptos 			= null;
			List<CategoriaDTO> listCategorias 			= null;
			List<SubcategoriaDTO> listSubcategorias		= null;
			DepartamentoDTO depto						= null;
			CategoriaDTO categoria 						= null;
			SubcategoriaDTO subcategoria 				= null;
			String llave 								= Constants.EMPTY ;
			
			
			for(ItemDTO item : itemDTOList){
				llave = String.valueOf( item.getIdDepartamento() );
				
				if(mapDepartamentos.containsKey(llave)){
					item.setDepartamento( mapDepartamentos.get(llave) );
				}else{
					listDeptos = MBUtil.getDepartamentoByAttr(serviceDynamicCatalogs, generateAttrList(Constants.ATT_ID, llave, AttrSearch.SEARCH_TYPE_EQUAL));
					depto = listDeptos.get(Constants.ZERO);
					item.setDepartamento(depto.getCodigo());
					mapDepartamentos.put(llave, depto.getCodigo());
				}
				
				llave = String.valueOf( item.getIdCategoria() );
				
				if(mapCategorias.containsKey(llave)){
					item.setCategoria( mapCategorias.get(llave) );
				}else{
					listCategorias = MBUtil.getCategoriasByAttr(serviceDynamicCatalogs, generateAttrList(Constants.ATT_ID, llave, AttrSearch.SEARCH_TYPE_EQUAL));
					categoria = listCategorias.get(Constants.ZERO);
					item.setCategoria(categoria.getCodigo());
					mapCategorias.put(llave, categoria.getCodigo());
				}
				
				
				llave = String.valueOf( item.getIdSubcategoria() );
				
				if(mapSubcategorias.containsKey(llave)){
					item.setSubcategoria( mapSubcategorias.get(llave) );
				}else{
					listSubcategorias = MBUtil.getSubcategoriasByAttr(serviceDynamicCatalogs, generateAttrList(Constants.ATT_ID, llave, AttrSearch.SEARCH_TYPE_EQUAL));
					subcategoria = listSubcategorias.get(Constants.ZERO);
					item.setSubcategoria(subcategoria.getCodigo());
					mapSubcategorias.put(llave, subcategoria.getCodigo());
				}
				
				
			}
			
			
		}
		
		/**
		 * Metodo que forma un objeto CategoriaDTO con la informacion que se le proporciona
		 * @param setReg
		 * @return
		 */
		public static ArticuloDTO crearArticuloDTO(Set<CatRegValues> setReg,ServiceArquitectura serviceArquitectura){
			
			ArticuloDTO articulo	= new ArticuloDTO();
			
			for(CatRegValues  regVals : setReg  ) {				
				String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
				if(attName.equals(Constants.ATT_DESCRIPTION)){
					articulo.setDescripcion(regVals.getValue());
				}else if(attName.equals(Constants.ATT_ID)){
					articulo.setSku(regVals.getValue()); 
				}else if ( attName.equals(Constants.ATT_ID_DEPTO) ){
					articulo.setCategoria(regVals.getValue());
				}
				else if ( attName.equals(Constants.ATT_ID_MARCA) ){
					articulo.setMarca(regVals.getValue());
				}
				else if ( attName.equals(Constants.COST) ){
					articulo.setPrecio(regVals.getValue());
				}else if ( attName.equals(Constants.Tipo) ){
					articulo.setTipo(regVals.getValue());
				}else if ( attName.equals(Constants.Medida) ){
					articulo.setMedida(regVals.getValue());
				}
			}
			
			articulo.setImagenesArticulos(serviceArquitectura.buscaSKUById(articulo.getSku()));
			
			return articulo;
		}
		
		
		
		private static String concatInfo(List<String> listTipoPublicidad){
			String resultado = Constants.EMPTY;
			
			for(String tipoPublicidad :listTipoPublicidad){
				resultado += tipoPublicidad+Constants.SEPARADOR;
			}
			
			return resultado;
			
		}
		
		private static List<String> unconcatInfo(String listTipoPublicidad){
			List<String> resultado = new ArrayList<String>();
			
			if(listTipoPublicidad !=null ){
				String[] division = listTipoPublicidad.split(Constants.SEPARADOR);
				
				for(int iterator=0 ; iterator < division.length ; iterator ++ ){
					System.out.println("---->"+ iterator + "::" + division[iterator]);
					resultado.add( division[iterator] );
				}
			}
			
			return resultado;
			
		}
		
	
}

