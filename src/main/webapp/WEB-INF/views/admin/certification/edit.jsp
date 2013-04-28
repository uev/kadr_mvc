<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="menu.jsp" />
<form 
  		class="form-inline">
         	<div class="clearfix control-group">
              <legend>Тесты</legend>
              <select  placeholder="Категория" name="category" id="selectTest" class="span11">
				<option></option>
				<c:forEach var="i" items="${tests}">
					<option>${i.getName()}</option>
				</c:forEach>
			   </select>
            </div>
            <legend>Добавить к аттестации</legend>
            <div class="clearfix control-group">
              <label class="control-label" for="selectDepartment">Департамент</label>
              <select  placeholder="Департамент" name="department" id="selectDepartment" onClick="getEmployers();">
				<option></option>
				<c:forEach var="i" items="${departments}">
					<option>${i.getName()}</option>
				</c:forEach>
			   </select>
			   
			  <label class="control-label" for="selectEmploye">&nbsp&nbsp&nbsp&nbspСлужащий</label>
              <select  placeholder="Служащий" name="employe" id="selectEmploye">
				<option></option>
				<!-- 
				<c:forEach var="i" items="${tests}">
					<option>${i.getName()}</option>
				</c:forEach>
				 -->
			   </select>
            </div>
	</form>