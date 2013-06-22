<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	<ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/admin/accounting.html">Управление логинами</a></li>
    </ul>
--><br/> 
    	 <form class="form-inline">
            <fieldset>
              <div class="clearfix" align="center">
                
                	<input type="text" placeholder="Логин" name="login"><span class="divider" style="padding-right:5px;"> </span>
                	<input type="password" placeholder="Пароль" name="password">
                	<span class="divider" style="padding-right:5px;"> </span>Роль:<span class="divider" style="padding-right:5px;"> </span>
              		<select  placeholder="Роль" name="role" id="selectRole">
						<option></option>
						<c:forEach var="i" items="${roles}">
							<option>${i.rname}</option>
						</c:forEach>
					</select><span class="divider" style="padding-right:5px;"> </span>
			 		<input class="btn btn-primary btn-custom-login" onClick="bindlogin()" type="button" value="       Создать       " /><span class="divider" style="padding-right:5px;"> </span>
              
              </div>
              <br/>
           
		  <table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td></tr>
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
           
           
           <!-- 
    	 <form align="center">
            <fieldset>
              <div class="clearfix">
                <input type="text" placeholder="Логин" name="login">
              </div>
              <div class="clearfix">
                <input type="password" placeholder="Пароль" name="password">
              </div>
             <div class="clearfix">
              <select  placeholder="Роль" name="role" id="selectRole">
				<option></option>
				<c:forEach var="i" items="${roles}">
					<option>${i.rname}</option>
				</c:forEach>
			</select>
            </div>
            <input class="btn btn-primary btn-custom-login" onClick="bindlogin()" type="button" value="       Создать       " /><br/><br/>
		  <table class="table table-striped">
    		<tbody>
	    		<tr><td></td><td>id</td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td></tr>
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
           -->