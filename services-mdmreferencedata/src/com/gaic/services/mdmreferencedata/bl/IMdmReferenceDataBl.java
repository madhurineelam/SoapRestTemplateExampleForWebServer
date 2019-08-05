package com.gaic.services.mdmreferencedata.bl;

import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;

public interface IMdmReferenceDataBl
{
	
	String checkDependencies();
	
	ItemDto findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto);
	
	ItemDtoList findItemByList(ItemByListSearchParametersDto itemByListSearchParametersDto);
	
	ContextListNameDtoList findAllLists(AllListsSearchParametersDto allListsSearchParametersDto ); 
}
