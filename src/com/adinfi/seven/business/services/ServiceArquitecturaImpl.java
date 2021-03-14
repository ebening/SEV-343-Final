package com.adinfi.seven.business.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblArchivoArticulo;
import com.adinfi.seven.business.domain.TblComentarioArticulo;
import com.adinfi.seven.business.domain.TblImageArticulo;
import com.adinfi.seven.business.domain.TblImagenes;
import com.adinfi.seven.business.domain.TblMarcaLogo;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateSegments;
import com.adinfi.seven.business.domain.TblTemplateUser;
import com.adinfi.seven.business.domain.ViewTemplateUser;
import com.adinfi.seven.persistence.daos.DAOArchivoArticulo;
import com.adinfi.seven.persistence.daos.DAOArqMarcaLogo;
import com.adinfi.seven.persistence.daos.DAOArqTemplate;
import com.adinfi.seven.persistence.daos.DAOArqTemplateUser;
import com.adinfi.seven.persistence.daos.DAOComentarioArticulo;
import com.adinfi.seven.persistence.daos.DAOImageArticulo;
import com.adinfi.seven.persistence.daos.DAOImagen;
import com.adinfi.seven.persistence.daos.DAOTemplateSegment;
import com.adinfi.seven.persistence.daos.DAOViewTemplateUser;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;

public class ServiceArquitecturaImpl implements ServiceArquitectura {
	private Logger LOG = Logger.getLogger(ServiceArquitecturaImpl.class);
	private DAOArqMarcaLogo daoArqMarcaLogo;
	private DAOArqTemplate daoArqTemplate;
	private DAOArqTemplateUser daoArqTemplateUser;
	private DAOViewTemplateUser daoVTemplateUser;
	private DAOImageArticulo daoImageArticulo;
	private DAOImagen daoImagen;
	private DAOTemplateSegment daoTemplateSegment;
	private DAOComentarioArticulo daoComentarioArticulo;
	private DAOArchivoArticulo daoArchivoArticulo;

	@Override
	public Map<String, String> getMarcas() throws GeneralException {
		return daoArqMarcaLogo.getMarcas();
	}

	@Override
	public TblMarcaLogo saveMarcas(TblMarcaLogo marca) throws GeneralException {
		return daoArqMarcaLogo.saveMarcas(marca);
	}

	@Override
	public TblMarcaLogo getMarcaLogo(int IdMarcaLogo) throws Exception {
		return daoArqMarcaLogo.getMarcaLogo(IdMarcaLogo);
	}

	@Override
	public TblTemplate saveTemplate(TblTemplate template) {
		TblTemplate tbl=null;
		 try{
		   tbl=daoArqTemplate.saveTemplate(template);
		   daoArqTemplate.flush();
		    
		  // daoArqTemplate.refresh(template);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return tbl;
		
	}

	@Override
	public List<TblTemplate> getAllTemplate() throws Exception {
		return daoArqTemplate.getAll();
	}

	@Override
	public TblTemplate getTemplate(int templateId) throws Exception {
		 
		return daoArqTemplate.getById(templateId);
	}

	@Override
	public void deleteTemplate(TblTemplate[] templates) throws Exception {
		for (TblTemplate temp : templates.clone()) {
			daoArqTemplate.delete(temp);
		}
	}

	@Override
	public TblTemplateUser saveTemplateUser(TblTemplateUser templateUser) {
		return daoArqTemplateUser.saveTemplateUser(templateUser);
	}

	@Override
	public List<ViewTemplateUser> getPersonaTemplate() throws Exception {
		return daoVTemplateUser.getAll();
	}

	@Override
	public Map<String, String> getTemplatesUser() throws GeneralException {
		return daoArqTemplate.getTemplates();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getCategoriasByUsuario(
			ServiceDynamicCatalogs serviceDynamicCatalogs, int usuarioId) {
		Map<String, String> resVal = null;
		try {
			List<String> usCat = MBUtil.getUserCategories(
					serviceDynamicCatalogs, usuarioId);

			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			List<CategoriaDTO> allCategorias = new ArrayList<>();
			for (String idcat : usCat) {
				AttrSearch attr = new AttrSearch();
				attr.setSearchType(AttrSearch.SEARCH_TYPE_EQUAL);
				attr.setAttrName("ID");
				attr.setValue(idcat);
				insertSearch.clear();
				insertSearch.add(attr);
			
				List<CategoriaDTO> categoria = MBUtil.getCategoriasByAttr(
						serviceDynamicCatalogs, insertSearch);
				
				if( categoria!=null ){
					allCategorias.addAll(categoria);
				}
				
				
			}
			Map<String, String> map = new HashMap<String, String>();
			
			
			
			for (CategoriaDTO catDTO : allCategorias) {
				map.put(catDTO.getCodigo(), String.valueOf(catDTO.getId()));
			}

			resVal = sortByComparator(map);

		} catch (Exception e) {
			LOG.error(e);
		}
		return resVal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getUsuarios(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		Map<String, String> resVal = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<UsuarioDTO> usuario = MBUtil.getUSers(serviceDynamicCatalogs);
			for (UsuarioDTO userDTO : usuario) {
				String nombre = userDTO.getFullName();
				map.put(nombre, String.valueOf(userDTO.getUserId()));
			}

			resVal = sortByComparator(map);
		} catch (Exception e) {
			LOG.error(e);
		}
		return resVal;
	}

	@Override
	public boolean usedTemplateByUser(TblTemplateUser tblTemplateUser) {
		boolean resVal = false;
		try {
			Object[] objs = { tblTemplateUser.getIdUser(),
					tblTemplateUser.getTblTemplate().getTemplateId(),
					tblTemplateUser.getIdCategory(),
					tblTemplateUser.getIdTemplateUser() };
			List<TblTemplateUser> tempUser = daoArqTemplate
					.getTemplatesByUser(objs);

			if (tempUser != null && tempUser.size() > 0) {
				resVal = true;
			}
		} catch (Exception e) {
			LOG.error(e);
		}

		return resVal;
	}

	@Override
	public TblTemplateUser getTemplateUser(int templateUserId) {
		TblTemplateUser retVal = new TblTemplateUser();
		try {
			retVal = daoArqTemplateUser.getById(templateUserId);
		} catch (Exception e) {
			LOG.error(e);
		}
		return retVal;
	}

	@Override
	public void deleteTemplateUser(TblTemplateUser templateUser) {
		try {
			daoArqTemplateUser.delete(templateUser);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@Override
	public List<TblImageArticulo> buscaSKUById(String idArticulo) {
		List<TblImageArticulo> imgArticulos = null;

		try {
			imgArticulos = daoImageArticulo.buscaSKUById(idArticulo);

		} catch (Exception e) {
			LOG.error(e);
		}

		return imgArticulos;
	}

	@Override
	public boolean saveImagenArt(TblImageArticulo imgArt) {
		boolean retVal = true;
		try {
			daoImageArticulo.saveOrUpdate(imgArt);
		} catch (Exception e) {
			LOG.error(e);
			retVal = false;
		}

		return retVal;
	}

	@Override
	public boolean saveImagenes(TblImagenes img) {
		boolean retVal = true;
		try {
			daoImagen.saveOrUpdate(img);
		} catch (Exception e) {
			LOG.error(e);
			retVal = false;
		}

		return retVal;
	}

	@Override
	public boolean deleteImagenArt(TblImageArticulo imgArt) {
		boolean retVal = true;
		try {
			daoImageArticulo.delete(imgArt);
		} catch (Exception e) {
			LOG.error(e);
			retVal = false;
		}
		return retVal;
	}

	@Override
	public TblTemplateSegments saveTemplateSegment(TblTemplateSegments segment) {
		return daoTemplateSegment.saveTemplateSegment(segment);
	}

	@Override
	public boolean deleteTemplateSegment(TblTemplateSegments segment) {
		return daoTemplateSegment.deleteTemplateSegment(segment);
	}

	@Override
	public TblTemplateSegments getTemplateSegment(int segmentId) {
		TblTemplateSegments retVal = null;
		try {
			retVal = daoTemplateSegment.getById(segmentId);
		} catch (Exception e) {
			LOG.error(e);
		}

		return retVal;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map sortByComparator(Map unsortMap) {

		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator() {
                        @Override
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue().toString()
						.toUpperCase()).compareTo(((Map.Entry) (o2)).getValue()
						.toString().toUpperCase());
			}
		});

		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	@Override
	public List<TblComentarioArticulo> getComentarioArticulo(int segmentId,
			int hojaId) {
		List<TblComentarioArticulo> resVal = new ArrayList<>();
		try {
			Object[] objs = { segmentId, hojaId };
			resVal = daoComentarioArticulo.getComentariosHojaSegmento(objs);
		} catch (Exception e) {
			LOG.error(e);
		}

		return resVal;
	}

	@Override
	public TblComentarioArticulo saveComentarioArticulo(
			TblComentarioArticulo coment) {
		return daoComentarioArticulo.saveComentarioArticulo(coment);
	}

	@Override
	public List<TblArchivoArticulo> getArchivoArticulo(int segmentId, int hojaId) {
		List<TblArchivoArticulo> resVal = new ArrayList<>();
		try {
			Object[] objs = { segmentId, hojaId };
			resVal = daoArchivoArticulo.getArchivoArticulo(objs);
		} catch (Exception e) {
			LOG.error(e);
		}

		return resVal;
	}

	@Override
	public TblArchivoArticulo saveArchivoArticulo(TblArchivoArticulo archivo) {
		return daoArchivoArticulo.saveArchivoArticulo(archivo);
	}

	public DAOArqMarcaLogo getDaoArqMarcaLogo() {
		return daoArqMarcaLogo;
	}

	public void setDaoArqMarcaLogo(DAOArqMarcaLogo daoArqMarcaLogo) {
		this.daoArqMarcaLogo = daoArqMarcaLogo;
	}

	public DAOArqTemplate getDaoArqTemplate() {
		return daoArqTemplate;
	}

	public void setDaoArqTemplate(DAOArqTemplate daoArqTemplate) {
		this.daoArqTemplate = daoArqTemplate;
	}

	public DAOArqTemplateUser getDaoArqTemplateUser() {
		return daoArqTemplateUser;
	}

	public void setDaoArqTemplateUser(DAOArqTemplateUser daoArqTemplateUser) {
		this.daoArqTemplateUser = daoArqTemplateUser;
	}

	public DAOViewTemplateUser getDaoVTemplateUser() {
		return daoVTemplateUser;
	}

	public void setDaoVTemplateUser(DAOViewTemplateUser daoVTemplateUser) {
		this.daoVTemplateUser = daoVTemplateUser;
	}

	public DAOImageArticulo getDaoImageArticulo() {
		return daoImageArticulo;
	}

	public void setDaoImageArticulo(DAOImageArticulo daoImageArticulo) {
		this.daoImageArticulo = daoImageArticulo;
	}

	public DAOImagen getDaoImagen() {
		return daoImagen;
	}

	public void setDaoImagen(DAOImagen daoImagen) {
		this.daoImagen = daoImagen;
	}

	public DAOTemplateSegment getDaoTemplateSegment() {
		return daoTemplateSegment;
	}

	public void setDaoTemplateSegment(DAOTemplateSegment daoTemplateSegment) {
		this.daoTemplateSegment = daoTemplateSegment;
	}

	public DAOComentarioArticulo getDaoComentarioArticulo() {
		return daoComentarioArticulo;
	}

	public void setDaoComentarioArticulo(
			DAOComentarioArticulo daoComentarioArticulo) {
		this.daoComentarioArticulo = daoComentarioArticulo;
	}

	public DAOArchivoArticulo getDaoArchivoArticulo() {
		return daoArchivoArticulo;
	}

	public void setDaoArchivoArticulo(DAOArchivoArticulo daoArchivoArticulo) {
		this.daoArchivoArticulo = daoArchivoArticulo;
	}

	@Override
	public TblImageArticulo findImageArticuloById(int id) {
		return this.daoImageArticulo.getImageArticulo(id);
	}

}
