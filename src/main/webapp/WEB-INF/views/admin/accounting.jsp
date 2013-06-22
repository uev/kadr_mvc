<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../default/header.jsp" />
    <script>
    function popAccount(event) {
    	var hname = "${pageContext.request.contextPath}/admin/unbindlogin.html";
    	var hash = "dcd95bcb84b09897b2b66d4684c040da";
    	var json={'hash' : hash, 'id' : $(event.target).attr("id")};
    	var jqxhr = $.post(hname,json, function() {
    	})
    		.success(function(data) {		
    			if (data['error'] == 1) {
    				alert("Не удалось удалить аккаунт");
    			}
    			if(data['error'] == 0) {
    				alert("Аккаунт удалён");
    				location.reload();
    			}
    			location.reload();
    		});
    	return 0;
    }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/default/navbar.jsp" />
<div class="container">
<div class ="hatsite"></div>
<center><h1>${title}</h1></center>
	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="menu.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;">
<!-- UserManagment menu -->
    <ul class="breadcrumb">
    <!--  <li><a href="admin.html?accounting&listacc">Просмотр пользователей</a> <span class="divider">/</span></li> -->
    <li><a href="bindlogin.html">Привязка пользователя к аккаунту</a> <span class="divider">/</span></li>
    </ul>

    <!-- List users  -->
    <c:if test="${json != null}">
    	<table class="table table-striped">
    		<tbody>
    			<tr><td></td><td>Логин</td><td>ФИО</td><td>Подразделение</td><td>Роль</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td><td>Доступные операции</td></tr>
    			<c:forEach var="record" items="${json}">
    		    	<tr>
    						<td></td>
    						<td><c:out value="${record.username}"></c:out></td>
        					<td><c:out value="${record.fio}"></c:out></td>
        					<td><c:out value="${record.department}"></c:out></td>
        					<td><c:out value="${record.role}"></c:out></td>
        					<td><c:out value="${record.country}"></c:out></td>
        					<td><c:out value="${record.region}"></c:out></td>
        					<td><c:out value="${record.city}"></c:out></td>
        					<td><a href="#" id="${record.id}" onClick="popAccount(event)">Удаление аккаунта</a></td>
    		   		</tr>      
    			</c:forEach>
	    	</tbody>
	    </table>
	</c:if>
	<center>
	

    <!-- Unbinding login  -->
    
    <c:if test="${rmusers != null}">
    	<form action="${form_unbind}" method="post">
            <fieldset>
              <div class="clearfix">
                <input type="text" placeholder="Имя пользователя" name="login">
              </div>
              <button class="btn btn-large btn-primary" type="submit">Удалить</button>
            </fieldset>
          </form>
          <c:choose>
  			<c:when test="${error == 1}">
  			    <div class="alert alert-error">Ошибка. Возиожно пользователь не существует</div>
  			</c:when>
  			<c:when test="${error == 0}">
    			<div class="alert alert-success">Пользователь успешно удалён</div>
  			</c:when>
		  </c:choose>
    </c:if>
    </center>
    </div>
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>

<jsp:include page="../default/footer.jsp" />