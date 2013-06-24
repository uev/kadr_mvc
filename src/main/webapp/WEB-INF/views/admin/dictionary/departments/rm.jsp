<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Название департамента</td><td>Доступная операция</td></tr>
	    		<c:forEach var="record" items='${departments}'>
        			<tr>
        				<td><c:out value="${record.id}"></c:out></td>
        				<td><c:out value="${record.name}"></c:out></td>
        				<td><a href='#' id="${record.name}" onClick="popDep(event);">Удалить</a></td>
					</tr>
				</c:forEach>
	    	</tbody>
	    </table>
