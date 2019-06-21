<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<style>
.success{
	color: green;
	font-weight: bold;
}

.fail{
	color: red;
	font-weight: bold;
}
</style>
<script type="text/javascript">
var idcheck = false;
$(document).ready(function() {
	 
	//document.getElementById("registerBtn").addEventListener("click", register, false);
	//document.getElementById("id").addEventListener("keyup", idcheck, false);
	$("#registerBtn").click(registerBtnClick);
	$("#id").keyup(idKeyUp);
});
function registerBtnClick() {
	console.log("registerBtnClick 클릭");
	var id = $("#id").val().trim();
	var pass = $("#pass").val();
	var name = $("#name").val();
	var passcheck = $("#passcheck").val();
	
	//유효성 검사
	if(id == null || id.length == 0){
		alert("아이디 확인하세요.");
		return;
	}
	if(pass == null || pass.length == 0){
		alert("패스워드 확인하세요.");
		return;
	}
	if(passcheck == null || passcheck != pass){
		alert("패스워드 중복 확인하세요.");
		return;
	}
	if(idcheck == null || !idcheck){
		alert("아이디 중복 확인하세요.");
		return;
	}
	
	$("#memberform").attr("action", "${root}/user/register.kitri").submit();
	
	return false;
	
}

function idKeyUp() {
	//console.log("11");
	var inputId = $(this).val();
	var idresult = $("#idresult");
	if(inputId.length < 5 || id.length > 16){
		idcheck = false;
		idresult.attr("class", "fail");
		idresult.text("아이디는 5자이상 16자이하입니다.");
		//idresult.css("color", "red"); 
	}else{
		$.ajax({
			url : "${root}/user/idcheck.kitri"
			, type : "GET"
			, dataType : "json"
			, data : {
				"checkid" : inputId
			}
			, success : function(data) {
				//console.log(data);
				var idcount = parseInt(data.idcount);
				if(idcount == 0){
					idresult.attr("class", "success");
					idresult.text(inputId + " 는 사용가능합니다.");
					//idresult.css("color", "green");
					idcheck = true;
				}else{
					idresult.attr("class", "fail");
					idresult.text("사용중인 아이디 입니다.");
					//idresult.css("color", "red");
					idcheck = false;
				}
			}
		});
		return false;
	}
}

</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>회원가입</h2>
		<form id="memberform" method="post" action="">
			<input type="hidden" name="act" value="register">
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름입력">
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="4자이상 16자 이하">
				<div id="idresult" class="fail"></div>
			</div>
			
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호재입력</label>
				<input type="password" class="form-control" id="passcheck" name="passcheck" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="email">이메일</label><br>
				<div id="email" class="custom-control-inline">
				<input type="text" class="form-control" id="emailid" name="emailid" placeholder="" size="25"> @
				<select class="form-control" id="emaildomain" name="emaildomain">
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
					<option value="daum.net">daum.net</option>
					<option value="nate.com">nate.com</option>
					<option value="hanmail.net">hanmail.net</option>
				</select>
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="tel">전화번호</label>
				<div id="tel" class="custom-control-inline">
				<select class="form-control" id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
					<option value="041">041</option>
					<option value="051">051</option>
					<option value="061">061</option>
				</select> _
				<input type="text" class="form-control" id="tel2" name="tel2" placeholder="1234"> _
				<input type="text" class="form-control" id="tel3" name="tel3" placeholder="5678">
				</div>
			</div>
			<div class="form-group" align="left">
				<label for="">주소</label><br>
				<div id="addressdiv" class="custom-control-inline">
					<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" size="7" maxlength="5" readonly="readonly">
					<!--<button type="button" class="btn btn-primary" onclick="javascript:">우편번호</button>-->
				</div>
				<input type="text" class="form-control" id="address" name="address" placeholder="">
				<input type="text" class="form-control" id="address_detail" name="address_detail" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="registerBtn" >회원가입</button>
				<button type="reset" class="btn btn-warning">초기화</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/views/template/zipsearchWeb.jsp" %>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>
