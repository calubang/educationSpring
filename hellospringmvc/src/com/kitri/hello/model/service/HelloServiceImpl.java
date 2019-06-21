package com.kitri.hello.model.service;

import com.kitri.hello.model.dao.HelloDao;

public class HelloServiceImpl implements HelloService{
	
	private HelloDao dao;
	
	public HelloServiceImpl() {
		super();
	}
	
	public void setDao(HelloDao dao) {
		this.dao = dao;
	}

	public HelloServiceImpl(HelloDao helloDao) {
		System.out.println("HelloServiceKor 생성자 호출!!!");
		this.dao = helloDao;
	}
	
	public String hello(String name) {
		return name + "님 안녕하세요~!!\n" + dao.getGreeting() ;
	}

	/*
	 * public void setHelloDao(HelloDao helloDao) { this.helloDao = helloDao; }
	 */
	
}
