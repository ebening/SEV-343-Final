package com.adinfi.seven.presentation.views;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.ValueChangeEvent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.hibernate.Hibernate;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.MenuModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatEstatus;
import com.adinfi.seven.business.domain.CatRole;
import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.business.domain.TblCategoria;
import com.adinfi.seven.business.domain.TblMecanica;
import com.adinfi.seven.business.services.ServiceArquitecturaSeven;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceCatActPred;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatEstatus;
import com.adinfi.seven.business.services.ServiceCatRole;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceTblActividades;
import com.adinfi.seven.business.services.ServiceUsuarios;
import com.adinfi.seven.persistence.dto.CampanaResultDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.presentation.views.exporter.PDFCustomExporter;
import com.adinfi.seven.presentation.views.exporter.XLSCustomExporter;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.EtapaDashboard;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;
import com.sun.faces.component.visit.FullVisitContext;

/**
 * Created by jdominguez on 2/22/16.
 */
public class MBActividad implements Serializable {

    // ******************* Services ************************* //
    private ServiceCampana serviceCampana;
    private ServiceCatActPred serviceCatActPred;
    private ServiceTblActividades serviceTblActividades;
    private ServiceUsuarios serviceUsuarios;
    private ServiceCatEstatus serviceCatEstatus;
    private ServiceArquitecturaSeven serviceArquitecturaSeven;
    private ServiceCatCategory serviceCatCategory;
    private ServiceDynamicCatalogs serviceDynamicCatalogs;
    private ServiceCatRole serviceCatRole;


    // ****************** Listas *************************** //
    private List<TblCampana> eventoList;
    private List<DashboardItem> dashboardList;
    private List<TblActividad> actividadsMecanicasTable;
    private List<TblActividad> useractividades;
    private List<TblActividad> tblActividadListToday;
    private List<CatEstatus> estatusList;
    private List<CatUsuarios> usuariosList;
    private List<TblMecanica> mecanicaList;
    private List<MecanicasActivities> mecanicasActivities;
    private List<TipoCampanaDTO> tipoCampanaDTOs;
    private List<CatUsuarios> usuariosByRole;
    private List<CatRole> roleList;
    private List<PeriodoDTO> periodos;
    private List<PeriodoDTO> periodosFull;
    private List<Integer> anos;

    // ***************** Selected ***************************** //
    private TblActividad actividadMecanicaSelected;
    private TblActividad[] actividadesSelected;
    private List<TblMecanica> mecanicaSelectedList;
    private DashboardItem dashboardItemSelected;
    private CatCategory catCategorySelected;
    private TblCampanaProgramas tblProgramaSelected;
    private CatRole roleSelected;
    private CatUsuarios usuarioSelected;
    private PeriodoDTO periodoSelected;
    private Integer anoSelected;
    private TblMecanica[] mecSelected;
    private String programaSelected;
    private TblMecanica mecanicaSeleccionada;

    // ***************** Menu y Calendario ******************** //
    private MenuModel model;
    private MenuModel modelActivities;

    private ScheduleModel scheduleActivities;
    private ScheduleEvent event = new DefaultScheduleEvent();

    // ***************** Filtros ****************************** //
    private Date startDate;
    private Date endDate;
    private String periodoFilter;
    private TipoCampanaDTO tipoCampanaSelected;
    private int estatusSelected;

    // ****************** Nueva / Editar Actividad ***************** //

    private TblActividad actividadSelected;
    private String mecanicaSelected;

    // ****************** Otras Variables ********************* //
    private CatUsuarios userLogged;
    private CatUsuarios userBoss;
    private CatUsuarios userSelected;

    private String etapaProgrma;

    private Date today = new Date();

    private boolean detalleView;
    private boolean buttonOpenAct;
    private boolean update;
    private boolean admDiseno;
    private boolean disabledDates;

    @PostConstruct
    public void init(){
        detalleView = false;
        update = false;
        disabledDates = false;
        initAnos();
        userLogged = Util.getSessionAttribute("userLoged");
        admDiseno = userLogged.getCatRole().getIdrole() == 4;
        userBoss = serviceUsuarios.getUsuarioById(userLogged.getIdjefe());
        useractividades = serviceTblActividades.getTblActividadByResponsable(userLogged);
        estatusList = serviceCatEstatus.getEstatusAll();
        for (CatEstatus e : estatusList){
            if (e.getNombre().equalsIgnoreCase("Abierto")){
                estatusSelected = e.getIdestatus();
                break;
            }
        }
        actividadSelected = new TblActividad();
        try {
            periodosFull = MBUtil.getPeriodos(serviceDynamicCatalogs);
            filterPeriodosByAno();
        } catch (Exception e) {
            System.out.println(e);
        }
        buttonOpenAct = true;
        try {
            tipoCampanaDTOs = MBUtil.getTipoCampanas(serviceDynamicCatalogs);
        }catch (Exception e){
            System.out.println(e);
        }
        dashboardList = new ArrayList<>();
        filterEventosList();
    }

    public void filterPeriodosByAno(){
        periodos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (PeriodoDTO p : periodosFull){
            calendar.setTime(p.getFechaInicial());
            if (calendar.get(Calendar.YEAR) == anoSelected.intValue()){
                periodos.add(p);
            }
        }
    }

    public void initAnos(){
        anos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, -1);
        anos.add(calendar.get(Calendar.YEAR));
        calendar.add(Calendar.YEAR, 1);
        anoSelected = calendar.get(Calendar.YEAR);
        anos.add(anoSelected);
        calendar.add(Calendar.YEAR, 1);
        anos.add(calendar.get(Calendar.YEAR));
    }

    public void pdfExportTable(DataTable dataTable, String fileName){
        FacesContext context = FacesContext.getCurrentInstance();
        Exporter exporter = new PDFCustomExporter();
        try {
            exporter.export(context, dataTable, fileName, false, false, "UTF-8", null, null);
        } catch (IOException e) {
            System.out.println(e);
        }
        context.responseComplete();
    }

    public void xlsExportTable(DataTable dataTable, String fileName){
        FacesContext context = FacesContext.getCurrentInstance();
        Exporter exporter = new XLSCustomExporter();
        try {
            exporter.export(context, dataTable, fileName, false, false, "UTF-8", null, null);
        } catch (IOException e) {
            System.out.println(e);
        }
        context.responseComplete();
    }

    public UIComponent findComponent(final String id) {

        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if(component.getId().equals(id)){
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });

        return found[0];
    }

    public void onPeriodoSelect(){
        if (periodoSelected == null){
            startDate = new Date();
            endDate = new Date();
            disabledDates = false;
        }else{
            startDate = periodoSelected.getFechaInicial();
            endDate = periodoSelected.getFechaFinal();
            disabledDates = true;
        }

    }

    public void onAnoSelect(){
        startDate = null;
        endDate = null;
        periodoSelected = null;
        filterPeriodosByAno();
        filterEventosList();
    }

    public void filterEventosList(){
        int tipo = tipoCampanaSelected != null ? tipoCampanaSelected.getId() : 0;
        eventoList = serviceCampana.getAllCampanaByDashboardFilters(periodoFilter, tipo, estatusSelected, startDate, endDate, periodoSelected == null ? 0 : periodoSelected.getId().intValue(),anoSelected);
        getCampanas();
    }

    private void getCampanas(){
        dashboardList = new ArrayList<>();
        for (TblCampana campana : eventoList){
            DashboardItem item = new DashboardItem(campana);
            item.setTipoCampanaDTO(getTipoById(campana.getIdTipoCampana()));
            item.setEtapa("Captura Promociones");
            if (campana.getIdPeriodo() == null){
                item.setPeriodoStr("Del " + Util.fechaFormatComplete(campana.getFechaInicio()) + " Al " + Util.fechaFormatComplete(campana.getFechaFin()));
            }else{
                for (PeriodoDTO p : periodos){
                    if (p.getId().intValue() == campana.getIdPeriodo().intValue()){
                        item.setPeriodoStr(p.getShortDescription());
                        break;
                    }
                }
            }
            //item.setProgramasList(serviceCampana.getCampanaProgramas(campana.getIdCampana()));
            item.setProgramasList(new ArrayList<>(campana.getTblCampanaProgramas()));
            dashboardList.add(item);
        }
    }

    private TipoCampanaDTO getTipoById(int idTipoCampana){
        if (tipoCampanaDTOs != null){
            for (TipoCampanaDTO tc : tipoCampanaDTOs){
                if (tc.getId() == idTipoCampana){
                    return tc;
                }
            }
        }
        return null;
    }

    public void onNewButton(){
        mecanicaList = serviceArquitecturaSeven.getMecanicasByCampana((int)dashboardItemSelected.getTblCampana().getIdCampana());
        try {
            roleList = serviceCatRole.getRoleAll();
            RequestContext.getCurrentInstance().execute("dashBoardEditDialog.show();");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (update){
            mecanicaSelected = actividadSelected.getTblMecanica().getNombreMecanica();
            roleSelected = actividadSelected.getCatRole();
            onRoleChangeDialog();
        }else {
            actividadSelected = new TblActividad();
            actividadSelected.setVigenciaFinal(new Date());
            actividadSelected.setVigenciaInicio(new Date());
        }
    }

    public void onEditButton(TblActividad a){
        update = true;
        actividadSelected = a;
        onNewButton();
    }

    public void onRoleChangeDialog(){
        usuariosByRole = serviceUsuarios.getUsuariosByRoleAndCategory(roleSelected.getIdrole(), dashboardItemSelected.getCatCategoryList().get(0).getIdCategory());
    }

    public void deleteActividad(TblActividad a){
        if (serviceTblActividades.deleteActividad(a)){
            Messages.mensajeSatisfactorio("Actividad Eliminada", "");
            for (MecanicasActivities ma : mecanicasActivities){
                boolean delete = false;
                if (ma.getMecanica().getMecanicaId() == a.getTblMecanica().getMecanicaId()){
                    for (TblActividad ta : ma.actividadList){
                        if (ta.equals(a)){
                            delete = true;
                            break;
                        }
                    }
                }
                if (delete){
                    ma.getActividadList().remove(a);
                    break;
                }
            }
        }else{
            Messages.mensajeErroneo("Error al eliminar actividad", "");
        }
    }

    public void saveActividad(){
        if (update){
            if (actividadSelected.getCatEstatus().getNombre().equalsIgnoreCase("Cerrado")){
                actividadSelected.setFechaFin(new Date());
            }else{
                actividadSelected.setFechaFin(null);
            }
            if (serviceTblActividades.updateActividad(actividadSelected)){
                Messages.mensajeSatisfactorio("Actividad Actualizada", "");
                actividadSelected = new TblActividad();
            }else {
                Messages.mensajeErroneo("Error al actualizar actividad", "");
            }
        }else {
          /*  for (TblMecanica m : mecanicaList){
                if (m.getNombreMecanica().equals(mecanicaSelected)){
                    actividadSelected.setTblMecanica(m);
                    break;
                }
            } */
            actividadSelected.setTblMecanica(mecanicaSeleccionada);
            actividadSelected.setOrden(0);
            actividadSelected.setFechaCreacion(new Date());
            actividadSelected.setCatUsuariosByIdcreador(userLogged);
            actividadSelected.setIdactbefore(0);
            actividadSelected.setAvance(0);
            actividadSelected.setAlerta(actividadSelected.getVigenciaFinal());
            actividadSelected.setCatRole(roleSelected);
            actividadSelected.setEstatusEscalable(0);
            actividadSelected.setNivelEscalable(0);
            if (serviceTblActividades.saveActividad(actividadSelected)){
                Messages.mensajeSatisfactorio("Actividad Guardada", "");
                for (MecanicasActivities ma : mecanicasActivities){
                  //  if (ma.getMecanica().getNombreMecanica().equals(mecanicaSelected)){
                    if (ma.getMecanica().getMecanicaId() == mecanicaSeleccionada.getMecanicaId()){
                        ma.getActividadList().add(actividadSelected);
                        break;
                    }
                }
                actividadSelected = new TblActividad();
            }else {
                Messages.mensajeErroneo("Error al guardar actividad", "");
            }
        }
    }

    private CatUsuarios userBefore;

    public void onDelegarButton(TblActividad actividad){
        actividadMecanicaSelected = actividad;
        int idRole = actividadMecanicaSelected.getCatRole().getIdrole();
        if (userLogged.getCatRole().getIdrole() == 4 && idRole < 4){
            Messages.mensajeErroneo("Administrador de Diseño solo puede delegar actividad de diseño", "");
            return;
        }
        CatUsuarios userCreador = serviceUsuarios.getUsuarioById(actividadMecanicaSelected.getCatUsuariosByIdcreador().getIdusuarios());
        List<RelUsuariosCategorias> categoriasList = new ArrayList<>(userCreador.getRelUsuariosCategoriases());
        int idCategory = categoriasList.get(0).getCatCategory().getIdCategory();
        usuariosList = serviceUsuarios.getUsuariosByRoleAndCategory(idRole == 4 ? 5 : idRole, idCategory);
        userBefore = actividad.getCatUsuariosByIdresponsable();
        RequestContext.getCurrentInstance().execute("delegarDialog.show();");
    }

    public void delegarAction(){
        if (serviceTblActividades.saveActividad(actividadMecanicaSelected)){
            Messages.mensajeSatisfactorio("Actividad delegada correctamente", "");
            String[] mailTo = {userBefore.getEmail(), actividadMecanicaSelected.getCatUsuariosByIdresponsable().getEmail(), actividadMecanicaSelected.getCatUsuariosByIdcreador().getEmail()};
            String subject = "Confirmacion Cambio de Responsable de la Actividad: " + actividadMecanicaSelected.getDescripcion();
            String body = "Este mensaje es de confirmacion de cambio de Responsable<br><br>" +
                    "Periodo Promocional: " + dashboardItemSelected.tblCampana.getNombre() + "<br>" +
                    "Actividad: " + actividadMecanicaSelected.getDescripcion() + "<br>" +
                    "Responsable Anterior: " + userBefore.getNombre() + " " + userBefore.getPlastName() + "<br>" +
                    "<strong>Nuevo Responsable: " + actividadMecanicaSelected.getCatUsuariosByIdresponsable().getNombre() +
                                        actividadMecanicaSelected.getCatUsuariosByIdresponsable().getPlastName() + "</strong><br>" +
                    "<br>Este mensaje es solo informativo y generado de manera automatica. NO REPONDER<br><br>";
            SendMail.sendGenericEmail(mailTo, subject, body);
        }else {
            Messages.mensajeErroneo("Error al delegar actividad", "");
        }
        RequestContext.getCurrentInstance().execute("delegarDialog.hide();");
    }

    public void onBackButton(){
        detalleView = false;
        mecanicaList = new ArrayList<>();
        mecanicaSelectedList = new ArrayList<>();
        programaSelected = "0";
        catCategorySelected = null;
        buttonOpenAct = false;
    }

    public void onProgramaSelected(){
        int id = 0;
        etapaProgrma = "";
        for (TblCampanaProgramas t : dashboardItemSelected.getProgramasList()){
            if (t.getPrograma().getNombre().equals(programaSelected)){
                id = t.getPrograma().getIdPrograma();
                tblProgramaSelected = t;
                etapaProgrma = t.getEtapa();
                break;
            }
        }
        if (id > 0 && catCategorySelected == null){
            mecanicaList = serviceArquitecturaSeven.getMecanicaByPrograma(dashboardItemSelected.getTblCampana().getIdCampana(), id);
            buttonOpenAct = false;
        }else if (id == 0 && catCategorySelected == null){
            mecanicaList = serviceArquitecturaSeven.getMecanicasByCampana(dashboardItemSelected.getTblCampana().getIdCampana());
            buttonOpenAct = true;
        }else if (id > 0 && catCategorySelected.getIdCategory() > 0){
            List<TblMecanica> mecs = serviceArquitecturaSeven.getMecanicaByPrograma(dashboardItemSelected.getTblCampana().getIdCampana(), id);
            buttonOpenAct = false;
            filtrarMecanicaByCategory(mecs);
        }else if (id == 0 && catCategorySelected.getIdCategory() > 0){
            List<TblMecanica> mecs = serviceArquitecturaSeven.getMecanicasByCampana(dashboardItemSelected.getTblCampana().getIdCampana());
            buttonOpenAct = true;
            filtrarMecanicaByCategory(mecs);
        }
        mecSelected = null;
        mecanicasActivities = new ArrayList<>();
    }

    private void filtrarMecanicaByCategory(List<TblMecanica> mecanicas){
        mecanicaList = new ArrayList<>();
                
        for (TblMecanica mecanica : mecanicas){
            List<TblCategoria> categorias = new ArrayList<>(mecanica.getTblCategorias());
            for (TblCategoria c : categorias){
                if (c.getCategoriaId() == catCategorySelected.getIdCategory()){
                    mecanicaList.add(mecanica);
                    break;
                }
            }
        }
    }

    public void onEventoSelected(DashboardItem activity){
        actividadMecanicaSelected = new TblActividad();
        mecanicasActivities = new ArrayList<>();
        dashboardItemSelected = activity;
        mecSelected = null;
        try {
        	Map<String, String> filters = new HashMap<>();
        	filters.put("getCount", "false");
        	filters.put("idCampana", dashboardItemSelected.getTblCampana().getIdCampana()+"");
        	filters.put("loadCatFlujoAct", "true");
        	filters.put("loadStatus", "true");
        	filters.put("loadCampanaProgramas", "true");
        	filters.put("loadCampanaProgramasPrograma", "true");
        	filters.put("loadCampanaProgramasCategorias", "true");
        	CampanaResultDTO r = serviceCampana.getCampanas(0,1, filters);
			dashboardItemSelected.setTblCampana(r.getCampanas().get(0));
			dashboardItemSelected.setProgramasList(new ArrayList<TblCampanaProgramas>());
			for(TblCampanaProgramas cp: r.getCampanas().get(0).getTblCampanaProgramas()){
				if(Hibernate.isInitialized(cp.getPrograma()))
					dashboardItemSelected.getProgramasList().add(cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        mecanicaList = serviceArquitecturaSeven.getMecanicasByCampana(dashboardItemSelected.getTblCampana().getIdCampana());
        for(TblMecanica m: mecanicaList){
        	m.setCampana(dashboardItemSelected.getTblCampana());
        }
        detalleView = true;
        etapaProgrma = "";
    }

    public void despuesDeSeleccionarMecanica(){
        mecanicasActivities = new ArrayList<>();
        if (mecSelected != null && mecSelected.length > 0){
            System.out.println("Si hay un arreglo de mecanicas, iterando");
            for (TblMecanica mec : mecSelected){
                System.out.println("sacando actividades de mecanica");
                List<TblActividad> actividads = serviceTblActividades.getTblActividadByMecanica(mec.getMecanicaId());
                System.out.println("Generando objeto actividades de mecanica");
                MecanicasActivities mac = new MecanicasActivities(mec);
                mac.setActividadList(actividads);
                System.out.println("Agregando al listado");
                mecanicasActivities.add(mac);
            }
        }
    }

    public void onMecanicaSelected(ValueChangeEvent event){
        mecSelected = (TblMecanica[]) event.getNewValue();
        despuesDeSeleccionarMecanica();
    }

    public void toggleMecanicaSelected(ToggleSelectEvent event){
        System.out.println("toogle a la mecanica");
        SelectCheckboxMenu selectcb = (SelectCheckboxMenu) event.getSource();
        String[] selectedMecanicas = (String[]) selectcb.getSubmittedValue();
        System.out.println("Mecanicas seleccionadas: " + selectedMecanicas);
        
        if (selectedMecanicas.length > 0){
            System.out.println("Mecanicas seleccionadas, creando arreglo");
            mecSelected = new TblMecanica[selectedMecanicas.length];
            int counter = 0;
            System.out.println("Seteando mecanicas");
            for (String s : selectedMecanicas){
                for (TblMecanica m : mecanicaList){
                    if (m.getMecanicaId() == Integer.valueOf(s)){
                        mecSelected[counter] = m;
                        counter++;
                        break;
                    }
                }
            }
        }else{
            System.out.println("sin Mecanicas seleccionadas, arreglo nulo");
            mecSelected = null;
        }
        System.out.println("ejecutando despues de seleccionar mecanica");
        despuesDeSeleccionarMecanica();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        actividadMecanicaSelected = (TblActividad) event.getData();
    }

    public void abrirActividades(){
        EtapaDashboard etapaCerrar = Util.convertProgramaEtapa(tblProgramaSelected.getEtapa());
        if (etapaCerrar == EtapaDashboard.CAPTURA || etapaCerrar == EtapaDashboard.PENDIENTE ) {
            Messages.mensajeErroneo("No se pueden reabrir actividades en esta etapa","");
        }else{
            List<TblMecanica> mecanicas = serviceArquitecturaSeven.getMecanicaByPrograma(dashboardItemSelected.getTblCampana().getIdCampana(), tblProgramaSelected.getPrograma().getIdPrograma());
            CatEstatus cerrado = serviceCatEstatus.getEstatusByNameContains("Pendiente");
            CatEstatus reabierto = serviceCatEstatus.getEstatusByNameContains("Re-");
            EtapaDashboard etapaAbrir = Util.convertProgramaEtapaBefore(tblProgramaSelected.getEtapa());
            if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, cerrado, etapaCerrar)){
                if (serviceTblActividades.changeEstatusActivityByMecaniaList(mecanicas, reabierto, etapaAbrir)){
                    tblProgramaSelected.setEtapa(Util.convertProgramaEtapa(etapaAbrir));
                    try {
                        serviceCampana.updateCampanaProgramas(tblProgramaSelected);
                        etapaProgrma = Util.convertProgramaEtapa(etapaAbrir);
                        Messages.mensajeSatisfactorio("Actividades Re-Abiertas", "Programa cambia a Etapa: " + etapaProgrma);
                        enviarCorreoAbrirActividades(mecanicas, etapaAbrir, etapaCerrar);
                    } catch (GeneralException e) {
                        Messages.mensajeErroneo("Error al actualizar programa: " + e.getMessage(), "");
                        e.printStackTrace();
                    }
                }else {
                    Messages.mensajeErroneo("No se abrieron las actividades", "");
                }
            }else {
                Messages.mensajeErroneo("No se cerraron las actividades", "");
            }
        }
      //  String[] sms = selectedMecanicas;
        TblMecanica[] sms = mecSelected;
        onProgramaSelected();
        mecSelected = sms;
        despuesDeSeleccionarMecanica();
    }

    private void enviarCorreoAbrirActividades(List<TblMecanica> mecanicas, EtapaDashboard etapaAbrir, EtapaDashboard etapaCerrar){
        List<String> mailList = new ArrayList<>();
        int ordenCerrar = Util.etapaDashboardToInt(etapaCerrar);
        int ordenAbrir = Util.etapaDashboardToInt(etapaAbrir);
        String programa = tblProgramaSelected.getPrograma().getNombre();
        String evento = "";
        mailList.add(Util.getCoordinadorEmail(serviceUsuarios));
        for (TblMecanica m : mecanicas){
            TblActividad actCerrar = serviceTblActividades.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), ordenCerrar);
            TblActividad actAbrir = serviceTblActividades.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), ordenAbrir);
            String mailCierre = actCerrar.getCatUsuariosByIdresponsable().getEmail();
            String mailApert = actAbrir.getCatUsuariosByIdcreador().getEmail();
            if (!mailList.contains(mailCierre)){
                mailList.add(mailCierre);
            }
            if (!mailList.contains(mailApert)){
                mailList.add(mailApert);
            }
            if (evento.isEmpty()){
                evento = m.getCampana().getNombre();
            }
            if (ordenAbrir == 4){
                TblActividad actRevDiseno = serviceTblActividades.getTblActividadByMecanicaAndOrden(m.getMecanicaId(), 5);
                if (!mailList.contains(actRevDiseno.getCatUsuariosByIdresponsable().getEmail())){
                    mailList.add(actRevDiseno.getCatUsuariosByIdresponsable().getEmail());
                }
            }
        }
        String[] toMails = mailList.toArray(new String[mailList.size()]);
        String subject = "AVISO: "+ evento +": RE-Apertura de Actividades - " + Util.convertProgramaEtapa(etapaAbrir);
        String msj = "Este correo es solo un aviso de re-apertura de actividades<br /><br />" +
                "Periodo Promocional: " + evento + "<br />" +
                "Programa: " + programa + "<br />" +
                "Etapa Cierre: " + Util.convertProgramaEtapa(etapaCerrar) + "<br />" +
                "Etapa RE-Apertura: " + Util.convertProgramaEtapa(etapaAbrir) + "<br /><br /><br />" +
                "Correo generado automaticamente, NO RESPONDER<br /><br />";
        SendMail.sendGenericEmail(toMails, subject, msj);
    }

    public boolean renderEditButton(TblActividad a){
        if (a.getOrden() == 0){
            return true;
        }else if (a.getOrden() == 6){
            for (TblCampanaProgramas tl : dashboardItemSelected.getProgramasList()){
                if (tl.getId().getIdPrograma() == a.getTblMecanica().getProgramaId()){
                    if (Util.convertProgramaEtapa(tl.getEtapa()) == EtapaDashboard.ENVIO_DISENO){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
            return false;
        }else {
            return false;
        }
    }

    public String getYearByDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    private Boolean validationFieldsEdit(TblActividad editActivity){
        Boolean bRet= Boolean.TRUE;
        if(validRangeDates(editActivity.getVigenciaInicio(), editActivity.getFechaFin())){
            bRet= Boolean.FALSE;
        }
        return bRet;
    }

    public boolean validRangeDates(Date init, Date end){
        if(init!=null&&end!=null){
            return init.after(end);
        }else{
            return false;
        }
    }


    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
        }
    }

    public String obtenerSemaforoCSS(int idEstatus, Date fechaFin) {
        switch (idEstatus) {
            case Constants.STATUS_CERRADO:
                return "semNegro";
            case Constants.STATUS_ABIERTO:
                Date fechaLimite = new Date();
                if (fechaFin.after(fechaLimite)) {
                    Long diff = MBUtil.dateDiff(new Date(), fechaFin,
                            Constants.MILLISECS_PER_DAY);
                    if (diff == 1) {
                        return "semAmarillo";
                    }
                    return "semVerde";
                }
            default:
                return "semRojo";
        }
    }

    private MethodExpressionActionListener createMethodActionListener(
            String valueExpression, Class<?> expectedReturnType,
            Class<?>[] expectedParamTypes) {
        return new MethodExpressionActionListener(createMethodExpression(
                valueExpression, expectedReturnType, expectedParamTypes));
    }

    private MethodExpression createMethodExpression(String valueExpression, Class<?> expectedReturnType, Class<?>[] expectedParamTypes) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExpressionFactory factory = fc.getApplication().getExpressionFactory();

        return factory.createMethodExpression(fc.getELContext(), valueExpression, expectedReturnType, expectedParamTypes);
    }

    public String obtenerUsuarioName(TblCampanaActividades activity){
        CatUsuarios u = serviceUsuarios.getUsuarioById(activity.getIdUsuarioResp());
        if ( u != null){
            return u.getNombre() + " " + u.getPlastName();
        }
        return "";
    }

    // ****************************** Getters AND Setters ************************************************* //


    public TblMecanica[] getMecSelected() {
        return mecSelected;
    }

    public void setMecSelected(TblMecanica[] mecSelected) {
        this.mecSelected = mecSelected;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public CatUsuarios getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(CatUsuarios userSelected) {
        this.userSelected = userSelected;
    }

    public List<CatUsuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<CatUsuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public ServiceCampana getServiceCampana() {
        return serviceCampana;
    }

    public void setServiceCampana(ServiceCampana serviceCampana) {
        this.serviceCampana = serviceCampana;
    }

    public ServiceCatActPred getServiceCatActPred() {
        return serviceCatActPred;
    }

    public void setServiceCatActPred(ServiceCatActPred serviceCatActPred) {
        this.serviceCatActPred = serviceCatActPred;
    }

    public ServiceTblActividades getServiceTblActividades() {
        return serviceTblActividades;
    }

    public int getEstatusSelected() {
        return estatusSelected;
    }

    public void setEstatusSelected(int estatusSelected) {
        this.estatusSelected = estatusSelected;
    }

    public void setServiceTblActividades(ServiceTblActividades serviceTblActividades) {
        this.serviceTblActividades = serviceTblActividades;
    }

    public ServiceUsuarios getServiceUsuarios() {
        return serviceUsuarios;
    }

    public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
        this.serviceUsuarios = serviceUsuarios;
    }

    public ServiceCatEstatus getServiceCatEstatus() {
        return serviceCatEstatus;
    }

    public void setServiceCatEstatus(ServiceCatEstatus serviceCatEstatus) {
        this.serviceCatEstatus = serviceCatEstatus;
    }

    public ServiceArquitecturaSeven getServiceArquitecturaSeven() {
        return serviceArquitecturaSeven;
    }

    public void setServiceArquitecturaSeven(ServiceArquitecturaSeven serviceArquitecturaSeven) {
        this.serviceArquitecturaSeven = serviceArquitecturaSeven;
    }

    public List<TblCampana> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<TblCampana> eventoList) {
        this.eventoList = eventoList;
    }



    public List<TblActividad> getActividadsMecanicasTable() {
        return actividadsMecanicasTable;
    }

    public List<CatEstatus> getEstatusList() {
        return estatusList;
    }

    public void setEstatusList(List<CatEstatus> estatusList) {
        this.estatusList = estatusList;
    }

    public CatUsuarios getUserBoss() {
        return userBoss;
    }

    public void setUserBoss(CatUsuarios userBoss) {
        this.userBoss = userBoss;
    }

    public void setActividadsMecanicasTable(List<TblActividad> actividadsMecanicasTable) {
        this.actividadsMecanicasTable = actividadsMecanicasTable;
    }

    public List<TblActividad> getUseractividades() {
        return useractividades;
    }

    public void setUseractividades(List<TblActividad> useractividades) {
        this.useractividades = useractividades;
    }

    public List<TblActividad> getTblActividadListToday() {
        return tblActividadListToday;
    }

    public void setTblActividadListToday(List<TblActividad> tblActividadListToday) {
        this.tblActividadListToday = tblActividadListToday;
    }


    public TblActividad getActividadMecanicaSelected() {
        return actividadMecanicaSelected;
    }

    public void setActividadMecanicaSelected(TblActividad actividadMecanicaSelected) {
        this.actividadMecanicaSelected = actividadMecanicaSelected;
    }


    public TblActividad[] getActividadesSelected() {
        return actividadesSelected;
    }

    public void setActividadesSelected(TblActividad[] actividadesSelected) {
        this.actividadesSelected = actividadesSelected;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public MenuModel getModelActivities() {
        return modelActivities;
    }

    public void setModelActivities(MenuModel modelActivities) {
        this.modelActivities = modelActivities;
    }

    public ScheduleModel getScheduleActivities() {
        return scheduleActivities;
    }

    public void setScheduleActivities(ScheduleModel scheduleActivities) {
        this.scheduleActivities = scheduleActivities;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CatUsuarios getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(CatUsuarios userLogged) {
        this.userLogged = userLogged;
    }

    public List<TblMecanica> getMecanicaList() {
        return mecanicaList;
    }

    public void setMecanicaList(List<TblMecanica> mecanicaList) {
        this.mecanicaList = mecanicaList;
    }

    public List<MecanicasActivities> getMecanicasActivities() {
        return mecanicasActivities;
    }

    public void setMecanicasActivities(List<MecanicasActivities> mecanicasActivities) {
        this.mecanicasActivities = mecanicasActivities;
    }

    public List<TblMecanica> getMecanicaSelectedList() {
        return mecanicaSelectedList;
    }

    public void setMecanicaSelectedList(List<TblMecanica> mecanicaSelectedList) {
        this.mecanicaSelectedList = mecanicaSelectedList;
    }

    public TblMecanica getMecanicaSeleccionada() {
        return mecanicaSeleccionada;
    }

    public void setMecanicaSeleccionada(TblMecanica mecanicaSeleccionada) {
        this.mecanicaSeleccionada = mecanicaSeleccionada;
    }

    public boolean isDetalleView() {
        return detalleView;
    }

    public void setDetalleView(boolean detalleView) {
        this.detalleView = detalleView;
    }

    public List<DashboardItem> getDashboardList() {
        return dashboardList;
    }

    public void setDashboardList(List<DashboardItem> dashboardList) {
        this.dashboardList = dashboardList;
    }

    public DashboardItem getDashboardItemSelected() {
        return dashboardItemSelected;
    }

    public void setDashboardItemSelected(DashboardItem dashboardItemSelected) {
        this.dashboardItemSelected = dashboardItemSelected;
    }

    public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
        return serviceDynamicCatalogs;
    }

    public void setServiceDynamicCatalogs(ServiceDynamicCatalogs serviceDynamicCatalogs) {
        this.serviceDynamicCatalogs = serviceDynamicCatalogs;
    }

    public List<TipoCampanaDTO> getTipoCampanaDTOs() {
        return tipoCampanaDTOs;
    }

    public void setTipoCampanaDTOs(List<TipoCampanaDTO> tipoCampanaDTOs) {
        this.tipoCampanaDTOs = tipoCampanaDTOs;
    }

    public String getProgramaSelected() {
        return programaSelected;
    }

    public void setProgramaSelected(String programaSelected) {
        this.programaSelected = programaSelected;
    }

    public List<CatUsuarios> getUsuariosByRole() {
        return usuariosByRole;
    }

    public void setUsuariosByRole(List<CatUsuarios> usuariosByRole) {
        this.usuariosByRole = usuariosByRole;
    }

    public boolean isButtonOpenAct() {
        return buttonOpenAct;
    }

    public void setButtonOpenAct(boolean buttonOpenAct) {
        this.buttonOpenAct = buttonOpenAct;
    }

    public CatCategory getCatCategorySelected() {
        return catCategorySelected;
    }

    public void setCatCategorySelected(CatCategory catCategorySelected) {
        this.catCategorySelected = catCategorySelected;
    }

    public ServiceCatCategory getServiceCatCategory() {
        return serviceCatCategory;
    }

    public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
        this.serviceCatCategory = serviceCatCategory;
    }

    public String getEtapaProgrma() {
        return etapaProgrma;
    }

    public void setEtapaProgrma(String etapaProgrma) {
        this.etapaProgrma = etapaProgrma;
    }

    public TblCampanaProgramas getTblProgramaSelected() {
        return tblProgramaSelected;
    }

    public void setTblProgramaSelected(TblCampanaProgramas tblProgramaSelected) {
        this.tblProgramaSelected = tblProgramaSelected;
    }

    public String getPeriodoFilter() {
        return periodoFilter;
    }

    public void setPeriodoFilter(String periodoFilter) {
        this.periodoFilter = periodoFilter;
    }

    public TipoCampanaDTO getTipoCampanaSelected() {
        return tipoCampanaSelected;
    }

    public void setTipoCampanaSelected(TipoCampanaDTO tipoCampanaSelected) {
        this.tipoCampanaSelected = tipoCampanaSelected;
    }

    public List<CatRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<CatRole> roleList) {
        this.roleList = roleList;
    }

    public CatRole getRoleSelected() {
        return roleSelected;
    }

    public void setRoleSelected(CatRole roleSelected) {
        this.roleSelected = roleSelected;
    }

    public ServiceCatRole getServiceCatRole() {
        return serviceCatRole;
    }

    public void setServiceCatRole(ServiceCatRole serviceCatRole) {
        this.serviceCatRole = serviceCatRole;
    }

    public CatUsuarios getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(CatUsuarios usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public TblActividad getActividadSelected() {
        return actividadSelected;
    }

    public void setActividadSelected(TblActividad actividadSelected) {
        this.actividadSelected = actividadSelected;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getMecanicaSelected() {
        return mecanicaSelected;
    }

    public void setMecanicaSelected(String mecanicaSelected) {
        this.mecanicaSelected = mecanicaSelected;
    }

    public boolean isAdmDiseno() {
        return admDiseno;
    }

    public void setAdmDiseno(boolean admDiseno) {
        this.admDiseno = admDiseno;
    }

    public List<PeriodoDTO> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<PeriodoDTO> periodos) {
        this.periodos = periodos;
    }

    public PeriodoDTO getPeriodoSelected() {
        return periodoSelected;
    }

    public void setPeriodoSelected(PeriodoDTO periodoSelected) {
        this.periodoSelected = periodoSelected;
    }

    public boolean isDisabledDates() {
        return disabledDates;
    }

    public void setDisabledDates(boolean disabledDates) {
        this.disabledDates = disabledDates;
    }

    public List<Integer> getAnos() {
        return anos;
    }

    public void setAnos(List<Integer> anos) {
        this.anos = anos;
    }

    public Integer getAnoSelected() {
        return anoSelected;
    }

    public void setAnoSelected(Integer anoSelected) {
        this.anoSelected = anoSelected;
    }

    // *********************** Inner DashboardItem Class ************************ //
    public class DashboardItem {
        private TblCampana tblCampana;
        private TipoCampanaDTO tipoCampanaDTO;
        private String etapa;
        private List<TblCampanaProgramas> programasList;
        private List<CatCategory> catCategoryList;
        private String periodoStr;

        public DashboardItem(TblCampana tblCampana) {
            this.tblCampana = tblCampana;
        }

        public List<CatCategory> getCatCategoryList() {
            catCategoryList = new ArrayList<>();
            if (programasList != null){
            	List<Integer> ids = new ArrayList<Integer>();
                for (TblCampanaProgramas cp : programasList){
                    List<TblCampanaProgramasCategorias> list = new ArrayList<>(cp.getTblCampanaProgramasCategorias());
                    ids = new ArrayList<Integer>();
                    for (TblCampanaProgramasCategorias c : list){
                    	ids.add(c.getIdCategoria());
                    }
                }
                System.out.println("Categories to search: "+ids);
                List<CatCategory> cats = serviceCatCategory.getCatCategoryByIds(ids);
                if(cats!=null){
                	catCategoryList.addAll(cats);
                }
            }
            return catCategoryList;
        }

        public String getPeriodoStr() {
            return periodoStr;
        }

        public void setPeriodoStr(String periodoStr) {
            this.periodoStr = periodoStr;
        }

        public void setCatCategoryList(List<CatCategory> catCategoryList) {
            this.catCategoryList = catCategoryList;
        }

        public TblCampana getTblCampana() {
            return tblCampana;
        }

        public void setTblCampana(TblCampana tblCampana) {
            this.tblCampana = tblCampana;
        }


        public TipoCampanaDTO getTipoCampanaDTO() {
            return tipoCampanaDTO;
        }

        public void setTipoCampanaDTO(TipoCampanaDTO tipoCampanaDTO) {
            this.tipoCampanaDTO = tipoCampanaDTO;
        }

        public String getEtapa() {
            return etapa;
        }

        public void setEtapa(String etapa) {
            this.etapa = etapa;
        }

        public List<TblCampanaProgramas> getProgramasList() {
            return programasList;
        }

        public void setProgramasList(List<TblCampanaProgramas> programasList) {
            this.programasList = programasList;
        }
    }

    //************************* Inner Mecanicas Activities Class *********************** //
    public class MecanicasActivities {
        private TblMecanica mecanica;
        private List<TblActividad> actividadList;

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            MecanicasActivities that = (MecanicasActivities) object;
            return Objects.equals(mecanica, that.mecanica);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mecanica);
        }

        public MecanicasActivities(TblMecanica mecanica) {
            this.mecanica = mecanica;
        }

        public TblMecanica getMecanica() {
            return mecanica;
        }

        public void setMecanica(TblMecanica mecanica) {
            this.mecanica = mecanica;
        }

        public List<TblActividad> getActividadList() {
            return actividadList;
        }

        public void setActividadList(List<TblActividad> actividadList) {
            this.actividadList = actividadList;
        }
    }
}
