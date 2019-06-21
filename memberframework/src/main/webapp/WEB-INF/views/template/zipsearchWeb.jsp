<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	 
	$('#zipcode').focusin(function() {
		$('#zipModal').modal();
	});
	
	//document.getElementById("searchBtn").addEventListener("click", zipSearch, "false");	
	//zipListView = document.getElementById("zip_codeList");
	
	$("#searchBtn").click(searchBtnClick);
	$("#doro").keyup(function(e) {
		var doro = $(this).val();
		if(e.keyCode == 13 && doro.length > 0){
			$("#searchBtn").trigger("click");
		}
	});
	
});

function searchBtnClick() {
	//console.log("searchBtn 눌림");
	var doro = $("#doro").val();
	if(doro == null || doro.length == 0){
		return;
	}
	$.ajax({
		url : "${root}/user/zipsearch.kitri"
		, type : "GET"
		, dataType : "json"
		, data : {
			"doro" : doro
		}
		, beforeSend : function() {
			var btn = $("#searchBtn");
			//btn.css("background-image", "url('/member/resources/img/loading.gif')");
		}
		, success : function(data) {
			//$(this).css("background-image", "none")
			console.log(data);
			var ziplist = data.ziplist;
			var body = $("#zip_codeList");
			var view = "";
			for(var i = 0 ; i<ziplist.length ; i++){
				var zipcode = ziplist[i].zipcode;
				var address = 
						ziplist[i].sido +  " " 
						+ ziplist[i].gugun + " " 
						+ ziplist[i].upmyon + " " 
						+ ziplist[i].doro + " "
						+ ziplist[i].building_number + " "
						+ ziplist[i].sigugun_building_name;
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
			body.empty().append(view);
		}
	});
}

function selectZip(zipcode, address) {
	$("#zipcode").val(zipcode);
	$("#address").val(address);
	
	$("#doro").val("");
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