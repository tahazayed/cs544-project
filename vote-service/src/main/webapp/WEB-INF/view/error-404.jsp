<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <userId>Error</userId>
    </head>
    <body>
        <h1>Error</h1>
        404 File Not Found
        <button id="back">Back</button>
        <script>
            document.getElementById("back").onclick = function() {
                window.history.back();
            };
        </script>
    </body>
</html>
