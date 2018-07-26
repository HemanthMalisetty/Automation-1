package com.qa.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restclient.RestClient;

public class GetAPITest extends TestBase {
	
	String url=null;
	TestBase tb=null;
	
	@BeforeTest
	public void setUp()
	{
		tb=new TestBase();
		
		String serviceurl=prop.getProperty("serviceurl");
		
		String apiurl=prop.getProperty("apiurl");
		
		url=serviceurl+apiurl;
	}
	@Test
	public void getTest() throws Exception
	{
		RestClient client = new RestClient();
		
		client.getRequest(url);
	}

}
