package com.adinfi.seven.presentation.views.exporter;

import com.adinfi.seven.presentation.views.util.Util;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.PDFExporter;


import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by jdominguez on 9/19/16.
 */
public class PDFCustomExporter extends PDFExporter {

    private Document e;
    private final String[] columnHeaders = {"Mecanica", "Actividad", "Fecha Inicio", "Vencimiento", "Fecha Fin", "Estatus", "Responsable"};
    private final Font LETRA_TITULO = FontFactory.getFont("Times", "UTF-8", 10.0F, 1);
    private final Font LETRA_VALUE = FontFactory.getFont("Times", "UTF-8", 8.0F, 0);

    public PDFCustomExporter() {
    }

    @Override
    public void export(FacesContext context, DataTable table, String filename, boolean pageOnly, boolean selectionOnly, String encodingType, MethodExpression preProcessor, MethodExpression postProcessor) throws IOException {
        try {
            e = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(e, baos);
            if(preProcessor != null) {
                preProcessor.invoke(context.getELContext(), new Object[]{e});
            }

            if(!e.isOpen()) {
                e.open();
            }

            e.add(this.exportPDFTable(context, table, pageOnly, selectionOnly, encodingType));
            if(postProcessor != null) {
                postProcessor.invoke(context.getELContext(), new Object[]{e});
            }

            e.close();
            this.writePDFToResponse(context.getExternalContext(), baos, filename);
        } catch (com.lowagie.text.DocumentException var11) {
            throw new IOException(var11.getMessage());
        }
    }

    @Override
    protected void addColumnFacets(DataTable table, PdfPTable pdfTable, ColumnType columnType) {
        Iterator i$ = table.getColumns().iterator();
        int i = 0;
        while(i$.hasNext()) {
            UIColumn col = (UIColumn)i$.next();
            if(col.isRendered()) {
                if(col instanceof DynamicColumn) {
                    ((DynamicColumn)col).applyModel();
                }

                if(col.isExportable()) {
                    if (columnType.facet() == "header"){
                        if (table.getColumns().size() == 1){
                            pdfTable.addCell(new Paragraph("Reporte Generado el dia " + Util.fechaFormatComplete(new Date()), LETRA_TITULO ));
                        }else{
                            pdfTable.addCell(new Paragraph(columnHeaders[i], LETRA_TITULO));
                            i++;
                        }
                    }
                   // this.addColumnValue(pdfTable, col.getFacet(columnType.facet()), FontFactory.getFont("Times", "UTF-8", 12.0F, 1));
                }
            }
        }

    }

    @Override
    protected String exportValue(FacesContext context, UIComponent component){
        if (component instanceof DataTable){
            DataTable tab = (DataTable) component;
            try {
                e.add(exportPDFTable(context, tab, false, false, "UTF-8"));
            }catch (DocumentException ex){
                System.out.println(ex);
            }
            return "";
        }else{
            return super.exportValue(context, component);
        }
    }

    protected void addColumnValue(PdfPTable pdfTable, UIComponent component, Font font) {
        String value = component == null?"":this.exportValue(FacesContext.getCurrentInstance(), component);
        pdfTable.addCell(new Paragraph(value, LETRA_VALUE));
    }

    protected void addColumnValue(PdfPTable pdfTable, java.util.List<UIComponent> components, Font font) {
        StringBuilder builder = new StringBuilder();
        FacesContext context = FacesContext.getCurrentInstance();
        Iterator i$ = components.iterator();

        while(i$.hasNext()) {
            UIComponent component = (UIComponent)i$.next();
            if(component.isRendered()) {
                String value = this.exportValue(context, component);
                if(value != null) {
                    builder.append(value);
                }
            }
        }

        pdfTable.addCell(new Paragraph(builder.toString(), LETRA_VALUE));
    }
}
