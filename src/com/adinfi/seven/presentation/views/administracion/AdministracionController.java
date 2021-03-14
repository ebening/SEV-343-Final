package com.adinfi.seven.presentation.views.administracion;

import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by christian on 26/01/2015.
 */
public class AdministracionController implements Serializable{

    private static final long serialVersionUID = 6577370041531259665L;
    private TreeMenu treeMenu;
    private TreeNode selectedNode;
    private ServiceDynamicCatalogs serviceDynamicCatalogs;
    private CatalogoDinamico catalogoDinamico;

    public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
        return serviceDynamicCatalogs;
    }

    public void setServiceDynamicCatalogs(ServiceDynamicCatalogs serviceDynamicCatalogs) {
        this.serviceDynamicCatalogs = serviceDynamicCatalogs;
    }

    @PostConstruct
    public void init() {
        catalogoDinamico = new CatalogoDinamico(serviceDynamicCatalogs);
        treeMenu = new TreeMenu(serviceDynamicCatalogs);

    }

    private void goCatalog(String catalogNametitle,String catName) {
        catalogoDinamico.setCatalogNametitle(catalogNametitle);
        catalogoDinamico.setCatName(catName);
        catalogoDinamico.setModel(null);
        catalogoDinamico.setColumns(new ArrayList<ColumnModel>());
        catalogoDinamico.setUpdate(false);
        catalogoDinamico.setRowSelect(null);
        catalogoDinamico.setResponseArray(null);
        catalogoDinamico.fillCatalog();
        catalogoDinamico.fillUpdate(-1);
    }

    /**
     * Metodo  que sirve para activar un evento en el que un nodo del
     * menu es seleccionado, cambia de pagina segun la opcion del menu
     * seleccionada.
     */
    public void onNodeSelectV2(NodeSelectEvent event) {
        MenuElement elemento = (MenuElement) selectedNode.getData();
        if (elemento == null || elemento.getUrl().equals("")) {
            if (elemento == null || elemento.getUrl().equals("")) {
                if (selectedNode.isSelected() && selectedNode.isExpanded()) {
                    selectedNode.setExpanded(false);
                    selectedNode.setSelected(false);
                    RequestContext.getCurrentInstance().update("frmMenuLogin:treemenu");
                } else {
                    selectedNode.setExpanded(true);
                    selectedNode.setSelected(false);
                    RequestContext.getCurrentInstance().update("frmMenuLogin:treemenu");
                }
            }
        } else {
            goCatalog(elemento.getNombre(),elemento.getUrl());
            RequestContext.getCurrentInstance().update("pgPrincipalLogin");
        }
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }


    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public CatalogoDinamico getCatalogoDinamico() {
        return catalogoDinamico;
    }

    public TreeMenu getTreeMenu() {
        return treeMenu;
    }
}
