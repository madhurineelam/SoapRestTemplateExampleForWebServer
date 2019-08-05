package com.gaic.services.mdmreferencedata.dao;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

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
import com.gaic.services.mdmreferencedata.dto.ContextListNameDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
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



public class MdmReferenceDataDaoTest extends AbstractInMemmoryDatabaseBaseNoDataSourceMultiSchemasTest
{

	@Autowired
	MdmReferenceDataDao mdmReferenceDataDao; 
	
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
				returnedValue = mdmReferenceDataDao.checkDependencies();
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
				returnedItem = mdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
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
				returnedItem = mdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
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
	public void findItemByKey_whenRequiredParametersAndContextNameAreSet()
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
				returnedItem = mdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
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
	public void findItemByKey_whenRequiredParametersAndShortDescriptionAreSet()
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
				returnedItem = mdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( "1-1000150", returnedItem.getSubjectId() );
				assertEquals( "ENT", returnedItem.getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getInServiceDate() );
				assertEquals( "ISOLCR", returnedItem.getItemCodeAsMasterDataKey() );
				assertEquals( "External Report Category", returnedItem.getListName() );
				assertEquals( "ISO Loss Cost Reports", returnedItem.getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItem.getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItem.getOutOfServiceDate() );
				assertEquals( "ISO Loss Cost Reports", returnedItem.getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	@Test
	public void findItemByKey_whenRequiredParametersAndLongDescriptionAreSet()
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
				itemByKeySearchParametersDto.setItemCode("ITVREPORT");
				itemByKeySearchParametersDto.setLongDescription("Insurance to Value (ITV) report");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = mdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( "1-1000151", returnedItem.getSubjectId() );
				assertEquals( "ENT", returnedItem.getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getInServiceDate() );
				assertEquals( "ITVREPORT", returnedItem.getItemCodeAsMasterDataKey() );
				assertEquals( "External Report Category", returnedItem.getListName() );
				assertEquals( "Insurance to Value (ITV) report", returnedItem.getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItem.getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItem.getOutOfServiceDate() );
				assertEquals( "Insurance to Value (ITV) report", returnedItem.getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByKey_whenRequiredParametersAndNotesAreSet()
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
				itemByKeySearchParametersDto.setItemCode("LPREPORT");
				itemByKeySearchParametersDto.setNotes("Jira item RA-22190");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = mdmReferenceDataDao.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( "1-1000152", returnedItem.getSubjectId() );
				assertEquals( "ENT", returnedItem.getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItem.getInServiceDate() );
				assertEquals( "LPREPORT", returnedItem.getItemCodeAsMasterDataKey() );
				assertEquals( "External Report Category", returnedItem.getListName() );
				assertEquals( "Loss Prevention (LP) Report", returnedItem.getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItem.getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItem.getOutOfServiceDate() );
				assertEquals( "Loss Prevention (LP) Report", returnedItem.getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	
	@Test
	public void findItemByList_whenOnlyRequiredParametersAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByKeySearchParametersDto;
			private List<ItemDto> returnedItems;
			@Override
			public void createTestData() throws Exception
			{
				itemByKeySearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByKeySearchParametersDto.setListName("List Test External Report Category");
				
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItems = mdmReferenceDataDao.findItemByList(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				
				assertEquals( 2, returnedItems.size() );
				
				assertEquals( "1-1000156", returnedItems.get(0).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getInServiceDate() );
				assertEquals( "BDEHAAN", returnedItems.get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(0).getListName() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(0).getOutOfServiceDate() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getShortDescription() );

				assertEquals( "1-1000157", returnedItems.get(1).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(1).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(1).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(1).getInServiceDate() );
				assertEquals( "LOSSACR", returnedItems.get(1).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(1).getListName() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(1).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(1).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(1).getOutOfServiceDate() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(1).getShortDescription() );
			
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
			private List<ItemDto> returnedItems;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("Petrik");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItems = mdmReferenceDataDao.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( 0, returnedItems.size() );
				

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenRequiredParametersAndContextNameAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<ItemDto> returnedItems;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
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
				returnedItems = mdmReferenceDataDao.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( 2, returnedItems.size() );
				
				assertEquals( "1-1000156", returnedItems.get(0).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getInServiceDate() );
				assertEquals( "BDEHAAN", returnedItems.get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(0).getListName() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(0).getOutOfServiceDate() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getShortDescription() );

				assertEquals( "1-1000157", returnedItems.get(1).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(1).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(1).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(1).getInServiceDate() );
				assertEquals( "LOSSACR", returnedItems.get(1).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(1).getListName() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(1).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(1).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(1).getOutOfServiceDate() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(1).getShortDescription() );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenRequiredParametersAndShortDescriptionAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<ItemDto> returnedItems;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("List Test External Report Category");				
				itemByListSearchParametersDto.setShortDescription("lt Loss Analysis & Claims Review");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItems = mdmReferenceDataDao.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( 1, returnedItems.size() );
				assertEquals( "1-1000157", returnedItems.get(0).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getInServiceDate() );
				assertEquals( "LOSSACR", returnedItems.get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(0).getListName() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(0).getOutOfServiceDate() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(0).getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	@Test
	public void findItemByList_whenRequiredParametersAndLongDescriptionAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<ItemDto> returnedItems;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("List Test External Report Category");
				itemByListSearchParametersDto.setLongDescription("lt Brief Description & Hazard Analysis");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItems = mdmReferenceDataDao.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( 1, returnedItems.size() );
				
				assertEquals( "1-1000156", returnedItems.get(0).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getInServiceDate() );
				assertEquals( "BDEHAAN", returnedItems.get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(0).getListName() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(0).getOutOfServiceDate() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getShortDescription() );

				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenRequiredParametersAndNotesAreSet()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private List<ItemDto> returnedItems;
			@Override
			public void createTestData() throws Exception
			{
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				itemByListSearchParametersDto.setListName("List Test External Report Category");
				itemByListSearchParametersDto.setNotes("Jira item RA-22190");
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItems = mdmReferenceDataDao.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( 2, returnedItems.size() );
				
				assertEquals( "1-1000156", returnedItems.get(0).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(0).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(0).getInServiceDate() );
				assertEquals( "BDEHAAN", returnedItems.get(0).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(0).getListName() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(0).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(0).getOutOfServiceDate() );
				assertEquals( "lt Brief Description & Hazard Analysis", returnedItems.get(0).getShortDescription() );

				assertEquals( "1-1000157", returnedItems.get(1).getSubjectId() );
				assertEquals( "ENT", returnedItems.get(1).getContextName() );
				assertEquals( DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(1).getGaiAdoptionDate() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("01/01/2015"), returnedItems.get(1).getInServiceDate() );
				assertEquals( "LOSSACR", returnedItems.get(1).getItemCodeAsMasterDataKey() );
				assertEquals( "List Test External Report Category", returnedItems.get(1).getListName() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(1).getLongDescription() );
				assertEquals( "Jira item RA-22190", returnedItems.get(1).getNotes() );
				assertEquals(  DateBuilder.parseAndSetToMidnight("12/31/2099"), returnedItems.get(1).getOutOfServiceDate() );
				assertEquals( "lt Loss Analysis & Claims Review", returnedItems.get(1).getShortDescription() );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	@Test
	public void findAllLists()
	{
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private AllListsSearchParametersDto allListsSearchDto;
			private List<ContextListNameDto> returnedContextListNameDtos;
			@Override
			public void createTestData() throws Exception
			{
				allListsSearchDto = new AllListsSearchParametersDto();
				
				allListsSearchDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				allListsSearchDto.setProcessDate( DateBuilder.parseAndSetToMidnight( "12/12/2016") );
				
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( "ENT");
				contextNames.add( "MASTER");
				allListsSearchDto.setContextNames(contextNames);
				
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedContextListNameDtos = mdmReferenceDataDao.findAllLists(allListsSearchDto);
			}
			
			@Override
			public void postConditions() 
			{
				
				assertEquals( 3, returnedContextListNameDtos.size() );
				
				assertEquals( "ENT", returnedContextListNameDtos.get(0).getContextName() );
				assertEquals( "External Report Category", returnedContextListNameDtos.get(0).getListName() );

				assertEquals( "ENT", returnedContextListNameDtos.get(1).getContextName() );
				assertEquals( "List Test External Report Category", returnedContextListNameDtos.get(1).getListName() );

				assertEquals( "MASTER", returnedContextListNameDtos.get(2).getContextName() );
				assertEquals( "List Test1 External Report Category", returnedContextListNameDtos.get(2).getListName() );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
}
