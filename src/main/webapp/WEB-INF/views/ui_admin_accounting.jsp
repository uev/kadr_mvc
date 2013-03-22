<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
</head>
<body>
<center><h1>Админзона</h1></center>
    
<div class="container-fluid">

	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="menu_admin.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;">
<!-- UserManagment menu -->
    <ul class="breadcrumb">
    <li><a href="admin.html?accounting&listacc">Просмотр пользователей</a> <span class="divider">/</span></li>
    <li><a href="admin.html?accounting&createuser">Создание пользователя</a> <span class="divider">/</span></li>
    <li><a href="#">Удаление пользователя</a> <span class="divider">/</span></li>
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
	<c:if test="${createuser != null}">
    	 <center>
    	 <form action="login.html" method="post">
            <fieldset>
              <div class="clearfix">
                <input type="text" placeholder="Имя пользователя" name="login">
              </div>
              <div class="clearfix">
                <input type="password" placeholder="Пароль" name="password">
              </div>
             <div class="clearfix">
              <select>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
			</select>
            </div>
              <button class="btn btn-large btn-primary" type="submit">Создать</button>
            </fieldset>
          </form>
          </center>
	</c:if>
    </div>
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>

<jsp:include page="footer.jsp" />