package com.adinfi.seven.business.domain;
// Generated Sep 29, 2014 11:31:29 AM by Hibernate Tools 3.6.0



/**
 * TblCategoria generated by hbm2java
 */
public class TblCategoria  implements java.io.Serializable {


     private int categoriaId;
     private TblMecanica tblMecanica;

    public TblCategoria() {
    }

    public TblCategoria(int categoriaId, TblMecanica tblMecanica) {
       this.categoriaId = categoriaId;
       this.tblMecanica = tblMecanica;
    }
   
    public int getCategoriaId() {
        return this.categoriaId;
    }
    
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    public TblMecanica getTblMecanica() {
        return this.tblMecanica;
    }
    
    public void setTblMecanica(TblMecanica tblMecanica) {
        this.tblMecanica = tblMecanica;
    }




}


