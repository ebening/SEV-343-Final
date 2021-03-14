/**
 * 
 */
package com.adinfi.seven.presentation.views.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.context.ExternalContext;

import com.adinfi.seven.persistence.dto.CabeceraReporteDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioFiltroDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioSkuDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaFiltroDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaSkuDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author OMAR
 *
 */
public final class ExportPDFUtil {
	
	
	public static void writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
	    try {
	        externalContext.responseReset();
	        externalContext.setResponseContentType("application/pdf");
	        externalContext.setResponseHeader("Expires", "0");
	        externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        externalContext.setResponseHeader("Pragma", "public");
	        externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
	        externalContext.setResponseContentLength(baos.size());
	        OutputStream out = externalContext.getResponseOutputStream();
	        baos.writeTo(out);
	        externalContext.responseFlushBuffer();
	    } catch (Exception e) {
	        //e.printStackTrace();
	    }
	}
	
	
	public static void addHeaderPage(Document document,CabeceraReporteDTO cabecera){
		    Paragraph preface	 		= new Paragraph();
		    BaseFont helvetica			= null;
		    
		    try {
		        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
		    } catch (Exception e) {
		        //font exception
		    }
		    
		    Font font 	= new Font(helvetica, Constants.FONT_SIZE_HEADER, Font.NORMAL);
		    Font bold 	= new Font(helvetica, Constants.FONT_SIZE_HEADER, Font.BOLD);

		    preface.add(new Paragraph("Folio: "+ cabecera.getFolio(), font));
		    preface.add(new Paragraph("Campaña: "+ cabecera.getNombreCampania(), font));
		    preface.add(new Paragraph("Vigencia: "+ cabecera.getVigencia() , font));
		    
		    addEmptyLine(preface, 1);

		    try {
				document.add(preface);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	
	
	
	public static PdfPTable exportInventario(List<ReporteInventarioSkuDTO> infoReporteInventarioSku) {

	    PdfPTable pdfTable	 				= new PdfPTable(Constants.NUMERO_COLUMNAS_10);
	    BaseFont helvetica 					= null;
	    pdfTable.setWidthPercentage(100);	   
	    
	    try {
	    	 pdfTable.setWidths(new int[]{1, 1, 1,1,2,1,1,1,1,1});
	        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
	    } catch (Exception e) {
	        //font exception
	    }
	    Font font 		= new Font(helvetica, Constants.FONT_SIZE, Font.NORMAL);
	    Font cabecera 	= new Font(helvetica, Constants.FONT_SIZE, Font.BOLD);
	    
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_DISTRITO, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_ZONA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_SUCURSAL, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_SKU, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_DESCRIPCION, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_CATEGORIA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_SUBCATEGORIA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_TIENDA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_CEDIS, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_TRANSITO, cabecera));
	    
	    for(ReporteInventarioSkuDTO item :infoReporteInventarioSku){
	    	 //pdfTable.addCell(new Paragraph('any_string_field', font));
	        pdfTable.addCell(new Paragraph(item.getDistrito(), font));
	        pdfTable.addCell(new Paragraph(item.getZonaNombre(), font));
	        pdfTable.addCell(new Paragraph(item.getSucursal(), font));
	        pdfTable.addCell(new Paragraph(item.getSku(), font));
	        pdfTable.addCell(new Paragraph(item.getDescripcion(), font));
	        pdfTable.addCell(new Paragraph(item.getCategoria(), font));
	        pdfTable.addCell(new Paragraph(item.getSubcategoria(), font));
	        pdfTable.addCell(new Paragraph(item.getInventarioTiendaFormato(), font));
	        pdfTable.addCell(new Paragraph(item.getInventarioCedisFormato(), font));
	        pdfTable.addCell(new Paragraph(item.getInventarioTransitoFormato(), font));
	    	
	    }

	    return pdfTable;
	}
	
	
	
	
	public static  PdfPTable exportInventarioFiltro(List<ReporteInventarioFiltroDTO> infoReporteInventarioFiltro,String filtro) {

	    PdfPTable pdfTable	 				= new PdfPTable(Constants.NUMERO_COLUMNAS_4);
	    BaseFont helvetica 					= null;
	    pdfTable.setWidthPercentage(100);
	    
	    try {
	        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
	    } catch (Exception e) {
	        //font exception
	    }
	    Font font 		= new Font(helvetica, Constants.FONT_SIZE, Font.NORMAL);
	    Font cabecera 	= new Font(helvetica, Constants.FONT_SIZE, Font.BOLD);
	    
	    pdfTable.addCell(new Paragraph(filtro, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_TIENDA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_CEDIS, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_TRANSITO, cabecera));
	    
	    for(ReporteInventarioFiltroDTO item :infoReporteInventarioFiltro){
	    	 //pdfTable.addCell(new Paragraph('any_string_field', font));
	        pdfTable.addCell(new Paragraph(item.getFiltro(), font));
	        pdfTable.addCell(new Paragraph(item.getInventarioTiendaFormato(), font));
	        pdfTable.addCell(new Paragraph(item.getInventarioCedisFormato(), font));
	        pdfTable.addCell(new Paragraph(item.getInventarioTransitoFormato(), font));
	    	
	    }

	    return pdfTable;
	}
	
	
	
	public static PdfPTable exportVenta(List<ReporteVentaSkuDTO> infoReporteVentaSku) {	    
		
	    PdfPTable pdfTable	 				= new PdfPTable(Constants.NUMERO_COLUMNAS_9);
	    BaseFont helvetica 					= null;
	    pdfTable.setWidthPercentage(100);
	    
	    try {	    	 
	    	pdfTable.setWidths(new int[]{1, 1, 1,1,2,1,1,1,1});
	        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
	    } catch (Exception e) {
	        //font exception
	    }
	    Font font 		= new Font(helvetica, Constants.FONT_SIZE, Font.NORMAL);
	    Font cabecera 	= new Font(helvetica, Constants.FONT_SIZE, Font.BOLD);
	    
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_DISTRITO, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_ZONA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_SUCURSAL, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_SKU, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_DESCRIPCION, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_CATEGORIA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_SUBCATEGORIA, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_VENTA_UNIDADES, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_VENTA_PESOS, cabecera));
	    
	    
	    for(ReporteVentaSkuDTO item :infoReporteVentaSku){
	    	 //pdfTable.addCell(new Paragraph('any_string_field', font));
	        pdfTable.addCell(new Paragraph(item.getDistrito(), font));
	        pdfTable.addCell(new Paragraph(item.getZonaNombre(), font));
	        pdfTable.addCell(new Paragraph(item.getSucursal(), font));
	        pdfTable.addCell(new Paragraph(item.getSku(), font));
	        pdfTable.addCell(new Paragraph(item.getDescripcion(), font));
	        pdfTable.addCell(new Paragraph(item.getCategoria(), font));
	        pdfTable.addCell(new Paragraph(item.getSubcategoria(), font));
	        pdfTable.addCell(new Paragraph(item.getVentaUnidadesFormato(), font));
	        pdfTable.addCell(new Paragraph(item.getVentaPesosFormato(), font));
	    	
	    }

	    return pdfTable;
	}
	

	public static PdfPTable exportVentaFiltro(List<ReporteVentaFiltroDTO> infoReporteVentaFiltro,String filtro) {	    
		
	    PdfPTable pdfTable	 				= new PdfPTable(Constants.NUMERO_COLUMNAS_3);
	    BaseFont helvetica 					= null;
	    pdfTable.setWidthPercentage(100);
	    
	    try {
	
	        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
	    } catch (Exception e) {
	        //font exception
	    }
	    Font font 		= new Font(helvetica, Constants.FONT_SIZE, Font.NORMAL);
	    Font cabecera 	= new Font(helvetica, Constants.FONT_SIZE, Font.BOLD);
	    
	    pdfTable.addCell(new Paragraph(filtro, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_VENTA_UNIDADES, cabecera));
	    pdfTable.addCell(new Paragraph(Constants.CABECERA_VENTA_PESOS, cabecera));
	    
	    
	    for(ReporteVentaFiltroDTO item :infoReporteVentaFiltro){
	    	 //pdfTable.addCell(new Paragraph('any_string_field', font));
	        pdfTable.addCell(new Paragraph(item.getFiltro(), font));
	        pdfTable.addCell(new Paragraph(item.getVentaUnidadesFormato(), font));
	        pdfTable.addCell(new Paragraph(item.getVentaPesosFormato(), font));
	    	
	    }

	    return pdfTable;
	}

}
