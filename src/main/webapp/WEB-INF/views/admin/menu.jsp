<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/WEB-INF/views/default/header.jsp" />
    
<div class="well sidebar-nav">
	<ul class="nav nav-list">
    	<li class="nav-header">Меню</li>
        <li><a href="admin/accounting.html">Управление логинами</a></li>
        <li><a href="report_patterns.html">Управление шаблонами</a></li>
        <li><a href="admin/dictionary/index.html">Справочники</a></li>
        <li><a href="<c:url value="logout.html"/>">Выход</a></li>
    </ul>
</div>