function findPrevio(idHoja) {
	// alert("idTemplate:" + idTemplate);
	loadHoja(idHoja);
	// REDIPS.drag.init();
}
/** **** inicio funciones nuevas *** */

var cellNames = [ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		'M', 'N', 'O', 'P', 'Q', 'R', 'S' ];
var currentCell = 0;
function drawLayoutChilds(ejecucion, tipoLayout, widthOrHeight, arrChilds,
		hashProds, readOnly) {

	var txtHtml = "";
	if (arrChilds == null)
		return "";
	// alert( "darwLayoutChilds :numero de hijos :"+ arrChilds.length );
	if (arrChilds.length <= 0)
		return "";
	// alert("HASHPRODS obj:"+ hashProds.get("147") );
	var objSegment = null;
	var htmlTmp = null;
	var objProd = null;
	// alert("tipo layout :"+ tipoLayout);
	if (tipoLayout == "R") {
		txtHtml += "<table id='ChristianTabla1' border='0' cellpadding='0' cellspacing='0'  width='"
				+ widthOrHeight + "'  > ";
		for (var i = 0; i < arrChilds.length; i++) {
			objSegment = arrChilds[i];
			// alert("segmento :"+ objSegment.getSegmentId()+ " , "
			// +objSegment.getTipo() );
			if (objSegment == null)
				continue;
			txtHtml += "<tr>";
			if (objSegment.getTipo() == 'N') {
				txtHtml += "<td  style='padding-top:5px padding-bottom: 5px; '   height='"
						+ objSegment.getHeight()
						+ "'   id='seg_"
						+ objSegment.getSegmentId()
						+ "_"
						+ objSegment.getSegmentName()
						+ "_"
						+ objSegment.getTemplateId() + "'   >";
			} else {
				txtHtml += "<td  style='padding-top:5px padding-bottom: 5px;  '  height='"
						+ objSegment.getHeight()
						+ "'   id='seg_"
						+ objSegment.getSegmentId()
						+ " "
						+ "_"
						+ objSegment.getTemplateId() + "'   >";
			}
			// alert("segmento :"+ objSegment.getSegmentId() );
			// alert("hashProds is :" +hashProds );
			// alert( objSegment.getSegmentId() );
			if (hashProds != null) {
				// alert("objprod clave :"+objSegment.getTemplateId()+ "-"
				// +objSegment.getSegmentId());
				objProd = hashProds.get(objSegment.getTemplateId() + "-"
						+ objSegment.getSegmentId());

				if (objProd != null) {

					txtHtml += "" + objProd.getPath();

				}

			}

			if (objSegment.getTipo() == 'R') {
				htmlTmp = drawLayoutChilds(ejecucion, objSegment.getTipo(),
						objSegment.getWidth(), objSegment.getChilds(),
						hashProds);
				if (htmlTmp != null) {
					txtHtml += htmlTmp;
				}

			} else {
				htmlTmp = drawLayoutChilds(ejecucion, objSegment.getTipo(),
						objSegment.getHeight(), objSegment.getChilds(),
						hashProds, readOnly);
				if (htmlTmp != null) {
					txtHtml += htmlTmp;
				}

			}

			txtHtml += "</td>";

			txtHtml += "</tr>"; // <td height='20px'
			txtHtml += "<tr> <td height='2.5px'  bgcolor='gray'  ></td></tr>";

		}
		txtHtml += "</table > ";

	} else if (tipoLayout == 'C') {
		txtHtml += "<table id='ChristianTable2' border='0' cellpadding='0' cellspacing='0'      height='"
				+ widthOrHeight + "'  > ";
		txtHtml += "<tr>";
		for (var i = 0; i < arrChilds.length; i++) {
			objSegment = arrChilds[i];
			if (objSegment == null)
				continue;

			if (objSegment.getTipo() == 'N') {
				txtHtml += "<td style='padding-top:5px padding-bottom: 5px;  '    width='"
						+ objSegment.getWidth()
						+ "'  id='seg_"
						+ objSegment.getSegmentId()
						+ "_"
						+ objSegment.getSegmentName()
						+ "_"
						+ objSegment.getTemplateId() + "'  >";
				txtHtml += objSegment.getSegmentName();
			} else {
				txtHtml += "<td style='padding-top:5px padding-bottom: 5px;  '   width='"
						+ objSegment.getWidth()
						+ "'  id='seg_"
						+ objSegment.getSegmentId()
						+ "_"
						+ " "
						+ "_"
						+ objSegment.getTemplateId() + "'  >";
			}
			// alert("C "+ objSegment.getSegmentId() );
			// alert("segmento :"+ objSegment.getSegmentId() );
			// alert("hashProds is :" +hashProds );
			if (hashProds != null) {
				// alert(objSegment.getTemplateId()+ "-"
				// +objSegment.getSegmentId());
				objProd = hashProds.get(objSegment.getTemplateId() + "-"
						+ objSegment.getSegmentId());
				// objProd=hashProds.get( objSegment.getTemplateId()+ "-"+
				// objSegment.getSegmentId() );

				if (objProd != null) {
					// alert( objProd.getNombre() )
					// txtHtml+=objSegment.getSegmentName();
					if (readOnly == false) {
						txtHtml += " <div id='Christian1' style='padding-top:5px padding-bottom: 5px;    width: 0px; border: 0px solid;  margin: 0px; cursor: move;' class='drag t1'>  ";
					} else {
						txtHtml += " <div id='Christian2' style='padding-top:5px padding-bottom: 5px;    width: 0px; border: 0px solid;  margin: 0px; ' >  ";
					}
					txtHtml += "      <img src='/seven/resources/images/fotos_articulos/"
							+ objProd.getPath()
							+ "' id='img_"
							+ objSegment.getSegmentId().toString()//objProd.getIdImagen()
							+ "' alt='2' name='"+ objProd.getIdImagen() +"' border='0' width='"+objProd.getImgWidth()+"' height='"+objProd.getImgHeight()+"' style='top:0; left:0;'>  ";
					txtHtml += " </div>  ";
					// txtHtml+=""+objProd.getPath();

					if (!ejecucion) {
						txtHtml += " <a id='comentarios' onclick='popupComentario("
								+ objSegment.getSegmentId().toString()
								+ ");' > <img height='20' width='20' src='../../resources/images/botones/chat.png' /></a>   ";
						txtHtml += " <a id='agregar'  onclick='popUpAgregarArticulos("
								+ objSegment.getSegmentId().toString()
								+ ", &apos;"
								+ objSegment.getSegmentName().toString()
								+ "&apos;);'><img height='20' width='20' src='../../resources/images/botones/agregar.png' /></a>   ";
						txtHtml += " <a id='archivos' onclick='popupArchivo("
								+ objSegment.getSegmentId().toString()
								+ ");'><img height='20' width='20' src='../../resources/images/botones/adjuntar.png' /></a>   ";
						txtHtml+=" <a id='resize' onclick='resizeImage(&apos;img_"+objSegment.getSegmentId().toString()+"&apos;);'><img height='20' width='20' src='../../resources/images/botones/resize.png' /></a>   ";
					} else {
						txtHtml += " <a id='precioPromocion'> $"
								+ objProd.getPrecio() + "</a>  ";
					}

				}

			}

			if (objSegment.getTipo() == 'R') {
				txtHtml += drawLayoutChilds(ejecucion, objSegment.getTipo(),
						objSegment.getWidth(), objSegment.getChilds(),
						hashProds);
			} else {
				txtHtml += drawLayoutChilds(ejecucion, objSegment.getTipo(),
						objSegment.getHeight(), objSegment.getChilds(),
						hashProds);
			}

			txtHtml += "</td>"; // <td width='20px'
			txtHtml += "  <td width='2.5px'  bgcolor='gray'  >        ";
		}

		txtHtml += "</tr>";
		txtHtml += "</table > ";
	}

	return txtHtml;

}

/*
 * function drawLayoutChilds( tipoLayout , widthOrHeight , arrChilds , hashProds ){
 * var txtHtml=""; if( arrChilds==null ) return ""; // alert( "darwLayoutChilds
 * :numero de hijos :"+ arrChilds.length ); if( arrChilds.length<=0 ) return
 * ""; // alert("HASHPRODS obj:"+ hashProds.get("147") ); var objSegment=null;
 * var htmlTmp=null; var objProd=null; // alert("tipo layout :"+ tipoLayout);
 * if( tipoLayout=="R" ){ txtHtml+="<table border='0' cellpadding='0'
 * cellspacing='0' width='"+ widthOrHeight +"' > "; for( var i=0; i<
 * arrChilds.length; i++ ){ objSegment=arrChilds[i]; // alert("segmento :"+
 * objSegment.getSegmentId()+ " , " +objSegment.getTipo() ); if(
 * objSegment==null ) continue; txtHtml+="<tr>";
 * 
 * txtHtml+="<td height='"+ objSegment.getHeight() +"'   id='seg_"+ objSegment.getSegmentId() +"'   >"; //
 * alert( objSegment.getSegmentId() ); if( hashProds!=null ){
 * objProd=hashProds.get(""+ objSegment.getSegmentId() ); if(objProd!=null){
 * txtHtml+=""+objProd.getPath(); } }
 * 
 * 
 * if( objSegment.getTipo()=='R' ){ htmlTmp= drawLayoutChilds(
 * objSegment.getTipo() , objSegment.getWidth() , objSegment.getChilds() ,
 * hashProds ); if(htmlTmp!=null){ txtHtml+= htmlTmp; }
 * 
 * 
 * 
 * 
 * }else{ htmlTmp=drawLayoutChilds( objSegment.getTipo() ,
 * objSegment.getHeight() , objSegment.getChilds() , hashProds ); if(
 * htmlTmp!=null ){ txtHtml+=htmlTmp; } }
 * 
 * txtHtml+="</td>";
 * 
 * txtHtml+="</tr>"; txtHtml+= "<tr> <td height='1'  bgcolor='#e4e7ef'  ></td></tr>"; }
 * txtHtml+="</table > ";
 * 
 * }else if( tipoLayout=='C' ){ txtHtml+="<table border='0' cellpadding='0'
 * cellspacing='0' height='"+ widthOrHeight +"' > "; txtHtml+="<tr>"; for( var
 * i=0; i< arrChilds.length; i++ ){ objSegment=arrChilds[i]; if(
 * objSegment==null ) continue;
 * 
 * 
 * txtHtml+="<td width='"+ objSegment.getWidth() +"'  id='seg_"+ objSegment.getSegmentId() +"'  >"; //
 * alert("C "+ objSegment.getSegmentId() ); if( hashProds!=null ){
 * 
 * objProd=hashProds.get(""+ objSegment.getSegmentId() );
 * 
 * if(objProd!=null){
 * 
 * txtHtml+=" <div style='width: 0px; border: 0px solid; padding: 0px; margin:
 * 0px; cursor: move;' class='drag t1'> "; txtHtml+=" <img
 * src='/seven/images_articles/"+ objProd.getPath() +"' id='"+
 * objProd.getIdImagen() +"' alt='2' border='0' width='120' height='120'
 * style='top:0; left:0;'> "; txtHtml+=" </div> "; //
 * txtHtml+=""+objProd.getPath(); } }
 * 
 * 
 * 
 * 
 * if( objSegment.getTipo()=='R' ){ txtHtml+= drawLayoutChilds(
 * objSegment.getTipo() , objSegment.getWidth() , objSegment.getChilds() );
 * }else{ txtHtml+= drawLayoutChilds( objSegment.getTipo() ,
 * objSegment.getHeight() , objSegment.getChilds() ); }
 * 
 * txtHtml+="</td>"; txtHtml+=" <td width='1'  bgcolor='#e4e7ef'  > "; }
 * 
 * txtHtml+="</tr>"; txtHtml+="</table > "; }
 * 
 * 
 * return txtHtml; }
 * 
 */

function ObjProduct() {

	this.item = null;
	this.descripcion = null;
	this.precio = 0.0;
	this.hoja = 0;
	this.version = 0;
	this.idImagen = null;
	this.marca = null;
	this.nombre = null;
	this.imgwidth = 120;
	this.imgheight = 120;
	this.path = null;
	this.copia = 0;
	this.comentarios = null;
	this.tipoImagen = null;
	this.tipoSKU = null;
	this.departamento = null;
	this.idTemplate = 0;
	this.idSegmento = 0;

	this.setIdSegmento = function(ids) {
		this.idSegmento = ids;
	};

	this.getIdSegmento = function() {
		return this.idSegmento;
	};

	this.setIdTemplate = function(idt) {
		this.idTemplate = idt;
	};

	this.getIdTemplate = function() {
		return this.idTemplate;
	};

	this.setDepartamento = function(dep) {
		this.departamento = dep;
	};

	this.getDepartamento = function() {
		return this.departamento;
	};

	this.setTipoSKU = function(ts) {
		this.tipoSKU = ts;
	};

	this.getTipoSKU = function() {
		return this.tipoSKU;
	};

	this.setTipoImagen = function(ti) {
		this.tipoImagen = ti;
	};

	this.getTipoImagen = function() {
		return this.tipoImagen;
	};

	this.setComentarios = function(com) {
		this.comentarios = com;
	};

	this.getComentarios = function() {
		return this.comentarios;
	};

	this.setCopia = function(cp) {
		this.copia = cp;
	};

	this.getCopia = function() {
		return this.copia;
	};

	this.setImgWidth = function(width) {
		this.imgwidth = width;
	};

	this.getImgWidth = function() {
		return this.imgwidth;
	};
	
	this.setImgHeight = function(height) {
		this.imgheight = height;
	};

	this.getImgHeight = function() {
		return this.imgheight;
	};
	
	this.setPath = function(pa) {
		this.path = pa;
	};

	this.getPath = function() {
		return this.path;
	};

	this.setNombre = function(nom) {
		this.nombre = nom;
	};

	this.getNombre = function() {
		return this.nombre;
	};

	this.setMarca = function(mr) {
		this.marca = mr;
	};

	this.getMarca = function() {
		return this.marca;
	};

	this.setIdImagen = function(idImg) {
		this.idImagen = idImg;
	};

	this.getIdImagen = function() {
		return this.idImagen;
	};

	this.setVersion = function(ver) {
		this.version = ver;
	};

	this.getVersion = function() {
		return this.version;
	};

	this.setHoja = function(hoj) {
		this.hoja = hoj;
	};

	this.getHoja = function() {
		return this.hoja;
	};

	this.setPrecio = function(pr) {
		this.precio = pr;
	};

	this.getPrecio = function() {
		return this.precio;
	};

	this.setDescripcion = function(desc) {
		this.descripcion = desc;
	};

	this.getDescripcion = function() {
		return this.descripcion;
	};

	this.setItem = function(it) {
		this.item = it;
	};

	this.getItem = function() {
		return this.item;
	};

}

function ObjSegment() {

	this.segmentId = 0;
	this.segmentParentId = 0;
	this.numElements = 0;
	this.tipo = null;
	this.width = 0;
	this.height = 0;
	this.segmentName = null;
	this.arrChilds = new Array();
	this.templateId = 0;

	this.setTemplateId = function(temp_id) {
		this.templateId = temp_id;
	};

	this.getTemplateId = function() {
		return this.templateId;
	};

	this.setSegmentId = function(segment_id) {
		this.segmentId = segment_id;
	};

	this.getSegmentId = function() {
		return this.segmentId;
	};

	this.setSegmentParentId = function(segment_parent_id) {
		this.segmentParentId = segment_parent_id;
	};

	this.getSegmentParentId = function() {
		return this.segmentParentId;
	};

	this.setNumElements = function(num_elements) {
		this.numElements = num_elements;
	};

	this.setTipo = function(tipo) {
		this.tipo = tipo;
	};

	this.getTipo = function() {
		return this.tipo;
	};

	this.setWidth = function(width) {
		this.width = width;
	};

	this.getWidth = function() {
		return this.width;
	};

	this.setHeight = function(height) {
		this.height = height;
	};

	this.getHeight = function() {
		return this.height;
	};

	this.setSegmentName = function(seg) {
		this.segmentName = seg;
	};

	this.getSegmentName = function() {
		return this.segmentName;
	};

	this.getChilds = function() {
		return this.arrChilds;
	};

	this.addChild = function(objSegChild) {
		if (this.arrChilds == null) {
			this.arrChilds = new Array();
		}
		this.arrChilds[this.arrChilds.length++] = objSegChild;
	};
}

function paintLayout(objSegment, hashProds, pathHead, pathFoot, readOnly,
		ejecucion) {
	if (objSegment == null)
		return;
	var html = "";
	// alert("paintLayout :hashProds is "+ hashProds );

	// alert( "num elementos :"+ hashProds.toString() ); 20px 20px
	html += "  <DIV id='previo' style='border-left:2.5px solid gray; border-top: 2.5px solid gray; width:"
			+ objSegment.getWidth() + "'>";
	html += "  	   <table         cellpadding='0'  cellspacing='0' > ";

	if (pathHead != null) {

		html += "<tr><td>";
		html += "<img width='" + objSegment.getWidth() + "' src='" + pathHead
				+ "'>";
		html += "</td></tr>";
	}

	/*
	 * html+= " <tr> "; html+= "
	 * html+= "
	 * <td   align='left'     style='background-repeat: no-repeat;'   bgcolor='#d1e2e9'  height='13'   class='arial_11bb'   ></td> ";
	 * html+= "
	 * html+= " </tr> "; html+= " <tr> ";
	 */

	// html+= " <tr> <td height='1' colspan='3' bgcolor='#e4e7ef'
	// style='background-repeat: no-repeat;'></td> </tr> ";
	html += "  	                          	       <tr>   ";
	// html+= " <td width='13'
	// style='background-repeat: no-repeat;' ></td> ";
	html += "  	                        	         <td  style='background-repeat: no-repeat;' class='arial_11'  >  ";
	html += "  	                        	          <!--  inicio td con global --> ";

	if (objSegment.getTipo() == 'R') {
		html += drawLayoutChilds(ejecucion, 'R', objSegment.getWidth(),
				objSegment.getChilds(), hashProds, readOnly);
	} else {
		html += drawLayoutChilds(ejecucion, 'C', objSegment.getHeight(),
				objSegment.getChilds(), hashProds, readOnly);
	}

	html += "                                	          <!--  fin td global -->        ";
	html += "  	                        	         </td>   ";
	// html+= " <td width='13'
	// style='background-repeat: no-repeat;' ></td> ";
	html += "  </tr>  ";

	if (pathFoot != null) {

		html += "<tr><td style='padding-bottom:5px; padding-right:5px; padding-left: 5px; ' >";
		html += "<img width='" + objSegment.getWidth() + "' src='" + pathFoot
				+ "'>";
		html += "</td></tr>";
	}

	/*
	 * html+= " <tr> "; html+= "
	 * html+= "
	 * <td  height='13'   style='background-repeat: no-repeat;'      bgcolor='#d1e2e9'  height='13'    style='background-repeat: no-repeat;'   ></td> ";
	 * html+= "
	 * html+= " </tr> ";
	 */
	html += "  	             	</table> 	";
	html += " </div>";

	return html;

}

function loadImages(idArt) {
	if (idArt == null)
		return;
	var url = "/seven/servlets/servletFolletos?command=get_images&id_articulo="
			+ idArt;
	callAjaxReq(url, loadImagesResult);

}

function loadImagesResult() {
	if (xmlHttp.readyState != 4)
		return;
	var textObj = xmlHttp.responseText;
	// alert(textObj);

	objImages = document.getElementById("images");
	objImages.innerHTML = textObj;
	REDIPS.drag.init();
}

function loadComboProductos(hojaId) {
	var url = "/seven/servlets/servletFolletos?command=get_skus&hoja_id="
			+ hojaId;

	// alert(url);
	// alert("loadTemplate");
	callAjaxReq(url, loadComboProductosResult);
}

function loadComboProductosResult() {

	if (xmlHttp.readyState != 4)
		return;
	var xmlObj = xmlHttp.responseXML;

	if (xmlObj == null) {

		alert("Error en la peticion loadComboProductos ");
		return;

	}

	// alert( xmlHttp.responseXML );

	var objInfo = xmlObj.getElementsByTagName('info')[0];

	var objItems = null;

	var id = null;
	var descrip = null;

	objItems = objInfo.getElementsByTagName('items')[0];
	if (objItems == null) {
		// alert("objItems is null");
		return;
	}

	var arrItems = objItems.getElementsByTagName('item');

	if (arrItems == null) {
		// alert("arr items is null");
		return;
	}

	var objItem = null;
	var cmbProds = document.getElementById("cmbProductos");
	for (var i = 0; i < arrItems.length; i++) {
		objItem = arrItems[i];

		id = objItem.getElementsByTagName('id')[0].firstChild.data;
		descrip = objItem.getElementsByTagName('descrip')[0].firstChild.data;

		cmbProds.length++;
		cmbProds.options[cmbProds.length - 1].value = id;
		cmbProds.options[cmbProds.length - 1].text = id;

		// alert("id:"+ id);
		// alert("descrip :"+ descrip);

	}

}

function loadHoja(hojaId) {
	var url = "/seven/servlets/servletFolletos?command=get_hoja&hoja_id="
			+ hojaId;
	// alert(url);
	// alert("loadTemplate");
	callAjaxReq(url, loadHojaResult);
}

function loadHojaResult() {
	if (xmlHttp.readyState != 4)
		return;
	var xmlObj = xmlHttp.responseXML;

	if (xmlObj == null) {
		alert("Error en la peticion ");
		return;

	}

	// alert( xmlHttp.responseXML );
	var hashProds = null;
	var objSegInfo = xmlObj.getElementsByTagName('segment_info_0')[0];

	var objChild = null;
	var id = null;
	var segType = null;
	var segWidth = 0;
	var segHeight = 0;
	var templateId = 0;

	id = objSegInfo.getElementsByTagName('segment_id_0')[0].firstChild.data;
	segType = objSegInfo.getElementsByTagName('segment_type_0')[0].firstChild.data;
	segWidth = objSegInfo.getElementsByTagName('segment_width_0')[0].firstChild.data;
	segHeight = objSegInfo.getElementsByTagName('segment_height_0')[0].firstChild.data;
	// templateId=objSegInfo.getElementsByTagName('segment_template_id_0'
	// )[0].firstChild.data;

	var objSegment = new ObjSegment();
	objSegment.setSegmentId(id);
	objSegment.setTipo(segType);
	objSegment.setWidth(segWidth);
	objSegment.setHeight(segHeight);
	// objSegment.setTemplateId(templateId);

	setSegmentChilds(objSegment, id, xmlObj);

	// si se pinta con productos
	var objProdInfo = xmlObj.getElementsByTagName('info_prods');
	// alert( "obj prod info :"+ objProdInfo );

	if (objProdInfo != null) {
		hashProds = setProducts(objProdInfo, xmlObj);

	}

	var objGridInfo = xmlObj.getElementsByTagName("info_grid");
	// var string = (new
	// XMLSerializer()).serializeToString(objGridInfo[0].firstChild);

	if (objGridInfo != null) {
		if (objGridInfo[0] != null) {
			document.getElementById("grid").innerHTML = objGridInfo[0].textContent;
		}

	}

	var pathHeadVal = null;
	var pathFootVal = null;

	var pathHead = xmlObj.getElementsByTagName("path_head");
	// alert("pathhead[0.text :"+pathHead[0].textContent);
	if (pathHead != null) {
		if (pathHead[0] != null) {
			pathHeadVal = pathHead[0].textContent;
		}
	}

	// alert("pathHeadVal :"+ pathHeadVal);

	var pathFoot = xmlObj.getElementsByTagName("path_foot");
	if (pathFoot != null) {
		if (pathFoot[0] != null) {
			pathFootVal = pathFoot[0].textContent;
		}
	}

	var readOnlyVal = false;

	var readOnly = xmlObj.getElementsByTagName("readonly");
	if (readOnly != null) {
		if (readOnly[0] != null) {
			if (readOnly[0].textContent == 'true') {
				readOnlyVal = true;
			}
		}
	}

	var ejecucionVal = false;

	var ejecucion = xmlObj.getElementsByTagName("ejecucion");
	if (ejecucion != null) {
		if (ejecucion[0] != null) {
			if (ejecucion[0].textContent == 'true') {
				ejecucionVal = true;
			}
		}
	}

	var hojaId = xmlObj.getElementsByTagName("hoja_id")[0].textContent;

	var html = paintLayout(objSegment, hashProds, pathHeadVal, pathFootVal,
			readOnlyVal, ejecucionVal);
	// alert( html )
	// alert( htmlLay)
	document.getElementById('htmMain').innerHTML = html;
	/*
	 * var td1 = document.createElement("TD");
	 * //td1.appendChild(document.createTextNode(html)); td1.innerHTML=htmlLay
	 * 
	 * var tr=document.getElementById('htmMain'); tr.appendChild(td1);
	 */
	REDIPS.drag.init();

	// set REDIPS.drag reference
	var rd = REDIPS.drag;
	// define event.changed handler

	rd.event.droppedBefore = function(targetCell) {

		var s_segmentId = targetCell.id;
		if (s_segmentId == null)
			return false;
		if (s_segmentId == "")
			return false;
		var ss = s_segmentId.split("_");
		if (ss == null)
			return false;
		if (ss.length <= 1)
			return false;
		var segmentId = parseInt(ss[1]);
		if (isNaN(segmentId)) {
			return false;
		}

		return true;
	};

	rd.event.dropped = function(targetCell) {
		// get current position (method returns positions as array)
		// alert( targetCell.id);
		var s_segmentId = targetCell.id;
		// targetCell.style.align='center';
		var pos = rd.getPosition();
		// display current row and current cell
		// alert('Changed: ' + pos[1] + ' ' + pos[2]);
		// alert(rd.obj.children[0].id);
		var obj = rd.obj.children[0];
		// alert(rd.obj.parentNode.id);
		var ss = s_segmentId.split("_");
		var segmentId = parseInt(ss[1]);
		var segmentName = ss[2];
		var templateId = ss[3];

		if (isNaN(segmentId)) {
			return false;
		}

		document.getElementById("seg_" + segmentId + "_"
				+ segmentName + "_" + templateId).getElementsByTagName('img')[0].id = 'img_'+segmentId;
		
		var objTd = document.getElementById("seg_" + segmentId + "_"
				+ segmentName + "_" + templateId);
		
		var idimgadd = 'img_'+segmentId;
		// var linkText = document.createTextNode("Comentarios");
		// objA.appendChild(linkText);
		// objA.title = "my title text";
		// objA.href = "javascript:void(0);";
		// var textSp = document.createTextNode("PROBANDO ");
		// objTd.appendChild(textSp);

		//alert("entro a la creacion de imagenes");
		var objA = document.createElement("a");
		var img = document.createElement("img");
		img.src = "../../resources/images/botones/chat.png";
		img.width = 20;
		img.height = 20;

		objA.setAttribute("id", "comentarios");
		objA.setAttribute("onclick", "popupComentario(" + segmentId.toString()
				+ ");");
		objA.appendChild(img);
		objTd.appendChild(objA);

		var textSp = document.createTextNode(" ");
		objTd.appendChild(textSp);

		objA = document.createElement("a");

		var img1 = document.createElement("img");
		img1.src = "../../resources/images/botones/agregar.png";
		img1.width = 20;
		img1.height = 20;
		// linkText = document.createTextNode("Productos Adicionales");
		// objA.appendChild(linkText);
		// objA.title = "my title text";
		// objA.href = "javascript:popupChristian();";
		// objA.onclick="";
		objA.setAttribute("id", "agregar");
		objA.setAttribute("onclick", "popUpAgregarArticulos("
				+ segmentId.toString() + ", &apos;" + segmentName.toString()
				+ "&apos;);");
		// objA.setAttribute( "onclick" , "openAditionalProdsWindow("+ segmentId
		// +" , \""+ segmentName +"\" );" );
		objA.appendChild(img1);
		objTd.appendChild(objA);

		objA = document.createElement("a");

		var img2 = document.createElement("img");
		img2.src = "../../resources/images/botones/adjuntar.png";
		img2.width = 20;
		img2.height = 20;
		// linkText = document.createTextNode("Productos Adicionales");
		// objA.appendChild(linkText);
		// objA.title = "my title text";
		// objA.href = "javascript:popupChristian();";
		// objA.onclick="";
		objA.setAttribute("id", "archivo");
		objA.setAttribute("onclick", "popupArchivo(" + segmentId.toString()
				+ ");");
		// objA.setAttribute( "onclick" , "openAditionalProdsWindow("+ segmentId
		// +" , \""+ segmentName +"\" );" );
		objA.appendChild(img2);
		objTd.appendChild(objA);
		
		objA=document.createElement("a");		
		 
		 var img3 = document.createElement("img");
		 img3.src = "../../resources/images/botones/resize.png";
		 img3.width = 20;
		 img3.height = 20;
		 objA.setAttribute("id", "resize");
		 objA.setAttribute("onclick", "resizeImage('"+idimgadd+"');");
        objA.appendChild(img3);
		 objTd.appendChild(objA);

		addImage(encodeURIComponent(obj.alt), encodeURIComponent(obj.name),
				segmentId, segmentName);
	};

	if (readOnlyVal == false) {
		loadComboProductos(hojaId);
	} else {
		document.getElementById('tr_cmb_prods').style.visibility = 'hidden';
		document.getElementById('tr_images').style.visibility = 'hidden';
		document.getElementById('tr_btns').style.visibility = 'hidden';
	}

	if (ejecucionVal) {
		document.getElementById('tr_cmb_prods').style.visibility = 'hidden';
		document.getElementById('tr_images').style.visibility = 'hidden';
		document.getElementById('tr_btns').style.visibility = 'hidden';
	}

}

function deleteItem(segId, artId) {
	var hojaId = document.getElementById("hoja_id").value;
	var url = "/seven/servlets/servletFolletos?command=delete_item&hoja_id="
			+ hojaId + "&seg_id=" + segId + "&item_id=" + artId;
	callAjaxReq(url, deleteItemResult);
}

function deleteItemResult() {

	if (xmlHttp.readyState != 4)
		return;
	var xmlObj = xmlHttp.responseXML;

	if (xmlObj == null) {
		alert("Error en la peticion ");
		return;
	}

	/*
	 * var objCode= xmlObj.getElementsByTagName('code')[0]; alert(objCode); if(
	 * objCode=="0" ){ alert("El art�culo se elimin") }
	 */

	var segId = xmlObj.getElementsByTagName("segment")[0].textContent;
	var segName = xmlObj.getElementsByTagName("segment_name")[0].textContent;
	var template = xmlObj.getElementsByTagName("template")[0].textContent;
	// alert(segId);
	var objGridInfo = xmlObj.getElementsByTagName("info_grid");
	// var string = (new
	// XMLSerializer()).serializeToString(objGridInfo[0].firstChild);

	if (segId != null) {

		// var objTd=document.getElementById("seg_"+ segId );
		var objTd = document.getElementById("seg_" + segId + "_" + segName
				+ "_" + template);
		if (objTd != null) {
			objTd.innerHTML = "";
		}
		if (objTd != null) {
			objTd.innerHTML = "";
		}
	}

	if (objGridInfo != null) {
		if (objGridInfo[0] != null) {
			document.getElementById("grid").innerHTML = objGridInfo[0].textContent;
		}

	}
	document.location.reload(true);
	// document.getElementById("grid").innerHTML = txtObj ;
}

function saveHoja() {
	var hojaId = document.getElementById("hoja_id").value;

	var url = "/seven/servlets/servletFolletos?command=save_hoja&hoja_id="
			+ hojaId;
	var numArts = parseInt(document.getElementById("num_arts").value);
	var segId;
	var artId;
	var precioPromocion;
	var plazo6;
	var plazo9;
	var plazo12;
	var plazo15;
	var plazo18;
	var plazo24;
	var unidades;
	var w_img;
	var h_img;
	for (var i = 0; i < numArts; i++) {
		segId = document.getElementById("seg_" + i).value;
		artId = document.getElementById("item_" + i).value;
		precioPromocion = document.getElementById("pricep_" + i).value;
		w_img = document.getElementById("img_" + segId).width;
		h_img = document.getElementById("img_" + segId).height;
		if (document.getElementById("plazo_6_" + i).checked) {
			plazo6 = "Y";
		} else {
			plazo6 = "";
		}

		if (document.getElementById("plazo_9_" + i).checked) {
			plazo9 = "Y";
		} else {
			plazo9 = "";
		}

		if (document.getElementById("plazo_12_" + i).checked) {
			plazo12 = "Y";
		} else {
			plazo12 = "";
		}

		if (document.getElementById("plazo_15_" + i).checked) {
			plazo15 = "Y";
		} else {
			plazo15 = "";
		}

		if (document.getElementById("plazo_18_" + i).checked) {
			plazo18 = "Y";
		} else {
			plazo18 = "";
		}

		if (document.getElementById("plazo_24_" + i).checked) {
			plazo24 = "Y";
		} else {
			plazo24 = "";
		}
		/*
		 * plazo9=document.getElementById("plazo_9_"+i).value;
		 * plazo12=document.getElementById("plazo_12_"+i).value;
		 * plazo15=document.getElementById("plazo_15_"+i).value;
		 * plazo18=document.getElementById("plazo_18_"+i).value;
		 * plazo24=document.getElementById("plazo_24_"+i).value;
		 */
		unidades = document.getElementById("unidades_" + i).value;

		url += "&seg_" + i + "=" + segId;
		url += "&item_" + i + "=" + artId;
		url += "&unidades_" + i + "=" + unidades;
		url += "&pricep_" + i + "=" + precioPromocion;
		url += "&plazo_6_" + i + "=" + plazo6;
		url += "&plazo_9_" + i + "=" + plazo9;
		url += "&plazo_12_" + i + "=" + plazo12;
		url += "&plazo_15_" + i + "=" + plazo15;
		url += "&plazo_18_" + i + "=" + plazo18;
		url += "&plazo_24_" + i + "=" + plazo24;
		url += "&w_img_" + i + "=" + w_img;
		url += "&h_img_" + i + "=" + h_img;
	}

	url += "&num_arts=" + numArts;
	alert(url);
	callAjaxReq(url, saveHojaResult);
}

function saveHojaResult() {
	if (xmlHttp.readyState != 4)
		return;
	var xmlObj = xmlHttp.responseXML;

	var objCode = xmlObj.getElementsByTagName('code')[0].textContent;
	if (objCode == "0") {
		mensajes("info", null, "La hoja se guardo correctamente ");
	}

	// document.getElementById("grid").innerHTML = txtObj ;
}

function addImage(idArt, idImg, segmentId, segName, principal) {

	var hojaId = document.getElementById("hoja_id").value;
	// alert("segmentname :"+ segName );
	var url = "/seven/servlets/servletFolletos?command=add_product&hoja_id="
			+ hojaId + "&art_id=" + idArt + "&img_id=" + idImg + "&segment_id="
			+ segmentId + "&principal=" + principal + "&segName=" + segName;
	// alert(url);
	callAjaxReq(url, addImageResult);
}

function addImageResult() {
	if (xmlHttp.readyState != 4)
		return;
	var txtObj = xmlHttp.responseText;
	document.getElementById("grid").innerHTML = txtObj;
}

function loadTemplateResultRO() {
	if (xmlHttp.readyState != 4)
		return;
	var xmlObj = xmlHttp.responseXML;

	if (xmlObj == null) {

		alert("Error en la peticion ");
		return;

	}

	// alert( xmlObj );
	var hashProds = null;
	var objSegInfo = xmlObj.getElementsByTagName('segment_info_0')[0];

	var objChild = null;
	var id = null;
	var segType = null;
	var segWidth = 0;
	var segHeight = 0;

	id = objSegInfo.getElementsByTagName('segment_id_0')[0].firstChild.data;
	segType = objSegInfo.getElementsByTagName('segment_type_0')[0].firstChild.data;
	segWidth = objSegInfo.getElementsByTagName('segment_width_0')[0].firstChild.data;
	segHeight = objSegInfo.getElementsByTagName('segment_height_0')[0].firstChild.data;

	var objSegment = new ObjSegment();
	objSegment.setSegmentId(id);
	objSegment.setTipo(segType);
	objSegment.setWidth(segWidth);
	objSegment.setHeight(segHeight);

	setSegmentChilds(objSegment, id, xmlObj);

	// si se pinta con productos
	var objProdInfo = xmlObj.getElementsByTagName('info_prods');
	// alert( "obj prod info :"+ objProdInfo );

	if (objProdInfo != null) {
		hashProds = setProducts(objProdInfo, xmlObj);
		// alert("hash table productos :" +hashProds );
	}

	var html = paintLayout(objSegment, hashProds);
	// alert( html )
	// alert( htmlLay)
	document.getElementById('htmMain').innerHTML = html;

}

function setProducts(objProducts, xmlObj) {

	var arrProdsInfo = xmlObj.getElementsByTagName('producto');
	// alert( "arrProdsInfo :"+ arrProdsInfo.length );

	var objChild = null;
	var objProdInfo = null;
	var id = null;
	var segId = null;
	var item = null;
	var descrip = null;
	var precio = 0.0;
	var hoja = 0;
	var version = 0;
	var id_img = 0;
	var marca = null;
	var nombre = null;
	var imgwidth = 120;
	var imgheight = 120;
	var path = null;
	var copia = 0;
	var comentarios = null;
	var tipo_img = null;
	var tipo_sku = null;
	var depto = null;
	var templateId = 0;
	var segmentId = 0;

	var hashProds = null;
	// alert( "numero de productos :"+ arrProdsInfo.length );
	for (var i = 0; i < arrProdsInfo.length; i++) {
		objProd = arrProdsInfo[i];

		item = objProd.getElementsByTagName('item')[0].firstChild.data;
		// alert("Item :"+ item );

		if (objProd.getElementsByTagName('descripcion')[0] != null) {
			if (objProd.getElementsByTagName('descripcion')[0].firstChild != null) {
				descrip = objProd.getElementsByTagName('descripcion')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('precio')[0] != null) {
			if (objProd.getElementsByTagName('precio')[0].firstChild != null) {
				precio = objProd.getElementsByTagName('precio')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('hoja')[0] != null) {
			if (objProd.getElementsByTagName('hoja')[0].firstChild != null) {
				hoja = objProd.getElementsByTagName('hoja')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('version')[0] != null) {
			if (objProd.getElementsByTagName('version')[0].firstChild) {
				version = objProd.getElementsByTagName('version')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('id_imagen')[0] != null) {
			if (objProd.getElementsByTagName('id_imagen')[0].firstChild) {
				id_img = objProd.getElementsByTagName('id_imagen')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('marca')[0] != null) {
			if (objProd.getElementsByTagName('marca')[0].firstChild) {
				marca = objProd.getElementsByTagName('marca')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('nombre')[0] != null) {
			if (objProd.getElementsByTagName('nombre')[0].firstChild != null) {
				nombre = objProd.getElementsByTagName('nombre')[0].firstChild.data;
			}
		}
		
		if (objProd.getElementsByTagName('imgwidth')[0] != null) {
			if (objProd.getElementsByTagName('imgwidth')[0].firstChild != null) {
				imgwidth = objProd.getElementsByTagName('imgwidth')[0].firstChild.data;
			}
		}
		
		if (objProd.getElementsByTagName('imgheight')[0] != null) {
			if (objProd.getElementsByTagName('imgheight')[0].firstChild != null) {
				imgheight = objProd.getElementsByTagName('imgheight')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('path')[0] != null) {
			if (objProd.getElementsByTagName('path')[0].firstChild != null) {
				path = objProd.getElementsByTagName('path')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('copia')[0] != null) {
			if (objProd.getElementsByTagName('copia')[0].firstChild != null) {
				copia = objProd.getElementsByTagName('copia')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('comentarios')[0] != null) {
			if (objProd.getElementsByTagName('comentarios')[0].firstChild != null) {
				comentarios = objProd.getElementsByTagName('comentarios')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('tipo_imagen')[0] != null) {
			if (objProd.getElementsByTagName('tipo_imagen')[0].firstChild != null) {
				tipo_img = objProd.getElementsByTagName('tipo_imagen')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('tipo_sku')[0] != null) {
			if (objProd.getElementsByTagName('tipo_sku')[0].firstChild != null) {
				tipo_sku = objProd.getElementsByTagName('tipo_sku')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('departamento')[0] != null) {
			if (objProd.getElementsByTagName('departamento')[0].firstChild != null) {
				depto = objProd.getElementsByTagName('departamento')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('template_id')[0] != null) {
			if (objProd.getElementsByTagName('template_id')[0].firstChild != null) {
				templateId = objProd.getElementsByTagName('template_id')[0].firstChild.data;
			}
		}

		if (objProd.getElementsByTagName('segment_id')[0] != null) {
			if (objProd.getElementsByTagName('segment_id')[0].firstChild != null) {
				segmentId = objProd.getElementsByTagName('segment_id')[0].firstChild.data;
			}
		}

		var objProduct = new ObjProduct();

		objProduct.setItem(item);
		objProduct.setDescripcion(descrip);
		objProduct.setPrecio(parseFloat(precio));
		objProduct.setHoja(parseInt(hoja));
		objProduct.setVersion(parseInt(version));
		objProduct.setIdImagen(id_img);
		objProduct.setMarca(marca);
		objProduct.setNombre(nombre);
		objProduct.setImgWidth(imgwidth);
		objProduct.setImgHeight(imgheight);
		objProduct.setPath(path);
		objProduct.setCopia(parseInt(copia));
		objProduct.setComentarios(comentarios);
		objProduct.setTipoSKU(tipo_sku);
		objProduct.setDepartamento(depto);
		objProduct.setIdTemplate(templateId);
		objProduct.setIdSegmento(segmentId);

		if (hashProds == null) {
			hashProds = new Hashtable();
		}

		// alert("putting segement :"+ objProduct.getIdSegmento() );
		// alert(objProduct.getIdTemplate()+ "-"+ objProduct.getIdSegmento() );
		hashProds.put(objProduct.getIdTemplate() + "-"
				+ objProduct.getIdSegmento(), objProduct);

	}
	return hashProds;

}

function setSegmentChilds(objSegment, segId, xmlObj) {
	if (xmlObj == null)
		return "";

	var arrSegsInfo = xmlObj.getElementsByTagName('segment_info_' + segId);

	var objChild = null;
	var objSegInfo = null;
	var id = null;
	var segType = null;
	var segWidth = 0;
	var segHeight = 0;
	var segName = null;
	var templateId = 0;
	for (var i = 0; i < arrSegsInfo.length; i++) {
		objSegInfo = arrSegsInfo[i];
		id = objSegInfo.getElementsByTagName('segment_id_' + segId)[0].firstChild.data;
		segType = objSegInfo.getElementsByTagName('segment_type_' + segId)[0].firstChild.data;
		segWidth = objSegInfo.getElementsByTagName('segment_width_' + segId)[0].firstChild.data;
		segHeight = objSegInfo.getElementsByTagName('segment_height_' + segId)[0].firstChild.data;
		// templateId= objSegInfo.getElementsByTagName('segment_template_id_'+
		// segId )[0].firstChild.data;
		// segName =objSegInfo.getElementsByTagName('segment_name_'+ segId
		// )[0].firstChild.data;

		templateId = "";
		if (objSegInfo.getElementsByTagName('segment_template_id_' + segId)[0] != null) {
			if (objSegInfo.getElementsByTagName('segment_template_id_' + segId)[0].firstChild != null) {
				templateId = objSegInfo
						.getElementsByTagName('segment_template_id_' + segId)[0].firstChild.data;
			}
		}
		// alert("template id :"+templateId);

		segName = "";
		if ("N" == segType) {
			if (objSegInfo.getElementsByTagName('segment_name_' + segId)[0].firstChild != null) {
				segName = objSegInfo.getElementsByTagName('segment_name_'
						+ segId)[0].firstChild.data;
			}
		}

		objChild = new ObjSegment();
		objChild.setSegmentId(id);
		objChild.setTipo(segType);
		objChild.setWidth(segWidth);
		objChild.setHeight(segHeight);
		objChild.setSegmentName(segName);
		objChild.setTemplateId(templateId);
		objSegment.addChild(objChild);
		setSegmentChilds(objChild, id, objSegInfo);

	}

	return objSegment;

}

function generarCopia() {
	var hojaId = document.getElementById("hoja_id").value;
	var url = "/seven/faces/servlets/servletFolletos?command=gen_copia&hoja_id="
			+ hojaId;
	callAjaxReq(url, saveHojaResult);
}

function eliminarCopia() {
	var hojaId = document.getElementById("hoja_id").value;
	var url = "/seven/faces/servlets/servletFolletos?command=del_copia&hoja_id="
			+ hojaId;
	callAjaxReq(url, saveHojaResult);
}

function popupComentario(segmentId) {
	var hojaId = document.getElementById("hoja_id").value;
	var ancho = 600;
	var alto = 600;
	// dialogs("Agregar
	// Comentario",'../pages/ArqAgregarComentario.xhtml?id_hoja='+hojaId+'&segment_id='+segmentId+'&comentario=');
	newwindow = window
			.showModalDialog(
					'../pages/ArqAgregarComentario.xhtml?id_hoja=' + hojaId
							+ '&segment_id=' + segmentId + '&comentario=',
					"Agregar Comentario",
					'dialogWidth:'
							+ ancho
							+ 'px; dialogHeight:'
							+ alto
							+ 'px;center:1;resizable:0;status:0;scrollbars:0;menubar:0;titlebar:0;toolbar:0;');
	if (window.focus) {
		newwindow.focus();
	}
	return false;
}

function popupArchivo(segmentId) {
	var hojaId = document.getElementById("hoja_id").value;
	var ancho = 700;
	var alto = 700;

	newwindow = window
			.showModalDialog(
					'../pages/ArqAgregarArchivo.xhtml?id_hoja=' + hojaId
							+ '&segment_id=' + segmentId + '&archivo=.',
					"Agregar Archivo",
					'dialogWidth:'
							+ ancho
							+ 'px; dialogHeight:'
							+ alto
							+ 'px;center:1;resizable:0;status:0;scrollbars:0;menubar:0;titlebar:0;toolbar:0;');
	if (window.focus) {
		newwindow.focus();
	}
	return false;
}

function cambiarTemplate() {
	enviarSelTemp();

	var hojaId = document.getElementById("hoja_id").value;
	var url = "/seven/faces/servlets/servletFolletos?command=cambiar_template&hoja_id="
			+ hojaId;
	window.location = url;
	// callAjaxReq( url , null );
}

function enviarSelTemp() {
	var hojaId = document.getElementById("hoja_id").value;
	var url = "/seven/faces/pages/folletos/selectTemplate.jsp?cambio_temp=Y&hoja_id="
			+ hojaId;
	window.location = url;
}

function popUpAgregarArticulos(segmentId, segmentName) {
	var ancho = 500;
	var alto = 400;
	var hoja_Id = document.getElementById("hoja_id").value;

	newwindow = window
			.showModalDialog(
					'../pages/ArqPoPupAgregarProductos.xhtml?hoja_id='
							+ hoja_Id + '&idSegmento=' + segmentId
							+ '&segname=' + segmentName,
					"Agregar Productos",
					'dialogWidth:'
							+ ancho
							+ 'px; dialogHeight:'
							+ alto
							+ 'px;center:1;resizable:0;status:0;scrollbars:0;menubar:0;titlebar:0;toolbar:0;');
	if (window.focus) {
		newwindow.focus();
	}
	return false;
}

function cerrarHoja() {
	var hoja_Id = document.getElementById("hoja_id").value;
	var url = "/seven/servlets/servletFolletos?command=cerrarHoja&ejecucion=true&hoja_id="
			+ hoja_Id;
	callAjaxReq(url, null);
	document.body.innerHTML = " ";
	mensajes('info', null, 'La hoja ha sido cerrada satisfactoriamente');
}

function eliminarHoja() {
	var hoja_Id = document.getElementById("hoja_id").value;
	var url = "/seven/servlets/servletFolletos?command=eliminarHoja&hoja_id="
			+ hoja_Id;
	callAjaxReq(url, null);
	var mensaje_conf = document.getElementById("mensajeConfirmacion").value;
	var tipoMensaje = document.getElementById("tipoMensaje").value;
	mensajes(tipoMensaje, null, mensaje_conf);
}

function resizeImage(id_imagen){
	imagen = document.getElementById(id_imagen);
	
	var ancho = 300;
	var alto = 300;

	newwindow = window.showModalDialog('../pages/ArqResizeImgHoja.xhtml?id_imagen='+id_imagen+'&ancho='+imagen.width+'&alto='+imagen.height,"Editar Imagen",'dialogWidth:'+ancho+'px; dialogHeight:'+alto+'px;center:1;resizable:0;status:0;scrollbars:0;menubar:0;titlebar:0;toolbar:0;');
	if (window.focus) {
		newwindow.focus();
	}
	return false;
}

function selectorDeMetodo(opc){
	switch(opc){
	case 0: cerrarHoja();break;
	case 1:	eliminarHoja();break;
	case 2:	generarCopia();break;
	case 3:	eliminarCopia();break;
	case 4: cambiarTemplate();break; 
	}	
}

function selectorDeMetodo(opc) {
	switch (opc) {
	case 0:
		cerrarHoja();
		break;
	case 1:
		eliminarHoja();
		break;
	case 2:
		generarCopia();
		break;
	case 3:
		eliminarCopia();
		break;
	case 4:
		cambiarTemplate();
		break;
	}
}

function mensajes(tipo, detail, summary) {
	var form = document.createElement("div");
	var mensajeDiv = document.createElement("div");
	var iconClose = document.createElement("a");
	var imageIconClose = document.createElement("span");
	var icon = document.createElement("span");
	var ul = document.createElement("ul");
	var li = document.createElement("li");
	var messageSumary = document.createElement("span");
	var messageDetail = document.createElement("span");

	form.setAttribute("id", "formMensaje");
	form.setAttribute("class", "ui-messages ui-widget");
	form.setAttribute("data-detail", "data-detail");

	mensajeDiv.setAttribute("class", "ui-messages-" + tipo + " ui-corner-all");

	iconClose.setAttribute("class", "ui-messages-close");
	iconClose.setAttribute("href", "#");
	iconClose.setAttribute("onclick",
			"$(this).parent().slideUp();return false;");
	imageIconClose.setAttribute("class", "ui-icon ui-icon-close");
	iconClose.appendChild(imageIconClose);

	icon.setAttribute("class", "ui-messages-" + tipo + "-icon");

	messageSumary.setAttribute("id", "messageSumary");
	messageSumary.setAttribute("class", "ui-messages-" + tipo + "-summary");
	messageSumary.innerHTML = summary;
	messageDetail.setAttribute("id", "messageDetail");
	messageDetail.setAttribute("class", "ui-messages-" + tipo + "-summary");
	messageDetail.innerHTML = detail;
	li.appendChild(messageSumary);
	li.appendChild(messageDetail);
	ul.appendChild(li);

	mensajeDiv.appendChild(iconClose);
	mensajeDiv.appendChild(icon);
	mensajeDiv.appendChild(ul);
	form.appendChild(mensajeDiv);

	document.body.insertBefore(form, document.body.firstChild);
}

$(document).ready(function() {
	var hojaId = document.getElementById("hoja_id").value;
	findPrevio(hojaId);
	// setTimeout("loadComboProductos(<%=hojaId%>);" , 1000);
	// setTimeout("loadComboProductos(<%=hojaId%>);" , 1000);
});

$(function() {
	var opc = 0;
	var p = document.getElementById("mensaje_Div");

	$("#eliminar").on("click", function() {
		$('#confirmacion').dialog("open");
	});

	$("#dialog_Si").on("click", function() {
		selectorDeMetodo(opc);
		$('#confirmacion').dialog("close");
	});

	$("#dialog_No").on("click", function() {
		$('#confirmacion').dialog("close");
	});

	$("#cerrarHoja")
			.on(
					"click",
					function() {
						opc = 0;
						p.innerHTML = "<span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>�Desea finalizar la edici�n de la hoja?";
						$('#confirmacion').dialog("option", "title",
								"Cerrar Hoja");
						$('#confirmacion').dialog("open");
					});

	$("#eliminarHoja")
			.on(
					"click",
					function() {
						opc = 1;
						p.innerHTML = "<span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>�Desea eliminar la hoja actual?";
						$('#confirmacion').dialog("option", "title",
								"Eliminar Hoja");
						$('#confirmacion').dialog("open");
					});

	$("#generarCopia")
			.on(
					"click",
					function() {
						opc = 2;
						p.innerHTML = "<span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>�Desea generar las copias por Sistemas de Venta?";
						$('#confirmacion').dialog("option", "title",
								"Generar Copias");
						$('#confirmacion').dialog("open");
					});

	$("#eliminarCopia")
			.on(
					"click",
					function() {
						opc = 3;
						p.innerHTML = "<span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>�Desea eliminar la copia actual?";
						$('#confirmacion').dialog("option", "title",
								"Eliminar Copia");
						$('#confirmacion').dialog("open");
					});

	$("#cambiarTemplate").on("click", function() {
		$('#confirmacion').dialog("option", "title", "Cambiar Template");
		opc = 4;
		selectorDeMetodo(opc);
		// p.innerHTML = "<span class='ui-icon ui-icon-alert' style='float:left;
		// margin:0 7px 20px 0;'></span>�Esta seguro de querer cambiar el
		// Template?";
		// $('#confirmacion').dialog("open");
	});

	$("#confirmacion").dialog({

		autoOpen : false,
		height : 130,
		width : 450,
		resizable : false,
		modal : true,
		dialogClass : "foo"
	});

});

