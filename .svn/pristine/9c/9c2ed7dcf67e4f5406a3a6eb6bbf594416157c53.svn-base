package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;

public class DAOCampanaProgramasCategoriasImpl extends AbstractDaoImpl<TblCampanaProgramasCategorias> implements DAOCampanaProgramasCategorias{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<TblCampanaProgramasCategorias> getCategoriasByCampanaIdAndPrograma(long idCampana, int idPrograma) {
		@SuppressWarnings("unchecked")
		Iterator<TblCampanaProgramasCategorias> tblCamCatPlazaIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaProgramasCategorias TCPC  where TCPC.tblCampanaProgramas.id.idCampana = ? and TCPC.tblCampanaProgramas.id.idPrograma = ?",
						new Object[] { idCampana, idPrograma });
		return toInitializedList(tblCamCatPlazaIterator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCategoriasIdsByCampanaIdAndPrograma(long idCampana, int idPrograma) {
		return getHibernateTemplate().find("select idCategoria from TblCampanaProgramasCategorias "
				+ "TCPC  where TCPC.tblCampanaProgramas.id.idCampana = ? and TCPC.tblCampanaProgramas.id.idPrograma = ?",
						new Object[] { idCampana, idPrograma });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaProgramasCategorias> getProgramasListByUser(UsuarioDTO usuario){
		StringBuilder categorias= new StringBuilder();
		
		if(usuario.getCategorias()!=null){
			for(CategoriaDTO categ:usuario.getCategorias()){
				if(categ.getId()!=null){
					categorias.append(categ.getId());
					categorias.append(",");
				}
			}
		}
		
		if(categorias.length()>0){
			categorias.deleteCharAt(categorias.length()-1);
		}else{
			return null;
		}
		Iterator<TblCampanaProgramasCategorias> programaLst = getHibernateTemplate()
				.iterate(
						"from TblCampanaProgramasCategorias pc " +
						"where pc.idCategoria in ( "+categorias.toString()+" )");
		return toInitializedList(programaLst);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaProgramasCategorias> getProgramasListByUser(CatUsuarios usuario, List<RelUsuariosCategorias> usuariosCategorias) {
		
		
		StringBuilder categorias= new StringBuilder();
		
		if(usuariosCategorias.isEmpty() == false){
			for(RelUsuariosCategorias categ : usuariosCategorias){
				if(categ.getCatCategory() != null){
					categorias.append(categ.getCatCategory().getIdCategory());
					categorias.append(",");
				}
			}
		}
		
		if(categorias.length()>0){
			categorias.deleteCharAt(categorias.length()-1);
		}else{
			return null;
		}
		
		Iterator<TblCampanaProgramasCategorias> programaLst = getHibernateTemplate()
				.iterate(
						"from TblCampanaProgramasCategorias pc " +
						"where pc.idCategoria in ( "+categorias.toString()+" )");
		
		return toInitializedList(programaLst);
		
		
	}
	
	@Override
	public void saveCampanaProgramasCategorias(List<TblCampanaProgramasCategorias> list){
		System.out.println("saveCampanaProgramasCategorias...");
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		String query = "";
		if(!list.isEmpty()){
			for(TblCampanaProgramasCategorias o: list){
				if(!query.isEmpty()){
					query+=" UNION ";
				}
				query+="SELECT "+o.getTblCampanaProgramas().getTblCampana().getIdCampana()+","+o.getTblCampanaProgramas().getPrograma().getIdPrograma()+","+o.getIdCategoria()+" FROM  SYSIBM.SYSDUMMY1";
			}
			query = "INSERT INTO "+schema+".TBL_CAMPANA_PROG_CATEG(ID_CAMPANA, ID_PROGRAMA, ID_CATEGORIA) "+query;
		}
		System.out.println("Saving TblCampanaProgramasCategorias: "+list.size());
		int r = this.getSession().createSQLQuery(query).executeUpdate();
		System.out.println("Inserted TblCampanaProgramasCategorias: "+r);
	}
	
}