<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@page pageEncoding="UTF-8" %>--%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<body>
<form method="post">
    Login:<input type="text" name="username"><p>
    Password:<input type="password" name="password"><p>
    <input type="submit" value="Log in"> Don't have an account, register <a href="${root}/register">here</a>.
</form>
</body>
</html>
