<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function getTestItem(){
	$("input[name='test']").val($("input[name='radiogroup']:checked").val());
	return 0;
}

function appendTest() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/add.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'test' : $("input[name='test']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось создать тест");
			}
			if(data['error'] == 0) {
				alert("Тест создан. Можно наполнять");
			}
			$("input[name='test']").val('');
			location.reload();
		});
	return 0;
}

function popTest(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/rm.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'test' : $(event.target).attr("id")};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось удалить тест");
			}
			if(data['error'] == 0) {
				alert("Тест удалён");
				location.reload();
			}
			$("input[name='test']").val('');
			location.reload();
		});
	return 0;
}

function editTest(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/getinfo.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var html=""; 
	var json={'hash' : hash, 'queshion' : $(event.target).text()};
	//$.fancybox({ href : '${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/edit.html'});
	//$.fancybox({source : "ya.ru"});	
	return 0;
	/*
	
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			html="<h5>Категория: " + data['category'] + "<br/>Наздание вопроса: " + data['title'] + "<br/>Содержание: " + data['content'] + "</h5>";
			//alert(data['count_answ']);
			for (var i=0; i < data['count_answ']; i++){
				//alert("Вариант "+(i+1)+": "+data['ans'+i][0]);
				if (data['ans'+i][1] == true) {
					html += "Вариант "+(i+1)+": "+data['ans'+i][0] + " || правальный ответ <br/>";
				} else {
					html += "Вариант "+(i+1)+": "+data['ans'+i][0] + "<br/>";	
				}
			}
			//alert(html);
			$.fancybox(html);
		});
	return 0;
	
	
	
	*/
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

function updateQueshion(id_queshion) {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/update.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var acount = $("div.form-inline").length; 
	var json={'hash' : hash, 'queshion' : $("input[name='queshion']").val(), 'content' : $("textarea#content").val(), 'category': $('#selectCategory :selected').val(), 'queshion_id' : id_queshion, 'acount' : acount};
	var key="";
	var rm=1;
	if (acount > 0){
		for (var i=0; i <= acount; i++){
			key = 'inAns'.concat(i+rm);
			while ( $("div#"+ key + " input[name='answer']").val() == "undefined" ){
				rm+=1;
				key = 'inAns'.concat(i+rm);
			} 
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
				alert("Вопрос успешно обновлён");
				window.location.replace("${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/rm.html");
			}
			$("#selectCategory").prop('selectedIndex', -1);
			$("input[name='queshion']").val('');
			$("textarea#content").val('');
		});
	return 0;
}

function appendAnswerLayout(){
	var id="inAns".concat($("div.form-inline").length+1);
	var rmbutton='<button class="btn btn-small btn-primary offset0" type="button" onclick="removeAnswerLayout(event);" id='+id+'>Исключить</button>  ';
	var inanswer='<input type="text"  class="span10" placeholder="Вариант ответа" name="answer" id="inputTextAnsw">  ';
	var validans = '<input type="checkbox" value="" name="inputCheckAnsw">  ';
	var payload = '<div class = "form-inline" id='+id+'>'+inanswer + validans + rmbutton + ' </div>' + '<br id=' + id +' />' ;
	$("#answer").append(payload);
	return 0;
}

function removeAnswerLayout(event){
	$("br#".concat($(event.target).attr("id"))).remove();
	$("div.form-inline#".concat($(event.target).attr("id"))).remove();
	return 0;
}

function getQueshionInfo(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/getinfo.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var html=""; 
	var json={'hash' : hash, 'queshion' : $(event.target).text()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			html="<h5>Категория: " + data['category'] + "<br/>Наздание вопроса: " + data['title'] + "<br/>Содержание: " + data['content'] + "</h5>";
			//alert(data['count_answ']);
			for (var i=0; i < data['count_answ']; i++){
				//alert("Вариант "+(i+1)+": "+data['ans'+i][0]);
				if (data['ans'+i][1] == true) {
					html += "Вариант "+(i+1)+": "+data['ans'+i][0] + " || правальный ответ <br/>";
				} else {
					html += "Вариант "+(i+1)+": "+data['ans'+i][0] + "<br/>";	
				}
			}
			//alert(html);
			$.fancybox(html);
		});
	return 0;
}
</script>