package com.gaic.services.mdmreferencedata;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.gaic.services.core.facade.IBaseFacade;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;
import com.gaic.services.mdmreferencedata.exception.MdmReferenceDataException;
@WebService(name = "MdmReferenceDataService")
public interface IMdmReferenceDataService extends IBaseFacade
{
	
	@WebMethod
	ItemDto findItemByKey( @WebParam(name="itemByKeySearchParametersDto") ItemByKeySearchParametersDto itemByKeySearchParametersDto ) throws MdmReferenceDataException;
	                                       
	@WebMethod
	ItemDtoList findItemsByList(@WebParam(name="itemByListSearchParam")ItemByListSearchParametersDto itemByListSearchParametersDto)throws MdmReferenceDataException;
	
	@WebMethod
	ContextListNameDtoList findAllLists( @WebParam(name="allListsSearchParam") AllListsSearchParametersDto allListsSearchParametersDto ) throws  MdmReferenceDataException;
}
