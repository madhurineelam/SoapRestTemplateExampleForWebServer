package com.gaic.services.mdmreferencedata.bl.validation;

import java.util.List;

import com.gaic.services.core.validation.BaseServicesValidation;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;

public class MdmReferenceDataValidation extends BaseServicesValidation  implements IMdmReferenceDataValidation
{


	public static final String AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE = "At least one context name is required";
	public static final String LIST_NAME_REQUIRED_VALIDATION_MESSAGE = "List Name required";
	public static final String ITEM_CODE_REQUIRED_VALIDATION_MESSAGE = "Item code required";
	public static final String EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE = "Effective date required";
	public static final String PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE = "Process date required";

	@Override
	public boolean findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto, List<String> validationMessages)
	{
		
		validateDataIsSet(itemByKeySearchParametersDto.getItemCode(), validationMessages, ITEM_CODE_REQUIRED_VALIDATION_MESSAGE);
		findItemByList(  itemByKeySearchParametersDto, validationMessages );
		
		return isValid(validationMessages);
	}

	@Override
	public boolean findItemByList(ItemByListSearchParametersDto itemByListSearchParametersDto, List<String> validationMessages)
	{
		validateDataIsSet(itemByListSearchParametersDto.getProcessDate(), validationMessages, PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE);
		validateDataIsSet(itemByListSearchParametersDto.getEffectiveDate(), validationMessages, EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE);
		validateDataIsSet(itemByListSearchParametersDto.getListName(), validationMessages, LIST_NAME_REQUIRED_VALIDATION_MESSAGE);
		if( !isValidateListOfStrings( itemByListSearchParametersDto.getContextNames() ) )
		{
			validationMessages.add( AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE );
		}
		
		return isValid(validationMessages);
	}
	
	@Override
	public boolean findAllLists( AllListsSearchParametersDto allListsSearchParametersDto, List<String> validationMessages )
	{
		
		validateDataIsSet(allListsSearchParametersDto.getProcessDate(), validationMessages, PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE);
		validateDataIsSet(allListsSearchParametersDto.getEffectiveDate(), validationMessages, EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE);
		if( !isValidateListOfStrings( allListsSearchParametersDto.getContextNames() ) )
		{
			validationMessages.add( AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE );
		}
		
		return isValid(validationMessages);
	}
	
}
