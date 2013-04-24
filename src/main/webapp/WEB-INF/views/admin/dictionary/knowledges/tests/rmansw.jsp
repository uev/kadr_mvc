<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form 
  		class="form-inline">
  				Тест: 	"${testname}"
         		<button class="btn btn-small btn-primary offset0" type="button" onclick="popQueshionFromTest(event);">Удалить</button>
	<br>
	<br>
	<table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>Название вопроса</td><td>Категория</td></tr>
	    		<c:set var="chk" value="1"/>
	    		<c:forEach var="record" items='${queshions}'>
        			<tr id="q${chk}" class="queshion">
        				<td width="1"><input type="checkbox" value="" name="inputCheckQueshion"></td>
        				<td width="5" class="id_queshion"><c:out value="${record.id}"></c:out></td>
        				<td width="50" onClick="getQueshionInfo(event)"><c:out value="${record.name}"></c:out></td>
        				<td width="50"><c:out value="${record.getFk_catgory().getCname()}"></c:out></td>
					</tr>
					<c:set var="chk" value="${chk+1}"/>
				</c:forEach>
	    	</tbody>
	    </table>
	</form>