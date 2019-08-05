package com.gaic.services.mdmreferencedata.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gaic.services.core.dto.BaseServicesDto;
@XmlRootElement
public class ItemDtoList extends BaseServicesDto
{
	private List<ItemDto> items;

	public List<ItemDto> getItems()
	{
		if( items == null )
		{
			items = new ArrayList<ItemDto>();
		}
		return items;
	}

	public void setItems(List<ItemDto> items)
	{
		this.items = items;
	}
	
	
}
