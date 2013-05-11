<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<div class="well sidebar-nav">
	<ul class="nav nav-list">
    	<li class="nav-header">Меню</li>
        <li><a href="${pageContext.request.contextPath}/candidate/accounting.html">Мои мероприятия</a></li>
        <li><a href="${pageContext.request.contextPath}/candidate/messages.html">Мои сообщения</a></li>
        <li><a href="${pageContext.request.contextPath}/candidate/kontakts.html">Мои контактные данные</a></li>
        <li><a href="${pageContext.request.contextPath}/candidate/feedback.html">Связь с менеджером</a></li>
        <li><a href="${pageContext.request.contextPath}/logout.html">Выход</a></li>
    </ul>
</div>