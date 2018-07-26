import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class GetInventoryLevels {

	static URL url=null;
	static Properties prop=null;
	static File infile,cred=null;
	static FileInputStream fis,fis1=null;
		
	public static void main(String[] args) throws Exception {
		
		String path="http://services.promostandards.org/WebServiceValidator/Inventory/getInventoryLevels_v1";
		
		prop = new Properties();

		infile = new File(".\\Input\\config.properties");

		fis = new FileInputStream(infile);

		prop.load(fis); 
		
		cred=new File(".\\Input\\xmlcredentials.properties");
		
		fis1 = new FileInputStream(cred);
		
		prop.load(fis1);
				
		String urlparams=prop.getProperty("param1")+"="+prop.getProperty("param2")+"&"+prop.getProperty("param3")+"="+"<Request xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/InventoryService/1.0.0/\">\r\n" + 
				"  <wsVersion>"+prop.getProperty("wsVersion")+"</wsVersion>\r\n" + 
				"  <id>"+prop.getProperty("id")+"</id>\r\n" + 
				"  <password>"+prop.getProperty("password")+"</password>\r\n" + 
				"  <productID>"+prop.getProperty("productID")+"</productID>\r\n" + 
				"  <productIDtype>Token1</productIDtype>\r\n" + 
				"  <FilterColorArray>\r\n" + 
				"    <filterColor>"+prop.getProperty("filterColor1")+"</filterColor>\r\n" + 
				"    <filterColor>"+prop.getProperty("filterColor2")+"</filterColor>\r\n" + 
				"  </FilterColorArray>\r\n" + 
				"  <FilterSizeArray>\r\n" + 
				"    <filterSize>"+prop.getProperty("filterSize1")+"</filterSize>\r\n" + 
				"    <filterSize>"+prop.getProperty("filterSize2")+"</filterSize>\r\n" + 
				"  </FilterSizeArray>\r\n" + 
				"  <FilterSelectionArray>\r\n" + 
				"    <filterSelection>Token1</filterSelection>\r\n" + 
				"    <filterSelection>Token2</filterSelection>\r\n" + 
				"  </FilterSelectionArray>\r\n" + 
				"</Request>"+"&"+prop.getProperty("param4")+"="+prop.getProperty("param5")+"&"+prop.getProperty("param6")+"="+prop.getProperty("param7")+"&"+prop.getProperty("param8")+"="+prop.getProperty("param9");
		
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
	            System.out.println(inputLine);
		  
	}
}
