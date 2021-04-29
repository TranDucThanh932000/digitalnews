
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/search.css" rel="stylesheet" type="text/css"/>
        <title>Search page</title>
    </head>
    <body>
        <div class="center">
            <jsp:include page="component/header.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="component/leftsearch.jsp"></jsp:include>
                <jsp:include page="component/right.jsp"></jsp:include>
                </div>
                <div class="paging">
                <c:forEach var="i" begin="1" end="${numOfPage}">
                    <c:choose>
                        <c:when test="${index==i}">
                            <button class="action">${i}</button>
                        </c:when>
                        <c:otherwise>
                            <button><a href="SearchServlet?index=${i}&txtSearch=${txtSearch}">${i}</a></button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <jsp:include page="component/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
