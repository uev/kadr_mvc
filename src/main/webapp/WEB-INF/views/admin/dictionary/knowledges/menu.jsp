<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- 
	<ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/index.html">Категории вопросов</a> <span class="divider">/</span>  </li>
        <li><a href="${pageContext.request.contextPath}/admin/dictionary/knowledges/answers/index.html">Вопросы</a></li>
    </ul>
     -->
    <ul class="breadcrumb" role="navigation">
         <li class="dropdown">
			<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Категории вопросов <b class="caret"></b></a>         
         		<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
            		<li role="presentation"><a  role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/add.html">Создание категории</a></li>
    				<li role="presentation" class="divider"></li>
    				<li role="presentation"><a  role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/rm.html">Управление категориями</a></li>
         		</ul>
         <li>
         
         <span class="divider">  /  </span>
         
         <li class="dropdown">
         	<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Вопросы <b class="caret"></b></a> 
            	<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
         			<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/add.html">Добавить вопрос</a></li>
            		<li role="presentation" class="divider"></li>
            		<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/rm.html">Управление вопросами</a></li>
         		</ul>
         </li>
         
         <span class="divider">  /  </span>
         
         <li class="dropdown">
         	<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Тесты <b class="caret"></b></a> 
            	<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
         			<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/manage.html">Управление тестами</a></li>
            		<li role="presentation" class="divider"></li>
         		</ul>
         </li>
    </ul>    
        <!-- 
    	<li class="dropdown">
                      <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>

                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://google.com">Action</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#anotherAction">Another action</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
                      </ul>

                    </li>
    	 -->
    
    
    
    
    <!-- 
    
              <div class="bs-docs-example">
            <div id="navbar-example" class="navbar navbar-static">
              <div class="navbar-inner">
                <div class="container" style="width: auto;">
                  <a class="brand" href="#">Project Name</a>
                  
                  <ul class="nav" role="navigation">
                    <li class="dropdown">
                      <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>

                      <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="http://google.com">Action</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#anotherAction">Another action</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
                      </ul>

                    </li>
                  </ul>
                </div>
              </div>
            </div> 
          </div> 
    
    -->
    
    