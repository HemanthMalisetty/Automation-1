package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Arrays {

	public static void main(String[] args) throws Exception {

		int arr1[][] = returnarray();
		int arr2[][] = returnarray();
		System.out.println("The detais of Array 1 are ");
		displayArray(arr1);
		System.out.println("The detais of Array 2 are ");
		displayArray(arr2);
		int arr3[][] = resultarray(arr1, arr2);
		System.out.println("The detais of Array 3 are ");
		displayArray(arr3);

	}

	public static int[][] returnarray() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the rows and columns");

		int row = Integer.parseInt(br.readLine());

		int column = Integer.parseInt(br.readLine());

		int[][] arr = new int[row][column];
		
		System.out.println("Please enter the numbers into array ");

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				
				arr[i][j] = Integer.parseInt(br.readLine());

			}
		}

		return arr;
	}

	public static int[][] resultarray(int[][] arr1, int[][] arr2) {
		int[][] arr = new int[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				arr[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
		return arr;
	}

	public static void displayArray(int[][] arr) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(arr[i][j] + "  ");
			}
			System.out.println();
		}
	}
}
