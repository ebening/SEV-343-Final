package com.adinfi.seven.bitacora;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.aop.MethodBeforeAdvice;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.Bitacora;
import com.adinfi.seven.business.domain.BitacoraMetodo;
import com.adinfi.seven.business.domain.BitacoraParam;
import com.adinfi.seven.business.domain.BitacoraTipo;
import com.adinfi.seven.business.services.ServiceBitacora;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;

public class RegistroBitacora implements MethodBeforeAdvice, Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(RegistroBitacora.class);
	private static Set<BitacoraMetodo> bitacoraMetodos = null;
	public ServiceBitacora serviceBitacora;

	@Override
	public void before(Method metodo, Object[] params, Object target)
			throws Throwable {
		try {
			if (registerMethod(metodo, target)) {
				Bitacora bitacora = new Bitacora();
				bitacora.setMetodo(metodo.getName());
				bitacora.setDao(target.getClass().getSimpleName());
				HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				bitacora.setIp(httpServletRequest.getRemoteAddr());
				Object objUser = httpServletRequest.getSession().getAttribute(
						Constants.LOGIN_VAR);
				if (objUser != null) {
					UsuarioDTO userLogin = (UsuarioDTO) objUser;
					bitacora.setUsuarioId(userLogin.getUserId());
				}
				serviceBitacora.saveBitacora(bitacora);
				if (params != null) {
					Type[] types = metodo.getGenericParameterTypes();
					for (int x = 0; x < params.length; x++) {
						Object obj = params[x];
						Type paramType = types[x];
						if (obj == null) {
							saveBitacoraParam(bitacora, paramType, null);
						} else {
							getStringValueOfFields(bitacora, paramType, obj);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private boolean registerMethod(Method metodo, Object target) {
		boolean bRet = false;
		String simpleName = target.getClass().getSimpleName();
		for (BitacoraMetodo bitacoraMetodo : getBitacoraMetodos()) {
			if (bitacoraMetodo.getNombre()
					.compareToIgnoreCase(metodo.getName()) == 0
					&& simpleName
							.compareToIgnoreCase(bitacoraMetodo.getClase()) == 0) {
				bRet = true;
			}
		}
		return bRet;
	}

	private void getStringValueOfFields(Bitacora bitacora, Object paramType,
			Object obj) throws GeneralException {
		if (obj instanceof String) {
			saveBitacoraParam(bitacora, paramType, (String) obj);
		} else if (obj instanceof Long) {
			saveBitacoraParam(bitacora, paramType, String.valueOf((Long) obj));
		} else if (obj instanceof Integer) {
			saveBitacoraParam(bitacora, paramType,
					String.valueOf((Integer) obj));
		} else if (obj instanceof Date) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			saveBitacoraParam(bitacora, paramType, df.format(obj));
		} else if (obj instanceof List) {
			List<?> objList = (List<?>) obj;
			for (Object objUnit : objList) {
				saveBitacoraParam(bitacora, paramType, objUnit);
			}
		} else {
			saveBitacoraParam(bitacora, paramType, obj);
		}
	}

	private void saveBitacoraParam(Bitacora bitacora, Object paramType,
			Object obj) throws GeneralException {
		BitacoraParam param = new BitacoraParam();
		param.setBitacora(bitacora);
		param.setParamNombre(paramType.getClass().getSimpleName());
		String value= null;
		if(obj!=null){
			try{
				value= String.valueOf(obj);
			}catch(Exception e){
				value= obj.toString();
			}
		}
		param.setParamValue(value);
		serviceBitacora.saveBitacoraParam(param);
	}

	/**
	 * @return the serviceBitacora
	 */
	public ServiceBitacora getServiceBitacora() {
		return serviceBitacora;
	}

	/**
	 * @param serviceBitacora
	 *            the serviceBitacora to set
	 */
	public void setServiceBitacora(ServiceBitacora serviceBitacora) {
		this.serviceBitacora = serviceBitacora;
	}

	/**
	 * @return the bitacoraMetodos
	 */
	public synchronized Set<BitacoraMetodo> getBitacoraMetodos() {
		try {
			if (bitacoraMetodos == null) {
				List<BitacoraTipo> tipos = serviceBitacora.getTipoActivo();
				if (tipos != null) {
					bitacoraMetodos = new HashSet<BitacoraMetodo>();
					for (BitacoraTipo tipo : tipos) {
						bitacoraMetodos.addAll(tipo.getBitacoraMetodos());
					}
				}
			}
		} catch (GeneralException e) {
			LOG.error(e);
		}
		return bitacoraMetodos;
	}

	/**
	 * @param bitacoraMetodos
	 *            the bitacoraMetodos to set
	 */
	public static synchronized void reset() {
		RegistroBitacora.bitacoraMetodos = null;
	}
}