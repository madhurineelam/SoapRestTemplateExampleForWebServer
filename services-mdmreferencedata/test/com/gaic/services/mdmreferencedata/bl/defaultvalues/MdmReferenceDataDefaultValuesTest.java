package com.gaic.services.mdmreferencedata.bl.defaultvalues;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;
import static com.gaic.services.mdmreferencedata.bl.defaultvalues.MdmReferenceDataDefaultValues.ENT_CONTEXT_NAME_DEFAULT_VALUE;
import static com.gaic.services.mdmreferencedata.bl.defaultvalues.MdmReferenceDataDefaultValues.MASTER_CONTEXT_NAME_DEFAULT_VALUE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gaic.services.UseCaseTestHelper;
import com.gaic.services.mdmreferencedata.dto.AllListsSearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByKeySearchParametersDto;
import com.gaic.services.mdmreferencedata.dto.ItemByListSearchParametersDto;
import com.gaic.services.util.DateBuilder;
public class MdmReferenceDataDefaultValuesTest
{
	
	private MdmReferenceDataDefaultValues mdmReferenceDataDefaultValues;

	
	@Test
	public void findItemByKey_whenProcessDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByKeySearchParametersDto itemByKeySearchParametersDto;
			private String contextName;
			
			@Override
			public void createTestData() throws Exception
			{
				
				contextName = "test";
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( contextName );
				itemByKeySearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertNull( itemByKeySearchParametersDto.getProcessDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getEffectiveDate() );
				assertEquals( 1, itemByKeySearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByKeySearchParametersDto.getContextNames().get(0) );
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.getCurrentCalendarSetTimeToMidnight().getTime(), itemByKeySearchParametersDto.getProcessDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getEffectiveDate() );
				assertEquals( 1, itemByKeySearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByKeySearchParametersDto.getContextNames().get(0) );
				
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
			private String contextName;
			
			@Override
			public void createTestData() throws Exception
			{
				
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( contextName );
				itemByKeySearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertNull( itemByKeySearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getProcessDate() );
				assertEquals( 1, itemByKeySearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByKeySearchParametersDto.getContextNames().get(0) );

			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.getCurrentCalendarSetTimeToMidnight().getTime(), itemByKeySearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getProcessDate() );
				assertEquals( 1, itemByKeySearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByKeySearchParametersDto.getContextNames().get(0) );

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
			
			
			@Override
			public void createTestData() throws Exception
			{
				
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				itemByKeySearchParametersDto = new ItemByKeySearchParametersDto();
				
				
				itemByKeySearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				itemByKeySearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getProcessDate() );
				assertNull(  itemByKeySearchParametersDto.getContextNames() );

			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findItemByKey(itemByKeySearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByKeySearchParametersDto.getProcessDate() );
				assertEquals( 2, itemByKeySearchParametersDto.getContextNames().size() );
				assertEquals( MASTER_CONTEXT_NAME_DEFAULT_VALUE, itemByKeySearchParametersDto.getContextNames().get(0) );
				assertEquals( ENT_CONTEXT_NAME_DEFAULT_VALUE, itemByKeySearchParametersDto.getContextNames().get(1) );
				
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
			private String contextName;
			
			@Override
			public void createTestData() throws Exception
			{
				
				contextName = "test";
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( contextName );
				itemByListSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertNull( itemByListSearchParametersDto.getProcessDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getEffectiveDate() );
				assertEquals( 1, itemByListSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByListSearchParametersDto.getContextNames().get(0) );
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.getCurrentCalendarSetTimeToMidnight().getTime(), itemByListSearchParametersDto.getProcessDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getEffectiveDate() );
				assertEquals( 1, itemByListSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByListSearchParametersDto.getContextNames().get(0) );
				
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
			private String contextName;
			
			@Override
			public void createTestData() throws Exception
			{
				
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( contextName );
				itemByListSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertNull( itemByListSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getProcessDate() );
				assertEquals( 1, itemByListSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByListSearchParametersDto.getContextNames().get(0) );

			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.getCurrentCalendarSetTimeToMidnight().getTime(), itemByListSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getProcessDate() );
				assertEquals( 1, itemByListSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, itemByListSearchParametersDto.getContextNames().get(0) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findItemByList_whenItemCodeNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private ItemByListSearchParametersDto itemByListSearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				itemByListSearchParametersDto = new ItemByListSearchParametersDto();
				
				
				itemByListSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				itemByListSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getProcessDate() );
				assertNull(  itemByListSearchParametersDto.getContextNames() );

			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findItemByList(itemByListSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), itemByListSearchParametersDto.getProcessDate() );
				assertEquals( 2, itemByListSearchParametersDto.getContextNames().size() );
				assertEquals( MASTER_CONTEXT_NAME_DEFAULT_VALUE, itemByListSearchParametersDto.getContextNames().get(0) );
				assertEquals( ENT_CONTEXT_NAME_DEFAULT_VALUE, itemByListSearchParametersDto.getContextNames().get(1) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}

	
	@Test
	public void findAllLists_whenEffectiveDateNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private AllListsSearchParametersDto allListsSearchParametersDto;
			private String contextName;
			
			@Override
			public void createTestData() throws Exception
			{
				
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				
				
				allListsSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( contextName );
				allListsSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertNull( allListsSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getProcessDate() );
				assertEquals( 1, allListsSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, allListsSearchParametersDto.getContextNames().get(0) );

			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findAllLists(allListsSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.getCurrentCalendarSetTimeToMidnight().getTime(), allListsSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getProcessDate() );
				assertEquals( 1, allListsSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, allListsSearchParametersDto.getContextNames().get(0) );

			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
	@Test
	public void findAllLists_whenItemCodeNotSet() throws Exception
	{
		
		UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
		{
			
			private AllListsSearchParametersDto allListsSearchParametersDto;
			
			
			@Override
			public void createTestData() throws Exception
			{
				
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				
				
				allListsSearchParametersDto.setProcessDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				allListsSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getProcessDate() );
				assertNull(  allListsSearchParametersDto.getContextNames() );

			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findAllLists(allListsSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getEffectiveDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getProcessDate() );
				assertEquals( 2, allListsSearchParametersDto.getContextNames().size() );
				assertEquals( MASTER_CONTEXT_NAME_DEFAULT_VALUE, allListsSearchParametersDto.getContextNames().get(0) );
				assertEquals( ENT_CONTEXT_NAME_DEFAULT_VALUE, allListsSearchParametersDto.getContextNames().get(1) );
				
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
			private String contextName;
			
			@Override
			public void createTestData() throws Exception
			{
				
				contextName = "test";
				mdmReferenceDataDefaultValues = new MdmReferenceDataDefaultValues();
				
				allListsSearchParametersDto = new AllListsSearchParametersDto();
				
				
				allListsSearchParametersDto.setEffectiveDate( DateBuilder.parseAndSetToMidnight("12/12/2016") );
				List<String> contextNames = new ArrayList<String>();
				contextNames.add( contextName );
				allListsSearchParametersDto.setContextNames(contextNames);

			}
	
			@Override
			public void preConditions()throws Exception
			{
				assertNull( allListsSearchParametersDto.getProcessDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getEffectiveDate() );
				assertEquals( 1, allListsSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, allListsSearchParametersDto.getContextNames().get(0) );
			}
			
			@Override
			public void executeFlow() throws Exception
			{
				mdmReferenceDataDefaultValues.findAllLists(allListsSearchParametersDto);
			}
			
			@Override
			public void postConditions() 
			{
				assertEquals( DateBuilder.getCurrentCalendarSetTimeToMidnight().getTime(), allListsSearchParametersDto.getProcessDate() );
				assertEquals( DateBuilder.parseAndSetToMidnight("12/12/2016"), allListsSearchParametersDto.getEffectiveDate() );
				assertEquals( 1, allListsSearchParametersDto.getContextNames().size() );
				assertEquals( contextName, allListsSearchParametersDto.getContextNames().get(0) );
				
			}
		};
		
		
		executeUseCaseTest( usercaseTestHelper );
		
		
	}
	
}
