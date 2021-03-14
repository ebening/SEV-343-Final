/**
 * AbstractDao.java		1.0 05/11/2009
 *
 * Todos los derechos reservados a la Secretaria de Educacion del Estado de Yucatan
 * Direccion de Profesiones
 * Departamento de Registro y Certificacion
 */
package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.persistence.daos.utils.QueryBuilder;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Clase base para los DAOs del sistema.
 * 
 * @version 1.0 05/11/2009
 * @author Augusto Valdez
 * @param <BO>
 */
public abstract class AbstractDaoImpl<BO> extends HibernateDaoSupport implements
		AbstractDao<BO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3230772729740840708L;
	private Class<BO> boClass;

	@Override
	public void persistence(BO entity) throws Exception {
		getHibernateTemplate().persist(entity);
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #save(java.lang.Object)
	 */

	@Override
	public void save(BO entity) throws Exception {
		getHibernateTemplate().save(entity);
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #update(java.lang.Object)
	 */
	@Override
	public void update(BO entity) throws Exception {
		getHibernateTemplate().update(entity);
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #saveOrUpdate(java.lang.Object)
	 */
	@Override
	public void saveOrUpdate(BO entity) throws Exception {
        Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
        try {
			tx = session.beginTransaction();
			getHibernateTemplate().saveOrUpdate(entity);
			tx.commit();
			
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
            this.merge(entity);
		}
	}
    
	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #merge(java.lang.Object)
	 */
	@Override
	public void merge(BO entity) throws Exception {
		getHibernateTemplate().merge(entity);

	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #deleteCampanas(java.lang.Object)
	 */
	@Override
	public void delete(BO entity) throws Exception {
		getHibernateTemplate().delete(entity);
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #attach(java.lang.Object)
	 */
	@Override
	public void attach(BO entity) throws Exception {
		getHibernateTemplate().lock(entity, LockMode.NONE);
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #detach(java.lang.Object)
	 */
	@Override
	public void detach(BO entity) throws Exception {
		getHibernateTemplate().evict(entity);
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BO> getAll() throws Exception {
		List<BO> bos = getHibernateTemplate().loadAll(getBoClass());
		//toInitializedList(bos);
		return bos;
	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao#getById(java.io.Serializable)
	 */
	@Override
	public BO getById(Serializable id) throws Exception {
		BO bo = getBoClass().cast(getHibernateTemplate().get(getBoClass(), id));
		Hibernate.initialize(bo);
		return bo;
	}

	/**
	 * Devuelve la clase del BO para el cual el dao esta dirigido.
	 * 
	 * @return La instancia Class del tipo de los BO.
	 */
	@SuppressWarnings("unchecked")
	private Class<BO> getBoClass() {
		if (boClass == null) {
			ParameterizedType absDaoType = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			boClass = (Class<BO>) absDaoType.getActualTypeArguments()[0];
		}

		return boClass;
	}

	/**
	 * Inicializa todos los objetos proporcionados.
	 * 
	 * @param <BO>
	 *            El tipo de los objetos de negocio proporcionados.
	 * 
	 * @param collection
	 *            Una coleccion de objetos, los cuales se inicializaran.
	 */
	protected static <BO> void initializeAll(Collection<BO> collection) {
		for (BO bo : collection) {
			Hibernate.initialize(bo);
		}
	}

	/**
	 * Convierte un iterador a una lista de objetos inicializados por hibernate.
	 * 
	 * @param <BO>
	 *            El tipo de los elementos de la lista resultante. Este tipo se
	 *            toma del tipo declarado para los elementos del iterador.
	 * 
	 * @param iterator
	 *            El iterador.
	 * 
	 * @return Una lista con objetos inicializados con hibernate. Notese que si
	 *         el iterador no tiene mas elementos sobre los cuales iterar al
	 *         momento de ser proporcionado, se devolvera una lista vacia.
	 */
	protected static <BO> List<BO> toInitializedList(Iterator<BO> iterator) {

		List<BO> initializedList = new ArrayList<>();

		while (iterator.hasNext()) {
			BO element = iterator.next();
			Hibernate.initialize(element);
			initializedList.add(element);
		}

		return initializedList;
	}

	protected static <BO> void toInitializedList(List<BO> list) {

		for (final BO element : list) {
			Hibernate.initialize(element);
		}
	}

	/**
	 * Extrae, del iterador proporcionado, el primero de sus elementos,
	 * inicializado por hibernate.
	 * 
	 * @param <BO>
	 *            El tipo del elemento resultante. Este tipo se toma del tipo
	 *            declarado para los elementos del iterador.
	 * 
	 * @param iterator
	 *            El iterador.
	 * 
	 * @return El primer elemento del iterador, inicializado por hibernate.
	 *         {@code null} en caso de que el iterador no tenga mas elementos
	 *         que devolver.
	 */
	protected static <BO> BO toInitializedInstance(Iterator<BO> iterator) {
		BO instance = null;

		if (iterator.hasNext()) {
			instance = iterator.next();
			Hibernate.initialize(instance);
		}

		return instance;
	}

	/**
     * @throws java.lang.Exception
	 * @s
	 */
	@Override
	public void clearSession() throws Exception {
		getHibernateTemplate().clear();

	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao #flush()
	 */
	@Override
	public void flush() throws Exception {
		getHibernateTemplate().flush();

	}

	/**
     * @throws java.lang.Exception
	 * @see gob.yuc.sep.prof.scensy.persistence.daos.AbstractDao
	 *      #refresh(java.lang.Object)
	 */
	@Override
	public void refresh(BO entity) throws Exception {
		getHibernateTemplate().refresh(entity);

	}

	/**
	 * Obtiene los objetos devueltos por el query proporcionado.
	 * 
	 * @param query
	 *            El query en hql.
	 * 
	 * @return Un lista con objetos inicializados o una lista vacia si el query
	 *         no tuvo resultados.
	 * 
	 * @throws Exception
	 *             Una excepcion indicando un error con la base de datos.
	 */
	@SuppressWarnings("unchecked")
	protected List<BO> toInitializedList(QueryBuilder query) throws Exception {
		Iterator<BO> i = getHibernateTemplate().iterate(query.getQueryString(),
				query.getParameters());
		return toInitializedList(i);
	}

	/**
	 * Obtiene el objeto devuelto por el query proporcionado.
	 * 
	 * @param query
	 *            El query en hql.
	 * 
	 * @return Un objeto inicializado o {@code null} si el query no tuvo
	 *         resultados.
	 * 
	 * @throws Exception
	 *             Una excepcion indicando un error con la base de datos.
	 */
	@SuppressWarnings("unchecked")
	protected BO toInitializedInstance(QueryBuilder query) throws Exception {
		Iterator<BO> i = getHibernateTemplate().iterate(query.getQueryString(),
				query.getParameters());
		return toInitializedInstance(i);
	}

}
