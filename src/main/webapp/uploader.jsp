<%--
  Created by IntelliJ IDEA.
  User: Gabi
  Date: 11.10.2020
  Time: 00:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uploader</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form method="post" action="up" enctype="multipart/form-data">
    Choose a file: <input type="file" name="multiPartServlet" />
    <input type="submit" value="Upload" />
</form>
</body>
</html>
