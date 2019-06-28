<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.cafe.member.model.*"%>
<%
response.sendRedirect(request.getContextPath() + "/boardadmin/boardmenu");

MemberDto user = new MemberDto();
user.setId("calubang");
user.setName("안병욱");
user.setEmail("calubang@gmail.com");

session.setAttribute("userInfo", user);
%>