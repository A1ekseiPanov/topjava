<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>

<form method="post" action="${pageContext.request.contextPath}/meals">
    <input type="hidden" name="id" value="${m.id}">
    Date Time: <input type="datetime-local" name="dateTime" value="${m.dateTime}">
    <br/>
    Description: <input type="text" name="description" value="${m.description}">
    <br/>
    Calories: <input type="number" name="calories" value="${m.calories}">
    <br/>
    <input type="submit" value="Save"/>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
