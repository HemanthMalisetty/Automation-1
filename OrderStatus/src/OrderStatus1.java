import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OrderStatus1 {
	
	static URL url=null;
	static File infile=null;
	static FileInputStream fis=null;
	static XSSFWorkbook wb=null;
	static XSSFSheet sheet=null;
	static int lastrow=0;
	static String sb;
	static Logger log=null;
	
	
	public static void main(String[] args) throws Exception
	{
        log=Logger.getLogger(OrderStatus1.class);
		
		BasicConfigurator.configure();
		
		String path="http://services.promostandards.org/WebServiceValidator/OrderStatus/getOrderStatusTypes_v1";
		
        infile=new File(".\\Input\\input.xlsx");    
        
        fis=new FileInputStream(infile);
        
        wb = new XSSFWorkbook(fis);

		sheet = wb.getSheetAt(0);

		lastrow = sheet.getLastRowNum();

		for (int i = 1; i <= lastrow; i++)
		{
				
		String urlparams="endpoint=https://devservices.alphabroder.com/OS/OrderStatus.svc&requestXml="+"<GetOrderStatusTypesRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/OrderStatusService/1.0.0/\">\r\n" + 
				"  <wsVersion>"+sheet.getRow(i).getCell(3)+"</wsVersion>\r\n" + 
				"  <id>"+sheet.getRow(i).getCell(1)+"</id>\r\n" + 
				"  <password>"+sheet.getRow(i).getCell(2)+"</password>\r\n" + 
				"</GetOrderStatusTypesRequest>"+"&webServiceName=OrderStatus&operationName=getOrderStatusTypes&version=1.0.0";
		
		byte[] postData = urlparams.getBytes( StandardCharsets.UTF_8 );
		
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
		
				
	        while ((inputLine = br.readLine()) != null) 
	        {	        	
	        	
	        	System.out.println(inputLine);
	        	sb=sb+inputLine;
	        	
	        }	
		 	        
	     if(con.getResponseCode()==200)
		 {
			 log.info("It has returned a response of "+con.getResponseCode()+" for customer "+sheet.getRow(i).getCell(0)+" and Response is valied");	 
		 }
		 else
		 {
			 log.info("It has returned a response of "+con.getResponseCode()+" for customer "+sheet.getRow(i).getCell(0)+" with response of" +sb);
		 }
		 
		sb=null;
	}

	}
	}
