package com.gaic.services.mdmreferencedata.bl.validation;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static com.gaic.services.mdmreferencedata.bl.validation.MdmReferenceDataValidation.EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE;
import static com.gaic.services.mdmreferencedata.bl.validation.MdmReferenceDataValidation.ITEM_CODE_REQUIRED_VALIDATION_MESSAGE;
import static com.gaic.services.mdmreferencedata.bl.validation.MdmReferenceDataValidation.LIST_NAME_REQUIRED_VALIDATION_MESSAGE;
import static com.gaic.services.mdmreferencedata.bl.validation.MdmReferenceDataValidation.PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE;
import static com.gaic.services.mdmreferencedata.bl.validation.MdmReferenceDataValidation.AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

import com.gaic.services.UseCaseTestHelper;
import com.gaic.services.mdmreferencedata.MdmReferenceDataServiceTest;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.util.DateBuilder;
import com.gaic.util.AbstractInMemmoryDatabaseBaseNoDataSourceMultiSchemasTest;
import com.gaic.util.DatabaseDataSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:com/gaic/services/core/resources/services-coreContext.xml",
	"classpath:com/gaic/services/configuration/resources/services-cprContext.xml",
	"classpath:com/gaic/services/mdmreferencedata/resources/testCpr.xml",
	"classpath:com/gaic/services/mdmreferencedata/resources/test-db-appContext.xml",
	"classpath:com/gaic/services/mdmreferencedata/resources/appContext.xml" })



public class MdmReferenceDataValidationTest extends AbstractInMemmoryDatabaseBaseNoDataSourceMultiSchemasTest
{

	@Autowired
	MdmReferenceDataValidation mdmReferenceDataValidation; 
	
	@Autowired
	private DataSource dataSource;
	
	public static boolean databaseNotInitialized = true;
	
	private  boolean useFlatXmlDataSet = false;

	@Override
	public boolean isDatabaseNotInitialized()
	{
		return databaseNotInitialized;
	}
	public boolean isUseFlatXmlDataSet()
	{
		return useFlatXmlDataSet;
	}

	public void setUseFlatXmlDataSet(boolean useFlatXmlDataSet)
	{
		this.useFlatXmlDataSet = useFlatXmlDataSet;
	}

	@Override
	public void setDatabaseNotInitialized(boolean databaseNotInitialized)
	{
		MdmReferenceDataServiceTest.databaseNotInitialized = databaseNotInitialized;
	}
	
	@Override
	public DataSource getDataSource()
	{
		return dataSource;
	}

	
	@Override
	public List<DatabaseDataSetup> setUpDatabaseSetupData()
	{
		List<DatabaseDataSetup> databaseDataSetups = new ArrayList<DatabaseDataSetup>();

		
		DatabaseDataSetup databaseDataSetup = new DatabaseDataSetup( "/com/gaic/services/mdmreferencedata/resources/deleteTableDataNames.xml",
				                                                     "/com/gaic/services/mdmreferencedata/resources/daoTestTestData.xml",
				                                                     "/com/gaic/services/mdmreferencedata/resources/databaseCreateTableSQLFileName.sql", 
				                                                     "/com/gaic/services/mdmreferencedata/resources/databaseCreateViewSQLFileName.sql",
				                                                     "dbo" );
		databaseDataSetups.add(databaseDataSetup);

		return databaseDataSetups;	
	}
	
	@BeforeClass
	public static void setUp() throws Exception
	{
		Log4jConfigurer.initLogging("classpath:com/gaic/services/core/test/resources/log4j-test.xml");
		System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime","true");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient","error");
	}

	
	@Test
	public void findItemByKey_whenAllValid() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/12/2016"));
				itemByKeySearchParametersDto.setItemCode(buildStringWithLength(3));
				itemByKeySearchParametersDto.setListName(buildStringWithLength(4));

				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByKeySearchParametersDto.setContextNames(contextNames);
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByKey(itemByKeySearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertTrue( valid );
				assertEquals( 0, validationMessages.size() );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	@Test
	public void findItemByKey_whenListNameNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/12/2016"));
				itemByKeySearchParametersDto.setItemCode(buildStringWithLength(3));
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByKeySearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByKey(itemByKeySearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( LIST_NAME_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenItemCodeNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/12/2016"));
				itemByKeySearchParametersDto.setListName(buildStringWithLength(3));
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByKeySearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByKey(itemByKeySearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( ITEM_CODE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenEffectiveDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setListName(buildStringWithLength(3));
				itemByKeySearchParametersDto.setItemCode(buildStringWithLength(3));
				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByKeySearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByKey(itemByKeySearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	
	@Test
	public void findItemByKey_whenProcessDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByKeySearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setListName(buildStringWithLength(3));
				itemByKeySearchParametersDto.setItemCode(buildStringWithLength(3));
				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByKeySearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByKey(itemByKeySearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenContextNmesNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByKeySearchParametersDto.setProcessDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByKeySearchParametersDto.setListName(buildStringWithLength(3));
				itemByKeySearchParametersDto.setItemCode(buildStringWithLength(3));
				

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByKey(itemByKeySearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}	
	
	
	@Test
	public void findItemByList_whenAllValid() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByListSearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/12/2016"));
				itemByListSearchParametersDto.setListName(buildStringWithLength(4));

				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByListSearchParametersDto.setContextNames(contextNames);
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByList(itemByListSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertTrue( valid );
				assertEquals( 0, validationMessages.size() );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	@Test
	public void findItemByList_whenListNameNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByListSearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/12/2016"));
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByListSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByList(itemByListSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( LIST_NAME_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	
	@Test
	public void findItemByList_whenEffectiveDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByListSearchParametersDto.setListName(buildStringWithLength(3));

				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByListSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByList(itemByListSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	
	@Test
	public void findItemByList_whenProcessDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByListSearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByListSearchParametersDto.setListName(buildStringWithLength(3));
				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				itemByListSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByList(itemByListSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenContextNmesNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				itemByListSearchParametersDto.setProcessDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByListSearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				itemByListSearchParametersDto.setListName(buildStringWithLength(3));
				

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findItemByList(itemByListSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}	
	
////////////////
	
	
	@Test
	public void findAllLists_whenEffectiveDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private AllListsSearchParametersDto allListsSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				allListsSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/13/2016"));
				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				allListsSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findAllLists(allListsSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( EFFECTIVE_DATE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	
	@Test
	public void findAllLists_whenProcessDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private AllListsSearchParametersDto allListsSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				allListsSearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "test");
				
				allListsSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findAllLists( allListsSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( PROCESS_DATE_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findAllLists_whenContextNmesNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private AllListsSearchParametersDto allListsSearchParametersDto;
			private List<String> validationMessages;
			private boolean valid;
			
			
			@Override
			public void createTestData() throws Exception
			{
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				validationMessages = new ArrayList<String>();
				
				allListsSearchParametersDto.setProcessDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				allListsSearchParametersDto.setEffectiveDate(DateBuilder.parseAndSetToMidnight("12/13/2016"));
				

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				valid = mdmReferenceDataValidation.findAllLists( allListsSearchParametersDto, validationMessages);
			}
			
			@Override
			public void postConditions() 
			{
				assertFalse( valid );
				assertEquals( 1, validationMessages.size() );
				assertEquals( AT_LEAST_ONE_CONTEXT_NAME_IS_REQUIRED_VALIDATION_MESSAGE, validationMessages.get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}	
	

}

