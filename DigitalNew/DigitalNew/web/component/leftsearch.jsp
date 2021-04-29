<%-- 
    Document   : leftsearch
    Created on : Feb 24, 2021, 8:10:07 PM
    Author     : admin2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/left.css" rel="stylesheet" type="text/css"/>
        <link href="css/leftsearch.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="left">
            <c:if test="${error != null}">
                <h1>${error}</h1>
            </c:if>
            <c:if test="${error == null}">
                <!--set list digital by searching-->
                <c:forEach var="l" items="${listDigitalBySearch}">
                    <a href="HomePageServlet?id=${l.id}"><p class="title">${l.title}</p></a>
                    <div class="short_content">
                        <img class="short_content_img" src="${pathImage}${l.image}"/>
                        <p class="detail">${l.shortDes}</p>
                    </div>
                    <hr/>
                </c:forEach>
            </c:if>

        </div>
    </body>
</html>
