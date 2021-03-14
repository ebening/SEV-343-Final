package com.adinfi.seven.presentation.views.administracion;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.ColumnModel;
import com.adinfi.seven.presentation.views.GenericControl;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.Utileria;
import edu.emory.mathcs.backport.java.util.Collections;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by christian on 26/01/2015.
 */
public class CatalogoDinamico implements Serializable {
   
    private final ServiceDynamicCatalogs serviceDynamicCatalogs;
    private List<CatViewControls> viewList;
    private List<CatAttrs> catAttrList;
    private DynaFormModel model;
    private List<Map<String, String>> responseArray;
    private Map<String, String> rowSelect;
    private List<ColumnModel> columns = new ArrayList<>();
    private List<CatRegs> regs;
    private String rowKeyValue;
    private String catName = "CAT_USER";
    private boolean isUpdate = false;
    private boolean catalogChange = false;
    private List<AttrSearch> lstSearchAttrs = new ArrayList<>();
    private String catalogNametitle = "CATALOGO DE USUARIO";
    private Map<String, String> rowDelete;


    public CatalogoDinamico(ServiceDynamicCatalogs serviceDynamicCatalogs) {
        this.serviceDynamicCatalogs = serviceDynamicCatalogs;
    }

    /**
     * Llenado de los controles para realizar modificaciones
     *
     * @param id del registro del grid a modificar
     *
     */
    public void fillUpdate(int id) {
        try {
            Map<String, String> rowUpdate = null;
            if (responseArray != null && id != -1) {
                rowUpdate = responseArray.get(id);
            }
            model = new DynaFormModel();
            Catalogs catalog = serviceDynamicCatalogs.getCatalogByName(catName);
            if (catalog != null) {
                checkCatalogReadOnly(catalog);
                viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
                catAttrList = serviceDynamicCatalogs.getCatalogAttributes(catalog.getCatId());
                Collections.sort(viewList);
                for (CatViewControls viewControl : viewList) {
                    DynaFormRow row = model.createRegularRow();
                    DynaFormLabel etiqRen = row.addLabel(viewControl.getLabel());
                    Attribs atrrib = serviceDynamicCatalogs.getAttribId(viewControl.getAttribId());
                    CatAttrs catAttReturn = new CatAttrs();
                    for (CatAttrs catAtt : catAttrList) {
                        if (catAtt.equals(viewControl)) {
                            catAttReturn = catAtt;
                            break;
                        }
                    }
                    GenericControl genericControl = getGenericControl(
                            viewControl, rowUpdate == null ? null : rowUpdate.get(atrrib.getAttrName()), atrrib, catAttReturn);
                    DynaFormControl control = row.addControl(genericControl, getControlType(viewControl .getControlType()));
                    etiqRen.setForControl(control);
                }
            }
        } catch (Exception e) {
            Utileria.logger(getClass()).error(e.getMessage());
        }
    }

    /**
     * Se verifica si el Catalogo a mostrar se va a poder modificar o ser?
     * solo lectura
     *
     * @param catalog
     */
    private void checkCatalogReadOnly(Catalogs catalog) {
        setCatalogChange(false);
        if (catalog != null && catalog.getCatViewForCatId() != null) {
            Iterator<CatView> it = catalog.getCatViewForCatId().iterator();
            while (it.hasNext()) {
                CatView catView = it.next();
                setCatalogChange((catView.getReadOnly().compareTo('Y') == 0 ? Boolean.FALSE : Boolean.TRUE));
                break;
            }
        }
    }

    public void resetValues() {
        isUpdate = false;
        fillUpdate(-1);
        rowSelect = null;
    }

    /**
     * Se establece los valores para el GenericControl usado en las formas
     * dinamicas
     *
     * @param viewControl
     * @param valueObj
     * @param atrrib
     * @param catAtt
     * @return
     * @throws Exception
     */
    private GenericControl getGenericControl(CatViewControls viewControl,Object valueObj, Attribs atrrib,
                                             CatAttrs catAtt) throws Exception {
        createDynamicColumns(atrrib.getAttrName(), viewControl.getLabel());
        GenericControl genericControl = new GenericControl(atrrib.getAttrName(), valueObj, catAtt.getRequired(),
                viewControl.getControlType(), catAtt.getIsUnique(),viewControl.getReadOnly(), viewControl.getVisible());
        try {
            switch (viewControl.getControlType()) {
                case 'G':
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = df.parse((String) genericControl.getValue());
                    genericControl.setValue(fecha);
                    break;
            }
        } catch (Exception e) {
        }
        if (isMultipleControl(viewControl.getControlType())) {
            List<SelectItem> selectValues = new ArrayList<>();
            List<AttrSearch> insertSearch = new ArrayList<>();
            List<CatRegs> regList = serviceDynamicCatalogs.getRegs(viewControl.getCatalogsByCatIdSrc().getCatName(), insertSearch);
            for (CatRegs catReg : regList) {
                Set<CatRegValues> setRegs = catReg.getRegValues();
                String attrName;
                if (setRegs != null) {
                    for (CatRegValues regVals : setRegs) {
                        attrName = regVals.getCatAttrs().getAttribs().getAttrName();
                        if (attrName.compareTo(viewControl.getAttribs() .getAttrName()) == 0) {
                            selectValues.add(new SelectItem(regVals.getValue(),regVals.getValue()));
                        }
                    }
                }
            }
            genericControl.setSelectValues(selectValues);
            setSearchAttrsForList(atrrib.getAttrName(), valueObj);
        } else {
            setSearchAttrsForList(atrrib.getAttrName(), valueObj);
        }
        return genericControl;
    }

    private void createDynamicColumns(String header, String property) {
        ColumnModel col = new ColumnModel(header, property);
        if (!columns.contains(col)) {
            columns.add(col);
        }
    }

    public void fillCatalog() {
        try {
            List<AttrSearch> insertSearch = new ArrayList<>();
            List<CatRegs> regs_local = this.serviceDynamicCatalogs.getRegs(catName, insertSearch);
            setRegs(regs_local);
            if (regs_local != null) {
                List<Map<String, String>> responseArray_local = new ArrayList<>();
                int i = 0;
                for (CatRegs reg : regs_local) {
                    Set<CatRegValues> setReg = reg.getRegValues();
                    Map<String, String> row = new HashMap<>();
                    if (setReg != null) {
                        for (CatRegValues regVals : setReg) {
                            String attName = regVals.getCatAttrs().getAttribs().getAttrName();
                            if (row.containsKey(attName)) {
                                String value = row.get(attName);
                                row.remove(attName);
                                row.put(attName,value + "," + regVals.getValue());
                            } else {
                                row.put(attName, regVals.getValue());
                            }
                        }
                    }
                    responseArray_local.add(i, row);
                    i++;
                }
                setResponseArray(responseArray_local);
            }
        } catch (Exception e) {
            Utileria.logger(getClass()).error(e);
        }

    }

    public String submitForm() throws Exception {
        boolean hasErrors = false;

        Map<String, Object> mapAttrValues = new HashMap<>();
        List<AttrSearch> insertSearch = new ArrayList<>();
        for (DynaFormControl control : model.getControls()) {
            GenericControl genericControl = (GenericControl) control.getData();
            if (isMultipleControl(genericControl.getType())
                    && genericControl.getType() != 'C'
                    && genericControl.getType() != 'F') {
                if (genericControl.getSelectedList() != null) {
                    List<String> valueList = new ArrayList<>();
                    for (String value : genericControl.getSelectedList()) {
                        valueList.add(value);
                        AttrSearch attSearch = new AttrSearch();
                        attSearch.setAttrName(genericControl.getName());
                        attSearch.setValue(value);
                        if (genericControl.isUnique()) {
                            insertSearch.add(attSearch);
                        }
                    }
                    mapAttrValues.put(genericControl.getName(), valueList);
                    if (genericControl.isRequired() && valueList.isEmpty()) {
                        Utileria.mensajeAlerta(Messages.getString("required"), genericControl.getName());
                        hasErrors = true;
                    }
                }
            } else {
                mapAttrValues.put(genericControl.getName(),getStringValue(genericControl.getValue()));
                AttrSearch attSearch = new AttrSearch();
                attSearch.setAttrName(genericControl.getName());
                String strValue = "";
                strValue = getStringValue(genericControl.getValue());
                attSearch.setValue(strValue);
                if (genericControl.isUnique()) {
                    insertSearch.add(attSearch);
                }
                if (genericControl.isRequired()&& (genericControl.getValue() == null || strValue.length() == 0)) {
                    Utileria.mensajeAlerta(Messages.getString("required"), genericControl.getName());
                    hasErrors = true;
                }
            }
        }
        if (!hasErrors) {
            if (isUpdate) {
                serviceDynamicCatalogs.updateRegs(catName, mapAttrValues, lstSearchAttrs);
                Utileria.mensajeSatisfactorio(Messages.getString("updateSuccess"));
            } else {
                List<CatRegs> regs_local = this.serviceDynamicCatalogs.getRegs(catName, insertSearch);
                if (regs_local != null && regs_local.size() > 0) {
                    Utileria.mensajeErroneo(Messages.getString("regExist"));
                } else {
                    serviceDynamicCatalogs.insertReg(catName, mapAttrValues);
                    Utileria.mensajeSatisfactorio(Messages.getString("insertSuccess"));
                }
            }
            isUpdate = false;
            fillUpdate(-1);
            fillCatalog();
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("isValid", !hasErrors);
        return null;
    }

    private String getControlType(char a) {
        String value = null;
        switch (a) {
            case 'A':
                value = "inputNumber";
                break;
            case 'B':
                value = "input";
                break;
            case 'C':
                value = "combo";
                break;
            case 'D':
                value = "manycombo";
                break;
            case 'E':
                value = "checkbox";
                break;
            case 'F':
                value = "radiobutton";
                break;
            case 'G':
                value = "calendar";
                break;
            case 'H':
                value = "manycheckbox";
                break;
            case 'I':
                value = "textarea";
                break;
            case 'J':
                value = "porc";
                break;
            default:
                value = "invalid";
                break;
        }
        return value;
    }

    private boolean isMultipleControl(char type) {
        switch (type) {
            case 'C':
            case 'D':
            case 'F':
            case 'H':
                return true;
        }
        return false;
    }

    public DynaFormModel getModel() throws Exception {
        if (null == model || null == model.getControls()
                || model.getControls().size() == 0) {
            fillUpdate(-1);
        }
        return model;
    }

    /**
     * public void
     * setColumns(ArrayList<com.adinfi.seven.presentation.views.MBPrueba
     * .ColumnModel> arrayList) { this.columns = arrayList; }
     */
    public List<Map<String, String>> getResponseArray() {
        if (responseArray == null || responseArray.size() == 0) {
            fillCatalog();
        }
        return responseArray;
    }

    public void onSelect(int selectIndex) {
        try {
            isUpdate = true;
            rowSelect = responseArray.get(selectIndex);
            lstSearchAttrs = new ArrayList<AttrSearch>();
            fillUpdate(selectIndex);
        } catch (Exception e) {
            Utileria.logger(getClass()).error(e);
        }
    }

    private void setSearchAttrsForList(String attrName, Object tokens) {
        if (tokens != null && tokens instanceof String) {
            StringTokenizer token = new StringTokenizer((String) tokens, ",");
            while (token.hasMoreTokens()) {
                AttrSearch attSearch = new AttrSearch();
                attSearch.setAttrName(attrName);
                attSearch.setValue(token.nextToken());
                lstSearchAttrs.add(attSearch);
            }
        }
    }

    private String getStringValue(Object value) {
        String strValue = "";
        if (value != null) {
            if (value instanceof Date) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                strValue = df.format((Date) value);
            } else if (value instanceof String) {
                strValue = (String) value;
            } else if (value instanceof Double) {
                Double valueLong = ((Double) value);
                strValue = valueLong.toString();
            } else {
                strValue = value.toString();
            }
        }
        return strValue;
    }

    public void onDelete() {
        try {
            List<AttrSearch> lstSearchAttrsDelete = new ArrayList<AttrSearch>();
            for (DynaFormControl control : model.getControls()) {
                GenericControl genericControl = (GenericControl) control.getData();
                setSearchAttrsForDelete(genericControl.getName(), rowDelete.get(genericControl.getName()),lstSearchAttrsDelete);
            }
            serviceDynamicCatalogs.deleteRegs(catName, lstSearchAttrsDelete);
            fillCatalog();
            Utileria.mensajeSatisfactorio(Messages.getString("deleteSucess"));
        } catch (Exception e) {
            Utileria.logger(getClass()).error(e);
        }
    }

    private void setSearchAttrsForDelete(String attrName, Object tokens, List<AttrSearch> lstSearchAttrsDelete) {
        if (tokens != null && tokens instanceof String) {
            StringTokenizer token = new StringTokenizer((String) tokens, ",");
            while (token.hasMoreTokens()) {
                AttrSearch attSearch = new AttrSearch();
                attSearch.setAttrName(attrName);
                attSearch.setValue(token.nextToken());
                lstSearchAttrsDelete.add(attSearch);
            }
        }
    }

    public Map<String, String> getRowSelect() {
        return rowSelect;
    }

    public void setRowSelect(Map<String, String> rowSelect) {
        this.rowSelect = rowSelect;
    }

    public String getRowKeyValue() {
        return rowKeyValue;
    }

    public void setRowKeyValue(String rowKeyValue) {
        this.rowKeyValue = rowKeyValue;
    }

    public List<AttrSearch> getLstSearchAttrs() {
        return lstSearchAttrs;
    }

    public void setLstSearchAttrs(List<AttrSearch> lstSearchAttrs) {
        this.lstSearchAttrs = lstSearchAttrs;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getCatalogNametitle() {
        return catalogNametitle;
    }

    public void setCatalogNametitle(String catalogNametitle) {
        this.catalogNametitle = catalogNametitle;
    }

    public boolean isCatalogChange() {
        return catalogChange;
    }

    public void setCatalogChange(boolean catalogChange) {
        this.catalogChange = catalogChange;
    }

    /**
     * @return the rowDelete
     */
    public Map<String, String> getRowDelete() {
        return rowDelete;
    }

    /**
     * @param rowDelete
     *            the rowDelete to set
     */
    public void setRowDelete(Map<String, String> rowDelete) {
        this.rowDelete = rowDelete;
    }

    public void setColumns(ArrayList<ColumnModel> arrayList) {
        this.columns = arrayList;
    }

    public List<CatRegs> getRegs() {
        return regs;
    }

    public void setRegs(List<CatRegs> regs) {
        this.regs = regs;
    }

    public List<CatViewControls> getViewList() {
        return viewList;
    }

    public void setViewList(List<CatViewControls> viewList) {
        this.viewList = viewList;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setResponseArray(List<Map<String, String>> responseArray) {
        this.responseArray = responseArray;
    }

    public void setModel(DynaFormModel model) {
        this.model = model;
    }
}