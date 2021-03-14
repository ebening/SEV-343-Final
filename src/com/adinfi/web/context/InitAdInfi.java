package com.adinfi.web.context;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.presentation.views.util.Constants;

import java.io.*;
import java.util.*;

public class InitAdInfi extends HttpServlet implements ServletContextListener,
		HttpSessionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3077888999893180131L;
	private static Logger LOG = Logger.getLogger(InitAdInfi.class);
	/** Notification that the web module is ready to process requests***/
	public void contextInitialized(ServletContextEvent sce) {
		initConfig(sce);
	}

	/** Notification that the servlet context is about to be shut down**/
	public void contextDestroyed(ServletContextEvent sce) {
		/** throw new
		 java.lang.UnsupportedOperationException("Method contextDestroyed() not yet implemented.");**/
	}

	/** Notification that a session was created**/
	public void sessionCreated(HttpSessionEvent se) {
		/** throw new
		// java.lang.UnsupportedOperationException("Method sessionCreated() not yet implemented.");
		// GlobalWeb.log.info("Sesion creada " );
		// GlobalWeb.log.error("errror ");**/
	}

	/** Notification that a session was invalidated**/
	public void sessionDestroyed(HttpSessionEvent se) {
		/** throw new
		// java.lang.UnsupportedOperationException("Method sessionDestroyed() not yet implemented.");**/
	}

	public void initConfig(ServletContextEvent sce) {

		String dir_config = sce.getServletContext().getInitParameter("dir_config");
		if (dir_config == null || dir_config.trim().equals("") == true) {
			LOG.info("No se proporciono el directorio de indexacion de anuncios ");
		}
		GlobalDefines.DIR_CONFIG = dir_config;
		loadFileConfig(GlobalDefines.FILE_CONFIG);
		loadFileConfig(GlobalDefines.FILE_CONFIG_ANALISIS);
		LOG.info("Setting  GlobalDefines.DIR_CONFIG to :  "+ GlobalDefines.DIR_CONFIG);
	}
	
	
	public static void loadFileConfig(String fileConfig) {
		Properties p = new Properties();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(GlobalDefines.DIR_CONFIG+ fileConfig);
		} catch (Exception e) {
			LOG.info("No se pudo cargar habrir el archivo : "+ fileConfig);
			LOG.error(e);
			System.exit(0);
		}

		try {
			if(fileConfig.equals(GlobalDefines.FILE_CONFIG)){
				loadInfoAdmasterConfig(p, fi);
			}else if (fileConfig.equals(GlobalDefines.FILE_CONFIG_ANALISIS)){
				loadInfoAnalisisConfig(p, fi);
			}

		} catch (IOException e) {
			LOG.info("No se pudo cargar las propiedades del archivo : walook.config ");
			LOG.error(e);
			System.exit(0);
		} finally {
			if (fi != null) {
				try {
					fi.close();
				} catch (Exception e) {
					LOG.error(e);
				}
			}
		}
	}
	
	
	private static void loadInfoAdmasterConfig(Properties p ,FileInputStream fi) throws IOException{
		p.load(fi);
		GlobalDefines.COMPANY = p.getProperty("company");
		
		/*GlobalDefines.ICM_LIBRARY = p.getProperty("icm.library");
		GlobalDefines.ICM_ADMIN = p.getProperty("icm.admin");
		GlobalDefines.ICM_PASSWORD = p.getProperty("icm.password");
		GlobalDefines.ICM_SCHEMA = p.getProperty("icm.schema");
		GlobalDefines.CM_ITEM_TYPE_1 = p.getProperty("cm.item.type.1");*/
	}
	
	private static void loadInfoAnalisisConfig(Properties p ,FileInputStream fi) throws IOException{
		
			p.load(fi);
			
			GlobalDefines.ANALISIS_VHISTORICAS_CONFIG = p.getProperty(Constants.VHISTORICAS);
			GlobalDefines.ANALISIS_MCONTRIBUCION_CONFIG = p.getProperty(Constants.MCONTRIBUCION);
			GlobalDefines.ANALISIS_EXISTENCIAS_CONFIG = p.getProperty(Constants.EXISTENCIAS);
			GlobalDefines.ANALISIS_NUEVO_CONFIG = p.getProperty(Constants.NUEVO);
			GlobalDefines.ANALISIS_UPROMO_CONFIG = p.getProperty(Constants.UPROMO);
			GlobalDefines.ANALISIS_PROMO_AANTERIOR_CONFIG = p.getProperty(Constants.PROMO_AANTERIOR);
			GlobalDefines.ANALISIS_DIAS_ANTIGUEDAD_CONFIG = Integer.parseInt( p.getProperty(Constants.DIAS_ANTIGUEDAD) );
			GlobalDefines.ANALISIS_TIENDA_BASE_CONFIG = Integer.parseInt( p.getProperty(Constants.TIENDA_BASE) );

	}

	/**public static void loadConfig() {
		Properties p = new Properties();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(GlobalDefines.DIR_CONFIG
					+ GlobalDefines.FILE_CONFIG);
		} catch (Exception e) {
			System.out
					.println("No se pudo cargar habrir el archivo : admaster.config ");
			e.printStackTrace();
			System.exit(0);
		}

		try {
			p.load(fi);
			GlobalDefines.COMPANY = Integer.parseInt(p.getProperty("company"));
			System.out.println("COMPANY :************************ :"
					+ GlobalDefines.COMPANY);

		} catch (IOException e) {
			System.out
					.println("No se pudo cargar las propiedades del archivo : walook.config ");
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (fi != null) {
				try {
					fi.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void loadFileConfigAnalisis(){
		Properties p 			= new Properties();
		FileInputStream fi 		= null;
		try {
			fi = new FileInputStream(GlobalDefines.DIR_CONFIG + GlobalDefines.FILE_CONFIG_ANALISIS);
		} catch (Exception e) {
			System.out.println("No se pudo cargar habrir el archivo : "+ GlobalDefines.FILE_CONFIG_ANALISIS );
			e.printStackTrace();
			System.exit(0);
		}
		try {
			p.load(fi);
			
			GlobalDefines.ANALISIS_VHISTORICAS_CONFIG = p.getProperty(Constants.VHISTORICAS);
			GlobalDefines.ANALISIS_MCONTRIBUCION_CONFIG = p.getProperty(Constants.MCONTRIBUCION);
			GlobalDefines.ANALISIS_EXISTENCIAS_CONFIG = p.getProperty(Constants.EXISTENCIAS);
			GlobalDefines.ANALISIS_NUEVO_CONFIG = p.getProperty(Constants.NUEVO);
			GlobalDefines.ANALISIS_UPROMO_CONFIG = p.getProperty(Constants.UPROMO);
			GlobalDefines.ANALISIS_PROMO_AANTERIOR_CONFIG = p.getProperty(Constants.PROMO_AANTERIOR);
			GlobalDefines.ANALISIS_DIAS_ANTIGUEDAD_CONFIG = Integer.parseInt( p.getProperty(Constants.DIAS_ANTIGUEDAD) );
			GlobalDefines.ANALISIS_TIENDA_BASE_CONFIG = Integer.parseInt( p.getProperty(Constants.TIENDA_BASE) );
			

		} catch (IOException e) {
			System.out.println("No se pudo cargar las propiedades del archivo : walook.config ");
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (fi != null) {
				try {
					fi.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}**/

}
