<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
 String idFolleto=request.getParameter("idFolleto");

%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">

function displayPDF(){
	var cmbSistVenta=document.getElementById("idSistemaVenta");
	var sistVenta=cmbSistVenta.value;
	var url="/seven/faces/servlets/servletFolletos?command=print_pdf&idFolleto=<%=idFolleto%>&idSistemaVenta="+ sistVenta;	
	window.location=url;
	
}


</script>
</head>
<body>

Imprimir Folleto en PDF



<TABLE>
<TR><TD>
<select  id="idSistemaVenta" name='idSistemaVenta'  >
<option value='1' >CC_GRAL</option>
<option value='2' >CC_FRONTERA</option>
<option value='3' >PROMOBIEN_GRAL</option>
<option value='4' >PROMOBIEN_MTY</option>
<option value='5' >CAMBACEO</option>


</select>
</TD>
</TR>
<TR>
<TD><input type='button' value='Imprimir Folleto PDF'  onclick='displayPDF();' ></TD>
</TR>

</TABLE>

</body>
</html>