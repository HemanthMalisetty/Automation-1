package com.qa.restclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//get method
	public void getRequest(String url) throws Exception
	{
		//create default http connection
		CloseableHttpClient closebleHttpclient=HttpClients.createDefault();
	
	    //create httpget connection
		HttpGet httpget=new HttpGet(url);
		
		//execute the get connection
        CloseableHttpResponse response=closebleHttpclient.execute(httpget);
	
        //get the status code
        int statuscode=response.getStatusLine().getStatusCode();

        System.out.println("Status code ---->"+statuscode);
        
        //converts the response into the string
        String responseString=EntityUtils.toString(response.getEntity(),"UTF-8");
        
        //convert the string into json object
        JSONObject responseobject=new JSONObject(responseString);
        
        System.out.println("The Json response from API ---->"+responseobject);
        
        //collect the headers into headers array
        Header[] allheaders=response.getAllHeaders();
        
        HashMap<String,String> map=new HashMap<String,String>();
        
        //collect the headers into the HashMap
        for(Header header:allheaders)
        {
        	map.put(header.getName(), header.getValue());
        }
        
        System.out.println("Headers---->"+map);
	}
	
	//Post method
	public CloseableHttpResponse postRequest(String url, String entityString,HashMap<String,String> map) throws Exception
	{
		
		//create the default http connection
		CloseableHttpClient closeableHttpclient=HttpClients.createDefault();
		
		//create the http post method
		HttpPost httppost=new HttpPost(url);
		
		//set the request payload
		httppost.setEntity(new StringEntity(entityString));
		
		//set the headers for the request
		for(Map.Entry<String, String> entry:map.entrySet())
		{
			httppost.addHeader(entry.getKey(),entry.getValue());
		}		
			
		CloseableHttpResponse closeableHttpResponse=closeableHttpclient.execute(httppost);
		
		return closeableHttpResponse;
	}

}
