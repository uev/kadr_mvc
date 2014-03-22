<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
/*
function getDict(arg){
	//var hname = "http://localhost:8080/uev61/json/";
	var hname = "json/";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={};
	var cur =  $(arg.concat(' :selected')).val();
	switch(arg){
		case "#selectDepartment":
			var hname = "${pageContext.request.contextPath}/admin/dictionary/departments/list.html";
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
*/

function bindDep() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/persons/bind_dep.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'fio' : $("input[name='person']").val(), 'department': $('#selectDepartment :selected').text()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось добавить пользователя к департаменту");
			}
			if(data['error'] == 0) {
				alert("Пользователь добавлен");
				location.reload();
			}
			$("input[name='person']").val('');
			$("input[name='radiogroup']").prop('checked', false);
		});
	return 0;
}

function preProcessingRegionForms(){
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/persons/add.html";
	var json={'hash' : hash, 'person': $("input[name='person']").val(), 'city' : $('#selectCity').val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Одно из полей не заполнено!");
				return 0;
			}
			if(data['error'] == 0) {
				alert("Пользователь добавлен");
				window.location.replace("${pageContext.request.contextPath}/admin/dictionary/persons/bind_dep.html");
			}
			//
		});
/*	    
	    if(!$('#selectCountry').val() || !$('#selectRegion').val() || !$('#selectCity').val()){
		      alert('Регион не найден');
		      return 0;
		} else {
			   window.location.replace("${pageContext.request.contextPath}/admin/bindlogin.html");
		} 
*/
return 0;
}

function getFioItem(){
	$("input[name='person']").val($("input[name='radiogroup']:checked").val());
	return 0;
}

function popEmploye(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/persons/rm.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'id' : $(event.target).attr("id")};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось удалить запись");
			}
			if(data['error'] == 0) {
				alert("Запись удалёна");
				location.reload();
			}
			location.reload();
		});
	return 0;
}

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
		case "#selectDepartment":
			var hname = "${pageContext.request.contextPath}/admin/dictionary/departments/list.html";
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