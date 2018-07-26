import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Sample {

	static byte[] postData=null;
	static URL url=null;
	static HttpURLConnection con=null;
	
	public static void main(String[] args) throws Exception {
		
		String data="endpoint=https://devservices.alphabroder.com/productConfig/service/index.php&requestXml=<GetAvailableLocationsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/\">\r\n" + 
				"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">1.0.0</wsVersion>\r\n" + 
				"  <id xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">19</id>\r\n" + 
				"  <password xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">19</password>\r\n" + 
				"  <productId xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">G200</productId>\r\n" + 
				"  <localizationCountry xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">US</localizationCountry>\r\n" + 
				"  <localizationLanguage xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">en</localizationLanguage>\r\n" + 
				"</GetAvailableLocationsRequest>&webServiceName=ProductPricingandConfiguration&operationName=getAvailableLocations&version=1.0.0";
		
		postData = data.getBytes( StandardCharsets.UTF_8 );
		
		int postDataLength = postData.length;
		
		url=new URL(" https://services.promostandards.org/WebServiceValidator/ProductPricingandConfiguration/getAvailableLocations_v1");
		
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
		
		File f= new File("C:\\Users\\JAYASRIM\\Desktop\\PandC.xlsx");
		
		FileInputStream inputStream = new FileInputStream(f);
		
		Workbook wb=WorkbookFactory.create(inputStream);
		
		Sheet sheet=wb.createSheet("Response1");
		
		System.out.println("Status code="+con.getResponseCode());
		
        InputStream is = con.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		HashMap<String,String> hm=new HashMap<String,String>();
		
		String inputLine;
		
		int row=0;
		int count=1;
	        while ((inputLine = br.readLine()) != null) 
	        {	        	
	        	row=row+1;
	            hm.put("str"+count, inputLine);
	            count=count+1;
	        }
	        
	        System.out.println(row);
	        
	        for(int i=1;i<=row;i++)
	        {
	        	System.out.println(hm.get("str"+i));
	        	
	        	sheet.createRow(i).createCell(1).setCellValue(hm.get("str"+i));
	        	
	        	sheet.autoSizeColumn(i);
	        	        	
	        	
	        }
	        
	        
	   
	        FileOutputStream fout=new FileOutputStream(new File("C:\\Users\\JAYASRIM\\Desktop\\PandC.xlsx"));
	        
	        wb.write(fout);
	        
		    hm.clear();

	}

}
