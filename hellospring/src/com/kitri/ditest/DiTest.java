package com.kitri.ditest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/kitri/ditest/applicationContext.xml");
		MemberDto memberDto = context.getBean("member", MemberDto.class);
		System.out.println(memberDto);
		
		MemberDto memberDto2 = context.getBean("member2", MemberDto.class);
		System.out.println(memberDto2);
		
		MemberDto memberDto3 = context.getBean("member3", MemberDto.class);
		System.out.println(memberDto3);
		
		MemberDto memberDto4 = context.getBean("member4", MemberDto.class);
		System.out.println(memberDto4);
		
		MemberDto memberDto5 = context.getBean("member5", MemberDto.class);
		System.out.println(memberDto5);
		
		MemberDto memberDto6 = context.getBean("member6", MemberDto.class);
		System.out.println(memberDto6);
		
		MemberDto memberDto7 = context.getBean("member7", MemberDto.class);
		System.out.println(memberDto7);
		
	}
}
