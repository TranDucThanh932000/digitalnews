<%-- 
    Document   : right
    Created on : Feb 23, 2021, 3:10:30 PM
    Author     : admin2
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/right.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="border_dotted">
            <div class="right">
                <p class="title">Digital News</p>
                <p class="detail">${mostRecentShortNew}</p>
                <div>
                    <p class="title">Search</p>
                    <form action="SearchServlet" method="get"> 
                        <table>
                            <tr>
                                <td><input class="inputSearch" type="text" name="txtSearch" required=""/></td>
                                <td><button class="btnGo">Go</button></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div>
                    <p class="title">Last Articles</p>
                    <table>
                        <!-- set top 5 news , order by time publish desc-->
                        <c:forEach var="top" items="${topfive}">
                            <tr class="list_arts">
                                <td><a href="HomePageServlet?id=${top.id}">${top.title}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
