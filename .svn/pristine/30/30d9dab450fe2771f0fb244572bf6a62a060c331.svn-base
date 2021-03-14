/**
 * DaoUtils.java		1.0 17/09/2009
 *
 * Todos los derechos reservados a la Secretaria de Educacion del Estado de Yucatan
 * Direccion de Profesiones
 * Departamento de Registro y Certificacion
 */
package com.adinfi.seven.persistence.daos.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Metodos de utilidad para utilizar en los DAOs
 * 
 * @version 1.0 17/09/2009
 * @author Augusto Valdez
 */
public final class DaoUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DaoUtils.class);

	private DaoUtils() {
	}

	/**
	 * Verifica si el campo es nulo o cadena vacia.
	 * 
	 * @param field
	 *            El campo a verificar.
	 * 
	 * @return {@code true} si el campo es {@code null} o cadena vacia,
	 *         {@code false} de lo contrario.
	 */
	public static boolean isEmpty(String field) {
		return ((field == null) || field.equals(""));
	}

	/**
	 * Convierte un iterador a una lista de objetos inicializados por hibernate.
	 * 
	 * @param <T>
	 *            El tipo de los elementos de la lista resultante. Este tipo se
	 *            toma del tipo declarado para los elementos del iterador.
	 * 
	 * @param iterator
	 *            El iterador.
	 * 
	 * @return Una lista con objetos inicializados con hibernate. Notese que si
	 *         el iterador no tiene mas elementos sobre los cuales iterar al
	 *         momento de ser proporcionado, se devolvera una lista vacia.
	 * 
	 */
	public static <T> List<T> toInitializedList(Iterator<T> iterator) {

		LOG.debug("Inicializando la lista de objetos persistidos.");

		List<T> initializedList = new ArrayList<T>();

		while (iterator.hasNext()) {
			T element = iterator.next();
			Hibernate.initialize(element);
			initializedList.add(element);
		}

		LOG.debug("Terminada la inicializacion de la lista de objetos persistidos.");
		return initializedList;
	}

	/**
	 * Extrae, del iterador proporcionado, el primero de sus elementos,
	 * inicializado por hibernate.
	 * 
	 * @param <T>
	 *            El tipo del elemento resultante. Este tipo se toma del tipo
	 *            declarado para los elementos del iterador.
	 * 
	 * @param iterator
	 *            El iterador.
	 * 
	 * @return El primer elemento del iterador, inicializado por hibernate.
	 *         {@code null} en caso de que el iterador no tenga mas elementos
	 *         que devolver.
	 * 
	 */
	public static <T> T toInitializedInstance(Iterator<T> iterator) {
		T instance = null;

		if (iterator.hasNext()) {
			instance = iterator.next();
			Hibernate.initialize(instance);
		}

		return instance;
	}

	/**
	 * Utility method that tries to properly initialize the Hibernate CGLIB
	 * proxy.
	 * 
	 * @param <T>
	 * @param maybeProxy
	 *            -- the possible Hibernate generated proxy
	 * @param baseClass
	 *            -- the resulting class to be cast to.
	 * @return the object of a class <T>
	 * @throws ClassCastException
	 */
	public static <T> T deproxy(Object maybeProxy, Class<T> baseClass)
			throws ClassCastException {
		if (maybeProxy instanceof HibernateProxy) {
			return baseClass.cast(((HibernateProxy) maybeProxy)
					.getHibernateLazyInitializer().getImplementation());
		}
		return baseClass.cast(maybeProxy);
	}

	public static Class<?> getClass(Object maybeProxy)
			throws ClassCastException {
		if (maybeProxy instanceof HibernateProxy) {
			return ((HibernateProxy) maybeProxy).getHibernateLazyInitializer()
					.getImplementation().getClass();
		}
		return maybeProxy.getClass();
	}

	/**
	 * Verifica el tipo del objeto.
	 * 
	 * Este metodo se utiliza cuando el objeto sea recuperado de la base de
	 * datos con hibernate ya que puede ser un HibernateProxy y hacer inefectivo
	 * el operador instanceof.
	 * 
	 * @param <T>
	 *            El tipo que se desea comprobar.
	 * 
	 * @param maybeProxy
	 *            El objeto.
	 * 
	 * @param clazz
	 *            El objeto de la clase
	 * 
	 * @return {@code true} si el objeto es del tipo proporcionado,
	 *         {@code false} de lo contrario.
	 */
	public static <T> boolean instanceOf(Object maybeProxy, Class<T> clazz) {
		return (maybeProxy instanceof HibernateProxy) ? clazz
				.isInstance(((HibernateProxy) maybeProxy)
						.getHibernateLazyInitializer().getImplementation())
				: clazz.isInstance(maybeProxy);
	}

}
