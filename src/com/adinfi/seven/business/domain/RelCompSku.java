package com.adinfi.seven.business.domain;
// Generated Sep 29, 2014 11:31:29 AM by Hibernate Tools 3.6.0



/**
 * RelCompSku generated by hbm2java
 */
public class RelCompSku  implements java.io.Serializable {


     private int skuId;
     private TblComponente tblComponente;

    public RelCompSku() {
    }

    public RelCompSku(int skuId, TblComponente tblComponente) {
       this.skuId = skuId;
       this.tblComponente = tblComponente;
    }
   
    public int getSkuId() {
        return this.skuId;
    }
    
    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }
    public TblComponente getTblComponente() {
        return this.tblComponente;
    }
    
    public void setTblComponente(TblComponente tblComponente) {
        this.tblComponente = tblComponente;
    }




}


