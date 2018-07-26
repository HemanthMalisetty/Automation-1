package com.mk.onchip;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrderStatusDetails {

	static URL url=null;
	static File infile,file,prev_report=null;
	static FileInputStream fis=null;
	static XSSFWorkbook wb=null;
	static XSSFSheet sheet=null;
	static int lastrow=0;
	static String sb,urlparams;
	static byte[] postData=null;
	static ExtentReports extent=null;
	static ExtentTest logger=null;
	
		
   public static void main(String[] args) throws Exception {	 
	   
	   prev_report=new File(".\\Reports\\report.html");
	   
	  Boolean report_flag= prev_report.delete();
	  
	  if(report_flag)
	  {
		  System.out.println("Previous  Report File successfully deleted");
	  }
	  else
	  {
		System.out.println("FIle not found");  
	  }
	   	   
	   String path="http://services.promostandards.org/WebServiceValidator/OrderStatus/getOrderStatusDetails_v1";
		
       infile=new File(".\\Input\\input.xlsx");    
       
       fis=new FileInputStream(infile);
       
       wb = new XSSFWorkbook(fis);

		sheet = wb.getSheetAt(0);

		lastrow = sheet.getLastRowNum();

		for (int i = 1; i <= lastrow; i++)
		{
		
		extent = new ExtentReports (System.getProperty("user.dir") +"/Reports/report.html", false);
			
		logger=extent.startTest(sheet.getRow(i).getCell(0).getStringCellValue());
			
					
		urlparams="endpoint=https://devservices.alphabroder.com/OS/OrderStatus.svc&requestXml="+"<GetOrderStatusDetailsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/OrderStatusService/1.0.0/\">\r\n" + 
				"  <wsVersion>"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
				"  <id>"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
				"  <password>"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
				"  <queryType>"+sheet.getRow(i).getCell(4)+"</queryType>\r\n" + 
				"  <referenceNumber>"+sheet.getRow(i).getCell(5)+"</referenceNumber>\r\n" + 
				"  <statusTimeStamp xsi:nil=\"true\" />\r\n" + 
				"</GetOrderStatusDetailsRequest>"+"&webServiceName=OrderStatus&operationName=getOrderStatusDetails&version=1.0.0";
			
		 
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
		String data="";
				
	        while ((inputLine = br.readLine()) != null) 
	        {	        	
	        	data=data+inputLine;
	        	
	        }	
	    
	      file=Base.enterdata(".//Data//data.xml", data);
	      
	      
	      Document doc=Base.parsing(file);
	      
	      Boolean flag=Base.verify_correct_xml_resp(doc);
	      
	      if(flag)
	      {
	    	  logger.log(LogStatus.PASS,"Xml is valied");
	      }
	      else
	      {
	    	  logger.log(LogStatus.FAIL,"Some error has occured");
	      }
	      
	        extent.endTest(logger);
			extent.flush();
			extent.close();
	      
	      Base.clearfile(file);
	   
	}
			

	}
 

}
