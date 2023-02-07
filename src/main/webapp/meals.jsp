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
<p><a href="${pageContext.request.contextPath}/add">Add User</a></p>
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
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
            <td>"${m.description}"</td>
            <td>${m.calories}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/delete">
                    <input hidden name="id" value="${m.id}"/>
                    <input type="submit" name="delete" value="Удалить"/>
                </form>
            </td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}/update">
                    <input hidden name="id" value="${m.id}"/>
                    <input type="submit" value="Редактировать"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>