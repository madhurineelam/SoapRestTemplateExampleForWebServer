package com.gaic.services.mdmreferencedata.bl.defaultvalues;

import java.util.ArrayList;
import java.util.List;

import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.BaseSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.util.DateBuilder;

public class MdmReferenceDataDefaultValues implements IMdmReferenceDataDefaultValues
{
	public static final String ENT_CONTEXT_NAME_DEFAULT_VALUE = "ENT";
	public static final String MASTER_CONTEXT_NAME_DEFAULT_VALUE = "MASTER";

	/* (non-Javadoc)
	 * @see com.gaic.services.mdmreferencedata.bl.defaultvalues.IMdmReferenceDataDefaultValues#findItemByKey(com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto)
	 */
	@Override
	public ItemByKeySearchParametersDto findItemByKey(ItemByKeySearchParametersDto itemByKeySearchParametersDto)
	{
		if( itemByKeySearchParametersDto == null )
		{
			itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
		}
		
		baseDefaultValues( itemByKeySearchParametersDto );
		
		return itemByKeySearchParametersDto;
	}
	
	
	@Override
	public ItemByListSearchParametersDto findItemByList(ItemByListSearchParametersDto itemByListSearchParametersDto)
	{
		
		if( itemByListSearchParametersDto == null )
		{
			itemByListSearchParametersDto = new ItemByListSearchParametersDto();
		}
	
		baseDefaultValues( itemByListSearchParametersDto );
		
		return itemByListSearchParametersDto;
	}
	
	
	@Override
	public AllListsSearchParametersDto findAllLists( AllListsSearchParametersDto allListsSearchParametersDto )
	{
		
		if( allListsSearchParametersDto == null )
		{
			allListsSearchParametersDto= new AllListsSearchParametersDto();
		}
		
		baseDefaultValues( allListsSearchParametersDto);
		
		return allListsSearchParametersDto;
	}
	
	
	private void baseDefaultValues( BaseSearchParametersDto baseSearchParametersDto)
	{
		if( baseSearchParametersDto != null )
		{
			if( baseSearchParametersDto.getEffectiveDate() == null )
			{
				baseSearchParametersDto.setEffectiveDate( DateBuilder.getCurrentDateAndSetTimeToMidnight() );
			}
			
			if( baseSearchParametersDto.getProcessDate() == null )
			{
				baseSearchParametersDto.setProcessDate( DateBuilder.getCurrentDateAndSetTimeToMidnight() );
			}
			
			if( baseSearchParametersDto.getContextNames() == null || baseSearchParametersDto.getContextNames().size() == 0)
			{
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( MASTER_CONTEXT_NAME_DEFAULT_VALUE );
				contextNames.add( ENT_CONTEXT_NAME_DEFAULT_VALUE );
				
				baseSearchParametersDto.setContextNames(contextNames);
			}
		}

	}
	
}
