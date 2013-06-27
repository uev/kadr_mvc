<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form 
  		class="form-inline">
         		<input type="text" placeholder="Название теста" name="test" id="inputTest"  class="span10">
         		
         		<input class="btn btn-primary btn-custom-login offset0" type="button" value="       Добавить       " onclick="appendTest();" />
	<br>
	<br>
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Заголовок теста</td><td>Доступная операция</td></tr>
	    		<c:forEach var="record" items='${tests}'>
        			<tr>
        				<td width="20"><c:out value="${record.id}"></c:out></td>
        				<td width="50"onClick="getTestInfo(event)"><c:out value="${record.name}"></c:out></td>
        				<td width="20"><a href="#" id="${record.id}" onClick="popTest(event)">Удаление теста</a> /
        				<a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/pop_queshion.html?id=${record.id}" id="${record.id}">Удаление вопросов</a>
        				/ <a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/append_queshion.html?id=${record.id}" id="${record.id}" onClick="AnswerToTest(event)">Добавление вопросов</a>
        				</td>
					</tr>
					<div id="${record.id}"></div>
				</c:forEach>
	    	</tbody>
	</table>
	</form>