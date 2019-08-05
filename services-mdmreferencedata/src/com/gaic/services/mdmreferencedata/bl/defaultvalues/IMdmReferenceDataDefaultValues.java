package com.gaic.services.mdmreferencedata.bl.defaultvalues;

import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;

public interface IMdmReferenceDataDefaultValues
{

	ItemByKeySearchParametersDto findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto);

	ItemByListSearchParametersDto findItemByList(ItemByListSearchParametersDto itemByListSearchParametersDto);
	
	AllListsSearchParametersDto findAllLists( AllListsSearchParametersDto allListsSearchParametersDto );
}