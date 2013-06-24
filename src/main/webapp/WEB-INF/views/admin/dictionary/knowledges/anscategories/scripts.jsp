<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function getCategoryItem(){
	$("input[name='category']").val($("input[name='radiogroup']:checked").val());
	return 0;
}

function appendCategory() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/add.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'category' : $("input[name='category']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалост создать категорию");
			}
			if(data['error'] == 0) {
				alert("Категория успешно создана");
			}
			$("input[name='category']").val('');
		});
	return 0;
}


function popCategory(event) {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/anscategories/rm.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	//var json={'hash' : hash, 'category' : $("input[name='category']").val()};
	var json={'hash' : hash, 'category' : $(event.target).attr("id")};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось удалить категорию");
			}
			if(data['error'] == 0) {
				alert("Категория успешно удалена");
				location.reload();
			}
			$("input[name='category']").val('');
			$("input[name='radiogroup']").prop('checked', false);
		});
	return 0;
}
</script>