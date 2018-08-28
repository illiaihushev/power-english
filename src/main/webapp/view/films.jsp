<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@page pageEncoding="UTF-8" %>--%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${root}/css/styles.css"/>
</head>
<body>
<div id="menu" class="sideMenu">
    <ul>
        <li><a href="${root}/home/">Home</a></li>
        <li><a href="${root}/books/">Books</a></li>
        <li><a href="${root}/view/films.jsp">Films</a></li>
    </ul>
</div>
<div class="mainPart" id="main">
    <h1>Coming soon...</h1>
</div>
</body>
</html>