package com.adinfi.seven.business.domain;
// Generated Sep 29, 2014 11:31:29 AM by Hibernate Tools 3.6.0



/**
 * RelCompUpc generated by hbm2java
 */
public class RelCompUpc  implements java.io.Serializable {


     private String upcId;
     private TblComponente tblComponente;

    public RelCompUpc() {
    }

    public RelCompUpc(String upcId, TblComponente tblComponente) {
       this.upcId = upcId;
       this.tblComponente = tblComponente;
    }
   
    public String getUpcId() {
        return this.upcId;
    }
    
    public void setUpcId(String upcId) {
        this.upcId = upcId;
    }
    public TblComponente getTblComponente() {
        return this.tblComponente;
    }
    
    public void setTblComponente(TblComponente tblComponente) {
        this.tblComponente = tblComponente;
    }

}
