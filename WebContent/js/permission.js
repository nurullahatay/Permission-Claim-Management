//personelleri açılır menüye getirme
$(document).ready(function(){
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonel").append('<option id="personelselect" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
            });
        });
});

//birici yönetici onayı
function confirmedPermissionFirstManager(permissionId) {
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/confirmedPermissionFirstManager',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(permissionId),
		success : function(result) {
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error");

		}
	});	
}
//birici yönetici onayı
function deniedPermissionFirstManager(permissionId) {
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/deniedPermissionFirstManager',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(permissionId),
		success : function(result) {
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error");

		}
	});	
}

//birici yönetici onayı bekleyen izinler.
function getFirstManagerApproval(deptId) {
	var TDEKLE='</td><td>';
	var durum='Henüz İncelenmedi';

	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getFirstManagerApproval',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(deptId),
		success : function(result) {
	$.each(result, function(i, per){
	        	
	        	$("#FirstManagerApproval").append('<tr><td>'+per.id+TDEKLE+per.sicilNo+TDEKLE+per.formTarihi+TDEKLE+per.baslangicTarihi+TDEKLE+per.bitisTarihi+TDEKLE+per.gun+TDEKLE+per.izinNedeni+TDEKLE+per.telefonNumarasi+TDEKLE+per.adres+TDEKLE+durum+TDEKLE+'<button type="button" onclick="confirmedPermissionFirstManager('+per.id+')" id="onaybuton" class="'+per.id+'">Onay</button>'+TDEKLE+'<button onclick="deniedPermissionFirstManager('+per.id+')" type="button" id="redbuton" class="'+per.id+'">Red</button>'+'</td></tr>');
	        });
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error");

		}
	});	
}
var formfiller;
$(document).ready(function(){
	$.getJSON("/Permission-Claim-Management/rest/session/getAuthenticatedPersonel", function(personel){

	$("#formudolduran").text(personel.ad+' '+personel.soyad);
	formfiller=personel.sicilno;
	getFirstManagerApproval(personel.department);
	});  
});

//personelleri açılır menüye getirme
$(document).ready(function(){
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonel2").append('<option id="personelselect2" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
            });
        });
});
//personelleri açılır menüye getirme
$(document).ready(function(){
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonel3").append('<option id="personelselect3" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
            });
        });
});

function getRightOfPermission(sicilNo) {

	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/right/getRightDetails',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(sicilNo),
		success : function(result) {
			$("#kullanilabilecekizin").text(result.dayCountOfDeserved);
			$("#toplamhakedilen").text(result.dayCountOfDeservedForYear);
		},
		error : function() {
			alert("error");

		}
	});
}


// açılır menüden seçilen personelin bilgilerinin doldurulması
$(document).ready(function(){
	 $(document).on("click","#personelselect",function(){
		 sicilno=$(this).attr("value");
		 $.ajax({
				type : "POST",
				url : '/Permission-Claim-Management/rest/personel/getPersonel',
				contentType : "application/json",
				mimeType : "application/json",
				data : sicilno,
				success : function(result) {
					getDepartman(result.department);
					$("#sicilno").text(result.sicilno);
					$("#isebaslama").text(result.isebaslangictarihi);
					 getRightOfPermission(result.sicilno);
				},
				error : function() {
					alert("error");
				}
			});
    });
});
//departman bilgisinin doldurulması
function getDepartman(variable){
		 $.ajax({
			 	type : "POST",
				url : '/Permission-Claim-Management/rest/department/getDepartment',
				contentType : "application/json",
				mimeType : "application/json",
				data : ''+variable,
				success : function(result) {
					$("#departman").text(result.departmentName);
				},
				error : function() {
					alert("error");

				}
			});
}

// Tüm izinleri Listeleme
$(document).ready(function(){
    	var TDEKLE='</td><td>';
    	var durum='Henüz İncelenmedi';
        $.getJSON("/Permission-Claim-Management/rest/permission/getAllPermission", function(result){
            $.each(result, function(i, permission){
            	if(permission.durum==true)
            		var durum='Onaylandı';
            	if(permission.durum==false)
            		var durum='Reddedildi';
            	$("#tümizinlertable").append('<tr><td>'+permission.id+TDEKLE+permission.formTarihi+TDEKLE+permission.baslangicTarihi+TDEKLE+permission.bitisTarihi+TDEKLE+permission.gun+TDEKLE+permission.izinNedeni+TDEKLE+permission.telefonNumarasi+TDEKLE+permission.adres+TDEKLE+durum+TDEKLE+'<button type="button" id="delete" class="'+permission.id+'">Sil</button>'+'</td></tr>');
            });
    });
});


// izintalebi
$(document).ready(
		function() {
			$("#postPermission")
					.click(
							function() {
								var permission = {}
								permission["sicilNo"] = $("#selectpersonel").val();
								permission["baslangicTarihi"] = $("#permissionstart").val();
								permission["bitisTarihi"] = $("#permissionfinish").val();
								permission["gun"] = $("#permissiongun").val();
								permission["izinNedeni"] = $("#permissionreason").val();
								permission["aciklama"] = $("#permissionaciklama").val();
								permission["telefonNumarasi"] = $("#permissiontel").val();
								permission["adres"] = $("#permissionadres").val();
								permission["formFiller"]= formfiller;
								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/Permission-Claim-Management/rest/permission/addPermission",
											data : JSON
													.stringify(permission),
											dataType : 'json',
											cache : false,
											timeout : 100000,
											success : function(data) {
												alert("SUCCESS : ",data);
											},
											error : function(e) {
												alert("ERROR : ",e);
											}
										});

							});
		});

		
// izin hakediş için dinamik div
$(document).ready(function(){
	 var i = 1;
	    $("#ekle").click(function () {
	    	i++;
			$(".izinhakedis").append('<tr id="izinhakedistr'+i+'"><td><input id="personelarama'+i+'" name="personelarama'+i+'"class="form-control input-md" placeholder="Ara" type="text"></td><td><div class="datepicker"><input id="tarih" class="form-control" type="datepicker"><input type="hidden" id="tarih-db" name="gecerliolacagitarih'+i+'"></div></td><td><input id="hakedilengunsayisi'+i+'" name="hakedilengunsayisi'+i+'" class="form-control input-md" type="text"></td></tr>');
	    });
	    $("#sil").click(function(){
	    	if(i==1)
	    		{
	    		alert("En az 1 personel olmalı")
	    		}
	    	if(i>1){
	    	$("#izinhakedistr"+i+"").remove(); 
	    	i--;
	    	}
	    });
});

// iki tarih arasındaki çalışma günlerini hesaplayan script
$(document).ready(function(){
	$('#permissiongun').mouseenter(function(){
  var d1 = $('#permissionstart').val();
  var d2 = $('#permissionfinish').val();
		$('#permissiongun').val(workingDaysBetweenDates(d1,d2));
	});
});

function workingDaysBetweenDates(d0, d1) {
	var holidays = ['2016-05-03','2016-05-05'];
    var startDate = parseDate(d0);
    var endDate = parseDate(d1);  
    // Validate input
    if (endDate < startDate) {
        return 0;
    }
    // Calculate days between dates
    var millisecondsPerDay = 86400 * 1000; // Day in milliseconds
    startDate.setHours(0,0,0,1);  // Start just after midnight
    endDate.setHours(23,59,59,999);  // End just before midnight
    var diff = endDate - startDate;  // Milliseconds between datetime objects
    var days = Math.ceil(diff / millisecondsPerDay);
    
    // Subtract two weekend days for every week in between
    var weeks = Math.floor(days / 7);
    days -= weeks * 2;

    // Handle special cases
    var startDay = startDate.getDay();
    var endDay = endDate.getDay();
    
    // Remove weekend not previously removed.
    if (startDay - endDay > 1) {
        days -= 2;
    }
    // Remove start day if span starts on Sunday but ends before Saturday
    if (startDay == 0 && endDay != 6) {
        days--;  
    }
    // Remove end day if span ends on Saturday but starts after Sunday
    if (endDay == 6 && startDay != 0) {
        days--;
    }
    /* Here is the code */
    for (var i in holidays) {
      if ((holidays[i] >= d0) && (holidays[i] <= d1)) {
      	days--;
      }
    }
    return days;
}
           
function parseDate(input) {
	// Transform date from text to date
  var parts = input.match(/(\d+)/g);
  // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
  return new Date(parts[0], parts[1]-1, parts[2]); // months are 0-based
}


// takvim scriptleri
$(document).ready(function(){
				$(function() {
					$("#tarih").datepicker(
							{
								beforeShowDay : $.datepicker.noWeekends,
								dateFormat : "dd-mm-yy",
								altFormat : "yy-mm-dd",
								altField : "#permissionstart",
								monthNames : [ "Ocak", "Şubat", "Mart",
										"Nisan", "Mayıs", "Haziran", "Temmuz",
										"Ağustos", "Eylül", "Ekim", "Kasım",
										"Aralık" ],
								dayNamesMin : [ "Pa", "Pt", "Sl", "Ça", "Pe",
										"Cu", "Ct" ],
								firstDay : 1,
							});
				});
				});
				//
$(document).ready(function(){
				$(function() {
					$("#tarih2").datepicker(
							{
								beforeShowDay : $.datepicker.noWeekends,
								dateFormat : "dd-mm-yy",
								altFormat : "yy-mm-dd",
								altField : "#permissionfinish",
								monthNames : [ "Ocak", "Şubat", "Mart",
										"Nisan", "Mayıs", "Haziran", "Temmuz",
										"Ağustos", "Eylül", "Ekim", "Kasım",
										"Aralık" ],
								dayNamesMin : [ "Pa", "Pt", "Sl", "Ça", "Pe",
										"Cu", "Ct" ],
								firstDay : 1,
							});
				});
				});
				
// Şuanki tarihi gösteren script -->
var d=new Date();
var monthname=new Array("Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık");
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();


// izin hakediş için takvim(geçerli olacağı tarih inputu için)
$(document).ready(function(){
	$(function() {
		$("#gtarih").datepicker(
				{
					beforeShowDay : $.datepicker.noWeekends,
					dateFormat : "dd-mm-yy",
					altFormat : "yy-mm-dd",
					altField : "#gtarih-db",
					monthNames : [ "Ocak", "Şubat", "Mart",
							"Nisan", "Mayıs", "Haziran", "Temmuz",
							"Ağustos", "Eylül", "Ekim", "Kasım",
							"Aralık" ],
					dayNamesMin : [ "Pa", "Pt", "Sl", "Ça", "Pe",
							"Cu", "Ct" ],
					firstDay : 1,
				});
	});
});