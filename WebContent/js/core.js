var authenticatedPersonel;
var isAdmin = false;
var isHR = false;
var isPersonel = false;



$(document).ready(function() {
  authenticatePersonel();
});

$("#dept").show();
function authenticatePersonel() {
  // login to system
  $.get("/Permission-Claim-Management/login", function() {
    $.ajax({
      type: "POST",
      url: '/Permission-Claim-Management/rest/session/getAuthenticatedPersonel',
      contentType: "application/json",
      mimeType: "application/json",
      success: function(data) {
        authenticatedUser = data;
        $("#nav_nickname").text(authenticatedPersonel.email);
        // sayfayi izinlere gore hazirla
        $.each(authenticatedPersonel.userRoles, function(key, value) {
          if (value == "admin")
            isAdmin = true;
          else if (value == "HR")
        	  isHR = true;
          else
        	  isPersonel = true;
        });
     
      }
    });
  });
}


function logout() {
  $.get("/Permission-Claim-Management/logout", function() {
    window.location = "/Permission-Claim-Management";
  });

}