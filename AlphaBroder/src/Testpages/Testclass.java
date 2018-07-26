package Testpages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testclass {

	static WebDriver driver = null;
	static HashMap<String, Integer> hmap = null;
	static HashMap<String, Integer> output = null;
	static HashMap<Integer, Integer> map = null;
	static HashMap<Integer, Integer> columnmap = null;
	static Float total = 0.0f;
	static int col = 0;
	static int row = 0;
	static int column = 0;
	static int rowsize = 0;

	public static void main(String[] args) throws Exception {

		output = new HashMap<String, Integer>();

		System.setProperty("webdriver.chrome.driver", ".\\exefiles\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://www.alphabroder.com/");

		driver.findElement(By.xpath(".//*[@id='userName']")).click();

		driver.findElement(By.xpath(".//*[@id='userName']")).sendKeys("perftest100");

		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("password");

		driver.findElement(By.xpath(".//*[@id='goBtn']")).click();

		driver.findElement(By.xpath(".//*[@id='subMenu1']/ul/li[3]/a")).click();

		driver.findElement(
				By.xpath(".//td[@class='imageTD']/div[2]/a/img[@title=\"G500 Gildan Adult 5.3 oz. T-Shirt\"]")).click();

		String stylecode=driver.findElement(
				By.xpath(".//*[@id='style-header-name']/h1")).getText();
		
		output = enter_details_into_warehouse(driver, "5/10/15/20/50");

		// loop to display the contents of hashmap
		Iterator<String> itr = output.keySet().iterator();
		while (itr.hasNext()) {
			String str = itr.next();
			System.out.print(str);
			System.out.println(" " + output.get(str));

		}
		output.size();
		
		driver.findElement(
				By.xpath(".//div[text()='Add & Checkout'][1]")).click();
		
		Boolean flag=checkingstyle(output,stylecode);
		
		if(flag)
		{
			System.out.println("Success 1");
		}
		else
		{
			System.out.println("Failure 1");
		}
		
		Boolean flag1=checking_size_quantity(output);
		
		if(flag1)
		{
			System.out.println("Success 2");
		}
		else
		{
			System.out.println("Failure 2");
		}
		
		Boolean flag2=	costverification(output);
		
		if(flag2)
		{
			System.out.println("Success 3");
		}
		else
		{
			System.out.println("Failure 3");
		}
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(".//input[@name='poNumber']")).sendKeys("12345676543");
		
		driver.findElement(By.xpath(".//img[@alt='Verify Order']")).click();
	}

	public static HashMap enter_details_into_warehouse(WebDriver driver, String quantity) {
		// HashMap to collect the return type
		hmap = new HashMap<String, Integer>();

		columnmap = new HashMap<Integer, Integer>();

		map = new HashMap<Integer, Integer>();
		// Scanner class to read complex string and break it to pieces
		Scanner sc = new Scanner(quantity.trim()).useDelimiter("/");

		// checking condition if "sc" is having next value or not
		while (sc.hasNextInt()) {

			// collecting the piece from number of quantities
			String quant = sc.next();

			// caliculating the ending tr value in table
			List<WebElement> trs = driver.findElements(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr"));

			// caliculating row size
			rowsize = trs.size();

			for (int k = 7; k <= 7; k++) {
				WebElement ele1 = driver
						.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + k + "]"));

				List<WebElement> tds = ele1.findElements(By.tagName("td"));

				// assigning column size
				col = tds.size() - 2;

				// selecting a random value in the column
				column = rand(2, col);

				// selecting a random row
				row = rand(7, rowsize);

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

								row = rand(7, rowsize);

								System.out.println("selecting another warehouse");

								column = rand(2, col);

								System.out.println("selecting another size");

							}
							// entering the data
							else {
								// checking if it is already entered or not
								if (verification(row, map, column, columnmap)) {

									enter.sendKeys(quant);

									String warehouse = ware_house(row);

									// displaying the ware house name where the quantity is entered
									System.out.println("Order placed in Warehouse =" + warehouse);

									// collecting size entered
									String sizeofproduct = size(column);

									// storing size entered and quantity into hashmap
									hmap.put(sizeofproduct, Integer.parseInt(quant));

									// collecting cost for the individual quantity entered
									String costofproduct = cost(column, Integer.parseInt(quant));

									// calculating total value of multiple quantity entered
									total = total + Float.parseFloat(costofproduct);

									flag = false;

									// storing the quantity entered locations into hashmap
									map.put(row, column);

									columnmap.put(column, row);

								}
								// selecting another location since it is already entered
								else {

									row = rand(7, rowsize);

									column = rand(2, col);

									System.out.println(
											"Selecting another row and column since it is already entered once");

								}

							}

						}
						// selecting another location since quantity constrains
						else {

							System.out.println("selecting another size.");

							row = rand(7, rowsize);

							column = rand(2, col);

						}
					}
					// selecting another location since None is present
					else {

						System.out.println("selecting another size");

						column = rand(2, col);
					}

				}
				System.out.println("Total cost =" + Float.toString(total) + "$");
			}
		}
		// clearing the map for next usage
		map.clear();
		columnmap.clear();
		sc.close();
		return hmap;
	}

	// method to select random numbers
	public static int rand(int min, int max) {
		Random ran = new Random();

		int x = ran.nextInt((max - min) + 1) + min;

		return x;
	}

	// method to gather the warehouse information
	public static String ware_house(int row) {

		WebElement warehouse_name = driver
				.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + row + "]/td[" + 1 + "]"));

		String warehousename = warehouse_name.getText();

		return warehousename;
	}

	// method to gather size entered
	public static String size(int column) {

		WebElement sizereqired = driver
				.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 4 + "]/td[" + column + "]"));

		String size = sizereqired.getText();

		return size;
	}

	// method to caliculate the cost
	public static String cost(int column, int quantity) {

		WebElement price = driver
				.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 6 + "]/td[" + 1 + "]"));

		String tagname = price.getText();

		tagname = tagname.substring(0, 14).trim();

		// checking if special price tag is present or not
		if (tagname.equalsIgnoreCase("Special Price")) {

			String str = spcost(column, quantity);

			return str;
		} else {

			String str1 = regularcost(column, quantity);

			return str1;
		}
	}

	// method if special price tag is present
	public static String spcost(int column, int quantity) {

		WebElement specialprize = driver
				.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 6 + "]/td[" + column + "]"));

		String specialcost = specialprize.getText();

		// checking if special price contains N/A
		if ((specialprize.getText().trim()).equalsIgnoreCase("N/A")) {

			WebElement regularprize = driver.findElement(
					By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 5 + "]/td[" + column + "]"));

			// calling regular cost method
			String regularcost = regularprize.getText();

			regularcost = regularcost.replaceAll("[$]", "");

			Float finalprize = (Float.valueOf(regularcost)) * quantity;

			String finalcost = Float.toString(finalprize);

			return finalcost;
		} else {

			// collecting special price value and calculating the price
			specialcost = specialcost.replaceAll("[$]", "");

			Float finalvale = (Float.valueOf(specialcost)) * quantity;

			String finalvalue = Float.toString(finalvale);

			return finalvalue;
		}
	}

	// method for regular cost caliculation
	public static String regularcost(int column, int quantity) {
		WebElement regularprize = driver
				.findElement(By.xpath(".//div[@id='tabs1-Warehouse']/table/tbody/tr[" + 5 + "]/td[" + column + "]"));

		String regularcost = regularprize.getText();

		regularcost = regularcost.replaceAll("[$]", "");

		Float finalprize = (Float.valueOf(regularcost)) * quantity;

		String finalcost = Float.toString(finalprize);

		return finalcost;

	}

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
	
	//method to validate for only one product with single style code
	public static Boolean checkingstyle(HashMap<String, Integer> mapping,String stylecode)
	{
		int ordersplaced=mapping.size();
		
		int loopcount=2+ordersplaced;
		
		Boolean flag=true;
		
		for(int i=3;i<=loopcount;i++)
		{
			WebElement style=driver.findElement(By.xpath(".//div[@class='OdrInfoClip']/table[1]/tbody/tr["+i+"]/td[1]"));
			
			String code=style.getText();
			
			if(stylecode.equalsIgnoreCase(code))
			{
				flag=true;
			}
			else
			{
				System.out.println("checking style method failure");
				flag=false;
				
				break;
			}
			
		}
		return flag;
	}
	
	public static Boolean checking_size_quantity(HashMap<String, Integer> mapping)
	{
         int ordersplaced=mapping.size();
		
		 int loopcount=2+ordersplaced;
		
		 Boolean flag=true;
		 
		 for(int i=3;i<=loopcount;i++)
		 {
			 WebElement prodsize=driver.findElement(By.xpath(".//div[@class='OdrInfoClip']/table[1]/tbody/tr["+i+"]/td[4]"));
			 
			 String size=prodsize.getText();
			 
			 WebElement style=driver.findElement(By.xpath(".//div[@class='OdrInfoClip']/table[1]/tbody/tr["+i+"]/td[7]/input"));
			 
			 int quantity_in_current_page=Integer.parseInt(style.getAttribute("value"));
			 
			 if(mapping.get(size)!=null)
			 {
				 int quantity_in_prev_page=mapping.get(size);
				 
				 if(quantity_in_prev_page==quantity_in_current_page)
				 {
					 flag=true;
				 }
				 else
				 {
					 System.out.println("checking size and quantity method failure");
					 
					 System.out.println("quantity is not same");
					
					 flag=false;
					
					 break;
				 }
				 
			 }
			 else
			 {
				 flag= false;
				 break;
			 }
		 }
		
		 return flag;
	}
	
	public static Boolean costverification(HashMap<String, Integer> mapping)
	{
		 int ordersplaced=mapping.size();
		
		 int loopcount=2+ordersplaced;
		
		 Boolean flag=true;
		 
		 for(int i=3;i<=loopcount;i++)
		 {
             WebElement quantity=driver.findElement(By.xpath(".//div[@class='OdrInfoClip']/table[1]/tbody/tr["+i+"]/td[7]/input"));
			 
			 int quantity_in_current_page=Integer.parseInt(quantity.getAttribute("value"));
			 
             WebElement cost=driver.findElement(By.xpath(".//div[@class='OdrInfoClip']/table[1]/tbody/tr["+i+"]/td[5]"));
			 
			 Float costofsingleprod=Float.parseFloat(cost.getText().replaceAll("[$]",""));
			 
			 WebElement costintable=driver.findElement(By.xpath(".//div[@class='OdrInfoClip']/table[1]/tbody/tr["+i+"]/td[9]"));
			 
			 Float costdisplayed=Float.parseFloat(costintable.getText().replaceAll("[$]",""));
			 
			 Float finalcost=quantity_in_current_page*costofsingleprod;
			 
			 int finalcost1=Math.round(finalcost);
			 
			 int costdisplayed1=Math.round(costdisplayed);
			 
			 
			 if(finalcost1==costdisplayed1)
			 {
				 flag= true;
			 }
			 else
			 {
				System.out.println("costverification method failure");
				 flag=false;
				 break;
				
			 }
			 
			 
		 }
		 return flag;		
		 
	}
	

}
