<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />
</head>
<body>
	<div class="container" align = "center">
    <div class="content">
      <div class="row">
        <div class="login-form">
          <h2>Вход в систему</h2>
          <form action="login.html" method="post">
            <fieldset>
              <div class="clearfix">
                <input type="text" placeholder="Имя пользователя" name="login">
              </div>
              <div class="clearfix">
                <input type="password" placeholder="Пароль" name="password">
              </div>
              <button class="btn btn-large btn-primary" type="submit">Войти</button>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div>
	

</body>
<jsp:include page="footer.jsp" />