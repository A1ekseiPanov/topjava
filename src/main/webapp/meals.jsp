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
<%--<p><a href="${pageContext.request.contextPath}/meals/add">Add meal</a></p>--%>
<p><a href="meals?action=create">Add meal</a></p>
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
        <tr ${m.excess ? 'style="color: red"' : 'style="color: green"'}>
            <td><fmt:parseDate value="${m.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${ parsedDateTime }"/></td>
            <td>${m.description}</td>
            <td>${m.calories}</td>
            <td><a href="meals?action=update&id=${m.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${m.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>