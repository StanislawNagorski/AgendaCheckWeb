<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
<%--    <link href="resources/static/css/home.css" rel="stylesheet"/>--%>
    <script src="resources/static/js/homeScripts.js"></script>
    <title>eStah</title>
</head>
<body>

<div class="appScreen">
    <form action="home" method="post" enctype = "multipart/form-data">
        <div class="dropZone">
            <p>Tutaj załaduj pliki pliki</p>

            <div class="fileUpload">
                <label for="gessef">1. Gessefa w formacie .xls </label>
                <input type="file" id="gessef" name="gessef" accept=".xlsx">
                <%--                    <img class="icon" src="resources/static/images/greyIcon.png">--%>
            </div>

            <br>
            <div class="fileUpload">
                <label for="planQ">2. Raport godzin z PlanuQ </label>
                <input type="file" id="planQ" name="planQ" accept=".xlsx">
                <%--                    <img class="icon" src="resources/static/images/greyIcon.png">--%>
            </div>
        </div>

        <div class="productivityTargetSection">
            <label for="productivityTarget">Podaj cel produktywności:</label>
            <input type="number" id="productivityTarget" name="productivityTarget" value="1000"
                   onkeypress="return targetCheck()">
            <button type="submit">Generuj raport</button>
        </div>

    </form>
</div>

</body>
</html>