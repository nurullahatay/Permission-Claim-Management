var authenticatedPersonel;
var isAdmin = false;
var isHR = false;
var isPersonel = false;
var isFirstManager = false;
var isSecondManager = false;


//rollere göre div gösterme
$(document).ready(function() {
  authenticatePersonel();
});

function authenticatePersonel() {
  $.get("/Permission-Claim-Management/login", function() {
    $.ajax({
      type: "GET",
      url: '/Permission-Claim-Management/rest/session/getAuthenticatedPersonel',
      contentType: "application/json",
      mimeType: "application/json",
      success: function(data) {
    	 $.each(data.personelRoles, function(key, value) {
    		 
    		 if (value == "admin"){
    			$("#personelkayıtdiv").show();
    			$("#izintalebidiv").show();
    			$("#getallpersoneldiv").show();
    			$("#tümizinlerdiv").show();
    			//$("#aizinhakedis").hide();
    			$("#izinhakedisdiv").hide();
    			}
    		 
             else if (value == "HR"){   
            	$("#personelkayıtdiv").show();
            	$("#izintalebidiv").show();
            	$("#getallpersoneldiv").show();
         		$("#tümizinlerdiv").show();
         		$("#izinhakedisdiv").show();
         		$("#aizinhakedis").show();
         		}
    		 
             else if (value == "personel")
            	 $("#izintalebidiv").show();
    		 
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

