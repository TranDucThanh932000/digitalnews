<%-- 
    Document   : error
    Created on : Mar 5, 2021, 10:42:29 AM
    Author     : admin2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/error.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="center">
            <jsp:include page="component/header.jsp"></jsp:include>
                <div class="main">
                    <div class="error">
                        <p class="text-error">${error}</p>
                    </div>
                </div>
            <jsp:include page="component/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
