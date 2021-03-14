package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author christian
 */
public class Navigator implements Serializable {

    private List<UrlParameter> parametros;
    private String pagina;

    public Navigator() {
        parametros = new ArrayList<>();
    }

    public List<UrlParameter> getParametros() {
        return parametros;
    }

    public void setParametros(List<UrlParameter> parametros) {
        this.parametros = parametros;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

}
