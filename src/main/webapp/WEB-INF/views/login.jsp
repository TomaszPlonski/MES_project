<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--include head oraz górnego menu. Zawiera owarcie tagu <body>--%>
<%@include file="fragments/header.jsp"%>

<body>
<form method="post">
<div><label> User Name : <input type="text" name="username"/> </label></div>
<div><label> Password: <input type="password" name="password"/> </label></div>
<div><input type="submit" class="btn-primary" value="Sign In"/></div>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
