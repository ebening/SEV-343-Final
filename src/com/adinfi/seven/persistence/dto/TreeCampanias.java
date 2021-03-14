package com.adinfi.seven.persistence.dto;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.VerTodas;

/**
 * 
 * @author christian
 */
public class TreeCampanias {

	private String year = "";
	private String type = "";
	private final TreeNode raiz;
	private TreeNode yearNode;
	private TreeNode typeNode;
	@SuppressWarnings("unused")
	private TreeNode nomCampania;
	private TreeNode showAllNode;
	private final List<CampaniaTreeRegs> listaCampaniaRegs;
	private CampaniaTreeRegs campaniaObject;
	private VerTodas showAllObject;
	private final List<VerTodas> listaVerTodas;
	private boolean generalFlag = true;

	public TreeCampanias(List<CampaniaTreeRegs> listaCampaniaRegs, List<VerTodas> listaVerTodas) {
		campaniaObject = null;
		showAllObject = null;
		this.listaCampaniaRegs = listaCampaniaRegs;
		this.listaVerTodas = listaVerTodas;
		raiz = new DefaultTreeNode("raiz", null);
		yearNode = null;
		typeNode = null;
		nomCampania = null;
		showAllNode = null;
		//showAll();
		year = "";
		generalFlag = true;
		categorizar();
	}

	private void showAll() {
		for (VerTodas verTodas : listaVerTodas) {
			if (generalFlag) {
				type = verTodas.getDescripcion();
				year = String.valueOf(verTodas.getAnio());
				showAllObject = verTodas;
				showAllNode = new DefaultTreeNode("Ver Todos", raiz);
				yearNode = new DefaultTreeNode(showAllObject, showAllNode);
				generalFlag = false;
			} else if (type.equals(verTodas.getDescripcion()) == true) {
				year = String.valueOf(verTodas.getAnio());
				showAllObject = verTodas;
				yearNode = new DefaultTreeNode(showAllObject, showAllNode);
			}
		}
	}

	private void categorizar() {
		for (CampaniaTreeRegs campania : listaCampaniaRegs) {
			if (generalFlag == true) {
				type = campania.getTipoCampania();
				year = String.valueOf(campania.getAnio());
				campaniaObject = campania;
				typeNode = new DefaultTreeNode(type, raiz);
				yearNode = new DefaultTreeNode(year, typeNode);
				nomCampania = new DefaultTreeNode(campaniaObject, yearNode);
				generalFlag = false;
			} else if (type.equals(campania.getTipoCampania()) == true && year.equals(String.valueOf(campania.getAnio())) == true) {
				campaniaObject = campania;
				nomCampania = new DefaultTreeNode(campaniaObject, yearNode);
			}

			if (type.equals(campania.getTipoCampania()) == false) {
				type = campania.getTipoCampania();
				year = String.valueOf(campania.getAnio());
				typeNode = new DefaultTreeNode(type, raiz);
				yearNode = new DefaultTreeNode(year, typeNode);
				campaniaObject = campania;
				nomCampania = new DefaultTreeNode(campaniaObject, yearNode);
			}

			if (year.equals(String.valueOf(campania.getAnio())) == false) {
				year = String.valueOf(campania.getAnio());
				yearNode = new DefaultTreeNode(year, typeNode);
				campaniaObject = campania;
				nomCampania = new DefaultTreeNode(campaniaObject, yearNode);
			}
		}
	}

	public TreeNode getRaiz() {
		return raiz;
	}

}
