package com.onchip.alpha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class Utility {
	
	static XSSFWorkbook wb=null;
	static File enduserdata=null;
	static FileInputStream fis1=null;
	static XSSFSheet sheet=null;
	
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

}
