<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDetailDto"%>
<%@ include file="/template/header.jsp" %>
<c:set var="userInfo" value="${requestScope.userInfo}"></c:set>
<c:if test="${!empty userInfo}">
${userInfo.name}님 회원가입을 환영합니다.<br>
가입하신 정보는 아래와 같습니다.
아이디 : ${userInfo.id}<br>
이메일 : ${userInfo.emailid}@${userInfo.emaildomain}<br>
전화번호 : ${userInfo.tel1} - ${userInfo.tel2} - ${userInfo.tel3}<br>
주소 : ${userInfo.zipcode} ${userInfo.address} ${userInfo.addressDetail}<br>
로그인 후 모든 서비스를 이용할 수 있습니다.<br>
<a href='${root}/user?act=login'>로그인</a>		
</c:if>
<c:if test="${empty userInfo}">
	<c:redirect url="/index.jsp"></c:redirect>
</c:if>
<%@ include file="/template/footer.jsp" %>