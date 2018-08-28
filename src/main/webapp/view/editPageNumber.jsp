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
        <li><a href="${root}/view/films.jsps.jsp">Films</a></li>
    </ul>
</div>
<div class="mainPart">
    <form <%--action="./"--%> method="post">
        Chapter:<input type="text" name="chapter" id="chapter" value="${chapter}">
        <p>
            Page:<input type="text" name="page" id="page" value="${page}">
        <p>
            <input type="submit" value="Save">
    </form>
</div>
</body>
</html>
