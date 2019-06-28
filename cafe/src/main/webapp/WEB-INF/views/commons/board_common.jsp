<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="bcode" value="${requestScope.parameter.bcode}"/>
<c:set var="pg" value="${requestScope.parameter.pg}"/>
<c:set var="key" value="${requestScope.parameter.key}"/>
<c:set var="word" value="${requestScope.parameter.word}"/>
<c:set var="seq" value="${requestScope.seq}"/>
<form id="boardCommonForm">
	<input type="hidden" name="bcode" value="" id="bcode">
	<input type="hidden" name="pg" value="" id="pg">
	<input type="hidden" name="key" value="" id="key">
	<input type="hidden" name="word" value="" id="word">
	<input type="hidden" name="seq" value="" id="seq">
</form>