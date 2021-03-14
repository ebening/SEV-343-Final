package com.adinfi.servlets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateSegments;
import com.adinfi.seven.business.domain.TemplateSegmentVO;
import com.adinfi.seven.business.domain.TemplateVO;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.presentation.views.util.Utilities;

/**
 * Servlet implementation class UploadServlet
 */
public class ServletTemplate implements HttpRequestHandler {
	
	Logger LOG = Logger.getLogger(ServletTemplate.class);
	ServiceArquitectura serviceArquitectura;
	private String[] segmentNames={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};

	@Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command=request.getParameter("command");
       	if( command==null || command.isEmpty() ){
       		return;
       	}
        
    	if( "save".equals(command) ){
    		save(request,response);
    	}
	}
	
	public void save(HttpServletRequest request , HttpServletResponse response ){
		int segmentId=0;
		short numElements=0;
		String type=null;	  
		short width=0;
		short height=0;
		short segParent=0;
		int templateChild = 0;
	    TemplateVO templateVO=new TemplateVO();
	    String eqLng="";
	    
	    String templateName=null;
	    String layoutType=null;
	    short templateWidth=0;
	    short templateHeight=0;
	    int templateId=0;
	    String templateHead=null;
	    String templateFoot=null;
	    
			
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
		TblTemplate modTemplate = upsertTemplate(templateVO, request, response);
		
		if(modTemplate!=null)
		{
			LOG.info("Se guardo satisfactoriamente el Template");
			//TblTemplate tmpTbl;
			templateVO.setTemplateId(modTemplate.getTemplateId());
			try{
			//tmpTbl=this.serviceArquitectura.getTemplate(modTemplate.getTemplateId());
			armaSegments(templateVO);
						 			 
			//TemplateVO tmpTmpl=this.getTemplate(modTemplate.getTemplateId());
			//this.getTemplate(templateId)
			templateVO.setTemplateId(modTemplate.getTemplateId());
			genTemplateThumb(templateVO,request);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			LOG.info("Ocurrio un error mientras se guardaba el Template");
		}
		
	}
	
	public TblTemplate upsertTemplate(TemplateVO templateVO, HttpServletRequest request , HttpServletResponse response ){
		boolean retVal=true;
		TblTemplate modTemplate = null;
		try{
			//TblTemplate modTemplate = new TblTemplate();
			 
			
			if(templateVO.getTemplateId() > 0){
				modTemplate=serviceArquitectura.getTemplate(templateVO.getTemplateId());
				if(modTemplate==null){
					return null;
				}
				List<TblTemplateSegments> segments = modTemplate.getTblTemplateSegments();
				modTemplate=null;
				for (TblTemplateSegments segment : segments) {
					deleteTemplateSegment(segment.getSegmentId());
				}
				modTemplate=serviceArquitectura.getTemplate(templateVO.getTemplateId());
			}else{
				modTemplate=new TblTemplate();
			}
			
			modTemplate.setTemplateName(templateVO.getTemplateName());
			modTemplate.setWidth(templateVO.getWidht());
			modTemplate.setHeight(templateVO.getHeight());
			modTemplate.setStatus(templateVO.getStatus());
			modTemplate.setFechaCreacion(new Date());
			modTemplate.setTipoLayout(templateVO.getLayoutType());
			if( modTemplate.getTblTemplateSegments()!=null ){
			   modTemplate.getTblTemplateSegments().clear();
			}else{
			   modTemplate.setTblTemplateSegments(new ArrayList<TblTemplateSegments>());
			}	
			serviceArquitectura.saveTemplate(modTemplate);
			
			int ind = 0;
			int respParentRoot = 0;
			int parentValue = 0;
			for( TemplateSegmentVO segVO : templateVO.getSegments()    ){
				TblTemplateSegments segment = new TblTemplateSegments();
				
				if(segVO.getSegmentId() > 0){
					segment.setSegmentId(segVO.getSegmentId());
				}
				
				if (segVO.getParentSegment() == 0){
					segment.setSegmentParentId( respParentRoot );
				}else{
					segment.setSegmentParentId( parentValue );
				}
				
				
				segment.setWidth( segVO.getWidth() );
				segment.setHeight( Integer.valueOf( segVO.getHeight() ) );
				
				String stipo = segVO.getType().trim();
				char[] ctipo = stipo.toCharArray();
				if(ctipo != null && ctipo.length > 0){
					segment.setTipo( ctipo[0] );
				}else{
					segment.setTipo(null);
				}
				
				segment.setIsPercentage(null);
				
				if( segVO.isChildsEqual() ){
					segment.setElemEq('Y');
				}else{
					segment.setElemEq('N');
				}
				
				if( "N".equals(segVO.getType()) ){
					segment.setSegName(segmentNames[ind]);
					ind++;
				}
				
				segment.setTemplateChildId(segVO.getTemplateChild());
				
				segment.setTblTemplate(modTemplate);
				
				serviceArquitectura.saveTemplateSegment(segment);
				
				if(segment.getSegmentId() <= 0){
					LOG.error("Ocurrio un error al guardar el TblTemplateSegment");
					retVal = false;
					break;
				}else{
					segVO.setSegmentId(segment.getSegmentId());
				}
				
				if (segVO.getParentSegment() == 0){
					parentValue = segment.getSegmentId();
				}else if (segVO.getParentSegment() < 0){
					respParentRoot = segment.getSegmentId();
				}
				segVO.setParentSegment(segment.getSegmentParentId() );
			}
			
		}catch (Exception e){
			LOG.error(e);
			retVal = false;
			modTemplate=null;
		}
		
		return modTemplate;
	}
	
	private boolean deleteTemplateSegment(int segmentId){
		boolean retVal=true;
		try{
			TblTemplateSegments seg_tem=serviceArquitectura.getTemplateSegment(segmentId);
			retVal = serviceArquitectura.deleteTemplateSegment(seg_tem);
		}catch (Exception e){
			LOG.error(e);
			retVal = false;
		}
		
		return retVal;
	}
	
	
	
	public TemplateVO getTemplate(int templateId){
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		setServiceArquitectura( (ServiceArquitectura) applicationContext.getBean("serviceArquitectura") );
		
		TemplateVO templateVO=null;
		TemplateSegmentVO segmentVO=null;	
				 				
		LOG.info("Se invoca getTemplate()");
	
		try {
			List<TemplateSegmentVO> lstSegments=new ArrayList<TemplateSegmentVO>();
			Map<Integer , List<TemplateSegmentVO> > segmentsByParent=new TreeMap< Integer , List<TemplateSegmentVO> >();
			Map<Integer , TemplateSegmentVO> segmentsById=new TreeMap<Integer,TemplateSegmentVO>();
			List<TemplateSegmentVO> lstSegmentsLeave=null;
			
			TblTemplate modTemplate = serviceArquitectura.getTemplate(templateId);
									
			List<TemplateSegmentVO> arrTmp=null;
			for (TblTemplateSegments modSegment : modTemplate.getTblTemplateSegments()){
				if( templateVO==null ){
					templateVO=new TemplateVO();
					templateVO.setTemplateId( modTemplate.getTemplateId() );
					templateVO.setTemplateName( modTemplate.getTemplateName() );
					templateVO.setWidht( Short.valueOf(String.valueOf(modTemplate.getWidth())) );
					templateVO.setHeight( Short.valueOf(String.valueOf(modTemplate.getHeight())) );
					templateVO.setLayoutType( modTemplate.getTipoLayout() );
					templateVO.setTemplateHead(modTemplate.getPathHead() == null ? "" : modTemplate.getPathHead());
					templateVO.setTemplateFoot(modTemplate.getPathFoot() == null ? "" : modTemplate.getPathFoot());
					
					templateVO.setMapSegmentsById(segmentsById);
					templateVO.setMapSegmentsByParent(segmentsByParent);
					templateVO.setSegments(lstSegments);
				}
				segmentVO=new TemplateSegmentVO();
				segmentVO.setSegmentId( modSegment.getSegmentId() );
				segmentVO.setSegName( modSegment.getSegName() );
				segmentVO.setParentSegment( modSegment.getSegmentParentId() );
				segmentVO.setWidth( Short.valueOf(String.valueOf(modSegment.getWidth())) );
				segmentVO.setHeight( Short.valueOf(String.valueOf(modSegment.getHeight())) );
				segmentVO.setType( modSegment.getTipo().toString() );
				
				segmentVO.setPercentage( modSegment.getIsPercentage()!=null && modSegment.getIsPercentage().equals('Y') ? true : false   );
				segmentVO.setChildsEqual( modSegment.getElemEq()!=null && modSegment.getElemEq().equals('Y') ? true : false );
				
				segmentVO.setTemplateChild( modSegment.getTemplateChildId() );
				lstSegments.add(segmentVO);
				
				if( segmentVO.getParentSegment()==0 ){
					templateVO.setRootSegmentId(segmentVO.getSegmentId());
				}
				
				arrTmp=segmentsByParent.get(segmentVO.getParentSegment());
				if( arrTmp==null ){
					arrTmp=new ArrayList<TemplateSegmentVO>();
					segmentsByParent.put(segmentVO.getParentSegment(), arrTmp);
				}
				arrTmp.add(segmentVO);
				
				segmentsById.put(segmentVO.getSegmentId() , segmentVO );
				
				if( "N".equals( segmentVO.getType()) ){
					if(lstSegmentsLeave==null ){
				      	lstSegmentsLeave=new ArrayList<TemplateSegmentVO>();
				      	templateVO.setLstSegmentsLeave(lstSegmentsLeave);					
					}
					lstSegmentsLeave.add(segmentVO);										
				}

				LOG.info("TemplateId :"+ templateId );
			}
			templateVO.setSegments(lstSegments);
		}catch (Exception e) {
			LOG.error("ERROR: en ServletTemplate.getTemplate(int)(. JAVA_ERROR: "+e.getMessage()); 
		}
		
		return templateVO;
	}
	
	protected void armaSegments(TemplateVO templateVO){
		
		//TemplateSegmentVO segmentVO=null;	
		if( templateVO.getSegments()==null || templateVO.getSegments().size()<=0 ){
			return;
		}
			
		LOG.info("Se invoca getTemplate()");
	
		try {
			//List<TemplateSegmentVO> lstSegments=new ArrayList<TemplateSegmentVO>();
			Map<Integer , List<TemplateSegmentVO> > segmentsByParent=new TreeMap< Integer , List<TemplateSegmentVO> >();
			Map<Integer , TemplateSegmentVO> segmentsById=new TreeMap<Integer,TemplateSegmentVO>();
			List<TemplateSegmentVO> lstSegmentsLeave=null;
			
			 
			templateVO.setMapSegmentsById(segmentsById);
			templateVO.setMapSegmentsByParent(segmentsByParent);			
									
			List<TemplateSegmentVO> arrTmp=null;
			for (TemplateSegmentVO segmentVO : templateVO.getSegments() ){
				 
								
				 				
				if( segmentVO.getParentSegment()==0 ){
					templateVO.setRootSegmentId(segmentVO.getSegmentId());
				}
				
				arrTmp=segmentsByParent.get(segmentVO.getParentSegment());
				if( arrTmp==null ){
					arrTmp=new ArrayList<TemplateSegmentVO>();
					segmentsByParent.put(segmentVO.getParentSegment(), arrTmp);
				}
				arrTmp.add(segmentVO);
				
				segmentsById.put(segmentVO.getSegmentId() , segmentVO );
				
				if( "N".equals( segmentVO.getType()) ){
					if(lstSegmentsLeave==null ){
				      	lstSegmentsLeave=new ArrayList<TemplateSegmentVO>();
				      	templateVO.setLstSegmentsLeave(lstSegmentsLeave);					
					}
					lstSegmentsLeave.add(segmentVO);										
				}

				 
			}
			//templateVO.setSegments(lstSegments);
		}catch (Exception e) {
			LOG.error("ERROR: en ServletTemplate.getTemplate(int)(. JAVA_ERROR: "+e.getMessage()); 
		}
		
		
		
	}

	public ServiceArquitectura getServiceArquitectura() {
		return serviceArquitectura;
	}

	public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
		this.serviceArquitectura = serviceArquitectura;
	}
	
	
	
    public void genTemplateThumb( TemplateVO template , HttpServletRequest req ){
 	   if( template==null ) {
 		   return;
 	   }
 	   
 	   int width = 142;
        int height = 142;
        int imageType = BufferedImage.TYPE_4BYTE_ABGR;
        BufferedImage imageBuffer =null;    	   
        List<TemplateSegmentVO> listSegments= null;

 	   try{
 		   
            imageBuffer = new BufferedImage(width,height,imageType);
            Graphics2D graphics = imageBuffer.createGraphics();
            graphics.setBackground(Color.gray);                             
            Rectangle rect = new Rectangle(0, 0, 140, 140);
            graphics.setColor(Color.black);
           // graphics.drawRect(rect);
            graphics.draw(rect);

     	   //listSegments= template.getTblTemplateSegments();
            listSegments =template.getSegments();
     	   if( listSegments==null || listSegments.size()<=0 ){
     		   return;
     	   }    		   
     	   
     	   //template.getMapSegmentsById().get(template.getRootSegmentId() )
     	   
 		   TemplateSegmentVO segRoot=template.getMapSegmentsById().get(template.getRootSegmentId() );

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

	
	
	   public void drawThumbSegment(Graphics2D  graphics , Rectangle rectParent, TemplateSegmentVO seg , Map<Integer, TemplateSegmentVO> segmentsById , Map<Integer,List<TemplateSegmentVO>> segmentsByParent ){
	    	
	    	if(seg==null){
	    		return;
	    	}
	    	List<TemplateSegmentVO> childs=segmentsByParent.get(seg.getSegmentId());
	    	
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
	    	if( seg.getType().equals("R")  ){
	    		width=(int)rectParent.getWidth();
	    	  	height=(int)(rectParent.getHeight())/childs.size();
	    	}else if(seg.getType().equals("C") ){
	    		width=(int)(rectParent.getWidth())/childs.size();
	    		height=(int)rectParent.getHeight();
	    	}
	    	
	    	Rectangle rectChild=null;
	    	int offset=0;
	    	for( TemplateSegmentVO segChild : childs ){
	    		if( seg.getType().equals("R") ){
	    			
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
    
	
	
	
	
	

}
