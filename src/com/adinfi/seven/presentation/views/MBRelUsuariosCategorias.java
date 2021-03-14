package com.adinfi.seven.presentation.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceRelUsuariosCategorias;
import com.adinfi.seven.persistence.dto.RelUsuariosCategoriasDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;

public class MBRelUsuariosCategorias {

	private Logger LOG = Logger.getLogger(MBRelUsuariosCategorias.class);
	private String catalogNametitle = "CATEGORIAS POR USUARIO";
	private ServiceRelUsuariosCategorias serviceRelUsuariosCategorias;
	private List<RelUsuariosCategorias> listRelUsuariosCategorias;
	private RelUsuariosCategorias relUsuariosCategorias = null;
	private HashMap<Integer, List<Integer>> usuariosCategorias = new HashMap<Integer, List<Integer>>();
	private HashMap<Integer, String> usuariosDTO = new HashMap<Integer, String>();
	private HashMap<Integer, String> usuariosLoginDTO = new HashMap<Integer, String>();
	private HashMap<Integer, String> categoriasDTO = new HashMap<Integer, String>();
	private String errorMsg = "";
	private RelUsuariosCategoriasDTO relUsuariosCategoriasDTO = new RelUsuariosCategoriasDTO();
	private List<RelUsuariosCategoriasDTO> relUsuariosCategoriasDTOList = new ArrayList<>();
	private ServiceDynamicCatalogs serviceDynamicCatalogs = null;
	List<AttrSearch> attrSearchLst = new ArrayList<>();
	List<UsuarioDTO> usuariosLstAux = new ArrayList<>();
	private ServiceCatCategory serviceCatCategory = null;
	List<CatCategory> categorias = null;
	List<Integer> categoriasSelected = null;
	private UsuarioDTO usuarioSeleccionado = null;
	private boolean desabilitado = false;
	private String label = "Cancelar";
	

	@PostConstruct
	public void init() {
		// extrae de la base de datos las relaciones usuarios-categorias
		getAllRel();
		// Extrae los usuarios del catalogo dinamico
		getAllUsers();
		// carga el hashmap agrupando llave:usuario  valor: lista de categorias. ademas carga las categorias.
		loadData();
		// carga el DTO para mandarlo a v
		setDTO();
		
	}

	public void reset() {
		relUsuariosCategorias = new RelUsuariosCategorias();
	}

	public void setInfo(RelUsuariosCategorias relUsuariosCategorias) {
		this.relUsuariosCategorias = relUsuariosCategorias;
		LOG.info("relUsuariosCategorias");
	}

	private void getAllRel() {
		try {

			listRelUsuariosCategorias = this.serviceRelUsuariosCategorias
					.listaRelUsuariosCategorias();

		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void getAllUsers(){
		attrSearchLst = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		attrSearch.setAttrName(Constants.ATT_INACTIVE);
		attrSearch.setValue(Constants.FALSE);
		attrSearch.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
		attrSearch.setExact(true);
		attrSearchLst.add(attrSearch);
		try {
			usuariosLstAux = MBUtil.getUsuario(this.serviceDynamicCatalogs, attrSearchLst);
			if(usuariosLstAux.isEmpty() == false){
				for(UsuarioDTO usr : usuariosLstAux){
					usuariosDTO.put(usr.getUserId(), usr.getFullName());
					usuariosLoginDTO.put(usr.getUserId(), usr.getLogin());
				}
			}
		} catch (GeneralException e) {
			LOG.error(e);
		}
	}

	
	public void loadData() {
		usuariosCategorias = new HashMap<Integer, List<Integer>>();

		for (RelUsuariosCategorias rel : listRelUsuariosCategorias) {
			List<Integer> categorias = new ArrayList<>();
			if (usuariosCategorias.isEmpty()) {
				// si la lista viene vacia se carga el primer elemento
				categorias.add(rel.getCatCategory().getIdCategory());
				usuariosCategorias.put(rel.getCatUsuarios().getIdusuarios(), categorias);
			} else {
				// si la lista no esta vacia se revisa
				if (usuariosCategorias.containsKey(rel.getCatUsuarios().getIdusuarios())) {
					// si la lista si contiene el elemento se revisan sus categorias
					List<Integer> categoriasAux = usuariosCategorias.get(rel.getCatUsuarios().getIdusuarios());
					if(categoriasAux.contains(rel.getCatCategory().getIdCategory()) == false){
						// si esa categoria no esta en su lista se añade
						categoriasAux.add(rel.getCatCategory().getIdCategory());
						usuariosCategorias.put(rel.getCatUsuarios().getIdusuarios(), categoriasAux);
					}
					
				} else {
					//si la lista no contiene ese id de usuario se añade el elemento al mapa.
					categorias.add(rel.getCatCategory().getIdCategory());
					usuariosCategorias.put(rel.getCatUsuarios().getIdusuarios(), categorias);
				}
			}
		}
		
		
		// cargar categorias.
		
		try {
			categorias = this.serviceCatCategory.getCatCategoryList();
		} catch (Exception e) {
			LOG.error(e);
		}
		
		if(categorias.isEmpty() == false){
			for(CatCategory cat : categorias){
				categoriasDTO.put(cat.getIdCategory(), cat.getCode());
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void setDTO(){
		if(usuariosCategorias.isEmpty() == false){
			Iterator<Map.Entry<Integer,List<Integer>>> it = usuariosCategorias.entrySet().iterator();
			while(it.hasNext()){
				RelUsuariosCategoriasDTO ruDTO = new RelUsuariosCategoriasDTO();
				@SuppressWarnings("rawtypes")
				Map.Entry el = (Map.Entry)it.next();
				
				ruDTO.setUsuario(usuariosDTO.get(el.getKey()));
				ruDTO.setIdUsuario((Integer) el.getKey());
				//ruDTO.setIdRel(34);
				ruDTO.setUsuarioLogin(usuariosLoginDTO.get(el.getKey()));
				
				List<Integer> auxCat = (List<Integer>) el.getValue();
				List<String>  categoriasNombres = new ArrayList<>();
				for(Integer catAux : auxCat){
					categoriasNombres.add(categoriasDTO.get(catAux));
				}	
				ruDTO.setCategoriasNombres(categoriasNombres);
				ruDTO.setCategorias(auxCat);
				
				// se agrega el objeto al DTO
				relUsuariosCategoriasDTOList.add(ruDTO);
			}
		}
	}
	
	public void guardarRelacion() {

		try {

			// List<RelUsuariosCategorias> lst =
			// this.serviceRelUsuariosCategorias.listaRelUsuariosCategorias();

			if (usuarioSeleccionado != null) {
				if (categoriasSelected.isEmpty() == false) {
		
					// se cargan los objetos seleccionados
					List<RelUsuariosCategorias> rels = new ArrayList<>();
					
					
					for(int i = 0 ; i < categoriasSelected.size(); i++){
						
						String ix3 = String.valueOf(categoriasSelected.get(i));
	                    RelUsuariosCategorias rel = new RelUsuariosCategorias();
						
		/*				rel.setIdCategory(Integer.valueOf(ix3));
						rel.setIdSubcategory(1);
						rel.setIdUsuario(usuarioSeleccionado.getUserId());
						rel.setIdRel(null); */
						
						//añadir a la lista para guardar
						 rels.add(rel);
						 
					}
					
					    // se envia a guardar
					   if(this.serviceRelUsuariosCategorias.borrarRelUsuarioCategoria(usuarioSeleccionado.getUserId())){
						   this.serviceRelUsuariosCategorias.crearRelUsuarioCategoria(rels);
						   
						   relUsuariosCategoriasDTOList = new ArrayList<>();
						   usuarioSeleccionado = null;
						   categoriasSelected = new ArrayList<>();
						   //estado();
						   
						   
						    // extrae de la base de datos las relaciones usuarios-categorias
							getAllRel();
							// Extrae los usuarios del catalogo dinamico
							loadData();
							// carga el DTO para mandarlo a v
							setDTO();
						   
					   }
					   

				} else {
					// enviar mensaje selelccione categorias
				}
			} else {
				// seleccione un usuario
			}

		} catch (Exception e) {
			LOG.error(e);
		}

	}
	
	
	
	
    public void estado(){
    	
		if(this.label.equals("Nuevo") ){
          
			this.label = "Cancelar";
			this.desabilitado = false;

		}else if(this.label.equals("Cancelar") ){
			
			this.label = "Nuevo";
			this.desabilitado = true;

			
		}
		
	}
    
    
    

	public Logger getLOG() {
		return LOG;
	}

	public void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public ServiceRelUsuariosCategorias getServiceRelUsuariosCategorias() {
		return serviceRelUsuariosCategorias;
	}

	public void setServiceRelUsuariosCategorias(
			ServiceRelUsuariosCategorias serviceRelUsuariosCategorias) {
		this.serviceRelUsuariosCategorias = serviceRelUsuariosCategorias;
	}

	public List<RelUsuariosCategorias> getListRelUsuariosCategorias() {
		return listRelUsuariosCategorias;
	}

	public void setListRelUsuariosCategorias(
			List<RelUsuariosCategorias> listRelUsuariosCategorias) {
		this.listRelUsuariosCategorias = listRelUsuariosCategorias;
	}

	public RelUsuariosCategorias getRelUsuariosCategorias() {
		return relUsuariosCategorias;
	}

	public void setRelUsuariosCategorias(
			RelUsuariosCategorias relUsuariosCategorias) {
		this.relUsuariosCategorias = relUsuariosCategorias;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public HashMap<Integer, List<Integer>> getUsuariosCategorias() {
		return usuariosCategorias;
	}

	public void setUsuariosCategorias(
			HashMap<Integer, List<Integer>> usuariosCategorias) {
		this.usuariosCategorias = usuariosCategorias;
	}

	public RelUsuariosCategoriasDTO getRelUsuariosCategoriasDTO() {
		return relUsuariosCategoriasDTO;
	}

	public void setRelUsuariosCategoriasDTO(
			RelUsuariosCategoriasDTO relUsuariosCategoriasDTO) {
		this.relUsuariosCategoriasDTO = relUsuariosCategoriasDTO;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public HashMap<Integer, String> getUsuariosDTO() {
		return usuariosDTO;
	}

	public void setUsuariosDTO(HashMap<Integer, String> usuariosDTO) {
		this.usuariosDTO = usuariosDTO;
	}

	public List<AttrSearch> getAttrSearchLst() {
		return attrSearchLst;
	}

	public void setAttrSearchLst(List<AttrSearch> attrSearchLst) {
		this.attrSearchLst = attrSearchLst;
	}

	public List<UsuarioDTO> getUsuariosLstAux() {
		return usuariosLstAux;
	}

	public void setUsuariosLstAux(List<UsuarioDTO> usuariosLstAux) {
		this.usuariosLstAux = usuariosLstAux;
	}

	public List<RelUsuariosCategoriasDTO> getRelUsuariosCategoriasDTOList() {
		return relUsuariosCategoriasDTOList;
	}

	public void setRelUsuariosCategoriasDTOList(
			List<RelUsuariosCategoriasDTO> relUsuariosCategoriasDTOList) {
		this.relUsuariosCategoriasDTOList = relUsuariosCategoriasDTOList;
	}

	public ServiceCatCategory getServiceCatCategory() {
		return serviceCatCategory;
	}

	public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
		this.serviceCatCategory = serviceCatCategory;
	}

	public List<CatCategory> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CatCategory> categorias) {
		this.categorias = categorias;
	}

	public HashMap<Integer, String> getCategoriasDTO() {
		return categoriasDTO;
	}

	public void setCategoriasDTO(HashMap<Integer, String> categoriasDTO) {
		this.categoriasDTO = categoriasDTO;
	}

	public UsuarioDTO getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(UsuarioDTO usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Integer> getCategoriasSelected() {
		return categoriasSelected;
	}

	public void setCategoriasSelected(List<Integer> categoriasSelected) {
		this.categoriasSelected = categoriasSelected;
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public HashMap<Integer, String> getUsuariosLoginDTO() {
		return usuariosLoginDTO;
	}

	public void setUsuariosLoginDTO(HashMap<Integer, String> usuariosLoginDTO) {
		this.usuariosLoginDTO = usuariosLoginDTO;
	}
	
	

}