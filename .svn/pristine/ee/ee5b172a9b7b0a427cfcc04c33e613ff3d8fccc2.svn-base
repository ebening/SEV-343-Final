var xmlHttp;
function callAjaxReq( url , callBackFn ){
	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null){
		alert ("Su navegador no soporta AJAX!");
		return;
	}	
	xmlHttp.onreadystatechange=callBackFn ;
	xmlHttp.open("POST",url,true);
//    xmlHttp.overrideMimeType('text/xml');	
	xmlHttp.send(null);

} 


function GetXmlHttpObject(){
	var xmlHttp=null;
	try{
		// Firefox, Opera 8.0+, Safari
		xmlHttp=new XMLHttpRequest();
	}
	catch (e){
		// Internet Explorer
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}
