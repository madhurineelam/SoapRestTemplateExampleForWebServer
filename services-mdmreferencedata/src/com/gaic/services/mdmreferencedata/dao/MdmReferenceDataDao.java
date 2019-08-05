package com.gaic.services.mdmreferencedata.dao;

import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CHECK_DEPENDENCY_SQL;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CONTEXT_NAME_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.FIND_ALL_LISTS_SQL;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.GAI_ADOPTION_DATE_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.IN_SERVICE_DATE_PARAMETER_NAME;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.gaic.services.mdmreferencedata.dao.mapper.ContextListNameDtoMapper;
import com.gaic.services.mdmreferencedata.dao.mapper.ItemMapper;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;

public class MdmReferenceDataDao extends NamedParameterJdbcTemplate implements IMdmReferenceDataDao
{
	
	@Autowired
	private IBuildSqlStatements buildSqlStatements;
	
	
	public MdmReferenceDataDao(DataSource dataSource)
	{
		super(dataSource);	
	}


	public String checkDependencies()
	{
		queryForObject( CHECK_DEPENDENCY_SQL, new HashMap<String,String>(), Long.class );
		
		
		return("checkDependencies() OK");
	}
	

	@Override
	public ItemDto findItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto )
	
	{
		
		List<ItemDto> items = query( getBuildSqlStatements().buildSQLForFindItemByKey(itemByKeySearchParametersDto), 
                                  getBuildSqlStatements().buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto), 
		                          new ItemMapper() );


		if( items == null || items.size() == 0 )
		{
			return null;
		}
		
		return items.get(0);
	}

	@Override
	public List<ContextListNameDto> findAllLists( AllListsSearchParametersDto allListsSearchDto )
	
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put( GAI_ADOPTION_DATE_PARAMETER_NAME,allListsSearchDto.getProcessDate() );
		parameters.put( IN_SERVICE_DATE_PARAMETER_NAME, allListsSearchDto.getEffectiveDate() );
		parameters.put( CONTEXT_NAME_PARAMETER_NAME, allListsSearchDto.getContextNames() );

		
		return query( FIND_ALL_LISTS_SQL, 
				      parameters, 
		              new ContextListNameDtoMapper() );

	}

	
	public List<ItemDto> findItemByList( ItemByListSearchParametersDto itemByListSearchParametersDto )
	
	{
		ItemByKeySearchParametersDto itemByKeySearchParametersDto = mapItemByListSearchParametersDtoToItemByKeySearchParametersDto( itemByListSearchParametersDto );
		
		return query( getBuildSqlStatements().buildSQLForFindItemByKey(itemByKeySearchParametersDto), 
                      getBuildSqlStatements().buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto), 
      		          new ItemMapper() );

	}
	
	private ItemByKeySearchParametersDto mapItemByListSearchParametersDtoToItemByKeySearchParametersDto(ItemByListSearchParametersDto itemByListSearchParametersDto)
	{

		ItemByKeySearchParametersDto itemByKeySearchParametersDto= new ItemByKeySearchParametersDto();
		
		itemByKeySearchParametersDto.setContextNames(itemByListSearchParametersDto.getContextNames());
		itemByKeySearchParametersDto.setEffectiveDate(itemByListSearchParametersDto.getEffectiveDate());
		itemByKeySearchParametersDto.setListName(itemByListSearchParametersDto.getListName());
		itemByKeySearchParametersDto.setLongDescription(itemByListSearchParametersDto.getLongDescription());
		itemByKeySearchParametersDto.setNotes(itemByListSearchParametersDto.getNotes());
		itemByKeySearchParametersDto.setProcessDate(itemByListSearchParametersDto.getProcessDate());
		itemByKeySearchParametersDto.setShortDescription(itemByListSearchParametersDto.getShortDescription());
		
		return itemByKeySearchParametersDto;
	}


	public IBuildSqlStatements getBuildSqlStatements()
	{
		return buildSqlStatements;
	}

	public void setBuildSqlStatements(IBuildSqlStatements buildSqlStatements)
	{
		this.buildSqlStatements = buildSqlStatements;
	}
}
