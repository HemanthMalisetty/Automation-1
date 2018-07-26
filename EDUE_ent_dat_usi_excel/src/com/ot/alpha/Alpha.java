package com.ot.alpha;

import org.testng.TestNG;

public class Alpha {

	public static void main(String[] args) {
		
		 TestNG testng = new TestNG();
         Class[] classes = new Class[]{Filling_Form.class};
         testng.setTestClasses(classes);
         testng.run();

	}

}
