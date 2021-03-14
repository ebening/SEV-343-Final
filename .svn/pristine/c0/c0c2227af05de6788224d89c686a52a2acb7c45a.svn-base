/**
 * 
 */
package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.*;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblComentarioArticulo;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;



/**
 * @author OMAR
 *
 */
public class XMLUtil {
	
	private static ServiceArquitectura serviceArquitectura;
	 
	
	
	
	public static Document createXMLEjecucion(List<List<TblArticulosHoja>> lstEspacios ,List<String> datosExp , String tipo,String idHoja , ServiceDynamicCatalogs serviceDynamicCatalogs){
		Document document 				= null;
		Boolean exportarImagen 			= Boolean.FALSE;
		Boolean exportarSku 			= Boolean.FALSE;
		Boolean exportarPrecio 			= Boolean.FALSE;
		Boolean exportarDescripcion 	= Boolean.FALSE;
		Boolean exportarComentario 		= Boolean.FALSE;
		double abono;
		
		if(lstEspacios==null || lstEspacios.size()<=0){
			return null;
		}
		
		for(String dato:datosExp){
			if(dato.equals(Constants.XML_EXPORTAR_IMAGEN)){
				exportarImagen=Boolean.TRUE;
			}
			
			else if(dato.equals(Constants.XML_EXPORTAR_SKU)){
				exportarSku=Boolean.TRUE;
			}
			
			else if(dato.equals(Constants.XML_EXPORTAR_PRECIOPROM)){
				exportarPrecio=Boolean.TRUE;
			}
			
			else if(dato.equals(Constants.XML_EXPORTAR_DESCRIPCION)){
				exportarDescripcion=Boolean.TRUE;
			}
			
			/*
			else if(dato.equals(Constants.XML_EXPORTAR_COMENTARIO)){
				exportarComentario=Boolean.TRUE;
			}*/
		}
		
		
		try {
			String configMedio 					= Constants.EMPTY;
			DocumentBuilderFactory factory 		= DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder 							= factory.newDocumentBuilder();
		
			DOMImplementation implementation 	= builder.getDOMImplementation();
			
			document = implementation.createDocument(null, tipo, null);
			document.setXmlVersion("1.0");
			Element raiz = document.getDocumentElement();
			
			if(tipo.equals(Constants.FOLLETO)){
				configMedio = Constants.COLUMNA_HOJA;
			}else if(tipo.equals(Constants.PRENSA)){
				configMedio = Constants.COLUMNA_ESPACIO;
			}
			
			Element configuracionMedio = document.createElement(configMedio); 
			//configuracionMedio.setAttribute(Constants.XML_ATTR_ID, idHoja);
			 Map<String,String> mapRegArt;
			 String precioBase=null;
			
			for( List<TblArticulosHoja>  articulosHoja : lstEspacios ){
			
			for(TblArticulosHoja articulo : articulosHoja){
								 
				mapRegArt=getMapRegArt(articulo.getIdArticulo() , serviceDynamicCatalogs );		
				
				if( mapRegArt!=null ){					 
					precioBase=mapRegArt.get("COST");
				}	
				 
				Element etiquetaArticulo = document.createElement(Constants.XML_ETIQUETA_ARTICULO);
				etiquetaArticulo.setAttribute(Constants.XML_ATTR_ID, articulo.getIdArticulo());
				Text nodoValorCampo = null;
				
				if(exportarImagen){
					Element imagen = document.createElement(Constants.XML_ETIQUETA_IMAGEN);
					imagen.setAttribute(Constants.XML_ATTR_HREF, "file:////admaster/imagenes/" + articulo.getIdArticulo()+ ".jpg"  );
					nodoValorCampo = document.createTextNode(articulo.getTblImageArticulo().getTblImagenes().getPathFile()!=null?articulo.getTblImageArticulo().getTblImagenes().getPathFile():Constants.EMPTY); 				
					//imagen.appendChild(nodoValorCampo); 
					etiquetaArticulo.appendChild(imagen);
				}
				
				if(exportarSku){
					Element skuArticulo = document.createElement(Constants.XML_ETIQUETA_SKU); 
					nodoValorCampo = document.createTextNode(articulo.getIdArticulo()!=null?articulo.getIdArticulo():Constants.EMPTY); 				
					skuArticulo.appendChild(nodoValorCampo); 
					etiquetaArticulo.appendChild(skuArticulo);
				}
				
				if(exportarPrecio){	
					Element precioProm = document.createElement(Constants.XML_ETIQUETA_PRECIOPROM); 
					System.out.println("PRECIO PROMOCION------------------->"+articulo.getPrecioPromocion());
					nodoValorCampo = document.createTextNode(precioBase!=null?precioBase:Constants.EMPTY); 				
					precioProm.appendChild(nodoValorCampo); 
					etiquetaArticulo.appendChild(precioProm);
					
					
					Element precioAbono = document.createElement(Constants.XML_ETIQUETA_ABONO); 
					System.out.println("PRECIO PROMOCION------------------->"+articulo.getPrecioPromocion());
					
					abono=0.0;
					try{
					   abono=Double.parseDouble(precioBase);
					}catch(Exception e){}
					abono= abono/10.0;
					
					nodoValorCampo = document.createTextNode(precioBase!=null? ""+( (double)(Double.parseDouble(precioBase)/10.0))  :Constants.EMPTY); 				
					precioAbono.appendChild(nodoValorCampo); 
					etiquetaArticulo.appendChild(precioAbono);
					
					
				}
				
				if(exportarDescripcion){
					Element descripcion = document.createElement(Constants.XML_ETIQUETA_DESCRIPCION); 
					nodoValorCampo = document.createTextNode(articulo.getTblImageArticulo().getPathDesc()!=null?articulo.getTblImageArticulo().getPathDesc():Constants.EMPTY);
					//if(articulo.getTblImageArticulo().getPathDesc()!=null   ){
						descripcion.setAttribute(Constants.XML_ATTR_HREF, "file:////admaster/descripciones/" + articulo.getIdArticulo()+ ".txt");
					//}
					//descripcion.appendChild(nodoValorCampo); 
					etiquetaArticulo.appendChild(descripcion);
				}
				
				/**System.out.println("Articulo Segment --->"+ articulo.getSegmentId() + "id Hoja--->"+idHoja);**/
	 
				
				
				//configuracionMedio.appendChild(etiquetaArticulo);
				raiz.appendChild(etiquetaArticulo);
			}			
			
			
			}
			
			
			//raiz.appendChild(configuracionMedio); 
			
		
			
			
		} catch (ParserConfigurationException e) {
			Util.logger(XMLUtil.class).error(e);
		}
		
		return document;
	}
	



/**
 * @return the serviceArquitectura
 */
public ServiceArquitectura getServiceArquitectura() {
	return serviceArquitectura;
}


protected static Map<String,String> getMapRegArt( String idArt , ServiceDynamicCatalogs serviceDynamicCatalogs ){
	Map<String,String> result=null;
    Set<CatRegValues> setReg = null;
    
    try{
    ArrayList<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
    AttrSearch attSearch = new AttrSearch();
	attSearch.setAttrName("ID");
					
	List<CatRegs> regs = null;	   
	Map<String,String> mapRegArt;
	CatRegs reg=null;

    attSearch.setValue( idArt );
	lstSearchAttrs.add(attSearch);   
	
	regs = serviceDynamicCatalogs.getRegs("CAT_ARTICULO", lstSearchAttrs);  
			
	if(regs==null || regs.size()<=0){
		return null;
	}
	reg=regs.get(0);
    result=reg.getValuesAsMap();
    
    }catch(Exception e){
    	e.printStackTrace();
    }
    return result;
	
}




/**
 * @param serviceArquitectura the serviceArquitectura to set
 */
public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
	this.serviceArquitectura = serviceArquitectura;
}



 
	
	/**
	public Document createXMLEjecucion(List<TblArticulosHoja> articulosHoja , String tipo,String idHoja){
		
		
			Element tipoMedio 	= new Element(tipo);
			Document doc 		= new Document(tipoMedio);
			String configMedio 	= Constants.EMPTY;
			doc.setRootElement(tipoMedio);
	 
			if(tipo.equals(Constants.FOLLETO)){
				configMedio = Constants.COLUMNA_HOJA;
			}else if(tipo.equals(Constants.PRENSA)){
				configMedio = Constants.COLUMNA_ESPACIO;
			}
			
			Element configuracionMedio = new Element(configMedio);
			configuracionMedio.setAttribute(new Attribute(Constants.XML_ATTR_ID,idHoja));
		
			for(TblArticulosHoja articulo : articulosHoja){
				Element etiquetaArticulo = new Element(Constants.XML_ETIQUETA_ARTICULO);
				
				//etiquetaArticulo.addContent(new Element(Constants.XML_ETIQUETA_IMAGEN).setText());
				etiquetaArticulo.addContent(new Element(Constants.XML_ETIQUETA_SKU).setText(articulo.getIdArticulo()));
				etiquetaArticulo.addContent(new Element(Constants.XML_ETIQUETA_PRECIOPROM).setText(articulo.getPrecioPromocion().toString()));
				etiquetaArticulo.addContent(new Element(Constants.XML_ETIQUETA_DESCRIPCION).setText(articulo.getTblImageArticulo().getPathDesc()));
				//etiquetaArticulo.addContent(new Element(Constants.XML_ETIQUETA_COMENTARIO).setText());
				
				configuracionMedio.addContent(etiquetaArticulo);

			}
			
			doc.getRootElement().addContent(configuracionMedio);
		
			return doc;
		
	}
*/
	


	
	
}
