package com.gaic.services.mdmreferencedata.dao;

import java.util.List;

import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;

public interface IMdmReferenceDataDao
{
	
	ItemDto findItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto );
	
	List<ItemDto> findItemByList( ItemByListSearchParametersDto itemByListSearchParametersDto );
	
	List<ContextListNameDto> findAllLists( AllListsSearchParametersDto allListsSearchDto );
	
	String checkDependencies();
}
