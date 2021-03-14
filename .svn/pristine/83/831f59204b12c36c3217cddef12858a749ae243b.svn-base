package com.adinfi.catalogs.bos.exception.catalogs;

import java.util.List;

public class CatalogException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final int CAT_ERR_UNKNOWN = 1;
	public static final int CAT_ERR_DB = 2;
	public static final int CAT_ERR_REQUIRED_VAL = 3;
	public static final int CAT_ERR_VAL_REF_NOT_FOUND = 4;
	public static final int CAT_ERR_VAL_REF_FOUND = 5;
	public static final int CAT_ERR_BATCH = 6;
	public static final int CAT_ERR_VAL_EXT_REF_FOUND = 7;
	private String errorMessage = null;
	private CatalogExceptionInfo catExInfo = null;
	private List<CatalogException> lstCatException = null;
	private Exception exception = null;
	private int errorCode = 0;

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the lstCatException
	 */
	public List<CatalogException> getLstCatException() {
		return lstCatException;
	}

	/**
	 * @param lstCatException
	 *            the lstCatException to set
	 */
	public void setLstCatException(List<CatalogException> lstCatException) {
		this.lstCatException = lstCatException;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	public CatalogException(int errorCode,
			CatalogExceptionInfo catExceptionInfo, Exception e) {
		this.errorCode = errorCode;
		this.catExInfo = null;
		this.exception = e;
		lstCatException = null;
	}

	public CatalogException(int errorCode,
			List<CatalogException> lstCatException) {
		this.errorCode = errorCode;
		this.catExInfo = null;
		this.exception = null;
		this.lstCatException = lstCatException;
	}

	public CatalogExceptionInfo getCatExInfo() {
		return catExInfo;
	}

	public void setCatExInfo(CatalogExceptionInfo catExInfo) {
		this.catExInfo = catExInfo;
	}

	public String getErrMessage() {
		String message = "";
		switch (errorCode) {
		case CAT_ERR_UNKNOWN:
			message = this.exception.getMessage();
			break;
		case CAT_ERR_REQUIRED_VAL:
			message = "El valor para el atributo "
					+ this.catExInfo.getAttrName() + " es requerido ";
			break;
		case CAT_ERR_VAL_REF_FOUND:
			message = "El registro no puede ser borrado porque existe una referencia al atributo "
					+ this.catExInfo.getAttrName() + "  ";
			break;
		case CAT_ERR_BATCH:
			message = "Errores multiples al ejecutar las operaciones ";
			break;
		case CAT_ERR_VAL_EXT_REF_FOUND:
			message = "El registro no puede ser borrado porque existe una referencia externa a la tabla "
					+ this.catExInfo.getExtFkTable() + "  ";
			break;

		}
		return message;

	}

}
