package com.kitri.hello3;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HelloMain {
	public static void main(String[] args) {
		//HelloServiceKor kor = new HelloServiceKor();
		//String msg = kor.hello("안병욱");
		//System.out.println(msg);
		//HelloServiceEng eng = new HelloServiceEng();
		//msg = eng.hello("안병욱");
		//System.out.println(msg);
		
		//spring에서 객체를 얻어내는 방법
		//2.x에서나 쓰던 방식
//		Resource resource = new ClassPathResource("com/kitri/hello3/applicationContext.xml");
//		BeanFactory factory = new XmlBeanFactory(resource);
//		HelloService helloService = (HelloService) factory.getBean("hsKor");
//		String msg = helloService.hello("안병욱");
//		
//		System.out.println(msg);
		System.out.println("hello3 패키지 프로그램 시작!!!");
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("com/kitri/hello3/applicationContext.xml");
		System.out.println("설정파일 읽었다!!!");
		
		HelloService service = context.getBean("hsKor", HelloService.class);
		System.out.println("service 얻었다!!!");
		
		HelloService service2= context.getBean("bb", HelloService.class);
		System.out.println("service 얻었다!!!");
		
		System.out.println(service2.hello("안병욱"));
		
	}
}
