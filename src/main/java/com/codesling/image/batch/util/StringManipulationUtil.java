package com.codesling.image.batch.util;

public class StringManipulationUtil {

	public static String replaceToken(String string, String schema, String token) {
		if (string.indexOf(token) >= 1){
			return string.replaceAll(token, schema);
		} else {
			return string;
		}
	}
	
	

}
