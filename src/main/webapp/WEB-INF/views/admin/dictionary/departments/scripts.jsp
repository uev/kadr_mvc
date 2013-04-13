<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function appendDep() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/departments/add.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'department' : $("input[name='department']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалост создать департамент");
			}
			if(data['error'] == 0) {
				alert("Департамент успешно создан");
			}
			$("input[name='department']").val('');
		});
	return 0;
}

function popDep() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/departments/rm.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'department' : $("input[name='department']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось удалить департамент");
			}
			if(data['error'] == 0) {
				alert("Департамент успешно удалён");
				location.reload();
			}
			$("input[name='department']").val('');
			$("input[name='radiogroup']").prop('checked', false);
		});
	return 0;
}

function loockDb(){
	var hname = "${pageContext.request.contextPath}/admin/dictionary/departments/list.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash};
	var jqxhr = $.get(hname,function() {
	})
		.success(function(data) {		
			$("input[name='department']").val('');
		});
	return 0;
}

function getDepartmentItem(){
	$("input[name='department']").val($("input[name='radiogroup']:checked").val());
	return 0;
}


</script>