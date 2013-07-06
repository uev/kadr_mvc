<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <td width="50"onClick="getTestInfo(event)"><c:out value="${record}"></c:out></td>  -->
<jsp:include page="menu.jsp" />
	<table class="table table-striped">
    		<tbody>
	    		<tr><td>id</td><td>Дата</td><td>Заголовок аттестации</td><td>Аттестуемый</td><td>Доступная операция</td></tr>
	    		<c:if test="${cert != null}">
	    		<c:forEach var="record" items='${cert}'>
        			<tr>
        				<td width="20"><c:out value="${record.id}"></c:out></td>
        				<td width="50"><c:out value="${record.datecomplete}"></c:out></td>
        				<td width="50"><c:out value="${record.fk_certification.name}"></c:out></td>
        				<td width="50"><c:out value="${record.fk_employe.fio}"></c:out></td>
        				<td width="20"><a href="${pageContext.request.contextPath}/admin/certification/view.html?id=${record.id}">Просмотр результатов</a>
        				</td>
					</tr>
					<div id="${record.id}"></div>
				</c:forEach>
				</c:if>
	    	</tbody>
	</table>