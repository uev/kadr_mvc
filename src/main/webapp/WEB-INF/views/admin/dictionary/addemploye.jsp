<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/default/header.jsp" />

<script>
//Загрузка адресного словаря
function getDict(arg){
	//var hname = "http://localhost:8080/uev61/json/";
	var hname = "json/";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={};
	var cur =  $(arg.concat(' :selected')).val();
	//alert(cur);
	switch(arg){
		case "#selectCountry":
			//$('#selectCountry').find('option').remove().end();
			$('#selectRegion').find('option').remove().end();
			$('#selectCity').find('option').remove().end();
			hname = hname.concat("getcountry.html");
			json = {"hash":hash};
			break;
		case "#selectRegion":
			//$('#selectRegion').find('option').remove().end();
			$('#selectCity').find('option').remove().end();
			hname = hname.concat("getregion.html");
			json = {"hash":hash, "id": $('#selectCountry :selected').val()};
			break;
		case "#selectCity":
			//$('#selectCity').find('option').remove().end();
			hname = hname.concat("getcity.html");
			json = {"hash":hash, "id": $('#selectRegion :selected').val()};
			break;
		case "#selectDeaprtment":
			hname = "${pageContext.request.contextPath}/admin/dictionary/departments/list.html";
			json = {"hash":hash};
			break;
	}
	if (cur == "" || cur ==undefined) {
		var jqxhr = $.post(hname,json, function() {
		})
			.done(function(data) { 
				$.each(data, function (index, value) {
		    		$(arg).append($('<option>', { 
		        		value: value.id,
		        		text : value.name 
		 }));
	})});
	}
	return 0;
}

function json2td(t){
	$.each(t,  function(key,val){
		if (null !== /^fk.*/g.exec(key)){
			json2td(val);
		} else {
			$('.table tr:last').after("<td>" + val + "</td>");
		}
	});
	return 0;
}


function lookDb() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "json/recbykey.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'key' : $("input[name='person']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			$(".table-striped tbody").each(function(){$(this).remove();});
			if (data.length > 0){
				$('<tr></tr>').appendTo('.table-striped');
				$("<td>id</td><td>ФИО</td><td>Подразделение</td><td>Страна</td><td>Регион</td><td>Населённый пункт</td>").appendTo('.table tr:last');
			}
			for (var key in data){
				$('<tr></tr>').appendTo('.table-striped');
				/*
				for (var item in data[key]){
					$("<td>" + data[key][item] + "</td>").appendTo('.table tr:last');
				}*/
				var d = data[key];
				$("<td>" + d['id'] + "</td>").appendTo('.table tr:last');
				$("<td>" + d['fio'] + "</td>").appendTo('.table tr:last');
				$("<td>" + d['department'] + "</td>").appendTo('.table tr:last');
				$("<td>" + d['country'] + "</td>").appendTo('.table tr:last');
				$("<td>" + d['region'] + "</td>").appendTo('.table tr:last');
				$("<td>" + d['city'] + "</td>").appendTo('.table tr:last');
			}
		});
	return 0;
}


function d(record){
	for (var item in record) {
		if (null !== /^fk.*/g.exec(item)){
			d(record[item]);
		} else {
			$("<td>" + record[item] + "</td>").appendTo('.table tr:last');	
		}	
	}
return 0;
}


</script>
</head>
<body>
<center><h1>Админзона / Создание профиля сотрудника</h1></center>
<div class="container-fluid">
	<div class="row-fluid" >
    	<div class="span2">
    		<jsp:include page="/WEB-INF/views/admin/menu.jsp" />
    	</div>
    <div class="span10" style="margin: 5 auto;">
<!-- UserManagment menu -->
    <ul class="breadcrumb">
    <li><a href="addemploye.html">Создание пользователя</a> <span class="divider">/</span></li>
    <li><a href="rmemploye.html">Удаление пользователя</a> <span class="divider">/</span></li>
    </ul>
    
  	<form action="http://localhost:8080/uev61/admin/dictionary/addemploye.html" method="get" class="form-horizontal span10 offset3">
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
    <div class="control-group">
    	 <label class="control-label" for="selectDepartment">Подразделение</label>
         	<div class="clearfix controls">
              	<select  placeholder="Подразделение" name="city" id="selectDepartment" onClick="getDict('#selectDepartment');">
					<option></option>
			  	</select>
           	 </div>      	
    </div>    
    
    
    
    
    
     
             <button class="btn btn-large btn-primary offset3" type="submit">Создать</button>
		</form>
		<table class="table table-striped">
    		<tbody>
	    	</tbody>
	    </table>


	<center>
	
	<c:if test="${employe != null}">
		<form action="admin.html?accounting&createuser" method="post">
		      <div class="clearfix">
                <input type="text" placeholder="Имя пользователя" name="person">
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
    
		</form>
	</c:if>


    </center>
    </div>
    
    </div>
</div>

</body>

<jsp:include page="/WEB-INF/views/default/footer.jsp" />
