<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<div class="pagination">
    	<ul>
        	<li><a href="#">Начало</a></li>
                <c:forEach var="item" items='${paginnav}'>
                        <li><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/append_Question.html?id=${pageContext.request.getParameter('id')}&page=${item}">${item}</a></li>
                </c:forEach>
             <li><a href="#">Конец</a></li>
          </ul>
     </div>
	<form 
  		class="form-inline">
	<table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>Название вопроса</td><td>Категория</td></tr>
	    		<c:set var="chk" value="1"/>
	    		<c:forEach var="record" items='${Questions}'>
        			<tr id="q${chk}" class="Question">
        				<td width="1"><input type="checkbox" value="" name="inputCheckQuestion"></td>
        				<td width="5" class="id_Question"><c:out value="${record.id}"></c:out></td>
        				<td width="50" onClick="getQuestionInfo(event)"><c:out value="${record.name}"></c:out></td>
        				<td width="50"><c:out value="${record.getFk_catgory().getCname()}"></c:out></td>
					</tr>
					<c:set var="chk" value="${chk+1}"/>
				</c:forEach>
	    	</tbody>
	    </table>
	    <input class="btn btn-primary btn-custom-login offset0" type="button" value="       Добавить       " onclick="appendQuestionToTest(event);" /> 
	</form>