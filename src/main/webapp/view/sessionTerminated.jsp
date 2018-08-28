<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
</head>
<body>
Session terminated, please <a href="${root}/logIn/">re-login</a>.
</body>
</html>
