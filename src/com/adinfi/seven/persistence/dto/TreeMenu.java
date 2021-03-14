package com.adinfi.seven.persistence.dto;

import com.adinfi.seven.business.domain.CatalogosOpc;
import com.adinfi.seven.business.domain.Opcion;
import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.admin.ServiceMenuAndRoles;
import com.adinfi.seven.presentation.views.util.Util;
import com.adinfi.seven.presentation.views.util.Utileria;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author christian
 */
public class TreeMenu implements Serializable {

    private TreeNode root;
    private MenuElement raiz;
    private Navigator navigator;
    private TreeNode selectedNode;
    private ServiceDynamicCatalogs serviceDynamicCatalogs;

    public TreeNode getRoot() {
        return root;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }


    public TreeMenu(ServiceDynamicCatalogs serviceDynamicCatalogs) {
       init(getMenuElemets(serviceDynamicCatalogs));
       // init(getMenuElemets2());
    }

    private void init(List<MenuElement> lista) {
        this.raiz = new MenuElement(0, -1, 0, "raiz", "");
        this.root = new DefaultTreeNode("Root", null);

        lista = new ArrayList<>(lista);
        lista.add(this.raiz);
        for (MenuElement item : lista) {
            for (MenuElement padre : lista) {
                if (item.getModulosPadreId().intValue() == padre.getModulosId().intValue()) {
                    padre.getSubmenu().add(item);
                    break;
                }
            }
        }

        initMenu();
    }

    private void initMenu() {
        if (this.raiz.isHasChildren()) {
            for (MenuElement menu1 : this.raiz.getSubmenu()) {
                // armarMenuV1(menu1, root);
                armarMenuV2(menu1, root);
            }
        }

    }


    /**
     * Metodo  que sirve para armar el menu.
     */
    private void armarMenuV1(MenuElement elemento, TreeNode nodo) {
        TreeNode ele = new DefaultTreeNode(elemento, nodo);
        if (elemento.isHasChildren()) {
            for (MenuElement item : elemento.getSubmenu()) {
                armarMenuV1(item, ele);
            }
        }
    }

    /**
     * Metodo  que sirve para activar un evento en el que un nodo del
     * menu es seleccionado, refresca la parte del centro de la pagina
     * principal.
     */
    public void onNodeSelectV1(NodeSelectEvent event) {
        MenuElement elemento = (MenuElement) selectedNode.getData();
        navigator = new Navigator();
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
        } else {

            Utileria.mensajeSatisfactorio(Utileria.getUrlSinParametros(elemento.getUrl()));
            navigator.setPagina(Utileria.getUrlSinParametros(elemento.getUrl()));
        }
    }


    /**
     * Metodo  que sirve para armar el menu, pero cuando  se cambie de pagina
     * mantenga el nodo seleccionado.... seleccionado
     */
    private void armarMenuV2(MenuElement elemento, TreeNode nodo) {
        TreeNode ele = new DefaultTreeNode(elemento, nodo);
        if (elemento.isHasChildren()) {
            for (MenuElement item : elemento.getSubmenu()) {
                armarMenuV2(item, ele);
            }
        }
    }

    /**
     * Metodo  que sirve para activar un evento en el que un nodo del
     * menu es seleccionado, cambia de pagina segun la opcion del menu
     * seleccionada.
     */
    public void onNodeSelectV2(NodeSelectEvent event) {
        MenuElement elemento = (MenuElement) selectedNode.getData();
        navigator = new Navigator();
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
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + elemento.getUrl());
            } catch (IOException e) {
                Utileria.logger(getClass()).info(e);
            }
        }
    }

    /*
     <p:ajax event="expand" update=":center-form:mensajes"
     listener="#{MBAplicacion.menuPrincipal.onNodeExpand}" />
     <p:ajax event="collapse"  update=":center-form:mensajes"
     listener="#{MBAplicacion.menuPrincipal.onNodeCollapse}" />
     <p:ajax event="unselect"  update=":center-form:mensajes"
     listener="#{MBAplicacion.menuPrincipal.onNodeUnselect}" />
     public void onNodeExpand(NodeExpandEvent event) {
     Utileria.logger(getClass()).info(event.getTreeNode().toString());
     Utileria.mensajeSatisfactorio(event.getTreeNode().toString());
     }
     public void onNodeCollapse(NodeCollapseEvent event) {
     Utileria.logger(getClass()).info(event.getTreeNode().toString());
     Utileria.mensajeSatisfactorio(event.getTreeNode().toString());
     }
     public void onNodeUnselect(NodeUnselectEvent event) {
     Utileria.logger(getClass()).info(event.getTreeNode().toString());
     Utileria.mensajeSatisfactorio(event.getTreeNode().toString());
     }
     */

    private void print(List<Opcion> opciones) {
        for (Opcion o : opciones) {
            Utileria.logger(getClass()).info(o.getOpcName());
        }

    }


    private List<MenuElement> getMenuElemets(){
        List<MenuElement> items = new ArrayList<>();
        items.add(new MenuElement(1, 0, 1, "Administración",""));
        items.add(new MenuElement(2, 0, 2, "Configuración",""));
        items.add(new MenuElement(3, 0, 3, "Reportes",""));
        items.add(new MenuElement(4, 0, 4, "Mensajes",""));
        items.add(new MenuElement(5, 0, 5, "Visitas",""));
        items.add(new MenuElement(6, 1, 1, "Católogos",""));
        items.add(new MenuElement(7, 1, 2, "Seguridad",""));
        items.add(new MenuElement(8, 1, 3, "Parómetros",""));
        items.add(new MenuElement(9, 1, 4, "Pagina prueba","../prueba.xhtml?parameter1=value1&parameter2=value2"));
        items.add(new MenuElement(10, 1, 5, "Pagina prueba2","../prueba2.xhtml"));
        items.add(new MenuElement(11, 2, 1, "Clientes","") );
        items.add(new MenuElement(12, 2, 2, "Unidad Negocio",""));
        items.add(new MenuElement(13, 2, 3, "Regiones",""));
        items.add(new MenuElement(14, 2, 4, "Equipos",""));
        items.add(new MenuElement(15, 2, 5, "Territorios",""));
        items.add(new MenuElement(16, 2, 6, "Tiendas",""));
        items.add(new MenuElement(17, 2, 7, "Tipos de Mensaje",""));
        items.add(new MenuElement(18, 2, 8, "Tipo de Insidente",""));
        items.add(new MenuElement(19, 2, 9, "Catalogo Opciones",""));
        items.add(new MenuElement(20, 7, 4, "Usuarios","../seguridad/UsuariosCrossMark.xhtml"));
        items.add(new MenuElement(21, 7, 4, "Perfiles",""));
        return items;
    }


    private List<MenuElement> getMenuElemets2(){
        List<MenuElement> items = new ArrayList<>();
        items.add(new MenuElement(1, 0, 1, "Catalogos",""));
        items.add(new MenuElement(2, 1, 2, "Jeraquia Organizacional",""));
        items.add(new MenuElement(3, 1, 3, "Jerarquia Articulos",""));
        items.add(new MenuElement(4, 1, 4, "ABC´s",""));
        items.add(new MenuElement(5, 1, 5, "Configuraciones",""));
        items.add(new MenuElement(6, 2, 1, "Division","CAT_DIVISION"));
        items.add(new MenuElement(7, 2, 2, "Plaza","CAT_PLACE"));
        items.add(new MenuElement(8, 2, 3, "Mercado","CAT_MARKET"));
        items.add(new MenuElement(9, 2, 4, "Campo","CAT_FIELD"));
        items.add(new MenuElement(10, 2, 5, "Tienda","CAT_STORE"));
        items.add(new MenuElement(11, 3, 1, "Departamento","CAT_DEPTO") );
        items.add(new MenuElement(12, 3, 2, "Categoria","CAT_CATEGORY"));
        items.add(new MenuElement(13, 3, 3, "SubCategoria","CAT_SCATEGORY"));
        items.add(new MenuElement(14, 3, 4, "Segmento","CAT_SEGMENT"));
        items.add(new MenuElement(15, 3, 5, "Articulo","CAT_ITEM"));
        items.add(new MenuElement(16, 4, 7, "Usuarios","CAT_USER"));
        items.add(new MenuElement(17, 4, 8, "Roles","CAT_ROLE"));
        items.add(new MenuElement(18, 4, 9, "Actividades","CAT_ACTIVITY"));
        items.add(new MenuElement(19, 4, 4, "Periodos","CAT_PERIOD"));
        items.add(new MenuElement(20, 4, 4, "Mecanicas","CAT_MECANICA"));
        items.add(new MenuElement(21, 4, 9, "Señalamientos","CAT_SENAL"));
        items.add(new MenuElement(22, 4, 4, "Programas","CAT_PROGRAMA"));
        items.add(new MenuElement(23, 4, 4, "Dias Festivos","CAT_HOLYDAY"));
        items.add(new MenuElement(24, 5, 9, "Frecuencia de Notificaciones","CAT_FREC_NOTIF"));
        items.add(new MenuElement(24, 5, 9, "Notificaciones de Correos","CAT_TEM_NOTF"));
        items.add(new MenuElement(26, 5, 4, "Cadena de Autorizacion","catPrueba1"));
        items.add(new MenuElement(27, 2, 6, "Grupo Zona","CAT_G_ZONE"));
        items.add(new MenuElement(28, 2, 7, "Zona","CAT_ZONE"));

        items.add(new MenuElement(29, 1, 5, "Prueba",""));
        items.add(new MenuElement(30, 29, 1, "CAT_CATEGORY","CAT_CATEGORY"));
        items.add(new MenuElement(31, 29, 2, "CAT_PROMOCION","CAT_PROMOCION"));
        items.add(new MenuElement(32, 29, 3, "CAT_SEÑAL","CAT_SEÑAL"));
        items.add(new MenuElement(33, 29, 4, "CAT_PROGRAMA","CAT_PROGRAMA"));
        items.add(new MenuElement(34, 29, 5, "CAT_SUBCATEGORY","CAT_SUBCATEGORY"));
        items.add(new MenuElement(35, 29, 6, "CAT_PROVEEDOR","CAT_PROVEEDOR"));
        items.add(new MenuElement(36, 29, 7, "CAT_GRUPO_ZONAS","CAT_GRUPO_ZONAS"));
        items.add(new MenuElement(37, 29, 8, "CAT_ZONAS","CAT_ZONAS"));
        items.add(new MenuElement(38, 29, 9, "CAT_TIENDAS","CAT_TIENDAS"));
        items.add(new MenuElement(39, 29, 10, "CAT_DESCRIPCION","CAT_DESCRIPCION"));
        items.add(new MenuElement(40, 29, 11, "CAT_LISTA","CAT_LISTA"));
        items.add(new MenuElement(41, 29, 12, "CAT_ESP_PROMO","CAT_ESP_PROMO"));
        items.add(new MenuElement(42, 29, 13, "UPC", "UPC"));
        items.add(new MenuElement(43, 29, 14, "SKU","SKU"));

        return items;
    }

    private List<MenuElement> getMenuElemets(ServiceDynamicCatalogs serviceDynamicCatalogs){
        List<MenuElement> items = new ArrayList<>();
        List<CatalogosOpc> catalogosOpcs = new ArrayList<>();
        MenuElement tmp = null;
        try {
            catalogosOpcs = serviceDynamicCatalogs.getMenuElements();
        } catch (Exception e) {
            Utileria.logger(getClass()).error(e);
        }
        for(CatalogosOpc e : catalogosOpcs){
            tmp = new MenuElement();
            tmp.setNombre(e.getOpcName());
            tmp.setModulosId(e.getOpcId());
            tmp.setModulosPadreId(e.getParentId());
            tmp.setOrden(e.getOrderSort().intValue());
            tmp.setUrl(e.getDescrip());
            items.add(tmp);
        }

        return items;
    }
}

