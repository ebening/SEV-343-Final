package com.adinfi.seven.business.services;

import java.util.List;
import java.util.Map;

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

public interface ServiceArquitectura {

	Map<String, String> getMarcas() throws GeneralException;

	TblMarcaLogo saveMarcas(TblMarcaLogo marca) throws GeneralException;

	TblMarcaLogo getMarcaLogo(int IdMarcaLogo) throws Exception;

	TblTemplate saveTemplate(TblTemplate template);

	List<TblTemplate> getAllTemplate() throws Exception;

	TblTemplate getTemplate(int templateId) throws Exception;

	void deleteTemplate(TblTemplate[] templates) throws Exception;

	TblTemplateUser saveTemplateUser(TblTemplateUser templateUser);

	List<ViewTemplateUser> getPersonaTemplate() throws Exception;

	Map<String, String> getTemplatesUser() throws GeneralException;

	Map<String, String> getCategoriasByUsuario(
			ServiceDynamicCatalogs serviceDynamicCatalogs, int usuarioId);

	Map<String, String> getUsuarios(
			ServiceDynamicCatalogs serviceDynamicCatalogs);

	boolean usedTemplateByUser(TblTemplateUser tblTemplateUser);

	TblTemplateUser getTemplateUser(int templateUserId);

	void deleteTemplateUser(TblTemplateUser templateUsers);

	List<TblImageArticulo> buscaSKUById(String idArticulo);

	boolean saveImagenArt(TblImageArticulo imgArt);

	boolean saveImagenes(TblImagenes img);

	boolean deleteImagenArt(TblImageArticulo imgArt);

	TblTemplateSegments saveTemplateSegment(TblTemplateSegments segment);

	boolean deleteTemplateSegment(TblTemplateSegments segment);

	TblTemplateSegments getTemplateSegment(int segmentId);

	List<TblComentarioArticulo> getComentarioArticulo(int segmentId, int hojaId);

	TblComentarioArticulo saveComentarioArticulo(TblComentarioArticulo coment);

	List<TblArchivoArticulo> getArchivoArticulo(int segmentId, int hojaId);

	TblArchivoArticulo saveArchivoArticulo(TblArchivoArticulo archivo);
	
	TblImageArticulo findImageArticuloById(int id);

}
