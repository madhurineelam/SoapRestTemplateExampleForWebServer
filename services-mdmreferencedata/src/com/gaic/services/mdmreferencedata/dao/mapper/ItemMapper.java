package com.gaic.services.mdmreferencedata.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gaic.services.mdmreferencedata.dto.ItemDto;
import static  com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.*;
public class ItemMapper implements RowMapper<ItemDto>
{

	@Override
	public ItemDto mapRow(ResultSet resultSet, int rowNumber ) throws SQLException
	{
		
		ItemDto item = new ItemDto();
		
		item.setSubjectId( resultSet.getString( SUBJECT_ID_COLUMN_NAME ));
		item.setContextName(resultSet.getString(CONTEXT_NAME_COLUMN_NAME));
		item.setGaiAdoptionDate(resultSet.getDate(GAI_ADOPTION_DATE_COLUMN_NAME));
		item.setInServiceDate(resultSet.getDate(IN_SERVICE_DATE_COLUMN_NAME));
		item.setItemCodeAsMasterDataKey(resultSet.getString(ITEM_CODE_COLUMN_NAME));
		item.setListName(resultSet.getString(LIST_NAME_COLUMN_NAME));
		item.setLongDescription(resultSet.getString(LONG_DESCRIPTION_COLUMN_NAME));
		item.setNotes(resultSet.getString(NOTES_COLUMN_NAME));
		item.setOutOfServiceDate(resultSet.getDate(OUT_OF_SERVICE_DATE_COLUMN_NAME));
		item.setShortDescription(resultSet.getString(SHORT_DESCRIPTION_COLUMN_NAME));
		
		return item;
	}

}
