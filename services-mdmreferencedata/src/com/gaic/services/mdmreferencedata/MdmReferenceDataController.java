package com.gaic.services.mdmreferencedata;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaic.services.core.controller.BaseController;
import com.gaic.services.core.exception.CommonUtilException;
import com.gaic.services.core.spring.RefreshableProperty;
import com.gaic.services.mdmreferencedata.bl.IMdmReferenceDataBl;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;
import com.gaic.services.mdmreferencedata.exception.MdmReferenceDataException;

@RestController
public class MdmReferenceDataController extends BaseController implements IMdmReferenceDataController
{
	@RefreshableProperty
	@Autowired
	private IMdmReferenceDataBl iMdmReferenceDataBl;

	static Logger _log = Logger.getLogger(MdmReferenceDataController.class);
	
	public String checkDependencies() throws CommonUtilException
	{
			return iMdmReferenceDataBl.checkDependencies();
	}
	
	/**
	 * Do not delete this setter!
	 * Although the field is autowired, refresh dependencies requires 
	 * a setter to set the property.
	 */
	public void setiMdmReferenceDataBl(@RequestParam IMdmReferenceDataBl iMdmReferenceDataBl)
	{
		this.iMdmReferenceDataBl = iMdmReferenceDataBl;
	}
//{"listName":"External Report Category", "itemCode":"BDEHAAN"}
	//<ItemByKeySearchParametersDto><listName> External Report Category</listName><itemCode>BDEHAAN</itemCode></ItemByKeySearchParametersDto>
	/* (non-Javadoc)
	 * @see com.gaic.services.mdmreferencedata.IMdmReferenceDataController#findItemByKey(com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto)
	 */
	@Override
	public ItemDto findItemByKey(@RequestBody ItemByKeySearchParametersDto itemByKeySearchParametersDto)throws MdmReferenceDataException
	{
		System.out.println( _log.getLevel() );
		return iMdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto);
	}
	
	/* (non-Javadoc)
	 * @see com.gaic.services.mdmreferencedata.IMdmReferenceDataController#findItemByList(com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto)
	 */
	@Override
	public ItemDtoList findItemsByList(@RequestBody ItemByListSearchParametersDto itemByListSearchParametersDto)throws MdmReferenceDataException
	{
		return iMdmReferenceDataBl.findItemByList( itemByListSearchParametersDto);
	}

	/* (non-Javadoc)
	 * @see com.gaic.services.mdmreferencedata.IMdmReferenceDataController#findAllLists(com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto)
	 */
	@Override
	public ContextListNameDtoList findAllLists(@RequestBody AllListsSearchParametersDto allListsSearchParametersDto )throws MdmReferenceDataException
	{
		return iMdmReferenceDataBl.findAllLists( allListsSearchParametersDto );
	}

	

	
}
