package com.ot.alpha;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Entering_Date {

	
	int j = 0;
	String month = null;
	String currentmonth = null;
	Base b = null;
	Properties prop = null;

	/*
	 * method to click on particular date of calender
	 */
	public void clickondate(WebDriver driver,String date) throws Exception {
		b = new Base(driver);

		prop = b.loadfile();

		DateFormat input = new SimpleDateFormat("mm/dd/yyyy");

		DateFormat output = new SimpleDateFormat("mm");

		DateFormat reqdate = new SimpleDateFormat("dd");

		String in = date;

		Date crmonth = input.parse(in);

		String out = output.format(crmonth);

		Date crdate = input.parse(in);

		String dat = reqdate.format(crdate);

		j = Integer.parseInt(out);

		if (j <= 12) {
			if (j == 1) {
				month = "January";
			} else if (j == 2) {
				month = "February";
			} else if (j == 3) {
				month = "March";
			} else if (j == 4) {
				month = "April";
			} else if (j == 5) {
				month = "May";
			} else if (j == 6) {
				month = "June";
			} else if (j == 7) {
				month = "July";
			} else if (j == 8) {
				month = "August";
			} else if (j == 9) {
				month = "September";
			} else if (j == 10) {
				month = "October";
			} else if (j == 11) {
				month = "November";
			} else if (j == 12) {
				month = "December";
			}

			for (int i = 1; i <= 12; i++) {
				currentmonth = driver.findElement(By.xpath(prop.getProperty("monthname-xpath"))).getText();

				if (currentmonth.equalsIgnoreCase(month)) {
					List<WebElement> l2 = driver.findElements(By.tagName("td"));

					for (int k = 0; k < l2.size(); k++) {
						WebElement ele = l2.get(k);

						String str = ele.getText();

						if (str.equalsIgnoreCase(dat)) {
							ele.click();

							return;
						}
					}
				} else {
					driver.findElement(By.xpath(prop.getProperty("nextmonth-xpath"))).click();
				}
			}

		} else {
			System.out.println("Please enter the correct month");
		}
	}
}
