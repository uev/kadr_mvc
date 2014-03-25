<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function appendCert() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/certification/add.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'cert' : $("input[name='cert']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось создать аттестацию");
			}
			if(data['error'] == 0) {
				alert("Аттестация создана");
			}
			$("input[name='cert']").val('');
			location.reload();
		});
	return 0;
}

function popCert(event) {
	var hname = "${pageContext.request.contextPath}/admin/certification/rm.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'id' : $(event.target).attr("id")};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось удалить аттестацию. Обратитесь к администратору.");
			}
			if(data['error'] == 0) {
				alert("Запись удалена");
			}
			location.reload();
		});
	return 0;
}


//==

	function getEmployers(){
	//var hname = "http://localhost:8080/uev61/json/";
		var hname = "${pageContext.request.contextPath}/admin/certification/getemployers.html";
		var hash = "dcd95bcb84b09897b2b66d4684c040da";
		json = {"hash":hash, "department": $('#selectDepartment :selected').val()};
		var jqxhr = $.post(hname,json, function() {
		}).done(function(data) { 
			$('#selectEmploye').empty();
			$.each(data, function (index, value) {
		 		$('#selectEmploye').append($('<option>', { 
		       		value: value.id,
		       		text : value.fio 
		 }));
	})});
	return 0;
	}
	
	function setTest(event) {
		var hname = "${pageContext.request.contextPath}/admin/certification/set_test.html";
		var hash = "dcd95bcb84b09897b2b66d4684c040da";
		var json={'hash' : hash, 'certification' : $("form.form-inline fieldset#certtitle legend").text(), 'testname' : $("#selectTest :selected").val()};
		var jqxhr = $.post(hname, json, function() {
		})
			.success(function(data) {		
				if (data['error'] == 1) {
					alert("Ошибка закрепления теста за аттестацией");
				}
				if(data['error'] == 0) {
					alert("Тест закреплён за аттестацией");
					//window.location.replace("${pageContext.request.contextPath}/admin/dictionary/knowledges/tests/manage.html");	
				}		
			});
		return 0;
	}	
	
	function appendPersonCertification(event){
		var hname = "${pageContext.request.contextPath}/admin/certification/append_person.html";
		var hash = "dcd95bcb84b09897b2b66d4684c040da";
		var json={'hash' : hash, 'certification' : $("form.form-inline fieldset#certtitle legend").text(), 'employe' : $("#selectEmploye :selected").val()};
		var jqxhr = $.post(hname,json, function() {
		})
			.success(function(data) {		
				if (data['error'] == 1) {
					alert("Не удаётся добавить специалиста к аттестации. Обратитесь к разработчикам");
				}
				if(data['error'] == 0) {
					alert("Специалист добавлен к аттестации");
					window.location.replace("${pageContext.request.contextPath}/admin/certification/manage.html");
					//location.reload();
				}
				//location.reload();
			});
		
		return 0;
	}
	
	function popPersonFromCertification(event){
		var hname = "${pageContext.request.contextPath}/admin/certification/pop_person.html";
		var hash = "dcd95bcb84b09897b2b66d4684c040da";
		var json={'hash' : hash, 'certification' : $("form.form-inline fieldset#certtitle legend").text(), 'employe' : $(event.target).attr("id")};
		var jqxhr = $.post(hname,json, function() {
		})
			.success(function(data) {		
				if (data['error'] == 1) {
					alert("Не удалить исключить сотрудника из аттестации. Обратитесь к администратору");
				}
				if(data['error'] == 0) {
					alert("Сотрудник исключен из аттестации");
					location.reload();
				}
//				location.reload();
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