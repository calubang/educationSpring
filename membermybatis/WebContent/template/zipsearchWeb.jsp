<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String zipSearchWebRoot = request.getContextPath();
%>
<script type="text/javascript" src="<%=zipSearchWebRoot%>/js/httpRequest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});
	
	document.getElementById("searchBtn").addEventListener("click", zipSearch, "false");	
	zipListView = document.getElementById("zip_codeList");
	
});

var zipListView;

function zipSearchAjax(doro, currentPage) {
	$.ajax({
		url: "<%=zipSearchWebRoot%>/user"
		, type:"get"
		, dataType:"xml"
		, data : "act=zipsearchWeb&doro="+doro + "&currentPage=" + currentPage
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
					$("#address").val(tdlnmAdres.text());
					$('#zipModal').modal("hide");
				});
				tdlnmAdres.click(function() {
					$("#zipcode").val(tdzipNo.text());
					$("#address").val(tdlnmAdres.text());
					$('#zipModal').modal("hide");
				});
				tdrnAdres.click(function() {
					$("#zipcode").val(tdzipNo.text());
					$("#address").val(tdrnAdres.text());
					$('#zipModal').modal("hide");
				});
				
				tr.append(tdzipNo).append(tdlnmAdres);
				tr2.append(tdrnAdres);
				$("#zip_codeList").append(tr).append(tr2);
		 	}
		}
	});
}

function zipSearch() {
	
	var doro = document.getElementById("doro").value;
	if(doro.length == 0){
		alert("검색할 도로명을 입력하세요.");
		return;
	} else{
		$("#zip_codeList").empty();
		zipSearchAjax(doro, 1);
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
<div id="zipModal" class="modal fade" role="dialog">
	<h5 class="modal-title" id="myModalLabel">우편번호검색</h5>
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>    
            <div class="modal-body text-center">
            		<div align="center">
            			<label>도로명 주소검색</label>
            		</div>
					<div class="input-group" align="left">
						<input type="text" class="form-control" id="doro" name="doro" placeholder="검색 할 도로명 입력(예: 구로디지털로, 여수울로)">
						<span class="input-group-btn">
						<input type="button" class="btn btn-warning" value="검색" id="searchBtn">
						</span>
					</div>
                <div style="width:100%; height:500px; overflow:auto">
                	<table class = "table-bordered text-center">
                		<thead>
                		<tr>
                			<th style="width:150px;">우편번호</th>
                			<th style="width:600px;">주소</th>
                		</tr>
                		</thead>
                		<tbody id="zip_codeList">
                		<tr>
                			<td rowspan="2">우편번호</td>
                			<td>도로명</td>
                		</tr>
                		<tr>
                			<td>구주소</td>
                		</tr>
                		</tbody>
                	</table>
                </div>
            </div>
        </div>
    </div>
</div>