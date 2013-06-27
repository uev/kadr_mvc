<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="menu.jsp" />
	<form 
  		class="form-inline">
         		<input type="text" placeholder="Заголовок аттестации" name="cert" id="inputCert"  class="span10">
         		
         		<input class="btn btn-primary btn-custom-login offset0" type="button" value="	Добавить		" onclick="appendCert();" />
         		
	<br>
	<br>
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Заголовок аттестации</td><td>Доступная операция</td></tr>
	    		<c:forEach var="record" items='${cert}'>
        			<tr>
        				<td width="20"><c:out value="${record.id}"></c:out></td>
        				<td width="50"onClick="getTestInfo(event)"><c:out value="${record.name}"></c:out></td>
        				<td width="20"><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/pop_queshion.html?id=${record.id}" id="${record.id}">Удаление аттестации</a>
        				<br/><a href="${pageContext.request.contextPath}/admin/certification/edit.html?id=${record.id}" id="${record.id}">Редактирование аттестации</a>
        				</td>
					</tr>
					<div id="${record.id}"></div>
				</c:forEach>
	    	</tbody>
	</table>
	</form>