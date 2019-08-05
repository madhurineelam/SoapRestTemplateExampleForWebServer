package com.gaic.services.mdmreferencedata;

import org.apache.commons.httpclient.HttpClient;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static com.gaic.services.TestCaseHelper.executeUseCaseTest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;

import com.gaic.services.UseCaseTestHelper;

public class RestTest
{

	public static String url_dev = "https://sstdev.td.afg:1024/sst/mdm.reference.data.rest_1/";
	
	public static String url_local = "http://localhost:8085/MdmReferenceDataService_1.0/";

	public static String url = url_local;

	@Test
	public void test()
	{
		
	}
	
//code to test calling the rest service via java	
//	@Test 
//	public void runRestClient_forfindItemByKey()
//	{
//			UseCaseTestHelper usercaseTestHelper = new UseCaseTestHelper()
//			{
//		
//				
//				private String uri;
//				private String parameters;
//				@Override
//				public void createTestData() throws Exception
//				{
//					uri = getURL(true ) + "findItemByKey";
//					parameters = "{\"listName\":\"External Report Category\", \"itemCode\":\"BDEHAAN\"} ";
//				}
//		
//				@Override
//				public void preConditions()throws Exception
//				{
//					
//				}
//				
//				@Override
//				public void executeFlow() throws Exception
//				{
//					
//					makeRestCall( uri, parameters );
//					
//				}
//				
//				@Override
//				public void postConditions() 
//				{
//				}
//			};
//			
//			
//			executeUseCaseTest( usercaseTestHelper );
//		
//		}

	private void makeRestCall( String uri, String inputParameters )
	{
		try
		{
			URL url = new URL(uri);//"http://localhost:8080/RESTfulExample/json/product/post");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			String input ="{\"listName\":\"External Report Category1\", \"itemCode\":\"BDEHAAN\"} ";// "{\"qty\":100,\"name\":\"iPad 4\"}";

			OutputStream os = httpURLConnection.getOutputStream();
			os.write(inputParameters.getBytes());
			os.flush();

			System.out.println("Response Code from Server: " + httpURLConnection.getResponseCode()+ "\n");
//			if ( !(conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED) ) 
//			{
//				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
//			}

			BufferedReader errorReader = new BufferedReader(new InputStreamReader((httpURLConnection.getErrorStream())));
			String output;
			System.out.println("Error Output from Server .... \n");
			
			//http://springinpractice.com/2013/10/09/generating-json-error-object-responses-with-spring-web-mvc
			//link to add ControllerAdvice to convert exception to JSON
			
			while ((output = errorReader.readLine()) != null) 
			{
				System.out.println(output);
			}

			
			RestTemplate restTemplate;
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));

			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) 
			{
				System.out.println(output);
			}

			httpURLConnection.disconnect();

	  } 
	  catch (MalformedURLException e) 
	  {

		e.printStackTrace();

	  } 
	  catch (IOException e) {

		e.printStackTrace();
	  }


	}
	
	private String getURL( boolean local )
	{
		if(local)
		{
			return url_local;
		}
		return url_dev;
	}
}
