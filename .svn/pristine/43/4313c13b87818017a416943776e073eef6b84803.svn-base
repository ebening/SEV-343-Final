package com.adinfi.seven.presentation.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.bitacora.RegistroBitacora;
import com.adinfi.seven.business.domain.Bitacora;
import com.adinfi.seven.business.domain.BitacoraParam;
import com.adinfi.seven.business.domain.BitacoraTipo;
import com.adinfi.seven.business.services.ServiceBitacora;
import com.adinfi.seven.persistence.dto.BitacoraDTO;
import com.adinfi.seven.persistence.dto.BitacoraModel;
import com.adinfi.seven.persistence.dto.BitacoraParamDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.presentation.views.util.Constants;

public class MBBitacora {
	private Logger LOG = Logger.getLogger(MBBitacora.class);
	public ServiceBitacora serviceBitacora;
	private BitacoraModel bitacoraLst;
	private BitacoraDTO[] bitacoraFilterLst;
	private List<GenericItem> listLevels;
	private List<String> listTipos = new ArrayList<String>();
	private RegistroBitacora registroBitacora;
	private List<BitacoraParamDTO> paramList;

	public void saveTipos() {
		FacesMessage fm = null;
		try {
			for (GenericItem item : listLevels) {
				if (listTipos.contains(item.getId().toString())) {
					serviceBitacora.saveNiveles(item.getId(), Constants.ACTIVO);
				} else {
					serviceBitacora.saveNiveles(item.getId(),
							Constants.INACTIVO);
				}
			}
			setListLevels(populateLstLevels());
			RegistroBitacora.reset();
			fm = new FacesMessage("Niveles actualizados con exito");
		} catch (GeneralException e) {
			LOG.error(e);
			fm = new FacesMessage("Ocurrio un error al actualizar los niveles");
		}
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	@PostConstruct
	private void init() {
		try {
			makeBitacoraDTOLst(serviceBitacora.getBitacora());
			setListLevels(populateLstLevels());
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private List<GenericItem> populateLstLevels() {
		listTipos = new ArrayList<String>();
		List<GenericItem> listLevels = new ArrayList<GenericItem>();
		try {
			List<BitacoraTipo> lstTipoInterno = serviceBitacora.getNiveles();
			if (lstTipoInterno != null) {
				for (BitacoraTipo tipo : lstTipoInterno) {
					GenericItem item = new GenericItem();
					item.setId(tipo.getTipoId());
					item.setValue(tipo.getDescripcion());
					item.setObj(tipo);
					listLevels.add(item);
					if (tipo.getActive() == Constants.ACTIVO) {
						listTipos.add(String.valueOf(tipo.getTipoId()));
					}
				}
			}
		} catch (GeneralException e) {
			LOG.error(e);
		}
		return listLevels;
	}

	private void makeBitacoraDTOLst(List<Bitacora> bitacoraEntityLst) {
		List<BitacoraDTO> bitacoraLst = null;
		if (bitacoraEntityLst != null) {
			bitacoraLst = new ArrayList<BitacoraDTO>();
			for (Bitacora bitacora : bitacoraEntityLst) {
				bitacoraLst.add(makeBitacoraDTO(bitacora));
			}
		}
		setBitacoraLst(new BitacoraModel(bitacoraLst));
	}

	private BitacoraDTO makeBitacoraDTO(Bitacora bitacora) {
		BitacoraDTO dto = new BitacoraDTO();
		dto.setBitacoraId(bitacora.getBitacoraId());
		dto.setDao(bitacora.getDao());
		dto.setFechaMov(bitacora.getFechaMov());
		dto.setIp(bitacora.getIp());
		dto.setMetodo(bitacora.getMetodo());
		dto.setUsuarioId(bitacora.getUsuarioId());
		return dto;
	}

	public void showParam() {
		Boolean bShow = Boolean.FALSE;
		if (bitacoraFilterLst != null) {
			if (bitacoraFilterLst.length == 0) {
				FacesMessage fm = new FacesMessage(
						"Favor de seleccionar un registro");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			} else if (bitacoraFilterLst.length == 1) {
				try {
					paramList = getParams(bitacoraFilterLst[0]);
					bShow = Boolean.TRUE;
				} catch (GeneralException e) {
					LOG.error(e);
					FacesMessage fm = new FacesMessage(
							"Ocurrio un error al obtener los datos de los parametros");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			} else {
				FacesMessage fm = new FacesMessage(
						"Solo se puede mostrar las parametros de un registro a la vez");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		}
		RequestContext.getCurrentInstance().addCallbackParam("show", bShow);
	}

	private List<BitacoraParamDTO> getParams(BitacoraDTO bitacora)
			throws GeneralException {
		List<BitacoraParamDTO> paramList = new ArrayList<BitacoraParamDTO>();
		List<BitacoraParam> paramListTmp = serviceBitacora
				.getParamsListByBitacoraId(bitacora.getBitacoraId());
		for (BitacoraParam param : paramListTmp) {
			paramList.add(makeParamDTO(param));
		}
		return paramList;
	}

	private BitacoraParamDTO makeParamDTO(BitacoraParam param) {
		BitacoraParamDTO paramDTO = new BitacoraParamDTO();
		paramDTO.setBitacoraParamId(param.getBitacoraParamId());
		paramDTO.setParamNombre(param.getParamNombre());
		paramDTO.setParamValue(param.getParamValue());
		return paramDTO;
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
	 * @return the bitacoraLst
	 */
	public BitacoraModel getBitacoraLst() {
		return bitacoraLst;
	}

	/**
	 * @param bitacoraLst
	 *            the bitacoraLst to set
	 */
	public void setBitacoraLst(BitacoraModel bitacoraLst) {
		this.bitacoraLst = bitacoraLst;
	}

	/**
	 * @return the bitacoraFilterLst
	 */
	public BitacoraDTO[] getBitacoraFilterLst() {
		return bitacoraFilterLst;
	}

	/**
	 * @param bitacoraFilterLst
	 *            the bitacoraFilterLst to set
	 */
	public void setBitacoraFilterLst(BitacoraDTO[] bitacoraFilterLst) {
		if (bitacoraFilterLst != null)
			this.bitacoraFilterLst = bitacoraFilterLst.clone();
	}

	/**
	 * @return the listLevels
	 */
	public List<GenericItem> getListLevels() {
		return listLevels;
	}

	/**
	 * @param listLevels
	 *            the listLevels to set
	 */
	public void setListLevels(List<GenericItem> listLevels) {
		this.listLevels = listLevels;
	}

	/**
	 * @return the listTipos
	 */
	public List<String> getListTipos() {
		return listTipos;
	}

	/**
	 * @param listTipos
	 *            the listTipos to set
	 */
	public void setListTipos(List<String> listTipos) {
		this.listTipos = listTipos;
	}

	/**
	 * @return the registroBitacora
	 */
	public RegistroBitacora getRegistroBitacora() {
		return registroBitacora;
	}

	/**
	 * @param registroBitacora
	 *            the registroBitacora to set
	 */
	public void setRegistroBitacora(RegistroBitacora registroBitacora) {
		this.registroBitacora = registroBitacora;
	}

	/**
	 * @return the paramList
	 */
	public List<BitacoraParamDTO> getParamList() {
		if (paramList == null && bitacoraFilterLst != null
				&& bitacoraFilterLst.length > 0) {
			try {
				paramList = getParams(bitacoraFilterLst[0]);
			} catch (GeneralException e) {
				LOG.error(e);
			}
		}
		return paramList;
	}

	/**
	 * @param paramList
	 *            the paramList to set
	 */
	public void setParamList(List<BitacoraParamDTO> paramList) {
		this.paramList = paramList;
	}
}
