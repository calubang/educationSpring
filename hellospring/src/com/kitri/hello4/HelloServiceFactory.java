package com.kitri.hello4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HelloServiceFactory {
	
	@Bean(name = {"hsKor", "helloservice"})
	public HelloService getHelloService() {
		return new HelloServiceKor();
	}
}
