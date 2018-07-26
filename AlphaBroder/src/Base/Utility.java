package Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utility extends Base {

	static WebDriver driver = null;
	static HashMap<String, Integer> hmap = null;
	static HashMap<Integer, Integer> rowmap = null;
	static HashMap<Integer, Integer> colmap = null;
	static Float total = 0.0f;
	static int col = 0;
	static int row = 0;
	static int column = 0;
	static int rowsize = 0;
	static Scanner sc = null;
	static Properties prop = null;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public static WebDriver launchurl(String url) {

		System.setProperty("webdriver.chrome.driver", ".\\exefiles\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.alphabroder.com/cgi-bin/online/webshr/ABtest.w?set=A");

		driver.get(url);

		return driver;
	}

	public static Properties loadfile() {
		try {
			prop = new Properties();

			File f = new File(".\\config\\config.properties");

			FileInputStream fis = new FileInputStream(f);

			prop.load(fis);

			File f1 = new File(".\\config\\credentials.properties");

			FileInputStream fis1 = new FileInputStream(f1);

			prop.load(fis1);

			File f2 = new File(".\\config\\ca_credentials.properties");

			FileInputStream fis2 = new FileInputStream(f2);

			prop.load(fis2);

			File f3 = new File(".\\config\\ca_config.properties");

			FileInputStream fis3 = new FileInputStream(f3);

			prop.load(fis3);

			return prop;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public WebElement getelement(String path) {

		// Properties prop = loadfile();

		if (path.endsWith("-xpath")) {
			WebElement ele = driver.findElement(By.xpath(prop.getProperty(path)));

			return ele;
		} else if (path.endsWith("-id")) {
			WebElement ele = driver.findElement(By.id(prop.getProperty(path)));

			return ele;
		} else if (path.endsWith("-name")) {
			WebElement ele = driver.findElement(By.name(prop.getProperty(path)));

			return ele;

		} else if (path.endsWith("-class")) {
			WebElement ele = driver.findElement(By.className(prop.getProperty(path)));

			return ele;
		} else if (path.endsWith("-tagname")) {
			WebElement ele = driver.findElement(By.tagName(prop.getProperty(path)));

			return ele;
		} else {
			return null;
		}

	}

	public static int randint(int min, int max) {
		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public String validate(String xpath) {
		WebElement validation = getelement(xpath);

		String text = validation.getText();

		return text;
	}

	public String validation_using_attribute(String xpath) {
		WebElement validation = getelement(xpath);

		String text = validation.getAttribute("alt");

		return text;
	}

	// complete logic implementation by simply calling this method
	public static HashMap enter_details_into_warehouse(String quantity) {
		// Hashmap to collect the return type
		hmap = new HashMap<String, Integer>();

		rowmap = new HashMap<Integer, Integer>();

		colmap = new HashMap<Integer, Integer>();

		// Scanner class to read complex string and break it to pieces
		sc = new Scanner(prop.getProperty(quantity).trim()).useDelimiter("/");

		// checking condition if "sc" is having next value or not
		while (sc.hasNextInt()) {

			// collecting the piece from number of quantities
			String quant = sc.next();

			// calculating the ending tr value in table
			List<WebElement> trs = driver.findElements(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr"));

			// calculating row size
			rowsize = trs.size();

			//The reason for setting the value of k as 7 is,it is the location in the table from where the quantity is to be entered
			for (int k = 7; k <= 7; k++) {
				WebElement ele1 = driver
						.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + k + "]"));

				List<WebElement> tds = ele1.findElements(By.tagName("td"));

				// assigning column size
				col = tds.size() - 2;

				// selecting a random value in the column
				column = randint(2, col);

				// selecting a random row
				row = randint(7, rowsize);

				Boolean flag = true;

				while (flag) {

					WebElement data = driver.findElement(
							By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + row + "]/td[" + column + "]"));

					// checking if the pointed column contains none or not
					if ((data.getText()).compareTo("None") != 0) {

						// checking if the quantity is less than the quantity present in the warehouse
						if (Integer.parseInt(data.getText().replaceAll("[+]", "")) > Integer.parseInt(quant)) {

							WebElement enter = driver
									.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + row
											+ "]/td[" + column + "]/input"));

							String atrvalue = enter.getAttribute("value").trim();

							// checking is the pointed location is empty or having call string in there and
							// also selecting another location
							if (atrvalue.equalsIgnoreCase("Call")) {

								row = randint(7, rowsize);

								System.out.println("selecting another warehouse");

								column = randint(2, col);

								System.out.println("selecting another size");

							}
							// entering the data
							else {
								// checking if it is already entered or not
								if (verification(row, rowmap, column, colmap)) {

									//enter.sendKeys(quant);
									
									try {
										typeTextUsingJavaScript(driver, enter, quant);
									} catch (InterruptedException e) {
										System.out.println("Jave script executor error");
										e.printStackTrace();
									}

									// String warehouse = ware_house(row);

									// displaying the ware house name where the quantity is entered
									// System.out.println("Order placed in Warehouse =" + warehouse);

									// collecting size entered
									String sizeofproduct = size(column);

									// storing size entered and quantity into hashmap
									hmap.put(sizeofproduct, Integer.parseInt(quant));

									// collecting cost for the individual quantity entered
									// String costofproduct = cost(column, Integer.parseInt(quant));

									// calculating total value of multiple quantity entered
									// total = total + Float.parseFloat(costofproduct);

									flag = false;

									// storing the entered row and column for further verification
									rowmap.put(row, column);

									// storing the entered row and column for further verification
									colmap.put(column, row);

								}
								// selecting another location since it is already entered
								else {

									row = randint(7, rowsize);

									column = randint(2, col);

									System.out.println(
											"Selecting another row and column since it is already entered once");

								}

							}

						}
						// selecting another location since quantity constrains
						else {

							System.out.println("selecting another size.");

							row = randint(7, rowsize);

							column = randint(2, col);

						}
					}
					// selecting another location since None is present
					else {

						System.out.println("selecting another size");

						column = randint(2, col);
					}

				}
				//System.out.println("Total cost =" + Float.toString(total) + "$");
			}
		}
		// clearing the map for next usage
		rowmap.clear();
		colmap.clear();
		sc.close();
		return hmap;
	}

	/*
	 * // method to gather the warehouse information public static String
	 * ware_house(int row) {
	 * 
	 * WebElement warehouse_name = driver
	 * .findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + row +
	 * "]/td[" + 1 + "]"));
	 * 
	 * String warehousename = warehouse_name.getText();
	 * 
	 * return warehousename; }
	 */
	// method to gather size entered
	public static String size(int column) {

		WebElement sizereqired = driver
				.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 4 + "]/td[" + column + "]"));

		String size = sizereqired.getText();

		return size;
	}

	/*
	 * // method to calculate the cost public static String cost(int column, int
	 * quantity) {
	 * 
	 * WebElement price = driver
	 * .findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 6 +
	 * "]/td[" + 1 + "]"));
	 * 
	 * String tagname = price.getText();
	 * 
	 * tagname = tagname.substring(0, 14).trim();
	 * 
	 * // checking if special price tag is present or not if
	 * (tagname.equalsIgnoreCase("Special Price")) {
	 * 
	 * String str = spcost(column, quantity);
	 * 
	 * return str; } else {
	 * 
	 * String str1 = regularcost(column, quantity);
	 * 
	 * return str1; } }
	 * 
	 * // method if special price tag is present public static String spcost(int
	 * column, int quantity) {
	 * 
	 * WebElement specialprize = driver
	 * .findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 6 +
	 * "]/td[" + column + "]"));
	 * 
	 * String specialcost = specialprize.getText();
	 * 
	 * // checking if special price contains N/A if
	 * ((specialprize.getText().trim()).equalsIgnoreCase("N/A")) {
	 * 
	 * WebElement regularprize = driver.findElement(
	 * By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 5 + "]/td[" +
	 * column + "]"));
	 * 
	 * // calling regular cost method String regularcost = regularprize.getText();
	 * 
	 * regularcost = regularcost.replaceAll("[$]", "");
	 * 
	 * Float finalprize = (Float.valueOf(regularcost)) * quantity;
	 * 
	 * String finalcost = Float.toString(finalprize);
	 * 
	 * return finalcost; } else {
	 * 
	 * // collecting special price value and calculating the price specialcost =
	 * specialcost.replaceAll("[$]", "");
	 * 
	 * Float finalvale = (Float.valueOf(specialcost)) * quantity;
	 * 
	 * String finalvalue = Float.toString(finalvale);
	 * 
	 * return finalvalue; } }
	 * 
	 * // method for regular cost calculation public static String regularcost(int
	 * column, int quantity) { WebElement regularprize = driver
	 * .findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 5 +
	 * "]/td[" + column + "]"));
	 * 
	 * String regularcost = regularprize.getText();
	 * 
	 * regularcost = regularcost.replaceAll("[$]", "");
	 * 
	 * Float finalprize = (Float.valueOf(regularcost)) * quantity;
	 * 
	 * String finalcost = Float.toString(finalprize);
	 * 
	 * return finalcost;
	 * 
	 * }
	 */
	// method for the verification of location
	public static Boolean verification(int row, HashMap<Integer, Integer> mapping, int column,
			HashMap<Integer, Integer> colmapping) {

		if (mapping.get(row) == null) {

			if (colmapping.get(column) == null) {

				System.out.println("verification success");

				return true;

			} else {

				System.out.println("already entered in this column");

				return false;
			}

		} else {

			System.out.println("verification failure");

			return false;

		}
	}
}
