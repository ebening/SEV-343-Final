package com.adinfi.seven.presentation.views.util;

public class AdManagerConstants {
	public static final String INTRA_PROPERTIES_FILE = "configuration";
	public static final String INTRA_EXTERNAL_DB_PROPERTIES_FILE = "external-db-configuration";
	
	public static final String ADM_ACTUALIZACION_MERCADERIAS_LOG = "actualizacion_mercaderias.log";
	public static final String ADM_ACTUALIZACION_MERCADERIAS_TITLE = "Actualizacón Mercaderías";
	
	public static final String BITACORA_GUARDAR_TEMPLATE = "Guardando Template";


	public static final String ADM_ESQUEMA_BD ="" ; /*** *****/
	/**public static final String ADM_ESQUEMA_BD ="SEV343DEV" ;  Seven**/
	public static final String ADM_PROGRESS_ESQUEMA_BD = "ADMINISTRADOR"; /**"PUB"; "ADMINISTRADOR";/*"DB2ADMIN";**/

	/**public static final String RUTA_REPORTE_CSV_INDISIGN = "//usr//ibm//WebSphere//AppServer//profiles//domino//installedApps//admanagerNode01Cell//admanager_war.ear//admanager.war//include//";//200.14.19.20//medios impresos//AD Manager//Interfase ADManager-InDesign//";**/
	public static final String RUTA_REPORTE_CSV_INDISIGN = "/Medios Impresos/AD Manager/Interfase ADManager-InDesign";
	public static final String HOST_MAC_FOTOS = "10.21.36.9";
	/**"/medios impresos/AD Manager/Interfase ADManager-InDesign";**/
	/**"200.14.19.20";**/
	public static final String RUTA_TEMP = "//usr//ibm//WebSphere//AppServer//profiles//domino//";//"C:\\";//
	/**Daniel Uscanga - Se agregaron los estatus posibles del SKU**/
	public static final char SKU_ELIMINADO = 'E';
	public static final char SKU_DELETE    = 'D'; 
	public static final char SKU_ACTIVO    = '-';
	public static final char SKU_INACTIVO  = 'I';
	/**Columba b, Cuando el articulo no existe en la tabla eihp al traer los detalles de progress**/
	public static final String SKU_NO_EXISTE = "NE";
	
	/**Daniel Uscanga - 25/Nov/2011 Agregue esta constante del evento, para la obtencion de los precios NLP**/
	public static final int NPL_EVENT = 810414;
	/**Daniel Uscanga - 2/Dic/2011 Estos estatus son la agrupacion de las posibles acciones para cada estatus del SKU **/
	public static final int SKU_ACCION_ELIMINAR = 1;
	public static final int SKU_ACCION_PEGAR    = 2;
	public static final int SKU_ACCION_ACTIVAR  = 3;
	public static final int SKU_ACCION_EXCEPTO  = 4;


	/**columba b 06-12-11 Constantes utilizadas en el Reporte Discrepancias**/
	/** TIPOS DE DISCREPANCIA **/
	public static final int ARTICULO_NO_ACTIVO = 1;
	public static final int DUMMY_PRECIO_CERO = 2;
	public static final int EXCLUSION = 3;
	public static final int HUECO = 4;
	public static final int PRECIO_DIFERENTE = 5;
	/** TIPOS SKU **/
	public static final int DUMMY = 2;
	public static final int REGULAR = 1;
	public static final int INFORMATIVO = 3;
	
	/**Daniel Uscanga - 2/Dic/2011 Estos estatus son la agrupacion de las posibles acciones para cada estatus del SKU **/
	public static final String MENSAJE_ELIMINAR = "QUITAR sku";
	public static final String MENSAJE_PEGAR    = "PEGAR ó CAMBIAR sku";
	public static final String MENSAJE_ACTIVAR  = "ACTIVAR sku";
	public static final String MENSAJE_EXCEPTO  = "Excepto";


	/**Eliud Carrillo - Tipos de versionamiento**/
	public static final String VERSIONAMIENTO_AUTOMATICO  = "Versionamiento Automatico";
	public static final String VERSIONAMIENTO_ESTANDARD  = "Versionamiento Estandard";
	/**
	 * Adanm 09-12-2011
	 * Se agregan constantes que se utilizaran en el
	 * reporte de SKU para extraer el valor de "Descricpion de Tipo"
	 * que viene del campo "TYPE" de la tabla "ITMP".
	 */
	public static final String SKU_TYPE_A_DESC = "Alternate";
	public static final String SKU_TYPE_B_DESC = "Special Buy";
	public static final String SKU_TYPE_C_DESC = "Class Item";
	public static final String SKU_TYPE_D_DESC = "Display";
	public static final String SKU_TYPE_E_DESC = "Expense Item";
	public static final String SKU_TYPE_K_DESC = "Kit Item";
	public static final String SKU_TYPE_L_DESC = "Special Order";
	public static final String SKU_TYPE_R_DESC = "Rental Item";
	public static final String SKU_TYPE_S_DESC = "Special Order";
	public static final String SKU_TYPE_Z_DESC = "Primary";
	
	
	/**Eliud Carrillo - almacenamiento de Resume de versiones**/
	/**public static final String FORMAT_REGISTRO_BITACORA = "{accion}&{agregados}&{eliminados}&{descripcion}&{fecha}&{hora}&{ip}&{modulo}&{usuario}";**/
	/**public static final String FORMAT_CAMBIOS_PRECIO = "{hoja}&{tienda}&{sku}&{precio}&{precioD}";**/
	/**public static final String FORMAT_NUMERO_VERSIONES = "{numeroVersiones}"; **/
	public static final String FORMAT_SEPARADOR = "~";
	
	
	/**COLUMBA B, Se agregan descripcion de Origen (VENP.Type) para el reporte de discrepancias**/
	public static final String ORIGEN_OVERSEAS = "3";
	public static final String ORIGEN_OVERSEAS_CONST = "O";
	public static final String ORIGEN_NACIONAL = "M";
	public static final String ORIGEN_FRONTERA = "F";
	public static final String ORIGEN_OVERSEAS_DESC = "Overseas";
	public static final String ORIGEN_NACIONAL_DESC = "Nacional";
	public static final String ORIGEN_FRONTERA_DESC = "Frontera";
	
	/**
	 * Constante para el calculo de huecos.
	 */
	public static final double PORCENTAJE_HUECO = 0.05d;
	

}
 