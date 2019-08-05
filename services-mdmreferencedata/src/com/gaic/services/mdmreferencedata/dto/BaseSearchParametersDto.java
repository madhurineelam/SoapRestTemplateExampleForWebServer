package com.gaic.services.mdmreferencedata.dto;

import java.util.Date;
import java.util.List;

public class BaseSearchParametersDto
{
	private List<String> contextNames;
	
	private Date effectiveDate;
	
	private Date processDate;

	public List<String> getContextNames()
	{
		return contextNames;
	}

	public void setContextNames(List<String> contextNames)
	{
		this.contextNames = contextNames;
	}

	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public Date getProcessDate()
	{
		return processDate;
	}

	public void setProcessDate(Date processDate)
	{
		this.processDate = processDate;
	}

}
