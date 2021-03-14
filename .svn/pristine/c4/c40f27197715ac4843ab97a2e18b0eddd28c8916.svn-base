package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblImageArticulo;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceFolleto;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.Util;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class MBAgregarArticulosPopUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6723161205855152877L;
	private Logger LOG = Logger.getLogger(MBAgregarArticulosPopUp.class);
	private String sku;
	private List<TblImageArticulo> listImagenes;
	private List<ArticuloDTO> listaArticulos;
	private List<ArticuloDTO> listaArticulos2;
	private String categoria;
	private String hoja_id;
	private String idSegmento;
	private String segName;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceArquitectura serviceArquitectura;
	private ServiceFolleto serviceFolleto;

	@PostConstruct
	public void init() {
		listaArticulos = new ArrayList<ArticuloDTO>();
		listaArticulos2 = new ArrayList<ArticuloDTO>();
		setListImagenes(new ArrayList<TblImageArticulo>());
		//categoria = getCategoria();
	}
	
	public void busquedaArticulos() {
		if (sku != null && !sku.equals("")) {
			LOG.info("Busqueda");
			
			try {
				TblFolletoHojas hoja = serviceFolleto.getHoja(Integer.parseInt(hoja_id));
				categoria=hoja.getIdCategory().toString();
			} catch (NumberFormatException e) {
				LOG.error(e);
				categoria="0";
			} catch (Exception e) {
				LOG.error(e);
				categoria="0";
			}
			
			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			AttrSearch attrSearch = new AttrSearch();
			AttrSearch attrSearch2 = new AttrSearch();
			attrSearch.setAttrName(Constants.ATT_ID_CATEGORIA);
			attrSearch.setValue(categoria);
			attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
			attrSearch2.setAttrName(Constants.ATT_ID);
			attrSearch2.setValue(sku);
			attrSearch2.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
			insertSearch.add(attrSearch);
			insertSearch.add(attrSearch2);
			try {
				listaArticulos = MBUtil.getArticuloDTOsByAttr(
						serviceDynamicCatalogs, serviceArquitectura,
						insertSearch);

				if (listaArticulos.isEmpty()) {
					setListImagenes(new ArrayList<TblImageArticulo>());
				} else {
					listImagenes = listaArticulos.get(0).getImagenesArticulos();
				}
			} catch (Exception ex) {
				LOG.error(ex.getMessage());
			}
		} else {
			return;
		}

	}

	public String getImagen(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("imagen");

	}

	public void eliminar(ActionEvent event) {
		ArticuloDTO current = (ArticuloDTO) event.getComponent()
				.getAttributes().get("articulo");
		try {
			listaArticulos2.remove(current);
		} catch (Exception ex) {
			LOG.error(ex);

		}
	}

	public void agregarArticulos() {
		FacesContext fc = FacesContext.getCurrentInstance();
		LOG.info("Agregar");
		try {
			if (listaArticulos.isEmpty()) {
				LOG.info("Esta vacio");
			} else {

				LOG.info("tiene un registro");
				ArticuloDTO articulo = listaArticulos.get(0);
				LOG.info("linea 107");
				TblImageArticulo imagen = serviceArquitectura
						.findImageArticuloById(Integer.valueOf(getImagen(fc)));
				articulo.setIdImage(String.valueOf(imagen.getTblImagenes().getIdImage()));
				articulo.setImagenPath(imagen.getTblImagenes().getPathFile());
				LOG.info("linea 109");
				listaArticulos2.add(articulo);

			}
			clear();
		} catch (Exception ex) {
			LOG.error(ex);
		}
	}

	public void enviar() {
		LOG.info("Conectado......");
		StringBuffer url = new StringBuffer();
		
		try {
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			List<ArticuloDTO> lstArtAdi = (List<ArticuloDTO>) req.getSession().getAttribute(GlobalDefines.SESS_CURR_ART_ADI );
			
			url.append("/servlets/servletFolletos?command=popUp&hoja_id=" + hoja_id + "&segment_id=" + idSegmento + "&segName=" + segName);
			
			lstArtAdi.addAll(listaArticulos2);
			
//			LOG.info(listaArticulos2.size());
//			for (ArticuloDTO element : listaArticulos2) {
//				url.append("&art_" + i + "=" + element.getSku() + "&img_" + i
//						+ "=" + element.getIdImage());
//				i++;
//			}
//			url.append("&num_art=" + listaArticulos2.size());
//			LOG.info(url.toString());
//			LOG.info("IdHoja:"+hoja_id);
			
			Util.redirect(url.toString());

		} catch (Exception e) {

			LOG.error(e);
		}

	}

	private Map<String, String> parametros(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params;
	}

	public void clear() {
		listaArticulos.clear();
		listImagenes.clear();
		setSku("");
	}

	/**
	 * @return the listaArticulos
	 */
	public List<ArticuloDTO> getListaArticulos() {
		return listaArticulos;
	}

	/**
	 * @param listaArticulos
	 *            the listaArticulos to set
	 */
	public void setListaArticulos(List<ArticuloDTO> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
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
	 * @return the serviceArquitectura
	 */
	public ServiceArquitectura getServiceArquitectura() {
		return serviceArquitectura;
	}

	/**
	 * @param serviceArquitectura
	 *            the serviceArquitectura to set
	 */
	public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
		this.serviceArquitectura = serviceArquitectura;
	}

	/**
	 * @return the listImagenes
	 */
	public List<TblImageArticulo> getListImagenes() {
		return listImagenes;
	}

	/**
	 * @param listImagenes
	 *            the listImagenes to set
	 */
	public void setListImagenes(List<TblImageArticulo> listImagenes) {
		this.listImagenes = listImagenes;
	}

	/**
	 * @return the listaArticulos2
	 */
	public List<ArticuloDTO> getListaArticulos2() {
		return listaArticulos2;
	}

	/**
	 * @param listaArticulos2
	 *            the listaArticulos2 to set
	 */
	public void setListaArticulos2(List<ArticuloDTO> listaArticulos2) {
		this.listaArticulos2 = listaArticulos2;
	}

	public String getHoja_id() {
		return hoja_id;
	}

	public void setHoja_id(String hoja_id) {
		this.hoja_id = hoja_id;
	}



	public ServiceFolleto getServiceFolleto() {
		return serviceFolleto;
	}



	public void setServiceFolleto(ServiceFolleto serviceFolleto) {
		this.serviceFolleto = serviceFolleto;
	}

	public String getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

	public String getSegName() {
		return segName;
	}

	public void setSegName(String segName) {
		this.segName = segName;
	}
	
	
}
