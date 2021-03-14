/**
 * 
 */
package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.adinfi.seven.persistence.dto.ItemDTO;

/**
 * @author OMAR
 *
 */
public final  class TreeUtil {
	
	
	public static TreeNode createTree(List <ItemDTO> itemDTOList){
		
		List <ItemDTO> itemsTempDepto 							= null;
		List <ItemDTO> itemsTempCategoria						= null;
		List <ItemDTO> itemsTempSubcategoria					= null;
		ItemDTO nodoDepto										= null;
		ItemDTO nodoCate										= null;
		ItemDTO nodoSubcate										= null;
		TreeNode root 											= new DefaultTreeNode("Root", null);
		TreeNode treeNodeDepto									= null;
		TreeNode treeNodeCate 									= null;
		TreeNode treeNodeSubcate								= null;
		TreeNode leafItem 										= null;
		
		root.setExpanded(Boolean.TRUE);
		
		
		
		Iterator<Entry<String, List<ItemDTO>>> iteradorDepto = getMapDepartamentosArticulos(itemDTOList).entrySet().iterator();
		  
		  while(iteradorDepto.hasNext()){
		   Entry<String, List<ItemDTO>> mapTemporalDepto=(Entry<String, List<ItemDTO>>)iteradorDepto.next();
		   
		   nodoDepto = new ItemDTO(mapTemporalDepto.getKey(), Boolean.FALSE);
		   itemsTempDepto = mapTemporalDepto.getValue();
		   treeNodeDepto = new DefaultTreeNode(nodoDepto, root);
		   treeNodeDepto.setExpanded(Boolean.TRUE);
		   
		   Iterator<Entry<String, List<ItemDTO>>> iteradorCate = getMapCategoriasArticulos(itemsTempDepto).entrySet().iterator();
		   
		   while(iteradorCate.hasNext()){
			   Entry<String, List<ItemDTO>> mapTemporalCate=(Entry<String, List<ItemDTO>>)iteradorCate.next();
			   
			   nodoCate = new ItemDTO(mapTemporalCate.getKey(), Boolean.FALSE);
			   itemsTempCategoria = mapTemporalCate.getValue();
			   treeNodeCate =  new DefaultTreeNode(nodoCate, treeNodeDepto);
			   treeNodeCate.setExpanded(Boolean.TRUE);
			   
			   Iterator<Entry<String, List<ItemDTO>>> iteradorSubcate = getMapSubcategoriassArticulos(itemsTempCategoria).entrySet().iterator();
			   
			   while(iteradorSubcate.hasNext()){
				   
				   Entry<String, List<ItemDTO>> mapTemporalSubcate=(Entry<String, List<ItemDTO>>)iteradorSubcate.next();
				   
				   nodoSubcate = new ItemDTO(mapTemporalSubcate.getKey(), Boolean.FALSE);
				   if(mapTemporalSubcate.getValue() != null){
					   nodoSubcate.setIdSubcategoria(mapTemporalSubcate.getValue().get(0).getIdSubcategoria());
					   nodoSubcate.setIdCategoria(mapTemporalCate.getValue().get(0).getIdCategoria());
					   nodoSubcate.setIdDepartamento(mapTemporalDepto.getValue().get(0).getIdDepartamento());
				   }
				   
				   treeNodeSubcate = new DefaultTreeNode(nodoSubcate, treeNodeCate);
				   treeNodeSubcate.setExpanded(Boolean.TRUE);
				   itemsTempSubcategoria = mapTemporalSubcate.getValue();
				   
				   for(ItemDTO item:itemsTempSubcategoria){
					   leafItem = new DefaultTreeNode(item,treeNodeSubcate);
					   leafItem.setExpanded(Boolean.TRUE);
				   }
				   
			   }
		   }
		   
		  }
		
		return root;
		
	}
	
	
	private static HashMap<String,List <ItemDTO>> getMapDepartamentosArticulos(List <ItemDTO> itemDTOList ){
		
		HashMap<String,List <ItemDTO>> departamentosArticulos 	= new HashMap<String,List <ItemDTO>>();
		
		for(ItemDTO item:itemDTOList){
			String depto = item.getDepartamento();
			validateFiltro(depto, departamentosArticulos, item);
		}
		
		return departamentosArticulos;
		
	}
	
	
	private static HashMap<String,List <ItemDTO>> getMapCategoriasArticulos(List <ItemDTO> itemDTOList){
		HashMap<String,List <ItemDTO>> categoriaArticulos 	= new HashMap<String,List <ItemDTO>>();
		
		for(ItemDTO item:itemDTOList){
			String categoria = item.getCategoria();
			validateFiltro(categoria, categoriaArticulos, item);
		}
		
		return categoriaArticulos;
		
	}
	
	
	private static HashMap<String,List <ItemDTO>> getMapSubcategoriassArticulos(List <ItemDTO> itemDTOList){
		HashMap<String,List <ItemDTO>> subcategoriaArticulos 	= new HashMap<String,List <ItemDTO>>();
		
		for(ItemDTO item:itemDTOList){
			String subcategoria = item.getSubcategoria();
			validateFiltro(subcategoria, subcategoriaArticulos, item);
		}
		
		return subcategoriaArticulos;
		
	}
	
	
	private static void validateFiltro(String filtro, HashMap<String,List <ItemDTO>> departamentosArticulos,ItemDTO item){
		List <ItemDTO> listaTemporal 		= null;
		item.setIsItem(Boolean.TRUE);
		
		if( departamentosArticulos.containsKey(filtro)){
			listaTemporal = departamentosArticulos.get(filtro);
			listaTemporal.add(item);
			departamentosArticulos.put(filtro, listaTemporal);
		}else{
			listaTemporal = new ArrayList<ItemDTO>();
			listaTemporal.add(item);
			departamentosArticulos.put(filtro, listaTemporal);
		}
	}
	
	
	/**
	 * Obtiene la lista de ItemDTO de todos los nodos seleccionados, con objetos NO REPETIDOS.
	 * @param selectedTableNodes
	 * @return
	 */
	public static List<ItemDTO> procesarInfoNodos(TreeNode[] selectedTableNodes){
		
		List<ItemDTO> resultado = new ArrayList<ItemDTO>();
		
		for(TreeNode node:selectedTableNodes){
			
			if( !isHojaAddItem(node, resultado) ){
				List<TreeNode> nodosHijos = node.getChildren();
				for(TreeNode subNode:nodosHijos){
					if(!isHojaAddItem(subNode, resultado)){
						List<TreeNode> SubnodosHijos = subNode.getChildren();
						for(TreeNode subNode2:SubnodosHijos){
							isHojaAddItem(subNode2, resultado);
						}
					}
				}
			}
			
			
		}
		
		
		return resultado;
		
	} 
	
	
	/**
	 * Obtiene la lista de ItemDTO de un arbol.
	 * @param selectedTableNodes
	 * @return
	 */
	public static List<ItemDTO> procesarInfoTree(TreeNode root){
		
		List<ItemDTO> resultado = new ArrayList<ItemDTO>();
		List<TreeNode>treeInfo = root.getChildren();
		
		for(TreeNode node:treeInfo){
				if( !isHojaAddItem(node, resultado) ){
					List<TreeNode> nodosHijos = node.getChildren();
					for(TreeNode subNode:nodosHijos){
						if(!isHojaAddItem(subNode, resultado)){
							List<TreeNode> SubnodosHijos = subNode.getChildren();
							for(TreeNode subNode2:SubnodosHijos){
								if(!isHojaAddItem(subNode2, resultado)){
									List<TreeNode> SubnodosHijos2 = subNode2.getChildren();
									for(TreeNode subNode3:SubnodosHijos2){
										isHojaAddItem(subNode3, resultado);
									}
								}
							}
						}
					}
				}
		}
			
		
		
		
		return resultado;
		
	} 
	
	public static void setInfoPrecioPromocion(TreeNode node,String value){
		
		if(! node.isLeaf()){
			List<TreeNode> hijos = node.getChildren();
			for(TreeNode hijo:hijos){
				if(hijo.isLeaf()){
					( (ItemDTO) hijo.getData() ).setPrecioPromocion(value);
					
				}else{
					List<TreeNode> subHijos = hijo.getChildren();
					for(TreeNode subHijo :subHijos){
						if(subHijo.isLeaf()){
							( (ItemDTO) subHijo.getData() ).setPrecioPromocion(value);
						}else{
							List<TreeNode> leafs =subHijo.getChildren();
							for(TreeNode nodo:leafs){
								( (ItemDTO) nodo.getData() ).setPrecioPromocion(value);
							}
						}
						
					}
				}
			}
		}
		
	}
	
	
public static void setInfoTipoPromocion(TreeNode node,String value){
		
		if(! node.isLeaf()){
			List<TreeNode> hijos = node.getChildren();
			for(TreeNode hijo:hijos){
				if(hijo.isLeaf()){
					( (ItemDTO) hijo.getData() ).setTipoPromocion(value);
					
				}else{
					List<TreeNode> subHijos = hijo.getChildren();
					for(TreeNode subHijo :subHijos){
						if(subHijo.isLeaf()){
							( (ItemDTO) subHijo.getData() ).setTipoPromocion(value);
						}else{
							List<TreeNode> leafs =subHijo.getChildren();
							for(TreeNode nodo:leafs){
								( (ItemDTO) nodo.getData() ).setTipoPromocion(value);
							}
						}
						
					}
				}
			}
		}
		
	}



public static void setInfoTipoPublicidad(TreeNode node,List<String> values){
	
	if(! node.isLeaf()){
		List<TreeNode> hijos = node.getChildren();
		for(TreeNode hijo:hijos){
			if(hijo.isLeaf()){
				( (ItemDTO) hijo.getData() ).setTipoPublicidad(values);
				
			}else{
				List<TreeNode> subHijos = hijo.getChildren();
				for(TreeNode subHijo :subHijos){
					if(subHijo.isLeaf()){
						( (ItemDTO) subHijo.getData() ).setTipoPublicidad(values);
					}else{
						List<TreeNode> leafs =subHijo.getChildren();
						for(TreeNode nodo:leafs){
							( (ItemDTO) nodo.getData() ).setTipoPublicidad(values);
						}
					}
					
				}
			}
		}
	}
	
}
	
	private static boolean isHojaAddItem(TreeNode node,List<ItemDTO> listaResultado){
		
		boolean resultado = Boolean.TRUE;
		
		if(node.isLeaf()){
			ItemDTO item = (ItemDTO)node.getData();
			if(!listaResultado.contains(item)){
				listaResultado.add(item);
			}
		}else{
			resultado = Boolean.FALSE;
		}
		
		return resultado;
	}
	
	
	/**
	 * Obtiene el TreeNode, de acuerdo al SKU que se le proporcione.
	 * @param sku
	 * @param root
	 * @return
	 */
	public static TreeNode findLeaf(String sku , TreeNode root){
			
			List<TreeNode> treeInfo = root.getChildren();
			ItemDTO item  = null;
			TreeNode nodeResult = null;
			
			for(TreeNode node:treeInfo){
				
				if( ! node.isLeaf() ){
					List<TreeNode> nodosHijos = node.getChildren();
					for(TreeNode subNode:nodosHijos){
						if(! subNode.isLeaf()){
							List<TreeNode> SubnodosHijos = subNode.getChildren();
							for(TreeNode subNode2:SubnodosHijos){
								if(! subNode2.isLeaf()){
									List<TreeNode> SubnodosHijos2 = subNode2.getChildren();
									for(TreeNode subNode3:SubnodosHijos2){
										if(subNode3.isLeaf()){
											item = (ItemDTO) subNode3.getData();
											if( item.getSku().equals(sku) ){
												nodeResult = subNode3;
											}
											
										}
									}
									
									
								}
							}
						}
					}
				}
	
			}
			return nodeResult;
		}
	
	public static TreeNode  findNode(String titulo , TreeNode root){
		
		List<TreeNode> treeInfo = root.getChildren();
		ItemDTO itemTemp  = null;
		TreeNode nodeResult = null;
		
		for(TreeNode node:treeInfo){
			if( titulo.equals( ( (ItemDTO)node.getData() ).getTitulo() ) ){
				nodeResult = node;
			}else{
				List<TreeNode> nodeInfo = node.getChildren();
				for( TreeNode subNode : nodeInfo){
					if( titulo.equals( ( (ItemDTO)subNode.getData() ).getTitulo() ) ){
						nodeResult = subNode;
					}else{
						List<TreeNode> subNodeInfo = subNode.getChildren();
						for(TreeNode subNode2 : subNodeInfo){
							if( titulo.equals( ( (ItemDTO)subNode2.getData() ).getTitulo() ) ){
								nodeResult = subNode2;
							}
						}
					}
				}
			}
			
		}
		return nodeResult;
		
	}
	
	public static boolean validationisNewLeaf(String sku,TreeNode root){
		
		Boolean result 	= null;
		TreeNode node 	= null;
		node = findLeaf(sku, root);
		
		if(node == null){
			result = Boolean.TRUE;
		}else {
			result = Boolean.FALSE;
		}
		
		return result;
	}
	
	
	public static void removeNode(TreeNode node){
		node.getChildren().clear();
		node.getParent().getChildren().remove(node);	
	}
	
	
	public static void addNode(ItemDTO item,TreeNode root){
		
		TreeNode leaf 				= null;
		TreeNode node 				= null;
		TreeNode nodeSubcat 		= null;
		TreeNode nodeCate 			= null;
		TreeNode nodeDepto 			= null;
		ItemDTO depto 				= new ItemDTO(item.getDepartamento(), Boolean.FALSE);
		ItemDTO cate 				= new ItemDTO(item.getCategoria(), Boolean.FALSE);
		ItemDTO subcate				= new ItemDTO(item.getSubcategoria(), Boolean.FALSE);
		
		node = findNode(item.getSubcategoria(), root);
		
		if(node !=null){
			leaf = new DefaultTreeNode(item,node) ;
			leaf.setExpanded(Boolean.TRUE);
		}else{
			node = findNode(item.getCategoria(), root);
			
			if(node !=null){
				nodeSubcat = new DefaultTreeNode(subcate,node);
				nodeSubcat.setExpanded(Boolean.TRUE);
				leaf = new DefaultTreeNode(item, nodeSubcat) ;
				leaf.setExpanded(Boolean.TRUE);
			}else{
				node = findNode(item.getDepartamento(), root);
				if(node != null){
					nodeCate = new DefaultTreeNode(cate,node);
					nodeCate.setExpanded(Boolean.TRUE);
					nodeSubcat = new DefaultTreeNode(subcate,nodeCate);
					nodeSubcat.setExpanded(Boolean.TRUE);
					leaf = new DefaultTreeNode(item,nodeSubcat);
					leaf.setExpanded(Boolean.TRUE);
				}else{
					nodeDepto = new DefaultTreeNode(depto,root);
					nodeDepto.setExpanded(Boolean.TRUE);
					nodeCate = new DefaultTreeNode(cate,nodeDepto);
					nodeCate.setExpanded(Boolean.TRUE);
					nodeSubcat = new DefaultTreeNode(subcate,nodeCate);
					nodeSubcat.setExpanded(Boolean.TRUE);
					leaf = new DefaultTreeNode(item,nodeSubcat) ;
					leaf.setExpanded(Boolean.TRUE);
					
				}
				
			}
		}
	}
	
	
}
