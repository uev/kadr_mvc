<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Заголовок вопроса</td><td>Доступная операция</td></tr>
	    		<c:forEach var="record" items='${Questions}'>
        			<tr>
        				<td width="20"><c:out value="${record.id}"></c:out></td>
        				<td width="50"onClick="getQuestionInfo(event)"><c:out value="${record.name}"></c:out></td>
        				<td width="20"><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/Questions/edit.html?id=${record.id}">Редактирование</a> / 
        				<a href="#" id="${record.id}" onClick="popQuestion(event);">Удаление</a>
        				</td>
					</tr>
					<div id="${record.id}"></div>
				</c:forEach>
	    	</tbody>
	</table>	
	<div rel="popover" data-content="It's so simple to create a tooltop for my website!" data-original-title="Twitter Bootstrap Popover" data-placement="top" />