package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringToken {
	
	static String str=null;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("please enter the data");
		
		str=br.readLine();
		
		StringTokenizer st=new StringTokenizer(str);
		
		String s1=st.nextToken(" ");
		
		String s2=st.nextToken(" ");
		
		String s3=st.nextToken(" ");
		
		s1.trim();
		
		s2.trim();
		
		s3.trim();
		
		System.out.println("String s1 is "+s1);
		
		System.out.println("String s2 is "+s2);
		
		System.out.println("String s3 is "+s3);
		
		
	}

}
