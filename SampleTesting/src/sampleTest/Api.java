package sampleTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Api {

	static URL url=null;
	static String urlparams="";
	static byte[] postData=null;
	
	
	public static void main(String[] args)throws Exception {
		
		String path="http://services.promostandards.org/WebServiceValidator/OrderStatus/getOrderStatusDetails_v1";
		
	    String urlparams="endpoint=https://devservices.alphabroder.com/OS/OrderStatus.svc&requestXml="+"<GetOrderStatusDetailsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/OrderStatusService/1.0.0/\">\r\n" + 
	            "  <wsVersion>"+"</wsVersion>\r\n" + 
				"  <id>"+"</id>\r\n" + 
				"  <password>"+"</password>\r\n" + 
				"  <queryType>"+"</queryType>\r\n" + 
				"  <referenceNumber>"+"</referenceNumber>\r\n" + 
				"  <statusTimeStamp xsi:nil=\"true\" />\r\n" + 
				"</GetOrderStatusDetailsRequest>"+"&webServiceName=OrderStatus&operationName=getOrderStatusDetails&version=1.0.0";
		
	   
	    
	    
	    
	    /*File f=new File(".//Input//urlparams.txt");
	    
	    FileReader freader = new FileReader(f);
		BufferedReader br1 = new BufferedReader(freader);
		String s;
		while((s = br1.readLine()) != null) {
		urlparams=urlparams+s;
		}
	    
	    System.out.println(urlparams);*/
	    
	    postData = urlparams.getBytes( StandardCharsets.UTF_8 );
		
		int postDataLength = postData.length;
		
		url=new URL(path);
		
		HttpURLConnection con=(HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("POST");
		
		con.setDoInput(true);
		
		con.setDoOutput(true);
		
		con.setRequestProperty("Accept","application/xml, text/xml, */*; q=0.01");
		
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		
		con.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
		
		con.setRequestProperty("X-Requested-With","XMLHttpRequest");
		
		con.setRequestProperty("Accept-Language","en-US,en;q=0.9");
		
		con.setRequestProperty("Connection","keep-alive");
		
		con.setUseCaches(false);
		
		try(DataOutputStream wr = new DataOutputStream(con.getOutputStream()))
		 
		{
			   wr.write( postData );
		}
		
		con.connect();
				
		System.out.println("Status code="+con.getResponseCode());
				
		InputStream is = con.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String inputLine;
		//String data="";
				
	        while ((inputLine = br.readLine()) != null) 
	        {	        	
	        	//data=data+inputLine;
	        	System.out.println(inputLine);
	        	
	        }	
	    
	    

	}

}
