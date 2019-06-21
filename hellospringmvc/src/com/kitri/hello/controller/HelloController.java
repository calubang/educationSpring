package com.kitri.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.hello.model.service.HelloService;

@Controller
public class HelloController {
	private HelloService service;
	
	public void setService(HelloService service) {
		this.service = service;
	}

	@RequestMapping("/hello.kitri")
	public ModelAndView greeting() {
		ModelAndView mav = new ModelAndView();
		String msg = service.hello("안병욱");
		mav.addObject("msg", msg);
		mav.setViewName("result");
		return mav;
	} 
}
