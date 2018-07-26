package com.ot.alpha;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Filling_Form {

	WebDriver driver;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	Properties prop;
	Base b = null;
	WebElement scalable = null;
	WebElement elementofplacementcode = null;
	WebElement carrier = null;
	Entering_Date dat = null;
	List<String> lis, lis1, lis2, lis3, lis4 = null;

	@Test
	public void fillform() throws Exception {
		System.setProperty("webdriver.gecko.driver", ".\\Gecko driver\\geckodriver.exe");

		System.setProperty("webdriver.chrome.driver", ".\\exefiles\\chromedriver.exe");

		lis = new ArrayList<String>();

		lis1 = new ArrayList<String>();

		lis2 = new ArrayList<String>();

		lis3 = new ArrayList<String>();

		lis4 = new ArrayList<String>();

		dat = new Entering_Date();

		File f = new File(".\\Input\\ManualEntryForm1.xlsx");

		FileInputStream fis = new FileInputStream(f);

		wb = new XSSFWorkbook(fis);

		sheet = wb.getSheetAt(0);

		int lastrow = sheet.getLastRowNum();

		for (int i = 1; i <= lastrow; i++) {

			if (sheet.getRow(i).getCell(0).getStringCellValue() != null) {
				
				driver = new ChromeDriver();

				Actions builder = new Actions(driver);
				
				b = new Base(driver);

				prop = b.loadfile();

				driver.manage().window().maximize();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				// launching the form
				driver.get(prop.getProperty("url"));

				WebElement custnum = b.web_element(prop.getProperty("custnum-xpath"));
				Base.typeTextUsingJavaScript(driver, custnum, sheet.getRow(i).getCell(1).getStringCellValue());

				WebElement ponum = b.web_element(prop.getProperty("ponum-xpath"));
				Base.typeTextUsingJavaScript(driver, ponum, sheet.getRow(i).getCell(2).getStringCellValue());

				WebElement email = b.web_element(prop.getProperty("email-xpath"));
				Base.typeTextUsingJavaScript(driver, email, sheet.getRow(i).getCell(3).getStringCellValue());

				// clicking on date dropdown
				b.clickonelement(prop.getProperty("shipdate-xpath"));

				// clicking on required date
				dat.clickondate(driver, sheet.getRow(i).getCell(4).getStringCellValue());

				// element of decorator
				WebElement decorator = b.web_element(prop.getProperty("decorator-xpath"));

				// clicking on scalable
				//b.click_on_placeementusingtext(decorator, sheet.getRow(i).getCell(5).getStringCellValue());
                Base.clickondropdown(decorator, sheet.getRow(i).getCell(5).getStringCellValue());
				
				// element of order type
				WebElement ordertype = b.web_element(prop.getProperty("ordertype-xpath"));

				// clicking on order type
				Base.clickondropdown(ordertype, sheet.getRow(i).getCell(6).getStringCellValue());

				// element of carrier code
				WebElement carriercode = b.web_element(prop.getProperty("carriercode-xpath"));

				// clicking on carrier code
				Base.clickondropdown(carriercode, sheet.getRow(i).getCell(7).getStringCellValue());

				// element of company name
				WebElement companyname = b.web_element(prop.getProperty("companyname-xpath"));

				// typing company name
				Base.typeTextUsingJavaScript(driver, companyname, sheet.getRow(i).getCell(8).getStringCellValue());

				// element of attain no
				WebElement attnto = b.web_element(prop.getProperty("attainto-xpath"));

				// typing attain no
				Base.typeTextUsingJavaScript(driver, attnto, sheet.getRow(i).getCell(9).getStringCellValue());

				// element of address1
				WebElement address1 = b.web_element(prop.getProperty("address1-xpath"));

				// typing address1
				Base.typeTextUsingJavaScript(driver, address1, sheet.getRow(i).getCell(10).getStringCellValue());

				// element of address2
				WebElement address2 = b.web_element(prop.getProperty("address2-xpath"));

				// typing address1
				Base.typeTextUsingJavaScript(driver, address2, sheet.getRow(i).getCell(11).getStringCellValue());

				// element of city
				WebElement city = b.web_element(prop.getProperty("city-xpath"));

				// typing city
				Base.typeTextUsingJavaScript(driver, city, sheet.getRow(i).getCell(12).getStringCellValue());

				// element of state
				WebElement state = b.web_element(prop.getProperty("state-xpath"));

				// typing state
				Base.typeTextUsingJavaScript(driver, state, sheet.getRow(i).getCell(13).getStringCellValue());

				// element of zip
				WebElement zip = b.web_element(prop.getProperty("zip-xpath"));

				// typing zip
				Base.typeTextUsingJavaScript(driver, zip, sheet.getRow(i).getCell(14).getStringCellValue());

				// element of comment
				WebElement comment = b.web_element(prop.getProperty("comment-xpath"));

				// typing comment
				Base.typeTextUsingJavaScript(driver, comment, sheet.getRow(i).getCell(15).getStringCellValue());

				// collecting the line item numbers
				String items = sheet.getRow(i).getCell(16).getStringCellValue();

				lis = b.collection_of_data(items);

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

				// finds the list of quantity and splits for each item
				String quantity = sheet.getRow(i).getCell(17).getStringCellValue();

				lis1 = b.collection_of_data(quantity);

				int que = lis1.size();

				for (int k = 0; k < que; k++) {
					if (k == 0) {
						b.senddata(".//*[@id='Items_" + k + "__Quantity']", lis1.get(k));
					} else {
						b.senddata(".//*[@id='Items_" + k + "_Quantity']", lis1.get(k));
					}
				}
				
				
				// clicking on new button
				b.clickonelement(prop.getProperty("newdecoration-xpath"));
				
                builder.moveToElement(b.web_element(prop.getProperty("upload-xpath")));
				
				b.clickonelement(prop.getProperty("upload-xpath"));
				
				ProcessBuilder pb = new ProcessBuilder(".\\autoIt\\imageupload.exe",
						sheet.getRow(i).getCell(24).getStringCellValue());

				pb.start();
				
				 Thread.sleep(5000);

				WebElement placementcode = b.web_element(prop.getProperty("placementcode-xpath"));

				// clicking on placement code
				Base.clickondropdown(placementcode, sheet.getRow(i).getCell(18).getStringCellValue());

				// typing the width
				b.senddata(prop.getProperty("width-xpath"), sheet.getRow(i).getCell(19).getStringCellValue());

				// typing the width
				b.senddata(prop.getProperty("height-xpath"), sheet.getRow(i).getCell(20).getStringCellValue());

				if (lis.size() == 1) {

					// collecting the colorfamily codes
					String colors = sheet.getRow(i).getCell(21).getStringCellValue();

					b.clickonelement(prop.getProperty("closingaddcolor-xpath"));
					
					lis2 = b.collection_of_data(colors);

					for (int q = 0; q < lis2.size(); q++) {

						b.clickonelement(prop.getProperty("addcolor-xpath"));

						// clicking on the color family
						b.click_on_placeementusingtext(
								b.web_element(".//select[@id='Decorations_0__Color_" + q + "__ColorFamily']"),
								lis2.get(q));

					}

					String colorcodes = sheet.getRow(i).getCell(22).getStringCellValue();

					lis3 = b.collection_of_data(colorcodes);

					for (int a = 0; a < lis3.size(); a++) {

						// clicking on the required color code
						b.clickonelement(".//*[@id='select2-Decorations_0__Color_" + a + "__ColorCode-container']");

						// entering the data in search
						b.senddata(prop.getProperty("colorcodesearch-xpath"), lis3.get(a));

						builder.sendKeys(Keys.ENTER).build().perform();
						builder.release().perform();

					}
					
                   			
                    String priorities = sheet.getRow(i).getCell(23).getStringCellValue();

					lis4 = b.collection_of_data(priorities);

					for (int c = 0; c < lis3.size(); c++) {
						String value = lis4.get(c);

						// entering color priority
						b.senddata(".//*[@id='Decorations_0__Color_" + c + "__ColorPriority']", value);

					}

					// clearing the lists for next iterations
					lis2.clear();

					lis3.clear();

					lis4.clear();
					
					
				}
				lis.clear();

				lis1.clear();

				Thread.sleep(10000);
				
				builder.sendKeys(Keys.TAB).build().perform();
				builder.release().perform();
				
				//moving to submit button
				builder.moveToElement(b.web_element(prop.getProperty("submit-xpath")));
				
				//clicking on submit button
				b.clickonelement(prop.getProperty("submit-xpath"));
			} 
			else
			{
				System.out.println("error");
			}
		}

	}

}
