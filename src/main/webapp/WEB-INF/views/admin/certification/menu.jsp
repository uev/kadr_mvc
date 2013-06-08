<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/WEB-INF/views/default/header.jsp" />
    
	<ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/admin/certification/manage.html">Создание аттестации</a><span class="divider">/</span></li>
        <li><a href="${pageContext.request.contextPath}/admin/certification/res.html">Результаты аттестации</a><span class="divider">/</span></li>
    </ul>