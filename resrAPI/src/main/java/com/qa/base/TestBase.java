package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	
	public Properties prop=null;
	
	
	public TestBase()
	{
		try
		{
			prop= new Properties();
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
			
			prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
