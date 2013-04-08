<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/default/header.jsp" />
</head>
<body>
<center><h1>${title}</h1></center>
<div class="container-fluid">
	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="../../menu.jsp" />
    	</div>
    	<div class="span10" style="margin: 5 auto;">
			<!-- UserManagment menu -->
    		<jsp:include page="menu.jsp" />
    	</div>
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
<jsp:include page="/WEB-INF/views/default/header.jsp" />