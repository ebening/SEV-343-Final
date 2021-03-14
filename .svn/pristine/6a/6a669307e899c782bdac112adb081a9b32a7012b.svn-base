package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.Componente;
import com.adinfi.seven.business.domain.TblComponente;

public interface DAOTblComponente extends AbstractDao<TblComponente> {
	List<TblComponente> getByMecanicaId(int mecanicaId) throws GeneralException;
	List<TblComponente> getPreciosPromocionComponentes(int mecanicaId,
			Integer categoriaId, Integer subCategoriaId, Integer descripcionId,
			Integer componenteId, Integer listaId, List<String> skuId,
			List<String> upcId) throws GeneralException;
	List<TblComponente> getPreciosPromocionComponentes(Integer categoriaId,
			Integer programaId, Integer mecanicaId, List<Integer> grupoZona,
			List<Integer> zona);
	List<Componente> getComponentesFromLista(int idLista);
	boolean updateHoja(int pkcomp, int hoja);
    boolean updateComponentNumber(int componentId, int numeroComponente);
	boolean checkPrecioExistsByMecanica (int idMecanica);
	public void save(List<TblComponente> list);
}
