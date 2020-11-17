<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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

        <title>Uploader</title>
    </head>

<body>
<div class="monitor">
    <p class="signature">App: Stanisław Nagórski style: Mariusz Wojtkiewicz</p>
    <div class="container">
        <form class="form" method="post" action="up" enctype="multipart/form-data"
              onsubmit="return checkForm(this)">
            <fieldset class="fieldset">
                <legend class="legend">Załaduj pliki</legend>
                <p class="notice">*Wybierz pliki w formacie .xlsx:</p>
                <p>
                    <label>
                        <span class="form__element" id="gessefTxt"> Plik gessef:</span>
                        <input class="form__label__first" type="file" name="gessef" accept=".xlsx" required>
                    </label>
                </p>
                <p>
                    <label>
                        <span class="form__element" id="planQTxt"> Plik raportu z planQ:</span>
                        <input class="form__label" type="file" name="planQ" accept=".xlsx" required>
                    </label>
                </p>
                <p>
                    <label class="form__element" for="productivityTarget">Podaj cel produktywności:
                    </label>
                    <input class="form__label" type="number" id="productivityTarget" name="productivityTarget"
                           value="1000" onkeypress="targetCheck()">
                </p>
            </fieldset>
            <button class="submit__button" type="submit">Generuj raport</button>
        </form>
        <div id="processing" style="visibility: hidden">
            <img class="loading__bar" src="resources/static/images/loading.gif">
        </div>
    </div>
</div>


<script language="JavaScript" type="text/javascript" src="resources/static/js/script.js"></script>
</body>

</html>