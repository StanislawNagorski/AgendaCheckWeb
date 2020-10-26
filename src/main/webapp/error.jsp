<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html lang="en">
<script type="text/javascript">
    var appInsights=window.appInsights||function(a){
        function b(a){c[a]=function(){var b=arguments;c.queue.push(function(){c[a].apply(c,b)})}}var c={config:a},d=document,e=window;setTimeout(function(){var b=d.createElement("script");b.src=a.url||"https://az416426.vo.msecnd.net/scripts/a/ai.0.js",d.getElementsByTagName("script")[0].parentNode.appendChild(b)});try{c.cookie=d.cookie}catch(a){}c.queue=[];for(var f=["Event","Exception","Metric","PageView","Trace","Dependency"];f.length;)b("track"+f.pop());if(b("setAuthenticatedUserContext"),b("clearAuthenticatedUserContext"),b("startTrackEvent"),b("stopTrackEvent"),b("startTrackPage"),b("stopTrackPage"),b("flush"),!a.disableExceptionTracking){f="onerror",b("_"+f);var g=e[f];e[f]=function(a,b,d,e,h){var i=g&&g(a,b,d,e,h);return!0!==i&&c["_"+f](a,b,d,e,h),i}}return c
    }({
        instrumentationKey:"93365a0f-c064-4304-83ae-ca3660f91764"
    });
    window.appInsights=appInsights,appInsights.queue&&0===appInsights.queue.length&&appInsights.trackPageView();
</script>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<h3> (✖╭╮✖) Coś poszło nie tak... </h3>
<h3> Ale spokojnie ogarniemy temat! (ง'̀-'́)ง</h3>
<h4> 1. Sprawdź filmy instruktażowe poniżej o poprawności plików </h4>
<h4> 2. Jeśli wszystko wydaje się ok, wyślij proszę pliki do mnie:</h4>
<h4> stanislaw.nagorski@decathlon.com</h4>
<h3><a href="uploader.jsp"> Powrót do głównej </a> </h3>
</body>
</html>