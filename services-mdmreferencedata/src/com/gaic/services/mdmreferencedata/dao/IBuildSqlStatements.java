package com.gaic.services.mdmreferencedata.dao;

import java.util.Map;

import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;

public interface IBuildSqlStatements
{
	String buildSQLForFindItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto );
	
	Map<String, Object > buildReplaceableParameterMapForFindItemByKey( ItemByKeySearchParametersDto itemByKeySearchParametersDto );
}
