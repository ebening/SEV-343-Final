package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.business.domain.TblCampanaActividades;

import java.util.List;

/**
 * Created by jdominguez on 2/15/16.
 */
public interface DAOTblActividad extends AbstractDao<TblActividad>{

    public List<TblActividad> getTblActividadAll();

    public List<TblActividad> getTblActividadByMecanica (int idMecanica);

    public List<TblActividad> getTblActividadByResponsable (CatUsuarios responsable);

    public List<TblActividad> getTblActividadByCreador (CatUsuarios creador);

    public List<TblActividad> getTblActividadByCampanaAct (TblCampanaActividades camapanaAct);

    public TblActividad getTblActividadById (int idActividad);

    public TblActividad getTblActividadByMecanicaAndOrden (int idMecanica, int orden);

    public List<TblActividad> getTblActividadForTodayAlert();

    public List<TblActividad> getTblActividadVencidas();

    public List<TblActividad> getTblActividadToOpen();

    public boolean deleteActividadesByIdMecanica(int idmecanica);
}
