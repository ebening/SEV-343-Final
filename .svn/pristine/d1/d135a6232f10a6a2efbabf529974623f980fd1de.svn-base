package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;

public class GenericControl implements Serializable {
	private static final long serialVersionUID = -8138896343949852654L;
	private String name;
	private Object value;
	private boolean required;
	private List<SelectItem> selectValues;
	private List<String> selectedList;
	private char type;
	private boolean unique;
	private boolean readOnly;
	private boolean rendered;
    private String labelDescription;
    private String errorClass;
    private int keepErrorClass = 0;
    
    private int length = 0;


    public GenericControl() {
    }

    public GenericControl(String name, Object value, char requerid, char type,
			char unique, char readOnly, char rendered) throws ParseException {
		this.name = name;
		this.value = value;
		getValueControl(value);
		this.required = (requerid == 'Y' ? true : false);
		this.type = type;
		this.unique = (unique == 'Y' ? true : false);
		this.readOnly = (readOnly == 'Y' ? true : false);
		this.rendered = (rendered == 'Y' ? true : false);
	}

	private void getValueControl(Object value) throws ParseException {
		if (value == null) {
			return;
		}
		if (value instanceof String) {
			StringTokenizer tokens = new StringTokenizer((String) value, ",");
			selectedList = new ArrayList<String>();
			while (tokens.hasMoreTokens()) {
				selectedList.add(tokens.nextToken());
			}
		}
	}

	public GenericControl(String name, boolean requerid) {
		this.name = name;
		this.required = requerid;
	}

	public GenericControl(String name, boolean requerid,
			List<SelectItem> selectValues) {
		this.name = name;
		this.required = requerid;
		this.selectValues = selectValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean requerid) {
		this.required = requerid;
	}

	public List<SelectItem> getSelectValues() {
		return selectValues;
	}

	public void setSelectValues(List<SelectItem> selectValues) {
		this.selectValues = selectValues;
	}

	public List<String> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<String> selectedList) {
		this.selectedList = selectedList;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

    /**
     * @return the labelDescription
     */
    public String getLabelDescription() {
        return labelDescription;
    }

    /**
     * @param labelDescription the labelDescription to set
     */
    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    /**
     * @return the errorClass
     */
    public String getErrorClass() {
        return errorClass;
    }

    /**
     * @param errorClass the errorClass to set
     */
    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    /**
     * @return the keepErrorClass
     */
    public int getKeepErrorClass() {
        return keepErrorClass;
    }

    /**
     * @param keepErrorClass the keepErrorClass to set
     */
    public void setKeepErrorClass(int keepErrorClass) {
        this.keepErrorClass = keepErrorClass;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }
}
