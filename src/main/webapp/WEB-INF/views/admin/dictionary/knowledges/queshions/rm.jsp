<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form 
  		class="form-inline">
         		<input type="text" placeholder="Название категории" name="queshion" id="inputQueshion"  class="span11">
         		&nbsp&nbsp&nbsp&nbsp&nbsp
         		<button class="btn btn-small btn-primary offset0" type="button" onclick="popQueshion();">Удалить</button>
	<br>
	<br>
	<table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>Заголовок вопроса</td></tr>
	    		<c:forEach var="record" items='${queshions}'>
        			<tr>
        				<td width="10"><input type="radio" value="${record.name}" name="radiogroup" onClick="getQueshionItem()"></td>
        				<td width="20"><c:out value="${record.id}"></c:out></td>
        				<td onClick="getQueshionInfo(event)"><c:out value="${record.name}"></c:out></td>
					</tr>
					<div id="${record.id}"></div>
				</c:forEach>
	    	</tbody>
	</table>
	</form>
	
	
	<div rel="popover" data-content="It's so simple to create a tooltop for my website!" data-original-title="Twitter Bootstrap Popover" data-placement="top" />
	  
	
	