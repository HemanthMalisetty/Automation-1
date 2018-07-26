package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Anagram {

	public static void isAnagram()throws Exception {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("please enter the first string ");
		
		String str1=br.readLine();
		
		System.out.println("please enter the second string");
		
		String str2=br.readLine();
		
		str1=str1.replaceAll("//s","").toLowerCase();
		
		str2=str2.replaceAll("//s","").toLowerCase();
		
		boolean status = true;
		
		if(str1.length() != str2.length())
        {
           status = false;
        }
		else
		{
		char[] arr1=str1.toCharArray();
		
		 for (char c : arr1)
         {
             int index = str2.indexOf(c);

             if(index != -1)
             {
            	 str2=str2.substring(0,index)+str2.substring(index+1, str2.length());            	 
             }
             else
             {
            	 status=false;
            	 break;
             }
		
		
         }
		}
		if(status)
        {
            System.out.println(str1+" and "+str2+" are anagrams");
        }
        else
        {
            System.out.println(str1+" and "+str2+" are not anagrams");
        }
		
		

	}
	public static void main(String[] args)throws Exception {
		isAnagram();
	}

}
