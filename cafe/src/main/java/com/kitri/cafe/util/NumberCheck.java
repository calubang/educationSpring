package com.kitri.cafe.util;

public class NumberCheck {
	//숫자 아닌경우 0을 return
	public static int notNumberToZero(String temp) {
		int result = 0;
		if(isNumber(temp)) {
			result = Integer.parseInt(temp);
		}
		
		return result;
	}
	
	//숫자가 아닌경우 1을 return
	public static int notNumberToOne(String temp) {
		int result = 1;
		if(isNumber(temp)) {
			result = Integer.parseInt(temp);
		}
		
		return result;
	}
	
	private static boolean isNumber(String temp) {
		//널체크부터
		if(temp == null) {
			return false;
		}
		
		for(int i=0 ; i<temp.length() ; i++) {
			char tempChar = temp.charAt(i);
			if(tempChar > '9' || tempChar < '0') {
				return false;
			}
		}
		
		return true;
	}
}
