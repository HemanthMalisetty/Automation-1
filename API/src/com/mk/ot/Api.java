package com.mk.ot;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Api {
	
	static Properties prop;
	static HashMap<String,String> hm=null;
	static byte[] postData=null;
	static String path,urlparams=null;
	static URL url=null;
	static HttpURLConnection con=null;
	static InputStream is=null;
	static Boolean flag,prev_report=null;
	static ExtentTest logger=null;
	
	public static void main(String[] args)throws Exception {
		
		  prev_report= Utility_methods.deleteReport(new File(".//Reports//report.html"));
		
		  if(prev_report)
		  {
			  System.out.println("Generating new Report");
		  }
		  else
		  {
			  System.out.println("No file Found");
		  }
		  
		  prop=Utility_methods.loadfile(".//Input//Masterconfig.properties");	
		  
		  hm=Requests.urlparams(prop.getProperty("request_for_api"));
		  
		  int length_of_map;
		  if(hm!=null)
		  {
			  length_of_map=hm.size(); 
		  }
		  else
		  {
			  length_of_map=0;
		  }
		  
		  for(int i=1;i<length_of_map;i++)
		  {
			  path=hm.get("path");
			  
			  urlparams=hm.get("urlparams"+i);
			  
			  postData = urlparams.getBytes( StandardCharsets.UTF_8 );
			  
			  int postDataLength = postData.length;
				
				url=new URL(path);
				
				con=(HttpURLConnection)url.openConnection();
				
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
						
				is= con.getInputStream();
				
				Utility_methods.writeFile(is);
				
				flag=Response.validate(prop.getProperty("request_for_api"));
			        
			    logger=Utility_methods.startReport("customer "+i);
			    		    
			    if(flag==true)
			    {
			    	logger.log(LogStatus.PASS, "Its a valied xml");
			    }
			    
			    else
			    {
			    	logger.log(LogStatus.FAIL, "please do check the input for this customer, if input is valied - there is an error from server side");
			    }
			    			
			    Utility_methods.generateResult(logger);
			    
			    Utility_methods.clearfile(new File(".\\Data_to_be_validated\\validate.xml"));		  
		 
		        System.out.println("Generated report");
		  }
		  
		  		  
		  if(hm!=null)
		  {
		     hm.clear();		  
		  }
		  else
		  {
			  System.out.println("Please enter a valied query type");
		  }
		  
		 		  
		  
	}

}
