<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="uploader.jsp">Powrót</a>
<br>
<a href="<c:url value = "${reportDownloadPath}"/>"> Pobierz raport</a>
</body>
</html>
