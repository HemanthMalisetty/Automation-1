package com.ab.onchip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {
	
	Properties prop=null;
	XSSFWorkbook wb=null;
	XSSFSheet sheet=null;
	String[] arr=null;
	Random rand;
	
	public Properties loadfiles(){
		
		try {
			
			prop=new Properties();
			
			arr=new String[200];
			
			File f2=new File(".//Input//config.properties");
			
			FileInputStream fis2=new FileInputStream(f2);
			
			prop.load(fis2);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	public String[] itemnumber()
	{
		try {
			File f1=new File(".\\Required\\GTIN_Item_List_fortesting.xlsx");
			
			FileInputStream fis1=new FileInputStream(f1);
			
			wb=new XSSFWorkbook(fis1);
			
			sheet=wb.getSheetAt(0);
			
			int lastrownum=sheet.getLastRowNum();
			
			for(int i=1;i<=lastrownum;i++)
			{
				arr[i]=sheet.getRow(i).getCell(0).getStringCellValue();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;		
	}
	
	public String shipDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		return dateFormat.format(cal.getTime());
	}
	
	public int randInt(int min, int max)
	{
		rand=new Random();
		
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		return randomNum;
	}
		
}
