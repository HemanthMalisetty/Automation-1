package Testpages;

import org.testng.TestNG;

public class Alphabroder {

	public static void main(String[] args) {
		
		 TestNG testng = new TestNG();
         Class[] classes = new Class[]{Page1.class};
         testng.setTestClasses(classes);
         testng.run();
		

	}

}
