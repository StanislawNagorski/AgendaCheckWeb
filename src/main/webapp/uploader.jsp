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
    <script src="resources/static/js/homeScripts.js"></script>
    <title>Uploader</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form method="post" action="up" enctype="multipart/form-data">
    Choose a gessef: <input type="file" name="gessef" />
    <br>
    Choose a planQ: <input type="file" name="planQ" />
    <br>
    <label for="productivityTarget">Podaj cel produktywności:</label>
    <input type="number" id="productivityTarget" name="productivityTarget" value="1000"
           onkeypress="return targetCheck()">
    <input type="submit" value="Upload" />
</form>
</body>
</html>
