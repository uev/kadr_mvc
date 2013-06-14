<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
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
</script>