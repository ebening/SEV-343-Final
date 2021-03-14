package com.adinfi.seven.persistence.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**import sun.jkernel.Bundle;*/

import com.adinfi.seven.presentation.views.util.ResourceBundleManager;

/**import com.homedepot.tab.utils.PropertiesHelper;*/

/**
* @Project Name   : AdManager HomeDepot
* @Component Name : Persistor
* @Description    : Conección a Base de Datos DB2 y Progress
*  
*/

/**
 * 
 * @author ELIAS AKE
 * 	Clase usada para la conexión a las distintas bases de datos del proyecto AdManager
 *
 */ 
public class Persistor{

	private static Connection staticConnectionProgress;
	private static Connection staticConnectionDB2;
	static Logger LOG = Logger.getLogger(Persistor.class);
	
	
	
	public static Connection getStaticConnectionProgress() 
			throws SQLException
		{
			if (staticConnectionProgress == null ||
			    staticConnectionProgress.isClosed()  )
			{
				LOG.info("Creando Static conection Progress... ... ");
				
				ResourceBundleManager bundleManager = new ResourceBundleManager();
					    
				String jdbcDriver = bundleManager.getValue("progress.driver");
				String connectionURL = bundleManager.getValue("progress.source.url");
				String connectionUser = bundleManager.getValue("progress.source.username");        
				String connectionPassword = bundleManager.getValue("progress.source.password");
				
				try {
			       /** Get the driver class or the application server data source*/      
			       Class.forName(jdbcDriver).newInstance();
			       staticConnectionProgress = DriverManager.getConnection(connectionURL,connectionUser,connectionPassword);
			       /**staticConnectionProgress = DriverManager.getConnection(connectionURL);*/
			       LOG.info ("<<<--- Progress Connection Satisfactory --->>>");
			       
				} catch(Exception e) {
					LOG.error("ERROR: Persistor.getConnectionProgress() while Getting Connection : " + e.toString());
				}
							
			}
			
			return staticConnectionProgress;
			
		}
	
	
	/**
	 * @author ELIAS AKE UC
	 * @return un objeto de conección a progress
	 * @throws SQLException
	 */
	public static Connection getStaticConnectionProgress1() 
		throws SQLException
	{
		if (staticConnectionProgress == null ||    staticConnectionProgress.isClosed()  )
		{
			try{
				poolConnPro.getInstance();
				staticConnectionDB2  = poolConnPro.getConnection();
			}catch(Exception ex){
				LOG.error("Error al tomar conexion");
				staticConnectionDB2 = null;
			}
			
			LOG.info("Creando Static conection Progress... ... ");
			/**
			 
			ResourceBundleManager bundleManager = new ResourceBundleManager();
				    
			String jdbcDriver = bundleManager.getValue("progress.driver");
			String connectionURL = bundleManager.getValue("progress.source.url");
			String connectionUser = bundleManager.getValue("progress.source.username");        
			String connectionPassword = bundleManager.getValue("progress.source.password");
			
			try {
		       // Get the driver class or the application server data source          
		       Class.forName(jdbcDriver).newInstance();
		       staticConnectionProgress = DriverManager.getConnection(connectionURL,connectionUser,connectionPassword);
//		       staticConnectionProgress = DriverManager.getConnection(connectionURL);
		       System.out.println ("<<<--- Progress Connection Satisfactory --->>>");
		       
			} catch(Exception e) {
				System.err.println("ERROR: Persistor.getConnectionProgress() while Getting Connection : " + e.toString());
			}
				*/		
		}
		
		return staticConnectionProgress;
		
	}

	/**
	 * @author ELIAS AKE UC
	 * @return un objeto de conección a DB2
	 * 
	 */
	public static Connection getConnectionDB2() throws SQLException{
		/**
		if (staticConnectionDB2 == null || staticConnectionDB2.isClosed()  )
		{		
			try{
				PoolConnectionAdmprod.getInstance();
				staticConnectionDB2  = PoolConnectionAdmprod.getConnection();
			}catch(Exception ex){
				System.out.println("Error al tomar conexion");
				staticConnectionDB2 = null;
			}
		} */
		
		ResourceBundleManager bundleManager = new ResourceBundleManager();
		String jdbcDriver = bundleManager.getValue("db2.driver");
		String connectionURL = bundleManager.getValue("db2.source.url");
	    String connectionUser = bundleManager.getValue("db2.source.username");        
	    String connectionPassword = bundleManager.getValue("db2.source.password");
	    
	    LOG.info("Creando static connection DB2...");
	    
		try{				
	    	Class.forName(jdbcDriver).newInstance();
	    	staticConnectionDB2 = DriverManager.getConnection(connectionURL,connectionUser,connectionPassword);
	    	/**staticConnectionDB2.setSchema(connectionSchema);
	    	/**staticConnectionDB2 = DriverManager.getConnection(connectionURL);*/
	    	LOG.info("<<<--- DB2 Connection Satisfactory --->>>");
		} catch(Exception e) {
			LOG.error("ERROR: Persistor.getConnectionDB2() while Getting Connection : " + e.toString());
		}		
		
		
		
		
		return staticConnectionDB2;
	}
			/**
				ResourceBundleManager bundleManager = new ResourceBundleManager();
				String jdbcDriver = bundleManager.getValue("db2.driver");
				String connectionURL = bundleManager.getValue("db2.source.url");
			    String connectionUser = bundleManager.getValue("db2.source.username");        
			    String connectionPassword = bundleManager.getValue("db2.source.password");
			    
			    System.out.println("Creando static connection DB2...");
			    
				try{				
			    	Class.forName(jdbcDriver).newInstance();
			    	staticConnectionDB2 = DriverManager.getConnection(connectionURL,connectionUser,connectionPassword);
			    	//staticConnectionDB2 = DriverManager.getConnection(connectionURL);
			    	System.out.println("<<<--- DB2 Connection Satisfactory --->>>");
				} catch(Exception e) {
					System.err.println("ERROR: Persistor.getConnectionDB2() while Getting Connection : " + e.toString());
				}

			 */

	
/** Close the given connections, clean the result set and the prepared statement */
	public void close(ResultSet resultSet, PreparedStatement sqlStatement, Connection connection ) {
		
		try {

			if (resultSet != null) {
				resultSet.close();
			}
			
			if (sqlStatement != null) {
				sqlStatement.close();
			}
						
			if (connection != null) {
				connection.close();
			}
		
		} catch (SQLException sqlException) {
			sqlStatement = null;
			resultSet = null;
			connection = null;
			
			/* Get the logger for the exception*/
			LOG.error("ERROR: Persistor.close(ResultSet,PreparedStatement,Connection) : " + sqlException.toString());
	    }
	}

	/** Close the given connections, clean the result set and the prepared statement */
	public void close(PreparedStatement sqlStatement, Connection connection ) {
		
		try {
			
			if (sqlStatement != null) {
				sqlStatement.close();
			}
						
			if (connection != null) {
				connection.close();
			}
		
		} catch (SQLException sqlException) {
			sqlStatement = null;
			connection = null;
			
			/** Get the logger for the exception*/
			LOG.error("ERROR: Persistor.close(PreparedStatement,Connection) : " + sqlException.toString());
	    }
	}

	/** Close the given connections, clean the result set and the prepared statement */
	public void close(Connection connection ) {
		
		try {
						
			if (connection != null) {
				/**System.out.println(" -----> Cerrando connection = " + connection.hashCode() + " <-----");*/
				connection.close();
			}
		
		} catch (SQLException sqlException) {
			connection = null;
			
			/** Get the logger for the exception*/
			LOG.error("ERROR: Persistor.close(Connection) : " + sqlException.toString());
	    }
	}
		
	/** Get the result set from the given sql statement*/	
	@SuppressWarnings("all")
	public ResultSet get( PreparedStatement sqlStatement ) {
		ResultSet resultSet = null;
	 
	     try {
	    	 	resultSet = sqlStatement.executeQuery();
	     } catch (SQLException sqlException) {
	     	/**Get the logger for the exception	   */ 
			LOG.error("ERROR: Persistor.get(PreparedStatement) while Getting the ResultSet : " + sqlException.toString() + " CAUSE: "+sqlException.getCause());
			LOG.info("MESSAGE = "+sqlException.getMessage()+" JAVA_CODE_ERROR: "+sqlException.getErrorCode());
	     }	     
	     return resultSet;	
	}

	
	/**
	 * Execute an INSERT, UPDATE or DELETE transaction from the sql statement
	 */
	public int transaction(PreparedStatement sqlStatement){
		int rowsAffected = -1;
		
		try {
			rowsAffected = sqlStatement.executeUpdate();
		} catch (SQLException sqlException) {
	     	/** Get the logger for the exception	*/    
			LOG.error("ERROR: Persistor.transaction(PreparedStatement) while Executing transaction : " + sqlException.toString());
		 }
		 
		return rowsAffected;	
	}
	
	
	public static void main(String[] args) 
	{
		try {
			Persistor.getConnectionDB2();
			Persistor.getStaticConnectionProgress();
		} catch (SQLException e) {
			LOG.error(e);
		}
	}
}
