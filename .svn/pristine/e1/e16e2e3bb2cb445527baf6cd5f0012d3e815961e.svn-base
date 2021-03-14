package com.adinfi.seven.scheduler;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatEstatus;
import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.services.*;
import com.adinfi.seven.presentation.views.util.EtapaDashboard;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by jdominguez on 9/14/16.
 */
public class ActividadesJob implements Job {

    private ServiceTblActividades serviceTblActividades;
    private ServiceUsuarios serviceUsuarios;
    private ServiceCatEstatus serviceCatEstatus;
    private ServiceCampana serviceCampana;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        serviceTblActividades = (ServiceTblActividades) context.getJobDetail().getJobDataMap().get("serviceTblActividades");
        serviceUsuarios = (ServiceUsuarios) context.getJobDetail().getJobDataMap().get("serviceUsuarios");
        serviceCatEstatus = (ServiceCatEstatus) context.getJobDetail().getJobDataMap().get("serviceCatEstatus");
        serviceCampana = (ServiceCampana) context.getJobDetail().getJobDataMap().get("serviceCampana");
        List<TblActividad> actividadList = serviceTblActividades.getForTodayAlert();
        for (TblActividad a : actividadList){
            CatUsuarios jefe = serviceUsuarios.getUsuarioById(a.getCatUsuariosByIdresponsable().getIdjefe());
            String[] toMails = {a.getCatUsuariosByIdresponsable().getEmail(), jefe.getEmail()};
            String subject = "ALERTA - Actividad " + a.getDescripcion() + " de la mecanica " + a.getTblMecanica().getNombreMecanica();
            String body = "Este correo solo es un recordatoria para terminar la actividad " + a.getDescripcion() + " de la " +
                    "mecanica " + a.getTblMecanica().getNombreMecanica();
            SendMail.sendGenericEmail(toMails, subject, body );
        }
        openProcess();
        checkVencimientos();
    }

    private void openProcess (){
        List<TblActividad> list = serviceTblActividades.getActividadesToOpen();
        CatEstatus abierta = serviceCatEstatus.getEstatusByNameContains("Abier");
        for (TblActividad a : list){
            a.setCatEstatus(abierta);
            if (!serviceTblActividades.updateActividad(a)){
                System.out.println("Error al abrir actividad");
            }
            if (a.getOrden() == 1){
                String[] toMails = {a.getCatUsuariosByIdresponsable().getEmail(), Util.getCoordinadorEmail(serviceUsuarios)};
                String subject = "Actividad Abierta: " + a.getDescripcion();
                String body = "Actividad " + a.getDescripcion() + " de la mecanica " + a.getTblMecanica().getNombreMecanica() + "  se ha iniciado ";
                SendMail.sendGenericEmail(toMails, subject, body);
            }
        }
    }

    private void checkVencimientos(){
        List<TblActividad> actividadList = serviceTblActividades.getActividadesVencidas();
        CatEstatus vencida = serviceCatEstatus.getEstatusByNameContains("Venci");
        for (TblActividad a : actividadList){
            a.setCatEstatus(vencida);
            if (!serviceTblActividades.updateActividad(a)){
                System.out.println("Error al actualizar actividad vencida");
            }
            TblCampanaProgramas programa = serviceCampana.getProgramaById(a.getTblMecanica().getCampana().getIdCampana(), a.getTblMecanica().getProgramaId());
            if (!programa.getEtapa().equals(Util.convertProgramaEtapa(EtapaDashboard.VENCIDA))){
                programa.setEtapa(Util.convertProgramaEtapa(EtapaDashboard.VENCIDA));
                try {
                    serviceCampana.updateCampanaProgramas(programa);
                    CatUsuarios jefe = serviceUsuarios.getUsuarioById(a.getCatUsuariosByIdcreador().getIdjefe());
                    String[] toMails = {a.getCatUsuariosByIdresponsable().getEmail(), jefe.getEmail(), Util.getCoordinadorEmail(serviceUsuarios)};
                    String subject = "Actividad Vencida " + a.getDescripcion();
                    String body = "La Actividad " + a.getDescripcion() + "de la Mecanica " + a.getTblMecanica().getNombreMecanica() +
                            " ha Vencido, por lo que se requiere su inmediata atencion. El Programa " + programa.getDescripcion() +" " +
                            " ha sido marcado como Vencido.";
                    SendMail.sendGenericEmail(toMails, subject, body);
                } catch (GeneralException e) {
                    System.out.println("Error al cambiar estatus de programa: " + e.getMessage());
                }
            }
        }
    }
}
