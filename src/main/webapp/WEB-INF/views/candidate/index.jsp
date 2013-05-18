<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../default/header.jsp" />

<body>
<div align="right">Пользоватль: ${nameOfPerson} [<a href="${pageContext.request.contextPath}/logout.html">Выход</a>]</div> 
<center><h1>Личный кабинет</h1></center>
<br/>
<table class="table table-striped">
	<tbody>
    	<tr><td/><td>Наименование</td><td>Доступная операция</td></tr>
    	<c:forEach var="record" items="${cert}">
    	<tr>
        	<td/>
        	<td><c:out value="${record.getName()}"></c:out></td>
        	<td><a href="${pageContext.request.contextPath}/candidate/certification/proxy.html?id=${record.getId()}">Начать тестирование</a></td>
    	</tr>      
    	</c:forEach>
	</tbody>	
</table>
<!-- 
<div class="container-fluid">
	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="menu.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;background-color:#FFDDDD;">
    <center>Личный кабинет</center>
    </div>
    </div>
</div>
 -->
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
<jsp:include page="../default/footer.jsp" />