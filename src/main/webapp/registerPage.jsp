<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@page pageEncoding="UTF-8" %>--%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
</head>
<body>
<form method="post">
    Login:<input type="text" name="login">
    <p>
        Password:<input type="password" name="password">
    <p>
        <input type="submit" value="Register">
</form>
</body>
</html>
