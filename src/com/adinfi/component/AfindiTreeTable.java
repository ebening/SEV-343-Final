/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.component;

import com.adinfi.seven.presentation.views.MBArquitectura;
import org.primefaces.component.api.UITree;
import javax.faces.context.FacesContext;
import javax.faces.component.UINamingContainer;
import javax.el.ValueExpression;
import javax.el.MethodExpression;
import javax.faces.render.Renderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import java.util.List;
import java.util.ArrayList;
import org.primefaces.model.TreeTableModel;
import org.primefaces.model.TreeNode;
import javax.faces.model.DataModel;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import java.util.Collection;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Iterator;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.component.column.Column;
import java.lang.StringBuilder;
import javax.faces.component.UIColumn;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import org.apache.log4j.Logger;
import org.primefaces.component.treetable.TreeTable;


public class AfindiTreeTable extends TreeTable implements org.primefaces.component.api.Widget,javax.faces.component.behavior.ClientBehaviorHolder {

	private static final String DEFAULT_RENDERER = "org.primefaces.component.TreeTableRenderer";
    private static final transient Logger LOG = Logger.getLogger(AfindiTreeTable.class);


	public AfindiTreeTable() {
		super.setRendererType(DEFAULT_RENDERER);
	}
    
    @Override
    protected boolean visitFacets(VisitContext context, VisitCallback callback) {
        if(getFacetCount() > 0) {
            for(UIComponent facet : getFacets().values()) {
                if(facet.visitTree(context, callback))
                    return true;
            }
        }

        return false;
    }
    
    @Override
    public boolean visitTree(VisitContext context, VisitCallback callback) {
        if(!isVisitable(context))
            return false;

        FacesContext facesContext = context.getFacesContext();

        String oldRowKey= getRowKey();
        setRowKey(null);
        
        pushComponentToEL(facesContext, null);

        try {
            VisitResult result = context.invokeVisitCallback(this, callback);

            if(result == VisitResult.COMPLETE)
                return true;

            if((result == VisitResult.ACCEPT) && doVisitChildren(context)) {
                if(visitFacets(context, callback))
                    return true;

                if(visitNodes(context, callback))
                    return true;
            }
        }
        finally {
            popComponentFromEL(facesContext);
            setRowKey(oldRowKey);
        }

        return false;
    }
    
    @Override
    protected boolean visitNodes(VisitContext context, VisitCallback callback) {
        TreeNode root = getValue();
        if(root != null) {
            if(visitNode(context, callback, root, null)) {
                return true;
            }
		}

        setRowKey(null);
        
        return false;
    }
    
    @Override
    protected boolean visitNode(VisitContext context, VisitCallback callback, TreeNode treeNode, String rowKey) {
        if(visitColumns(context, callback, rowKey)) {
            return true;
        }
        
        //visit child nodes if node is expanded or node itself is the root
        if((treeNode.isExpanded() || treeNode.getParent() == null)) {
            int childIndex = 0;
            for(Iterator<TreeNode> iterator = treeNode.getChildren().iterator(); iterator.hasNext();) {
                String childRowKey = rowKey == null ? String.valueOf(childIndex) : rowKey + SEPARATOR + childIndex;

                if(visitNode(context, callback, iterator.next(), childRowKey)) {
                    return true;
                }

                childIndex++;
            }
        }
        
        return false;
    }
    
    private boolean visitColumns(VisitContext context, VisitCallback callback, String rowKey) {
        setRowKey(rowKey);
        
        if(rowKey == null)
            return false;
        
        if(getChildCount() > 0) {
            for(UIComponent child : getChildren()) {
                if(child instanceof UIColumn) {
                    if(child.visitTree(context, callback)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}