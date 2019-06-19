<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	var doro = document.getElementById("doro").value;
	if(doro.length == 0){
		alert("검색할 도로명을 입력하세요.");
		return;
	} else{
		var params = "act=zipsearch&doro="+doro;
		sendRequest("${root}/user", params, zipsearchResult, "GET");
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
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px">
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
                <div style="width:100%; height:200px; overflow:auto">
                	<table class = "table text-center">
                		<thead>
                		<tr>
                			<th style="width:150px;">우편번호</th>
                			<th style="width:650px;">주소</th>
                		</tr>
                		</thead>
                		<tbody id="zip_codeList"></tbody>
                	</table>
                </div>
            </div>
        </div>
    </div>
</div>