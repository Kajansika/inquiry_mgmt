<%@page import="com.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Inquiry.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Inquiry</h1>
<form id="formInq" name="formInq">
  Subject: 
 <input id="Subject" name="Subject" type="text" 
 class="form-control form-control-sm">
 <br> full Name: 
 <input id="fullName" name="fullName" type="text" 
 class="form-control form-control-sm">
 <br> Account Number: 
 <input id="accountNo" name="accountNo" type="text" 
 class="form-control form-control-sm">
 <br> Inquiry ID: 
 <input id="inquiryID" name="inquiryID" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidPIDSave" 
 name="hidPIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divInquiryGrid">
	<%
	Inquiry inquiryObj = new Inquiry(); 
		 		out.print(inquiryObj.readInqe());
	%>
</div>
</div> </div> </div> 
</body>
</html>