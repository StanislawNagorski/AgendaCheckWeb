<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Uploader</title>
</head>
<body>
<h3>Załaduj pliki</h3>
Wybierz pliki w formacie .xlsx: <br/>
<form method="post" action="up" enctype="multipart/form-data" onsubmit="return checkForm(this)">
    <p id="gessefTxt"> Plik gessef: </p> <input type="file" name="gessef"/>
    <br>
    <p id="planQTxt"> Plik raportu z planQ: </p><input type="file" name="planQ"/>
    <br>
    <label for="productivityTarget">Podaj cel produktywności:</label>
    <input type="number" id="productivityTarget" name="productivityTarget" value="1000"
           onkeypress="targetCheck()">
    <input type="submit" value="Generuj raport"/>
</form>


<script language="JavaScript" type="text/javascript" src="resources/static/js/homeScripts.js"></script>
</body>
</html>
