/**
 * 
 */
package com.adinfi.seven.presentation.views.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.persistence.dto.ItemDTO;

/**
 * @author OMAR
 *
 */
public class ItemDTODataModel extends ListDataModel<ItemDTO> implements SelectableDataModel<ItemDTO> {

    public ItemDTODataModel() {
    }

    public ItemDTODataModel(List<ItemDTO> data) {
        super(data);
    }
    
    @Override
    public ItemDTO getRowData(String rowKey) {
        
        List<ItemDTO> items = (List<ItemDTO>) getWrappedData();
        
        for(ItemDTO item : items) {
        	String sku = item.getSku();
            if(sku.equals(rowKey)){
                return item;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(ItemDTO itemDTO) {
        return itemDTO.getSku();
    }
}
