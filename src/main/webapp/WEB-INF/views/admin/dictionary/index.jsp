<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/default/header.jsp" />
</head>
<body>
<center><h1>Админзона</h1></center>
<div class="container-fluid">

	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="../menu.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;">
<!-- UserManagment menu -->
    <ul class="breadcrumb">
    <li><a href="addemploye.html">Создание пользователя</a> <span class="divider">/</span></li>
    <li><a href="rmemploye.html">Удаление пользователя</a> <span class="divider">/</span></li>
    </ul>
    
    <!-- List users  -->
    <c:if test="${json != null}">
    	<table class="table table-striped">
    		<tbody>
    			<c:forEach var="i" items="${json}">
    		    	<tr>
    					<td>${i.key}</td>
    					<td>${i.value[0]}</td>
    		   		</tr>
    		   		<!-- <c:out value="${json.get(i)}"/> -->      
    			</c:forEach>
	    	</tbody>
	    </table>
	</c:if>
	<center>
	
	<c:if test="${employe != null}">
		<form action="admin.html?accounting&createuser" method="post">
		      <div class="clearfix">
                <input type="text" placeholder="Имя пользователя" name="person">
              </div>
              <div class="clearfix">
              <select  placeholder="Роль" name="role">
				<option></option>
				<c:forEach var="i" items="${roles}">
					<option>${i[1]}</option>
				</c:forEach>
			</select>
            </div>
             <button class="btn btn-large btn-primary" type="submit">Создать</button>
    
		</form>
	</c:if>


    </center>
    </div>
    
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>

<jsp:include page="/WEB-INF/views/default/header.jsp" />