<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
    		<div class="span10" style="margin: 5 auto;">
    			<jsp:include page="../menu.jsp" />
  					<form action="${pageContext.request.contextPath}/admin/dictionary/persons/add.html" method="get"
  					 class="form-horizontal span9 offset2">
    					<div class="control-group">
    	 					<label class="control-label" for="inputPerson">Имя пользователя</label>
    	 						<div class="clearfix  controls">
         							<input type="text" placeholder="Имя пользователя" name="person" id="inputPerson" onchange="lookDb();">
         						</div>
    					</div>
    					<div class="control-group">
    	 					<label class="control-label" for="selectCountry">Страна</label>
         						<div class="clearfix controls">
              						<select  placeholder="Страна" name="country" id="selectCountry" onClick="getDict('#selectCountry');">
										<option></option>
			  						</select>
           	 					</div>      	
    					</div>    
    					<div class="control-group">
    	 					<label class="control-label" for="selectRegion">Регион</label>
         					<div class="clearfix controls">
              					<select  placeholder="Регион" name="region" id="selectRegion" onClick="getDict('#selectRegion');">
									<option></option>
			  					</select>
           	 				</div>      	
    					</div>    
    					<div class="control-group">
    	 					<label class="control-label" for="selectCity">Населённый пункт</label>
         						<div class="clearfix controls">
              						<select  placeholder="Город" name="city" id="selectCity" onClick="getDict('#selectCity');">
										<option></option>
			  						</select>
           	 					</div>      	
    					</div>
    					
             			<input class="btn btn-primary btn-custom-login offset4" type="submit" value="       Создать       ">
					</form>
					<table class="table table-striped">
    					<tbody>
	    				</tbody>
	    			</table>
			</div>    