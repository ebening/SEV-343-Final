<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@page import="com.adinfi.seven.business.domain.TemplatesVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.adinfi.seven.presentation.views.util.Commons"%>
<%@page import="com.adinfi.seven.persistence.daos.TemplateDAO"%>
<%@page import="com.adinfi.seven.business.domain.TemplateVO"%>
<%@page import="com.adinfi.seven.business.domain.TemplateSegmentVO"%>
<%@page import="com.adinfi.servlets.ServletTemplate"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generar Template</title>
 <!-- JQuery -->
 <%
 String path = request.getContextPath();
 %>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/adMasterButton.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resources/js/jquery/themes/base/jquery-ui.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/js/jquery/css/demos.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/js/jquery/themes/base/jquery.ui.all.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/resources/css/adMaster.buttons.css"/>
	<link type="text/css" rel="stylesheet" href="../resources/css/ModuloArquitectura.css">

	 <script type="text/javascript"  src="<%=path%>/resources/js/ajax.js" ></script>		
	 
	 

<script type="text/javascript">
$(document).ready(function(){
	adMasterButton("btnSaveTemplate");
	//adMasterButton("btnReturnBack");
});


function popup(idx) {

	var ban=document.getElementById("uppop");
	if( ban.value == 0 ){
		ban.value = 1;
		asig();
	}
		
	var posicion_x;
	var posicion_y;
	var ancho=300;
	var alto=400; 
	var selectId=document.getElementById("col_seg_"+idx).value;
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2);
	newwindow=window.open('ArqAsociarSegmento.xhtml?selectId='+selectId+'&idx='+idx,'_blank','height='+alto+',width='+ancho+',left='+posicion_x+',top='+posicion_y+', location=0, resizable=0');
	if (window.focus) {newwindow.focus()}
	return false;
}

function popupimage(tipo) {	
	var posicion_x;
	var posicion_y;
	var ancho=590;
	var alto=215; 
	var nular=" ";
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2);
	newwindow=window.open('ArqUpload.xhtml?nular='+nular+'&tipo='+tipo,'_blank','height='+alto+',width='+ancho+',left='+posicion_x+',top='+posicion_y+', location=0, resizable=0');
	if (window.focus) {newwindow.focus()}
	return false;
}

function quitarimg(tipo){
	if(tipo == 'H'){
		document.getElementById("imgheader").value=" ";
	}else{
		document.getElementById("imgfooter").value=" ";
	}
}

function UpdateLayoutType(f , lyType ){

         EnableLLayout(lyType.value); 
         UpdateLayout();
		  
}


function UpdateNRows_G(/* f , c */){
    var f= document.forms["flayout_config"];
    var c= f.nrows_g;

    var isPercentage= f.elements["rows_g_lng_percent"].checked;

    var nRows= parseInt( c.value);
    var rowEqLngChk=f.rows_eq_lng;

    var width=parseInt(f.width.value);
    var height=parseInt(f.height.value);

    var heightCell=0;
    var txtHeight="";
    var disabled="";
     heightCell=height/nRows;  

    
    if( rowEqLngChk.checked==true ){
                 
       disabled=" disabled='true' ";
    }else{
       if(  isPercentage==true ){
          heightCell=100/nRows;  
       }else{
          heightCell=height/nRows;  
       }

    }
    txtHeight=" value='"+heightCell+"'  " ;
    

    var trRows_g_l=document.getElementById("nrows_g_l");
    var trRows_g_v=document.getElementById("nrows_g_v");
    
    
    while (trRows_g_l.hasChildNodes()) {
    	trRows_g_l.removeChild(trRows_g_l.lastChild);
    }    
    
       
    while (trRows_g_v.hasChildNodes()) {
    	trRows_g_v.removeChild(trRows_g_v.lastChild);
    }    
        
    
    
    var htmlTDs_l="";
    var htmlTDs_v="";
    
   // alert( trRows_g_v);
   /*
   var td = document.createElement('td');
td.innerHTML = 'ABC';
tr.appendChild(td);
   */
    var newTd_l=null;
    var newTd_v=null;
    for( var i=0;i<nRows;i++ ){

       newTd_l=document.createElement('td');
       newTd_l.innerHTML="Fila "+ (i+1);
       trRows_g_l.appendChild(newTd_l);
       
       newTd_v=document.createElement('td');
       newTd_v.innerHTML="<input type='text'   id='row_g_"+i+"'    name='row_g_"+i+"'  "+txtHeight+"  size='3'  "+disabled+"   onblur='verifHeight_G();UpdateLayout();'  > ";
       trRows_g_v.appendChild(newTd_v);
       
       
     //  htmlTDs_l+="<td>Fila "+ (i+1) + "</td>";
     //  htmlTDs_v+="<td><input type='text'   id='row_g_"+i+"'    name='row_g_"+i+"'  "+txtHeight+"  size='3'  "+disabled+"   onblur='verifHeight_G();UpdateLayout();'  ></td>";
    }

  //  trRows_g_l.innerHTML=htmlTDs_l;
  //  trRows_g_v.innerHTML=htmlTDs_v;

    UpdateLayout();         

}

function verifHeight_G(){
    var f= document.forms["flayout_config"];
    var c= f.nrows_g;

    var nRows= parseInt( c.value);
    var rowEqLngChk=f.rows_eq_lng;

    var width=parseInt(f.width.value);
    var height=parseInt(f.height.value);

    var heightCell=0;
    var txtHeight="";
    var disabled="";
   
    var isPercentage= f.elements["rows_g_lng_percent"].checked;
    var objInput=null;
    var heightTotal=0;
    for( var i=0;i<nRows;i++ ){
       objInput=document.getElementById("row_g_" + i );
       if( objInput!=null ){
          if( objInput.value!= null && objInput.value!="" ){  
            heightTotal+=parseInt( objInput.value );
          } 
       } 

    }

    
    if( isPercentage ==false ) {
       f.height.value="" + heightTotal;
    }


}

function verifWidth_G(){
    var f= document.forms["flayout_config"];
    var c= f.ncols_g;


    var nCols= parseInt( c.value);                   

    var width=parseInt(f.width.value);
    var height=parseInt(f.height.value);
    var isPercentage= f.elements["cols_g_lng_percent"].checked;


    var widthTotal=0;       
    var objInput=null; 
    for( var i=0;i<nCols;i++ ){
       objInput=document.getElementById("col_g_" + i );
       if( objInput!=null ){
          if( objInput.value!= null && objInput.value!="" ){  
            widthTotal+=parseInt( objInput.value );
          } 
       } 

    }


    if( isPercentage ==false ) {
       f.width.value="" + widthTotal;
    }

}

function UpdateNCols_G(){
    
    var f= document.forms["flayout_config"];
    var c= f.ncols_g;


    var nCols= parseInt( c.value);
    var isPercentage= f.elements["cols_g_lng_percent"].checked;
    
    var colEqLngChk=f.cols_eq_lng;

    var width=parseInt(f.width.value);
    var height=parseInt(f.height.value);

    var widthCell=0;
    var txtWidth="";
    var disabled="";
       widthCell=width/nCols;  
    

    if( colEqLngChk.checked==true ){
       disabled=" disabled='true' ";

    }else{

       if(  isPercentage==true ){
          widthCell=100/nCols;  
       }else{
          widthCell=width/nCols;  
       }



    }
    txtWidth=" value='"+widthCell+"'  " ;
    
    var trCols_g_l=document.getElementById("ncols_g_l");
    var trCols_g_v=document.getElementById("ncols_g_v");
    
    
    while (trCols_g_l.hasChildNodes()) {
    	trCols_g_l.removeChild(trCols_g_l.lastChild);
    }    
    
       
    while (trCols_g_v.hasChildNodes()) {
    	trCols_g_v.removeChild(trCols_g_v.lastChild);
    }    
    
    
    
    var htmlTDs_l="";
    var htmlTDs_v="";
    var newTd_l=null;
    var newTd_v=null;
    
    for( var i=0;i<nCols;i++ ){
    	
        newTd_l=document.createElement('td');
        newTd_l.innerHTML="Columna " + (i+1) ;
        trCols_g_l.appendChild(newTd_l);
        
        newTd_v=document.createElement('td');
        newTd_v.innerHTML="<input type='text'  id='col_g_"+i+"'   name='col_g_"+i+"'  "+ txtWidth +"  size='3'  "+disabled+"    onblur='verifWidth_G();UpdateLayout();'   > ";
        trCols_g_v.appendChild(newTd_v);
        
    	    	    	
     //  htmlTDs_l+="<td>Columna "+ (i+1) + "</td>";
     //  htmlTDs_v+="<td><input type='text'  id='col_g_"+i+"'   name='col_g_"+i+"'  "+ txtWidth +"  size='3'  "+disabled+"    onblur='verifWidth_G();UpdateLayout();'   ></td>";
    }

  //  trCols_g_l.innerHTML=htmlTDs_l;
   // trCols_g_v.innerHTML=htmlTDs_v;

    UpdateLayout();        


}

function UpdateNCols_R(/* f , c , */ idx ){


var f= document.forms["flayout_config"];
var c= f.elements["ncols_r_"+ idx];
if( c==null ) return;
var nCols= parseInt( c.value);

var widthCell=0;
var txtWidth="";

var width=parseInt(f.width.value);
var height=parseInt(f.height.value);


var colEqLngChk=f.elements["r_all_cols_eq_" + idx ];

widthCell=width/nCols;  
txtWidth=" value='"+widthCell+"'  " ;
var disabled=''

if( colEqLngChk.checked==true){
disabled=" disabled='true' "
    
    
}



var trCols_r_l=document.getElementById("tr_cols_l_"+ idx);
var trCols_r_v=document.getElementById("tr_cols_v_"+ idx);



while (trCols_r_l.hasChildNodes()) {
	trCols_r_l.removeChild(trCols_r_l.lastChild);
}    

   
while (trCols_r_v.hasChildNodes()) {
	trCols_r_v.removeChild(trCols_r_v.lastChild);
}    



 var htmlTDs_l="";
var htmlTDs_v="";
var newTd_l=null;
var newTd_v=null;
for( var i=0;i<nCols;i++ ){
	
	
    newTd_l=document.createElement('td');
    newTd_l.innerHTML="Columna "+ (i+1);
    trCols_r_l.appendChild(newTd_l);
    
    newTd_v=document.createElement('td');
    newTd_v.innerHTML="<input type='text'  id='col_r_"+idx+"_"+i+"'  "+disabled+"   name='col_r_"+idx+"_"+i+"'  "+ txtWidth +"  size='3' >";
    trCols_r_v.appendChild(newTd_v);
	
	
   //    htmlTDs_l+="<td>Columna "+ (i+1) + "</td>";
    //   htmlTDs_v+="<td><input type='text'  id='col_r_"+idx+"_"+i+"'  "+disabled+"   name='col_r_"+idx+"_"+i+"'  "+ txtWidth +"  size='3' ></td>";
}

//trCols_r_l.innerHTML=htmlTDs_l;
//trCols_r_v.innerHTML=htmlTDs_v;

UpdateLayout();


}


function EnableLLayout(tipo){
var f= document.forms["flayout_config"];
var lr=document.getElementById("lr_config");
var lg=document.getElementById("lg_config");



if(tipo=="G"){
  lr.style.display="none";
  lg.style.display="";
//  f.nrows_g.disabled=false;
 // f.ncols_g.disabled=false;
 // f.nrows_r.disabled=true;
  
  document.getElementById("nrows_g").disabled=false;
  document.getElementById("ncols_g").disabled=false;
  document.getElementById("nrows_r").disabled=true;  
  
  
  UpdateNRows_G();      
  UpdateNCols_G();
}else if( tipo=="R" ){
  lr.style.display="";
  lg.style.display="none";
  
  
  document.getElementById("nrows_r").disabled=false;
  document.getElementById("nrows_g").disabled=true;
  document.getElementById("ncols_g").disabled=true;  
  
 // f.nrows_r.disabled=false;
 // f.nrows_g.disabled=true;
 // f.ncols_g.disabled=true;
  


  UpdateNRows_R();  
  setTimeout( "UpdateNCols_R(0)" , 300 );
//  UpdateNCols_R(0);

}

}



/**** Objetos ******/


function ObjConfig(widthI,heightI){
  this.width=widthI;
  this.height=heightI;
  this.type="G";
  

  this.setType =function ( typeL ){
          this.type=typeL;
  }    
  
  this.setImage=function (img){
    this.image=img;
  }

}


function ObjLayoutGrid(widthI,heightI){
  this.numRows=numRows;
  this.numCols=numCols;
  this.colsEqualLng=true;
      this.rowsEqualLng=true;
      this.arrCols=new Array();
      this.arrRows=new Array();
  

  this.setNumRows =function ( nRows ){
          this.numRows=nRows;
  }    
  
  this.setNumCols=function ( nCols ){
          this.numCols=numCols;
  }

  this.setColsEqLng=function ( eqLng ){
          this.colsEqualLng=eqLng;
  }

      this.setRowsEqLng=function(  eqLng ){
          this.rowsEqualLng=eqLng;
      }



}



function verifHeight_R(){
    var f= document.forms["flayout_config"];
    var c= f.nrows_r;

   
    var nRows= parseInt( c.value);
    var rowEqLngChk=f.rows_r_eq_lng;

    var width=parseInt(f.width.value);
    var height=parseInt(f.height.value);

    var heightCell=0;
    var txtHeight="";
    var disabled="";
   
    var objInput=null;
    var heightTotal=0;
    for( var i=0;i<nRows;i++ ){
       objInput=document.getElementById("row_r_" + i );
       if( objInput!=null ){
          if( objInput.value!= null && objInput.value!="" ){  
            heightTotal+=parseInt( objInput.value );
          } 
       } 

    }



}



function UpdateNRows_R( ){

    var f= document.forms["flayout_config"];
  ///  var c= f.elements["nrows_r"];
    var c= document.getElementById("nrows_r");

    var isPercentage= f.elements["rows_r_lng_percent"].checked;


    var nRows= parseInt( c.value);

    
    
    var width=parseInt(f.width.value);
    var height=parseInt(f.height.value);

   var rowEqLngChk=f.rows_r_eq_lng;

    
    var heightCell=0;
    var txtHeight="";
    var disabled="";
    heightCell=height/nRows;  
  

    if( rowEqLngChk.checked==true ){
       disabled=" disabled='true' ";
    }else{
       if(  isPercentage==true ){
          heightCell=100/nRows;  
       }else{
          heightCell=height/nRows;  
       }

    }

    txtHeight=" value='"+heightCell+"'  " ;
   
    
    var trRows_r_l=document.getElementById("nrows_r_l");
    var trRows_r_v=document.getElementById("nrows_r_v");
    
    while (trRows_r_l.hasChildNodes()) {
    	trRows_r_l.removeChild(trRows_r_l.lastChild);
    }    
    
       
    while (trRows_r_v.hasChildNodes()) {
    	trRows_r_v.removeChild(trRows_r_v.lastChild);
    }        

    var htmlTDs_l="";
    var htmlTDs_v="";
    var newTd_l=null;
    var newTd_v=null;
    for( var i=0;i<nRows;i++ ){
    	
        newTd_l=document.createElement('td');
        newTd_l.innerHTML="Fila "+ (i+1);
        trRows_r_l.appendChild(newTd_l);
    	
        newTd_v=document.createElement('td');
        newTd_v.innerHTML="<input type='text'  id='row_r_"+i+"'  name='row_r_"+i+"'  "+ txtHeight +"  size='3'  "+ disabled +"   onblur='verifHeight_R();UpdateLayout();'   > ";
        trRows_r_v.appendChild(newTd_v);
    	
        
    	
    //   htmlTDs_l+="<td>Fila "+ (i+1) + "</td>";
     //  htmlTDs_v+="<td><input type='text'  id='row_r_"+i+"' class='FuenteYColor borderTextbox'  name='row_r_"+i+"'  "+ txtHeight +"  size='3'  "+ disabled +"   onblur='verifHeight_R();UpdateLayout();'   ></td>";
    }



 //   trRows_r_l.innerHTML=htmlTDs_l;
    //trRows_r_v.innerHTML=htmlTDs_v;


  

    var tblRows_r=document.getElementById("tbl_nrows_r");
    
    
    while (tblRows_r.hasChildNodes()) {
    	tblRows_r.removeChild(tblRows_r.lastChild);
    }    
    
    
   
    var htmlTRs="";
    
    var bgcolor_row="";



    var newTr=null;
    var newTd=null;
    for( var i=0;i<nRows;i++ ){

       if( i%2  >0 ){
         bgcolor_row="#FFFFFF";
       }else{
          
         bgcolor_row="#F0F0F6";
       }

       
       newTr=document.createElement('tr');      
     
       
       newTd=document.createElement('td');
       newTd.innerHTML="Fila "+(i+1);
       newTd.style.backgroundColor=bgcolor_row;
       
       newTr.appendChild(newTd);
       
       tblRows_r.appendChild(newTr);       
       
       
       htmlTRs="                  <table>            ";
       htmlTRs+="                         <tr>        ";
       htmlTRs+="                              <td>   ";
       htmlTRs+="                                    <select  id='ncols_r_"+i+"'   name='ncols_r_"+i+"'  onchange='UpdateNCols_R( "+i+"  );'  >   ";
       htmlTRs+="                                         <option value='1'>1</option>    ";
       htmlTRs+="                                         <option value='2'>2</option>    ";
       htmlTRs+="                                         <option value='3'>3</option>    ";
       htmlTRs+="                                         <option value='4'>4</option>    ";
       htmlTRs+="                                         <option value='5'>5</option>    ";
       htmlTRs+="                                         <option value='6'>6</option>    ";
       htmlTRs+="                                         <option value='7'>7</option>    ";
       htmlTRs+="                                         <option value='8'>8</option>    ";
       htmlTRs+="                                         <option value='9'>9</option>    ";
       htmlTRs+="                                         <option value='10'>10</option>    ";
       htmlTRs+="                                     </select> Columnas                  ";
       htmlTRs+="                              </td>                                      ";
       htmlTRs+="                         </tr> ";
       htmlTRs+="                         <tr><td><input type='checkbox'  name='r_all_cols_eq_"+i+"' checked='checked' value='Y'  onclick='UpdateAllColsEqual_R("+i+");'     >Columnas de igual ancho   ";
       htmlTRs+="                         </tr>  ";
       htmlTRs+="                         <tr>   ";
       htmlTRs+="                               <td>  ";
       htmlTRs+="                                    <table>    ";
       htmlTRs+="                                          <tr id='tr_cols_l_"+i+"'  > ";
       htmlTRs+="                                               <td>Columna 1      ";
       htmlTRs+="                                          </tr>                   ";
       htmlTRs+="                                          <tr  id='tr_cols_v_"+i+"'  >                    ";
       htmlTRs+="                                               <td><input type='text' size='3'    id='col_r_"+i+"_0'  name='col_r_"+i+"_0'  value='"+width+"'  disabled='true'  >     ";
       htmlTRs+="                                               ";                                                                            
       htmlTRs+="                                          </tr>   ";
       htmlTRs+="                                                  ";
       htmlTRs+="                                     </table>     ";
       htmlTRs+="                              </td>               ";  
       htmlTRs+="                         </tr>                    ";
       htmlTRs+="                </table>                          ";
      
       
       newTd=document.createElement('td');
       newTd.style.backgroundColor=bgcolor_row;
       newTd.innerHTML=htmlTRs;
       newTr.appendChild(newTd);      
       
       newTr=document.createElement('tr');
       newTd=document.createElement('td');
       newTd.innerHTML=" ";
       newTr.appendChild(newTd);
       
       tblRows_r.appendChild(newTr);
       
       
       /*
       htmlTRs+="              <tr><td bgcolor='"+bgcolor_row+"' >Fila "+(i+1)+" </td>   ";
       htmlTRs+="                  <td  bgcolor='"+bgcolor_row+"'    >               ";
       htmlTRs+="                  <table>            ";
       htmlTRs+="                         <tr>        ";
       htmlTRs+="                              <td>   ";
       htmlTRs+="                                    <select  id='ncols_r_"+i+"' class='fuenteYColor'  name='ncols_r_"+i+"'  onchange='UpdateNCols_R( "+i+"  );'  >   ";
       htmlTRs+="                                         <option value='1'>1</option>    ";
       htmlTRs+="                                         <option value='2'>2</option>    ";
       htmlTRs+="                                         <option value='3'>3</option>    ";
       htmlTRs+="                                         <option value='4'>4</option>    ";
       htmlTRs+="                                         <option value='5'>5</option>    ";
       htmlTRs+="                                         <option value='6'>6</option>    ";
       htmlTRs+="                                         <option value='7'>7</option>    ";
       htmlTRs+="                                         <option value='8'>8</option>    ";
       htmlTRs+="                                         <option value='9'>9</option>    ";
       htmlTRs+="                                         <option value='10'>10</option>    ";
       htmlTRs+="                                     </select> Columnas                  ";
       htmlTRs+="                              </td>                                      ";
       htmlTRs+="                         </tr> ";
       htmlTRs+="                         <tr><td><input type='checkbox'  name='r_all_cols_eq_"+i+"' checked='checked' value='Y'  onclick='UpdateAllColsEqual_R("+i+");'     >Columnas de igual ancho   ";
       htmlTRs+="                         </tr>  ";
       htmlTRs+="                         <tr>   ";
       htmlTRs+="                               <td>  ";
       htmlTRs+="                                    <table>    ";
       htmlTRs+="                                          <tr id='tr_cols_l_"+i+"'  > ";
       htmlTRs+="                                               <td>Columna 1      ";
       htmlTRs+="                                          </tr>                   ";
       htmlTRs+="                                          <tr  id='tr_cols_v_"+i+"'  >                    ";
       htmlTRs+="                                               <td><input type='text' size='3'    id='col_r_"+i+"_0'  name='col_r_"+i+"_0'  value='"+width+"'  disabled='true'  >     ";
       htmlTRs+="                                               ";                                                                            
       htmlTRs+="                                          </tr>   ";
       htmlTRs+="                                                  ";
       htmlTRs+="                                     </table>     ";
       htmlTRs+="                              </td>               ";  
       htmlTRs+="                         </tr>                    ";
       htmlTRs+="                </table>                          ";
       htmlTRs+="                </td>                             ";
       htmlTRs+="             </tr>                                ";
       htmlTRs+="              <tr><td height='3' colspan='2' ></td>   ";
        */

    }
 //  tblRows_r.innerHTML=htmlTRs;
   UpdateLayout();         

}


function UpdateLayout(){

var f= document.forms["flayout_config"];

var c_layout_type=f.layout_type;

var layoutType="";

for (var i = 0; i < c_layout_type.length; i++) {
if (c_layout_type[i].checked) {
  layoutType = c_layout_type[i].value;     
}
}


var html="";

var width=13+13+parseInt(f.width.value);
var height=13+13+parseInt(f.height.value);


html+= "  	   <table width='"+width+"' height='"+height+"'  bgcolor='gray'   cellpadding='0'  cellspacing='0' > "; 
html+= "      	    	<tr>   ";
html+= "      	    	 <td width='13' bgcolor='gray'     style='background-repeat: no-repeat;'   ></td>  ";
html+= "      	   	 <td   align='left'     style='background-repeat: no-repeat;'  bgcolor='gray'   height='13'   class='arial_11bb'   ></td>  "; 
html+= "      	         <td  width='13'   bgcolor='gray'    style='background-repeat: no-repeat;'   ></td>  		";
html+= "      	    	</tr> ";
html+= "      	  	<tr>  "; 
	  	

/*	  	      	 
html+= "  	                          	       <tr>   ";
html+= "  	                          	      	 <td width='13' background='img/filtro_left.png'   style='background-repeat: no-repeat;'  ></td>  ";     	         	     
html+= "  	                        	         <td   bgcolor='#ffffff'   style='background-repeat: no-repeat;' class='arial_11'  >  ";
html+= "  	                        	          <!--  inicio td con global --> ";
	          
html+= "  	                        	          CONTENIDO  ";
	          
html+= "  	                        	         </td>   ";
html+= "  	                                    <td width='13' background='img/filtro_right.png'   style='background-repeat: no-repeat;'  ></td>  ";   	     
html+= "  	                        	    	</tr>  ";  
*/


if( layoutType=='G' ){
html+=GetHTMLLayoutG();
}else if( layoutType=='R'  ){
html+=GetHTMLLayoutR();
}


html+= "  	                   <tr>  ";  
html+= "  	                    <td width='13' bgcolor='gray'   style='background-repeat: no-repeat;'   ></td>  "; 
html+= "  	                    <td  height='13'   style='background-repeat: no-repeat;'      bgcolor='gray'  height='13'    style='background-repeat: no-repeat;'   ></td>  ";  
html+= "  	                    <td  width='13'   bgcolor='gray'  style='background-repeat: no-repeat;'   ></td>   ";			
html+= "  	              	</tr>   "; 	         		 		    	          		         		    		    		    		    	
html+= "  	             	</table> 	";      

var objLayoutGen=document.getElementById("layout_gen");
objLayoutGen.innerHTML=html;


}


function GetHTMLLayoutG(){
var f= document.forms.flayout_config;
var c_rows=f.nrows_g;
var c_cols=f.ncols_g;

var numRows=parseInt( c_rows.value );
var numCols=parseInt( c_cols.value );
var htmlRows="";

if( numRows<=0 || numCols<=0 ) return;

var width=parseInt(f.width.value);
var height=parseInt(f.height.value);

var isPercentageRows= f.elements["rows_g_lng_percent"].checked;
var isPercentageCols= f.elements["cols_g_lng_percent"].checked;


var heighthCell=0;
var objHeightCell=null;
var idx=1;
for ( var i=0;i<numRows;i++ ){

objHeightCell=document.getElementById("row_g_"+i );
if( objHeightCell==null ) continue;
heightCell=parseInt( objHeightCell.value );
if( isPercentageRows==true ){
   heightCell= ( height/100 )* heightCell;
} 

htmlRows+="  <tr>   	<td width='"+width+"' height='1' colspan='3'bgcolor='gray' style='background-repeat: no-repeat;'></td>   	</tr>   ";

htmlRows+= "  	                          	       <tr>   ";
htmlRows+= "  	                          	      	 <td width='13' bgcolor='gray'   style='background-repeat: no-repeat;'  ></td>  ";     	         	     
htmlRows+= "  	                        	         <td   bgcolor='#ffffff'   style='background-repeat: no-repeat;' class='arial_11 fuenteYColor'  >  ";
htmlRows+= "  	                        	          <!--  inicio td con global --> ";
	          
htmlRows+= "  	                        	          <table  cellpadding='0'  cellspacing='0' >  ";
htmlRows+= "  	                        	          <tr>  ";
var widthCell=0;
var objWidthCell=null;


for( var j=0;j<numCols;j++ ){
	idx++;
	objWidthCell=document.getElementById("col_g_"+j );
	if( objWidthCell==null ) continue;
		widthCell=parseInt( objWidthCell.value );

	if( isPercentageCols==true ){
   		widthCell= ( width/100 )* widthCell;
	} 


  

//htmlRows+= "  	                        	          <td  width='"+widthCell+"'  height='"+heightCell+"'  >Cell "+i +", COL "+ j+ "</td>";
	htmlRows+= "  	                        	          <td  width='"+widthCell+"'  height='"+heightCell+"' align='right' valign='bottom' ><input type='hidden' id='col_seg_"+idx+"' value='0'><button  title='Asociar' type='button' class='common-button-miniatura' onclick='popup("+idx+")'>A</button></td>";
	if( j+1< numCols ){ 
		htmlRows+= "  	                        	          <td width='1'  bgcolor='gray'  ></td>";
	}

}
idx++;

htmlRows+= "  	                        	          </tr>  ";
htmlRows+= "  	                        	          </table>  ";
	          
htmlRows+= "  	                        	         </td>   ";
htmlRows+= "  	                                    <td width='13' bgcolor='gray'   style='background-repeat: no-repeat;'  ></td>  ";   	     
htmlRows+= "  	                        	    	</tr>  ";  



}


return htmlRows;


}





function GetHTMLLayoutR(){
var f= document.forms.flayout_config;
var c_rows=f.nrows_r;


var numRows=parseInt( c_rows.value );
var htmlRows="";

var width=parseInt(f.width.value);
var height=parseInt(f.height.value);

var isPercentageRows= f.elements["rows_r_lng_percent"].checked;
//var isPercentageCols= f.elements["cols_r_lng_percent"].checked;


var heighthCell=0;
var objHeightCell=null;
var numCols=0;
var idx=1;
for ( var i=0;i<numRows;i++ ){

objHeightCell=document.getElementById("row_r_"+i );
if( objHeightCell==null ) continue;
//alert( "objHeightCell "+ objHeightCell );
heightCell=parseInt( objHeightCell.value );

if( isPercentageRows==true ){
   heightCell= ( height/100 )* heightCell;
} 



numCols=parseInt( document.getElementById( "ncols_r_"+i  ).value);

htmlRows+="  <tr>   	<td width='"+width+"' height='1' colspan='3' bgcolor='#e4e7ef' style='background-repeat: no-repeat;'></td>   	</tr>   ";

htmlRows+= "  	                          	       <tr>   ";
htmlRows+= "  	                          	      	 <td width='13' background='<%=path%>/images/filtro_leftRED.png'   style='background-repeat: no-repeat;'  ></td>  ";     	         	     
htmlRows+= "  	                        	         <td   bgcolor='#ffffff'   style='background-repeat: no-repeat;' class='arial_11'  >  ";
htmlRows+= "  	                        	          <!--  inicio td con global --> ";
	          
htmlRows+= "  	                        	          <table  cellpadding='0'  cellspacing='0' >  ";
htmlRows+= "  	                        	          <tr>  ";
var widthCell=0;
var objWidthCell=null;


for( var j=0;j<numCols;j++ ){
	idx++;
	objWidthCell=document.getElementById("col_r_"+i+"_"+j );
	if( objWidthCell==null ) continue;
	widthCell=parseInt( objWidthCell.value );
  

	//htmlRows+= "  	                        	          <td  width='"+widthCell+"'  height='"+heightCell+"'  >Cell "+i +", COL "+ j+ "</td>";
	htmlRows+= "  	                        	          <td  width='"+widthCell+"'  height='"+heightCell+"' align='right' valign='bottom' ><input type='hidden' id='col_seg_"+idx+"' value='0'><button title='Asociar' type='button' onclick='popup("+idx+")'>A</button></td>";
	if( j+1< numCols ){ 
  		htmlRows+= "  	                        	          <td width='1'  bgcolor='#e4e7ef'  ></td>";
	}

}
idx++;
htmlRows+= "  	                        	          </tr>  ";
htmlRows+= "  	                        	          </table>  ";     	          
htmlRows+= "  	                        	         </td>   ";
htmlRows+= "  	                                    <td width='13' background='<%=path%>/images/filtro_rightRED.png'   style='background-repeat: no-repeat;'  ></td>  ";   	     
htmlRows+= "  	                        	    	</tr>  ";  



}


return htmlRows;


}


function UpdateAllRowsEqual_G(){
var f= flayout_config;
var rowEqLngChk=f.rows_eq_lng;

var c_rows=f.nrows_g;

var numRows=parseInt( c_rows.value );


if( numRows<=0   ) return;


var width=parseInt(f.width.value);
var height=parseInt(f.height.value);
var isPercentage= f.elements["rows_g_lng_percent"].checked;

   
var disabled=false; 
var  heightCell=0;
if( rowEqLngChk.checked==true ){
       disabled=true;            
       heightCell=height/numRows;                              
       txtHeight=" value='"+heightCell+"'  " ;
       f.elements["rows_g_lng_percent"].checked=false;
       f.elements["rows_g_lng_percent"].disabled=true;
}else{
   f.elements["rows_g_lng_percent"].disabled=false;
   if(  isPercentage==true ){
          heightCell=100/numRows;  
    }else{
          heightCell=height/numRows;  
     }
}

                       
for( var i=0;i<numRows;i++ ){             
      
       f.elements["row_g_"+ i ].disabled=disabled;
      // if( rowEqLngChk.checked==true ){
            f.elements["row_g_"+ i ].value="" + heightCell ;
           
     //  }
}

// UpdateLayout();

}



function UpdateAllColsEqual_G(){
var f= flayout_config;
var colEqLngChk=f.cols_eq_lng;

var c_cols=f.ncols_g;

var numCols=parseInt( c_cols.value );


if( numCols<=0   ) return;


var width=parseInt(f.width.value);
var height=parseInt(f.height.value);
var isPercentage= f.elements["cols_g_lng_percent"].checked;
    
var disabled=false; 
var  widthCell=0;
if( colEqLngChk.checked==true ){
       disabled=true;
       widthCell=width/numCols;  
       txtWidth=" value='"+widthCell+"'  " ;

       f.elements["cols_g_lng_percent"].checked=false;
       f.elements["cols_g_lng_percent"].disabled=true;

}else{

   f.elements["cols_g_lng_percent"].disabled=false;
   if(  isPercentage==true ){
          widthCell=100/numCols;  
    }else{
          widthCell=width/numCols;  
     }




}

                       
for( var i=0;i<numCols;i++ ){             
                  
       f.elements["col_g_"+ i ].disabled=disabled;
    //   if( colEqLngChk.checked==true ){
            f.elements["col_g_"+ i ].value="" + widthCell ;
     //  }
}

//UpdateLayout();

}


function UpdateAllRowsEqual_R(){
var f= flayout_config;
var rowEqLngChk=f.rows_r_eq_lng;
var isPercentage= f.elements["rows_r_lng_percent"].checked;


var c_rows=f.nrows_r;

var numRows=parseInt( c_rows.value );  
if( numRows<=0   ) return;


var width=parseInt(f.width.value);
var height=parseInt(f.height.value);
    
var disabled=false; 
var  heightCell=0;
if( rowEqLngChk.checked==true ){
       disabled=true;
       heightCell=height/numRows;  
       txtHeight=" value='"+heightCell+"'  " ;
       f.elements["rows_r_lng_percent"].checked=false;
       f.elements["rows_r_lng_percent"].disabled=true;

}else{
   f.elements["rows_r_lng_percent"].disabled=false;
   if(  isPercentage==true ){
          heightCell=100/numRows;  
    }else{
          heightCell=height/numRows;  
     }


}

                       
for( var i=0;i<numRows;i++ ){             
                
       f.elements["row_r_"+ i ].disabled=disabled;
    //   if( rowEqLngChk.checked==true ){
            f.elements["row_r_"+ i ].value="" + heightCell ;
      // }
}



}




function UpdateAllColsEqual_R(idx){
var f= flayout_config;
var colEqLngChk=f.elements["r_all_cols_eq_"+ idx ];
var c_cols=f.elements["ncols_r_"+ idx];   
var numCols=parseInt( c_cols.value );   
if( numCols<=0   ) return;


var width=parseInt(f.width.value);
var height=parseInt(f.height.value);
    


var disabled=false; 
var  widthCell=0;
widthCell=width/numCols;  
txtWidth=" value='"+widthCell+"'  " ;

if( colEqLngChk.checked==true ){
       disabled=true;
}

                       
for( var i=0;i<numCols;i++ ){             
      // htmlTDs_v+="<td><input type='text'  id='row_r_"+i+"'     name='row_r_"+i+"'  "+ txtHeight +"  size='3' ></td>";
      
       f.elements["col_r_"+ idx+"_" + i ].disabled=disabled;
       if( colEqLngChk.checked==true ){
            f.elements["col_r_"+ idx+"_" + i ].value="" + widthCell ;
       }
}

UpdateLayout();
}








function VerifCond(){

var f= document.forms["flayout_config"];

var c_layout_type=f.layout_type;

var layoutType="";

for (var i = 0; i < c_layout_type.length; i++) {
if (c_layout_type[i].checked) {
  layoutType=c_layout_type[i].value;     
}
}



if( layoutType=='G' ){
UpdateAllRowsEqual_G();
UpdateAllColsEqual_G();
}else if( layoutType=='R'  ){
//html+=GetHTMLLayoutR();
UpdateAllRowsEqual_R();
}



}


function GuardarTemplate(){

var ban=document.getElementById("uppop");
if( ban.value == 0 ){
	asig();
}
	
var fsave=  document.forms["ftemplates"];	
var f=  document.forms["flayout_config"];
var objHidden= document.getElementById("hiddenDiv");

var c_layout_type=f.layout_type;
var layoutType="";

for (var i = 0; i < c_layout_type.length; i++) {
  if (c_layout_type[i].checked) {
      layoutType=c_layout_type[i].value;     
  }
}
	  
var url= GenURL(layoutType);
callAjaxReq( url ,  saveTemplateResult );


}

function regresarSeleccionarTemplate(){
var url = document.URL;
var newURL = url.substring(0, url.indexOf("templates", 0));
window.location.href = newURL+'Arquitectura.xhtml';
}


function saveTemplateResult(){


 if (xmlHttp.readyState!=4) return;    
 var xmlObj=xmlHttp.responseXML; 


 var msgInfo=null; 
 var msgObj=null; 
 var msg_txt="";
 var codObj=null;
 var errCode="";

 if(xmlObj==null  ){ 	 

 //alert("Error en la peticion ");
 return;
    	 	   	  
 }
 //alert( xmlObj );

 /*
 msgInfo=xmlObj.getElementsByTagName('message_info')[0];
 msgObj=msgInfo.getElementsByTagName('message')[0];
 codObj=msgInfo.getElementsByTagName('code')[0];
 itemObj=msgInfo.getElementsByTagName('item')[0];
	 
 msg_txt=msgObj.firstChild.data;
 errCode=codObj.firstChild.data; 	
 alert( msg_txt ); 	   
 if( errCode!='0' ){
    return;
 }	*/   


}


 <%
    //TemplateDAO templateDAO=null;
 	ServletTemplate servletTemp = new ServletTemplate();
    TemplateVO templateVO=null;
	int templateId = (request.getParameter("template_id")!=null)?Commons.parseIntDefaultCero(request.getParameter("template_id")):0;
	%>
	idTemplate=<%=templateId%>;
	<%
	if( templateId>0){
	 	//templateDAO=new TemplateDAO();
	 	templateVO=  servletTemp.getTemplate(templateId); //tem.getTemplate(templateId); //
	 	if(templateVO==null){
	 		%>
	 		 alert("No se encontro el template ");	 		
	 		<%	 			 		
	 	}else{
	 		%>
	 		//loadTemplate();	 		
	 		<%
	 		
	 		
	 	}

 
	}
 %>

 
 
 <%
if( templateId>0 ){
	  
 %>
 function loadTemplate(){
 	var f= document.forms["flayout_config"];	  
	var c_layout_type=f.layout_type;
	var layoutTypeVal='<%=templateVO.getLayoutType()%>';
	  		 		 		 		 		
	var i;
	for( i = 0; i < c_layout_type.length; i++ ){
		if(c_layout_type[i].value==layoutTypeVal){
			c_layout_type[i].checked=true;
		    break;
		}
	}		
	  
	f.template_width.value='<%=templateVO.getWidht()%>';
	f.template_height.value='<%=templateVO.getHeight()%>';
	f.template_name.value='<%=templateVO.getTemplateName()%>';
	f.imgheader.value='<%=templateVO.getTemplateHead()%>';
	f.imgfooter.value='<%=templateVO.getTemplateFoot()%>';
	  
	EnableLLayout(layoutTypeVal); 
	  
	<%
	int rootSegment= templateVO.getRootSegmentId();
	%>
	<%
	List<TemplateSegmentVO> childSegments=null;
	// Map<Integer , TemplateSegmentVO > mapSegs=  templateVO.getMapSegmentsById();
	Map<Integer , List< TemplateSegmentVO> > mapSegs=  templateVO.getMapSegmentsByParent();
	childSegments=mapSegs.get(rootSegment);
	TemplateSegmentVO  segmentVO= templateVO.getMapSegmentsById().get(rootSegment);
	%>
	  
	  
	<%	 
	List<TemplateSegmentVO> subChildSegments=null;
	if( childSegments!=null && childSegments.size()>0 ){			  
		%>
		var cmb=null;
		if( layoutTypeVal=="G" ){
			cmb=document.getElementById("nrows_g");
		}else{
			cmb=document.getElementById("nrows_r");
		}
				
		var m=0;			 
		cmb.disabled=false;
		for( m=0;m< cmb.length;m++ ){				  
			if( cmb.options[m].value=='<%=childSegments.size()%>' ){
				cmb.selectedIndex=m;
				break;					   
			}
		}
		<%
		if("G".equals(templateVO.getLayoutType())){
			if( segmentVO.isChildsEqual() ){
		%>
				f.rows_eq_lng.checked=true;
		<%				  
			}else{
		%>
				f.rows_eq_lng.checked=false;
		<%				  
			}
		}else{
			if( segmentVO.isChildsEqual() ){
		%>
				f.rows_r_eq_lng.checked=true;
		<%				  
			}else{
		%>
				f.rows_r_eq_lng.checked=false;
		<%				  
			}				  
		}
		%>
		if( layoutTypeVal=="G" ){
			UpdateNRows_G(); 
			// alert("UpdateNRows_G " );
			//  UpdateNCols_G();
		}else{
			UpdateNRows_R();
		}			  
		<%
		int numCols=1;
		int i=0;
		for( TemplateSegmentVO segTmp : childSegments  ){
			subChildSegments=mapSegs.get( segTmp.getSegmentId() );				  
				  
			if( subChildSegments==null || subChildSegments.size()<=0 ){
				i++;
				continue;	
			}
			numCols=subChildSegments.size();
			if("G".equals(templateVO.getLayoutType())){
				/*
				if( segmentVO.isChildsEqual() ){
		%>
					f.row_g_<%=i%>.value=''
		<%						  
				}else{
		%>
					f.row_g_<%=i%>.value='<%=segTmp.getHeight()%>';
		<%						  						  
				}	
				*/
		%>
						  					  					  					  
				//  EnableLLayout(layoutTypeVal); 
				cmb=f.ncols_g;
				m=0;			 
				cmb.disabled=false;
				for( m=0;m< cmb.length;m++ ){				  
					if( cmb.options[m].value=='<%=numCols%>' ){
						cmb.selectedIndex=m;
						break;					   
						}
					}
		<%
					if( i==0){
		%>
						UpdateNCols_G(); 
		<%   
						   	     
					}			
		%> 
		<%
					if(segTmp.isChildsEqual() ){					  					  
		%>
						f.cols_eq_lng.checked=true;
						     
		<%
						if( i==0){
		%>
							UpdateNCols_G(); 
		<%   
						}			
		%>					     
		<%
					}else{
		%>
						f.cols_eq_lng.checked=false;
		<%
						int j=0;
						for( TemplateSegmentVO segSubTmp : subChildSegments ){
		%>
							f.col_g_<%=j%>.disabled=false;
							f.col_g_<%=j%>.value='<%=segSubTmp.getWidth()%>';
		<%
							j++;
						}
							  
					}  
			}else{	  
		%>
			//  EnableLLayout(layoutTypeVal); 
			//  UpdateNRows_R();  UpdateNCols_R( 1  );
						
				cmb=f.ncols_r_<%=i%>;
						  					  					  					 
				m=0;			 
				cmb.disabled=false;
				for( m=0;m< cmb.length;m++ ){								  
					if( cmb.options[m].value=='<%=numCols%>' ){
						cmb.selectedIndex=m;							  
						break;					   
					}
				}
		<%
				if( segmentVO.isChildsEqual() ){
		%>
					// f.row_r_<%=i%>.value=''
		<%						  
				}else{
		%>
					f.row_r_<%=i%>.value='<%=segTmp.getHeight()%>';
		<%						  						  
				}					  					  
						  
		%>
		<%
				if( segTmp.isChildsEqual() ){					    	
		%>
					f.r_all_cols_eq_<%=i%>.checked=true;
					UpdateNCols_R(<%=i%>);
		<%					    						    	
				}else{
		%>
					f.r_all_cols_eq_<%=i%>.checked=false;
					UpdateNCols_R(<%=i%>);
		<%    
					int j=0;
					for( TemplateSegmentVO segSubTmp : subChildSegments ){
		%>
						f.col_r_<%=i%>_<%=j%>.value='<%=segSubTmp.getWidth()%>';
		<%
						j++;
					}
		%>
		<%
				}  
			}
			i++;
		}

 	}
	%>

	UpdateLayout();
	 	 	 		
 } 

 <%
}
 %>

function GenURL(layoutType){
	
	  var f= flayout_config;
	  var rowsEqLngChk=null;
	  var numRows=0;
	  
	  var isRowsPercentage= false;
	  var rowsPercentage="N";
	  var eqLng="";
	  
	  if( layoutType=="G" ){
		 rowsEqLngChk=f.rows_eq_lng.checked;
		 numRows=parseInt( f.nrows_g.value );
		 isRowsPercentage= f.elements["rows_g_lng_percent"].checked;
		 rowsPercentage=isRowsPercentage ? "Y" : "N";	
		 
	  }else{
		 rowsEqLngChk=f.rows_r_eq_lng.checked;
		 numRows=parseInt( f.nrows_r.value );		 
		 isRowsPercentage= f.elements["rows_r_lng_percent"].checked;
		 rowsPercentage=isRowsPercentage ? "Y" : "N";		 
		 
	  }
	  
	  
	  if( rowsEqLngChk ){
		  eqLng="Y";
	  }else{
		  eqLng="N";
	  }
	  
	  	 	   
	  if( numRows<=0   ) return null;
	  
 
	  //var url="/pages/ActionTemplates?command=save&layout_type="+layoutType;
	  var url="<%=path%>/servlets/servletTemplate?command=save&layout_type="+layoutType;
	  
	  <%
	  if( templateId>0 ){
	  %>
	  url+="&template_id=<%=templateId%>";
	  <%
	  }
	  %>
	  
	  var templateName=f.elements["template_name"].value;
	  var width=parseInt(f.width.value);
	  var height=parseInt(f.height.value);
	  
	  
	  url+="&template_name="+ templateName;
	  url+="&template_width=" + width;
	  url+="&template_height=" + height;	  	  	  
	  
	  url+="&segment_count_0=" + numRows;
	  url+="&segment_type_0=R";
	  url+="&segment_width_0=" + width;
	  url+="&segment_height_0=" + height;	  
	  url+="&segment_parent_0=-1";
	  url+="&segment_percentage_0="+ rowsPercentage;
	  url+="&segment_eq_lng_0="+ eqLng;	  
	  
	         	  
	  var  heightCell=0;	 
	  var rowsEqualHeight=false;
	  	   	          
	  var idx=1;
	  var numCols=0;
	  var currIdx=0;
	  var colsEqualWidth=false;
	  var colsEqLngChk=null;
	  var widthCell=0;
	  var isColsPercentage=false;
	   
	  for( var i=0;i<numRows;i++ ){    
		   
		   
			  if( rowsEqLngChk.checked==true ){	       
			       heightCell=height/numRows;  	                                    	             
			   }else{
				 				   
				   if( layoutType=="G" ){
						  heightCell=parseInt( f.elements["row_g_"+ i ].value);
				   }else{
						  heightCell=parseInt( f.elements["row_r_"+ i ].value);
				   }
				   
			   }
		   
			  currIdx=idx;	
			  if( layoutType=="G" ){
				  numCols=parseInt( f.ncols_g.value );
				 if( f.cols_eq_lng.checked ){
					 eqLng="Y";
				 }else{
					 eqLng="N";
				 }
				 				 				 
			  }else{
				//  alert(idx)
				  
				  
				  numCols=parseInt( f.elements["ncols_r_"+i].value );
				  if( f.elements["r_all_cols_eq_"+ i ].checked ){
					  eqLng="Y";
				  }else{
					  eqLng="N";
				  }
			  }			  
		   
		     	      
			  url+="&segment_count_"+idx+"=" + numCols;
			  url+="&segment_type_"+idx+"=C";			 
			  url+="&segment_height_"+idx+"=" + heightCell;
			  url+="&segment_parent_"+idx+"=0";	
			  url+="&segment_eq_lng_"+idx+"="+ eqLng;	
			  
		//	  url+="&segment_percentage_"+ idx+ "="
			  			  			 			  
			  
			  for( var j=0;j<numCols;j++ ){
				  
				  if( layoutType=="G" ){
					  colsEqLngChk=f.cols_eq_lng;
					  if( colsEqLngChk.checked==true ){
						   widthCell=width/numCols;  
					  }else{
						   widthCell=parseInt( f.elements["col_g_"+j].value );
						  isColsPercentage=f.elements["cols_g_lng_percent"].checked;						 						   
					  }
					 
				  }else{
					  colsEqLngChk=f.elements["r_all_cols_eq_"+i];
					  if(colsEqLngChk.checked==true ){
						  widthCell=width/numCols;  
					  }else{
						  widthCell=parseInt( f.elements["col_r_"+i+"_"+j].value );
					  }
					  
					  
				  }				  
				  
				  
				  
				  idx++;
				  url+="&segment_count_"+idx+"=1";
				  url+="&segment_type_"+idx+"=N";
				//  if( rowsEqualHeight ){
					  url+="&segment_width_"+idx+"=" + widthCell;
					  /*
				  }else{
					  if( layoutType=="G" ){
				          url+="&segment_width_"+idx+"=" + f.elements["row_g_"+ i ].value;
					  }else{
						  url+="&segment_width_"+idx+"=" + f.elements["row_r_"+ i ].value; 
					  }
				  }*/
				  url+="&segment_parent_"+idx+"=1"//+ currIdx; 
				  url+="&segment_child_"+idx+"="+ document.getElementById("col_seg_"+idx).value;
				  
				  
				  
			  }
			  idx++;
		    

	   }
	   url+="&num_segments=" + idx;
	   url+="&template_head=" + document.getElementById("imgheader").value;
	   url+="&template_foot=" + document.getElementById("imgfooter").value;
	   
	   alert("El template se guardo correctamente.");
	   return url;
	
}

</script>

</head>
<body   <%if(templateId>0){ %>  onload='loadTemplate();'    <%} %>  >

	<table  cellpadding="0" cellspacing="0" border=0 style="width:1000px;">
		<tr>
			<td>
			<!-- <img src="<%=path%>/images/Logo_Header_Blanco.png" style="height: 25px; float: left;"><!--width: 2000px; Inicia tabla de contenido de formas -->
			</td>
			<td>
				<span style="color: rgb(142, 41, 44); font-size: 20px; font-weight: bold;">  Generar Template</span>
			</td>
		</tr>
		<tr style="padding-bottom: 5px;">
			<td colspan="2" >
			</td>
		</tr>
	</table>
<table>
<tr>
 <td>
    
  </td>
 
</tr>
<tr>
<td  valign='top' >
    <!-- tabla de la forma -->
         <form  class="form-content" name="flayout_config" method="post" enctype="multipart/form-data">
         	  <input type='hidden' id='uppop' value='0' />
              <table  cellpadding='0'  cellspacing='0' >
                    <tr>
                    	<td style="text-indent: 5px; padding: 5px; padding-left: 0px;" class="fuenteYColor">Nombre del Layout</td> 
                        <td><input type='text' id='template_name' class="fuenteYColor"  name='template_name'  ></td>
                    </tr>     
                    <tr><td style="text-indent: 5px;" class="fuenteYColor">Ancho</td>
                        <td style="padding-top:5px; padding-bottom: 5px; "><input type='text' id='template_width' class='fuenteYColor borderTextbox' name='width'  value='400'   onblur='VerifCond();UpdateLayout()'  ></td>
                    </tr>     
                    <tr><td style="text-indent: 5px;" class="fuenteYColor">Longitud</td>
                        <td style="padding-top:5px; padding-bottom: 5px; "><input type='text' id='template_height' name='height' value='300' class="fuenteYColor borderTextbox"  onblur='VerifCond();UpdateLayout()'  ></td>
                    </tr>   


                    <tr><td></td>
                        <td style="text-indent: 5px;" class="fuenteYColor" ><input type='checkbox' name='width_modif'     > Permitir modificar ancho</td>
                    </tr>     
                    <tr><td></td>
                        <td style="text-indent: 5px;" class="fuenteYColor" ><input type='checkbox' name='height_modif'    > Permitir modificar alto</td>
                    </tr>   


						<tr>
						<td height="13px"></td>
						</tr>				
	                    <tr>
                    
                    
                    <td valign='top' class="fuenteYColor">Tipo de Layout</td>
                        <td  valign='top'  class="fuenteYColor">
                              <table   cellpadding='0'  cellspacing='0'   >
                                     <tr><td   ></td></tr>
                                     <tr> <td>
                                               <table    cellpadding='0'  cellspacing='0'   >
                                                  <tr>
                                                     <td class="fuenteYColor" >

                                               <input type='radio'  name='layout_type'  onclick='UpdateLayoutType(this.form,this);'  value="G"  checked  >&nbsp; <select  name='nrows_g'  id='nrows_g' onchange='UpdateNRows_G(this.form,this)'  class="fuenteYColor" >
                                                                                <option value='1' >1</option>
                                                                                <option value='2' >2</option>
                                                                                <option value='3' >3</option>
                                                                                <option value='4' >4</option>
                                                                                <option value='5' >5</option>
                                                                                <option value='6' >6</option>
                                                                                <option value='7' >7</option>
                                                                                <option value='8' >8</option>
                                                                                <option value='9' >9</option>
                                                                                <option value='10' >10</option>                                                                               
                                                                            </select>
                                               Filas x <select  id='ncols_g'   name='ncols_g'  onchange='UpdateNCols_G(this.form,this);' class="fuenteYColor">
                                                                                <option value='1' >1</option>
                                                                                <option value='2' >2</option>
                                                                                <option value='3' >3</option>
                                                                                <option value='4' >4</option>
                                                                                <option value='5' >5</option>
                                                                                <option value='6' >6</option>
                                                                                <option value='7' >7</option>
                                                                                <option value='8' >8</option>
                                                                                <option value='9' >9</option>
                                                                                <option value='10' >10</option>                                                                               
                                                                            </select> Columnas
                                                       </td>
                                                  </tr>
                                                  <tr  id="lg_config" >
                                                       <td ><table   cellpadding='0'  cellspacing='0'   >
                                                                <tr><td class="fuenteYColor"><input type='checkbox'  id='rows_eq_lng'   name='rows_eq_lng' checked="checked"   value='Y'  onclick='UpdateAllRowsEqual_G();UpdateLayout();'   >Filas de igual altura 
                                                                        <input type='checkbox'  id='rows_g_lng_percent'  name='rows_g_lng_percent'   disabled='true'    onclick='UpdateAllRowsEqual_G();UpdateLayout();'      >%
                                                                </tr>
                                                                <tr><td class="fuenteYColor"><!-- background -->
                                                                        <table> 
                                                                              <tr  id="nrows_g_l"       >
                                                                                   <td>Row 1
                                                                                   
                                                                              </tr>
                                                                              <tr id="nrows_g_v" >
                                                                                   <td><input type='text' size='3'  id='row_g_0'   onblur='UpdateLayout();'  disabled='true'   class="fuenteYColor">
                                                                                    
                                                                              </tr>

                                                                        </table>
                                                                     </td>
                                                                </tr>    
                                                                <tr><td class="fuenteYColor"><input type='checkbox' name='cols_eq_lng'  checked="checked" value='Y'       onclick='UpdateAllColsEqual_G();UpdateLayout();'     >Columnas de igual ancho 
                                                                 <input type='checkbox'  id='cols_g_lng_percent' name='cols_g_lng_percent'   disabled='true'    onclick='UpdateAllColsEqual_G();UpdateLayout();'      >%
                                                                </tr>
                                                                <tr><td class="fuenteYColor">
                                                                        <table> 
                                                                              <tr id="ncols_g_l" >
                                                                                   <td>Col 1
                                                                                   
                                                                              </tr>
                                                                              <tr  id="ncols_g_v"   >
                                                                                   <td><input type='text' size='3' id='col_g_0'  onblur='UpdateLayout();'  disabled='true'  class="fuenteYColor" >
                                                                                   
                                                                              </tr>

                                                                        </table>
                                                                     </td>
                                                                </tr>    


							   </table>	
                                                       </td>  
                                                  </tr> 
                                               </table>
                                           </td><!-- arial_11 -->
                                     </tr>
                                     <tr><td   height='10' ></td></tr>
                                     <tr> <td><table    cellpadding='0'  cellspacing='0'  >
                                               <tr>
                                               <td class="fuenteYColor">   
                                               <input type='radio'   name='layout_type'   onclick='UpdateLayoutType(this.form,this);UpdateNRows_R(this.form,this);this.form.nrows_r.disabled=false;'  value="R"   >&nbsp; 
                                                                            <select  name='nrows_r'  id='nrows_r'   class='fuenteYColor'  onchange='UpdateNRows_R(this.form,this);'   disabled='true'  >
                                                                                <option value='1' >1</option>
                                                                                <option value='2' >2</option>
                                                                                <option value='3' >3</option>
                                                                                <option value='4' >4</option>
                                                                                <option value='5' >5</option>
                                                                                <option value='6' >6</option>
                                                                                <option value='7' >7</option>
                                                                                <option value='8' >8</option>
                                                                                <option value='9' >9</option>
                                                                                <option value='10' >10</option>                                                                               
                                                                            </select>
                                                Filas
                                               </td>                                               
                                               </tr>
                                               <tr  id="lr_config"  style="display:none;"  >
                                                <td>    
                                                   <table   cellpadding='0'  cellspacing='0'   class="fuenteYColor">
                                                           <tr>
                                                                 <td><input name='rows_r_eq_lng' type='checkbox' checked='checked' value='Y'     onclick='UpdateAllRowsEqual_R();UpdateLayout();'    >Filas de la misma altura
                                                                 <input type='checkbox'  id='rows_r_lng_percent'  name='rows_r_lng_percent'   disabled='true'    onclick='UpdateAllRowsEqual_R();UpdateLayout();'      >%
                                                           </tr>
                                                           <tr>
                                                                  <td class="fuenteYColor">
                                                                      <table   cellpadding='0'  cellspacing='0'   class="fuenteYColor"> 
                                                                            <tr  id='nrows_r_l'  >
                                                                                                            <td>Fila 1
                                                                                                            <td>Fila 2
                                                                                                            <td>Fila 3
                                                                            </tr>
                                                                            <tr  id='nrows_r_v' >
                                                                                                            <td><input type='text' size='3' >
                                                                                                            <td><input type='text' size='3' >
                                                                                                            <td><input type='text' size='3' >
                                                                                                             
                                                                            </tr>

                                                                       </table>
                                                                   </td>   
                                                           </tr>

 
                                                           <tr>
                                                               <td class="fuenteYColor">
                                                                    <table  id='tbl_nrows_r'    cellpadding='0'  cellspacing='0'> 
                                                                          <tr><td>Fila 1 </td>
                                                                              <td>
                                                                                  <table>
                                                                                      <tr>
                                                                                       <td>
                                                                                      <select   name='nrows_r' class="fuenteYColor" onchange='UpdateNRows_R(this.form,this);'  >
                                                                                        <option value='1'>1</option>
                                                                                        <option value='1'>2</option>
                                                                                      </select> Columnas
                                                                                        </td>
                                                                                       </tr>
                                                                                       <tr><td><input type='checkbox' >Columnas de igual ancho
                                                                                       </tr>
                                                                                       <tr>
                                                                                          <td>
                                                                                               <table> 
                                                                                                      <tr>
                                                                                                            <td>Columna 1
                                                                                                            <td>Columna 2
                                                                                                            <td>Columna 3
                                                                                                      </tr>
                                                                                                      <tr>
                                                                                                            <td><input type='text' size='3' >
                                                                                                            <td><input type='text' size='3' >
                                                                                                            <td><input type='text' size='3' >
                                                                                                             
                                                                                                      </tr>

                                                                                               </table>
                                                                                          </td>   
                                                                                       </tr>
                                                                                  </table>
                                                                              </td>
                                                                          </tr>
                                                                          <tr><td>Fila 2 </td>
                                                                              <td><select >
                                                                                       <option value='1'>1</option>
                                                                                       <option value='1'>2</option>
                                                                                  </select> Columnas
                                                                              </td>
                                                                          </tr>

                                                                    </table>  
                                                               </td>
                                                           </tr>
                                                           <tr>
                                                           </tr>
                                                           <tr>
                                                           </tr> 



                                                   </table>
                                                 </td>
                                               </tr>
                                              </table>
                                          </td>
                                     </tr>
                                     


                              </table>

                        </td>
                    </tr>
                    
                    <tr><td colspan="2"></td></tr>
                       <tr>
                       <td class="fuenteYColor">
                        <form name='ftemplates' method='post' >
     						<button id="btnSaveTemplate" class="common-button"  onclick='GuardarTemplate();'  >Guardar</button>
     						<div  id='hiddenDiv' ></div>
    					 </form>
                       
                       </td>
                       </tr> 
                         <tr><td colspan="2"></td></tr>
                    <tr><td colspan="2" style="padding: 5px; padding-left: 0px;" class="fuenteYColor">Imagen de Cabecera de Pagina</td></tr>
                    <tr>
                        <td colspan="2" class="fuenteYColor">
                       		<input type="text" name="header" id="imgheader" style="width: 215px"/>
                       		<button title='Examinar' class="common-button" type='button' onclick="popupimage('H');">Examinar</button>
                       		<button title='Eliminar' class="common-button" type='button' onclick="quitarimg('H');">Quitar</button>
                        </td>
                    </tr> 
                    
                    <tr><td colspan="2" style="padding: 5px; padding-left: 0px;" class="fuenteYColor">Imagen de Pie de Pagina</td></tr>
                    <tr>
                    	<td colspan="2" class="fuenteYColor">
                        	<input type="text" name="footer" id="imgfooter" style="width: 215px"/>
                       		<button title='Examinar' class="common-button" type='button' onclick="popupimage('F');">Examinar</button>
                       		<button title='Eliminar' class="common-button" type='button' onclick="quitarimg('F');">Quitar</button>
                        </td>
                    </tr> 
                 <tr>
                 <td height="40px"></td>
                 </tr>              
              </table>
              
         </form>   

    <!-- fin tabla de la forma  -->
</td>
<td width='20'></td>
<td  valign='top' id="layout_gen" >
     <!-- tabla del layout -->
		
    	   <table width='800'  cellpadding='0'  cellspacing='0' class="fuenteYColor"> 
    	    	<tr> 
    	    	 <td width='13'         ></td>
    	   	 <td   align='left'       height='13'   class='arial_11bb'   ></td> 
    	         <td  width='13'       ></td>  		
    	    	</tr> 
    	  	<tr> 
    	  	

       	       <tr> 
       	      	 <td width='13'    style='background-repeat: no-repeat;'  ></td>     	         	     
     	         <td   class='arial_11'  >
     	          <!--  inicio td con global -->      	          
     	          CONTENIDO
     	          <!-- fin td con global -->
     	         </td> 
                 <td width='13'    ></td>   	     
     	    	</tr>  

<tr>   	<td width="750" height="1" colspan="3"></td>   	</tr>


                <tr>  
                 <td width='13' ></td> 
                 <td height='13' ></td>  
                 <td width='13'  ></td>   			
           	</tr> 	         		 		    	          		         		    		    		    		    	
          	</table> 
		
     <!-- fin tabla layout  Filas de la misma altura-->

</td>
</tr>
</table>
<script type="text/javascript">
function asig(){
	<%
	if( templateId>0 ){
		int rootSegment= templateVO.getRootSegmentId();
		List<TemplateSegmentVO> childSegments=null;
		List<TemplateSegmentVO> subChildSegments=null;
		// Map<Integer , TemplateSegmentVO > mapSegs=  templateVO.getMapSegmentsById();
		Map<Integer , List< TemplateSegmentVO> > mapSegs=  templateVO.getMapSegmentsByParent();
		childSegments=mapSegs.get(rootSegment);
	%>
	<%
	 	int idx=2;
	 	for( TemplateSegmentVO segTmp : childSegments  ){
			subChildSegments=mapSegs.get( segTmp.getSegmentId() );
			
			if( subChildSegments==null || subChildSegments.size()<=0 ){
				idx++;
				continue;	
			}
			
			for( TemplateSegmentVO segSubTmp : subChildSegments ){
	%>
				temp = document.getElementById('col_seg_<%=idx%>');
				temp.value = '<%=segSubTmp.getTemplateChild()%>';
	<%
	 			idx++;
			}
	 		idx++;
	 		
		}
	}
	%>
 }
</script>

</body>
</html>