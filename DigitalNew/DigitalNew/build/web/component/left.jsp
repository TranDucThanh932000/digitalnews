<%-- 
    Document   : left
    Created on : Feb 24, 2021, 7:28:26 PM
    Author     : admin2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/left.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="left">
            <c:if test="${error != null}">
                <h1>${error}</h1>
            </c:if>
            <c:if test="${error == null}">
                <p class="title">
                    ${digital.title}
                </p>
                <div class="img_content">
                    <img src="${pathImage}${digital.image}"/>
                </div>
                <p class="detail marginHr">${digital.content}</p>
                <hr/>
                <div class="infor">
                    <div class="imgCmt"></div>
                    <div class="imgTime"></div>
                    <p class="time">By ${digital.author} | ${digital.getDateAfterFormat()}</p>
                </div>
            </c:if>

        </div>
    </body>
</html>
