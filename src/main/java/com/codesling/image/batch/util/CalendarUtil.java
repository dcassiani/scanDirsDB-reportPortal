package com.codesling.image.batch.util;

import java.util.Calendar;

public class CalendarUtil {

	public static String getDateTimeLabel (Calendar cal){
		if (cal==null) return "";
		String str = getDateLabel(cal)
				.concat(" ")
				.concat(getTime24Label(cal));
		
		
		return str;
	}

	public static String getTime24Label(Calendar cal) {
		if (cal==null) return "";
		String str = IntegerUtil.zeroFill(cal.get(Calendar.HOUR_OF_DAY),2) + 
				":" + IntegerUtil.zeroFill(cal.get(Calendar.MINUTE),2);		
		return str;
	}
	
	public static String getTime12Label(Calendar cal) {
		if (cal==null) return "";
		String str = IntegerUtil.zeroFill(cal.get(Calendar.HOUR),2) + 
				":" + IntegerUtil.zeroFill(cal.get(Calendar.MINUTE),2);		
		return str;
	}

	public static String getDateLabel(Calendar cal) {
		if (cal==null) return "";
		String str = IntegerUtil.zeroFill(cal.get(Calendar.DAY_OF_MONTH),2) + 
				"/" + IntegerUtil.zeroFill((cal.get(Calendar.MONTH)+1),2) +
				"/" + (cal.get(Calendar.YEAR)+1);		
		
		return str;
	}
}
