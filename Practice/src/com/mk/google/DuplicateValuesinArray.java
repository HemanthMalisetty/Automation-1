package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DuplicateValuesinArray {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the size of array");
		
		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[n];
		
		System.out.println("please enter the numbers");
		
		for(int i=0;i<n;i++)
		{
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		Set<Integer> se=new HashSet();
		
		int limit=n-1;
		
		for(int x=0;x<limit;x++)
		{
			for(int y=0;y<limit-x;y++)
			{
				if(arr[y]==arr[y+1])
				{
					System.out.println("it is a duplicate value ");
					
				}
				else
				{
					se.add(arr[y]);
				}
			}
		}
		
		Iterator it=se.iterator();
		
		while(it.hasNext())
		{
			int x=(int) it.next();
			
			System.out.println(x);
			
		}
		
		
	}

}
