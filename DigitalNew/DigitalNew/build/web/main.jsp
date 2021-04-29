<%-- 
    Document   : main
    Created on : Feb 22, 2021, 5:14:59 PM
    Author     : admin2
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="center">
            <jsp:include page="component/header.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="component/left.jsp"></jsp:include>
                <jsp:include page="component/right.jsp"></jsp:include>
                </div>
            <jsp:include page="component/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
