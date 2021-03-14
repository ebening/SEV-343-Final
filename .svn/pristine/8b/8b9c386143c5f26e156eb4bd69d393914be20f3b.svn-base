package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.presentation.views.util.EtapaDashboard;

import java.util.List;

/**
 * Created by jdominguez on 2/16/16.
 */
public interface ServiceTblActividades {

    public int getDiasActividadesByFlujo(CatFlujoAct catFlujoAct);

    public List<TblActividad> getActividadesToOpen();

    public List<TblActividad> getActividadesVencidas();

    public List<TblActividad> getForTodayAlert();

    public List<TblActividad> getTblActividadAll();

    public List<TblActividad> getTblActividadByMecanica (int idMecanica);

    public List<TblActividad> getTblActividadByResponsable (CatUsuarios responsable);

    public List<TblActividad> getTblActividadByCreador (CatUsuarios creador);

    public List<TblActividad> getTblActividadByCampanaAct (TblCampanaActividades camapanaAct);

    public TblActividad getTblActividadById (int idActividad);

    public TblActividad getTblActividadByMecanicaAndOrden(int idMecanica, int orden);

    public boolean changeEstatusTblAct(TblActividad actividad, CatEstatus estatus);

    public boolean changeEstatusActivityByMecaniaList(List<TblMecanica> mecanicas, CatEstatus estatus, EtapaDashboard etapaCerrar);

    public boolean saveActividad(TblActividad actividad);

    public boolean updateActividad(TblActividad actividad);

    public boolean deleteActividad(TblActividad actividad);

    public boolean saveCatFlujoAct(CatFlujoAct catFlujoAct);
    public boolean updateCatFlujoAct(CatFlujoAct catFlujoAct);
    public boolean updateRelFlujoAct(RelFlujoAct relFlujoAct);
    public List<CatFlujoAct> getAllFull();
    public List<RelFlujoAct> getRelFlujoByFlujo(CatFlujoAct flujoAct);

    public CatFlujoAct getFlujoById(int id);
    public CatActPred getActById(int id);


}
