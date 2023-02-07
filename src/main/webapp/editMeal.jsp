<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meals</h2>

<form method="post" action="${pageContext.request.contextPath}/update">
    Date Time: <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}"/>">
    <br/>
    Description: <input type="text" name="description" value="<c:out value="${meal.description}"/>">
    <br/>
    Calories: <input type="number" name="calories" value="<c:out value="${meal.calories}"/>">
    <br/>
    <input type="submit" value="Edit"/>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
