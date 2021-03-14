/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.presentation.views;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.RelPrecioItemGzona;
import com.adinfi.seven.business.domain.RelPrecioItemZona;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatGZone;
import com.adinfi.seven.business.services.ServiceCatItem;
import com.adinfi.seven.business.services.ServiceCatZone;
import com.adinfi.seven.business.services.ServiceRelPrecioItemGZone;
import com.adinfi.seven.business.services.ServiceRelPrecioItemZone;
import com.adinfi.seven.presentation.views.datamodel.RelItemPrecioZonaDTO;
import com.adinfi.seven.presentation.views.util.MBUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author jdominguez
 */
public class MBEstrategia implements Serializable{
    private static final long serialVersionUID = 6577370056533253265L;
    private ServiceRelPrecioItemGZone servicePrecioGZone;
    private ServiceRelPrecioItemZone servicePrecioZone;
    private ServiceCatItem serviceCatItem;
    private ServiceCatCategory serviceCatCategory;
    private ServiceCatZone serviceCatZone;
    private ServiceCatGZone serviceCatGZone;
    
    private List<SelectItem> lcategorias;
    private List<SelectItem> ldescrips;
    private List<RelItemPrecioZonaDTO> preciosForSelectedItems;
    private List<String> litemSelected;
    
    private List<RelPrecioItemZona> precioItemsZone;
    private List<RelPrecioItemGzona>precioItemsGZone;
    
    private List<CatGZone> gzoneslist;
    private List<CatZone> zoneslist;
    private String[] gzoneSelected;
    private String[] zoneSelected;
    
    private int categoSelected;
    private int descripSelected;

    public MBEstrategia() {
        
    }
    
    @PostConstruct
    public void init(){
        try {
            lcategorias = MBUtil.getSelectItemsCategory(serviceCatCategory);
            gzoneslist = serviceCatGZone.getCatGZoneList();
            ldescrips = new ArrayList<>();
            litemSelected = new ArrayList<>();
            precioItemsZone = new ArrayList<>();
            preciosForSelectedItems = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(MBEstrategia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTableBySelectedZones (){
        preciosForSelectedItems = new ArrayList<>();
        for(String item : litemSelected){
            for (String zone : zoneSelected){
                List<RelPrecioItemZona> precios = servicePrecioZone.getRelByItemAndZone(item, zone);
                RelItemPrecioZonaDTO ripzDTO = new RelItemPrecioZonaDTO();
                ripzDTO.setCatItem(precios.get(0).getCatItem());
                ripzDTO.setPrecioZona(precios);
                if (preciosForSelectedItems.isEmpty() || addRelItemPrecioZonaDTOtoList(ripzDTO)){
                    preciosForSelectedItems.add(ripzDTO);
                }
            }
        }
    }
    
    private boolean addRelItemPrecioZonaDTOtoList (RelItemPrecioZonaDTO item){
        for (RelItemPrecioZonaDTO r : preciosForSelectedItems){
            if (r.getCatItem().getIdItem().equals(item.getCatItem().getIdItem())){
                r.getPrecioZona().addAll(item.getPrecioZona());
                return false;
            }
        }
        return true; 
    }
    
    public void fillDataBaseInfoGZone(){
        try {
            List<CatItem> items = serviceCatItem.getCatItemList();
            List<CatGZone> zones = serviceCatGZone.getCatGZoneList();
            for (CatItem item : items){
                for (CatGZone z : zones){
                    RelPrecioItemGzona r = new RelPrecioItemGzona(0, z, item, 26.50);
                    servicePrecioGZone.saveRelItemGZone(r);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MBEstrategia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fillDataBaseInfo(){
        try {
            List<CatItem> items = serviceCatItem.getCatItemList();
            List<CatZone> zones = serviceCatZone.getCatZoneList();
            for(CatItem i : items){
                for(CatZone z : zones){
                    RelPrecioItemZona r = new RelPrecioItemZona(0, i, z, 25.50);
                    servicePrecioZone.saveRelPrecioItemZona(r);
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MBEstrategia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void fillPrecioZoneTable(){
        preciosForSelectedItems = new ArrayList<>();
        for (String item : litemSelected){          
            List<RelPrecioItemZona> precios = servicePrecioZone.getAllRelPrecioByCode(item);
            RelItemPrecioZonaDTO ripzDTO = new RelItemPrecioZonaDTO();
            ripzDTO.setCatItem(precios.get(0).getCatItem());
            ripzDTO.setPrecioZona(precios);
            preciosForSelectedItems.add(ripzDTO);
        }
    }
    
    public void fillItemTable(){
        String itemSelected = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemToTable");
        System.out.println(itemSelected);
        precioItemsGZone = servicePrecioGZone.getAllRelITemGZoneByItemCode(itemSelected);
    }
    
    public void deleteItemFromList(){
        String itemSelected = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemSelected");
//        System.out.println(itemSelected);
        String itemtoDelete = null;
        for(String si : litemSelected){
            if(si.equals(itemSelected)){
                itemtoDelete = si;
                break;
            }
        }
        if (itemtoDelete != null)
            litemSelected.remove(itemtoDelete);
    }
    
    public void addItemToList(){
//        System.out.println(descripSelected);
        for (SelectItem si : ldescrips){
            if (si.getValue().equals(String.valueOf(descripSelected))){
                litemSelected.add(si.getLabel());
                break;
            }
        }
        ldescrips = new ArrayList<>();
        categoSelected = -1;
    }
    
    public void fillComboDescEstrategia(){
        System.out.println(categoSelected);
        List<CatItem> items = serviceCatItem.getCatItemByCategory(categoSelected);
        ldescrips = new ArrayList<>();
        for (CatItem item : items){
            ldescrips.add(new SelectItem(item.getIdItem(), item.getCode()));
        }
    }

    public void changeGrupoZonasEstr() {
        List<CatZone> catZoneList;
        zoneslist = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        if (gzoneSelected != null) {
            for (String gz : gzoneSelected) {
            	ids.add(Integer.valueOf(gz));
            }
            catZoneList = serviceCatZone.getCatZonesByGrupoZonas(ids);
            if (catZoneList != null) {
                    zoneslist.addAll(catZoneList);
            } else {
                    zoneslist = new ArrayList<>();
            }
        }
        if (zoneSelected != null) {
                zoneSelected = null;
        }
    }

    public ServiceRelPrecioItemGZone getServicePrecioGZone() {
        return servicePrecioGZone;
    }

    public void setServicePrecioGZone(ServiceRelPrecioItemGZone servicePrecioGZone) {
        this.servicePrecioGZone = servicePrecioGZone;
    }

    public ServiceRelPrecioItemZone getServicePrecioZone() {
        return servicePrecioZone;
    }

    public void setServicePrecioZone(ServiceRelPrecioItemZone servicePrecioZone) {
        this.servicePrecioZone = servicePrecioZone;
    }

    public ServiceCatItem getServiceCatItem() {
        return serviceCatItem;
    }

    public void setServiceCatItem(ServiceCatItem serviceCatItem) {
        this.serviceCatItem = serviceCatItem;
    }

    public ServiceCatCategory getServiceCatCategory() {
        return serviceCatCategory;
    }

    public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
        this.serviceCatCategory = serviceCatCategory;
    }

    public List<SelectItem> getLcategorias() {
        return lcategorias;
    }

    public void setLcategorias(List<SelectItem> lcategorias) {
        this.lcategorias = lcategorias;
    }

    public List<SelectItem> getLdescrips() {
        return ldescrips;
    }

    public void setLdescrips(List<SelectItem> ldescrips) {
        this.ldescrips = ldescrips;
    }

    public int getCategoSelected() {
        return categoSelected;
    }

    public void setCategoSelected(int categoSelected) {
        this.categoSelected = categoSelected;
    }

    public int getDescripSelected() {
        return descripSelected;
    }

    public void setDescripSelected(int descripSelected) {
        this.descripSelected = descripSelected;
    }

    public List<String> getLitemSelected() {
        return litemSelected;
    }

    public void setLitemSelected(List<String> litemSelected) {
        this.litemSelected = litemSelected;
    }

    public List<RelPrecioItemZona> getPrecioItemsZone() {
        return precioItemsZone;
    }

    public void setPrecioItemsZone(List<RelPrecioItemZona> precioItemsZone) {
        this.precioItemsZone = precioItemsZone;
    }

    public List<RelPrecioItemGzona> getPrecioItemsGZone() {
        return precioItemsGZone;
    }

    public void setPrecioItemsGZone(List<RelPrecioItemGzona> precioItemsGZone) {
        this.precioItemsGZone = precioItemsGZone;
    }

    public List<RelItemPrecioZonaDTO> getPreciosForSelectedItems() {
        return preciosForSelectedItems;
    }

    public void setPreciosForSelectedItems(List<RelItemPrecioZonaDTO> preciosForSelectedItems) {
        this.preciosForSelectedItems = preciosForSelectedItems;
    }

    

    public ServiceCatZone getServiceCatZone() {
        return serviceCatZone;
    }

    public void setServiceCatZone(ServiceCatZone serviceCatZone) {
        this.serviceCatZone = serviceCatZone;
    }

    public ServiceCatGZone getServiceCatGZone() {
        return serviceCatGZone;
    }

    public void setServiceCatGZone(ServiceCatGZone serviceCatGZone) {
        this.serviceCatGZone = serviceCatGZone;
    }

    public String[] getGzoneSelected() {
        return gzoneSelected;
    }

    public void setGzoneSelected(String[] gzoneSelected) {
        this.gzoneSelected = gzoneSelected;
    }

    public String[] getZoneSelected() {
        return zoneSelected;
    }

    public void setZoneSelected(String[] zoneSelected) {
        this.zoneSelected = zoneSelected;
    }

    public List<CatGZone> getGzoneslist() {
        return gzoneslist;
    }

    public void setGzoneslist(List<CatGZone> gzoneslist) {
        this.gzoneslist = gzoneslist;
    }

    public List<CatZone> getZoneslist() {
        return zoneslist;
    }

    public void setZoneslist(List<CatZone> zoneslist) {
        this.zoneslist = zoneslist;
    }

    
    
    
}
