package com.adinfi.seven.business.domain;
/** Generated 4/02/2014 05:03:32 PM by Hibernate Tools 3.6.0*/


import java.util.HashSet;
import java.util.Set;

/**
 * TblImagenes generated by hbm2java
 */
public class TblImagenes  implements java.io.Serializable {

	private static final long serialVersionUID = 7104467673666548214L;
	private long idImage;
     private String nombre;
     private String pathFile;
     private Set<TblImageArticulo> tblImageArticulos = new HashSet<TblImageArticulo>(0);

    public TblImagenes() {
    }

	
    public TblImagenes(long idImage, String pathFile) {
        this.idImage = idImage;
        this.pathFile = pathFile;
    }
    public TblImagenes(long idImage, String nombre, String pathFile, Set<TblImageArticulo> tblImageArticulos) {
       this.idImage = idImage;
       this.nombre = nombre;
       this.pathFile = pathFile;
       this.tblImageArticulos = tblImageArticulos;
    }
   
    public long getIdImage() {
        return this.idImage;
    }
    
    public void setIdImage(long idImage) {
        this.idImage = idImage;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPathFile() {
        return this.pathFile;
    }
    
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
    public Set<TblImageArticulo> getTblImageArticulos() {
        return this.tblImageArticulos;
    }
    
    public void setTblImageArticulos(Set<TblImageArticulo> tblImageArticulos) {
        this.tblImageArticulos = tblImageArticulos;
    }




}

