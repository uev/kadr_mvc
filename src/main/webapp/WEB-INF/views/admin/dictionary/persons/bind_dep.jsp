<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
<br/>
	<form class="form-horizontal span10 offset3">
         		<div class="control-group">
         			<label class="control-label" for="inputPerson">Имя пользователя</label>
    	 			<div class="clearfix  controls">
         				<input type="text" placeholder="Имя пользователя" name="person" id="inputPerson">
         			</div>
         		</div>
         		
         		<div class="control-group">
         			<label class="control-label" for="selectDepartment">Подразделение</label>
         			<div class="clearfix controls">
         				<select  placeholder="Подразделение" name="city" id="selectDepartment">
							<option></option>
							<c:forEach var="i" items="${departmentList}">
								<option>${i.name}</option>
						</c:forEach>
			  			</select>
           	 		</div>
           	 		           	 		      	
           	 	</div>
    
    <input class="btn btn-primary btn-custom-login offset4" type="button" value="  Закрепить  " onclick="bindDep();" />
   
    <br/>
    </form>
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
	