<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<body>
<c:if test="${title != null}">
	<center><h1>${title}</h1></center>
</c:if>
<div class="container-fluid">
	<div class="row-fluid" >
    	<c:if test="${menu != null}">
    		<div class="span2">
    			<jsp:include page="${menu}" />
    		</div>
    	</c:if>
    	<div class="span10" style="margin: 5 auto;">
    	
    		<jsp:include page="${body}" />
    	
    </div>
    </div>
</div>
<script src="<s:url value="/resources/bootstrap" />/js/bootstrap.js"></script>
<script src="<s:url value="/resources/bootstrap" />/js/bootstrap.min.js"></script>
<script src="<s:url value="/resources/bootstrap" />/assets/js/bootstrap-dropdown.js"></script>
<!-- <script src="<s:url value="/resources/bootstrap" />/assets/js/bootstrap-popover.js"></script> -->
</body>
<jsp:include page="footer.jsp" />