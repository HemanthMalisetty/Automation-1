package com.mk.onchip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Addition {
	public static String getdate()
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		String str=dateFormat.format(date);
		
		return str;
	}

}
