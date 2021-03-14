package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.Attrib;
import com.adinfi.seven.business.domain.Attribs;
import com.adinfi.seven.business.domain.CatAttrs;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.CatView;
import com.adinfi.seven.business.domain.CatViewControls;
import com.adinfi.seven.business.domain.Catalogs;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.admin.ServiceMenuAndRoles;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.Messages;

import edu.emory.mathcs.backport.java.util.Collections;
import java.util.Comparator;

public class MBPrueba implements Serializable{

	private static final long serialVersionUID = 1L;
	private transient final Logger LOG = Logger.getLogger(MBPrueba.class);
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceMenuAndRoles serviceMenuAndRoles;
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

        
        private Comparator<CatViewControls> sortForCATESPPROMO(){
            final List<String> definedOrder = new ArrayList();
            definedOrder.add("ID");
            definedOrder.add("CODIGO");
            return new Comparator<CatViewControls>() {
                @Override
                public int compare(CatViewControls o1, CatViewControls o2) {
                    return Integer.valueOf(definedOrder.indexOf(o1.getLabel())).compareTo(definedOrder.indexOf(o2.getLabel()));
                }
            };
        }
	/**
	 * Llenado de los controles para realizar modificaciones
	 * 
	 * @param id
	 *            del registro del grid a modificar
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
				//carga los attributos de la forma
                viewList = serviceDynamicCatalogs.getCatalogViewControl(catalog.getCatId());
                                
                //carga los atributos de los elementos de la forma (si es requerido o unico)
				catAttrList = serviceDynamicCatalogs.getCatalogAttributes(catalog.getCatId());
                                
                //ordena los elementos
                if(catName.equalsIgnoreCase("CAT_ESP_PROMO")){
                    Collections.sort(viewList, sortForCATESPPROMO());
                }else{
                    Collections.sort(viewList);
                }  
                                
                //itera los elementos de la forma, por cada uno:
				for (CatViewControls viewControl : viewList) {
                                    
                    //crea un row del modelo
					DynaFormRow row = model.createRegularRow();
                                        
                    //crea un label para el row (LABEL de CAT_VIEW_CONTROLS)
                    String labelDescription = viewControl.getLabel().equalsIgnoreCase("codigo") ? "DESCRIPCION" : viewControl.getLabel();
					DynaFormLabel etiqRen = row.addLabel(labelDescription);
					
                    //trae los el attributo asociado al control (ATTRIB_ID -> ATTRIB)
                    Attribs atrrib = serviceDynamicCatalogs.getAttribId(viewControl.getAttribId());
                                        
					CatAttrs catAttReturn = new CatAttrs();
					
                    //itera sobre el listado de attributos
                    for (CatAttrs catAtt : catAttrList) {
                        //si el attributo es igual al elemento de la forma en cuestion, lo respalda y rompe
						if (catAtt.equals(viewControl)) {
							catAttReturn = catAtt;
							break;
						}
					}
                                        
                    //obtiene un genericControl
					GenericControl genCtrl = getGenericControl(viewControl, rowUpdate == null ? null : rowUpdate.get(atrrib.getAttrName()), atrrib, catAttReturn);
					genCtrl.setLabelDescription(labelDescription);
                    
                    //si el campo es el id, en automatico lo pone readonly
                    if (genCtrl.getName().equals("ID")){
                        genCtrl.setReadOnly(true);
                    }
					
                    //getControlType es el que asocia
					DynaFormControl control = row.addControl(genCtrl, getControlType(viewControl.getControlType()));
					etiqRen.setForControl(control);
				}
			}
            
            
		} catch (Exception e) {
            e.printStackTrace();
			LOG.error(e.getMessage());
		}
	}

	/**
	 * Se verifica si el Catalogo a mostrar se va a poder modificar o ser�
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
				setCatalogChange((catView.getReadOnly().compareTo('Y') == 0 ? Boolean.FALSE
						: Boolean.TRUE));
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
	private GenericControl getGenericControl(CatViewControls viewControl, Object valueObj, Attribs atrrib, CatAttrs catAtt) throws Exception {
		
                createDynamicColumns(atrrib.getAttrName(), viewControl.getLabel());
		
                GenericControl genericControl = new GenericControl(
				atrrib.getAttrName(), valueObj, catAtt.getRequired(),
				viewControl.getControlType(), catAtt.getIsUnique(),
				viewControl.getReadOnly(), viewControl.getVisible());
		// --
        
        int attribId = viewControl.getAttribId();
        System.out.println("attribId ---> "+attribId);
        Attribs attrib = serviceDynamicCatalogs.getAttribId(attribId);
        if(attrib != null){
            int length = attrib.getLength().intValue();
            if(catName.equalsIgnoreCase("CAT_PERIOD") &&  viewControl.getLabel().equalsIgnoreCase("Nombre del Periodo")){
                length = 50;
            }
            if(catName.equalsIgnoreCase("CAT_ESP_PROMO") &&  viewControl.getLabel().equalsIgnoreCase("codigo")){
                length = 50;
            }
            
            genericControl.setLength(length);
        }else{
            System.out.println("Attrib nulo");
        }
        
        
		String atributo = atrrib.getAttrName();
		try {
                    if(viewControl.getControlType() == 'G' || viewControl.getControlType() == 'L' || viewControl.getControlType() == 'M'){
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        if(genericControl.getValue()!=null){
                            Date fecha = df.parse((String) genericControl.getValue());
                            genericControl.setValue(fecha);
                        }
                    }           
		} catch (Exception e) {
                    LOG.error(e.getMessage());
		}
		if (isMultipleControl(viewControl.getControlType())) {
			List<SelectItem> selectValues = new ArrayList<>();
			List<AttrSearch> insertSearch = new ArrayList<>();
			List<CatRegs> regList = serviceDynamicCatalogs.getRegs(viewControl
					.getCatalogsByCatIdSrc().getCatName(), insertSearch);
			for (CatRegs catReg : regList) {
				Set<CatRegValues> setRegs = catReg.getRegValues();
				String attrName = null;
				if (setRegs != null) {
					//LOG.info(catReg);
					//LOG.info(setRegs);
					for (CatRegValues regVals : setRegs) {
						attrName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
					
						
						if (attrName.compareTo(viewControl.getAttribs()
								.getAttrName()) == 0) {
							
							if(Constants.ATT_ROLE.equals(atributo) && isNumeric(regVals.getValue())){
								selectValues.add(new SelectItem(regVals.getValue(),
										getRolValue(Integer.valueOf(regVals.getValue()))));
							}else{
								selectValues.add(new SelectItem(regVals.getValue(),
										regVals.getValue()));	
							}
							
						}
					}
				}
			}
			genericControl.setSelectValues(selectValues);
			setSearchAttrsForList(atrrib.getAttrName(), valueObj);
		} else {
			setSearchAttrsForList(atrrib.getAttrName(), valueObj);
		}
        
        //se setea el max length del campo
        //genericControl.setLength(viewControl.getAttribs().getLength().intValue());
        
		return genericControl;
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
                case 'K':
                        value = "password";
                        break;
                case 'L':
			value = "calendar";
			break;
                case 'M':
                        value = "calendar";
			break;
		default:
			value = "invalid";
			break;
		}
		return value;
	}

	public void fillCatalog() {
		try {
			List<AttrSearch> insertSearch = new ArrayList<>();
			List<CatRegs> registros = this.serviceDynamicCatalogs.getRegs(catName, insertSearch);
			setRegs(registros);
			if (registros != null) {
				List<Map<String, String>> response = new ArrayList<>();
				int i = 0;
                
                //iteracion de registros
				for (CatRegs reg : registros) {
					Set<CatRegValues> setReg = reg.getRegValues();
					Map<String, String> row = new HashMap<>();
					if (setReg != null) {
						for (CatRegValues regVals : setReg) {
							String attName = regVals.getCatAttrs().getAttribs().getAttrName();
							if (row.containsKey(attName)) {
								String value = row.get(attName);
								row.remove(attName);
								row.put(attName,
										value + "," + regVals.getValue());
							} else {
								if(Constants.ATT_ROLE.equals(attName)){
									if(isNumeric(regVals.getValue())){
										row.put(attName, getRolValue(Integer.valueOf(regVals.getValue())));
									}else{
										row.put(attName, regVals.getValue());
									}
								}else{
									row.put(attName, regVals.getValue());
								}
								
							}
						}
					}
					response.add(i, row);
					i++;
				}
				setResponseArray(response);
			}
		} catch (Exception e) {
			LOG.error(e);
		}

	}
	
	
	public String getRolValue(int idRol){
		switch(idRol){
		case 1: return Constants.ROL1; 
		case 2: return Constants.ROL2;
		case 3: return Constants.ROL3;
		case 4: return Constants.ROL4;
		case 5: return Constants.ROL5;
	/*	case 6: return Constants.ROL6;
		case 7: return Constants.ROL7;
		case 8: return Constants.ROL8;
		case 9: return Constants.ROL9; */
		default: return Constants.ROL0;
		}
	}
	
	
	public static boolean isNumeric(String str){  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
        
        
        private boolean validateDateRange(List<DynaFormControl> elements) throws Exception{
            boolean valid = true;
            String initialDateStr = "";
            String finalDateStr = "";
            String dayDuration = "";
            GenericControl dayDurationControl = null;
            
            for(DynaFormControl e : elements){
                GenericControl c = (GenericControl)e.getData();
                if(c.getType() == 'L'){
                    initialDateStr = this.getStringValue(c.getValue());
                    continue;
                }if(c.getType() == 'M'){
                    finalDateStr = this.getStringValue(c.getValue());
                    continue;
                }
                if(c.getLabelDescription().equals("DURACION EN DIAS")){
                    dayDuration = this.getStringValue(c.getValue());
                    dayDurationControl = c;
                }
                
            }
            
            if(initialDateStr.length() <= 0 || finalDateStr.length() <= 0){
                return false;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date now = sdf.parse(sdf.format(new Date()));
            
            Date initialDate = sdf.parse(initialDateStr);
            
            if(initialDate.compareTo(now) <= 0){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La fecha inicial debe ser posterior a la fecha actual" , null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return false;
            }
            
            Date finalDate = sdf.parse(finalDateStr);
            if(finalDate.compareTo(initialDate) <= 0){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La fecha final debe ser posterior a la fecha inicial" , null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return false;
            }
            
            float diff = (((((finalDate.getTime() - initialDate.getTime())/1000)/60)/60)/24);
            int diferencia = Math.round(diff);
            
            
            
            if(dayDuration.length() > 0){
                
                if(Integer.parseInt(dayDuration) == 0){
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La duracion en dias debe ser mayor a cero. Verifique." , null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return false;
                }   
                
                try{
                    int day = Integer.parseInt(dayDuration);
                    
                    if(day > (diferencia + 1)){
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La duracion en dias capturada ("+ day +") no puede ser mayor al rango de fechas. ("+ (diferencia + 1) +"). Verifique" , null);
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        if(dayDurationControl != null){
                            dayDurationControl.setErrorClass("border:solid red 1px;");
                            dayDurationControl.setKeepErrorClass(1);
                        }
                        return false;
                    }else{
                        dayDurationControl.setErrorClass("");
                    }
                    
                }catch(Exception e){
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El valor de duracion en dias no es numero. Verifique." , null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    if(dayDurationControl != null){
                        dayDurationControl.setErrorClass("border:solid red 1px;");
                    }
                    return false;
                }
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Capture por favor la duracion en dias." , null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return false;
                    
            }
            
            return valid;
        }

	public String submitForm() throws Exception {
		boolean hasErrors = false;
        boolean dateRangeValidation = false;
                
		Map<String, Object> mapAttrValues = new HashMap<>();
		List<AttrSearch> insertSearch = new ArrayList<>();
		for (DynaFormControl control : model.getControls()) {
			GenericControl genericControl = (GenericControl) control.getData();
                        
            if((genericControl.getType() == 'L' || genericControl.getType() == 'M') && !dateRangeValidation){
                boolean rangeValid = this.validateDateRange(model.getControls());
                hasErrors = !rangeValid;
                dateRangeValidation = true;
            }
            
			if (isMultipleControl(genericControl.getType()) && genericControl.getType() != 'C' && genericControl.getType() != 'F') {
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
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Messages.getString("required", genericControl.getLabelDescription()),null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
                        genericControl.setErrorClass("border:solid red 1px;");
						hasErrors = true;
					}else{
                        if(genericControl.getKeepErrorClass() <=0 ){
                            genericControl.setErrorClass("");
                        }else{
                            genericControl.setKeepErrorClass(0);
                        }
                    }
                    control.setData(genericControl);
				}
			} else {
				if(genericControl.getName().equals("ID") && !isUpdate){
					int id = getNextID();
					if(id != 0)
						genericControl.setValue(id);
					else
						LOG.error("No se pudo generar ID automatico");
				}
				mapAttrValues.put(genericControl.getName(),
						getStringValue(genericControl.getValue()));
				AttrSearch attSearch = new AttrSearch();
				attSearch.setAttrName(genericControl.getName());
				String strValue = "";
				strValue = getStringValue(genericControl.getValue());
				attSearch.setValue(strValue);
				if (genericControl.isUnique()) {
					insertSearch.add(attSearch);
				}
				if (genericControl.isRequired() && (genericControl.getValue() == null || strValue.length() == 0)) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Messages.getString("required", genericControl.getLabelDescription()), null);
				    genericControl.setErrorClass("border:solid red 1px;");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
					hasErrors = true;
                    return null;
				}else{
                    if(genericControl.getKeepErrorClass() <=0 ){
                        genericControl.setErrorClass("");
                    }else{
                        genericControl.setKeepErrorClass(0);
                    }
                }
                control.setData(genericControl);
            }
		}
		if (!hasErrors) {
			if (isUpdate) {
				serviceDynamicCatalogs.updateRegs(catName, mapAttrValues, lstSearchAttrs);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getString("updateSuccess"), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				List<CatRegs> regs = this.serviceDynamicCatalogs.getRegs(catName, insertSearch);
				if (regs != null && regs.size() > 0) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getString("regExist"), null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					serviceDynamicCatalogs.insertReg(catName, mapAttrValues);
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getString("insertSuccess"), null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
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

	private int getNextID() {
		try {
			Set<CatRegValues> regVals;
			List<AttrSearch> insertSearch = new ArrayList<>();
			List<CatRegs> regs = this.serviceDynamicCatalogs.getRegs(catName, insertSearch);
			if (!regs.isEmpty()){
				regVals = regs.get(regs.size()-1).getRegValues();
				for(CatRegValues r : regVals)
				{
					if(r.getCatAttrs().getAttribs().getAttrName().equals("ID")){
						return Integer.parseInt(r.getValue()) + 1;
					}
				}
			} 
			else
				return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
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

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public List<CatViewControls> getViewList() {
		return viewList;
	}

	public void setViewList(List<CatViewControls> viewList) {
		this.viewList = viewList;
	}

	public DynaFormModel getModel() throws Exception {
		if (null == model || null == model.getControls() || model.getControls().isEmpty()) {
			fillUpdate(-1);
		}
       // fillUpdate(-1);
		return model;
	}

	public void setModel(DynaFormModel model) {
		this.model = model;
	}

	public static class ColumnModel implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			super();
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}

		@Override
		public boolean equals(Object object) {
			if (!(object instanceof ColumnModel)) {
				return false;
			}
			ColumnModel other = (ColumnModel) object;
			if (header.compareTo(other.getHeader()) != 0) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int hash = 0;
			hash += (header != null ? header.hashCode() : 0);
			return hash;
		}
	}

	private void createDynamicColumns(String header, String property) {
		ColumnModel col = new ColumnModel(header, property);
		if (!columns.contains(col)) {
                    if(catName.equalsIgnoreCase("CAT_ESP_PROMO") && col.header.equals("CODE")){
                        col.property = "DESCRIPCION";
                    }
                    columns.add(col);
		}
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	/**
	 * public void
	 * setColumns(ArrayList<com.adinfi.seven.presentation.views.MBPrueba
	 * .ColumnModel> arrayList) { this.columns = arrayList; }
     * @return 
	 */
	public List<Map<String, String>> getResponseArray() {
		if (responseArray == null || responseArray.size() == 0) {
			fillCatalog();
		}
		return responseArray;
	}

	public void setResponseArray(List<Map<String, String>> responseArray) {
		this.responseArray = responseArray;
	}

	public List<CatRegs> getRegs() {
		return regs;
	}

	public void setRegs(List<CatRegs> regs) {
		this.regs = regs;
	}

	public void onSelect(int selectIndex) {
		try {
			isUpdate = true;
			rowSelect = responseArray.get(selectIndex);
			lstSearchAttrs = new ArrayList<AttrSearch>();
			fillUpdate(selectIndex);
		} catch (Exception e) {
			LOG.error(e);
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

	private void setSearchAttrsForDelete(String attrName, Object tokens,
			List<AttrSearch> lstSearchAttrsDelete) {
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

    public void onDelete() {
        try {
            List<AttrSearch> lstSearchAttrsDelete = new ArrayList<>();
            for (DynaFormControl control : model.getControls()) {
                    GenericControl genericControl = (GenericControl) control.getData();
                    setSearchAttrsForDelete(genericControl.getName(),rowDelete.get(genericControl.getName()),lstSearchAttrsDelete);
            }
            if(serviceDynamicCatalogs.deleteRegs(catName, lstSearchAttrsDelete) > 0){
                fillCatalog();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Messages.getString("deleteSucess"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg); 
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,Messages.getString("deleteError"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg); 
            }
            
        } catch (Exception e) {
                LOG.error(e);
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

	public void setColumns(
			ArrayList<com.adinfi.seven.presentation.views.MBPrueba.ColumnModel> arrayList) {
		this.columns = arrayList;

	}
	
	
	 public ServiceMenuAndRoles getServiceMenuAndRoles() {
			return serviceMenuAndRoles;
		}
	    
	    public void setServiceMenuAndRoles(
				ServiceMenuAndRoles serviceMenuAndRoles) {
			this.serviceMenuAndRoles = serviceMenuAndRoles;
		}
	

}