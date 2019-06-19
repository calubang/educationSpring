package com.kitri.hello4;

public class HelloServiceEng implements HelloService{
	
	public HelloServiceEng() {
		System.out.println("HelloServiceEng 생성자 호출!!!");
	}
	
	public String hello(String name) {
		return name + ", Hello~!!";
	}
}
