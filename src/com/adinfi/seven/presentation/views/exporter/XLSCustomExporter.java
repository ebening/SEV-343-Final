package com.adinfi.seven.presentation.views.exporter;

import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.presentation.views.MBActividad;
import com.adinfi.seven.presentation.views.util.Util;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.ExcelExporter;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jdominguez on 9/19/16.
 */
public class XLSCustomExporter extends ExcelExporter {

    private final String[] HEADERS = {"Mecanica", "Actividad", "Fecha Inicio", "Vencimiento", "Fecha Fin", "Estatus", "Responsable"};
    private FacesContext context;
    private Sheet sheet;
    private int rowIndex;
    private HSSFWorkbook wb;

    public void export(FacesContext context, DataTable table, String filename, boolean pageOnly, boolean selectionOnly, String encodingType, MethodExpression preProcessor, MethodExpression postProcessor) throws IOException {
        this.context = context;
        wb = new HSSFWorkbook();
        sheet = wb.createSheet();
        if(preProcessor != null) {
            preProcessor.invoke(context.getELContext(), new Object[]{wb});
        }

        //this.addColumnFacets(table, sheet, ColumnType.HEADER);
        if(pageOnly) {
            this.exportPageOnly(context, table, sheet);
        } else if(selectionOnly) {
            this.exportSelectionOnly(context, table, sheet);
        } else {
            rowIndex = 2;
            List<MBActividad.MecanicasActivities> mas = (List<MBActividad.MecanicasActivities>) table.getValue();
            for (MBActividad.MecanicasActivities mbama : mas){
                exportTable(mbama);
                rowIndex++;
            }
           // this.exportAll(context, table, sheet);
        }

        if(table.hasFooterColumn()) {
            this.addColumnFacets(table, sheet, ColumnType.FOOTER);
        }

        table.setRowIndex(-1);
        if(postProcessor != null) {
            postProcessor.invoke(context.getELContext(), new Object[]{wb});
        }

        this.writeExcelToResponse(context.getExternalContext(), wb, filename);
    }

    private void exportTable (MBActividad.MecanicasActivities mbma){
        Row row = sheet.createRow(rowIndex);
        applyAutoSize();
        int columnIndex = 0;
        for (String t : HEADERS){
            Cell cell = row.createCell(columnIndex);
            CellUtil.setAlignment(cell, wb, CellStyle.ALIGN_CENTER);
            cell.setCellValue(t);
            columnIndex++;
        }
        rowIndex++;
        List<TblActividad> acts = mbma.getActividadList();
        for (TblActividad actividad : acts){
            row = sheet.createRow(rowIndex);
            applyAutoSize();
            for (int i = 0; i < HEADERS.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(getCellValueFromAct(actividad, i));
            }
            rowIndex++;
        }
    }

    private void applyAutoSize(){
        for (int i = 0; i < 7; i++){
            sheet.autoSizeColumn(i);
        }
    }

    private String getCellValueFromAct(TblActividad actividad, int index){
        switch (index){
            case 0: return actividad.getTblMecanica().getNombreMecanica();
            case 1: return actividad.getDescripcion();
            case 2: return Util.fechaFormatComplete(actividad.getVigenciaInicio());
            case 3: return Util.fechaFormatComplete(actividad.getVigenciaFinal());
            case 4: return actividad.getFechaFin() == null ? "" : Util.fechaFormatComplete(actividad.getFechaFin());
            case 5: return actividad.getCatEstatus().getNombre();
            case 6: return actividad.getCatUsuariosByIdresponsable().getNombre() + " " +
                    actividad.getCatUsuariosByIdresponsable().getPlastName();
            default: return "";
        }
    }
}
