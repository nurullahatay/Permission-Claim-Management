
$(document).ready(function(){
	personelInfo();
    var TDEKLE='</th><td>';
    $.getJSON("/Permission-Claim-Management/rest/right/getAllRight", function(result){
        $.each(result, function(i, right){
            $("#getAllRightOfPermission").append('<tr><th>'+right.sicilNo+TDEKLE+right.validDate+TDEKLE+right.dayCountOfDeserved+TDEKLE+right.dayCountOfDeservedForYear+TDEKLE+'<button type="button" id="delete" class="btn btn-danger" onclick="deleteRight('+right.sicilNo+')">Sil</button>'+'</th></tr>');
        });
    });
});








function addRightOfPermission() {

	var rightOfPermission = {};

	rightOfPermission["sicilNo"] = $('#selectpersonelRight').val();
	rightOfPermission["validDate"] =$("#gtarih-db").val();
	rightOfPermission["dayCountOfDeserved"] = $('#hakedilengunsayisi').val();
	rightOfPermission["dayCountOfDeservedForYear"] = $('#hakedilengunsayisi').val();
	
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



function personelInfo() {
	$.getJSON("/Permission-Claim-Management/rest/session/getAuthenticatedPersonel", function(personel){
		 $("#nickname").text(personel.ad+" "+personel.soyad);
	$("#personel_name").text(personel.ad+' '+personel.soyad);
	  $("#personel_department").text(personel.departmentId);
	  $("#personel_sicilNo").text(personel.sicilno);
	  
	});  
	
	}


//personelleri açılır menüye getirme
$(document).ready(function(){
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonelRight").append('<option id="personelselectRight" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
            });
        });
});
