<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
<br/>
	<!-- 
	<form 
  		class="form-horizontal span10">
  		 -->
			<input type="text" placeholder="Название вопроса" name="queshion" id="inputQueshion" class="span10"/>
		<br/>
		<br/>
			<textarea rows="5" placeholder="Содержание вопроса" class="span10"></textarea>
        <br/>
		<br/>
	<div class="controls controls-row">
         		<button class="btn btn-small btn-primary " type="button" onclick="insertAnswer();">Создать</button>
         		<button class="btn btn-small btn-primary " type="button" onclick="insertAnswer();">Добавить вариант ответа</button>
         		</div>
	<hr/>
	<div id="answer" class="span10">
		<!--  <input type="text"  class="span10" placeholder="Вариант ответа" name="queshion" id="inputAnswer"> -->
	</div>