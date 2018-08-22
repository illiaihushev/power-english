<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@page pageEncoding="UTF-8" %>--%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${root}/css/styles.css"/>

</head>
<body>
<div id="menu" class="sideMenu">
    <ul>
        <li><a href="${root}/home/">Home</a></li>
        <li><a href="${root}/books/">Books</a></li>
        <li><a href="${root}/films.jsp">Films</a></li>
    </ul>
</div>
<div class="mainPart">
    <form method="post">
        Title:<input type="text" name="bookName" id="bookName" value="${bookName}">
        <p>
            Author:<input type="text" name="author" id="author" value="${author}">
        <p>
            Release year:<input type="text" name="date" id="date" value="${date}">
        <p>
            <input type="submit" value="Save">
    </form>
</div>
</body>
