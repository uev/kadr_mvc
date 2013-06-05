<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function pushAnswers(event){
	var hname = "${pageContext.request.contextPath}/candidate/validatetest.html";
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var xml = '<?xml version="1.0" encoding="UTF-8"?><root><hash>'+hash+'</hash>';
	var queshion="";
	var answer="";
	var check="";
	$("tr.item").each(function() {
		if ($(this).attr("class").search("well") > -1){
			queshion=$(this).attr('id');
		} else {
			check=$(this).find("input[name=inputCheckAnsw]").prop('checked');
			answer=$(this).attr('id');
			xml += '<answer q="'+queshion+'" c="'+ check + '" a="' + answer + '" />"';
		}	
	});  
	xml+='</root>';
	var jqxhr = $.post(hname,{'xml':xml}, function() {
	})
		.success(function(data) {		
			if (data['error'] == 1) {
				alert("Проблема в центре обработки данных. Свяжитесь с менеджером");
			}
			if(data['error'] == 0) {
				alert("Данные переданы в центр обработки информации");
			}
			window.location.replace("${pageContext.request.contextPath}/");
		});
return 0;
}

</script>