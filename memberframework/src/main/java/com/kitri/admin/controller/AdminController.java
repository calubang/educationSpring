package com.kitri.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kitri.admin.model.dao.AdminDaoImpl;
import com.kitri.admin.model.service.AdminService;
import com.kitri.admin.model.service.AdminServiceImpl;
import com.kitri.member.controller.MemberController;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = {"userInfo"})
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/mvmemberlist.kitri", method = RequestMethod.GET )
	public String memberList(@ModelAttribute("userInfo") MemberDto dto) {
		return "admin/member/memberlist";
	}
	
	@RequestMapping(value = "/memberlist.kitri")
	public @ResponseBody String memberList(@ModelAttribute("userInfo") MemberDto dto, @RequestParam Map<String, String> map) {
		logger.info("map : " + map.toString());
		String result = service.getMemberList(map);
		return result;
	}

}
