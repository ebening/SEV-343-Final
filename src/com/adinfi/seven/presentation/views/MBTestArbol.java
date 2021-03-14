package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.VerTodas;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.persistence.dto.TreeCampanias;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBTestArbol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceCampana serviceCampana;
	private TreeNode raiz;
	private TreeNode selectedNode;

	@PostConstruct
	public void init() {
		List<CampaniaTreeRegs> listaCampaniaRegs = serviceCampana.todas();
		List<VerTodas> listaVerTodas = serviceCampana.showAllvT();
		TreeCampanias tree = new TreeCampanias(listaCampaniaRegs, listaVerTodas);
		raiz = tree.getRaiz();

	}

	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}

	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	public TreeNode getRaiz() {
		return raiz;
	}

	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void onNodeExpand(NodeExpandEvent event) {
		Messages.mensajeErroneo(event.getTreeNode().toString() + "Expanded");
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		Messages.mensajeErroneo(event.getTreeNode().toString() + "Collapsed");
	}

	public void onNodeSelect(NodeSelectEvent event) {

		if (event.getTreeNode().getData() instanceof CampaniaTreeRegs) {
			CampaniaTreeRegs campania = (CampaniaTreeRegs) event.getTreeNode()
					.getData();
			String msj = "" + campania.getId();
			Messages.mensajeSatisfactorio(msj + " Selected");

		} else if (event.getTreeNode().getData() instanceof VerTodas) {
			VerTodas showAll = (VerTodas) event.getTreeNode().getData();
			String msj = "" + showAll.getAnio();
			Messages.mensajeSatisfactorio(msj + "Selected");

		} else if (event.getTreeNode().getData() instanceof String) {
			String msj = event.getTreeNode().getData().toString();
			Messages.mensajeSatisfactorio(msj + "Selected");

		}

	}

	public void onNodeUnselect(NodeUnselectEvent event) {
		String msj = getSelectedNode().toString() + " "
				+ getSelectedNode().getParent() + " "
				+ getSelectedNode().getParent().getParent();
		Messages.mensajeErroneo(msj + "Unselected");
	}

}
