package com.ab.onchip;

import java.io.File;
import java.util.Properties;

import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

public class UpdateXML {
	
	Properties prop=null;
	String xmlFileName;
	String poNum;
	String shipDate;
	String custNumber;
	String[] arr;
	String filename;
	String output;
	String fileEndPart;
	String completename;
	@Test
	public void enter()
	{
		try {
			File f=new File(".\\Required\\ALP-1281175-QACD082417161-1style2images.xml");
			
			filename=f.getName();
			
			Reader rs=new Reader();
			
			prop=rs.loadfiles();
			
			arr=rs.itemnumber();
			
			XMLUtils xml=new XMLUtils(f);
			
			int files=Integer.parseInt(prop.getProperty("Files_Required"));
			
			int items=Integer.parseInt(prop.getProperty("Items_Required"));
			
			for(int i=1;i<=files;i++)
			{
			
				long ponum=System.currentTimeMillis();
			    xml.setPoNum("QACD"+ponum);
			    xml.updatePONum();
			    
			    xml.setShiptocompany(prop.getProperty("shiptocompany"));
			    xml.updateshiptocomany();
			    
			    xml.setShiptoattn(prop.getProperty("shiptoattn"));
			    xml.updateshiptoattn();
			    
			    xml.setShiptoaddress1(prop.getProperty("shiptoaddress1"));
			    xml.updateshiptoaddress1();
			    
			    xml.setShiptoaddress2(prop.getProperty("shiptoaddress2"));
			    xml.updateshiptoaddress2();
			    
			    xml.setShiptocity(prop.getProperty("shiptocity"));
			    xml.updateshiptocity();
			    
			    xml.setShiptostate(prop.getProperty("shiptostate"));
			    xml.updateshiptostate();
			    
			    xml.setShiptozip(prop.getProperty("shiptozip"));
			    xml.updateshiptozip();
			    
			    xml.setcomment(prop.getProperty("comment"));
			    xml.updatecomment();
			    
			    xml.setemail(prop.getProperty("email"));
			    xml.updateemail();
			    
			    xml.setShipDate(rs.shipDate());
			    xml.updateShipDate();
			    
			    for(int j=1;j<=items;j++)
			    {
			    
			    if(j==1)
			    {
			    	xml.setitemnumber(arr[1]);
		    		xml.updateitemnumber();
		    		
		    		xml.setimagename("cd"+rs.randInt(1, 5)+".png");
		    		xml.updateimage();
		    		
		    		xml.setplacementcode(prop.getProperty("placementcode"));
		    		xml.updateplacementcode();
			    }
			    
			    else {  
			    
			    	Reader r=xml.load();
			    	xml.typeitemstag(arr[j], j,r.randInt(1, 5));
			    	
			    } 
			    }
			    custNumber = xml.getCustNumber();
				fileEndPart = filename.substring(filename.lastIndexOf("-"));
				completename = "ALP-" + custNumber + "-QACD" + ponum +"-"+ prop.getProperty("comment")+".xml";
				xml.saveXML(prop.getProperty("outputfolder"),completename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	    
	}

}
