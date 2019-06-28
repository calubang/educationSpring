<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/template/top.jsp" %>
<style>
.menulist{
	width: 300px;
	border: 1px solid black;
	font-size: 20px;
}

.category{
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	font-weight: bold;
	color: white;
	background-color: steelblue;
}

.menu{
	text-align: left;
	display: none;
}

.menu a{
	display: block;
	padding: 10px;
	font-size: 15px;
	text-decoration: none;
}
.menu a:hover{
	background-color: steelblue;
	color : white;
}
</style>
<script type="text/javascript">
$(function() {
	$("#boardmenu p.category").click(categoryClick);
});

function categoryClick() {
	//console.log("클릭");
	if($(this).next("div.menu").css("display") != "none"){
		$(this).next("div.menu").slideUp("slow");
	}else{
		$(this).next("div.menu").slideDown("slow").siblings("div.menu").slideUp("slow");	
	}	
}
</script>
<c:set var="boardmenulist" value="${requestScope.boardmenulist}"></c:set>
<div class="menulist" id="boardmenu">
	<c:forEach var="boardmenu" items="${boardmenulist}" varStatus="status">
		<c:set var="ccode" value="${boardmenu.ccode}"></c:set>
		<c:if test="${oldCcode != ccode}">
		<p class="category">${boardmenu.cname}</p>
		<div class="menu">
		</c:if>
			<a href="${root}/${boardmenu.control}/list?bcode=${boardmenu.bcode}&pg=1&key=&word=">${boardmenu.bname}</a>
		<c:if test="${!status.last && ccode != boardmenulist.get(status.index+1).ccode}">
		</div>
		</c:if>
		<c:set var="oldCcode" value="${ccode}"></c:set>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/views/commons/template/bottom.jsp" %>
