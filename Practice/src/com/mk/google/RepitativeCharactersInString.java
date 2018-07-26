package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RepitativeCharactersInString {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the string");
		
		String str=br.readLine();
		
		HashMap<Character,Integer> map=new HashMap<Character,Integer>();
		
		for(char c:str.toCharArray())
		{
		   if(map.containsKey(c))
		   {
			  int count=map.get(c);
			  
			  map.put(c,++count);
		   }
		   else
		   {
			  map.put(c,1);
		   }
		} 
		
		map.forEach((key, value) -> System.out.println(key + " : " + value));
	}

}
