<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@page pageEncoding="UTF-8" %>--%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${root}/css/styles.css"/>
</head>
<body>
<c:set var="counter" value="0" scope="page"/>
<div id="menu" class="sideMenu">
    <ul>
        <li><a href="${root}/home/">Home</a></li>
        <li><a href="${root}/books/">Books</a></li>
        <li><a href="${root}/view/films.jsps.jsp">Films</a></li>
    </ul>
</div>
<div class="mainPart">
    <div class="excerptsTitle">
        <h1>Excerpts of ${bookName}</h1>
    </div>
    <div>
        <a class="aNewExcerpt" href="${root}/bookExcerpts/new?id=${bookId}">New excerpt</a>
    </div>
    <c:forEach var="excerpt" items="${excerpts}">
        <c:choose>
            <c:when test="${counter == 0}">
                <div class="titleOriginalTranslation">
                    <div class="leftPart">
                        <h3>Original</h3>
                    </div>
                    <div class="rightPart">
                        <h3>Translation</h3>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <div class="titleExcerpt">
            <div class="leftPart">
                <c:choose>
                    <c:when test="${excerpt.getPage() > 0}">
                        <c:out value="page:${excerpt.getPage()}"></c:out>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${excerpt.getChapter() > 0}">
                        <c:out value="chapter:${excerpt.getChapter()}"></c:out>
                    </c:when>
                </c:choose>
            </div>
            <div class="rightPart">
                <button class="likeHref deleteExcerpt" id="${excerpt.getBookExcerptId()}">
                    Delete excerpt
                </button>
                <a href="${root}/bookExcerpts/edit/${excerpt.getBookExcerptId()}">Edit number of chapter and/or page</a>
            </div>
        </div>
        <div class="excerpt">
            <div>
                <div class="divText">
                <textarea id="${excerpt.getBookExcerptId()}" class="area"
                          name="txtOriginal${excerpt.getBookExcerptId()}"
                          form="formOriginal${excerpt.getBookExcerptId()}">${excerpt.getOriginal()}</textarea>
                </div>
                <div class="divButtons">
                    <form action="${root}/books/${bookId}" id="formOriginal${excerpt.getBookExcerptId()}"
                          name="confirmationForm"
                          method="post">
                        <input type="submit" value="Save" class="control">
                    </form>
                </div>
            </div>
            <div>
                <div class="divText">
                <textarea id="${excerpt.getBookExcerptId()}" class="area"
                          name="txtTranslation${excerpt.getBookExcerptId()}"
                          form="formTranslation${excerpt.getBookExcerptId()}">${excerpt.getTranslation()}</textarea>
                </div>
                <div class="divButtons">
                    <form action="${root}/books/${bookId}" id="formTranslation${excerpt.getBookExcerptId()}"
                          name="confirmationForm"
                          method="post">
                        <input type="submit" value="Save" class="control">
                    </form>
                </div>
            </div>
        </div>
        <c:set var="counter" value="${counter + 1}" scope="page"/>
    </c:forEach>
</div>
<%--<c:out value="${pathInfo}"/>--%>
<%--<c:out value="${username}"/>--%>

<script src="${root}/scripts/jquery-3.3.1.min.js"></script>
<script>
    $("form").submit(function (e) {
        var str = $(this).serialize();
        // alert(decodeURIComponent(str));
        <%--alert(${sessionScope.userId});--%>
        $.ajax({
            url: '${root}/books',
            type: 'POST',
            data: {
                "txt": decodeURIComponent(str)
            },
            success: function (msg) {
                alert('Text saved');
                location.reload()
            }
        });
        // e.preventDefault();
        // alert(xhr.responseURL);
    });
    // $("form").submit(function (e) {
    //     var str = $(this).serialize();
    //     $.ajax({
    //         url: 'someservlet',
    //         type: 'POST',
    //         data: {"txt": unescape(str)},
    //         success: function (msg) {
    //             alert('Email Sent');
    //         }
    //     });
    //     e.preventDefault();
    // });
    $(".likeHref").click(function (event) {
        $.ajax({
            url: '${root}/bookExcerpts/delete',
            type: 'POST',
            data: {
                "id": event.target.id
            },
            success: function (msg) {
                alert("Excerpt deleted");
                location.reload();
            }
        });
        alert(event.target.id);
    });
</script>
</body>
</html>