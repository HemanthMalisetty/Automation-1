package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibbonaci {

	static int f1=0;
	static int f2=1;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
	    System.out.println("Please enter the series range ");
	    
	    int n=Integer.parseInt(br.readLine());
	    
	    System.out.println(f1);
	    
	    System.out.println(f2);
	    
	    int f=f1+f2;
	    
	    System.out.println(f);
	    
	    for(int i=3;i<=n;i++)
	    {
	    	f1=f2;
	    	f2=f;
	    	f=f1+f2;	    	
	    	System.out.println(f);
	    }
		

	}

}
