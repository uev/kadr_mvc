<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<body>
<center><h1>Админзона</h1></center>
    
<div class="container-fluid">
	<div class="row-fluid" >
    	<div class="span2">
    		<div class="well sidebar-nav">
           		<ul class="nav nav-list">
              		<li class="nav-header">Меню</li>
              		<li><a href="admin.html?accounting">Управление пользователями</a></li>
              		<li><a href="report_patterns.html">Управление шаблонами</a></li>
              		<li><a href="login.html?logout=1">Выход</a></li>
            	</ul>
    		</div>
    	</div>
    <div class="span10" style="margin: 5 auto;background-color:#FFDDDD;">
    
    
    <center>Управление пользователями</center>
    <li><a href="admin.html?accounting&listacc">Управление пользователями</a></li>

    </div>
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>

<jsp:include page="footer.jsp" />