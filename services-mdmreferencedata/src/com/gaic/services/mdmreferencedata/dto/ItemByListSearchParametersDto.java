package com.gaic.services.mdmreferencedata.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemByListSearchParametersDto extends BaseSearchParametersDto
{
	
	private String listName;
	
	private String shortDescription;
	
	private String longDescription;
	
	private String notes;

	public String getListName()
	{
		return listName;
	}

	public void setListName(String listName)
	{
		this.listName = listName;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public String getLongDescription()
	{
		return longDescription;
	}

	public void setLongDescription(String longDescription)
	{
		this.longDescription = longDescription;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

}
