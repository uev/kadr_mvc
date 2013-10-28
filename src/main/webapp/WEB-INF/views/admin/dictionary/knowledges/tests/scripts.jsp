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


function appendQuestionToTest(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/append_Question.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'testname' : "${testname}" , 'Questions' : []};
	//alert( $("tr#q1 input[name='inputCheckQuestion']").prop('checked') );
	//alert($(event.target).attr("id"));
	for (var i=1; i <= $("tr.Question").length; i++){
		if ( $("tr#q" + i + " input[name='inputCheckQuestion']").prop('checked') === true ) {
			json.Questions.push( $("tr#q" + i + " td.id_Question").text());
		}
	}
	var jqxhr = $.post(hname,JSON.stringify(json), function() {
	})
		.success(function(data) {		
			//alert(data['error']);
			if (data['error'] == 1) {
				alert("Ошибка прикрепления вопросов к тесту. Обратитесь к системному администратору");
			}
			if(data['error'] == 0) {
				alert("Вопросы успешно добавлены к тесту");
				window.location.replace("${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/manage.html");
				//location.reload();
			}
			//location.reload();
		});
	return 0;
}

function popQuestionFromTest(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/pop_Question.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'testname' : "${testname}" , 'Questions' : []};
	for (var i=1; i <= $("tr.Question").length; i++){
		if ( $("tr#q" + i + " input[name='inputCheckQuestion']").prop('checked') === true ) {
			json.Questions.push( $("tr#q" + i + " td.id_Question").text());
		}
	}
	var jqxhr = $.post(hname,JSON.stringify(json), function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Ошибка исключения вопросов из теста. Обратитесь к системному администратору");
			}
			if(data['error'] == 0) {
				alert("Вопросы успешноисключены");
				window.location.replace("${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/manage.html");	
			}		
		});
	return 0;
}



function editTest(event) {
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/Questions/getinfo.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var html=""; 
	var json={'hash' : hash, 'Question' : $(event.target).text()};
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

function getTestInfo(event){
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/getinfo.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var html=""; 
	var json={'hash' : hash, 'test' : $(event.target).text()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			html="<h5>Вопросы:<br/>";
			//alert(data['count_answ']);
			if (data.length > 0){
				for (var i=0; i < data.length; i++){
					html+= i+1+". "+data[i].name +"<br/>";
				}
				html+="</h5>";	
				$.fancybox(html);
			}
		});
	return 0;
}
</script>
<jsp:include page="../Questions/scripts.jsp" />