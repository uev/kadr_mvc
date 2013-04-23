<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form 
  		class="form-horizontal span6">
  				К тесту с названием 	"${testname}"
         		<button class="btn btn-small btn-primary offset0" type="button" onclick="popQueshionFromTest();">Добавить</button>
	<br>
	<br>
	<table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>Название вопроса</td><td>Категория</td></tr>
	    		<c:forEach var="record" items='${queshions}'>
        			<tr>
        				<td><input type="checkbox" value="" name="inputCheckQueshion"></td>
        				<td><c:out value="${record.id}"></c:out></td>
        				<td><c:out value="${record.name}"></c:out></td>
        				<td><c:out value="${record.getFk_catgory().getCname()}"></c:out></td>
					</tr>
				</c:forEach>
	    	</tbody>
	    </table>
	</form>