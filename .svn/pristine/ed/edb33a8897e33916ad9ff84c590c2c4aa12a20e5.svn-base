/**
 * QueryBuilder.java		1.0 26/10/2009
 *
 * Todos los derechos reservados a la Secretaria de Educacion del Estado de Yucatan
 * Direccion de Profesiones
 * Departamento de Registro y Certificacion
 */
package com.adinfi.seven.persistence.daos.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @version 1.0 26/10/2009
 * @author Augusto Valdez
 */
public class QueryBuilder {

	private StringBuilder query;
	private boolean useBooleanOperator;
	private String defaultBooleanOperator;
	private boolean selectDefined;
	private List<Object> paramArray;

	public static enum BooleanOperator {

		AND(" and "), OR(" or ");

		private BooleanOperator(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		private String value;
	};

	public QueryBuilder() {
		this(BooleanOperator.AND);
	}

	public QueryBuilder(BooleanOperator defaultOperator) {
		this.query = new StringBuilder();
		paramArray = new ArrayList<Object>();
		this.defaultBooleanOperator = defaultOperator.toString();
	}

	public void appendSelectFrom(String selectFromStatement) {
		if (selectDefined) {
			throw new IllegalStateException(
					"Ya se ha definido un select from para este query");
		}

		query.append(selectFromStatement);
		selectDefined = true;
	}

	public void appendWhere(String whereStatement) {
		appendWhere(whereStatement, this.defaultBooleanOperator);
	}

	public void appendWhere(String whereStatement, BooleanOperator operator) {
		appendWhere(whereStatement, operator.toString());
	}

	public void appendWhere(String whereStatement, Object param) {
		appendWhere(whereStatement, param, this.defaultBooleanOperator);
	}

	public void appendWhere(String whereStatement, Object param,
			BooleanOperator operator) {
		appendWhere(whereStatement, param, operator.toString());
	}

	private void appendWhere(String whereStatement, Object param,
			String booleanOperator) {

		if (param != null && !"".equals(param.toString().replaceAll("%", ""))) {

			appendWhere(whereStatement, booleanOperator);

			if (whereStatement
					.matches("^( )?upper\\(.*\\)( )?(=|like)( )?\\?( )?$")) {
				paramArray.add(param.toString().toUpperCase());
			} else if (param instanceof QueryBuilder) {

				QueryBuilder subQuery = (QueryBuilder) param;

				query.append(" (");
				query.append(subQuery.getQueryString());
				query.append(")");

				paramArray.addAll(Arrays.asList(subQuery.getParameters()));

			} else {
				paramArray.add(param);
			}
		}

	}

	public void append(String s) {
		query.append(s);
	}

	private void appendWhere(String whereStatement, String booleanOperator) {
		query.append(useBooleanOperator ? booleanOperator : " where ");
		query.append(whereStatement);

		useBooleanOperator = true;
	}

	public String getQueryString() {
		return query.toString();
	}

	public Object[] getParameters() {
		return paramArray.toArray();
	}

}
