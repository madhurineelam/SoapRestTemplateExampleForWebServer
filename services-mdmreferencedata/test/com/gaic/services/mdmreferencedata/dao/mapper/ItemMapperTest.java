package com.gaic.services.mdmreferencedata.dao.mapper;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CONTEXT_NAME_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.GAI_ADOPTION_DATE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.IN_SERVICE_DATE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.ITEM_CODE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LIST_NAME_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LONG_DESCRIPTION_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.NOTES_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.OUT_OF_SERVICE_DATE_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.SHORT_DESCRIPTION_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.SUBJECT_ID_COLUMN_NAME;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;


import org.junit.Test;

import com.gaic.services.UseCaseTestHelper;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.util.DateBuilder;

import static junit.framework.TestCase.assertEquals;

public class ItemMapperTest
{

	
	
	@Test
	public void mapRow()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ResultSet mockResultSet;
			private String subjectId;
			private String contextName;
			private Date gaiAdoptionDate;
			private Date inServiceDate;
			private String itemCode;
			private String listName;
			private String longDescrption;
			private String note;
			private Date outOfServiceDate;
			private String shortDescription;
			
			private ItemMapper itemMapper;
			private ItemDto returnedItem;
			
			@Override
			public void createTestData() throws Exception
			{
				itemMapper = new ItemMapper();
				
				subjectId = "1";
				contextName = "2";
				gaiAdoptionDate = new java.sql.Date( DateBuilder.getCurrentDateAndSetTimeToMidnight().getTime() );
				inServiceDate =  new java.sql.Date( DateBuilder.getCurrentDateAndSetTimeToMidnight().getTime() );
				itemCode = "3";
				listName = "4";
				longDescrption = "5";
				note = "6";
				outOfServiceDate =  new java.sql.Date( DateBuilder.getCurrentDateAndSetTimeToMidnight().getTime() );
				shortDescription = "7";

				
				
				mockResultSet = mock( ResultSet.class );
				
				when( mockResultSet.getString( SUBJECT_ID_COLUMN_NAME )).thenReturn(subjectId);
				when( mockResultSet.getString(CONTEXT_NAME_COLUMN_NAME)).thenReturn(contextName);
				when( mockResultSet.getDate(GAI_ADOPTION_DATE_COLUMN_NAME)).thenReturn(gaiAdoptionDate);
				when( mockResultSet.getDate(IN_SERVICE_DATE_COLUMN_NAME)).thenReturn(inServiceDate);
				when( mockResultSet.getString(ITEM_CODE_COLUMN_NAME)).thenReturn(itemCode);
				when( mockResultSet.getString(LIST_NAME_COLUMN_NAME)).thenReturn(listName);
				when( mockResultSet.getString(LONG_DESCRIPTION_COLUMN_NAME)).thenReturn(longDescrption);
				when( mockResultSet.getString(NOTES_COLUMN_NAME)).thenReturn(note);
				when( mockResultSet.getDate(OUT_OF_SERVICE_DATE_COLUMN_NAME)).thenReturn(outOfServiceDate);
				when( mockResultSet.getString(SHORT_DESCRIPTION_COLUMN_NAME)).thenReturn(shortDescription);

				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = itemMapper.mapRow(mockResultSet, 0 );
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( subjectId, returnedItem.getSubjectId() );
				assertEquals( contextName, returnedItem.getContextName() );
				assertEquals( gaiAdoptionDate, returnedItem.getGaiAdoptionDate() );
				assertEquals( inServiceDate, returnedItem.getInServiceDate() );
				assertEquals( itemCode, returnedItem.getItemCodeAsMasterDataKey() );
				assertEquals( listName, returnedItem.getListName() );
				assertEquals( longDescrption, returnedItem.getLongDescription() );
				assertEquals( note, returnedItem.getNotes() );
				assertEquals( outOfServiceDate, returnedItem.getOutOfServiceDate() );
				assertEquals( shortDescription, returnedItem.getShortDescription() );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		

	}
}
