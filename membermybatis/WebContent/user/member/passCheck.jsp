<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto"%>
<%@ include file="/template/header.jsp"%>
<script type="text/javascript" src="${root}/js/httpRequest.js"></script>
<script type="text/javascript">
<%
Boolean passCheck = (Boolean)request.getAttribute("passCheck");
if(passCheck != null && !passCheck){
%>
alert("비밀번호를 다시 확인해주세요.");
<%
}
%>
$(document).ready(function() {
	$("#passCheckBtn").click(passCheck);
	
});

function passCheck() {
	if(document.getElementById("pass").value == ""){
		alert("비밀번호를 입력하세요");
		return;
	} else{
		document.getElementById("passform").action = "${root}/user";
		document.getElementById("passform").submit();
	}
}
</script>
<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>비밀번호 확인</h2>
		<form id="passform" method="post" action="">	
			<input type="hidden" name="act" value="passCheck">
			<div class="form-group" align="left">
				<label for="">비밀번호</label> <input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="passCheckBtn">확인</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="/user/member/zipsearch.jsp"%>
<%@ include file="/template/footer.jsp"%>
