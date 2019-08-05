package com.gaic.services.mdmreferencedata.bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gaic.services.mdmreferencedata.bl.defaultvalues.IMdmReferenceDataDefaultValues;
import com.gaic.services.mdmreferencedata.bl.validation.IMdmReferenceDataValidation;
import com.gaic.services.mdmreferencedata.dao.IMdmReferenceDataDao;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;

public class MdmReferenceDataBl implements IMdmReferenceDataBl
{
	@Autowired
	protected IMdmReferenceDataDao iMdmReferenceDataDao; 
	
	@Autowired
	protected IMdmReferenceDataValidation iMdmReferenceDataValidation;
	
	@Autowired
	protected IMdmReferenceDataDefaultValues mdmReferenceDataDefaultValues;
	
	@Override
	public String checkDependencies()
	{
		return iMdmReferenceDataDao.checkDependencies();
	}
	
	
	@Override
	public ItemDto findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto) 
	{
		itemByKeySearchParametersDto = mdmReferenceDataDefaultValues.findItemByKey(itemByKeySearchParametersDto);
		
		List<String> validationMessages = new ArrayList<String>();
		ItemDto item = new ItemDto();
		
		if( iMdmReferenceDataValidation.findItemByKey( itemByKeySearchParametersDto, validationMessages ) )
		{
			return iMdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
		}
		
		iMdmReferenceDataValidation.setValidationMessages(item, validationMessages);
		
		return item;
	}

	
	
	@Override
	public ItemDtoList findItemByList(ItemByListSearchParametersDto itemByListSearchParametersDto) 
	{
		itemByListSearchParametersDto = mdmReferenceDataDefaultValues.findItemByList(itemByListSearchParametersDto);
		
		List<String> validationMessages = new ArrayList<String>();
		ItemDtoList itemList = new ItemDtoList();
		
		if( iMdmReferenceDataValidation.findItemByList( itemByListSearchParametersDto, validationMessages ) )
		{
			List<ItemDto> items =  iMdmReferenceDataDao.findItemByList(itemByListSearchParametersDto);
			itemList.setItems(items);
			
			return itemList;
		}
		
		iMdmReferenceDataValidation.setValidationMessages(itemList, validationMessages);
		
		return itemList;
	}
	
	
	@Override
	public ContextListNameDtoList findAllLists(AllListsSearchParametersDto allListsSearchParametersDto ) 
	{
		allListsSearchParametersDto = mdmReferenceDataDefaultValues.findAllLists(allListsSearchParametersDto);
		
		List<String> validationMessages = new ArrayList<String>();
		ContextListNameDtoList contextListNameDtoList = new ContextListNameDtoList();
		
		if( iMdmReferenceDataValidation.findAllLists(allListsSearchParametersDto, validationMessages) )
		{
			List<ContextListNameDto> contextListNameDtos =  iMdmReferenceDataDao.findAllLists(allListsSearchParametersDto);
			contextListNameDtoList.setContextListNameDtos(contextListNameDtos);
			
			return contextListNameDtoList;
		}
		
		iMdmReferenceDataValidation.setValidationMessages(contextListNameDtoList, validationMessages);
		
		return contextListNameDtoList;
	}	
}
