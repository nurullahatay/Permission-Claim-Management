// izin hakediş için takvim(geçerli olacağı tarih inputu için)
function righttakvim(x){
	$(function() {
		$("#tarihright"+x).datepicker(
				{
					beforeShowDay : $.datepicker.noWeekends,
					dateFormat : "dd-mm-yy",
					altFormat : "yy-mm-dd",
					altField : "#tarih-dbright"+x,
					monthNames : [ "Ocak", "Şubat", "Mart",
							"Nisan", "Mayıs", "Haziran", "Temmuz",
							"Ağustos", "Eylül", "Ekim", "Kasım",
							"Aralık" ],
					dayNamesMin : [ "Pa", "Pt", "Sl", "Ça", "Pe",
							"Cu", "Ct" ],
					firstDay : 1,
				});
	});
}
$(document).ready(function(){
		$("#tarihright1").datepicker(
				{
					beforeShowDay : $.datepicker.noWeekends,
					dateFormat : "dd-mm-yy",
					altFormat : "yy-mm-dd",
					altField : "#tarih-dbright1",
					monthNames : [ "Ocak", "Şubat", "Mart",
							"Nisan", "Mayıs", "Haziran", "Temmuz",
							"Ağustos", "Eylül", "Ekim", "Kasım",
							"Aralık" ],
					dayNamesMin : [ "Pa", "Pt", "Sl", "Ça", "Pe",
							"Cu", "Ct" ],
					firstDay : 1,
				});

});

// izin hakediş için dinamik div
$(document).ready(function(){
	 var j = 1;
	    $("#ekle").on("click",function () {
	    	j++;
            $(".izinhakedis").append('<tr id="izinhakedistr'+j+'"><td><select id="selectpersonelRight'+j+'" name="personelarama'+j+'" class="form-control input-md"><option>Seçiniz</option></select></td><td><div class="datepicker"><input id="tarihright'+j+'" class="form-control" type="datepicker"><input type="hidden" id="tarih-dbright'+j+'" name="gecerliolacagitarih'+j+'"></div></td><td><input id="hakedilengunsayisi'+j+'" name="hakedilengunsayisi'+j+'" class="form-control input-md" type="text"></td></tr>');
            dropboxfiller(j);
            righttakvim(j);
		  	
	    });
	    $("#sil").click(function(){
	    	if(j==1)
	    		{
	    		alert("En az 1 personel olmalı")
	    		}
	    	if(j>1){
	    	$("#izinhakedistr"+j+"").remove(); 
	    	j--;
	    	}
	    });
	    $("#addRightOfPermissionBtn").on("click",function(){
	    	addRightOfPermission(j);
	    });
});

function dropboxfiller(k){
//	$(document).on("click","#personelaram"+k,function(){
		$.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
			$.each(result, function(i, personel){
				$("#selectpersonelRight"+k).append('<option value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
			});
		});
	//});
}
function getAllRight(){
    var TDEKLE='</th><th>';
    $.getJSON("/Permission-Claim-Management/rest/right/getAllRight", function(result){
        $.each(result, function(i, right){
            $("#getAllRightOfPermission").append('<tr><th>'+right.sicilNo+TDEKLE+right.validDate+TDEKLE+right.dayCountOfDeserved+TDEKLE+right.dayCountOfDeservedForYear+TDEKLE+'<button type="button" id="delete" class="btn btn-danger" onclick="deleteRight('+right.sicilNo+')">Sil</button>'+'</th></tr>');
        });
    });
}








function addRightOfPermission(z) {
	var a;
	for(a=1; a<=z; a++){
		alert(a);
	var rightOfPermission = {};
	rightOfPermission["sicilNo"] = $("#selectpersonelRight"+a).val();
	rightOfPermission["validDate"] =$("#tarih-dbright"+a).val();
	rightOfPermission["dayCountOfDeserved"] = $("#hakedilengunsayisi"+a).val();
	rightOfPermission["dayCountOfDeservedForYear"] = $("#hakedilengunsayisi"+a).val();
	
	console.log(rightOfPermission.sicilNo+rightOfPermission.validDate+rightOfPermission.dayCountOfDeserved+rightOfPermission.dayCountOfDeservedForYear);
	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/right/addRight',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(rightOfPermission),
		success : function() {
			alert("success");
		},
		error : function() {
			alert("error");

		}
	});
	};
}

function getRightOfPermission(sicilNo) {

	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/right/getRightDetails',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(sicilNo),
		success : function(result) {
			alert("success");
		},
		error : function() {
			alert("error");

		}
	});
}

function getAllRightOfPermission() {

	$.ajax({
		type : "GET",
		url : '/Permission-Claim-Management/rest/right/getAllRight',
		contentType : "application/json",
		mimeType : "application/json",
		success : function(result) {
			allRight = result;
			alert("success");
		},
		error : function() {
			alert("error");

		}
	});
}

function getRight(sicilNo) {
	$(allRight).each(
			function(index, item) {

				// each iteration
				if (item.sicilNo == sicilNo) {

					alert(item.sicilNo + '|' + item.ValidDate + '|'
							+ item.DayCountOfDeserved + '|'
							+ item.DayCountOfDeservedForYear);
					return item;
				}
				return null;

			});

}

function deleteAllRight() {
	$.ajax({
		type : "GET",
		url : '/Permission-Claim-Management/rest/right/deleteAllRight',
		contentType : "application/json",
		mimeType : "application/json",
		success : function() {
			alert("success");
		},
		error : function() {
			alert("error");

		}
	});
}

function deleteRight(sicilNo) {
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/right/deleteRight',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(sicilNo),
		success : function() {
			alert("success");
		},
		error : function() {
			alert("error");

		}
	});
}
var personel;




//personelleri açılır menüye getirme
$(document).ready(function(){
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonelRight1").append('<option id="personelselectRight" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
            });
        });
});




$(document).ready(function(){
	$.getJSON("/Permission-Claim-Management/rest/session/getAuthenticatedPersonel", function(personel){
		 $("#nickname").text(personel.ad+" "+personel.soyad);
		 $("#personel_name").text(personel.ad+' '+personel.soyad);
		 $("#personel_department").text(personel.departmentId);
		 $("#personel_sicilNo").text(personel.sicilno);
	   	 $.each(personel.personelRoles, function(key, value) {
	   		if (value =="HR"){
	   			getAllRight();
			}
	   		
	     });
    });
});
