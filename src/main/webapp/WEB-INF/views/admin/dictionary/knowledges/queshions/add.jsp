<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form class="form-inline">
		<label>Заголовок вопроса&nbsp&nbsp&nbsp&nbsp</label>
		<input type="text" placeholder="Название вопроса" name="queshion" id="inputQueshion" class="span10"/>
		<br/>
		<br/>
		<label>Содержание вопроса</label>
		<textarea rows="5" placeholder="Содержание вопроса" class="span10" id="content"></textarea>
		<br/>
		<br/>
		<div class="clearfix controls">
			<label>Категория вопроса&nbsp&nbsp&nbsp&nbsp</label>
            	<select class="span10"  placeholder="Страна" name="country" id="selectCategory">
            		<option></option>
            		<c:forEach var="record" items="${categories}">
    		    		<option><c:out value="${record.cname}"></c:out></option>
    				</c:forEach>
			  	</select>
           	 </div>      
	</form>
    <br/>
	<br/>
	<div class="controls controls-row">
    	<button class="btn btn-small btn-primary " type="button" onclick="appendQueshion();">Создать</button>
        <button class="btn btn-small btn-primary " type="button" onclick="appendAnswerLayout();">Добавить вариант ответа</button>
    </div>
	<hr/>
	<div id="answer" class="span10">
		<!--  <input type="text"  class="span10" placeholder="Вариант ответа" name="queshion" id="inputAnswer"> -->
	</div>