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
        <li><a href="${root}/view/films.jsps.jsp">Films</a></li>
    </ul>
</div>
<div class="mainPart" id="main">
    <div class="booksTitle">
        <h1>${login}'s books</h1>
    </div>
    <p></p>
    <a href="./new">New book</a>
    <p></p>
    <c:set var="counter" value="0" scope="page"/>
    <c:forEach var="book" items="${books}">
        <c:choose>
            <c:when test="${counter == 0}">
                <div class="bookInfoBlock">
                    <b>Title</b>
                </div>
                <div class="bookInfoBlock">
                    <b>Author</b>
                </div>
                <div class="smallBookInfoBlock">
                    <b>Year</b>
                </div>
                <div class="smallBookInfoBlock">

                </div>
                <div class="bookInfoBlock">

                </div>
            </c:when>
        </c:choose>
        <div class="bookInfoBlock">
            <a href="./${book.getBookId()}">${book.getBookName()}</a>
        </div>
        <div class="bookInfoBlock">
            <c:out value="${book.getAuthor()}"></c:out>
        </div>
        <div class="smallBookInfoBlock">
            <c:out value="${book.getReleaseDate().toLocalDate().getYear()}"></c:out>
        </div>
        <div class="smallBookInfoBlock">
            <a href="${root}/books/edit/${book.getBookId()}">Edit</a>
        </div>
        <div class="bookInfoBlock">
            <button class="likeHref" id="${book.getBookId()}">Delete</button>
        </div>
        <c:set var="counter" value="${counter + 1}" scope="page"/>
    </c:forEach>
</div>
<script src="${root}/scripts/jquery-3.3.1.min.js"></script>
<script>
    $(".likeHref").click(function (event) {
        $.ajax({
            url: '${root}/books/delete',
            type: 'POST',
            data: {
                "id": event.target.id
            },
            success: function (msg) {
                alert("Book deleted");
                location.reload();
            }
        });
    });
</script>
<%--<c:out value="${pathInfoo}"/>--%>
<%--<c:out value="${pageContext.request.pathInfo}"/>--%>
<%--<c:out value="${pageContext.request.pathInfo}"/>--%>
<%--<c:out value="${pageContext.request.pathTranslated}"/>--%>
<%--<c:out value="${pageContext.request.contextPath}"/>--%>
<%--<c:out value="${pageContext.request.requestURI}"/>--%>
<%--<c:out value="${pageContext.request.requestURL}"/>--%>
<%--<c:out value="${application.realPath}"/>--%>
<%--<c:out value="${pageContext.request.queryString}"/>--%>

</body>
</html>