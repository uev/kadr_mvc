<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../default/header.jsp" />
    <c:if test="${allemp != null}">
    	<script>
    	
    	
    	</script>
    </c:if>

</head>
<body onLoad="init();">
<center><h1>${title}</h1></center>
<div class="container-fluid">

	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="menu.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;">
<!-- UserManagment menu -->
    <ul class="breadcrumb">
    <!--  <li><a href="admin.html?accounting&listacc">Просмотр пользователей</a> <span class="divider">/</span></li> -->
    <li><a href="bindlogin.html">Привязка пользователя к аккаунту</a> <span class="divider">/</span></li>
    <li><a href="unbindlogin.html">Удаление аккаунта</a> <span class="divider">/</span></li>
    </ul>

    <!-- List users  -->
    <c:if test="${json != null}">
    	<table class="table table-striped">
    		<tbody>
    			<c:forEach var="i" items="${json}">
    		    	<tr>
    					<td>${i.key}</td>
    					<td>${i.value[0]}</td>
    		   		</tr>
    		   		<!-- <c:out value="${json.get(i)}"/> -->      
    			</c:forEach>
	    	</tbody>
	    </table>
	</c:if>
	<center>
	
	<!-- Binding login  -->
	<c:if test="${roles != null}">
    	 
    	 <form action="${form_bind}" method="post">
            <fieldset>
              <div class="clearfix">
                <input type="text" placeholder="Логин" name="login">
              </div>
              <div class="clearfix">
                <input type="password" placeholder="Пароль" name="password">
              </div>
             <div class="clearfix">
              <select  placeholder="Роль" name="role">
				<option></option>
				<c:forEach var="i" items="${roles}">
					<option>${i[1]}</option>
				</c:forEach>
			</select>
            </div>
              <button class="btn btn-large btn-primary" type="submit">Создать</button>
            
            
          <c:choose>
  			<c:when test="${error == 1}">
  			    <div class="alert alert-error">Пользователь существует или на сервере проблемы</div>
  			</c:when>
  			<c:when test="${error == 0}">
    			<div class="alert alert-success">Пользователь успешно добавлен</div>
  			</c:when>
		  </c:choose>
		  <table class="table table-striped">
    		<tbody>
	    		<tr><td> </td><td>id</td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td></tr>
	    		<c:forEach var="record" items='${allemp}'>
        			<tr>
        				<td><input type="radio" value=${record.id} name="radiogroup"></td>
        				<td><c:out value="${record.id}"></c:out></td>
        				<td><c:out value="${record.fio}"></c:out></td>
        				<td><c:out value="${record.department}"></c:out></td>
        				<td><c:out value="${record.country}"></c:out></td>
        				<td><c:out value="${record.region}"></c:out></td>
        				<td><c:out value="${record.city}"></c:out></td>
					</tr>
				</c:forEach>
	    	</tbody>
	    </table>
	    </fieldset>
          </form>
	</c:if>
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