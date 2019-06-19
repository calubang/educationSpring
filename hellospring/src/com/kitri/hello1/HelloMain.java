package com.kitri.hello1;

public class HelloMain {
	public static void main(String[] args) {
		HelloServiceKor kor = new HelloServiceKor();
		String msg = kor.helloKor("안병욱");
		
		System.out.println(msg);
		
		HelloServiceEng eng = new HelloServiceEng();
	}
}
