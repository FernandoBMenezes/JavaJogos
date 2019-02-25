package com.mondergames.Encrypts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {
	public static final String DEFAULT_ENCODING = "UTF-8";
	static BASE64Encoder enc = new BASE64Encoder();
	static BASE64Decoder dec = new BASE64Decoder();

	public static String base64encode(String text) {
		try {
			return enc.encode(text.getBytes(DEFAULT_ENCODING));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String base64decode(String text) {
		try {
			return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
		} catch (IOException e) {
			return null;
		}
	}
	//
	public static String double64encode(Double db) {
		String text = toString(db);
		try {
			return enc.encode(text.getBytes(DEFAULT_ENCODING));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static double double64decode(String text) {
		try {
			//
			String textf = text;
			textf = base64decode(textf);
			return toDouble(textf);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static double toDouble(String sti) {
		String value;
		value = sti;
		value = value.replaceAll("q", "0");
		value = value.replaceAll("w", "1");
		value = value.replaceAll("e", "2");
		value = value.replaceAll("r", "3");
		value = value.replaceAll("t", "4");
		value = value.replaceAll("y", "5");
		value = value.replaceAll("u", "6");
		value = value.replaceAll("i", "7");
		value = value.replaceAll("o", "8");
		value = value.replaceAll("p", "9");
		return Double.parseDouble(value);
	}

	public static String toString(double its) {
		double value;
		String valuef = null;
		value = its;
		valuef = ("" + value).replaceAll("0", "q");
		valuef = valuef.replaceAll("1", "w");
		valuef = valuef.replaceAll("2", "e");
		valuef = valuef.replaceAll("3", "r");
		valuef = valuef.replaceAll("4", "t");
		valuef = valuef.replaceAll("5", "y");
		valuef = valuef.replaceAll("6", "u");
		valuef = valuef.replaceAll("7", "i");
		valuef = valuef.replaceAll("8", "o");
		valuef = valuef.replaceAll("9", "p");
		return valuef;
	}
}
