package com.gaic.services.mdmreferencedata;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import com.gaic.services.mdmreferencedata.bl.MdmReferenceDataBl;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ContextListNameDtoList;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemDto;
import com.gaic.services.mdmreferencedata.dto.ItemDtoList;
import com.gaic.util.AbstractInMemmoryDatabaseBaseNoDataSourceMultiSchemasTest;
import com.gaic.util.DatabaseDataSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:com/gaic/services/core/resources/services-coreContext.xml",
	"classpath:com/gaic/services/configuration/resources/services-cprContext.xml",
	"classpath:com/gaic/services/mdmreferencedata/resources/testCpr.xml",
	"classpath:com/gaic/services/mdmreferencedata/resources/test-db-appContext.xml",
	"classpath:com/gaic/services/mdmreferencedata/resources/appContext.xml" })



public class MdmReferenceDataControllerTest  extends AbstractInMemmoryDatabaseBaseNoDataSourceMultiSchemasTest
{
	
	protected MdmReferenceDataController controller;

	private MdmReferenceDataBl mockMdmReferenceDataBl;
	

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
		MdmReferenceDataControllerTest.databaseNotInitialized = databaseNotInitialized;
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
	public void testCheckDependencies() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			String response;
			
			@Override
			public void createTestData() throws Exception
			{
				controller = new MdmReferenceDataController();
				mockMdmReferenceDataBl = mock( MdmReferenceDataBl.class );
				controller.setiMdmReferenceDataBl(mockMdmReferenceDataBl);
				when( mockMdmReferenceDataBl.checkDependencies()).thenReturn( "implemented");
			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				response = controller.checkDependencies();
			}
			
			@Override
			public void postConditions() 
			{
				assertTrue("Please implement the checkDependencies() method!",!response.contains("not implemented"));				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}
	
	@Test
	public void findItemByKey() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private ItemDto returnedItem;
			private ItemDto knownItem;
			
			@Override
			public void createTestData() throws Exception
			{
				knownItem = new ItemDto();
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				controller = new MdmReferenceDataController();
				mockMdmReferenceDataBl = mock( MdmReferenceDataBl.class );
				controller.setiMdmReferenceDataBl(mockMdmReferenceDataBl);
				when( mockMdmReferenceDataBl.findItemByKey(itemByKeySearchParametersDto)).thenReturn( knownItem);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItem = controller.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( knownItem, returnedItem );
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}
	@Test
	public void findItemsByList() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			private ItemDtoList returnedItemList;
			private ItemDtoList knownItemList;
			
			@Override
			public void createTestData() throws Exception
			{
				knownItemList = new ItemDtoList();
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				controller = new MdmReferenceDataController();
				mockMdmReferenceDataBl = mock( MdmReferenceDataBl.class );
				controller.setiMdmReferenceDataBl(mockMdmReferenceDataBl);
				when( mockMdmReferenceDataBl.findItemByList(itemByListSearchParametersDto)).thenReturn( knownItemList);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedItemList = controller.findItemsByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( knownItemList, returnedItemList );
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	
	@Test
	public void findAllLists() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{

			private AllListsSearchParametersDto allListsSearchParametersDto;
			private ContextListNameDtoList returnedContextListNameDtoList;
			private ContextListNameDtoList knownContextListNameDtoList;
			
			@Override
			public void createTestData() throws Exception
			{
				knownContextListNameDtoList = new ContextListNameDtoList();
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				
				controller = new MdmReferenceDataController();
				mockMdmReferenceDataBl = mock( MdmReferenceDataBl.class );
				controller.setiMdmReferenceDataBl(mockMdmReferenceDataBl);
				when( mockMdmReferenceDataBl.findAllLists( allListsSearchParametersDto)).thenReturn( knownContextListNameDtoList);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedContextListNameDtoList = controller.findAllLists(allListsSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( returnedContextListNameDtoList, returnedContextListNameDtoList );
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}

	
	@Test
	public void checkDependencies() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{


			private String returnedValue;
			private String expectedReturnedValue;
			
			@Override
			public void createTestData() throws Exception
			{
				expectedReturnedValue = "test";
				
				controller = new MdmReferenceDataController();
				mockMdmReferenceDataBl = mock( MdmReferenceDataBl.class );
				controller.setiMdmReferenceDataBl(mockMdmReferenceDataBl);
				when( mockMdmReferenceDataBl.checkDependencies()).thenReturn( expectedReturnedValue);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedValue = controller.checkDependencies();
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( expectedReturnedValue, returnedValue );
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}
	@Test
	public void ping() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			private long returnedValue;
			
			@Override
			public void createTestData() throws Exception
			{
	
				controller = new MdmReferenceDataController();

			}
	
			@Override
			public void preConditions()throws Exception
			{
				
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				returnedValue = controller.ping();
			}
			
			@Override
			public void postConditions() 
			{
				//had to do this because ping uses the System.currentTimeMillis()
				assertTrue( returnedValue > 0  );
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );

	}


}
