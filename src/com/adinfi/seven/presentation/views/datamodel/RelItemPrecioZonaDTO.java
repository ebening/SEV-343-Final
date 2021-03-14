/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.presentation.views.datamodel;

import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.RelPrecioItemZona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public class RelItemPrecioZonaDTO implements Serializable{
    private List<RelPrecioItemZona> precioZona;
    private CatItem catItem;

    public RelItemPrecioZonaDTO() {
        precioZona = new ArrayList<>();
    }

    
    public RelItemPrecioZonaDTO(List<RelPrecioItemZona> precioZona, CatItem catItem) {
        this.precioZona = precioZona;
        this.catItem = catItem;
    }

    public CatItem getCatItem() {
        return catItem;
    }

    public void setCatItem(CatItem catItem) {
        this.catItem = catItem;
    }

    public List<RelPrecioItemZona> getPrecioZona() {
        return precioZona;
    }

    public void setPrecioZona(List<RelPrecioItemZona>precioZona) {
        this.precioZona = precioZona;
    }
    
}
