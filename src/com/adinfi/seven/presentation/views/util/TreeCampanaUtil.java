/**
 * 
 */ 
package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceFolleto;
import com.adinfi.seven.persistence.dto.FolletoInfoRelacionDTO;
import com.adinfi.seven.persistence.dto.NodoTreeCampana;
import com.adinfi.seven.business.domain.ProgramaDTO;
import com.adinfi.seven.business.domain.TblDisenoPromoCm;
import com.adinfi.seven.business.services.ServiceEjecucion;
/**
 * @author OMAR
 *
 */
public class TreeCampanaUtil {
	
	
	private static ServiceCampana serviceCampana;
	
	private static ServiceDynamicCatalogs serviceDynamicCatalogs;
	
	private static ServiceFolleto serviceFolleto;
	
	private static ServiceEjecucion serviceEjecucion;
	
	
	
	
	/**
	 * Recibe el id del Folleto que desean que se despliegue abierto
	 * @param idFolleto
	 * @return
	 * @throws Exception
	 */
	public static TreeNode  createTree(int idFolleto) throws Exception{
		List<TblCampana> listaCampana 									= serviceCampana.getAllCampana();
		TreeNode root													= new DefaultTreeNode("Root", null);
		TreeNode nodoCampana 											= null;
		TreeNode nodoMedio 												= null;
		TreeNode nodoTipoMedio 											= null;
		TreeNode nodoHoja 												= null;
		NodoTreeCampana infoNodoCampana  								= null;
		NodoTreeCampana infoNodoMedio   								= null;
		NodoTreeCampana infoNodoTipoMedio   							= null;
		NodoTreeCampana infoNodoHoja  									= null;
		NodoTreeCampana infoNodoCopia  									= null;
		HashMap <String,List<FolletoInfoRelacionDTO>> infoCamapana 		= null;
		HashMap<Integer,String> mapEtiquetas 							= new HashMap<Integer,String>();
		HashMap<Integer,String> mapMedios								= new HashMap<Integer,String>();
		HashMap<Integer,String> mapTipoMedios							= new HashMap<Integer,String>();
		HashMap<String,String> mapCategorias 							= new HashMap<String,String>();
		HashMap<Integer,String> mapSistemaVenta							= new HashMap<Integer,String>();
		boolean isOpen 													= Boolean.FALSE;
		
		
		
		for(TblCampana campana:listaCampana){			//Nodo Campaña	
			
			infoNodoCampana	= new NodoTreeCampana();
			infoNodoCampana.setLabel(campana.getNombre());
			infoNodoCampana.setAccion(Boolean.FALSE);
			infoNodoCampana.setColor(campana.getCatEtiquetas().getCodigo());
			
			nodoCampana = new DefaultTreeNode(infoNodoCampana, root);
			
			infoCamapana = analizeInfoTblCampanaMedio( campana.getTblCampanaMedios() );
			
			Iterator<Entry<String,List<FolletoInfoRelacionDTO>>> iteradorInfoMedios = infoCamapana.entrySet().iterator();
			Map<Integer, Map<Integer, Map<Integer , TblFolletoHojas>  >  > mapHojasxNumHoja;
			while(iteradorInfoMedios.hasNext()){		// Nodo Medio
				Entry<String,List<FolletoInfoRelacionDTO>> infoCampana 	= iteradorInfoMedios.next();
				
				Integer idMedio  								= Integer.valueOf( infoCampana.getKey() );
				List<FolletoInfoRelacionDTO> listInfoFolleto 	= infoCampana.getValue();
				infoNodoMedio 									= new NodoTreeCampana();				
				
				infoNodoMedio.setLabel( getValueMedio(idMedio, mapMedios) );
				infoNodoMedio.setAccion(Boolean.FALSE);
				
				nodoMedio = new DefaultTreeNode(infoNodoMedio,nodoCampana);
				
				for(FolletoInfoRelacionDTO info :listInfoFolleto){				//Nodo tipoMedio
					
					int idTipoMedio 								= Integer.valueOf( info.getIdTipoMedio() );
					String labelTipoMedio 							= getValueTipoMedio(idTipoMedio, mapTipoMedios);
					int idActualFolleto 							= info.getIdFolleto();
					//infoNodoMedio.setId(idActualFolleto);
					infoNodoTipoMedio 								= new NodoTreeCampana();
					HashMap<Integer,List<TblFolletoHojas>> copias 	= null;
					boolean isTipoPrensa 							= isTipoPrensa( idTipoMedio, mapTipoMedios);
					
					if(idActualFolleto == idFolleto){
						isOpen=Boolean.TRUE;
					}else{
						isOpen=Boolean.FALSE;
					}
					
					List<TblFolletoHojas> hojasFolleto = serviceFolleto.getHojasByIdFolleto(idActualFolleto);
					
					infoNodoTipoMedio.setLabel(  labelTipoMedio );					
					nodoTipoMedio = new DefaultTreeNode(infoNodoTipoMedio,nodoMedio);
					
					if(isTipoPrensa){
						copias = processInfoHojasPrensa(hojasFolleto);
						infoNodoTipoMedio.setAccion(Boolean.TRUE);
					}else{
						mapHojasxNumHoja = processInfoHojasFolleto1(hojasFolleto);
						infoNodoTipoMedio.setAccion(Boolean.TRUE);
						infoNodoTipoMedio.setId(idActualFolleto);
						processNodosFolletos(idActualFolleto , nodoTipoMedio);
					}
					
					
					
					
					
					
					
					
					
				}	
				nodoMedio.setExpanded(isOpen);
				nodoTipoMedio.setExpanded(isOpen);
			}

			nodoCampana.setExpanded(isOpen);
		}
		
		return root;
		
	}
	
	
	
	/**
	 * Recibe el id del Folleto que desean que se despliegue abierto
	 * @param idFolleto
	 * @return
	 * @throws Exception
	 */
	public static TreeNode  createTree1(int idFolleto) throws Exception{
		List<TblCampana> listaCampana 									= serviceCampana.getAllCampana();
		TreeNode root													= new DefaultTreeNode("Root", null);
		TreeNode nodoCampana 											= null;
		TreeNode nodoMedio 												= null;
		TreeNode nodoTipoMedio 											= null;
		TreeNode nodoHoja 												= null;
		NodoTreeCampana infoNodoCampana  								= null;
		NodoTreeCampana infoNodoMedio   								= null;
		NodoTreeCampana infoNodoTipoMedio   							= null;
		NodoTreeCampana infoNodoHoja  									= null;
		NodoTreeCampana infoNodoCopia  									= null;
		HashMap <String,List<FolletoInfoRelacionDTO>> infoCamapana 		= null;
		HashMap<Integer,String> mapEtiquetas 							= new HashMap<Integer,String>();
		HashMap<Integer,String> mapMedios								= new HashMap<Integer,String>();
		HashMap<Integer,String> mapTipoMedios							= new HashMap<Integer,String>();
		HashMap<String,String> mapCategorias 							= new HashMap<String,String>();
		HashMap<Integer,String> mapSistemaVenta							= new HashMap<Integer,String>();
		boolean isOpen 													= Boolean.FALSE;
		
		
		
		for(TblCampana campana:listaCampana){			//Nodo Campaña	
			
			infoNodoCampana	= new NodoTreeCampana();
			infoNodoCampana.setLabel(campana.getNombre());
			infoNodoCampana.setAccion(Boolean.FALSE);
			infoNodoCampana.setColor(campana.getCatEtiquetas().getCodigo());
			
			nodoCampana = new DefaultTreeNode(infoNodoCampana, root);
			
			infoCamapana = analizeInfoTblCampanaMedio( campana.getTblCampanaMedios() );
			
			Iterator<Entry<String,List<FolletoInfoRelacionDTO>>> iteradorInfoMedios = infoCamapana.entrySet().iterator();
			
			while(iteradorInfoMedios.hasNext()){		// Nodo Medio
				Entry<String,List<FolletoInfoRelacionDTO>> infoCampana 	= iteradorInfoMedios.next();
				
				Integer idMedio  								= Integer.valueOf( infoCampana.getKey() );
				List<FolletoInfoRelacionDTO> listInfoFolleto 	= infoCampana.getValue();
				infoNodoMedio 									= new NodoTreeCampana();				
				
				infoNodoMedio.setLabel( getValueMedio(idMedio, mapMedios) );
				infoNodoMedio.setAccion(Boolean.FALSE);
				
				nodoMedio = new DefaultTreeNode(infoNodoMedio,nodoCampana);
				
				for(FolletoInfoRelacionDTO info :listInfoFolleto){				//Nodo tipoMedio
					
					int idTipoMedio 								= Integer.valueOf( info.getIdTipoMedio() );
					String labelTipoMedio 							= getValueTipoMedio(idTipoMedio, mapTipoMedios);
					int idActualFolleto 							= info.getIdFolleto();
					infoNodoTipoMedio 								= new NodoTreeCampana();
					HashMap<Integer,List<TblFolletoHojas>> copias 	= null;
					boolean isTipoPrensa 							= isTipoPrensa( idTipoMedio, mapTipoMedios);
					
					if(idActualFolleto == idFolleto){
						isOpen=Boolean.TRUE;
					}else{
						isOpen=Boolean.FALSE;
					}
					
					List<TblFolletoHojas> hojasFolleto = serviceFolleto.getHojasByIdFolleto(idActualFolleto);
					
					if(isTipoPrensa){
						copias = processInfoHojasPrensa(hojasFolleto);
						infoNodoTipoMedio.setAccion(Boolean.TRUE);
					}else{
						copias = processInfoHojasFolleto(hojasFolleto);
						infoNodoTipoMedio.setAccion(Boolean.FALSE);
					}
					
					infoNodoTipoMedio.setLabel(  labelTipoMedio );					
					nodoTipoMedio = new DefaultTreeNode(infoNodoTipoMedio,nodoMedio);
					
					Iterator<Entry<Integer,List<TblFolletoHojas>>> iteradorCopias = copias.entrySet().iterator();
					
					while(iteradorCopias.hasNext()){			//
						Entry<Integer,List<TblFolletoHojas>> infoCopias= iteradorCopias.next();
						
						List<TblFolletoHojas> copiasHojas 	= infoCopias.getValue();
						
						if(isTipoPrensa){
							int idHoja = infoCopias.getKey();
							infoNodoTipoMedio.setId(idHoja);
						}
						
						if(!copiasHojas.isEmpty()){
							
							if(isTipoPrensa){
								for(TblFolletoHojas hoja :copiasHojas){	
									
									infoNodoHoja			= new NodoTreeCampana();
									String categoriaName 	= getValueCategory(String.valueOf( hoja.getIdCategory() ) , mapCategorias);
									String labelDivision 	= Constants.EMPTY;
									String tipo 			= Constants.EMPTY;
									
									infoNodoHoja.setAccion(Boolean.TRUE);
									infoNodoHoja.setId(hoja.getIdHoja());
									
									labelDivision = "Espacio "+ hoja.getNumHoja()+"-"+categoriaName + "-"+ getValueSistemaVenta(hoja.getIdSistemaVenta(), mapSistemaVenta);
									tipo = Constants.PRENSA;
									
									infoNodoHoja.setLabel( labelDivision);
									infoNodoHoja.setTipo(tipo);
									
									new DefaultTreeNode(infoNodoHoja,nodoTipoMedio);
							 }
								
							}
							else{
								TblFolletoHojas hoja 	= copiasHojas.get(0);
								infoNodoHoja			= new NodoTreeCampana();
								String categoriaName 	= getValueCategory(String.valueOf( hoja.getIdCategory() ) , mapCategorias);
								String labelDivision 	= Constants.EMPTY;
								String tipo 			= Constants.EMPTY;
								

								
								infoNodoHoja.setAccion(Boolean.TRUE);
								infoNodoHoja.setId(hoja.getIdHoja());
								
								labelDivision = "Hoja "+ hoja.getNumHoja()+"-"+categoriaName;
								tipo = Constants.FOLLETO;
								
								infoNodoHoja.setLabel( labelDivision);
								infoNodoHoja.setTipo(tipo);
								
								nodoHoja = new DefaultTreeNode(infoNodoHoja,nodoTipoMedio);
								
								if( !isTipoPrensa){
									int iteradorCopia = 1;
								
									for(TblFolletoHojas hojaCopia :copiasHojas){
										infoNodoCopia		= new NodoTreeCampana();
										String labelCopia 	= Constants.EMPTY;
										
										infoNodoCopia.setAccion(Boolean.TRUE);
										infoNodoCopia.setId(hojaCopia.getIdHoja());
										
										if(labelTipoMedio.equals( Constants.FOLLETO)){
											labelCopia = Constants.PREFIJO_COPIA + iteradorCopia + "-" +getValueSistemaVenta(hojaCopia.getIdSistemaVenta(), mapSistemaVenta);
										}else{
											labelCopia = getValueSistemaVenta(hojaCopia.getIdSistemaVenta(), mapSistemaVenta);
										}
										infoNodoCopia.setLabel( labelCopia);
										infoNodoCopia.setTipo(tipo);
										
										new DefaultTreeNode("document",infoNodoCopia,nodoHoja);
										iteradorCopia++;
									}
								}
							}
						}
						if(!isTipoPrensa){
							nodoHoja.setExpanded(isOpen);
						}
						
					}
					
					
				}	
				nodoMedio.setExpanded(isOpen);
				nodoTipoMedio.setExpanded(isOpen);
			}

			nodoCampana.setExpanded(isOpen);
		}
		
		return root;
		
	}
	
	
	protected  static void processNodosFolletos(Integer idActualFolleto , TreeNode nodoTipoMedio  ) throws Exception{
		
		HashMap<Integer,String> mapSistemaVenta							= new HashMap<Integer,String>();		
		Map<Integer, Map<Integer, Map<Integer , TblFolletoHojas>  >  > mapHojasxNumHoja;		
		HashMap<String,String> mapCategorias 							= new HashMap<String,String>();
		
		List<TblFolletoHojas> hojasFolleto = serviceFolleto.getHojasByIdFolleto(idActualFolleto);
		
		 
		mapHojasxNumHoja = processInfoHojasFolleto1(hojasFolleto);
 
		Iterator<Entry<Integer, Map<Integer, Map<Integer , TblFolletoHojas>  >  >  > itHojas=mapHojasxNumHoja.entrySet().iterator();
		Iterator<Entry<Integer, Map<Integer , TblFolletoHojas>  > > itSistVenta=null;
		Iterator<Entry<Integer , TblFolletoHojas>   > itEspacios=null;
		
		Integer hojaId;
		TblFolletoHojas hoja;
		NodoTreeCampana infoNodoHoja			= new NodoTreeCampana();
//		Iterator<Entry<Integer,List<TblFolletoHojas>>> iteradorCopias = copias.entrySet().iterator();
		
		Integer numHoja=null;
		Map<Integer, Map<Integer , TblFolletoHojas>  >    mapSistVenta=null;
		 
		NodoTreeCampana infoNodoNumHoja			= new NodoTreeCampana();
		String labelHoja;
		String tipo;
		TreeNode nodeNumHoja;
		TreeNode nodeSistVenta;
		TreeNode nodeSistEspacio;
		TreeNode nodeHoja;
		NodoTreeCampana infoNodoSistVenta			= new NodoTreeCampana();
		NodoTreeCampana infoNodoEspacio			= new NodoTreeCampana();
		while(itHojas.hasNext()){			//
			Entry<Integer, Map<Integer, Map<Integer , TblFolletoHojas>  >  > infoHojas= itHojas.next();
			
			numHoja=infoHojas.getKey();
			mapSistVenta = infoHojas.getValue();
			
			infoNodoNumHoja			= new NodoTreeCampana();
			infoNodoNumHoja.setAccion(Boolean.TRUE);
			infoNodoNumHoja.setId(numHoja);
			
			labelHoja = "Hoja "+ numHoja;
			tipo = Constants.FOLLETO;
			
			infoNodoNumHoja.setLabel( labelHoja);
			infoNodoNumHoja.setTipo(tipo);
			
			nodeNumHoja = new DefaultTreeNode(infoNodoNumHoja ,nodoTipoMedio);
			
			itSistVenta= mapSistVenta.entrySet().iterator();
			
			Integer sistVenta;
			Map<Integer , TblFolletoHojas> mapEspacios;
			String labelSistVenta;
			
			while( itSistVenta.hasNext() ){
				Entry<Integer, Map<Integer , TblFolletoHojas>   > infoSistVenta= itSistVenta.next();
				
				sistVenta=infoSistVenta.getKey();
				mapEspacios = infoSistVenta.getValue();
				
				
				infoNodoSistVenta			= new NodoTreeCampana();
				infoNodoSistVenta.setAccion(Boolean.TRUE);
				infoNodoSistVenta.setId(sistVenta);
				
				labelSistVenta = " "+  getValueSistemaVenta(sistVenta, mapSistemaVenta);
				tipo = Constants.FOLLETO;
				
				infoNodoSistVenta.setLabel( labelSistVenta);
				infoNodoSistVenta.setTipo(tipo);
				
				nodeSistVenta = new DefaultTreeNode(infoNodoSistVenta ,nodeNumHoja);
				
				itEspacios=mapEspacios.entrySet().iterator();
				int i=0;
				while( itEspacios.hasNext() ){
				  
					Entry<Integer , TblFolletoHojas  > infoHoja = itEspacios.next();
					hojaId=infoHoja.getKey();
					hoja=infoHoja.getValue();
					
					if( hoja.getIdHojaPadre()==null || hoja.getIdHojaPadre()<=0 ){
						continue;
					}
					
					infoNodoSistVenta.setIdHoja(hoja.getIdHojaPadre());
					
					i++;
					//****
					
					infoNodoHoja			= new NodoTreeCampana();
					String categoriaName 	= getValueCategory(String.valueOf( hoja.getIdCategory() ) , mapCategorias);
					String labelDivision 	= Constants.EMPTY;
					tipo 			= Constants.EMPTY;
					

					
					infoNodoHoja.setAccion(Boolean.TRUE);
					infoNodoHoja.setId(hoja.getIdHoja());
					
					labelDivision = "Espacio "+ i+"-"+categoriaName;
					tipo = Constants.FOLLETO;
					
					infoNodoHoja.setLabel( labelDivision);
					infoNodoHoja.setTipo(tipo);
					
					nodeHoja = new DefaultTreeNode(infoNodoHoja,nodeSistVenta);					
					
					
					
					
					//***
					
					
					
				}
				
				
				
				
				
				
				
				
				
			}
			
			
			 					
			/*mapSistVen
			if(!copiasHojas.isEmpty()){
				
				 
			 
					TblFolletoHojas hoja 	= copiasHojas.get(0);
					infoNodoHoja			= new NodoTreeCampana();
					String categoriaName 	= getValueCategory(String.valueOf( hoja.getIdCategory() ) , mapCategorias);
					String labelDivision 	= Constants.EMPTY;
					String tipo 			= Constants.EMPTY;
					

					
					infoNodoHoja.setAccion(Boolean.TRUE);
					infoNodoHoja.setId(hoja.getIdHoja());
					
					labelDivision = "Hoja "+ hoja.getNumHoja()+"-"+categoriaName;
					tipo = Constants.FOLLETO;
					
					infoNodoHoja.setLabel( labelDivision);
					infoNodoHoja.setTipo(tipo);
					
					nodoHoja = new DefaultTreeNode(infoNodoHoja,nodoTipoMedio);
					 
					
					if( !isTipoPrensa){
						int iteradorCopia = 1;
					
						for(TblFolletoHojas hojaCopia :copiasHojas){
							infoNodoCopia		= new NodoTreeCampana();
							String labelCopia 	= Constants.EMPTY;
							
							infoNodoCopia.setAccion(Boolean.TRUE);
							infoNodoCopia.setId(hojaCopia.getIdHoja());
							
							if(labelTipoMedio.equals( Constants.FOLLETO)){
								labelCopia = Constants.PREFIJO_COPIA + iteradorCopia + "-" +getValueSistemaVenta(hojaCopia.getIdSistemaVenta(), mapSistemaVenta);
							}else{
								labelCopia = getValueSistemaVenta(hojaCopia.getIdSistemaVenta(), mapSistemaVenta);
							}
							infoNodoCopia.setLabel( labelCopia);
							infoNodoCopia.setTipo(tipo);
							
							new DefaultTreeNode("document",infoNodoCopia,nodoHoja);
							iteradorCopia++;
						}
					}
				
			} */
			
			/*
			if(!isTipoPrensa){
				nodoHoja.setExpanded(isOpen);
			}*/
			
		} //hasta aqui
		
		
		
	}
	
	
	
	/**
	 * Recibe el id del Folleto que desean que se despliegue abierto
	 * @param idFolleto
	 * @return
	 * @throws Exception
	 */
	public static TreeNode createTreeCampanaSeven(int idFolleto) throws Exception{

		List<TblCampana> listaCampana 									= serviceCampana.getAllCampana();
		TreeNode root													= new DefaultTreeNode("Root", null);
		TreeNode nodoCampana 											= null;
		TreeNode nodoMedio 												= null;
		TreeNode nodoTipoMedio 											= null;
		TreeNode nodoPrograma 											= null;
		
		TreeNode nodoHoja 												= null;
		NodoTreeCampana infoNodoCampana  								= null;
		NodoTreeCampana infoNodoMedio   								= null;
		NodoTreeCampana infoNodoTipoMedio   							= null;
		
/*
 		NodoTreeCampana infoNodoHoja  									= null;
		NodoTreeCampana infoNodoCopia  									= null;
*/
		NodoTreeCampana infoNodoPrograma  								= null;
		NodoTreeCampana infoNodoCategoria  								= null;
		HashMap <String,List<FolletoInfoRelacionDTO>> infoCamapana 		= null;
		HashMap<Integer,String> mapEtiquetas 							= new HashMap<Integer,String>();
		HashMap<Integer,String> mapMedios								= new HashMap<Integer,String>();
		HashMap<Integer,String> mapTipoMedios							= new HashMap<Integer,String>();
		HashMap<String,String> mapCategorias 							= new HashMap<String,String>();
		HashMap<String,String> mapProgramas 							= new HashMap<String,String>();
		HashMap<Integer,String> mapSistemaVenta							= new HashMap<Integer,String>();
		boolean isOpen 													=Boolean.FALSE;
						
		for(TblCampana campana:listaCampana){			//Nodo Campaña	
			
			infoNodoCampana	= new NodoTreeCampana();
			infoNodoCampana.setLabel(campana.getNombre());
			infoNodoCampana.setAccion(Boolean.FALSE);
			infoNodoCampana.setColor(campana.getCatEtiquetas().getCodigo());
			
			nodoCampana = new DefaultTreeNode(infoNodoCampana, root);		
			
			infoCamapana = analizeInfoTblCampanaMedioSeven( campana.getTblCampanaMedios() );
			
			Iterator<Entry<String,List<FolletoInfoRelacionDTO>>> iteradorInfoMedios = infoCamapana.entrySet().iterator();
			
			while(iteradorInfoMedios.hasNext()){		// Nodo Medio
				Entry<String,List<FolletoInfoRelacionDTO>> infoCampana 	= iteradorInfoMedios.next();
				
				Integer idMedio  								= Integer.valueOf( infoCampana.getKey() );
				List<FolletoInfoRelacionDTO> listInfoFolleto 	= infoCampana.getValue();
				infoNodoMedio 									= new NodoTreeCampana();				
				
				infoNodoMedio.setLabel( getValueMedio(idMedio, mapMedios) );
				infoNodoMedio.setAccion(Boolean.FALSE);
				
				nodoMedio = new DefaultTreeNode(infoNodoMedio,nodoCampana);
				
				for(FolletoInfoRelacionDTO info :listInfoFolleto){				//Nodo tipoMedio
					
					int idTipoMedio 								= Integer.valueOf( info.getIdTipoMedio() );
					String labelTipoMedio 							= getValueTipoMedio(idTipoMedio, mapTipoMedios);
					infoNodoTipoMedio 								= new NodoTreeCampana();
					HashMap<Integer,List<TblFolletoHojas>> copias 	= null;
					
					infoNodoTipoMedio.setAccion(false);
					
					infoNodoTipoMedio.setLabel(  labelTipoMedio );					
					nodoTipoMedio = new DefaultTreeNode(infoNodoTipoMedio,nodoMedio);
										
					
			Set<TblCampanaProgramas> listCampanaProgramas = campana.getTblCampanaProgramas();
			
			for (TblCampanaProgramas tblCampanaPrograma : listCampanaProgramas) { //Nodo programa
				
				
				infoNodoPrograma = new NodoTreeCampana();
//				infoNodoPrograma.setIdPrograma(String.valueOf(tblCampanaProgramas.getId().getIdPrograma()));
//				infoNodoPrograma.setId(Integer.valueOf(String.valueOf((tblCampanaProgramas.getId().getIdCampana()))));
				infoNodoPrograma.setAccion(false);				
				infoNodoPrograma.setLabel(getValuePrograma(String.valueOf(tblCampanaPrograma.getId().getIdPrograma()), mapProgramas));
				
				nodoPrograma = new DefaultTreeNode(infoNodoPrograma,nodoTipoMedio);
											
				Set<TblCampanaProgramasCategorias> listCampProCat = tblCampanaPrograma.getTblCampanaProgramasCategorias();
				for (TblCampanaProgramasCategorias tblCampProCat : listCampProCat) {	//Nodo Categoria
					System.out.println("idPro" + tblCampProCat.getId() + " idCat" + tblCampProCat.getIdCategoria());
					
					infoNodoCategoria = new NodoTreeCampana();
					infoNodoCategoria.setIdPrograma(tblCampanaPrograma.getId().getIdPrograma());
					infoNodoCategoria.setNombrePrograma(infoNodoPrograma.getLabel());
					infoNodoCategoria.setId(Integer.valueOf(String.valueOf((tblCampanaPrograma.getId().getIdCampana()))));
					infoNodoCategoria.setNombreCampana(campana.getNombre());
					infoNodoCategoria.setAccion(true);
					infoNodoCategoria.setIdCategoria(tblCampProCat.getIdCategoria());
					infoNodoCategoria.setLabel(getValueCategory(String.valueOf(tblCampProCat.getIdCategoria()),mapCategorias));
					infoNodoCategoria.setFechaInicio(campana.getFechaInicio());
					infoNodoCategoria.setFechaFin(campana.getFechaFin());
					TblDisenoPromoCm tblDisenoPromoCm = serviceEjecucion.getDiseno(infoNodoCategoria.getId(), infoNodoCategoria.getIdPrograma());
					
					if (tblDisenoPromoCm == null) {
					} else {
						infoNodoCategoria.setPid(tblDisenoPromoCm.getPid())	;
					}																	
					new DefaultTreeNode(infoNodoCategoria,nodoPrograma);					
				}
			}
				}	
				nodoMedio.setExpanded(isOpen);
				nodoTipoMedio.setExpanded(isOpen);
			}
			nodoCampana.setExpanded(isOpen);
		}		
		return root;		
	}
	
	private static HashMap<Integer,List<TblFolletoHojas>>  processInfoHojasFolleto(List<TblFolletoHojas> hojasFolleto){
		HashMap<Integer,List<TblFolletoHojas>> copias = new HashMap<Integer,List<TblFolletoHojas>>(); 
		
		for(TblFolletoHojas infoHoja :hojasFolleto){
			Integer numHoja = Integer.valueOf(infoHoja.getNumHoja());
			
			if(copias.containsKey(numHoja)){
				copias.get(numHoja).add(infoHoja);
				
			}else{
				List<TblFolletoHojas> listSistemasVenta = new ArrayList<TblFolletoHojas>();
				listSistemasVenta.add(infoHoja);
				copias.put(numHoja, listSistemasVenta);
			}
		}
		
		return copias;
	}
	
	
	private static Map<Integer, Map<Integer, Map<Integer , TblFolletoHojas>  >  >  processInfoHojasFolleto1(List<TblFolletoHojas> hojasFolleto){
		//HashMap<Integer,List<TblFolletoHojas>> copias = new HashMap<Integer,List<TblFolletoHojas>>(); 
		Map<Integer, Map<Integer, Map<Integer , TblFolletoHojas>  >  > mapHojas=new TreeMap<>();
		//Tree< num_hoja , Tree< sistema_venta , Tree< id_hoja  
		Integer sistemaVenta=null;
		
		Map<Integer, Map<Integer , TblFolletoHojas>  > mapHojasxSistVenta=null;
		Map<Integer , TblFolletoHojas> mapEspacios=null;
		Integer idHoja;
		 
		for(TblFolletoHojas infoHoja :hojasFolleto){
			Integer numHoja = Integer.valueOf(infoHoja.getNumHoja());
			sistemaVenta=Integer.valueOf(infoHoja.getIdSistemaVenta());
			mapHojasxSistVenta = mapHojas.get(numHoja);
			if( mapHojasxSistVenta==null ){
				mapHojasxSistVenta=new TreeMap<>();
				mapHojas.put( numHoja , mapHojasxSistVenta );
				
			}
			sistemaVenta=infoHoja.getIdSistemaVenta();
			mapEspacios= mapHojasxSistVenta.get(sistemaVenta);
			if( mapEspacios==null ){
				mapEspacios=new TreeMap<>();
				mapHojasxSistVenta.put(sistemaVenta, mapEspacios);
			}
			
			
			idHoja=    infoHoja.getIdHoja();
			mapEspacios.put(idHoja, infoHoja);
			
			 
		}
		
		return mapHojas;
	}
	
	
	
	
	
	private static HashMap<Integer,List<TblFolletoHojas>>  processInfoHojasPrensa(List<TblFolletoHojas> hojasFolleto){
		HashMap<Integer,List<TblFolletoHojas>> copias = new HashMap<Integer,List<TblFolletoHojas>>(); 
		
		for(TblFolletoHojas infoHoja :hojasFolleto){
			List<TblFolletoHojas> hojasHijas = new ArrayList<TblFolletoHojas>(infoHoja.getChildHojas());
			
			if(infoHoja.getIdHojaPadre() == null){
				copias.put(infoHoja.getIdHoja(),hojasHijas );
			}
			
		}
		
		return copias;
		
	}
	
	private static HashMap <String,List<FolletoInfoRelacionDTO>> analizeInfoTblCampanaMedio( Set<TblCampanaMedio> tblCampanaMedios ){
		
		Iterator <TblCampanaMedio> iterador 							= tblCampanaMedios.iterator() ;
		HashMap <String,List<FolletoInfoRelacionDTO>> mapInfoMedio 		= new HashMap <String,List<FolletoInfoRelacionDTO>>();
		FolletoInfoRelacionDTO folletoInfoRelacionDTO 					= null;
		
		while ( iterador.hasNext()){
			
			TblCampanaMedio medioCampana 	= iterador.next();
			if(medioCampana.getIdFolleto()  !=null){
				String idMedio 					= String.valueOf( medioCampana.getIdMedio() );
				String idtipoMedio 				= String.valueOf( medioCampana.getIdTipoMedio() );
				int idFolleto 					= medioCampana.getIdFolleto() ;
				folletoInfoRelacionDTO 			= new FolletoInfoRelacionDTO();
				
				folletoInfoRelacionDTO.setIdTipoMedio(idtipoMedio);
				folletoInfoRelacionDTO.setIdFolleto( idFolleto);
				
				if(!mapInfoMedio.containsKey(idMedio)){
					List<FolletoInfoRelacionDTO> listaInfoFolleto	= new ArrayList<FolletoInfoRelacionDTO>();
					
					listaInfoFolleto.add( folletoInfoRelacionDTO );
					mapInfoMedio.put(idMedio,listaInfoFolleto );
				}else{				
					mapInfoMedio.get(idMedio).add(folletoInfoRelacionDTO);
				}
			}
		}
		return mapInfoMedio;		
	}
	
	private static HashMap <String,List<FolletoInfoRelacionDTO>> analizeInfoTblCampanaMedioSeven( Set<TblCampanaMedio> tblCampanaMedios ){
		
		Iterator <TblCampanaMedio> iterador 							= tblCampanaMedios.iterator() ;
		HashMap <String,List<FolletoInfoRelacionDTO>> mapInfoMedio 		= new HashMap <String,List<FolletoInfoRelacionDTO>>();
		FolletoInfoRelacionDTO folletoInfoRelacionDTO 					= null;
		
		while ( iterador.hasNext()){
			
			TblCampanaMedio medioCampana 	= iterador.next();
			
				String idMedio 					= String.valueOf( medioCampana.getIdMedio() );
				String idtipoMedio 				= String.valueOf( medioCampana.getIdTipoMedio() );
				
				folletoInfoRelacionDTO 			= new FolletoInfoRelacionDTO();
				
				folletoInfoRelacionDTO.setIdTipoMedio(idtipoMedio);
							
				if(!mapInfoMedio.containsKey(idMedio)){
					List<FolletoInfoRelacionDTO> listaInfoFolleto	= new ArrayList<FolletoInfoRelacionDTO>();
					
					listaInfoFolleto.add( folletoInfoRelacionDTO );
					mapInfoMedio.put(idMedio,listaInfoFolleto );
				}else{				
					mapInfoMedio.get(idMedio).add(folletoInfoRelacionDTO);
				}
		}
		return mapInfoMedio;
		
	}
	
	private static boolean isTipoPrensa(int idTipoMedio,HashMap<Integer,String> mapTipoMedios) throws Exception{
		
		Boolean resultado 			= Boolean.FALSE;
		String nombreTipoMedio 		= getValueTipoMedio(idTipoMedio, mapTipoMedios);
		
		if(nombreTipoMedio.equals(Constants.PRENSA)){
			resultado = Boolean.TRUE;
		}
		
		return resultado;
	}
	
	
	
	private static String getValueEtiqueta(int idEtiqueta,HashMap<Integer,String> mapEtiquetas) throws GeneralException{
		String resultadoEtiqueta = Constants.EMPTY;
		
		if(mapEtiquetas.containsKey(idEtiqueta)){
			resultadoEtiqueta = mapEtiquetas.get(idEtiqueta);
		}else{
			resultadoEtiqueta = MBUtil.getEtiqueta(serviceDynamicCatalogs, idEtiqueta).getCodigo();
			mapEtiquetas.put(idEtiqueta, resultadoEtiqueta);
		}
		return resultadoEtiqueta;
	}
	
	
	private static String getValueMedio(int idMedio,HashMap<Integer,String> mapMedios) throws Exception{
		String resultadoMedio = Constants.EMPTY;
		
		if(mapMedios.containsKey(idMedio)){
			resultadoMedio = mapMedios.get(idMedio);
		}else{
			resultadoMedio = MBUtil.getMedio(serviceDynamicCatalogs, idMedio).getCode();
			mapMedios.put(idMedio, resultadoMedio);
		}
		return resultadoMedio;
		
	}
	
	private static String getValueTipoMedio(int idTipoMedio,HashMap<Integer,String> mapTipoMedios) throws Exception{
		String resultadoTipoMedio = Constants.EMPTY;
		
		if(mapTipoMedios.containsKey(idTipoMedio)){
			resultadoTipoMedio = mapTipoMedios.get(idTipoMedio);
		}else{
			resultadoTipoMedio = MBUtil.getTipoMediosByIdTipo(serviceDynamicCatalogs, idTipoMedio).getCode();
			mapTipoMedios.put(idTipoMedio, resultadoTipoMedio);
		}
		return resultadoTipoMedio;
		
	}
	
	private static String getValueCategory(String idCategory,HashMap<String,String> mapCategorias) throws Exception{
		String resultadoCategoria = Constants.EMPTY;
		
		if(mapCategorias.containsKey(idCategory)){
			resultadoCategoria = mapCategorias.get(idCategory);
		}else{
			resultadoCategoria = MBUtil.getCategoria(serviceDynamicCatalogs, idCategory).getCodigo();
			mapCategorias.put(idCategory, resultadoCategoria);
		}
		return resultadoCategoria;
		
	}
	
	private static String getValuePrograma(String idPrograma,HashMap<String,String> mapProgramas) throws Exception{
		String resultadoPrograma = Constants.EMPTY;
		
		if(mapProgramas.containsKey(idPrograma)){
			resultadoPrograma = mapProgramas.get(idPrograma);
		}else{
			resultadoPrograma = MBUtil.getPrograma(serviceDynamicCatalogs, idPrograma).getCode();
			mapProgramas.put(idPrograma, resultadoPrograma);
		}
		return resultadoPrograma;
		
	}
	
	private static ProgramaDTO getPrograma(String idPrograma) throws Exception{
		ProgramaDTO programaDTO = MBUtil.getPrograma(serviceDynamicCatalogs, idPrograma);
		return programaDTO;
	}
		
	private static String getValueSistemaVenta(int idSistemaVenta,HashMap<Integer,String> mapSistemaVenta) throws Exception{
		String resultadoSistemaVenta = Constants.EMPTY;
		
		if(mapSistemaVenta.containsKey(idSistemaVenta)){
			resultadoSistemaVenta = mapSistemaVenta.get(idSistemaVenta);
		}else{
			resultadoSistemaVenta = MBUtil.getSistemaVentaDTO(serviceDynamicCatalogs, idSistemaVenta).getCode();
			mapSistemaVenta.put(idSistemaVenta, resultadoSistemaVenta);
		}
		return resultadoSistemaVenta;
		
	}




	/**
	 * @return the serviceCampana
	 */
	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}




	/**
	 * @param serviceCampana the serviceCampana to set
	 */
	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}




	/**
	 * @return the serviceDynamicCatalogs
	 */
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}




	/**
	 * @param serviceDynamicCatalogs the serviceDynamicCatalogs to set
	 */
	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}




	/**
	 * @return the serviceFolleto
	 */
	public ServiceFolleto getServiceFolleto() {
		return serviceFolleto;
	}




	/**
	 * @param serviceFolleto the serviceFolleto to set
	 */
	public void setServiceFolleto(ServiceFolleto serviceFolleto) {
		this.serviceFolleto = serviceFolleto;
	}
	
	public ServiceEjecucion getServiceEjecucion() {
		return serviceEjecucion;
	}


	public void setServiceEjecucion(ServiceEjecucion serviceEjecucion) {
		this.serviceEjecucion = serviceEjecucion;
	}
}