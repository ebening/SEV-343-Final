package com.adinfi.seven.presentation.views.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Commons {
	
	//Constantes incluidas para funcion de conversion de numero a letra.
	private static final String STR_VACIO = "";
	private static final String STR_ASTERISCO = "*";
	private static final String STR_PARENTESIS_ABRE = "(";
	private static final String STR_PARENTESIS_CIERRA = ")";
	private static final String ERROR_FECHA_FORMATO = "ERROR: Intranet@Commons.sqldateToString -> El formato para la fecha no es valido.";
	private static final long LONG_MIL=1000L;
	private static final long LONG_SESENTA=60L;
	private static final long LONG_VEINTICUATRO=24L;
	
	public static String sqldateToString(java.sql.Date date,String format) {
		SimpleDateFormat formatter = null;
		String result = null;
		
		if (date==null) return "";
		
		try {
			formatter = new SimpleDateFormat(format,new java.util.Locale("es","MX"));
			result = formatter.format(new java.util.Date(date.getTime()));
		} catch(IllegalArgumentException illegalException) {
			Util.logger(Commons.class).error(ERROR_FECHA_FORMATO);
		} catch(Exception e) {
			Util.logger(Commons.class).error("ERROR: Intranet@Commons.sqldateToString");
		}
		
		return result;
	}
	
	public static java.sql.Date stringToSqldate(String date,String format) {
		SimpleDateFormat formatter = null;
		java.sql.Date result = null;
		
		try {
			formatter = new SimpleDateFormat(format);
			result = new java.sql.Date(formatter.parse(date).getTime());
		} catch(IllegalArgumentException illegalException) {
			Util.logger(Commons.class).error("ERROR: Commons.stringToSqldate -> El formato para la fecha no es valido.");
		} catch(Exception e) {
			Util.logger(Commons.class).error("ERROR: Commons.stringToSqldate");
		}
		
		return result;
		
	}

	public static String timestampToString(java.sql.Timestamp timestamp,String format) {
		SimpleDateFormat formatter = null;
		String result = null;
		
		if (timestamp==null) return "";
		
		try {
			formatter = new SimpleDateFormat(format);
			result = formatter.format(new java.util.Date(timestamp.getTime()));
		} catch(IllegalArgumentException illegalException) {
			Util.logger(Commons.class).error(ERROR_FECHA_FORMATO);
		} catch(Exception e) {
			Util.logger(Commons.class).error("ERROR: Intranet@Commons.timestampToString");
		}
		
		return result;
	}
	
	public static java.sql.Timestamp stringToTimestamp(String timestamp,String format) {
		SimpleDateFormat formatter = null;
		java.sql.Timestamp result = null;
		
		try {
			formatter = new SimpleDateFormat(format);
			result = new java.sql.Timestamp (formatter.parse(timestamp).getTime());
		} catch(IllegalArgumentException illegalException) {
			Util.logger(Commons.class).error("ERROR: Intranet@Commons.stringToSqldate -> El formato para la fecha no es valido.");
		} catch(Exception e) {
			Util.logger(Commons.class).error("ERROR: Intranet@Commons.stringToSqldate");
		}
		
		return result;
		
	}

	
	public static String getNow(String format) {
		SimpleDateFormat formatter = null;
		String result = null;
		
		try {
			formatter = new SimpleDateFormat(format);
			result = formatter.format(Calendar.getInstance().getTime());
		} catch(IllegalArgumentException illegalException) {
			Util.logger(Commons.class).error(ERROR_FECHA_FORMATO);
		} catch(Exception e) {
			Util.logger(Commons.class).error("ERROR: Intranet@Commons.getNow");
		}
		return result;
	}
	
	/**public static String getApplicationFilesPath(String application) {
		String filesPath = PropertiesHelper.getInstance().getProperty("path-for-apps-files");
		if (!filesPath.endsWith("/")) filesPath += "/";
		if (filesPath.charAt(0)!='/') filesPath = "/" + filesPath;
		return (filesPath + application + "/");
	}

	public static String getFullUploadPath(String application) {
        String webServerPath = PropertiesHelper.getInstance().getProperty("path-for-web-server");
        String filesPath = PropertiesHelper.getInstance().getProperty("path-for-apps-files");
        StringBuffer fullPath = new StringBuffer(webServerPath);
        if (!webServerPath.endsWith("/")) fullPath.append("/");
        fullPath.append(filesPath).append("/");
        if (!application.matches("")) fullPath.append(application).append("/");
        
        return fullPath.toString().replaceAll("//","/");
	}*/

	public static String dateToString(java.util.Date date,String format) {
		SimpleDateFormat formatter = null;
		String result = null;
		
		try {
			formatter = new SimpleDateFormat(format);
			result = formatter.format(new java.util.Date(date.getTime()));
		} catch(IllegalArgumentException illegalException) {
			Util.logger(Commons.class).error(ERROR_FECHA_FORMATO);
		} catch(Exception e) {
			Util.logger(Commons.class).error("ERROR: Intranet@Commons.dateToString");
		}
		
		return result;
	}

	public static int parseIntDefaultCero(String integer) {
		return parseIntDefault(integer, 0);
	}
	
	public static int parseIntDefault(String integer, int defaultValue) {
		int value;
		try {
			value = Integer.parseInt(integer);
		} catch (NumberFormatException e) {
			value = defaultValue;
		}
		return value;
	}

	public static double parseDoubleDefaultCero(String ddouble) {
		return parseDoubleDefault(ddouble, 0);
	}
	
	public static double parseDoubleDefault(String ddouble, double defaultValue) {
		double value;
		
		try {
			value = Double.parseDouble(ddouble);
		} catch (NumberFormatException e) {
			value = defaultValue;
		} catch (Exception e) {
			/**Util.logger(Commons.class)Factory.Util.logger(Commons.class)("infraestructure.Util.logger(Commons.class)","Commons",e.getMessage() + " / ddouble = " + ddouble);*/
			value = defaultValue;
			Util.logger(Commons.class).error(e);
		}
		return value;
	}	

	public static int getYear(){	
		int result=Calendar.getInstance().get(Calendar.YEAR);
		return result;
	}
	
	/**public static int getApplicationId(String url) {
		int appId = 0;
		String[] menuPathInfo = url.split("/");
		if (menuPathInfo!=null && menuPathInfo.length>3) {
			appId = Commons.parseIntDefaultCero(PropertiesHelper.getInstance().getProperty(menuPathInfo[menuPathInfo.length-2]));
		}
		return appId; 
	}*/
	
	@SuppressWarnings("all")
	public static String monthToString(int month) {
		switch (month) {
			case 1: return "Enero";
			case 2: return "Febrero";
			case 3: return "Marzo";
			case 4: return "Abril";
			case 5: return "Mayo";
			case 6: return "Junio";
			case 7: return "Julio";
			case 8: return "Agosto";
			case 9: return "Septiembre";
			case 10: return "Octubre";
			case 11: return "Noviembre";
			case 12: return "Diciembre";
			default: return null;
		}
	}

    public static int getYearDifferenceFromToday(long compareDate) {
    		int difference = 0;
    		Calendar now = Calendar.getInstance();
    		now.add(Calendar.YEAR,-1);
    		long minus = now.getTimeInMillis() - compareDate;
    		for (difference=0;minus>=0;difference++) {
    			now.add(Calendar.YEAR,-1);
    			minus = now.getTimeInMillis() - compareDate;
    		}
    		
    		return difference;
    }

    public static int getMonthsDifference(long first, long last) {
		int difference = 0;
		Calendar greater = Calendar.getInstance();
		greater.setTimeInMillis(last);
		greater.add(Calendar.MONTH,-1);
		long minus = greater.getTimeInMillis() - first;
		for (difference=0;minus>=0;difference++) {
			greater.add(Calendar.MONTH,-1);
			minus = greater.getTimeInMillis() - first;
		}
		
		return difference;
    }

    
    public static String convertTextToHTML(String text) {
    		return text.replaceAll("\n","<br>");
    }
    
	public static int getDaysBetween(java.util.Date begin, java.util.Date end) {
		long offset = end.getTime() - begin.getTime();
		return (int)(offset / (LONG_MIL * LONG_SESENTA * LONG_SESENTA * LONG_VEINTICUATRO));
	}
	
	public static int getDaysOfWeekendsBetween(java.util.Date begin, java.util.Date end) {
		int SyD = 0;
		int days = getDaysBetween(begin, end);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(begin.getTime());
		int weekDay;
		for (int i=0; i <= days; i++) {
			weekDay = c.get(Calendar.DAY_OF_WEEK);
			if ((weekDay == Calendar.SATURDAY) || (weekDay == Calendar.SUNDAY)) {
				SyD++;
			}
			c.add(Calendar.DATE, 1);
		}		
		return SyD;
	}
	
	public static String repeatString(String str, int loop) {
		StringBuffer repeated = new StringBuffer();
		for (int i=0;i<loop;i++) 
			repeated.append(str);
		return repeated.toString();
	}

	
    /*
     * Funciones proporcionadas por Banregio para la conversion de un numero a letra 
     */
    
	/**
	 * Funci&oacute;n para devolver el valor en letra de un cantidad.
	 * 
	 * @author Banregio Grupo Financiero
	 * @param cantidad
	 * @param abreviacionStr
	 * @param monedaDescripcion
	 * @return 
	 */
	@SuppressWarnings("all")
	public static String deNumerosALetras(double cantidad, String abreviacionStr, String monedaDescrpcion) {
		String cantidadEnLetras = STR_VACIO;
		
		String strCien = null;
		String strDiez = null;
		String strUno = null;
		
		long centavos	 = 0;
		long unidades	 = 0;
		long diez		 = 0;
		long cien	 	 = 0;
		long milesMill = 0;
		
		int i = 0;
		long numero = 0;
		try{
			if (cantidad > 999999999999.99) {
				cantidadEnLetras = "Error numero muy Grande";
				return	cantidadEnLetras;
			}
		
			numero = (long)(cantidad / 1000000000);
		
			cantidadEnLetras = STR_ASTERISCO + STR_ASTERISCO + STR_ASTERISCO + STR_PARENTESIS_ABRE;
		
			while (i < 4) {
				i++;
				strCien = STR_VACIO;
				strDiez = STR_VACIO;
				strUno = STR_VACIO;
				if (numero != 0 ) {
					cien = (numero / 100);
					diez = (numero - (cien * 100)) / 10;
					unidades = numero - ((numero / 10) * 10);
					strCien = STR_VACIO;
				
					switch ((int)cien) {
						case 0:
						strCien = STR_VACIO;
						break;
						case 1:
						if(diez == 0 && unidades == 0){
							strCien = "CIEN ";
						} else {
							strCien = "CIENTO ";
						}
						break;
						
						case 2:
						strCien = "DOSCIENTOS ";
						break;						
						case 3:
						strCien = "TRESCIENTOS ";
						break;												
						case 4:
						strCien = "CUATROCIENTOS ";
						break;
						case 5:
						strCien = "QUINIENTOS ";
						break;
						case 6:
						strCien = "SEISCIENTOS ";
						break;
						case 7:
						strCien = "SETECIENTOS ";
						break;
						case 8:
						strCien = "OCHOCIENTOS ";
						break;
						case 9:
						strCien = "NOVECIENTOS ";
						break;					
					}
			
					strDiez = STR_VACIO;
					
					switch ((int)diez) {
						case 0:
						strDiez = STR_VACIO;
						break;
						case 1:
						if( unidades <= 5 && unidades > 0 ){
							 switch((int)unidades){
								case 1:
									strDiez = "ONCE ";
									break;
								case 2:
									strDiez = "DOCE ";
									break;
								case 3:
									strDiez = "TRECE ";
									break;
								case 4:
									strDiez = "CATORCE ";
									break;
								case 5:
									strDiez = "QUINCE ";
									break;
							 }							
						}else{
							strDiez = "DIEZ ";							
						}
						break;
						case 2:
						if (unidades > 0 ){
							strDiez = "VEINTI";
						} else{
							strDiez = "VEINTE ";	
						}
						break;
						case 3:
						strDiez = "TREINTA ";
						break;
						case 4:
						strDiez = "CUARENTA ";
						break;						
						case 5:
						strDiez = "CINCUENTA ";
						break;
						case 6:
						strDiez = "SESENTA ";
						break;						
						case 7:
						strDiez = "SETENTA ";
						break;
						case 8:
						strDiez = "OCHENTA ";
						break;
						case 9:
						strDiez = "NOVENTA ";
						break;					
					}
			
					if (diez == 1 && unidades > 5){
						strDiez = strDiez + " Y ";
					}
			
					if (diez > 2 && unidades != 0){
						strDiez = strDiez + " Y ";
					}									
			
					strUno = STR_VACIO;
				
					if(diez == 1 && unidades <= 5 && unidades > 0 ){					
						strUno = STR_VACIO;					
					} else{
				
						switch((int)unidades) {
							case 0:
								strUno = STR_VACIO;
								break;
							case 1:
								strUno = "UN ";
								break;
							case 2:
								strUno = "DOS ";
								break;
							case 3:
								strUno = "TRES ";
								break;
							case 4:
								strUno = "CUATRO ";
								break;
							case 5:
								strUno = "CINCO ";
								break;
							case 6:
								strUno = "SEIS ";
								break;
							case 7:
								strUno = "SIETE ";
								break;
							case 8:
								strUno = "OCHO ";
								break;
							case 9:
								strUno = "NUEVE ";
								break;
						}
					}
				}
		
				switch (i) {
					case 1:
						if( numero > 0){
							cantidadEnLetras = strCien + strDiez + strUno + "MIL ";
							milesMill = 1;
						}
				
						cantidad = cantidad - (numero * 1000000000);
						numero = (long)cantidad / 1000000;
						break;
					case 2:
						if(numero > 0 || milesMill == 1){
							cantidadEnLetras = cantidadEnLetras + strCien + 
											strDiez + strUno + "MILLON";
							if(numero > 1 || milesMill == 1){
								cantidadEnLetras = cantidadEnLetras + "ES ";
							}else{
								cantidadEnLetras = cantidadEnLetras + " ";
							}
						}
				
						cantidad = cantidad - (numero * 1000000);
						numero = (long)(cantidad / 1000);
						break;
					case 3:
						if(numero > 0){
							cantidadEnLetras = cantidadEnLetras + strCien + strDiez +
								 strUno + "MIL ";
						}	
						cantidad = cantidad - (numero * 1000);
						numero = (long)(cantidad / 1);
						break;
					case 4:													
						cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno +
							(!monedaDescrpcion.equalsIgnoreCase(STR_VACIO)?
							(monedaDescrpcion.trim()) + " ": "PESOS ");											
															
						cantidad = cantidad - numero;						
						centavos = (long)(Math.round((cantidad * 100) * Math.pow(10, 0)) / Math.pow(10, 0));
						break;								
				}
			}
		
			if(centavos == 0) {
				cantidadEnLetras = cantidadEnLetras + "00/100 " +			
				(!abreviacionStr.equalsIgnoreCase(STR_VACIO)?abreviacionStr: "M.N.");			
			}else{
				cantidadEnLetras = cantidadEnLetras +
				cerosIzq(String.valueOf((long)centavos), 2) + "/100 " + (!abreviacionStr.equalsIgnoreCase(STR_VACIO)?abreviacionStr: "M.N.");
			}
		} catch (Exception ex) {
			Util.logger(Commons.class).error(ex);
		}				
		cantidadEnLetras += STR_PARENTESIS_CIERRA + STR_ASTERISCO + STR_ASTERISCO + STR_ASTERISCO;
		return cantidadEnLetras;
	}

    public static String cerosIzq(String valorStr, int longitudEnt) {
        StringBuffer cerosStr = new StringBuffer();
        for (int i = valorStr.length(); i < longitudEnt; i++) {
        	cerosStr.append("0");
        }

        return cerosStr.toString() + valorStr;
    }

    public static String cerosIzq(long numeroLng, int longitudEnt) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < longitudEnt; i++)
        	buf.append("0");
        buf.append(String.valueOf(numeroLng).trim());
        String cerosStr = buf.toString();
        return cerosStr.substring(cerosStr.length() - longitudEnt, cerosStr.length());
    }

    public static String cerosIzq(int numeroEnt, int longitudEnt) {
        return cerosIzq((long)numeroEnt, longitudEnt);
    }
 
    /**
     * @ELIAS AKE UC
     * @param fecha
     * @param format
     * @return true si el formato de la fecha es correcto
     */
    public static boolean validaFormatoFecha(String fecha, String format){
    	try {
    	     SimpleDateFormat sdf = new SimpleDateFormat(format);
    	     sdf.setLenient(false);
    	     Date dt2 = sdf.parse(fecha);
    	     Util.logger(Commons.class).info("Fecha ok = " + dt2 + "(" + fecha + ")");
    	}catch (ParseException e) {
    		 Util.logger(Commons.class).error(e);
    	     return false;
    	}catch (IllegalArgumentException e) {
    		 Util.logger(Commons.class).error("Fecha incorrecta : " + e.getMessage());
    	     return false;
    	}
    	return true;
    }
    
    public static String bubbleSort(String cad) {
    	String x[] = cad.split(",");
    	String cadena = "";
        int n = x.length;
        for (int pass=1; pass < n; pass++) {  // count how many times
            // This next loop becomes shorter and shorter
            for (int i=0; i < n-pass; i++) {
                if (Integer.parseInt(x[i]) > Integer.parseInt(x[i+1])) {
                    // exchange elements
                    int temp = Integer.parseInt(x[i]);  x[i] = x[i+1];  x[i+1] = "" + temp;
                }
            }
        }
        
        for(int y=0; y<n; y++){
        	if(cadena.equals("")){
        		cadena = x[y];
        	}else{
        		cadena += "," + x[y];
        	}
        }
        Util.logger(Commons.class).info("CADENA_ORDENADA = "+cadena);
        return cadena;
    }

    public static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    	
    	
    }
    
    public static void main(String arg []){
    	/**Commons yo = new Commons();
    	
    	String date = yo.getNow("MMddyy");
    	System.out.println("date = "+date);*/
    }
}
