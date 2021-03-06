package com.adinfi.seven.persistence.daos.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.CatEtiquetas;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.persistence.daos.AbstractDaoImpl;

public class DAORolOpcionImpl extends AbstractDaoImpl<RolOpcion> implements
		DAORolOpcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<RolOpcion> getOpcionesPorRol(String role) throws Exception {
		Criteria criteria = this.getSession().createCriteria(RolOpcion.class,"ro");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("ro.opcion", "op", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("ro.role", role));
		criteria.add(Restrictions.eq("op.active", Boolean.TRUE));
		criteria.addOrder(Order.asc("op.opcLevel"));
		criteria.addOrder(Order.asc("op.parentId"));
		criteria.addOrder(Order.asc("op.orderSort"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMenuByUsuario(int idUsuario) {
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		return getSession().createSQLQuery("SELECT DISTINCT " +
				"{V.*}, {C.*}, {E.*}, {CP.*}, {P.*}, M.MECANICA_ID AS ID_MECANICA, M.NOMBRE_MECANICA, " +
				"CASE WHEN UCPC.ID_USUARIO IS NOT NULL THEN 'Y' ELSE 'N' END AS AUTORIZADO_PROGRAMA, " +
				"CASE WHEN UCA.ID_USUARIO IS NOT NULL THEN 'Y' ELSE 'N' END AS AUTORIZADO_MECANICA " +
			"FROM "+schema+".V_CAMPANA_TREE_REGS V " +
			"JOIN "+schema+".TBL_CAMPANA C ON C.ID_CAMPANA = V.ID_CAMPANA " +
			"JOIN "+schema+".CAT_ETIQUETAS E ON E.IDETIQUETA = C.ID_ETIQUETA " +
			"LEFT JOIN "+schema+".TBL_CAMPANA_PROGRAMAS CP ON CP.ID_CAMPANA = C.ID_CAMPANA " +
			"LEFT JOIN "+schema+".CAT_PROGRAMA P ON P.ID_PROGRAMA = CP.ID_PROGRAMA " +
			"LEFT JOIN "+schema+".TBL_CAMPANA_PROG_CATEG CPC ON CPC.ID_CAMPANA = CP.ID_CAMPANA AND CPC.ID_PROGRAMA = CP.ID_PROGRAMA " +
			"LEFT JOIN "+schema+".TBL_MECANICA M ON M.ID_CAMPANA = CP.ID_CAMPANA AND M.ID_PROGRAMA = P.ID_PROGRAMA " +
			"LEFT JOIN "+schema+".TBL_CATEGORIA CA ON CA.MECANICA_ID = M.MECANICA_ID " +
			"LEFT JOIN "+schema+".REL_USUARIOS_CATEGORIAS UCPC ON UCPC.ID_CATEGORY = CPC.ID_CATEGORIA AND UCPC.ID_USUARIO = "+idUsuario+" " +
			"LEFT JOIN "+schema+".REL_USUARIOS_CATEGORIAS UCA ON UCA.ID_CATEGORY = CA.CATEGORIA_ID AND UCA.ID_USUARIO = "+idUsuario+" " +
			"ORDER BY V.TIPO, V.ANIO, V.NOMBRE, P.NOMBRE, M.NOMBRE_MECANICA")
				.addEntity("V", CampaniaTreeRegs.class)
				.addEntity("C", TblCampana.class)
				.addEntity("E", CatEtiquetas.class)
				.addEntity("CP", TblCampanaProgramas.class)
				.addEntity("P", CatPrograma.class)
				.addScalar("ID_MECANICA", Hibernate.INTEGER)
				.addScalar("NOMBRE_MECANICA", Hibernate.STRING)
				.addScalar("AUTORIZADO_PROGRAMA", Hibernate.STRING)
				.addScalar("AUTORIZADO_MECANICA", Hibernate.STRING)
				.list();
	}

}
