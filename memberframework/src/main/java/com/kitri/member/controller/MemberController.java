package com.kitri.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberService;

@Controller
@RequestMapping("/user")
@SessionAttributes(names = {"userInfo"})
public class MemberController {
	@Autowired
//	@Qualifier("impl1")
	private MemberService servie;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	public void setServie(MemberService servie) {
		this.servie = servie;
	}

	@RequestMapping(value = "/register.kitri", method = RequestMethod.GET)
	public String register() {
		return "user/member/member";
	}
	
	@RequestMapping(value = "/register.kitri", method = RequestMethod.POST)
	public String register(MemberDetailDto dto, Model model) {
		int cnt = servie.registerMember(dto);
		
		if(cnt != 0) {
			model.addAttribute("registerInfo", dto);
			return "user/member/registerOK";
		}
		return "user/member/registerFail";
	}
	
	@RequestMapping(value="/login.kitri", method= RequestMethod.GET)
	public String login() {
		return "user/login/login";
	}
	
	/*
	 * @RequestMapping(value="/login.kitri", method= RequestMethod.POST) public
	 * String login(@RequestParam("id") String id, @RequestParam("pass") String
	 * pass, HttpSession session) { MemberDto memberDto = servie.loginMember(id,
	 * pass);
	 * 
	 * if(memberDto != null) { session.setAttribute("userInfo", memberDto); return
	 * "user/login/loginOK"; } return "user/login/loginFail"; }
	 */
	
	//@RequestParam 이 있으면 param을 받는 map으로 사용한다.
	//어노테이션없이 사용된 map 은 model 을 대체하는 map으로 인식해서 param 내용을 담아주지 않는다.
	@RequestMapping(value="/login.kitri", method= RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model) {
		MemberDto memberDto = servie.loginMember(map);
		
		if(memberDto != null) {
			model.addAttribute("userInfo", memberDto);
			return "user/login/loginOK";
		}
		return "user/login/loginFail";
	}
	
	@RequestMapping("/idcheck.kitri")
	public @ResponseBody String idCheck(@RequestParam(name = "checkid", defaultValue = "") String checkid) {
		logger.info("checkid : " + checkid);
		String jsonData = servie.idCheck(checkid);
		return jsonData;
	}
	
	@RequestMapping("/zipsearch.kitri")
	@ResponseBody
	public String zipSearch(@RequestParam(name = "doro", defaultValue = "") String doro) {
		logger.info("doro : " + doro);
		String jsonData = servie.zipSearch(doro);
		return jsonData;
	}
	
	@RequestMapping("/logout")
	public String logout(@ModelAttribute("userInfo") MemberDto dto ,SessionStatus status) {
		status.setComplete();
		return "redirect:/index.jsp";
	}
}
