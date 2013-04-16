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

function appendQueshion() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/add.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'queshion' : $("input[name='queshion']").val(), 'content' : $("textarea#content").val(), 'category': $('#selectCategory :selected').val()};
	var key="";
	if ($("div.form-inline").length > 0){
		for (var i=0; i < $("div.form-inline").length; i++){
			key = 'inAns'.concat(i+1);	
			//alert($("div#"+ key + " input[name='inputCheckAnsw']").prop('checked'));
			json[key] = {'answer' : $("div#"+ key + " input[name='answer']").val(),'valid': $("div#"+ key + " input[name='inputCheckAnsw']").prop('checked')};
		}
	}
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Вопрос с заданными характеристиками существует или проблеммы на сервере");
			}
			if(data['error'] == 0) {
				alert("Вопрос успешно добавлен");
				location.reload();
			}
			$("#selectCategory").prop('selectedIndex', -1);
			$("input[name='queshion']").val('');
			$("textarea#content").val('');
		});
	return 0;
}

function appendAnswerLayout(){
	var id="inAns".concat($("div.form-inline").length+1);
	var rmbutton='<button class="btn btn-small btn-primary offset0" type="button" onclick="removeAnswerLayout();" id='+id+'>Исключить</button>  ';
	var inanswer='<input type="text"  class="span10" placeholder="Вариант ответа" name="answer" id="inputTextAnsw">  ';
	var validans = '<input type="checkbox" value="" name="inputCheckAnsw">  ';
	var payload = '<div class = "form-inline" id='+id+'>'+inanswer + validans + rmbutton + ' </div>' + '<br id=' + id +' />' ;
	$("#answer").append(payload);
	return 0;
}

function removeAnswerLayout(){
	$("br#".concat($(event.target).attr("id"))).remove();
	$("div#".concat($(event.target).attr("id"))).remove();
}

</script>