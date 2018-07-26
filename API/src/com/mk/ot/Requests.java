package com.mk.ot;

import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Requests {
	
	
	static XSSFSheet sheet=null;
	static int querytype=0;
		
	public static HashMap<String,String> urlparams(String value)throws Exception
	{

		
		HashMap<String,String> hm=new HashMap<String,String>();
		
		if(value.equalsIgnoreCase("orderstatus_getorderstatustypes"))
		{
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//order_status_types.xlsx");
			
			String path="http://services.promostandards.org/WebServiceValidator/OrderStatus/getOrderStatusTypes_v1";
			
			hm.put("path",path);
						
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(0)!=null)
				{
				
			String url="endpoint=https://devservices.alphabroder.com/OS/OrderStatus.svc&requestXml="+"<GetOrderStatusTypesRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/OrderStatusService/1.0.0/\">\r\n" + 
					"  <wsVersion>"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
					"  <id>"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
					"  <password>"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
					"</GetOrderStatusTypesRequest>"+"&webServiceName=OrderStatus&operationName=getOrderStatusDetails&version=1.0.0";
		   
			hm.put("urlparams"+i,url);
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
			
		    }
			return hm;  
			
			
		}
		else if(value.equalsIgnoreCase("orderstatus_getorderstatusdetails"))
		{
			
			sheet=Utility_methods.loadExcel(".//Input//Requestparams//order_status_details.xlsx");
			
			String path="http://services.promostandards.org/WebServiceValidator/OrderStatus/getOrderStatusDetails_v1";
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
			
				if(sheet.getRow(i).getCell(0)!=null)
				{
		    querytype=Integer.parseInt(sheet.getRow(i).getCell(4).getStringCellValue());
					
			if(querytype==3)
			{
				String url1="endpoint=https://devservices.alphabroder.com/OS/OrderStatus.svc&requestXml="+"<GetOrderStatusDetailsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/OrderStatusService/1.0.0/\">\r\n" + 
						"  <wsVersion>"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id>"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password>"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"  <queryType>"+sheet.getRow(i).getCell(4)+"</queryType>\r\n" + 
						"  <referenceNumber>"+sheet.getRow(i).getCell(5)+"</referenceNumber>\r\n" + 
						"  <statusTimeStamp>"+sheet.getRow(i).getCell(6)+"</statusTimeStamp>\r\n" + 
						"</GetOrderStatusDetailsRequest>"+"&webServiceName=OrderStatus&operationName=getOrderStatusDetails&version=1.0.0";
				
				hm.put("urlparams"+i,url1);
			}
		   
			else
			{
				String url="endpoint=https://devservices.alphabroder.com/OS/OrderStatus.svc&requestXml="+"<GetOrderStatusDetailsRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/OrderStatusService/1.0.0/\">\r\n" + 
						"  <wsVersion>"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id>"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password>"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"  <queryType>"+sheet.getRow(i).getCell(4)+"</queryType>\r\n" + 
						"  <referenceNumber>"+sheet.getRow(i).getCell(5)+"</referenceNumber>\r\n" + 
						"  <statusTimeStamp xsi:nil=\"true\" />\r\n" + 
						"</GetOrderStatusDetailsRequest>"+"&webServiceName=OrderStatus&operationName=getOrderStatusDetails&version=1.0.0";
				
				hm.put("urlparams"+i,url);			
			}
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
			
		    }
			return hm;
		}
		else if(value.equalsIgnoreCase("inventory_getfiltervalues"))
		{
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//Inventory_getfiltervalues.xlsx");
			
			String path="http://services.promostandards.org/WebServiceValidator/Inventory/getFilterValues_v1";
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
			
				if(sheet.getRow(i).getCell(0)!=null)
				{
			
			String url="endpoint=https://devservices.alphabroder.com/inventory/InventoryService.svc&requestXml="+"<GetFilterValuesRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/InventoryService/1.0.0/\">\r\n" + 
					"  <wsVersion>"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
					"  <id>"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
					"  <password>"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
					"  <productID>"+sheet.getRow(i).getCell(4)+"</productID>\r\n" + 
					"  <productIDtype>Token1</productIDtype>\r\n" + 
					"</GetFilterValuesRequest>"+"&webServiceName=Inventory&operationName=getFilterValues&version=1.2.1";
			
			hm.put("urlparams"+i,url);
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
			}
		    
			return hm;
			
		}
		else if(value.equalsIgnoreCase("inventory_getinventorylevels"))
		{
			String path="http://services.promostandards.org/WebServiceValidator/Inventory/getInventoryLevels_v1";
		
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//Inventory_getinventorylevels.xlsx");
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
							
				if(sheet.getRow(i).getCell(0)!=null)
				{
				
				String url="endpoint=https://devservices.alphabroder.com/inventory/InventoryService.svc&requestXml="+"<Request xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/InventoryService/1.0.0/\">\r\n" + 
						"  <wsVersion>"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id>"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password>"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"  <productID>"+sheet.getRow(i).getCell(4)+"</productID>\r\n" + 
						"  <productIDtype>"+sheet.getRow(i).getCell(5)+"</productIDtype>\r\n" + 
						"  <FilterColorArray>\r\n" + 
						"    <filterColor>"+sheet.getRow(i).getCell(6)+"</filterColor>\r\n" + 
						"    <filterColor>"+sheet.getRow(i).getCell(7)+"</filterColor>\r\n" + 
						"  </FilterColorArray>\r\n" + 
						"  <FilterSizeArray>\r\n" + 
						"    <filterSize>"+sheet.getRow(i).getCell(8)+"</filterSize>\r\n" + 
						"    <filterSize>"+sheet.getRow(i).getCell(9)+"</filterSize>\r\n" + 
						"  </FilterSizeArray>\r\n" + 
						"  <FilterSelectionArray>\r\n" + 
						"    <filterSelection>Token1</filterSelection>\r\n" + 
						"    <filterSelection>Token2</filterSelection>\r\n" + 
						"  </FilterSelectionArray>\r\n" + 
						"</Request>"+"&webServiceName=Inventory&operationName=getInventoryLevels&version=1.2.1";
				
				hm.put("urlparams"+i, url);
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
				
			}
			
			return hm;
		}
		else if(value.equalsIgnoreCase("productdata_getproduct"))
		{
			String path="http://services.promostandards.org/WebServiceValidator/ProductData/getProduct_v1";
			
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//ProductData_getproduct.xlsx");
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
			    if(sheet.getRow(i).getCell(0)!=null)
			    {
				String url="endpoint=https://devservices.alphabroder.com/ProductData/ProductDataService.svc&requestXml="+"<GetProductRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/\">\r\n" + 
						"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"  <localizationCountry xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(4)+"</localizationCountry>\r\n" + 
						"  <localizationLanguage xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(5)+"</localizationLanguage>\r\n" + 
						"  <productId xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(6)+"</productId>\r\n" + 
						"  <partId xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\"></partId>\r\n" + 
						"  <colorName>Token1</colorName>\r\n" + 
						"  <ApparelSizeArray>\r\n" + 
						"    <ApparelSize xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">\r\n" + 
						"      <apparelStyle>Unisex</apparelStyle>\r\n" + 
						"      <labelSize>OSFA</labelSize>\r\n" + 
						"      <customSize>customSize1</customSize>\r\n" + 
						"    </ApparelSize>\r\n" + 
						"    <ApparelSize xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">\r\n" + 
						"      <apparelStyle>Youth</apparelStyle>\r\n" + 
						"      <labelSize>6XS</labelSize>\r\n" + 
						"      <customSize>customSize2</customSize>\r\n" + 
						"    </ApparelSize>\r\n" + 
						"  </ApparelSizeArray>\r\n" + 
						"</GetProductRequest>"+"&webServiceName=ProductData&operationName=getProduct&version=1.0.0";
				
				hm.put("urlparams"+i,url);
			    }
			    else
			    {
			    	System.out.println("Some active cells with no data has been found in row "+i);
			    }
				
			}
			return hm;
		}
		
		else if(value.equalsIgnoreCase("productdata_getproductdatemodified"))
		{
			
            String path="http://services.promostandards.org/WebServiceValidator/ProductData/getProductDateModified_v1";
			
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//ProductData_getproductdatemodified.xlsx");
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(0)!=null)
				{
				
				String url="<GetProductDateModifiedRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/\">\r\n" + 
						"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"  <changeTimeStamp xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(4)+"</changeTimeStamp>\r\n" + 
						"</GetProductDateModifiedRequest>";
				
				hm.put("urlparams"+i,url);
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
			}
			
			return hm;
		}
		
		else if(value.equalsIgnoreCase("productdata_getproductcloseout"))
		{
			
            String path="http://services.promostandards.org/WebServiceValidator/ProductData/getProductCloseOut_v1";
			
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//ProductData_getproductcloseout.xlsx");
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				
				if(sheet.getRow(i).getCell(0)!=null)
				{
				String url="endpoint=https://devservices.alphabroder.com/ProductData/ProductDataService.svc&requestXml="+"<GetProductCloseOutRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/\">\r\n" + 
						"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"</GetProductCloseOutRequest>"+"&webServiceName=ProductData&operationName=getProductCloseOut&version=1.0.0";
				
				hm.put("urlparams"+i,url);
				}
				else
				{
					System.out.println("Some active cells with no data has been found in row "+i);
				}
			}
			
			return hm;
		}
		
		else if(value.equalsIgnoreCase("productdata_getproductsellable"))
		{
			
            String path="http://services.promostandards.org/WebServiceValidator/ProductData/getProductSellable_v1";
			
            sheet=Utility_methods.loadExcel(".//Input//Requestparams//ProductData_getproductsellable.xlsx");
			
			hm.put("path",path);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(0)!=null)
				{
					
				String url="endpoint=https://devservices.alphabroder.com/ProductData/ProductDataService.svc&requestXml="+"<GetProductSellableRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/\">\r\n" + 
						"  <wsVersion xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(1)+"</wsVersion>\r\n" + 
						"  <id xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(2)+"</id>\r\n" + 
						"  <password xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(3)+"</password>\r\n" + 
						"  <productId xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(4)+"</productId>\r\n" + 
						"  <partId xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">"+sheet.getRow(i).getCell(5)+"</partId>\r\n" + 
						"  <isSellable xmlns=\"http://www.promostandards.org/WSDL/ProductDataService/1.0.0/SharedObjects/\">true</isSellable>\r\n"+
						"  </GetProductSellableRequest>"+"&webServiceName=ProductData&operationName=getProductSellable&version=1.0.0";
				
				hm.put("urlparams"+i,url);
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

