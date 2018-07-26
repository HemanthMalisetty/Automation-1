package com.onchip.alpha;

import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Request {
	
	static XSSFSheet sheet=null;
	static Properties prop=null;
	
	public static HashMap<String,String> urlparams(String value)throws Exception
	{
		HashMap<String,String> hm=new HashMap<String,String>();
		
		prop=Utility.loadfile(".//ObjectRepository//ObjectsRep.properties");
		
		if(value.equalsIgnoreCase("ProductPricingandConfiguration_getAvailablelocations"))
		{
			sheet=Utility.loadExcel(".//Input//getAvailablelocations.xlsx");
			
			String path="https://services.promostandards.org/WebServiceValidator/ProductPricingandConfiguration/getAvailableLocations_v1";
        
			hm.put("path", path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(0)!=null)
				{
					String url="endpoint="+prop.getProperty("endpoint")+"&requestXml=<GetAvailableLocationsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/\">\r\n" + 
							"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(1).getStringCellValue()+"</wsVersion>\r\n" + 
							"  <id xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(2).getStringCellValue()+"</id>\r\n" + 
							"  <password xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(3).getStringCellValue()+"</password>\r\n" + 
							"  <productId xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(4).getStringCellValue()+"</productId>\r\n" + 
							"  <localizationCountry xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(5).getStringCellValue()+"</localizationCountry>\r\n" + 
							"  <localizationLanguage xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(6).getStringCellValue()+"</localizationLanguage>\r\n" + 
							"</GetAvailableLocationsRequest>&webServiceName=ProductPricingandConfiguration&operationName=getAvailableLocations&version="+sheet.getRow(i).getCell(1).getStringCellValue();
					
					hm.put("urlparams"+i,url);
					
					
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
			}
			
			return hm;
		}
		else if(value.equalsIgnoreCase("ProductPricingandConfiguration_getDecorationcolors"))
		{
			
            sheet=Utility.loadExcel(".//Input//getDecorationcolors.xlsx");
			
			String path="https://services.promostandards.org/WebServiceValidator/ProductPricingandConfiguration/getDecorationColors_v1";
					
			hm.put("path", path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(0)!=null)
				{
					
					String url1="endpoint="+prop.getProperty("endpoint")+"&requestXml=<GetDecorationColorsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/\">\r\n" + 
							"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(1).getStringCellValue()+"</wsVersion>\r\n" + 
							"  <id xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(2).getStringCellValue()+"</id>\r\n" + 
							"  <password xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(3).getStringCellValue()+"</password>\r\n" + 
							"  <locationId xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(4).getStringCellValue()+"</locationId>\r\n" + 
							"  <productId xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(5).getStringCellValue()+"</productId>\r\n" + 
							"  <decorationId xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">1</decorationId>\r\n" + 
							"  <localizationCountry xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(6).getStringCellValue()+"</localizationCountry>\r\n" + 
							"  <localizationLanguage xmlns=\"http://www.promostandards.org/WSDL/PricingAndConfiguration/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(7).getStringCellValue()+"</localizationLanguage>\r\n" + 
							"</GetDecorationColorsRequest>&webServiceName=ProductPricingandConfiguration&operationName=getDecorationColors&version="+sheet.getRow(i).getCell(1).getStringCellValue();
				
					hm.put("urlparams"+i,url1);
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
		    }
			return hm;
		
	   }
		else 
		{
			return null;
		}
		}

}

