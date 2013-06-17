<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <!-- 
	<ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/admin/dictionary/persons/index.html">Персонал</a><span class="divider">/</span></li>
        <li><a href="${pageContext.request.contextPath}/admin/dictionary/departments/index.html">Депертаменты</a><span class="divider">/</span></li>
        <li><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/index.html">База вопросов</a></li>
    </ul>
     -->
     <ul class="breadcrumb" role="navigation">
         <li class="dropdown">
			<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Персонал <b class="caret"></b></a>         
         		<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
            		<li role="presentation"><a  role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/persons/add.html">Создание пользователя</a></li>
    				<li role="presentation" class="divider"></li>
    				<li role="presentation"><a  role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/persons/rm.html">Удаление пользователя</a></li>
    				<li role="presentation" class="divider"></li>
    				<li role="presentation"><a  role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/persons/bind_dep.html">Добавление пользователя в подразделение</a></li>
         		</ul>
         </li>
         
         <span class="divider">  /  </span>
         
         <li class="dropdown">
         	<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Департаменты <b class="caret"></b></a> 
            	<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
         			<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/departments/add.html">Добавить департамент</a></li>
            		<li role="presentation" class="divider"></li>
            		<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/departments/rm.html">Удалить департамент</a></li>
         		</ul>
         </li>
         <span class="divider">  /  </span>
         <li><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/index.html">База вопросов</a></li>
         
    </ul>    
    