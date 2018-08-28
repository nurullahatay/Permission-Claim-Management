var authenticatedPersonel;
var isAdmin = false;
var isHR = false;
var isPersonel = false;



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

