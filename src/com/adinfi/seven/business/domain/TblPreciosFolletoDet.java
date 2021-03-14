package com.adinfi.seven.business.domain;
// Generated 31/01/2014 04:48:00 PM by Hibernate Tools 3.6.0


import java.math.BigDecimal;

/**
 * TblPreciosFolletoDet generated by hbm2java
 */
public class TblPreciosFolletoDet  implements java.io.Serializable {


     private int idPrecFolletoDet;
     private TblPreciosFolleto tblPreciosFolleto;
     private int idSistemaVenta;
     private String idSku;
     private BigDecimal cdoPublicar;
     private BigDecimal absem6mes;
     private BigDecimal absem9mes;
     private BigDecimal absem12mes;
     private BigDecimal absem15mes;
     private BigDecimal absem18mes;
     private BigDecimal absem24mes;

    public TblPreciosFolletoDet() {
    }

	
    public TblPreciosFolletoDet(int idPrecFolletoDet, TblPreciosFolleto tblPreciosFolleto, int idSistemaVenta, String idSku) {
        this.idPrecFolletoDet = idPrecFolletoDet;
        this.tblPreciosFolleto = tblPreciosFolleto;
        this.idSistemaVenta = idSistemaVenta;
        this.idSku = idSku;
    }
    public TblPreciosFolletoDet(int idPrecFolletoDet, TblPreciosFolleto tblPreciosFolleto, int idSistemaVenta, String idSku, BigDecimal cdoPublicar, BigDecimal absem6mes, BigDecimal absem9mes, BigDecimal absem12mes, BigDecimal absem15mes, BigDecimal absem18mes, BigDecimal absem24mes) {
       this.idPrecFolletoDet = idPrecFolletoDet;
       this.tblPreciosFolleto = tblPreciosFolleto;
       this.idSistemaVenta = idSistemaVenta;
       this.idSku = idSku;
       this.cdoPublicar = cdoPublicar;
       this.absem6mes = absem6mes;
       this.absem9mes = absem9mes;
       this.absem12mes = absem12mes;
       this.absem15mes = absem15mes;
       this.absem18mes = absem18mes;
       this.absem24mes = absem24mes;
    }
   
    public int getIdPrecFolletoDet() {
        return this.idPrecFolletoDet;
    }
    
    public void setIdPrecFolletoDet(int idPrecFolletoDet) {
        this.idPrecFolletoDet = idPrecFolletoDet;
    }
    public TblPreciosFolleto getTblPreciosFolleto() {
        return this.tblPreciosFolleto;
    }
    
    public void setTblPreciosFolleto(TblPreciosFolleto tblPreciosFolleto) {
        this.tblPreciosFolleto = tblPreciosFolleto;
    }
    public int getIdSistemaVenta() {
        return this.idSistemaVenta;
    }
    
    public void setIdSistemaVenta(int idSistemaVenta) {
        this.idSistemaVenta = idSistemaVenta;
    }
    public String getIdSku() {
        return this.idSku;
    }
    
    public void setIdSku(String idSku) {
        this.idSku = idSku;
    }
    public BigDecimal getCdoPublicar() {
        return this.cdoPublicar;
    }
    
    public void setCdoPublicar(BigDecimal cdoPublicar) {
        this.cdoPublicar = cdoPublicar;
    }
    public BigDecimal getAbsem6mes() {
        return this.absem6mes;
    }
    
    public void setAbsem6mes(BigDecimal absem6mes) {
        this.absem6mes = absem6mes;
    }
    public BigDecimal getAbsem9mes() {
        return this.absem9mes;
    }
    
    public void setAbsem9mes(BigDecimal absem9mes) {
        this.absem9mes = absem9mes;
    }
    public BigDecimal getAbsem12mes() {
        return this.absem12mes;
    }
    
    public void setAbsem12mes(BigDecimal absem12mes) {
        this.absem12mes = absem12mes;
    }
    public BigDecimal getAbsem15mes() {
        return this.absem15mes;
    }
    
    public void setAbsem15mes(BigDecimal absem15mes) {
        this.absem15mes = absem15mes;
    }
    public BigDecimal getAbsem18mes() {
        return this.absem18mes;
    }
    
    public void setAbsem18mes(BigDecimal absem18mes) {
        this.absem18mes = absem18mes;
    }
    public BigDecimal getAbsem24mes() {
        return this.absem24mes;
    }
    
    public void setAbsem24mes(BigDecimal absem24mes) {
        this.absem24mes = absem24mes;
    }




}


