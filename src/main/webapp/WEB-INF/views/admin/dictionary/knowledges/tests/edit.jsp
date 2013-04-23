<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

	 
	<form class="form-inline">
		<div class="clearfix controls">
			<label>Категория вопроса&nbsp&nbsp&nbsp&nbsp</label>
            	<select class="span10"  placeholder="Категория" name="country" id="selectCategory">
            		<option></option>
            		<c:forEach var="record" items="${categories}">
    		    		<c:if test="${record.id == queshion.getFk_catgory().getId()}">
    		    			<option selected><c:out value="${record.cname}"></c:out></option>
    		    		</c:if>
    				</c:forEach>
			  	</select>
           	 </div>      
	</form>
	
	 
    
    
    
    
    
    <!-- 
    <br/>
	<br/>
	<div class="controls controls-row">
    	<button class="btn btn-small btn-primary " type="button" onclick="updateQueshion(${queshion.getId()});">Обновить</button>
        <button class="btn btn-small btn-primary " type="button" onclick="appendAnswerLayout();">Добавить вариант ответа</button>
    </div>
	<hr/>
	<div id="answer" class="span10">
		
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
    			<button class="btn btn-small btn-primary offset0" type="button" onclick="removeAnswerLayout(event);" id="inAns${chk}">Исключить</button>
    		</div><br id="inAns${chk}" />
    		<c:set var="chk" value="${chk+1}"/>
    	</c:forEach>		
	</div>
	 -->