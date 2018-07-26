package Base;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.google.common.base.Function;

public class Base {

	/*
	 * This method is used to select a single value from a dropdown
	 * 
	 */
	public static boolean selectSingleValueFromDropdown(WebElement dropdown, String value) throws InterruptedException {
   
		Thread.sleep(2000);
		if (dropdown.isDisplayed() && dropdown.isEnabled()) {
			Select select = new Select(dropdown);

			// checking for presence of value

			List<WebElement> allAvailableOptions = select.getOptions();
			List<String> allValues = new ArrayList<String>();
			if (allAvailableOptions.size() > 0) {
				for (WebElement optn : allAvailableOptions) {
					allValues.add(optn.getText());
				}
			} else {
				System.out.println("Dropdown with no option found.");
				return false;
			}

			// checking if desire option is already selected
			if (allValues.contains(value)) {
				if (select.getAllSelectedOptions().size() > 0) {
					List<WebElement> allSelectedOptions = select.getAllSelectedOptions();
					List<String> allSelectedvalues = new ArrayList<String>();
					for (WebElement e : allSelectedOptions) {
						allSelectedvalues.add(e.getText());
					}
					if (allSelectedvalues.contains(value)) {
						System.out.println(value + " is already selected.");
						return true;
					} else {
						select.selectByVisibleText(value);
						return true;
					}
				} else {
					select.selectByVisibleText(value);
					return true;
				}
			} else {
				System.out.println("Desired " + value + " is not available in dropdown.");
				return false;
			}

		}

		else {
			System.out.println("Dropdown is either not enabled or displayed. ");
			return false;
		}

	}

	/*
	 * This method is used to provide the explict wait
	 * 
	 */
	public static void expliciteWait(WebElement element, int timeToWaitInSec, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * This method is used to select multiple values from dropdown
	 * 
	 */
	public static boolean selectMultipleValuesFromDropdown(WebElement dropdown, List<String> value)
			throws InterruptedException {

		try {

			Thread.sleep(2000);
			if (dropdown.isDisplayed() && dropdown.isEnabled()) {
				Select select = new Select(dropdown);
				// check if drop down is multi select
				if (select.isMultiple()) {
					// check if all options are available in dropdown

					List<WebElement> allAvailableOptions = select.getOptions();
					List<String> allValues = new ArrayList<String>();
					if (allAvailableOptions.size() > 0) {
						for (WebElement optn : allAvailableOptions) {
							allValues.add(optn.getText().trim());
						}
					} else {
						System.out.println("Dropdown with no option found.");
						return false;
					}

					if (allValues.containsAll(value)) {
						for (String val : value) {
							select.selectByVisibleText(val);
						}
						return true;
					} else {
						System.out.println("All values are not available in dropdown.");
						return false;
					}

				} else {
					System.out.println("Drop down is not multiselect.");
					return false;
				}
			} else {
				System.out.println("Dropdown is either not enabled or displayed. ");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Some exception occured.");
			return false;
		}
	}

	/*
	 * 
	 * This method is used to select a single value from listbox
	 * 
	 * 
	 */
	public static boolean select_from_Listbox(WebElement listbox, String value) {

		try {
			Thread.sleep(2000);
			if (listbox.isDisplayed() && listbox.isEnabled()) {
				Select select = new Select(listbox);
				// checking for presence of value

				List<WebElement> listboxElements = select.getOptions();
				List<String> allValues = new ArrayList<String>();
				if (listboxElements.size() > 0) {
					for (WebElement optn : listboxElements) {
						allValues.add(optn.getText());
						select.selectByVisibleText(value);
						System.out.println(true);
						return true;
					}
				} else {
					System.out.println("Listbox with no option found.");
					return false;
				}
				
			}
		} catch (Exception e) {
			System.out.println("some problem has occured" + e.getMessage());
			return false;
		}
		return false;

	}

	/*
	 * 
	 * This method is used to verify the inner text
	 * 
	 */
	public static boolean verifyInnerText(WebElement element, String expectedInnerText) throws InterruptedException {
		try {

			Thread.sleep(2000);
			if (element.isDisplayed() && element.isEnabled()) {
				String actualInnerText = element.getText().trim();
				if (actualInnerText.equalsIgnoreCase(expectedInnerText.trim())) {
					System.out.println("Actual inner text and expected inner text are matching.");
					return true;
				} else {
					System.out.println("Actual inner text and expected inner text are not matching.");
					System.out.println("Expected: " + expectedInnerText + " but found: " + actualInnerText);
					return false;
				}
			} else {
				System.out.println("element is either not enabled or displayed. ");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured.");
			return false;
		}

	}

	/*
	 * This method makes the cursor move to the element using actions class
	 * 
	 */

	public static boolean moveToElement(WebDriver driver, WebElement element) {
		try {
			if (driver != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					Actions action = new Actions(driver);
					action.moveToElement(element).perform();
					//System.out.println("Moved to an element "+element.getText());
					return true;
				} else {
					System.out.println("Element is either not enabled or displayed. ");
					return false;
				}
			} else {
				System.out.println("Driver is null.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured.");
			return false;
		}
	}

	/*
	 * 
	 * This method is used to double click on a element using actions class
	 * 
	 */
	public static boolean doubleClickOnElement(WebDriver driver, WebElement element) {
		try {
			if (driver != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					Actions action = new Actions(driver);
					action.doubleClick(element).perform();
					System.out.println("Double clicked on element.");
					return true;
				} else {
					System.out.println("Element is either not enabled or displayed. ");
					return false;
				}
			} else {
				System.out.println("Driver is null.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured.");
			return false;
		}
	}

	/*
	 * This method is used to context click on an element
	 * 
	 */
	public static boolean contextClickOnElement(WebDriver driver, WebElement element, WebElement menuItemToBeSelected) {
		try {
			if (driver != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					Actions action = new Actions(driver);
					action.contextClick(element).perform();
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOf(menuItemToBeSelected));
					menuItemToBeSelected.click();
					System.out.println("Right clicked on element and selected menu option.");
					return true;
				} else {
					System.out.println("Element is either not enabled or displayed. ");
					return false;
				}
			} else {
				System.out.println("Driver is null.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured.");
			return false;
		}
	}

	/*
	 * This method is used to capture the screenshot of the whole desktop by taking
	 * the teststepname as the argument and stores it in the output folder as
	 * teststepname.png and also returns the path of the folder
	 * 
	 */
	public static String captureScreenshotOfDesktopWithTestStepName(String testStepName) {
		try {
			Robot robot = new Robot();
			BufferedImage tmp = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			String path = System.getProperty("user.dir") + "/Screenshots/" + testStepName + System.currentTimeMillis()
					+ ".png";
			ImageIO.write(tmp, "png", new File(path));
			return path;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return null;
		}

	}

	/*
	 * This method is used to simply capture the screenshot and store it in the
	 * screenshots folder and returns the path of the screenshots folder where the
	 * .png files are stored
	 * 
	 */
	public static String captureScreenshotOfDesktop() {
		try {
			Robot robot = new Robot();
			BufferedImage tmp = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
			ImageIO.write(tmp, "png", new File(path));
			return path;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return null;
		}

	}

	/*
	 * This method is used to capture the screenshot of the visible area by taking
	 * the stepname as input and store it in the Screenshots folder with stepname
	 * and current time .png and returns the path of the output folder
	 * 
	 */
	public static String CaptureScreenShotWithTestStepName(WebDriver driver, String testStepName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshotSRC = ts.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/Screenshots/" + testStepName + System.currentTimeMillis()
					+ ".png";
			File screenshotDest = new File(path);
			FileUtils.copyFile(screenshotSRC, screenshotDest);
			return path;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return null;
		}
	}

	/*
	 * This method is used to capture the image of the visible screen and stores it
	 * in the screenshots folder and returns the path where the .png files are
	 * stored
	 * 
	 */
	public static String CaptureScreenShot(WebDriver driver) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshotSRC = ts.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
			File screenshotDest = new File(path);
			FileUtils.copyFile(screenshotSRC, screenshotDest);
			return path;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return null;
		}
	}

	/*
	 * captures the screen shot of the particular element only with elementname.png
	 * in Screenshots folder
	 */
	public static String CaptureScreenShotOfElement(WebDriver driver, WebElement element, String elementName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			// Capture screenshot of entire visible area
			File screenshotSRC = ts.getScreenshotAs(OutputType.FILE);
			// Convert captured image in to buffered image
			BufferedImage screenshotFull = ImageIO.read(screenshotSRC);

			// Get the location of element on the page
			Point point = element.getLocation();

			// Get width and height of the element
			int eleWidth = element.getSize().getWidth();
			int eleHeight = element.getSize().getHeight();

			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot = screenshotFull.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshotSRC);

			// Copy the element screenshot to disk

			String path = System.getProperty("user.dir") + "/Screenshots/" + elementName + System.currentTimeMillis()
					+ ".png";
			File screenshotDest = new File(path);
			FileUtils.copyFile(screenshotSRC, screenshotDest);
			return path;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return "";
		}
	}

	/*
	 * this method is used to scroll up using java script executor
	 */
	public static void scrollUsingJavaSript(WebDriver driver, int verticalPixels) {
		try {
			if (driver != null) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0," + verticalPixels + ")", "");
			} else {
				System.out.println("Driver is not initialised.");
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
		}
	}

	/*
	 * This method is used to scroll down to the last of the page using java script
	 * executor
	 */
	public static void scrollToLastOfWebPageUsingJavaSript(WebDriver driver) {
		try {
			if (driver != null) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			} else {
				System.out.println("Driver is not initialised.");
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
		}
	}

	/*
	 * This method is used to scroll to the particular element using java script
	 * executor
	 */
	public static String scrollToElementUsingJavaSript(WebDriver driver, WebElement element) {
		try {
			if (driver != null) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView();", element);
			} else {
				System.out.println("Driver is not initialised.");
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
		}
		return null;
	}

	/*
	 * verify particular element is visible or not
	 */
	public static boolean verifyVisibilityOfElement(WebElement element) {
		try {

			if (element.isDisplayed())
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to verify particular element is enabled or not
	 */
	public static boolean verifyEnabilityOfElement(WebElement element) {
		try {

			if (element.isEnabled())
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method verifies weather the image is correctly loaded or not
	 */
	public static boolean veryImageIsNotBroken(WebDriver driver, WebElement element) {
		try {
			String script = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth>0)";
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Object status = jse.executeScript(script, element);
			if (status.equals(true)) {
				System.out.println("Image is not broken.");
				return true;
			} else {
				System.out.println("Image is broken.");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to switch to frame using frame index
	 * 
	 */
	public static boolean switchToFrameUsingFrameIndex(WebDriver driver, int frameIndex) {
		try {
			if (driver != null) {
				try {
					driver.switchTo().frame(frameIndex);
					return true;
				} catch (Exception e) {
					System.out.println("Unable to switch frame." + e.getMessage());
					return false;
				}
			} else {
				System.out.println("Driver is not initilasied.");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to switch to frame using frame name or id
	 */
	public static boolean switchToFrameUsingFrameName(WebDriver driver, String frameName) {
		try {
			if (driver != null) {
				try {
					driver.switchTo().frame(frameName);
					return true;
				} catch (Exception e) {
					System.out.println("Unable to switch frame." + e.getMessage());
					return false;
				}
			} else {
				System.out.println("Driver is not initilasied.");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to get the attribute of a particular element
	 */
	public static String getAttributeOfWebElement(WebElement element, String attributeName) {
		try {
			if (element.isDisplayed()) {
				return element.getAttribute(attributeName);
			} else {
				System.out.println("Element is not displayed.");
				return null;
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return null;
		}
	}

	/*
	 * This method is used to verify attribute value of a particular element
	 */
	public static boolean verifyAttributeOfWebElement(WebElement element, String attributeName, String expected) {
		String actual = null;
		try {
			if (element.isDisplayed()) {
				try {
					actual = element.getAttribute(attributeName);
					Assert.assertEquals(actual, expected);
					return true;
				} catch (Error e) {
					System.out.println("Expecting " + expected + " but found " + actual);
					return false;
				}
			} else {
				System.out.println("Element is not displayed.");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to enter data using send keys option
	 */
	public static void typeText(WebElement element, String text) throws InterruptedException {
		Thread.sleep(2000);
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				element.sendKeys(text);
				Thread.sleep(1000);
				//return true;
			} else {
				System.out.println("Element is not displayed.");
				//return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			//return false;
		}
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

	/*
	 * This method is used to accept alert box
	 */
	public static boolean acceptAlert(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		try {

			driver.switchTo().alert().accept();
			return true;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to dismiss alert box
	 */
	public static boolean dismissAlert(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		try {

			driver.switchTo().alert().dismiss();
			return true;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used to return text in the alert box
	 */
	public static String getAlertText(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		try {

			String alertText = driver.switchTo().alert().getText();
			return alertText;
		} catch (Exception e) {
			System.out.println("Some exception occured." + e.getMessage());
			return "";
		}
	}

	/*
	 * This method is used to click on buttons using java script executor
	 */
	public static boolean clickOnElementUsingJavaScript(WebDriver driver, WebElement element)
			throws InterruptedException {
		Thread.sleep(2000);
		try {

			if (element.isDisplayed() && element.isEnabled()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);
				return true;
			} else {
				System.out.println("element is not clicked.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured. " + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used for clicking on element using the click method
	 */
	public static boolean clickOnElement(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		try {

			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				return true;
			} else {
				System.out.println("element is not clicked.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured. " + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used for drag and drop purpose
	 */
	public static boolean DragAndDrop(WebElement sourceElement, WebElement destinationElement, WebDriver driver)
			throws InterruptedException {
		Thread.sleep(2000);
		try {
			Actions action = new Actions(driver);
			action.dragAndDrop(sourceElement, destinationElement).build().perform();
			return true;
		} catch (Exception e) {
			System.out.println("some problem has occured" + e.getMessage());
		}
		return false;
	}

	/*
	 * This method is used to verify the page title
	 */
	public static boolean verifyTitleOfWebPage(WebDriver driver, String expected) throws InterruptedException {
		Thread.sleep(2000);
		try {

			if (driver != null) {
				String actualtitle = driver.getTitle();
				if (actualtitle.equalsIgnoreCase(expected))

					return true;
				else {
					System.out.println("Expected is " + expected + " but found " + actualtitle);
					return false;
				}

			} else {
				System.out.println("Driver is not initilased.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured. " + e.getMessage());
			return false;
		}
	}

	/*
	 * This method is used for switching between the windows
	 */
	public static boolean switchToWindow(WebDriver driver, String windowHandle) throws InterruptedException {
		Thread.sleep(2000);
		try {

			if (driver != null) {
				driver.switchTo().window(windowHandle);
				return true;

			} else {
				System.out.println("Driver is not initilased.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured. " + e.getMessage());
			return false;
		}
	}

	/*
	 * This method gives the window handles and displays the opened windows
	 */
	public static Set<String> getAllWindowHandles(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		try {

			if (driver != null) {

				Set<String> allHandles = driver.getWindowHandles();
				System.out.println("Total opened window: " + allHandles.size());
				return allHandles;

			} else {
				System.out.println("Driver is not initilased.");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured. " + e.getMessage());
			return null;
		}
	}

	/*
	 * This method is used to close all the child windows
	 */
	public static boolean closeAllChildWindows(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		try {

			if (driver != null) {
				String currentWindowhandle = driver.getWindowHandle();
				Set<String> allHandles = driver.getWindowHandles();
				System.out.println("Total opened window: " + allHandles.size());
				for (String handles : allHandles) {
					if (handles != currentWindowhandle) {
						driver.switchTo().window(handles).close();
					}
				}
				return true;

			} else {
				System.out.println("Driver is not initilased.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Some exception occured. " + e.getMessage());
			return false;
		}
	}

	/*
	 * This method provides fluent wait and returns WebElement
	 */
	/*public static WebElement fluentWait(final WebElement webElement, int timeinsec, WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return webElement;
			}
		});

		return element;
	}*/

}