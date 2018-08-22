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
    <h1>${sessionScope.username}</h1>
    <p></p>
    Choose one of menu item on the right.
    <p></p>
    <button class="likeHref" id="logoutButton">Log out</button>
</div>
<script src="${root}/scripts/jquery-3.3.1.min.js"></script>
<script>
    $(".likeHref").click(function (event) {
        if (event.target.id ==  'logoutButton') {
            $.ajax({
                url: '${root}/home/',
                type: 'POST',
                data: {
                    "id": event.target.id
                },
                success: function (msg) {
                    location.reload();
                }
            });
        }
    });
</script>
</body>
</html>
