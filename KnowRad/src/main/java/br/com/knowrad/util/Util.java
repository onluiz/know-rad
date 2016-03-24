package br.com.knowrad.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static Boolean verifyList(List list) {
		return list != null && !list.isEmpty();
    }

	public static List<Long> objectToArrayListLong(Object val) {

		if(val == null) {
			return new ArrayList<Long>();
		}

		try {
			return (ArrayList<Long>) val;
		} catch(Exception e) {
			return new ArrayList<Long>();
		}
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

	public static String cleanText(String string) {
		if(string == null)
			return "";

		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\p{ASCII}]", "");
		string = string.toLowerCase();

		return string;
	}
	
}
