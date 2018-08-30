var authenticatedPersonel;
var isAdmin = false;
var isHR = false;
var isPersonel = false;
var isFirstManager = false;
var isSecondManager = false;



$(document).ready(function() {
  authenticatePersonel();

  
  
  
});

$("#dept").show();
function authenticatePersonel() {
 
  $.get("/Permission-Claim-Management/login", function() {
    $.ajax({
      type: "GET",
      url: '/Permission-Claim-Management/rest/session/getAuthenticatedPersonel',
      contentType: "application/json",
      mimeType: "application/json",
      success: function(data) {
    	  authenticatedPersonel = data;
    	$("#personel_avatar_name").text(authenticatedPersonel.ad+' '+authenticatedPersonel.soyad);
    	 $.each(authenticatedUser.userRoles, function(key, value) {
             if (value == "admin")
               isAdmin = true;
             else if (value == "HR")
               isHR = true;
             else if (value == "personel")
            	 isPersonel = true;
             else if (value == "firstManager")
            	 isFirstManager = true;
             else
            	 isSecondManager = true;
           });
      },
		error : function() {
			alert("error");

		}
    });
  });
}





function logout() {
  $.get("/Permission-Claim-Management/logout", function() {
    window.location = "/Permission-Claim-Management";
  });

}

