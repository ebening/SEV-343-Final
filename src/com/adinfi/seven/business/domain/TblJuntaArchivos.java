package com.adinfi.seven.business.domain;

public class TblJuntaArchivos implements java.io.Serializable{
	private static final long serialVersionUID = -3315215707754404977L;
	private int id;
	private TblJuntaPlaneacion tblJuntaPlaneacion;
	private String url;
	private int sizeFile;
	private String mimeType;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public TblJuntaPlaneacion getTblJuntaPlaneacion() {
		return tblJuntaPlaneacion;
	}

	public void setTblJuntaPlaneacion(TblJuntaPlaneacion tblJuntaPlaneacion) {
		this.tblJuntaPlaneacion = tblJuntaPlaneacion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSizeFile() {
		return sizeFile;
	}

	public void setSizeFile(int sizeFile) {
		this.sizeFile = sizeFile;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
