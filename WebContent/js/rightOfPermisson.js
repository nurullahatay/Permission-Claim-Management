function addRightOfPermission() {
	var personel = $('#personelarama').val();
	var tarih = "qw";
	var hakedilenGunSayisi = $('#hakedilengunsayisi').val();
	var mevcutYilIciHakedilenGunSayisi = $('#hakedilengunsayisi').val();

	var rightOfPermission = {};

	rightOfPermission["sicilNo"] = personel;
	rightOfPermission["ValidDate"] = tarih;
	rightOfPermission["DayCountOfDeserved"] = hakedilenGunSayisi;
	rightOfPermission["DayCountOfDeservedForYear"] = mevcutYilIciHakedilenGunSayisi;

	console.log(rightOfPermission.ValidDate);

			
		    $.ajax({
		        type: "POST",
		        url: '/Permission-Claim-Management/rest/right/addRight',
		        contentType: "application/json",
		        mimeType: "application/json",
			data : JSON.stringify(rightOfPermission),
			dataType : 'json',
			cache : false,
			timeout : 100000,
			success : function(result) {
				alert("success");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("error");

			}
		});}