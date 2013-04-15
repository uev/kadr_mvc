<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form 
  		class="form-horizontal span6">
         		<input type="text" placeholder="Название категории" name="category" id="inputCategory">
         		<button class="btn btn-small btn-primary offset0" type="button" onclick="popCategory();">Удалить</button>
	<br>
	<br>
	<table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>Название Категории</td></tr>
	    		<c:forEach var="record" items='${categories}'>
        			<tr>
        				<td><input type="radio" value=${record.cname} name="radiogroup" onClick="getCategoryItem()"></td>
        				<td><c:out value="${record.id}"></c:out></td>
        				<td><c:out value="${record.cname}"></c:out></td>
					</tr>
				</c:forEach>
	    	</tbody>
	    </table>
	</form>