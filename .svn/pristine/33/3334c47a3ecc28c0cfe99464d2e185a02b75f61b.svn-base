package com.adinfi.seven.persistence.daos;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

@SuppressWarnings("all")
public class poolConnPro {

	static Logger LOG = Logger.getLogger(poolConnPro.class);
	static InitialContext ic = null;
	static DataSource ds = null;
	static poolConnPro instancia = null;

	public static poolConnPro getInstance() {
		if (instancia == null) {
			instancia = new poolConnPro();
			try {
				ic = new InitialContext();
				if(ic == null) {
					throw new Exception("No hubo contacto con Context");
				}
				ds = (DataSource)ic.lookup("java:comp/env/jdbc/progress");
				if ( ds == null ) {
					   throw new Exception("Data Source no encontrado!");
				}
				LOG.info("Creo la instancia");
			} catch (NamingException nmException) {
				LOG.error("ConnectionFactory:getConnection():Naming exception "+  nmException );   
			} catch (Exception e) {
				LOG.error("ConnectionFactory:getConnection():No se pudo obtener una conexion del pool - " + e.getMessage());  
			}
		}else LOG.info("Regresa instancia de ADMPROD que previamente había sido creada");
		
		return instancia;
	}
	
	public static Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			conn = ds.getConnection();
			if( conn!=null ) {
				LOG.info("ConnectionFactory:getConnection():Se logro una conexion del pool ");
			} else {
				LOG.info("ConnectionFactory:getConnection():No se logro una conexion del pool ");
			}
		}catch (SQLException e) {
			LOG.error("ConnectionFactory:getConnection():No se pudo obtener una conexion del pool - " + e.getMessage() );  
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) throws SQLException {

		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		}catch (SQLException e) {
			LOG.error("ConnectionFactory:getConnection():No se pudo obtener una conexion del pool - " + e.getMessage() );  
		}
	}

	
}
