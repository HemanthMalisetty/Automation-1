package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BubbleSort {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
		System.out.println("Please enter the number of numbers you want to arrange");

		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[n];
		
		for(int i=0;i<n;i++)
		{
			System.out.print("Please enter Number :");
			
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		int limit=n-1;
		
		Boolean flag=false;
		
		for(int i=0;i<limit;i++)
		{
			for(int j=0;j<limit-i;j++)
			{
				if(arr[j]>arr[j+1])
				{
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
					System.out.println("Values got interchanged");
					flag=true;
				}
			}
			if(flag==false)
			{
				System.out.println("No swapping required");
				break;
		    }
			else
			{
				flag=false;
			}
			
	   }
		
		System.out.println("The numbers in ascending order are");
		
		for(int i=0;i<n;i++)
		{
		  System.out.println(arr[i]);	
		}
		
		
}

}
