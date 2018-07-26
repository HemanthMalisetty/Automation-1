package com.mk.ot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Utility_methods {
	
	static File enduserdata=null;
	static FileInputStream fis1=null;
	static XSSFWorkbook wb=null;
	static XSSFSheet sheet=null;
	static ExtentReports extent=null;
	static ExtentTest logger=null;
	static Boolean flag=null;
	
	public static void writeFile(InputStream is)
	{
		  
		    try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder builder = factory.newDocumentBuilder();
				
				Document doc = builder.parse(is);

				TransformerFactory tfactory = TransformerFactory.newInstance();
				
				Transformer xform = tfactory.newTransformer();

				File myOutput = new File(".//Data_to_be_validated//validate.xml");
				
				xform.transform(new DOMSource(doc), new StreamResult(myOutput));
				
			} catch (TransformerConfigurationException e) {
				
				e.printStackTrace();
				
			} catch (ParserConfigurationException e) {
				
				e.printStackTrace();
				
			} catch (SAXException e) {
				
				e.printStackTrace();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			} catch (TransformerFactoryConfigurationError e) {
				
				e.printStackTrace();
				
			} catch (TransformerException e) {
				
				e.printStackTrace();
				
			}
	        
	        
	}
	
	public static Properties loadfile(String path)
	{
		try {
			Properties prop=new Properties();
			
			File file=new File(path);
			
			FileInputStream fis=new FileInputStream(file);
			
			prop.load(fis);
			
			return prop;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
			return null;
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public static Document xmlvalidation(File f)
	{
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			dbf.setValidating(false);
			
			dbf.setNamespaceAware(true);
			
			DocumentBuilder db = dbf.newDocumentBuilder();

			db.setErrorHandler(new SimpleErrorHandler());
			
			Document doc = db.parse(f);

			doc.getDocumentElement().normalize();
			
			return doc;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
			return null;
		}
	}
	
	public static Element getParentNode(Document doc,String parenttag)
	{
		try
		{
		NodeList nodeLst = doc.getElementsByTagName(parenttag);
		
		Node parentNode = nodeLst.item(randInt(0,(nodeLst.getLength())-1));
		
		Element parentElmnt = (Element) parentNode;
		
		return parentElmnt;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String getChildNodeData(Element element,String childtag)
	{
		if(element!=null)
		{
		 try
		 {
			NodeList nodelist = element.getElementsByTagName(childtag);
		    Element ele = (Element) nodelist.item(0);

		    NodeList childnode = ele.getChildNodes();

		    String dataInTag=((Node)childnode.item(0)).getNodeValue().trim();
		
		    return dataInTag;
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		}
		else
		{
			return null;
		}
		
	}
	public static int randInt(int min, int max)
	{
		Random rand=new Random();
		
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		return randomNum;
	}
	
	public static XSSFSheet loadExcel(String path)
	{
		try {
			enduserdata=new File(path);
			
			fis1=new FileInputStream(enduserdata);
			
			wb=new XSSFWorkbook(fis1);
			
			sheet=wb.getSheetAt(0);
			
			return sheet;
		}  
		catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static ExtentTest startReport(String nameOfCust)
	{
		extent = new ExtentReports (System.getProperty("user.dir") +"/Reports/report.html", false);
		
		logger=extent.startTest(nameOfCust);
		
		return logger;
			
	}
	
	public static void clearfile(File f) throws Exception
	{
		PrintWriter writer = new PrintWriter(f);
        writer.print("");
        writer.close();
	 	
	}
	
	public static void generateResult(ExtentTest logger)
	{
		    extent.endTest(logger);
			extent.flush();
			extent.close();
	}
	
	public static Boolean deleteReport(File f)
	{
		if(f.exists())
		{
		   Boolean flag=f.delete();
		
		   return flag;
		}
		else
		{
			flag=true;
			
			return flag;
		}
		
		
	}

}
