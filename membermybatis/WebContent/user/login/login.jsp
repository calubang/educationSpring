<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.http.Cookie"%>
<%@ include file="/template/header.jsp" %>
<%
String saveId = "";
String ckId = "";
Cookie cookies[] = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if("kid_inf".equals(cookie.getName())){
			saveId = cookie.getValue();
			ckId =  "checked";
			break;
		}
	}
}
%>
<script type="text/javascript">
$(document).ready(function() {

});

window.onload = function(){
	document.getElementById("loginBtn").addEventListener("click", function(e) {
		if(document.getElementById("id").value == ""){
			alert("id 를 입력하세요.");
			return;
		} else if(document.getElementById("pass").value == ""){
			alert("비밀번호를 입력하세요.");
			return;
		} else {
			document.getElementById("loginform").action = "${root}/user";
			document.getElementById("loginform").submit();
		}
	}, false);
	
	document.getElementById("moveRegisterBtn").addEventListener("click", function(e) {
		document.location.href="${root}/user/member/member.jsp";
	}, false);
}
</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>로그인</h2>
		<form id="loginform" method="post" action="">
			<input type="hidden" name="act" value="loginProcess">
			<div class="form-group" align="right">
				<label for="">
				<input type="checkbox" class="form-control" name="idsave" value="idsave" placeholder="" <%=ckId%>>아이디</label>
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="" value="<%=saveId%>">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-warning" id="loginBtn">로그인</button>
				<button type="button" class="btn btn-primary" id="moveRegisterBtn">회원가입</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/template/footer.jsp" %>