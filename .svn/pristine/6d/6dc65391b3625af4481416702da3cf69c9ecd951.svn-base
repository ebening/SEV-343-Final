
<%@page import="com.adinfi.defines.GlobalDefines"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../../resources/css/style.css"
	type="text/css" media="screen" />


<%
	String path = request.getContextPath();
	String tipoMensaje = (String) request.getSession().getAttribute(
			GlobalDefines.SESS_CURR_TYPE_MSJ_CONF);
	String mensajeConfirmacion = (String) request.getSession()
			.getAttribute(GlobalDefines.SESS_CURR_MSJ_CONF);
%>


<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/adMaster.buttons.css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/FontAndColor.css">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resources/css/ModuloArquitectura.css">
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
<script type="text/javascript" src="../../resources/js/header.js"></script>
<script type="text/javascript"
	src="../../resources/js/redips-drag-min.js"></script>
<script type="text/javascript" src="../../resources/js/script.js"></script>
<script type="text/javascript" src="../../resources/js/ajax.js"></script>
<script type="text/javascript" src="../../resources/js/hash_table.js"></script>
<script type="text/javascript" src="../../resources/js/hoja.js"></script>
<script type="text/javascript" src="../../resources/js/Validaciones.js"></script>


<%
	String hojaId = request.getParameter("hoja_id");
	String isLink = request.getParameter("islink");
	System.out.println(isLink);
%>



</head>
<body>
	<table>
		<tr>
			<td>
				<div id="drag">
					<table style="border-collapse: 2px;">
						<tr>
							<td valign='top' >
								<table>
									<tr id='tr_cmb_prods'>
										<td class="fuenteYColor">SKU</td>
										<td class="fuenteYColor"><select id='cmbProductos' class='select-style '
											onchange='javascript:loadImages(this.value);'>
												<option value='0'>Seleccione el SKU</option>
										</select></td>
									</tr>
									<tr id='tr_images'>
										<td>

											<div id="images"
												style="vertical-align: top; text-align: center;"></div>
										</td>
									</tr>
								</table>

							</td>
							<td valign='top' align="right">
								<table class="botonesAlign">
									<thead>
										<tr id='tr_btns'>
											<th align="right"><a id="grabarHoja"
												onclick='saveHoja();'><img title="Grabar Hoja"
													src="../../resources/images/botones/guardar.png" /></a> &nbsp;
												<a id="cerrarHoja"><img title="Cerrar Hoja"
													src="../../resources/images/botones/salir.png" /></a> &nbsp; <a
												id='eliminarHoja'><img title="Eliminar Hoja"
													src="../../resources/images/botones/eliminar_hoja.png" /></a>
												&nbsp; <a id='generarCopia'><img title="Generar Copias"
													src="../../resources/images/botones/generar_copias.png" /></a>
												&nbsp; <a id='eliminarCopia'><img title="Eliminar Copia"
													src="../../resources/images/botones/eliminar.png" /></a>
												&nbsp; <a id='cambiarTemplate'><img
													title="Cambiar Template"
													src="../../resources/images/botones/perioricidad.png" /></a></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan='5'>
												<!-- inicio tabla cuadricula -->
												<table  id='table1' style="font-family: Arial">
													<tr>
														<TD valign="top" align="left" colspan="3" id='htmMain' class="portlet-title">
															<!--  	<DIV id="previo" style="overflow:scroll; overflow-y:; overflow-x:auto; height: 600px; border: 1px solid silver;"></DIV> -->
														</TD>
													</tr>
												</table> <!-- fin tabla cuadricula -->
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>

		</tr>
		<!-- fin working -->

		<tr>
			<td valign='top'>
				<div id="grid"></div>
			</td>
		</tr>

	</table>


	<input type='hidden' id='tipoMensaje' value='<%=tipoMensaje%>'>
	<input type='hidden' id='mensajeConfirmacion' value='<%=mensajeConfirmacion%>'>
	<input type='hidden' id='hoja_id' value='<%=hojaId%>'>
	<div id="confirmacion" class="foo">
		<p id="mensaje_Div"></p>
		<div style="padding-right: 2px">
			<button id="dialog_Si" class="common-button" style="float: right;">Si</button>
			<button id="dialog_No" class="common-button" style="float: right;">No</button>
		</div>
	</div>
</body>
</html>


