<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>


<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, intial-scale=1.0">
    <link href="resources/static/css/style.css" rel="stylesheet">
    <meta name="autor" content="funcion Stanisław Nagorski, style Mariusz Wojtkiewicz">
    <meta name="description" content="">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;900&display=swap" rel="stylesheet">
    <script type="text/javascript">
        var appInsights=window.appInsights||function(a){
            function b(a){c[a]=function(){var b=arguments;c.queue.push(function(){c[a].apply(c,b)})}}var c={config:a},d=document,e=window;setTimeout(function(){var b=d.createElement("script");b.src=a.url||"https://az416426.vo.msecnd.net/scripts/a/ai.0.js",d.getElementsByTagName("script")[0].parentNode.appendChild(b)});try{c.cookie=d.cookie}catch(a){}c.queue=[];for(var f=["Event","Exception","Metric","PageView","Trace","Dependency"];f.length;)b("track"+f.pop());if(b("setAuthenticatedUserContext"),b("clearAuthenticatedUserContext"),b("startTrackEvent"),b("stopTrackEvent"),b("startTrackPage"),b("stopTrackPage"),b("flush"),!a.disableExceptionTracking){f="onerror",b("_"+f);var g=e[f];e[f]=function(a,b,d,e,h){var i=g&&g(a,b,d,e,h);return!0!==i&&c["_"+f](a,b,d,e,h),i}}return c
        }({
            instrumentationKey:"93365a0f-c064-4304-83ae-ca3660f91764"
        });

        window.appInsights=appInsights,appInsights.queue&&0===appInsights.queue.length&&appInsights.trackPageView();
    </script>

    <script defer src="resources/static/js/script.js"></script>

    <head>
        <title>Uploader</title>
    </head>

<body>
<div class="monitor">
    <p class="signature">App: Stanisław Nagórski style: Mariusz Wojtkiewicz</p>
    <div class="container">

        <fieldset class="fieldset">

            <legend class="legend">Sukces</legend>


            <div style="text-align: center;">
                <h4> Raport stworzony poprawnie</h4>
            </div>

            <div class="text_icon" style="height: 100px; text-align: center;">
                <h5 class="flexbox_element"> Kliknij w ikonę aby go pobrać</h5>
                <a class="flexbox_element" href="<c:url value = "${reportDownloadPath}"/>">
                    <img onclick="appInsights.trackEvent('Clicked on raport download')" class="icon" src="resources/static/images/spreadsheet.gif"> </a>

            </div>

        </fieldset>

        <a href="uploader.jsp">
            <button class="submit__button" type="button">Powrót</button>
        </a>

    </div>
</div>

<script language="JavaScript" type="text/javascript" src="resources/static/js/script.js"></script>
</body>

</html>


