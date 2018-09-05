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
    	  authenticatedPersonel=data;
    	 $.each(data.personelRoles, function(key, value) {
    		 
    		 if (value == "admin"){
    			 isAdmin=true;
    			$("#personelkayıtdiv").show();
    			$("#izintalebidiv").show();
    			$("#getallpersoneldiv").show();
    			$("#tümizinlerdiv").show();
    			$("#aizinhakedis").hide();
    			$("#izinhakedisdiv").hide();
    			 $("#getAllFirstManagerApproval").hide();
            	 $("#getAllSecondManagerApproval").hide();
            	 $("#getAllHRApproval").hide();
    			}
    		 

    		 
             if (value == "HR"){   
            	 isHR=true;
            	$("#personelkayıtdiv").show();
            	$("#izintalebidiv").show();
            	$("#getallpersoneldiv").show();
         		$("#tümizinlerdiv").show();
         		$("#izinhakedisdiv").show();
         		$("#aizinhakedis").show();
         		$("#getAllFirstManagerApproval").hide();
         		$("#getAllSecondManagerApproval").hide();
         		$("#getAllHRApproval").show();
         		}
    		 
              if (value == "personel"){
            	  isPersonel=true;
            	 $("#izintalebidiv").show();
     			$("#aizinhakedis").hide();
     			$("#izinhakedisdiv").hide();
     			$("#tümizinlerdiv").hide();
     			$("#personelkayıtdiv").hide();
     			$("#setdepartmanmanagersdiv").hide();
     			$("#getAllFirstManagerApproval").hide();
         		$("#getAllSecondManagerApproval").hide();
         		$("#getAllHRApproval").hide();
         		$("#tarihigecmeyenizinler").hide();
         		$("#atarihigecmeyenizinler").hide();
         		$("#adepartmanyonetimi").hide();
         		$("#aizinlistesi").hide();
         		$("#apersonellistesi").hide();
         		$("#apersonelekle").hide();
         		$("#getallpersoneldiv").hide();
         		$("#iziniptaldiv").hide();
         		
         		

              }
             if (value == "FirstManager"){
            	 isFirstManager = true;
            	 $("#getAllFirstManagerApproval").show();
            	 $("#getAllSecondManagerApproval").hide();
            	 $("#getAllHRApproval").hide();
           		$("#getallpersoneldiv").hide();
           		$("#iziniptaldiv").hide();
             }
    		 
             if (value == "SecondManager"){
            	 isSecondManager = true;
            	 $("#getAllFirstManagerApproval").hide();
            	 $("#getAllSecondManagerApproval").show();
            	 $("#getAllHRApproval").hide();
            		$("#getallpersoneldiv").hide();
            		$("#iziniptaldiv").hide();
             }
           });
      },
		error : function() {
			alert("error: authenticatePersonel");

		}
    });
  });
}





function logout() {
  $.get("/Permission-Claim-Management/logout", function() {
    window.location = "/Permission-Claim-Management";
  });

}

