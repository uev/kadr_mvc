<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="ru">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- <base href="${pageContext.request.contextPath}"> -->  
	<!-- Bootstrap -->
	<!--  <link href="<c:url value="bootstrap/css/bootstrap.min.css" />" rel="stylesheet" media="screen">  -->
	
	<link href="<s:url value="/resources/bootstrap" />/css/bootstrap.css"
              rel="stylesheet"
              media="screen" />  
	<!--  <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen"> -->
	<link href="<s:url value="/resources/bootstrap" />/css/bootstrap-responsive.css" rel="stylesheet"/>
	
	<!-- JQuery -->
<script src="<s:url value="/resources/scripts" />/jquery/jquery-1.9.1.min.js"></script>
<script src="<s:url value="/resources/bootstrap" />/js/bootstrap.min.js"></script>
<script src="<s:url value="/resources/bootstrap" />/js/bootstrap.js"></script>
<!-- 
<script src="<s:url value="/resources/lightview" />/js/swfobject.js"></script>
<script src="<s:url value="/resources/lightview" />/js/spinners/spinners.min.js"></script>
<script src="<s:url value="/resources/lightview" />/js/lightview/lightview.js"></script>
<link href="<s:url value="/resources/lightview/css/lightview" />/lightview.css" rel="stylesheet">
 -->
 <script src="<s:url value="/resources/fancybox" />/js/bootstrap.js"></script>
 
 
 <!-- Add fancyBox -->
  <script  type="text/javascript" src="<s:url value="/resources/fancybox" />/jquery.mousewheel-3.0.6.pack.js"></script>
 <link href="<s:url value="/resources/fancybox" />/jquery.fancybox.css?v=2.1.4" rel="stylesheet" media="screen"/>  
 <script  type="text/javascript" src="<s:url value="/resources/fancybox" />/jquery.fancybox.pack.js?v=2.1.4"></script>
 <!-- Helpers fancyBox  -->
 <link href="<s:url value="/resources/fancybox/helpers" />/jquery.fancybox-buttons.css?v=1.0.5" rel="stylesheet" media="screen"/>
 <script  type="text/javascript" src="<s:url value="/resources/fancybox/helpers" />/jquery.fancybox-buttons.js?v=1.0.5"></script>
 <script  type="text/javascript" src="<s:url value="/resources/fancybox/helpers" />/jquery.fancybox-media.js?v=1.0.5"></script>
 <link href="<s:url value="/resources/fancybox/helpers" />/jquery.fancybox-thumbs.css?v=1.0.7" rel="stylesheet" media="screen" />
 <script  type="text/javascript" src="<s:url value="/resources/fancybox/helpers" />/jquery.fancybox-thumbs.js?v=1.0.7"></script>
 <script>
 $(document).ready(function() {
		$(".various").fancybox({
			maxWidth	: 1024,
			maxHeight	: 768,
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none'
		});
	});
 </script>
<c:if test="${hscript != null}">
	<jsp:include page="${hscript}" />
</c:if>
<title>Справочно-аттестационный комплекс "Кадры для делопроизводственных служб"</title>
</head>