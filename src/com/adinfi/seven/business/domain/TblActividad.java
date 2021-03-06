package com.adinfi.seven.business.domain;
// Generated Feb 15, 2016 1:51:41 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TblActividad generated by hbm2java
 */
public class TblActividad  implements java.io.Serializable {


     private int idactividad;
     private CatUsuarios catUsuariosByIdresponsable;
     private CatUsuarios catUsuariosByIdcreador;
     private CatRole catRole;
     private TblMecanica tblMecanica;
     private Integer idtblcampact;
     private Integer idactbefore;
     private Integer idactafter;
     private String descripcion;
     private Date fechaCreacion;
     private Date vigenciaInicio;
     private Date vigenciaFinal;
     private Date fechaFin;
     private Date alerta;
     private Integer nivelEscalable;
     private Integer estatusEscalable;
     private CatEstatus catEstatus;
     private Integer avance;
     private String comments;
     private Integer orden;

    public TblActividad() {
    }

	
    public TblActividad(int idactividad) {
        this.idactividad = idactividad;
    }
    public TblActividad(int idactividad, CatUsuarios catUsuariosByIdresponsable, CatUsuarios catUsuariosByIdcreador, TblMecanica tblMecanica, Integer idtblcampact, Integer idactbefore, Integer idactafter, String descripcion, Date fechaCreacion, Date vigenciaInicio, Date vigenciaFinal, Date fechaFin, Integer nivelEscalable, Integer estatusEscalable, CatEstatus catEstatus, Integer avance, String comments) {
       this.idactividad = idactividad;
       this.catUsuariosByIdresponsable = catUsuariosByIdresponsable;
       this.catUsuariosByIdcreador = catUsuariosByIdcreador;
       this.tblMecanica = tblMecanica;
       this.idtblcampact = idtblcampact;
       this.idactbefore = idactbefore;
       this.idactafter = idactafter;
       this.descripcion = descripcion;
       this.fechaCreacion = fechaCreacion;
       this.vigenciaInicio = vigenciaInicio;
       this.vigenciaFinal = vigenciaFinal;
       this.fechaFin = fechaFin;
       this.nivelEscalable = nivelEscalable;
       this.estatusEscalable = estatusEscalable;
       this.catEstatus = catEstatus;
       this.avance = avance;
       this.comments = comments;
    }
   
    public int getIdactividad() {
        return this.idactividad;
    }
    
    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }
    public CatUsuarios getCatUsuariosByIdresponsable() {
        return this.catUsuariosByIdresponsable;
    }
    
    public void setCatUsuariosByIdresponsable(CatUsuarios catUsuariosByIdresponsable) {
        this.catUsuariosByIdresponsable = catUsuariosByIdresponsable;
    }
    public CatUsuarios getCatUsuariosByIdcreador() {
        return this.catUsuariosByIdcreador;
    }
    
    public void setCatUsuariosByIdcreador(CatUsuarios catUsuariosByIdcreador) {
        this.catUsuariosByIdcreador = catUsuariosByIdcreador;
    }
    public TblMecanica getTblMecanica() {
        return this.tblMecanica;
    }
    
    public void setTblMecanica(TblMecanica tblMecanica) {
        this.tblMecanica = tblMecanica;
    }
    public Integer getIdtblcampact() {
        return this.idtblcampact;
    }
    
    public void setIdtblcampact(Integer idtblcampact) {
        this.idtblcampact = idtblcampact;
    }
    public Integer getIdactbefore() {
        return this.idactbefore;
    }
    
    public void setIdactbefore(Integer idactbefore) {
        this.idactbefore = idactbefore;
    }
    public Integer getIdactafter() {
        return this.idactafter;
    }
    
    public void setIdactafter(Integer idactafter) {
        this.idactafter = idactafter;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getVigenciaInicio() {
        return this.vigenciaInicio;
    }
    
    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }
    public Date getVigenciaFinal() {
        return this.vigenciaFinal;
    }
    
    public void setVigenciaFinal(Date vigenciaFinal) {
        this.vigenciaFinal = vigenciaFinal;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Integer getNivelEscalable() {
        return this.nivelEscalable;
    }

    public Date getAlerta() {
        return alerta;
    }

    public void setAlerta(Date alerta) {
        this.alerta = alerta;
    }

    public void setNivelEscalable(Integer nivelEscalable) {
        this.nivelEscalable = nivelEscalable;
    }
    public Integer getEstatusEscalable() {
        return this.estatusEscalable;
    }
    
    public void setEstatusEscalable(Integer estatusEscalable) {
        this.estatusEscalable = estatusEscalable;
    }

    public CatEstatus getCatEstatus() {
        return catEstatus;
    }

    public void setCatEstatus(CatEstatus catEstatus) {
        this.catEstatus = catEstatus;
    }

    public Integer getAvance() {
        return this.avance;
    }
    
    public void setAvance(Integer avance) {
        this.avance = avance;
    }
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public CatRole getCatRole() {
        return catRole;
    }

    public void setCatRole(CatRole catRole) {
        this.catRole = catRole;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}


