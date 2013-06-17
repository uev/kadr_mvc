<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../default/header.jsp" />

<body>

<jsp:include page="/WEB-INF/views/default/navbar.jsp" />
<div class="container">
	<div class ="hatsite"><br/></div>
	<center><h1>Админзона</h1></center>
	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="menu.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;">
    
    Краткий функцинал системы: <br>
    <ul>
    <li>Управление пользователями. Пользователи закрепляются за департаментами с указанием региона проживания;</li>
    <li>Ролевая система управлени доступом. Роль пользователя из функционала позволяет просматривать назначенные события и учавствовать в них
    Один пользователь может иметь N аккаунтом, соответственно и ролей в системе
    </li>
    <li>Управление базой знаний. Создание\удаление категорий вопросов. Вопросы не могут входить в N категорий.</li>
    <li>Управление аттестациями. Аттестации состоят из вопросов с предопределёнными ответами. В одной аттестации может быть N участников</li>
    <li>Просмотр результатов завершенных аттестаций</li>
    </ul>
    
    </div>
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>

</body>

<jsp:include page="../default/footer.jsp" />