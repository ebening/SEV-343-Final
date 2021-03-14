package com.adinfi.defines;

public class GlobalDefines {
	public static String DIR_CONFIG = null;
	public static final String FILE_CONFIG = "admaster.config";
	public static String DIR_CONFIG_ANALISIS=null;
	public static final String FILE_CONFIG_ANALISIS= "config_analisis.config";
	public GlobalDefines() {
	}
	
    public static final String SEC_TYPE_ROW = "R";
    public static final String SEC_TYPE_COL = "C";
    public static final String SEC_TYPE_CELL = "N";
	
	
	public static Character HOJA_CERRADA='C';
	
	public static String COMPANY = "";
	public static String ANALISIS_VHISTORICAS_CONFIG 		= null;
	public static String ANALISIS_MCONTRIBUCION_CONFIG 		= null;
	public static String ANALISIS_EXISTENCIAS_CONFIG 		= null;
	public static String ANALISIS_NUEVO_CONFIG 				= null;
	public static String ANALISIS_UPROMO_CONFIG 			= null;
	public static String ANALISIS_PROMO_AANTERIOR_CONFIG 	= null;
	public static int ANALISIS_DIAS_ANTIGUEDAD_CONFIG 		= 0;
	public static int ANALISIS_TIENDA_BASE_CONFIG 			= 0;
		
	public static String ICM_LIBRARY = "ICMNLSDB";
	public static String ICM_ADMIN = "icmadmin";
	public static String ICM_PASSWORD = "Passw0rd";
	public static String ICM_SCHEMA = "SCHEMA=icmadmin";
	public static String CM_ITEM_TYPE_1 = "SEV_Campanas";
	public static String CM_ITEM_TYPE_2 = "SEV_ADM";
	
	/**Ruta de carga temporal de Ejecucion para subir imagen a CM*/
	public static final String RUTA_CM = "//resources//ruta_cm//";
	
	/**Objetos de sesion*/
	public static final String SESS_CURR_HOJAS="sess_curr_hojas";
	public static final String REQ_CURR_LST_TMPL_BY_USR="req_curr_lst_tmpl_by_usr" ;
	public static final String SESS_CURR_COMENT="sess_curr_coment";
	public static final String SESS_CURR_ARCHIVO="sess_curr_archivo";
	public static final String SESS_CURR_ART_ADI="sess_curr_art_adi";
	public static final String SESS_CURR_MSJ_CONF="mensaje_confirmacion";
	public static final String SESS_CURR_TYPE_MSJ_CONF="tipo_mensaje";
	public static final String SESS_CURR_TYPE_MSJ_CONF_INFO="info";
	public static final String SESS_CURR_TYPE_MSJ_CONF_WARN="warn";
	public static final String SESS_CURR_TYPE_MSJ_CONF_ERROR="error";
	/**Rutas de carga Arquitectura*/
	public static final String RUTA_CARGA = "/resources/ruta_carga/";
	public static final String RUTA_FOTO = "/resources/images/fotos_articulos/";
	public static final String RUTA_THUMBS= "/resources/images/thumbs_templates/";

}
