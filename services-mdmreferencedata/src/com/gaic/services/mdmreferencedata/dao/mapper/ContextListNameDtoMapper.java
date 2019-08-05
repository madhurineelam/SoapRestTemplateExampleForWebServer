package com.gaic.services.mdmreferencedata.dao.mapper;

import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CONTEXT_NAME_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LIST_NAME_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaic.services.mdmreferencedata.dto.ContextListNameDto;

public class ContextListNameDtoMapper implements RowMapper<ContextListNameDto>
{

	@Override
	public ContextListNameDto mapRow(ResultSet resultSet, int rowNumber) throws SQLException
	{
		
		ContextListNameDto contextListNameDto = new ContextListNameDto();
		
		contextListNameDto.setContextName(resultSet.getString(CONTEXT_NAME_COLUMN_NAME));
		contextListNameDto.setListName(resultSet.getString(LIST_NAME_COLUMN_NAME));
				
		return contextListNameDto;
	}

}
