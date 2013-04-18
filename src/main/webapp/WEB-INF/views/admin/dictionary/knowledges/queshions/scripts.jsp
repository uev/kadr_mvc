<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function getQueshionItem(){
	$("input[name='queshion']").val($("input[name='radiogroup']:checked").val());
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

function popQueshion() {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/rm.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var json={'hash' : hash, 'queshion' : $("input[name='queshion']").val()};
	var jqxhr = $.post(hname,json, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Не удалось удалить вопрос");
			}
			if(data['error'] == 0) {
				alert("Вопрос удалён");
				location.reload();
			}
			$("input[name='queshion']").val('');
			$("input[name='radiogroup']").prop('checked', false);
		});
	return 0;
}

function getQueshionInfo(event) {
	//var hname = "http://localhost:8080/uev61/json/recbykey.html";
	
	//Lightview.show({url: "<h1>Терминал не связи</h1>", type: "html"});
	/*
	Lightview.show({
  url: '<h3>123</h3>',
  type: 'html',
  border: { size: 3, color: '#000', opacity: .6, radius:10 },
  options: {
    skin: 'mac',
    params: {
      controller: false
    }
  }
});
	*/
	//$.fancybox('123');
	var hname = "${pageContext.request.contextPath}/admin/dictionary/knowledges/queshions/getinfo.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var html="";
	//alert($(event.target).text());
	//alert("123");
	//$("#example").popover("show"); 
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
			if (data['error'] == 1) {
				alert("Не удалось удалить вопрос");
			}
			if(data['error'] == 0) {
				alert("Вопрос удалён");
		//		location.reload();
			}
		//	$("input[name='queshion']").val('');
			//$("input[name='radiogroup']").prop('checked', false);
		});
	
	return 0;
}

</script>