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

		
