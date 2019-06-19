package com.kitri.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.dao.AdminDaoImpl;
import com.kitri.admin.model.service.AdminServiceImpl;
import com.kitri.member.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet({ "/AdminController", "/admin" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		
		System.out.println("admin[" + act + "]");
		if("memberlist".equals(act)) {
			path = "/admin/member/memberlist.jsp";
			MoveUrl.redirect(request, response, path);
		} else if("getmemberlist".equals(act)) {
			//System.out.println(act);
			String key = request.getParameter("key");
			String word = request.getParameter("word");
			
			String result = AdminServiceImpl.getAdminService().getMemberList(key, word);
			sendXML(response, result);
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
