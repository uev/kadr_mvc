<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" />
	<form class="form-inline">
		<label>Заголовок вопроса&nbsp&nbsp&nbsp&nbsp</label>
		<input type="text" placeholder="Название вопроса" name="Question" id="inputQuestion" class="span10" value="${Question.getName()}"/>
		<br/>
		<br/>
		<label>Содержание вопроса</label>
		<textarea rows="5" placeholder="Содержание вопроса" class="span10" id="content">${Question.getContent()}</textarea>
		<br/>
		<br/>
		<div class="clearfix controls">
			<label>Категория вопроса&nbsp&nbsp&nbsp&nbsp</label>
            	<select class="span10"  placeholder="Категория" name="country" id="selectCategory">
            		<option></option>
            		<c:forEach var="record" items="${categories}">
    		    		<c:if test="${record.id == Question.getFk_catgory().getId()}">
    		    			<option selected><c:out value="${record.cname}"></c:out></option>
    		    		</c:if>
    		    		<c:if test="${record.id != Question.getFk_catgory().getId()}">
    		    			<option><c:out value="${record.cname}"></c:out></option>
    		    		</c:if>	
    				</c:forEach>
			  	</select>
           	 </div>      
	</form>
    <br/>
	<br/>
	<div class="controls controls-row">
    	<button class="btn btn-primary btn-custom-login" type="button" onclick="updateQuestion(${Question.getId()});">Обновить</button>
        <button class="btn btn-primary btn-custom-login" type="button" onclick="appendAnswerLayout();">Добавить вариант ответа</button>
    </div>
	<hr/>
	<div id="answer" class="span10">
		<!--  <input type="text"  class="span10" placeholder="Вариант ответа" name="Question" id="inputAnswer"> -->
	 	<c:set var="chk" value="1"/> 
	 	<c:forEach var="record" items="${answers}">
    		<div class = "form-inline" id="inAns${chk}">
    			<input type="text"  class="span10" placeholder="Вариант ответа" name="answer" id="inputTextAnsw" value="${record.getContent()}">
    			<c:if test="${record.isValid() == true}">
    				<input type="checkbox" value="" name="inputCheckAnsw" checked>
    			</c:if>
    			<c:if test="${record.isValid() != true}">
    				<input type="checkbox" value="" name="inputCheckAnsw">
    			</c:if>
    			<button class="btn btn-primary  btn-custom-login offset0" type="button" onclick="removeAnswerLayout(event);" id="inAns${chk}">Исключить</button>
    		</div><br id="inAns${chk}" />
    		<c:set var="chk" value="${chk+1}"/>
    	</c:forEach>		
	</div>