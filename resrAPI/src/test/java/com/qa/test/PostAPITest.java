package com.qa.test;

import java.io.File;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.users.Users;

public class PostAPITest extends TestBase{
	
	String serviceurl=null;
	String apiUrl=null;
	String url=null;
	
	@BeforeTest
	public void setUp()
	{
		serviceurl=prop.getProperty("serviceurl");
		apiUrl=prop.getProperty("apiurl");
		url=serviceurl+apiUrl;
	}
	@Test
	public void postAPITest() throws Exception
	{
		RestClient rest=new RestClient();
		
		//setting up the Headers
		HashMap<String,String> headerMap=new HashMap<String,String>();
		
		headerMap.put("content-type", "application/json");
		
		//now we need to create the StringEntity of json for request Payload
		ObjectMapper mapper=new ObjectMapper();
		
		Users user=new Users("sydney@fife","pistol");
		
		mapper.writeValue(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\users\\Uer.json"), user);
	
	    String usersJsonString = mapper.writeValueAsString(user);

	    System.out.println(usersJsonString);
	    
	    System.out.println(url);
	    
	    System.out.println(headerMap);
	    
	    CloseableHttpResponse closeableHttpResponse=rest.postRequest(url, mapper.writeValueAsString(user), headerMap);
	   
	    int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
	   
	    System.out.println("Status code---->"+statuscode);
	   
	    String responseJsonString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
	   
	    JSONObject json=new JSONObject(responseJsonString);
	   
	    System.out.println("The response--->"+json);
	   
	    //create the User class object to get the values from the mapper
	    Users usersResObj=mapper.readValue(responseJsonString,Users.class);
	   
	    System.out.println("The content of response "+usersResObj.getToken());
	}

}
