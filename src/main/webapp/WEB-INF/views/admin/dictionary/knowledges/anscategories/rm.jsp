<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Название Категории</td><td>Доступная операция</td></tr>
	    		<c:forEach var="record" items='${categories}'>
        			<tr>
        				<td><c:out value="${record.id}"></c:out></td>
        				<td><c:out value="${record.cname}"></c:out></td>
        				<td><a href='#' id="${record.cname}" onClick="popCategory(event);">Удалить</a><br/>
        					<a href='${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/list.html?id=${record.id}'>Просмотреть</a>
        				</td>
					</tr>
				</c:forEach>
	    	</tbody>
	    </table>