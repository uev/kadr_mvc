<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function bindlogin(){
	var hash = "dcd95bcb84b09897b2b66d4684c040da";
	var url = "${pageContext.request.contextPath}/admin/json/bindlogin.html";
	var json={'hash' : hash, 'login' : $("input[name='login']").val(),
			'password' : $("input[name='password']").val(),
			'role' : $("#selectRole :selected").val(),
			'idemp' : $("input[name='radiogroup']:checked").val()
			};
	var jqxhr = $.post(url,json, function() {
	})
		.done(function(data) { 
			if (data['error'] == 1) {
				alert("Логин уже связан с одним из пользователей или на сервере проблемы");
			}
			if(data['error'] == 0) {
				alert("Логин успешно привязан к пользователю");
			}
			//cleaning components
			$("input[name='radiogroup']").prop('checked', false);
			$("#selectRole").prop('selectedIndex', -1);
			$("input[name='password']").val('');
			$("input[name='login']").val('');
		});
	return 0;
}
</script>