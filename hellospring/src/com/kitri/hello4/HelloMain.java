package com.kitri.hello4;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class HelloMain {
	public static void main(String[] args) {

		System.out.println("hello4 패키지 프로그램 시작!!!");
		ApplicationContext context 
			= new AnnotationConfigApplicationContext(HelloServiceFactory.class);
		System.out.println("설정파일 읽었다!!!");
		
		HelloService service = context.getBean("helloservice", HelloService.class);
		System.out.println("service 얻었다!!!");
		
		System.out.println(service.hello("안병욱"));
		
	}
}
