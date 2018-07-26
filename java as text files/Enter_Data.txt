package com.mk.onchip;

import com.mk.onchip.Base;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Enter_Data {

	public static void formfill(String stepname) throws Exception {
		WebDriver driver = Base.launch_url("https://devservices2.alphabroder.com/deco/order/manualentry", "browser");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://devservices2.alphabroder.com/deco/order/manualentry");

		Base.senddata(driver, ".//*[@id='CustomerNumber']", "cust-number");

		Base.senddata(driver, ".//*[@id='PoNumber']", "po-number");

		Base.senddata(driver, ".//*[@id='Email']", "email-addr");

		Base.clickonelement(driver, ".//*[@id='ShipDate']");

		Base.clickonelement(driver, ".//*[@id='ui-datepicker-div']/div/a[2]");

		Base.clickonelement(driver, ".//*[@id='ui-datepicker-div']/div/a[2]");

		Base.calenderclick(driver, ".//*[@id='ui-datepicker-div']/table/tbody/tr", "ship-date");

		// Actions act=new Actions(driver);

		Base.clickonelement(driver, ".//*[@id='SelectedDecorator']");

		Base.clickondropdown(driver, "option", "deco-type 0");

		Base.clickonelement(driver, ".//*[@id='CarrierCode']");

		Base.clickondropdown(driver, "option", "carrier-code");

		Base.senddata(driver, ".//*[@id='ShipToCompany']", "ship-to-company");

		Base.senddata(driver, ".//*[@id='ShipToAttn']", "ship-to-attn");

		Base.senddata(driver, ".//*[@id='ShipToAddress1']", "ship-to-addr1");

		Base.senddata(driver, ".//*[@id='ShipToAddress2']", "ship-to-addr2");

		Base.senddata(driver, ".//*[@id='ShipToCity']", "ship-to-city");

		Base.senddata(driver, ".//*[@id='ShipToState']", "ship-to-state");

		Base.senddata(driver, ".//*[@id='ShipToZip']", "ship-to-zip");

		Base.senddata(driver, ".//*[@id='Comment']", "comment");

		int i = Read_Data.getItemnumber();

		int x = i - 1;

		for (int j = 0; j < i; j++) {
			if (j == 0) {
				Base.senddata(driver, ".//*[@id='Items_" + j + "__ItemNumber']", "item-number " + Integer.toString(j));

				Base.senddata(driver, ".//*[@id='Items_" + j + "__Quantity']", "quantity " + Integer.toString(j));
			} else {
				Base.senddata(driver, ".//*[@id='Items_" + j + "_ItemNumber']", "item-number " + Integer.toString(j));

				Base.senddata(driver, ".//*[@id='Items_" + j + "_Quantity']", "quantity " + Integer.toString(j));
			}
			if (j < x) {
				Base.clickonelement(driver, ".//*[@id='AddItem']");
			}
		}

		Base.clickonradio(driver, ".//div[@class='col-md-4']/p/input", "button");

		Base.clickonelement(driver, ".//*[@id='Items_0__Images_0__Placementcode']");

		Base.clickondropdown(driver, "option", "placement-code 0");

		Base.senddata(driver, ".//*[@id='Items_0__Images_0__Width']", "width 0");

		Base.senddata(driver, ".//*[@id='Items_0__Images_0__Height']", "height 0");

		Base.clickonelement(driver, ".//*[@id='Items_0__Images_0__UploadedImage']");

		Base.uploadFile("imagepath");

		Thread.sleep(2000);

		Base.clickonelement(driver, "//input[@value='Submit']");

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File dest = new File(".//Output//" + stepname + ".png");

		FileUtils.copyFile(src, dest);

	}
}
