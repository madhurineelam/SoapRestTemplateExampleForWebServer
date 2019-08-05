package com.gaic.services.mdmreferencedata.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gaic.services.core.dto.BaseServicesDto;

@XmlRootElement
public class ContextListNameDtoList extends BaseServicesDto
{
	private List<ContextListNameDto> contextListNameDtos;

	public List<ContextListNameDto> getContextListNameDtos()
	{
		if( contextListNameDtos== null )
		{
			contextListNameDtos = new ArrayList<ContextListNameDto>();
		}
		return contextListNameDtos;
	}

	public void setContextListNameDtos(List<ContextListNameDto> contextListNameDtos)
	{
		this.contextListNameDtos = contextListNameDtos;
	}
	
	
}
