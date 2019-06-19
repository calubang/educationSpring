package com.kitri.hello4;

import com.kitri.hello3.HelloDao;

public class HelloServiceKor implements HelloService{
	
	public HelloServiceKor() {
		System.out.println("HelloServiceKor 생성자 호출!!!");
	}
	
	public String hello(String name) {
		return name + "님 안녕하세요~!!";
	}
	
}
