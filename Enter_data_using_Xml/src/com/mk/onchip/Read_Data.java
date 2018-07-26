package com.mk.onchip;

import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Read_Data {

	static String filename;

	public static HashMap<String, String> read() throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();

		File folder = new File(".//Input//");
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles[0].isFile()) {
			String file = listOfFiles[0].getName();

			if (file.endsWith(".xml")) {
				filename = file;

				File f = new File(".//Input//" + filename);

				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

				DocumentBuilder db = dbf.newDocumentBuilder();

				Document doc = db.parse(f);

				doc.getDocumentElement().normalize();

				NodeList nodeLst = doc.getElementsByTagName("cust-po");

				Node fstNode = nodeLst.item(0);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;

					NodeList ordertypelist = fstElmnt.getElementsByTagName("order-type");
					Element ordertype = (Element) ordertypelist.item(0);

					NodeList orderlist = ordertype.getChildNodes();
					// System.out.println("order type : " +
					// ((Node)orderlist.item(0)).getNodeValue().trim());
					map.put("order-type", ((Node) orderlist.item(0)).getNodeValue().trim());

					NodeList ponumberlist = fstElmnt.getElementsByTagName("po-number");
					Element ponumber = (Element) ponumberlist.item(0);

					NodeList polist = ponumber.getChildNodes();
					// System.out.println("po-number : "+((Node)polist.item(0)).getNodeValue());
					map.put("po-number", ((Node) polist.item(0)).getNodeValue());

					NodeList custnumberlist = fstElmnt.getElementsByTagName("cust-number");
					Element custnumber = (Element) custnumberlist.item(0);

					NodeList custnum = custnumber.getChildNodes();
					// System.out.println("cust-number : "+((Node)custnum.item(0)).getNodeValue());
					map.put("cust-number", ((Node) custnum.item(0)).getNodeValue());

					NodeList carriercodelist = fstElmnt.getElementsByTagName("carrier-code");
					Element carriercode = (Element) carriercodelist.item(0);

					NodeList carrier = carriercode.getChildNodes();
					// System.out.println("cust-number : "+((Node)custnum.item(0)).getNodeValue());
					map.put("carrier-code", ((Node) carrier.item(0)).getNodeValue());

					NodeList shiptocompanylist = fstElmnt.getElementsByTagName("ship-to-company");
					Element shiptocompany = (Element) shiptocompanylist.item(0);

					NodeList shiptocom = shiptocompany.getChildNodes();
					// System.out.println("ship to company :
					// "+((Node)shiptocom.item(0)).getNodeValue());
					map.put("ship-to-company", ((Node) shiptocom.item(0)).getNodeValue());

					NodeList shiptoattnlist = fstElmnt.getElementsByTagName("ship-to-attn");
					Element shiptoattn = (Element) shiptoattnlist.item(0);

					NodeList shiptoatt = shiptoattn.getChildNodes();
					// System.out.println("ship to attn :
					// "+((Node)shiptoatt.item(0)).getNodeValue());
					map.put("ship-to-attn", ((Node) shiptoatt.item(0)).getNodeValue());

					NodeList shiptoaddress1list = fstElmnt.getElementsByTagName("ship-to-addr1");
					Element shiptoaddress1 = (Element) shiptoaddress1list.item(0);

					NodeList shiptoaddres1 = shiptoaddress1.getChildNodes();
					// System.out.println("ship to address 1 :
					// "+((Node)shiptoaddres1.item(0)).getNodeValue());
					map.put("ship-to-addr1", ((Node) shiptoaddres1.item(0)).getNodeValue());

					NodeList shiptoaddress2list = fstElmnt.getElementsByTagName("ship-to-addr2");
					Element shiptoaddress2 = (Element) shiptoaddress2list.item(0);

					NodeList shiptoaddres2 = shiptoaddress2.getChildNodes();
					// System.out.println("ship to address 2 :
					// "+((Node)shiptoaddres2.item(0)).getNodeValue());
					map.put("ship-to-addr2", ((Node) shiptoaddres2.item(0)).getNodeValue());

					NodeList shiptocitylist = fstElmnt.getElementsByTagName("ship-to-city");
					Element shiptocity = (Element) shiptocitylist.item(0);

					NodeList shiptocit = shiptocity.getChildNodes();
					// System.out.println("ship to city :
					// "+((Node)shiptocit.item(0)).getNodeValue());
					map.put("ship-to-city", ((Node) shiptocit.item(0)).getNodeValue());

					NodeList shiptostatelist = fstElmnt.getElementsByTagName("ship-to-state");
					Element shiptostate = (Element) shiptostatelist.item(0);

					NodeList shiptostat = shiptostate.getChildNodes();
					// System.out.println("ship to state :
					// "+((Node)shiptostat.item(0)).getNodeValue());
					map.put("ship-to-state", ((Node) shiptostat.item(0)).getNodeValue());

					NodeList shiptoziplist = fstElmnt.getElementsByTagName("ship-to-zip");
					Element shiptozip = (Element) shiptoziplist.item(0);

					NodeList shiptozi = shiptozip.getChildNodes();
					// System.out.println("ship to zip : "+((Node)shiptozi.item(0)).getNodeValue());
					map.put("ship-to-zip", ((Node) shiptozi.item(0)).getNodeValue());

					NodeList commentlist = fstElmnt.getElementsByTagName("comment");
					Element comment = (Element) commentlist.item(0);

					NodeList commen = comment.getChildNodes();
					// System.out.println("comment : "+((Node)commen.item(0)).getNodeValue());
					map.put("comment", ((Node) commen.item(0)).getNodeValue());

					NodeList emaillist = fstElmnt.getElementsByTagName("email-addr");
					Element email = (Element) emaillist.item(0);

					NodeList emai = email.getChildNodes();
					// System.out.println("email : "+((Node)emai.item(0)).getNodeValue());
					map.put("email-addr", ((Node) emai.item(0)).getNodeValue());

					NodeList shipdatelist = fstElmnt.getElementsByTagName("ship-date");
					Element shipdate = (Element) shipdatelist.item(0);

					NodeList shipdat = shipdate.getChildNodes();
					// System.out.println("email : "+((Node)emai.item(0)).getNodeValue());
					map.put("ship-date", ((Node) shipdat.item(0)).getNodeValue());

					NodeList items = doc.getElementsByTagName("Item");

					int totitems = items.getLength();

					for (int i = 0; i < totitems; i++) {
						Node firstitemnode = items.item(i);
						if (firstitemnode.getNodeType() == Node.ELEMENT_NODE) {
							Element fstitem = (Element) firstitemnode;

							NodeList itemnumberlist = fstitem.getElementsByTagName("item-number");
							Element itemnumber = (Element) itemnumberlist.item(0);

							NodeList itemlist = itemnumber.getChildNodes();
							// System.out.println("Item number : "+
							// ((Node)itemlist.item(0)).getNodeValue());
							map.put("item-number " + Integer.toString(i), ((Node) itemlist.item(0)).getNodeValue());

							NodeList quantitylist = fstitem.getElementsByTagName("quantity");
							Element quantity = (Element) quantitylist.item(0);

							NodeList quantlist = quantity.getChildNodes();
							// System.out.println("quantity : "+ ((Node)quantlist.item(0)).getNodeValue());
							map.put("quantity " + Integer.toString(i), ((Node) quantlist.item(0)).getNodeValue());

							NodeList decoration = doc.getElementsByTagName("decoration");

							Node deco = decoration.item(0);

							Element decor = (Element) deco;

							NodeList decotypelist = decor.getElementsByTagName("deco-type");
							Element decorate = (Element) decotypelist.item(0);

							NodeList dectype = decorate.getChildNodes();
							// System.out.println("decotype : "+ ((Node)dectype.item(0)).getNodeValue());
							map.put("deco-type " + Integer.toString(i), ((Node) dectype.item(0)).getNodeValue());

							NodeList placementcodelist = decor.getElementsByTagName("placement-code");
							Element placementcode = (Element) placementcodelist.item(0);

							NodeList pc = placementcode.getChildNodes();
							// System.out.println("placementcode : "+ ((Node)pc.item(0)).getNodeValue());
							map.put("placement-code " + Integer.toString(i), ((Node) pc.item(0)).getNodeValue());

							NodeList widthlist = decor.getElementsByTagName("width");
							Element width = (Element) widthlist.item(0);

							NodeList widt = width.getChildNodes();
							// System.out.println("width : "+ ((Node)widt.item(0)).getNodeValue());
							map.put("width " + Integer.toString(i), ((Node) widt.item(0)).getNodeValue());

							NodeList heightlist = decor.getElementsByTagName("height");
							Element height = (Element) heightlist.item(0);

							NodeList heigh = height.getChildNodes();
							// System.out.println("height : "+ ((Node)heigh.item(0)).getNodeValue());
							map.put("height " + Integer.toString(i), ((Node) heigh.item(0)).getNodeValue());

						}
					}
				}
			}

		}
		return map;

	}

	public static int getItemnumber() throws Exception {
		File f = new File(".//Input//" + filename);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(f);

		doc.getDocumentElement().normalize();

		NodeList items = doc.getElementsByTagName("Item");

		int totitems = items.getLength();

		return totitems;
	}

}
