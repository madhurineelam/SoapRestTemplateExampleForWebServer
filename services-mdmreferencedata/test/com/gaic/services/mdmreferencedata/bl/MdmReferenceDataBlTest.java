package com.gaic.services.mdmreferencedata.bl;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static com.gaic.services.mdmreferencedata.bl.validation.MdmReferenceDataValidation.LIST_NAME_REQUIRED_VALIDATION_MESSAGE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
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
import org.springframework.util.Log4jConfigurer;

import com.gaic.services.UseCaseTestHelper;
import com.gaic.services.mdmreferencedata.MdmReferenceDataServiceTest;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;
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




public class MdmReferenceDataBlTest extends AbstractInMemmoryDatabaseBaseNoDataSourceMultiSchemasTest
{

	@Autowired
	MdmReferenceDataBl mdmReferenceDataBl; 
	
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
	public void checkDependencies() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			private String expectedValue;
			private String returnedValue;
			
			@Override
			public void createTestData() throws Exception
			{
				expectedValue = "checkDependencies() OK";
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedValue = mdmReferenceDataBl.checkDependencies();
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( expectedValue, returnedValue );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	@Test
	public void findItemByKey_whenOnlyRequiredParametersAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private ItemDto returnedItem;
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setListName("External Report Category");
				itemByKeySearchParametersDto.setItemCode("COMPSGAPP");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = mdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				
				
				assertEquals( "1-1000149", returnedItem.getSubjectId() );
				assertEquals( "ENT", returnedItem.getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getInServiceDate() );
				assertEquals( "COMPSGAPP", returnedItem.getItemCodeAsMasterDataKey() );
				assertEquals( "External Report Category", returnedItem.getListName() );
				assertEquals( "Complete & Signed Application", returnedItem.getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItem.getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItem.getOutOfServiceDate() );
				assertEquals( "Complete & Signed Application", returnedItem.getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenNotFound()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private ItemDto returnedItem;
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setListName("External Report Category");
				itemByKeySearchParametersDto.setItemCode("COMPSGAPP_petrik");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = mdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertNull( returnedItem );
				

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenARequiedDefaultValueNotSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private ItemDto returnedItem;
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				itemByKeySearchParametersDto.setEffectiveDate( null );
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setListName("External Report Category");
				itemByKeySearchParametersDto.setItemCode("MVRREPORT");
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "ENT");
				itemByKeySearchParametersDto.setContextNames(contextNames);
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = mdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( "1-1000148", returnedItem.getSubjectId() );
				assertEquals( "ENT", returnedItem.getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getInServiceDate() );
				assertEquals( "MVRREPORT", returnedItem.getItemCodeAsMasterDataKey() );
				assertEquals( "External Report Category", returnedItem.getListName() );
				assertEquals( "Motor Vehicle Reports (MVR)", returnedItem.getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItem.getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItem.getOutOfServiceDate() );
				assertEquals( "Motor Vehicle Reports (MVR)", returnedItem.getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenRequiredParameterNotSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private ItemDto returnedItem;
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setListName(null);
				itemByKeySearchParametersDto.setItemCode("ISOLCR");
				itemByKeySearchParametersDto.setShortDescription("ISO Loss Cost Reports");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = mdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertFalse(  returnedItem.isStatus() );
				assertEquals( 1, returnedItem.getMessages().size() );
				assertEquals( LIST_NAME_REQUIRED_VALIDATION_MESSAGE, returnedItem.getMessages().get(0) );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	
	@Test
	public void findItemByList_whenOnlyRequiredParametersAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private ItemDtoList returnedItemList;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("List Test External Report Category");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItemList = mdmReferenceDataBl.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertTrue( returnedItemList.isStatus() );
				assertEquals( 0, returnedItemList.getMessages().size() );
				assertEquals( 2, returnedItemList.getItems().size() );
				
				assertEquals( "1-1000156", returnedItemList.getItems().get(0).getSubjectId() );
				assertEquals( "ENT", returnedItemList.getItems().get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(0).getInServiceDate() );
				assertEquals( "BDEHAAN", returnedItemList.getItems().get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItemList.getItems().get(0).getListName() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItemList.getItems().get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItemList.getItems().get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItemList.getItems().get(0).getOutOfServiceDate() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItemList.getItems().get(0).getShortDescription() );

				assertEquals( "1-1000157", returnedItemList.getItems().get(1).getSubjectId() );
				assertEquals( "ENT", returnedItemList.getItems().get(1).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(1).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(1).getInServiceDate() );
				assertEquals( "LOSSACR", returnedItemList.getItems().get(1).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItemList.getItems().get(1).getListName() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItemList.getItems().get(1).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItemList.getItems().get(1).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItemList.getItems().get(1).getOutOfServiceDate() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItemList.getItems().get(1).getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenNotFound()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private ItemDtoList returnedItemList;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("testExternal Report Category");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItemList = mdmReferenceDataBl.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertTrue( returnedItemList.isStatus() );
				assertEquals( 0, returnedItemList.getMessages().size() );
				assertEquals( 0, returnedItemList.getItems().size() );
				

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenARequiedDefaultValueNotSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private ItemDtoList returnedItemList;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( null );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("List Test External Report Category");
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "ENT");
				itemByListSearchParametersDto.setContextNames(contextNames);
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItemList = mdmReferenceDataBl.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertTrue( returnedItemList.isStatus() );
				assertEquals( 0, returnedItemList.getMessages().size() );
				assertEquals( 2, returnedItemList.getItems().size() );
				
							
				assertEquals( "1-1000156", returnedItemList.getItems().get(0).getSubjectId() );
				assertEquals( "ENT", returnedItemList.getItems().get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(0).getInServiceDate() );
				assertEquals( "BDEHAAN", returnedItemList.getItems().get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItemList.getItems().get(0).getListName() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItemList.getItems().get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItemList.getItems().get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItemList.getItems().get(0).getOutOfServiceDate() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItemList.getItems().get(0).getShortDescription() );

				assertEquals( "1-1000157", returnedItemList.getItems().get(1).getSubjectId() );
				assertEquals( "ENT", returnedItemList.getItems().get(1).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(1).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItemList.getItems().get(1).getInServiceDate() );
				assertEquals( "LOSSACR", returnedItemList.getItems().get(1).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItemList.getItems().get(1).getListName() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItemList.getItems().get(1).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItemList.getItems().get(1).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItemList.getItems().get(1).getOutOfServiceDate() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItemList.getItems().get(1).getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenRequiredParameterNotSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private ItemDtoList returnedItemList;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName(null);
				itemByListSearchParametersDto.setShortDescription("ISO Loss Cost Reports");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItemList = mdmReferenceDataBl.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertFalse(  returnedItemList.isStatus() );
				assertEquals( 1, returnedItemList.getMessages().size() );
				assertEquals( LIST_NAME_REQUIRED_VALIDATION_MESSAGE, returnedItemList.getMessages().get(0) );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}


	
	@Test
	public void findAllLists_whenRequiredParameterSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private AllListsSearchParametersDto allListsSearchParametersDto;
			private ContextListNameDtoList returnedContextListNameDtoList;
			@Override
			public void createTestData() throws Exception
			{
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				
				allListsSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				allListsSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "ENT");
				allListsSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedContextListNameDtoList = mdmReferenceDataBl.findAllLists(allListsSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertTrue(  returnedContextListNameDtoList.isStatus() );
				assertEquals( 0, returnedContextListNameDtoList.getMessages().size() );
				assertEquals( 2, returnedContextListNameDtoList.getContextListNameDtos().size() );

				assertEquals( "ENT", returnedContextListNameDtoList.getContextListNameDtos().get(0).getContextName() );
				assertEquals( "External Report Category", returnedContextListNameDtoList.getContextListNameDtos().get(0).getListName() );

				assertEquals( "ENT", returnedContextListNameDtoList.getContextListNameDtos().get(1).getContextName() );
				assertEquals( "List Test External Report Category", returnedContextListNameDtoList.getContextListNameDtos().get(1).getListName() );


			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	@Test
	public void findAllLists_whenARequiedDefaultValueNotSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private AllListsSearchParametersDto allListsSearchParametersDto;
			private ContextListNameDtoList contextListNameDtoList;
			@Override
			public void createTestData() throws Exception
			{
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				
				allListsSearchParametersDto.setEffectiveDate( null );
				allListsSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "ENT");
				allListsSearchParametersDto.setContextNames(contextNames);
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				contextListNameDtoList = mdmReferenceDataBl.findAllLists(allListsSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertTrue( contextListNameDtoList.isStatus() );
				assertEquals( 0, contextListNameDtoList.getMessages().size() );
				assertEquals( 2, contextListNameDtoList.getContextListNameDtos().size() );
				
				assertEquals( "ENT", contextListNameDtoList.getContextListNameDtos().get(0).getContextName() );
				assertEquals( "External Report Category", contextListNameDtoList.getContextListNameDtos().get(0).getListName() );
				
				assertEquals( "ENT", contextListNameDtoList.getContextListNameDtos().get(1).getContextName() );
				assertEquals( "List Test External Report Category", contextListNameDtoList.getContextListNameDtos().get(1).getListName() );
				
							
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

}
