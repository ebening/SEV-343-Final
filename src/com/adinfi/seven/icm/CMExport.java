package com.adinfi.seven.icm;

import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.persistence.dto.DiseñoDTO;
import com.adinfi.seven.presentation.views.util.Utilities;
import com.ibm.mm.sdk.common.DKAttrDefICM;
import com.ibm.mm.sdk.common.DKConstant;
import com.ibm.mm.sdk.common.DKConstantICM;
import com.ibm.mm.sdk.common.DKDDO;
import com.ibm.mm.sdk.common.DKException;
import com.ibm.mm.sdk.common.DKLobICM;
import com.ibm.mm.sdk.common.DKNVPair;
import com.ibm.mm.sdk.common.DKParts;
import com.ibm.mm.sdk.common.DKPidICM;
import com.ibm.mm.sdk.common.DKRetrieveOptionsICM;
import com.ibm.mm.sdk.common.DKSequentialCollection;
import com.ibm.mm.sdk.common.DKTextICM;
import com.ibm.mm.sdk.common.DKUsageError;
import com.ibm.mm.sdk.common.dkIterator;
import com.ibm.mm.sdk.common.dkResultSetCursor;
import com.ibm.mm.sdk.server.DKDatastoreICM;
import java.sql.Date;
import java.util.ArrayList;


import org.apache.log4j.Logger;

public class CMExport {

    private CMConnection connection = null;
    private Logger LOG = Logger.getLogger(ContentManagerExport.class);
    
    public CMExport() {
        connection = new CMConnection();
    }
    
    public boolean connectToServer() {
        
        boolean isConnected = connection.connectCM();
        if (isConnected == true) {            
            LOG.info("CM => Conexion satisfactoria");
        } else {
            LOG.info("CM => Error de conexión");
        }
        return isConnected;
    }
        
    public void disconnect(){
        if (connection != null) {
            connection.disconectCM();
        }
    }
    
    public String importDocument(DiseñoDTO diseñoDTO) {
       
        if (diseñoDTO == null) {
           LOG.info("Documento nulo");
        }
        DKDatastoreICM dataStore = null;
        try {
             dataStore = connection.getDataStoreCM();
        } catch (Exception e) {
            LOG.info("Error en conexión a CM");
        }                    
        
        Documento doc = new Documento();
        
        //doc.setUrl(diseñoDTO.getPath());
        doc.setUrl("C:\\config\\pdfs\\doc.pdf");
       
        String itemType = GlobalDefines.CM_ITEM_TYPE_2;
        doc.setNombre(itemType);
        
        ArrayList<Atributo> lista = null;
        try {
             lista = getAtributosListByItemType(itemType, dataStore);
        } catch (Exception ex) {
            LOG.info(ex);
        }        
        
        lista = fillAttributes(lista, diseñoDTO);
        try {
            doc.setAtributos(lista);
        } catch (Exception e) {
            LOG.info("Error al agregar la lista de atributos al documento CM");
        }
                
        DKDDO documento = importDocument(doc, dataStore, itemType);
        if (documento == null) {
            LOG.info("Error al importar documento CM");
        }        
        String pid = documento.getPidObject().pidString(); 	
        LOG.info("--> Documento importado correctamente = " + pid);
        return pid;
    }
    
    public String importDocument(DiseñoDTO diseñoDTO, byte[] blob) {
        
        if (diseñoDTO == null) {
           LOG.info("Documento nulo");
        }
        DKDatastoreICM dataStore = null;
        try {
             dataStore = connection.getDataStoreCM();
        } catch (Exception e) {
            LOG.info("Error en conexión a CM");
        }                    
        
        Documento doc = new Documento();
        
        //doc.setUrl(diseñoDTO.getPath());
        //doc.setUrl("C:\\config\\pdfs\\doc.pdf");
        doc.setBlob(blob);
       
        String itemType = GlobalDefines.CM_ITEM_TYPE_2;
        doc.setNombre(itemType);
        
        ArrayList<Atributo> lista = null;
        try {
             lista = getAtributosListByItemType(itemType, dataStore);
        } catch (Exception ex) {
            LOG.info(ex);
        }        
        
        lista = fillAttributes(lista, diseñoDTO);
        try {
            doc.setAtributos(lista);
        } catch (Exception e) {
            LOG.info("Error al agregar la lista de atributos al documento CM");
        }
                
        DKDDO documento = importDocument(doc, dataStore, itemType);
        if (documento == null) {
            LOG.info("Error al importar documento CM");
        }        
        String pid = documento.getPidObject().pidString(); 	
        LOG.info("--> Documento importado correctamente = " + pid);
        return pid;
    }
    
    public ArrayList<Atributo> fillAttributes(ArrayList<Atributo> list, DiseñoDTO diseñoDTO) {
        try {
            for (Atributo a : list) {
                if (a.getName().equals("SEVAD_PROGRAMA") == true) {
                    String programa = String.valueOf(diseñoDTO.getPrograma());
                    a.setValor(programa);
                } else if (a.getName().equals("SEVAD_GRUPO_ZONA") == true) {
                    String grupoZona = String.valueOf(diseñoDTO.getGrupoZona());
                    a.setValor(grupoZona);
                } else if (a.getName().equals("SEVAD_ZONA") == true) {
                    String zona = String.valueOf(diseñoDTO.getZona());
                    a.setValor(zona);
                } else if (a.getName().equals("SEVAD_MECANICA") == true) {
                    String mecanica = String.valueOf(diseñoDTO.getMecanica());
                    a.setValor(mecanica);
                } else if (a.getName().equals("SEVAD_TIENDA") == true) {
                    String tienda = String.valueOf(diseñoDTO.getTienda());
                    a.setValor(tienda);
                } else if (a.getName().equals("SEVAD_SENALAMIENTO") == true) {
                    String señalamiento = String.valueOf(diseñoDTO.getSeñalamiento());
                    a.setValor(señalamiento);
                } else {
                }
            }
        } catch (Exception ex) {
        		LOG.info(ex);
        }
        return list;
    }     
 
    public ArrayList<Atributo> getAtributosListByItemType(String itemType, DKDatastoreICM connCM)
            throws DKException, Exception {
        ArrayList<Atributo> atributos = new ArrayList<>();

        DKSequentialCollection attrColl = (DKSequentialCollection) connCM.listEntityAttrs(itemType);

        if (attrColl != null) {
            dkIterator iter = attrColl.createIterator();

            while (iter.more()) {
                DKAttrDefICM attr = (DKAttrDefICM) iter.next();
                Atributo atributo = new Atributo();
                atributo.setName(attr.getName());
                atributo.setDescription(attr.getDescription());
                atributo.setTipo(attr.getType());
                atributo.setLonguitud(attr.getMax());
                atributo.setRequerido(!attr.isNullable());
                atributo.setRepresentativo(attr.isRepresentative());
                atributos.add(atributo);
            }
        }
        return atributos;
    }
    
    public DKDDO importDocument(Documento item, DKDatastoreICM connCM, String itemTypeString) {
        String newPid = null;
        DKDDO ddoDocument = null;
        try {
            String nombreItemType = item.getNombre();
            short itemType = item.getItemType();
            if (itemType == DKConstant.DK_CM_DOCUMENT) {
              
                ddoDocument = connCM.createDDO(nombreItemType, itemType);

                ddoDocument = fillDocumentAttrs(item.getAtributos(), ddoDocument);
              
                ddoDocument = fillParts(ddoDocument, item, connCM);
                
                ddoDocument.add();            
                
                System.out.println(getUrlDocumentoByPID(ddoDocument.getPidObject().toString()));
                                                                                 
                newPid = ddoDocument.getPidObject().pidString();
                LOG.info("==============================");
                LOG.info("PID="+newPid);
                LOG.info("==============================");
            }
        } catch (DKException e) {
        	LOG.info("Ha ocurrido un error en la importaciOn: " + e.getMessage());            
        } catch (Exception e) {
            LOG.info("Ha ocurrido un error en la importaciOn: " + e.getMessage());
        } 
                
        return ddoDocument;
    }
    
    private DKDDO fillParts(DKDDO ddoDocument, Documento doc, DKDatastoreICM dsICM){
        try {
            DKTextICM base = (DKTextICM) dsICM.createDDO("ICMBASETEXT", DKConstantICM.DK_ICM_SEMANTIC_TYPE_BASE);
            ((DKTextICM) base).setFormat("");
            ((DKTextICM) base).setLanguageCode("");
            
            String mimeType = new MimeType().getCMMimeTypeByUrl(doc.getUrl());
//                          
//             if(mimeType == null){
//                 LOG.info("Mimetype=null"+doc.getPid());
//                 return ddoDocument;
//             }
//        
            base.setMimeType("image/jpeg");
            
//            if (doc.getUrl() != null && doc.getUrl().isEmpty() == false) {
                //base.setContentFromClientFile(doc.getUrl());
            	base.setContent(doc.getBlob());
                DKParts dkParts = (DKParts) ddoDocument.getData(ddoDocument.dataId(
                        DKConstant.DK_CM_NAMESPACE_ATTR, DKConstant.DK_CM_DKPARTS));
                dkParts.addElement(base);
//            }                        
            
        } catch (Exception ex) {
           LOG.info(ex);
        }
        return ddoDocument;
    }
    

  
    
    private DKDDO fillDocumentAttrs(ArrayList<Atributo> atributos, DKDDO ddoDocument) {

     for (Atributo attr : atributos) {
            String attrName = attr.getName();
            Short attrType = attr.getTipo();
            String attrValue = attr.getValor();

            boolean addAttr = false;
            if (attrValue != null) {
                if (!attrValue.isEmpty()) {
                    addAttr = true;
                }
            }
            
        try {
            if (addAttr) {
                if (attrType.equals(DKConstant.DK_DATE)) {
                                                   	                                 				
                		String strDate  = attr.getValor();
                		strDate = strDate.substring(0, 10);
                		strDate = strDate.replaceAll("/", "-");
                		Date date = java.sql.Date.valueOf(strDate);
                 
                		ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR, attr.getName()),date);                		                	
                	                	                   
                } else if (attrType.equals(DKConstant.DK_LONG)) {
                    ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR, attr.getName()),
                            Utilities.convertToInteger(attr.getValor()));
                } else if (attrType.equals(DKConstant.DK_DECIMAL)) {
                    ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR, attr.getName()),
                            Utilities.convertToBigDecimal(attr.getValor()));
                } else if (attrType.equals(DKConstant.DK_FSTRING) || attrType.equals(DKConstant.DK_VSTRING)) {
                    ddoDocument.setData(ddoDocument.dataId(DKConstant.DK_CM_NAMESPACE_ATTR, attr.getName()), attr.getValor());
                } else {
                    throw new Exception("No se ha identificado el tipo de dato para el atributo " + attrName);
                }
            }
        } catch (DKUsageError ex) {
        	LOG.info(ex);
        } catch (Exception ex) {
        	LOG.info(ex);
        }
        }
        return ddoDocument;
    }               

    private boolean existsFolder(DKDatastoreICM dsICM, DiseñoDTO diseñoDTO) {
        boolean exists = false;
        try {
            
            //String query = "(/VEC_Contrato)";
            String query = "/SEV_Campanas[@SEV_Campana ="+/*diseñoDTO.getIdCampana()*/"]";
            LOG.info("    Query:  "+query);
            
            // Always Specify Retrieve Options
            DKRetrieveOptionsICM dkRetrieveOptions = DKRetrieveOptionsICM.createInstance(dsICM);
            dkRetrieveOptions.baseAttributes(true);  // Select any desired options, but choose options wisely based on detailed performance considerations documented in the DKRetrieveOptionsICM Javadoc.
            
            // Specify Search / Query Options
            DKNVPair options[] = new DKNVPair[3];
            options[0] = new DKNVPair(DKConstant.DK_CM_PARM_MAX_RESULTS, "1"); // No Maximum (Default) // Specify max using a string value.
            options[1] = new DKNVPair(DKConstant.DK_CM_PARM_RETRIEVE,    dkRetrieveOptions);           // Always specify desired Retrieve Options.
            options[2] = new DKNVPair(DKConstant.DK_CM_PARM_END,         null);                        // Must mark the end of the NVPair
            
            dkResultSetCursor cursor = dsICM.execute(query, DKConstantICM.DK_CM_XQPE_QL_TYPE, options);                        
//          iterateCursor(cursor,dsICM);
            
            DKDDO ddo;
            if((ddo = cursor.fetchNext())!=null) {
                String itemId = ((DKPidICM)ddo.getPidObject()).getItemId();                
                LOG.info("     - Item ID:  "+itemId+"  ("+ddo.getPidObject().getObjectType()+")");  // Print Item ID & Object Type
                exists = true;
            } else {
                exists = false;
            }                                         
        } catch (Exception ex) {
        	LOG.info(ex);            
        }              
        return exists;
    }       
    
	
	public String getUrlDocumentoByPID(String pid) 
			throws DKUsageError, DKException, Exception {       
		String url = "";
		if (pid == null  || pid.trim().equals("")) {
	           LOG.info("Documento nulo");
	        }
	        DKDatastoreICM dkDsICM = null;
	        try {
	        	dkDsICM = connection.getDataStoreCM();
	        } catch (Exception e) {
	            LOG.info("Error en conexión a CM");
	        }                    
		
		DKDDO doc = dkDsICM.createDDOFromPID(pid);
		doc.retrieve(this.getRetrieveOptions(dkDsICM).dkNVPair());
		short tipoObjeto = (Short) doc.getPropertyByName(DKConstant.DK_CM_PROPERTY_ITEM_TYPE);
		
		if(tipoObjeto != DKConstant.DK_CM_FOLDER){
			short dkPartsId = doc.dataId(DKConstant.DK_CM_NAMESPACE_ATTR, DKConstant.DK_CM_DKPARTS);
			
			if(dkPartsId > 0){
				DKParts parts = (DKParts) doc.getData(dkPartsId);
				dkIterator iterator = parts.createIterator();
				
				while (iterator.more()){
					DKLobICM lobICM = (DKLobICM) iterator.next();
					int versionOption = DKConstant.DK_CM_VERSION_LATEST;
					int offset = -1;
					int length = -1;
					int replicaOption = DKConstantICM.DK_ICM_GETINITIALRMURL;
				
//					this.setMimeType(lobICM.getMimeType());
					String urls[] = lobICM.getContentURLs(versionOption, offset, length, replicaOption);
					
					url = urls[0];
					break;
				}
			}
		}
		
		LOG.info("============================================");
		LOG.info("URL="+url);
		LOG.info("============================================");
		
		return url;
	}
	
	private DKRetrieveOptionsICM getRetrieveOptions(DKDatastoreICM dkDsICM) throws DKUsageError, Exception{
		
		DKRetrieveOptionsICM dkRetrieveOptions = DKRetrieveOptionsICM.createInstance(dkDsICM);
		dkRetrieveOptions.baseAttributes(true);
		dkRetrieveOptions.partsAttributes(true);
		dkRetrieveOptions.partsList(true);
		dkRetrieveOptions.linksOutbound(true);
		dkRetrieveOptions.linksTypeFilter(DKConstantICM.DK_ICM_LINKTYPENAME_DKFOLDER);
		dkRetrieveOptions.resourceContent(false);
		
		return dkRetrieveOptions;
	}
	
	public boolean deleteDocCM(String pid)  {
		DKDatastoreICM dkDsICM = null;
		boolean deleted = false;
        try {
        	dkDsICM = connection.getDataStoreCM();
        } catch (Exception e) {
            LOG.info("Error en conexión a CM");
        }     
		DKDDO documento;
		try {
			documento = dkDsICM.createDDOFromPID(pid);
			documento.del();
			deleted = true;
		} catch (DKUsageError e) {
			LOG.info(e);
		} catch (DKException e) {
			LOG.info(e);
		} catch (Exception e) {
			LOG.info(e);
		}
		return deleted;
	}
}