package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import com.adinfi.seven.business.domain.TblArticulosHoja;

public class ArticulosHojaDataModel extends ListDataModel<TblArticulosHoja> 
									implements SelectableDataModel<TblArticulosHoja> {

	public ArticulosHojaDataModel() {}
	
	public ArticulosHojaDataModel(List<TblArticulosHoja> data) {
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TblArticulosHoja getRowData(String rowKey) {
		List<TblArticulosHoja> articulos = (List<TblArticulosHoja>) getWrappedData();
		TblArticulosHoja articulo = null;
		for (TblArticulosHoja element : articulos) {
			String id=String.valueOf(element.getIdArticulosHoja());
			if(id.equals(rowKey)){
				articulo = element;
				break;
			}
		}
		
		return articulo;
	}
	
	@Override
	public Object getRowKey(TblArticulosHoja articulo){
		return articulo.getIdArticulosHoja();
	}

}
