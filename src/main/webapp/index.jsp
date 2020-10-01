<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="resources/static/css/home.css" rel="stylesheet"/>
    <script src="resources/static/js/homeScripts.js"></script>
    <title>eStah</title>
</head>
<body>

<div class="appScreen">
    <div class="dropZone">
        <div class="dropZoneInfo">
            <p>Tutaj przeciągnij pliki</p>
            <div class="iconTextBox">
                <img class="icon" src="resources/static/images/greyIcon.png">
                <p>1. Gessefa w formacie .xls</p>

            </div>
            <br>
            <div class="iconTextBox">
                <img class="icon" src="resources/static/images/greyIcon.png">
                <p id="planQ">2. Raport godzin z PlanuQ</p>
            </div>
        </div>
    </div>

    <div class="ui-section">
        <label for="productivityTarget">Podaj cel produktywności:</label>
        <input id="inputTarget"  type="number" id="productivityTarget" name="productivityTarget" value="1000"
               onkeypress="return targetCheck()" >
        <button type="button">Generuj raport</button>
    </div>

</div>

</body>
</html>