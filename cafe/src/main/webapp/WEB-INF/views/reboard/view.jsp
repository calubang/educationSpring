<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/template/top.jsp" %>
<%@ include file="/WEB-INF/views/commons/logincheck.jsp" %>
<%@ include file="/WEB-INF/views/commons/board_common.jsp" %>
<script>
$(function() {
	$(".moveWriteBtn").click(moveWriteBtnClick);
	$(".moveReplyBtn").click(function() {
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		$("#seq").val("${article.seq}");
		
		$("#boardCommonForm").attr("method", "get").attr("action", "${root}/reboard/reply").submit();
	});
	
	$(".firstListBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		$("#seq").val("");
		
		$("#boardCommonForm").attr("method", "get").attr("action", "${root}/reboard/list").submit();
	});
	
	$(".listBtn").click(function() {
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		
		$("#boardCommonForm").attr("method", "get").attr("action", "${root}/reboard/list").submit();
	});
	
	//글 수정하는 부분
	$(".moveModifyBtn").click(function() {
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		$("#seq").val("${article.seq}");

		
		$("#boardCommonForm").attr("method", "get").attr("action", "${root}/reboard/modify").submit();
		
	});
	
	$(".moveDeleteBtn").click(function() {
		console.log("클릭됨");
		var seq = ${article.seq};
		$.ajax({
			url : "${root}/reboard/delete"
			, method : "get" 
			, data : {
				"seq" : seq
			} 
			, success : function(result) {
				console.log(result);
				if(result.trim() == 'possible'){
					var answer = confirm("정말 삭제하시겠습니까?");
					if(answer){
						deleteArticle(seq);
					}
				}else{
					alert("댓글이 있어서 삭제할 수 없습니다.");
				}
			}
		});
	});
	
	//댓글 부분
	$(".memoBtn").click(function() {
		var seq = ${article.seq};
		var mcontent = $("#mcontent").val();
		var param = JSON.stringify({'seq' : seq, 'mcontent' : mcontent});
		if(mcontent.trim().length != 0){
			$.ajax({
				url : "${root}/memo"
				, method : "post"
				, contentType : "application/json; charset=utf-8"
				, dataType : "json"
				, data : param
				, success : function(result) {
					console.log("잘 넘어옴 : " +result);
					makeMemoList(result.memolist);
					$("#mcontent").val("");
				}
			});
		}
	});
	
	getMemoList();
});

function getMemoList(){
	$.ajax({
		url : "${root}/memo"
		, method : "get"
		, contentType : "application/json; charset=utf-8"
		, dataType : "json"
		, data : {
			"seq" : "${article.seq}"
		}
		, success : function(result) {
			console.log("잘 넘어옴 : " +result);
			makeMemoList(result.memolist);
			$("#mcontent").val("");
		}
	});
}

function makeMemoList(memolist) {
	var memoCount = memolist.length;
	var memoStr = "";
	for(var i=0 ; i<memoCount ; i++){
		var memo = memolist[i];
		memoStr += '<tr>';
		memoStr += '	<td width="50px">' + memo.name + '</td>';
		memoStr += '	<td style="padding: 10px; width:300px">';
		memoStr += memo.mcontent;
		memoStr += '	</td>';
		memoStr += '	<td width="100px" style="padding: 10px">';
		memoStr += memo.mtime;
		memoStr += '	</td>';
		
		if('${userInfo.id}' == memo.id){
			memoStr += '	<td width="100px" style="padding: 10px" data-mseq="' + memo.mseq + '">';
			memoStr += '		<input type="button" class="mmodify" value="수정" />';
			memoStr += '		<input type="button" class="mdelete" value="삭제"/>';
			memoStr += '	</td></tr>';
			
			memoStr += '	<tr class="memoModifyTr" style="display:none;">';			
			memoStr += '		<td style="padding: 10px" colspan="3">';
			memoStr += '			<textarea class="mcontent" rows="5" style="width:90%; float:right"></textarea>';
			memoStr += '		</td>';
			memoStr += '		<td width="100" style="padding: 10px" data-mseq="' + memo.mseq + '">';
			memoStr += '			<input type="button" class="memomodifyBtn" value="수정"></button>';
			memoStr += '			<input type="button" class="memomodifyCancelBtn" value="취소"></button>';
			memoStr += '		</td>';
		}
		
		memoStr += '</tr>';
		memoStr += '<tr>';
		memoStr += '	<td class="bg_board_title_02" colspan="4" height="1"';
		memoStr += '		style="overflow: hidden; padding: 0px"></td>';
		memoStr += '</tr>';		
	}
	
	$("#mList").empty();
	$("#mList").append(memoStr);
	
	$(".mmodify").click(function() {
		//console.log($(this));
		$(this).parent().parent().next().css("display", "table-row");
	});
	
	$(".memomodifyBtn").click(function() {
		//var param = JSON.stringify({'seq' : '${article.seq}'});
		console.log($(this).parent().prev().children(".mcontent").val());
		var param = JSON.stringify({
				"mcontent" : $(this).parent().prev().children(".mcontent").val()
				, "mseq" : $(this).parent().attr("data-mseq")
				, "seq" : "${article.seq}"
			});
		$.ajax({
			url : "${root}/memo/" + $(this).parent().attr("data-mseq")
			, type : "PUT"
			, contentType : "application/json; charset=utf-8"
			, dataType : "json"
			, data : param
			, success : function(result) {
				makeMemoList(result.memolist);
			}
		});
	});
	
	$(".memomodifyCancelBtn").click(function() {
		var tr = $(this).parent().parent();
		tr.find(".mcontent").val("");
		//console.log(tr.children(".mcontent").val());
		tr.css("display", "none");	
	});
	
	$(".mdelete").click(function() {
		//console.log($(this).parent().attr("data-mseq"));
		var param = JSON.stringify({'seq' : '${article.seq}'});
		$.ajax({
			url : "${root}/memo/" + $(this).parent().attr("data-mseq")
			, type : "delete"
			, contentType : "application/json; charset=utf-8"
			, dataType : "json"
			, data : param
			, success : function(result) {
				makeMemoList(result.memolist);
			}
		});
	});
	return false;
}

function deleteArticle(seq){
	console.log(seq + " 삭제 시도");
	var param = JSON.stringify({"seq" : seq});
	$.ajax({
		url : "${root}/reboard/delete"
		, type : "delete"
		, contentType : "application/json; charset=utf-8"
		, dataType : "json"
		, data : param
		, success : function(result) {
			if(result == 1){
				alert("삭제되었습니다.");
				$(".firstListBtn").trigger("click");
				return false;
			}	
		}
	});
}


function listViewBtnClick(){
	$("#bcode").val("${bcode}");
	$("#pg").val("1");
	$("#key").val("");
	$("#word").val("");
	
	$("#boardCommonForm").attr("method", "get").attr("action", "${root}/reboard/list").submit();
}

function moveWriteBtnClick() {
	$("#bcode").val("${bcode}");
	$("#pg").val("1");
	$("#key").val("");
	$("#word").val("");
	$("#seq").val("");
	
	$("#boardCommonForm").attr("method", "get").attr("action", "${root}/reboard/write").submit();
}
</script>
<!-- title -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="${root}/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 글을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<form name="bbsForm" id="bbsbbs" method="post"><input
		type="hidden" name="" value="">
		<!-- 같이 가야하는 부분 -->
	<tr>
		<td valign="bottom" nowrap>
			<img src="${root}/img/board/btn_write_01.gif" width="64" height="22"
				border="0" align="absmiddle" alt="글쓰기" class="moveWriteBtn"> 
			<img src="${root}/img/board/btn_reply.gif" width="40" height="22"
				border="0" align="absmiddle" alt="답글" class="moveReplyBtn">
			
			<c:if test="${userInfo.id == article.id}">	
			<img src="${root}/img/board/btn_modify.gif" 
				border="0" align="absmiddle" alt="글수정" class="moveModifyBtn"> 
			<img src="${root}/img/board/btn_delete.gif" 
				border="0" align="absmiddle" alt="글삭제" class="moveDeleteBtn">
			</c:if>
		</td>
		<td valign="bottom" width="100%" style="padding-left: 4px"></td>
		<td align="right" nowrap valign="bottom">
			<label class="firstListBtn">최신목록</label> <font color="#c5c5c5">|</font>
			<label class="listBtn">목록</label> <font color="#c5c5c5">|</font>

		<a href="javascript:goBbsRead();"><img
			src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
	</form>
</table>

<table border="0" cellpadding="5" cellspacing="0" width="100%">
	<tr>
		<td class="bg_board_title_02" colspan="2" height="2"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="28">
		<td class="bg_board_title" colspan="2" style="padding-left: 14px">
		<b><font class="text">${article.subject}</font></b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="26">
		<td width="100%" style="padding-left: 14px"><font class="stext">번호
		:</font> <font class="text_commentnum">${article.seq}</font> &nbsp; <font
			class="stext">글쓴이 :</font> <a href="javascript:;"
			onClick="showSideView();" class="link_board_02">${article.name}</a><br>
		</td>
		<td style="padding-right: 14px" nowrap class="stext">조회 : <font
			class="text_commentnum">${article.hit}</font> &nbsp; 스크랩 : <font
			class="text_commentnum">0</font> &nbsp; 날짜 : <font
			class="text_commentnum">${article.logtime}</font></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="15" cellspacing="0" width="100%">
	<tr valign="top">
		<td bgcolor="#ffffff" width="100%" class="text"
			style="padding-bottom: 8px; line-height: 1.3" id="clix_content">



		<P>${article.content}</P>



		</td>
		<td nowrap valign="top" align="right" style="padding-left: 0px">

		</td>
	</tr>
</table>

<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<!-- 하단 페이징 -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
	<tr valign="top">
		<td nowrap>
			<img src="${root}/img/board/btn_write_01.gif" width="64" height="22"
				border="0" align="absmiddle" alt="글쓰기" class="moveWriteBtn"> 
			<img src="${root}/img/board/btn_reply.gif" width="40" height="22"
				border="0" align="absmiddle" alt="답글" class="moveReplyBtn">
		</td>
		<td style="padding-left: 4px" width="100%"><a href=""
			target="new"><img src="${root}/img/board/btn_print.gif"
			width="30" height="18" border="0" align="absmiddle" alt="인쇄"></a></td>

		<td align="right" nowrap>
			<label class="firstListBtn">최신목록</label> <font color="#c5c5c5">|</font>
			<label class="listBtn">목록</label> <font color="#c5c5c5">|</font>
			
			<a href="javascript:goBbsRead();"><img
			src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
</table>
<br>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="2" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr>
		<td style="padding: 10px">
		<textarea id="mcontent" cols="160" rows="5"></textarea>
		</td>
		<td width="100" style="padding: 10px">
		<input type="button" class="memoBtn">글작성</button>
		</td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="4" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="4" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tbody id = "mList">
		
	</tbody>
</table>
<br>
<%@ include file="/WEB-INF/views/commons/template/bottom.jsp" %>