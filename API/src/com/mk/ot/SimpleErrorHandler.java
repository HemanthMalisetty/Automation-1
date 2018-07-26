package com.mk.ot;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler{

	  public void warning(SAXParseException e) throws SAXException {
	      System.out.println("warning");  
		  System.out.println(e.getMessage());
	    }

	    public void error(SAXParseException e) throws SAXException {
	        System.out.println("error");
	    	System.out.println(e.getMessage());
	    }

	    public void fatalError(SAXParseException e) throws SAXException {
	        System.out.println("fatalerror");
	    	System.out.println(e.getMessage());
	    }
	
}
