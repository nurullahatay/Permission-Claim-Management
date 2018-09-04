//personelleri açılır menüye getirme
var isPersonel;



//birici yönetici onayı
function confirmedPermissionFirstManager(permissionId) {
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorum").val();
	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/confirmedPermissionFirstManager',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ");
		},
		error : function() {
			alert("error: confirmedPermissionFirstManager");

		}
	});	
}
//birici yönetici reddi
function deniedPermissionFirstManager(permissionId) {
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorum").val();
	
	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/deniedPermissionFirstManager',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error : deniedPermissionFirstManager");

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
	        	
	        	$("#FirstManagerApproval").append('<tr><td>'+per.id+TDEKLE+per.sicilNo+TDEKLE+per.formTarihi+TDEKLE+per.baslangicTarihi+TDEKLE+per.bitisTarihi+TDEKLE+per.gun+TDEKLE+per.izinNedeni+TDEKLE+per.telefonNumarasi+TDEKLE+per.adres+TDEKLE+durum+TDEKLE+'<button  onclick="getPermissionInfo('+per.id+')"  type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#İzinBilgisi">Göster</button>'+'</td></tr>');
	        });
			
		},
		error : function() {
			alert("error : getFirstManagerApproval");

		}
	});	
}

//2. yönetici onayı
function confirmedPermissionSecondManager(permissionId) {
	
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorum2").val();
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/confirmedPermissionSecondManager',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(obj) {
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error : confirmedPermissionSecondManager");

		}
	});	
}
//2. yönetici reddi
function deniedPermissionSecondManager(permissionId) {
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorum2").val();
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/deniedPermissionSecondManager',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ");
		},
		error : function() {
			alert("error : deniedPermissionSecondManager");

		}
	});	
}
//2. yönetici onayı bekleyen izinler.
function getSecondManagerApproval(deptId) {
	var TDEKLE='</td><td>';
	var durum='Henüz İncelenmedi';

	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getSecondManagerApproval',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(deptId),
		success : function(result) {
	$.each(result, function(i, per){
	        	
	        	$("#SecondManagerApproval").append('<tr><td>'+per.id+TDEKLE+per.sicilNo+TDEKLE+per.formTarihi+TDEKLE+per.baslangicTarihi+TDEKLE+per.bitisTarihi+TDEKLE+per.gun+TDEKLE+per.izinNedeni+TDEKLE+per.telefonNumarasi+TDEKLE+per.adres+TDEKLE+durum+TDEKLE+'<button  onclick="getPermissionInfo2('+per.id+')"  type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#ikinciyoneticiizinbilgisi">Göster</button>'+'</td></tr>');
	        });
			
		},
		error : function() {
			alert("error : getSecondManagerApproval");

		}
	});	
}
//
//
//HR onayı
function confirmedPermissionHR(permissionId) {
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorumhr").val();
	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/confirmedPermissionHR',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error : confirmedPermissionHR");

		}
	});	
}
//HR reddi
function deniedPermissionHR(permissionId) {
	
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorumhr").val();
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/deniedPermissionHR',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ",data);
		},
		error : function() {
			alert("error : deniedPermissionHR");

		}
	});	
}
//HR onayı bekleyen izinler.
function getHRApproval() {
	var TDEKLE='</td><td>';
	var durum='Henüz İncelenmedi';

	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getHRApproval',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(),
		success : function(result) {
	$.each(result, function(i, per){
	        	
	        	$("#HRApproval").append('<tr><td>'+per.id+TDEKLE+per.sicilNo+TDEKLE+per.formTarihi+TDEKLE+per.baslangicTarihi+TDEKLE+per.bitisTarihi+TDEKLE+per.gun+TDEKLE+per.izinNedeni+TDEKLE+per.telefonNumarasi+TDEKLE+per.adres+TDEKLE+durum+TDEKLE+'<button  onclick="getPermissionInfoHR('+per.id+')"  type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#hryoneticiizinbilgisi">Göster</button>'+'</td></tr>');
	        });
			
		},
		error : function() {
			alert("error : getHRApproval");

		}
	});	
}


//personel onayı
function confirmedPermissionPersonel(permissionId) {
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorump").val();
	
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/confirmedPermissionPersonel',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ",result);
		},
		error : function() {
			alert("error : confirmedPermissionPersonel");

		}
	});	
}
//personel reddi
function deniedPermissionPersonel(permissionId) {
	var obj = {};
	obj["id"]=permissionId; 
	obj["comment"] =$("#yorump").val();
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/deniedPermissionPersonel',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(obj),
		success : function(result) {
			alert("SUCCESS : ",result);
		},
		error : function() {
			alert("error : deniedPermissionPersonel");

		}
	});	
}

//Personel onayı bekleyen izinler.
function getPersonelApproval(sicilno) {
	var TDEKLE='</td><td>';
	var durum='Henüz İncelenmedi';


	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getPersonelApproval',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(sicilno),
		success : function(result) {
	$.each(result, function(i, per){
	        	
	        	$("#PersonelApproval").append('<tr><td>'+per.id+TDEKLE+per.sicilNo+TDEKLE+per.formTarihi+TDEKLE+per.baslangicTarihi+TDEKLE+per.bitisTarihi+TDEKLE+per.gun+TDEKLE+per.izinNedeni+TDEKLE+per.telefonNumarasi+TDEKLE+per.adres+TDEKLE+durum+TDEKLE+'<button  onclick="getPermissionInfoP('+per.id+')"  type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#pyoneticiizinbilgisi">Göster</button>'+'</td></tr>');
	        });
			
		},
		error : function() {
			alert("error : getPersonelApproval");

		}
	});	
}


var formfiller;
$(document).ready(function(){
	$.getJSON("/Permission-Claim-Management/rest/session/getAuthenticatedPersonel", function(personel){
   	 $.each(personel.personelRoles, function(key, value) {

		if (value =="personel"){
	    	 $("#dropboxgetirme").hide();

		getDepartman(personel.department);
		$("#sicilno").text(personel.sicilno);
		$("#isebaslama").text(personel.isebaslangictarihi);
		 getRightOfPermission(personel.sicilno);
		 isPersonel=true;

		}
		
		if (value =="FirstManager"){
			getFirstManagerApproval(personel.department);
		}
		if (value =="HR"){
			getHRApproval();
		}
		if (value =="SecondManager"){
			getSecondManagerApproval(personel.department);
		}

		
     });

		getPersonelApproval(personel.sicilno);
		getMyOwnPermissions(personel.sicilno);

	$("#formudolduran").text(personel.ad+' '+personel.soyad);
	formfiller=personel.sicilno;
	});  
});

$(document).ready(function(){
	if (isPersonel==true){

		
	}else{
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonel").append('<option id="personelselect" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
            });
        });
	}
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
//personelleri açılır menüye getirme
$(document).ready(function(){
        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
                $("#selectpersonelforsearch").append('<option id="personelselect4" value="'+personel.sicilno+'">'+personel.ad+' '+personel.soyad+'</option');
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
			$("#kullanilabilecekizinpop").text(result.dayCountOfDeserved);
		    $("#toplamhakedilenpop").text(result.dayCountOfDeservedForYear);
			$("#kullanilabilecekizinpop2").text(result.dayCountOfDeserved);
		    $("#toplamhakedilenpop2").text(result.dayCountOfDeservedForYear);
			$("#kullanilabilecekizinpophr").text(result.dayCountOfDeserved);
		    $("#toplamhakedilenpophr").text(result.dayCountOfDeservedForYear);
			$("#kullanilabilecekizinpopp").text(result.dayCountOfDeserved);
		    $("#toplamhakedilenpopp").text(result.dayCountOfDeservedForYear);
		},
		error : function() {
			alert("error : getRightOfPermission");

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
					alert("error : personelselect");
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
				    $("#departmanpop").text(result.departmentName);
				    $("#departmanpop2").text(result.departmentName);
				    $("#departmanpophr").text(result.departmentName);
				    $("#departmanpopp").text(result.departmentName);

				},
				error : function() {
					alert("error : getDepartman");

				}
			});
}

//Tüm izinleri Listeleme
$(document).ready(function(){
    	var TDEKLE='</td><td>';
    	var durum='Henüz İncelenmedi';
        $.getJSON("/Permission-Claim-Management/rest/permission/getAllPermission", function(result){
            $.each(result, function(i, permission){
            	if(permission.durum=='0')
            		durum='Beklemede';
            	else 
            		durum = permission.durum;
            	$("#tümizinlertable").append('<tr><td>'+permission.id+TDEKLE+permission.formTarihi+TDEKLE+permission.baslangicTarihi+TDEKLE+permission.bitisTarihi+TDEKLE+permission.gun+TDEKLE+permission.izinNedeni+TDEKLE+permission.telefonNumarasi+TDEKLE+permission.adres+TDEKLE+durum+'</td></tr>');
            });
    });
});



//tarihi geçmeyen izinleri getirme
$(document).ready(function(){
    	var TDEKLE='</td><td>';
    	var durum='Henüz İncelenmedi';
        $.getJSON("/Permission-Claim-Management/rest/permission/getFromNowLaterAllPermission", function(result){
            $.each(result, function(i, permission){
            	if(permission.durum=='0')
            		durum='Beklemede';
            	else 
            		durum = permission.durum;
            	$("#tarihigecmeyenizinlertable").append('<tr><td>'+permission.id+TDEKLE+permission.formTarihi+TDEKLE+permission.baslangicTarihi+TDEKLE+permission.bitisTarihi+TDEKLE+permission.gun+TDEKLE+permission.izinNedeni+TDEKLE+permission.telefonNumarasi+TDEKLE+permission.adres+TDEKLE+durum+'</td></tr>');
            });
    });
});


//kendi izimlerimi getirme
function getMyOwnPermissions(variable){
	var TDEKLE='</td><td>';
	var durum='Henüz İncelenmedi';
		 $.ajax({
			 	type : "POST",
				url : '/Permission-Claim-Management/rest/permission/getMyOwnPermissions',
				contentType : "application/json",
				mimeType : "application/json",
				data : JSON.stringify(variable),
				success : function(result) {
					 $.each(result, function(i, permission){
			            	if(permission.durum=='0')
			            		durum='Beklemede';
			            	else 
			            		durum = permission.durum;
			            	$("#kendiizinlerimtable").append('<tr><td>'+permission.id+TDEKLE+permission.formTarihi+TDEKLE+permission.baslangicTarihi+TDEKLE+permission.bitisTarihi+TDEKLE+permission.gun+TDEKLE+permission.izinNedeni+TDEKLE+permission.telefonNumarasi+TDEKLE+permission.adres+TDEKLE+durum+'</td></tr>');
			            });

				},
				error : function() {
					alert("error : getMyOwnPermissions");

				}
			});
}

function getPermissionInfo(id){
    $("#deneme").text(id);
    $.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getPermission',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(id),
		success : function(permission) {
			sicilNo=permission.sicilNo;
		    $("#sicilnopop").text(permission.sicilNo);
		    $("#formudolduranpop").text(permission.formFiller);
		    $("#formtarihinpop").text(permission.formTarihi);
		    $("#izinbaslangıctarihipop").text(permission.baslangicTarihi);
		    $("#izindonustarihipop").text(permission.bitisTarihi);
		    $("#gunsayisipop").text(permission.gun);
		    $("#nedenipop").text(permission.izinNedeni);
		    $("#aciklamapop").text(permission.aciklama);
		    $("#telpop").text(permission.telefonNumarasi);
		    $("#adrespop").text(permission.adres);
		    $("#izinidpop").text(permission.id);    
		    $.ajax({
				type : "POST",
				url : '/Permission-Claim-Management/rest/personel/getPersonel',
				contentType : "application/json",
				mimeType : "application/json",
				data :  JSON.stringify(permission.sicilNo),
				success : function(result) {
					getDepartman(result.department);
					$("#personelpop").text(result.ad+' '+result.soyad);
					$("#isebaslamapop").text(result.isebaslangictarihi);
				},
				error : function() {
					alert("error : getPersonel");
				}
			});
		 
		 getRightOfPermission(permission.sicilNo);
		    
		    
		},
		error : function() {
			alert("error : getPermissionInfo");

		}
	});
    
    
	
    
}
 



function getPermissionInfo2(id){
    $.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getPermission',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(id),
		success : function(permission) {
			sicilNo=permission.sicilNo;
		    $("#sicilnopop2").text(permission.sicilNo);
		    $("#formudolduranpop2").text(permission.formFiller);
		    $("#formtarihinpop2").text(permission.formTarihi);
		    $("#izinbaslangıctarihipop2").text(permission.baslangicTarihi);
		    $("#izindonustarihipop2").text(permission.bitisTarihi);
		    $("#gunsayisipop2").text(permission.gun);
		    $("#nedenipop2").text(permission.izinNedeni);
		    $("#aciklamapop2").text(permission.aciklama);
		    $("#telpop2").text(permission.telefonNumarasi);
		    $("#adrespop2").text(permission.adres);
		    $("#izinidpop2").text(permission.id);    
		    $.ajax({
				type : "POST",
				url : '/Permission-Claim-Management/rest/personel/getPersonel',
				contentType : "application/json",
				mimeType : "application/json",
				data :  JSON.stringify(permission.sicilNo),
				success : function(result) {
					getDepartman(result.department);
					$("#personelpop2").text(result.ad+' '+result.soyad);
					$("#isebaslamapop2").text(result.isebaslangictarihi);
				},
				error : function() {
					alert("error : getPersonel");
				}
			});
		 
		 getRightOfPermission(permission.sicilNo);
		    
		    
		},
		error : function() {
			alert("error : getPermissionInfo");

		}
	});
    
    
	
    
}







function getPermissionInfoHR(id){
    $.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getPermission',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(id),
		success : function(permission) {
			sicilNo=permission.sicilNo;
		    $("#sicilnopophr").text(permission.sicilNo);
		    $("#formudolduranpophr").text(permission.formFiller);
		    $("#formtarihinpophr").text(permission.formTarihi);
		    $("#izinbaslangıctarihipophr").text(permission.baslangicTarihi);
		    $("#izindonustarihipophr").text(permission.bitisTarihi);
		    $("#gunsayisipophr").text(permission.gun);
		    $("#nedenipophr").text(permission.izinNedeni);
		    $("#aciklamapophr").text(permission.aciklama);
		    $("#telpophr").text(permission.telefonNumarasi);
		    $("#adrespophr").text(permission.adres);
		    $("#izinidpophr").text(permission.id);    
		    $.ajax({
				type : "POST",
				url : '/Permission-Claim-Management/rest/personel/getPersonel',
				contentType : "application/json",
				mimeType : "application/json",
				data :  JSON.stringify(permission.sicilNo),
				success : function(result) {
					getDepartman(result.department);
					$("#personelpophr").text(result.ad+' '+result.soyad);
					$("#isebaslamapophr").text(result.isebaslangictarihi);
				},
				error : function() {
					alert("error : getPersonel");
				}
			});
		 
		 getRightOfPermission(permission.sicilNo);
		    
		    
		},
		error : function() {
			alert("error : getPermissionInfo");

		}
	});
    
    
	
    
}




function getPermissionInfoP(id){
    $.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/getPermission',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(id),
		success : function(permission) {
			sicilNo=permission.sicilNo;
		    $("#sicilnopopp").text(permission.sicilNo);
		    $("#formudolduranpopp").text(permission.formFiller);
		    $("#formtarihinpopp").text(permission.formTarihi);
		    $("#izinbaslangıctarihipopp").text(permission.baslangicTarihi);
		    $("#izindonustarihipopp").text(permission.bitisTarihi);
		    $("#gunsayisipopp").text(permission.gun);
		    $("#nedenipopp").text(permission.izinNedeni);
		    $("#aciklamapopp").text(permission.aciklama);
		    $("#telpopp").text(permission.telefonNumarasi);
		    $("#adrespopp").text(permission.adres);
		    $("#izinidpopp").text(permission.id);    
		    $.ajax({
				type : "POST",
				url : '/Permission-Claim-Management/rest/personel/getPersonel',
				contentType : "application/json",
				mimeType : "application/json",
				data :  JSON.stringify(permission.sicilNo),
				success : function(result) {
					getDepartman(result.department);
					$("#personelpopp").text(result.ad+' '+result.soyad);
					$("#isebaslamapopp").text(result.isebaslangictarihi);
				},
				error : function() {
					alert("error : getPersonel");
				}
			});
		 
		 getRightOfPermission(permission.sicilNo);
		    
		    
		},
		error : function() {
			alert("error : getPermissionInfo");

		}
	});
    
    
	
    
}






// izintalebi
$(document).ready(
		function() {
			$("#postPermission")
					.submit(
							function() {
								var permission = {}
								if (isPersonel==true){
									permission["sicilNo"]=authenticatedPersonel.sicilno;
								}else{
									permission["sicilNo"] = $("#selectpersonel").val();
								}
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
												alert("ERROR : postPermission");
											}
										});

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



//izin iptal için takvim(aramada başlangıc zamanın belirleme)
$(document).ready(function(){
	$(function() {
		$("#btarih").datepicker(
				{
					beforeShowDay : $.datepicker.noWeekends,
					dateFormat : "dd-mm-yy",
					altFormat : "yy-mm-dd",
					altField : "#btarih-db",
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

//izin iptal için takvim(aramada bitis zamanın belirleme)
$(document).ready(function(){
	$(function() {
		$("#starih").datepicker(
				{
					beforeShowDay : $.datepicker.noWeekends,
					dateFormat : "dd-mm-yy",
					altFormat : "yy-mm-dd",
					altField : "#starih-db",
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

//izin arama
$(document).ready(function(){
    $(document).on("click","#iziniptalarama",function(){
    	$("#iziniptalsonuclari").empty();
	var TDEKLE='</td><td>';
	var durum='Henüz İncelenmedi';
	var permission={};
	permission["sicilNo"]=$("#selectpersonelforsearch").val();
	permission["baslangicTarihi"] = $("#btarih-db").val();
	permission["bitisTarihi"] = $("#starih-db").val();
	 $.ajax({
			type : "POST",
			url : '/Permission-Claim-Management/rest/permission/searchPermission',
			contentType : "application/json",
			mimeType : "application/json",
			data : JSON
			.stringify(permission),
			success : function(result) {
				$.each(result, function(i, permission){
	            	if(permission.durum=='0')
	            		durum='Beklemede';
	            	else 
	            		durum = permission.durum;
	            	$("#iziniptalsonuclari").append('<tr><td>'+permission.id+TDEKLE+permission.sicilNo+TDEKLE+permission.formTarihi+TDEKLE+permission.baslangicTarihi+TDEKLE+permission.bitisTarihi+TDEKLE+permission.gun+TDEKLE+permission.izinNedeni+TDEKLE+permission.telefonNumarasi+TDEKLE+permission.adres+TDEKLE+durum+TDEKLE+'<button type="button" onclick="'+cancelPermission(permission.id)+'">İptal</button>'+'</td></tr>');
	            });
			},
			error : function() {
				alert("error : personelselect");
			}
		});
    });  
});


function cancelPermission(permission){
	$.ajax({
		type : "POST",
		url : '/Permission-Claim-Management/rest/permission/cancelPermission',
		contentType : "application/json",
		mimeType : "application/json",
		data : JSON.stringify(permission),
		success : function(result) {
			//alert("SUCCESS : ",result);
		},
		error : function() {
			alert("error : cancelPermission");

		}
	});	
	
}




