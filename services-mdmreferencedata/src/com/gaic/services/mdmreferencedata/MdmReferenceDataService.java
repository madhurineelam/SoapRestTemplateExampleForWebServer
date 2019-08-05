package com.gaic.services.mdmreferencedata;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.gaic.services.core.exception.CommonUtilException;
import com.gaic.services.core.facade.BaseFacade;
import com.gaic.services.core.spring.RefreshableProperty;
import com.gaic.services.mdmreferencedata.bl.IMdmReferenceDataBl;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;
import com.gaic.services.mdmreferencedata.exception.MdmReferenceDataException;

@WebService(endpointInterface = "com.gaic.services.mdmreferencedata.IMdmReferenceDataService", serviceName = "MdmReferenceDataService")
public class MdmReferenceDataService extends BaseFacade implements IMdmReferenceDataService
{
	@RefreshableProperty
	@Autowired
	private IMdmReferenceDataBl iMdmReferenceDataBl;


	@Override
	public String checkDependencies() throws CommonUtilException
	{
		return iMdmReferenceDataBl.checkDependencies();
	}

	/**
	 * Do not delete this setter!
	 * Although the field is autowired, refresh dependencies requires 
	 * a setter to set the property.
	 */
	public void setiMdmReferenceDataBl(IMdmReferenceDataBl iMdmReferenceDataBl)
	{
		this.iMdmReferenceDataBl = iMdmReferenceDataBl;
	}

	@Override
	public ItemDto findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto)throws MdmReferenceDataException
	{
		return iMdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto);
	}
	
	@Override
	public ItemDtoList findItemsByList(ItemByListSearchParametersDto itemByListSearchParametersDto)throws MdmReferenceDataException
	{
		return iMdmReferenceDataBl.findItemByList( itemByListSearchParametersDto);
	}

	@Override
	public ContextListNameDtoList findAllLists(AllListsSearchParametersDto allListsSearchParametersDto ) throws  MdmReferenceDataException
	{
		return iMdmReferenceDataBl.findAllLists( allListsSearchParametersDto );
	}

}
