package com.adinfi.seven.persistence.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImgEjecucionDTO {
	
	private static  SimpleDateFormat formatDate = new SimpleDateFormat(
			"dd-MMMM-yyyy", new Locale("es", "ES"));

	private long idCampana;
	private Date vigenciaIncio;
	private Date vigenciaFinal;
	private int idCategoria;
	private String nombreCampana;
	private int idPrograma;
	private String nombrePrograma;
	private String nombreCategoria;
	private String vigencia;
	private String pid;
	
	public String getVigencia() {
							
//			vigencia = formatDate.parse(vigenciaIncio.toString().substring(0,10)) +" al "+ formatDate.parse(vigenciaFinal.toString().substring(0, 10));
			SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yy");
//			DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
			
			vigencia = f.format(vigenciaIncio) + " - " + f.format(vigenciaFinal);
		
		System.out.println("VIGENCIA ====="+ vigencia);
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	private String path;
	
	public String getNombreCampana() {
		return nombreCampana;
	}
	public void setNombreCampana(String nombreCampana) {
		this.nombreCampana = nombreCampana;
	}
	public Date getVigenciaIncio() {
		return vigenciaIncio;
	}
	public void setVigenciaIncio(Date vigenciaIncio) {
		this.vigenciaIncio = vigenciaIncio;
	}
	public Date getVigenciaFinal() {
		return vigenciaFinal;
	}
	public void setVigenciaFinal(Date vigenciaFinal) {
		this.vigenciaFinal = vigenciaFinal;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getIdCampana() {
		return idCampana;
	}
	public void setIdCampana(long idCampana) {
		this.idCampana = idCampana;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}