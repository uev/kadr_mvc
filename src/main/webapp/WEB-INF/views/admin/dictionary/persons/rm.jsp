<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	    <table class="table table-striped">
    		<tbody>
    			<tr><td></td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td><td>Доступные операции</td></tr>
    			<c:forEach var="record" items="${employers}">
    		    	<tr>
    						<td></td>
        					<td><c:out value="${record.fio}"></c:out></td>
        					<td><c:out value="${record.department}"></c:out></td>
        					<td><c:out value="${record.country}"></c:out></td>
        					<td><c:out value="${record.region}"></c:out></td>
        					<td><c:out value="${record.city}"></c:out></td>
        					<td><a href="#" id="${record.id}" onClick="popEmploye(event)">Удалить</a></td>
    		   		</tr>      
    			</c:forEach>
	    	</tbody>
	    </table>