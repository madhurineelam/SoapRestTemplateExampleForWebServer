package com.gaic.services.mdmreferencedata;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaic.services.core.controller.IBaseController;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;
import com.gaic.services.mdmreferencedata.exception.MdmReferenceDataException;

public interface IMdmReferenceDataController extends IBaseController
{
	@RequestMapping(value = "/finditembykey", method = {RequestMethod.POST} )
	ItemDto findItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto) throws MdmReferenceDataException;

	@RequestMapping(value = "/finditemsbylist", method = {RequestMethod.POST})
	ItemDtoList findItemsByList( ItemByListSearchParametersDto itemByListSearchParametersDto) throws MdmReferenceDataException;
	
	@RequestMapping(value = "/findalllists", method = {RequestMethod.POST})
	ContextListNameDtoList findAllLists( AllListsSearchParametersDto allListsSearchParametersDto)throws MdmReferenceDataException;

}