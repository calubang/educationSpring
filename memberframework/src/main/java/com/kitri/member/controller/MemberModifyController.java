package com.kitri.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberModifyService;

@Controller
@RequestMapping("/usermodify")
@SessionAttributes("userInfo")
public class MemberModifyController {
	
	@Autowired
	private MemberModifyService service;
	
	@RequestMapping(value = "/modify.kitri", method = RequestMethod.GET)
	public String memberModify(@ModelAttribute("userInfo") MemberDto user, Model model) {
		//유저 디테일 정보 뽑아내기
		MemberDetailDto memberDetailDto = service.getMember(user.getId());
		//System.out.println(memberDetailDto);
		model.addAttribute("userDetailInfo", memberDetailDto);
		return "user/member/modify";
	}
	
	@RequestMapping(value = "/modify.kitri", method = RequestMethod.POST)
	public String memberModify(@ModelAttribute("userInfo") MemberDto user
					, MemberDetailDto dto) {
		System.out.println("post : " + dto);
		service.modifyMember(dto);
		return "user/login/loginOK";
	}
	
	@RequestMapping(value = "/delete.kitri", method = RequestMethod.PUT)
	public @ResponseBody String memberDelete(@ModelAttribute("userInfo") MemberDto user, SessionStatus status) {
		//System.out.println("지워짐");
		int result = service.deleteMember(user.getId());
		if(result == 1) {
			status.setComplete();
			return "회원 탈퇴 되었습니다.";
		}
		return "회원 탈퇴 실패하였습니다.";
		
	}
}
