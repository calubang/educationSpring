<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="text-align: center">
<h3>MVC를 이용한 방명록</h3>
<a href="${root}/user?act=join">회원가입</a><br>
<a href="${root}/user?act=login">로그인</a><br>
</div>
</body>
</html>
