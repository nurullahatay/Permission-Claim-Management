//izintalebi
$(document).ready(
		function() {
			$("#postPermission")
					.click(
							function() {
								var permission = {}
								permission["sicilNo"] = 2;
								permission["baslangicTarihi"] = $("#permissionstart").val();
								permission["bitisTarihi"] = $("#permissionfinish").val();
								permission["gun"] = $("#permissiongun").val();
								permission["izinNedeni"] = $("#permissionreason").val();
								permission["aciklama"] = $("#permissionaciklama").val();
								permission["telefonNumarasi"] = $("#permissiontel").val();
								permission["adres"] = $("#permissionadres").val();
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
		
//izin hakediş için dinamik div
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
	    	$("#izinhakedistr"+i+"").remove(); <!-- tablodaki satırları sil ––>
	    	i--;}
	    });
});

//iki tarih arasındaki çalışma günlerini hesaplayan script
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


//takvim scriptleri
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
				
//Şuanki tarihi gösteren script -->
var d=new Date();
var monthname=new Array("Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık");
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();


//izin hakediş için takvim(geçerli olacağı tarih inputu için)
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