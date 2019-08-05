package com.gaic.services.mdmreferencedata.bl.validation;

import java.util.List;

import com.gaic.services.core.validation.IBaseServicesValidation;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;

public interface IMdmReferenceDataValidation extends IBaseServicesValidation
{

	boolean findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto, List<String> validationMessages);
	
	boolean findItemByList(ItemByListSearchParametersDto itemByListSearchParametersDto, List<String> validationMessages);
	
	boolean findAllLists( AllListsSearchParametersDto allListsSearchParametersDto, List<String> validationMessages );
	
}