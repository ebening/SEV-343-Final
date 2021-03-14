<%@page import="com.adinfi.servlets.ServletFolletos"%>
<%@page import="com.adinfi.defines.GlobalDefines"%> 
<%@page import="com.adinfi.seven.business.domain.TblTemplateUser"%> 
<%@page import="java.util.List"%> 

<html> 
<head>   
<%
 String path = request.getContextPath();
 String urlThumb;
 %>
 	<link rel="stylesheet" href="<%=path%>/resources/css/style.css" type="text/css" media="screen" /> 
    <script type="text/javascript" src="<%=path%>/resources/jquery/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/adMasterButton.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resources/js/jquery/themes/base/jquery-ui.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/js/jquery/css/demos.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/js/jquery/themes/base/jquery.ui.all.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/adMaster.buttons.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/FontAndColor.css">   
  
  
  <script type="text/javascript" src="<%=path%>/resources/js/header.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/redips-drag-min.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/script.js"></script>	
  <script type="text/javascript" src="<%=path%>/resources/js/ajax.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/hash_table.js"></script>  
  <script type="text/javascript" src="<%=path%>/resources/js/hoja.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery-1.8.0.min.js"></script>
  
  <%
    String hojaId=request.getParameter("hoja_id");
  	String esCambio=request.getParameter("cambio_temp");
    int numCols=6;
    List<TblTemplateUser> lstTemplates=(List<TblTemplateUser>)request.getAttribute(GlobalDefines.REQ_CURR_LST_TMPL_BY_USR);
    
    if(esCambio!=null && "Y".equals(esCambio)){
    	ServletFolletos sf=new ServletFolletos();
    	lstTemplates = sf.getTemplateForChange(hojaId, request);
    }
  %>
  
 <script type="text/javascript">
  //<![CDATA[ 
	function popUpConfCambioTemp(templateId) {	
		var ancho = 600;
		var alto = 600;

		newwindow = window.showModalDialog('../pages/ArticulosExcedentes.xhtml?id_hoja=<%=hojaId%>&template_id='+templateId,"Confirmar Cambio de Template",'dialogWidth:'+ancho+'px; dialogHeight:'+alto+'px;center:1;resizable:0;status:0;scrollbars:0;menubar:0;titlebar:0;toolbar:0;');
		if (window.focus) {newwindow.focus();}
		return false;
	}
  //]]>
</script>
  
<head>
<body    >
  <h2  style='text-align:center' >FAVOR DE SELECCIONAR EL TEMPLATE </h2>
   <%
   int curr=0;
   TblTemplateUser template;
   if(lstTemplates!=null & lstTemplates.size() > 0){
	   %>	   
	   <table  align="center"  border="0" >
	      <%
	      while( curr<lstTemplates.size() ){	
	    	%>
	    	<tr>
	    	<%   
	        for(int i=0;i<numCols;i++ ){	
	        	
	        	%>
	            <td  width='100'>
	               <%
	               	if( curr<lstTemplates.size()  ){
	            	   template=lstTemplates.get(curr);
	            	   urlThumb=path+ "/"+  GlobalDefines.RUTA_THUMBS + "/thumb_temp_"+ template.getTblTemplate().getTemplateId() +".png" ;
	            	   
	            	   
	            	   if(esCambio!=null && "Y".equals(esCambio)){
	            	%>
		               	<a href="javascript:void(0);" onclick='popUpConfCambioTemp(<%=template.getTblTemplate().getTemplateId()%>);'>Template <%=template.getTblTemplate().getTemplateId()%></a>
		            <%
	            	   }else{
	               %>
	                    <table   >
	                        <tr><td  align="center" >
	                 	    <a href='<%=path%>/servlets/servletFolletos?command=sel_template&hoja_id=<%=hojaId%>&template_id=<%=template.getTblTemplate().getTemplateId()%>'>Template <%=template.getTblTemplate().getTemplateName() %> </a>
	                 	    </td>
	                 	    </tr>
	                 	    <tr>
	                 	       <td>
	                 	          <a href='<%=path%>/servlets/servletFolletos?command=sel_template&hoja_id=<%=hojaId%>&template_id=<%=template.getTblTemplate().getTemplateId()%>'><img width="100" height="100"  src='<%=urlThumb%>'></a>
	                 	       </td>
	                 	    
	                 	    </tr>
	                 	</table>
	                 	
	               <%
	            	   }	
	               }else{ %>
	                  &nbsp;
	               <%} %>
	               
	               
	            </td>
	            <% 
	            curr++;
	        }	    
	    	
	    	%>
	    	</tr>
	    	<% 
	    	
	      }
	      %>
	   
	   </table>
	   <% 
   }else{
   %>
   		<table  align="center"  border="1" >
   			<tr>
   				<td><p>No se han configurado Templates para esta Categoria</p></td>
   			</tr>
   		</table>
	<%} %> 
</body>
</html>