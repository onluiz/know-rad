package br.com.knowrad.util;

import java.util.List;

public class Util {

	public static Boolean verifyList(List list) {
		return list != null && !list.isEmpty();
    }

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
