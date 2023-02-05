<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="m">

        <c:choose>
            <c:when test="${m.excess == 'true'}">
                <tr style="color:red;">
            </c:when>
            <c:when test="${m.excess == 'false'}">
                <tr style="color:green;">
            </c:when>
        </c:choose>
        <td><fmt:parseDate value="${m.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
        <td><c:out value="${m.description}"/></td>
        <td><c:out value="${m.calories}"/></td>
        <td></td>
        <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>