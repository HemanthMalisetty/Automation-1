package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BubbleSortPrac {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the length of the array");
		
		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[n];
		
		System.out.println("Please enter the contents of the array");
		
		for(int i=0;i<n;i++)
		{
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		//display the contents of the array before changing
		
        System.out.println("The contents of the array before interchanging are");
		
		for(int i=0;i<n;i++)
		{
			System.out.println(arr[i]);
		}
		
		int limit=n-1;
		
		for(int i=0;i<limit;i++)
		{
			for(int j=0;j<limit-i;j++)
			{
				if(arr[j]>arr[j+1])
				{
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		//display the array contents
		
		System.out.println("The contents of the array after interchanging are");
		
		for(int i=0;i<n;i++)
		{
			System.out.println(arr[i]);
		}
	}

}
