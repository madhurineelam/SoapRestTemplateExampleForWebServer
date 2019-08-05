package com.gaic.services.mdmreferencedata.dao.mapper;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.CONTEXT_NAME_COLUMN_NAME;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.LIST_NAME_COLUMN_NAME;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import org.junit.Test;

import com.gaic.services.UseCaseTestHelper;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDto;

public class ContextListNameDtoMapperTest
{
	
	@Test
	public void mapRow()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ResultSet mockResultSet;
			private String contextName;
			private String listName;
			
			private ContextListNameDtoMapper contextListNameDtoMapper;
			private ContextListNameDto returnContextListNameDto;
			
			@Override
			public void createTestData() throws Exception
			{
				contextListNameDtoMapper = new ContextListNameDtoMapper();
				
				contextName = "2";
				listName = "4";

				
				
				mockResultSet = mock( ResultSet.class );
				
				when( mockResultSet.getString(CONTEXT_NAME_COLUMN_NAME)).thenReturn(contextName);
				when( mockResultSet.getString(LIST_NAME_COLUMN_NAME)).thenReturn(listName);

				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnContextListNameDto = contextListNameDtoMapper.mapRow(mockResultSet, 0 );
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( contextName, returnContextListNameDto.getContextName() );
				assertEquals( listName, returnContextListNameDto.getListName() );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		

	}

}
