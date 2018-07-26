package Testpages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Base.Utility;

public class Page2 {
	
	static Properties prop = null;

	public Page2() {
		prop = Utility.loadfile();
	}
	@Test
	public static void scenario2()
	{
		try
		{
		WebDriver driver=Utility.launchurl(prop.getProperty("ca_url"));
		
		Utility util=new Utility(driver);
		
		WebElement ele=util.getelement("ca_username-id");
		
		WebElement ele1=util.getelement("ca_password-id");
		
		Utility.clickOnElement(ele);
		
		//Utility.typeText(ele, prop.getProperty("ca_username"));
		
		Utility.typeTextUsingJavaScript(driver, ele, prop.getProperty("ca_username"));
		
		Utility.clickOnElement(ele1);
		
		Utility.typeTextUsingJavaScript(driver, ele1, prop.getProperty("ca_password"));
		
		//Utility.typeText(ele1, prop.getProperty("ca_password"));
		
		WebElement ele3=util.getelement("ca_login-id");
		
		Utility.clickOnElement(ele3);
		
		WebElement ele4=util.getelement("ca_T-shirts-xpath");
		
		Utility.clickOnElement(ele4);
		
		WebElement ele5=util.getelement("ca_product1-xpath");
		
		Utility.clickOnElement(ele5);
		
		WebElement ele6=util.getelement("ca_warehouse1-id");
		
		Utility.scrollToElementUsingJavaSript(driver, ele6);
		
	    //Utility.typeText(ele6, prop.getProperty("ca_warehousequant"));
	    
		Utility.typeTextUsingJavaScript(driver, ele6, prop.getProperty("ca_warehousequant"));
		
	    Thread.sleep(2000);
	    
	    WebElement ele7=util.getelement("ca_addandcheckout-xpath");
	    
	    Utility.scrollToElementUsingJavaSript(driver, ele7);
	    
	    Utility.clickOnElement(ele7);
	    
	    WebElement ele8=util.getelement("ca_ponumber-xpath");
	    
	    Utility.scrollToElementUsingJavaSript(driver, ele8);
	    
	    //Utility.typeText(ele8, prop.getProperty("ca_ponumber"));
	    
	    Utility.typeTextUsingJavaScript(driver, ele8, prop.getProperty("ca_ponumber"));
	    
	    WebElement ele9=util.getelement("ca_paymentmode-id");
	    
	    Utility.selectSingleValueFromDropdown(ele9, prop.getProperty("ca_accvalue"));
	    
	    WebElement ele10=util.getelement("ca_verifyorder-xpath");
	    
	    Utility.moveToElement(driver, ele10);
	    
	    Utility.clickOnElement(ele10);
	    
	    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
