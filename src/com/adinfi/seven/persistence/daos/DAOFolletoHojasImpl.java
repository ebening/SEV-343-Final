package com.adinfi.seven.persistence.daos;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.adinfi.seven.business.domain.TblFolletoHojas;

public class DAOFolletoHojasImpl extends AbstractDaoImpl<TblFolletoHojas>
		implements DAOFolletoHojas {
	private Logger LOG = Logger.getLogger(DAOFolletoHojasImpl.class);
	
	@Override
	public void deleteHojas(int folletoId, int ultimaHoja) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblFolletoHojas a where a.idFolleto= "+folletoId
						+" and a.numHoja > "+ultimaHoja);
		query.executeUpdate();
	}
	
	@Override
	public void delete(int idFolleto) throws Exception{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblFolletoHojas a where a.idFolleto = "
								+ idFolleto);
		query.executeUpdate();
	}

	@Override
	public List<TblFolletoHojas> getHojasByFolletoId(int idFolleto) {
		@SuppressWarnings("unchecked")
		Iterator<TblFolletoHojas> folletoHojas = getHibernateTemplate()
				.iterate(
						"from TblFolletoHojas obj where obj.id.idFolleto = ? ORDER BY obj.numHoja, obj.idHoja",
						new Object[] { idFolleto });
		return toInitializedList(folletoHojas);
	}

	@Override
	public TblFolletoHojas saveFolletoHojas(TblFolletoHojas folletoHojas) {
		try {
			saveOrUpdate(folletoHojas);
		} catch (Exception e) {
			LOG.error(e);
		}

		return folletoHojas;
	}

	@Override
	public TblFolletoHojas getHojaByIdFolletoIdHoja(int idFolleto, int idHoja) {
		@SuppressWarnings("unchecked")
		Iterator<TblFolletoHojas> folletoHojas = getHibernateTemplate()
				.iterate(
						"from TblFolletoHojas obj where obj.idFolleto = ? and obj.idHoja",
						new Object[] { idFolleto, idHoja });
		return toInitializedInstance(folletoHojas);

	}

	@Override
	public Number getTotalNumHojasFolleto(final int idHoja) {
		Number number = null;
		try {
			number = (Number) getHibernateTemplate().execute(
					new HibernateCallback() {

						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String query = "SELECT count(DISTINCT NUM_HOJA) numero_hojas FROM FAM161DEV.TBL_FOLLETO_HOJAS where ID_FOLLETO="
									+ " " + idHoja;
							Query q = session.createSQLQuery(query);

							LOG.info(q.uniqueResult());
							return q.uniqueResult();
						}
					});
		} catch (java.lang.ClassCastException e) {
			LOG.error(e);
		}

		return number;
	}

	@Override
	public List<TblFolletoHojas> getCopias(TblFolletoHojas hoja) {
		@SuppressWarnings("unchecked")
		Iterator<TblFolletoHojas> folletoHojas = getHibernateTemplate()
				.iterate(
						"from TblFolletoHojas obj where obj.idFolleto = ? and obj.numHoja=?",
						new Object[] { hoja.getIdFolleto(), hoja.getNumHoja() });
		return toInitializedList(folletoHojas);
	}

	@Override
	public Number getCategoriaHoja(final int idHoja) {
		Number number = null;
		try {
			number = (Number) getHibernateTemplate().execute(
					new HibernateCallback() {

						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String query = "SELECT 	idCategory  FROM TblFolletoHojas where idHoja= "
									+ idHoja;
							Query q = session.createQuery(query);
							LOG.info(q.uniqueResult());
							return q.uniqueResult();
						}
					});
		} catch (java.lang.ClassCastException e) {
			LOG.error(e);
			;
		}

		return number;
	}

	@Override
	public void deleteHojas(int folletoId, short numHoja, String espaciosValidos) {
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblFolletoHojas a where a.idFolleto= "+folletoId
						+" and a.numHoja = "+numHoja
						+" and a.idHoja not in ("+espaciosValidos+")");
		query.executeUpdate();
	}
}