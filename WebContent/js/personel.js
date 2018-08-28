//tüm personelleri listele
$(document).ready(function(){
    $("#getallpersonelbutton").on("click",function(){
    	var TDEKLE='</td><td>';
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
								var personel = {}
								personel["ad"] = $("#personelad").val();
								personel["soyad"] = $("#personelsoyad").val();
								personel["email"] = $("#personelemail").val();
								personel["password"] = $("#personelpassword").val();
								//personel["isebaslangictarihi"] = $("#personelisbasi").val();
								//personel["department"] = $("#personeldep").val();
								personel["pozisyon"] = $("#personelpoz").val();
								personel["ikinciyoneticionay"] = $("#personeliyonay").val();
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

		
