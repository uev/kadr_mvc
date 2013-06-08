<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <td width="50"onClick="getTestInfo(event)"><c:out value="${record}"></c:out></td>  -->
<jsp:include page="menu.jsp" />
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Вопрос</td><td>Ответ</td><td>Результат</td></tr>
	    		<c:forEach var="record" items='${cert}'>
        			<tr>
        				<td width="20"><c:out value="${record['answer']}"></c:out></td>
					</tr>
				</c:forEach>
	    	</tbody>
	</table>