package com.gaic.services.mdmreferencedata.dao;



import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CONTEXT_NAME_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CONTEXT_NAME_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.FIND_ITEM_BY_KEY_SQL;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.GAI_ADOPTION_DATE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.GAI_ADOPTION_DATE_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.IN_SERVICE_DATE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.IN_SERVICE_DATE_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LIST_NAME_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LIST_NAME_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LONG_DESCRIPTION_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LONG_DESCRIPTION_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.NOTES_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.NOTES_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.OUT_OF_SERVICE_DATE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.SHORT_DESCRIPTION_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.SHORT_DESCRIPTION_PARAMETER_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.SUBJECT_ID_COLUMN_NAME;

import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.ITEM_CODE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.ITEM_CODE_PARAMETER_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gaic.services.core.dao.AbstractBuildSqlStatements;
import com.gaic.services.core.dto.WhereParameterDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.util.SQL_OPERATION;


public class BuildSqlStatements extends AbstractBuildSqlStatements implements IBuildSqlStatements
{
	public final static boolean START_WHERE_CLAUSE_WITH_AND = true;
	public final static boolean DO_NOT_START_WHERE_CLAUSE_WITH_AND = false;
	public final static String WHERE = " where ";
	
	@Override
	public String buildSQLForFindItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto )
	{
		String whereClause = buildWhereClauseColumnNamesForFindItemByKey(itemByKeySearchParametersDto);
		StringBuilder sql = new StringBuilder();

		sql.append( FIND_ITEM_BY_KEY_SQL);
		
		if( StringUtils.isNotEmpty( whereClause ) )
		{

			sql.append( WHERE );
			sql.append( whereClause );
		}
		
		sql.append( " order by " );
		sql.append( SUBJECT_ID_COLUMN_NAME );
		
		return sql.toString();
		
	}
	
	@Override
	public Map<String, Object > buildReplaceableParameterMapForFindItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto )
	{
		Map<String, Object > parameters = new HashMap<String,Object>();
		
		
		if( filterOnListOfStrings( itemByKeySearchParametersDto.getContextNames() ) )
		{
			parameters.put( CONTEXT_NAME_PARAMETER_NAME, itemByKeySearchParametersDto.getContextNames() );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getEffectiveDate() ) )
		{
			parameters.put( GAI_ADOPTION_DATE_PARAMETER_NAME, itemByKeySearchParametersDto.getEffectiveDate() );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getProcessDate() ) )
		{
			parameters.put( IN_SERVICE_DATE_PARAMETER_NAME, itemByKeySearchParametersDto.getProcessDate() );
		}

		if( filterOn( itemByKeySearchParametersDto.getListName() ) )
		{
			parameters.put( LIST_NAME_PARAMETER_NAME, itemByKeySearchParametersDto.getListName() );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getLongDescription() ) )
		{
			parameters.put( LONG_DESCRIPTION_PARAMETER_NAME, itemByKeySearchParametersDto.getLongDescription() );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getNotes() ) )
		{
			parameters.put( NOTES_PARAMETER_NAME, itemByKeySearchParametersDto.getNotes() );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getShortDescription() ) )
		{
			parameters.put( SHORT_DESCRIPTION_PARAMETER_NAME, itemByKeySearchParametersDto.getShortDescription() );
		}
		
		
		if( filterOn( itemByKeySearchParametersDto.getItemCode() ) )
		{
			parameters.put( ITEM_CODE_PARAMETER_NAME, itemByKeySearchParametersDto.getItemCode() );
		}

		return parameters;
	}
	
	private String buildWhereClauseColumnNamesForFindItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto )
	{
		List<WhereParameterDto> whereParameterDtos = new ArrayList<WhereParameterDto>();
		
		if( filterOnListOfStrings( itemByKeySearchParametersDto.getContextNames() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( CONTEXT_NAME_COLUMN_NAME, CONTEXT_NAME_PARAMETER_NAME, SQL_OPERATION.IN ) );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getEffectiveDate() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( GAI_ADOPTION_DATE_COLUMN_NAME, GAI_ADOPTION_DATE_PARAMETER_NAME, SQL_OPERATION.LESS_THAN_OR_EQUAL_TO ) );
			whereParameterDtos.add( new WhereParameterDto( OUT_OF_SERVICE_DATE_COLUMN_NAME, GAI_ADOPTION_DATE_PARAMETER_NAME, SQL_OPERATION.GREATER_THAN_OR_EQUAL_TO ) );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getProcessDate() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( IN_SERVICE_DATE_COLUMN_NAME, IN_SERVICE_DATE_PARAMETER_NAME, SQL_OPERATION.LESS_THAN_OR_EQUAL_TO ) );
			whereParameterDtos.add( new WhereParameterDto( OUT_OF_SERVICE_DATE_COLUMN_NAME, IN_SERVICE_DATE_PARAMETER_NAME, SQL_OPERATION.GREATER_THAN_OR_EQUAL_TO ) );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getListName() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( LIST_NAME_COLUMN_NAME, LIST_NAME_PARAMETER_NAME, SQL_OPERATION.EQUALS ) );
		}

		if( filterOn( itemByKeySearchParametersDto.getLongDescription() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( LONG_DESCRIPTION_COLUMN_NAME, LONG_DESCRIPTION_PARAMETER_NAME, SQL_OPERATION.EQUALS) );
		}
		
		if( filterOn( itemByKeySearchParametersDto.getNotes() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( NOTES_COLUMN_NAME, NOTES_PARAMETER_NAME, SQL_OPERATION.EQUALS) );
		}


		if( filterOn( itemByKeySearchParametersDto.getShortDescription() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( SHORT_DESCRIPTION_COLUMN_NAME, SHORT_DESCRIPTION_PARAMETER_NAME, SQL_OPERATION.EQUALS) );
		}

		if( filterOn( itemByKeySearchParametersDto.getItemCode() ) )
		{
			whereParameterDtos.add( new WhereParameterDto( ITEM_CODE_COLUMN_NAME, ITEM_CODE_PARAMETER_NAME, SQL_OPERATION.EQUALS) );
		}
		
		
		return buildWhereClauseForAnd( whereParameterDtos, DO_NOT_START_WHERE_CLAUSE_WITH_AND ) ;
	}

}
