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
			   <button class="btn btn-small btn-primary" type="button" onClick="setTest();">Сохранить</button>
            </div>
            <legend id ="${cert_title}">Добавить к аттестации "${cert_title}"</legend>
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
			   </select>&nbsp&nbsp
			   <button class="btn btn-small btn-primary" type="submit">Добавить</button>
            </div>
	</form>
	<hr/>
      <table class="table table-striped">
    		<tbody>
    			<tr><td></td><td>id</td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td></tr>
    			<c:forEach var="record" items="${employeList}">
    		    	<tr>
    		    			<td><input type="radio" value="${record.fio}" name="radiogroup" onClick="getFioItem()"></td>
    						<td><c:out value="${record.id}"></c:out></td>
        					<td><c:out value="${record.fio}"></c:out></td>
        					<td><c:out value="${record.department}"></c:out></td>
        					<td><c:out value="${record.country}"></c:out></td>
        					<td><c:out value="${record.region}"></c:out></td>
        					<td><c:out value="${record.city}"></c:out></td>
    		   		</tr>      
    			</c:forEach>
	    	</tbody>
	    </table>