<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form 
  		class="form-horizontal span10 offset3">
         		<input type="text" placeholder="Название подразделения" name="department" id="inputDepartment"/>
         		<input class="btn btn-primary btn-custom-login" type="button" onclick="appendDep();" value="       Создать       "/>
	</form>