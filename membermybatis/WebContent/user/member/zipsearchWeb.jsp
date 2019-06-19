<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>
<script type="text/javascript" src="${root}/js/httpRequest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});
	
	document.getElementById("searchBtn").addEventListener("click", zipSearch, "false");	
	zipListView = document.getElementById("zip_codeList");
	
});

var zipListView;

function zipSearch() {
	$("#zip_codeList").empty();
	var doro = document.getElementById("doro").value;
	if(doro.length == 0){
		alert("검색할 도로명을 입력하세요.");
		return;
	} else{
		$.ajax({
			url: "${root}/user"
			, type:"get"
			, dataType:"xml"
			, data : "act=zipsearchWeb&doro="+doro + "&currentPage=" + 1
			, timeout : 30000
			, cache:false
			, success: function(xml){	
				//성공
				var totalCount = $(xml).find("totalCount");
			 	var countPerPage = $(xml).find("countPerPage");
			 	var totalPage = $(xml).find("totalPage");
			 	var currentPage = $(xml).find("currentPage");
			 	var addressList = $(xml).find("newAddressListAreaCdSearchAll");
			 	
			 	var length = addressList.length;
			 	for(var i=0 ; i < length ; i++){
			 		var zipNo = $(addressList[i]).find("zipNo") .text();
			 		var lnmAdres = $(addressList[i]).find("lnmAdres") .text();
			 		var rnAdres = $(addressList[i]).find("rnAdres") .text();
			 		
			 		var tr = $("<tr>");
					var tdzipNo = $("<td>").html(zipNo).attr("rowspan", "2");
					var tdlnmAdres = $("<td>").html(lnmAdres);
					var tdrnAdres = $("<td>").html(rnAdres);
					var tr2 = $("<tr>");
					
					tdzipNo.click(function() {
						$("#zipcode").val(tdzipNo.text());
					});
					
					tr.append(tdzipNo).append(tdlnmAdres);
					tr2.append(tdrnAdres);
					$("#zip_codeList").append(tr).append(tr2);
			 	}
			}
		});
	}
}

function zipsearchResult() {
	if(httpRequest.readyState == 4){
		if(httpRequest.status = 200){
			var result = httpRequest.responseXML;
			if(result.getElementsByTagName("ziplist")[0].firstChild == null || result.getElementsByTagName("ziplist")[0].firstChild == ""){
				zipListView.innerHTML = "데이터가 없습니다.";
				return;
			}
			var ziplist = result.getElementsByTagName("zip");
			var length = ziplist.length;
			var view = "";
			
			for(var i =0 ; i<length ; i++){
				var zipcode = ziplist[i].getElementsByTagName("zipcode")[0].firstChild.data;
				var address = ziplist[i].getElementsByTagName("address")[0].firstChild.data;
				view += "<tr>"+"\n";
				view += "	<td align='left'>" + zipcode +"\n";
				view += "	</td>"+"\n";
				view += "	<td align='left'>";
				view += "	<a href='javascript:selectZip(" + "\""+ zipcode +"\", \"" + address + "\");'" + ">";
				view += address +"\n";
				view += "	</a>";
				view += "	</td>"+"\n";
				view += "</tr>"+"\n";
			}
			zipListView.innerHTML = view;
		}
	} else{
		//로딩중...
		zipListView.innerHTML = "<img src='${root}/img/loading.gif' width='80' height='80'>";
	}
}

function selectZip(zipcode, address) {
	document.getElementById("zipcode").value = zipcode;
	document.getElementById("address").value = address;
	
	$('#zipModal').modal("hide");
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	 
	document.getElementById("registerBtn").addEventListener("click", register, false);
	document.getElementById("id").addEventListener("keyup", idcheck, false);
	
});
var idcount = 1;
var resultView;

function register() {
	
	if(document.getElementById("name").value == ""){
		alert("이름 입력!!!!");
		return;
	} else if(idcount != 0){
		alert("아이디 중복검사를 하세요.");
		return;
	} else if(document.getElementById("pass").value == ""){
		alert("비밀번호 입력!!!");
		return;
	} else if(document.getElementById("pass").value != document.getElementById("passcheck").value){
		alert("비밀번호를 체크해라!!!!");
		return;
	} else{
		document.getElementById("memberform").action = "${root}/user";
		document.getElementById("memberform").submit();
	}
} 

function idcheck(){
	resultView = document.getElementById("idresult");
	var searchId = document.getElementById("id").value;
	if(searchId.length < 5 || searchId.length > 16){
		resultView.innerHTML = "<font color='gray'>아이디는 5글자 이상 16자이하입니다.</font>";
	} else{
		var params = "act=idcheck&sid="+searchId;
		sendRequest("${root}/user", params, idcheckResult, "GET");
	}
}

function idcheckResult() {
	if(httpRequest.readyState == 4){
		if(httpRequest.status = 200){
			var result = httpRequest.responseXML;
			//alert(result);
			//tagname의 집합 -> 첫번째 자식엘리먼트 -> 그것의 data 를 해야 정확히 가져온다
			idcount = parseInt(result.getElementsByTagName("cnt")[0].firstChild.data);
			if(idcount == 0){
				resultView.innerHTML = "<font color='green'>사용 가능합니다.</font>";
			}else{
				resultView.innerHTML = "<font color='red'>사용중입니다. 다른 아이디를 사용하세요.</font>";
			}
		}
	} else{
		//로딩중...
		resultView.innerHTML = "<img src='${root}/img/loading.gif' width='80' height='80'>";
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
				<div id="idresult"></div>
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
<%@ include file="/template/zipsearchWeb.jsp" %>
<%@ include file="/template/footer.jsp" %>