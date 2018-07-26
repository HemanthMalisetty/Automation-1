package com.mk.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LengthOfString {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the string");

		String str = br.readLine();

		int count = 0;

		StringTokenizer st = new StringTokenizer(str, " ");

		List<String> list = new ArrayList<String>();

		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}

		for (String s : list) {

			for (char c : s.toCharArray()) {
				count++;
			}

		}
		System.out.println("The total length of characters in a string is " + count);

	}

}
