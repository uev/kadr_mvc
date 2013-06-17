<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<div class="well sidebar-nav">
	<ul class="nav nav-list">
    	<li class="nav-header">Меню</li>
        <li><a href="${pageContext.request.contextPath}/admin/accounting.html">Управление логинами</a></li>
        <li><a href="${pageContext.request.contextPath}/report_patterns.html">Управление шаблонами</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/dictionary/index.html">Справочники</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/certification/index.html">Аттестация</a></li>
        <!-- <li><a href="${pageContext.request.contextPath}/logout.html">Выход</a></li>  -->
        <!--<li><a href="<c:url value="logout.html"/>">Выход</a></li>  -->
    </ul>
</div>