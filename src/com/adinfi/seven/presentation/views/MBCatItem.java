package com.adinfi.seven.presentation.views;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatLista;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatDepto;
import com.adinfi.seven.business.services.ServiceCatItem;
import com.adinfi.seven.business.services.ServiceCatLista;
import com.adinfi.seven.business.services.ServiceCatProveedor;
import com.adinfi.seven.business.services.ServiceCatSubCategory;
import java.io.Serializable;

public class MBCatItem implements Serializable{

	private Logger LOG = Logger.getLogger(MBCatItem.class);
	private String catalogNametitle = "ARTÍCULOS";

	private String idItem;
	private Integer idItemParent;
	private String code;
	private String segmento;
	private String fabricante;
	private String marca;
	private Date fechaCreacion;
	private Double precioActual;
	private String margen;
	private String existencia;
	private Double porcMargen;
	private Boolean esMercancia = false;

	private CatItem catItem;
	private CatDepto catDepto;
	private CatProveedor catProveedor;
	private CatCategory catCategory;
	private CatSubCategory catSubCategory;
	private CatLista catLista;

	private List<CatItem> catItemList;
	private List<CatProveedor> catProveedorList;
	private List<CatCategory> catCategoryList;
	private List<CatSubCategory> catSubCategoryList;
	private List<CatLista> catListas;
	private List<CatDepto> catDeptoList;

	private ServiceCatItem serviceCatItem;
	private ServiceCatProveedor serviceCatProveedor;
	private ServiceCatCategory serviceCatCategory;
	private ServiceCatSubCategory serviceCatSubCategory;
	private ServiceCatDepto serviceCatDepto;
	private ServiceCatLista serviceCatLista;

	private String errorMsg = "";

	@PostConstruct
	public void init() {
		getAllCatDepto();
		getAllCatCategory();
		getAllCatItem();
		getAllCatProveedor();
		getAllCatSubCategory();
		getAllCatLista();
		reset();
	}

	public void reset() {
		catItem = new CatItem();
		catProveedor = new CatProveedor();
		catCategory = new CatCategory();
		catSubCategory = new CatSubCategory();
		catDepto = new CatDepto();
		catLista = new CatLista();
	}

	public void setCatItemInfo(CatItem catItem) {
		
		reset();

		//TODO ADINFI
		/*
		if (catItem.getIdItem() != null) {
			this.catItem.setIdItem(catItem.getIdItem());
		}

		if (catItem.getIdItemParent() != null) {
			this.catItem.setIdItemParent(catItem.getIdItemParent());
		}

		if (catItem.getCode() != null) {
			this.catItem.setCode(catItem.getCode());
		}

		if (catItem.getCatDepto() != null && catItem.getCatDepto().getDeptoId() != null) {
			this.catDepto.setDeptoId(catItem.getCatDepto().getDeptoId());
		}

		if (catItem.getCatCategory() != null && catItem.getCatCategory().getCategoryId() != null) {
			this.catCategory.setCategoryId(catItem.getCatCategory()
					.getCategoryId());
		}

		if (catItem.getEsMercancia() == 1) {
			this.esMercancia = true;
		} else {
			this.esMercancia = false;
		}

		if (catItem.getSegmento() != null) {
			this.catItem.setSegmento(catItem.getSegmento());
		}

		if (catItem.getCatSubcategory() != null && catItem.getCatSubcategory().getSubCategoryId() != null) {
			this.catSubCategory.setSubCategoryId(catItem.getCatSubcategory()
					.getSubCategoryId());
		}

		if (catItem.getFabricante() != null) {
			this.catItem.setFabricante(catItem.getFabricante());
		}
		
		if(catItem.getCatProveedor() != null && catItem.getCatProveedor().getIdProveedor() != 0){
		this.catProveedor.setIdProveedor(catItem.getCatProveedor().getIdProveedor());
		}
		
		
		if (catItem.getMarca() != null) {
			this.catItem.setMarca(catItem.getMarca());
		}
		
		if(catItem.getFechaCreacion() != null){
		this.catItem.setFechaCreacion(catItem.getFechaCreacion());
		}
		
		if (catItem.getPrecioActual() != null) {
			this.catItem.setPrecioActual(precioActual);;
		}
		
		if(catItem.getMargen() != null){
			this.catItem.setMargen(catItem.getMargen());
		}
		
		if(catItem.getExistencia() != null){
			this.catItem.setExistencia(catItem.getExistencia());
		}
		
		if(catItem.getCatLista() != null && catItem.getCatLista().getIdLista() != null){
			this.catLista.setIdLista(catItem.getCatLista().getIdLista());
		}
		
		if(catItem.getPorcMargen() != null){
			this.catItem.setPorcMargen(catItem.getPorcMargen());
		}
		*/
	     
	}

	private void getAllCatItem() {
		catItemList = null;
		try {
			catItemList = serviceCatItem.getCatItemList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void getAllCatCategory() {
		try {
			catCategoryList = this.serviceCatCategory.getCatCategoryList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void getAllCatSubCategory() {
		try {
			catSubCategoryList = this.serviceCatSubCategory
					.getCatSubCategoryList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void getAllCatProveedor() {
		try {
			catProveedorList = this.serviceCatProveedor.getCatProveedorList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void getAllCatLista() {

		try {
			catListas = serviceCatLista.getCatListaList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void getAllCatDepto() {

		try {
			catDeptoList = serviceCatDepto.getCatDeptoList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void addCatItem() {
		
		//TODO ADINFI
		/*
		if (this.catDepto.getIdDepto() > 0) {
			this.catItem.setCatDepto(this.catDepto);
		}

		if (this.catCategory.getCategoryId() > 0) {
			this.catItem.setCatCategory(this.catCategory);
		}

		if (this.catSubCategory.getSubCategoryId() > 0) {
			this.catItem.setCatSubcategory(this.catSubCategory);
		}

		if (this.catProveedor != null && this.catProveedor.getIdProveedor() > 0) {
			this.catItem.setCatProveedor(this.catProveedor);
		}

		if (this.catLista.getIdLista() > 0) {
			this.catItem.setCatLista(this.catLista);
		}

		if (this.esMercancia) {
			this.catItem.setEsMercancia((short) 1);
		} else {
			this.catItem.setEsMercancia((short) 0);
		}

		if (this.catItem.getCode().isEmpty()) {
			Messages.mensajeError("El campo CODIGO es requerido");
		} else {
			this.serviceCatItem.crearCatItem(this.catItem);
		}
		*/
		reset();
		getAllCatItem();
	}

	public void removeCatItem() {

		boolean deleted = this.serviceCatItem.eliminarCatItem(this.catItem);

		if (deleted) {
			errorMsg = "Registro eliminado con exito";
		} else {
			errorMsg = "El registro no pudo ser eliminado";
		}
		reset();
		getAllCatItem();
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public CatItem getCatItem() {
		return catItem;
	}

	public void setCatItem(CatItem catItem) {
		this.catItem = catItem;
	}

	public CatDepto getCatDepto() {
		return catDepto;
	}

	public void setCatDepto(CatDepto catDepto) {
		this.catDepto = catDepto;
	}

	public CatProveedor getCatProveedor() {
		return catProveedor;
	}

	public void setCatProveedor(CatProveedor catProveedor) {
		this.catProveedor = catProveedor;
	}

	public CatCategory getCatCategory() {
		return catCategory;
	}

	public void setCatCategory(CatCategory catCategory) {
		this.catCategory = catCategory;
	}

	public CatSubCategory getCatSubCategory() {
		return catSubCategory;
	}

	public void setCatSubCategory(CatSubCategory catSubCategory) {
		this.catSubCategory = catSubCategory;
	}

	public CatLista getCatLista() {
		return catLista;
	}

	public void setCatLista(CatLista catLista) {
		this.catLista = catLista;
	}

	public List<CatItem> getCatItemList() {
		return catItemList;
	}

	public void setCatItemList(List<CatItem> catItemList) {
		this.catItemList = catItemList;
	}

	public List<CatProveedor> getCatProveedorList() {
		return catProveedorList;
	}

	public void setCatProveedorList(List<CatProveedor> catProveedorList) {
		this.catProveedorList = catProveedorList;
	}

	public List<CatCategory> getCatCategoryList() {
		return catCategoryList;
	}

	public void setCatCategoryList(List<CatCategory> catCategoryList) {
		this.catCategoryList = catCategoryList;
	}

	public List<CatSubCategory> getCatSubCategoryList() {
		return catSubCategoryList;
	}

	public void setCatSubCategoryList(List<CatSubCategory> catSubCategoryList) {
		this.catSubCategoryList = catSubCategoryList;
	}

	public List<CatLista> getCatListas() {
		return catListas;
	}

	public void setCatListas(List<CatLista> catListas) {
		this.catListas = catListas;
	}

	public ServiceCatItem getServiceCatItem() {
		return serviceCatItem;
	}

	public void setServiceCatItem(ServiceCatItem serviceCatItem) {
		this.serviceCatItem = serviceCatItem;
	}

	public ServiceCatProveedor getServiceCatProveedor() {
		return serviceCatProveedor;
	}

	public void setServiceCatProveedor(ServiceCatProveedor serviceCatProveedor) {
		this.serviceCatProveedor = serviceCatProveedor;
	}

	public ServiceCatCategory getServiceCatCategory() {
		return serviceCatCategory;
	}

	public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
		this.serviceCatCategory = serviceCatCategory;
	}

	public ServiceCatSubCategory getServiceCatSubCategory() {
		return serviceCatSubCategory;
	}

	public void setServiceCatSubCategory(
			ServiceCatSubCategory serviceCatSubCategory) {
		this.serviceCatSubCategory = serviceCatSubCategory;
	}

	public ServiceCatDepto getServiceCatDepto() {
		return serviceCatDepto;
	}

	public void setServiceCatDepto(ServiceCatDepto serviceCatDepto) {
		this.serviceCatDepto = serviceCatDepto;
	}

	public ServiceCatLista getServiceCatLista() {
		return serviceCatLista;
	}

	public void setServiceCatLista(ServiceCatLista serviceCatLista) {
		this.serviceCatLista = serviceCatLista;
	}

	public List<CatDepto> getCatDeptoList() {
		return catDeptoList;
	}

	public void setCatDeptoList(List<CatDepto> catDeptoList) {
		this.catDeptoList = catDeptoList;
	}

	public Logger getLOG() {
		return LOG;
	}

	public void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public Integer getIdItemParent() {
		return idItemParent;
	}

	public void setIdItemParent(Integer idItemParent) {
		this.idItemParent = idItemParent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Double getPrecioActual() {
		return precioActual;
	}

	public void setPrecioActual(Double precioActual) {
		this.precioActual = precioActual;
	}

	public String getMargen() {
		return margen;
	}

	public void setMargen(String margen) {
		this.margen = margen;
	}

	public String getExistencia() {
		return existencia;
	}

	public void setExistencia(String existencia) {
		this.existencia = existencia;
	}

	public Double getPorcMargen() {
		return porcMargen;
	}

	public void setPorcMargen(Double porcMargen) {
		this.porcMargen = porcMargen;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Boolean getEsMercancia() {
		return esMercancia;
	}

	public void setEsMercancia(Boolean esMercancia) {
		this.esMercancia = esMercancia;
	}

}