$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateInquiryForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidPIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "InquiryAPI", 
type : type, 
data : $("#formInq").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onInquirySaveComplete(response.responseText, status); 
} 
}); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidPIDSave").val($(this).data("pid")); 
 $("#Subject").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#fullName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#accountNo").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#inquiryID").val($(this).closest("tr").find('td:eq(3)').text()); 
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "InquiryAPI", 
		 type : "DELETE", 
		 data : "PID=" + $(this).data("pid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onInquiryDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


//CLIENT-MODEL================================================================
function validateInquiryForm() 
{ 
// CODE
if ($("#Subject").val().trim() == "") 
 { 
 return "Insert Method."; 
 } 
// NAME
if ($("#fullName").val().trim() == "") 
 { 
 return "Insert full Name."; 
 } 
//AccountNo-------------------------------
if ($("#accountNo").val().trim() == "") 
 { 
 return "Insert accountNo."; 
 } 

// is numerical value
var tmpPrice = $("#accountNo").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for accountNo."; 
 } 
// convert to decimal price
 $("#accountNo").val(parseFloat(tmpPrice).toFixed(2)); 
 
// DESCRIPTION------------------------
if ($("#inquiryID").val().trim() == "") 
 { 
 return "Insert inquiryID."; 
 } 
return true; 
}

function onInquirySaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divInquiryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidPIDSave").val(""); 
$("#formInq")[0].reset(); 
}


function onInquiryDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divInquiryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
