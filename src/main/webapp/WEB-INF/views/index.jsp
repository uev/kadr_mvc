<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />
<body>
<center><h1>ATM project</h1></center>
<hr/>

<div class="container" align="center">
	<form action="/atm1/login.html" method="post" class = "input-append"> 
		<label> Введите пинкод </label> 
		<input type="text" name="pin" maxlength="4" class = "span2" /> 
		<input type="submit" value="Ввести"  class="btn btn-inverse"/>
	</form>
	<center>${error}</center>
</div>
<center>${countM}</center>
<br>
</body>
<jsp:include page="footer.jsp" />