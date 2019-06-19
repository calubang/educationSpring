<%@page import="com.kitri.member.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto, com.kitri.util.MoveUrl"%>
<%@ include file="/template/header.jsp" %>
<c:set var="userInfo" value="${sessionScope.userInfo}"></c:set>
<c:if test="${!empty userInfo}">
	<script type="text/javascript">
	function deleteMember() {
		if(confirm("탈퇴 하시겠습니까?")){
			document.location.href = "${root}/user?act=deletemember";
		}
	}
	</script>
	<font size='15' color='green'>${userInfo.name}님, 환영합니다.</font>
	<br><a href="${root}/user?act=logout">로그아웃</a>
	<br><a href="${root}/user?act=mvmodify">정보수정</a>
	<br><a href="#" onclick="javascript:deleteMember();">회원탈퇴</a>
	<c:if test="${userInfo.id == 'calubang'}">
		<br><a href="${root}/admin?act=memberlist">관리자</a>
	</c:if>
</c:if>
<%@ include file="/template/footer.jsp" %>