<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="menu.jsp" />
<form 
  		class="form-inline">
         	<div class="clearfix control-group">
              <fieldset>
              	<legend>Тесты</legend>
              </fieldset>
              <br/>
              <select  placeholder="Тест" name="test" id="selectTest" class="span10">
				<option></option>
				<c:forEach var="i" items="${tests}">
					<c:choose>
						<c:when test="${!current_test.equals(i.getName())}">
							<option>${i.getName()}</option>
						</c:when>
						<c:when test="${current_test.equals(i.getName())}">
							<option selected>${i.getName()}</option>
						</c:when>
					</c:choose>
				</c:forEach>
			   </select>
			   <input class="btn btn-primary btn-custom-login offset0" type="button" value="       Сохранить       " onClick="setTest(event);" />
            </div>
            <fieldset id ="certtitle">
            	<legend>${cert_title['name']}</legend>
            </fieldset><br/>
            <div class="clearfix control-group">
              <label class="control-label" for="selectDepartment">Департамент</label>
              <select  placeholder="Департамент" name="department" id="selectDepartment" onClick="getEmployers();">
				<option></option>
				<c:forEach var="i" items="${departments}">
					<option>${i.getName()}</option>
				</c:forEach>
			   </select>
			   
			  <label class="control-label" for="selectEmploye">&nbsp&nbsp&nbsp&nbsp&nbsp&nbspСлужащий</label>
              <select  placeholder="Служащий" name="employe" id="selectEmploye" class="span5">
				<option></option>
			   </select>
			   <input class="btn btn-primary btn-custom-login offset0" type="button" value="         Добавить         " onClick="appendPersonCertification(event);" />
			   
            </div>
	</form>
	<hr/>
      <table class="table table-striped">
    		<tbody>
    			<tr><td>id</td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td><td>Доступные операции</td></tr>
    			<c:forEach var="record" items="${cert_employers}">
    		    	<tr>
    						<td><c:out value="${record.id}"></c:out></td>
        					<td><c:out value="${record.fio}"></c:out></td>
        					<td><c:out value="${record.department}"></c:out></td>
        					<td><c:out value="${record.country}"></c:out></td>
        					<td><c:out value="${record.region}"></c:out></td>
        					<td><c:out value="${record.city}"></c:out></td>
        					<td><a href="#" id='${record.id}' onClick="popPersonFromCertification(event);">Удалить</a></td>
    		   		</tr>      
    			</c:forEach>
	    	</tbody>
	    </table>