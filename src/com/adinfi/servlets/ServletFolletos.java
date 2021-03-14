package com.adinfi.servlets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestHandler;
import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.TblArchivoArticulo;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblComentarioArticulo;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblFolletoSistemaVentaId;
import com.adinfi.seven.business.domain.TblImageArticulo;
import com.adinfi.seven.business.domain.TblPreciosFolletoDet;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateSegments;
import com.adinfi.seven.business.domain.TblTemplateUser;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceArticles;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceFolleto;
import com.adinfi.seven.business.services.ServicePrecioFolleto;
import com.adinfi.seven.business.services.ServiceTemplates;
import com.adinfi.seven.presentation.views.ArticuloDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.PdfDocument;
import com.adinfi.seven.presentation.views.util.Utilities;

public class ServletFolletos implements HttpRequestHandler {
	private Logger LOG = Logger.getLogger(ServletFolletos.class);
	ServiceFolleto serviceFolleto;
	ServiceTemplates serviceTemplates;
	ServiceDynamicCatalogs serviceDynamicCatalogs;
	ServiceArticles serviceArticles;
	ServiceArquitectura serviceArquitectura;
	ServicePrecioFolleto servicePrecioFolleto;
	private boolean esCambioTemplate;
	private boolean esEjecucion;
 
	public ServiceArquitectura getServiceArquitectura() {
		return serviceArquitectura;
	}
	public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
		this.serviceArquitectura = serviceArquitectura;
	}
	
	public ServiceArticles getServiceArticles() {
		return serviceArticles;
	}
	public void setServiceArticles(ServiceArticles serviceArticles) {
		this.serviceArticles = serviceArticles;
	}
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}
	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}
	public ServiceTemplates getServiceTemplates() {
		return serviceTemplates;
	}
	public void setServiceTemplates(ServiceTemplates serviceTemplates) {
		this.serviceTemplates = serviceTemplates;
	}
	public ServiceFolleto getServiceFolleto() {
		return serviceFolleto;
	}
	public void setServiceFolleto(ServiceFolleto serviceFolleto) {
		this.serviceFolleto = serviceFolleto;
	}
	
    public ServicePrecioFolleto getServicePrecioFolleto() {
		return servicePrecioFolleto;
	}
	public void setServicePrecioFolleto(ServicePrecioFolleto servicePrecioFolleto) {
		this.servicePrecioFolleto = servicePrecioFolleto;
	}
	
	@Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException
    {
    	//serviceFolleto.test();
    //	test(); 
    	
		request.setCharacterEncoding("UTF-8");
    	esCambioTemplate=false;
    	
    	
    	
    	/*
    	LOG.info("VARIABLE ENTRO---> " + esEjecucion); 
    	LOG.info("FROM REQUEST " + request.getParameter("ejecucion"));
    	LOG.info("ValueOF!"+ Boolean.valueOf( request.getParameter("ejecucion") ));
    	LOG.info("COMMAND!"+ request.getParameter("command") );
    	LOG.info("VARIABLE TERMINO---> " + esEjecucion);*/
    	
    	
       	String command=request.getParameter("command");
       	if( command==null || command.isEmpty() ){
       		return;
       	}
        
       	if( "load_hoja".equals(command) ){
       		esEjecucion = Boolean.valueOf( request.getParameter("ejecucion") );
       		genLoadHojaRsp(request,response);
       	}if( "get_hoja".equals(command) ){    		 
    		genGetHojaRsp(request,response);
    	}else if( "get_skus".equals(command) ){
    		genGetSKUSRsp(request,response);
    	}else if( "get_images".equals(command) ){
    		genGetImagesRsp(request,response);
    	}else if( "add_product".equals(command)  ){
    		genAddProductRsp(request,response ,false );
    	}else if("save_hoja".equals(command)  ){
    		genSaveHojaRsp(request,response  );
    	}else if("delete_item".equals(command)  ){
    		genDeleteItemRsp(request,response);
    	}else if("popUp".equals(command)  ){
    		popUp(request,response  );
    	}else if("gen_copia".equals(command)){
    		generarCopia(request, response);
    	}else if("del_copia".equals(command)){
    		eliminarCopia(request, response);
    	}else if("sel_template".equals(command) ){
    		genSelTemplateRsp(request , response  );
    	}else if("add_coment".equals(command) ){
    		addComent(request , response  );
    	}else if("add_archivo".equals(command) ){
    		addArchivo(request , response  );
    	}else if("cambiar_template".equals(command)){
    		esCambioTemplate=true;
    		cambioTemplate(request, response);
    	}else if("cambiar_template_conf".equals(command)){
    		ejecutarCambioTemplate(request, response);
    	}else if("cerrarHoja".equals(command)){
    		cerrarHoja(request, response);
    	}else if("hojaBlanco".equals(command)){
    		hojaBlanco(request, response);
    	}else if("eliminarHoja".equals(command)){
    		eliminarHoja(request, response);
    	}else if("display_preview".equals(command)   ){
    		previewFolleto(request,response);
    	}else if("print_pdf".equals(command)){
    		genFolletoPDF(request,response  );
    	}
        
        
    }
	
	
	public void eliminarHoja(HttpServletRequest request, HttpServletResponse response){
	
			try{
	    		int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
	    		
	    		TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
	    		
	    		TblFolletoSistemaVentaId fId = new TblFolletoSistemaVentaId(folletoHoja.getIdFolleto(), folletoHoja.getIdSistemaVenta());
	    		TblFolletoSistemaVenta folletoSistVenta = serviceFolleto.getFolletoSistemaVenta(fId);
	    		
	    		if('Y' == folletoSistVenta.getSistemaDefault()){
	    			LOG.error("La copia pertenece a un sistema de venta default, no se puede eleiminar");
	    		}else{
	    			List<TblFolletoHojas> folletoHojas=serviceFolleto.getFolletoHojaByFolleto(folletoHoja.getIdFolleto());
	    			
	    			for (TblFolletoHojas fHoja : folletoHojas) {
						if(fHoja.getIdSistemaVenta().intValue()==folletoHoja.getIdSistemaVenta().intValue()){
							serviceFolleto.deleteFolletoHoja(fHoja);
						}
					}
	    			
	    		}
	    		
	    	}catch(Exception e){
	    		LOG.error(e);
	    	}
		
		/*String  msj_conf = "la hoja fue eliminada satisfactoriamente";
    	String  tipoMensaje =GlobalDefines.SESS_CURR_TYPE_MSJ_CONF_WARN;
    	request.getSession().setAttribute(GlobalDefines.SESS_CURR_TYPE_MSJ_CONF, tipoMensaje);
    	request.getSession().setAttribute(GlobalDefines.SESS_CURR_MSJ_CONF, msj_conf);*/
	    	
		}
    
	 private void hojaBlanco(HttpServletRequest request,HttpServletResponse response) {
		/* try{
			 String nextJSP = "/pages/folletos/Blank.jsp";
			 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
			
				//dispatcher.forward(request,response);
			} catch (IOException |ServletException e) {
				LOG.error(e);
			}
		*/
	}
	 
	 
	 private void previewFolleto(HttpServletRequest request,HttpServletResponse response) {
		/* try{
			 String nextJSP = "/pages/folletos/folletoPreview.jsp";
			 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
			
				dispatcher.forward(request,response);
			} catch (IOException |ServletException e) {
				LOG.error(e);
			}
		*/
	}
	 
	 
	 
	public void cerrarHoja(HttpServletRequest request , HttpServletResponse response ){
		 LOG.info("cerrar Hoja");
		 	esEjecucion = Boolean.valueOf( request.getParameter("ejecucion") );
		 	 LOG.info(esEjecucion);
		 	genSaveHojaRsp(request,response  );
	 }
	
    public void popUp(HttpServletRequest request , HttpServletResponse response ){
    	try{
    		
    		int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
    		if (hojaId <= 0){
    			return;
    		}	
    		
			Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				return;
			}
			
		    TblFolletoHojas  hoja=mapHojas.get(hojaId);
		    if(hoja==null){
		    	return;
		    }

		    int idSegment=Utilities.StringToInt( request.getParameter("segment_id"));
		    String segName=request.getParameter("segName");
		    
		    Set<TblArticulosHoja> articulos=null;
		    articulos=hoja.getArticulos();
		    if(articulos==null){
		       articulos=new HashSet<TblArticulosHoja>()	;
		       hoja.setArticulos(articulos);
		    }
		    
    		List<ArticuloDTO> lstArtAdi=(List<ArticuloDTO>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_ART_ADI );
    		for(ArticuloDTO element : lstArtAdi){
    			TblArticulosHoja articulo=new TblArticulosHoja();
    		    articulo.setIdArticulo(element.getSku());
    		    articulo.setHoja(hoja);
    		    articulo.setSegmentId(idSegment);
    		    articulo.setIdFolleto(hoja.getIdFolleto());
    		    articulo.setSegmentName(segName);
    		    
    		    
    		    TblImageArticulo imageArt=this.serviceArticles.getImageArtByImgId( Integer.parseInt(element.getIdImage()) );
    		    
    		    articulo.setTblImageArticulo(imageArt);
    		    articulos.add(articulo);
    		}
    		
		    List< Set<TblArticulosHoja>> arrAllArts=new ArrayList<>();
		    arrAllArts.add(hoja.getArticulos());
		    
		    lstArtAdi.clear();
		    
		    StringBuffer strBuff=getGridArticulos( request , response ,   arrAllArts , false );
		    
		    response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(strBuff.toString());

    	}catch(Exception e){
    		LOG.error(e);
    	}
    }
      
    public void genSelTemplateRsp( HttpServletRequest request , HttpServletResponse response   ){
    	int hojaId;
    	int templateId;
    	TblFolletoHojas hoja;
    	try{
    		hojaId= Utilities.StringToInt(request.getParameter("hoja_id"));
    		templateId=Utilities.StringToInt(request.getParameter("template_id"));
          	hoja=	this.serviceFolleto.getHoja(hojaId);
          	
          	if(hoja==null){
          		return;
          	}
          	          	           	
          	hoja.setIdTemplate(templateId);
          	this.serviceFolleto.saveHoja(hoja);
          //	forwardHoja(request,response,hoja);
    		//String nextJSP =   "/faces/pages/folletos/selectTemplate.jsp";
    		String nextJSP = request.getContextPath()+ "/servlets/servletFolletos?command=load_hoja&hoja_id="+ hojaId;
    		LOG.info(nextJSP);
    		 
    		//RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
    		//dispatcher.forward(request,response);
    		 response.sendRedirect(nextJSP);

    	
    	
    	}catch(Exception e){
    		
    	}
    	
    }
    
    public void genDeleteItemRsp(HttpServletRequest request , HttpServletResponse response ){
    	StringBuffer xml = new StringBuffer("");
    	try{
    		
    		LOG.info("Borrando item  ");
    		int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
    		if (hojaId <= 0){
    			return;
    		}	
    		
			Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				return;
			}
    		
		    TblFolletoHojas  hoja=mapHojas.get(hojaId);
		    if(hoja==null){
		    	return;
		    }
		    
		    Map<String,TblArticulosHoja> mapArts=hoja.getArticulosByArtIdAndSegment();
		    
		    if(mapArts==null){
		    	return;
		    }
		    
		    int segId=Utilities.StringToInt(request.getParameter("seg_id")  );
		    String artId=request.getParameter("item_id");
		      		        
		    TblArticulosHoja  articulo=mapArts.get(artId+"_"+segId);
		    
		    if( articulo==null ){
		    	return;
		    }
            String segName=articulo.getSegmentName();
            hoja.getArticulos().remove(articulo);
		    //articulo.setHoja(null);
		    //articulo.setIdArticulosHoja(null);
		    
		    List< Set<TblArticulosHoja>> arrAllArts=new ArrayList<>();
		    arrAllArts.add(hoja.getArticulos());
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xml.append("<info>\n");
			xml.append("<code>0</code>\n ");
			xml.append("<segment>"+ articulo.getSegmentId() +"</segment>\n");
			xml.append("<segment_name>"+ segName +"</segment_name>\n");
			xml.append("<template>"+ articulo.getIdFolleto() +"</template>\n");
			xml.append( "<info_grid>\n"  );			
			xml.append( "<![CDATA[");
			xml.append(  getGridArticulos(request,response,arrAllArts,false) );
			xml.append( "]]>");			
			xml.append( "</info_grid>\n");			
			xml.append("</info>\n");			
    		
    	}catch(Exception e){
    		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xml.append("<info>\n");
			xml.append("<code>-1</code>\n ");
			xml.append("</info>\n");
    		
    	}finally{
    		try{
    			 LOG.info("xml respuesta borrado :"+ xml.toString() );
    			 response.setContentType("text/xml");
    			 response.setHeader("Cache-Control", "no-cache");
    			 response.getWriter().write(xml.toString());

    			
    		}catch(Exception e){
    			
    		}
    	}
    	
    }
    
    
    public void genSaveHojaRsp(HttpServletRequest request , HttpServletResponse response ){
    	StringBuffer xml=new StringBuffer("");
    	try{
    		
    		LOG.info("Salvando hoja ");
    		int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
    		if (hojaId <= 0){
    			return;
    		}	
    		
			Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				return;
			}
    		
		    TblFolletoHojas  hoja=mapHojas.get(hojaId);
		    if(hoja==null){
		    	return;
		    }
		    
		    int numArts=Utilities.StringToInt(request.getParameter("num_arts"));
		    int unidades;
		    int segId;
		    int w_img;
		    int h_img;
		    String artId;
		    BigDecimal precioPromocion;
		    String plazo6,plazo9,plazo12,plazo15,plazo18,plazo24;
		    Map<String,TblArticulosHoja> mapArts=hoja.getArticulosByArtIdAndSegment();
		    TblArticulosHoja articulo;
		    String plazosValues="";
		    String plazo;
		    int[] plazos={6,9,12,15,18,24};
		    int j=0;
		    if( mapArts!=null && mapArts.size()>0 ){
			    for(int i=0;i<numArts;i++){
			       plazosValues=null;
			       segId=Utilities.StringToInt(request.getParameter("seg_"+i)  );
			       artId=request.getParameter("item_"+i);
			       precioPromocion=Utilities.StringToBigDecimal(request.getParameter("pricep_"+i));
			       
			       w_img = Utilities.StringToInt(request.getParameter("w_img_"+i));
			       h_img = Utilities.StringToInt(request.getParameter("h_img_"+i));
			       
			       unidades=Utilities.StringToInt( request.getParameter("unidades_"+ i ) );
			       for(j=0;j<plazos.length;j++){
			           plazo=request.getParameter("plazo_"+plazos[j]+"_"+i);
			           if("Y".equals(plazo)  ){
			        	   if(plazosValues==null){
			        		   plazosValues=""+plazos[j];
			        	   }else{
			        		   plazosValues+="," +plazos[j];
			        	   }		   
			           }
			       }
			        
			      
			       articulo=mapArts.get(artId+"_"+segId);
			       if(articulo==null){
			    	  continue; 
			       }
			       
			       
			       
			       LOG.info("############ articulo segname vale :"+ articulo.getSegmentName() );
			       articulo.setPrecioPromocion(  precioPromocion );
			       articulo.setPlazos(plazosValues);
			       articulo.setPronosticoVenta(new Integer(unidades));
			       articulo.setImgWidth(w_img);
			       articulo.setImgHeight(h_img);
			       
			    }
		    }
		    
		    if(esEjecucion){
		    	  hoja.setEstatus(Constants.ESTATUS_CERRADO);
		       }
		    LOG.info(hoja.getEstatus());
    		this.serviceFolleto.saveHoja(hoja);
    		
    		saveComentariosArchivos(request, response);
		   
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xml.append("<info>\n");
			xml.append("<code>0</code>\n ");			 		
			xml.append("</info>\n");	    		
    		
    		
    	}catch(Exception e){
    		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xml.append("<info>\n");
			xml.append("<code>-1</code>\n ");
			xml.append("</info>\n");    		
    	}finally{
    		try{    			
    			 response.setContentType("text/xml");
    			 response.setHeader("Cache-Control", "no-cache");
    			 response.getWriter().write(xml.toString());    			
    		}catch(Exception e){
    			
    		}
    	}
    	
    }
    
    
    public void genAddProductRsp(HttpServletRequest request,
			HttpServletResponse response ,boolean adicional ){
    	
    	try{
    		
    		int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
    		if (hojaId <= 0){
    			return;
    		}	
    		
			Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				return;
			}
			
		    TblFolletoHojas  hoja=mapHojas.get(hojaId);
		    if(hoja==null){
		    	return;
		    }
    		
			//var url="/seven/servlets/servletFolletos?command=add_product&hoja_id=" + hojaId+"&art_id="+idArt + "&img_id="+idImg+"&segmentId="+segmentId+"&principal="+ principal+"&segName="+segName;
		    String idArticulo=request.getParameter("art_id");
		    int idImage= Utilities.StringToInt( request.getParameter("img_id"));
		    int idSegment=Utilities.StringToInt( request.getParameter("segment_id"));
		    String segName=request.getParameter("segName");
		    LOG.info("segName:"+ segName );
		    
		    Set<TblArticulosHoja> articulos=null;
		    articulos=hoja.getArticulos();
		    if(articulos==null){
		       articulos=new HashSet<TblArticulosHoja>()	;
		       hoja.setArticulos(articulos);
		    }
		    
		    TblArticulosHoja articulo=new TblArticulosHoja();
		    articulo.setIdArticulo(idArticulo);
		    //articulo.setIdHoja(hojaId);
		    articulo.setHoja(hoja);
		    articulo.setSegmentId(idSegment);
		    articulo.setIdFolleto(hoja.getIdFolleto());
		    articulo.setSegmentName(segName);
		   // articulo.getTblImageArticulo().setIdImageArt(idImage);
		    
		    
		    TblImageArticulo imageArt=this.serviceArticles.getImageArtByImgId(idImage);
		    		
		    /*
		    imageArt.setIdArticulo(idArticulo);
		   // imageArt.setIdImageArt(idImage);
		    
		    TblImagenes tblImagenes=new TblImagenes();
		    tblImagenes.setIdImage(idImage);
		    
		    imageArt.setTblImagenes(tblImagenes);
		    */
		    articulo.setTblImageArticulo(imageArt);
		    		    		    		    
		    articulos.add(articulo);
		    
		    List< Set<TblArticulosHoja>> arrAllArts=new ArrayList<>();
		    arrAllArts.add(hoja.getArticulos());
		    
		    StringBuffer strBuff=getGridArticulos( request , response ,   arrAllArts , false );
		    
		    response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(strBuff.toString());
		    		        		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
     
    private StringBuffer getGridArticulos(HttpServletRequest request,
			HttpServletResponse response ,   List< Set<TblArticulosHoja>> arrAllArts , boolean readOnly ){
    	StringBuffer strBuff=new StringBuffer("");
    	DecimalFormat formateador = new DecimalFormat("#,##0.00;(-#,##0.00)");
    	
    	try{
    	
    	strBuff.append("<table id='tablaArticulos' class='tablaArticulos'>");
    	strBuff.append( getHeadGridArticulos() );
    	strBuff.append( "	<tbody >" );   
	    Set<CatRegValues> setReg = null;
	    ArrayList<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
	    AttrSearch attSearch = new AttrSearch();
		attSearch.setAttrName("ID");
		
		
		
		List<CatRegs> regs = null;	   
		CatRegs reg=null;
    	String idArt=null;
    	String descrip=null;
    	String precioPromocion;
    	String unidades;
    	
    	String abSem6mes;
    	String abSem9mes;
    	String abSem12mes;
    	String abSem15mes;
    	String abSem18mes;
    	String abSem24mes;    	
    	String precioBase;
    	
    	int numArts=0;
    	
    	if( arrAllArts!=null && arrAllArts.size()>0 ){
    		
    		int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
    		List<TblPreciosFolletoDet> preciosArticulos = null;
    		if (hojaId > 0){
    			TblFolletoHojas hojaFolleto = serviceFolleto.getHoja(hojaId);
    			
    			preciosArticulos = servicePrecioFolleto.getPreciosArticuloHoja(hojaFolleto.getIdSistemaVenta(), hojaFolleto.getIdFolleto());
    		}
    		
    		
	    	for( Set<TblArticulosHoja> articulos : arrAllArts    ){	
	    	
			    	if(  articulos!=null && articulos.size()>0 ){
			    		int i=0;
			    		numArts+=articulos.size();
			    		Map<String,String> treePlazos;
			    		String[] plazos;
			    		for(TblArticulosHoja articulo : articulos ){
			    			//inicio articulo
			    			
			    			TblPreciosFolletoDet precioArticulo = getPreciosArticulo( preciosArticulos, articulo.getIdArticulo() );
			    			if( precioArticulo != null ){
			    				
			    				if( precioArticulo.getAbsem6mes()==null ){
				    				abSem6mes = "";
				    			}else{
				    				abSem6mes = "" + precioArticulo.getAbsem6mes().doubleValue();
				    			}
				    			
				    			if( precioArticulo.getAbsem9mes()==null ){
				    				abSem9mes = "";
				    			}else{
				    				abSem9mes = "" + precioArticulo.getAbsem9mes().doubleValue();
				    			}    			
				    			    			
				    			if( precioArticulo.getAbsem12mes()==null ){
				    				abSem12mes = "";
				    			}else{
				    				abSem12mes = "" + precioArticulo.getAbsem12mes().doubleValue();
				    			}
				    			
				    			if( precioArticulo.getAbsem15mes()==null ){
				    				abSem15mes = "";
				    			}else{
				    				abSem15mes = "" + precioArticulo.getAbsem15mes().doubleValue();
				    			}
				    			
				    			if( precioArticulo.getAbsem18mes()==null ){
				    				abSem18mes = "";
				    			}else{
				    				abSem18mes = "" + precioArticulo.getAbsem18mes().doubleValue();
				    			}
				    			
				    			if( precioArticulo.getAbsem24mes()==null ){
				    				abSem24mes = "";
				    			}else{
				    				abSem24mes = "" + precioArticulo.getAbsem24mes().doubleValue();
				    			}
				    			
			    			}else{
			    				
			    				abSem6mes = "";      
			    				abSem9mes = "";
			    				abSem12mes = "";
			    				abSem15mes = "";
			    				abSem18mes = "";
			    				abSem24mes = "";
			    			}    			
		    				
			    			if( articulo.getPrecioPromocion()==null ){
		    					precioPromocion = "";	
			    			}else{
			    				precioPromocion = "" + articulo.getPrecioPromocion().doubleValue();
			    			}
		    				
			    			if( articulo.getPronosticoVenta()==null ){
			    				unidades="";
			    			}else{
			    				unidades=""+articulo.getPronosticoVenta().intValue();
			    			}

			    			attSearch.setValue( ""+ articulo.getIdArticulo() );
			    			lstSearchAttrs.add(attSearch);    			    		
			    			regs = serviceDynamicCatalogs.getRegs("CAT_ARTICULO", lstSearchAttrs);  
			    			
			    			if(regs==null || regs.size()<=0){
			    				continue;
			    			}
			    			reg=regs.get(0);
			    			precioBase=reg.getValuesAsMap().get("COST");
			    			descrip=reg.getValuesAsMap().get("DESCRIP");
			    			
			    			  treePlazos=new TreeMap<String,String>();
			    			    if( articulo.getPlazos()!=null ){
			    			    	plazos=articulo.getPlazos().split(",");
			    			    	if(plazos!=null){
			    			    		for( String plazo :plazos ){
			    			    			if(plazo==null || plazo.trim().length()<=0) continue;
			    			    			treePlazos.put(plazo, plazo);
			    			    		}		    				    		
			    			    	}
			    			    }		    				    
			    			 
			    			strBuff.append( "	<tr >" );
			    		    strBuff.append( "		<td >"+ (i+1) +"</td>");
			    		    strBuff.append("        <td >"+ articulo.getSegmentName() +"</td>   ");
			    		    strBuff.append("		<td >"+ articulo.getIdArticulo() +"</td>");
			    		    strBuff.append("		<td >"+descrip  +"</td>");    
			    		    strBuff.append("		<td >"+ articulo.getHoja().getIdCategory() +"</td>");
			    		    strBuff.append("		<td >"+ precioBase  +"</td>");	//precio stories	    
			    		    
			    		    if( readOnly==false ){
			    		      strBuff.append("		<td ><input type='hidden'  id='seg_"+i+"' value='"+articulo.getSegmentId() +"'  > <input type='hidden' id='item_"+i+"' value='"+articulo.getIdArticulo() +"'  >   "+ "$ <input id='pricep_"+ i  +"' type='text' size='6'  value='"+precioPromocion+"'  onkeypress=\"return isNumberKey(event)\"    onblur=\"onlyNumber('pricep_"+ i  +"')\" > </td>");
			    		    }else{
			    		    	strBuff.append("		<td >"+precioPromocion+"</td>");			    		    	
			    		    }
			    		   
			    		    
			    		    if( readOnly==false ){
			    		       strBuff.append("		<td '><table class='checkBoxAlign'><tr><td><input type='checkbox' "+ ( treePlazos.containsKey("6")? "checked='checked'" : "" )   +"   id='plazo_6_"+ i  +"'  value='Y'      >6</td><td><input type='checkbox' "+  ( treePlazos.containsKey("9")? "checked='checked'" : "" )  +"  id='plazo_9_"+ i +"'  value='Y'   >9</td><td><input type='checkbox'  "+ ( treePlazos.containsKey("12")? "checked='checked'" : "" ) +"   id='plazo_12_"+ i +"'  value='Y'     >12</td><td><input type='checkbox' "+ ( treePlazos.containsKey("15")? "checked='checked'" : "" ) +"    id='plazo_15_"+ i  +"'  value='Y'     >15</td><td><input type='checkbox' "+ ( treePlazos.containsKey("18")? "checked='checked'" : "" ) +"   id='plazo_18_"+ i +"'  value='Y'    >18</td><td><input type='checkbox'  "+ ( treePlazos.containsKey("24")? "checked='checked'" : "" ) +"   id='plazo_24_"+ i +"'  value='Y'     >24</td></tr></table>     </td>");
			    		    }else{
				    		       strBuff.append("		<td ><table class='checkBoxAlign><tr><td><input disabled='disabled'   type='checkbox' "+ ( treePlazos.containsKey("6")? "checked='checked'" : "" )   +"   id='plazo_6_"+ i  +"'  value='Y'      >6</td><td><input  disabled='disabled'  type='checkbox' "+  ( treePlazos.containsKey("9")? "checked='checked'" : "" )  +"  id='plazo_9_"+ i +"'  value='Y'   >9</td><td><input  disabled='disabled'  type='checkbox'  "+ ( treePlazos.containsKey("12")? "checked='checked'" : "" ) +"   id='plazo_12_"+ i +"'  value='Y'     >12</td><td><input  disabled='disabled'  type='checkbox' "+ ( treePlazos.containsKey("15")? "checked='checked'" : "" ) +"    id='plazo_15_"+ i  +"'  value='Y'     >15</td><td><input  disabled='disabled'  type='checkbox' "+ ( treePlazos.containsKey("18")? "checked='checked'" : "" ) +"   id='plazo_18_"+ i +"'  value='Y'    >18</td><td><input  disabled='disabled'  type='checkbox'  "+ ( treePlazos.containsKey("24")? "checked='checked'" : "" ) +"   id='plazo_24_"+ i +"'  value='Y'     >24</td></tr></table>     </td>");			    		    	
			    		    }
			    		   
			    		    
			    		    if( readOnly==false ){
			    		    strBuff.append("		<td>"+ "<input id='unidades_"+ i  +"' type='text' size='6'  value='"+ unidades + "' onKeyPress=\"return isNumberKey(event)\" onblur=\"onlyNumber('unidades_"+ i  +"')\" > </td>");
			    		    }else{
				    		    strBuff.append("		<td >"+  unidades + "</td>");			    		    	
			    		    }
			    		    
			    		    strBuff.append("		<td >&nbsp;</td>");
			    		    strBuff.append("		<td >"+ "$ "+ abSem6mes +"</td>");
			    		    strBuff.append("		<td >"+ "$ "+ abSem9mes +"</td>");
			    		    strBuff.append("		<td >"+ "$ "+ abSem12mes +"</td>");
			    		    strBuff.append("		<td >"+ "$ "+ abSem15mes +"</td>");
			    		    strBuff.append("		<td >"+ "$ "+ abSem18mes +"</td>");
			    		    strBuff.append("		<td >"+ "$ "+ abSem24mes +"</td>");
			    		    
			    		    
			    		    if( readOnly==false ){
			    		    	String[] arr = {"info" ,"inventario" ,"proximamente"};
			    		    	String[] arr1 = {"warn" ,"inventario" ,"proximamente"};
			    		    	String[] arr2 = {"error" ,"inventario" ,"proximamente"};
			    		       strBuff.append("		<td ><button class='botonInventario' onclick='mensajes( \""+ arr[0]+"\", \""+arr[1]+"\",\""+arr[2]+"\");mensajes( \""+ arr1[0]+"\", \""+arr1[1]+"\",\""+arr1[2]+"\");mensajes( \""+ arr2[0]+"\", \""+arr2[1]+"\",\""+arr2[2]+"\");'></td>");
			    		       strBuff.append("		<td ><button class='botonEliminar'   onclick='deleteItem( "+ articulo.getSegmentId() +" ,\""+articulo.getIdArticulo()+"\");' ></td>");
			    		       
			    		    }else{
			    		       strBuff.append("		<td >&nbsp;</td>");			    		    	
			    		    }
			    		    
			    		    strBuff.append("	</tr>");    			
			    			
			    		    i++;
			    			
			    			//fin articulo
			    		}

			    	}
	    	
	    	}//for
	    	
    	}
    	
    	strBuff.append( "</tbody >" );   
    	strBuff.append("</table>");
    	strBuff.append("<input type='hidden'  id='num_arts'  value='" + numArts +"'  >");
    	
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	 
    	return strBuff;
    }
    
    private TblPreciosFolletoDet getPreciosArticulo(List<TblPreciosFolletoDet> precios, String idSku){
    	TblPreciosFolletoDet precio = null;
    	Iterator<TblPreciosFolletoDet> preciosIterator = precios.iterator();
    	while(preciosIterator.hasNext()){
    		TblPreciosFolletoDet elemento = preciosIterator.next();
    		if( idSku.equals(elemento.getIdSku()) ){
    			precio = elemento;
    			break;
    		}
    	}
    	
    	return precio;
    }
    
    private StringBuffer getHeadGridArticulos(   ){
    	StringBuffer head=new StringBuffer("");
    	head.append ("	<thead>");
        head.append ("	<tr>");
        head.append ("		<th >No.</TD>");
        head.append("		<th >Posici&oacute;n</TD>");        
        head.append("		<th >SKU</TD>");			
        head.append("		<th >Descripci&oacute;n</TD>");
        head.append("		<th >Categor&iacute;a</TD>");
        head.append("		<th >Precio(Stories)</TD>");                
        head.append("		<th >Precio Promoci&oacute;n</TD>");
        head.append("		<th >Plazo (En Meses)</TD>");        
        head.append("		<th >Pron&oacute;stico de ventas</br> (En Unidades)</TD>"); 
        head.append("		<th >Precio Contado</TD>");        
        head.append("		<th >AB 6 Meses</TD>");
        head.append("		<th >AB Meses</TD>");
        head.append("		<th >AB Meses</TD>");
        head.append("		<th >AB Meses</TD>");
        head.append("		<th >AB Meses</TD>");
        head.append("		<th width='5%' align='center'>AB Meses</TD>");                              
        head.append("		<th width='10%' align='center'></TD>");	
        head.append("		<th width='10%' align='center'></TD>");
        head.append("	</tr>");    
        head.append ("	</thead>");
    	return head;
    }
      
    public void genGetImagesRsp(HttpServletRequest request,
			HttpServletResponse response){
    	StringBuffer codeHTML=new StringBuffer("");
    	String idArt = request.getParameter("id_articulo");
    	if(idArt==null || idArt.isEmpty() ){
    		return;
    	}
    	
    	try{
    	 List<TblImageArticulo> lstImages=this.serviceArticles.getImagesByArt(idArt);
    	  
    	 
    	 codeHTML.append("<table width='100%' border='0' cellspacing='2' cellpadding='0' align='center'>");
		 codeHTML.append("<tr><td>");
		 
		 if( lstImages==null || lstImages.size()<=0 ){
			 
		 }else{
			 for(TblImageArticulo item : lstImages  ){
				  
			    codeHTML.append("<tr><td align='left'     ><div  style='width:0 ;border:0;padding:0;margin: 0;'   class='drag t1'><img src='"+request.getContextPath()+"/resources/images/fotos_articulos/" + item.getTblImagenes().getPathFile() + "'    id='"+ item.getTblImagenes().getIdImage() +"' name='"+ item.getTblImagenes().getIdImage() +"'   alt='"+ item.getIdArticulo() +"'   border='0' width='120' height='120' style='top:0; left:0;'  /></div></td></tr>");
			 }
		 }
		 
		 
		 
		 codeHTML.append("</td></tr>");
		 codeHTML.append("</table>");
		 
		 response.setContentType("text/xml");
		 response.setHeader("Cache-Control", "no-cache");
		 response.getWriter().write(codeHTML.toString());		 
		 
    		
    	}catch(Exception e){
    		
    	}
    	
    	
    	
    }
   
    
    public void genFolletoPDF(HttpServletRequest request,
			HttpServletResponse response){
    	
    	int idFolleto=Integer.valueOf(request.getParameter("idFolleto"))  ;
    	int idSistVenta=Integer.valueOf(request.getParameter("idSistemaVenta"));
    	
    	try{
    	TblFolleto folleto= serviceFolleto.getById(idFolleto);
		List<TblFolletoHojas> hojasFolleto = serviceFolleto.getHojasByIdFolleto(idFolleto);
		String dir=request.getSession().getServletContext().getRealPath("/");
		dir+="resources/images/fotos_articulos/";
		
		PdfDocument docPDF=new PdfDocument(dir,serviceDynamicCatalogs );
		docPDF.createPdfDocument(request , response, folleto,  idSistVenta , hojasFolleto   );
		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		    	
    	
    }
    
    
    public void genLoadHojaRsp(HttpServletRequest request,
			HttpServletResponse response){
    	int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
    	
    	
		if (hojaId <= 0){
			return;
		}	
		
		try{
			
		//	genFolletoPDF(request,response);
            
			Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				mapHojas=new TreeMap<Integer,TblFolletoHojas> ();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_HOJAS, mapHojas);
			}
			
		    TblFolletoHojas  hoja=mapHojas.get(hojaId);
		    		
		    if( hoja==null ){
		    	hoja=	this.serviceFolleto.getHoja(hojaId);
		    	
		    	if(hoja==null){
				   return;
				}
		    //	mapHojas.put( new Integer(hojaId) , hoja );		   
		    }
		    
		    //Agregar lista de Comentarios
		    List<TblComentarioArticulo> lstComentario=(List<TblComentarioArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_COMENT );
			if( lstComentario==null ){
				lstComentario=new ArrayList<TblComentarioArticulo>();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_COMENT, lstComentario);
			}
			
			//Agregar lista de Archivo
		    List<TblArchivoArticulo> lstArchivos=(List<TblArchivoArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_ARCHIVO );
			if( lstArchivos==null ){
				lstArchivos=new ArrayList<TblArchivoArticulo>();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_ARCHIVO, lstArchivos);
			}
		    
		    if(hoja.getIdTemplate()==null || hoja.getIdTemplate()<=0 ){
		       forwardSelectTemplate(request,response,hoja);
		       
		    }else {
		       forwardHoja(request,response,hoja);
		    }
									
		}catch(Exception e){
			LOG.error(e);
		}
    	
    }
        
	@SuppressWarnings("unchecked")
	public void genGetHojaRsp(HttpServletRequest request,HttpServletResponse response) {
		
		int hojaId 				= Utilities.StringToInt(request.getParameter("hoja_id"));
		
		LOG.info("ES EJECUCION -------- >>>>>>" + esEjecucion);
		
		
		if (hojaId <= 0){
			return;
		}	
		 		 		 		 
		try {
			
			Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				mapHojas=new TreeMap<Integer,TblFolletoHojas> ();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_HOJAS, mapHojas);
			}
			
		    TblFolletoHojas  hoja=mapHojas.get(hojaId);
		    		
		    if( hoja==null ){
		    	hoja=	this.serviceFolleto.getHoja(hojaId);
		    	
		    	if(hoja==null){
				   return;
				}
		    	mapHojas.put( new Integer(hojaId) , hoja );		   
		    }
		    
		    if(hoja.getIdTemplate()==null || hoja.getIdTemplate()<=0 ){
		       forwardSelectTemplate(request,response,hoja);
		       return;
		    }
		    
		    
		    TblTemplate  template=this.getServiceTemplates().getTemplate(hoja.getIdTemplate());
		    if(template==null ){
		    	   return;
		    }

		    
		    //test
		    genTemplateThumb(template,request);
		    
		    
		    //Agregar lista de Comentarios
		    List<TblComentarioArticulo> lstComentario=(List<TblComentarioArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_COMENT );
			if( lstComentario==null ){
				lstComentario=new ArrayList<TblComentarioArticulo>();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_COMENT, lstComentario);
			}
			
			//Agregar lista de Archivo
		    List<TblArchivoArticulo> lstArchivos=(List<TblArchivoArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_ARCHIVO );
			if( lstArchivos==null ){
				lstArchivos=new ArrayList<TblArchivoArticulo>();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_ARCHIVO, lstArchivos);
			}
			
			//Agregar lista para articulos adicionales
			List<ArticuloDTO> lstArtAdi=(List<ArticuloDTO>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_ART_ADI );
			if( lstArtAdi==null ){
				lstArtAdi=new ArrayList<ArticuloDTO>();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_ART_ADI, lstArtAdi);
			}
			
			//Agregar mensaje confirmacion a session
			String tipoMensaje = (String) request.getSession().getAttribute(GlobalDefines.SESS_CURR_MSJ_CONF );
			if(tipoMensaje == null){
				tipoMensaje="";
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_TYPE_MSJ_CONF, tipoMensaje);
			}
			
			
			//Agregar mensaje confirmacion a session
			String msj_conf = (String) request.getSession().getAttribute(GlobalDefines.SESS_CURR_MSJ_CONF );
			if(msj_conf == null){
				msj_conf="";
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_MSJ_CONF, msj_conf);
			}
			
			// StringBuffer xmlProds=new StringBuffer("");
			StringBuffer xml = new StringBuffer(
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xml.append("<info>\n");
			xml.append("<code>0</code>\n ");
			
			xml.append("<hoja_id>"+ hojaId +"</hoja_id>\n ");
			
			if( template.getPathHead()!=null && template.getPathHead().isEmpty()==false ){
				xml.append("<path_head>"+ request.getContextPath() + "/"  + GlobalDefines.RUTA_CARGA + "/"+ template.getPathHead() +"</path_head>\n ");
			}
			
			if( template.getPathFoot()!=null && template.getPathFoot().isEmpty()==false ){
				xml.append("<path_foot>"+  request.getContextPath() + "/" + GlobalDefines.RUTA_CARGA + "/"+  template.getPathFoot() +"</path_foot>\n ");
			}			
			
			
			boolean readOnly=false;
			
			if( GlobalDefines.HOJA_CERRADA.equals( ""+ hoja.getEstatus()) ){
				readOnly=true;
			}			
			
									 
			Set<TblArticulosHoja> arts= hoja.getArticulos();
			List<Set<TblArticulosHoja>> allArts=new ArrayList<>();
			allArts.add(arts);
						
			if( hoja.getChildHojas()!=null && hoja.getChildHojas().size()>0 ){
				for( TblFolletoHojas hojaChild : hoja.getChildHojas()  ){
					arts= hojaChild.getArticulos();
					allArts.add(arts);					
				}								
			}
			
			if( hoja.getChildHojas()!=null && hoja.getChildHojas().size()>0 ){
				//Es prensa
				readOnly=true;
			}
			xml.append("<readonly>"+ readOnly +"</readonly>");
			
			if(esEjecucion){
				xml.append("<ejecucion>true</ejecucion>");
			}
			
			
			xml.append(genXMLSegment(template.getRootSegmentId(),
					template.getMapSegmentsByParent(),
					template.getMapSegmentsById())); 			
			
			
			Map<String,String> mapRegArt;
			String descrip;

			StringBuffer xmlProds=new StringBuffer("");
			StringBuffer xmlGrid=new StringBuffer("");
			if (hojaId > 0) {
				
				//arts = artsDAO.getGrid(evento, hoja, version, copia);
				if( allArts!=null && allArts.size()>0 ){
					for( Set<TblArticulosHoja>  arts1 : allArts  ){
				        arts=arts1;
						if (arts != null) {
							
							for (TblArticulosHoja art :arts  ) {
								mapRegArt=getMapRegArt(art.getIdArticulo() );
								if( mapRegArt==null ){
									continue;
								}
								descrip=mapRegArt.get("DESCRIP");
								if (art == null)
									continue;
								xmlProds.append("<producto>\n");
		
								xmlProds.append("<item>" + art.getIdArticulo() + "</item>\n");
								xmlProds.append("<descripcion>" + descrip
										+ "</descripcion>\n");
								xmlProds.append("<precio>"  + art.getPrecioPromocion() + "</precio>\n");
								xmlProds.append("<hoja>" + hoja.getNumHoja() + "</hoja>\n");
								xmlProds.append("<version>" + ""
										+ "</version>\n");
								//Hibernate.initialize(art.getTblImageArticulo()); 
								
								xmlProds.append("<id_imagen>" + art.getTblImageArticulo().getTblImagenes().getIdImage()
										+ "</id_imagen>\n");
								xmlProds.append("<marca>" + ""
										+ "</marca>\n");
								xmlProds.append("<nombre>" + ""
										+ "</nombre>\n");
								xmlProds.append("<imgwidth>" + (art.getImgWidth() == null ? 120 : art.getImgWidth().intValue()) + ""
										+ "</imgwidth>\n");
								xmlProds.append("<imgheight>" + (art.getImgHeight() == null ? 120 : art.getImgHeight().intValue()) +  ""
										+ "</imgheight>\n");
								xmlProds.append("<path><![CDATA[" + art.getTblImageArticulo().getTblImagenes().getPathFile()
										+ "]]></path>\n");
								xmlProds.append("<copia>" + ""
										+ "</copia>\n");
								xmlProds.append("<comentarios>" + ""
										+ "</comentarios>\n");
								xmlProds.append("<tipo_imagen>" + ""
										+ "</tipo_imagen>\n");
								xmlProds.append("<tipo_sku>" + ""
										+ "</tipo_sku>\n");
								xmlProds.append("<departamento>" + ""
										+ "</departamento>\n");
								xmlProds.append("<template_id>" + art.getHoja().getIdTemplate()
										+ "</template_id>\n");
								xmlProds.append("<segment_id>" + art.getSegmentId()
										+ "</segment_id>\n");
								xmlProds.append("<principal>"
										+ ( 1==1 ? "Y" : "")
										+ "</principal>\n");
		
								xmlProds.append("</producto>\n");
							}
							
																																					
						//	xmlGrid.append(  getGridArticulos(request,response,allArts , readOnly ) );
										
									
											
							
							
		
						}//if arts
					
					}//for
			  }// if( allArts!=null    
				
			
			  xml.append("<info_prods>\n");	
			  xml.append(xmlProds) ;
			  xml.append("</info_prods>\n");	
			  
			  xml.append( "<info_grid>\n"  );
			  if(!esEjecucion){
				  xml.append( "<![CDATA[");
				  xml.append(  getGridArticulos(request,response,allArts , readOnly ) );
				  xml.append( "]]>");
			  }
			  xml.append( "</info_grid>\n");	
				
				
			}
 
			xml.append("</info>\n");	
			


			/*
			 * response.setContentType("text/xml");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.getWriter().write(xml.toString());
			 */

			LOG.info("xml generado :\n" + xml);
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			try {
				 response.getWriter().print(xml.toString() );
				//response.getOutputStream().print(xml.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
          LOG.error(e);
          e.printStackTrace();
		} finally {
			// templateDAO.
		}

	}

	public StringBuffer genXMLSegment(int segId,
			Map<Integer, List<TblTemplateSegments>> segmentsByParent,
			Map<Integer, TblTemplateSegments> segmentsById) {
		StringBuffer xml = new StringBuffer("");

		List<TblTemplateSegments> segmentsChilds = segmentsByParent.get(segId);
		TblTemplateSegments segment = segmentsById.get(segId);
		int parentId = segment.getSegmentParentId();
		if (segmentsByParent == null || segmentsByParent.size() <= 0)
			return xml;
		if (segment == null)
			return xml;
		try {

			xml.append("<segment_info_" + parentId + ">\n");
			xml.append("  <segment_id_" + parentId + ">" + segId
					+ "</segment_id_" + parentId + "> \n ");
			 
			if(  segment.getTemplateChildId() >0  ){
			     xml.append("  <segment_type_" + parentId + ">R"  
					+ "</segment_type_" + parentId + "> \n ");
			}else{
				  xml.append("  <segment_type_" + parentId + ">" + segment.getTipo()
							+ "</segment_type_" + parentId + "> \n ");
			}
			xml.append("  <segment_width_" + parentId + ">"
					+ segment.getWidth() + "</segment_width_" + parentId
					+ "> \n ");
			xml.append("  <segment_height_" + parentId + ">"
					+ segment.getHeight() + "</segment_height_" + parentId
					+ ">\n");
			if (segmentsChilds != null && segmentsChilds.size() > 0) {
				xml.append("  <segment_childs_" + parentId + ">\n");
				for (TblTemplateSegments segTmp : segmentsChilds) {
					xml.append(genXMLSegment(segTmp.getSegmentId(),
							segmentsByParent, segmentsById));
				}
				xml.append("  </segment_childs_" + parentId + "> ");
			}else{
				
				if(  segment.getTemplateChildId() >0  ){
					xml.append("  <segment_childs_" + parentId + ">\n");
					TblTemplateSegments segTmp1=segment.getTemplateChild().getMapSegmentsById(  ).get(segment.getTemplateChild().getRootSegmentId() );
							xml.append("<segment_info_" + segment.getSegmentId() + ">\n");
							xml.append("  <segment_id_" + segment.getSegmentId()  + ">" + segment.getTemplateChild().getRootSegmentId()
									+ "</segment_id_" + segment.getSegmentId()  + "> \n ");
							xml.append("  <segment_type_" + segment.getSegmentId()  + ">R"  
									+ "</segment_type_" + segment.getSegmentId()  + "> \n ");
							xml.append("  <segment_width_" + segment.getSegmentId()  + ">"
									+ segment.getWidth() + "</segment_width_" + segment.getSegmentId() 
									+ "> \n ");
							xml.append("  <segment_height_" + segment.getSegmentId()  + ">"
									+ segment.getHeight() + "</segment_height_" + segment.getSegmentId() 
									+ ">\n");
					 
							xml.append("  <segment_childs_" + segment.getSegmentId() + ">\n");	
							List<TblTemplateSegments> tmpChldTmp=segment.getTemplateChild().getMapSegmentsByParent().get(segment.getTemplateChild().getRootSegmentId());
						//xml.append(genXMLSegment( segment.getTemplateChild().getRootSegmentId()  ,
							//	segment.getTemplateChild().getMapSegmentsByParent() , segment.getTemplateChild().getMapSegmentsById()));
							 
							for (TblTemplateSegments segTmp : tmpChldTmp ) {
								xml.append(genXMLSegment(segTmp.getSegmentId(),
										segment.getTemplateChild().getMapSegmentsByParent(), segment.getTemplateChild().getMapSegmentsById()));
							}
						 
						xml.append("  </segment_childs_" + segment.getSegmentId() + ">\n");	
						xml.append("  <segment_name_"
								+ segment.getSegmentId()
								+ ">"
								+ (segment.getSegName() == null ? "" : segment.getSegName())
								+ "</segment_name_" + segment.getSegmentId() + "> \n ");
						xml.append("</segment_info_" + segment.getSegmentId() + ">\n");
						
						xml.append("  <segment_template_id_" + segment.getSegmentId()  + ">"
								+ segment.getTblTemplate().getTemplateId() + "</segment_template_id_" + segment.getSegmentId() 
								+ "> \n ");	
					 
					xml.append("  </segment_childs_" + parentId + ">\n ");															
					
				}
				
				
			}

			xml.append("  <segment_name_"
					+ parentId
					+ ">"
					+ (segment.getSegName() == null ? "" : segment.getSegName())
					+ "</segment_name_" + parentId + "> \n ");
			
			xml.append("  <segment_template_id_" + parentId  + ">"
					+ segment.getTblTemplate().getTemplateId() + "</segment_template_id_" + parentId 
					+ "> \n ");			
			
			xml.append("</segment_info_" + parentId + ">\n");

		} catch (Exception e) {
			e.printStackTrace();

		}
		return xml;

	}
    
	public void genGetSKUSRsp(HttpServletRequest request,
			HttpServletResponse response) {
		int hojaId = Utilities.StringToInt(request.getParameter("hoja_id"));
		if (hojaId <= 0){
			return;
		}	
		 		 		 		 
		try {
			
		    TblFolletoHojas  hoja=this.serviceFolleto.getHoja(hojaId);	
		    if(hoja==null){
		    	return;
		    }
		    
		    Set<CatRegValues> setReg = null;
		    ArrayList<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
		    AttrSearch attSearch = new AttrSearch();
			attSearch.setAttrName("ID_CATEGORIA");
			attSearch.setValue( ""+hoja.getIdCategory() );
			lstSearchAttrs.add(attSearch);
			
			
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs("CAT_ARTICULO",
					lstSearchAttrs);		    
			
		    if( regs==null || regs.size()<=0 ){
		    	return ;
		    }
		    
			// StringBuffer xmlProds=new StringBuffer("");
		    String attrName;
			StringBuffer xml = new StringBuffer(
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			
			xml.append("<info>\n");
			xml.append("<code>0</code>\n ");
			xml.append("<items>\n");
			
			for( CatRegs reg : regs ){
				xml.append("<item>\n");
				setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						attrName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attrName == null || attrName.isEmpty())
							continue;
						if ("ID".equals(attrName)) {
							xml.append("<id>" + regVals.getValue() +  "</id>");
						}else if( "DESCRIP".equals(attrName) ){
							xml.append("<descrip>" + regVals.getValue() +  "</descrip>");
						}
						
						
					}				
				
				}
				xml.append("</item>\n");
				
			}
			xml.append("</items>\n");
			
		 

			xml.append("</info>\n");

			/*
			 * response.setContentType("text/xml");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.getWriter().write(xml.toString());
			 */

			LOG.info("xml generado :\n" + xml);
			response.setContentType("text/xml; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			try {
				// resp.getWriter().print( );
				//response.getOutputStream().print(xml.toString());
				
				response.getWriter().print(xml.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

		} finally {
			// templateDAO.
		}

	}
	
	protected void forwardSelectTemplate(HttpServletRequest request,
			HttpServletResponse response , TblFolletoHojas hoja  ){
		
		try{
		
		int category=hoja.getIdCategory();
		int user=hoja.getIdUserInvitado();
		
		List<TblTemplateUser> lstTemplates=serviceTemplates.getByUserAndCategory(user, category); 
		
		//String nextJSP =   "/faces/pages/folletos/selectTemplate.jsp";
		String nextJSP =   "/pages/folletos/selectTemplate.jsp";
		
		if(esCambioTemplate){
			nextJSP+="?cambio_temp=Y";
		}
		
		LOG.info(nextJSP);
		request.setAttribute(GlobalDefines.REQ_CURR_LST_TMPL_BY_USR , lstTemplates);
	//	RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
	//	dispatcher.forward(request,response);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

	protected void forwardHoja(HttpServletRequest request,
			HttpServletResponse response , TblFolletoHojas hoja  ){
		
		try{
				 		
		String nextJSP = "/pages/folletos/Hoja.jsp?hoja_id="+ hoja.getIdHoja().intValue() ;
		//String nextJSP =  "seven/pages/folletos/selectTemplate.jsp";
		LOG.info(nextJSP);
	//	RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(nextJSP);
		//response.sendRedirect(nextJSP);
	//	dispatcher.forward(request,response);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
	protected Map<String,String> getMapRegArt( String idArt ){
		Map<String,String> result=null;
	    Set<CatRegValues> setReg = null;
	    
	    try{
	    ArrayList<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
	    AttrSearch attSearch = new AttrSearch();
		attSearch.setAttrName("ID");
						
		List<CatRegs> regs = null;	   
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
	
    public void test(){
       	Map<String,Object> mapValues=new TreeMap<String,Object>();		 
        
       	
       	try{
       		
       		
        mapValues.put("ID", "30");
        mapValues.put("CODE", "ELECTRONICA"  );                          	    	    	    
      //  serviceDynamicCatalogs.insertReg("CAT_CATEGORY" , mapValues ); 	
       		
        mapValues=new  TreeMap<String,Object>();	
    	mapValues.put("ID", "100");
    	mapValues.put("ID_CATEGORIA", "30"  );   
    	mapValues.put("DESCRIP", "TV COLOR 50 PULGADAS");
    	mapValues.put("ID_MARCA", "J2");    	 
    	mapValues.put("COST", "5200");    	    	    	    	
    	serviceDynamicCatalogs.insertReg("CAT_ARTICULO" , mapValues ); 
    	
        mapValues=new  TreeMap<String,Object>();	
    	mapValues.put("ID", "101");
    	mapValues.put("ID_CATEGORIA", "30"  );   
    	mapValues.put("DESCRIP", "REPRODUCTOR DVD");
    	mapValues.put("ID_MARCA", "J2");    	 
    	mapValues.put("COST", "1200");    	    	    	    	
    	serviceDynamicCatalogs.insertReg("CAT_ARTICULO" , mapValues );   
    	
        mapValues=new  TreeMap<String,Object>();	
    	mapValues.put("ID", "102");
    	mapValues.put("ID_CATEGORIA", "30"  );   
    	mapValues.put("DESCRIP", "TV LCD 30 PULGADAS");
    	mapValues.put("ID_MARCA", "J2");    	 
    	mapValues.put("COST", "4500");    	    	    	    	
    	serviceDynamicCatalogs.insertReg("CAT_ARTICULO" , mapValues );      	
    	
    	
    	
    	
       	}catch(Exception e){
       		e.printStackTrace();
       	}

    }
    
    public void generarCopia(HttpServletRequest request , HttpServletResponse response){
    	String  msj_conf ="";
    	try{
    		//Obtengo el id de la hoja
    		int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
    		
    		TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
    		
    		TblFolletoSistemaVentaId fId = new TblFolletoSistemaVentaId(folletoHoja.getIdFolleto(), folletoHoja.getIdSistemaVenta());
    		
    		TblFolletoSistemaVenta folletoSistVenta = serviceFolleto.getFolletoSistemaVenta(fId);
    		
    		if('Y'==folletoSistVenta.getSistemaDefault()){
    			//obtener sistemas de venta para el folleto
    			List<TblFolletoSistemaVenta> lstSistVentaFolleto = serviceFolleto.getFolletoSistemaVentaByFolleto(folletoHoja.getIdFolleto());
    			
    			//Obtener lista de Folleto Hojas
    			List<TblFolletoHojas> lstFolletoHojas = serviceFolleto.getFolletoHojaByFolleto(folletoHoja.getIdFolleto());
    			
    			//Obtenemos las copias hoja normales y las default
    			List<TblFolletoHojas> lstCopiasHojaDefault = new ArrayList<TblFolletoHojas>();
    			List<TblFolletoHojas> lstCopiasHojaNoDefault = new ArrayList<TblFolletoHojas>();
    			
    			for (TblFolletoHojas copiasHoja : lstFolletoHojas) {
					if(copiasHoja.getIdSistemaVenta().intValue()==folletoHoja.getIdSistemaVenta().intValue()){
						lstCopiasHojaDefault.add(copiasHoja);
					}else{
						lstCopiasHojaNoDefault.add(copiasHoja);
					}
				}
    			
    			//Obtenemos las listas de los articulos para las copias default
    			Map<Integer, List<TblArticulosHoja>> disHojas = new HashMap<Integer, List<TblArticulosHoja>>();
    			
    			for (TblFolletoHojas copiaHojaDefault : lstCopiasHojaDefault) {
    				List<TblArticulosHoja> lstArtHoja = serviceFolleto.getArticulosHojaByHoja(copiaHojaDefault.getIdHoja());
					disHojas.put(copiaHojaDefault.getIdHoja(), lstArtHoja);
				}
    			
    			//Eliminar registros de las Hojas no contempladas en los sistemas default
    			for (TblFolletoHojas folletoHojaDel : lstCopiasHojaNoDefault) {
					serviceFolleto.deleteFolletoHoja(folletoHojaDel);
				}
    			
    			//Crear los registros para los diferentes sistemas de venta para cada hoja
    			for (TblFolletoHojas folletoHojaDef : lstCopiasHojaDefault) {
    				
					for (TblFolletoSistemaVenta sistVentaFolleto : lstSistVentaFolleto) {
						
						if(!sistVentaFolleto.getSistemaDefault().equals(folletoSistVenta.getSistemaDefault())){
							
							TblFolletoHojas folletoHojaNuevo = new TblFolletoHojas();
							folletoHojaNuevo.setIdCategory(folletoHojaDef.getIdCategory());
							folletoHojaNuevo.setIdFolleto(folletoHojaDef.getIdFolleto());
							//folletoHojaNuevo.setIdHoja(idHojaNueva++);
							folletoHojaNuevo.setIdScategory(folletoHojaDef.getIdScategory());
							folletoHojaNuevo.setIdSistemaVenta(sistVentaFolleto.getId().getIdSistemaVenta());
							folletoHojaNuevo.setIdTemplate(folletoHojaDef.getIdTemplate());
							folletoHojaNuevo.setIdUserInvitado(folletoHojaDef.getIdUserInvitado());
							folletoHojaNuevo.setNumHoja(folletoHojaDef.getNumHoja());
							
							//folletoHojaNuevo=serviceFolleto.saveFolletoHojas(folletoHojaNuevo);
							
							Set<TblArticulosHoja> stArticulosHoja = new HashSet<TblArticulosHoja>();
							
							for (TblArticulosHoja artHoja : disHojas.get(folletoHojaDef.getIdHoja())) {
								TblArticulosHoja artHojaNuevo = new TblArticulosHoja();
								artHojaNuevo.setAbSem12mes(artHoja.getAbSem12mes());
								artHojaNuevo.setAbSem15mes(artHoja.getAbSem15mes());
								artHojaNuevo.setAbSem18mes(artHoja.getAbSem18mes());
								artHojaNuevo.setAbSem24mes(artHoja.getAbSem24mes());
								artHojaNuevo.setAbSem6mes(artHoja.getAbSem6mes());
								artHojaNuevo.setAbSem9mes(artHoja.getAbSem9mes());
								artHojaNuevo.setIdArticulo(artHoja.getIdArticulo());
								artHojaNuevo.setIdFolleto(artHoja.getIdFolleto());
								artHojaNuevo.setPlazos(artHoja.getPlazos());
								artHojaNuevo.setPrecioPromocion(artHoja.getPrecioPromocion());
								artHojaNuevo.setPrincipal(artHoja.getPrincipal());
								artHojaNuevo.setPronosticoVenta(artHoja.getPronosticoVenta());
								artHojaNuevo.setSegmentId(artHoja.getSegmentId());
								artHojaNuevo.setSegmentName(artHoja.getSegmentName());
								artHojaNuevo.setSegmentParentId(artHoja.getSegmentParentId());
								artHojaNuevo.setSegmentName(artHoja.getSegmentName());
								artHojaNuevo.setTblFolleto(artHoja.getTblFolleto());
								artHojaNuevo.setTblImageArticulo(artHoja.getTblImageArticulo());
								artHojaNuevo.setHoja(folletoHojaNuevo);
								
								stArticulosHoja.add(artHojaNuevo);
								//serviceFolleto.saveArticulosHoja(artHojaNuevo);
							}
							
							folletoHojaNuevo.setArticulos(stArticulosHoja);
							serviceFolleto.saveFolletoHojas(folletoHojaNuevo);
						}
					}
				}
    			msj_conf="Se generaron las copias satisfactoriamente.";
    	    	request.getSession().setAttribute(GlobalDefines.SESS_CURR_MSJ_CONF, msj_conf);
    		}else{
    			LOG.info("El sistema de venta no es default");
    			msj_conf="El sistema de venta de la copia no es default.";
    			request.getSession().setAttribute(GlobalDefines.SESS_CURR_MSJ_CONF, msj_conf);
    		}
    		
    	}catch (Exception e){
    		LOG.error(e);
    		msj_conf=e.toString();
    		request.getSession().setAttribute(GlobalDefines.SESS_CURR_MSJ_CONF, msj_conf);
    		
    	}
    	
    	
    }
    
    public void eliminarCopia(HttpServletRequest request , HttpServletResponse response){
    	try{
    		int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
    		
    		TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
    		
    		TblFolletoSistemaVentaId fId = new TblFolletoSistemaVentaId(folletoHoja.getIdFolleto(), folletoHoja.getIdSistemaVenta());
    		TblFolletoSistemaVenta folletoSistVenta = serviceFolleto.getFolletoSistemaVenta(fId);
    		
    		if('Y' == folletoSistVenta.getSistemaDefault()){
    			LOG.error("La copia pertenece a un sistema de venta default, no se puede eleiminar");
    		}else{
    			List<TblFolletoHojas> folletoHojas=serviceFolleto.getFolletoHojaByFolleto(folletoHoja.getIdFolleto());
    			
    			for (TblFolletoHojas fHoja : folletoHojas) {
					if(fHoja.getIdSistemaVenta().intValue()==folletoHoja.getIdSistemaVenta().intValue()){
						serviceFolleto.deleteFolletoHoja(fHoja);
					}
				}
    			
    		}
    		
    	}catch(Exception e){
    		LOG.error(e);
    	}
    }
    
    public void addComent(HttpServletRequest request, HttpServletResponse response  ){
    	try{
    		int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
    		int segmentoId = Utilities.StringToInt( request.getParameter("segment_id"));
    		String comentario = request.getParameter("comentario");
    		
		    List<TblComentarioArticulo> lstComentario=(List<TblComentarioArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_COMENT );
		    
		    TblComentarioArticulo icom=new TblComentarioArticulo();
			icom.setComentario(comentario);
			icom.setFechaAlta(new Date());
			icom.setIdHoja(hojaId);
			icom.setSegmentId(segmentoId);
			
			lstComentario.add(icom);
			
    	}catch(Exception e){
    		LOG.error(e);
    	}
    }
    
    public void addArchivo(HttpServletRequest request, HttpServletResponse response  ){
    	try{
    		int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
    		int segmentoId = Utilities.StringToInt( request.getParameter("segment_id"));
    		String archivo = request.getParameter("archivo");
    		
		    List<TblArchivoArticulo> lstArchivos=(List<TblArchivoArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_ARCHIVO );
		    
		    TblArchivoArticulo att=new TblArchivoArticulo();
			att.setPathArchivo(archivo);
			att.setFechaAlta(new Date());
			att.setIdHoja(hojaId);
			att.setSegmentId(segmentoId);
			
			lstArchivos.add(att);
		    
    	}catch(Exception e){
    		LOG.error(e);
    	}
    }
    
    public void saveComentariosArchivos(HttpServletRequest request, HttpServletResponse response  ){
    	try{
		    List<TblArchivoArticulo> lstArchivos=(List<TblArchivoArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_ARCHIVO );
		    
		    for (TblArchivoArticulo archivoNuevo : lstArchivos) {
				serviceArquitectura.saveArchivoArticulo(archivoNuevo);
			}
		    
		    List<TblComentarioArticulo> lstComentario=(List<TblComentarioArticulo>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_COMENT );
		    
		    for (TblComentarioArticulo comentarioNuevo : lstComentario) {
				serviceArquitectura.saveComentarioArticulo(comentarioNuevo);
			}
		    
		    lstArchivos.clear();
		    lstComentario.clear();
		    
    	}catch(Exception e){
    		LOG.error(e);
    	}
    }
    
    public void ejecutarCambioTemplate(HttpServletRequest request, HttpServletResponse response ){
    	try{
    		int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
    		int templateId = Utilities.StringToInt( request.getParameter("template_id"));
    		
    		TblTemplate templateSel = serviceArquitectura.getTemplate(templateId);
    		List<TblTemplateSegments> segmentDisp = new ArrayList<TblTemplateSegments>();
    		for (TblTemplateSegments segment : templateSel.getTblTemplateSegments()) {
    			if( 'N' != segment.getTipo() ){
    				continue;
    			}
    			
    			segmentDisp.add(segment);
			}
    		 
    		List<TblArticulosHoja> lstArtHoja = serviceFolleto.getArticulosHojaByHoja(hojaId);
    		
    		TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
    		folletoHoja.setIdTemplate(templateId);
    		
    		//Set<TblArticulosHoja> stArticulosHoja = new HashSet<TblArticulosHoja>();
    		int s=-1;
    		String ind="-";
    		for (TblArticulosHoja artHoja : lstArtHoja) {
    			if( !ind.equals(artHoja.getSegmentName()) ){
    				ind=artHoja.getSegmentName();
    				s++;
    			}
    			
    			artHoja.setSegmentId(segmentDisp.get(s).getSegmentId());
    			artHoja.setSegmentName(segmentDisp.get(s).getSegName());
    			artHoja.setHoja(folletoHoja);
    			
    			//stArticulosHoja.add(artHoja);
    			serviceFolleto.saveArticulosHoja(artHoja);
			}
    		
    		//folletoHoja.setArticulos(stArticulosHoja);
    		folletoHoja = serviceFolleto.saveFolletoHojas(folletoHoja);
    		
    		Map<Integer,TblFolletoHojas> mapHojas=(Map<Integer,TblFolletoHojas>)request.getSession().getAttribute(GlobalDefines.SESS_CURR_HOJAS );
			if( mapHojas==null ){
				mapHojas=new TreeMap<Integer,TblFolletoHojas> ();
				request.getSession().setAttribute(GlobalDefines.SESS_CURR_HOJAS, mapHojas);
			}
			
			mapHojas.put( new Integer(hojaId) , null );
			genLoadHojaRsp(request, response);
    	}catch(Exception e){
    		LOG.error(e);
    	}
    }
    
 
    
    public void cambioTemplate(HttpServletRequest request, HttpServletResponse response ){
		try{
			int hojaId = Utilities.StringToInt( request.getParameter("hoja_id"));
			TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
			
			forwardSelectTemplate(request,response,folletoHoja);
		}catch(Exception e){
			LOG.error(e);
		}
	}
    
    public List<TblTemplateUser> getTemplateForChange(String hojaIdStr, HttpServletRequest request){
    	List<TblTemplateUser> retVal=new ArrayList<>();
    	try {
    		
    		/**int hojaId = Utilities.StringToInt( hojaIdStr);
    		TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
        	
        	int category=folletoHoja.getIdCategory();
    		int user=folletoHoja.getIdUserInvitado();*/
    		
    		retVal = (List<TblTemplateUser>)request.getAttribute(GlobalDefines.REQ_CURR_LST_TMPL_BY_USR);
    		
    		/**retVal = serviceTemplates.getByUserAndCategory(user, category); */
		} catch (Exception e) {
			LOG.error(e);
		}
    	return retVal;
    }
    
    
    public void drawThumbSegment(Graphics2D  graphics , Rectangle rectParent, TblTemplateSegments seg , Map<Integer, TblTemplateSegments> segmentsById , Map<Integer,List<TblTemplateSegments>> segmentsByParent ){
    	
    	if(seg==null){
    		return;
    	}
    	List<TblTemplateSegments> childs=segmentsByParent.get(seg.getSegmentId());
    	
    	if( childs==null || childs.size()<=0 ){
    		if(seg.getSegName()!=null && seg.getSegName().isEmpty()==false ){
    		   graphics.drawString( seg.getSegName() , (int)( rectParent.getX() + rectParent.getWidth()/2 ) , (int)(rectParent.getY()+ rectParent.getHeight()/2 ) );
    		}
    		return;
    	}
    	
    	int x=0;
    	int y=0;
    	int width=0;
    	int height=0;
    	if( seg.getTipo()=='R' ){
    		width=(int)rectParent.getWidth();
    	  	height=(int)(rectParent.getHeight())/childs.size();
    	}else if(seg.getTipo()=='C' ){
    		width=(int)(rectParent.getWidth())/childs.size();
    		height=(int)rectParent.getHeight();
    	}
    	
    	Rectangle rectChild=null;
    	int offset=0;
    	for( TblTemplateSegments segChild : childs ){
    		if( seg.getTipo()=='R' ){
    			
    		    rectChild = new Rectangle( (int)rectParent.getX()  , (int)rectParent.getY() + offset  , width  , height );
    		    offset+=height;
        	}else{
  			    rectChild = new Rectangle( (int)rectParent.getX() + offset  , (int)rectParent.getY()  , width  , height );
  			    offset+=width;
        	}
    		graphics.draw(rectChild);
    		drawThumbSegment(graphics , rectChild , segChild , segmentsById , segmentsByParent  );
    		
    	}
    	
    	
    	
    	
    	
    	
    }
    
    public void genTemplateThumb( TblTemplate template , HttpServletRequest req ){
    	   if( template==null ) {
    		   return;
    	   }
    	   
    	   int width = 142;
           int height = 142;
           int imageType = BufferedImage.TYPE_4BYTE_ABGR;
           BufferedImage imageBuffer =null;    	   
           List<TblTemplateSegments> listSegments= null;

    	   
    	   
    	   
    	   try{
    		   
               imageBuffer = new BufferedImage(width,height,imageType);
               Graphics2D graphics = imageBuffer.createGraphics();
               graphics.setBackground(Color.gray);                             
               Rectangle rect = new Rectangle(0, 0, 140, 140);
               graphics.setColor(Color.black);
              // graphics.drawRect(rect);
               graphics.draw(rect);

        	   listSegments= template.getTblTemplateSegments();
        	   if( listSegments==null || listSegments.size()<=0 ){
        		   return;
        	   }    		   
    		   TblTemplateSegments segRoot=template.getMapSegmentsById().get(template.getRootSegmentId());

               drawThumbSegment( graphics , rect , segRoot , template.getMapSegmentsById() , template.getMapSegmentsByParent() );
                                             
               graphics.setStroke(new BasicStroke(3));
               
               rect = new Rectangle(0, 0, 142, 142);
               graphics.draw(rect);
               
               String dir=req.getSession().getServletContext().getRealPath("/");
               System.out.println(dir);
               
               
        	   File file = new File(dir+ "/"+  GlobalDefines.RUTA_THUMBS + "/thumb_temp_"+ template.getTemplateId()+".png"  );
               ImageIO.write(imageBuffer, "png", file);   
    		    
    		   
    	   }catch(Exception e){
    		   e.printStackTrace();
    		   LOG.error(e); 
    	   }
    	   
    	
    }
    
}



