package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MecanicaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int pkMec;
	private long idCampana;
	private double ingresoPopReal;
	private Integer idPromo;
	private String nombrePromo;
	private int idTipoPromocion;
	private int esCompartido;
	private String acuerdo1;
	private String acuerdo2;
	private String acuerdo3;
	private String comentarios;
	private List<GenericItem> groupList;
	private List<GenericItem> zoneList;
	private List<GenericItem> storeList;
	private List<GenericItem> programaList;
	private List<GenericItem> categoriaList;
	//TODO: En caso de ser la opción otro el ID deberá ser vacio
	//y el value contener el valor tecleado
	private List<GenericItem> senalamientoList;
	private List<ComponenteDTO> componenteList;
	private List<ConfMecanicaDTO> confMecanicaDTO;
	private List<PreciosPromocionDTO> preciosLst;;
	private int programaId;
	@SuppressWarnings("deprecation")
	private Date horaIni = new Date(12, 12, 12);
	private Date horaFin ;
	private int lunes = 1;
	private int martes = 1;
	private int miercoles = 1;
	private int jueves = 1;
	private int viernes = 1;
	private int sabado = 1;
	private int domingo = 1;
	private String nombreMecanica;
	
	public int getPkMec() {
		return pkMec;
	}
	public void setPkMec(int pkMec) {
		this.pkMec = pkMec;
	}
	public long getIdCampana() {
		return idCampana;
	}
	public void setIdCampana(long getIdCampana) {
		this.idCampana = getIdCampana;
	}
	public double getIngresoPopReal() {
		return ingresoPopReal;
	}
	public void setIngresoPopReal(double ingresoPopReal) {
		this.ingresoPopReal = ingresoPopReal;
	}
	public Integer getIdPromo() {
		return idPromo;
	}
	public void setIdPromo(Integer idPromo) {
		this.idPromo = idPromo;
	}
	public String getNombrePromo() {
		return nombrePromo;
	}
	public void setNombrePromo(String nombrePromo) {
		this.nombrePromo = nombrePromo;
	}
	public int getIdTipoPromocion() {
		return idTipoPromocion;
	}
	public void setIdTipoPromocion(int idTipoPromocion) {
		this.idTipoPromocion = idTipoPromocion;
	}
	public int getEsCompartido() {
		return esCompartido;
	}
	public void setEsCompartido(int esCompartido) {
		this.esCompartido = esCompartido;
	}
	public String getAcuerdo1() {
		return acuerdo1;
	}
	public void setAcuerdo1(String acuerdo1) {
		this.acuerdo1 = acuerdo1;
	}
	public String getAcuerdo2() {
		return acuerdo2;
	}
	public void setAcuerdo2(String acuerdo2) {
		this.acuerdo2 = acuerdo2;
	}
	public String getAcuerdo3() {
		return acuerdo3;
	}
	public void setAcuerdo3(String acuerdo3) {
		this.acuerdo3 = acuerdo3;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public List<GenericItem> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GenericItem> groupList) {
		this.groupList = groupList;
	}
	public List<GenericItem> getZoneList() {
		return zoneList;
	}
	public void setZoneList(List<GenericItem> zoneList) {
		this.zoneList = zoneList;
	}
	public List<GenericItem> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<GenericItem> storeList) {
		this.storeList = storeList;
	}
	public List<GenericItem> getProgramaList() {
		return programaList;
	}
	public void setProgramaList(List<GenericItem> programaList) {
		this.programaList = programaList;
	}
	public List<GenericItem> getCategoriaList() {
		return categoriaList;
	}
	public void setCategoriaList(List<GenericItem> categoriaList) {
		this.categoriaList = categoriaList;
	}
	public List<GenericItem> getSenalamientoList() {
		return senalamientoList;
	}
	public void setSenalamientoList(List<GenericItem> senalamientoList) {
		this.senalamientoList = senalamientoList;
	}
	public List<ComponenteDTO> getComponenteList() {
		return componenteList;
	}
	public void setComponenteList(List<ComponenteDTO> componenteList) {
		this.componenteList = componenteList;
	}
	public Date getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(Date horaIni) {
		this.horaIni = horaIni;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public int getLunes() {
		return this.lunes;
	}
	public void setLunes(int lunes) {
		this.lunes = lunes;
	}
	public int getMartes() {
		return martes;
	}
	public void setMartes(int martes) {
		this.martes = martes;
	}
	public int getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(int miercoles) {
		this.miercoles = miercoles;
	}
	public int getJueves() {
		return jueves;
	}
	public void setJueves(int jueves) {
		this.jueves = jueves;
	}
	public int getViernes() {
		return viernes;
	}
	public void setViernes(int viernes) {
		this.viernes = viernes;
	}
	public int getSabado() {
		return sabado;
	}
	public void setSabado(int sabado) {
		this.sabado = sabado;
	}
	public int getDomingo() {
		return domingo;
	}
	public void setDomingo(int domingo) {
		this.domingo = domingo;
	}
	public int getProgramaId() {
		return programaId;
	}
	public void setProgramaId(int programaId) {
		this.programaId = programaId;
	}
	public String getNombreMecanica() {
		return nombreMecanica;
	}
	public void setNombreMecanica(String nombreMecanica) {
		this.nombreMecanica = nombreMecanica;
	}
	public List<ConfMecanicaDTO> getConfMecanicaDTO() {
		return confMecanicaDTO;
	}
	public void setConfMecanicaDTO(List<ConfMecanicaDTO> confMecanicaDTO) {
		this.confMecanicaDTO = confMecanicaDTO;
	}
	public List<PreciosPromocionDTO> getPreciosLst() {
		return preciosLst;
	}
	public void setPreciosLst(List<PreciosPromocionDTO> preciosLst) {
		this.preciosLst = preciosLst;
	}
}