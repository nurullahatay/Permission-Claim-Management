//departman yöneticilerini ayarlama
$(document).ready(
		function() {$("#setdepartmanmanagers").click(function() {
								var department = {};
								department["id"] = $("#personeldep2").val();
								department["departmentFirstManager"] = $("#selectpersonel2").val();
								department["departmentSecondManager"] = $("#selectpersonel3").val();
								$.ajax({
											type : "POST",
											contentType : "application/json",
											url : "/Permission-Claim-Management/rest/department/setDepartmentManagers",
											data : JSON
													.stringify(department),
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
