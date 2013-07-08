<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.springframework.jca.cci.core.RecordCreator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../default/header.jsp" />

<body>
 	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				  	<a
						class="brand pull-left" href="#">Вы вошли как: ${nameOfPerson}</a>
				<div class="btn-group pull-right">
					
				</div>
			</div>
		</div>
	</div>
	<hr visible="0">    
<div class="container">
<div class="hatsite">
</div>
<c:set var="chk" value="0"/>
<table class="table">
	<TBODY>
	<c:forEach var="record" items="${answers}">
		<c:set var="chk" value="${chk+1}"/>
		<tr class="item well well-large" id="${record.value.get(0).fk_queshion.id}"><td/><td>${chk}. ${record.key}</td></tr>
		<c:forEach var="ans" items="${record.value}">
			<tr class="item" id="${ans.id}"><td><input type="checkbox" value="" name="inputCheckAnsw"></td><td>${ans.content}</td></tr>
		</c:forEach>
	</c:forEach>
	</TBODY>
</table>
<center>
	<button class="btn btn-primary btn-custom-login offset0" type="button" onclick="pushAnswers(event);">Ответить</button>
</center>
<!-- 
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
 -->
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