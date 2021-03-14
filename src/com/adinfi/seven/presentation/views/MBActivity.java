package com.adinfi.seven.presentation.views;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.business.services.*;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.MenuModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.persistence.dto.ActivityDTO;
import com.adinfi.seven.persistence.dto.ActivityModel;
import com.adinfi.seven.persistence.dto.DelegateDTO;
import com.adinfi.seven.persistence.dto.DelegateModel;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;
import com.itextpdf.text.DocumentException;
import java.util.Objects;

public class MBActivity implements Serializable {
	private static final long serialVersionUID = 1L;
	private final transient Logger LOG = Logger.getLogger(MBActivity.class);
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceCampana serviceCampana;
	private ServiceCatActPred serviceCatActPred;
	private ServiceTblActividades serviceTblActividades;
	private ServiceUsuarios serviceUsuarios;

	private List<TblActividad> tblActividadList;
	private TblCampanaActividades tblCampanaActividadesSelected;

	private List<CatActPred> catActPredList;
	private List<TblCampanaActividades> listActivity = null;
	private List<ActivityDTO> listActivityToday = new ArrayList<>();
	private List<ActivityDTO> listActivityTomorrow = new ArrayList<>();

	private Map<Long, TblCampana> listCampana = null;
	private List<ActivityDTO> listActivityDTO;
	private ActivityModel activityModel = null;
	private List<ActivityDTO> activityModelFiltered = null;
	private ActivityDTO[] activitySelection;

	private MenuModel model;
	private MenuModel modelActivities;

	private ScheduleModel scheduleActivities;
	private ScheduleEvent event = new DefaultScheduleEvent();

	private TblCampanaActividades activity;
	private TblCampanaActividades createActivity;
	private ActivityDTO activityDTO;
	private List<TblCampana> listCampanaCombo;
	private List<GenericItem> listEstatusCombo;

	private Long idCampana = (long) -1;
	public String duration;

	private TblDelegacionActividades delegate;
	private DelegateModel delegateModel;
	private DelegateDTO[] delegateSelection;
	private List<UsuarioDTO> lstUsuarios;
	private List<UsuarioDTO> lstUsuarioDelega;

	private CatUsuarios userLogin;
	
	private Date startDate = new Date();
	private Date endDate = new Date();

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
	
	public void filterDates(){
		try {
			initAd();
		} catch (GeneralException e) {
			LOG.error(e);
		}
	}

	@PostConstruct
	public void init() {
		try {
			initDates();
			initAd();
		} catch (GeneralException e) {
			LOG.error(e);
		}
	}
	
	private void initAd() throws GeneralException{
		activity = new TblCampanaActividades();
		activity.setTblCampana(new TblCampana());
		createActivity = new TblCampanaActividades();
		createActivity.setTblCampana(new TblCampana());
		listActivity = new ArrayList<>();
		listActivityDTO = new ArrayList<>();
		listActivityToday = new ArrayList<>();
		listActivityTomorrow = new ArrayList<>();
		scheduleActivities = new DefaultScheduleModel();
		listCampana = new HashMap<>();

		userLogin = Util.getSessionAttribute("userLogged");
		fillActivityList(idCampana, startDate, endDate);
		processActivityList();
		fillActivitySchedule();
		generateMenu();
		generateMenuActivities();
		fillCatalogs();
		initDelegate();
	}
	
	private void initDates(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2000);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		startDate= new java.sql.Date(cal.getTimeInMillis());

		cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 12);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		endDate= new java.sql.Date(cal.getTimeInMillis());
	}

	private void initDelegate() {
            delegate = new TblDelegacionActividades();
            lstUsuarios = new ArrayList<>();
            //lstUsuarioDelega = new ArrayList<UsuarioDTO>();
            //lstUsuarioDelega.add(userLogin);
            //fillDelegateList();
            fillListUser();
	}

	private void fillActivityList(Long idCampana, Date startDate, Date endDate) throws GeneralException {
            if (userLogin != null && userLogin.getCatRole().getIdrole() != Constants.PERFIL_ADMIN) {
                listActivity = serviceCampana.getActivityList(idCampana,userLogin.getCatRole().getIdrole(), startDate, endDate);
            } else {
                listActivity = serviceCampana.getActivityList(idCampana, startDate, endDate);
            }
            if (listActivity != null) {
                for (TblCampanaActividades activity_local : listActivity) {
                        ActivityDTO activityDTO_local = fillActivityDTO(activity_local);
                        listActivityDTO.add(activityDTO_local);
                }
            }
            setActivityModel(new ActivityModel(listActivityDTO));
	}

	private ActivityDTO fillActivityDTO(TblCampanaActividades activity)throws GeneralException {
            ActivityDTO activityDTO_local = new ActivityDTO();
            activityDTO_local.setActividad(activity.getNombreActividad());
     //       activityDTO_local.setEstatus(MBUtil.genericSearch(Constants.ATT_CODE,
     //                       String.valueOf(activity.getCatEstatus().getNombre()), Constants.ATT_NAME,
     //                       Constants.CAT_STATUS, serviceDynamicCatalogs));
            activityDTO_local.setId(activity.getId());
            activityDTO_local.setTblCampana(activity.getTblCampana());
            EtiquetaDTO etiquetaDTO = MBUtil.getEtiqueta(serviceDynamicCatalogs,
                            activityDTO_local.getTblCampana().getCatEtiquetas().getIdetiqueta());
            activityDTO_local.setCodigoColor(etiquetaDTO.getCodigo());
            activityDTO_local.setPerfil(activity.getIdRol());
            activityDTO_local.setPerfilName(MBUtil.genericSearch(Constants.ATT_ID,
                            String.valueOf(activity.getIdRol()), Constants.ATT_DESCRIPTION,
                            Constants.CAT_ROLE, serviceDynamicCatalogs));
            activityDTO_local.setUsuarioByUsuarioCreacion(activity.getUsuarioCreacion());
            activityDTO_local.setUsuarioByUsuarioModificacion(activity
                            .getUsuarioModificacion());
            activityDTO_local.setUsuarioByIdUsuarioResp(MBUtil.makeUser(
                            String.valueOf(activity.getIdUsuarioResp()),
                            serviceDynamicCatalogs));
            activityDTO_local.setNombreActividad(activity.getNombreActividad());
            activityDTO_local.setEsFlujo(activity.getEsFlujo());
            activityDTO_local.setFechaCierre(activity.getFechaCierre());
            activityDTO_local.setFechaInicio(activity.getFechaInicio());
            activityDTO_local.setFechaFin(activity.getFechaFin());
            activityDTO_local.setFechaCreacion(activity.getFechaCreacion());
            activityDTO_local.setFechaModificacion(activity.getFechaModificacion());
            activityDTO_local.setPorcentajeCompletado(activity.getPorcentajeCompletado());
            activityDTO_local.setFechaInicioReal(activity.getFechaInicioReal());
            activityDTO_local.setFechaFinReal(activity.getFechaFinReal());
            activityDTO_local.setSemaforo(obtenerSemaforo(activity));
            return activityDTO_local;
	}
	private String obtenerSemaforoCSS(TblCampanaActividades activity) {
            switch (activity.getCatEstatus().getIdestatus()) {
            case Constants.STATUS_CERRADO:
                    return "semNegro";
            case Constants.STATUS_ABIERTO:
                    Date fechaLimite = new Date();
                    if (activity.getFechaFin().after(fechaLimite)) {
                            Long diff = MBUtil.dateDiff(new Date(), activity.getFechaFin(),
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

	/*public void setEventoSelected(TblCampanaActividades activity){
		tblCampanaActividadesSelected = activity;
		FacesContext context = FacesContext.getCurrentInstance();
		MBNavigator navigator = (MBNavigator) context.getApplication().getELResolver().getValue(context.getELContext(), null, "MBNavigator");
		navigator.filterActivities("dashboard/detalleDashboard.xhtml", -1L);

		MBActividad mbActividad = (MBActividad) context.getApplication().getELResolver().getValue(context.getELContext(), null, "MBActividad");
		mbActividad.setActividadEventoSelected(activity);
	} */

	public String obtenerUsuarioName(TblCampanaActividades activity){
		CatUsuarios u = serviceUsuarios.getUsuarioById(activity.getIdUsuarioResp());
		if ( u != null){
			return u.getNombre() + " " + u.getPlastName();
		}
		return "";
	}

	public String obtenerSemaforo(TblCampanaActividades activity) {
            switch (activity.getCatEstatus().getIdestatus()) {
            case Constants.STATUS_CERRADO:
                    return "online.png";
            case Constants.STATUS_ABIERTO:
                    Date fechaLimite = new Date();
                    if (activity.getFechaFin().after(fechaLimite)) {
                            Long diff = MBUtil.dateDiff(new Date(), activity.getFechaFin(),
                                            Constants.MILLISECS_PER_DAY);
                            if (diff == 1) {
                                    return "CircleYellow.png";
                            }
                            return "CircleGreen.png";
                    }
            default:
                    return "CircleRed.png";
            }
	}

	private void processActivityList() throws GeneralException {
		if (listActivity != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar tomorrow = Calendar.getInstance();
			tomorrow.add(Calendar.DATE, 1);
			for (TblCampanaActividades activity_local : listActivity) {
				if (!listCampana.containsKey(activity_local.getTblCampana()
						.getIdCampana())) {
					listCampana.put(activity_local.getTblCampana().getIdCampana(),
							activity_local.getTblCampana());
				}
			}
			for (ActivityDTO activity_local : listActivityDTO) {
				String strDate = sdf.format(activity_local.getFechaInicio());
				if (strDate.compareTo(sdf.format(new Date())) == 0) {
					listActivityToday.add(activity_local);
				}
				if (strDate.compareTo(sdf.format(tomorrow.getTime())) == 0) {
					listActivityTomorrow.add(activity_local);
				}
			}
		}
	}

	private void fillActivitySchedule() {
		if (listActivity != null) {
			for (TblCampanaActividades activity_local : listActivity) {
				DefaultScheduleEvent scheduleEvent = new DefaultScheduleEvent(
						activity_local.getNombreActividad(),
						activity_local.getFechaInicio(), activity_local.getFechaInicio());
				scheduleEvent.setData(activity_local);
				scheduleEvent.setStyleClass(obtenerSemaforoCSS(activity_local));
				scheduleActivities.addEvent(scheduleEvent);
			}
		}
	}

	@SuppressWarnings("el-syntax")
	private void generateMenuActivities() {
		modelActivities = new DefaultMenuModel();
		// TODAY
		Submenu subToday = new Submenu();
		subToday.setLabel("Hoy");
		for (ActivityDTO today : listActivityToday) {
			MenuItem item = generateMenu(today.getActividad() + "",
					"#{MBNavigator.detailActivities('activitiesDetail.xhtml',"
							+ today.getId() + ")}", null);
			subToday.getChildren().add(item);
		}
		modelActivities.addSubmenu(subToday);
		// TOMORROW
		Submenu subTomorrow = new Submenu();
		subTomorrow.setLabel("Mañana");
		for (ActivityDTO tomorrow : listActivityTomorrow) {
			MenuItem item = generateMenu(tomorrow.getActividad(),
					"#{MBNavigator.detailActivities('activitiesDetail.xhtml',"
							+ tomorrow.getId() + ")}", null);
			subTomorrow.getChildren().add(item);
		}
		modelActivities.addSubmenu(subTomorrow);
	}

	@SuppressWarnings("el-syntax")
	private void generateMenu() {
		model = new DefaultMenuModel();
		// CALENDARIO
		Submenu submenu = new Submenu();
		submenu.setLabel("Calendario");
		submenu.setIcon("ui-icon-calendar");
		submenu.getChildren()
				.add(generateMenu(
						"Calendario",
						"#{MBNavigator.filterActivities('activitiesSchedule.xhtml',-1)}",
						"ui-icon-calendar"));
		model.addSubmenu(submenu);

		//DASHBOARD
		submenu = new Submenu();
		submenu.setLabel("DashBoard");
		submenu.setIcon("ui-icon-clipboard");
		submenu.getChildren().add(generateMenu("DashBoard","#{MBNavigator.filterActivities('dashboard/dashboard.xhtml', -1)}", "ui-icon-clipboard"));
		model.addSubmenu(submenu);

		// MIS ACTIVIDADES
		submenu = new Submenu();
		submenu.setLabel("Mis Actividades");
		submenu.setIcon("ui-icon-note");
		submenu.getChildren().add(generateMenu(
						"Ver Todas",
						"#{MBNavigator.filterActivities('activitiesGrid.xhtml',-1)}",
						"ui-icon-note"));
		StringBuilder actionBuilder;
		for (TblCampana campana : listCampana.values()) {
			actionBuilder = new StringBuilder(
					"#{MBNavigator.filterActivities('activitiesGrid.xhtml',");
			actionBuilder.append(campana.getIdCampana());
			actionBuilder.append(")}");
			submenu.getChildren().add(
					generateMenu(campana.getNombre(), actionBuilder.toString(),
							"ui-icon-note"));
		}
		model.addSubmenu(submenu);
	}

	private MenuItem generateMenu(String title, String command, String icon) {
		return generateMenu(title, command, icon, Constants.PG_FORM_ACTIVITY);
	}

	private MenuItem generateMenu(String title, String command, String icon,
			String update) {
		MenuItem item = new MenuItem();
		item.setValue(title);
		item.addActionListener(this.createMethodActionListener(command,
				Void.class, new Class[] { ActionEvent.class }));
		item.setIcon(icon);
		item.setUpdate(update);
		return item;
	}

	private MethodExpressionActionListener createMethodActionListener(
			String valueExpression, Class<?> expectedReturnType,
			Class<?>[] expectedParamTypes) {
		return new MethodExpressionActionListener(createMethodExpression(
				valueExpression, expectedReturnType, expectedParamTypes));
	}

	private MethodExpression createMethodExpression(String valueExpression,
			Class<?> expectedReturnType, Class<?>[] expectedParamTypes) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExpressionFactory factory = fc.getApplication().getExpressionFactory();

		return factory.createMethodExpression(fc.getELContext(),
				valueExpression, expectedReturnType, expectedParamTypes);
	}

	public void onEventSelect(SelectEvent selectEvent) {
		try {
			event = (ScheduleEvent) selectEvent.getObject();
			activity = (TblCampanaActividades) event.getData();
			activityDTO = fillActivityDTO(activity);
		} catch (GeneralException e) {
			LOG.error(e);
			FacesMessage fm = new FacesMessage(
					"Error inesperado al intentar mostrar el detalle de la actividad");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null) {
			scheduleActivities.addEvent(event);
		} else {
			scheduleActivities.updateEvent(event);
		}
		event = new DefaultScheduleEvent();
	}

	public void fillCatalogs() {
		try {
			listCampanaCombo = serviceCampana.getAllCampana();
			setListEstatusCombo(MBUtil.genericSearch(Constants.ATT_CODE,
					Constants.ATT_NAME, Constants.CAT_STATUS,
					serviceDynamicCatalogs));
		} catch (GeneralException e) {
			LOG.error(e);
		}
	}
   
    public void createActivityMethod() {
        try {
            if (validationFieldsCreate(createActivity)) {
                createActivity = fillActivity(createActivity);
                serviceCampana.saveActivity(createActivity);
             //   SendMail.sendActivity(Constants.CORREOS_ACTIVIDAD_FROM_ACTIVIDAD, createActivity, false);
                initAd();
                updateView();
                RequestContext.getCurrentInstance().execute("creatActivityPanel.hide();");
            }
        } catch (GeneralException ex) {
            LOG.error(ex);
            FacesMessage fm = new FacesMessage( "Ocurrio un error al intentar dar de alta la actividad",ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("messageCreate", fm);
        }   catch (Exception ex) {
            LOG.error(ex);
            FacesMessage fm = new FacesMessage("Ocurrio un error al intentar dar de alta la actividad",ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("messageCreate", fm);
        }
        
    }

	private void updateView() {
		List<String> colUpdateLst = new ArrayList<>();
		colUpdateLst.add("frmActivitiesGrid:dtActivityGrid");
		colUpdateLst.add("pgActivitiesEast");
		colUpdateLst.add("frmMenu");
		colUpdateLst.add("frmActivitiesPanel");
		Collection<String> colUpdate = colUpdateLst;
		RequestContext.getCurrentInstance().update(colUpdate);
	}
	
	private Boolean validationFieldsEdit(TblCampanaActividades editActivity){
		Boolean bRet= Boolean.TRUE;
		if(validRangeDates(editActivity.getFechaInicioReal(), editActivity.getFechaFinReal())){
			bRet= Boolean.FALSE;
		}
		return bRet;
	}

	private Boolean validationFieldsCreate(TblCampanaActividades createActivity) {
		Boolean bRet = Boolean.TRUE;
		if (createActivity != null) {
			Boolean bValid = Boolean.TRUE;
			if (createActivity.getNombreActividad() == null
					|| createActivity.getNombreActividad().trim().length() == 0) {
				bRet = Boolean.FALSE;
				bValid = Boolean.FALSE;
			}
			RequestContext.getCurrentInstance().addCallbackParam("nombreActividadLabel", bValid);
			bValid = Boolean.TRUE;
			if (createActivity.getFechaInicio() == null) {
				bRet = Boolean.FALSE;
				bValid = Boolean.FALSE;
			}
			RequestContext.getCurrentInstance().addCallbackParam("fechaInicio",
					bValid);
			bValid = Boolean.TRUE;
			if (createActivity.getFechaFin() == null) {
				bRet = Boolean.FALSE;
				bValid = Boolean.FALSE;
			}
			RequestContext.getCurrentInstance().addCallbackParam("fechaFin",
					bValid);
			if(createActivity.getFechaInicio().after(createActivity.getFechaFin())){
				bRet = Boolean.FALSE;
				bValid = Boolean.FALSE;
				RequestContext.getCurrentInstance().addCallbackParam("fechaInicio",
						bValid);
				RequestContext.getCurrentInstance().addCallbackParam("fechaFin",
						bValid);
			}
		} else {
			bRet = Boolean.FALSE;
		}
		return bRet;
	}

	public void showActivity() {
		createActivity = new TblCampanaActividades();
		createActivity.setTblCampana(new TblCampana());
	}

	public void editActivity(Long id) {
		try {
			activity = serviceCampana.getActivityDetail(id);
			activityDTO = fillActivityDTO(activity);
		} catch (GeneralException e) {
			LOG.error(e);
			FacesMessage fm = new FacesMessage(
					"Ocurrio un error al obtener los datos de la actividad");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

	public void viewActivityDetail(Long id) {
	/*	try {
			setIdCampana(null);
			init();
			activity = serviceCampana.getActivityDetail(id);
			activityDTO = fillActivityDTO(activity);
		} catch (GeneralException e) {
			LOG.error(e);
			FacesMessage fm = new FacesMessage(
					"Ocurrio un error al obtener los datos de la actividad");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		} */
	}
	
    public void delegateViewActivity(){
            Boolean bShow = Boolean.FALSE;
            if (activitySelection != null) {
                    if (activitySelection.length == 0) {
                            FacesMessage fm = new FacesMessage(
                                            "Favor de seleccionar el registro que desee delegar");
                            FacesContext.getCurrentInstance().addMessage(null, fm);
                    } else {
                            for(ActivityDTO activityDTO_local: activitySelection){
                                    try {
                                            TblCampanaActividades activity_local= serviceCampana.getActivityDetail(activityDTO_local
                                                            .getId());
                                            if( ((activity_local.getIdUsuarioResp()!=null &&
                                                            Objects.equals(activity_local.getIdUsuarioResp(), userLogin.getIdusuarios()))  ||
                                                            Objects.equals(userLogin.getCatRole().getIdrole(), Constants.PERFIL_ADMIN) ) &&
                                                            activity_local.getCatEstatus().getIdestatus() != Constants.STATUS_CERRADO
                                                            ){
                                                    bShow = Boolean.TRUE;
                                            }else {
                                                    FacesMessage fm = new FacesMessage(
                                                                    "No se pude delegar la actividad "+activity_local.getNombreActividad());
                                                    FacesContext.getCurrentInstance().addMessage(null, fm);
                                                    bShow = Boolean.FALSE;
                                                    break;
                                            }

                                    } catch (GeneralException e) {
                                            LOG.error(e);
                                            FacesMessage fm = new FacesMessage(
                                                            "Ocurrio un error al obtener los datos de la actividad");
                                            FacesContext.getCurrentInstance().addMessage(null, fm);
                                    }
                            }
                    }
            }
            RequestContext.getCurrentInstance().addCallbackParam("show", bShow);
    }

    public void editActivity() {
         Boolean bShow = Boolean.FALSE;
        if (activitySelection != null) {
            if (activitySelection.length == 0) {
                FacesMessage fm = new FacesMessage("Favor de seleccionar el registro que desee modificar");
                FacesContext.getCurrentInstance().addMessage(null, fm);
            } else if (activitySelection.length == 1) {
                activityDTO = activitySelection[0];
                try {
                    activity = serviceCampana.getActivityDetail(activityDTO.getId());
                    bShow = Boolean.TRUE;
                } catch (GeneralException e) {
                    LOG.error(e);
                    FacesMessage fm = new FacesMessage("Ocurrio un error al obtener los datos de la actividad");
                    FacesContext.getCurrentInstance().addMessage(null, fm);
                }
            } else {
                FacesMessage fm = new FacesMessage("Solo se puede editar un registro a la vez");
                FacesContext.getCurrentInstance().addMessage(null, fm);
            }
        }
        RequestContext.getCurrentInstance().addCallbackParam("show", bShow);
    }

    public void saveActivity() {
        try {
            Boolean rangoValido= Boolean.FALSE;
            if(validationFieldsEdit(activity)){
                    activity.setTblCampana(activityDTO.getTblCampana());
                    serviceCampana.saveActivity(activity);
                    String[] toMails = {"jorged@adinfi.com","sandram@adinfi.com"};
             //       SendMail.sendActivity(toMails, activity, true);
                    init();
                    rangoValido= Boolean.TRUE;
            }
            RequestContext.getCurrentInstance().addCallbackParam("rangoValido",rangoValido);
        } catch (Exception e) {
            LOG.error(e);
            FacesMessage fm = new FacesMessage("Ocurrio un error al guardar la actividad");
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }
	
	public boolean validRangeDates(Date init, Date end){
		if(init!=null&&end!=null){
			return init.after(end);
		}else{
			return false;
		}
	}

	public void deleteActivity() {
		if (activitySelection != null) {
			Boolean bValidation = Boolean.FALSE;
			StringBuilder actBuilder = new StringBuilder(
					"Las actividades automaticas no pueden borrarse ( ");
			for (ActivityDTO dto : activitySelection) {
				try {
					TblCampanaActividades deleteAct = serviceCampana
							.getActivityDetail(dto.getId());
					if (deleteAct.getEsFlujo() == Constants.NUMERO_ES_FLUJO) {
						bValidation = Boolean.TRUE;
						actBuilder.append(dto.getNombreActividad());
						actBuilder.append(",");
					} else {
						serviceCampana.deleteActivity(deleteAct);
						LOG.debug(deleteAct.getNombreActividad());
					}
				} catch (GeneralException e) {
					LOG.error(e);
					FacesMessage fm = new FacesMessage(
							"Error al elimnar la actividad "
									+ dto.getNombreActividad());
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			}
			if (bValidation) {
				actBuilder.deleteCharAt(actBuilder.length() - 1);
				actBuilder.append(")");
				FacesMessage fm = new FacesMessage(actBuilder.toString());
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
			init();
		}
	}

	public void deleteDelegateActivity() {
		if (delegateSelection != null) {
			if (delegateSelection.length > 0) {
				try {
					for (DelegateDTO delegate : delegateSelection) {
						TblDelegacionActividades delDeleAct = serviceCampana
								.getDelegateActivity(delegate.getId());
						serviceCampana.deleteDelegateActivity(delDeleAct);
					}
				} catch (GeneralException e) {
					LOG.error(e);
					FacesMessage fm = new FacesMessage(
							"Ocurrio un error al borrar los registros");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			} else {
				FacesMessage fm = new FacesMessage(
						"Favor de seleccionar un registro");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		}
		initDelegate();
	}

	private TblCampanaActividades fillActivity(
			TblCampanaActividades tblCampanaActividad) throws GeneralException {
		try{
			tblCampanaActividad.setEsFlujo(Constants.NUMERO_ES_FLUJO_PERS);
			tblCampanaActividad.setFechaCreacion(new Date());
			tblCampanaActividad.setUsuarioCreacion(5);
			tblCampanaActividad.setIdUsuarioResp(5);
			tblCampanaActividad.setIdRol(1);
			long idCampana_local = tblCampanaActividad.getTblCampana().getIdCampana();
			tblCampanaActividad.setTblCampana(serviceCampana.getCampana(idCampana_local));
			return tblCampanaActividad;
		}catch(Exception e){
			throw new GeneralException(e);
		}
	}

	public void completeActivity() {
		if (activitySelection != null) {
			if (activitySelection.length == 0) {
				FacesMessage fm = new FacesMessage(
						"Favor de seleccionar el registro que desee modificar");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			} else {
				try {
					for (ActivityDTO dto : activitySelection) {
						activity = serviceCampana
								.getActivityDetail(dto.getId());
						activity.setPorcentajeCompletado(Integer.valueOf(100));
						activity.getCatEstatus().setIdestatus(Constants.STATUS_CERRADO);
						serviceCampana.saveActivity(activity);
					}
					initAd();
				} catch (GeneralException e) {
					LOG.error(e);
					FacesMessage fm = new FacesMessage(
							"Ocurrio un error al completar las actividades");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			}
		}
	}

	public void delegateActivity() {
		try {
			if (activitySelection!=null && activitySelection.length>0) {
				serviceCampana.delegateActivity(activitySelection, Integer.valueOf(delegate.getIdUsuarioDelegado()));
				initAd();
			}
		} catch (GeneralException e) {
			LOG.error(e);
			FacesMessage fm = new FacesMessage(
					"Ocurrio un error al guardar la actividad");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}

	private void fillListUser() {
		try {
			List<Map<String, String>> responseArray = MBUtil.getCatalogValues(
					Constants.CAT_USER, serviceDynamicCatalogs);
			if (responseArray != null) {
				for (Map<String, String> mapValues : responseArray) {
					if (userLogin != null && userLogin.getCatRole() != null && userLogin.getCatRole().getIdrole() != Constants.PERFIL_ADMIN) {
						if (userLogin.getCatRole().getDesc().compareToIgnoreCase(mapValues.get(Constants.ATT_ROLE)) == 0) {
							UsuarioDTO usuarioDTO = new UsuarioDTO();
							usuarioDTO.setName(mapValues.get(Constants.ATT_NAME));
							usuarioDTO.setEmail(mapValues.get(Constants.ATT_EMAIL));
							usuarioDTO.setPlastName(mapValues.get(Constants.ATT_PLAST_NAME));
							usuarioDTO.setUserId(Integer.valueOf(mapValues.get(Constants.ATT_ID)));
							lstUsuarios.add(usuarioDTO);
						}
					} else {
						UsuarioDTO usuarioDTO = new UsuarioDTO();
						usuarioDTO.setName(mapValues.get(Constants.ATT_NAME));
						usuarioDTO.setEmail(mapValues.get(Constants.ATT_EMAIL));
						usuarioDTO.setPlastName(mapValues.get(Constants.ATT_PLAST_NAME));
						usuarioDTO.setUserId(Integer.valueOf(mapValues.get(Constants.ATT_ID)));
						lstUsuarios.add(usuarioDTO);
					}
				}
			}
		} catch (GeneralException e) {
			LOG.error(e);
		}
	}
	
	public void printActivities() throws DocumentException, IOException, PrintException, GeneralException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Actividades");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(listActivityDTO!=null){
			int rownum = 0;
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFRow row= sheet.createRow(rownum++);
			int cellnum= 0;
			HSSFCell cell = row.createCell(cellnum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("Actividad");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("Estatus");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("Vencimiento");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("Fecha Cierre");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("Campaña");

			cell = row.createCell(cellnum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("Responsable");

			for(ActivityDTO dto: listActivityDTO){
				row= sheet.createRow(rownum++);
				cellnum= 0;
				cell = row.createCell(cellnum++);
				cell.setCellValue(dto.getActividad());
				cell = row.createCell(cellnum++);
				cell.setCellValue(MBUtil.genericSearch(Constants.ATT_CODE,
						String.valueOf(activity.getCatEstatus().getIdestatus()), Constants.ATT_NAME,
						Constants.CAT_STATUS, serviceDynamicCatalogs));
				cell = row.createCell(cellnum++);
				cell.setCellValue(dto.getFechaFin()!=null?sdf.format(dto.getFechaFin()):"");
				cell = row.createCell(cellnum++);
				cell.setCellValue(dto.getFechaCierre()!=null?sdf.format(dto.getFechaCierre()):"");
				cell = row.createCell(cellnum++);
				cell.setCellValue(dto.getTblCampana().getNombre());
				cell = row.createCell(cellnum++);
				cell.setCellValue(dto.getUsuarioByIdUsuarioResp().getFullName());
			}
		}

		FileOutputStream fileOut= new FileOutputStream("Actividades"+userLogin.getLogin()+".xls");
		workbook.write(fileOut);
		fileOut.close();

		FileInputStream fis = new FileInputStream("Actividades"+userLogin.getLogin()+".xls");
		PrintRequestAttributeSet pras =     new HashPrintRequestAttributeSet();
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = defaultService.createPrintJob();
        DocAttributeSet das = new HashDocAttributeSet();
        Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, das);

        job.print(doc, pras);
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		Long days = MBUtil.dateDiff(activity.getFechaInicio(),
				activity.getFechaFin(), Constants.MILLISECS_PER_DAY);
		Long hrs = MBUtil.dateDiff(activity.getFechaInicio(),
				activity.getFechaFin(), Constants.MILLISECS_PER_HR);
		return Messages.getString(Constants.MSG_DURACION, days, hrs);
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the serviceDynamicCatalogs
	 */
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	/**
	 * @param serviceDynamicCatalogs
	 *            the serviceDynamicCatalogs to set
	 */
	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	/**
	 * @return the serviceCampana
	 */
	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}

	/**
	 * @param serviceCampana
	 *            the serviceCampana to set
	 */
	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	/**
	 * @return the listActivity
	 */
	public List<TblCampanaActividades> getListActivity() {
		return listActivity;
	}

	/**
	 * @param listActivity
	 *            the listActivity to set
	 */
	public void setListActivity(List<TblCampanaActividades> listActivity) {
		this.listActivity = listActivity;
	}

	/**
	 * @return the listCampana
	 */
	public Map<Long, TblCampana> getListCampana() {
		return listCampana;
	}

	/**
	 * @param listCampana
	 *            the listCampana to set
	 */
	public void setListCampana(Map<Long, TblCampana> listCampana) {
		this.listCampana = listCampana;
	}

	/**
	 * @return the listActivityDTO
	 */
	public List<ActivityDTO> getListActivityDTO() {
		return listActivityDTO;
	}

	/**
	 * @param listActivityDTO
	 *            the listActivityDTO to set
	 */
	public void setListActivityDTO(List<ActivityDTO> listActivityDTO) {
		this.listActivityDTO = listActivityDTO;
	}

	/**
	 * @return the scheduleActivities
	 */
	public ScheduleModel getScheduleActivities() {
		return scheduleActivities;
	}

	/**
	 * @param scheduleActivities
	 *            the scheduleActivities to set
	 */
	public void setScheduleActivities(ScheduleModel scheduleActivities) {
		this.scheduleActivities = scheduleActivities;
	}

	/**
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(MenuModel model) {
		this.model = model;
	}

	/**
	 * @return the listActivityToday
	 */
	public List<ActivityDTO> getListActivityToday() {
		return listActivityToday;
	}

	/**
	 * @param listActivityToday
	 *            the listActivityToday to set
	 */
	public void setListActivityToday(List<ActivityDTO> listActivityToday) {
		this.listActivityToday = listActivityToday;
	}

	/**
	 * @return the listActivityTomorrow
	 */
	public List<ActivityDTO> getListActivityTomorrow() {
		return listActivityTomorrow;
	}

	/**
	 * @param listActivityTomorrow
	 *            the listActivityTomorrow to set
	 */
	public void setListActivityTomorrow(List<ActivityDTO> listActivityTomorrow) {
		this.listActivityTomorrow = listActivityTomorrow;
	}

	/**
	 * @return the modelActivities
	 */
	public MenuModel getModelActivities() {
		return modelActivities;
	}

	/**
	 * @param modelActivities
	 *            the modelActivities to set
	 */
	public void setModelActivities(MenuModel modelActivities) {
		this.modelActivities = modelActivities;
	}

	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	/**
	 * @return the activity
	 */
	public TblCampanaActividades getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(TblCampanaActividades activity) {
		this.activity = activity;
	}

	/**
	 * @return the listCampanaCombo
	 */
	public List<TblCampana> getListCampanaCombo() {
		return listCampanaCombo;
	}

	/**
	 * @param listCampanaCombo
	 *            the listCampanaCombo to set
	 */
	public void setListCampanaCombo(List<TblCampana> listCampanaCombo) {
		this.listCampanaCombo = listCampanaCombo;
	}

	/**
	 * @return the listEstatusCombo
	 */
	public List<GenericItem> getListEstatusCombo() {
		return listEstatusCombo;
	}

	/**
	 * @param listEstatusCombo
	 *            the listEstatusCombo to set
	 */
	public void setListEstatusCombo(List<GenericItem> listEstatusCombo) {
		this.listEstatusCombo = listEstatusCombo;
	}

	/**
	 * @return the activityModel
	 */
	public ActivityModel getActivityModel() {
		return activityModel;
	}

	/**
	 * @param activityModel
	 *            the activityModel to set
	 */
	public void setActivityModel(ActivityModel activityModel) {
		this.activityModel = activityModel;
	}

	/**
	 * @return the activitySelection
	 */
	public ActivityDTO[] getActivitySelection() {
		return activitySelection;
	}

	/**
	 * @param activitySelection
	 *            the activitySelection to set
	 */
	public void setActivitySelection(ActivityDTO[] activitySelection) {
		if (activitySelection != null)
			this.activitySelection = activitySelection.clone();
	}

	/**
	 * @return the idCampana
	 */
	public Long getIdCampana() {
		return idCampana;
	}

	/**
	 * @param idCampana
	 *            the idCampana to set
	 */
	public void setIdCampana(Long idCampana) {
		this.idCampana = idCampana;
	}

	/**
	 * @return the activityDTO
	 */
	public ActivityDTO getActivityDTO() {
		return activityDTO;
	}

	/**
	 * @param activityDTO
	 *            the activityDTO to set
	 */
	public void setActivityDTO(ActivityDTO activityDTO) {
		this.activityDTO = activityDTO;
	}

	/**
	 * @return the createActivity
	 */
	public TblCampanaActividades getCreateActivity() {
		return createActivity;
	}

	/**
	 * @param createActivity
	 *            the createActivity to set
	 */
	public void setCreateActivity(TblCampanaActividades createActivity) {
		this.createActivity = createActivity;
	}

	/**
	 * @return the delegate
	 */
	public TblDelegacionActividades getDelegate() {
		return delegate;
	}

	/**
	 * @param delegate
	 *            the delegate to set
	 */
	public void setDelegate(TblDelegacionActividades delegate) {
		this.delegate = delegate;
	}

	/**
	 * @return the delegateModel
	 */
	public DelegateModel getDelegateModel() {
		return delegateModel;
	}

	/**
	 * @param delegateModel
	 *            the delegateModel to set
	 */
	public void setDelegateModel(DelegateModel delegateModel) {
		this.delegateModel = delegateModel;
	}

	/**
	 * @return the delegateSelection
	 */
	public DelegateDTO[] getDelegateSelection() {
		return delegateSelection;
	}

	/**
	 * @param delegateSelection
	 *            the delegateSelection to set
	 */
	public void setDelegateSelection(DelegateDTO[] delegateSelection) {
		if (delegateSelection != null)
			this.delegateSelection = delegateSelection.clone();
	}

	/**
	 * @return the lstUsuarios
	 */
	public List<UsuarioDTO> getLstUsuarios() {
		return lstUsuarios;
	}

	/**
	 * @param lstUsuarios
	 *            the lstUsuarios to set
	 */
	public void setLstUsuarios(List<UsuarioDTO> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	/**
	 * @return the lstUsuarioDelega
	 */
	public List<UsuarioDTO> getLstUsuarioDelega() {
		return lstUsuarioDelega;
	}

	/**
	 * @param lstUsuarioDelega
	 *            the lstUsuarioDelega to set
	 */
	public void setLstUsuarioDelega(List<UsuarioDTO> lstUsuarioDelega) {
		this.lstUsuarioDelega = lstUsuarioDelega;
	}

	public List<ActivityDTO> getActivityModelFiltered() {
		return activityModelFiltered;
	}

	public void setActivityModelFiltered(List<ActivityDTO> activityModelFiltered) {
		this.activityModelFiltered = activityModelFiltered;
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
	
	public Date getToday(){
		return new Date();
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

	public void setServiceTblActividades(ServiceTblActividades serviceTblActividades) {
		this.serviceTblActividades = serviceTblActividades;
	}

	public List<TblActividad> getTblActividadList() {
		return tblActividadList;
	}

	public void setTblActividadList(List<TblActividad> tblActividadList) {
		this.tblActividadList = tblActividadList;
	}

	public List<CatActPred> getCatActPredList() {
		return catActPredList;
	}

	public void setCatActPredList(List<CatActPred> catActPredList) {
		this.catActPredList = catActPredList;
	}

	public TblCampanaActividades getTblCampanaActividadesSelected() {
		return tblCampanaActividadesSelected;
	}

	public void setTblCampanaActividadesSelected(TblCampanaActividades tblCampanaActividadesSelected) {
		this.tblCampanaActividadesSelected = tblCampanaActividadesSelected;
	}

	public ServiceUsuarios getServiceUsuarios() {
		return serviceUsuarios;
	}

	public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
		this.serviceUsuarios = serviceUsuarios;
	}

}