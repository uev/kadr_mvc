<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
<br/>
	<form 
  		class="form-horizontal span10 offset3">
         		<input type="text" placeholder="Название категории" name="category" id="inputCategory">
         		<button class="btn btn-small btn-primary offset0" type="button" onclick="appendCategory();">Создать</button>
	</form>