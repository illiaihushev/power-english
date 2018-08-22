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
    <input class="hidden" type="text" name="bookId" id="bookId" form="saveForm">
    Chapter:<input type="text" name="chapter" id="chapter" form="saveForm">
    <p>
        Page:<input type="text" name="page" id="page" form="saveForm">
    <p>
    <div class="titleOriginalTranslation">
        <div class="leftPart">
            <h3>Original</h3>
        </div>
        <div class="rightPart">
            <h3>Translation</h3>
        </div>
    </div>
    <p>
    <div class="excerpt">
        <div>
            <div class="divNewBookExcerptText">
                <textarea id="idTxtOriginal" class="area"
                          name="txtOriginal"
                          form="saveForm"></textarea>
            </div>
        </div>
        <div>
            <div class="divNewBookExcerptText">
                <textarea id="idTxtTranslation" class="area"
                          name="txtTranslation"
                          form="saveForm"></textarea>
            </div>
        </div>
    </div>
    <div class="divSaveExcerpt">
        <form id="saveForm" name="confirmationForm"
              method="post">
            <input type="submit" value="Save">
        </form>
    </div>
</div>
<script>
    document.getElementById("bookId").value = "${bookId}";
</script>
</body>
