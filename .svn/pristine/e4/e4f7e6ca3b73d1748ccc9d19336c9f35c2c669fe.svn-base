package com.adinfi.seven.persistence.daos;

 
/**
* Creado en Agosto 2009
*	Eliud Carrillo Lucio
*/
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.TemplateSegmentVO;
import com.adinfi.seven.business.domain.TemplateVO;
import com.adinfi.seven.presentation.views.util.AdManagerConstants;

public class TemplateDAO {
	
	Logger LOG = Logger.getLogger(TemplateDAO.class);
	Connection connectionProgress;
	
	String esquema = AdManagerConstants.ADM_ESQUEMA_BD;
	String esquema_progress = AdManagerConstants.ADM_PROGRESS_ESQUEMA_BD;
	String[] segmentNames={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};
	private final int UNO = 1;
	private final int DOS = 2;
	private final int TRES = 3;
	private final int CUATRO = 4;
	private final int CINCO = 5;
	private final int SEIS = 6;
	private final int SIETE = 7;
	private final int OCHO = 8;
	
	public TemplateDAO(){}
	
	/**
	 * Guarda un template en la base de datos (imagenes_logos)
	 * @param logosVO
	 * @return true si grabo, false si no grabo
	 */
	@SuppressWarnings("all")
	public boolean upsertTemplate(TemplateVO templateVO){
		
		Connection connectionDB2 = null;
				
		PreparedStatement stInsTemplate=null;
		PreparedStatement pstInsSegment=null;	
		
		PreparedStatement stUpdTemplate=null;		
		String sqlUpdTemplate=null;
		
		PreparedStatement stDelSegments=null;		 
		String sqlDelSegments=null;
						
		String sqlInsTemplate=null;
		String sqlInsSegment=null;
		 				
		int templateId=0;
		int segmentId=0;
		boolean result = false;
		int n=0;
		Map<Integer, TemplateSegmentVO > segmentsByIdx=null;
	    int currSegName=0; 	
		
	    LOG.info("Se invoca TemplatesDAO:saveTemplate():");

		templateId=templateVO.getTemplateId();
		String segName=null;
	
		try {
			connectionDB2 = Persistor.getConnectionDB2();
			try{
				if( templateVO.getTemplateId()<=0 ){
					templateVO.setTemplateId(templateId);
					sqlInsTemplate=" INSERT INTO FAM161DEV.Tbl_Template ( TEMPLATE_NAME , WIDTH , HEIGHT , STATUS , FECHA_CREACION , TIPO_LAYOUT, PATH_HEAD, PATH_FOOT )  VALUES ( ? , ? , ? , NULL , CURRENT DATE , ? , ? , ? ) " ;
					
					stInsTemplate= connectionDB2.prepareStatement(sqlInsTemplate, Statement.RETURN_GENERATED_KEYS);
					stInsTemplate.setString(UNO, templateVO.getTemplateName());
					stInsTemplate.setShort(DOS, templateVO.getWidht());
					stInsTemplate.setShort(TRES, templateVO.getHeight());
					stInsTemplate.setString(CUATRO, templateVO.getLayoutType());
					stInsTemplate.setString(CINCO, templateVO.getTemplateHead());
					stInsTemplate.setString(SEIS, templateVO.getTemplateFoot());
					
					n=stInsTemplate.executeUpdate();
					
					if( n>0){
						ResultSet rs = stInsTemplate.getGeneratedKeys();
						try{
							rs.next();
					    	templateId = rs.getInt(1);
						}catch(SQLException ex){
							LOG.error(ex);
						}finally{
							if(rs!=null){
								rs.close();
								rs=null;
							}
						}
					    LOG.info("Se inserto un template con id :"+ templateId );
					}
				
				}else{
					sqlUpdTemplate=" UPDATE FAM161DEV.Tbl_Template SET TEMPLATE_NAME = ? , WIDTH = ? , HEIGHT = ? , FECHA_CREACION = CURRENT DATE , TIPO_LAYOUT= ? , PATH_HEAD= ? , PATH_FOOT= ? WHERE TEMPLATE_ID = ? ";  
					
					stUpdTemplate= connectionDB2.prepareStatement(sqlUpdTemplate);
					stUpdTemplate.setString(UNO, templateVO.getTemplateName());
					stUpdTemplate.setShort(DOS, templateVO.getWidht());
					stUpdTemplate.setShort(TRES, templateVO.getHeight());
					stUpdTemplate.setString(CUATRO, templateVO.getLayoutType());
					stUpdTemplate.setString(CINCO, templateVO.getTemplateHead());
					stUpdTemplate.setString(SEIS, templateVO.getTemplateFoot());
					stUpdTemplate.setInt(SIETE, templateId);
					
					n=stUpdTemplate.executeUpdate();
					
					if(n>0){
						LOG.info("Se actualizo el template con id :"+ templateId );
					}
		
					sqlDelSegments=" DELETE FROM  FAM161DEV.Tbl_Template_Segments WHERE TEMPLATE_ID = ? ";
					
					stDelSegments= connectionDB2.prepareStatement(sqlDelSegments);
					stDelSegments.setInt(UNO, templateId);
					
					n=stDelSegments.executeUpdate();
					
					if(n>0){
						LOG.info("Se actualizo el template con id :"+ templateId );
					}
	
				}
	
				segmentsByIdx= templateVO.getMapSegmentsByIdx();
				int idx;
				TemplateSegmentVO segTmp=null;
				int segmentParentId=0;
				if( segmentsByIdx!=null &&  templateVO.getSegments()!=null && templateVO.getSegments().size()>0 ){
					
					sqlInsSegment=" INSERT INTO FAM161DEV.Tbl_Template_Segments ( TEMPLATE_ID , SEGMENT_PARENT_ID , WIDTH , HEIGHT , TIPO , IS_PERCENTAGE , ELEM_EQ , SEG_NAME , TEMPLATE_CHILD_ID ) "+
					              " VALUES ( ? , ? , ? , ? , ? , NULL , ? , ? , ? )  ";	
					
					pstInsSegment= connectionDB2.prepareStatement(sqlInsSegment, Statement.RETURN_GENERATED_KEYS);
					
					for( TemplateSegmentVO segment : templateVO.getSegments()    ){
	
						idx=segment.getParentSegment();
						segTmp=segmentsByIdx.get(idx);
						if( segTmp!=null ){
							segmentParentId=segTmp.getSegmentId();
						}
						
						pstInsSegment.setInt(UNO , templateId );
						pstInsSegment.setInt(DOS,  segmentParentId );
						pstInsSegment.setShort( TRES , segment.getWidth() );
						pstInsSegment.setShort(CUATRO , segment.getHeight());
						pstInsSegment.setString(CINCO, segment.getType());
						
						if( segment.isChildsEqual() ){
						     pstInsSegment.setString(SEIS, "Y");
						}else{
							  pstInsSegment.setString(SEIS, "N");
						}
						
						if( "N".equals(segment.getType() )){
							segName=segmentNames[currSegName++];
							pstInsSegment.setString(SIETE, segName);
						}else{
							pstInsSegment.setNull(SIETE, Types.VARCHAR );
						}
						pstInsSegment.setInt(OCHO, segment.getTemplateChild());
						
						n=pstInsSegment.executeUpdate();
						if(n>0){
							ResultSet rs = pstInsSegment.getGeneratedKeys();
						    try{
						    	rs.next();
							    segmentId = rs.getInt(1);
							}catch(SQLException ex){
								LOG.error(ex);
							}finally{
								if(rs!=null){
									rs.close();
									rs=null;
								}
							}
						    LOG.info("Se inserto el segmento :"+ segmentId );
						}
						
						segTmp=segmentsByIdx.get(segment.getIdx());
						if( segTmp!=null ){
							segTmp.setSegmentId(segmentId);
						}
					}
				}
				result=true;
			} catch (SQLException sqlException) {
				LOG.error("ERROR: en TemplateDAO.upsertTemplate(. JAVA_ERROR: "+sqlException.getMessage());
				result = false;
			}finally{
		       	try{
		    		if( pstInsSegment!=null ){
		    			pstInsSegment.close();
		    			pstInsSegment=null;
		    		}
		    		
		    		if( stInsTemplate!=null ){
		    			stInsTemplate.close();
		    			stInsTemplate=null;
		    		}
		    		
		    		if(stUpdTemplate!=null){
		    			stUpdTemplate.close();
		    			stUpdTemplate=null;
		    		}
		    		
		    		if( stDelSegments!=null ){
		    			stDelSegments.close();
		    			stDelSegments=null;
		    		}
		       	}
		       	catch (SQLException ex)
					{ LOG.error("Error cerrando statements en TemplateDAO.upsertTemplate: "+ ex.getMessage()); }
	       }
			
		} catch (SQLException sqlException) {
			LOG.error("ERROR: en TemplateDAO.upsertTemplate(. JAVA_ERROR: "+sqlException.getMessage());
			result = false;
		}finally{
	       	try{
	    		if(connectionDB2!=null){
	       			connectionDB2.close();
	       			connectionDB2=null;
	       		}
	       	}
	       	catch (SQLException ex)
				{ LOG.error("Error cerrando connection en TemplateDAO.upsertTemplate: "+ ex.getMessage()); }
       } 

		return result;
	}

	@SuppressWarnings("all")
	public TemplateVO getTemplate(int templateId){
		
		Connection connectionDB2 = null;
		TemplateVO templateVO=null;
		TemplateSegmentVO segmentVO=null;	
		PreparedStatement stTemplate=null;		 		
		String sqlTemplate=null;
		ResultSet rsTemplate=null;
				 				
		LOG.info("Se invoca getTemplate()");
	
		try {
			connectionDB2 = Persistor.getConnectionDB2();
			
			try{
				sqlTemplate=" SELECT                 "+
					"	T.TEMPLATE_ID ,             "+
					"	T.TEMPLATE_NAME ,           " +
					"	T.WIDTH AS TEMPLATE_WIDTH ,   "+
					"	T.HEIGHT AS TEMPLATE_HEIGHT ,  "+
					"	T.STATUS AS TEMPLATE_STATUS ,  "+
					"	T.TIPO_LAYOUT               ,  "+
					"	T.PATH_HEAD               ,  "+
					"	T.PATH_FOOT               ,  "+
					"	S.SEGMENT_ID ,                 " + 
					"   S.SEG_NAME   ,                 " +
					"	S.SEGMENT_PARENT_ID ,           "+
					"	S.WIDTH  ,                      "+
					"	S.HEIGHT ,                      "+
					"	S.TIPO   ,                      "+
					"   S.ELEM_EQ  ,                     "+
					"	S.IS_PERCENTAGE ,                " +
					"	S.TEMPLATE_CHILD_ID              " +
					"	FROM FAM161DEV.TBL_TEMPLATE T , FAM161DEV.TBL_TEMPLATE_SEGMENTS S   "+
					"	WHERE T.TEMPLATE_ID=S.TEMPLATE_ID        "+
					"	AND T.TEMPLATE_ID="+ templateId +"                     "+
					"	ORDER BY S.SEGMENT_ID                   ";
				
			    stTemplate  = connectionDB2.prepareStatement(sqlTemplate) ;	
				rsTemplate = stTemplate.executeQuery();
				
				
			    List<TemplateSegmentVO> lstSegments=new ArrayList<TemplateSegmentVO>();
				Map<Integer , List<TemplateSegmentVO> > segmentsByParent=new TreeMap< Integer , List<TemplateSegmentVO> >();
				Map<Integer , TemplateSegmentVO> segmentsById=new TreeMap<Integer,TemplateSegmentVO>();
				List<TemplateSegmentVO> lstSegmentsLeave=null;			
										
				List<TemplateSegmentVO> arrTmp=null;		    			 
				while( rsTemplate.next() ){
					if( templateVO==null ){
						templateVO=new TemplateVO();
						templateVO.setTemplateId(rsTemplate.getInt("template_id"));
						templateVO.setTemplateName( rsTemplate.getString("template_name") );
						templateVO.setWidht( rsTemplate.getShort("template_width"));
						templateVO.setHeight(rsTemplate.getShort("template_height"));
						templateVO.setLayoutType(rsTemplate.getString("tipo_layout"));
						templateVO.setTemplateHead(rsTemplate.getString("path_head"));
						templateVO.setTemplateFoot(rsTemplate.getString("path_foot"));
						
						templateVO.setMapSegmentsById(segmentsById);
						templateVO.setMapSegmentsByParent(segmentsByParent);
						templateVO.setSegments(lstSegments);
					}
					segmentVO=new TemplateSegmentVO();
					segmentVO.setSegmentId(rsTemplate.getInt("segment_id"));
					segmentVO.setSegName(rsTemplate.getString("seg_name"));
					segmentVO.setParentSegment(rsTemplate.getInt("segment_parent_id"));
					segmentVO.setWidth(rsTemplate.getShort("width"));
					segmentVO.setHeight(rsTemplate.getShort("height"));
					segmentVO.setType(rsTemplate.getString("tipo"));
					segmentVO.setPercentage(  rsTemplate.getString("is_percentage")!=null && rsTemplate.getString("is_percentage").equals("Y") ? true : false   );
					segmentVO.setChildsEqual( rsTemplate.getString("elem_eq")!=null && rsTemplate.getString("elem_eq").equals("Y") ? true : false );
					segmentVO.setTemplateChild(rsTemplate.getInt("TEMPLATE_CHILD_ID"));
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

			} catch (SQLException sqlException) {
				LOG.error("ERROR: en TemplateDAO.getTemplate(int)(. JAVA_ERROR: "+sqlException.getMessage());
				 
			}finally{
		       	try{
		       		if( rsTemplate!=null ){
		       			rsTemplate.close();
		       		}
		       		
		       		if( stTemplate!=null ){
		       			stTemplate.close();
		       		}
		       	}catch (SQLException ex){
		       		LOG.error("Error cerrando statements en ABCLogosDAO.SaveLogos: "+ ex.getMessage());
		       	}
	        } 
			
		} catch (SQLException sqlException) {
			LOG.error("ERROR: en TemplateDAO.getTemplate(int)(. JAVA_ERROR: "+sqlException.getMessage());
			 
		}finally{
	       	try{
	       		if(connectionDB2!=null){
	       			connectionDB2.close();
	       			connectionDB2=null;
	       		}
	       	}catch (SQLException ex){
	       		LOG.error("Error cerrando connection en ABCLogosDAO.SaveLogos: "+ ex.getMessage());
	       	}
        } 
		
		return templateVO;
	}
	
	@SuppressWarnings("all")
	public List<TemplateVO> getTemplates(){
		
		Connection connectionDB2 = null;
		TemplateVO templateVO=null;		 		
		String sqlTemplate=null;
		List<TemplateVO> lstTemplates=null;
		
		LOG.info("Se invoca getTemplates()");
	
		try {
			connectionDB2 = Persistor.getConnectionDB2();
			PreparedStatement stTemplate = null;
			ResultSet rsTemplate = null;
			try{
				sqlTemplate=" SET SCHEMA FAM161DEV; SELECT                 "+
					"	T.TEMPLATE_ID ,             "+
					"	T.TEMPLATE_NAME ,           " +
					"	T.WIDTH AS TEMPLATE_WIDTH ,   "+
					"	T.HEIGHT AS TEMPLATE_HEIGHT ,  "+
					"	T.STATUS AS TEMPLATE_STATUS   "+			 
					"	FROM Tbl_Template T    "+			 
					"	ORDER BY  T.TEMPLATE_NAME ";
				
			    stTemplate  = connectionDB2.prepareStatement(sqlTemplate);	
				rsTemplate = stTemplate.executeQuery();
														    			 
				while( rsTemplate.next() ){		 
					templateVO=new TemplateVO();
					templateVO.setTemplateId(rsTemplate.getInt("template_id"));
					templateVO.setTemplateName( rsTemplate.getString("template_name") );
					templateVO.setWidht( rsTemplate.getShort("template_width"));
					templateVO.setHeight(rsTemplate.getShort("template_height"));
					
					if( lstTemplates==null ){
						lstTemplates=new ArrayList<TemplateVO>();
					}
					lstTemplates.add(templateVO);
				}
				
			} catch (SQLException sqlException) {
				LOG.error("ERROR: en TemplateDAO.getTemplates(. JAVA_ERROR: "+sqlException.getMessage());
				
			}finally{
		       	try{
		       		if( rsTemplate!=null ){
		       			rsTemplate.close();
		       			rsTemplate=null;
		       		}
		       		
		       		if( stTemplate!=null ){
		       			stTemplate.close();
		       			stTemplate=null;
		       		}	          		       		 
		       	}
		       	catch (SQLException ex)
					{ LOG.error("Error cerrando statements en TemplateDAO.getTemplates: "+ ex.getMessage()); }
	       } 					 										 							 
		} catch (SQLException sqlException) {
			LOG.error("ERROR: en TemplateDAO.getTemplates(. JAVA_ERROR: "+sqlException.getMessage());
			
		}finally{
	       	try{
	       		if(connectionDB2!=null){
	       			connectionDB2.close();
	       			connectionDB2=null;
	       		}         		       		 
	       	}
	       	catch (SQLException ex)
				{ LOG.error("Error cerrando connection en TemplateDAO.getTemplates: "+ ex.getMessage()); }
       } 
				
	   return lstTemplates;
	}
	
	
	
	
	
	
	
	
}
