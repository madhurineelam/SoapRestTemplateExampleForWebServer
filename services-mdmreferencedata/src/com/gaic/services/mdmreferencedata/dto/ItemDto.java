package com.gaic.services.mdmreferencedata.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.gaic.services.core.dto.BaseServicesDto;
@XmlRootElement
public class ItemDto extends BaseServicesDto 
{
	private String subjectId;
	
	private String contextName;
	
	private String listName;
	
	private String itemCodeAsMasterDataKey;
	
	private String shortDescription;
	
	private String longDescription;
	
	private Date gaiAdoptionDate;
	
	private Date inServiceDate;
	
	private Date outOfServiceDate;
	
	private String notes;

	public String getContextName()
	{
		return contextName;
	}

	public void setContextName(String context)
	{
		this.contextName = context;
	}

	public String getListName()
	{
		return listName;
	}

	public void setListName(String listName)
	{
		this.listName = listName;
	}

	public String getItemCodeAsMasterDataKey()
	{
		return itemCodeAsMasterDataKey;
	}

	public void setItemCodeAsMasterDataKey(String itemCodeAsMasterDataKey)
	{
		this.itemCodeAsMasterDataKey = itemCodeAsMasterDataKey;
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

	public Date getGaiAdoptionDate()
	{
		return gaiAdoptionDate;
	}

	public void setGaiAdoptionDate(Date gaiAdoptionDate)
	{
		this.gaiAdoptionDate = gaiAdoptionDate;
	}

	public Date getInServiceDate()
	{
		return inServiceDate;
	}

	public void setInServiceDate(Date inServiceDate)
	{
		this.inServiceDate = inServiceDate;
	}

	public Date getOutOfServiceDate()
	{
		return outOfServiceDate;
	}

	public void setOutOfServiceDate(Date outOfServiceDate)
	{
		this.outOfServiceDate = outOfServiceDate;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}
	
	
	
}
