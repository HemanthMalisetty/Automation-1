package com.mk.onchip;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlGenerator {
	
	@Test
	public static void gen()throws Exception
	{
		Properties prop=new Properties();
		
		File f1=new File(".//Input//images.properties");
		
		FileInputStream fis1=new FileInputStream(f1);
		
		prop.load(fis1);
		
        File f2=new File(".//Input//ItemNumbers.properties");
		
		FileInputStream fis2=new FileInputStream(f2);
		
		prop.load(fis2);
		
        File f3=new File(".//Input//Required_Line_and_Items.properties");
		
		FileInputStream fis3=new FileInputStream(f3);
		
		prop.load(fis3);
	
		int reps=Integer.parseInt(prop.getProperty("Required_Files"));
		
		int lineitems=Integer.parseInt(prop.getProperty("Reqiured_line_items"));
		
		String date=Addition.getdate();
		
		for(int i=1;i<=reps;i++)
		{
			DocumentBuilderFactory docbuild=DocumentBuilderFactory.newInstance();
			
			DocumentBuilder document=docbuild.newDocumentBuilder();
			
			//creating root element
			Document doc = document.newDocument();
			
			Element rootElement = doc.createElement("order");
			doc.appendChild(rootElement);
			
			System.out.println();
			
			Element custpo = doc.createElement("cust-po");
			rootElement.appendChild(custpo);
			
			System.out.println();
			
			Element ordertype = doc.createElement("order-type");
			ordertype.appendChild(doc.createTextNode("SP"));
			custpo.appendChild(ordertype);
			
			System.out.println();
			
			Element ponumber = doc.createElement("po-number");
			ponumber.appendChild(doc.createTextNode(date+"-"+Integer.toString(lineitems)+"L"+"-"+i));
			custpo.appendChild(ponumber);
			
			System.out.println();
			
			Element recoderdeco = doc.createElement("reorder-deco-po-num");
			custpo.appendChild(recoderdeco);
			
			System.out.println();
			
			Element whse = doc.createElement("whse");
			custpo.appendChild(whse);
			
			System.out.println();
			
			Element custnumber = doc.createElement("cust-number");
			custnumber.appendChild(doc.createTextNode("1281175"));
			custpo.appendChild(custnumber);
			
			System.out.println();
			
			Element carriercode = doc.createElement("carrier-code");
			carriercode.appendChild(doc.createTextNode(prop.getProperty("carriercode")));
			custpo.appendChild(carriercode);
			
			System.out.println();
			
			Element shiptocompany = doc.createElement("ship-to-company");
			shiptocompany.appendChild(doc.createTextNode("QA Test Co"));
			custpo.appendChild(shiptocompany);
			
			System.out.println();
			
			Element shiptoattn = doc.createElement("ship-to-attn");
			shiptoattn.appendChild(doc.createTextNode("John"));
			custpo.appendChild(shiptoattn);
			
			System.out.println();
			
			Element shiptoaddr1 = doc.createElement("ship-to-addr1");
			shiptoaddr1.appendChild(doc.createTextNode("123 Main St"));
			custpo.appendChild(shiptoaddr1);
			
			System.out.println();
			
			Element shiptoaddr2 = doc.createElement("ship-to-addr2");
			//shiptoaddr2.appendChild(doc.createTextNode("123 Main"));
			custpo.appendChild(shiptoaddr2);
			
			System.out.println();
			
			Element shiptocity = doc.createElement("ship-to-city");
			shiptocity.appendChild(doc.createTextNode("Langhorne"));
			custpo.appendChild(shiptocity);
			
			System.out.println();
			
			Element shiptostate = doc.createElement("ship-to-state");
			shiptostate.appendChild(doc.createTextNode("PA"));
			custpo.appendChild(shiptostate);
			
			System.out.println();
			
			Element shiptozip = doc.createElement("ship-to-zip");
			shiptozip.appendChild(doc.createTextNode("19047"));
			custpo.appendChild(shiptozip);
			
			System.out.println();
			
			Element brodervendornum = doc.createElement("broder-vendor-num");
			custpo.appendChild(brodervendornum);
			
			System.out.println();
			
			Element comment = doc.createElement("comment");
			comment.appendChild(doc.createTextNode(Integer.toString(lineitems)+" Line Items"));
			custpo.appendChild(comment);
			
			System.out.println();
			
			Element emailaddr = doc.createElement("email-addr");
			emailaddr.appendChild(doc.createTextNode("abdecotest@gmail.com"));
			custpo.appendChild(emailaddr);
			
			System.out.println();
			
			Element shipdate = doc.createElement("ship-date");
			shipdate.appendChild(doc.createTextNode(prop.getProperty("shipdate")));
			custpo.appendChild(shipdate);
			
			System.out.println();
			
			Element items = doc.createElement("Items");
			custpo.appendChild(items);
			
			System.out.println();
			
			for(int j=1;j<=lineitems;j++)
			{
				Element item = doc.createElement("Item");
				items.appendChild(item);
				
				System.out.println();
				
				Element itemnumber = doc.createElement("item-number");
				itemnumber.appendChild(doc.createTextNode(prop.getProperty("Item_Number_"+j)));
				item.appendChild(itemnumber);
				
				System.out.println();
				
				Element quantity = doc.createElement("quantity");
				quantity.appendChild(doc.createTextNode("5"));
				item.appendChild(quantity);
				
				System.out.println();
				
				Element custitemnumber = doc.createElement("cust-item-number");
				item.appendChild(custitemnumber);
				
				System.out.println();
				
				Element polinenumber = doc.createElement("po-line-number");
				polinenumber.appendChild(doc.createTextNode(Integer.toString(j)));
				item.appendChild(polinenumber);
				
				System.out.println();
				
				Element addonitems = doc.createElement("add-on-items");
				item.appendChild(addonitems);
				
				System.out.println();
				
				Element addonitem = doc.createElement("add-on-item");
				addonitems.appendChild(addonitem);
				
				System.out.println();
				
				Element addonitemnumber = doc.createElement("add-on-item-number");
				addonitem.appendChild(addonitemnumber);
				
				System.out.println();
				
				Element decorations = doc.createElement("decorations");
				item.appendChild(decorations);
				
				System.out.println();
				
				Element decoration = doc.createElement("decoration");
				decorations.appendChild(decoration);
				
				System.out.println();
				
				Element decotype = doc.createElement("deco-type");
				decotype.appendChild(doc.createTextNode("S"));
				decoration.appendChild(decotype);
				
				System.out.println();
				
				Element decorationid = doc.createElement("decoration-id");
				decoration.appendChild(decorationid);
				
				System.out.println();
				
				Element productionartfilename = doc.createElement("production-art-file-name");
				productionartfilename.appendChild(doc.createTextNode(prop.getProperty("image_"+j)));
				decoration.appendChild(productionartfilename);
				
				System.out.println();
				
				Element placementcode = doc.createElement("placement-code");
				placementcode.appendChild(doc.createTextNode(prop.getProperty("placementcode")));
				decoration.appendChild(placementcode);
				
				System.out.println();
				
				Element width = doc.createElement("width");
				width.appendChild(doc.createTextNode("300"));
				decoration.appendChild(width);
				
				System.out.println();
				
				Element height = doc.createElement("height");
				height.appendChild(doc.createTextNode("120"));
				decoration.appendChild(height);
				
				System.out.println();
				
				Element colors = doc.createElement("colors");
				decoration.appendChild(colors);
				
				System.out.println();
				
				Element color = doc.createElement("color");
				colors.appendChild(color);
				
				System.out.println();
				
				Element threadchart = doc.createElement("thread-chart");
				color.appendChild(threadchart);
				
				System.out.println();
				
				Element threadstop = doc.createElement("thread-stop");
				color.appendChild(threadstop);
				
				System.out.println();
				
				Element colornumber = doc.createElement("color-number");
				color.appendChild(colornumber);
				
			
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File(".//Output//alp-1281175-"+date+"-"+Integer.toString(lineitems)+"L-"+i+".xml"));
			
			transformer.transform(source, result);
			
			
		}
	}


}
