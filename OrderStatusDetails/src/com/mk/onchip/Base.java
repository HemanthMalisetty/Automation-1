package com.mk.onchip;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Base {
	
	public static File enterdata(String path,String outdata) throws Exception
	{
		File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(outdata);
        fileWriter.close();
        return file;
        
	}
	
	public static void clearfile(File f) throws Exception
	{
		PrintWriter writer = new PrintWriter(f);
        writer.print("");
        writer.close();
	 	
	}
	
	public static Document parsing(File f) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(f);

		doc.getDocumentElement().normalize();

		return doc;
	}
	
	public static Boolean verify_correct_xml_resp(Document doc)
	{
		try
		{
		
		Boolean clk,clk1,clk2,clk3,clk4;
		
		NodeList nodeLst = doc.getElementsByTagName("OrderStatus");

		Node fstNode = nodeLst.item(0);
		
		Element fstElmnt = (Element) fstNode;
		
		NodeList purchaseorderno = fstElmnt.getElementsByTagName("purchaseOrderNumber");
		Element ponumber = (Element) purchaseorderno.item(0);

		NodeList polist = ponumber.getChildNodes();

		String purchaseOrderNumber=((Node)polist.item(0)).getNodeValue().trim();
		
		if(purchaseOrderNumber!=null)
		{
			clk=true;
		}
		else
		{
			clk=false;
			
		}

		NodeList factoryOrderNumber = fstElmnt.getElementsByTagName("factoryOrderNumber");
		Element facnumber = (Element) factoryOrderNumber.item(0);

		NodeList faclist = facnumber.getChildNodes();
		
		String factryOrderNumber=((Node)faclist.item(0)).getNodeValue().trim();
		
		if(factryOrderNumber!=null)
		{
			clk1=true;
		}
		else
		{
			clk1=false;
			
		}

				
		NodeList statid = fstElmnt.getElementsByTagName("statusID");
		Element stid = (Element) statid.item(0);

		NodeList stlist = stid.getChildNodes();
		
        String statusid=((Node)stlist.item(0)).getNodeValue().trim();
        
        if(statusid!=null)
		{
			clk2=true;
		}
		else
		{
			clk2=false;
			
		}
		
		NodeList statname = fstElmnt.getElementsByTagName("statusName");
		Element stnam = (Element) statname.item(0);

		NodeList stnamlist = stnam.getChildNodes();

		String statusname=((Node)stnamlist.item(0)).getNodeValue().trim();
			
			if(statusname!=null)
			{
				clk3=true;
			}
			else
			{
				clk3=false;
				
			}

		if(clk==true||clk1==true||clk2==true||clk3==true )
		{
			clk4=true;
		}
		else
		{
			clk4=false;
		}
			
		return clk4;
		}
		catch(NullPointerException e)
		{
			return false;
		}
	}

}
