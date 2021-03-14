/**
 * Miguel Ramirez
 * @param idBtn 
 */
function adMasterButton(idBtn){
	  $("#"+idBtn).addClass("ad-button");
	  $("#"+idBtn).addClass("ad-widget");
	  $("#"+idBtn).addClass("ad-state-default");
	  $("#"+idBtn).addClass("ad-button-text-only");
	  $("#"+idBtn).addClass("ad-button-text");
	   $("#"+idBtn+" span:first").addClass("ad-button-text");
 }

/**
 * Miguel Ramírez
 * @param idButton
 * Esta funcion se utiliza para modiificar el fondo de los botones deshabilitados,
 * evitando que se confundan con los habilitados.
 */
function removeDisabled(idButton){
	var id = "#"+idButton;
	var bandera = $("#"+idButton).attr("disabled");
	
//para volver a activar el botón ponemos la sig. linea en donde queremos habilitarlo.
// $("#idBtn").addClass('ad-state-default'); y se pone cuando el código 
// tiene una linea como esta: document.getElementById("btn_fotosSecundaria").disabled = "";
   //ó como: 				  document.getElementById("btn_reemplazarDummie").disabled = false;
	
	if(bandera == 'disabled'){
	//	alert("desactivado: "+bandera+" button: "+idButton);
		$(id).removeClass('ad-state-default');
	}
}
