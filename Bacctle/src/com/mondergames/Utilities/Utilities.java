package com.mondergames.Utilities;


public class Utilities {
	public static int toAngle(int i) {
		if(i <= -90) return -90;
		if(i >= 90) return 90;
		return i;
	}
}
