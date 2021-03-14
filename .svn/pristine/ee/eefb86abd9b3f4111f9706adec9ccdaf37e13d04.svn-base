package com.adinfi.seven.persistence.dto;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

import com.adinfi.seven.business.domain.RelDisenoSenal;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class DisenosDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int disenoId;
    private int programaId;
    private String programaStr;
    private int pkMec;
    private String mecanicaStr;
    //private Integer senalamientoId;
    private List<RelDisenoSenal> senalList;
    private String senalamientoStr;
    private byte[] image;
    private StreamedContent imageStream;
    private String imageStr;
    private Integer preciosAuth;
    private String preciosComenta;
    private Integer categoryAuth;
    private String categoryComenta;
    private Integer estatusPrecios;
    private Integer estatusDiseno;
    private List<RelObj> grupoZonaLst;
    private List<RelObj> zonaLst;
    private List<RelObj> storeLst;
    private List<String> grupoZonas;
    private List<String> zonas;
    private List<String> tiendas;
    private boolean selected;

    public static class RelObj {
            private int id;
            private String str;

            public int getId() {
                    return id;
            }

            public void setId(int id) {
                    this.id = id;
            }

            public String getStr() {
                    return str;
            }

            public void setStr(String str) {
                    this.str = str;
            }
    }

    public String getGrupoZonaStr() {
            return getStr(grupoZonaLst);
    }

    public String getZonaStr() {
            return getStr(zonaLst);
    }

    public String getStoreStr() {
            return getStr(storeLst);
    }

    public String getStr(List<RelObj> lst) {
            if(lst.size() > 2){
                return "Varias";
            }
            StringBuilder value = new StringBuilder("");
            if (lst != null) {
                    for (RelObj obj : lst) {
                            value.append(obj.getStr());
                            value.append(",");
                    }
                    if (value.length() > 0) {
                            value.deleteCharAt(value.length() - 1);
                    }
            }
            return value.toString();
    }

    public boolean getPreciosAuthB() {
            Boolean bAuth = Boolean.FALSE;
            if (preciosAuth != null && preciosAuth.intValue() == 1) {
                    bAuth = Boolean.TRUE;
            }
            return bAuth;
    }

    public String getPreciosAuthBStr() {
        if(preciosAuth == null){
            return "PENDIENTE";
        }
            String auth = "RECHAZADO";
            if (getPreciosAuthB()) {
                    auth = "AUTORIZADO";
            }
            return auth;
    }

    public boolean getCategoryAuthB() {
            Boolean bAuth = Boolean.FALSE;
            if (categoryAuth != null && categoryAuth.intValue() == 1) {
                    bAuth = Boolean.TRUE;
            }
            return bAuth;
    }

    public String getCategoryAuthBStr() {
        if(categoryAuth == null){
            return "PENDIENTE";
        }
            String auth = "RECHAZADO";
            if (getCategoryAuthB()) {
                    auth = "AUTORIZADO";
            }
            return auth;
    }

    public boolean getAuth() {
            Boolean bAuth = Boolean.FALSE;
            if (getPreciosAuthB() && getCategoryAuthB()) {
                    bAuth = Boolean.TRUE;
            }
            return bAuth;
    }

    public String getAuthStr() {
        if(categoryAuth == null || preciosAuth == null){
            return "PENDIENTE";
        }
            String auth = "RECHAZADO";
            if (getAuth()) {
                    auth = "AUTORIZADO";
            }
            return auth;
    }

    public int getDisenoId() {
            return disenoId;
    }

    public void setDisenoId(int disenoId) {
            this.disenoId = disenoId;
    }

    public int getProgramaId() {
            return programaId;
    }

    public void setProgramaId(int programaId) {
            this.programaId = programaId;
    }

    public int getPkMec() {
            return pkMec;
    }

    public void setPkMec(int pkMec) {
            this.pkMec = pkMec;
    }

    public List<RelDisenoSenal> getSenalList() {
        return senalList;
    }

    public void setSenalList(List<RelDisenoSenal> senalList) {
        this.senalList = senalList;
    }

    /*  public Integer getSenalamientoId() {
            return senalamientoId;
    }

    public void setSenalamientoId(Integer senalamientoId) {
            this.senalamientoId = senalamientoId;
    } */

    public byte[] getImage() {
            return image;
    }

    public void setImage(byte[] image) {
            this.image = image;
    }

    public StreamedContent getImageDinamica() {
            if (image == null) {
                    return new DefaultStreamedContent(new ByteArrayInputStream(new byte[0]));
            }
            return new DefaultStreamedContent(new ByteArrayInputStream(getImage()));
    }

    public Integer getPreciosAuth() {
            return preciosAuth;
    }

    public void setPreciosAuth(Integer preciosAuth) {
            this.preciosAuth = preciosAuth;
    }

    public String getPreciosComenta() {
            return preciosComenta;
    }

    public void setPreciosComenta(String preciosComenta) {
            this.preciosComenta = preciosComenta;
    }

    public Integer getCategoryAuth() {
            return categoryAuth;
    }

    public void setCategoryAuth(Integer categoryAuth) {
            this.categoryAuth = categoryAuth;
    }

    public String getCategoryComenta() {
            return categoryComenta;
    }

    public void setCategoryComenta(String categoryComenta) {
            this.categoryComenta = categoryComenta;
    }

    public Integer getEstatusPrecios() {
            return estatusPrecios;
    }

    public void setEstatusPrecios(Integer estatusPrecios) {
            this.estatusPrecios = estatusPrecios;
    }

    public Integer getEstatusDiseno() {
            return estatusDiseno;
    }

    public void setEstatusDiseno(Integer estatusDiseno) {
            this.estatusDiseno = estatusDiseno;
    }

    public String getProgramaStr() {
            return programaStr;
    }

    public void setProgramaStr(String programaStr) {
            this.programaStr = programaStr;
    }

    public String getMecanicaStr() {

        return mecanicaStr;
    }

    public void setMecanicaStr(String mecanicaStr) {
            this.mecanicaStr = mecanicaStr;
    }

    public String getSenalamientoStr() {
        StringBuilder sb = new StringBuilder();
        if (!senalList.isEmpty())  {
            for (RelDisenoSenal relDisenoSenal : senalList){
                sb.append(relDisenoSenal.getCatSenal().getNombre()).append(",");
            }
            return sb.toString();
        }else{
            return "";
        }
    }

    public void setSenalamientoStr(String senalamientoStr) {
            this.senalamientoStr = senalamientoStr;
    }

    public StreamedContent getImageStream() {
            return imageStream;
    }

    public void setImageStream(StreamedContent imageStream) {
            this.imageStream = imageStream;
    }

    public String getImageStr() {
            return imageStr;
    }

    public void setImageStr(String imageStr) {
            this.imageStr = imageStr;
    }

    public List<RelObj> getGrupoZonaLst() {
            return grupoZonaLst;
    }

    public void setGrupoZonaLst(List<RelObj> grupoZonaLst) {
            this.grupoZonaLst = grupoZonaLst;
    }

    public List<RelObj> getZonaLst() {
            return zonaLst;
    }

    public void setZonaLst(List<RelObj> zonaLst) {
            this.zonaLst = zonaLst;
    }

    public List<RelObj> getStoreLst() {
            return storeLst;
    }

    public void setStoreLst(List<RelObj> storeLst) {
            this.storeLst = storeLst;
    }

    public List<String> getGrupoZonas() {
            return grupoZonas;
    }

    public void setGrupoZonas(List<String> grupoZonas) {
            this.grupoZonas = grupoZonas;
    }

    public List<String> getZonas() {
            return zonas;
    }

    public void setZonas(List<String> zonas) {
            this.zonas = zonas;
    }

    public List<String> getTiendas() {
            return tiendas;
    }

    public void setTiendas(List<String> tiendas) {
            this.tiendas = tiendas;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
