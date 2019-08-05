package com.gaic.services.mdmreferencedata.dao;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.FIND_ITEM_BY_KEY_SQL;
import static com.gaic.services.mdmreferencedata.dao.MdmReferenceDataSql.*;
import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.gaic.services.UseCaseTestHelper;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.util.DateBuilder;
public class BuildSqlStatementsTest
{

	private BuildSqlStatements buildSqlStatements;
	
	@Test
	public void buildSQLForFindItemByKey_whenContextNameIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  CONTEXT_NM in ( :contextName )  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				List<String> value = new ArrayList<String>();
				value.add( "dddd");
				
				itemByKeySearchParametersDto.setContextNames(value);
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}
	@Test
	public void buildSQLForFindItemByKey_whenItemCodeIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private String value;
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  ITEM_CD = :itemCode  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				value = "dddd";
				
				itemByKeySearchParametersDto.setItemCode(value);
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}
	
	@Test
	public void buildSQLForFindItemByKey_whenListNameIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  LIST_NM = :listName  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setListName( "set" );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	
	@Test
	public void buildSQLForFindItemByKey_whenShortDescriptionIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  SHORT_DESC = :shortDescription  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setShortDescription( "set" );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	@Test
	public void buildSQLForFindItemByKey_whenLongDescriptionIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  LONG_DESC = :longDescription  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setLongDescription( "set" );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}
	
	@Test
	public void buildSQLForFindItemByKey_whenNotesIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  NOTES_TXT = :noteText  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setNotes( "set" );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	@Test
	public void buildSQLForFindItemByKey_whenGaiAdoptionDateIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  GAI_ADOPTION_DT <= :gaiAdoptionDate  AND OUT_OF_SERVICE_DT >= :gaiAdoptionDate  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.getCurrentDateAndSetTimeToMidnight() );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	@Test
	public void buildSQLForFindItemByKey_wheninServiceDateIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  IN_SERVICE_DT <= :inServiceDate  AND OUT_OF_SERVICE_DT >= :inServiceDate  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.getCurrentDateAndSetTimeToMidnight() );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	
	@Test
	public void buildSQLForFindItemByKey_whenMultipleFieldsAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private String expectedSQL;
			private String builtSQL;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				expectedSQL = FIND_ITEM_BY_KEY_SQL + " where  CONTEXT_NM in ( :contextName )  AND GAI_ADOPTION_DT <= :gaiAdoptionDate  AND OUT_OF_SERVICE_DT >= :gaiAdoptionDate  AND LIST_NM = :listName  order by SUBJECT_ID";
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.getCurrentDateAndSetTimeToMidnight() );
				List<String> value = new ArrayList<String>();
				value.add( "dddd");
				
				itemByKeySearchParametersDto.setContextNames(value);
				itemByKeySearchParametersDto.setListName("lll");
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				builtSQL = buildSqlStatements.buildSQLForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals(expectedSQL, builtSQL );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenContextNameIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private String value;
			@Override
			public void createTestData() throws Exception
			{
				value = "test";
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				List<String> values = new ArrayList<String>();
				values.add( value);
				
				itemByKeySearchParametersDto.setContextNames(values);
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				List<String> values  = (List<String>)returnedParameterMap.get(CONTEXT_NAME_PARAMETER_NAME);
				assertEquals( value, values.get(0) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
	
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenListNameIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private String value;
			@Override
			public void createTestData() throws Exception
			{
				value = "test";
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setListName( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(LIST_NAME_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenItemCodeIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private String value;
			@Override
			public void createTestData() throws Exception
			{
				value = "test";
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setItemCode( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(ITEM_CODE_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
	
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenShortDescriptionIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private String value;
			@Override
			public void createTestData() throws Exception
			{
				value = "test";
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setShortDescription( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(SHORT_DESCRIPTION_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenLongDescriptionIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private String value;
			@Override
			public void createTestData() throws Exception
			{
				value = "test";
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setLongDescription( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(LONG_DESCRIPTION_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}

	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenNotesIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private String value;
			@Override
			public void createTestData() throws Exception
			{
				value = "test";
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setNotes( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(NOTES_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenGaiAdoptionDateIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private Date value;
			@Override
			public void createTestData() throws Exception
			{
				value = DateBuilder.getCurrentDateAndSetTimeToMidnight();
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setEffectiveDate( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(GAI_ADOPTION_DATE_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenInServiceDateIsSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private Date value;
			@Override
			public void createTestData() throws Exception
			{
				value = DateBuilder.getCurrentDateAndSetTimeToMidnight();
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setProcessDate( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 1, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(IN_SERVICE_DATE_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
	
	@Test
	public void buildReplaceableParameterMapForFindItemByKey_whenMultipleValuesAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			
			private Map<String, Object> returnedParameterMap;
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			
			private Date value;
			@Override
			public void createTestData() throws Exception
			{
				value = DateBuilder.getCurrentDateAndSetTimeToMidnight();
				
				buildSqlStatements = new BuildSqlStatements();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				itemByKeySearchParametersDto.setEffectiveDate( value );
				itemByKeySearchParametersDto.setProcessDate( value );
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedParameterMap = buildSqlStatements.buildReplaceableParameterMapForFindItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( 2, returnedParameterMap.size() );
				assertEquals( value, returnedParameterMap.get(GAI_ADOPTION_DATE_PARAMETER_NAME) );
				assertEquals( value, returnedParameterMap.get(IN_SERVICE_DATE_PARAMETER_NAME) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
	}
	
}
