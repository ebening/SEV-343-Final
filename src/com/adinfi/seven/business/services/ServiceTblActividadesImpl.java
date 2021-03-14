package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.persistence.daos.DAOCatActPred;
import com.adinfi.seven.persistence.daos.DAOCatEstatus;
import com.adinfi.seven.persistence.daos.DAOFlujoAct;
import com.adinfi.seven.persistence.daos.DAOTblActividad;
import com.adinfi.seven.presentation.views.util.EtapaDashboard;

import java.util.*;

/**
 * Created by jdominguez on 2/16/16.
 */
public class ServiceTblActividadesImpl implements ServiceTblActividades {

    private DAOTblActividad daoTblActividad;
    private DAOFlujoAct daoFlujoAct;
    private DAOCatActPred daoCatActPred;
    private DAOCatEstatus daoCatEstatus;

    @Override
    public int getDiasActividadesByFlujo(CatFlujoAct catFlujoAct) {
        return daoFlujoAct.getDiasTotalesFlujo(catFlujoAct);
    }

    @Override
    public List<TblActividad> getActividadesToOpen() {
        return daoTblActividad.getTblActividadToOpen();
    }

    @Override
    public List<TblActividad> getActividadesVencidas() {
        return daoTblActividad.getTblActividadVencidas();
    }

    private boolean updateActivity(TblActividad a){
        try {
            daoTblActividad.update(a);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<TblActividad> getForTodayAlert() {
        return daoTblActividad.getTblActividadForTodayAlert();
    }

    /**
     * Metodo para cambiar el estatus por bloque de todas las mecanicas enviadas por el parametro mecanicas
     *
     * @param mecanicas
     * @param estatus
     * @param etapaCerrar
     * @return
     */
    @Override
    public boolean changeEstatusActivityByMecaniaList(List<TblMecanica> mecanicas, CatEstatus estatus, EtapaDashboard etapaCerrar) {
        int orden = 0;
        boolean tudoBom = true;
        switch (etapaCerrar){
            case PENDIENTE: orden = 1; break;
            case CAPTURA: orden = 1; break;
            case REV_PRECIOS: orden = 2; break;
            case DISENO: orden = 3; break;
            case REV_DISENO: orden = 4; break;
            case ENVIO_DISENO: orden = 6; break;
        }
        for (TblMecanica m : mecanicas){
            TblActividad a = daoTblActividad.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), orden);
            if (a == null || !changeEstatusTblAct(a, estatus)){
                tudoBom = false;
            }
            if (orden == 4){
                a = daoTblActividad.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), 5);
                if (!changeEstatusTblAct(a, estatus)){
                    tudoBom = false;
                }
            }
        }
        return tudoBom;
    }

    @Override
    public TblActividad getTblActividadByMecanicaAndOrden(int idMecanica, int orden) {
        return daoTblActividad.getTblActividadByMecanicaAndOrden(idMecanica, orden);
    }

    @Override
    public boolean updateActividad(TblActividad actividad) {
        try {
            daoTblActividad.update(actividad);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean changeEstatusTblAct(TblActividad actividad, CatEstatus estatus) {
        actividad.setCatEstatus(estatus);
        if (estatus.getNombre().contains("Cerrado")){
            actividad.setFechaFin(new Date());
        }else{
            actividad.setFechaFin(null);
        }
        return updateActivity(actividad);
    }

    @Override
    public CatFlujoAct getFlujoById(int id) {
        return daoFlujoAct.getFlujoById(id);
    }

    @Override
    public CatActPred getActById(int id) {
        return daoCatActPred.getActById(id);
    }

    @Override
    public boolean saveActividad(TblActividad actividad) {
        try{
            daoTblActividad.saveOrUpdate(actividad);
            return true;
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean deleteActividad(TblActividad actividad) {
        try {
            daoTblActividad.delete(actividad);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TblActividad> getTblActividadAll() {
        return daoTblActividad.getTblActividadAll();
    }

    @Override
    public List<TblActividad> getTblActividadByMecanica(int idMecanica) {
        List<TblActividad> list = daoTblActividad.getTblActividadByMecanica(idMecanica);
        if (list != null){
            Collections.sort(list, new Comparator<TblActividad>() {
                @Override
                public int compare(TblActividad o1, TblActividad o2) {
                    return o1.getVigenciaInicio().compareTo(o2.getVigenciaInicio());
                }
            });
        }
        return list;
    }

    @Override
    public List<TblActividad> getTblActividadByResponsable(CatUsuarios responsable) {
        return daoTblActividad.getTblActividadByResponsable(responsable);
    }

    @Override
    public List<TblActividad> getTblActividadByCreador(CatUsuarios creador) {
        return daoTblActividad.getTblActividadByCreador(creador);
    }

    @Override
    public List<TblActividad> getTblActividadByCampanaAct(TblCampanaActividades camapanaAct) {
        return daoTblActividad.getTblActividadByCampanaAct(camapanaAct);
    }

    @Override
    public TblActividad getTblActividadById(int idActividad) {
        return daoTblActividad.getTblActividadById(idActividad);
    }

    @Override
    public boolean saveCatFlujoAct(CatFlujoAct catFlujoAct) {
        try{
            daoFlujoAct.save(catFlujoAct);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateCatFlujoAct(CatFlujoAct catFlujoAct) {
        try {
            daoFlujoAct.update(catFlujoAct);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateRelFlujoAct(RelFlujoAct relFlujoAct) {
        try {
            daoFlujoAct.updateRelFlujoAct(relFlujoAct);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<CatFlujoAct> getAllFull() {
        return daoFlujoAct.getAllFull();
    }

    @Override
    public List<RelFlujoAct> getRelFlujoByFlujo(CatFlujoAct flujoAct) {
        return daoFlujoAct.getRelFlujoByFlujo(flujoAct);
    }

    // **************** Getters And Setters ******************** //

    public DAOTblActividad getDaoTblActividad() {
        return daoTblActividad;
    }

    public void setDaoTblActividad(DAOTblActividad daoTblActividad) {
        this.daoTblActividad = daoTblActividad;
    }

    public DAOFlujoAct getDaoFlujoAct() {
        return daoFlujoAct;
    }

    public void setDaoFlujoAct(DAOFlujoAct daoFlujoAct) {
        this.daoFlujoAct = daoFlujoAct;
    }

    public DAOCatActPred getDaoCatActPred() {
        return daoCatActPred;
    }

    public void setDaoCatActPred(DAOCatActPred daoCatActPred) {
        this.daoCatActPred = daoCatActPred;
    }

    public DAOCatEstatus getDaoCatEstatus() {
        return daoCatEstatus;
    }

    public void setDaoCatEstatus(DAOCatEstatus daoCatEstatus) {
        this.daoCatEstatus = daoCatEstatus;
    }
}
