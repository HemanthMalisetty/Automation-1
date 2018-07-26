package com.mk.Onchip;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Form_filling_Manually {
	
	
	public static void formfill(String stepname) throws Exception
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\JAYASRIM\\Downloads\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.get("https://devservices2.alphabroder.com/deco/order/manualentry");
		
		Base.senddata(driver, ".//*[@id='CustomerNumber']", "customer_number");
		
		Base.senddata(driver, ".//*[@id='PoNumber']", "customer_po_number");
		
		Base.senddata(driver, ".//*[@id='Email']","email");
		
		Base.clickonelement(driver, ".//*[@id='ShipDate']");
		
		Base.clickonelement(driver, ".//*[@id='ui-datepicker-div']/div/a[2]");
		
		Base.calenderclick(driver, ".//*[@id='ui-datepicker-div']/table/tbody/tr", "requested_ship_date");
		
		//Actions act=new Actions(driver);
		
		Base.clickonelement(driver, ".//*[@id='SelectedDecorator']");
		
		Base.clickondropdown(driver, "option", "decorators");
		
		Base.clickonelement(driver, ".//*[@id='CarrierCode']");
		
		Base.clickondropdown(driver, "option", "carrier");
		
		Base.senddata(driver, ".//*[@id='ShipToCompany']","company_name");
		
		Base.senddata(driver, ".//*[@id='ShipToAttn']","attn");
		
		Base.senddata(driver, ".//*[@id='ShipToAddress1']","address1");
		
		Base.senddata(driver, ".//*[@id='ShipToAddress2']", "address2");
		
		Base.senddata(driver, ".//*[@id='ShipToCity']","city");
		
		Base.senddata(driver, ".//*[@id='ShipToState']", "state");
		
		Base.senddata(driver, ".//*[@id='ShipToZip']","zip");
		
		Base.senddata(driver,".//*[@id='Comment']", "comment");
		
		Base.senddata(driver, ".//*[@id='Items_0__ItemNumber']","item_number");
		
		Base.senddata(driver, ".//*[@id='Items_0__Quantity']","quantity");
		
	    Base.clickonradio(driver, ".//div[@class='col-md-4']/p/input", "button");
		
		Base.clickonelement(driver, ".//*[@id='Items_0__Images_0__Placementcode']");
		
	    Base.clickondropdown(driver,"option","placement_code");
		
		 Base.senddata(driver, ".//*[@id='Items_0__Images_0__Width']","width");
		
		Base.senddata(driver,".//*[@id='Items_0__Images_0__Height']","height");
		
		Base.clickonelement(driver,".//*[@id='Items_0__Images_0__UploadedImage']");
		
		Base.uploadFile("imagepath");
		
		Thread.sleep(2000);
		
		Base.clickonelement(driver, "//input[@value='Submit']");
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File dest=new File(".//Output//"+stepname+".png");
		
	    FileUtils.copyFile(src, dest);
	}

}
