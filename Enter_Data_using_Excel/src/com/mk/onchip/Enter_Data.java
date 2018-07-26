package com.mk.onchip;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Enter_Data {

	WebDriver driver;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	Properties prop;
	Base b=null;
	WebElement scalable=null;
	WebElement elementofplacementcode=null;
	WebElement carrier=null;

	@Test
	public void enterdata() throws Exception {
		System.setProperty("webdriver.chrome.driver", ".\\exefiles\\chromedriver.exe");

		List<String> lis = new ArrayList<String>();

		List<String> lis1 = new ArrayList<String>();

		File f = new File(".\\Input\\ManualEntryForm1.xlsx");

		FileInputStream fis = new FileInputStream(f);

		wb = new XSSFWorkbook(fis);

		sheet = wb.getSheetAt(0);

		int lastrow = sheet.getLastRowNum();

		Date1 d = new Date1();
		
		for (int i = 1; i <= lastrow; i++) {
			
			driver = new ChromeDriver();

            b = new Base(driver);

			prop = b.loadfile();
			
			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//launching the url
			driver.get(prop.getProperty("url"));

			//enter customer number
			WebElement customernum=b.web_element(prop.getProperty("customernumber-xpath"));
			
			Base.typeTextUsingJavaScript(driver, customernum,sheet.getRow(i).getCell(1).getStringCellValue() );
			
			//enter customer po number
			WebElement custpo=b.web_element(prop.getProperty("customerponumber-xpath"));
            Base.typeTextUsingJavaScript(driver, custpo, sheet.getRow(i).getCell(2).getStringCellValue());
			
            //enter email
            WebElement email=b.web_element(prop.getProperty("email-xpath"));
			Base.typeTextUsingJavaScript(driver, email, sheet.getRow(i).getCell(3).getStringCellValue());
			
			//clicking on date dropdown
			b.clickonelement(prop.getProperty("shipdate-xpath"));

			//clicking on required date
			d.clickondate(driver,sheet.getRow(i).getCell(4).getStringCellValue());

			//element of decorator
			WebElement decorator=b.web_element(prop.getProperty("clickondecorator-xpath"));
			
			String str=sheet.getRow(i).getCell(5).getStringCellValue();
			
			System.out.println("Printing the value "+str);
						
			//clicking on scalable
			b.click_on_placeementusingtext(decorator, str);
						
			//element of order type
			WebElement ordertype=b.web_element(prop.getProperty("ordertype-xpath"));
			
			System.out.println("Data from sheet "+sheet.getRow(i).getCell(6).getStringCellValue());
			
			String str1=sheet.getRow(i).getCell(6).getStringCellValue();
			
			//clicking on order type
			b.click_on_placeementusingtext(ordertype, str1);
						
			//element of carrier code 
			carrier=driver.findElement(By.id(prop.getProperty("clickoncarriercode-id")));
			
			//clicking on required carrier code
			b.click_on_placeementusingtext(carrier, sheet.getRow(i).getCell(6).getStringCellValue());
			
			//enters ship to company name
			WebElement companyname=b.web_element(prop.getProperty("shiptocompany-xpath"));
			Base.typeTextUsingJavaScript(driver, companyname, sheet.getRow(i).getCell(7).getStringCellValue());
			
			//enters attn
			WebElement attn=b.web_element(prop.getProperty("shiptoattn-xpath"));
            Base.typeTextUsingJavaScript(driver, attn, sheet.getRow(i).getCell(8).getStringCellValue());
			
            //enters address1
			WebElement address1=b.web_element(prop.getProperty("shiptoaddress1-xpath"));
            Base.typeTextUsingJavaScript(driver, address1, sheet.getRow(i).getCell(9).getStringCellValue());
		    
            //enters address2
			WebElement address2=b.web_element(prop.getProperty("shiptoaddress2-xpath"));
            Base.typeTextUsingJavaScript(driver, address2, sheet.getRow(i).getCell(10).getStringCellValue());
			
            //enters ship to city
			WebElement shiptocity=b.web_element(prop.getProperty("shiptocity-xpath"));
            Base.typeTextUsingJavaScript(driver, shiptocity, sheet.getRow(i).getCell(11).getStringCellValue());
            
			//enters ship to state
			WebElement shiptostate=b.web_element(prop.getProperty("shiptostate-xpath"));
            Base.typeTextUsingJavaScript(driver, shiptostate, sheet.getRow(i).getCell(12).getStringCellValue());
            
			//enters zip
			WebElement shiptozip=b.web_element(prop.getProperty("shiptozip-xpath"));
            Base.typeTextUsingJavaScript(driver, shiptozip, sheet.getRow(i).getCell(13).getStringCellValue());
            
			//enters comment
			WebElement comment=b.web_element(prop.getProperty("comment-xpath"));
            Base.typeTextUsingJavaScript(driver, comment, sheet.getRow(i).getCell(14).getStringCellValue());
            
			/*//finds the list of items and enters separately
			String items = sheet.getRow(i).getCell(15).getStringCellValue();

			Scanner sc = new Scanner(items);

			sc.useDelimiter("/");

			while (sc.hasNext()) {
				lis.add(sc.next());
			}

			int num = lis.size();

			int x = num - 1;

			for (int j = 0; j < num; j++) {

				if (j == 0) {
					b.senddata(".//*[@id='Items_" + j + "__ItemNumber']", lis.get(j));
				} else {
					b.senddata(".//*[@id='Items_" + j + "_ItemNumber']", lis.get(j));
				}
				if (j < x) {
					b.clickonelement(prop.getProperty("addanitem-xpath"));
				}

			}
			
			//finds the list of quantity and splits for each item
			String quantity = sheet.getRow(i).getCell(16).getStringCellValue();

			Scanner sc1 = new Scanner(quantity);

			sc1.useDelimiter("/");

			while (sc1.hasNext()) {
				lis1.add(sc1.next());
			}

			int que = lis1.size();

			for (int k = 0; k < que; k++) {
				if (k == 0) {
					b.senddata(".//*[@id='Items_" + k + "__Quantity']", lis1.get(k));
				} else {
					b.senddata(".//*[@id='Items_" + k + "_Quantity']", lis1.get(k));
				}
			}

			//clicking on the new item button
			b.clickonelement(prop.getProperty("newitembutton-xpath"));

			//element of placementcode dropdown
			elementofplacementcode=driver.findElement(By.id(prop.getProperty("placementcode-id")));
			
			//gathering the string value of placement code
			String value=b.get_Placement_code(sheet.getRow(i).getCell(17).getStringCellValue());
			
			//clicking on the required placementcode
			b.click_on_placementcode(elementofplacementcode, value);	
			
			//enters width
			b.senddata(prop.getProperty("width-xpath"), sheet.getRow(i).getCell(18).getStringCellValue());

			//enters height
			b.senddata(prop.getProperty("height-xpath"), sheet.getRow(i).getCell(19).getStringCellValue());

			//clicks on browse button
			b.clickonelement(prop.getProperty("chooseimagebutton-xpath"));

			Thread.sleep(2000);

			//uploads the image
			b.uploadFile(sheet.getRow(i).getCell(20).getStringCellValue());

			Thread.sleep(2000);

			//clearing all lists
			lis.clear();

			lis1.clear();

			sc.close();

			sc1.close();

			//clicking on submit button
			b.clickonelement(prop.getProperty("submit-xpath"));

			Thread.sleep(2000);

			TakesScreenshot ts = (TakesScreenshot) driver;

			File src = ts.getScreenshotAs(OutputType.FILE);

			File dest = new File(".//Output//Form " + i + ".png");

			FileUtils.copyFile(src, dest);

			driver.close();

		}*/
	}
}}
