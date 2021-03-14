<!DOCTYPE html>
<html>
<head>


<%
	String path = request.getContextPath();
%>



<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/adMaster.buttons.css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/FontAndColor.css">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/mensajes.css">
<link rel="stylesheet" href="../../resources/css/style.css"
	type="text/css" media="screen" />
	
	<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/ModuloArquitectura.css">
<script type="text/javascript"
	src="<%=path%>/resources/js/adMasterButton.js"></script>
<script type="text/javascript" src="../../resources/js/header.js"></script>
<script type="text/javascript"
	src="../../resources/js/redips-drag-min.js"></script>
<script type="text/javascript" src="../../resources/js/script.js"></script>
<script type="text/javascript" src="../../resources/js/ajax.js"></script>
<script type="text/javascript" src="../../resources/js/hash_table.js"></script>
<script type="text/javascript" src="../../resources/js/hoja.js"></script>
<link type="text/css" rel="stylesheet"
	href="../../resources/css/mensajes.css">
<link type="text/css" rel="stylesheet"
	href="../../resources/css/css/smoothness/jquery-ui-1.10.4.custom.css">
<link type="text/css" rel="stylesheet"
	href="../../resources/css/css/smoothness/jquery-ui-1.10.4.custom.min.css">
<script type="text/javascript"
	src="../../resources/js/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="../../resources/js/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript"
	src="../../resources/js/js/jquery-ui-1.10.4.custom.min.js"></script>
<script>
	function popupChristian() {
		var ancho = 300;
		var alto = 400;
		posicion_x = (screen.width / 2) - (ancho / 2);
		posicion_y = (screen.height / 2) - (alto / 2);
		newwindow = window.open('../ArqPoPupAgregarProductos.xhtml');
		if (window.focus) {
			newwindow.focus();
		}
		return false;
	}
</script>
</head>
<body onload="mensajes('error', 'detail', 'summary')">
</body>
</html>