package com.adinfi.seven.business.domain;

import java.util.List;

public class Opcion implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String descrip = null;
	private String url = null;
	private Integer opcId = null;
	private Integer parentId = null;
	private Short opcLevel = null;
	private String opcName = null;
	private Short orderSort = null;
	private String fileImage = null;
	private Boolean menHor= false;
    private Boolean active = true;
    
    private String menuDescription;

	public Boolean isMenHor() {
		if( menHor==null ){
			return false;
		}
		return menHor;
	}

	public void setMenHor(Boolean isMenHor) {
		this.menHor = isMenHor;
	}
        
    public Boolean isActive(){
    	return active;
    }
        
    public void setActive(Boolean active){
    	this.active = active;
    }

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}

	public Short getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(Short orderSort) {
		this.orderSort = orderSort;
	}

	private List<Opcion> childsAsArray = null;

	public Integer getOpcId() {
		return opcId;
	}

	public List<Opcion> getChildsAsArray() {
		return childsAsArray;
	}

	public void setChildsAsArray(List<Opcion> childsAsArray) {
		this.childsAsArray = childsAsArray;
	}

	public void setOpcId(Integer opcId) {
		this.opcId = opcId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Short getOpcLevel() {
		return opcLevel;
	}

	public void setOpcLevel(Short opcLevel) {
		this.opcLevel = opcLevel;
	}

	public String getOpcName() {
		return opcName;
	}

	public void setOpcName(String opcName) {
		this.opcName = opcName;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    /**
     * @return the menuDescription
     */
    public String getMenuDescription() {
        return menuDescription;
    }

    /**
     * @param menuDescription the menuDescription to set
     */
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

}
