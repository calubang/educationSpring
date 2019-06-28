<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.userInfo}">
	<c:redirect url="${root}/index.jsp"/>
</c:if>