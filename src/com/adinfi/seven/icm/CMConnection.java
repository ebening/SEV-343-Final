package com.adinfi.seven.icm;

import org.apache.log4j.Logger;
import com.adinfi.defines.GlobalDefines;
import com.ibm.mm.sdk.common.DKException;
import com.ibm.mm.sdk.server.DKDatastoreICM;

public class CMConnection {

	private DKDatastoreICM dsICM = null;
	private Logger LOG = Logger.getLogger(CMConnection.class);

	public DKDatastoreICM getDataStoreCM() {
		return dsICM;
	}

	/**
	 * Este metodo genera una conexion a Content Manager.
	 * 
	 * @return true - Si la conexion fue exitosa.
	 */
	public boolean connectCM() {
				
	/*	
	 	String dbConnectionLS = "ICMNLSDB";
		String dbConnectionUser = "icmadmin";
		String dbConnectionPassword = "Passw0rd";
		String dbConnectionSchema = "icmadmin";
	*/
		
		String dbConnectionLS = GlobalDefines.ICM_LIBRARY;
		String dbConnectionUser = GlobalDefines.ICM_ADMIN;
		String dbConnectionPassword = GlobalDefines.ICM_PASSWORD;
		String dbConnectionSchema = GlobalDefines.ICM_SCHEMA;

		boolean connectionActive = true;
		try {
			dsICM = new DKDatastoreICM();
			dsICM.connect(dbConnectionLS, dbConnectionUser,
					dbConnectionPassword, dbConnectionSchema);
		} catch (DKException e) {
			LOG.error(e);
			connectionActive = false;
		} catch (Exception e) {
			LOG.error(e);
			connectionActive = false;
		}
		return connectionActive;
	}

	/**
	 * Desconecta la conexion a Content Manager
	 */
	public void disconectCM() {
		try {
			if (dsICM != null && dsICM.isConnected()) {
				dsICM.disconnect();
				dsICM.destroy();
			}
		} catch (DKException e) {
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public static void main(String[] args) {
		CMConnection connection = new CMConnection();

		int percent = -1; 
		boolean connect = connection.connectCM();
		if (connect == true) { System.out.println("CM => Conexion satisfactoria"); 
		} else {
			System.out.println("CM => Error de conexion"); 
		}
	}
}