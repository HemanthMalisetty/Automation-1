package com.ab.onchip;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLUtils {
	File xmlFileName;
	Document doc;
	XPath xPath;
	String poNum;
	String shipDate;
	String custNumber;
	String shiptocompany;
	String shiptoattn;
	String shiptoaddress1;
	String shiptoaddress2;
	String shiptocity;
	String shiptostate;
	String shiptozip;
	String comment;
	String email;
	String itemnumber;
	String imagename;
	String placementcode;
	Properties prop;

	public XMLUtils(File xmlFileName){
		try {
			this.xmlFileName = xmlFileName;
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			doc = b.parse(xmlFileName);
			xPath = XPathFactory.newInstance().newXPath();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public Reader load()
	{
		Reader red=new Reader();
		
		prop=new Properties();
		
		prop=red.loadfiles();
		
		return red;
		
	}
	
	public void updatePONum() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/po-num").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(poNum);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateshiptoattn() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-attn").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptoattn);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	
	public void updateshiptocomany() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-company").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptocompany);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	public void updateshiptoaddress1() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-addr1").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptoaddress1);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateshiptoaddress2() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-addr2").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptoaddress2);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateshiptocity() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-city").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptocity);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateshiptostate() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-state").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptostate);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateshiptozip() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-to-zip").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shiptozip);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updatecomment() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/comment").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(comment);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateemail() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/email-addr").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(email);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateShipDate() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/ship-date").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(shipDate);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	public void updateitemnumber() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/items/item/item-number").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(itemnumber);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateimage() throws Exception {
		try {
			
			Node startDateNode = (Node) xPath.compile("/order/cust-po/items/item/decorations/decoration/production-art-file-name").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(imagename);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}
	
	public void updateplacementcode() throws Exception {
		try {
			
			Node startDateNode = (Node) xPath.compile("/order/cust-po/items/item/decorations/decoration/placement-code").evaluate(doc, XPathConstants.NODE);
			startDateNode.setTextContent(placementcode);
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	public void typeitemstag(String itemnumber,int i,int k)
	{
		NodeList nodes = doc.getElementsByTagName("item");
		
		Element item=doc.createElement("item");
		
		Element itemnumb=doc.createElement("item-number");
		itemnumb.appendChild(doc.createTextNode(itemnumber));
		item.appendChild(itemnumb);
		
		Element quant=doc.createElement("quantity");
		quant.appendChild(doc.createTextNode("10"));
		item.appendChild(quant);
		
		Element custitemnum=doc.createElement("cust-item-num");
		item.appendChild(custitemnum);
		
		Element polinenum=doc.createElement("po-line-num");
		polinenum.appendChild(doc.createTextNode(Integer.toString(i)));
		item.appendChild(polinenum);
		
		Element addonitems=doc.createElement("add-on-items");
		item.appendChild(addonitems);
		
		Element addonitem=doc.createElement("add-on-item");
		addonitems.appendChild(addonitem);
		
		Element addonitemnumber=doc.createElement("add-on-item-number");
		addonitem.appendChild(addonitemnumber);
		
		Element decorations=doc.createElement("decorations");
		item.appendChild(decorations);
		
		Element decoration=doc.createElement("decoration");
		decorations.appendChild(decoration);
		
		Element decotype=doc.createElement("deco-type");
		decotype.appendChild(doc.createTextNode("S"));
		decoration.appendChild(decotype);
		
		Element decorationid=doc.createElement("decoration-id");
		decoration.appendChild(decorationid);
		
		Element productionartfilename=doc.createElement("production-art-file-name");
		productionartfilename.appendChild(doc.createTextNode("cd"+k+".png"));
		decoration.appendChild(productionartfilename);
		
		Element placementcod=doc.createElement("placement-code");
		placementcod.appendChild(doc.createTextNode(prop.getProperty("placementcode")));
		decoration.appendChild(placementcod);
		
		Element width=doc.createElement("width");
		width.appendChild(doc.createTextNode(prop.getProperty("width")));
		decoration.appendChild(width);
		
		Element height=doc.createElement("height");
		height.appendChild(doc.createTextNode(prop.getProperty("height")));
		decoration.appendChild(height);
		
		Element colors=doc.createElement("colors");
		decoration.appendChild(colors);
		
		Element color=doc.createElement("color");
		colors.appendChild(color);
		
		Element threadchart=doc.createElement("thread-chart");
		color.appendChild(threadchart);
		
		Element threadstop=doc.createElement("thread-stop");
		color.appendChild(threadstop);
		
		Element colornumber=doc.createElement("color-number");
		color.appendChild(colornumber);
		
		nodes.item(0).getParentNode().insertBefore(item, nodes.item(0));
		
	}
	
	public String getCustNumber() throws Exception {
		try {
			Node startDateNode = (Node) xPath.compile("/order/cust-po/cust-number").evaluate(doc, XPathConstants.NODE);
			custNumber = startDateNode.getTextContent();
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return custNumber;
	}

	public boolean saveXML(String output,String fileName) {
		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource domSource = new DOMSource(doc);
			StreamResult sr=new StreamResult(new File(output,fileName));
			tf.transform(domSource, sr);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return true;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public void setShiptocompany(String shiptocompany) {
		this.shiptocompany = shiptocompany;
	}
	public void setShiptoattn(String shiptoattn) {
		this.shiptoattn = shiptoattn;
	}
	public void setShiptoaddress1(String shiptoaddress1) {
		this.shiptoaddress1 = shiptoaddress1;
	}
	public void setShiptoaddress2(String shiptoaddress2) {
		this.shiptoaddress2 = shiptoaddress2;
	}
	public void setShiptocity(String shiptocity) {
		this.shiptocity = shiptocity;
	}
	public void setShiptostate(String shiptostate) {
		this.shiptostate = shiptostate;
	}
	public void setShiptozip(String shiptozip) {
		this.shiptozip = shiptozip;
	}
	public void setcomment(String comment) {
		this.comment = comment;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public void setitemnumber(String itemnumber) {
		this.itemnumber = itemnumber;
	}
	public void setimagename(String imagename) {
		this.imagename = imagename;
	}
	public void setplacementcode(String placementcode) {
		this.placementcode = placementcode;
	}
}
