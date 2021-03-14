package com.adinfi.seven.presentation.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**import com.homedepot.tab.articles.dao.ArticlesDAO;*/
import com.adinfi.seven.business.services.BaseServlet;
import com.adinfi.seven.business.services.ServiceArquitectura;
/**import com.homedepot.tab.hojas.vo.HojasVO;*/
import com.adinfi.seven.persistence.daos.TemplateDAO;
import com.adinfi.seven.business.domain.TemplateVO;
import com.adinfi.seven.business.domain.TemplateSegmentVO;
import com.adinfi.seven.presentation.views.util.Utilities;

public class ActionTemplates extends BaseServlet{
	
	private static final long serialVersionUID = -2134810667953911584L;
	Logger LOG = Logger.getLogger(ActionTemplates.class);
	public void doAction(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException {

	    String command = (request.getParameter("command")!=null)?request.getParameter("command"):"";
		StringBuffer codeHTML = new StringBuffer("");	    
	   
		LOG.info("Comando-->"+command);
	     
		int segmentId=0;
		short numElements=0;
		String type=null;	  
		short width=0;
		short height=0;
		short segParent=0;
		int templateChild = 0;
	    TemplateVO templateVO=new TemplateVO();
	    String eqLng="";
	    
	    TemplateDAO tempalteDao = new TemplateDAO();
	    
	    String templateName=null;
	    String layoutType=null;
	    short templateWidth=0;
	    short templateHeight=0;
	    int templateId=0;
	    String templateHead=null;
	    String templateFoot=null;
	     
	    
		
	     if (command.equals("save"))
		{
			
	    	int numSegments=Utilities.StringToInt( request.getParameter("num_segments") );
	    	templateName=Utilities.putOffNulls(request.getParameter("template_name"));
	    	templateWidth=Utilities.StringToShort( request.getParameter("template_width") );
	    	templateHeight=Utilities.StringToShort(request.getParameter("template_height"));
	    	layoutType=Utilities.putOffNulls(request.getParameter("layout_type"));
	    	templateId=Utilities.StringToInt( request.getParameter("template_id") );
	    	templateHead=Utilities.putOffNulls(request.getParameter("template_head"));
	    	templateFoot=Utilities.putOffNulls(request.getParameter("template_foot"));
	    	
	    	TemplateSegmentVO segment=null;
	    	
	    	templateVO.setTemplateName(templateName);
	    	templateVO.setWidht(templateWidth);
	    	templateVO.setHeight(templateHeight);
	    	templateVO.setLayoutType(layoutType);
	    	Map<Integer, TemplateSegmentVO> segmentsByIdx=new TreeMap<Integer,TemplateSegmentVO>();
	    	List<TemplateSegmentVO> lstSegments=new ArrayList<TemplateSegmentVO>();
	    	
	    	templateVO.setMapSegmentsByIdx(segmentsByIdx);
	    	templateVO.setSegments(lstSegments);
	    	templateVO.setTemplateHead(templateHead);
	    	templateVO.setTemplateFoot(templateFoot);
	    		    	  			 						
			for(int i=0;i<numSegments;i++)
			{
				
				segmentId=Utilities.StringToInt( request.getParameter("segment_id_"+i) );
				numElements=Utilities.StringToShort( request.getParameter("segment_count_"+i)  );
				type=Utilities.putOffNulls( request.getParameter("segment_type_"+i) );
				width=Utilities.StringToShort( request.getParameter("segment_width_"+i));
				height=Utilities.StringToShort( request.getParameter("segment_height_"+i));
				segParent=Utilities.StringToShort( request.getParameter("segment_parent_"+i));
				templateChild = Utilities.StringToInt( request.getParameter("segment_child_"+i));
				
				eqLng=Utilities.putOffNulls( request.getParameter("segment_eq_lng_"+i));				
				
				segment=new TemplateSegmentVO();
				segment.setNumElements(numElements);
				segment.setWidth(width);
				segment.setHeight(height);
				segment.setParentSegment(segParent);
				segment.setType(type);
				segment.setIdx(i);
				segment.setChildsEqual("Y".equals(eqLng));
				segment.setTemplateChild(templateChild);
				
				lstSegments.add(segment);
				segmentsByIdx.put(i, segment );
				
				LOG.info("i:"+ i);
				LOG.info("SegmentId :"+ segmentId );
				LOG.info("numElements:"+ numElements);
				LOG.info("type:"+type);
				LOG.info("width:"+width);
				LOG.info("height:"+ height );	
				LOG.info("parent:"+ segParent );
				
			}
			
			LOG.info("Numero totoal de segmentos :"+ numSegments);
			
			templateVO.setTemplateId(templateId);
			 
			if(tempalteDao.upsertTemplate(templateVO))
			{
				LOG.info("Se guardo satisfactoriamente el Template");
			}else{
				LOG.info("Ocurrio un error mientras se guardaba el Template");
			}
	
			senderResponse(response, codeHTML);
		
		}
	}
	
	public TemplateVO getTemplate(int templateId){
		
		TemplateVO templateVO=null;
				 				
		LOG.info("Se invoca getTemplate()");
	
		try {
			WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
			ServiceArquitectura serArq = (ServiceArquitectura) applicationContext.getBean("serviceArquitectura");
			//arq = (ArquitecturaMB) context.getApplication().evaluateExpressionGet(context, "#{ArquitecturaMB}", ArquitecturaMB.class);
			
			//templateVO = arq.getTemplate(templateId);
			
		}catch (Exception e) {
			LOG.error("ERROR: en ServletTemplate.getTemplate(int). JAVA_ERROR: "+e.getMessage()); 
		}
		
		return templateVO;
	}
	
	public void 	genCMBTemplates(HttpServletRequest request , HttpServletResponse response){


		 try{
		   
		   TemplateDAO templateDAO=new TemplateDAO();
		   List<TemplateVO> lstTemplates= templateDAO.getTemplates();	
		   StringBuffer html=new StringBuffer("");
		   if( lstTemplates!=null && lstTemplates.size()>0 ){
			   for( TemplateVO template : lstTemplates ){
				   html.append("<option value='"+ template.getTemplateId() +"'>"+ template.getTemplateName() +"</option>"  );				   				   
			   }
		   }
		   
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(html.toString());	
			
		 }catch(Exception e){
			 LOG.error(e);
		 }
		   
		   
		   
		
	}
	
	/**
	public void genGetTemplateRsp(HttpServletRequest request,HttpServletResponse response){
		   int templateId=Utilities.StringToInt(request.getParameter("template_id"));
		   if( templateId<=0  ) return;
		   TemplateDAO templateDAO=new TemplateDAO();
		   TemplateVO templateVO=new TemplateVO();
		   int hoja=Utilities.StringToInt(request.getParameter("hoja"));
		   int evento=Utilities.StringToInt(request.getParameter("evento"));
		   int version=Utilities.StringToInt(request.getParameter("version"));
		   int copia= Utilities.StringToInt(request.getParameter("copia"));
		   
		   ArticlesDAO artsDAO=null;
		   ArrayList arts=null;
		   HojasVO hojasVO=null;
		   
		   try{
			   templateVO=templateDAO.getTemplate(templateId);
			   
			   if( templateVO==null ) return;
			   
			   // StringBuffer xmlProds=new StringBuffer("");
				StringBuffer xml=new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				xml.append("<info>\n");
				xml.append("<code>0</code>\n ");
				xml.append(genXMLSegment( templateVO.getRootSegmentId() , templateVO.getMapSegmentsByParent() , templateVO.getMapSegmentsById() ));			 
			
				
			
				if(hoja>0){
					artsDAO=new ArticlesDAO();
					arts=artsDAO.getGrid(evento, hoja, version, copia);
					if(arts!=null){
						xml.append("<info_prods>\n");
						for( int i=0;i<arts.size();i++ ){
							hojasVO=(HojasVO)arts.get(i);
							if(hojasVO==null) continue;
							xml.append("<producto>\n");
							
							xml.append("<item>"+hojasVO.getItem() +"</item>\n");
							xml.append("<descripcion>"+hojasVO.getDescripcion()+"</descripcion>\n");
							xml.append("<Precio>"+hojasVO.getPrecio()+"</Precio>\n");
							xml.append("<hoja>"+hojasVO.getHoja()+"</hoja>\n");
							xml.append("<version>"+hojasVO.getVersion()+"</version>\n");
							xml.append("<id_imagen>"+hojasVO.getIdImg()+"</id_imagen>\n");
							xml.append("<marca>"+hojasVO.getMarca()+"</marca>\n");
							xml.append("<nombre>"+hojasVO.getNombre()+"</nombre>\n");
							xml.append("<path><![CDATA["+hojasVO.getPath()+"]]></path>\n");
							xml.append("<copia>"+hojasVO.getCopia()+"</copia>\n");
							xml.append("<comentarios>"+hojasVO.getComentario()+"</comentarios>\n");
							xml.append("<tipo_imagen>"+hojasVO.getTipoImagen()+"</tipo_imagen>\n");
							xml.append("<tipo_sku>"+ hojasVO.getTipoSku()+"</tipo_sku>\n");
							xml.append("<departamento>"+hojasVO.getDepartamento()+"</departamento>\n");
							xml.append("<template_id>"+hojasVO.getIdTemplate()+"</template_id>\n");							
							xml.append("<segment_id>"+ hojasVO.getIdSegment() +"</segment_id>\n");							
							xml.append("<principal>"+ ( hojasVO.isPrincipal() ? "Y": "" ) +"</principal>\n");							
																					
							xml.append("</producto>\n");
						}
						xml.append("</info_prods>\n");						
																														
					}
					
					
				}
				
				xml.append("</info>\n");
				
				/*
				response.setContentType("text/xml");
			    response.setHeader("Cache-Control", "no-cache");
			    response.getWriter().write(xml.toString()); *\
				
				
				 System.out.println("xml generado :\n"+ xml );
			     response.setContentType("text/xml");		    
			     response.setCharacterEncoding( "UTF-8" );
			     try{
			        //resp.getWriter().print(  );
			        response.getOutputStream().print( xml.toString() );
			     }catch(Exception e){
			    	 e.printStackTrace();
			     }  
			   
				
				
							   			   			   
		   }catch(Exception e){
			   
		   }finally{
			   //templateDAO.
		   }
		
	}*/
	
	@SuppressWarnings("all")
	public StringBuffer genXMLSegment( int segId ,     Map< Integer ,  List<TemplateSegmentVO>> segmentsByParent , Map<Integer , TemplateSegmentVO> segmentsById  ){
		   StringBuffer xml=new StringBuffer("");

		   List<TemplateSegmentVO> segmentsChilds = new ArrayList<TemplateSegmentVO>();
		   if(segmentsByParent != null)
			   segmentsChilds=segmentsByParent.get(segId);
		   
		   TemplateSegmentVO segment=segmentsById.get(segId);
		   int parentId=0;
		   
		   if(segment!=null)
			   parentId=segment.getParentSegment();
		   
		   if( segmentsByParent==null || segmentsByParent.size()<=0 || segment==null ) 
			   return xml;
		   
		   try{ 
				xml.append("<segment_info_"+parentId+">\n" );
				xml.append("  <segment_id_"+parentId+">"+segId+"</segment_id_"+parentId+"> \n ");
				xml.append("  <segment_type_"+parentId+">"+segment.getType()+"</segment_type_"+parentId+"> \n ");
				xml.append("  <segment_width_"+parentId+">"+segment.getWidth()+"</segment_width_"+parentId+"> \n ");
				xml.append("  <segment_height_"+parentId+">"+segment.getHeight() +"</segment_height_"+parentId+">\n");
				if( segmentsChilds!=null && segmentsChilds.size()>0 ){
					xml.append("  <segment_childs_"+parentId+">\n");
					for( TemplateSegmentVO segTmp : segmentsChilds ){
						xml.append( genXMLSegment( segTmp.getSegmentId() , segmentsByParent , segmentsById   )  );					
					}								
					xml.append("  </segment_childs_"+parentId+"> ");
				}
				 
				xml.append("  <segment_name_"+parentId+">"+ (segment.getSegName()==null ? "" : segment.getSegName() )   +"</segment_name_"+parentId+"> \n ");
				xml.append("</segment_info_"+parentId+">\n" );
   
		   }catch(Exception e){
			   LOG.error(e);
		   }
		   return xml;
		
	}

	
	public void senderResponse(HttpServletResponse response, StringBuffer codeHTML) throws IOException
	{
		
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write(codeHTML.toString());
	}
	
	

}

