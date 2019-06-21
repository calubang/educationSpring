<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDetailDto"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<c:set var="registerInfo" value="${requestScope.registerInfo}"></c:set>
<c:if test="${!empty registerInfo}">
${registerInfo.name}님 회원가입을 환영합니다.<br>
가입하신 정보는 아래와 같습니다.
아이디 : ${registerInfo.id}<br>
이메일 : ${registerInfo.emailid}@${registerInfo.emaildomain}<br>
전화번호 : ${registerInfo.tel1} - ${registerInfo.tel2} - ${registerInfo.tel3}<br>
주소 : ${registerInfo.zipcode} ${registerInfo.address} ${registerInfo.address_detail}<br>
로그인 후 모든 서비스를 이용할 수 있습니다.<br>
<a href='${root}/user/login.kitri'>로그인</a>		
</c:if>
<c:if test="${empty registerInfo}">
	<c:redirect url="/index.jsp"></c:redirect>
</c:if>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>