//tüm personelleri listele
$(document).ready(function(){
    var TDEKLE='</th><td>';

        $.getJSON("/Permission-Claim-Management/rest/personel/getAllPersonel", function(result){
            $.each(result, function(i, personel){
            	if(personel.ikinciyoneticionay==true)
            		var evethayır='Evet';
            	else
            		var evethayır='Hayır';
                $("#getallpersoneltable").append('<tr><td>'+personel.sicilno+TDEKLE+personel.ad+TDEKLE+personel.soyad+TDEKLE+personel.email+TDEKLE+personel.password+TDEKLE+personel.isebaslangictarihi+TDEKLE+personel.pozisyon+TDEKLE+evethayır+TDEKLE+'<button type="button" id="delete" class="'+personel.sicilno+'">Sil</button>'+'</td></tr>');
            });
        });
    });


//personel silme
$(document).ready(function(){
    $(document).on("click","#delete",function(){
        var sicilno= $(this).attr("class");
        var personel={}
        personel["sicilno"]=sicilno;
        $
		.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/Permission-Claim-Management/rest/personel/deletePersonel",
			data : sicilno,
			
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

//Personel ekleme
$(document).ready(
		function() {
			$("#postPersonel")
					.click(
							function() {
								var personel = {};
								personel["ad"] = $("#personelad").val();
								personel["soyad"] = $("#personelsoyad").val();
								personel["email"] = $("#personelemail").val();
								personel["password"] = $("#personelpassword").val();
								//personel["isebaslangictarihi"] = $("#personelisbasi").val();
								personel["department"] = 0;
								personel["pozisyon"] = $("#personelpoz").val();
								personel["ikinciyoneticionay"] = $("#personeliyonay").val();
								
								var roles = [];
								if ($('#adminRole').is(':checked')) {
									roles.push("admin");
								}
								if ($('#HRRole').is(':checked')) {
									roles.push("HR");
								}
								if ($('#personelRole').is(':checked')) {
									roles.push("personel");
								}
								personel["personelRoles"] = roles;
								$
										.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/Permission-Claim-Management/rest/personel/addPersonel",
											data : JSON
													.stringify(personel),
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

		
