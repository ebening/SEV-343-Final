/**
 * AbstractDao.java		1.0 05/11/2009
 *
 * Todos los derechos reservados a la Secretaria de Educacion del Estado de Yucatan
 * Direccion de Profesiones
 * Departamento de Registro y Certificacion
 */
package com.adinfi.seven.persistence.daos;

import java.io.Serializable;
import java.util.List;

/**
 * Interface base para los DAOs del sistema.
 * 
 * @version 1.0 05/11/2009
 * @author Augusto Valdez
 */
public interface AbstractDao<BO> extends Serializable{

	/**
	 * Guarda, en la base de datos del sistema, un objeto de negocio.
	 * 
	 * @param entity
	 *            El objeto de negocio a guardar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void save(BO entity) throws Exception;

	/**
	 * Actualiza, en la base de datos del sistema, la informacion de un objeto
	 * de negocio.
	 * 
	 * @param entity
	 *            El objeto de negocio a actualizar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void update(BO entity) throws Exception;

	/**
	 * Guarda o actualiza, en la base de datos del sistema, la informacion de un
	 * objeto de negocio.
	 * 
	 * @param entity
	 *            El objeto de negocio a guardar o actualizar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void saveOrUpdate(BO entity) throws Exception;

	/**
	 * Guarda o actualiza, en la base de datos del sistema, la informacion de un
	 * objeto de negocio.
	 * 
	 * @param entity
	 *            El objeto de negocio a guardar o actualizar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void merge(BO entity) throws Exception;

	/**
	 * Elimina, de la base de datos del sistema, la informacion de un objeto de
	 * negocio.
	 * 
	 * @param entity
	 *            El objeto de negocio a eliminar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void delete(BO entity) throws Exception;

	/**
	 * Asocia un objeto de negocio a la sesion de base de datos.
	 * 
	 * @param entity
	 *            El objeto de negocio a asociar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void attach(BO entity) throws Exception;

	/**
	 * Desasocia un objeto de negocio a la sesion de base de datos.
	 * 
	 * @param entity
	 *            El objeto de negocio a desasociar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void detach(BO entity) throws Exception;

	/**
	 * Recupera, de la base de datos del sistema, un objeto de negocio
	 * utilizando como referencia su identificador.
	 * 
	 * @param id
	 *            El identificador unico del objeto de negocio a recuperar.
	 * 
	 * @return El objeto de negocio o {@code null} si este no existe.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	BO getById(Serializable id) throws Exception;

	/**
	 * Recupera la lista de todos los objetos de negocio del mismo tipo.
	 * 
	 * @return La lista con todos los objetos del mismo tipo o una lista vacia
	 *         si ninguno existe.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	List<BO> getAll() throws Exception;

	/**
	 * Elimina todos los objetos de una Session de Hibernate.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void clearSession() throws Exception;

	/**
	 * Fuerza la ejecucion de las operaciones de persistencia (commit).
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void flush() throws Exception;

	/**
	 * Restaura una entidad asignandole los valores que le corresponden de la
	 * base de datos, ignorando si esta entidad se encuentra en cache.
	 * 
	 * @param entity
	 *            La entidad a restaurar.
	 * 
	 * @throws Exception
	 *             Una DataAccessException indicando que la operacion no se pudo
	 *             realizar debido a un error en la transaccion.
	 */
	void refresh(BO entity) throws Exception;

	void persistence(BO entity) throws Exception;

}
