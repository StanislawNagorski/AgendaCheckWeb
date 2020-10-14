<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="resources/static/js/homeScripts.js"></script>
    <title>Uploader</title>
</head>
<body>
<h3>Załaduj pliki</h3>
Wybierz pliki w formacie .xlsx: <br />
<form method="post" action="up" enctype="multipart/form-data">
    Plik gessef: <input type="file" name="gessef" />
    <br>
    Plik raportu z planQ: <input type="file" name="planQ" />
    <br>
    <label for="productivityTarget">Podaj cel produktywności:</label>
    <input type="number" id="productivityTarget" name="productivityTarget" value="1000"
           onkeypress="return targetCheck()">
    <input type="submit" value="Generuj raport" />

</form>
</body>
</html>
