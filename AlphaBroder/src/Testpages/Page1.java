package Testpages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Base.Utility;

public class Page1 {

	static Properties prop = null;
	static ExtentReports extent=null;
	static ExtentTest logger=null;
	static WebDriver driver=null;
	static HashMap<String, Integer> output = null;
	
	@BeforeTest
	public static void startTest()
	{
		prop = Utility.loadfile();
		
		extent = new ExtentReports (System.getProperty("user.dir") +"/Reports/"+prop.getProperty("nameofreport")+".html", true);
		
		logger=extent.startTest(prop.getProperty("nameofreport"));
	}
	
	@Test
	public static void Alpha() {
		try {

			driver = Utility.launchurl(prop.getProperty("url"));

			Utility util = new Utility(driver);

			String pagetitle=driver.getTitle();
			
			if(pagetitle.equalsIgnoreCase("Welcome to alphabroder"))
			{
				logger.log(LogStatus.PASS,"Navigated to page "+pagetitle);
			}
			else
			{
				logger.log(LogStatus.FAIL,"Navigated to wrong page");
			}

			String user=util.validate("validation-forgotusername-xpath");
			
			if(user.equalsIgnoreCase("Forgot Username"))
			{
				logger.log(LogStatus.PASS,"Found Forgot username on page top");
			}
			else
			{
				logger.log(LogStatus.FAIL,"Page was not loaded properly");
			}
			String passwod=util.validate("validation-forgotpassword-xpath");
			
			if(passwod.equalsIgnoreCase("Forgot Password"))
			{
			logger.log(LogStatus.PASS,"Found Forgot password at top of page");	
			}
			else
			{
				logger.log(LogStatus.FAIL,"Page was not loaded properly");
			}

			String aboutus=util.validate("validation-aboutus-xpath");
			
			if(aboutus.equalsIgnoreCase("About Us"))
			{
				logger.log(LogStatus.PASS, "Found About Us at bottom of page");
			}
			else
			{
				logger.log(LogStatus.FAIL,"Page was not loaded properly");
			}

			logger.log(LogStatus.PASS,"Home Page loaded successfully");

			WebElement username = util.getelement("username-id");

			WebElement password = util.getelement("password-id");

			Utility.typeTextUsingJavaScript(driver, username, prop.getProperty("username"));

			Utility.typeTextUsingJavaScript(driver, password, prop.getProperty("password"));

			WebElement login = util.getelement("login-id");

			Utility.clickOnElement(login);

			logger.log(LogStatus.PASS ,"Page title after login is " + driver.getTitle());

			String welcome=util.validate("validation-welcomequote-xpath");
			
			if(welcome.equalsIgnoreCase("Welcome!"))
			{
			logger.log(LogStatus.PASS,"Found " + util.validate("validation-welcomequote-xpath") + " on top of page");
			}
			else
			{
				logger.log(LogStatus.FAIL,"Page was not loaded properly");
			}
			
			String logout=util.validate("validation-logout-xpath");
			
			if(logout.equalsIgnoreCase("Logout"))
			{
			logger.log(LogStatus.PASS,"Found " + util.validate("validation-logout-xpath") + "on top of page");
			}
			else
			{
				logger.log(LogStatus.FAIL,"Page was not loaded properly");
			}
			
			logger.log(LogStatus.PASS,"Main page loaded successfully");

			int x = Utility.randint(1, 5);
			
			//int x=4;
			
			if (x == 1) {
				WebElement Tshirts = util.getelement("t-shirts-xpath");

				String category1=Tshirts.getText();
				
				System.out.println(category1);
				
				if(category1.equalsIgnoreCase("T-Shirts"))
				{
					logger.log(LogStatus.PASS,"Clicked on category "+ Tshirts.getText());
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				}
				Utility.clickOnElement(Tshirts);

				String searchresults1=util.validate("validation-tshirtspage-xpath");
				
				if(searchresults1.equalsIgnoreCase("T-Shirts"))
				{
					logger.log(LogStatus.PASS,"Navigated to " + util.validate("validation-tshirtspage-xpath") + " page");
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				}
								
				logger.log(LogStatus.PASS,"T-SHIRTS page loaded successfully");

				WebElement product1 = util.getelement("product1-xpath");

				Utility.moveToElement(driver, product1);

				logger.log(LogStatus.PASS,"Clicking on product " + util.validation_using_attribute("product1-xpath"));

				Utility.clickOnElement(product1);

				logger.log(LogStatus.PASS,"Page title after clicking on product is " + driver.getTitle());

				String productpage1=util.validate("validation-productpage1-xpath");
				if(productpage1.equalsIgnoreCase("G500"))		
				{
					logger.log(LogStatus.PASS,"Product on product page is " + util.validate("validation-productpage1-xpath"));
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong product");
				}
				//WebElement warehouse1 = util.getelement("warehouse1-xpath");

				//Utility.typeText(warehouse1, prop.getProperty("warehouse1-quantity"));

				output=Utility.enter_details_into_warehouse("warehouse1-quantity");
				
				Iterator<String> itr = output.keySet().iterator();
				while (itr.hasNext()) {
					String str = itr.next();
					System.out.print(str);
					System.out.println(" " + output.get(str));

				}

				
				//logger.log(LogStatus.PASS,"Entered quantity into warehouse " + util.validate("validation-warehouse1-xpath"));

				logger.log(LogStatus.PASS,"completed filling details in product page");
			} else if (x == 2) {
				WebElement sweatshirts = util.getelement("Sweatshirts-xpath");
				
                 String category2=sweatshirts.getText();
				
				if(category2.equalsIgnoreCase("Sweatshirts"))
				{
					logger.log(LogStatus.PASS,"Clicked on category "+ sweatshirts.getText());
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				}

				Utility.clickOnElement(sweatshirts);

				String searchresults2=util.validate("validation-sweatshirts-xpath");
				if(searchresults2.equalsIgnoreCase("Sweatshirts"))
				{
					logger.log(LogStatus.PASS,"Navigated to " + util.validate("validation-sweatshirts-xpath") + " page");
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				}
				logger.log(LogStatus.PASS,"Sweatshirts page loaded successfully");

				WebElement product2 = util.getelement("product2-xpath");

				Utility.moveToElement(driver, product2);

				logger.log(LogStatus.PASS,"Clicking on product " + util.validation_using_attribute("product2-xpath"));

				Utility.clickOnElement(product2);

				logger.log(LogStatus.PASS,"Page title after clicking on product is " + driver.getTitle());

				String productpage2=util.validate("validation-productpage2-xpath");
				
				if(productpage2.equalsIgnoreCase("996"))
				{
				logger.log(LogStatus.PASS,"Product on the page is " + util.validate("validation-productpage2-xpath"));
			    }
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong product");
				}
				//WebElement warehouse2 = util.getelement("warehouse2-xpath");
				
				//String ware2=prop.getProperty("warehouse2-quantity");
				
				//System.out.println("Sweat shirts "+ware2);

				//Utility.typeText(warehouse2, ware2);

				//Utility.typeTextUsingJavaScript(driver, warehouse2, ware2);
				
				Utility.enter_details_into_warehouse("warehouse2-quantity");
				
				logger.log(LogStatus.PASS,"Entered quantity into warehouse " + util.validate("validation-warehouse2-xpath"));

				logger.log(LogStatus.PASS,"Completed filling details into product page");

			} else if (x == 3) {
				WebElement polos = util.getelement("polos-xpath");

				 String category3=polos.getText();
					
					if(category3.equalsIgnoreCase("Polos"))
					{
						logger.log(LogStatus.PASS,"Clicked on category "+ polos.getText());
					}
					else
					{
						logger.log(LogStatus.FAIL,"Navigated to wrong category");
					}

				Utility.clickOnElement(polos);

				String searchresults3=util.validate("validation-polos-xpath");
				
				if(searchresults3.equalsIgnoreCase("Polos"))
				{
					logger.log(LogStatus.PASS,"Navigated to " + util.validate("validation-polos-xpath")+" page");
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				}
				
				logger.log(LogStatus.PASS,"Polos page loaded successfuly");

				WebElement product3 = util.getelement("product3-xpath");

				Utility.moveToElement(driver, product3);

				logger.log(LogStatus.PASS,"Clicking on product " + util.validation_using_attribute("product3-xpath"));

				Utility.clickOnElement(product3);

				logger.log(LogStatus.PASS,"Page title after clicking on product is " + driver.getTitle());

				String productpage3=util.validate("validation-productpage3-xpath");
				
				if(productpage3.equalsIgnoreCase("Ash City - Core 365 Ladies' Origin Performance Piqué Polo"))
				{
				logger.log(LogStatus.PASS,"Product found on page is " + util.validate("validation-productpage3-xpath"));
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong product");
				}
				//WebElement warehouse3 = util.getelement("warehouse3-xpath");
				
				//String ware3=prop.getProperty("warehouse3-quantity");
				
				//System.out.println("Polos "+ware3);

				//Utility.typeText(warehouse3, ware3);

				//Utility.typeTextUsingJavaScript(driver, warehouse3, ware3);
				
				Utility.enter_details_into_warehouse("warehouse3-quantity");
				
				logger.log(LogStatus.PASS,"Entered quantity into warehouse" + util.validate("validation-warehouse3-xpath"));

				logger.log(LogStatus.PASS,"Completed filling data in product details page");
			} else if (x == 4) {
				WebElement knitsandlayering = util.getelement("knitsandlayering-xpath");

				 String category4=knitsandlayering.getText();
					
					if(category4.equalsIgnoreCase("Knits and Layering"))
					{
						logger.log(LogStatus.PASS,"Clicking on category "+ knitsandlayering.getText());
					}
					else
					{
						logger.log(LogStatus.FAIL,"Navigated to wrong category");
					}

				Utility.clickOnElement(knitsandlayering);

				String searchresults4=util.validate("validation-knites-xpath");
				if(searchresults4.equalsIgnoreCase("Knits and Layering"))
				{
				logger.log(LogStatus.PASS,"Navigated to " + util.validate("validation-knites-xpath"));
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				}
				logger.log(LogStatus.PASS,"Knits and Layering page loaded successfully");

				WebElement product4 = util.getelement("product4-xpath");

				Utility.moveToElement(driver, product4);

				logger.log(LogStatus.PASS,"Clicking on product " + util.validation_using_attribute("product4-xpath"));

				Utility.clickOnElement(product4);

				logger.log(LogStatus.PASS,"Page title aftet clicking on product is " + driver.getTitle());

				String productpage4=util.validate("validation-productpage4-xpath");
				if(productpage4.equalsIgnoreCase("All Sport Unisex Quarter-Zip Lightweight Pullover"))
				{
					logger.log(LogStatus.PASS,"Product found on page " + util.validate("validation-productpage4-xpath"));
				}
				else
				{
					logger.log(LogStatus.FAIL,"Navigated to wrong product");
				}
				//WebElement warehouse4 = util.getelement("warehouse4-xpath");
				
				//String ware4=prop.getProperty("warehouse4-quantity");
				
				//System.out.println("Knites and layerings "+ware4);

				//Utility.typeText(warehouse4, ware4);

				//Utility.typeTextUsingJavaScript(driver, warehouse4, ware4);
				
				Utility.enter_details_into_warehouse("warehouse4-quantity");
				
				logger.log(LogStatus.PASS,"Entered quantity into warehouse " + util.validate("validation-warehouse4-xpath"));

				logger.log(LogStatus.PASS,"Completed filling details in product details page");
			} else if(x==5){
				WebElement pants = util.getelement("pants-xpath");

				 String category5=pants.getText();
					
					if(category5.equalsIgnoreCase("Pants"))
					{
						logger.log(LogStatus.PASS,"Clicked on category "+ pants.getText());
					}
					else
					{
						logger.log(LogStatus.FAIL,"Navigated to wrong category");
					}

				Utility.clickOnElement(pants);

				String searchresults5=util.validate("validation-pants-xpath");
				
				    if(searchresults5.equalsIgnoreCase("Pants"))
				   {
				       logger.log(LogStatus.PASS,"Navigated to page " + util.validate("validation-pants-xpath"));
				   }
				   else
				   {
					logger.log(LogStatus.FAIL,"Navigated to wrong category");
				   }
				logger.log(LogStatus.PASS,"Pants page loaded successfully");

				WebElement product5 = util.getelement("product5-xpath");

				Utility.moveToElement(driver, product5);

				logger.log(LogStatus.PASS,"Clicking on product " + util.validation_using_attribute("product5-xpath"));

				Utility.clickOnElement(product5);

				logger.log(LogStatus.PASS,"Page title after clicking on product is " + driver.getTitle());

				String productdetails5=util.validate("validation-productpage5-xpath");
				
				    if(productdetails5.equalsIgnoreCase("Bella + Canvas Ladies' Cotton/Spandex Legging"));
				    {
				      logger.log(LogStatus.PASS,"Prroduct found on page is " + util.validate("validation-productpage5-xpath"));
				    }
				    								
				//WebElement warehouse5 = util.getelement("warehouse5-xpath");
				
				//String ware5=prop.getProperty("warehouse5-quantity");
				
				//System.out.println("Pants "+ware5);

				//Utility.typeText(warehouse5, ware5);

				//Utility.typeTextUsingJavaScript(driver, warehouse5, ware5);
				
				Utility.enter_details_into_warehouse("warehouse5-quantity");
				    
				logger.log(LogStatus.PASS,"Entered quantity into warehouse " + util.validate("validation-warehouse5-xpath"));
 
				logger.log(LogStatus.PASS,"completed filling details in product details of page");
			}
			else {
				logger.log(LogStatus.FAIL,"	Invalid category");
			}
					

			WebElement addandcheckout = util.getelement("Addandcheckout-xpath");

			Utility.clickOnElement(addandcheckout);

			String value=util.validate("validate-textonpage-xpath");
			
			logger.log(LogStatus.PASS,"Found "+value+" on page");
			
			WebElement ponumber = util.getelement("Ponum-xpath");

			Utility.moveToElement(driver, ponumber);

			//Utility.typeText(ponumber, prop.getProperty("Ponumber"));
			
			Utility.typeTextUsingJavaScript(driver, ponumber, prop.getProperty("Ponumber"));
			
			WebElement verifyorder = util.getelement("verifyorder-xpath");

			Utility.scrollToElementUsingJavaSript(driver, verifyorder);

			Utility.clickOnElement(verifyorder);
			
			String verifyvalue=util.validate("validation-verifyorderimg-xpath");
			
			if(verifyvalue.equalsIgnoreCase("Order Verify"))
			{
				logger.log(LogStatus.PASS,"verify order page loaded successfully");
			}
            
			WebElement cart = util.getelement("cartoption-xpath");

			Utility.moveToElement(driver, cart);

			Utility.clickOnElement(cart);
			
			logger.log(LogStatus.PASS,"Clicked on cart option");

			WebElement clearcart = util.getelement("clearcart-xpath");

			Utility.moveToElement(driver, clearcart);

			Utility.clickOnElement(clearcart);
			
			logger.log(LogStatus.PASS,"Cleared the cart");

			driver.close();
			
			} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			String screenshotpath=Utility.CaptureScreenShot(driver);
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			logger.log(LogStatus.FAIL,"Snapshot below: " + logger.addScreenCapture(screenshotpath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
			String screenshotpath1=Utility.CaptureScreenShot(driver);
			logger.log(LogStatus.FAIL,"Snapshot below: " + logger.addScreenCapture(screenshotpath1));
		}
		extent.endTest(logger);
		extent.flush();
		extent.close();
	}
}

	

