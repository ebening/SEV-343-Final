package com.adinfi.seven.presentation.views.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

	private static String hour = null;

	public static String getHour() {
		return hour;
	}

	public static String getActualDate() {
		String month;
		String day;
		int year;
		hour = null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		year = calendar.get(Calendar.YEAR);
		month = getData(calendar.get(Calendar.MONTH) + 1);
		day = getData(calendar.get(Calendar.DAY_OF_MONTH));

		hour = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);

		return day + "-" + month + "-" + year;
	}

	private static String getData(int data)
	{
		String strData = "" + data;
		if (data < Constants.DIEZ) {
			strData = "0" + strData;
		}
		return strData;
	}

	public static short StringToShort(String s_number) {
		short number = Constants.ZERO;
		if (s_number == null)
			return Constants.ZERO;
		try {
			number = (short) Double.parseDouble(s_number);
		} catch (NumberFormatException e) {
			return Constants.ZERO;
		}
		return number;

	}

	public static int StringToInt(String s_number) {
		int number = Constants.ZERO;
		if (s_number == null)
			return Constants.ZERO;
		try {
			number = Integer.parseInt(s_number.trim());
		} catch (NumberFormatException e) {
			return Constants.ZERO;
		}
		return number;

	}

	public static BigDecimal StringToBigDecimal(String s_number) {
		BigDecimal result = null;
		if (s_number == null) {
			return null;
		}

		try {
			result = new BigDecimal(s_number);
		} catch (NumberFormatException e) {
			result = null;
		}
		return result;

	}

	public static String putOffNulls(String txt) {
		if (txt == null)
			return txt;
		txt = txt.trim();
		if (txt.equals("") == true)
			return null;
		return txt;
	}

	public static Date parserSqlDate(String strDate) {
		strDate = strDate.replaceAll("/", "-");
		Date date = java.sql.Date.valueOf(strDate);
		return date;
	}

	public static Integer convertToInteger(String cadena) throws NumberFormatException {
		Integer numero = new Integer(cadena);
		return numero;
	}

	public static BigDecimal convertToBigDecimal(String cadena) throws NumberFormatException {
		BigDecimal numero = new BigDecimal(cadena);
		return numero;
	}
}
