package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet({ "/MemberFrontController", "/user" })
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		String result = "";
		//
		if(act == null) {
			//에러코드 삽입
			return;
		}
		
		System.out.println(act);
		//분기
		switch (act) {
		case "join":
			MoveUrl.redirect(request, response, "/user/member/member.jsp");
			break;
		case "login":
			MoveUrl.redirect(request, response, "/user/login/login.jsp");
			break;
		case "idcheck":
			String sid = request.getParameter("sid");
			result = MemberServiceImpl.getMemberService().idCheck(sid);
			sendXML(response, result);
			break;
		case "zipsearch":
			String doro = request.getParameter("doro");
			result = MemberServiceImpl.getMemberService().zipSearch(doro);
			sendXML(response, result);
			break;
		case "register":
			path = MemberController.getMemberController().register(request, response);
			MoveUrl.forword(request, response, path);
			break;
		case "loginProcess":
			path = MemberController.getMemberController().login(request, response);
			MoveUrl.forword(request, response, path);
			break;
		case "logout":
			path = MemberController.getMemberController().logout(request, response);
			MoveUrl.redirect(request, response, path);
			break;
		case "mvmodify":
			MoveUrl.redirect(request, response, "/user/member/passCheck.jsp");
			break;
		case "passCheck":
			path = MemberController.getMemberController().passCheck(request, response);
			MoveUrl.forword(request, response, path);
			break;
		case "modify":
			path = MemberController.getMemberController().memberModify(request, response);
			MoveUrl.redirect(request, response, path);
			break;
		case "zipsearchWeb":
			String searchAddress = request.getParameter("doro");
			int currenPage = Integer.parseInt(request.getParameter("currentPage"));
			StringBuffer resultXML = MemberServiceImpl.getMemberService().zipSearchWeb(searchAddress, currenPage);
			sendXML(response, resultXML.toString());
			break;
		case "deletemember":
			path = MemberController.getMemberController().memberDelete(request, response);
			break;
		default:
			break;
		}
	}

	private void sendXML(HttpServletResponse response, String xml) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println(xml);
		out.print(xml);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}
}
