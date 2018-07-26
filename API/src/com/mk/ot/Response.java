package com.mk.ot;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Response {
	
	static XSSFSheet sheet=null;
	static Boolean flag=null;
	
	public static Boolean validate(String querytype)
	{
		int i=0;
		if(querytype.equalsIgnoreCase("orderstatus_getorderstatusdetails"))
		{
			i=0;
		}
		else if(querytype.equalsIgnoreCase("orderstatus_getorderstatustypes"))
		{
			i=1;
		}
		else if(querytype.equalsIgnoreCase("inventory_getfiltervalues"))
		{
			i=2;
		}
		else if(querytype.equalsIgnoreCase("inventory_getinventorylevels"))
		{
			i=3;
		}
		else if(querytype.equalsIgnoreCase("productdata_getproduct"))
		{
			i=4;
		}
		else if(querytype.equalsIgnoreCase("productdata_getproductdatemodified"))
		{
			i=5;
		}
		else if(querytype.equalsIgnoreCase("productdata_getproductcloseout"))
		{
			i=6;
		}
		else if(querytype.equalsIgnoreCase("productdata_getproductsellable"))
		{
			i=7;
		}
		else
		{
			return null;
		}
		File f=new File(".\\Data_to_be_validated\\validate.xml");
		
		Document doc=Utility_methods.xmlvalidation(f);
		
		sheet=Utility_methods.loadExcel(".\\Data_to_be_validated\\Tags.xlsx");
				
		int lastcell=sheet.getRow(i).getLastCellNum();
		
		for(int j=1;j<lastcell;j++)
		{
		
			String tags=sheet.getRow(i).getCell(j).getStringCellValue();
		
			String[] tag=tags.split("/");
			
		    Element ele=Utility_methods.getParentNode(doc, tag[0]);
		
		    if(Utility_methods.getChildNodeData(ele, tag[1])!=null)
		    {
		    	flag=true;
		    }
		    else
		    {
		    	flag=false;
		    	return flag;
		    }
		
		}
				
		return flag;
	}
	

}
