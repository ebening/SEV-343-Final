package com.adinfi.seven.business.domain;
// Generated Mar 21, 2016 4:56:19 PM by Hibernate Tools 4.3.1



/**
 * RelDisenoSenal generated by hbm2java
 */
public class RelDisenoSenal  implements java.io.Serializable {


     private int id;
     private CatSenal catSenal;
     private TblDisenos tblDisenos;

    public RelDisenoSenal() {
    }

	
    public RelDisenoSenal(int id) {
        this.id = id;
    }
    public RelDisenoSenal(int id, CatSenal catSenal, TblDisenos tblDisenos) {
       this.id = id;
       this.catSenal = catSenal;
       this.tblDisenos = tblDisenos;
    }


   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public CatSenal getCatSenal() {
        return this.catSenal;
    }
    
    public void setCatSenal(CatSenal catSenal) {
        this.catSenal = catSenal;
    }
    public TblDisenos getTblDisenos() {
        return this.tblDisenos;
    }
    
    public void setTblDisenos(TblDisenos tblDisenos) {
        this.tblDisenos = tblDisenos;
    }




}


