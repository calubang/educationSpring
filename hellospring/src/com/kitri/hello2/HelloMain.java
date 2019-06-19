package com.kitri.hello2;

public class HelloMain {
	public static void main(String[] args) {
		HelloServiceKor kor = new HelloServiceKor();
		String msg = kor.hello("안병욱");
		
		System.out.println(msg);
		
		HelloServiceEng eng = new HelloServiceEng();
		msg = eng.hello("안병욱");
		
		System.out.println(msg);
		
	}
}
