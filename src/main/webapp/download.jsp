<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Download report</title>
</head>
<body>
<p> productivity target: ${productivityTarget}</p>
<p> Gessef file size is ${gessef} in MB </p>
<p> PlanQ file size is ${planQ} in MB </p>
<p> Report created in ${duration} seconds. </p>
<p> Click on the link to download: <a href="<c:url value = "${reportPath}"/> ">Download a File</a> </p>

</body>
</html>
