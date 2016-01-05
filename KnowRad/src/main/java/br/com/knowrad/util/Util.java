package br.com.knowrad.util;

public class Util {

	public static Long verifyLong(Object val) {
		try {
			return Long.valueOf(String.valueOf(val));
		} catch(Exception e) {
			return new Long(0);
		}
	}
	
	public static String verifyString(Object val) {
		try {
			return String.valueOf(val);
		} catch(Exception e) {
			return "";
		}
	}
	
}
