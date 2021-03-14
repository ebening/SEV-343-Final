package com.adinfi.seven.presentation.views;

import com.adinfi.seven.business.domain.CatActPred;
import com.adinfi.seven.business.domain.CatFlujoAct;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.RelFlujoAct;
import com.adinfi.seven.business.services.ServiceCatActPred;
import com.adinfi.seven.business.services.ServiceTblActividades;
import com.adinfi.seven.presentation.views.util.Messages;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jdominguez on 8/19/16.
 */
public class MBFLujo implements Serializable {

    // **************** Services ************** //
    private ServiceTblActividades serviceTblActividades;
    private ServiceCatActPred serviceCatActPred;

    // **************** Variables ************* //
    private List<RelFlujoAct> relFlujoActList;
    private List<CatFlujoAct> catFlujoActList;
    private List<CatActPred> catActPredList;
    private RelFlujoAct selectedRel;

    private boolean update;

    private CatFlujoAct flujoSelected;
    private CatActPred actSelected;

    // *************** Methods *************** //

    @PostConstruct
    public void init(){

        catFlujoActList = serviceTblActividades.getAllFull();
    }

    public void onFlujoSelect(){
        relFlujoActList = new ArrayList<>(flujoSelected.getRelFlujoActs());
        catActPredList = new ArrayList<>();
        for (RelFlujoAct rel : relFlujoActList){
            catActPredList.add(rel.getCatActPred());
        }
        orderActLists();
        actSelected = null;
        selectedRel = null;
    }

    public void onActSelect(){
        for (RelFlujoAct r : relFlujoActList){
            if (r.getCatActPred().getId() == actSelected.getId()){
                selectedRel = r;
                break;
            }
        }
    }

    public void saveEstate(){
        if (serviceTblActividades.updateCatFlujoAct(flujoSelected)){
            if (serviceTblActividades.updateRelFlujoAct(selectedRel)){
                Messages.mensajeSatisfactorio("Informacion Actualizada", "Informacion Actualizada");
            }else{
                Messages.mensajeAlerta("Actualizacion Incompleta", "Flujo Actualizado, Actividad no Actualizada");
            }
        }else{
            Messages.mensajeErroneo("","Error al actualizar la informacion");
        }
        cancelAction();
    }

    public void cancelAction(){
        flujoSelected = null;
        actSelected = null;
        selectedRel = null;
        catActPredList = new ArrayList<>();
        relFlujoActList = new ArrayList<>();
    }

    private void orderActLists(){
        Collections.sort(relFlujoActList, new Comparator<RelFlujoAct>() {
            @Override
            public int compare(RelFlujoAct o1, RelFlujoAct o2) {
                return o1.getCatActPred().getOrden().compareTo(o2.getCatActPred().getOrden());
            }
        });

        Collections.sort(catActPredList, new Comparator<CatActPred>() {
            @Override
            public int compare(CatActPred o1, CatActPred o2) {
                return o1.getOrden().compareTo(o2.getOrden());
            }
        });
    }


    // *************** Getters And Setters *********** //

    public ServiceTblActividades getServiceTblActividades() {
        return serviceTblActividades;
    }

    public void setServiceTblActividades(ServiceTblActividades serviceTblActividades) {
        this.serviceTblActividades = serviceTblActividades;
    }

    public ServiceCatActPred getServiceCatActPred() {
        return serviceCatActPred;
    }

    public void setServiceCatActPred(ServiceCatActPred serviceCatActPred) {
        this.serviceCatActPred = serviceCatActPred;
    }

    public List<RelFlujoAct> getRelFlujoActList() {
    	if(relFlujoActList==null) return null;
        Collections.sort(relFlujoActList, new Comparator<RelFlujoAct>() {
            @Override
            public int compare(RelFlujoAct o1, RelFlujoAct o2) {
                return (o1.getId() > o2.getId()) ? 1 : (o1.getId() == o2.getId()) ? 0 : -1;
            }
        });
        return relFlujoActList;
    }

    public void setRelFlujoActList(List<RelFlujoAct> relFlujoActList) {
        this.relFlujoActList = relFlujoActList;
    }

    public List<CatFlujoAct> getCatFlujoActList() {
        return catFlujoActList;
    }

    public void setCatFlujoActList(List<CatFlujoAct> catFlujoActList) {
        this.catFlujoActList = catFlujoActList;
    }

    public List<CatActPred> getCatActPredList() {
        return catActPredList;
    }

    public void setCatActPredList(List<CatActPred> catActPredList) {
        this.catActPredList = catActPredList;
    }

    public RelFlujoAct getSelectedRel() {
        return selectedRel;
    }

    public void setSelectedRel(RelFlujoAct selectedRel) {
        this.selectedRel = selectedRel;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public CatFlujoAct getFlujoSelected() {

        return flujoSelected;
    }

    public void setFlujoSelected(CatFlujoAct flujoSelected) {
        this.flujoSelected = flujoSelected;
    }

    public CatActPred getActSelected() {
        return actSelected;
    }

    public void setActSelected(CatActPred actSelected) {
        this.actSelected = actSelected;
    }
}
