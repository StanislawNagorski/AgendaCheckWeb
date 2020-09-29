<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <link href="../static/css/home.css" th:href="@{/css/home.css}" rel="stylesheet"/>
    <title>eStah</title>
</head>
<body>

<div class="appScreen">
    <div class="dropZone">
        <div class="dropZoneInfo">
            <p>Tutaj przeciągnij pliki</p>
            <div class="iconTextBox">
                <img id="icon" src="../images/greyIcon.png">
                <p>1. Gessefa w formacie .xls</p>
            </div>
            <br>
            <div class="iconTextBox">
                <img src="../images/greyIcon.png">
                <p id="planQ">2. Raport godzin z PlanuQ</p>
            </div>
        </div>
    </div>

    <div class="ui-section">
        <label for="productivityTarget">Podaj cel produktywności:</label>
        <input type="number" id="productivityTarget" name="productivityTarget" value="1000"
               onkeypress="return (charCode !=8 && charCode ==0 ||
               (charCode >= 48 && charCode <= 57))" >
        <button type="button">Generuj raport</button>
    </div>

</div>

</body>
</html>