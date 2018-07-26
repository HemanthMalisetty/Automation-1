package com.mk.onchip;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Base {

	WebDriver driver;
	Properties prop;
	String ret;

	Base(WebDriver driver) {
		this.driver = driver;
		prop = new Properties();
	}

	/*
	 * method for clicking on an element
	 */
	public void clickonelement(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	/*
	 * method for filling the data into form
	 */
	public WebElement web_element(String xpath) {
		WebElement ele=driver.findElement(By.xpath(xpath));
		return ele;
	}

	/*
	 * method to upload an image
	 */
	public void uploadFile(String fileLocation) {
		try {

			StringSelection stringSelection = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	/*
	 * method to load config file
	 */
	public Properties loadfile() {
		try {
			File f = new File(".\\config\\xpat.properties");

			FileInputStream fis = new FileInputStream(f);

			prop.load(fis);

			return prop;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/*
	 * method to get the placement code value
	 */
	public String get_Placement_code(String value) {
		if (value.equalsIgnoreCase("Front Center")) {
			ret = "FC";

			return ret;
		} else if (value.equalsIgnoreCase("Front Center Left")) {
			ret = "FCL";

			return ret;
		} else if (value.equalsIgnoreCase("Front Center Right")) {
			ret = "FCR";

			return ret;
		} else if (value.equalsIgnoreCase("Front Left Pocket")) {
			ret = "FLP";

			return ret;
		} else if (value.equalsIgnoreCase("Front Right Pocket")) {
			ret = "FRP";

			return ret;
		} else if (value.equalsIgnoreCase("Front Top Left")) {
			ret = "FTL";

			return ret;
		} else if (value.equalsIgnoreCase("Front Top Right")) {
			ret = "FTR";

			return ret;
		} else if (value.equalsIgnoreCase("Front Bottom")) {
			ret = "FB";

			return ret;
		} else if (value.equalsIgnoreCase("Front Bottom Left")) {
			ret = "FBL";

			return ret;
		} else if (value.equalsIgnoreCase("Front Bottom Right")) {
			ret = "FBR";

			return ret;
		} else if (value.equalsIgnoreCase("Back Center")) {
			ret = "BC";

			return ret;
		} else if (value.equalsIgnoreCase("Back Center Left")) {
			ret = "BCL";

			return ret;
		} else if (value.equalsIgnoreCase("Back Center Right")) {
			ret = "BCR";

			return ret;
		} else if (value.equalsIgnoreCase("Back Top Left")) {
			ret = "BTL";

			return ret;
		} else if (value.equalsIgnoreCase("Back Top Right")) {
			ret = "BTR";

			return ret;
		} else if (value.equalsIgnoreCase("Back Bottom")) {
			ret = "BB";

			return ret;
		} else if (value.equalsIgnoreCase("Back Bottom Left")) {
			ret = "BBL";

			return ret;
		} else if (value.equalsIgnoreCase("Back Bottom Right")) {
			ret = "BBR";

			return ret;
		} else if (value.equalsIgnoreCase("Left Sleeve")) {
			ret = "LS";

			return ret;
		} else if (value.equalsIgnoreCase("Right Sleeve")) {
			ret = "RS";

			return ret;
		} else {
			return "please enter the proper placement code";
		}
	}

	/*
	 * clicking on placement code using value attribute
	 */
	public void click_on_placementcode(WebElement ele1, String value) {
		Select sel = new Select(ele1);

		sel.selectByValue(value);

	}

	/*
	 * clicking on placement code using visible text
	 */
	public void click_on_placeementusingtext(WebElement ele2, String value)

	{
		//ele2.click();
		
		System.out.println(value);
		
		Select sel = new Select(ele2);

		sel.selectByVisibleText(value);
		
	}
	
	/*
	 * This method is used to enter data using java script executor
	 */
	public static boolean typeTextUsingJavaScript(WebDriver driver, WebElement element, String text)
			throws InterruptedException {
		Thread.sleep(2000);
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value='" + text + "';", element);
				return true;
			} else {
				System.out.println("Element is not displayed.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}
	
	public static void clickondropdown(WebElement ele,String value)
	{
		List<WebElement> l1=ele.findElements(By.tagName("option"));
		
		for(int i=0;i<l1.size();i++)
		{
			WebElement item=l1.get(i);
			
			String str=item.getText().trim();
			
			System.out.println(str);
			
			if(str.equalsIgnoreCase(value.trim()))
			{
				item.click();
				
				System.out.println("clicked on "+value);
			}
		}
	}
}
