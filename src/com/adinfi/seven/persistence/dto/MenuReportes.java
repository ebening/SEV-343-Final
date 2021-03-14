package com.adinfi.seven.persistence.dto;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.adinfi.seven.presentation.views.util.Constants;

public class MenuReportes {
	private TreeNode raiz;

	public MenuReportes() {
		raiz = new DefaultTreeNode("Root", null);
		TreeNode reportes = new DefaultTreeNode(Constants.REPORTES, raiz);
		TreeNode sinFotoDesc = new DefaultTreeNode(
				Constants.REPORTES_SIN_FOTO_DESCRIPCION, reportes);
		TreeNode reporteArticulo = new DefaultTreeNode(
				Constants.REPORTE_DE_ARTICULOS, reportes);
		TreeNode reporteVentas = new DefaultTreeNode(Constants.REPORTE_DE_VENTAS,
				reportes);
		TreeNode reporteInventario = new DefaultTreeNode(Constants.REPORTE_DE_INVENTARIO,
				reportes);

	}

	public TreeNode getRaiz() {
		return raiz;
	}

}
