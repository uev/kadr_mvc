<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	    <div class="pagination">
                <ul>
                <li><a href="#">Начало</a></li>
                <c:forEach var="item" items='${paginnav}'>
                        <li><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/list.html?id=${Questions.get(1).fk_catgory.id}&page=${item}">${item}</a></li>
                </c:forEach>
                <li><a href="#">Конец</a></li>
                </ul>
        </div>
	 
	 
	 
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Заголовок вопроса</td><td>Доступная операция</td></tr>
	    		<c:forEach var="record" items='${Questions}'>
        			<tr>
        				<td><c:out value="${record.id}"></c:out></td>
        				<td onClick="getQuestionInfo(event);"><c:out value="${record.name}"></c:out></td>
        				<td><a href='#' id="${record.id}" onClick="popQuestion(event);">Удалить</a><br/>
        					<a href='${pageContext.request.contextPath}/admin/dictionary/knowledges/Questions/edit.html?id=${record.id}'>Редактировать</a>
        				</td>
					</tr>
				</c:forEach>
	    	</tbody>
	    </table>