package com.codesling.image.batch.util;


public class IntegerUtil {


	public static String zeroFill (Integer val, int size){
		return zeroFill(val.toString(), size);
	}
	
	public static String zeroFill (String val, int size){
		return charFill(val, size, "0");
	}
	
	public static String charFill (String val, int size, String ch){
		if (size<=val.length()){
			return val;
		}
			
		StringBuilder sb = new StringBuilder();
		
		for (int i=size-val.length(); i>0; i--) {
		    sb.append(ch);
		}
		sb.append(val);
		return sb.toString();
	}
}
